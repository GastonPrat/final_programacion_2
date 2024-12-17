package ar.unrn.miagenda.excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta crear un evento ya existente.
 */
public class EventoDuplicadoException extends Exception {
    /**
     * Constructor que crea una nueva excepción con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe la causa de la excepción.
     */
    public EventoDuplicadoException(String mensaje) {
      super(mensaje);
    }
}
