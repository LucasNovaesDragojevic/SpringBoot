package br.com.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Topico 
{
	@EqualsAndHashCode.Include
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	private Usuario autor;
	private Curso curso;
	private List<Resposta> respostas = new ArrayList<>();

	public Topico(String titulo, String mensagem, Curso curso) 
	{
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.curso = curso;
	}
}
