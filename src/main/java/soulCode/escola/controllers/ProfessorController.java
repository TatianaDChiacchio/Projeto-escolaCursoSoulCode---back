package soulCode.escola.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
@RestController
@RequestMapping("escola")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/professor")
	public List<Professor> mostrarTodosProfessores(){
		List<Professor> professor = professorService.mostrarTodosProfessores();
		return  professor;
	}
	
	@GetMapping("/professor/{id_professor}")
	public ResponseEntity<Professor> mostrarUmProfessor(@PathVariable Integer id_professor) {
		Professor professor = professorService.mostrarUmProfessor(id_professor);
		return ResponseEntity.ok().body(professor);
	}
	
	@GetMapping("/professor-turma/{id_turma}")
	public ResponseEntity<Professor> buscarProfessorDaTurma(@PathVariable Integer id_turma){
		Professor professor = professorService.buscarProfessorDaTurma(id_turma);
		return ResponseEntity.ok().body(professor);
	}
	
	@GetMapping("/professor-nome/{pro_nome}")
	public ResponseEntity<Professor> buscarProfessorPeloNome(@PathVariable String pro_nome){
		Professor professor = professorService.buscarProfessorPeloNome(pro_nome);
		return ResponseEntity.ok().body(professor);
	}
	@GetMapping("/professorSemTurma")
	public List<Professor> professorSemTurma(){
		List<Professor> professor= professorService.professorSemTurma();
		return professor;
	}
	
	@GetMapping("/professor/professor-turma")
	public List<List> alunosComTurma(){
		List<List> professorTurma = professorService.ProfesssorComSuaTurma();
		return professorTurma;
	}
	@PostMapping("/professor")
	public ResponseEntity<Professor> InserirProfessorComTurma(@RequestParam(value="turma", required = false)Integer id_turma,@RequestBody Professor professor){
		professor = professorService.InserirProfessor(id_turma, professor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(professor.getId_professor()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/professor/{id_professor}")
	public ResponseEntity<Professor> editarProfessor(@RequestParam(value="turma")Turma turma, @PathVariable Integer id_professor, @RequestBody Professor professor){
		professor.setId_professor(id_professor);
		professor.setTurma(turma);
		turma.setProfessor(professor);
		professor = professorService.editarProfessor(professor);
		
		return ResponseEntity.noContent().build();
	}

}
