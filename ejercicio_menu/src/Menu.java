//Cree un programa que muestre un menu, que permita navegar entre varios niveles (2 para empezar)

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        mostrarMenu(input, 1);
        input.close();
    }

    private static void mostrarMenu(Scanner input, int nivel){
        String dato_entrada;
        int opcion;
        boolean bandera=false; //Variable de control para el bucle do-while
        
        do {
            try {
                if (nivel==1) {
                   System.out.println("-Menu principal-"
                        +"\n1.1 - Avanzar"
                        +"\n0 - Salir"
                        +"\nEscoja una opcion: "); 
                } 
                else if (nivel==2) {
                    System.out.println("-Sub menu-"
                        +"\n1.2 - Avanzar"
                        +"\n0 - Salir"
                        +"\nEscoja una opcion: ");
                }
                else if (nivel==3) {
                    System.out.println("-Sub menu-"
                        +"\n1.3 - Avanzar"
                        +"\n0 - Salir"
                        +"\nEscoja una opcion: ");
                }

                dato_entrada=input.nextLine();
                opcion=Integer.parseInt(dato_entrada);

                switch (opcion) {
                    case 1:
                        if (nivel==1) {
                            mostrarMenu(input, 2);
                        } 
                        else if (nivel==2) {
                            mostrarMenu(input, 3);
                        }
                        else if (nivel==3) {
                            System.out.println("No hay nada mas por mostrar");
                        }
                        limpiarPantalla(input);
                        break;
                    case 0:
                        System.out.println("Muchas gracias por usar el programa");
                        bandera=true;
                        
                        break;
                    default:
                        System.out.println("Por favor, ingrese una opcion valida");
                        limpiarPantalla(input);
                        break;
                }  
            } catch (NumberFormatException e) {
                System.out.println("Tipo de dato no valido, intentelo de nuevo");
                limpiarPantalla(input);
            }
            
        } while (!bandera);
    }

    private static void limpiarPantalla(Scanner input){
        System.out.println("Presione Enter para continuar...");
        input.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
