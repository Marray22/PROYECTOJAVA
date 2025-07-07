/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sistemabiblioteca;
import javax.swing.JOptionPane; 
/* Estudiantes:
   Valeria Ledezma
   Amanda Calderón 
   Michael Muñoz

 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //esta parte de los arreglos me esta generando error, sin embargo, pude investigar que es como algo realcionado a carpetas, pero viendo la clase libro y socio esta en un .javax
        
     /* Libro[] libros = DatosIniciales.generarLibros(50);
        Socio[] socios = DatosIniciales.generarSocios(30);
        MenuBiblioteca menuBiblioteca = new MenuBiblioteca(libros, socios);*/

        
        
        String[] opciones = {"Biblioteca", "Préstamos", "Catálogo", "Socios", "Reportes", "Salir"};

        while (true) {
            int seleccion = JOptionPane.showOptionDialog(
                null, 
                "Seleccione una opción:", 
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (seleccion == 0) {
                //menuBiblioteca.mostrarInformacionBiblioteca();
                // Llama al método de las clase menuBiblioteca con un mostrar
            } else if (seleccion == 1) {
                
                // Llama al método de las clase menuprestamos con un mostrar
            } else if (seleccion == 2) {
                
                // Llama al método de las clase menucatalogo con un mostrar
            } else if (seleccion == 3) {
               
                // Llama al método de las clase menusocio con un mostrar
            } else if (seleccion == 4) {
                
                // Llama al método de las clase menuReporte con un mostrar
            } else if (seleccion == 5 || seleccion == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema. ¡Hasta luego!");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
    }
}