package com.assignments.project.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "idcardnumber"),
                @UniqueConstraint(columnNames = "cnp")
        })
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String firstname;

    @Column(nullable = false, length = 20)
    private String lastname;

    @Column(nullable = false, length = 8)
    private String idcardnumber;

    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 13)
    private String cnp;

    @Column(nullable = false, length = 120)
    private String address;
}