package com.estudo.bffagendadortarefas.business.dto.entrada;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTORequest {

    private String rua;
    private Long numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;

}
