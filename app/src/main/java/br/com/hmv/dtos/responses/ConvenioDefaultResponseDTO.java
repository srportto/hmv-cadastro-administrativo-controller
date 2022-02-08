package br.com.hmv.dtos.responses;

import br.com.hmv.dtos.general.ConvenioDTO;
import br.com.hmv.models.entities.Convenio;
import br.com.hmv.services.validation.user.criacao.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UserInsertValid
public class ConvenioDefaultResponseDTO extends ConvenioDTO {

    //Construtor diferenciado
    public ConvenioDefaultResponseDTO(Convenio entity) {
        super(entity);
    }
}
