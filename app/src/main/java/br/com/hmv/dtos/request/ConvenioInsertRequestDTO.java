package br.com.hmv.dtos.request;

import br.com.hmv.services.validation.convenio.criacao.ConvenioInsertValid;
import br.com.hmv.services.validation.user.criacao.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@Data
@ConvenioInsertValid
public class ConvenioInsertRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Campo password é obrigatório")
    private String descricao;
}