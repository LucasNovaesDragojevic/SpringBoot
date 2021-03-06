package br.com.forum.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.forum.model.Topico;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class TopicoDto
{
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	public TopicoDto(Topico topico)
	{
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public static Page<TopicoDto> converter(Page<Topico> topicos) 
	{
		return topicos.map(TopicoDto::new);
	}
}
