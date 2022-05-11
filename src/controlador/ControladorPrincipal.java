/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.VistaPrincipal;
import controlador.ControladorTitular;
import controlador.ControladorCuenta;



/**
 *
 * @author grey
 */
public class ControladorPrincipal implements ActionListener{
    
    private VistaPrincipal vistaPrincipal;
    private ControladorTitular controladorTitular;
    private ControladorCuenta controladorCuenta;
    private ControladorMovimientos controladorMovtos;
    private ControladorConsultaCtaTodas controladorVerCuentas;
    private ControladorConsultMovs controladorVerMovs;
    
    

    public ControladorPrincipal() {
        
        
        vistaPrincipal = new VistaPrincipal();
        
        this.vistaPrincipal.ItemTitular.addActionListener(this);
        this.vistaPrincipal.ItemCuenta.addActionListener(this);
        this.vistaPrincipal.ItemMovimiento.addActionListener(this);
        this.vistaPrincipal.ItemConsultarMovimientos.addActionListener(this);
        this.vistaPrincipal.ItemConsultaCts.addActionListener(this);
        this.vistaPrincipal.ItemSalir.addActionListener(this);

       
        
        this.vistaPrincipal.setVisible(true);
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaPrincipal.ItemTitular) {
            controladorTitular = new ControladorTitular();
            this.vistaPrincipal.setVisible(false);
        }
        if (e.getSource() == vistaPrincipal.ItemCuenta) {
            controladorCuenta = new ControladorCuenta();
            this.vistaPrincipal.setVisible(false);
        }
        if (e.getSource() == vistaPrincipal.ItemMovimiento) {
            controladorMovtos = new ControladorMovimientos();
            this.vistaPrincipal.setVisible(false);
        }
        if (e.getSource() == vistaPrincipal.ItemConsultaCts) {
            controladorVerCuentas = new ControladorConsultaCtaTodas();
            this.vistaPrincipal.setVisible(false);
        }
        if (e.getSource() == vistaPrincipal.ItemConsultarMovimientos) {
            controladorVerMovs = new ControladorConsultMovs();
            this.vistaPrincipal.setVisible(false);
        }

        if (e.getSource() == vistaPrincipal.ItemSalir) {
            System.exit(0);
        }
        
        
    }
}
