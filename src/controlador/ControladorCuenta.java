/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.DAO.CuentaCorrienteDAO;
import Modelo.DAO.PersonaDAO;
import Modelo.VO.CuentaCorrienteVO;
import Modelo.VO.PersonaVO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vistas.VistaCrearCuenta;

/**
 *
 * @author grey
 */
public class ControladorCuenta implements ActionListener{
    
    private VistaCrearCuenta vistaCuenta;
    private CuentaCorrienteVO cuentaVO;
    private CuentaCorrienteDAO cuentaDAO;
    private ControladorPrincipal controladorPrincipal;

    public ControladorCuenta() {
        
        vistaCuenta = new VistaCrearCuenta();
        cuentaDAO = new CuentaCorrienteDAO();
        this.vistaCuenta.setVisible(true);
        
        this.vistaCuenta.ButtonCrear.addActionListener(this);
        this.vistaCuenta.ButtonBuscar.addActionListener(this);
        this.vistaCuenta.ButtonBorrar.addActionListener(this);
        this.vistaCuenta.ButtonSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCuenta.ButtonCrear){
            boolean validar=vistaCuenta.validarCampos(vistaCuenta.TexIdTitular.getText(), vistaCuenta.TexNumCuenta.getText());
           boolean validar1 = vistaCuenta.validarNumero(vistaCuenta.TexIdTitular.getText(), vistaCuenta.TexNumCuenta.getText());
            if (!validar){
               JOptionPane.showMessageDialog(null, " campos vacios");
           }else if(!validar1){
               JOptionPane.showMessageDialog(null, " id o numero de cuenta no son un numero");
        }else{
            PersonaVO titular = new PersonaVO();
            int id = Integer.parseInt(vistaCuenta.TexIdTitular.getText());
            titular.setIdPersona(id);
            PersonaDAO titular1 = new PersonaDAO();
            titular=titular1.buscar(id);
            if(titular.getIdPersona()==0){
                JOptionPane.showMessageDialog(null, "No existe titular");
                
            }else{
            cuentaVO = new CuentaCorrienteVO();
           cuentaVO.setIdPersona(Integer.parseInt(vistaCuenta.TexIdTitular.getText()));
           cuentaVO.setNumeroCuenta(Integer.parseInt(vistaCuenta.TexNumCuenta.getText()));
           cuentaVO.setSaldo(Double.parseDouble(vistaCuenta.TexSaldo.getText()));
           cuentaDAO.crear(cuentaVO);
           eliminardatos();
            }
           }
          
    }
        if (e.getSource() == vistaCuenta.ButtonBuscar){
            boolean validar1 = vistaCuenta.validarNumeroCTA(vistaCuenta.TexNumCuenta.getText());
            boolean validar=vistaCuenta.validarCampo(vistaCuenta.TexNumCuenta.getText());
            if(!validar){
               JOptionPane.showMessageDialog(null, " El campo de cuenta esta vacio");
        }else if(!validar1){
              JOptionPane.showMessageDialog(null, " El numero de cuenta no es un numero");
              eliminardatos();
        }else{
            int numeroCTA = Integer.parseInt(vistaCuenta.TexNumCuenta.getText());
           cuentaVO = cuentaDAO.buscarNC(numeroCTA);
           
           if (cuentaVO.getNumeroCuenta()==0){
               JOptionPane.showMessageDialog(null, " Esta cuenta no existe ");
               eliminardatos();
           }else{
           vistaCuenta.TexNumCuenta.setText(String.valueOf(cuentaVO.getNumeroCuenta()));
           vistaCuenta.TexIdTitular.setText(String.valueOf(cuentaVO.getIdPersona()));
           vistaCuenta.TexSaldo.setText(String.valueOf(cuentaVO.getSaldo()));
           } 
        }
        }
       
        if (e.getSource() == vistaCuenta.ButtonBorrar){
            boolean validar1 = vistaCuenta.validarNumeroCTA(vistaCuenta.TexNumCuenta.getText());
             boolean validar=vistaCuenta.validarCampo(vistaCuenta.TexNumCuenta.getText());
             if(!validar){
               JOptionPane.showMessageDialog(null, " El campo de cuenta esta vacio");
               eliminardatos();
        }else if(!validar1){
               JOptionPane.showMessageDialog(null, " El numero de cuenta no es un numero");
               eliminardatos();
        }else{
            int numeroCuenta = Integer.valueOf(vistaCuenta.TexNumCuenta.getText());
            cuentaDAO.borrar(numeroCuenta);
            
            eliminardatos2();
            }      
    }
        if (e.getSource() == vistaCuenta.ButtonSalir){
          controladorPrincipal = new ControladorPrincipal();
          vistaCuenta.setVisible(false);
            
        }
    }
    public void eliminardatos(){
    vistaCuenta.TexIdTitular.setText("");
    vistaCuenta.TexNumCuenta.setText("");
}
    public void eliminardatos2(){
    vistaCuenta.TexIdTitular.setText("");
    vistaCuenta.TexNumCuenta.setText("");
    vistaCuenta.TexSaldo.setText("");
}
    
    
    
    
    
}
