package com.estudo.bffagendadortarefas.business;

import com.estudo.bffagendadortarefas.business.dto.entrada.LoginDTORequest;
import com.estudo.bffagendadortarefas.business.dto.saida.TarefasDTOResponse;
import com.estudo.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefasProximaHora(){
        String tokenPuro = login(converterParaLoginDTORequest());

        // Garante o prefixo 'Bearer ' exigido pelo JwtRequestFilter da 8081
        String token = (tokenPuro != null && !tokenPuro.startsWith("Bearer "))
                ? "Bearer " + tokenPuro
                : tokenPuro;

        log.info("Busca de tarefas ");
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        List<TarefasDTOResponse> listaTarefas = tarefasService.listarTarefasPorPeriodo(horaAtual, horaFutura, token);

        log.info("Tarefas encontradas: {}", listaTarefas);

        listaTarefas.forEach(tarefas -> {
            emailService.enviaEmail(tarefas);
            log.info("email enviado para: " + tarefas.getEmailUsuario());
            // Utiliza o método sem token para chamada limpa no Feign
            tarefasService.atualizarStatusNotificacao(tarefas.getId(), StatusNotificacaoEnum.NOTIFICADA);
        });
        log.info("finalizada a busca e notificação de tarefas");
    }

    public String login(LoginDTORequest loginDTORequest) {
        return usuarioService.login(loginDTORequest);
    }

    public LoginDTORequest converterParaLoginDTORequest() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}