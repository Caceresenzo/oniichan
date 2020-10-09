package caceresenzo.oniichan.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import caceresenzo.oniichan.common.exception.EntityNotFoundException;

public interface ICRUDService<E, C, U, I> {
	
	E create(C data);
	
	default E read(I id) {
		return getRepository().findById(id).orElseThrow(() -> EntityNotFoundException.create(getEntityClass(), id));
	}
	
	E update(I id, U data);
	
	default E delete(I id) {
		E entity = read(id);
		
		getRepository().delete(entity);
		
		return entity;
	}
	
	JpaRepository<E, I> getRepository();
	
	default List<E> list() {
		return getRepository().findAll();
	}
	
	default Page<E> list(Pageable pageable) {
		return getRepository().findAll(pageable);
	}
	
	Class<E> getEntityClass();
	
}