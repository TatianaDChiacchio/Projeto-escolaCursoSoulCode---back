package soulCode.escola.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.escola.models.Aluno;
import soulCode.escola.models.Professor;
import soulCode.escola.models.Turma;
import soulCode.escola.repositorys.AlunoRepository;

@Service
public class AlunoService {

	// fazendo a injeção de depedencia
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private TurmaService turmaService;
	
		
	//o primeiro serviço que vamos implementar é a listagem de todos os alunos cadastrados
	
	public List<Aluno> mostrarTodosAlunos(){
		return alunoRepository.findAll();
	
	}
	
	public List<List> alunosComTurma(){
		return alunoRepository.alunosComTurma();
	}
	
	//Optional - nos ajuda a evitar os erros NullPointerException
	//tira a necessidade da verificação de criarmos codificação (if aluno != null)
	
	//orElseThrow() -se o aluno estiver presente no banco de dando, retorna o aluno,
	//se naão lança uma exceç
	
	public Aluno buscarUmAluno(Integer ra_aluno) {
		Optional<Aluno> aluno = alunoRepository.findById(ra_aluno);
		return aluno.orElseThrow();
	}
	public List<Aluno> buscarAlunoTurma(Integer id_turma){
		List<Aluno> aluno = alunoRepository.fetchByTurma(id_turma);
		return aluno;
	}
	
	public Aluno inserirAlunoNaTurma(Integer ra_aluno, Turma turma) {
		Aluno aluno = buscarUmAluno(ra_aluno);
		aluno.setTurma(turma);
		return alunoRepository.save(aluno);
	}
	
	public Aluno deixarAlunoSemTurma(Integer ra_aluno) {
		Aluno aluno = buscarUmAluno(ra_aluno);
		aluno.setTurma(null);
		return alunoRepository.save(aluno);
	}
	
	
	public Aluno InserirAluno(Aluno aluno) {
		aluno.setRa_aluno(null);
		return alunoRepository.save(aluno);
	}
	
	public void deletarUmAluno(Integer ra_aluno) {
		alunoRepository.deleteById(ra_aluno);
	}
	
	public Aluno editarAluno(Aluno aluno) {
		buscarUmAluno(aluno.getRa_aluno());
		return alunoRepository.save(aluno);
	}
	
	
	
	
}
