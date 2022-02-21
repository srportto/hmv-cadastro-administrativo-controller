package br.com.hmv.dtos.request;

import br.com.hmv.dtos.general.HospitalDTO;
import br.com.hmv.services.validation.hospital.criacao.HospitalInsertValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@HospitalInsertValid
public class HospitalUnidadeInsertRequestDTO extends HospitalDTO {


    @NotBlank(message = "Campo nome_unidade deve ser preenchido")
    @JsonProperty("nome_unidade")
    private String nomeUnidade;

    @NotNull(message = "endereco deve ser preenchido")
    private EnderecoInsertRequestDTO endereco;
}