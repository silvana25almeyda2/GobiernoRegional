/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MYS1
 */
public class Conexion_siga {
    
    static String servidor_siga="";
    static String puerto_siga="";
    static String user_siga="";
    static String password_siga="";
    static String baseDatos_siga="";  

    public void leerTexto(String direccion){
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String bfRead;
            int cont = 0;
            while((bfRead = bf.readLine()) != null){
                cont = cont + 1;
                if (cont ==1){
                    servidor_siga = "" + bfRead + "";
                }
                if (cont ==2){
                    puerto_siga = "" + bfRead + "";
                }
                if (cont ==3){
                    user_siga = "" + bfRead + "";
                }
                if (cont ==4){
                    password_siga = "" + bfRead + "";
                }
                if (cont ==5){
                    baseDatos_siga = "" + bfRead + "";
                }
            }
        } catch (Exception e) {
            System.out.println("No se encontró el archivo");
        }
    }
    
    Connection conexion=null;
       
     public Connection conectar(){
        try{
            leerTexto("C:\\SOF\\conexion_siga.txt");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://"+servidor_siga+":"+puerto_siga+";"+"databaseName="+baseDatos_siga
                    +";user="+user_siga+";password="+password_siga;
            conexion=DriverManager.getConnection(url);
//             System.out.println("Conexión exitosa");
        }
        catch(Exception ex)
        {   
            JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor");
            System.out.println("Error de conexion: " + ex.getMessage());
        }
        return conexion;
    }
     
     public ResultSet Listar(String cad){
        try {
            String url="jdbc:sqlserver://"+servidor_siga+":"+puerto_siga+";"+"databaseName="+baseDatos_siga
                    +";user="+user_siga+";password="+password_siga;
            conexion=DriverManager.getConnection(url);
            
            PreparedStatement da=conexion.prepareStatement(cad);
            ResultSet tbl=da.executeQuery();
            return tbl;
        } catch (Exception e) {
            return null;
        }
    }
    
//    public static void main(String[] args) throws SQLException {
//        Conexion conexion = new Conexion();
//        Connection con = conexion.conectar();
//       
//    }
    
}
