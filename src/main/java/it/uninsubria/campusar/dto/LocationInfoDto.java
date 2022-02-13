package it.uninsubria.campusar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationInfoDto implements Dto{
    
    private Integer id;

    private String title;

    private String content;
}
