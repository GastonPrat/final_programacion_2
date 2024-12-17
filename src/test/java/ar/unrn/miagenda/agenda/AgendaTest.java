package ar.unrn.miagenda.agenda;

import ar.unrn.miagenda.contacto.Contacto;
import ar.unrn.miagenda.evento.Evento;
import ar.unrn.miagenda.excepciones.AgendaDeContactosVaciaException;
import ar.unrn.miagenda.excepciones.AgendaDeEventosVaciaException;
import ar.unrn.miagenda.excepciones.ContactoDuplicadoException;
import ar.unrn.miagenda.excepciones.ContactoNoEncontradoEnAgendaException;
import ar.unrn.miagenda.excepciones.ContactoNoEncontradoEnEventoException;
import ar.unrn.miagenda.excepciones.ContactoYaInvitadoException;
import ar.unrn.miagenda.excepciones.EventoDuplicadoException;
import ar.unrn.miagenda.excepciones.EventoNoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Clase de pruebas unitarias para la clase {@link Agenda}.
 */
class AgendaTest {

    private Agenda agenda;
    private Contacto demian;
    private Contacto alma;
    private Contacto karina;
    private Contacto gaston;
    private Contacto juan;
    private Evento cumpleDeAlma;
    private List<Contacto> invitadosCumpleAlma;

    /**
     * Configura los datos necesarios para cada prueba.
     *
     * @throws ContactoDuplicadoException Si se intenta agregar un contacto duplicado.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra un
     * contacto al agregar.
     * @throws EventoDuplicadoException Si se intenta crear un evento duplicado.
     */
    @BeforeEach
    void setUp()
            throws ContactoDuplicadoException,
            ContactoNoEncontradoEnAgendaException,
            EventoDuplicadoException {
        agenda = new Agenda();
        // Creación de contactos
        demian = new Contacto(
                "Demian Castañeda",
                "44.121.248",
                LocalDate.of(2002, 6, 27)
        );
        alma = new Contacto(
                "Alma Prat",
                "55.443.563",
                LocalDate.of(2016, 6, 7)
        );
        karina = new Contacto(
                "Karina Reyes",
                "31.244.321",
                LocalDate.of(1984, 9, 21)
        );
        gaston = new Contacto(
                "Gaston Prat",
                "36.324.556",
                LocalDate.of(1992, 6, 11)
        );
        juan = new Contacto(
                "Juan Perez",
                "13.123.456",
                LocalDate.of(1949, 12, 3)
        );
        // Configuración de invitados
        invitadosCumpleAlma = new ArrayList<>();
        invitadosCumpleAlma.add(alma);
        // Creación del evento
        cumpleDeAlma = new Evento(
                "Cumpleaños de Almita",
                LocalDate.of(2024, 6, 7),
                invitadosCumpleAlma
        );
        // Agregar contactos y eventos a la agenda
        agenda.agregarContacto(alma);
        agenda.agregarContacto(demian);
        agenda.agregarContacto(karina);
        agenda.agregarContacto(gaston);
        agenda.crearEvento(cumpleDeAlma);
    }

    /**
     * Prueba para verificar la funcionalidad del metodo agregarContacto.
     *
     * @throws ContactoDuplicadoException Si se intenta agregar un contacto duplicado.
     */
    @Test
    void agregarContacto() throws ContactoDuplicadoException {
        agenda.agregarContacto(juan);
        assertEquals(5, agenda.cantidadTotalContactos());
        assertTrue(agenda.verificarExistenciaContacto(juan));
        assertThrows(ContactoDuplicadoException.class, ()
                -> agenda.agregarContacto(juan));
    }

    /**
     * Prueba para eliminar un contacto de la agenda y verificar su
     * impacto en los eventos.
     *
     * @throws ContactoNoEncontradoEnAgendaException Si el contacto no está en la agenda.
     * @throws AgendaDeEventosVaciaException Si la lista de eventos está vacía.
     */
    @Test
    void eliminarContacto()
            throws ContactoNoEncontradoEnAgendaException,
            AgendaDeEventosVaciaException {
        agenda.eliminarContacto(demian);
        assertFalse(agenda.verificarExistenciaContacto(demian));
        assertEquals(3, agenda.cantidadTotalContactos());
        assertThrows(ContactoNoEncontradoEnAgendaException.class, ()
                -> agenda.eliminarContacto(demian));
        agenda.eliminarContacto(alma);
        assertEquals(0,
                agenda.listarEventos().get(0).obtenerInvitadosEvento().size());
    }

    /**
     * Prueba para buscar un contacto por su nombre.
     *
     * @throws ContactoNoEncontradoEnAgendaException Si el contacto no se encuentra.
     */
    @Test
    void buscarContactoPorNombre() throws ContactoNoEncontradoEnAgendaException {
        Contacto contactoEncontrado = agenda.buscarContactoPorNombre("karina reyes");
        assertEquals(karina, contactoEncontrado);
        assertThrows(ContactoNoEncontradoEnAgendaException.class, ()
                -> agenda.buscarContactoPorNombre("juan perez"));
    }

