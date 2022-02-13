package it.uninsubria.campusar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LOCATION_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationInfoEntity implements PersistenceEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private LocationEntity location;
    
}
