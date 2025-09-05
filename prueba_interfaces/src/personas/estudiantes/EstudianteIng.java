package personas.estudiantes;
import java.io.Serializable;

import personas.interfaces.Estudiante;

public class EstudianteIng implements Estudiante, Serializable {
    //Atributo necesario para indicar el identificador de version de la clase
    private static final long serialVersionUID = 1L;

    //Atributos
    private String nombre;
    private String apellido;
    private int edad;
    private Long dni;
    private String nacionalidad;
    private String numCuenta;
    private String universidad;
    private String carrera;
    private int promedioGlobal;

    //Método constructor
    public EstudianteIng(String nombre, String apellido, int edad, Long dni, String nacionalidad,
            String universidad, String carrera, int promedioGlobal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.nacionalidad = nacionalidad;
        this.numCuenta = numeroCuenta();
        this.universidad = universidad;
        this.carrera = carrera;
        this.promedioGlobal = promedioGlobal;
    }

    //Definicion de los métodos proporcionados por el contrato Estudiante
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public Long getDni() {
        return dni;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public String getUniversidad() {
        return universidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getPromedioGlobal() {
        return promedioGlobal;
    }

    @Override
    public String toString() {
        return "EstudianteIng [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", dni=" + dni
                + ", nacionalidad=" + nacionalidad + ", numCuenta=" + numCuenta + ", universidad=" + universidad
                + ", carrera=" + carrera + ", promedioGlobal=" + promedioGlobal + "]";
    }
}
