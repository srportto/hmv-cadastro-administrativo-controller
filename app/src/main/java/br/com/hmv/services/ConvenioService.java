package br.com.hmv.services;

import br.com.hmv.dtos.general.ConvenioDTO;
import br.com.hmv.dtos.request.ConvenioInsertRequestDTO;
import br.com.hmv.dtos.request.UserInsertRequestDTO;
import br.com.hmv.dtos.responses.ConvenioDefaultResponseDTO;
import br.com.hmv.exceptions.DatabaseException;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.Convenio;
import br.com.hmv.repositories.RoleRepository;
import br.com.hmv.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConvenioService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(ConvenioService.class);
	
	private BCryptPasswordEncoder passwordEncoder;
	private UserRepository repository;
	private RoleRepository roleRepository;


	@Transactional
	public ConvenioDefaultResponseDTO insert(ConvenioInsertRequestDTO dto) {
		Convenio entity = new Convenio();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDefaultResponseDTO(entity);
	}

	//	@Transactional
//	public UserExtendsResponseDTO update(Long id, UserUpdateRequestDTO dto) {
//		try {
//			User entity = repository.getOne(id);
//			copyDtoToEntity(dto, entity);
//			entity = repository.save(entity);
//			return new UserExtendsResponseDTO(entity);
//		}
//		catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException("Id not found " + id);
//		}
//	}
//
//	public void delete(Long id) {
//		try {
//			repository.deleteById(id);
//		}
//		catch (EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException("Id not found " + id);
//		}
//		catch (DataIntegrityViolationException e) {
//			throw new DatabaseException("Integrity violation");
//		}
//	}



//
//	@Transactional(readOnly = true)
//	public Page<UserDefaultResponseDTO> findAllPaged(Pageable pageable) {
//		Page<User> list = repository.findAll(pageable);
//		return list.map(x -> new UserDefaultResponseDTO(x));
//	}
//
//	@Transactional(readOnly = true)
//	public UserExtendsResponseDTO findById(Long id) {
//		Optional<User> obj = repository.findById(id);
//		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
//		return new UserExtendsResponseDTO(entity);
//	}
//
//
	private void copyDtoToEntity(ConvenioDTO dto, Convenio entity) {
		entity.setDescricao(dto.getDescricao());
	}

}
