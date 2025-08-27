package com.examplejni.menus;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.time.LocalDate;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.examplejni.biblioteca.Libro;
import com.examplejni.lib_native.cargarLibNative;
import com.examplejni.utilidades.Validacion;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class MenuLibro extends JFrame {
    //Atributos del elemento JFrame
    private JTextField txt_autor;
    private JTextField txt_titulo;
    private DatePicker datePicker; //Para escoger la fecha
    private JTextField txt_editorial;
    private JComboBox<String> cmb_pais;
    private DefaultComboBoxModel<String> cmbModel_ciudad; //El modelo de datos del ComboBox para ciudad. Lo utilizo para poder cambiar los datos en tiempo de ejecución
    private JComboBox<String> cmb_ciudad;
    private JTextField txt_edicion;
    private JComboBox<String> cmb_genero;
    private JTextField txt_pag;
    private JFormattedTextField txt_isbn;

    private TreeMap<String, String[]> tMapPaises;

    //Método constructor
    public MenuLibro(){
        super("Nuevo libro");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(450, 650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //JPanel principal que contendrá los elementos del formulario
        JPanel panel_img = crearPanelImg();
        JPanel panel_libro = crearPanelLibro();
        JPanel panel_btn = crearPanelBoton();

        this.add(panel_img, BorderLayout.NORTH);
        this.add(panel_libro, BorderLayout.CENTER);
        this.add(panel_btn, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void registrarNuevoLibro(){
        Libro libro = crearLibro(); 
        cargarLibNative.datosLibro(libro);  //Llamada al método para acceder a la libreria nativa

        JOptionPane.showMessageDialog(null, "Libro registrado con exito !", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel crearPanelImg(){
        JPanel panel = new JPanel();
            ImageIcon a = new ImageIcon("src/main/resources/images/glasses-1052010_640.jpg"); //Foto tomada de Pixabay, por el autor: DariuszSankowski
            JLabel img = new JLabel(a);
            img.setSize(450, 196);
            panel.add(img);

        return panel;
    }

    private JPanel crearPanelLibro(){
        JPanel panel = new JPanel(new GridLayout(11, 2, 5, 5));
            //Autor
            panel.add(new JLabel("Autor: "));
            txt_autor = new JTextField(20);
            panel.add(txt_autor);

            //Titulo del libro
            panel.add(new JLabel("Nombre del libro: "));
            txt_titulo = new JTextField(20);
            panel.add(txt_titulo);

            //Fecha de publicacion
            panel.add(new JLabel("Fecha de publicacion: "));
            DatePickerSettings datePickerSettings = new DatePickerSettings(); //Usé datePickerSettings para poder aplicar el mismo formato de fecha que el que se guardará como String
            datePickerSettings.setFormatForDatesCommonEra("dd/MM/yyyy"); //.setFormatForDatesCommonEra() permite añadir un formato personalizado para la fecha
            datePickerSettings.setAllowEmptyDates(false);

            datePicker = new DatePicker(datePickerSettings); //DatePicker corresponde al elemento que contiene el calendario y el campo para escoger una fecha
            datePicker.setDate(LocalDate.now()); //Se establece la fecha actual con LocalDate.now() al inicializarse
            panel.add(datePicker);

            //Editorial
            panel.add(new JLabel("Editorial: "));
            txt_editorial = new JTextField();
            panel.add(txt_editorial);

            //TreeMap relacionado a los paises y ciudades
            String[] paises = {"España", "Italia", "Francia", "Honduras", "El Salvador", "Argentina", "Colombia"};

            String[] ciudades_esp = { "Madrid", "Barcelona", "Valencia" };
            String[] ciudades_ita = { "Milan", "Florencia", "Turin" };
            String[] ciudades_fra = { "Paris", "Lyon", "Marsella" };
            String[] ciudades_hn = { "Tegucigalpa", "San Pedro Sula", "Comayagua" };
            String[] ciudades_slv = { "San Salvador", "San Vicente" };
            String[] ciudades_arg = { "Buenos Aires", "Rosario", "La Plata" };
            String[] ciudades_col = { "Bogota", "Medellin", "Barranquilla" };
            String[][] ciudades = {ciudades_esp, ciudades_ita, ciudades_fra, ciudades_hn, ciudades_slv, ciudades_arg, ciudades_col};

            tMapPaises = Validacion.crearTreeMap(paises, ciudades); //Creación del TreeMap usando los arrays como parametros

            //Paises
            panel.add(new JLabel("Pais: "));   
            cmb_pais = new JComboBox<>(tMapPaises.keySet().toArray(new String[0])); //El método .keySet() permite obtener las keys pertenecientes al TreeMap. 
            panel.add(cmb_pais);                                                  //El metodo .toArray() guarda las keys en un nuevo arreglo

            //Ciudades
            panel.add(new JLabel("Ciudad: "));
            //Modelo de datos para el JComboBox de las tMapPaises inicializado con las tMapPaises del pais por defecto
            cmbModel_ciudad = new DefaultComboBoxModel<>(tMapPaises.get(cmb_pais.getSelectedItem())); //El metodo .get() en un Map permite obtener los valores relacionados a una "key" 
            cmb_ciudad = new JComboBox<>(cmbModel_ciudad);                                          //Siendo cmb_pais.getSelectecItem() el primer item que aparece en el JComboBox
            panel.add(cmb_ciudad);

            cmb_pais.addActionListener(e->{
                actualizarCiudades(); //Este metodo permite actualizar las ciudades cada vez que suceda un cambio en el Listener de la lista desplegable de ciudades
            });

            //Edicion
            panel.add(new JLabel("Edicion: "));
            txt_edicion = new JTextField();
            panel.add(txt_edicion);

            //Genero del libro
            panel.add(new JLabel("Genero literario: "));
            String[] generos = {"Novela", "Drama", "Romance", "Ficcion", "Aventura", "Historia", "Terror", "Contemporaneo", "Biografia", "Cocina", "Salud y deporte", "Informativo"};
            cmb_genero = new JComboBox<>(generos);
            panel.add(cmb_genero);

            //Cantidad de páginas
            panel.add(new JLabel("Cantidad de paginas"));
            txt_pag = new JTextField(20);
            panel.add(txt_pag);

            //ISBN (con maskformatter para establecer el formato del código)
            panel.add(new JLabel("ISBN: "));
            txt_isbn = Validacion.formattedFieldValidado(); //Se crea el formattedField con el formato de ISBN, o sin formato en caso que suceda un error
            panel.add(txt_isbn);

        return panel;
    }

    private JPanel crearPanelBoton(){
        JPanel panel = new JPanel();
            JButton btn_reg = new JButton("Confirmar");
            btn_reg.addActionListener(e->{
                registrarNuevoLibro(); //Acción para que el botón registre un nuevo libro 
            });
            panel.add(btn_reg);

        return panel;
    }

    private Libro crearLibro(){
        //Asignación de los datos de tipo String de los JTextField
        String autor = txt_autor.getText();
        String titulo = txt_titulo.getText();
        String editorial = txt_editorial.getText();

        //Se almacena la fecha con su formato especifico como una variable de tipo String
        //Tuve que realizar una validación != null porque si datePicker es vacio, siempre
        String fecha = Validacion.fechaValidada(datePicker);

        //Asignación y casteo de los datos obtenidos de los JComboBox
        String pais = (String) cmb_pais.getSelectedItem();
        String ciudad = (String) cmb_ciudad.getSelectedItem();
        String genero = (String) cmb_genero.getSelectedItem();  

        //Asignación de los datos numericos tipo int obtenidos de los JTextField
        int edicion = Validacion.numValidado(txt_edicion.getText()); //(txt_edicion.getText() es el String obtenido del campo de texto del que se desea 
        int paginas = Validacion.numValidado(txt_pag.getText());

        //Asignación del dato tipo int del JFormattedField
        Object obj_isbn = txt_isbn.getValue(); //Un JFormattedField siempre retorna un Object, por ejemplo (123-42-56789) incluyendo los guiones
        String str_isbn = obj_isbn.toString().replace("-", ""); //Al usar replaceAll se reemplazan los guiones del String, por "", para que no afecte la conversión a int

        Long isbn = Validacion.numT(str_isbn, Long.class);
    
        //Método constructor para el objeto Libro
        Libro libro = new Libro(autor, titulo, fecha, editorial, pais, ciudad, edicion, genero, paginas, isbn);

        //Llamada la función para limpiar los campos de texto después de un registro    
        limpiarCampos();
        
        return libro;
    }

    private void actualizarCiudades(){
        Object obj_pais = cmb_pais.getSelectedItem();

        //Se evalua si el pais seleccionado en el ComboBox (de tipo Object) es una instancia de String
        String sel_pais = (String) obj_pais; //se realiza un casteo a String del object

        cmbModel_ciudad.removeAllElements(); //Se eliminan los elementos en el modelo
        if (sel_pais!=null && !sel_pais.trim().isEmpty()) {
            String[] arr_ciudades = tMapPaises.get(sel_pais); //Se llama al HasMap ciudades y se le pasa como parametro la "key", es decir el pais
            for (String ciudad : arr_ciudades) {
                cmbModel_ciudad.addElement(ciudad); //Se añaden los elementos nuevos al modelo
            }
        }   
    }

    private void limpiarCampos(){
        txt_autor.setText("");
        txt_titulo.setText("");
        datePicker.setDate(LocalDate.now());
        txt_editorial.setText("");
        cmb_pais.setSelectedIndex(0);
        cmb_ciudad.setSelectedIndex(0);
        txt_edicion.setText("");
        cmb_genero.setSelectedIndex(0);
        txt_pag.setText("");
        txt_isbn.setText("");
    }
}
