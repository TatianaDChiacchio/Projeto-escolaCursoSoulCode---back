package soulCode.escola.controllers;

import java.net.URI;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.escola.models.Aluno;
import soulCode.escola.models.Turma;
import soulCode.escola.services.AlunoService;

@CrossOrigin @RestController @RequestMapping("escola")
public class AlunoController {
	
	// precisamos da injeção de dependências
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping("/aluno")
	public List<Aluno> mostrarTodosAlunos(){
		List<Aluno> aluno = alunoService.mostrarTodosAlunos();
		return aluno;
	}
	
	@GetMapping("/aluno-turma")
	public List<List> alunosComTurma(){
		List<List> alunoTurma = alunoService.alunosComTurma();
		return alunoTurma;
	}
	
	
	@GetMapping("/aluno/{ra_aluno}")
	public ResponseEntity<?> buscarUmAluno(@PathVariable Integer ra_aluno){
		Aluno aluno  = alunoService.buscarUmAluno(ra_aluno);
		return ResponseEntity.ok().body(aluno);
		
	}
	
	@GetMapping("/aluno/busca-turma/{id_turma}")
	public List<Aluno> buscarAlunoTurma(@PathVariable Integer id_turma){
		List<Aluno> aluno = alunoService.buscarAlunoTurma(id_turma);
		return aluno;
	}
	
	@PutMapping("/aluno/inserirTurma/{ra_aluno}")
	public ResponseEntity<Aluno> inserirAlunoNaTurma(@PathVariable Integer ra_aluno, @RequestBody Turma turma){
		Aluno aluno = alunoService.inserirAlunoNaTurma(ra_aluno, turma);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/aluno/deixarSemTurma/{ra_aluno}")
	public ResponseEntity<Aluno> deixarAlunoSemTurma(@PathVariable Integer ra_aluno){
		Aluno aluno = alunoService.deixarAlunoSemTurma(ra_aluno);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/aluno")
	public ResponseEntity<Aluno> InserirAluno(@RequestBody Aluno aluno){
		aluno = alunoService.InserirAluno(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(aluno.getRa_aluno()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@DeleteMapping("/aluno/{ra_aluno}")
	public ResponseEntity<Void> deletarUmAluno(@PathVariable Integer ra_aluno){
		alunoService.deletarUmAluno(ra_aluno);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/aluno/{ra_aluno}")
	public ResponseEntity<Void> editarAluno(@PathVariable Integer ra_aluno, @RequestBody Aluno aluno){
		aluno.setRa_aluno(ra_aluno);
		aluno = alunoService.editarAluno(aluno);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	

}
