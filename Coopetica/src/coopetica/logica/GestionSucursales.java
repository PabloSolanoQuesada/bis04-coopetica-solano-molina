/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.logica;
import coopetica.modelo.Sucursal;
import java.util.ArrayList;

/**
 * Administrar el registro y consulta de sucursales de Coopetica R.L.
 *
 * @author psola.
 */
public class GestionSucursales {
    
    private final ArrayList<Sucursal> sucursales;
    
    /**
     * Iniciar la lista de sucursales.
     */
    
    public GestionSucursales() {
        sucursales = new ArrayList<>();
    }
    
    /**
     * Registra una nueva sucursal
     * 
     * @param codigo Codigo de la sucursal.
     * @param nombre Nombre de la sucursal.
     * @param true si se registra; false si el codigo ya existe.
     */
  
    public boolean registrarSucursal(String codigo, String nombre) {
        Sucursal nueva = new Sucursal(codigo, nombre);
        
        for (Sucursal sucursal : sucursales) {
            if(sucursal.getCodigo().equalsIgnoreCase(nueva.getCodigo())) {
                return false;
            }
        }
        
        sucursales.add(nueva);
        return true;
    }
    /**
     * Busca una sucursal mediante su codigo.
     * 
     * @param codigo Codigo que se desea hacer.
     * @return Sucursal encontrada o null si no existe.
     */
    
    public Sucursal buscarSucursal(String codigo) {
        
       if(codigo == null) {
           return null;
           
       }
       
       for (Sucursal sucursal : sucursales ) {
           if (sucursal.getCodigo().equals(codigo.trim())) {
               return sucursal;
           }
       }
       
       return null;
    }
    
    /**
     * Obtiene todas las sucursales registradas.
     * 
     * @return Copia de la lista de sucursales.
     */
    
    public ArrayList<Sucursal> listarSucursales() {
        return new ArrayList<>(sucursales);
    }
}