    /**
     * Prueba para buscar un contacto por su DNI.
     *
     * @throws ContactoNoEncontradoEnAgendaException Si el contacto no se encuentra.
     */
    @Test
    void buscarContactoPorDni() throws ContactoNoEncontradoEnAgendaException {
        Contacto contactoEncontrado = agenda.buscarContactoPorDni("55.443.563");
        assertEquals(alma, contactoEncontrado);
        assertThrows(ContactoNoEncontradoEnAgendaException.class, ()
                -> agenda.buscarContactoPorDni("21.876.555"));
    }

    /**
     * Prueba para verificar que los contactos se devuelvan ordenados correctamente
     * según su fecha de nacimiento de menor a mayor.
     *
     * @throws AgendaDeContactosVaciaException si la agenda no contiene contactos.
     */
    @Test
    void listarContactosPorNacimiento() throws AgendaDeContactosVaciaException {
        List<Contacto> contactosOrdenados = agenda.listarContactosPorNacimiento();
        assertEquals("Karina Reyes", contactosOrdenados.get(0).obtenerNombre());
        assertEquals("Demian Castañeda", contactosOrdenados.get(2).obtenerNombre());
        Agenda otraAgenda = new Agenda();
        assertThrows(AgendaDeContactosVaciaException.class, ()
                -> otraAgenda.listarContactosPorNacimiento());
    }

    /**
     * Prueba para verificar que los contactos se devuelvan ordenados correctamente
     * según su nombre alfabeticamente.
     *
     * @throws AgendaDeContactosVaciaException si la agenda no contiene contactos.
     */
    @Test
    void listarContactosPorNombre() throws AgendaDeContactosVaciaException {
        List<Contacto> contactosOrdenados = agenda.listarContactosPorNombre();
        assertEquals("Alma Prat", contactosOrdenados.get(0).obtenerNombre());
        assertEquals("Gaston Prat", contactosOrdenados.get(2).obtenerNombre());
        Agenda otraAgenda = new Agenda();
        assertThrows(AgendaDeContactosVaciaException.class, ()
                -> otraAgenda.listarContactosPorNombre());
    }

    /**
     * Prueba para verificar que el método devuelva la cantidad correcta de contactos
     * en la agenda
     *
     * @throws ContactoDuplicadoException si se intenta agregar un contacto duplicado.
     */
    @Test
    void cantidadTotalContactos() throws ContactoDuplicadoException {
        assertEquals(4, agenda.cantidadTotalContactos());
        agenda.agregarContacto(juan);
        assertEquals(5, agenda.cantidadTotalContactos());
        Agenda otraAgenda = new Agenda();
        assertEquals(0, otraAgenda.cantidadTotalContactos());
    }

    /**
     * prueba para verificar que el método determine correctamente si un contacto
     * existe o no en la agenda.
     */
    @Test
    void verificarExistenciaContacto() {
        assertTrue(agenda.verificarExistenciaContacto(alma));
        assertFalse(agenda.verificarExistenciaContacto(juan));
    }

    /**
     * prueba para verificar que el método {@code limpiarAgenda} elimine correctamente
     * todos los contactos y eventos de la agenda, dejando la misma vacía.
     *
     * @throws AgendaDeEventosVaciaException Si no hay eventos en la agenda.
     */
    @Test
    void limpiarAgenda() throws AgendaDeEventosVaciaException {
        assertEquals(4, agenda.cantidadTotalContactos());
        assertEquals(1, agenda.listarEventos().size());
        agenda.limpiarAgenda();
        assertEquals(0, agenda.cantidadTotalContactos());
        assertThrows(AgendaDeEventosVaciaException.class, ()
                -> agenda.listarEventos());
    }

    /**
     * Prueba para verificar que el método {@code crearEvento} cree correctamente
     * un evento en la agenda, y también maneje adecuadamente los casos de excepciones.
     *
     * @throws ContactoNoEncontradoEnAgendaException Si algún contacto no se encuentra
     * en la agenda.
     * @throws EventoDuplicadoException Si se intenta crear un evento que ya existe.
     * @throws AgendaDeEventosVaciaException Si no hay eventos en la agenda.
     */
    @Test
    void crearEvento()
            throws ContactoNoEncontradoEnAgendaException,
            EventoDuplicadoException,
            AgendaDeEventosVaciaException {
        Evento navidad = new Evento(
                "Festejo Navideño",
                LocalDate.of(2024, 12, 25),
                null
        );
        agenda.crearEvento(navidad);
        assertEquals(2, agenda.listarEventos().size());

        List<Contacto> invitadosCumpleJuan = new ArrayList<>();
        invitadosCumpleJuan.add(juan);
        Evento cumpleJuan = new Evento(
                "Cumpleaños de Juan Perez",
                LocalDate.of(2024, 12, 3),
                invitadosCumpleJuan
        );
        assertThrows(ContactoNoEncontradoEnAgendaException.class, ()
                -> agenda.crearEvento(cumpleJuan));

        assertThrows(EventoDuplicadoException.class, ()
                -> agenda.crearEvento(cumpleDeAlma));
    }

