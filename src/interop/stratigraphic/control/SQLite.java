/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.stratigraphic.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class SQLite 
{
    
    public void connect()
    {
        Connection conn = null;
        
       String dbPath = "jdbc:sqlite:Coreledge.db";
       
        try {
            conn = DriverManager.getConnection(dbPath);
            System.out.println("conectou");
            
            String sql = "SELECT * FROM TYPE_LITHOLOGY WHERE PETRO_ID = 1";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                System.out.println(rs.getString("VALUE_ENUS"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
