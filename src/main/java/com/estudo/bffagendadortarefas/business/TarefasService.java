package com.estudo.bffagendadortarefas.business;

import com.estudo.bffagendadortarefas.business.dto.entrada.TarefasDTORequest;
import com.estudo.bffagendadortarefas.business.dto.saida.TarefasDTOResponse;
import com.estudo.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.estudo.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;


    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest tarefasDTORequest) {
        return tarefasClient.gravarTarefa(tarefasDTORequest, token);
    }

    public List<TarefasDTOResponse> listarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim, String token) {
        return tarefasClient.buscarTarefasPorPeriodo(dataInicio, dataFim, token);
    }

    public List<TarefasDTOResponse> listarTarefasPorUsuario(String token) {
        return tarefasClient.buscarTarefasPorUsuario(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTOResponse atualizarStatusNotificacao(String id, StatusNotificacaoEnum statusNotificacaoEnum, String token) {
        return tarefasClient.atualizarStatusNotificacao(id, statusNotificacaoEnum, token);
    }

    public TarefasDTOResponse updateTarefas(String id, TarefasDTORequest tarefasDTORequest, String token) {
        return tarefasClient.atualizarTarefa(id, tarefasDTORequest, token);
    }

}


