package ar.unrn.miagenda.contacto;

import java.time.LocalDate;

/**
 * Interface que proporciona las operaciones necesarias
 * para obtener información básica de un contacto.
 */
public interface IContacto {
    /**
     * Obtiene el nombre completo del contacto.
     *
     * @return El nombre completo del contacto.
     */
    String obtenerNombre();

    /**
     * Obtiene el número de DNI del contacto.
     *
     * @return El número de DNI del contacto.
     */
    String obtenerDni();

    /**
     * Obtiene la fecha de nacimiento del contacto.
     *
     * @return La fecha de nacimiento del contacto.
     */
    LocalDate obtenerFechaNacimiento();
}
