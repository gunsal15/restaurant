package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WaiterDto {
    private Long id;
    private String name;
    private String surname;
    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;
    private String phone;
    private String mail;
    private MediaDto media;
}
