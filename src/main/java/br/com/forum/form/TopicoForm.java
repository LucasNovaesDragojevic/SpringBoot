package br.com.forum.form;

import br.com.forum.model.Topico;
import br.com.forum.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopicoForm 
{
	private String titulo;
	private String mensagem;
	private String cursoNome;
	
	public Topico converter(CursoRepository cursoRepository) 
	{
		return new Topico(titulo, mensagem, cursoRepository.findByNome(cursoNome));
	}
}
