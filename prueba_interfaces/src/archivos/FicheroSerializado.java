package archivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FicheroSerializado {
    public static void nuevaSerializacion(String ruta, Object obj) throws IOException { // En este caso como ejemplo, lo haré con un Object

        try (FileOutputStream fo_stream = new FileOutputStream(ruta, false); // append = false para sobreescribir el fichero
                ObjectOutputStream oo_stream = new ObjectOutputStream(fo_stream)) {

            // Se escribe el objeto serializado de tipo Estudiante
            oo_stream.writeObject(obj);

            // Mensaje de confirmacion
            System.out.println("Fichero serializado escrito correctamente");

        } catch (IOException e) {
            throw new IOException("Sucedio un error al serializar el fichero ");
        }
    }

    public static Object Deserealizacion(String ruta) throws IOException, ClassNotFoundException {

        try (FileInputStream fi_stream = new FileInputStream(ruta);
                ObjectInputStream oi_stream = new ObjectInputStream(fi_stream)) {

            // Se lee el objeto serializado y se realiza el casteo a su respectiva clase
            Object obj = oi_stream.readObject();

            // Mensaje de confirmacion
            System.out.println("El fichero serializado se ha leido correctamente");

            return obj;

        } catch (IOException e) {
            throw new IOException("Ocurrio un error al deserealizar el fichero ");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("La clase no ha sido encontrada ");
        }
    }
}
