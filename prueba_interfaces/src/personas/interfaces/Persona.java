package personas.interfaces;

public interface Persona {
    //Firma de los métodos
    public String getNombre();
    public String getApellido();
    public int getEdad();
    public Long getDni();
    public String getNacionalidad();

    public default void planeta(){
        System.out.println("Usted vive en el planeta Tierra !");
    }
}
