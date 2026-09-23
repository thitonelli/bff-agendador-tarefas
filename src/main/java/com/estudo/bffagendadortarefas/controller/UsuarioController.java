package com.estudo.bffagendadortarefas.controller;

import com.estudo.bffagendadortarefas.business.UsuarioService;
import com.estudo.bffagendadortarefas.business.dto.entrada.EnderecoDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.LoginDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.TelefoneDTORequest;
import com.estudo.bffagendadortarefas.business.dto.entrada.UsuarioDTORequest;
import com.estudo.bffagendadortarefas.business.dto.saida.EnderecoDTOResponse;
import com.estudo.bffagendadortarefas.business.dto.saida.TelefoneDTOResponse;
import com.estudo.bffagendadortarefas.business.dto.saida.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "cadastro e login de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salva um novo usuário", description = "Endpoint para salvar um novo usuário no sistema.")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já existe")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza login de usuário", description = "Endpoint para realizar login de usuário no sistema.")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<String> login(@RequestBody LoginDTORequest loginDTORequest) {
        return ResponseEntity.ok(usuarioService.login(loginDTORequest));
    }

    @GetMapping
    @Operation(summary = "Busca usuário por email", description = "Endpoint para buscar um usuário pelo email.")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuário por email", description = "Endpoint para deletar um usuário pelo email.")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable("email") String email,
                                                      @RequestHeader(value = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados do usuário", description = "Endpoint para atualizar os dados de um usuário.")
    @ApiResponse(responseCode = "200", description = "Dados do usuário atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestHeader(value = "Authorization", required = false) String token,
                                                                    @RequestBody UsuarioDTORequest usuarioDTORequest) {
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioDTORequest));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço do usuário", description = "Endpoint para atualizar o endereço de um usuário.")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestParam("id") Long id,
                                                                @RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTORequest, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone do usuário", description = "Endpoint para atualizar o telefone de um usuário.")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestParam("id") Long id,
                                                                @RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTORequest, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastra endereço do usuário", description = "Endpoint para cadastrar o endereço de um usuário.")
    @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestHeader(value = "Authorization", required = false) String token,
                                                                @RequestBody EnderecoDTORequest enderecoDTORequest) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTORequest));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastra telefone do usuário", description = "Endpoint para cadastrar o telefone de um usuário.")
    @ApiResponse(responseCode = "201", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestHeader(value = "Authorization", required = false) String token,
                                                                @RequestBody TelefoneDTORequest telefoneDTORequest) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTORequest));
    }
}