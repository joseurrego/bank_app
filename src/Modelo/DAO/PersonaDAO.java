/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Conexion.Conexion;
import Modelo.VO.PersonaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Home
 */
public class PersonaDAO {
    
    private Connection dbConnection;
    private PreparedStatement preparedStmt;
    private ResultSet resultSet;
    private PersonaVO titular;

    public PersonaDAO() {
        dbConnection = null;
        preparedStmt = null;
        resultSet = null;
        
    }

    
    
    public int crear (PersonaVO persona){
        
        int resultado=0;
        try{
        dbConnection = Conexion.getConexion();
        String insertSQL ="INSERT INTO titular (idpersona,nombrepersona,direccion,telefono)" + " VALUES (?,?,?,?)";
        preparedStmt = dbConnection.prepareStatement(insertSQL);
        preparedStmt.setInt (1, persona.getIdPersona());
        preparedStmt.setString (2, persona.getNombrePersona());
        preparedStmt.setString (3, persona.getDireccionCasa());
        preparedStmt.setString (4, persona.getTelefonoMovil());
        
        
        resultado = preparedStmt.executeUpdate();
        dbConnection.close();
        preparedStmt.close();
        JOptionPane.showMessageDialog(null, "Se ha Creado un Nuevo titular Exitosamente");
        }catch(SQLException e){
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Problemas con la Creación del titular Comuniquese con el Adminstrador");
        }
        return resultado;
        }

    public PersonaVO buscar (int idpersona){
        
        PersonaVO titular = new PersonaVO();
        
        try{
            dbConnection = Conexion.getConexion();
            String selectSQL = "SELECT * FROM titular WHERE idpersona = ?";
            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idpersona);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                titular.setIdPersona(resultSet.getInt("idpersona"));
                titular.setNombrePersona(resultSet.getString("nombrepersona"));
                titular.setDireccionCasa(resultSet.getString("Direccion"));
                titular.setTelefonoMovil(resultSet.getString("telefono"));
            }
            dbConnection.close();
            preparedStmt.close();
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas para buscar el titular, comuniquese con el administrador");
        }
        return titular;
        
    }
    public int modificar (PersonaVO persona){
       int resultado =0;
       try{
           dbConnection = Conexion.getConexion();
           String updateSQL = "UPDATE titular SET nombrepersona = ?, direccion = ?, telefono = ?" + " WHERE idpersona = ?";
           preparedStmt = dbConnection.prepareStatement(updateSQL);
           
           preparedStmt.setString(1, persona.getNombrePersona());
           preparedStmt.setString(2, persona.getDireccionCasa());
           preparedStmt.setString(3, persona.getTelefonoMovil());
           preparedStmt.setInt(4, persona.getIdPersona());
           resultado = preparedStmt.executeUpdate();
           dbConnection.close();
           preparedStmt.close();

       }catch(SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas para modificar el titular comuniquese con el administrador");

       }
       if(resultado==1){
           JOptionPane.showMessageDialog(null, "se ha modificado");
       }else{
           JOptionPane.showMessageDialog(null, " problemas para modificar ");
       }
       return resultado;
        
    }

    public int borrar (int idpersona){
        int resultado =0;
        try{
            dbConnection = Conexion.getConexion();
            String deleteSQL = "DELETE FROM titular WHERE idpersona=?";
            preparedStmt = dbConnection.prepareStatement(deleteSQL);
            preparedStmt.setInt(1, idpersona);
            resultado = preparedStmt.executeUpdate();
            
        }catch(SQLException e){
         System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, "problemas para borrar un titular, comunicarse con el administrador");
    
        }
        if(resultado == 1){
         JOptionPane.showMessageDialog(null, "se ha eliminado el titular");
            
        }else{
         JOptionPane.showMessageDialog(null, "no se ha eliminado el cliente");

        }
        return resultado;
    }
    
     public List<PersonaVO> readAll() {
        List<PersonaVO> listTitulares = new ArrayList();
        try {
            dbConnection = Conexion.getConexion();

            String selectSQL = "SELECT * FROM titular";

            preparedStmt = dbConnection.prepareStatement(selectSQL);

            resultSet = preparedStmt.executeQuery();

            PersonaVO titularAll;

            while (resultSet.next()) {
                titularAll = new PersonaVO();
                titularAll.setIdPersona(resultSet.getInt("idpersona"));
                titularAll.setNombrePersona(resultSet.getString("nombrepersona"));
                titularAll.setDireccionCasa(resultSet.getString("direccion"));
                titularAll.setTelefonoMovil(resultSet.getString("telefono"));

             

                listTitulares.add(titularAll);
            }
            dbConnection.close();
            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas en la Consulta Comuniquese con el Administrador ");
        }
        return listTitulares;
    }
}


    

