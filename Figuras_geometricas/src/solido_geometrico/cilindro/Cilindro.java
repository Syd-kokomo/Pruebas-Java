package solido_geometrico.cilindro;

import solido_geometrico.SolidoGeometrico;

public class Cilindro extends SolidoGeometrico {
    //Método constructor
    public Cilindro (double radio, double altura){
        super(radio, 0, altura); //Usa el método constructor de la superclase SolidoGeometrico
    }

    //Métodos getter
    @Override
    public double getArea(){
        return 2*pi*radio*(altura+radio);
    }
    
    @Override
    public double getVolumen(){
        return pi*Math.pow(radio, 2)*altura;
    }
}
