package it.uninsubria.campusar.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import it.uninsubria.campusar.dto.LocationDto;
import it.uninsubria.campusar.dto.LocationListDto;
import it.uninsubria.campusar.entity.LocationEntity;
import it.uninsubria.campusar.repository.LocationRepository;
import it.uninsubria.campusar.services.EntityDtoConversionManager;
import it.uninsubria.campusar.services.LocationConverter;

@ExtendWith(RandomBeansExtension.class)
public class RestApiControllerTest {

    private final LocationRepository locationRepo = Mockito.mock(LocationRepository.class);
    private final EntityDtoConversionManager converter = Mockito.mock(EntityDtoConversionManager.class);

    private final RestApiController controller = Mockito.spy(new RestApiController(locationRepo, converter));

    @Test
    void shouldBeAbleToGetLocationList(){
        LocationDto dto = Mockito.mock(LocationDto.class);
        LocationEntity entity = Mockito.mock(LocationEntity.class);
        List<LocationEntity> entities = Arrays.asList(entity, entity, entity);
        Mockito.doReturn(entities).when(locationRepo).findAll();
        Mockito.doReturn(dto).when(converter).toDto(entity, LocationConverter.ENTITY_CODE);
        List<LocationDto> results = controller.getPlaces();
        results.forEach(e -> assertEquals(dto, e));
        Mockito.verify(locationRepo, Mockito.times(1)).findAll();
        Mockito.verify(converter, Mockito.times(entities.size())).toDto(entity, LocationConverter.ENTITY_CODE);
    }

    @Test
    void shouldBeAbleToAddLocation() {
        LocationDto dto = Mockito.mock(LocationDto.class);
        LocationEntity entity = Mockito.mock(LocationEntity.class);
        Mockito.doReturn(entity).when(converter).toEntity(dto, LocationConverter.ENTITY_CODE);
        Mockito.doReturn(entity).when(locationRepo).save(entity);
        ResponseEntity<?> resp = controller.addLocation(dto);
        Mockito.verify(locationRepo, Mockito.times(1)).save(entity);
        Mockito.verify(converter, Mockito.times(1)).toEntity(dto, LocationConverter.ENTITY_CODE);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Test
    void shouldBeAbleToDeleteLocation(@Random Integer locationId) {
        LocationEntity entity = Mockito.mock(LocationEntity.class);
        Mockito.doReturn(Optional.of(entity)).when(locationRepo).findById(locationId);
        Mockito.doNothing().when(locationRepo).delete(entity);
        ResponseEntity<?> resp = controller.deleteLocation(locationId);
        Mockito.verify(locationRepo, Mockito.times(1)).delete(entity);
        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldBeAbleToAddLocationList(@Random LocationListDto locationList) {
        LocationEntity entity = Mockito.mock(LocationEntity.class);
        List<LocationEntity> entities = Mockito.mock(List.class);
        Mockito.doReturn(entity).when(converter).toEntity(Mockito.argThat(dto -> locationList.getLocations().contains(dto)), Mockito.eq(LocationConverter.ENTITY_CODE));
        Mockito.doReturn(entities).when(locationRepo).saveAll(Mockito.anyList());
        ResponseEntity<?> resp = controller.addLocationList(locationList);
        Mockito.verify(converter, Mockito.times(locationList.getLocations().size())).toEntity(Mockito.argThat(dto -> locationList.getLocations().contains(dto)), Mockito.eq(LocationConverter.ENTITY_CODE));
        Mockito.verify(locationRepo, Mockito.times(1)).saveAll(Mockito.anyList());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }
}
