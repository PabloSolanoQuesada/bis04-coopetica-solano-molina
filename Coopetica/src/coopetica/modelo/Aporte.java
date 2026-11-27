/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 * Representa el saldo de aportes de un asociado de CoopeTica R.L.
 * 
 * @author psola
 */
public class Aporte {
    
    private final Asociado asociado;
    private double saldo;
    
    /**
     * Crear el registro de los reportes de un asociado.
     * 
     * @param asociado Asociado al que pertenece en los aportes.
     */
   
   public Aporte(Asociado asociado) {
       if(asociado == null) {
           throw new IllegalArgumentException("Debe indicar un asociado.");
       }
       
       this.asociado = asociado;
       this.saldo = 0;
   }
    /**
     * Obtiene el asociado dueño de los aportes.
     * 
     * @return Asociado dueño.
     */
   
   public Asociado getAsociado() {
       return asociado;
   }
    
    /**
     * Obtiene el saldo actual de aportes.
     * 
     * @return Saldo disponible.
     */
   
   public double getSaldo() {
       return saldo;
   }
   
   /**
    * Deposita un monto al saldo de aportes
    * 
    * @param monto Cantidad que se desea depositar
    */
   
   public void depositar(double monto) {
       if(!Double.isFinite(monto)|| monto <= 0) {
           throw new IllegalArgumentException("El monto debe ser mayor a cero");
       }
       saldo += monto;
   }
    
   /**
    * Retirar monto del saldo de aportes.
    * 
    * @param monto Cantidad deseada a retirar.
    */
   
  public void retirar(double monto) {

    if (!Double.isFinite(monto) || monto <= 0) {
        throw new IllegalArgumentException(
                "El monto debe ser mayor a cero.");
    }

    if (monto > saldo) {
        throw new IllegalArgumentException(
                "Fondos insuficientes. No puede retirar "
                + "un monto mayor al saldo disponible.");
    }

    saldo -= monto;
}

   
   /**
    * Muestra los datos del asociado y su saldo de aportes.
    * 
    * @return Descripción del aporte.
    */
   
   @Override
   public String toString() {
       return "Asociado: " + asociado.getNombre() + " | Saldo de Aportes: " +
               saldo;
   }
}
