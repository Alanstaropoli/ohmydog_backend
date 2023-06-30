package org.acme.ohmydog;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.entities.Campania;

import java.time.LocalDate;

@ApplicationScoped
public class MiApp {
        @PersistenceContext
        EntityManager entityManager;

        @Transactional
        void agregarUsuarios(@Observes StartupEvent event) {
                // Código para agregar usuarios a tu base de datos
                Usuario admin = new Usuario("admin@mail.com", "0", "admin", "admin", 0L, "Localidad0", "Direccion0",
                                123456789L,
                                "veterinario");
                Usuario usuario1 = new Usuario("usuario1@mail.com", "1", "usuario1", "1", 1L, "Localidad1",
                                "Direccion1",
                                123456789L, "cliente");
                Usuario usuario2 = new Usuario("usuario2@mail.com", "2", "usuario2", "2", 2L, "Localidad2",
                                "Direccion2",
                                223456789L, "cliente");
                Usuario usuario3 = new Usuario("usuario3@mail.com", "3", "usuario3", "3", 3L, "Localidad3",
                                "Direccion3",
                                323456789L, "cliente");

                // Persistir el usuario en la base de datos
                entityManager.persist(admin);
                entityManager.persist(usuario1);
                entityManager.persist(usuario2);
                entityManager.persist(usuario3);

                Perro perro1 = new Perro("Perro1", "Raza1", 6, "Ninguna", "Masculino", "Caracteristicas");
                Perro perro2 = new Perro("Perro2", "Raza2", 4, "Ninguna", "Masculino", "Caracteristicas");
                Perro perro3 = new Perro("Perro3", "Raza3", 1, "Ninguna", "Masculino", "Caracteristicas");
                entityManager.persist(perro1);
                entityManager.persist(perro2);
                usuario1.agregarPerro(perro1);
                usuario2.agregarPerro(perro2);
                usuario2.agregarPerro(perro3);

        }
}
