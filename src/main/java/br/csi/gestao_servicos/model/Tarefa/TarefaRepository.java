package br.csi.gestao_servicos.model.Tarefa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    public Tarefa findTarefaByUuid(UUID uuid);
    public void deleteTarefaById(Long id);
    public Tarefa findTarefaById(Long id);
    public void deleteTarefaByUuid(UUID uuid);


}
