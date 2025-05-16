package com.compras.BD_sql;

import java.sql.Connection;
import javax.swing.JOptionPane;
import com.compras.consulta_sql.Datos_consulta;
import com.compras.prenda.Prenda;
import java.sql.ResultSet;

public class Datos_BD extends ConexionSQL{

    java.sql.Statement state;
    ResultSet rs;
    Datos_consulta consult = new Datos_consulta();

    public void enviar_datos(Prenda prod) {
        try {
            Connection conn = conectar();
            state = conn.createStatement();
            String insert_sql = "insert into tienda_ropa(codigo, categoria, marca, color, talla, stock, precio) values ('"+prod.getCodigo()+"', '"+prod.getCategoria()+"', '"+prod.getMarca()+"', '"+prod.getColor()+"', '"+prod.getTalla()+"', '"+prod.getStock()+"', '"+prod.getPrecio()+"');";

            state.execute(insert_sql);
            state.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Los datos se han guardado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se han podido guardar los datos"
            +"\nError: "+e.getMessage());
        }
    }

    public void consultar_datos(String c_codigo){
        try {
            Connection conn = conectar();
            state = conn.createStatement();
            String select_sql = "select * from tienda_ropa where codigo='"+c_codigo+"';";

            rs = state.executeQuery(select_sql);

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Se han encontrado datos relacionados");
                consult.setCodigo(rs.getString("codigo"));
                consult.setCategoria(rs.getString("categoria"));
                consult.setMarca(rs.getString("marca"));
                consult.setColor(rs.getString("color"));
                consult.setTalla(rs.getString("talla"));
                consult.setStock(rs.getInt("stock"));
                consult.setPrecio(rs.getDouble("precio"));
            }
            else {
                JOptionPane.showMessageDialog(null, "No se han encontrado datos relacionados", "Sin datos", JOptionPane.WARNING_MESSAGE);
                consult.setCodigo("");
                consult.setCategoria("");
                consult.setMarca("");
                consult.setColor("");
                consult.setTalla("");
                consult.setStock(0);
                consult.setPrecio(0);      
            }
            state.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido acceder a los datos"+"\nError: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public Datos_consulta getConsulta(){
        return consult;
    }
}
