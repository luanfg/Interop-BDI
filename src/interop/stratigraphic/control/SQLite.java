/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.stratigraphic.control;

import interop.stratigraphic.model.AttributeType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the interface between the database and the model. 
 * @author Luan
 */
public class SQLite 
{
   
    private Connection connect()
    {
       Connection conn = null;
        
       String dbPath = "jdbc:sqlite:C:\\StrataDB\\Coreledge.db";
       
        try 
        {
            
            conn = DriverManager.getConnection(dbPath);                      
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
    
    public String readValue(AttributeType type, int code) 
    {
        String valueOfATuple = null;
                
        Connection conn = connect();
        

        String sql = "SELECT * FROM " + type.toString() + " WHERE PETRO_ID = " + code;

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                valueOfATuple = rs.getString("VALUE_ENUS");
            }
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try 
            {                
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valueOfATuple;
    }
    
    public String readValue(AttributeType table, String column, int code) 
    {
        String valueOfATuple = null;
                
        Connection conn = connect();
        

        String sql = "SELECT * FROM " + table.toString() + " WHERE PETRO_ID = " + code;

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                valueOfATuple = rs.getString(column);
            }
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try 
            {                
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valueOfATuple;
    }
}
