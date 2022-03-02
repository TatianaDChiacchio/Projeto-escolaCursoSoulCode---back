package soulCode.escola.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import soulCode.escola.models.Professor;
import soulCode.escola.models.Turma;
import soulCode.escola.repositorys.TurmaRepository;


@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Lazy
	@Autowired
	private ProfessorService professorService;

	public List<Turma> mostrarTodasTurmas() {
		return turmaRepository.findAll();
	}

	public Turma buscarUmaTurma(Integer id_turma) {
		Optional<Turma> turma = turmaRepository.findById(id_turma);
		return turma.orElseThrow(() -> new soulCode.escola.services.exceptions.ObjectNotFoundException(
				"Objeto não cadastrado! O id procurado foi: " + id_turma));
	}
	
	public List<Turma> turmaSemProfessor(){
		return turmaRepository.turmaSemProfessor();
	}
	
	public Turma turmaDoProfessor(Integer id_professor) {
		Turma turma = turmaRepository.turmaDoProfessor(id_professor);
		return turma;
	}
	

	public List<List> turmaComSeuProfessor(){
		return turmaRepository.turmaComSeuProfessor();
	}
	
	public Turma cadastrarTurma(Integer id_professor, Turma turma) {
		// é uma forma de segurança para não setarmos o id
		turma.setId_turma(null);
		if (id_professor != null) {
			Professor professor = professorService.mostrarUmProfessor(id_professor);
			turma.setProfessor(professor);
			
		}
		
		return turmaRepository.save(turma);
	}

	public Turma editarTurma(Turma turma) {
		buscarUmaTurma(turma.getId_turma());
		return turmaRepository.save(turma);
	}

	public void deletarUmaTurma(Integer id_turma) {
		buscarUmaTurma(id_turma);
		try {
			turmaRepository.deleteById(id_turma);
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			throw new soulCode.escola.services.exceptions.DataIntegrityViolationException(
					"A turma não pode ser deletada! Possui alunos relacionados!");
		}
		
	}

	public Turma atribuirProfessor(Integer id_turma,Integer id_professor){
		Turma turma = buscarUmaTurma(id_turma);
		Professor professorAnterior = professorService.buscarProfessorDaTurma(id_turma);
		Professor professor = professorService.mostrarUmProfessor(id_professor);
		if(turma.getProfessor()!=null) {
			turma.setProfessor(null);
			professorAnterior.setTurma(null);
		}
		turma.setProfessor(professor);
		professor.setTurma(turma);
		return turmaRepository.save(turma);
	}
	
	public Turma deixarTurmaSemProfessor(Integer id_turma, Integer id_professor) {
		Turma turma = buscarUmaTurma(id_turma);
		turma.setProfessor(null);
		Professor professor = professorService.mostrarUmProfessor(id_professor);
		professor.setTurma(null);
		return turmaRepository.save(turma);
	}

}
