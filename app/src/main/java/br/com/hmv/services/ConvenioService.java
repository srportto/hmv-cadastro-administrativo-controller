package br.com.hmv.services;

import br.com.hmv.dtos.general.ConvenioDTO;
import br.com.hmv.dtos.request.ConvenioAtualizaAllRequestDTO;
import br.com.hmv.dtos.request.ConvenioAtualizaStatusRequestDTO;
import br.com.hmv.dtos.request.ConvenioInsertRequestDTO;
import br.com.hmv.dtos.responses.ConvenioDefaultResponseDTO;
import br.com.hmv.exceptions.DatabaseException;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.Convenio;
import br.com.hmv.models.enums.StatusConvenioEnum;
import br.com.hmv.repositories.ConvenioRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConvenioService {
	private static Logger logger = LoggerFactory.getLogger(ConvenioService.class);
	private ConvenioRepository repository;

	@Transactional
	public ConvenioDefaultResponseDTO criacao(ConvenioInsertRequestDTO dto) {
		logger.info("solicitacao de inclusao {}", dto );

		Convenio entity = new Convenio();
		dtoToEntityOnCreate(dto, entity);
		entity = repository.save(entity);

		logger.info("Convenio incluido com sucesso {}",entity );
		return new ConvenioDefaultResponseDTO(entity);
	}

	@Transactional
	public ConvenioDefaultResponseDTO updateStatus(Long id, ConvenioAtualizaStatusRequestDTO dto) {
		try {
			Convenio entity = repository.getOne(id);

			//passa status novo
			entity.setCodigoStatusConvenio(dto.getStatusConvenioEnum().getCodigoStatusConvenio());

			entity = repository.save(entity);
			return new ConvenioDefaultResponseDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Convenio nao encontrado id: " + id);
		}
	}

	@Transactional
	public ConvenioDefaultResponseDTO updateAll(Long id, ConvenioAtualizaAllRequestDTO dto) {
		try {
			Convenio entity = repository.getOne(id);

			//Passa dados atualizados
			entity.setCodigoStatusConvenio(dto.getStatusConvenioEnum().getCodigoStatusConvenio());
			entity.setDescricao(dto.getDescricao());

			entity = repository.save(entity);
			return new ConvenioDefaultResponseDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Convenio nao encontrado id: " + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Convenio nao encontrado id: " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation - Ao deletar convenio id: " + id);
		}
	}

	@Transactional(readOnly = true)
	public Page<ConvenioDefaultResponseDTO> findAllPaged(Pageable pageable) {
		Page<Convenio> list = repository.findAll(pageable);
		return list.map(itemConvenioEntity -> new ConvenioDefaultResponseDTO(itemConvenioEntity));
	}

	@Transactional(readOnly = true)
	public ConvenioDefaultResponseDTO findById(Long id) {
		Optional<Convenio> obj = repository.findById(id);
		Convenio entity = obj.orElseThrow(() -> new ResourceNotFoundException("Convenio nao encontrado id: " + id));
		return new ConvenioDefaultResponseDTO(entity);
	}

	private void dtoToEntityOnCreate(ConvenioDTO dto, Convenio entity) {
		logger.info("Convertendo dto de cricao para entity {}", dto );

		entity.setDescricao(dto.getDescricao());
		entity.setCodigoStatusConvenio(StatusConvenioEnum.ATIVO.getCodigoStatusConvenio());
	}
}
