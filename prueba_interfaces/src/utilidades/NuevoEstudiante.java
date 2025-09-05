package utilidades;

import java.util.Scanner;

import personas.estudiantes.EstudianteIng;

public class NuevoEstudiante {
    public static EstudianteIng crearEstudianteIng(Scanner input) {
        System.out.println(
            "\n--------------\n" +
            "Registro de nuevo estudiante ingenieria\n" +
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

        // Numero de cuenta
        //No es necesario crearlo, porque se genera aleatoriamente en el método default de la interfaz Estudiante

        // Universidad
        System.out.print("Universidad: ");
        String universidad = input.nextLine();

        // Carrera
        System.out.print("Carrera: ");
        String carrera = input.nextLine();

        // Promedio global
        int promedio = Validacion.numValidado("Promedio global: ", input, Integer.class);

        EstudianteIng est_ing = new EstudianteIng(nombre, apellido, edad, dni, nacionalidad, universidad, carrera, promedio);

        return est_ing;
    }
}
