package br.csi.gestao_servicos;

import br.csi.gestao_servicos.model.Funcionario.FuncionarioRepository;
import br.csi.gestao_servicos.model.Tarefa.TarefaRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@OpenAPIDefinition(
		info = @Info(
				title = "API serviços",
				version = "1.0",
				description = "Documentação da API",
				contact = @Contact(name = "Suporte", email = "thiagoacmartin@gmail.com")
		)
)
@SpringBootApplication
public class GestaoDeServicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeServicosApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(FuncionarioRepository funcionarioRepository, TarefaRepository tarefaRepository) {
		return args -> {
			/*Funcionario funcionario = new Funcionario();
			funcionario.setNome("Thiafdfso");
			funcionario.setEmail("thfas@teste");
			funcionario.setSalario(1000);
			Servico servico = new Servico();
			servico.setNome("Limpeza");
			funcionario.addServico(servico);
			servico.addFuncionario(funcionario);
			//funcionarioRepository.save(funcionario);
			//servicoRepository.save(servico);*/


		};
	}

}
