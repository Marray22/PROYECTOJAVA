package sistemabiblioteca;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

enum EstadoSocio {
    ACTIVO,
    MOROSO,
    SUSPENDIDO,
    INACTIVO
}

public class Socio {
    private String idSocio;
    private String NombreCompleto;
    private String fechaRegistro;
    private EstadoSocio estadoSocio;
    private double multasAcumuladas;
    private int cantidadLibrosPrestadosActual;

    // Constructor
    public Socio(String idSocio, String nombreCompleto) {
        this.idSocio = idSocio;
        this.NombreCompleto = nombreCompleto;
        this.fechaRegistro = obtenerFechaActual();
        this.estadoSocio = EstadoSocio.ACTIVO;
        this.multasAcumuladas = 0.0;
        this.cantidadLibrosPrestadosActual = 0;
    }

    // Método para obtener la fecha actual en formato requerido
    private String obtenerFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formato.format(ahora);
    }

    // Getters y Setters
    public String getIdSocio() {
        return idSocio;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public EstadoSocio getEstadoSocio() {
        return estadoSocio;
    }

    public void setEstadoSocio(EstadoSocio estadoSocio) {
        this.estadoSocio = estadoSocio;
    }

    public double getMultasAcumuladas() {
        return multasAcumuladas;
    }

    public void setMultasAcumuladas(double multasAcumuladas) {
        this.multasAcumuladas = multasAcumuladas;
    }

    public int getCantidadLibrosPrestadosActual() {
        return cantidadLibrosPrestadosActual;
    }

    public void setCantidadLibrosPrestadosActual(int cantidadLibrosPrestadosActual) {
        this.cantidadLibrosPrestadosActual = cantidadLibrosPrestadosActual;
    }

    // Mostrar informacion del socio
    public void mostrarInformacion() {
        System.out.println("ID Socio: " + idSocio);
        System.out.println("Nombre: " + NombreCompleto);
        System.out.println("Fecha de registro: " + fechaRegistro);
        System.out.println("Estado: " + estadoSocio);
        System.out.println("Multas acumuladas: " + multasAcumuladas);
        System.out.println("Libros prestados actualmente: " + cantidadLibrosPrestadosActual);
        System.out.println("-------------------------------");
    }
    

}

