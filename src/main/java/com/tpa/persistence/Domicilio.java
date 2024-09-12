package com.tpa.persistence;

import com.tpa.persistence.entity.Cliente;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="domicilio")
@Audited

public class Domicilio {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre_calle")
    private String nombreCalle;

    @Column(name = "numero")
    private int numero;

    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;

    @Builder

    public Domicilio(String nombreCalle, int numero){
        this.nombreCalle=nombreCalle;
        this.numero=numero;
    }

    public Domicilio(String nombreCalle, int numero, Cliente cliente) {
        this.nombreCalle = nombreCalle;
        this.numero = numero;
        this.cliente = cliente;
    }
}

