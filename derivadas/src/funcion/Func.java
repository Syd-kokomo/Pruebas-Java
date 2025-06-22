package funcion;

public class Func {
    //Atributos
    private double coeficiente; //Coeficiente del término
    private double exponente;  //Exponente del término

    //Método constructor
    public Func (double coeficiente, double exponente){
        this.coeficiente = coeficiente;
        this.exponente = exponente;
    }

    //Métodos
    public double getDerivada_coef(){
        return coeficiente*exponente;
    }

    public double getDerivada_exp(){
        return exponente-1;
    }
}
