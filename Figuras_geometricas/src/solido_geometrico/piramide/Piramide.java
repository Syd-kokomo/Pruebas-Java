package solido_geometrico.piramide;

import solido_geometrico.SolidoGeometrico;

public class Piramide extends SolidoGeometrico {
    //Método constructor
    public Piramide(double lado, double altura){
        super(0, lado, altura);
    }

    //Métodos getter
    @Override
    public double getArea(){
        double apotema = Math.sqrt(Math.pow(altura, 2)+Math.pow((lado/2), 2));
        return lado*((2*apotema)+lado); //l*((2*ap)+l)
    }

    @Override
    public double getVolumen(){
        return (Math.pow(lado, 2)*altura)/3; //(lado^2)*h/3
    }
}
