package ar.unrn.miagenda.excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta realizar una operación
 * en la agenda de eventos y esta está vacía.
 */
public class AgendaDeEventosVaciaException extends Exception {
    /**
     * Constructor que crea una nueva excepción con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe la causa de la excepción.
     */
    public AgendaDeEventosVaciaException(String mensaje) {
      super(mensaje);
    }
}
