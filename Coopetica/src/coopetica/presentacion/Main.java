/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coopetica.presentacion;

import coopetica.logica.GestionSucursales;
import javax.swing.SwingUtilities;

/**
 * Clase principal del sistema CoopeTica R.L.
 *
 * @author Pablo Solano
 */
public class Main {

    /**
     * Inicia la aplicacion.
     *
     * @param args Argumentos de ejecucion.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GestionSucursales gestion = new GestionSucursales();

            VentanaSucursales ventana = new VentanaSucursales(gestion);

            ventana.setVisible(true);
        });
    }
}
            
       
        
    

     

