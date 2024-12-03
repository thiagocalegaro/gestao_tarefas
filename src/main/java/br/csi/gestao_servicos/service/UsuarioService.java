package br.csi.gestao_servicos.service;

import br.csi.gestao_servicos.model.usuario.DadosUsuario;
import br.csi.gestao_servicos.model.usuario.Usuario;
import br.csi.gestao_servicos.model.usuario.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService (UsuarioRepository repository){
        this.repository = repository;
    }
    public void cadastrar(Usuario usuario){
        //Criptografa a senha do usuário ANTES desse ser salvo
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.save(usuario);
    }
    public DadosUsuario findUsuario(Long id){
        Usuario usuario = this.repository.getReferenceById(id);
        return new DadosUsuario(usuario);
    }
    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }
}
