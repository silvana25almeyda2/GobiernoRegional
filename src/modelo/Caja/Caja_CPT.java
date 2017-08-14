/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import Servicios.Conexion;
import Vistas.Caja.Caja_CPTS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MYS1
 */
public class Caja_CPT {
DefaultTableModel m;
private Connection cn;
Conexion con = new Conexion(); 
private int ID_CPT;
private int ID_GRUPO;
private int ID_Cuenta6;
private String CPT;
private String DESCRIPCION;
private String USUARIO;

    public boolean NUEVO_CPT(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CPT_NUEVO ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_GRUPO());
            cmd.setInt(2, getID_Cuenta6());
            cmd.setString(3, getCPT());
            cmd.setString(4, getDESCRIPCION());
            cmd.setString(5, getUSUARIO());
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
    
    public boolean MODIFICAR_CPT(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CPT_MODIFICAR ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_CPT());
            cmd.setInt(2, getID_GRUPO());
            cmd.setInt(3, getID_Cuenta6());
            cmd.setString(4, getCPT());
            cmd.setString(5, getDESCRIPCION());
            cmd.setString(6, getUSUARIO());
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
    public boolean ELIMINAR_CPT(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CPT_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_CPT());
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
    
    public void DATOS_GRUPO(String usu){
        try {
            String consulta = "exec CAJA_GRUPO_DATOS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
        if(r.next()){
               Caja_CPTS.txtGrupo.setText(r.getString(2));
               Caja_CPTS.lblIDGRUPO.setText(r.getString(1));
        }
        }catch(Exception ex){
            System.out.println("ERROR AL CARGAR DATOS " + ex.getMessage());
        }
    }
    
    public void LISTA_CPT(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Grupo","Cuenta 6","CPT","IDGRUPO","IDCT6","NCTA6","DCT6","CGRUPO","CCPT","DCPT"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[11];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CPT_LISTAR ?";
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
                fila[8]=r.getString(9);
                fila[9]=r.getString(10); 
                fila[10]=r.getString(11);
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
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(400);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(600);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(10).setMinWidth(0);
        tabla.getColumnModel().getColumn(10).setMaxWidth(0);
        tabla.setRowHeight(40);
    }


    public Caja_CPT(){
            Conexion con = new Conexion();
            cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getID_CPT() {
        return ID_CPT;
    }

    public void setID_CPT(int ID_CPT) {
        this.ID_CPT = ID_CPT;
    }

    public int getID_GRUPO() {
        return ID_GRUPO;
    }

    public void setID_GRUPO(int ID_GRUPO) {
        this.ID_GRUPO = ID_GRUPO;
    }

    public int getID_Cuenta6() {
        return ID_Cuenta6;
    }

    public void setID_Cuenta6(int ID_Cuenta6) {
        this.ID_Cuenta6 = ID_Cuenta6;
    }

    public String getCPT() {
        return CPT;
    }

    public void setCPT(String CPT) {
        this.CPT = CPT;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }
    
    
}
