package br.csi.gestao_servicos.model.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Funcionario findFuncionarioByUuid(UUID uuid);
    public void deleteFuncionarioByUuid(UUID uuid);
    public Funcionario findFuncionarioById(Long id);
    public void deleteById(Long id);

}
