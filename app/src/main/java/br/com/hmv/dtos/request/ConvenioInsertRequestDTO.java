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

    @NotBlank(message = "Campo descricao deve ser preenchido")
    private String descricao;
}