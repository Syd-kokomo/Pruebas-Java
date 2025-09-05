package utilidades;

import java.io.InputStream;
import java.util.Scanner;

public class Validacion {
    public static Scanner validScanner(InputStream inputStream) {
        try {
            Scanner input = new Scanner(inputStream);
            return input;
        } catch (Exception e) {
            System.out.println("Ha sucedido un problema al inicializar el scanner");
            throw new IllegalArgumentException("El argumento del metodo Scanner no es valido");
        }
    }

    public static <T extends Number> T numValidado(String mensaje, Scanner input, Class<T> class1)
            throws NumberFormatException {
        String str;

        while (true) {
            try {
                System.out.print(mensaje);
                str = input.nextLine();
                if (class1 == Integer.class) {
                    return class1.cast(Integer.parseInt(str));
                } else if (class1 == Long.class) {
                    return class1.cast(Long.parseLong(str));
                } else if (class1 == Double.class) {
                    return class1.cast(Double.parseDouble(str));
                } else {
                    throw new IllegalArgumentException("La clase usada como parametro no es valida (por ejemplo, Short, no es aceptado ahora)");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: tipo de dato no valido " + e);
                continue;
            }

        }
    }
}
