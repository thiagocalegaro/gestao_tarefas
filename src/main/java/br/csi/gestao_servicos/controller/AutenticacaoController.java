package br.csi.gestao_servicos.controller;

import br.csi.gestao_servicos.infra.security.TokenServiceJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    private AuthenticationManager manager;
    private TokenServiceJWT tokenServiceJWT;
}
