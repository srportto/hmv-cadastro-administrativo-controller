package br.com.hmv.dtos.request;

import br.com.hmv.dtos.general.UserDTO;
import br.com.hmv.services.validation.criacao.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@UserInsertValid
public class UserInsertRequestDTO extends UserDTO {

    @NotBlank(message = "Campo password é obrigatório")
    private String password;
}