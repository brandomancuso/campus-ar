package it.uninsubria.campusar.services;

import org.springframework.stereotype.Service;

import it.uninsubria.campusar.dto.DummyDto;
import it.uninsubria.campusar.entity.DummyEntity;

@Service
@HandleEntity(DummyConverter.ENTITY_CODE)
public class DummyConverter implements EntityDtoConverter<DummyEntity, DummyDto>{

    public static final String ENTITY_CODE = "DUMMY";

    @Override
    public DummyDto toDto(DummyEntity entity) {
        throw new RuntimeException("Not implemented. To be mocked for test purpose.");
    }

    @Override
    public DummyEntity toEntity(DummyDto dto) {
        throw new RuntimeException("Not implemented. To be mocked for test purpose.");
    }
    
}
