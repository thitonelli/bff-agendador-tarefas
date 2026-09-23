package com.estudo.bffagendadortarefas.infrastructure.client;

import com.estudo.bffagendadortarefas.business.dto.entrada.TarefasDTORequest;
import com.estudo.bffagendadortarefas.business.dto.saida.TarefasDTOResponse;
import com.estudo.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefa(@RequestBody TarefasDTORequest tarefasDTORequest, @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscarTarefasPorUsuario(@RequestHeader("Authorization") String token);

    @DeleteMapping
    Void deletarTarefaPorId(@RequestParam String id, @RequestHeader("Authorization") String token);

    // Método utilizado pela Cron (sem token e sem o header corrompido)
    @PatchMapping
    TarefasDTOResponse atualizarStatusNotificacao(
            @RequestParam("id") String id,
            @RequestParam("status") StatusNotificacaoEnum status);

    // Método mantido com token (caso precise em outro lugar)
    @PatchMapping
    TarefasDTOResponse atualizarStatusNotificacao(
            @RequestParam("id") String id,
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse atualizarTarefa(@RequestParam String id, @RequestBody TarefasDTORequest tarefasDTORequest, @RequestHeader("Authorization") String token);

}