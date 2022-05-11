/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Conexion.Conexion;
import Modelo.VO.CuentaCorrienteVO;
import Modelo.VO.PersonaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class CuentaCorrienteDAO {
    private Connection dbConnection;
    private PreparedStatement preparedStmt;
    private ResultSet resultSet;
    private CuentaCorrienteVO cuenta;

    public CuentaCorrienteDAO () {
        dbConnection = null;
        preparedStmt = null;
        resultSet = null;
        
    }
    
     public int crear (CuentaCorrienteVO cuenta){
        
        int resultado=0;
        try{
        dbConnection = Conexion.getConexion();
        String insertSQL ="INSERT INTO cuenta (numerocuenta,idtitular,saldo)" + " VALUES ( ?, ?, ?)";
        preparedStmt = dbConnection.prepareStatement(insertSQL);
        preparedStmt.setInt (1, cuenta.getNumeroCuenta());
        preparedStmt.setInt (2, cuenta.getIdPersona());
        preparedStmt.setDouble (3, cuenta.getSaldo());
        
        
        
        resultado = preparedStmt.executeUpdate();
        dbConnection.close();
        preparedStmt.close();
        JOptionPane.showMessageDialog(null, "Se ha Creado una nueva cuenta Exitosamente");
        }catch(SQLException e){
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Problemas con la Creación de la cuenta Comuniquese con el Adminstrador");
        }
        return resultado;
        }

    public CuentaCorrienteVO buscar (int idtitular){
        
         CuentaCorrienteVO cuentica = new CuentaCorrienteVO();
        
        try{
            dbConnection = Conexion.getConexion();
            String selectSQL = "SELECT * FROM cuenta WHERE idtitular = ?";
            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idtitular);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                cuentica.setNumeroCuenta(resultSet.getInt("numerocuenta"));
                cuentica.setIdPersona(resultSet.getInt("idtitular"));
                cuentica.setSaldo(resultSet.getDouble("saldo"));
                
            }
            dbConnection.close();
            preparedStmt.close();
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas para buscar la cuenta, comuniquese con el administrador");
        }
        return cuentica;
        
    }
    public CuentaCorrienteVO buscarNC (int numerocuenta){
        
         CuentaCorrienteVO cuentica = new CuentaCorrienteVO();
        
        try{
            dbConnection = Conexion.getConexion();
            String selectSQL = "SELECT * FROM cuenta WHERE numerocuenta = ?";
            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, numerocuenta);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                cuentica.setNumeroCuenta(resultSet.getInt("numerocuenta"));
                cuentica.setIdPersona(resultSet.getInt("idtitular"));
                cuentica.setSaldo(resultSet.getDouble("saldo"));
                
            }
            dbConnection.close();
            preparedStmt.close();
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas para buscar la cuenta, comuniquese con el administrador");
        }
        return cuentica;
        
    }
    public int modificar (CuentaCorrienteVO cuenta){
       int resultado =0;
       try{
           dbConnection = Conexion.getConexion();
           String updateSQL = "UPDATE cuenta SET idtitular = ?, saldo = ?" + " WHERE numerocuenta = ?";
           preparedStmt = dbConnection.prepareStatement(updateSQL);
           
           preparedStmt.setInt(1, cuenta.getIdPersona());
           preparedStmt.setDouble(2, cuenta.getSaldo());
           preparedStmt.setInt(3, cuenta.getNumeroCuenta());
           
           resultado = preparedStmt.executeUpdate();
           dbConnection.close();
           preparedStmt.close();

       }catch(SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas para modificar la cuenta comuniquese con el administrador");

       }
       /*if(resultado==1){
           JOptionPane.showMessageDialog(null, "se ha modificado");
       }else{
           JOptionPane.showMessageDialog(null, " problemas para modificar ");
       }*/
       return resultado;
        
    }

    public int borrar (int numerocuenta){
        int resultado =0;
        try{
            dbConnection = Conexion.getConexion();
            String deleteSQL = "DELETE FROM cuenta WHERE numerocuenta=?";
            preparedStmt = dbConnection.prepareStatement(deleteSQL);
            preparedStmt.setInt(1, numerocuenta);
            resultado = preparedStmt.executeUpdate();
            
        }catch(SQLException e){
         System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, "problemas para borrar la cuenta, comunicarse con el administrador");
    
        }
        if(resultado == 1){
         JOptionPane.showMessageDialog(null, "se ha eliminado la cuenta");
            
        }else {
         JOptionPane.showMessageDialog(null, "no se ha eliminado la cuenta");

        }
        return resultado;
    }
    
    public ResultSet mostrarTodo (){
        
        try{
            dbConnection = Conexion.getConexion();
            Statement orden = dbConnection.createStatement();
            resultSet = orden.executeQuery("Select* From cuenta");
            
            return resultSet;
            
            
        }catch(SQLException e){
         System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, "problemas con la consulta");
         return null;
        }
        
    }
    
    /*public List<CuentaCorrienteVO> readAll() {
        List<CuentaCorrienteVO> listCuentas = new ArrayList();
        try {
            dbConnection = Conexion.getConexion();

            String selectSQL = "SELECT * FROM cuenta";

            preparedStmt = dbConnection.prepareStatement(selectSQL);

            resultSet = preparedStmt.executeQuery();

            CuentaCorrienteVO cuentaAll;

            while (resultSet.next()) {
                cuentaAll = new PersonaVO();
                cuentaAll.setIdPersona(resultSet.getInt("idpersona"));
                cuentaAll.setNombrePersona(resultSet.getString("nombrepersona"));
                cuentaAll.setDireccionCasa(resultSet.getString("direccion"));
                cuentaAll.setTelefonoMovil(resultSet.getString("telefono"));

             

                listTitulares.add(titularAll);
            }
            dbConnection.close();
            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas en la Consulta Comuniquese con el Administrador ");
        }
        return listTitulares;
    }*/

    }
    

