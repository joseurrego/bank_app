/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Conexion.Conexion;
import Modelo.VO.MovimientosVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import vistas.VistaMovimientos;

/**
 *
 * @author Home
 */
public class MovimientosDAO {
    
    private Connection dbConnection;
    private PreparedStatement preparedStmt;
    private ResultSet resultSet;
    private MovimientosVO movVO;

    public MovimientosDAO() {
        dbConnection = null;
        preparedStmt = null;
        resultSet = null;
           
    }
    
    public int crearMov (MovimientosVO movimiento){
        int resultado=0;
        try{
        dbConnection = Conexion.getConexion();
        String insertSQL ="INSERT INTO movimientos (numerocuenta,tipomovimiento,valor,lugar,fecha)" + " VALUES ( ?, ?, ?, ?, ?)";
        preparedStmt = dbConnection.prepareStatement(insertSQL);
        preparedStmt.setInt (1, movimiento.getNumeroCuenta());
        preparedStmt.setString (2, movimiento.getTipoMovimiento());
        preparedStmt.setDouble (3, movimiento.getValor());
        preparedStmt.setString (4, movimiento.getLugar());
        preparedStmt.setDate (5, movimiento.getFecha());
        
        resultado=preparedStmt.executeUpdate();
        dbConnection.close();
        preparedStmt.close();
        JOptionPane.showMessageDialog(null, "Se ha Creado una nuevo movimiento Exitosamente");
        }catch(SQLException e){
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Problemas con la Creación del movimiento, Comuniquese con el Adminstrador");
        }
        return resultado;
       
    }
    
    public ResultSet buscar (int numerocuenta){
        
         //MovimientosVO movimiento = new MovimientosVO();
        
        try{
            dbConnection = Conexion.getConexion();
            String selectSQL ="SELECT * FROM movimientos WHERE numerocuenta = ?" ;
            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setLong(1, numerocuenta);
            resultSet= preparedStmt.executeQuery();
            return resultSet;
            
            //String selectSQL = "SELECT * FROM Movimientos WHERE numerocuenta = ?";
            //preparedStmt = dbConnection.prepareStatement(selectSQL);
            //preparedStmt.setInt(1, numerocuenta);
            
           
            
           /* while (resultSet.next()) {
                movimiento.setNumeroCuenta(resultSet.getInt("numerocuenta"));
                movimiento.setTipoMovimiento(resultSet.getString("tipomovimiento"));
                movimiento.setValor(resultSet.getDouble("valor"));
                movimiento.setLugar(resultSet.getString("lugar"));
                movimiento.setFecha(resultSet.getDate("fecha"));
                
            }*/
            //dbConnection.close();
            //preparedStmt.close();
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas para buscar los movimientos ");
            return null;
        }
        
        
    }
    public ResultSet mostrarMovs (int numerocuenta){
        
        try{
            dbConnection = Conexion.getConexion();
            String selectSQL ="SELECT * FROM movimientos WHERE numerocuenta = ?" ;
            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setLong(1, numerocuenta);
            resultSet = preparedStmt.executeQuery();
            
            
            return resultSet;
            
            
        }catch(SQLException e){
         System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, "problemas con la consulta");
         return null;
        }
        
    }
    
    
    
    
}
