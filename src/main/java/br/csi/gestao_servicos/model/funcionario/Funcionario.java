package br.csi.gestao_servicos.model.funcionario;

import br.csi.gestao_servicos.model.tarefa.Tarefa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um funcionário")
public class Funcionario {
    @Id @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do funcionário", example = "01")
    private Long id;
    @UuidGenerator
    private UUID uuid;
    @NonNull
    @Schema(description = "Nome do funcionário", example = "Thiago")
    private String nome;
    @NonNull
    private double salario;
    @NonNull
    private String cargo;
    @NonNull
    @Email (message = "Email inválido")
    private String email;
    @ManyToMany(mappedBy = "funcionariosList")
    private List<Tarefa> tarefasList = new ArrayList<>();
    public void addTarefa(Tarefa tarefa){
        this.tarefasList.add(tarefa);
    }


}
