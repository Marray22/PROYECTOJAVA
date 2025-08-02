package sistemabiblioteca;

import javax.swing.*;
import java.util.ArrayList;
// Inicia la clase Menu Catalogo. contiene los metodos para gestionar el catalogo
public class MenuCatalogo {
    private static ArrayList<Libro> libros = new ArrayList<>(); // Almacena todos los libros registrados en el sistema
    private static int contadorIsbn = 1; // Contador que se usa para generar un codigo ISBN automatico
    private static final int LIMITE_LIBROS = 50; // Limite aximo de libros permitidos 
// Metodo principal que muestra el menu de opciones del catalogo
    public static void mostrarMenu() {
        String[] opciones = { //Arreglo de opciones que se mostraran en el cuadro de dialogo
            "Mostrar Catálogo Completo",
            "Agregar Nuevo Libro",
            "Editar Información de Libro",
            "Cambiar Estado de Libro",
            "Salir"
        };
// Variable que guarda la opcion elegida por el usuario
        int opcion;
        // Muestra el menu con botones y guarda la opcion seleccionada
        do {
            opcion = JOptionPane.showOptionDialog(null, "Menú de Catálogo", "Catálogo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
          // Ejecuta el metodo correspondiente según la opción elegida
            switch (opcion) {
                case 0 -> mostrarCatalogo();
                case 1 -> agregarLibro();
                case 2 -> editarLibro();
                case 3 -> cambiarEstadoLibro();
            }
       // Repite el menú mientras no se seleccione "Salir"
        } while (opcion != 4);
    }
// Metodo privado y estaico. Muestra todos los libros de la consola
    private static void mostrarCatalogo() {
        //Si la lista esta vacia, muestra un mensaje por consola. Si no está vacía, entra en el else para mostrar libros
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el catálogo.");
        } else {
            //Recorre cada objeto (Libro) dentro de la lista Libros
            for (Libro libro : libros) {
                System.out.println(libro);
                System.out.println("--------------------------------");
            }
        }
        //Muestra una ventana emergente informando al usuario que los libros se imprimieron en la consola
        JOptionPane.showMessageDialog(null, "Catálogo completo mostrado en consola.");
    }
// Metodo privado y estatico que permite registrar un nuevo libro en el sistema
    private static void agregarLibro() {
        // Verifica si ya alcanzo el limite de 50 libros
        if (libros.size() >= LIMITE_LIBROS) {
            JOptionPane.showMessageDialog(null, "Límite máximo de libros (50) alcanzado.");
            return;
        }
// Genera un ISBN automatico con el formato, luego incrementa el contador para el siguiente libro
        String isbn = "LIB-" + contadorIsbn++;
// Pide al usuario ingresar el titulo del libro, si cancela o cierra el cuadro se sale del metodo
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:");
        if (titulo == null) return;
// Pide el nombre del autor y verifica si el usuario canceló
        String autor = JOptionPane.showInputDialog("Ingrese el autor del libro:");
        if (autor == null) return;
//  Pide el nombre de la editorial del libro
        String editorial = JOptionPane.showInputDialog("Ingrese la editorial:");
        if (editorial == null) return;
// Pide el año de publicación, si se cancela se detiene el metodo
        int anio = 0;
        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese el año de publicación:");
            if (input == null) return;
// Intenta convertir el año a entero, si es válido o es menor o igual a 0, lanza error y entra al catch
            try {
                anio = Integer.parseInt(input);
                if (anio <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
// Muestra mensaje error, si se elije no, se cancela el ingreso del libro,si elije si vuelve a pedir el año
                int retry = JOptionPane.showConfirmDialog(null,
                        "Año de publicación inválido. Debe ser un número mayor a 0.\n¿Reintentar?",
                        "Error", JOptionPane.YES_NO_OPTION);
                if (retry != JOptionPane.YES_OPTION) return;
            }
        }
// Llama al metodo seleccionar genero, para elegir el genero del libro, si cancela termina el metodo
        Genero genero = seleccionarGenero();
        if (genero == null) return;
// Crea el nuevo objeto libro con los datos recopilados
        Libro nuevo = new Libro(isbn, titulo, autor, editorial, anio, genero);
        libros.add(nuevo); // Agrega el nuevo libro a la lista libros
// Muestra un mensaje confirmando que el libro fue agregado y muestra la info del libro
        JOptionPane.showMessageDialog(null, "Libro agregado correctamente:\n" + nuevo);
    }
// Metodo privado y estatico que permite al usuario elegir un genero literario para un libro
    private static Genero seleccionarGenero() {
        // Obtiene los valores definidos en el enum
        Genero[] valores = Genero.values();
        //Crea un arreglo de String que tendrá los nombres de los generos, con e mismo tamaño que el array
        String[] nombres = new String[valores.length];
        // Recoje todos los valores del enum y guarda su nombre, como texto
        for (int i = 0; i < valores.length; i++) nombres[i] = valores[i].name();
// Muestra un cuadro con botones que permiten seleccionar el genero del libro, los botones se optienen del enum y guarda la opcion seleccionada
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el género:",
                "Género del libro", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, nombres, nombres[0]);
// Si el usuario selleccionó un botón valido, devuelve el valor correspondiente del enum, se se cansela se devuelve null
        return (seleccion >= 0) ? valores[seleccion] : null;
    }
