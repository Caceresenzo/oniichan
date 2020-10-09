package caceresenzo.oniichan.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
public abstract class TypeAwareMapper<E, D> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	/* Variables */
	private final Class<E> entityClass;
	private final Class<D> dtoClass;
	
	@SuppressWarnings("unchecked")
	public TypeAwareMapper() {
		Class<?> self = getClass();
		Type[] types = ((ParameterizedType) self.getGenericSuperclass()).getActualTypeArguments();
		
		this.entityClass = (Class<E>) types[0];
		this.dtoClass = (Class<D>) types[1];
		
		if (!getClass().isAnnotationPresent(Component.class)) {
			this.modelMapper = new ModelMapper();
		}
	}
	
	public E toEntity(D dto) {
		if (dto == null) {
			return null;
		}
		
		return modelMapper.map(dto, entityClass);
	}
	
	public D toDto(E entity) {
		if (entity == null) {
			return null;
		}
		
		return modelMapper.map(entity, dtoClass);
	}
	
	public List<E> toEntities(List<D> dtos) {
		return internalListTransform(dtos, this::toEntity);
	}
	
	public List<D> toDtos(List<E> entities) {
		return internalListTransform(entities, this::toDto);
	}
	
	public Page<E> toEntities(Page<D> dtos) {
		return internalPageTransform(dtos, this::toEntity);
	}
	
	public Page<D> toDtos(Page<E> entities) {
		return internalPageTransform(entities, this::toDto);
	}
	
	private final <F, T> List<T> internalListTransform(List<F> fromList, Function<F, T> transformer) {
		return fromList
				.stream()
				.map(transformer)
				.collect(Collectors.toList());
	}
	
	private final <F, T> Page<T> internalPageTransform(Page<F> fromPage, Function<F, T> transformer) {
		return new PageImpl<>(internalListTransform(fromPage.getContent(), transformer), fromPage.getPageable(), fromPage.getTotalElements());
	}
	
	public ModelMapper getWrappedModelMapper() {
		return modelMapper;
	}
	
}