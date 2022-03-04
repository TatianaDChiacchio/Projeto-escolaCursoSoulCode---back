package soulCode.escola.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Boleto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(nullable = false)
	private String bo_descricao;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(columnDefinition = "date", nullable = false)
	@Temporal(TemporalType.DATE) //sem salvar a hora, só o dia
	private Date bo_dataVencimento;
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(nullable = false)
	private Double bo_valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo bo_status;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ra_aluno")
	private Aluno aluno;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getBo_descricao() {
		return bo_descricao;
	}
	public void setBo_descricao(String bo_descricao) {
		this.bo_descricao = bo_descricao;
	}
	public Date getBo_dataVencimento() {
		return bo_dataVencimento;
	}
	public void setBo_dataVencimento(Date bo_dataVencimento) {
		this.bo_dataVencimento = bo_dataVencimento;
	}
	public Double getBo_valor() {
		return bo_valor;
	}
	public void setBo_valor(Double bo_valor) {
		this.bo_valor = bo_valor;
	}
	public StatusTitulo getBo_status() {
		return bo_status;
	}
	public void setBo_status(StatusTitulo bo_status) {
		this.bo_status = bo_status;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	

}
