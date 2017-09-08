/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Servicios.Conexion;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author MYS1
 */
public class Caja_Cta6 {
private Connection cn;
DefaultTableModel m;
//CUENTA 6
private String id_cuenta6 ;
private String id_cuenta5 ;
private String cuenta_6 ;
private String descripcion ;
private String nom_usu ;
Conexion con = new Conexion();  
public boolean NuevaCTA6()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_Cta6_INSERTAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta5());
            cmd.setString(2, getCuenta_6());
            cmd.setString(3, getDescripcion());
            cmd.setString(4, getNom_usu());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }


public boolean modificarCta6(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Cta6_Actualizar ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta6());
            cmd.setString(2, getId_cuenta5());
            cmd.setString(3, getCuenta_6());
            cmd.setString(4, getDescripcion());
            cmd.setString(5, getNom_usu());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }

public boolean eliminarCTA6(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_Cta6_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta6());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminar: " + ex.getMessage());
        }
        return resp;
    }


public void LISTA_CTA6(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Cuenta","Descripción","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CTA7_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            Formato(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR : " + e.getMessage());
        }
    }
    
    public void Formato(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.setRowHeight(40);
    }


 public Caja_Cta6(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }  

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getId_cuenta6() {
        return id_cuenta6;
    }

    public void setId_cuenta6(String id_cuenta6) {
        this.id_cuenta6 = id_cuenta6;
    }

    public String getId_cuenta5() {
        return id_cuenta5;
    }

    public void setId_cuenta5(String id_cuenta5) {
        this.id_cuenta5 = id_cuenta5;
    }

    public String getCuenta_6() {
        return cuenta_6;
    }

    public void setCuenta_6(String cuenta_6) {
        this.cuenta_6 = cuenta_6;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
 
}
