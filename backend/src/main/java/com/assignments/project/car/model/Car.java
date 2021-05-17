package com.assignments.project.car.model;

import com.assignments.project.client.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "matNb"),
                @UniqueConstraint(columnNames = "vin")
        })
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String make;

    @Column(nullable = false, length = 20)
    private String model;

    @Column(nullable = false, length = 8)
    private String matNb;

    @Column(nullable = false, length = 17)
    private String vin;

    @Column(nullable = false)
    private LocalDate year;

    @ManyToOne(cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
