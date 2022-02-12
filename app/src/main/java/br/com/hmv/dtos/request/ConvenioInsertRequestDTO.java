package br.com.hmv.dtos.request;

import br.com.hmv.dtos.general.ConvenioDTO;
import br.com.hmv.services.validation.convenio.criacao.ConvenioInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@ConvenioInsertValid
public class ConvenioInsertRequestDTO extends ConvenioDTO {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Campo password é obrigatório")
    private String descricao;
}