// Metodo privado y estatico, su funcion es buscar un libro especifico en la lista por medio de su codigo
    private static Libro buscarLibroPorIsbn(String isbn) {
        // Recorre cada objeto dentro de la lista libros
        for (Libro libro : libros) {
            // Compara el isbn de cada libro con el ISBN buscado
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        // Sitermina de recorrer la lista y no encontro coincidencias, devuelve null
        return null;
    }
// Metodo privado y estatico que permite modificar los datos de un libro existente buscandolo por su ISBN
    private static void editarLibro() {
        // Solicita al usuario que ingrese el ISBn del libro que quiere editar
        String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro a editar:");
        if (isbn == null) return;
// Llama al metodo anterior, para buscar el libro en la lista 
        Libro libro = buscarLibroPorIsbn(isbn);
        // Si no se encuentra el libro, muestra un mensaje informando al usuario, da opcion con otro ISBN "YES"
        // Llama al metodo de nuevo, si elije no finaliza 
        if (libro == null) {
            int opcion = JOptionPane.showConfirmDialog(null,
                "El libro con ISBN '" + isbn + "' no existe.\n¿Desea ingresar otro ISBN?",
                "No encontrado", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) editarLibro();
            return;
        }
// Crea un arreglo con los campos que el ususario puede editar, también delcara la variable para saber que campo elegió
        String[] campos = {"Título", "Autor", "Editorial", "Año Pub.", "Género", "Atrás"};
        int opcion;
//  Muestra opciones para seleccionar que quiere modificar, también muestra los datos actuales del libro
        do {
            opcion = JOptionPane.showOptionDialog(null,
                "Editar libro:\n" + libro, "Editar Libro", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, campos, campos[0]);
// Si se selecciona titulo, pide un nuevo titulo, si no es cancelado se actualiza el campo usando setTitulo
            switch (opcion) {
                case 0 -> {
                    String nuevo = JOptionPane.showInputDialog("Nuevo título:");
                    if (nuevo != null) libro.setTitulo(nuevo);
                }
// Para modificar el autor 
                case 1 -> {
                    String nuevo = JOptionPane.showInputDialog("Nuevo autor:");
                    if (nuevo != null) libro.setAutor(nuevo);
                }
// Para cambiar la editorial del libro
                case 2 -> {
                    String nuevo = JOptionPane.showInputDialog("Nueva editorial:");
                    if (nuevo != null) libro.setEditorial(nuevo);
                }
// Muestra un cuadro en el que se ingresa un nuevo año, si se cancela se sale del ciclo 
                case 3 -> {
                    while (true) {
                        String nuevo = JOptionPane.showInputDialog("Nuevo año de publicación:");
                        if (nuevo == null) break;
// Intenta convertir el texto a entero, si es valido y mayor a 0 actualiza el año, si no muestra un mensaje con error
                        try {
                            int nuevoAnio = Integer.parseInt(nuevo);
                            if (nuevoAnio > 0) {
                                libro.setAnioPublicacion(nuevoAnio);
                                break;
                            }
                        } catch (NumberFormatException ignored) {}
                        JOptionPane.showMessageDialog(null, "Debe ser un número mayor a 0.");
                    }
                }
// Llama nuevamente a seleccionarGenero y actualiza el genero si el usuario selecciona uno
                case 4 -> {
                    Genero genero = seleccionarGenero();
                    if (genero != null) libro.setGenero(genero);
                }
            }
// El ciclo se repite mientras el usuario no presione atras
        } while (opcion != 5);
    }
// Permite al usuario cambiar el estado de un libro
    private static void cambiarEstadoLibro() {
        // Pide al usuario el ISBN del libro al que quiere cambiarle el estado
        String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro:");
        if (isbn == null) return;
// Busca el libro en la lista usando ISBN
        Libro libro = buscarLibroPorIsbn(isbn);
// Si el nombre no se encuentra, muestra un mensaje de error y sale del metodo
        if (libro == null) {
            JOptionPane.showMessageDialog(null, "El libro con ISBN '" + isbn + "' no existe.");
            return;
        }
// Muestra un mensaje indicando que debe devolverse primero
        if (libro.getEstadoLibro() == EstadoLibro.PRESTADO) {
            JOptionPane.showMessageDialog(null,
                "El libro '" + libro.getTitulo() + "' (" + isbn + ") está PRESTADO.\nDebe ser devuelto primero.");
            return;
        }
// Crea un arreglo con los estados a los que sí se puede cambiar
        EstadoLibro[] estados = {EstadoLibro.DISPONIBLE, EstadoLibro.EN_REPARACION, EstadoLibro.EXTRAVIADO};
        //Arreglo de nombres que se mostrara como botones en la ventana 
        String[] opciones = {"DISPONIBLE", "EN_REPARACION", "EXTRAVIADO", "Cancelar"};
        // Muestra un cuadro con las opciones a elejir por el usuario
        int seleccion = JOptionPane.showOptionDialog(null, "Estado actual: " + libro.getEstadoLibro() +
                "\nSeleccione nuevo estado:", "Cambiar Estado",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
// Si eligio una opcion valida, actualiza el estado del libro y muestra un mensaje confirmando el cambio
        if (seleccion >= 0 && seleccion < 3) {
            libro.setEstadoLibro(estados[seleccion]);
            JOptionPane.showMessageDialog(null,
                "Estado del libro '" + libro.getTitulo() + "' (" + isbn + ") cambiado a " + estados[seleccion]);
        }
    }
}


