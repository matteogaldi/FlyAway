package it.uniroma3.siw.flyAway.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @OneToOne
    private Accommodation accommodation;

    @OneToOne
    private City city;

    @ManyToOne
    private User user;

}
