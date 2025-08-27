package com.examplejni.utilidades;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;

public class Validacion {
    // Primer método de validacion, para convertir un String a un numero, usando un
    // tipo generico. (Lo usé para obtener el dato numerico)
    // Recibe un String como parametro, siendo el valor que se desea convertir
    // Class<T> numClass indica a que tipo de dato numerico (Integer, Double, etc)
    // se desea convertir
    public static <T extends Number> T numT(String cadena, Class<T> numClass) throws NumberFormatException {
        final String mensaje_error = "Error: tipo de dato no valido";

        T num;
        try {
            // Casteo del dato generico "num" a Integer
            if (numClass == Integer.class) {
                // Al siempre recibir un String, este se convierte a un valor entero
                int int_cadena = Integer.parseInt(cadena);

                // numClass.cast() intenta castear num (de tipo T) a int
                // A este punto, si no se ha lanzado un NumberFormatExcepcion, quiere decir que
                // int_cadena es un entero, y podrá castearse con numClass.cast()
                num = numClass.cast(int_cadena);
                return num;
            }
            // Casteo del dato generico "num" a Long
            else if (numClass == Long.class) {
                Long long_cadena = Long.parseLong(cadena);
                num = numClass.cast(long_cadena);
                return num;
            }
            // Casteo del dato generico "num" a Double
            else if (numClass == Double.class) {
                Double dbl_cadena = Double.parseDouble(cadena);
                num = numClass.cast(dbl_cadena);
                return num;
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de dato no soportado" + numClass, "Error",
                        JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Tipo de dato no soportado" + numClass);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, mensaje_error, "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException(mensaje_error + e);
        }

    }

    // Segundo metodo para validar un numero entero. Una vez obtenido su valor a
    // partir del metodo numT(), se verifica que el numero no sea ni negativo ni
    // represente una gran cantidad
    public static int numValidado(String cadena) {
        int num = numT(cadena, Integer.class); // Se obtiene el valor int de numT() pasando como parametro Integer.class

        if (num < 0 || num > 9999) {
            JOptionPane.showMessageDialog(null, "Numero no valido. Fuera de rango", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            throw new ArithmeticException("El numero no es valido, esta fuera de rango");
        }

        return num;
    }

    // Tercer metodo de validacion para validar la creacion de un JFormattedField
    public static JFormattedTextField formattedFieldValidado() {
        JFormattedTextField txt_format;

        try {
            MaskFormatter formatoISBN = new MaskFormatter("###-##-#####-##-#");
            txt_format = new JFormattedTextField(formatoISBN);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error al aplicar el formato del ISBN", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txt_format = new JFormattedTextField(); // Se le asigna un "nullFormattedField", es decir un textfield sin
                                                    // formato. Yo le di ese termino
        }

        return txt_format;
    }

    // Cuarto metodo para lanzar una excepcion personalizada en caso que el
    // datePicker() contenga un campo vacío
    public static String fechaValidada(DatePicker datePicker) throws NullPointerException {
        final String msj_error = "Error al obtener la fecha, el campo datePicker se encuentra vacio";

        try {
            LocalDate fecha_seleccionada = datePicker.getDate(); // Fecha seleccionada por el datePicker. En el caso que datePicker sea null, se lanzaría una NullPointerException
            DateTimeFormatter formato_fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Se crea el formato para el String
            String fecha = fecha_seleccionada.format(formato_fecha); // Se le asigna el formato de fecha al String

            return fecha;

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, msj_error, "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new NullPointerException(msj_error);
        }
    }

    // Quinto metodo para crear el TreeMap relacionado con los paises y ciudades
    public static TreeMap<String, String[]> crearTreeMap(String clave[], String[][] valor) {
        try {
            TreeMap<String, String[]> tMap = new TreeMap<>();
            for (int i = 0; i < clave.length; i++) {
                tMap.put(clave[i], valor[i]);
            }
            return tMap;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido crear la lista de paises", "Error", JOptionPane.ERROR_MESSAGE);
            return nullTMap(); //Retorna un TreeMap "vacio" en vez de lanzar una excepcion.
        }
    }

    private static TreeMap<String, String[]> nullTMap(){
        TreeMap<String, String[]> nullTM = new TreeMap<>();
        nullTM.put("", new String[0]);

        return nullTM;
    }
}