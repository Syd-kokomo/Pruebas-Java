package utilidades;

import java.util.Scanner;

import personas.empleados.Cajero;

public class NuevoEmpleado {
    public static Cajero crearCajero(Scanner input)
            throws NumberFormatException {
        System.out.println("\n--------------\n" +
            "Registro de nuevo empleado" + "\n" +
            "--------------\n");

        // Nombre
        System.out.print("Nombre : ");
        String nombre = input.nextLine();

        // Apellido
        System.out.print("Apellido: ");
        String apellido = input.nextLine();

        // Edad
        int edad = Validacion.numValidado("Edad: ", input, Integer.class);

        // DNI
        Long dni = Validacion.numValidado("DNI: ", input, Long.class);

        // Nacionalidad
        System.out.print("Nacionalidad: ");
        String nacionalidad = input.nextLine();

        // ID del empleado
        int id_empleado = Validacion.numValidado("ID empleado: ", input, Integer.class);

        // Ocupacion
        System.out.print("Ocupacion: ");
        String ocupacion = input.nextLine();

        // Empresa
        System.out.print("Empresa: ");
        String empresa = input.nextLine();

        // Sueldo bruto
        double sueldo_bruto = Validacion.numValidado("Sueldo bruto: ", input, Double.class);
        
        Cajero cajero = new Cajero(nombre, apellido, edad, dni, nacionalidad, id_empleado, ocupacion, empresa, sueldo_bruto, sueldo_bruto);
        
        return cajero;
    }
}
