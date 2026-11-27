/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.logica;
import coopetica.modelo.Aporte;
import coopetica.modelo.Asociado;
import java.util.ArrayList;
/**
 * Gestiona los aportes de los asociados de CoopeTica R.L.
 * 
 * @author psola
 */
public class GestionAportes {
   
    private final ArrayList<Aporte> aportes;
    
    /**
     * Crear lista de administración de los aportes.
     */
    
    public GestionAportes() {
        aportes = new ArrayList<>();
    }
    
    /**
     * Busca los aportes de X asociado por su cédula.
     * 
     * @param cedula Cedula del asociado.
     * @return Registro de aportes o null si no existe.
     */
    
    public Aporte buscarPorCedula(String cedula) {
        if(cedula == null || cedula.isBlank()) {
            return null;
        }
        
        for (Aporte aporte : aportes) {
            if(aporte.getAsociado().getCedula().equals(cedula.trim())) {
                return aporte;
            }
        }
        return null;
     
    }
    
    /**
     * Obtiene el registro de aportes de un asociado.
     * Si no existe, se crea.
     * 
     * @param asociado Asociado al que pertenece los aportes.
     * @return Registro de aportes del asociado.
     */
    
    public Aporte obtenerAporte(Asociado asociado) {
        if(asociado == null) {
            throw new IllegalArgumentException("Debe seleccionar un asociado.");
        }
        
        Aporte aporte = buscarPorCedula(asociado.getCedula());
        if(aporte == null) {
            aporte = new Aporte(asociado);
            aportes.add(aporte);
        }
        return aporte;
    }
    
    /**
     * Depositar aportes a un asociado.
     * 
     * @param asociado Asociado que realiza el aporte.
     * @param monto Monto a retirar.
     */
    
    public void depositar(Asociado asociado, double monto) {
        if(!Double.isFinite(monto)|| monto <= 0) {
            throw new IllegalArgumentException ("El monto debe ser mayor a cero");
        }
        Aporte aporte = obtenerAporte(asociado);
        aporte.depositar(monto);
    }
    
    /**
     * Retira aportes de un asociado.
     * 
     * @param asociado Asociado realiza el retiro.
     * @param monto Monto que desea retirar.
     */
    
    public void retirar(Asociado asociado, double monto) {
        if(!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        Aporte aporte = obtenerAporte(asociado);
        aporte.retirar(monto);
    }
    
    /**
     * Consulta el saldo de aportes de un asociado.
     * 
     * @param asociado Asociado que se desea consultar.
     * @return Saldo actual de aportes.
     */
    
    public double consultarSaldo(Asociado asociado) {
        return obtenerAporte(asociado).getSaldo();
    }
    
    /**
     * Obtener todos los registros de aportes.
     * 
     * @return Lista de aportes registrados.
     */
    
    public ArrayList<Aporte> listarAportes() {
        return new ArrayList<>(aportes);
    }
}
