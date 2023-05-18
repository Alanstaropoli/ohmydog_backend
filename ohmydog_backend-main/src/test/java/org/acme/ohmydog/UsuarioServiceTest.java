package org.acme.ohmydog;

import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.requests.UsuarioRequest;
import org.acme.ohmydog.services.UsuarioService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioService usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegister() {
        // Crear un usuario de prueba
        UsuarioRequest usuarioRequest = new UsuarioRequest("ejemplo@example.com", "123",
                "Usuario", "1", 123L, "Localidad", "Direccion", 123L, "cliente");

        // Mockear el comportamiento del repositorio
        when(usuarioRepository.buscarUsuarioPorEmail(usuarioRequest.getEmail())).thenReturn(null); // Indica que no se encontró ningún usuario con ese email
        doNothing().when(usuarioRepository).register(
                usuarioRequest.getEmail(), usuarioRequest.getPassword(),
                usuarioRequest.getNombre(), usuarioRequest.getApellido(),
                usuarioRequest.getDni(), usuarioRequest.getLocalidad(),
                usuarioRequest.getDireccion(), usuarioRequest.getTelefono(), usuarioRequest.getRol()
        ); // Indica que se espera que se llame al metodo "register" con los parametros especificados y no haga nada

        // Verificar el resultado
        assertTrue(usuarioService.register(usuarioRequest)); // Verifica que el resultado sea true, es decir, el usuario se registró correctamente

        // Verificar que se hayan realizado las interacciones esperadas con el repositorio
        verify(usuarioRepository, times(1)).buscarUsuarioPorEmail(usuarioRequest.getEmail());
        verify(usuarioRepository, times(1)).register(
                usuarioRequest.getEmail(), usuarioRequest.getPassword(),
                usuarioRequest.getNombre(), usuarioRequest.getApellido(),
                usuarioRequest.getDni(), usuarioRequest.getLocalidad(),
                usuarioRequest.getDireccion(), usuarioRequest.getTelefono(), usuarioRequest.getRol()
        );
    }

    @Test
    public void testModificarUsuario() {
        // Crear un usuario de prueba
        Usuario usuario = new Usuario();

        // Mockear el comportamiento del repositorio
        when(usuarioRepository.buscarUsuarioPorId(usuario.getId())).thenReturn(usuario); // Indica que se encontró el usuario con el ID especificado
        doNothing().when(usuarioRepository).persist(usuario); // Indica que se espera que se llame al método "persist" con el usuario especificado y no haga nada

        // Llamar al metodo de prueba
        boolean result = usuarioService.modificarUsuario(
                usuario.getId(), "nuevo_email", "nueva_contrasena",
                "nuevo_nombre", "nuevo_apellido", 123456789L,
                "nueva_localidad", "nueva_direccion", 987654321L, "cliente"
        );

        // Verificar el resultado
        assertTrue(result); // Verifica que el resultado sea true, es decir, el usuario se modificó correctamente

        // Verificar que se hayan realizado las interacciones esperadas con el repositorio
        verify(usuarioRepository, times(1)).buscarUsuarioPorId(usuario.getId());
        verify(usuarioRepository, times(1)).persist(usuario);
    }

    @Test
    public void testEliminarUsuario() {
        // Crear un usuario de prueba
        Usuario usuario = new Usuario();

        // Mockear el comportamiento del repositorio
        when(usuarioRepository.buscarUsuarioPorId(usuario.getId())).thenReturn(usuario); // Indica que se encontró el usuario con el ID especificado
        doNothing().when(usuarioRepository).eliminate(usuario); // Indica que se espera que se llame al método "eliminate" con el usuario especificado y no haga nada

        // Llamar al metodo de prueba
        boolean result = usuarioService.eliminarUsuario(usuario.getId());

        // Verificar el resultado
        assertTrue(result); // Verifica que el resultado sea true, es decir, el usuario se eliminó correctamente

        // Verificar que se hayan realizado las interacciones esperadas con el repositorio
        verify(usuarioRepository, times(1)).buscarUsuarioPorId(usuario.getId());
        verify(usuarioRepository, times(1)).eliminate(usuario);
    }
}
