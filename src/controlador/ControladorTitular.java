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
import java.time.Clock;
import javax.swing.JOptionPane;
import vistas.VistaPersona;

/**
 *
 * @author Home
 */
public class ControladorTitular implements ActionListener{
    
    private VistaPersona vistaTitular;
    private PersonaDAO titularDAO;
    private ControladorPrincipal controladorPrincipal;
    private PersonaVO titularVO;
    

    public ControladorTitular() {
        
        vistaTitular = new VistaPersona();
        this.vistaTitular.setVisible(true);
        titularDAO = new PersonaDAO();
                
        
        this.vistaTitular.ButtonCrear.addActionListener(this);
        this.vistaTitular.ButtonBuscar.addActionListener(this);
        this.vistaTitular.ButtonModificar.addActionListener(this);
        this.vistaTitular.ButtonBorrar.addActionListener(this);
        this.vistaTitular.ButtonSalir.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaTitular.ButtonCrear){
           boolean validar=vistaTitular.validarCampos(vistaTitular.TexID.getText(), vistaTitular.TexNombre.getText(), vistaTitular.TexDireccion.getText(), vistaTitular.TexTelefonoMovil.getText());
           boolean validar1 = vistaTitular.validarNumero(vistaTitular.TexID.getText());
           if (!validar){
               JOptionPane.showMessageDialog(null, " campos vacios");
           }else if(!validar1){
               JOptionPane.showMessageDialog(null, " id no es un numero");
               eliminardatos();
        }else{
           titularVO = new PersonaVO();
           
           titularVO.setIdPersona(Integer.valueOf(vistaTitular.TexID.getText()));
           titularVO.setNombrePersona(vistaTitular.TexNombre.getText());
           titularVO.setDireccionCasa(vistaTitular.TexDireccion.getText());
           titularVO.setTelefonoMovil(vistaTitular.TexTelefonoMovil.getText());
           
          titularDAO.crear(titularVO);
           }
          eliminardatos();
        
    }
        if (e.getSource() == vistaTitular.ButtonBuscar){
            boolean validar1 = vistaTitular.validarNumero(vistaTitular.TexID.getText());
            boolean validar2 = vistaTitular.validarCampo(vistaTitular.TexID.getText());
            if(!validar2){
               JOptionPane.showMessageDialog(null, " Campo ID vacio");
           }else{  
           if(!validar1){
               JOptionPane.showMessageDialog(null, " id no es un numero");
               eliminardatos();
           }else {  
           int idpersona = Integer.parseInt(vistaTitular.TexID.getText());
           titularVO = titularDAO.buscar(idpersona);
           if(titularVO.getIdPersona()==0){
               JOptionPane.showMessageDialog(null, " no existe");
               eliminardatos();
           }else{    
           vistaTitular.TexID.setText(String.valueOf(titularVO.getIdPersona()));
           vistaTitular.TexNombre.setText(titularVO.getNombrePersona());
           vistaTitular.TexDireccion.setText(titularVO.getDireccionCasa());
           vistaTitular.TexTelefonoMovil.setText(titularVO.getTelefonoMovil());
           }
           }
           
           
    }
        if (e.getSource() == vistaTitular.ButtonModificar){
          boolean validar=vistaTitular.validarCampos(vistaTitular.TexID.getText(), vistaTitular.TexNombre.getText(), vistaTitular.TexDireccion.getText(), vistaTitular.TexTelefonoMovil.getText());

          if (!validar){
               JOptionPane.showMessageDialog(null, " campos vacios");
          }else{
            
           titularVO.setNombrePersona(vistaTitular.TexNombre.getText());
           titularVO.setDireccionCasa(vistaTitular.TexDireccion.getText());
           titularVO.setTelefonoMovil(vistaTitular.TexTelefonoMovil.getText());
           
           titularDAO.modificar(titularVO);
            eliminardatos();
          }
          
           
    }
        if (e.getSource() == vistaTitular.ButtonBorrar){
            
            CuentaCorrienteVO cuenta=new CuentaCorrienteVO();
            int id = Integer.parseInt(vistaTitular.TexID.getText());
            boolean validar3 = vistaTitular.validarNumero(vistaTitular.TexID.getText());
             if(!validar1){
               JOptionPane.showMessageDialog(null, " id no es un numero");
               eliminardatos();
           }else{
            CuentaCorrienteDAO cuentaDAO = new CuentaCorrienteDAO();
            cuenta=cuentaDAO.buscar(id);
            if(cuenta.getIdPersona()==0){
                titularDAO.borrar(id);
            }else{
                JOptionPane.showMessageDialog(null, "no se puede borrar titular porque tiene cuentas");
            }
          }
         eliminardatos();
    }
 
        }
        if (e.getSource() == vistaTitular.ButtonSalir){
          controladorPrincipal = new ControladorPrincipal();
          vistaTitular.setVisible(false);
            
    }
   
   
}
     public void eliminardatos(){
        vistaTitular.TexID.setText("");
        vistaTitular.TexNombre.setText("");
        vistaTitular.TexDireccion.setText("");
        vistaTitular.TexTelefonoMovil.setText("");
    }
}
