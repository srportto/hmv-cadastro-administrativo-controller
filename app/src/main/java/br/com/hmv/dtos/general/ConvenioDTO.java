package br.com.hmv.dtos.general;

import br.com.hmv.models.entities.Convenio;
import br.com.hmv.models.enums.StatusConvenioEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	@JsonProperty("status")
	private StatusConvenioEnum statusConvenio;

	private LocalDateTime dataCriacao;

	private LocalDateTime dataAtualizacao;

	//? construtor diferenciado - de entity para DTO
	public ConvenioDTO(Convenio convenio) {
		id = convenio.getId();
		descricao = convenio.getDescricao();
		statusConvenio = StatusConvenioEnum.obterStatusConvenio(convenio.getCodigoStatusConvenio());
		dataCriacao = convenio.getDataCriacao();
		dataAtualizacao = convenio.getDataAtualizacao();
	}
}
