/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.logica;
import coopetica.modelo.Asociado;
import coopetica.modelo.Sucursal;
import java.util.ArrayList;
/**
 * Administra las asociaciones de CoopeTica R.L.
 * @author psola
 */
public class GestionAsociados {
    
    private final ArrayList<Asociado> asociados;
    /**
     * Inicializar la lista de asociados.
     */
    
    public GestionAsociados() {
        asociados = new ArrayList<>();
    }
    
    /**
     * Registra un nuevo asociado.
     * 
     * @param cedula Cedula del asociado.
     * @param nombre Nombre del asociado.
     * @param numeroAsociado Numero del asociado.
     * @param sucursal Sucursal a la que pertenece.
     */
    
    public void registrarAsociado(String cedula, String nombre,
            String numeroAsociado, Sucursal sucursal) {
        if(cedula != null && buscarPorCedula(cedula) != null) {
            throw new IllegalArgumentException(
            "Ya existe un asociado con esa cedula");
        }
        
        Asociado nuevo = new Asociado(
            cedula, nombre, numeroAsociado, sucursal);
        asociados.add(nuevo);
    }
    
    /**
     * Busca un asociado por su cedula.
     * 
     * @param cedula Cedula que desea buscar.
     * @return Asociado encontrado o null si no existe.
     */
    
    public Asociado buscarPorCedula(String cedula) {
        if(cedula == null) {
            return null;
        }
        
        for (Asociado asociado : asociados) {
            if(asociado.getCedula().equals(cedula.trim())) {
                return asociado;
            }
        }
        return null;
        
    }
    
    /**
     * Obtiene todos los asociados.
     * 
     * @return Lista de los asociados.
     */
    
    public ArrayList<Asociado> listarAsociados() {
        return new ArrayList<>(asociados);
    }
}
