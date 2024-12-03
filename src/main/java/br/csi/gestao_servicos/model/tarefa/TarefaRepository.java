package br.csi.gestao_servicos.model.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    public Tarefa findTarefaByUuid(UUID uuid);
    public void deleteTarefaById(Long id);
    public Tarefa findTarefaById(Long id);
    public void deleteTarefaByUuid(UUID uuid);


}
