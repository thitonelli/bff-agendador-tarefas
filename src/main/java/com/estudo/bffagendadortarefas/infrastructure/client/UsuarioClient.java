package com.estudo.bffagendadortarefas.infrastructure.client;

import com.estudo.bffagendadortarefas.business.dto.entrada.EnderecoDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.LoginDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.TelefoneDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.UsuarioDTORequest;
import com.estudo.bffagendadortarefas.business.dto.saida.EnderecoDTOResponse;
import com.estudo.bffagendadortarefas.business.dto.saida.TelefoneDTOResponse;
import com.estudo.bffagendadortarefas.business.dto.saida.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTORequest);

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader(value = "Authorization", required = false) String token);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable("email") String email,
                               @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestHeader(value = "Authorization", required = false) String token,
                                             @RequestBody UsuarioDTORequest usuarioDTORequest);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestParam("id") Long id,
                                         @RequestBody EnderecoDTORequest enderecoDTORequest,
                                         @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestParam("id") Long id,
                                         @RequestBody TelefoneDTORequest telefoneDTORequest,
                                         @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestHeader(value = "Authorization", required = false) String token,
                                         @RequestBody EnderecoDTORequest enderecoDTORequest);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestHeader(value = "Authorization", required = false) String token,
                                         @RequestBody TelefoneDTORequest telefoneDTORequest);
}