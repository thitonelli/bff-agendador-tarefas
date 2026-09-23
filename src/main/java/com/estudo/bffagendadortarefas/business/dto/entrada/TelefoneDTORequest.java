package com.estudo.bffagendadortarefas.business.dto.entrada;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {

    private String numero;
    private String ddd;

}
