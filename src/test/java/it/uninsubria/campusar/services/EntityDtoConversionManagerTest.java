package it.uninsubria.campusar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import it.uninsubria.campusar.dto.DummyDto;
import it.uninsubria.campusar.entity.DummyEntity;

@SuppressWarnings("rawtypes")
public class EntityDtoConversionManagerTest {
    
    private final DummyConverter dummyConverter = Mockito.mock(DummyConverter.class);
    private final List<EntityDtoConverter> converters = Arrays.asList(dummyConverter);

    private final EntityDtoConversionManager manager = Mockito.spy(new EntityDtoConversionManager(converters));

    @Test
    void shouldBeAbleToLoadServiceMap() {
        assertEquals(converters.size(), manager.getConverterMap().size());
        assertEquals(dummyConverter, manager.getConverterMap().get(DummyConverter.ENTITY_CODE));
    }

    @Test
    void shouldBeAbleToConvertEntityToDto(){
        DummyDto expected = Mockito.mock(DummyDto.class);
        DummyEntity entity = Mockito.mock(DummyEntity.class);
        Mockito.doReturn(expected).when(dummyConverter).toDto(entity);
        DummyDto actual = manager.toDto(entity, DummyConverter.ENTITY_CODE);
        assertSame(expected, actual);
    }

    @Test
    void shouldBeAbleToConvertDtoToEntity(){
        DummyDto dto = Mockito.mock(DummyDto.class);
        DummyEntity expected = Mockito.mock(DummyEntity.class);
        Mockito.doReturn(expected).when(dummyConverter).toEntity(dto);
        DummyEntity actual = manager.toEntity(dto, DummyConverter.ENTITY_CODE);
        assertSame(expected, actual);
    }
}
