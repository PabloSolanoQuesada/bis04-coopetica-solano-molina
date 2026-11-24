/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 * Representar una sucursal de la cooperativa CoopeTica R.L.
 * Cada sucursal tiene un codigo y un nombre.
 */
public class Sucursal {
    
    private String codigo;
    private String nombre;
    
/**
 * Construye una sucursal con su codigo y nombre.
 * 
 * @param codigo Codigo de la sucursal.
 * @param nombre Nombre de la sucursal.
 */    
    
    public Sucursal(String codigo, String nombre) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
               "El codigo de la sucursal no puede estar vacio.");
        }
        
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
              "El nombre de la sucursal no puede estar vacio");
        }
        
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        
    }
    
    /**
     * Obtiene el codigo de la sucursal.
     * 
     * @return Codigo de la sucursal.
     */
    
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * Obtiene nombre de la sucursal.
     * 
     * @return Nombre de la sucursal.
     */
    
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Modifica nombre de la sucursal.
     * 
     * @param nombre Nuevo de la sucursal.
     */
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
               "El nombre de la sucursal no puede estar vacio");
        }
           this.nombre = nombre.trim();
           
    }
    
    /**
     * Devuelve una represetación de la sucursal.
     * 
     * @return Codigo y nombre de la sucursal.
     */
    
    @Override 
    public String toString() {
        return codigo + " - " + nombre;
    }
}

