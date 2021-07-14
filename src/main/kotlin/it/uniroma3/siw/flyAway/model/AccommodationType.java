package it.uniroma3.siw.flyAway.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
public class AccommodationType {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Boolean animalsAllowed;
}
