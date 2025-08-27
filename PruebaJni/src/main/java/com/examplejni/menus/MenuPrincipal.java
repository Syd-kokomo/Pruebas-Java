package com.examplejni.menus;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;

public class MenuPrincipal extends JFrame { // JFrame ya usa BorderLayout por defecto
    //Atributos
    private JLabel titulo;

    //Método constructor
    public MenuPrincipal(){
        super("Biblioteca");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(670, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel panel_titulo = crearPanelTitulo();
        JPanel panel_img = crearPanelImg();
        JMenuBar menuBar = crearMenuBar();

        this.setJMenuBar(menuBar); 
        this.add(panel_titulo, BorderLayout.NORTH);
        this.add(panel_img, BorderLayout.CENTER);
    }

    private JPanel crearPanelTitulo(){
        JPanel panel = new JPanel();
            Font calibri = new Font("Helvetica", Font.BOLD, 20); //Fuente para usar en el título
            titulo = new JLabel("Coleccion general de Biblioteca");
            titulo.setFont(calibri);

            panel.add(titulo);

        return panel;
    }

    private JPanel crearPanelImg(){
        JPanel panel = new JPanel();
            ImageIcon imagen = new ImageIcon("src/main/resources/images/bookcases-1869616_640.jpg"); //Image via Pixabay, by autor: Pexels
            JLabel img = new JLabel(imagen);
            panel.add(img);
        
        return panel;
    }

    private JMenuBar crearMenuBar(){
        JMenuBar menuBar = new JMenuBar();
            JMenu menu_a = new JMenu("Archivo");

            JMenuItem itemLibro = new JMenuItem("Nuevo libro");
            itemLibro.addActionListener(e->{
                new MenuLibro(); //Se crea la instancia del constructor del formulario para registrar un libro
            });
            menu_a.add(itemLibro);

            JMenuItem itemRevista = new JMenuItem("Nueva revista");
            itemRevista.addActionListener(e->{
                JOptionPane.showMessageDialog(null, "No lo he hecho todavia"); //No lo he hecho
            });
            menu_a.add(itemRevista);

            JMenuItem itemSalir = new JMenuItem("Cerrar");
            itemSalir.addActionListener(e->{
                this.dispose();
            });
            menu_a.add(itemSalir);

            //Se añade el JMenu y sus items al JMenuBar
            menuBar.add(menu_a);

        return menuBar;
    }
}
