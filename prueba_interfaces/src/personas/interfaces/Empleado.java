package personas.interfaces;

public interface Empleado extends Persona{
    //Firma de los métodos
    public int getIdEmpleado();
    public String getOcupacion();
    public String getEmpresa();
    public double getSueldoBruto();
    public double getSueldoNeto();

    default void trabajar(){
        System.out.println("El empleado esta trabajando");
    }
}
