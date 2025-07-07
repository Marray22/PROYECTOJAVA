
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sistemabiblioteca;
import java.util.Random;

public class DatosIniciales {
    
      // listas para generar datos aleatorios
    private static String[] titulos = {"Cien Años de Soledad", "El Principito", "1984", "Don Quijote", "La Odisea"};
    private static String[] autores = {"Gabriel García Márquez", "Antoine de Saint-Exupéry", "George Orwell", "Miguel de Cervantes", "Homero"};
    private static String[] editoriales = {"Editorial Alfa", "Editorial Beta", "Editorial Gamma"};
    private static String[] nombres = {"Juan Pérez", "María López", "Carlos Sánchez", "Ana Gómez", "Luis Rodríguez"};

      //nuevo obje para datos aleatorios
    private static Random random = new Random();

    // Método para generar libros
    public static Libro[] generarLibros(int cantidad) {
        if (cantidad > 50) {
            cantidad = 50; 
        }

        Libro[] libros = new Libro[cantidad];

        for (int i = 0; i < cantidad; i++) {
            String titulo = titulos[random.nextInt(titulos.length)];
            String autor = autores[random.nextInt(autores.length)];
            String editorial = editoriales[random.nextInt(editoriales.length)];

            // elige genero y estado aleatorio
           Genero genero = Genero.values()[random.nextInt(Genero.values().length)];
           
           EstadoLibro estado;
            int numeroAleatorio = random.nextInt(2); // genera 0 o 1

            if (numeroAleatorio == 0) {
                estado = EstadoLibro.DISPONIBLE;
            } else {
                estado = EstadoLibro.EN_REPARACION;
            }

            // año entre 1950 y 2023
            int anio = 0;
            int numero = random.nextInt(5) + 1;  

            if (numero == 1) {
                anio = 1950;
            }
            if (numero == 2) {
                anio = 1970;
            }
            if (numero == 3) {
                anio = 1990;
            }
            if (numero == 4) {
                anio = 2010;
            }
            if (numero == 5) {
                anio = 2023;
            }

            libros[i] = new Libro(i + 1, titulo, autor, genero, editorial, anio, estado);
        }

        return libros;
    }

    // Método para generar socios
    public static Socio[] generarSocios(int cantidad) {
        if (cantidad > 30) {
            cantidad = 30; 
        }

        Socio[] socios = new Socio[cantidad];

        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            Socio nuevo = new Socio(nombre); // El constructor ya genera ID y fecha
            
            // los primeros 3 son morosos con multa de 5000,y el resto activo
            if (i < 3) {
                nuevo.setEstadoSocio(EstadoSocio.MOROSO);
                nuevo.setMultasAcumuladas(5000.0);
            } else {
                nuevo.setEstadoSocio(EstadoSocio.ACTIVO);
                nuevo.setMultasAcumuladas(0.0);
            }

            socios[i] = nuevo;
        }

        return socios;
    }
}