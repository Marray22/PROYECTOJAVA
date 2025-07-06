/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabiblioteca;
/**
 *
 * @author Valeria
 */
public class MenuBiblioteca {
    
    private Libro[] libros;
    private Socio[] socios;

    // Constructor para recibir los arreglos
    public MenuBiblioteca(Libro[] libros, Socio[] socios) {
        this.libros = libros;
        this.socios = socios;
    }

    public void mostrarInformacionBiblioteca() {
        System.out.println("------SECCIÓN LIBROS ------");
        int cantidadMostrar = Math.min(libros.length, 10);

        for (int i = 0; i < cantidadMostrar; i++) {
            Libro libro = libros[i];
            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Género: " + libro.getGenero());
            System.out.println("Estado: " + libro.getEstadoLibro());
            System.out.println("---------------------------");
        }

        System.out.println("---- RESUMEN DE SOCIOS ------");

        int totalSocios = socios.length;
        int activos = 0;
        int morosos = 0;

        for (int i = 0; i < totalSocios; i++) {
            if (socios[i].getEstadoSocio() == EstadoSocio.ACTIVO) {
                activos++;
            } else if (socios[i].getEstadoSocio() == EstadoSocio.MOROSO) {
                morosos++;
            }

        System.out.println("Total Socios Registrados: " + totalSocios);
        System.out.println("Socios Activos: " + activos);
        System.out.println("Socios Morosos: " + morosos);
    }
    }
}
    

