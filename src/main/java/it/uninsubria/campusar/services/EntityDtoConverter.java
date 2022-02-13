package it.uninsubria.campusar.services;

import it.uninsubria.campusar.dto.Dto;
import it.uninsubria.campusar.entity.PersistenceEntity;

public interface EntityDtoConverter<E extends PersistenceEntity, D extends Dto> {

    D toDto(E entity);
    E toEntity(D dto);
}
