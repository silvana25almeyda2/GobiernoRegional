/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import Servicios.Conexion;
import Vistas.Caja.Caja_Farmacia;
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
public class Caja_Farmacos {
DefaultTableModel m;
private Connection cn;
Conexion con = new Conexion(); 
private int ID_CPT ;
private int ID_GRUPO;

    public boolean CAMBIO_GRUPO(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_ACTUALIZAR_GRUPO_FARMACO ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_CPT());
            cmd.setInt(2, getID_GRUPO());
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

    public void LISTA_FARMACOS(String Descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Ítem","Igv","Precio Referencial","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_LISTAR_FARMACOS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            Formato(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR FARMACOS: " + e.getMessage());
        }
    }
    
    public void Formato(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.setRowHeight(40);
    }
    
    public void DATOS_FARMACOS(String usu){
        try {
            String consulta = "exec CAJA_CARGAR_CODIGO_FARMACO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
        if(r.next()){
               Caja_Farmacia.lblID_GRUPO.setText(r.getString(1));
        }
        }catch(Exception ex){
            System.out.println("ERROR AL CARGAR DATOS " + ex.getMessage());
        }
    }
    
    public Caja_Farmacos(){
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
    
    
    
}
