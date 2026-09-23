package com.estudo.bffagendadortarefas.business.dto.saida;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {

    private Long id;
    private String rua;
    private Long numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;

}
