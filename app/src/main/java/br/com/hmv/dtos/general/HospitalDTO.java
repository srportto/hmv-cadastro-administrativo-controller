package br.com.hmv.dtos.general;

import br.com.hmv.models.entities.Hospital;
import br.com.hmv.models.enums.StatusUnidadeHospitalEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	@JsonProperty("codigo_unidade")
	private String codigoUnidade;

	@JsonProperty("nome_unidade")
	private String nomeUnidade;

	private EnderecoDTO endereco;

	@JsonProperty("status")
	private StatusUnidadeHospitalEnum statusUnidadeHospital;

	private LocalDateTime dataCriacao;

	private LocalDateTime dataAtualizacao;

	//? construtor diferenciado - de entity para DTO
	public HospitalDTO(Hospital entity) {
		codigoUnidade = entity.getCodigoUnidade();
		nomeUnidade = entity.getNomeUnidade();
		endereco = new EnderecoDTO(entity.getEndereco());
		statusUnidadeHospital = StatusUnidadeHospitalEnum.obterStatusUnidadeHospital(entity.getCodigoStatusUnidade());
		dataCriacao = entity.getDataCriacao();
		dataAtualizacao = entity.getDataAtualizacao();
	}
}
