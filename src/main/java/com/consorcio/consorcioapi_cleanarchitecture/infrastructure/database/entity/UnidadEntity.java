package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity;
import com.consorcio.consorcioapi_cleanarchitecture.application.exception.UnidadException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "unidades")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Data
public class UnidadEntity {
    @Id
    @Column(name = "identificador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;

    @Column
    private String piso;

    @Column
    private String numero;

    @Column(nullable = false, columnDefinition = "varchar(1) default ''")
    private String habitado;

    @ManyToMany
    @JoinTable(
            name="duenios",
            joinColumns = @JoinColumn( name="identificador"),
            inverseJoinColumns = @JoinColumn( name="documento")
    )
    @OrderColumn(name = "id")
    private Set<PersonaEntity> duenios = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="inquilinos",
            joinColumns = @JoinColumn( name="identificador"),
            inverseJoinColumns = @JoinColumn( name="documento")
    )
    @OrderColumn(name = "id")
    private Set<PersonaEntity> inquilinos = new HashSet<>();
}
