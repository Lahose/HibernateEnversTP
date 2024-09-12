package com.tpa.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tpa.persistence.entity.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistenceXmlApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

//            *** PERSIST ***
			Factura factura1 = Factura.builder()
					.numero(12)
					.fecha("10/08/2020")
					.build();

			com.tpa.persistence.Domicilio dom = com.tpa.persistence.Domicilio.builder()
					.nombreCalle("Viamonte")
					.numero(1567)
					.build();

			Cliente cliente = Cliente.builder()
					.nombre("Enrique")
					.apellido("Garcia")
					.dni(15209278)
					.build();

			cliente.setDomicilio(dom);
			dom.setCliente(cliente);
			factura1.setCliente(cliente);

			Categoria perecederos = Categoria.builder()
					.denominacion("Perecederos")
					.build();

			Categoria lacteos = Categoria.builder()
					.denominacion("Lacteos")
					.build();

			Categoria limpieza = Categoria.builder()
					.denominacion("Limpieza")
					.build();

			Articulo art1 = Articulo.builder()
					.cantidad(200)
					.denominacion("Yogurt Ser sabor frutilla")
					.precio(20)
					.build();

			Articulo art2 = Articulo.builder()
					.cantidad(300)
					.denominacion("Detergente Magistral")
					.precio(80)
					.build();


			art1.getCategorias().add(perecederos);
			art1.getCategorias().add(lacteos);
			lacteos.getArticulos().add(art1);
			perecederos.getArticulos().add(art1);

			art2.getCategorias().add(limpieza);
			limpieza.getArticulos().add(art2);

			DetalleFactura det1 = DetalleFactura.builder()
					.cantidad(2)
					.subtotal(40)
					.build();

			det1.setArticulo(art1);

			art1.getDetallesFactura().add(det1);
			factura1.getDetallesFactura().add(det1);
			det1.setFactura(factura1);

			DetalleFactura det2 = DetalleFactura.builder()
					.cantidad(1)
					.subtotal(80)
					.build();

			det2.setArticulo(art2);

			art2.getDetallesFactura().add(det2);
			factura1.getDetallesFactura().add(det2);
			det2.setFactura(factura1);

			factura1.setTotal(120);

			em.persist(factura1);


//            *** MERGE ***
//            Factura factura1 = em.find(Factura.class, 1L);
//            factura1.setNumero(85);
//            em.merge(factura1);


//            *** REMOVE ***
//            Factura factura1 = em.find(Factura.class, 1L);
//            em.remove(factura1);
//
			em.flush();
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		}

		em.close();
		emf.close();

	}
}
