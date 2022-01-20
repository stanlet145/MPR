package pl.edu.pjatk.cardictionary.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(generator = "car_seq_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "car_seq_generator", sequenceName = "car_sequence", allocationSize = 1)
    @Column(name = "car_id", unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_brand")
    private Brand brand;

    @Column(name = "car_model")
    private String model;
}
