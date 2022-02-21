package br.com.hmv.services;

import br.com.hmv.dtos.general.HospitalDTO;
import br.com.hmv.dtos.request.ConvenioAtualizaStatusRequestDTO;
import br.com.hmv.dtos.request.HospitalAtualizaStatusUnidadeRequestDTO;
import br.com.hmv.dtos.request.HospitalUnidadeInsertRequestDTO;
import br.com.hmv.dtos.responses.HospitalDefaultResponseDTO;
import br.com.hmv.dtos.responses.HospitalDefaultResponseDTO;
import br.com.hmv.exceptions.DatabaseException;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.Convenio;
import br.com.hmv.models.entities.Endereco;
import br.com.hmv.models.entities.Hospital;
import br.com.hmv.models.enums.StatusUnidadeHospitalEnum;
import br.com.hmv.repositories.EnderecoRepository;
import br.com.hmv.repositories.HospitalRepository;
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
import java.util.UUID;

@Service
@AllArgsConstructor
public class HospitalService {
    private static Logger logger = LoggerFactory.getLogger(HospitalService.class);
    private HospitalRepository hospitalRepository;
    private EnderecoRepository enderecoRepository;

    @Transactional
    public HospitalDefaultResponseDTO criacao(HospitalUnidadeInsertRequestDTO dto) {
        String logCode = "criacao(HospitalUnidadeInsertRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        Hospital entity = new Hospital();
        dtoToEntityOnCreate(dto, entity);
        entity = hospitalRepository.save(entity);

        logger.info("{} - Convenio incluido com sucesso {}", logCode, entity);
        return new HospitalDefaultResponseDTO(entity);
    }

    @Transactional
    public HospitalDefaultResponseDTO updateStatus(String codigoUnidade, HospitalAtualizaStatusUnidadeRequestDTO dto) {
        String logCode = "updateStatus(String, HospitalAtualizaStatusUnidadeRequestDTO)";
        logger.info("{} - solicitacao de atualizacao de status {}", logCode, dto);

        try {
            var objOptional = hospitalRepository.findHospitalsByCodigoUnidade(codigoUnidade);
            Hospital entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + codigoUnidade));

            //passa status novo
            entity.setCodigoStatusUnidade(dto.getStatusUnidadeHospitalEnum().getCodigoStatusHospitalUnidade());
            entity = hospitalRepository.save(entity);

            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entity);
            return new HospitalDefaultResponseDTO(entity);
        } catch (EntityNotFoundException e) {
            logger.warn("{} - recurso nao encontrado id: {} ", codigoUnidade);
            throw new ResourceNotFoundException("Recurso nao encontrado id: " + codigoUnidade);
        }
    }

    @Transactional
    public void delete(String codigoUnidade) {
        String logCode = "delete(String)";
        logger.info("{} - deletando recurso: {}", logCode, codigoUnidade);

        try {
            hospitalRepository.deleteByCodigoUnidade(codigoUnidade);
            logger.info("{} - recurso deletado com sucesso: {}", logCode, codigoUnidade);

        } catch (EmptyResultDataAccessException e) {
            logger.warn("{} - recurso nao encontrado: {}", logCode, codigoUnidade);
            throw new ResourceNotFoundException("Recurso nao encontrado id: " + codigoUnidade);

        } catch (DataIntegrityViolationException e) {
            logger.warn("{} - erro de integridade de dados: {}", logCode, codigoUnidade);
            throw new DatabaseException("Integrity violation - Ao deletar convenio id: " + codigoUnidade);
        }
    }

    @Transactional(readOnly = true)
    public Page<HospitalDefaultResponseDTO> findAllPaged(Pageable pageable) {
        String logCode = "findAllPaged(Pageable)";
        logger.info("{} - consulta paginada de recursos vide parametros {}", logCode, pageable);

        Page<Hospital> list = hospitalRepository.findAll(pageable);
        logger.info("{} - consulta paginada de recursos realizada com sucesso: {}", logCode, list);
        return list.map(itemHospitalEntity -> new HospitalDefaultResponseDTO(itemHospitalEntity));
    }

    @Transactional(readOnly = true)
    public HospitalDefaultResponseDTO findByIdCodigoUnidade(String codigoUnidade) {
        String logCode = "findById(String)";
        logger.info("{} - buscando recurso pelo id: {}", logCode, codigoUnidade);

        Optional<Hospital> obj = hospitalRepository.findHospitalsByCodigoUnidade(codigoUnidade);
        Hospital entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + codigoUnidade));

        logger.info("{} - recurso encontrado: {}", logCode, entity);
        return new HospitalDefaultResponseDTO(entity);
    }

    private void dtoToEntityOnCreate(HospitalDTO dto, Hospital entity) {
        String logCode = "dtoToEntityOnCreate(HospitalDTO , Hospital)";
        logger.info("{} - convertendo dto de cricao para entity {}", logCode, dto);

        entity.setCodigoUnidade(UUID.randomUUID().toString());
        entity.setNomeUnidade(dto.getNomeUnidade());
        entity.setCodigoStatusUnidade(StatusUnidadeHospitalEnum.ATIVA.getCodigoStatusHospitalUnidade());

        //Endereco endereco = enderecoRepository.getOne(dto.getEndereco().getId());
        Endereco endereco = new Endereco();
        endereco.setCodigoEndereco(UUID.randomUUID().toString());
        endereco.setDescricao(dto.getEndereco().getDescricao());
        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setUf(dto.getEndereco().getUf());
        endereco.setCep(dto.getEndereco().getCep());
        entity.setEndereco(endereco);
        logger.info("{} - conversao realizada com sucesso {}", logCode, entity);
    }
}
