package com.estudo.bffagendadortarefas.controller;

import com.estudo.bffagendadortarefas.business.EmailService;
import com.estudo.bffagendadortarefas.business.dto.saida.TarefasDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasDTOResponse tarefasDTOResponse) {
        emailService.enviaEmail(tarefasDTOResponse);
        return ResponseEntity.ok().build();
    }



}
