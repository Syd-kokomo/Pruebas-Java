package com.compras.consulta_sql;

public class Datos_consulta {
    //Atributos
    private String c_codigo, c_categoria, c_marca, c_color, c_talla;
    private int c_stock;
    private double c_precio;

    //Métodos setter y getter
    public void setCodigo(String c_codigo){
        this.c_codigo = c_codigo;
    }
    public String getCodigo(){
        return c_codigo;
    }

    public void setCategoria(String c_categoria){
        this.c_categoria = c_categoria;
    }
    public String getCategoria(){
        return c_categoria;
    }

    public void setMarca(String c_marca){
        this.c_marca = c_marca;
    }
    public String getMarca(){
        return c_marca;
    }

    public void setColor(String c_color){
        this.c_color = c_color;
    }
    public String getColor(){
        return c_color;
    }

    public void setTalla(String c_talla){
        this.c_talla = c_talla;
    }
    public String getTalla(){
        return c_talla;
    }

    public void setStock(int c_stock){
        this.c_stock = c_stock;
    }
    public int getStock(){
        return c_stock;
    }

    public void setPrecio(double c_precio){
        this.c_precio = c_precio;
    }
    public double getPrecio(){
        return c_precio;
    }
}
