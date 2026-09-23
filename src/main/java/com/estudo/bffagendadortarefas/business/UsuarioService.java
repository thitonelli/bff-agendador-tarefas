package com.estudo.bffagendadortarefas.business;

import com.estudo.bffagendadortarefas.business.dto.entrada.EnderecoDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.LoginDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.TelefoneDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.UsuarioDTORequest;
import com.estudo.bffagendadortarefas.business.dto.saida.EnderecoDTOResponse;
import com.estudo.bffagendadortarefas.business.dto.saida.TelefoneDTOResponse;
import com.estudo.bffagendadortarefas.business.dto.saida.UsuarioDTOResponse;
import com.estudo.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTORequest) {
        return usuarioClient.salvaUsuario(usuarioDTORequest);
    }

    public String login(LoginDTORequest loginDTORequest) {
        return usuarioClient.login(loginDTORequest);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest usuarioDTORequest) {
        return usuarioClient.atualizarDadosUsuario(token, usuarioDTORequest);
    }

    public EnderecoDTOResponse atualizaEndereco(Long id, EnderecoDTORequest enderecoDTORequest, String token) {
        return usuarioClient.atualizaEndereco(id, enderecoDTORequest, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long id, TelefoneDTORequest telefoneDTORequest, String token) {
        return usuarioClient.atualizaTelefone(id, telefoneDTORequest, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTORequest) {
        return usuarioClient.cadastraEndereco(token, enderecoDTORequest);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTORequest) {
        return usuarioClient.cadastraTelefone(token, telefoneDTORequest);
    }
}