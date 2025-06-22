import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import funcion.Func;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        ArrayList<String> f_prima = new ArrayList<>();
        ArrayList<String> fn = new ArrayList<>();
        
        double coef = 0, exp = 0, terminos, punto, sum_terminos = 0;

        System.out.println("-Derivada-");
        System.out.println("Escriba una funcion f(x) para calcular su derivada");
        System.out.print("Cantidad de terminos de la funcion: ");
        terminos = leerNum(input);
        System.out.print("Punto en el que desea evaluar f'(x): ");
        punto = leerNum(input);

        for (int i = 1; i <= terminos; i++) {
            System.out.println("Termino ["+i+"]");
            System.out.print("Coeficiente de x: ");
            coef = leerNum(input);
            System.out.print("Exponente de x: ");
            exp = leerNum(input);

            Func func = new Func(coef, exp);

            agregarTermino_Funcion(coef, exp, fn);
            agregarTermino_Derivada(coef, exp, f_prima, func);

            sum_terminos += (func.getDerivada_coef() * Math.pow(punto, func.getDerivada_exp())); //Sumador de cada término evaluado en el punto dado
        }

        imprimirFuncion(coef, exp, fn);
        imprimirDerivada(punto, sum_terminos, f_prima);

        input.close();
    }

    private static double leerNum(Scanner input){
        double num;

        do {
            try {
                num = input.nextDouble();
                return num;
            } catch (InputMismatchException e) {
                System.out.println("Error: Tipo de dato no valido");
            } finally {
                input.nextLine();
            }
        } while (true);
    }

    private static void agregarTermino_Funcion(double coef, double exp, ArrayList<String> fn){
        try {
            if (exp == 0) {
                fn.add( (int) coef + "");
            }
            else if (exp == 1) {
                fn.add( (int) coef + "x");
            }
            else {
                if (coef == 1){
                    fn.add("x^" + (int) exp);
                }
                else {
                    fn.add( (int) coef + "x^" +exp);
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error: no se pudo agregar el elemento a la lista");
        }
    }

    private static void agregarTermino_Derivada(double coef, double exp, ArrayList<String> f_prima, Func func){
        try {
            if (exp == 0) {
                f_prima.add("0");
            }
            else if (exp == 1) {
                f_prima.add( (int) func.getDerivada_coef() +"");
            }
            else if (func.getDerivada_exp() == 1) {
                f_prima.add( (int) func.getDerivada_coef() + "x");
            }
            else {
                f_prima.add( (int) func.getDerivada_coef() + "x^" + (int) func.getDerivada_exp());
            }
            
        } catch (Exception e) {
            System.out.println("Error: no se pudo agregar el elemento a la lista");
        }
    }

    private static void imprimirFuncion(double coef, double exp, ArrayList<String> fn){
        try {
            System.out.print("f(x) = ");

            for (int i = 0; i < fn.size(); i++) {
                if (i == (fn.size() -1)){
                    System.out.print(fn.get(i)+"\n");
                }
                else {
                    System.out.print(fn.get(i) + " + ");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: no se pudo imprimir el resultado");
        }
    }

    private static void imprimirDerivada(double punto, double sum_terminos, ArrayList<String> f_prima){
        try {
            System.out.print("f'(x) = ");

            for (int i = 0; i < f_prima.size(); i++) {
                if (i == (f_prima.size() -1)){
                    System.out.print(f_prima.get(i)+"\n");
                }
                else {
                    System.out.print(f_prima.get(i) + " + ");
                }
            }

            System.out.println("f'("+ punto+") = " + sum_terminos);

        } catch (Exception e) {
            System.out.println("Error: no se pudo imprimir el resultado");
        }
    }
}
