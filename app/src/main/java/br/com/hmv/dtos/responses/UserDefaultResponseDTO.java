package br.com.hmv.dtos.responses;

import br.com.hmv.dtos.general.UserDTO;
import br.com.hmv.models.entities.User;
import br.com.hmv.services.validation.criacao.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UserInsertValid
public class UserDefaultResponseDTO extends UserDTO {

    //Construtor diferenciado
    public UserDefaultResponseDTO(User entity) {
        super(entity);
    }
}
