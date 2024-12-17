package ar.unrn.miagenda.excepciones;

/**
 * Excepción lanzada cuando un evento no se encuentra.
 */
public class EventoNoEncontradoException extends Exception {
    /**
     * Constructor que crea una nueva excepción con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe la causa de la excepción.
     */
    public EventoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
