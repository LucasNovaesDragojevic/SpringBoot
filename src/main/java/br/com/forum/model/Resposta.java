package br.com.forum.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Resposta 
{
	@EqualsAndHashCode.Include
	private Long id;
	private String mensagem;
	private Topico topico;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private Usuario autor;
	private Boolean solucao = false;
}
