package it.uninsubria.campusar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import it.uninsubria.campusar.dto.LocationDto;
import it.uninsubria.campusar.dto.LocationInfoDto;
import it.uninsubria.campusar.entity.LocationEntity;
import it.uninsubria.campusar.entity.LocationInfoEntity;

@ExtendWith(RandomBeansExtension.class)
public class LocationConverterTest {
    
    private LocationConverter converter = Mockito.spy(new LocationConverter());

    @Test
    void shouldBeAbleToConvertToEntity(@Random LocationDto dto) {
        LocationInfoEntity infoEntity = Mockito.mock(LocationInfoEntity.class);
        List<LocationInfoEntity> infos = new ArrayList<>();
        dto.getInfos().forEach(i -> infos.add(infoEntity));
        LocationEntity expected = LocationEntity.builder()
            .id(dto.getId())
            .name(dto.getName())
            .latitude(dto.getLatitude())
            .longitude(dto.getLongitude())
            .infos(infos)
            .build();
        Mockito.doReturn(infoEntity).when(converter).convertInfoDtoToEntity(Mockito.argThat(e -> dto.getInfos().contains(e)));
        Mockito.doNothing().when(infoEntity).setLocation(Mockito.any(LocationEntity.class));
        LocationEntity actual = converter.toEntity(dto);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLatitude(), actual.getLatitude());
        assertEquals(expected.getLongitude(), actual.getLongitude());
        assertEquals(expected.getInfos().size(), actual.getInfos().size());
        Mockito.verify(converter, Mockito.times(infos.size())).convertInfoDtoToEntity(Mockito.argThat(e -> dto.getInfos().contains(e)));
        Mockito.verify(infoEntity, Mockito.times(infos.size())).setLocation(Mockito.any(LocationEntity.class));
    }

    @Test
    void shouldBeAbleToConvertToDto(@Random LocationEntity entity) {
        LocationInfoDto infoDto = Mockito.mock(LocationInfoDto.class);
        List<LocationInfoDto> infos = new ArrayList<>();
        entity.getInfos().forEach(i -> infos.add(infoDto));
        LocationDto expected = LocationDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .latitude(entity.getLatitude())
            .longitude(entity.getLongitude())
            .infos(infos)
            .build();
        Mockito.doReturn(infoDto).when(converter).convertInfoEntityToDto(Mockito.argThat(e -> entity.getInfos().contains(e)));
        LocationDto actual = converter.toDto(entity);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLatitude(), actual.getLatitude());
        assertEquals(expected.getLongitude(), actual.getLongitude());
        assertEquals(expected.getInfos().size(), actual.getInfos().size());
        Mockito.verify(converter, Mockito.times(infos.size())).convertInfoEntityToDto(Mockito.argThat(e -> entity.getInfos().contains(e)));
    }

    @Test
    void shouldBeAbleToConvertInfoDtoToEntity(@Random LocationInfoDto dto) {
        LocationInfoEntity expected = LocationInfoEntity.builder()
            .id(dto.getId())
            .title(dto.getTitle())
            .content(dto.getContent())
            .build();
        LocationInfoEntity actual = converter.convertInfoDtoToEntity(dto);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    void shouldBeAbleToConvertInfoEntityToDto(@Random LocationInfoEntity entity) {
        LocationInfoDto expected = LocationInfoDto.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .build();
        LocationInfoDto actual = converter.convertInfoEntityToDto(entity);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }
}
