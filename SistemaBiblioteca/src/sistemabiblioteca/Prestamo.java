
package sistemabiblioteca;

import java.text.ParseException; // para capturar errores al covertir textos en fechas
import java.text.SimpleDateFormat;
import java.time.LocalDate; // sirve para manejar fechas modernas
import java.util.Calendar; //suma días a una fecha 
import java.util.Date;

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

    
    } // Devuelve la fecha en que se prestó el libro
    public LocalDate getFechaPrestamo(){
        return fechaPrestamo;
    }
    
    public LocalDate getFechaDevolucionEstimada(){ // Devuelve la fecha estimada de devolución 
        return fechaDevolucionEstimada;
    
    }
  
    public LocalDate getFechaDevolucionReal(){ //Permite establecer la fecha real de devolución    
        return fechaDevolucionReal; 
        
    }
    
    public void setMultaGenerada(double multa){ // Permite establecer una multa manualmente
    this.multaGeneradaEstePrestamo = multa;
    }
    
    public void setEstadoPrestamo(EstadoPrestamo estado){ // Permite cambiar manualmente el estado del prestamo
    this.estadoPrestamo = estado;
    }
    
    public int getIdPrestamo() { // Devuelce el Id del prestamo
        return idPrestamo;
    }

    public EstadoPrestamo getEstadoPrestamo() { // Devuelve el estado actual del prestamo
        return estadoPrestamo;
    }

    public double getMultaGeneradaEstePrestamo() { //Devuelve el monto de la multa generada
        return multaGeneradaEstePrestamo;
    }
    public Libro getLibro(){ //Devuelve el libro asociado al prestamo
    return libro;
    }
    public Socio getSocio() { //Devuelve al socio que tiene el prestamo 
    return socio;
    }
}
