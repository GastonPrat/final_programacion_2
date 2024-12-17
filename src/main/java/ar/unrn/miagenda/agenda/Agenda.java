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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Clase que representa una agenda que gestiona contactos y eventos.
 */
public class Agenda implements IAgenda {

    /**
     * La lista de contactos gestionados por la agenda.
     */
    private List<Contacto> contactos;
    /**
     * La lista de eventos gestionados por la agenda.
     */
    private List<Evento> eventos;

    /**
     * Constructor que inicializa una nueva instancia de la clase {@link Agenda}.
     */
    public Agenda() {
        this.contactos = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }
    /**
     * Agrega un contacto a la agenda.
     *
     * @param contacto El contacto que se desea agregar.
     * @throws ContactoDuplicadoException Si el contacto ya existe en la agenda.
     */
    @Override
    public void agregarContacto(Contacto contacto)
            throws ContactoDuplicadoException {
        for (Contacto contactoAgenda : this.contactos) {
            if (contactoAgenda.obtenerDni().equals(contacto.obtenerDni())) {
                throw new ContactoDuplicadoException("Ya existe un"
                        + "contacto con ese numero de DNI");
            }
        }
        this.contactos.add(contacto);
    }

    /**
     * Elimina un contacto de la agenda mediante su DNI.
     *
     * @param contacto El contacto que se desea eliminar.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra
     * el contacto en la agenda
     */
    @Override
    public void eliminarContacto(Contacto contacto)
            throws ContactoNoEncontradoEnAgendaException {
        if (!(verificarExistenciaContacto(contacto))) {
            throw new ContactoNoEncontradoEnAgendaException("El contacto "
                    + "que desea eliminar no existe");
        } else {
            this.contactos.remove(contacto);
            // Eliminar el contacto de todos los eventos donde esté invitado
            for (Evento evento : this.eventos) {
                evento.quitarInvitado(contacto);
            }
        }
    }

    /**
     * Busca un contacto en la agenda por su nombre.
     *
     * @param nombre El nombre del contacto a buscar.
     * @return El contacto encontrado.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra un
     * contacto con el nombre especificado.
     */
    @Override
    public Contacto buscarContactoPorNombre(String nombre)
            throws ContactoNoEncontradoEnAgendaException {
        for (Contacto contactoAgenda : this.contactos) {
            if ((contactoAgenda.obtenerNombre().equalsIgnoreCase(nombre))) {
                return contactoAgenda;
            }
        } throw new ContactoNoEncontradoEnAgendaException("No se encuentra"
                + " un contacto con ese nombre");
    }

    /**
     * Busca un contacto en la agenda por su DNI.
     *
     * @param dni El DNI del contacto a buscar.
     * @return El contacto encontrado.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra
     * un contacto con el DNI especificado.
     */
    @Override
    public Contacto buscarContactoPorDni(String dni)
            throws ContactoNoEncontradoEnAgendaException {
        for (Contacto contactoAgenda : this.contactos) {
            if ((contactoAgenda.obtenerDni().equals(dni))) {
                return contactoAgenda;
            }
        } throw new ContactoNoEncontradoEnAgendaException("No se encuentra"
                + " un contacto con ese DNI");
    }

    /**
     * Lista los contactos ordenados por fecha de nacimiento.
     *
     * @return Una lista de contactos ordenada por fecha de nacimiento.
     * @throws AgendaDeContactosVaciaException Si no hay contactos en la agenda.
     */
    @Override
    public List<Contacto> listarContactosPorNacimiento()
            throws AgendaDeContactosVaciaException {
        if (this.contactos.isEmpty()) {
            throw new AgendaDeContactosVaciaException("No hay contactos en la agenda.");
        }
        List<Contacto> contactosOrdenados = new ArrayList<>(this.contactos);
        contactosOrdenados.sort(Comparator.comparing(Contacto::obtenerFechaNacimiento));
        return contactosOrdenados;
    }

    /**
     * Lista los contactos ordenados alfabéticamente por nombre.
     *
     * @return Una lista de contactos ordenada por nombre.
     * @throws AgendaDeContactosVaciaException Si no hay contactos en la agenda.
     */
    @Override
    public List<Contacto> listarContactosPorNombre()
            throws AgendaDeContactosVaciaException {
        if (this.contactos.isEmpty()) {
            throw new AgendaDeContactosVaciaException("No hay contactos en la agenda.");
        }
        List<Contacto> contactosOrdenados = new ArrayList<>(this.contactos);
        contactosOrdenados.sort(Comparator.comparing(Contacto::obtenerNombre));
        return contactosOrdenados;
    }

