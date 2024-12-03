package br.csi.gestao_servicos.service;

import br.csi.gestao_servicos.model.funcionario.Funcionario;
import br.csi.gestao_servicos.model.tarefa.Tarefa;
import br.csi.gestao_servicos.model.tarefa.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;


    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public void salvar(Tarefa tarefa){
        this.tarefaRepository.save(tarefa);
    }
    public void excluirByID(Long id){
        this.tarefaRepository.deleteTarefaById(id);
    }
    public void atualizarByID(Tarefa tarefa){
        Tarefa s = this.tarefaRepository.findTarefaById(tarefa.getId());
        s.setNome(tarefa.getNome());
        s.setStatus(tarefa.getStatus());
        s.setDescricao(tarefa.getDescricao());
        s.setDuracao(tarefa.getDuracao());
    }
    public Tarefa getServicoByUUID(String uuid){
        UUID uuidformatado = UUID.fromString(uuid);
        return this.tarefaRepository.findTarefaByUuid(uuidformatado);
    }
    public void excluirByUUID(String uuid){
        this.tarefaRepository.deleteTarefaByUuid(UUID.fromString(uuid));
    }
    public List<Tarefa>listar(){
        return this.tarefaRepository.findAll();
    }
    public Tarefa getServicoByID(Long id){
        return this.tarefaRepository.findTarefaById(id);
    }
    public void atualizarByUUID(Tarefa tarefa){
        Tarefa s = this.tarefaRepository.findTarefaByUuid(tarefa.getUuid());
        s.setNome(tarefa.getNome());
        s.setStatus(tarefa.getStatus());
        s.setDescricao(tarefa.getDescricao());
        s.setDuracao(tarefa.getDuracao());
    }
    public Tarefa getServicoByUUID(UUID uuid){
        return this.tarefaRepository.findTarefaByUuid(uuid);
    }
    public void vincularTarefaAoFuncionario(Long tarefa, Funcionario funcionario){
        Tarefa t = this.tarefaRepository.findTarefaById(tarefa);
        t.addFuncionario(funcionario);
        this.tarefaRepository.save(t);
    }

}