package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity(name = "reclamos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Data
public class ReclamoEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReclamo;
    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    private PersonaEntity documento;
    @OneToOne
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    private EdificioEntity codigo;
    @Column
    private String ubicacion;
    @Column
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "identificador")
    private UnidadEntity identificador;
    @Column
    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    private EstadoEntity estado = EstadoEntity.nuevo;
    @OneToMany
    @ToString.Exclude
    private List<ImagenEntity> imagenes;

}