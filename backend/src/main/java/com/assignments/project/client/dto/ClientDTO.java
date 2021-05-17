package com.assignments.project.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String idcardnumber;
    private String cnp;
    private String email;
    private String address;
}
