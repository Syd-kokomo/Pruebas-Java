package com.compras;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.compras.BD_sql.*;
import com.compras.consulta_sql.Datos_consulta;
import com.compras.prenda.*;

public class Main
{
    public static void main( String[] args )
    {  
        System.out.println(System.getProperty("user.dir"));
        mostrarMenu();
    }

    private static void mostrarMenu(){
        boolean bandera=false; 
        int opcion;
        String dato_entrada;
        
        //Título y mensaje de bienvenida
        crearTitulo("Bienvenido a (nombre tienda de ropa)");

        final String menuInicio = "Menu de inicio"
        +"\nPor favor, seleccione una de las opciones mostradas a continuacion\n"
        +"\n1. Nuevo articulo"
        +"\n2. Buscar articulo"
        +"\n0. Salir";

        do {
            //Se le solicita
            dato_entrada = JOptionPane.showInputDialog(null, menuInicio, "Inicio", JOptionPane.INFORMATION_MESSAGE);

            if (dato_entrada==null) {
                JOptionPane.showMessageDialog(null, "Cerrando programa...", "Programa finalizado", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            else if (dato_entrada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este campo no puede estar vacio", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            else {
                try {
                    opcion = Integer.parseInt(dato_entrada);
                    switch (opcion) {
                        case 1:
                            leerDatos();
                            break;
                        case 2:
                            consulta_BD();
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "Cerrando programa...", "Programa finalizado", JOptionPane.PLAIN_MESSAGE);
                            bandera=true;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opcion no valida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Tipo de dato no valido, por favor ingrese un numero", "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (!bandera);

        return;
    }

    private static void crearTitulo(String titulo){
        String asteriscos = "-".repeat(titulo.length()*3);
        String espacios = " ".repeat(titulo.length()-1);

        JOptionPane.showMessageDialog(null, "\n" + asteriscos+"\n"
        +"|" + espacios + titulo + espacios + "|\n"
        +asteriscos + "\n", "Bienvenida", JOptionPane.PLAIN_MESSAGE);
    }

    private static void leerDatos(){
        String cod, cat, marc, color, talla;
        int stock;
        double prc;

        cod = leerCadena("Los codigos deben escribirse de la forma [####00]\n"
                +"\nCodigo: "); 
        if (cod==null){   //Si la cadena tiene el valor de null, entonces el usuario usó la opción de cancelar o intentó cerrar la ventana. Devuelve return y regresa al menú
        }
        else {
            cod = validarCodigo(cod);   //Se verifica el formato del código
        }

        cat = leerCadena("Categorias compatibles"
                +"\nRopa deportiva: Camiseta, Pants, Jersey, Sudadera, Short, Top"
                +"\nRopa formal: Vestido, Falda, Saco, Traje, Chaleco, Calzado\n"
                +"\nCategoria del articulo: ");
        if (cat==null){
            return;
        }
        else {
            cat = validarCategoria(cat); //Se verifica las categorias disponibles
        }

        marc = leerCadena("Marcas disponibles"
                +"\nRopa deportiva: Adidas, Fila, Umbro, NEW BALANCE, Nike"
                +"\nRopa formal: Zara, CHANEL, Dolce&Gabanna, Tiendas Charly\n"
                +"\nMarca del articulo: ");
        if (marc==null){
            return; 
        }
        else {
            marc = validarMarca(marc);  //Se verifican las marcas disponibles
        }

        color = leerCadena("Los colores deben escribirse en codigo HEX"
        +"\nEjemplo: #FF1111\n"
        +"\nColor del articulo: ");
        if (color==null){
            return;
        }
        else{
            color = validarColor(color);   //Se verifica el formato del código de color
        }

        talla = leerCadena("Lista de tallas disponibles"
        +"\nXXS, XS, S, M, L, XL, XXL\n"
        +"\nTalla del articulo: ");
        if (talla==null){
            return;
        } else{
            talla = validarTalla(talla);  //Se valida que el usuario haya ingresado una talla válida
        }

        stock = leerEntero("Stock: ");
        if (stock==-1) {  //Si la variable devuelve -1, quiere decir que el usuario usó la opción de cancelar o intentó cerrar la ventana. Devuelve return y regresa al menú
            return;
        }

        prc = leerDecimal("Precio de costo (monto en Lempiras)");
        if (prc==-1){
            return;
        }

        //Creacion del método constructor solamente cuando los datos ya han sido validados
        Prenda prod = new Prenda(cod, cat, marc, color, talla, stock, prc);

        //Método donde se enviarán los datos a la BD
        enviardatos_bd(prod);
    }

    private static String leerCadena(String mensaje){
        String dato_entrada;

        do {
            dato_entrada=JOptionPane.showInputDialog(null, mensaje, "Introduzca la informacion que se le solicita", JOptionPane.PLAIN_MESSAGE);

            if (volverMenu(dato_entrada)){   
                return null;
            }
            else if (dato_entrada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este campo no puede estar vacio", "Advertencia",JOptionPane.WARNING_MESSAGE);
                continue;
            }
            else {
                return dato_entrada;
            }
        } while (true);
    }

    private static int leerEntero(String mensaje){
        int num=0;
        String dato_entrada;

        do{
            dato_entrada=JOptionPane.showInputDialog(null, mensaje, "Introduzca la informacion que se le solicita", JOptionPane.PLAIN_MESSAGE);

            if(volverMenu(dato_entrada)){
                return -1;
            }   
            else if (dato_entrada.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Este campo no puede estar vacio", "Advertencia",JOptionPane.WARNING_MESSAGE); 
            }
            else {
                try {
                    num=Integer.parseInt(dato_entrada);
                    if (num<0) {
                        JOptionPane.showMessageDialog(null, "Numero no valido, no se aceptan numeros negativos", "Advertencia",JOptionPane.WARNING_MESSAGE);
                    } 
                    else { 
                        return num;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Tipo de dato no valido, por favor ingrese un numero", "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            } 
        } while (true);
    }

    private static double leerDecimal(String mensaje){
        double num=0;
        String dato_entrada;

        do{
            dato_entrada=JOptionPane.showInputDialog(null, mensaje, "Introduzca la informacion que se le solicita", JOptionPane.PLAIN_MESSAGE);

            if(volverMenu(dato_entrada)){
                return -1;
            }   
            else if (dato_entrada.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Este campo no puede estar vacio", "Advertencia",JOptionPane.WARNING_MESSAGE); 
            }
            else {
                try {
                    num=Double.parseDouble(dato_entrada);
                    if (num<0) {
                        JOptionPane.showMessageDialog(null, "Numero no valido, no se aceptan numeros negativos", "Advertencia",JOptionPane.WARNING_MESSAGE);
                    } 
                    else { 
                        return num;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Tipo de dato no valido, por favor ingrese un numero", "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            } 
        } while (true);
    }
    
    //Este método verifica si la entrada de usuario fue null. En caso de ser así, permite salir del programa en ese momento
    private static boolean volverMenu(String dato){
        boolean validacion = false;
        int confirmar;

        if (dato == null){
            confirmar = JOptionPane.showConfirmDialog(null, "Desea volver al menu principal?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar==JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Volviendo al menu...", "Cargando", JOptionPane.PLAIN_MESSAGE);
                validacion=true;
            }
            else{
                validacion = false;
            }
        }

        return validacion; 
    }

    private static String validarCodigo(String codigo){
        do{
            //Verifica el formato del código introducido
            if (!codigo.matches("[a-zA-Z]{4}\\d{2}")) {
                JOptionPane.showMessageDialog(null, "El formato del codigo no es valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                codigo=leerCadena("Los codigos deben escribirse de la forma [ABCD00]\n"
                        +"\nCodigo: ");
            }
        } while(!codigo.matches("[a-zA-Z]{4}\\d{2}"));

        return codigo;
    }

    private static String validarCategoria(String categoria){
        String cat_mayus;

        ArrayList<String> categorias_disp = new ArrayList<>(List.of(
            "CAMISA",
            "PANTS", 
            "JERSEY", 
            "SUDADERA", 
            "SHORT", 
            "TOP", 
            "VESTIDO", 
            "FALDA",
            "SACO",
            "TRAJE",
            "CHALECO",
            "CALZADO"));
 
        do {
            cat_mayus=categoria.toUpperCase();

            if (categorias_disp.contains(cat_mayus)) {
                return cat_mayus;
            }
            else {
                JOptionPane.showMessageDialog(null, "El dato que usted ingreso no pertenece a una de las categorias registradas", "Advertencia", JOptionPane.WARNING_MESSAGE);
                categoria = leerCadena("Categorias compatibles"
                        +"\nRopa deportiva: Camiseta, Pants, Jersey, Sudadera, Short, Top"
                        +"\nRopa formal: Vestido, Falda, Saco, Traje, Chaleco, Calzado\n"
                        +"\nCategoria del articulo: ");
            }
        } while (true);
    }

    private static String validarMarca(String marca){
        String marc_mayus;

        ArrayList<String> marcas_disp = new ArrayList<>(List.of(
            "ADIDAS",
            "FILA",
            "UMBRO",
            "NEW BALANCE",
            "NIKE",
            "ZARA",
            "CHANEL",
            "DOLCE&GABANNA"
            ,"TIENDAS CHARLY"));

            do {
                marc_mayus = marca.toUpperCase();
                if (marcas_disp.contains(marc_mayus)) {
                    return marc_mayus;
                }
                else {
                    JOptionPane.showMessageDialog(null, "El dato que usted ingreso no es una marca valida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    marca = leerCadena("Marcas disponibles"
                            +"\nRopa deportiva: Adidas, Fila, Umbro, NEW BALANCE, Nike"
                            +"\nRopa formal: Zara, CHANEL, Dolce&Gabanna, Tiendas Charly\n"
                            +"\nMarca del articulo: ");
                }
            } while (true);
    }

    private static String validarColor(String color){
        do {
            //Verifica si la informacion dada representa un código en formato hexadecimal
            if (!color.matches("#[0-9a-fA-F]{6}")) {
                JOptionPane.showMessageDialog(null, "Codigo de color hexadecimal no valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                color=leerCadena("Los colores deben escribirse en codigo HEX"
                        +"\nEjemplo: #FF1111\n"
                        +"\nColor del articulo: ");
            }
        } while (!color.matches("#[0-9a-fA-F]{6}"));

        return color;
    }

    private static String validarTalla(String talla){
        boolean validacion=false;
        String talla_mayus;

        ArrayList<String> tallas_disp = new ArrayList<>(List.of(
            "XXS", 
            "XS", 
            "S", 
            "M", 
            "L", 
            "XL", 
            "XXL"));

        do {
            talla_mayus = talla.toUpperCase(); 
            if (tallas_disp.contains(talla_mayus)) {
                validacion=true;
            } 
            else if (!validacion) {
                JOptionPane.showMessageDialog(null, "El dato no representa una talla valida, intentelo de nuevo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                talla=leerCadena("Lista de tallas disponibles"
                        +"\nXXS, XS, S, M, L, XL, XXL\n"
                        +"\nTalla del articulo: ");
            }
        } while (!validacion);

        return talla_mayus;
    }

    private static void enviardatos_bd(Prenda prod){
        Datos_BD datos = new Datos_BD();
        datos.enviar_datos(prod);
    }

    private static void consulta_BD(){
        Datos_BD datos = new Datos_BD();
        Datos_consulta consult_a = datos.getConsulta();

        String codigo_busqueda = JOptionPane.showInputDialog("Busqueda por medio de codigo\n"
        +"\nEscriba el codigo del articulo que desee buscar: ");

        if (volverMenu(codigo_busqueda)) {
            return;
        }
        
        datos.consultar_datos(codigo_busqueda);

        JOptionPane.showMessageDialog(null, "-Articulo encontrado-"
        +"\nCategoria: "+consult_a.getCategoria()
        +"\nMarca: "+consult_a.getMarca()
        +"\nColor: "+consult_a.getColor()
        +"\nTalla: "+consult_a.getTalla()
        +"\nStock disponible: "+consult_a.getStock()
        +"\nPrecio: HNL. "+consult_a.getPrecio());
    }
}