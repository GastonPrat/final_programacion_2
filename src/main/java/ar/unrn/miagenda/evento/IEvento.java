package ar.unrn.miagenda.evento;

import ar.unrn.miagenda.contacto.Contacto;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define los métodos esenciales para gestionar eventos en una agenda.
 */
public interface IEvento {
    /**
     * Obtiene el nombre del evento.
     *
     * @return El nombre del evento.
     */
    String obtenerNombreEvento();

    /**
     * Obtiene la fecha en la que se llevará a cabo el evento.
     *
     * @return La fecha del evento.
     */
    LocalDate obtenerFechaEvento();


    /**
     * Obtiene la lista de contactos invitados al evento.
     *
     * @return Una lista de objetos {@link Contacto} que representan
     * a los invitados al evento.
     */
    List<Contacto> obtenerInvitadosEvento();

    /**
     * Agrega un contacto a la lista de invitados del evento.
     *
     * @param contacto El contacto a agregar.
     */
    void agregarInvitado(Contacto contacto);

    /**
     * Elimina un contacto de la lista de invitados del evento.
     *
     * @param contacto El contacto a eliminar.
     */
     void quitarInvitado(Contacto contacto);
}

