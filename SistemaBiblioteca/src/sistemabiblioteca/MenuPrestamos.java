


package sistemabiblioteca;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//Atributos
public class MenuPrestamos{
    private Prestamo[] prestamos;
    private Libro[] libros;
    private Socio [] socios;
    private static int contadorPrestamos = 1001;
 // Constructor
public MenuPrestamos(Prestamo[] prestamos, Libro[] libros, Socio[] Socios){
    this.prestamos = prestamos; 
    this.libros = libros;
    this.socios = socios;
}
//Define las opciones del menu 
public void mostrarMenu(){
    String[] opciones = {"Registar prestamo", "Registar devolucion", "Consultar prestamo por ID", "Volver"};
    //muestra el menu
    while (true){
        int opcion = JOptionPane.showOptionDialog(null, "Menu de prestamos", "Prestamos", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,opciones,opciones[0]);
        //Segun la elección muestra un metodo
        switch (opcion){
            case 0 -> registrarPrestamo();
            case 1 -> registrarDevolucion();
            case 2 -> consultarPrestamoPorID();
            case 3, JOptionPane.CLOSED_OPTION -> {
            return;
        }
            default -> JOptionPane.showMessageDialog(null, "Opcion no valida");
    }
}
} //Cuenta los prestamos actuales
private void registrarPrestamo(){
    int cantidadActual = 0;
    for (Prestamo p : prestamos){
        if (p != null)cantidadActual++;
    } //verifica el espacio disponible
   if (cantidadActual >= prestamos.length){
       JOptionPane.showMessageDialog(null, "Limite maximo de prestamos");
       return;
   }
   //Bysca el socio
        Socio socio;
        while (true) {
            String id = JOptionPane.showInputDialog("Ingrese ID del socio");
            if (id == null) return;
// Verifica si el socio existe
            socio = buscarSocioPorId(id);
            if (socio == null) {
                int opcion = JOptionPane.showConfirmDialog(null, "El socio con ID de: " + id + " no existe.\n\u00bfDesea intentar de nuevo?", "Error",
                        JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.NO_OPTION) return;
            } else {
                break;
            }
        }
//verifica si el socio esta activo
        if (socio.getEstadoSocio() != EstadoSocio.ACTIVO) {
            JOptionPane.showMessageDialog(null, "El socio " + socio.getNombreCompleto() + " No está activo (Estado: " + socio.getEstadoSocio() + "). No puede realizar prestamos.");
            return;
        }
//Busca si un libro es valido
        Libro libro;
        while (true) {
            String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro: ");
            if (isbn == null) return;

            libro = buscarLibroPorIsbn(isbn);
            if (libro == null) {
                int opcion = JOptionPane.showConfirmDialog(null,
                        "El libro con ISBN " + isbn + " no existe.\n Quiere intentar de nuevo?", "Error",
                        JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.NO_OPTION) return;
            } else if (libro.getEstadoLibro() != EstadoLibro.DISPONIBLE) {
                JOptionPane.showMessageDialog(null, "El libro " + libro.getTitulo() + " no se encuentra disponible (Estado: " + libro.getEstadoLibro() + ").");
                return;
            } else {
                break;
            }
        }
//Crea y guarda los prestamos 
        Prestamo prestamo = new Prestamo(contadorPrestamos++, socio, libro);
        libro.setEstadoLibro(EstadoLibro.PRESTADO);
        socio.setCantidadLibrosPrestadosActual(socio.getCantidadLibrosPrestadosActual() + 1);

        for (int i = 0; i < prestamos.length; i++) {
            if (prestamos[i] == null) {
                prestamos[i] = prestamo;
                break;
            }
        }
//Muestra todos los detalles que tiene el prestamo
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null,
                "Prestamo registrado de manera exitosa: \n" +
                        "ID prestamo: " + prestamo.getIdPrestamo() + "\n" +
                        "Socio: " + socio.getNombreCompleto() + " (" + socio.getIdSocio() + ")\n" +
                        "Libro: " + libro.getTitulo() + " (" + libro.getIsbn() + ")\n" +
                        "Fecha prestamo: " + prestamo.getFechaPrestamo() + "\n" +
                        "Devolucion estimada: " + prestamo.getFechaDevolucionEstimada() + "\n" +
                        "Estado: Activo");
    }
