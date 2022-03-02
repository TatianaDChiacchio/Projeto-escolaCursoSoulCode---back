package soulCode.escola.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Turma {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_turma;
	
	@Column(nullable=false, length=30)
	private String tu_nome;
	
	@Column(nullable=false, length=100)
	private String tu_descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private List<Aluno> aluno = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_professor", unique = true)
	private Professor professor;

	public Integer getId_turma() {
		return id_turma;
	}

	public void setId_turma(Integer id_turma) {
		this.id_turma = id_turma;
	}

	public String getTu_nome() {
		return tu_nome;
	}

	public void setTu_nome(String tu_nome) {
		this.tu_nome = tu_nome;
	}

	public String getTu_descricao() {
		return tu_descricao;
	}

	public void setTu_descricao(String tu_descricao) {
		this.tu_descricao = tu_descricao;
	}

	public List<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
	}
	
	

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
}
