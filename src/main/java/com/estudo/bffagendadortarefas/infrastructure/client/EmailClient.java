package com.estudo.bffagendadortarefas.infrastructure.client;

import com.estudo.bffagendadortarefas.business.dto.saida.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping("/email")
    void enviaEmail(@RequestBody TarefasDTOResponse tarefasDTO);
}