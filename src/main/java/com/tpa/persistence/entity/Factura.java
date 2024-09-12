package com.tpa.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "factura")
@Audited
@Data
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="fecha")
	private String fecha;

	@Column(name="numero")
	private int numero;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="fk_cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleFactura> detallesFactura = new ArrayList<DetalleFactura>();

	@Builder

	public Factura(String fecha, int numero, int total){
		this.fecha=fecha;
		this.numero=numero;
		this.total=total;
	}

	@Column(name="total")
	private int total;

}
