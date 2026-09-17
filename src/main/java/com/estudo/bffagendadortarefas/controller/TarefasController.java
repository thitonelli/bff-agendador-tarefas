package com.estudo.bffagendadortarefas.controller;

import com.estudo.bffagendadortarefas.business.TarefasService;
import com.estudo.bffagendadortarefas.business.dto.entrada.TarefasDTORequest;
import com.estudo.bffagendadortarefas.business.dto.saida.TarefasDTOResponse;
import com.estudo.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.estudo.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "cadastra tarefas de usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Grava uma nova tarefa", description = "Grava uma nova tarefa para o usuário autenticado")
    @ApiResponse(responseCode = "200", description = "Tarefa gravada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefa(@RequestBody TarefasDTORequest tarefasDTORequest, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTORequest));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período", description = "Busca tarefas cadastradas dentro de um período específico")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.listarTarefasPorPeriodo(dataInicio, dataFim, token));
    }

    @GetMapping
    @Operation(summary = "Busca tarefas por email de usuário", description = "Busca tarefas cadastradas para o usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorUsuario(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.listarTarefasPorUsuario(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta uma tarefa por ID", description = "Deleta uma tarefa específica com base no ID fornecido")
    @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam String id, @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @Operation(summary = "Atualiza o status de notificação de uma tarefa", description = "Atualiza o status de notificação de uma tarefa específica")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> atualizarStatusNotificacao(@RequestParam("id") String id, @RequestParam("status") StatusNotificacaoEnum status, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.atualizarStatusNotificacao(id, status, token));
    }


    @PutMapping
    @Operation(summary = "Atualiza uma tarefa existente", description = "Atualiza os detalhes de uma tarefa específica com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> atualizarTarefa(@RequestParam String id, @RequestBody TarefasDTORequest tarefasDTORequest, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(id, tarefasDTORequest, token));
    }

}
