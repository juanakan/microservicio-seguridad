package com.whiteam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whiteam.model.Curso;


public interface CursosService {
	
	void altaCurso(Curso curso);
	Curso actualizarCurso(Curso curso);
	List<Curso> recuperarTodos();
	List<Curso> cursosDuracionMaxima(int duracion);
	void eliminarCursos(String denominacion);
	List<Curso> cursosPorTematica(String tematica);
}
