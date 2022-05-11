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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import vistas.VistaConsultadeCts;

/**
 *
 * @author grey
 */
public class ControladorConsultaCtaTodas implements ActionListener {

    private vistas.VistaConsultadeCts vistaConsulCTAs;
    private PersonaVO titularVO;
    private PersonaDAO titularDAO;
    private CuentaCorrienteVO cuentaVO;
    private CuentaCorrienteDAO cuentaDAO;
    private ControladorPrincipal controladorPrincipal;

    public ControladorConsultaCtaTodas() {
        vistaConsulCTAs = new VistaConsultadeCts();
        this.vistaConsulCTAs.setVisible(true);
        titularDAO = new PersonaDAO();

        this.vistaConsulCTAs.ButtonSalir.addActionListener(this);
        tabla();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaConsulCTAs.ButtonSalir) {
            controladorPrincipal = new ControladorPrincipal();
            vistaConsulCTAs.setVisible(false);

        }
    }

    private void tabla() {
        cuentaDAO = new CuentaCorrienteDAO();
        String columnas[]={"NumeroCuenta","IdTitular","nombrepersona","Saldo"};
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        try{
            ResultSet resultSet = cuentaDAO.mostrarTodo();
            while(resultSet.next()){
                titularVO=titularDAO.buscar(resultSet.getInt("idtitular"));
                Object Filas[] = {resultSet.getInt("Numerocuenta") , resultSet.getInt("idtitular"),titularVO.getNombrePersona(),resultSet.getDouble("saldo")};
                tabla.addRow(Filas);
            }
            this.vistaConsulCTAs.jTable1.setModel(tabla);
            resultSet.close();
        }catch(SQLException e){
            System.err.println("Error"+e);
        }

    }

}
