package com.compras;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.compras.conexion_sql.*;
import com.compras.consulta_sql.Datos_consulta;
import com.compras.producto.*;

/**
 * Programa con base de datos!
 *
 */


public class Main
{
    public static void main( String[] args )
    {  
        mostrarMenu();
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

    private static void crearTitulo(String titulo){
        String asteriscos = "-".repeat(titulo.length()*3);
        String espacios = " ".repeat(titulo.length()-1);

        JOptionPane.showMessageDialog(null, "\n" + asteriscos+"\n"
        +"|" + espacios + titulo + espacios + "|\n"
        +asteriscos + "\n", "Bienvenida", JOptionPane.PLAIN_MESSAGE);
    }

    private static void mostrarMenu(){
        boolean bandera=false;
        int opcion;
        String dato_entrada;
        
        //Título 
        crearTitulo("Bienvenido a (nombre tienda de ropa)");

        final String menuInicio = "Menu de inicio"
        +"\nPor favor, seleccione una de las opciones mostradas a continuacion\n"
        +"\n1. Nuevo articulo"
        +"\n2. Buscar articulo"
        +"\n0. Salir";

        do {
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

    private static void leerDatos(){
        String cod, cat, marc, color, talla;
        int stock;
        double prc;

        cod = leerCadena("Los codigos deben escribirse de la forma [ABCD00]\n"
                +"\nCodigo: "); 
        if (cod==null){
            return;
        }
        else {
            cod = validarCodigo(cod);
        }

        cat = leerCadena("Categorias compatibles: "
                +"\nCamisa, Pantalon, Sueter, "
                +"\nChaleco, Vestido, Zapato, Calceta, Bolso\n"
                +"\nCategoria del articulo: ");
        if (cat==null){
            return;
        }
        else {
            cat = validarCategoria(cat);
        }

        marc = leerCadena("Marcas disponibles: "
                +"\nAdidas, Zara, Converse, "
                +"\nFila, Umbro, NEW BALANCE, CHANEL, Dolce&Gabanna\n"
                +"\nMarca del articulo: ");
        if (marc==null){
            return; 
        }
        else {
            marc = validarMarca(marc);
        }

        color = leerCadena("Los colores deben escribirse en codigo HEX"
        +"\nEjemplo: #FF1111\n"
        +"\nColor del articulo: ");
        if (color==null){
            return;
        }
        else{
            color = validarColor(color);
        }

        talla = leerCadena("Lista de tallas disponibles"
        +"\nXXS, XS, S"
        +"\nM, L, XL, XXL\n"
        +"\nTalla del articulo: ");
        if (talla==null){
            return;
        } else{
            talla = validarTalla(talla);
        }

        stock = leerEntero("Stock: ");
        if (stock==-1) {
            return;
        }

        prc = leerDecimal("Precio de costo (monto en Lempiras)");
        if (prc==-1){
            return;
        }

        //Creacion del método constructor solamente cuando los datos ya han sido validados
        Producto prod = new Producto(cod, cat, marc, color, talla, stock, prc);

        //Método donde se enviarán los datos a la BD
        enviardatos_bd(prod);
    }

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
            //Verifica si la longitud es de 6 caracteres
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
            "PANTALON", 
            "ABRIGO", 
            "CHALECO", 
            "VESTIDO", 
            "ZAPATO", 
            "CALCETA", 
            "BOLSO"));

        do {
            cat_mayus=categoria.toUpperCase();

            if (categorias_disp.contains(cat_mayus)) {
                return cat_mayus;
            }
            else {
                JOptionPane.showMessageDialog(null, "El dato que usted ingreso no pertenece a una de las categorias registradas", "Advertencia", JOptionPane.WARNING_MESSAGE);
                categoria = leerCadena("Categorias compatibles: "
                        +"\nCamisa, Pantalon, Abrigo, "
                        +"\nChaleco, Vestido, Zapato, Calceta, Bolso\n"
                        +"\nCategoria del articulo: ");
            }
        } while (true);
    }

    private static String validarMarca(String marca){
        String marc_mayus;

        ArrayList<String> marcas_disp = new ArrayList<>(List.of(
            "ADIDAS",
            "ZARA",
            "CONVERSE",
            "FILA",
            "UMBRO",
            "NEW BALANCE",
            "CHANEL",
            "DOLCE&GABANNA"));

            do {
                marc_mayus = marca.toUpperCase();
                if (marcas_disp.contains(marc_mayus)) {
                    return marc_mayus;
                }
                else {
                    JOptionPane.showMessageDialog(null, "El dato que usted ingreso no es una marca valida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    marca = leerCadena("Marcas disponibles: "
                            +"\nAdidas, Zara, Converse, "
                            +"\nFila, Umbro, NEW BALANCE, CHANEL, Dolce&Gabanna\n"
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
                        +"\nXXS, XS, S"
                        +"\nM, L, XL, XXL\n"
                        +"\nTalla del articulo: ");
            }
        } while (!validacion);

        return talla_mayus;
    }

    private static void enviardatos_bd(Producto prod){
        Enviar datos = new Enviar();
        datos.enviar_datos(prod);
    }

    private static void consulta_BD(){
        Enviar datos = new Enviar();
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




/*
    private static void editarCodigo(Producto producto){
        String cod = JOptionPane.showInputDialog("Escriba un nuevo codigo");
    
        do{
            //Verifica si la longitud es de 6 caracteres
            if (producto.getCodigo().length()!=6) {
                JOptionPane.showMessageDialog(null, "El codigo debe tener 6 caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
                cod=leerCadena("Codigo");

                producto.setCodigo(cod);
            }
        } while(producto.getCodigo().length()!=6);
    }
*/