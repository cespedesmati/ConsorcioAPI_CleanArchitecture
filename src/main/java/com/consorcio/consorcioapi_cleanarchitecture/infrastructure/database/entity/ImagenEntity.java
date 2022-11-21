package com.consorcio.consorcioapi_cleanarchitecture.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name = "imagenes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class ImagenEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;
    @Column
    private String path;
    @Column
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "idReclamo")
    private ReclamoEntity idReclamo;
}