    /**
     * Obtiene la cantidad total de contactos en la agenda.
     *
     * @return El número total de contactos.
     */
    @Override
    public int cantidadTotalContactos() {
        return this.contactos.size();
    }

    /**
     * Verifica si un contacto ya existe en la agenda.
     *
     * @param contacto El contacto a verificar.
     * @return {@code true} si el contacto ya existe, {@code false} en caso contrario.
     */
    @Override
    public boolean verificarExistenciaContacto(Contacto contacto) {
        return this.contactos.contains(contacto);
    }

    /**
     * Limpia todos los contactos y eventos de la agenda.
     */
    @Override
    public void limpiarAgenda() {
        this.contactos.clear();
        this.eventos.clear();
    }

    /**
     * Crea un evento en la agenda.
     *
     * @param evento El evento a ser creado.
     * @throws EventoDuplicadoException Si el evento ya existe.
     * @throws ContactoNoEncontradoEnAgendaException si algun contacto invitado no
     * se encuentra en la agenda.
     */
    @Override
    public void crearEvento(Evento evento)
            throws EventoDuplicadoException, ContactoNoEncontradoEnAgendaException {
        for (Contacto contactoInvitado : evento.obtenerInvitadosEvento()) {
            if (!verificarExistenciaContacto(contactoInvitado)) {
                String nombreContacto = contactoInvitado.obtenerNombre();
                throw new ContactoNoEncontradoEnAgendaException("El contacto ("
                        + nombreContacto + ") no esta en la Agenda");
            }
        }
        if (this.eventos.contains(evento)) {
            throw new EventoDuplicadoException("Ese evento ya existe en la Agenda.");
        } else {
            this.eventos.add(evento);
        }
    }

    /**
     * Agrega un contacto a la lista de invitados del evento.
     *
     * @param evento El evento en el cual se quiere agregar un invitado.
     * @param contacto El contacto a agregar.
     * @throws ContactoYaInvitadoException si el contacto ya esta invitado.
     * @throws EventoNoEncontradoException Si el evento no existe en la agenda.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra el
     * contacto en la agenda.
     */
    @Override
    public void agregarInvitadoEvento(Evento evento, Contacto contacto)
            throws ContactoYaInvitadoException,
            EventoNoEncontradoException,
            ContactoNoEncontradoEnAgendaException {
        if (!this.eventos.contains(evento)) {
            throw new EventoNoEncontradoException("No existe ese evento en la agenda");
        } else if (evento.obtenerInvitadosEvento().contains(contacto)) {
            throw new ContactoYaInvitadoException("El contacto "
                    + "ya esta invitado al evento");
        } else if (!verificarExistenciaContacto(contacto)) {
            throw new ContactoNoEncontradoEnAgendaException("El contacto que intenta "
                    + "invitar no se encuentra agendado");
        } else {
            evento.agregarInvitado(contacto);
        }
    }

    /**
     * Elimina un contacto de la lista de invitados del evento.
     *
     * @param evento El evento del cual se quiere quitar un invitado.
     * @param contacto El contacto a eliminar.
     * @throws ContactoNoEncontradoEnEventoException Si no se encuentra un contacto.
     * @throws EventoNoEncontradoException Si el evento no se encuentra en la agenda.
     */
    @Override
    public void quitarInvitadoEvento(Evento evento, Contacto contacto)
            throws EventoNoEncontradoException, ContactoNoEncontradoEnEventoException {
        if (!this.eventos.contains(evento)) {
            throw new EventoNoEncontradoException("No existe ese evento en la agenda");
        } else if (!evento.obtenerInvitadosEvento().contains(contacto)) {
            throw new ContactoNoEncontradoEnEventoException("El contacto que "
                    + "intenta eliminar no esta invitado al evento");
        } else {
            evento.quitarInvitado(contacto);
        }
    }

    /**
     * Lista todos los eventos de la agenda.
     *
     * @return Una lista de eventos registrados en la agenda.
     * @throws AgendaDeEventosVaciaException Si no hay eventos en la agenda.
     */
    @Override
    public List<Evento> listarEventos() throws AgendaDeEventosVaciaException {
        if (this.eventos.isEmpty()) {
            throw new AgendaDeEventosVaciaException("No hay eventos en la agenda");
        } else {
            return Collections.unmodifiableList(eventos);
        }
    }
}
