package com.examplejni;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.examplejni.menus.MenuPrincipal;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

/* 
 * @autor Fernando
*/

public class Main {
    public static void main(String[] args) {
        try {
            FlatMacLightLaf.setup(); //Creacion de flatlaf para poder aplicar estilos al JFrame
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha sucedido un error al cargar la interfaz", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(()->{
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
    }
}