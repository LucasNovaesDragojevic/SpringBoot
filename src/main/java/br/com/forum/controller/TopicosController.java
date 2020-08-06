package br.com.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.dto.TopicoDto;

import br.com.forum.repository.TopicoRepository;

@RestController
public class TopicosController 
{
	@Autowired
	private TopicoRepository topicoRepository;

	@RequestMapping("topicos")
	public List<TopicoDto> lista(String cursoNome)
	{
		System.out.println(cursoNome);
		if (cursoNome == null)
		{
			return TopicoDto.converter(topicoRepository.findAll());
		}
		return TopicoDto.converter(topicoRepository.findByCursoNome(cursoNome));		
	}
}
