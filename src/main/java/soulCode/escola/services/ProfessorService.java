package soulCode.escola.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.escola.models.Aluno;
import soulCode.escola.models.Professor;
import soulCode.escola.models.Turma;
import soulCode.escola.repositorys.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TurmaService turmaService;
	
	public List<Professor> mostrarTodosProfessores(){
		return professorRepository.findAll();	
	}
	
	public Professor mostrarUmProfessor(Integer id_professor) {
		Optional<Professor> professor = professorRepository.findById(id_professor);
		return professor.orElseThrow();
	}
	
	public Professor buscarProfessorDaTurma(Integer id_turma){
		Professor professor = professorRepository.fetchByTurma(id_turma);
		return professor;
	}
	
	public List<Professor> professorSemTurma(){
		return professorRepository.professorSemTurma();
	}
	
	public List<List> ProfesssorComSuaTurma(){
		return professorRepository.professorComSuaTurma();
	}
	
	public Professor InserirProfessor(Integer id_turma, Professor professor) {
		professor.setId_professor(null);
		
		if(id_turma != null) {
			Turma turma = turmaService.buscarUmaTurma(id_turma);
			professor.setTurma(turma);
			
		}
		return professorRepository.save(professor);
		
	}
	
	public Professor editarProfessor(Professor professor) {
		mostrarUmProfessor(professor.getId_professor());
		return professorRepository.save(professor);
	}
	
	

}
