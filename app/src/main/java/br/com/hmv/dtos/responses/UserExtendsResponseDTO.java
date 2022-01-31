package br.com.hmv.dtos.responses;

import br.com.hmv.dtos.general.RoleDTO;
import br.com.hmv.dtos.general.UserDTO;
import br.com.hmv.models.entities.User;
import br.com.hmv.services.validation.criacao.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@UserInsertValid
public class UserExtendsResponseDTO extends UserDTO {

    Set<RoleDTO> roles = new HashSet<>();

    //Construtor diferenciado
    public UserExtendsResponseDTO(User entity) {
        super(entity);
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}