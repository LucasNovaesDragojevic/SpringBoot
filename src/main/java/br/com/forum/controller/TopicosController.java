package br.com.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.dto.DetalheTopicoDto;
import br.com.forum.dto.TopicoDto;
import br.com.forum.form.AtualizacaoTopicoForm;
import br.com.forum.form.TopicoForm;
import br.com.forum.model.Topico;
import br.com.forum.repository.CursoRepository;
import br.com.forum.repository.TopicoRepository;

@Transactional
@RestController
@RequestMapping("topicos")
public class TopicosController 
{
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	@Cacheable(value = "listaTopicos")
	public Page<TopicoDto> lista(@RequestParam(required = false) String cursoNome, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable paginacao)
	{
		if (cursoNome == null)
		{
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		}
		Page<Topico> topicos = topicoRepository.findByCursoNome(cursoNome, paginacao);
		return TopicoDto.converter(topicos);		
	}
	
	@PostMapping
	@CacheEvict(value = "listaTopicos", allEntries = true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder)
	{
		
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DetalheTopicoDto> detalhar(@PathVariable Long id)
	{
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent())
			return ResponseEntity.ok(new DetalheTopicoDto(topico.get()));
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "listaTopicos", allEntries = true)
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form)
	{
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent())
		{
			Topico topico = form.atualizar(optional.get());
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "listaTopicos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent())
		{
			topicoRepository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
