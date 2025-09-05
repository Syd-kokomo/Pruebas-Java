package personas.estudiantes;

import personas.interfaces.Estudiante;

public class EstudianteDer implements Estudiante{
    //Definición de los métodos
    public String getNombre(){
        return "Alejandro";
    }

    public String getApellido(){
        return "Gutierrez";
    }

    public int getEdad(){
        return 23;
    }

    public Long getDni(){
        return 120132000000L;
    }

    public String getNacionalidad(){
        return "Hondureno";
    }

    public String getNumCuenta(){
        return numeroCuenta();
    }

    public String getUniversidad(){
        return "Universidad Metropolitana de Honduras";
    }

    public String getCarrera(){
        return "Derecho";
    }

    public int getPromedioGlobal(){
        return 76;
    }
}
