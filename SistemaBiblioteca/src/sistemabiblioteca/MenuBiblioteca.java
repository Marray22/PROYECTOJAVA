/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabiblioteca;
import javax.swing.JOptionPane;
/**
 *
 * @author Valeria
 */
public class MenuBiblioteca {
    
    private Libro[] libros;
    private Socio[] socios;

    public MenuBiblioteca(Libro[] libros, Socio[] socios) {
        this.libros = libros;
        this.socios = socios;
    }

    public void mostrarInformacionBiblioteca() {
  
    System.out.println("SECCIÓN LIBROS");

    int cantidadMostrar = Math.min(libros.length, 10);

     System.out.println("| ISBN       | Título                        | Autor                    | Género         | Estado       |");
 
    // tabla donde se imprime en formato conforme a los espacios
    for (int i = 0; i < cantidadMostrar; i++) {
        Libro libro = libros[i];
        System.out.printf("| %-10s | %-30s | %-25s | %-15s | %-12s |\n",
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getGenero(),
                libro.getEstadoLibro());
    }

    System.out.println("RESUMEN DE SOCIOS");

    int totalSocios = socios.length;
    int activos = 0;
    int morosos = 0;

    for (int i = 0; i < totalSocios; i++) {
        EstadoSocio estado = socios[i].getEstadoSocio();
        if (estado == EstadoSocio.ACTIVO) {
            activos++;
        } else if (estado == EstadoSocio.MOROSO) {
            morosos++;
        }
    }

    // resumen
    System.out.println("Total Socios Registrados son: " + totalSocios);
    System.out.println("Socios Activos: " + activos);
    System.out.println("Socios Morosos: " + morosos);

    // mensaje de confirmación
    JOptionPane.showMessageDialog(null, "Información de Biblioteca mostrada en consola.");
}
}
