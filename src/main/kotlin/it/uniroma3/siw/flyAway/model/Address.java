package it.uniroma3.siw.flyAway.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    private City city;
    private String state;
    private String street;
    private Integer streetNumber;
    private Integer zipCode;
}
