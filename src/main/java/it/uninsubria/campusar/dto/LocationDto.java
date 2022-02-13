package it.uninsubria.campusar.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto implements Dto{
    
    private Integer id;

    private String name;

    private Double latitude;

    private Double longitude;

    private String image;

    private List<LocationInfoDto> infos;
}
