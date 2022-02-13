package br.com.hmv.dtos.request;

import br.com.hmv.dtos.general.ConvenioDTO;
import br.com.hmv.models.enums.StatusConvenioEnum;
import br.com.hmv.services.validation.convenio.atualizacao_all.ConvenioAtualizaAllValid;
import br.com.hmv.services.validation.convenio.criacao.ConvenioInsertValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@ConvenioAtualizaAllValid
public class ConvenioAtualizaAllRequestDTO extends ConvenioDTO {

    @NotBlank(message = "Campo descricao deve ser preenchido")
    private String descricao;

    @NotNull( message = "Campo status deve ser preenchido")
    @JsonProperty("status")
    private StatusConvenioEnum statusConvenioEnum;
}