package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import personas.interfaces.Empleado;

public class Fichero {
    public static void nuevoFichero(String ruta) throws IOException {
        try {
            File fichero = new File(ruta);
            if (fichero.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente");
            } 
            else if (fichero.exists()) {
                System.out.println("El fichero ya existe en esa ruta");
            } 
            else {
                System.out.println("No se ha podido crear el fichero");
            }

        } catch (IOException e) {
            throw new IOException("Sucedio un error al intentar crear el fichero " + e);
        }
    }

    public static void eliminarFichero(String ruta) throws FileNotFoundException {

        File fichero = new File(ruta);

        if (fichero.exists()) {
            fichero.delete();
            System.out.println("El fichero se ha eliminado correctamente");
        } 
        else {
            throw new FileNotFoundException("No se ha encontrado el archivo en la ruta seleccionada");
        }
    }

    public static void escribirFichero(Scanner input, String ruta, Empleado empleado) throws IOException {
        
        File fichero = new File(ruta);

        try (FileWriter fw = nuevoFileWriter(fichero, input); PrintWriter pw = new PrintWriter(fw)){
            //Se escriben todos los datos en el archivo de texto
            pw.println("------------------------------");
            pw.println("Datos personales del empleado");
            pw.println("------------------------------");
            pw.println("Nombre: " + empleado.getNombre());
            pw.println("Apellido: " + empleado.getApellido());
            pw.println("DNI: " + empleado.getDni());
            pw.println("Edad: " + empleado.getEdad());
            pw.println("Nacionalidad: " + empleado.getNacionalidad());
            pw.println("------------------------------");
            pw.println("Datos laborales del empleado");
            pw.println("------------------------------");
            pw.println("ID Empleado: " + empleado.getIdEmpleado());
            pw.println("Ocupacion: " + empleado.getOcupacion());
            pw.println("Empresa: " + empleado.getEmpresa());
            pw.println("Sueldo bruto: " + empleado.getSueldoBruto());
            pw.println("Sueldo neto: " + empleado.getSueldoNeto());
            pw.println("\n"); //Salto de linea al final de cada empleado

            //Mensaje de confirmacion
            System.out.println("Datos escritos correctamente");
        } catch (IOException e) {
            throw new IOException("Sucedio un error al escribir en el archivo " + e);
        }
    }
    
    private static Boolean deseaSobrescribir(Scanner input){

        String respuesta;

        while (true) {
            System.out.println("Desea sobreescribir el archivo ? S/N");
            respuesta = input.nextLine();
            if (respuesta.toUpperCase().equals("S")) {
                return true;
            }
            else if (respuesta.toUpperCase().equals("N")) {
                return false;
            }
            else {
                System.out.println("Por favor, seleccione una opcion valida");
                continue;
            }
        }
    }

    private static FileWriter nuevoFileWriter(File fichero, Scanner input) throws IOException {
        FileWriter fw;

        if (deseaSobrescribir(input)) {
            try {
                fw = new FileWriter(fichero, false);
            } catch (IOException e) {
                throw new IOException("Sucedio un error al crear el objeto FileWriter sobreescribible" + e);
            }
        } else {
            try {
                fw = new FileWriter(fichero, true);
            } catch (IOException e) {
                throw new IOException("Sucedio un error al crear el objeto FileWriter " + e);
            }
        }

        return fw;
    }

    public static void leerFichero(String ruta) throws IOException {
        File fichero = new File(ruta);
        String texto;

        try (FileReader fr = new FileReader(fichero); BufferedReader bw = new BufferedReader(fr)) {
            while ((texto = bw.readLine()) != null) {
                System.out.println(texto);
            }    
            System.out.println("\nSe ha leido todo el contenido !");
        } catch (IOException e) {
            throw new IOException("Sucedio un error al leer los datos del fichero");
        }
    }
}
