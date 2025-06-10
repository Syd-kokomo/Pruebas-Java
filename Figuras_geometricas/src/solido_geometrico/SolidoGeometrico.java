package solido_geometrico;

public class SolidoGeometrico {
    //Atributos
    protected final double pi = Math.PI;
    protected double radio;
    protected double lado;
    protected double altura;
    
    //Métodos constructores
    public SolidoGeometrico(double radio, double lado, double altura){
        this.radio = radio;
        this.lado = lado;
        this.altura = altura; 
    }

    public double getArea(){
        return 0;
    }
    public double getVolumen(){
        return 0;
    }
}
