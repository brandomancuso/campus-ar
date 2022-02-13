package it.uninsubria.campusar.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uninsubria.campusar.dto.LocationDto;
import it.uninsubria.campusar.dto.LocationListDto;
import it.uninsubria.campusar.entity.LocationEntity;
import it.uninsubria.campusar.repository.LocationRepository;
import it.uninsubria.campusar.services.EntityDtoConversionManager;
import it.uninsubria.campusar.services.LocationConverter;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private final LocationRepository locationRepo;
    private final EntityDtoConversionManager converter;

    @Autowired
    public RestApiController(LocationRepository locationRepo,
                            EntityDtoConversionManager converter) {
        this.locationRepo = locationRepo;
        this.converter = converter;
    }

    @GetMapping("/places")
    public List<LocationDto> getPlaces() {
        List<LocationEntity> entities = locationRepo.findAll();
        return entities.stream()
            .map(entity -> (LocationDto) converter.toDto(entity, LocationConverter.ENTITY_CODE))
            .collect(Collectors.toList());
    }

    @PostMapping("/location")
    public ResponseEntity<?> addLocation(@RequestBody LocationDto location) {
        LocationEntity entity = converter.toEntity(location, LocationConverter.ENTITY_CODE);
        locationRepo.save(entity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/location/{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Integer locationId) {
        locationRepo.findById(locationId).ifPresent(entity -> locationRepo.delete(entity));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/location/list")
    public ResponseEntity<?> addLocationList(@RequestBody LocationListDto location) {
        List<LocationEntity> locationEntities = location.getLocations().stream()
            .map(dto -> converter.toEntity(dto, LocationConverter.ENTITY_CODE))
            .map(LocationEntity.class::cast)
            .collect(Collectors.toList());
        locationRepo.saveAll(locationEntities);
        return ResponseEntity.ok().build();
    }
}
