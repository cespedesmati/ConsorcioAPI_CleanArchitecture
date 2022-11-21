package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "personas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class PersonaEntity {
    @Id
    @Column
    protected String documento;
    @Column
    protected String nombre;

    @Builder.Default
    @Column
    private String contrasenia = "1234";

    @Builder.Default
    @Column
    private Boolean admin = false;
}
