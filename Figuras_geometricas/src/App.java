//Cree un programa que calcule el área y el volumen de los siguientes 3 solidos geométricos
//Esfera, piramide y cilindro   

import java.util.InputMismatchException;
import java.util.Scanner;

import solido_geometrico.SolidoGeometrico;
import solido_geometrico.cilindro.Cilindro;
import solido_geometrico.esfera.Esfera;
import solido_geometrico.piramide.Piramide;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int opcion;
        boolean bandera = false;

        while (!bandera) {
            System.out.println("\n-Programa para calcular el area y volumen de un solido geometrico-");
            System.out.println("1. Esfera");
            System.out.println("2. Piramide");
            System.out.println("3. Cilindro");
            System.out.println("0. Salir");
            opcion = leerOpcion("Opcion: ", input);

            switch (opcion) {
                case 1:
                    calcularEsfera(input);
                    break;
                case 2:
                    calcularPiramide(input);
                    break;
                case 3:
                    calcularCilindro(input);
                    break;
                case 0:
                    System.out.println("Muchas gracias por usar el programa !");
                    bandera=true;
                    break;
                default:
                    System.out.println("Por favor, seleccione una opcion valida");
                    break;
            }
        }

        input.close();
    }

    private static int leerOpcion(String mensaje, Scanner input){
        int opcion=0;

        do {
            try {
                System.out.print(mensaje);
                opcion = input.nextInt();
                input.nextLine(); //Limpia el salto de línea pendiente;

                break;
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato no valido");
                input.nextLine();
            }
        } while (true);

        return opcion;
    }

    private static double leerDato(String mensaje, Scanner input){
        double dato;

        do {
            try {
                System.out.print(mensaje);
                dato = input.nextDouble();
                input.nextLine(); //Limpia el salto de línea pendiente

                if (dato<=0) {
                    System.out.println("El valor no puede ser negativo o 0");
                    continue;
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato no valido");
                input.nextLine();
            }
        } while (true);

        return dato;
    }

    private static void calcularEsfera(Scanner input){
        double radio;

        System.out.println("\n-Esfera-");
        radio = leerDato("Radio: ", input);

        SolidoGeometrico solido = new Esfera(radio);

        System.out.printf("%s%.2f%s\n", "Area: ", solido.getArea(), " cm^2");
        System.out.printf("%s%.2f%s\n", "Volumen: ", solido.getVolumen(), " cm^3");

        esperarEntrada(input); //Simula una pausa después de imprimir los resultados
    }

    private static void calcularPiramide(Scanner input){
        double lado, altura;

        System.out.println("\n-Piramide-");
        lado=leerDato("Lado de la base: ", input);
        altura=leerDato("Altura: ", input);

        SolidoGeometrico solido = new Piramide(lado, altura);

        System.out.printf("%s%.2f%s\n", "Area: ", solido.getArea(), " cm^2");
        System.out.printf("%s%.2f%s\n", "Volumen: ", solido.getVolumen(), " cm^3");

        esperarEntrada(input);
    }

    private static void calcularCilindro(Scanner input){
        double radio, altura;

        System.out.println("\n-Cilindro-");
        radio = leerDato("Radio: ", input);
        altura = leerDato("Altura: ", input);

        SolidoGeometrico solido = new Cilindro(radio, altura);

        System.out.printf("%s%.2f%s\n", "Area: ", solido.getArea(), " cm^2");
        System.out.printf("%s%.2f%s\n", "Volumen: ", solido.getVolumen(), " cm^3");

        esperarEntrada(input);
    }

    private static void esperarEntrada(Scanner input){
        System.out.print("\nPor favor, presione Enter para continuar...");
        input.nextLine();
    }
}
