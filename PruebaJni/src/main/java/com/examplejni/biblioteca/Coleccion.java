package com.examplejni.biblioteca;

public class Coleccion {
    //Atributos
    private String autor;
    private String titulo;
    private String fecha;
    private String editorial;
    
    public Coleccion(String autor, String titulo, String fecha, String editorial) {
        this.autor = autor;
        this.titulo = titulo;
        this.fecha = fecha;
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEditorial() {
        return editorial;
    }

    @Override
    public String toString() {
        return "Coleccion [autor=" + autor + ", titulo=" + titulo + ", fecha=" + fecha + "]";
    }

    
}
