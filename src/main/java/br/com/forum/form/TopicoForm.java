package br.com.forum.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.forum.model.Topico;
import br.com.forum.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopicoForm 
{
	@NotBlank @Size(min = 5, max = 140)
	private String titulo;
	@NotBlank @Size(min = 5)
	private String mensagem;
	@NotBlank
	private String cursoNome;
	
	public Topico converter(CursoRepository cursoRepository) 
	{
		return new Topico(titulo, mensagem, cursoRepository.findByNome(cursoNome));
	}
}
