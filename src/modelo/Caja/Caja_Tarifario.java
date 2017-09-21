/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import Servicios.Conexion;
import Vistas.Caja.Caja_Precios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrador
 */
public class Caja_Tarifario {
private Connection cn;
private int ID_PRECIO;  
private int ID_FORMA_PAGO;
private int ID_CPT;  
private double PRECIO;  
private String Usuario;
DefaultTableModel m;
Conexion con = new Conexion();


    public String DATOS_FOR_PAGO(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT ID_FORMA_PAGO FROM CAJA_FORMA_PAGO WHERE DESCIPCION = ?";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod PROVINCIA: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean NUEVO_PRECIO(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_PRECIOS_NUEVO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_FORMA_PAGO());
            cmd.setInt(2, getID_CPT());
            cmd.setDouble(3, getPRECIO());
            cmd.setString(4, getUsuario());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL REGISTRAR  " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean ACTUALIZAR_PRECIO(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_PRECIOS_ACTUALIZAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_PRECIO());
            cmd.setInt(2, getID_FORMA_PAGO());
            cmd.setInt(3, getID_CPT());
            cmd.setDouble(4, getPRECIO());
            cmd.setString(5, getUsuario());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL ACTUALIZAR  " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean ELIMINAR_PRECIO(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_PRECIOS_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_PRECIO());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL ELIMINAR  " + ex.getMessage());
        }
        return resp;
    }
    
    public int VALIDAR_PRECIO(String nombre,String CPT){
        int resultado=0;
        try
        {
            String sql = "EXEC CAJA_PRECIO_VALIDADO ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
            cmd.setString(2, CPT);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            //getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error verificacion repetidos: " + ex.getMessage());
        }
        return resultado;
    }
    
    public void LISTAR_PERMISOS(String usu){
        String consulta="";
        try {
            consulta="EXEC CAJA_VERIFICAR_NIVEL_USUARIO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Precios.lblNivel.setText(r.getString(1)); 
                if(r.getString(2).equals("X")){
                    Caja_Precios.lblPermiso.setText("L"); 
                }else   if(r.getString(3).equals("X")){
                    Caja_Precios.lblPermiso.setText("E"); 
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR EL PERMISOS: " + e.getMessage());
        }
    }
    
    public void LISTA_ITEMS(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Descripción","IGV","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_PRECIO_ITEMS ?";
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
            System.out.println("ERROR AL LISTAR ITEMS: " + e.getMessage());
        }
    }
    
    public void Formato(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.setRowHeight(40);
    }
    
    public void LISTA_PRECIOS(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Forma de Pago","Grupo","Ítem","Precio","ID_FP","ID_CPT","AFECTO"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_PRECIOS_LISTA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); 
                fila[7]=r.getString(8);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            Formato_PRECIO(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR ITEMS: " + e.getMessage());
        }
    }
    public void Formato_PRECIO(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(380);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.setRowHeight(40);
    }


    public Caja_Tarifario(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    

    public int getID_PRECIO() {
        return ID_PRECIO;
    }

    public void setID_PRECIO(int ID_PRECIO) {
        this.ID_PRECIO = ID_PRECIO;
    }

    public int getID_FORMA_PAGO() {
        return ID_FORMA_PAGO;
    }

    public void setID_FORMA_PAGO(int ID_FORMA_PAGO) {
        this.ID_FORMA_PAGO = ID_FORMA_PAGO;
    }

    public int getID_CPT() {
        return ID_CPT;
    }

    public void setID_CPT(int ID_CPT) {
        this.ID_CPT = ID_CPT;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    
    

}
