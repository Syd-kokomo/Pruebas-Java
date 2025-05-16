package com.compras.producto;

public class Producto{
    //Atributos
    private String codigo, categoria, marca, color, talla;
    private int stock;
    private double precio;

    //Método constructor
    public Producto(String codigo, String categoria, String marca, String color, String talla, int stock, double precio){
        this.codigo = codigo;
        this.categoria = categoria;
        this.marca = marca;
        this.color = color;
        this.talla = talla;
        this.stock = stock;
        this.precio = precio;
    }

    //Métodos getter
    //Código
    public String getCodigo(){
        return codigo;
    }

    //Categoria de la prenda
    public String getCategoria(){
        return categoria;
    }

    //Marca
    public String getMarca(){
        return marca;
    }

    //Color (en código hex)
    public String getColor(){
        return color;
    }

    //Talla de la prenda
    public String getTalla(){
        return talla;
    }

    //Stock
    public int getStock(){
        return stock;
    }

    //Precio de retail
    public double getPrecio(){
        return precio;
    }
}
