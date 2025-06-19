package com.prueba.cliente;

public class Cliente {
    //Atributos
    private int codigo;
    private double venta;

    //Método constructor
    public Cliente(int codigo, double venta){
        this.codigo = codigo;
        this.venta = venta;
    }

    //Métodos getter
    public double getDescuento(){
        double descuento; 
        if (codigo == 1) {
            descuento = venta*0.30;

        }
        else if (codigo == 2){
            descuento = venta*0.15;
        }
        else if (codigo == 3){
            descuento = venta*0.25;
        }
        else {
            descuento = 0;
        }
        return descuento;
    }

    public double getTotal(){
        return venta-getDescuento();
    }
}
