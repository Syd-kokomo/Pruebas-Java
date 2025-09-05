package personas.empleados;

import personas.interfaces.Empleado;

public class Gerente implements Empleado{
    //Definición de los métodos
    public String getNombre(){
        return "Tulio";
    }

    public String getApellido(){
        return "Trivino";
    }

    public int getEdad(){
        return 41;
    }

    public Long getDni(){
        return 1000111110000L;
    }

    public String getNacionalidad(){
        return "Chileno";
    }

    public int getIdEmpleado(){
        return 1111;
    }

    public String getOcupacion(){
        return "Gerente";
    }

    public String getEmpresa(){
        return "Doofenshmirtz malvados y asociados";
    }

    public double getSueldoBruto(){
        return 40000;
    }

    public double getSueldoNeto(){
        return getSueldoBruto() + getAumento();
    }

    private double getAumento(){
        final double bono = 0.15;
        return getSueldoBruto()*bono; //sueldo_bruto * 15%
    }
}
