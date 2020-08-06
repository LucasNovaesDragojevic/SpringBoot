package br.com.forum.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.dto.TopicoDto;
import br.com.forum.form.TopicoForm;
import br.com.forum.model.Topico;
import br.com.forum.repository.CursoRepository;
import br.com.forum.repository.TopicoRepository;

@RestController
@RequestMapping("topicos")
public class TopicosController 
{
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> lista(String cursoNome)
	{
		System.out.println(cursoNome);
		if (cursoNome == null)
		{
			return TopicoDto.converter(topicoRepository.findAll());
		}
		return TopicoDto.converter(topicoRepository.findByCursoNome(cursoNome));		
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder)
	{
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
}