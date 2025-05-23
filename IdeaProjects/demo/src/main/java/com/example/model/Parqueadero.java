package com.example.model;

public class Parqueadero {

    private static Parqueadero instancia;

    private String nombre;
    private String nit;
    private String direccion;
    private String ciudad;
    private String departamento;

    private String representante;
    private String cedulaRepresentante;
    private String cargoRepresentante;
    private String telefonoPrincipal;
    private String correo;

    private String rutaLogo;

    private Parqueadero() {
        // Constructor privado
    }

    public static Parqueadero getInstance() {
        if (instancia == null) {
            instancia = new Parqueadero();
        }
        return instancia;
    }

    // Getters y setters
    public String getNombre() {
        return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }

    public String getNit() {
        return nit; }
    public void setNit(String nit) {
        this.nit = nit; }

    public String getDireccion() {
        return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() {
        return ciudad; }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad; }

    public String getDepartamento() {
        return departamento; }
    public void setDepartamento(String departamento) {
        this.departamento = departamento; }

    public String getRepresentante() {
        return representante; }
    public void setRepresentante(String representante) { this.representante = representante; }

    public String getCedulaRepresentante() { return cedulaRepresentante; }
    public void setCedulaRepresentante(String cedulaRepresentante) { this.cedulaRepresentante = cedulaRepresentante; }

    public String getCargoRepresentante() { return cargoRepresentante; }
    public void setCargoRepresentante(String cargoRepresentante) { this.cargoRepresentante = cargoRepresentante; }

    public String getTelefonoPrincipal() { return telefonoPrincipal; }
    public void setTelefonoPrincipal(String telefonoPrincipal) { this.telefonoPrincipal = telefonoPrincipal; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRutaLogo() { return rutaLogo; }
    public void setRutaLogo(String rutaLogo) { this.rutaLogo = rutaLogo; }
}
