package soulCode.escola.controllers;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Flow.Processor;

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

import soulCode.escola.models.Professor;
import soulCode.escola.models.Turma;
import soulCode.escola.services.ProfessorService;
import soulCode.escola.services.TurmaService;

@CrossOrigin
@RestController
@RequestMapping("escola")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@GetMapping("/turma")
	public List<Turma> mostrarTodasTurmas(){
		List<Turma> turma = turmaService.mostrarTodasTurmas();
		return turma;
	}
	
	@GetMapping("/turma/{id_turma}")
	public ResponseEntity<Turma> buscarUmaTurma(@PathVariable Integer id_turma){
		Turma turma = turmaService.buscarUmaTurma(id_turma);
		return ResponseEntity.ok().body(turma);
	}
	
	@GetMapping("/turmaSemProfessor")
	public List<Turma> professorSemTurma(){
		List<Turma> turma = turmaService.turmaSemProfessor();
		return turma;
	}
	@GetMapping("/turma/turma-professor/{id_professor}")
	public Turma turmaDoProfessor(@PathVariable Integer id_professor){
		
		return turmaService.turmaDoProfessor(id_professor);
	}
	
	@GetMapping("/turma/turma-professor")
	public List<List> turmasComProfessor(){
		List<List> turmaProfessor = turmaService.turmaComSeuProfessor();
		return turmaProfessor;
	}
	
	@PostMapping("/turma")
	public ResponseEntity<Turma> cadastrarTurma(@RequestParam(value="professor", required = false)Integer id_professor,@RequestBody Turma turma){
		turma = turmaService.cadastrarTurma(id_professor,turma);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(turma.getId_turma()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/turma/{id_turma}")
	public ResponseEntity<Void> editarTurma(@PathVariable Integer id_turma, @RequestBody Turma turma){
		turma.setId_turma(id_turma);
		turma = turmaService.editarTurma(turma);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/turma/{id_turma}")
	public ResponseEntity<Turma> deletarUmaTurma(@PathVariable Integer id_turma){
		turmaService.deletarUmaTurma(id_turma);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/turma/definirProfessor/{id_turma}/{id_professor}")
	public ResponseEntity<Professor> atribuirProfessor(@PathVariable Integer id_turma, @PathVariable Integer id_professor){
		turmaService.atribuirProfessor(id_turma, id_professor);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/turma/tirarProfessor/{id_turma}/{id_professor}")
	public ResponseEntity<Professor> deixarTurmaSemProfessor(@PathVariable Integer id_turma, @PathVariable Integer id_professor){
		turmaService.deixarTurmaSemProfessor(id_turma, id_professor);
		return ResponseEntity.noContent().build();
	}

}
