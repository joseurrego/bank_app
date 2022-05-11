/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.DAO.CuentaCorrienteDAO;
import Modelo.DAO.MovimientosDAO;
import Modelo.DAO.PersonaDAO;
import Modelo.VO.CuentaCorrienteVO;
import Modelo.VO.MovimientosVO;
import Modelo.VO.PersonaVO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import vistas.VistaMovimientos;

/**
 *
 * @author grey
 */
public class ControladorMovimientos implements ActionListener{
    
    private VistaMovimientos vistaM;
    private MovimientosVO movimiento;
    private MovimientosDAO movimientoDAO;
    private ControladorPrincipal controladorPrincipal;
    Date fecha = Date.valueOf(LocalDate.now());
    Time hora = Time.valueOf(LocalTime.now());

    public ControladorMovimientos() {
        
        vistaM = new VistaMovimientos();
        this.vistaM.setVisible(true);
        movimientoDAO = new MovimientosDAO();
        movimiento = new MovimientosVO();
        vistaM.JlabelFecha.setText(fecha.toString());
        
        this.vistaM.ButtonBuscarTitular.addActionListener(this);
        this.vistaM.ButtonEnviar.addActionListener(this);
        this.vistaM.ButtonSalir.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==vistaM.ButtonBuscarTitular){
            boolean validar=vistaM.validarCampo(vistaM.TexNumCuenta.getText());
            boolean validar1 = vistaM.validarNumero(vistaM.TexNumCuenta.getText());
            
            if (!validar){
               JOptionPane.showMessageDialog(null, " campo numero Cuenta vacio");
               eliminardatos();
           }else if(!validar1){
               JOptionPane.showMessageDialog(null, " Numero Cuenta no es un numero");
               eliminardatos();
        }else{
            int numeroCTA=Integer.parseInt(vistaM.TexNumCuenta.getText());
            CuentaCorrienteDAO cuentaDAO = new CuentaCorrienteDAO();
            CuentaCorrienteVO cuentaVO = new CuentaCorrienteVO();
            PersonaDAO personaDAO = new PersonaDAO();
            PersonaVO personaVO = new PersonaVO();
            
            cuentaVO=cuentaDAO.buscarNC(numeroCTA);
            personaVO = personaDAO.buscar(cuentaVO.getIdPersona());
            if (cuentaVO.getNumeroCuenta()==0){
               JOptionPane.showMessageDialog(null, " Esta cuenta no existe ");
               eliminardatos();
           }else{
           
           vistaM.TexTitularCta.setText(personaVO.getNombrePersona());
           vistaM.JtexIdTitular.setText(String.valueOf(cuentaVO.getIdPersona()));
           vistaM.TexSaldoActual.setText(String.valueOf(cuentaVO.getSaldo()));
           }
           }
            
            
        }
        if(e.getSource()==vistaM.ButtonEnviar){
            boolean validar=vistaM.validarCampo(vistaM.TexNumCuenta.getText());
            boolean validar1 = vistaM.validarNumero(vistaM.TexNumCuenta.getText());
            boolean validar2 = vistaM.validarCampo(vistaM.TexValor.getText());
            boolean validar3 = vistaM.validarNumero(vistaM.TexValor.getText());
            if (!validar){
               JOptionPane.showMessageDialog(null, " Primero busque la cuenta con el id");
           }else if (!validar2){
               JOptionPane.showMessageDialog(null, " Anote un valor a enviar");
           }else if(!validar1){
               JOptionPane.showMessageDialog(null, " numero cuenta no es un numero");
               eliminardatos();
        }else if(!validar3){
               JOptionPane.showMessageDialog(null, " El valor de transaccion no es un numero");
               eliminardatos2();
        }else{
            int numCTA=Integer.parseInt(vistaM.TexNumCuenta.getText());
            CuentaCorrienteDAO cuentaDAO = new CuentaCorrienteDAO();
            CuentaCorrienteVO cuentaVO = new CuentaCorrienteVO();
            cuentaVO=cuentaDAO.buscarNC(numCTA);
            
            if(vistaM.ComboBoxTipo.getSelectedItem().equals("Deposito")){
                Double suma = cuentaVO.getSaldo()+Double.parseDouble(vistaM.TexValor.getText());
               cuentaVO.setSaldo(suma);
               movimiento.setValor(Double.parseDouble(vistaM.TexValor.getText()));
            }else if(vistaM.ComboBoxTipo.getSelectedItem().equals( "Retiro")){
                Double resta = cuentaVO.getSaldo()-(Double.parseDouble(vistaM.TexValor.getText()));
                if(resta<0){
                    JOptionPane.showMessageDialog(null, " La cuenta no tiene fondos suficientes");
                    cuentaVO.setSaldo(Double.parseDouble(vistaM.TexSaldoActual.getText()));
                    movimiento.setValor(0.0);
                }else{
                cuentaVO.setSaldo(resta);
                movimiento.setValor(Double.parseDouble(vistaM.TexValor.getText()));
                }
            }
            
            cuentaDAO.modificar(cuentaVO);
            
            
            movimiento.setNumeroCuenta(Integer.parseInt(vistaM.TexNumCuenta.getText()));
            movimiento.setTipoMovimiento(String.valueOf(vistaM.ComboBoxTipo.getSelectedItem()));
            movimiento.setLugar(String.valueOf(vistaM.ComboBoxLugares.getSelectedItem()));
            movimiento.setFecha(Date.valueOf(vistaM.JlabelFecha.getText()));
            
            if(movimiento.getValor()==0.0){
                      JOptionPane.showMessageDialog(null, "no se creo el movimiento");
            }else{
                movimientoDAO.crearMov(movimiento);
            }
            eliminardatos();
        }
        }
        
        if(e.getSource()==vistaM.ButtonSalir){
            controladorPrincipal = new ControladorPrincipal();
          vistaM.setVisible(false);
            
        }
        
        
    }
    public void eliminardatos(){
        vistaM.TexNumCuenta.setText("");
        vistaM.TexTitularCta.setText("");
        vistaM.TexSaldoActual.setText("");
        vistaM.JtexIdTitular.setText("");
         vistaM.TexValor.setText("");
        
    }
    
    public void eliminardatos2(){
        
        vistaM.TexValor.setText("");
        
    }
    
    
    
    
    
}
