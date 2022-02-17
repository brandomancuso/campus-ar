package it.uninsubria.campusar.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.uninsubria.campusar.dto.LocationDto;
import it.uninsubria.campusar.dto.LocationInfoDto;
import it.uninsubria.campusar.entity.LocationEntity;
import it.uninsubria.campusar.entity.LocationInfoEntity;

@Service
@HandleEntity(LocationConverter.ENTITY_CODE)
public class LocationConverter implements EntityDtoConverter<LocationEntity, LocationDto>{

    public static final String ENTITY_CODE = "LOCATION";

    @Override
    public LocationDto toDto(LocationEntity entity) {
        List<LocationInfoDto> infos = entity.getInfos().stream()
            .map(this::convertInfoEntityToDto)
            .collect(Collectors.toList());
        return LocationDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .latitude(entity.getLatitude())
            .longitude(entity.getLongitude())
            .infos(infos)
            .build();
    }

    @Override
    public LocationEntity toEntity(LocationDto dto) {
        List<LocationInfoEntity> infos = Optional.ofNullable(dto.getInfos())
            .map(infoDtoList -> infoDtoList.stream()
            .map(this::convertInfoDtoToEntity)
            .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
        LocationEntity entity = LocationEntity.builder()
            .id(dto.getId())
            .name(dto.getName())
            .latitude(dto.getLatitude())
            .longitude(dto.getLongitude())
            .infos(infos)
            .build();
        entity.getInfos().forEach(info -> info.setLocation(entity));
        return entity;
    }

    protected LocationInfoEntity convertInfoDtoToEntity(LocationInfoDto infoDto) {
        return LocationInfoEntity.builder()
            .id(infoDto.getId())
            .title(infoDto.getTitle())
            .content(infoDto.getContent())
            .build();
    }

    protected LocationInfoDto convertInfoEntityToDto(LocationInfoEntity infoEntity) {
        return LocationInfoDto.builder()
            .id(infoEntity.getId())
            .title(infoEntity.getTitle())
            .content(infoEntity.getContent())
            .build();
    }
    
}
