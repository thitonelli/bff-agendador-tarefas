package com.estudo.bffagendadortarefas.business;

import com.estudo.bffagendadortarefas.business.dto.saida.TarefasDTOResponse;
import com.estudo.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;


    public void enviaEmail(TarefasDTOResponse tarefasDTOResponse) {
        emailClient.enviaEmail(tarefasDTOResponse);
    }
}


