package soulCode.escola.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.escola.models.Aluno;
import soulCode.escola.models.Boleto;
import soulCode.escola.repositorys.BoletoRepository;

@Service
public class BoletoService {
	
	// fazendo a injeção de depedencia
		@Autowired
		private BoletoRepository boletoRepository;
		
		@Autowired
		private AlunoService alunoService;
		
					
		//o primeiro serviço que vamos implementar é a listagem de todos os alunos cadastrados
		
		public List<Boleto> buscarTodosBoletos(){
			return boletoRepository.findAll();
		}
		
		public Boleto buscarUmBoleto(Integer codigo) {
			Optional<Boleto> boleto = boletoRepository.findById(codigo);
			return boleto.orElseThrow();
		}
		
		public List<Boleto> buscarBoletosDoAluno(Integer ra_aluno){
			List<Boleto> boleto = boletoRepository.buscarBoletosDoAluno(ra_aluno);
			return boleto;
		}
		
		public Boleto InserirBoleto(Boleto boleto, Integer ra_aluno) {
			boleto.setCodigo(null);
			Aluno aluno = alunoService.buscarUmAluno(ra_aluno);
			boleto.setAluno(aluno);
			return boletoRepository.save(boleto);
		}
		
		public void deletarUmBoleto(Integer codigo) {
			boletoRepository.deleteById(codigo);
		}
		
		public Boleto editarBoleto(Boleto boleto) {
			buscarUmBoleto(boleto.getCodigo());
			return boletoRepository.save(boleto);
		}

}
