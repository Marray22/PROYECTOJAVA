
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sistemabiblioteca;
import java.util.Random;

public class DatosIniciales {
    
      // Listas para generar datos aleatorios
    private static String[] titulos = {"Cien Años de Soledad", "El Principito", "1984", "Don Quijote", "La Odisea"};
    private static String[] autores = {"Gabriel García Márquez", "Antoine de Saint-Exupéry", "George Orwell", "Miguel de Cervantes", "Homero"};
    private static String[] editoriales = {"Editorial Alfa", "Editorial Beta", "Editorial Gamma"};
    private static String[] nombres = {"Juan Pérez", "María López", "Carlos Sánchez", "Ana Gómez", "Luis Rodríguez"};

    private static Random random = new Random();

    // Método para generar libros
    public static Libro[] generarLibros(int cantidad) {
        if (cantidad > 50) {
            cantidad = 50; // Máximo permitido
        }

        Libro[] libros = new Libro[cantidad];

        for (int i = 0; i < cantidad; i++) {
            String titulo = titulos[random.nextInt(titulos.length)];
            String autor = autores[random.nextInt(autores.length)];
            String editorial = editoriales[random.nextInt(editoriales.length)];

            // Elegir género y estado de forma aleatoria
            Libro.Genero genero = Libro.Genero.values()[random.nextInt(Libro.Genero.values().length)];
            Libro.EstadoLibro estado = random.nextBoolean() ? Libro.EstadoLibro.DISPONIBLE : Libro.EstadoLibro.EN_REPARACION;

            // Año entre 1950 y 2023
            int anio = 1950 + random.nextInt(2024 - 1950);

            libros[i] = new Libro(i + 1, titulo, autor, genero, editorial, anio, estado);
        }

        return libros;
    }

    // Método para generar socios
    public static Socio[] generarSocios(int cantidad) {
        if (cantidad > 30) {
            cantidad = 30; // Máximo permitido
        }

        Socio[] socios = new Socio[cantidad];

        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            Socio nuevo = new Socio(nombre); // El constructor ya genera ID y fecha

            // Estado aleatorio: 80% ACTIVO, 20% MOROSO
            int numero = random.nextInt(10);
            if (numero < 8) {
                nuevo.setEstadoSocio(EstadoSocio.ACTIVO);
                nuevo.setMultasAcumuladas(0.0);
            } else {
                nuevo.setEstadoSocio(EstadoSocio.MOROSO);
                double multa = 1000 + random.nextDouble() * 9000; // Entre 1000 y 10000
                nuevo.setMultasAcumuladas(multa);
            }

            socios[i] = nuevo;
        }

        return socios;
    }
}