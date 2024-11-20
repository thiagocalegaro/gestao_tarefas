package br.csi.gestao_servicos.service;

import br.csi.gestao_servicos.model.Funcionario.Funcionario;
import br.csi.gestao_servicos.model.Funcionario.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {
    private final FuncionarioRepository repository;
    public FuncionarioService(FuncionarioRepository repository){
        this.repository = repository;}
    public void salvar(Funcionario funcionario){
        this.repository.save(funcionario);
    }
    public List<Funcionario> listar(){
        return this.repository.findAll();
    }
    public Funcionario getFuncionarioByID(Long id){
        return  this.repository.findFuncionarioById(id);
    }
    public void excluirByID(Long id){
        this.repository.deleteById(id);
    }
    public void atualizarByID(Funcionario funcionario){
        Funcionario f = this.repository.getReferenceById(funcionario.getId());
        f.setNome(funcionario.getNome());
        f.setSalario(funcionario.getSalario());
        f.setCargo(funcionario.getCargo());
        f.setEmail(funcionario.getEmail());
    }
    public void atualizarByUUID(Funcionario funcionario){
        Funcionario f = this.repository.findFuncionarioByUuid(funcionario.getUuid());
        f.setNome(funcionario.getNome());
        f.setSalario(funcionario.getSalario());
        f.setEmail(funcionario.getEmail());
        f.setCargo(funcionario.getCargo());
    }
    public Funcionario getFuncionarioByUUID(String uuid){
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findFuncionarioByUuid(uuidformatado);
    }
    public void excluirByUUID(String uuid){

        this.repository.deleteFuncionarioByUuid(UUID.fromString(uuid));
    }
}
