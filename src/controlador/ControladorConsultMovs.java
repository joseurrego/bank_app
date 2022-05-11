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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vistas.VistaConsultadeCts;
import vistas.VistaConsultasMovimientos;

/**
 *
 * @author grey
 */
public class ControladorConsultMovs implements ActionListener{
    private vistas.VistaConsultasMovimientos vistaConsulMVTs;
    /*private PersonaVO titularVO;
    private PersonaDAO titularDAO;
    */private CuentaCorrienteVO cuentaVO;
    private CuentaCorrienteDAO cuentaDAO;
    private MovimientosVO movimientoVO;
    private MovimientosDAO movimientoDAO;
    private ControladorPrincipal controladorPrincipal;
    private ResultSet resultSet;

    public ControladorConsultMovs() {
        vistaConsulMVTs = new VistaConsultasMovimientos();
        this.vistaConsulMVTs.setVisible(true);
        cuentaDAO = new CuentaCorrienteDAO();
        movimientoDAO= new MovimientosDAO();
        this.vistaConsulMVTs.ButtonSalir.addActionListener(this);
        this.vistaConsulMVTs.ButtonBuscarMovs.addActionListener(this);
    

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaConsulMVTs.ButtonSalir) {
            controladorPrincipal = new ControladorPrincipal();
            vistaConsulMVTs.setVisible(false);

        }
         if (e.getSource() == vistaConsulMVTs.ButtonBuscarMovs) {
             cuentaVO = cuentaDAO.buscarNC(Integer.parseInt(vistaConsulMVTs.TexNumCuenta.getText()));
            if(cuentaVO.getNumeroCuenta()==0){
                JOptionPane.showMessageDialog(null, " cuenta no encontrada");
                String columnas[] = {"numerocuenta","tipomovimiento","valor","lugar","fecha"};
                DefaultTableModel tabla = new DefaultTableModel(null, columnas);
                vistaConsulMVTs.jTable1.setModel(tabla);
            }else{
                tabla(cuentaVO.getNumeroCuenta());
         }

        }
        
    }
    public void tabla(int numerocuenta){
       String columnas[] = {"numerocuenta","tipomovimiento","valor","lugar","fecha"};
       DefaultTableModel tabla = new DefaultTableModel(null, columnas);
       try{
           resultSet=movimientoDAO.mostrarMovs(numerocuenta);
           while(resultSet.next()){
               Object Filas[] = {resultSet.getInt("numerocuenta"),resultSet.getString("tipomovimiento"),resultSet.getDouble("valor"),resultSet.getString("lugar"),resultSet.getDate("fecha")};
               tabla.addRow(Filas);
           }
           this.vistaConsulMVTs.jTable1.setModel(tabla);
           resultSet.close();
       }catch(SQLException e){
           System.err.println("Error "+e);
       }
   
    }
    
}
