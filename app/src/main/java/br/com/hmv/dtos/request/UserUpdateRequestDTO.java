package br.com.hmv.dtos.request;

import br.com.hmv.dtos.general.RoleDTO;
import br.com.hmv.dtos.general.UserDTO;
import br.com.hmv.services.validation.atualizacao.UserUpdateValid;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@UserUpdateValid
public class UserUpdateRequestDTO extends UserDTO {
    private String password;

    @NotNull(message = "Campo permissões é obrigatório")
    private Set<RoleDTO> roles;
}