package personas.empleados;

import personas.interfaces.Empleado;

public class Cajero implements Empleado {
    private String nombre;
    private String apellido;
    private int edad;
    private Long dni;
    private String nacionalidad;
    private int id_empleado;
    private String ocupacion;
    private String empresa;
    private double sueldo_bruto;
    private double sueldo_neto;

    public Cajero(String nombre, String apellido, int edad, Long dni, String nacionalidad, int id_empleado,
            String ocupacion, String empresa, double sueldo_bruto, double sueldo_neto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.nacionalidad = nacionalidad;
        this.id_empleado = id_empleado;
        this.ocupacion = ocupacion;
        this.empresa = empresa;
        this.sueldo_bruto = sueldo_bruto;
        this.sueldo_neto = sueldo_neto;
    }

    //Definición de los métodos
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public Long getDni() {
        return dni;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public int getIdEmpleado() {
        return id_empleado;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public double getSueldoBruto() {
        return sueldo_bruto;
    }

    public double getSueldoNeto() {
        return getSueldoBruto() + getAumento();
    }

    private double getAumento(){
        final double bono = 0.15;
        return getSueldoBruto()*bono; //sueldo_bruto * 15%
    }

    @Override
    public String toString() {
        return "Cajero [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", dni=" + dni
                + ", nacionalidad=" + nacionalidad + ", id_empleado=" + id_empleado + ", ocupacion=" + ocupacion
                + ", empresa=" + empresa + ", sueldo_bruto=" + sueldo_bruto + ", sueldo_neto=" + sueldo_neto + "]";
    }

    
}
