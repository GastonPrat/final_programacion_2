package ar.unrn.miagenda.excepciones;

/**
 * Excepción lanzada cuando se intenta agregar un contacto duplicado.
 */
public class ContactoDuplicadoException extends Exception {
    /**
     * Constructor que crea una nueva excepción con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe la causa de la excepción.
     */
    public ContactoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
