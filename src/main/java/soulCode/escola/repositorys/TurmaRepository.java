package soulCode.escola.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.escola.models.Turma;

public interface TurmaRepository extends JpaRepository<Turma,Integer>{

	@Query(value="SELECT * FROM turma WHERE id_professor is null", nativeQuery = true)
	List<Turma> turmaSemProfessor();
	
	@Query(value="SELECT * FROM turma where id_professor =:id_professor",nativeQuery = true)
	Turma turmaDoProfessor(Integer id_professor);
	
	@Query(value="SELECT turma.id_turma,turma.tu_nome,turma.tu_descricao,professor.id_professor,professor.pro_nome,professor.pro_formacao FROM turma left JOIN professor ON professor.id_turma = turma.id_turma order by turma.tu_nome;",nativeQuery = true)
	List<List> turmaComSeuProfessor();
}