    /**
     * Prueba el método {@code agregarInvitadoEvento} de la clase {@link Agenda}.
     * <p>
     * Prueba para verificar que el método {@code agregarInvitadoEvento} maneje
     * correctamente todos los casos
     *
     * @throws EventoNoEncontradoException Si el evento al que se intenta agregar un
     * invitado no existe.
     * @throws ContactoYaInvitadoException Si el contacto ya está invitado al evento.
     * @throws ContactoNoEncontradoEnAgendaException Si el contacto no se encuentra
     * en la agenda.
     * @throws ContactoDuplicadoException Si el contacto ya existe en la agenda.
     * @throws AgendaDeEventosVaciaException Si la agenda no tiene eventos.
     */
    @Test
    void agregarInvitadoEvento()
            throws EventoNoEncontradoException,
            ContactoYaInvitadoException,
            ContactoNoEncontradoEnAgendaException,
            ContactoDuplicadoException,
            AgendaDeEventosVaciaException {
        assertThrows(ContactoNoEncontradoEnAgendaException.class, ()
                -> agenda.agregarInvitadoEvento(cumpleDeAlma, juan));
        agenda.agregarContacto(juan);
        agenda.agregarInvitadoEvento(cumpleDeAlma, juan);
        List<Evento> eventos = agenda.listarEventos();
        assertEquals(2, eventos.get(0).obtenerInvitadosEvento().size());
        Evento navidad = new Evento(
                "Festejo Navideño",
                LocalDate.of(2024, 12, 25),
                null
        );
        assertThrows(EventoNoEncontradoException.class, ()
                -> agenda.agregarInvitadoEvento(navidad, alma));

        assertThrows(ContactoYaInvitadoException.class, ()
                -> agenda.agregarInvitadoEvento(cumpleDeAlma, alma));
    }

    /**
     * Prueba el método {@code quitarInvitadoEvento} de la clase {@link Agenda}.
     * <p>
     * Prueba que verifica que el método {@code quitarInvitadoEvento} maneje
     * correctamente todos los casos
     *
     * @throws EventoNoEncontradoException Si el evento al que se intenta quitar un
     * invitado no existe.
     * @throws ContactoNoEncontradoEnEventoException Si el contacto no ha sido invitado
     * al evento.
     * @throws AgendaDeEventosVaciaException Si la agenda no tiene eventos.
     * @throws ContactoYaInvitadoException Si el contacto ya está invitado al evento.
     * @throws ContactoNoEncontradoEnAgendaException Si el contacto no se encuentra en
     * la agenda.
     */
    @Test
    void quitarInvitadoEvento()
            throws EventoNoEncontradoException,
            ContactoNoEncontradoEnEventoException,
            AgendaDeEventosVaciaException,
            ContactoYaInvitadoException,
            ContactoNoEncontradoEnAgendaException {
        agenda.quitarInvitadoEvento(cumpleDeAlma, alma);
        List<Evento> eventos = agenda.listarEventos();
        assertEquals(0, eventos.get(0).obtenerInvitadosEvento().size());

        agenda.agregarInvitadoEvento(cumpleDeAlma, alma);
        agenda.agregarInvitadoEvento(cumpleDeAlma, karina);
        agenda.agregarInvitadoEvento(cumpleDeAlma, gaston);
        agenda.agregarInvitadoEvento(cumpleDeAlma, demian);
        Evento navidad = new Evento(
                "Festejo Navideño",
                LocalDate.of(2024, 12, 25),
                null
        );
        assertThrows(EventoNoEncontradoException.class, ()
                -> agenda.quitarInvitadoEvento(navidad, alma));
        assertThrows(ContactoNoEncontradoEnEventoException.class, ()
                -> agenda.quitarInvitadoEvento(cumpleDeAlma, juan));
    }

    /**
     * Prueba el método {@code listarEventos} de la clase {@link Agenda}.
     * <p>
     * Prueba para verificar que el método {@code listarEventos} maneje correctamente
     * todos los casos
     *
     * @throws AgendaDeEventosVaciaException Si la agenda no contiene eventos al
     * intentar listar.
     * @throws ContactoNoEncontradoEnAgendaException Si un contacto no se encuentra en
     * la agenda al intentar agregarlo a un evento.
     * @throws EventoDuplicadoException Si se intenta agregar un evento duplicado
     * en la agenda.
     */
    @Test
    void listarEventos()
            throws AgendaDeEventosVaciaException,
            ContactoNoEncontradoEnAgendaException,
            EventoDuplicadoException {
        assertEquals(1, agenda.listarEventos().size());
        Evento navidad = new Evento(
                "Festejo Navideño",
                LocalDate.of(2024, 12, 25),
                null
        );
        agenda.crearEvento(navidad);
        assertEquals("Festejo Navideño",
                agenda.listarEventos().get(1).obtenerNombreEvento());
        agenda.limpiarAgenda();
        assertThrows(AgendaDeEventosVaciaException.class, ()
                -> agenda.listarEventos());
    }
}
