/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabiblioteca;

enum Genero {NOVELA, CIENCIA_FICCION, HISTORIA, INFANTIL, POESIA, ENSAYO }

enum EstadoLibro {DISPONIBLE, PRESTADO, EN_REPARACION, EXTRAVIADO}

 public class Libro {
    
    private String isbn;
    private String titulo;
    private String autor;
    private Genero genero;
    private String editorial;
    private int anioPublicacion;
    private EstadoLibro estadoLibro;
    private int vecesPrestado;
    
    
    // Constructor recibe el número para crear el ISBN
    public Libro(int numero, String titulo, String autor, Genero genero, String editorial, int anioPublicacion, EstadoLibro estadoLibro) {
     this.isbn = "LIB-" + String.format("%03d", numero);
     this.titulo = titulo;
     this.autor = autor;
     this.genero = genero;
     this.editorial = editorial;

     if (anioPublicacion > 0) {
         this.anioPublicacion = anioPublicacion;
     } else {
         this.anioPublicacion = 2010;
     }

     this.estadoLibro = estadoLibro;
     this.vecesPrestado = 0;
 }
 

 public String getIsbn() {
     return isbn;
 }

 public void setIsbn(String isbn) {
     this.isbn = isbn;
 }

 public String getTitulo() {
     return titulo;
 }

 public void setTitulo(String titulo) {
     this.titulo = titulo;
 }

 public String getAutor() {
     return autor;
 }

 public void setAutor(String autor) {
     this.autor = autor;
 }

 public Genero getGenero() {
     return genero;
 }

 public void setGenero(Genero genero) {
     this.genero = genero;
 }

 public String getEditorial() {
     return editorial;
 }

 public void setEditorial(String editorial) {
     this.editorial = editorial;
 }

 public int getAnioPublicacion() {
     return anioPublicacion;
 }

 public void setAnioPublicacion(int anioPublicacion) {
     this.anioPublicacion = anioPublicacion;
 }

 public EstadoLibro getEstadoLibro() {
     return estadoLibro;
 }

 public void setEstadoLibro(EstadoLibro estadoLibro) {
     this.estadoLibro = estadoLibro;
 }

 public int getVecesPrestado() {
     return vecesPrestado;
 }

 public void setVecesPrestado(int vecesPrestado) {
     this.vecesPrestado = vecesPrestado;
 }
 }