package it.uniroma3.siw.flyAway.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Getter
@Setter
@Entity
public class City {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String state;
}
