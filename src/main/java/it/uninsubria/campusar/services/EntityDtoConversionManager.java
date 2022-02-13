package it.uninsubria.campusar.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uninsubria.campusar.dto.Dto;
import it.uninsubria.campusar.entity.PersistenceEntity;
import lombok.AccessLevel;
import lombok.Getter;

@SuppressWarnings("rawtypes")
@Service
public class EntityDtoConversionManager {
    
    @Getter(AccessLevel.PROTECTED)
    private final Map<String, EntityDtoConverter> converterMap;

    @Autowired
    public EntityDtoConversionManager(List<EntityDtoConverter> converters) {
        converterMap = converters.stream()
            .collect(Collectors.toMap(c -> {
                Optional<HandleEntity> handleEntity = Optional.ofNullable(c.getClass().getAnnotation(HandleEntity.class));
                return handleEntity.map(HandleEntity::value)
                    .orElseThrow(() -> new RuntimeException(String.format("Converter \"{}\" misses the annotation \"@HandleEntity\"", c.getClass().getSimpleName())));
            }, c -> c));
    }

    @SuppressWarnings("unchecked")
    public <D extends Dto, E extends PersistenceEntity> D toDto(E entity, String entityCode) {
        return (D) converterMap.get(entityCode).toDto(entity);
    }

    @SuppressWarnings("unchecked")
    public <D extends Dto, E extends PersistenceEntity> E toEntity(D dto, String entityCode) {
        return (E) converterMap.get(entityCode).toEntity(dto);
    }
}
