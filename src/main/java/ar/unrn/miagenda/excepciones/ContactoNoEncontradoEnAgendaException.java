package ar.unrn.miagenda.excepciones;

/**
 * Excepción lanzada cuando un contacto no se encuentra.
 */
public class ContactoNoEncontradoEnAgendaException extends Exception {
    /**
     * Constructor que crea una nueva excepción con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe la causa de la excepción.
     */
    public ContactoNoEncontradoEnAgendaException(String mensaje) {
        super(mensaje);
    }
}
