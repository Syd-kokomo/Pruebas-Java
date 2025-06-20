package com.prueba;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.prueba.cliente.Cliente;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int codigo;
        double venta;

        //Variables de suma
        double sum_te = 0, sum_cl = 0, sum_empl = 0, sum_part = 0; //Sumador para clientes de la tercera edad /clientes con receta / empleados con receta / clientes particulares 

        //Contadores
        int cont_te = 0, cont_cl = 0, cont_empl = 0, cont_part = 0; //Contadores
        
        //Variable de control del ciclo
        boolean bandera = false;

        while (!bandera) {
            System.out.println("-Farmacia El buen Sapito-"); 
            codigo=leerCodigo(input);
            venta=leerVenta(input);

            input.nextLine(); //Limpiar el salto de línea pendiente
            
            Cliente cl = new Cliente(codigo, venta);

            if (codigo == 1) { //Empleados con receta
                cont_empl++;
                sum_empl += cl.getTotal();
            } 
            else if (codigo == 2){ //Clientes con receta
                cont_cl++;
                sum_cl += cl.getTotal();
            }
            else if (codigo == 3){ //Clientes de la tercera edad con receta
                cont_te++;
                sum_te += cl.getTotal();
            } 
            else { //Clientes que no aplican a ningun descuento
                cont_part++;
                sum_part += cl.getTotal();
            }

            if (!validarContinuacion(input)) {
                bandera=true;
            }
        }

        System.out.println("\n-Resumen total del dia-");
        System.out.println("-Empleados con receta-");
        System.out.println("Total de empleados con receta: "+cont_empl);
        System.out.println("Promedio de ventas: "+calcularPromedio(sum_empl, cont_empl)+" .HNL");

        System.out.println("\n-Clientes con receta");
        System.out.println("Total de clientes: "+cont_cl);
        System.out.println("Promedio de ventas: "+calcularPromedio(sum_cl, cont_cl)+" .HNL");

        System.out.println("\n-Clientes de la tercera edad-");
        System.out.println("Total de clientes: "+cont_te);
        System.out.println("Promedio de ventas: "+calcularPromedio(sum_te, cont_te)+" .HNL");

        System.out.println("\n-Clientes particulares-");
        System.out.println("Total de clientes: "+cont_part);
        System.out.println("Promedio de ventas: "+calcularPromedio(sum_part, cont_part)+" .HNL");

        input.close();
    }

    private static int leerCodigo(Scanner input){
        int codigo=0;

        do {
            try {
                System.out.print("Escriba el codigo del cliente: ");
                codigo=input.nextInt();
                
                if (codigo<0) {
                    System.out.println("Codigo no valido");
                }
                else {
                    return codigo;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: tipo de dato no valido");
                input.nextLine();
            }
        } while (true);
    }

    private static double leerVenta(Scanner input){
        double num=0;

        do {
            try {
                System.out.print("Escriba la venta del cliente: ");
                num=input.nextDouble();
                
                if (num<=0) {
                    System.out.println("La venta ingresada no puede ser un valor negativo o 0");
                }
                else{
                    return num;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Tipo de dato no valido");
                input.nextLine();
            }
        } while (true);
    }

    private static boolean validarContinuacion(Scanner input){
        String opcion;

        while (true) {
            System.out.println("\nDesea continuar con otro cliente? Y/N ");
            opcion=input.nextLine();

            if (opcion.toUpperCase().equals("Y")) {
                return true;
            }
            else if (opcion.toUpperCase().equals("N")){
                return false;
            }
            else {
                System.out.println("Por favor escriba una opcion valida");
            }
        }
    }

    private static double calcularPromedio(double num, int cont){
        double promedio = (cont != 0) ? num/cont : 0;

        return promedio;
    }
}