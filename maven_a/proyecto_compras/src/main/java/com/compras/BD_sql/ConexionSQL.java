package com.compras.BD_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionSQL {
    Connection conect = null;
    Dotenv

    final String url = "jdbc:postgresql://DESKTOP-T7MMDGJ:5432/bd_hola";
    final String usuario = "postgres";
    final String contrasena = "Miyakobase_";
    
    public Connection conectar(){
        try {
            Class.forName("org.postgresql.Driver");
            conect = DriverManager.getConnection(url, usuario, contrasena);
            //Lo puse como comentario porque no es necesario verificar la conexion cada vez que se realice una consulta o que inserten nuevos datos
            //JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error con la conexion..."+"\nError: "+e.getMessage());
        }
        return conect;
    }
}
