package br.com.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.dto.TopicoDto;
import br.com.forum.model.Curso;
import br.com.forum.model.Topico;

@RestController
public class TopicosController 
{
	@RequestMapping("topicos")
	public List<TopicoDto> lista()
	{
		Topico topico = new Topico("Dúvida de spring", "Como funciona arquitetura do Spring?", new Curso("Spring Boot", "Programação"));
		return TopicoDto.converter(Arrays.asList(topico, topico, topico));
	}
}
