package ar.unrn.miagenda.evento;

import ar.unrn.miagenda.contacto.Contacto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que representa un evento dentro de una agenda.
 * Implementa la interfaz {@link IEvento}
 */
public class Evento implements IEvento {

    /**
     * El nombre del evento.
     */
    private String nombre;
    /**
     * La fecha del evento.
     */
    private LocalDate fecha;
    /**
     * La lista de contactos invitados al evento.
     */
    private List<Contacto> invitados;

    /**
     * Constructor que crea un nuevo evento con los datos proporcionados.
     *
     * @param nombreEvento El nombre del evento.
     * @param fechaEvento La fecha en la que se llevará a cabo el evento.
     * @param invitadosEvento La lista de contactos invitados al evento.
     */
    public Evento(String nombreEvento,
                  LocalDate fechaEvento,
                  List<Contacto> invitadosEvento) {
        this.nombre = nombreEvento;
        this.fecha = fechaEvento;
        if (invitadosEvento != null) {
            this.invitados = new ArrayList<>(invitadosEvento);
        } else {
            this.invitados = new ArrayList<>();
        }
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @return El nombre del evento.
     */
    @Override
    public String obtenerNombreEvento() {
        return this.nombre;
    }

    /**
     * Obtiene la fecha en la que se llevará a cabo el evento.
     *
     * @return La fecha del evento.
     */
    @Override
    public LocalDate obtenerFechaEvento() {
        return this.fecha;
    }

    /**
     * Obtiene la lista de contactos invitados al evento.
     *
     * @return Una lista de objetos {@link Contacto}
     * que representan a los invitados al evento.
     */
    @Override
    public List<Contacto> obtenerInvitadosEvento() {
        return Collections.unmodifiableList(invitados);
    }

    /**
     * Agrega un contacto a la lista de invitados del evento.
     *
     * @param contacto El contacto a agregar.
     */
    @Override
    public void agregarInvitado(Contacto contacto) {
        this.invitados.add(contacto);
    }

    /**
     * Elimina un contacto de la lista de invitados del evento.
     *
     * @param contacto El contacto a eliminar.
     */
    @Override
    public void quitarInvitado(Contacto contacto) {
        this.invitados.remove(contacto);
    }

    /**
     * Devuelve una representación en formato String del evento, incluyendo su
     * nombre, fecha y lista de invitados.
     *
     * @return Una cadena de texto que representa el evento.
     */
    @Override
    public String toString() {
        StringBuilder invitadosStr = new StringBuilder();
        for (Contacto contacto : this.invitados) {
            invitadosStr.append(contacto.obtenerNombre()).append(", ");
        }
        if (!invitadosStr.isEmpty()) {
            invitadosStr.setLength(invitadosStr.length() - 2);
        }

        return "Evento{nombre='" + this.nombre + '\''
                + ", fecha=" + this.fecha + ", invitados=["
                + invitadosStr + "]" + '}';
    }
}
