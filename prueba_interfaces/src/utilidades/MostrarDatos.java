package utilidades;

import personas.interfaces.Empleado;
import personas.interfaces.Estudiante;

public class MostrarDatos {
    public static void datosEmpleado(Empleado empleado){
        System.out.println("\n--------------\n" + 
            "Datos personales del empleado" + "\n" + 
            "--------------\n" + 
            "Nombre: " + empleado.getNombre() + "\n" +
            "Apellido: " + empleado.getApellido() + "\n" +
            "DNI: " + empleado.getDni() + "\n" +
            "Edad: " + empleado.getEdad() + "\n" +
            "Nacionalidad: " + empleado.getNacionalidad() + "\n"
        );

        System.out.println("--------------\n" +
            "Datos laborales del empleado\n" + 
            "--------------\n" +
            "ID Empleado: " + empleado.getIdEmpleado() + "\n" +
            "Ocupacion: " + empleado.getOcupacion() + "\n" +
            "Empresa: " + empleado.getEmpresa() + "\n" +
            "Sueldo bruto: " + empleado.getSueldoBruto() + "\n" +
            "Sueldo neto: " + empleado.getSueldoNeto() + "\n"
        );
    }

    public static void datosEstudiante(Estudiante estudiante){
        System.out.println("\n--------------\n" + 
            "Datos personales del estudiante\n" + 
            "--------------\n" + 
            "Nombre: " + estudiante.getNombre() + "\n" +
            "Apellido: " + estudiante.getApellido() + "\n" +
            "DNI: " + estudiante.getDni() + "\n" +
            "Edad: " + estudiante.getEdad() + "\n" +
            "Nacionalidad: " + estudiante.getNacionalidad() + "\n"
        );

        System.out.println("--------------\n" +
            "Datos academicos del estudiante\n" + 
            "--------------\n" +
            "Numero de cuenta: " + estudiante.getNumCuenta() + "\n" +
            "Universidad: " + estudiante.getUniversidad() + "\n" +
            "Carrera: " + estudiante.getCarrera() + "\n" +
            "Promedio global: " + estudiante.getPromedioGlobal() + "\n"
        );
    }
}