//Solicita el id del prestamo
    private void registrarDevolucion() {
        String idStr = JOptionPane.showInputDialog("Ingrese el id del prestamo: ");
        if (idStr == null) return;
//Verifica si el prestamo existe
        Prestamo p = buscarPrestamoPorId(idStr);
        if (p == null) {
            JOptionPane.showMessageDialog(null, "El prestamo con Id " + idStr + " no existe.");
            return;
        }
     //Verifica si el prestamo esta activo
        if (p.getEstadoPrestamo() != Prestamo.EstadoPrestamo.ACTIVO) {
            JOptionPane.showMessageDialog(null, "El prestamo " + idStr + " no esta activo (Estado: " + p.getEstadoPrestamo() + "). No se puede devolver.");
            return;
        }
//Muesta la fecha en la que se devuelve el prestamo y calcula la multa
        LocalDate fechaDevolucion = LocalDate.now();
        p.setFechaDevolucionReal(fechaDevolucion);
//Hace un calculo de los dias de retraso y genera la multa, solo si aplica
        long diasRetraso = java.time.temporal.ChronoUnit.DAYS.between(p.getFechaDevolucionEstimada(), fechaDevolucion);

        if (diasRetraso > 0) {// actualiza el estado del prestamo, si fue devuelto a tiempo o con retraso
                              // tambien actualiza los datos de libro y socio
            double multa = diasRetraso * 200;
            p.setMultaGenerada(multa);
            p.setEstadoPrestamo(Prestamo.EstadoPrestamo.DEVUELTO_CON_RETRASO);
            p.getSocio().setMultasAcumuladas(p.getSocio().getMultasAcumuladas() + multa);
            JOptionPane.showMessageDialog(null, "El libro fue devuelto con: " + diasRetraso + " dias de retraso.\nMulta generada: " + multa);
        } else {
            p.setEstadoPrestamo(Prestamo.EstadoPrestamo.DEVUELTO_A_TIEMPO);
            JOptionPane.showMessageDialog(null, "Devolucion realizada a tiempo.");
        }
        
     p.getLibro().setEstadoLibro(EstadoLibro.DISPONIBLE);
     p.getSocio().setCantidadLibrosPrestadosActual(p.getSocio().getCantidadLibrosPrestadosActual() - 1);
    }
//Busca los socios por su id
    private Socio buscarSocioPorId(String id) {
     for (Socio s : socios) {
       if (s != null && s.getIdSocio().equalsIgnoreCase(id)) {
         return s;
            }
        }
        return null;
    }
//Busca los libros por su ISBN
    private Libro buscarLibroPorIsbn(String isbn) {
     for (Libro l : libros) {
        if (l != null && l.getIsbn().equalsIgnoreCase(isbn)) {
            return l;
            }
        }
        return null;
    }
// Valida el numero de prestado 
    private Prestamo buscarPrestamoPorId(String idStr) {
      try {
       int id = Integer.parseInt(idStr);
         for (Prestamo p : prestamos) {
            if (p != null && p.getIdPrestamo() == id) {
              return p;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID de prestamo invalido.");
        }
        return null;
    }
    private void consultarPrestamoPorID() {
          private void consultarPrestamoPorID() {
        String idStr = JOptionPane.showInputDialog("Ingrese ID del préstamo:");
        if (idStr == null) return;

        Prestamo p = buscarPrestamoPorId(idStr);
        if (p == null) {
            JOptionPane.showMessageDialog(null, "El préstamo con ID '" + idStr + "' no existe.");
            return;
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaReal = (p.getFechaDevolucionReal() == null) ? "N/A" : p.getFechaDevolucionReal().format(formato);

        JOptionPane.showMessageDialog(null,
                "ID Préstamo: " + p.getIdPrestamo() + "\n" +
                "Socio: " + p.getSocio().getNombre() + " (" + p.getSocio().getIdSocio() + ")\n" +
                "Libro: " + p.getLibro().getTitulo() + " (" + p.getLibro().getIsbn() + ")\n" +
                "Fecha Préstamo: " + p.getFechaPrestamo().format(formato) + "\n" +
                "Devolución Estimada: " + p.getFechaDevolucionEstimada().format(formato) + "\n" +
                "Fecha Devolución Real: " + fechaReal + "\n" +
                "Estado: " + p.getEstadoPrestamo() + "\n" +
                "Multa Generada: ₡" + p.getMultaGenerada());
    }

    private Socio buscarSocioPorId(String id) {
        for (Socio s : socios) {
            if (s != null && s.getIdSocio().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    private Libro buscarLibroPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l != null && l.getIsbn().equalsIgnoreCase(isbn)) {
                return l;
            }
        }
        return null;
    }

    private Prestamo buscarPrestamoPorId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            for (Prestamo p : prestamos) {
                if (p != null && p.getIdPrestamo() == id) {
                    return p;
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
}

}

