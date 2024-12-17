package ar.unrn.miagenda.excepciones;

/**
 * Excepción lanzada cuando un contacto no se encuentra.
 */
public class ContactoNoEncontradoEnEventoException extends Exception {
    /**
     * Constructor que crea una nueva excepción con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe la causa de la excepción.
     */
    public ContactoNoEncontradoEnEventoException(String mensaje) {
        super(mensaje);
    }
}
