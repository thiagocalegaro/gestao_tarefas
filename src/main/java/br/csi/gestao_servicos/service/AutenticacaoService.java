package br.csi.gestao_servicos.service;

import br.csi.gestao_servicos.model.usuario.Usuario;
import br.csi.gestao_servicos.model.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    private UsuarioRepository repository;
    public AutenticacaoService(UsuarioRepository repository){
        this.repository = repository;
    }
    //Metodo destiando a criação de um UserDetail que será inserido no contexto o Spring
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = this.repository.findByLogin(login);
        if(usuario ==null){
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }else {
            UserDetails user = User.withUsername(usuario.getLogin()).password(usuario.getSenha())
                    .authorities(usuario.getPermissao()).build();
            return user;
        }
    }
}