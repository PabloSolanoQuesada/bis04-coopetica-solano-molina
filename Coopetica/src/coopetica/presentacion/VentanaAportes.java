/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.presentacion;
import coopetica.logica.GestionAportes;
import coopetica.logica.GestionAsociados;
import coopetica.modelo.Asociado;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Ventana para administrar los aportes de CoopeTica R.L.
 * 
 * @author psola
 */
public class VentanaAportes extends JFrame {
    
    private final GestionAportes gestionAportes;
    private final GestionAsociados gestionAsociados;
    
    private JTextField txtCedula;
    private JTextField txtMonto;
    private JTextArea areaResultados;
    
    /**
     * Crear la ventana aportes.
     * 
     * @param gestionAportes Gestion de aportes.
     * @param gestionAsociados Gestion de Asociados.
     */
    
    public VentanaAportes(GestionAportes gestionAportes, 
            GestionAsociados gestionAsociados) {
        
        this.gestionAportes = gestionAportes;
        this.gestionAsociados = gestionAsociados;
        
        setTitle("CoopeTica R.L.  - Aportes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        crearComponentes();
    }
    
    /**
     * Crear los componentes de la ventana
     */
    
    private void crearComponentes() {
        setLayout(new BorderLayout(10, 10));
        
       JPanel panelDatos = new JPanel(new GridLayout(2, 2, 5, 5));

       txtCedula = new JTextField(20);
       txtMonto = new JTextField(20);

       panelDatos.add(new JLabel("Cedula del asociado: "));
       panelDatos.add(txtCedula);

       panelDatos.add(new JLabel("Monto: "));
       panelDatos.add(txtMonto);

       add(panelDatos, BorderLayout.NORTH);
       areaResultados = new JTextArea();
       areaResultados.setEditable(false);
       
       add(new JScrollPane(areaResultados), BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        
        JButton btnDepositar = new JButton("Depositar");
        JButton btnRetirar = new JButton ("Retirar");
        JButton btnConsultar = new JButton ("Consultar saldo");
        
        panelBotones.add(btnDepositar);
        panelBotones.add(btnRetirar);
        panelBotones.add(btnConsultar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnDepositar.addActionListener(e -> depositar());
        btnRetirar.addActionListener (e -> retirar());
        btnConsultar.addActionListener (e -> consultarSaldo());
        
    }
    
    /**
     * Busca el asociado indicando su cédula.
     * 
     * @return Asociado encontrado.
     */
    
    private Asociado buscarAsociado() {
        String cedula = txtCedula.getText().trim();
        if(cedula.isEmpty()) {
            throw new IllegalArgumentException("Ingrese la cedula del asociado: ");
        }
        
        Asociado asociado = gestionAsociados.buscarPorCedula(cedula);
        
        if(asociado == null) {
            throw new IllegalArgumentException("No existe un asociado con esa cédula.");
        }
        return asociado;
    }
   
    /**
     * Obtiene y valida monto ingresado,
     * 
     * @return Monto Valido
     */
    
    private double obtenerMonto() {
        try {
            double monto = Double.parseDouble(txtMonto.getText().trim());
            if(!Double.isFinite(monto)|| monto <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor a cero");
            }
            
            return monto;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Ingrese un monto numerico valid.");
        }
    }
    
    /**
     * Deposita aportes a un asociado
     */
    
    private void depositar() {
        try {
            Asociado asociado = buscarAsociado();
            double monto = obtenerMonto();
            
            gestionAportes.depositar(asociado, monto);
            
            areaResultados.setText("");
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
            
        }
    }
    
    /**
     * Retira aportes de un asociado.
     */
    
    private void retirar() {
        try {
            Asociado asociado = buscarAsociado();
            double monto = obtenerMonto();
            
            gestionAportes.retirar(asociado, monto);
            
          
        areaResultados.setText(
                 "Deposito realizado correctamente.\n"
                + "Asociado: " + asociado.getNombre()
                + "\nMonto depositado: " + monto
                + "\nSaldo actual: "
                + gestionAportes.consultarSaldo(asociado));
        txtMonto.setText("");
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }
    
    /**
     * Consulta el saldo de aportes de un asociado.
     */
    
    private void consultarSaldo() {
        try {
            Asociado asociado = buscarAsociado();
            
            double saldo =
                    gestionAportes.consultarSaldo(asociado);
            
            areaResultados.setText("Consulta de aportes\n" + "Asociado: " + 
                    asociado.getNombre() + "\nCedula: " + asociado.getCedula() +
                            "\nSaldo actual: " + saldo);
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }
    
    /**
     * Muestra un mensaje de error.
     * 
     * @param mensaje Descripcion del error.
     */
    
    private void mostrarError(String mensaje){
        JOptionPane.showMessageDialog(
        this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
