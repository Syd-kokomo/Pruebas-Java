package com.compras.BD_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import io.github.cdimascio.dotenv.Dotenv;


public class ConexionSQL {
    Connection conect = null;
    Dotenv dotenv = Dotenv.configure().directory("proyecto_compras").load();
        
    final String bd = dotenv.get("DATABASE");
    final String host = dotenv.get("DB_HOST");
    final String puerto = dotenv.get("DB_PUERTO");
    final String usuario = dotenv.get("DB_USUARIO");
    final String contrasena = dotenv.get("DB_CONTRASENA");

    public ConexionSQL(){
        if (bd==null || host==null || puerto==null || usuario==null || contrasena==null) {
            JOptionPane.showMessageDialog(null, "Hubo un error", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException("No se han podido encontrar una o mas variables de entorno");
        }
    }
    
    final String url = "jdbc:postgresql://"+host+":"+puerto+"/"+bd;
    
    public Connection conectar(){
        try {
            Class.forName("org.postgresql.Driver");
            conect = DriverManager.getConnection(url, usuario, contrasena);
            //Lo puse como comentario porque no es necesario verificar la conexion cada vez que se realice una consulta o que inserten nuevos datos
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error con la conexion..."+"\nError: "+e.getMessage());
        }
        return conect;
    }
}
