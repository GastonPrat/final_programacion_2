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

import java.util.List;

/**
 * Interfaz que define los métodos principales para gestionar
 * contactos y eventos en una agenda.
 * Proporciona funcionalidades para agregar, eliminar, buscar y listar contactos,
 * así como para gestionar eventos asociados a la agenda.
 */
public interface IAgenda {
    /**
     * Agrega un contacto a la agenda.
     *
     * @param contacto El contacto que se desea agregar.
     * @throws ContactoDuplicadoException Si el contacto ya existe en la agenda.
     */
    void agregarContacto(Contacto contacto)
            throws ContactoDuplicadoException;

    /**
     * Elimina un contacto de la agenda mediante su DNI.
     *
     * @param contacto El contacto que se desea eliminar.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra
     * un contacto con el DNI especificado.
     */
    void eliminarContacto(Contacto contacto)
            throws ContactoNoEncontradoEnAgendaException;

    /**
     * Busca un contacto en la agenda por su nombre.
     *
     * @param nombre El nombre del contacto a buscar.
     * @return El contacto encontrado.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra un
     * contacto con el nombre especificado.
     */
    Contacto buscarContactoPorNombre(String nombre)
            throws ContactoNoEncontradoEnAgendaException;

    /**
     * Busca un contacto en la agenda por su DNI.
     *
     * @param dni El DNI del contacto a buscar.
     * @return El contacto encontrado.
     * @throws ContactoNoEncontradoEnAgendaException Si no se encuentra
     * un contacto con el DNI especificado.
     */
    Contacto buscarContactoPorDni(String dni)
            throws ContactoNoEncontradoEnAgendaException;

    /**
     * Lista los contactos ordenados por fecha de nacimiento.
     *
     * @return Una lista de contactos ordenada por fecha de nacimiento.
     * @throws AgendaDeContactosVaciaException Si no hay contactos en la agenda.
     */
    List<Contacto> listarContactosPorNacimiento()
            throws AgendaDeContactosVaciaException;

    /**
     * Lista los contactos ordenados alfabéticamente por nombre.
     *
     * @return Una lista de contactos ordenada por nombre.
     * @throws AgendaDeContactosVaciaException Si no hay contactos en la agenda.
     */
    List<Contacto> listarContactosPorNombre()
            throws AgendaDeContactosVaciaException;

    /**
     * Obtiene la cantidad total de contactos en la agenda.
     *
     * @return El número total de contactos.
     */
    int cantidadTotalContactos();

    /**
     * Verifica si un contacto ya existe en la agenda.
     *
     * @param contacto El contacto a verificar.
     * @return {@code true} si el contacto ya existe, {@code false} en caso contrario.
     */
    boolean verificarExistenciaContacto(Contacto contacto);

    /**
     * Limpia todos los contactos y eventos de la agenda.
     */
    void limpiarAgenda();

    /**
     * Crea un evento en la agenda.
     *
     * @param evento El evento a ser creado.
     * @throws EventoDuplicadoException Si el evento ya existe.
     * @throws ContactoNoEncontradoEnAgendaException si algun contacto invitado no
     * se encuentra en la agenda.
     */
    void crearEvento(Evento evento)
            throws EventoDuplicadoException, ContactoNoEncontradoEnAgendaException;

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
    void agregarInvitadoEvento(Evento evento, Contacto contacto)
            throws ContactoYaInvitadoException,
            EventoNoEncontradoException,
            ContactoNoEncontradoEnAgendaException;

    /**
     * Elimina un contacto de la lista de invitados del evento.
     *
     * @param evento El evento del cual se quiere quitar un invitado.
     * @param contacto El contacto a eliminar.
     * @throws ContactoNoEncontradoEnEventoException Si no se encuentra un contacto.
     * @throws EventoNoEncontradoException Si el evento no se encuentra en la agenda.
     */
    void quitarInvitadoEvento(Evento evento, Contacto contacto)
            throws EventoNoEncontradoException, ContactoNoEncontradoEnEventoException;

    /**
     * Lista todos los eventos de la agenda.
     *
     * @return Una lista de eventos registrados en la agenda.
     * @throws AgendaDeEventosVaciaException Si no hay eventos en la agenda.
     */
    List<Evento> listarEventos()
            throws AgendaDeEventosVaciaException;
}
