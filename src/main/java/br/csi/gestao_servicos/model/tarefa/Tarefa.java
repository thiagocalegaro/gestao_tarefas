package br.csi.gestao_servicos.model.Tarefa;

import br.csi.gestao_servicos.model.Funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa uma tarefa")
public class Tarefa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da tarefa", example = "01")
    private Long id;
    @UuidGenerator
    private UUID uuid;
    @NonNull
    @Schema(description = "Nome da tarefa", example = "Limpeza")
    private String nome;
    @NonNull
    @Schema(description = "Descrição da tarefa", example = "Limpeza de escritório")
    private String descricao;
    @NonNull
    private String status;
    @Schema(description = "Tempo de execução do serviço", example = "2")
    private double duracao;
    @ManyToMany
    @JsonIgnoreProperties("funcionarios")
    @JoinTable(
            name = "realiza",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_tarefa")
    )
    private List<Funcionario> funcionariosList = new ArrayList<>();
    public void addFuncionario(Funcionario funcionario){
        this.funcionariosList.add(funcionario);
    }

}
