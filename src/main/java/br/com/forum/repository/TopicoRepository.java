package br.com.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>
{
	List<Topico> findByCursoNome(String cursoNome);
	
	/**
	 * Para problemas de ambiguidade use
	 */
	List<Topico> findByCurso_Nome(String cursoNome);
	
	/**
	 * Para métodos personalizados use
	 */
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :pCursoNome")
	List<Topico> carregarPorCursoNome(@Param("pCursoNome") String cursoNome);
}
