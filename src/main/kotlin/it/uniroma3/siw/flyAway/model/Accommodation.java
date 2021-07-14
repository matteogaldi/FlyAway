package it.uniroma3.siw.flyAway.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
public class Accommodation {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Integer rating;
    private Float price;
    @OneToOne
    private AccommodationType type;
    @OneToOne
    private Address address;
}
