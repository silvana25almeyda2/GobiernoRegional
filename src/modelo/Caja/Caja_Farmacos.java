/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import Servicios.Conexion;
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

    public void LISTA_FARMACOS(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Ítem"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_LISTAR_FARMACOS ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
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
        tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
        tabla.setRowHeight(40);
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
    
}
