package ar.unrn.miagenda.excepciones;

/**
 * Excepción lanzada cuando se intenta agregar un contacto a un evento
 * en el cual ya se encuentra invitado.
 */
public class ContactoYaInvitadoException extends Exception {
    /**
     * Constructor que crea una nueva excepción con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe la causa de la excepción.
     */
    public ContactoYaInvitadoException(String mensaje) {
        super(mensaje);
    }
}
