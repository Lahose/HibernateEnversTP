package com.tpa.persistence.entity;

import javax.persistence.*;

import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articulo")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Audited
@Getter
@Setter

public class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="denominacion")
	private String denominacion;

	@Column(name="precio")
	private int precio;

	@Column(name="cantidad")
	private int cantidad;

	@OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
	private List<DetalleFactura> detallesFactura = new ArrayList<DetalleFactura>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "articulo_categoria",
			joinColumns = @JoinColumn(name = "articulo_id"),
			inverseJoinColumns = @JoinColumn(name="categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<Categoria>();

	@Builder

	public Articulo(String denominacion, int precio, int cantidad){
		this.denominacion=denominacion;
		this.precio=precio;
		this.cantidad=cantidad;
	}

}
