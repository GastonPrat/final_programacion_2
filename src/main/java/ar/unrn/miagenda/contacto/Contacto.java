package ar.unrn.miagenda.contacto;

import java.time.LocalDate;

/**
 * Clase que representa un contacto dentro de una agenda.
 * Implementa la interfaz {@link IContacto}, proporcionando la implementación de los
 * métodos para obtener información básica de un contacto,
 * como el nombre, el DNI y la fecha de nacimiento.
 */
public class Contacto implements IContacto {
    /**
     * El nombre del contacto.
     */
    private String nombre;
    /**
     * El número de DNI del contacto.
     */
    private String dni;
    /**
     * La fecha de nacimiento del contacto.
     */
    private LocalDate fechaNacimiento;

    /**
     * Constructor que crea un nuevo contacto con los datos proporcionados.
     *
     * @param nombreContacto El nombre completo del contacto.
     * @param dinContacto El número de DNI del contacto.
     * @param fechaNacimientoContacto La fecha de nacimiento del contacto.
     */
    public Contacto(String nombreContacto,
                    String dinContacto,
                    LocalDate fechaNacimientoContacto) {
        this.nombre = nombreContacto;
        this.dni = dinContacto;
        this.fechaNacimiento = fechaNacimientoContacto;
    }

    /**
     * Obtiene el nombre completo del contacto.
     *
     * @return El nombre completo del contacto.
     */
    @Override
    public String obtenerNombre() {
        return this.nombre;
    }

    /**
     * Obtiene el número de DNI del contacto.
     *
     * @return El número de DNI del contacto.
     */
    @Override
    public String obtenerDni() {
        return this.dni;
    }

    /**
     * Obtiene la fecha de nacimiento del contacto.
     *
     * @return La fecha de nacimiento del contacto.
     */
    @Override
    public LocalDate obtenerFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * Devuelve una representación en formato String del contacto, incluyendo su
     * nombre, DNI y fecha de nacimiento.
     *
     * @return Una cadena de texto que representa el contacto.
     */
    @Override
    public String toString() {
        return "Contacto{Nombre='" + nombre + '\''
                + ", DNI='" + dni + '\'' + ", Fecha de Nacimiento="
                + fechaNacimiento
                + '}';
    }
}
