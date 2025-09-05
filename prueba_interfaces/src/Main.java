import java.io.IOException;
import java.util.Scanner;

import archivos.Fichero;
import archivos.FicheroSerializado;
import hilos.Hilo;
import personas.estudiantes.EstudianteIng;
import personas.interfaces.Estudiante;
import utilidades.NuevoEstudiante;
//import personas.empleados.Gerente;
//import utilidades.NuevoEmpleado;
import utilidades.Validacion;

public class Main  extends Thread {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // El método .validScanner() recibe como argumento un objeto de tipo InputStream
        // Para uso de este programa, se usa System.in
        Scanner input = Validacion.validScanner(System.in); // Se crea un nuevo Scanner llamando al metodo .validScanner();

        final String ruta_fichero = "src/archivos/prueba.txt";
        final String ruta_serializacion = "src/archivos/estudiante.bin";

        //Prueba hilo a
        Hilo hilo = new Hilo();
        Thread thread = new Thread(hilo, "Hilo 1");
        thread.start();

        //Fichero.nuevoFichero(ruta_fichero);
        //Fichero.eliminarFichero(ruta_serializacion);

        //Gerente gerente = new Gerente();

        //Fichero.escribirFichero(input, ruta_fichero, gerente);
        //Fichero.leerFichero(ruta_fichero);

        //EstudianteIng est_ing = NuevoEstudiante.crearEstudianteIng(input);
        //FicheroSerializado.nuevaSerializacion(ruta_serializacion, est_ing);  

        EstudianteIng est_serializado = (EstudianteIng) FicheroSerializado.Deserealizacion(ruta_serializacion);
        System.out.println(est_serializado);

        // Ya teniendo el objeto de tipo Empleado creado, se puede pasar como parametro
        // para imprimir
        // Como en datosEmpleado() o pasar el objeto para añadirlo a un fichero
        // Datos de un nuevo empleado
        //MostrarDatos.datosEmpleado(cajero);

        input.close();
    }

    private static void mostrarMenu(){
        
    }
}
