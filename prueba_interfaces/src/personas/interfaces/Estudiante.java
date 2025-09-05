package personas.interfaces;

import java.time.LocalDate;
import java.lang.Math;

public interface Estudiante extends Persona {
    //Firma de los métodos
    public String getNumCuenta();
    public String getUniversidad();
    public String getCarrera();
    public int getPromedioGlobal();
    
    //Se instancia un objeto de tipo LocalDate llamado fecha
    LocalDate fecha = LocalDate.now(); //Con el método LocalDate.now() se obtiene la fecha actual de la computadora

    public default String numeroCuenta(){
        String prefijo = "";
        String periodo = "";
        int[] digitos = new int[4];
        String str_digitos = "";

        //Se obtiene el año actual con fecha.getYear(). Con el método Integer.toString() se convierte este número a una cadena
        prefijo = Integer.toString(fecha.getYear());

        //Condicionales para tomar la fecha actual, y determinar en que meses se realizo la matricula.
        //Suponiendo que es Febrero, se tomará periodo = "100" para determinar que es el primer periodo academico
        if (fecha.getMonthValue() >= 1 && fecha.getMonthValue() <= 4) { //El rango corresponde a los meses Enero-Abril
            periodo = "100";    
        }
        else if (fecha.getMonthValue() >=5 && fecha.getMonthValue() <=8) { //El rango corresponde a los meses Mayo-Agosto
            periodo = "102";
        }
        else if (fecha.getMonthValue() >= 9 && fecha.getMonthValue() <=12) { //El rango corresponde a los meses Septiembre-Diciembre
            periodo = "103";
        }

        //Ultimos 4 digitos del numero de cuenta generados de forma aleatoria
        //Primero se almacenan en un arreglo de tipo int
        for (int i = 0; i < digitos.length; i++) {
            //Se genera un número entre 1-9, y se castea de tipo double a int
            digitos[i] = (int)(Math.random() * 9) + 1; 
        }
    
        //Se recorre el arreglo para convertir y concatenar sus elementos en un unico String
        for (int i = 0; i < digitos.length; i++) {
            str_digitos += Integer.toString(digitos[i]);
        }

        //Retorna la concatenación de las tres partes que conforman el número de cuenta
        return prefijo + periodo + str_digitos; 
    }
}
