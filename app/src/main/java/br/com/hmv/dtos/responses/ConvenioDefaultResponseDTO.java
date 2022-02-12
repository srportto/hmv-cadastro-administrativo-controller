package br.com.hmv.dtos.responses;

import br.com.hmv.dtos.general.ConvenioDTO;
import br.com.hmv.models.entities.Convenio;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConvenioDefaultResponseDTO extends ConvenioDTO {

    //Construtor diferenciado
    public ConvenioDefaultResponseDTO(Convenio entity) {
        super(entity);
    }
}
