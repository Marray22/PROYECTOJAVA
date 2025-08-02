
package sistemabiblioteca;

import java.time.LocalDate; // sirve para manejar fechas modernas

public class Prestamo {

    public enum EstadoPrestamo {
        ACTIVO,
        DEVUELTO_A_TIEMPO,
        DEVUELTO_CON_RETRASO,
        CANCELADO_NO_RETIRADO
    }

    private static int contador = 1000; //Contador para generar ID'S 
    
    private int idPrestamo;
    private Socio socio;
    private Libro libro;
    private LocalDate fechaPrestamo; //Día en que se registra el prestamo
    private LocalDate fechaDevolucionEstimada;
    private String fechaDevolucionReal; 
    private EstadoPrestamo estadoPrestamo;
    private double multaGeneradaEstePrestamo;

// Constructor que recibe al socio, libro y crea un prestamo
    public Prestamo(int id, Socio socio, Libro libro) {
        this.idPrestamo = id; //Asigna un ID y lo incrementa para un siguiente prestamo 
       this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
// Crea una fecha
        this.fechaDevolucionEstimada = fechaPrestamo.plusDays(7); // Se espera que el prestamo se devuelva en 7 días

        this.fechaDevolucionReal = null; // Solo si el prestamo no esta devuelto aun 
        this.estadoPrestamo = EstadoPrestamo.ACTIVO; // Prestamo activo y sin multa
        this.multaGeneradaEstePrestamo = 0.0;

        libro.setEstadoLibro(EstadoLibro.PRESTADO); // Cambia el estado del libro si esta prestado
    }
     public int getIdPrestamo() {
        return idPrestamo;
    }

    public Socio getSocio() {
        return socio;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEstimada() {
        return fechaDevolucionEstimada;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(LocalDate fecha) {
        this.fechaDevolucionReal = fecha;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamo estado) {
        this.estadoPrestamo = estado;
    }

    public double getMultaGeneradaEstePrestamo() {
        return multaGeneradaEstePrestamo;
    }

    public void setMultaGenerada(double multa) {
        this.multaGeneradaEstePrestamo = multa;
    }
}

    
