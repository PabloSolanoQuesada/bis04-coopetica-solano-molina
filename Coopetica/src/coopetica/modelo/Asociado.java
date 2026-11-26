/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

import java.util.ArrayList;

/**
 * Representar un asociado de CoopeTica R.L.
 * @author psola
 */
public class Asociado {
    
    private final String cedula;
    private String nombre;
    private final String numeroAsociado;
    private Sucursal sucursal;
    private final ArrayList<Cuenta> cuentas;
    
/**
 * Crear un asociado
 * 
 * @param cedula Cedula del asociado.
 * @param nombre Nombre del asociado.
 * @param numeroAsociado Numero del Asociado.
 * @param sucursal Sucursal a la que pertenece.
 */
    
    public Asociado(String cedula, String nombre, String numeroAsociado, Sucursal sucursal) {
        if(cedula == null || cedula.isBlank() || nombre == null || nombre.isBlank()
                || numeroAsociado == null || numeroAsociado.isBlank()) {
            throw new IllegalArgumentException(
            "Todos los campos son obligatorios");
        }
        
        if (sucursal == null) {
            throw new IllegalArgumentException(
            "Debe ser una sucursal");
        }
        
        this.cedula = cedula.trim();
        this.nombre = nombre.trim();
        this.numeroAsociado = numeroAsociado.trim();
        this.sucursal = sucursal;
        this.cuentas = new ArrayList<>();
    }
    
    /**
     * @return Cedula del asociado.
     */
        
    public String getCedula() {
        return cedula;
    }
        
    /**
     * @return Nombre del asociado.
     */
    
    public String getNombre() {
        return nombre;
    }    
    
    /**
     *@return Numero del asociado.
     */
    
    public String getNumeroAsociado() {
        return numeroAsociado;
    }
    
    /**
     * @return Sucursal del asociado.
     */
    
    public Sucursal getSucursal() {
        return sucursal;
    }
    
    /**
     * @param nombre Nuevo nombre del asociado.
     */
    
    public void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
            "El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }
    
    /**
     * @param sucursal Nueva sucursal del asociado.
     */
    
    public void setSucursal(Sucursal sucursal) {
        if(sucursal == null) {
            throw new IllegalArgumentException("Debe seleccionar una sucursal");
        }
        this.sucursal = sucursal;
    }

    /**
     * Agrega una cuenta a la lista de cuentas del asociado.
     *
     * @param cuenta Cuenta a vincular.
     */
    public void agregarCuenta(Cuenta cuenta) {
        if (cuenta != null && !cuentas.contains(cuenta)) {
            cuentas.add(cuenta);
        }
    }

    /**
     * Obtiene las cuentas asociadas a este miembro.
     *
     * @return Copia de la lista de cuentas.
     */
    public ArrayList<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas);
    }
    
    /**
     * @return Datos del asociado.
     */
    
    @Override
    public String toString() {
        return cedula + " - " + nombre + " - " + numeroAsociado + " - " 
                + sucursal;
    }
}