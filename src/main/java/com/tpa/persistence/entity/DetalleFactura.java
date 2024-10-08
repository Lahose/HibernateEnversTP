package com.tpa.persistence.entity;

import javax.persistence.*;

import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "detalle_factura")
@Data
@Audited
public class DetalleFactura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="cantidad")
	private int cantidad;

	@Column(name="subtotal")
	private int subtotal;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_factura")
	private Factura factura;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="fk_articulo")
	private Articulo articulo;

	@Builder

	public DetalleFactura(int cantidad, int subtotal){
		this.cantidad=cantidad;
		this.subtotal=subtotal;
	}
}
