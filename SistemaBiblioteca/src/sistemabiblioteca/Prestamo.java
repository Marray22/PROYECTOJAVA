
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
    private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //Se utiliza para convertir textos a fechas y viceversa

    private int idPrestamo;
    private Socio socio;
    private Libro libro;
    private LocalDate fechaPrestamo; //Día en que se registra el prestamo
    private LocalDate fechaDevolucionEstimada;
    private String fechaDevolucionReal; 
    private EstadoPrestamo estadoPrestamo;
    private double multaGeneradaEstePrestamo;
    String fecha = null;
// Constructor que recibe al socio, libro y crea un prestamo
    public Prestamo(int par, Socio socio, Libro libro) {
        this.idPrestamo = contador++; //Asigna un ID y lo incrementa para un siguiente prestamo 
       this.socio = socio;
        this.libro = libro;

        Date hoy = new Date(); // Guarda la fecha actual
        this.fechaPrestamo = LocalDate.now();
// Crea una fecha
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(hoy);
        calendario.add(Calendar.DAY_OF_MONTH, 15); // Suma 15 días al calendario
        this.fechaDevolucionEstimada = LocalDate.now().plusDays(7); // Se espera que el prestamo se devuelva en 7 días

        this.fechaDevolucionReal = null; // Solo si el prestamo no esta devuelto aun 
        this.estadoPrestamo = EstadoPrestamo.ACTIVO; // Prestamo activo y sin multa
        this.multaGeneradaEstePrestamo = 0.0;

        libro.setEstadoLibro(EstadoLibro.PRESTADO); // Cambia el estado del libro si esta prestado
    }

    public void devolver(String fechaRealStr) {
        try {
            this.fechaDevolucionReal = fechaRealStr;
// Cambia la fecha de string a date
            Date fechaEstimada = formato.parse(fechaRealStr);
            Date fechaReal = formato.parse(fechaRealStr);

            long diferenciaMs = fechaReal.getTime() - fechaEstimada.getTime(); //Calcula la diferencia entre la fecha real y la estimada de devolucion
            long diasRetraso = diferenciaMs / (1000 * 60 * 60 * 24); // Cambia la diferencia anterior a días de retraso
//Cambia solo si hay días de retraso
            if (diasRetraso > 0) {
                this.estadoPrestamo = EstadoPrestamo.DEVUELTO_CON_RETRASO; // Cambia el estado del prestamo a Devuelto con retraso
                this.multaGeneradaEstePrestamo = diasRetraso * 100.0; // Genera una multa por cada día de retraso 
            } else {
                this.estadoPrestamo = EstadoPrestamo.DEVUELTO_A_TIEMPO; // Si devuelven el prestamo a tiempo se cambia a Devuelto a tiempo
            }

            libro.setEstadoLibro(EstadoLibro.DISPONIBLE); // Cuando devuelven el prestamo se cambia a disponible para la persona que lo quiera pedir

        } catch (ParseException e) { // Si hay un error en la fecha muestra un mensaje diciendo que la fecha es invalida
            System.out.println("Error: Fecha de devolución inválida.");
        }
    } // Devuelve la fecha en que se prestó el libro
    public LocalDate getFechaPrestamo(){
        return fechaPrestamo;
    }
    
    public LocalDate getFechaDevolucionEstimada(){ // Devuelve la fecha estimada de devolución 
        return fechaDevolucionEstimada;
    
    }
  
    public void setFechaDevolucionReal(LocalDate Fecha){ //Permite establecer la fecha real de devolución    
        this.fechaDevolucionReal = fecha; 
        
    }
    public String getFechaDevolucionReal(){ // Hace lo mismo que el anterior, pero en formato texto
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
