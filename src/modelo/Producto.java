package modelo;

public class Producto {
    
    private int id, cantidad;
    private String nombre, codigo, tipo;
    private float precio;
    private boolean disponibilidad;
    
    public Producto(){
    }
    
    public Producto(int id, String nombre, String codigo, String tipo, int cantidad, float precio, boolean disponibilidad){
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }
    
    public Producto(String nombre, String codigo, String tipo, int cantidad, float precio, boolean disponibilidad){
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
    
}
