package com.examplejni.biblioteca;

public class Libro extends Coleccion{
    //Atributos específicos
    private String pais;
    private String ciudad; 
    private int edicion;
    private String genero;
    private int paginas;
    private Long isbn;

    public Libro(String autor, String titulo, String fecha, String editorial, String pais, String ciudad, int edicion, String genero, int paginas, Long isbn) {
        super(autor, titulo, fecha, editorial);
        this.pais = pais;
        this.ciudad = ciudad;
        this.edicion = edicion;
        this.genero = genero;
        this.paginas = paginas;
        this.isbn = isbn;
    }

    public String getPais(){
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getEdicion() {
        return edicion;
    }

    public String getGenero() {
        return genero;
    }

    public int getPaginas() {
        return paginas;
    }

    public Long getIsbn() {
        return isbn;
    }
}
