package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity(name = "edificios")
@NoArgsConstructor
@AllArgsConstructor
@Table
@Data
public class EdificioEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column
    private String nombre;

    @Column
    private String direccion;

    @OneToMany(mappedBy = "codigoEdificio")
    @ToString.Exclude
    private List<UnidadEntity> unidades;
}
