package com.logiexpress.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paquetes")
@Data
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoRastreo;

    private String descripcion;
    private Double pesoKg;

    @Enumerated(EnumType.STRING)
    private EstadoPaquete estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}

