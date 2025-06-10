package solido_geometrico.esfera;

import solido_geometrico.SolidoGeometrico;

public class Esfera extends SolidoGeometrico {
    //Método constructor 
    public Esfera (double radio){
        super(radio, 0, 0); //Usa el método constructor de la superclase
    }

    //Métodos getter especificos
    @Override
    public double getArea(){
        return 4*pi*Math.pow(radio, 2);
    }

    @Override
    public double getVolumen(){
        return ((4.0/3)*pi)*(Math.pow(radio, 3));
    }
}
