/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Caja.Caja_cuentas;
import modelo.Caja.Caja_Cta2;
import modelo.Caja.Caja_Cta3;
import modelo.Caja.Caja_Cta4;
import modelo.Caja.Caja_Cta5;
import modelo.Caja.Caja_Cta6;
import Servicios.Conexion;

/**
 *
 * @author Ricardo
 */
public class Caja_Transaccion extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
byte tge;
byte tga;

    /**
     * Creates new form Caja_Transaccion
     */
Caja_cuentas cnn = new Caja_cuentas();
Caja_Cta2 cn2 = new Caja_Cta2();
Caja_Cta3 cn3 = new Caja_Cta3();
Caja_Cta4 cn4 = new Caja_Cta4();
Caja_Cta5 cn5 = new Caja_Cta5();
Caja_Cta6 cn6 = new Caja_Cta6();

    public Caja_Transaccion() {
       initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        LlenarTransCT2.setLocationRelativeTo(null);//en el centro
        LlenarTransCT2.getContentPane().setBackground(Color.WHITE); 
        LlenarGenericaCT3.setLocationRelativeTo(null);//en el centro
        LlenarGenericaCT3.getContentPane().setBackground(Color.WHITE); 
        LlenarSubGenericaCT4.setLocationRelativeTo(null);//en el centro
        LlenarSubGenericaCT4.getContentPane().setBackground(Color.WHITE); 
        LlenarSubGenericaDetalleCT5.setLocationRelativeTo(null);//en el centro
        LlenarSubGenericaDetalleCT5.getContentPane().setBackground(Color.WHITE); 
        LlenarEspecifica.setLocationRelativeTo(null);//en el centro
        LlenarEspecifica.getContentPane().setBackground(Color.WHITE); 
        
        
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        cargareliminar.setVisible(false);

        bc.setBackground(java.awt.Color.gray);
        LISTAR_cta1_CT2();
        LISTAR_cta2_CT3();
        LISTAR_cta3_CT4();
        LISTAR_cta4_CT5();
        LISTAR_cta5_CT6();
        LISTAR_CT6();
        
        txtcodT.setVisible(false);
        txtcodG.setVisible(false);
        txtcodsubgen.setVisible(false);
        txtcodGD.setVisible(false);
        txtcodE.setVisible(false);
        txtcodEd.setVisible(false);
        
 
 
    }
    public void CompleteDatos(){
        cargareliminar.setVisible(true);
        cargareliminar.setBackground(new Color(255,91,70)); 
        Mensaje.setText("Complete Todos los Datos antes de Guardar");
        eli.setVisible(false);
        noeli.setVisible(false);
    }
    public void OcurrioError(){
        cargareliminar.setVisible(true);
        cargareliminar.setBackground(new Color(255,91,70)); 
        Mensaje.setText("Ocurrió un error, Verifique");
        eli.setVisible(false);
        noeli.setVisible(false);
    }
    public void DatosGuardados(){
        cargareliminar.setVisible(true);
        cargareliminar.setBackground(new Color(0,153,102)); 
        Mensaje.setText("Datos Guardados de forma correcta");
        eli.setText("OK");
        eli.setVisible(true);
        noeli.setVisible(false);
        tge=1;
        LISTAR_cta1_CT2();
        LISTAR_cta2_CT3();
        LISTAR_cta3_CT4();
        LISTAR_cta4_CT5();
        LISTAR_cta5_CT6();
        LISTAR_CT6();
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
    }
    public void DatosActualizados(){
        cargareliminar.setVisible(true);
        cargareliminar.setBackground(new Color(0,153,102)); 
        Mensaje.setText("Datos Actualizados de forma correcta");
        noeli.setText("OK");
        eli.setVisible(false);
        noeli.setVisible(true);
        tge=9;
        LISTAR_cta1_CT2();
        LISTAR_cta2_CT3();
        LISTAR_cta3_CT4();
        LISTAR_cta4_CT5();
        LISTAR_cta5_CT6();
        LISTAR_CT6();
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
    }
    public void MensajeEliminado(){
        cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setText("OK");
                                noeli.setVisible(false);
                                tge=7;
                                 LISTAR_cta1_CT2();
        LISTAR_cta2_CT3();
        LISTAR_cta3_CT4();
        LISTAR_cta4_CT5();
        LISTAR_cta5_CT6();
        LISTAR_CT6();
    }
    public void OcurrioErrorEliminar(){
        cargareliminar.setVisible(true);
        cargareliminar.setBackground(new Color(255,91,70)); 
        Mensaje.setText("Ocurrió un error, Puede que Esta Cuenta tenga Sub Cuentas. Eliminelas Antes");
        eli.setVisible(false);
        noeli.setVisible(false);
    }
    public void EliminarCta1(){
         try{
            if(tge==6 ){
                Caja_cuentas hCEl = new Caja_cuentas();
                hCEl.setId_cuenta1(txtcodT.getText());
                if(hCEl.eliminarCTA1()==true){
                                MensajeEliminado();
                }else
                  OcurrioErrorEliminar();  
            } 
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
    }
    public void EliminarCta2(){
         try{
            if(tge==6 ){
                Caja_Cta2 hCEl = new Caja_Cta2();
                hCEl.setId_cuenta2(txtcodG.getText());
                if(hCEl.eliminarCTA2()==true){
                                MensajeEliminado();
                }else
                  OcurrioErrorEliminar();  
            } 
        }catch(Exception e){
            System.out.println("Error eliminar Cta2: " + e.toString());
        }
    }
    public void EliminarCta3(){
         try{
            if(tge==6 ){
                Caja_Cta3 hCEl = new Caja_Cta3();
                hCEl.setId_cuenta3(txtcodsubgen.getText());
                if(hCEl.eliminarCTA3()==true){
                                MensajeEliminado();
                }else
                  OcurrioErrorEliminar();  
            } 
        }catch(Exception e){
            System.out.println("Error eliminar Cta3: " + e.toString());
        }
    }
    public void EliminarCta4(){
         try{
            if(tge==6 ){
                Caja_Cta4 hCEl = new Caja_Cta4();
                hCEl.setId_cuenta4(txtcodGD.getText());
                if(hCEl.eliminarCTA4()==true){
                                MensajeEliminado();
                }else
                  OcurrioErrorEliminar();  
            } 
        }catch(Exception e){
            System.out.println("Error eliminar Cta4: " + e.toString());
        }
    }
    public void EliminarCta5(){
         try{
            if(tge==6 ){
                Caja_Cta5 hCEl = new Caja_Cta5();
                hCEl.setId_cuenta5(txtcodE.getText());
                if(hCEl.eliminarCTA5()==true){
                                MensajeEliminado();
                }else
                  OcurrioErrorEliminar();  
            } 
        }catch(Exception e){
            System.out.println("Error eliminar Cta5: " + e.toString());
        }
    }
    public void EliminarCta6(){
         try{
            if(tge==6 ){
                Caja_Cta6 hCEl = new Caja_Cta6();
                hCEl.setId_cuenta6(txtcodEd.getText());
                if(hCEl.eliminarCTA6()==true){
                                MensajeEliminado();
                }else
                  OcurrioErrorEliminar();  
            } 
        }catch(Exception e){
            System.out.println("Error eliminar Cta6: " + e.toString());
        }
    }
    
    //BUSCAR CUENTA 1 EN CUENTA 2
    public void BuscarCta1_CTA2(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_cuentas obj=new Caja_cuentas();
                    consulta="exec Caja_Cta1_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);

                m.addRow(fila);
                c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);

            formatoTipoCt1_CT2();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
     //BUSCAR CUENTA 1 EN CUENTA 2
    public void BuscarCta1_CTA2EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Cta1.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_cuentas obj=new Caja_cuentas();
                    consulta="exec Caja_Cta1_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);

                m.addRow(fila);
                c++;
            }
            tb_Cta1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Cta1.setRowSorter(elQueOrdena);
            this.tb_Cta1.setModel(m);

            formatoTipoCt1_CT2();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 2 EN CUENTA 3
    public void BuscarCta2_CTA3(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta2 obj=new Caja_Cta2();
                    consulta="exec Caja_Cta2_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            formatoTipoCt2_CT3();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
  
    public void BuscarCta2EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Cta2.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta2 obj=new Caja_Cta2();
                    consulta="exec Caja_Cta2_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Cta2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Cta2.setRowSorter(elQueOrdena);
            this.tb_Cta2.setModel(m);
            formatoTipoCt2_CT3();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 3 EN CUENTA 4
    public void BuscarCta3_CTA4(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo2.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta3 obj=new Caja_Cta3();
                    consulta="exec Caja_Cta3_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar2.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
               formatoTipoCt3_CT4();
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 3 EN CUENTA 4
    public void BuscarCta3EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Cta3.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta3 obj=new Caja_Cta3();
                    consulta="exec Caja_Cta3_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
           
            tb_Cta3.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Cta3.setRowSorter(elQueOrdena);
            this.tb_Cta3.setModel(m);
          formatoTipoCt3_CT4();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
  
    //BUSCAR CUENTA 4 EN CUENTA 5
    public void BuscarCta4_CTA5(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo3.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta4 obj=new Caja_Cta4();
                    consulta="exec Caja_Cta4_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar3.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo3.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo3.setRowSorter(elQueOrdena);
            this.tb_Grupo3.setModel(m);
            formatoTipoCt4_CT5();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 4 EN CUENTA 5
    public void BuscarCta4EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Cta4.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta4 obj=new Caja_Cta4();
                    consulta="exec Caja_Cta4_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Cta4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Cta4.setRowSorter(elQueOrdena);
            this.tb_Cta4.setModel(m);
            formatoTipoCt4_CT5();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 5 EN CUENTA 6
    public void BuscarCta5_CTA6(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo4.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta5 obj=new Caja_Cta5();
                    consulta="exec Caja_Cta5_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar4.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);
                m.addRow(fila);
                c++;
            }
            tb_Grupo4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo4.setRowSorter(elQueOrdena);
            this.tb_Grupo4.setModel(m);
            formatoTipoCt5_CT6();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 5 EN CUENTA 6
    public void BuscarCta5EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Cta5.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta5 obj=new Caja_Cta5();
                    consulta="exec Caja_Cta5_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Cta5.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Cta5.setRowSorter(elQueOrdena);
            this.tb_Cta5.setModel(m);
            formatoTipoCt5_CT6();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 4 EN CUENTA 5
    public void BuscarCta6EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Cta6.setModel(new DefaultTableModel());
            String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta6 obj=new Caja_Cta6();
                    consulta="exec Caja_Cta6_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Cta6.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Cta6.setRowSorter(elQueOrdena);
            this.tb_Cta6.setModel(m);
            formatoTipoCT6();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void LISTAR_cta1_CT2(){
    try {
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta1_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
  
                    m.addRow(fila);
                    c++;
            }
            
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);
            
            tb_Cta1.setModel(m);
            tb_Cta1.setRowSorter(elQueOrdena);
            this.tb_Cta1.setModel(m);
            formatoTipoCt1_CT2();
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta2_CT3(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta2_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4);
  
                    m.addRow(fila);
                    c++;
            }
          
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            
            tb_Cta2.setModel(m);
            tb_Cta2.setRowSorter(elQueOrdena);
            this.tb_Cta2.setModel(m);
            formatoTipoCt2_CT3();
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta3_CT4(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta3_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);
         
            tb_Cta3.setModel(m);
            tb_Cta3.setRowSorter(elQueOrdena);
            this.tb_Cta3.setModel(m);
            formatoTipoCt3_CT4();
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta4_CT5(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta4_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo3.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo3.setRowSorter(elQueOrdena);
            this.tb_Grupo3.setModel(m);
            
            tb_Cta4.setModel(m);

            tb_Cta4.setRowSorter(elQueOrdena);
            this.tb_Cta4.setModel(m);
            formatoTipoCt4_CT5();
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta5_CT6(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta5_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo4.setRowSorter(elQueOrdena);
            this.tb_Grupo4.setModel(m);
            
            tb_Cta5.setModel(m);
            
            tb_Cta5.setRowSorter(elQueOrdena);
            this.tb_Cta5.setModel(m);
            
            formatoTipoCt5_CT6();
    } catch (Exception e) {
    }
}
    
    public void LISTAR_CT6(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta6_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            tb_Cta6.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Cta6.setRowSorter(elQueOrdena);
            this.tb_Cta6.setModel(m);

            formatoTipoCT6();
    } catch (Exception e) {
    }
}
  
    public void formatoTipoCt1_CT2(){
    tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Grupo.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupo.setRowHeight(40);
    
    tb_Cta1.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Cta1.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Cta1.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Cta1.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Cta1.setRowHeight(40);
}
    
    public void formatoTipoCt2_CT3(){
    tb_Grupo1.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Grupo1.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Grupo1.setRowHeight(40);
    
    tb_Cta2.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Cta2.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Cta2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Cta2.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Cta2.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Cta2.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Cta2.setRowHeight(40);
}
    
    public void formatoTipoCt3_CT4(){
    tb_Grupo2.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Grupo2.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Grupo2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo2.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupo2.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupo2.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Grupo2.setRowHeight(40);
    
    tb_Cta3.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Cta3.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Cta3.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Cta3.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Cta3.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Cta3.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Cta3.setRowHeight(40);
}
    
    public void formatoTipoCt4_CT5(){
    tb_Grupo3.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Grupo3.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Grupo3.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo3.getColumnModel().getColumn(2).setMaxWidth(0);
    
    tb_Cta4.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Cta4.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Cta4.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Cta4.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Cta4.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Cta4.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Cta4.setRowHeight(40);
}
    
    public void formatoTipoCt5_CT6(){
    tb_Grupo4.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Grupo4.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Grupo4.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo4.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupo4.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupo4.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Grupo4.setRowHeight(40);
    
    tb_Cta5.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Cta5.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Cta5.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Cta5.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Cta5.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Cta5.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Cta5.setRowHeight(40);
}
    
    public void formatoTipoCT6(){
    tb_Cta6.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Cta6.getColumnModel().getColumn(1).setPreferredWidth(520);
    tb_Cta6.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Cta6.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Cta6.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Cta6.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Cta6.setRowHeight(40);
}
    
    public void GuardarCta1(){

        if((txttipoT.getText().equals("")) ||  txtdesT.getText().equals("")){
                CompleteDatos();         
        } 
                
                Caja_cuentas cno1 = new Caja_cuentas();
                cno1.setId_cuenta1(txtcodT.getText());//
                cno1.setCuenta_1(txttipoT.getText());//
                cno1.setDescripcion_1(txtdesT.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA1()==true){
                           DatosGuardados();
                           btnguardar.setEnabled(false);
                           txttipoT.setEditable(false);
                           txtdesT.setEditable(false);
                           
                       } else {
                           OcurrioError();
                       }}
    
    public void GuardarCta2(){

        if((txtgen.getText().equals("")) ||  txtdesG.getText().equals("")){
           CompleteDatos();
        } 
                
                Caja_Cta2 cno1 = new Caja_Cta2();
                cno1.setId_cuenta1(lblCT1.getText());//
                cno1.setId_cuenta2(txtcodG.getText());//
                cno1.setCuenta_2(txttrans1.getText()+"."+ txtgen.getText());//
                cno1.setDescripcion(txtdesG.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA2()==true){
                           DatosGuardados();
                           btnguardar.setEnabled(false);
                             txtgen.setEditable(false);
                             txtdesG.setEditable(false);
                       } else {
                           OcurrioError();
                       }}
    
    public void GuardarCta3(){

        if((txtsub.getText().equals("")) ||  txtdesSubG.getText().equals("")){
            CompleteDatos();
        } 
                
                Caja_Cta3 cno1 = new Caja_Cta3();
                cno1.setId_cuenta3(txtcodsubgen.getText());//
                cno1.setId_cuenta2(lblCT2.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_3(txtgen3.getText()+"."+ txtsub.getText());//
                cno1.setDescripcion(txtdesSubG.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA3()==true){
                           DatosGuardados();
                              btnguardar.setEnabled(false);
                             txtsub.setEditable(false);
                             txtdesSubG.setEditable(false);
                           
                       } else {
                           OcurrioError();
                       }}
    
    public void GuardarCta4(){

        if((txtgend.getText().equals("")) ||  txtdesGd.getText().equals("")){
            CompleteDatos();
        } 
                
                Caja_Cta4 cno1 = new Caja_Cta4();
                cno1.setId_cuenta4(txtcodGD.getText());//
                cno1.setId_cuenta3(lblCT3.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_4(txtsuggend.getText()+"."+ txtgend.getText());//
                cno1.setDescripcion(txtdesGd.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA4()==true){
                           DatosGuardados();
                            btnguardar.setEnabled(false);
                             txtgend.setEditable(false);
                             txtdesGd.setEditable(false);
                       } else {
                           OcurrioError();
                       }}
    
    public void GuardarCta5(){

        if((txtespe.getText().equals("")) ||  txtdesE.getText().equals("")){
            CompleteDatos();
        } 
                
                Caja_Cta5 cno1 = new Caja_Cta5();
                cno1.setId_cuenta5(txtcodE.getText());//
                cno1.setId_cuenta4(lblCT4.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_5(txtsgd.getText()+"."+ txtespe.getText());//
                cno1.setDescripcion(txtdesE.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA5()==true){
                           DatosGuardados();
                           btnguardar.setEnabled(false);
                             txtespe.setEditable(false);
                             txtdesE.setEditable(false);
                       } else {
                           OcurrioError();
                       }}
        
    public void GuardarCta6(){

        if((txtespedet.getText().equals("")) ||  txtdesEd.getText().equals("")){
            CompleteDatos();
        } 
                
                Caja_Cta6 cno1 = new Caja_Cta6();
                cno1.setId_cuenta6(txtcodEd.getText());//
                cno1.setId_cuenta5(lblCT5.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_6(txted1.getText()+"."+ txtespedet.getText());//
                cno1.setDescripcion(txtdesEd.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA6()==true){
                           DatosGuardados();
                             btnguardar.setEnabled(false);
                             txtespedet.setEditable(false);
                             txtdesEd.setEditable(false);
                       } else {
                           OcurrioError();
                       }}
        
    public void ModificarCta1(){

      
                        Caja_cuentas cno = new Caja_cuentas();
                        cno.setId_cuenta1(txtcodT.getText());//
                        cno.setCuenta_1(txttipoT.getText());//
                        cno.setDescripcion_1(txtdesT.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta1()==true){
                             DatosActualizados();
                             btnguardar.setEnabled(false);
                             txttipoT.setEditable(false);
                             txtdesT.setEditable(false);
                        } else {
                           
                             OcurrioError();
                        }
                    
    }
        
    public void ModificarCta2(){
       
          
                        Caja_Cta2 cno = new Caja_Cta2();
                        cno.setId_cuenta2(txtcodG.getText());//
                        cno.setId_cuenta1(lblCT1.getText());//
                        cno.setCuenta_2(txttrans1.getText()+"."+ txtgen.getText());//
                        cno.setDescripcion(txtdesG.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta2()==true){
                             DatosActualizados();
                             btnguardar.setEnabled(false);
                             txtgen.setEditable(false);
                             txtdesG.setEditable(false);
                        } else {
                           
                             OcurrioError();
                        }
                       
    }    
    
    public void ModificarCta3(){
       
            
                        Caja_Cta3 cno = new Caja_Cta3();
                        cno.setId_cuenta3(txtcodsubgen.getText());//
                        cno.setId_cuenta2(lblCT2.getText());//
                        cno.setCuenta_3(txtgen3.getText()+"."+ txtsub.getText());//
                        cno.setDescripcion(txtdesSubG.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta3()==true){
                             DatosActualizados();
                             btnguardar.setEnabled(false);
                             txtsub.setEditable(false);
                             txtdesSubG.setEditable(false);
                        } else {
                           
                             OcurrioError();
                        }
                      
    }    
    
    public void ModificarCta4(){
       
          
                        Caja_Cta4 cno = new Caja_Cta4();
                        cno.setId_cuenta4(txtcodGD.getText());//
                        cno.setId_cuenta3(lblCT3.getText());//
                        cno.setCuenta_4(txtsuggend.getText()+"."+ txtgend.getText());//
                        cno.setDescripcion(txtdesGd.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta4()==true){
                             DatosActualizados();
                             btnguardar.setEnabled(false);
                             txtgend.setEditable(false);
                             txtdesGd.setEditable(false);
                        } else {
                           
                             OcurrioError();
                        }
                  
    }    
    
    public void ModificarCta5(){
       
                        Caja_Cta5 cno = new Caja_Cta5();
                        cno.setId_cuenta5(txtcodE.getText());//
                        cno.setId_cuenta4(lblCT4.getText());//
                        cno.setCuenta_5(txtsgd.getText()+"."+ txtespe.getText());//
                        cno.setDescripcion(txtdesE.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta5()==true){
                             DatosActualizados();
                             btnguardar.setEnabled(false);
                             txtespe.setEditable(false);
                             txtdesE.setEditable(false);
                        } else {
                           
                             OcurrioError();
                        }
                        
    }
    
    public void ModificarCta6(){
       
  
                        Caja_Cta6 cno = new Caja_Cta6();
                        cno.setId_cuenta6(txtcodEd.getText());//
                        cno.setId_cuenta5(lblCT5.getText());//
                        cno.setCuenta_6(txted1.getText()+"."+ txtespedet.getText());//
                        cno.setDescripcion(txtdesEd.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta6()==true){
                             DatosActualizados();
                             btnguardar.setEnabled(false);
                             txtespedet.setEditable(false);
                             txtdesEd.setEditable(false);
                        } else {
                           
                             OcurrioError();
                        }
                      
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LlenarTransCT2 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnbuscar1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            LlenarGenericaCT3 = new javax.swing.JDialog();
            jPanel8 = new javax.swing.JPanel();
            jLabel21 = new javax.swing.JLabel();
            btnbuscar2 = new javax.swing.JButton();
            txtBuscar1 = new javax.swing.JTextField();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Grupo1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                LlenarSubGenericaCT4 = new javax.swing.JDialog();
                jPanel9 = new javax.swing.JPanel();
                jLabel29 = new javax.swing.JLabel();
                btnbuscar3 = new javax.swing.JButton();
                txtBuscar2 = new javax.swing.JTextField();
                jScrollPane4 = new javax.swing.JScrollPane();
                tb_Grupo2 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    LlenarSubGenericaDetalleCT5 = new javax.swing.JDialog();
                    jPanel15 = new javax.swing.JPanel();
                    jLabel39 = new javax.swing.JLabel();
                    btnbuscar4 = new javax.swing.JButton();
                    txtBuscar3 = new javax.swing.JTextField();
                    jScrollPane5 = new javax.swing.JScrollPane();
                    tb_Grupo3 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        LlenarEspecifica = new javax.swing.JDialog();
                        jPanel17 = new javax.swing.JPanel();
                        jLabel53 = new javax.swing.JLabel();
                        btnbuscar5 = new javax.swing.JButton();
                        txtBuscar4 = new javax.swing.JTextField();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        tb_Grupo4 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jPanel1 = new javax.swing.JPanel();
                            btnNuevo = new javax.swing.JButton();
                            btneditar = new javax.swing.JButton();
                            btnguardar = new javax.swing.JButton();
                            btneliminar = new javax.swing.JButton();
                            lblusu = new javax.swing.JLabel();
                            jLabel60 = new javax.swing.JLabel();
                            jPanel24 = new javax.swing.JPanel();
                            buscartodo = new javax.swing.JTextField();
                            btnBuscarPaciente = new javax.swing.JButton();
                            lbldetalle = new javax.swing.JLabel();
                            lblBusqueda = new javax.swing.JLabel();
                            lblMant = new javax.swing.JLabel();
                            PaginasCuentas = new javax.swing.JTabbedPane();
                            jPanel3 = new javax.swing.JPanel();
                            bc = new javax.swing.JButton();
                            bc1 = new javax.swing.JButton();
                            bc2 = new javax.swing.JButton();
                            bc3 = new javax.swing.JButton();
                            bc4 = new javax.swing.JButton();
                            bc5 = new javax.swing.JButton();
                            jPanel4 = new javax.swing.JPanel();
                            jLabel10 = new javax.swing.JLabel();
                            jLabel11 = new javax.swing.JLabel();
                            txtcodT = new javax.swing.JTextField();
                            txttipoT = new javax.swing.JTextField();
                            jScrollPane1 = new javax.swing.JScrollPane();
                            txtdesT = new javax.swing.JEditorPane();
                            jScrollPane7 = new javax.swing.JScrollPane();
                            tb_Cta1 = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                jPanel36 = new javax.swing.JPanel();
                                jPanel11 = new javax.swing.JPanel();
                                jLabel15 = new javax.swing.JLabel();
                                jLabel16 = new javax.swing.JLabel();
                                txtcodG = new javax.swing.JTextField();
                                jLabel17 = new javax.swing.JLabel();
                                txttrans1 = new javax.swing.JTextField();
                                jLabel18 = new javax.swing.JLabel();
                                txtgen = new javax.swing.JTextField();
                                lblCT1 = new javax.swing.JLabel();
                                cuentagenerica = new javax.swing.JLabel();
                                panelCPT = new javax.swing.JPanel();
                                txttrans = new javax.swing.JTextField();
                                B1 = new javax.swing.JButton();
                                jScrollPane13 = new javax.swing.JScrollPane();
                                txtdesG = new javax.swing.JEditorPane();
                                jScrollPane8 = new javax.swing.JScrollPane();
                                tb_Cta2 = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    jPanel37 = new javax.swing.JPanel();
                                    jPanel12 = new javax.swing.JPanel();
                                    jLabel42 = new javax.swing.JLabel();
                                    jLabel43 = new javax.swing.JLabel();
                                    txtcodsubgen = new javax.swing.JTextField();
                                    jLabel44 = new javax.swing.JLabel();
                                    txtgen3 = new javax.swing.JTextField();
                                    jLabel45 = new javax.swing.JLabel();
                                    txtsub = new javax.swing.JTextField();
                                    lblCT2 = new javax.swing.JLabel();
                                    sugenericacadena = new javax.swing.JLabel();
                                    panelCPT1 = new javax.swing.JPanel();
                                    txtgen2 = new javax.swing.JTextField();
                                    b2 = new javax.swing.JButton();
                                    jScrollPane14 = new javax.swing.JScrollPane();
                                    txtdesSubG = new javax.swing.JEditorPane();
                                    jScrollPane9 = new javax.swing.JScrollPane();
                                    tb_Cta3 = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};
                                        jPanel38 = new javax.swing.JPanel();
                                        jPanel13 = new javax.swing.JPanel();
                                        jLabel25 = new javax.swing.JLabel();
                                        jLabel26 = new javax.swing.JLabel();
                                        txtcodGD = new javax.swing.JTextField();
                                        jLabel27 = new javax.swing.JLabel();
                                        txtsuggend = new javax.swing.JTextField();
                                        jLabel28 = new javax.swing.JLabel();
                                        txtgend = new javax.swing.JTextField();
                                        lblCT3 = new javax.swing.JLabel();
                                        Subgenericadetalle = new javax.swing.JLabel();
                                        panelCPT2 = new javax.swing.JPanel();
                                        txtsubgen = new javax.swing.JTextField();
                                        B3 = new javax.swing.JButton();
                                        jScrollPane15 = new javax.swing.JScrollPane();
                                        txtdesGd = new javax.swing.JEditorPane();
                                        jScrollPane10 = new javax.swing.JScrollPane();
                                        tb_Cta4 = new javax.swing.JTable(){
                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                return false; //Disallow the editing of any cell
                                            }};
                                            jPanel39 = new javax.swing.JPanel();
                                            jPanel14 = new javax.swing.JPanel();
                                            jLabel32 = new javax.swing.JLabel();
                                            jLabel36 = new javax.swing.JLabel();
                                            txtcodE = new javax.swing.JTextField();
                                            jLabel37 = new javax.swing.JLabel();
                                            txtsgd = new javax.swing.JTextField();
                                            jLabel38 = new javax.swing.JLabel();
                                            txtespe = new javax.swing.JTextField();
                                            lblCT4 = new javax.swing.JLabel();
                                            Especifica = new javax.swing.JLabel();
                                            panelCPT3 = new javax.swing.JPanel();
                                            txtsubgd = new javax.swing.JTextField();
                                            b4 = new javax.swing.JButton();
                                            jScrollPane16 = new javax.swing.JScrollPane();
                                            txtdesE = new javax.swing.JEditorPane();
                                            jScrollPane11 = new javax.swing.JScrollPane();
                                            tb_Cta5 = new javax.swing.JTable(){
                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                    return false; //Disallow the editing of any cell
                                                }};
                                                jPanel40 = new javax.swing.JPanel();
                                                jPanel16 = new javax.swing.JPanel();
                                                jLabel47 = new javax.swing.JLabel();
                                                jLabel48 = new javax.swing.JLabel();
                                                txtcodEd = new javax.swing.JTextField();
                                                jLabel49 = new javax.swing.JLabel();
                                                txted1 = new javax.swing.JTextField();
                                                jLabel50 = new javax.swing.JLabel();
                                                txtespedet = new javax.swing.JTextField();
                                                lblCT5 = new javax.swing.JLabel();
                                                especificadetalle = new javax.swing.JLabel();
                                                panelCPT4 = new javax.swing.JPanel();
                                                txtespe2 = new javax.swing.JTextField();
                                                b6 = new javax.swing.JButton();
                                                jScrollPane17 = new javax.swing.JScrollPane();
                                                txtdesEd = new javax.swing.JEditorPane();
                                                jScrollPane12 = new javax.swing.JScrollPane();
                                                tb_Cta6 = new javax.swing.JTable(){
                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                        return false; //Disallow the editing of any cell
                                                    }};
                                                    jPanel41 = new javax.swing.JPanel();
                                                    jPanel6 = new javax.swing.JPanel();
                                                    jLabel20 = new javax.swing.JLabel();
                                                    jLabel22 = new javax.swing.JLabel();
                                                    jPanel5 = new javax.swing.JPanel();
                                                    lblDescripcion = new javax.swing.JLabel();
                                                    cargareliminar = new javax.swing.JPanel();
                                                    Mensaje = new javax.swing.JLabel();
                                                    eli = new javax.swing.JButton();
                                                    noeli = new javax.swing.JButton();

                                                    LlenarTransCT2.setAlwaysOnTop(true);
                                                    LlenarTransCT2.setMinimumSize(new java.awt.Dimension(450, 430));
                                                    LlenarTransCT2.setResizable(false);

                                                    jPanel7.setBackground(new java.awt.Color(230, 230, 230));

                                                    jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                    jLabel19.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel19.setText("Transacciones");

                                                    btnbuscar1.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                                                    btnbuscar1.setToolTipText("");
                                                    btnbuscar1.setContentAreaFilled(false);
                                                    btnbuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar1.setIconTextGap(30);
                                                    btnbuscar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar1ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscarCaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscarActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                                                    jPanel7.setLayout(jPanel7Layout);
                                                    jPanel7Layout.setHorizontalGroup(
                                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel19)
                                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(235, Short.MAX_VALUE))
                                                    );
                                                    jPanel7Layout.setVerticalGroup(
                                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                                    .addComponent(jLabel19)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Grupo.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo.setRowHeight(35);
                                                    tb_Grupo.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Grupo.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_GrupoMouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_GrupoKeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane2.setViewportView(tb_Grupo);

                                                    javax.swing.GroupLayout LlenarTransCT2Layout = new javax.swing.GroupLayout(LlenarTransCT2.getContentPane());
                                                    LlenarTransCT2.getContentPane().setLayout(LlenarTransCT2Layout);
                                                    LlenarTransCT2Layout.setHorizontalGroup(
                                                        LlenarTransCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane2)
                                                    );
                                                    LlenarTransCT2Layout.setVerticalGroup(
                                                        LlenarTransCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarTransCT2Layout.createSequentialGroup()
                                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                                                    );

                                                    LlenarGenericaCT3.setAlwaysOnTop(true);
                                                    LlenarGenericaCT3.setMinimumSize(new java.awt.Dimension(450, 430));
                                                    LlenarGenericaCT3.setResizable(false);

                                                    jPanel8.setBackground(new java.awt.Color(230, 230, 230));

                                                    jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                    jLabel21.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel21.setText("Genérica");

                                                    btnbuscar2.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                                                    btnbuscar2.setToolTipText("");
                                                    btnbuscar2.setContentAreaFilled(false);
                                                    btnbuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar2.setIconTextGap(30);
                                                    btnbuscar2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar2ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar1.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar1CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar1ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                                    jPanel8.setLayout(jPanel8Layout);
                                                    jPanel8Layout.setHorizontalGroup(
                                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel21)
                                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel8Layout.setVerticalGroup(
                                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(btnbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                    .addComponent(jLabel21)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Grupo1.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo1.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo1.setRowHeight(35);
                                                    tb_Grupo1.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Grupo1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo1MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo1.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo1KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane3.setViewportView(tb_Grupo1);

                                                    javax.swing.GroupLayout LlenarGenericaCT3Layout = new javax.swing.GroupLayout(LlenarGenericaCT3.getContentPane());
                                                    LlenarGenericaCT3.getContentPane().setLayout(LlenarGenericaCT3Layout);
                                                    LlenarGenericaCT3Layout.setHorizontalGroup(
                                                        LlenarGenericaCT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane3)
                                                    );
                                                    LlenarGenericaCT3Layout.setVerticalGroup(
                                                        LlenarGenericaCT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarGenericaCT3Layout.createSequentialGroup()
                                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                                                    );

                                                    LlenarSubGenericaCT4.setAlwaysOnTop(true);
                                                    LlenarSubGenericaCT4.setMinimumSize(new java.awt.Dimension(450, 430));
                                                    LlenarSubGenericaCT4.setResizable(false);

                                                    jPanel9.setBackground(new java.awt.Color(230, 230, 230));

                                                    jLabel29.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                    jLabel29.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel29.setText("Sub Generica");

                                                    btnbuscar3.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                                                    btnbuscar3.setToolTipText("");
                                                    btnbuscar3.setContentAreaFilled(false);
                                                    btnbuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar3.setIconTextGap(30);
                                                    btnbuscar3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar3ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar2.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar2CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar2ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                                    jPanel9.setLayout(jPanel9Layout);
                                                    jPanel9Layout.setHorizontalGroup(
                                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel29)
                                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel9Layout.setVerticalGroup(
                                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(btnbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                    .addComponent(jLabel29)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Grupo2.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo2.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo2.setRowHeight(35);
                                                    tb_Grupo2.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Grupo2.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo2MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo2.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo2KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane4.setViewportView(tb_Grupo2);

                                                    javax.swing.GroupLayout LlenarSubGenericaCT4Layout = new javax.swing.GroupLayout(LlenarSubGenericaCT4.getContentPane());
                                                    LlenarSubGenericaCT4.getContentPane().setLayout(LlenarSubGenericaCT4Layout);
                                                    LlenarSubGenericaCT4Layout.setHorizontalGroup(
                                                        LlenarSubGenericaCT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane4)
                                                    );
                                                    LlenarSubGenericaCT4Layout.setVerticalGroup(
                                                        LlenarSubGenericaCT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarSubGenericaCT4Layout.createSequentialGroup()
                                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                                                    );

                                                    LlenarSubGenericaDetalleCT5.setAlwaysOnTop(true);
                                                    LlenarSubGenericaDetalleCT5.setMinimumSize(new java.awt.Dimension(450, 430));
                                                    LlenarSubGenericaDetalleCT5.setResizable(false);

                                                    jPanel15.setBackground(new java.awt.Color(230, 230, 230));

                                                    jLabel39.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                    jLabel39.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel39.setText("Sub Generica Detalle");

                                                    btnbuscar4.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                                                    btnbuscar4.setToolTipText("");
                                                    btnbuscar4.setContentAreaFilled(false);
                                                    btnbuscar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar4.setIconTextGap(30);
                                                    btnbuscar4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar4ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar3.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar3CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar3ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                                                    jPanel15.setLayout(jPanel15Layout);
                                                    jPanel15Layout.setHorizontalGroup(
                                                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel39)
                                                                .addGroup(jPanel15Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel15Layout.setVerticalGroup(
                                                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel39)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtBuscar3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                                                .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Grupo3.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo3.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo3.setRowHeight(35);
                                                    tb_Grupo3.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Grupo3.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo3MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo3.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo3KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane5.setViewportView(tb_Grupo3);

                                                    javax.swing.GroupLayout LlenarSubGenericaDetalleCT5Layout = new javax.swing.GroupLayout(LlenarSubGenericaDetalleCT5.getContentPane());
                                                    LlenarSubGenericaDetalleCT5.getContentPane().setLayout(LlenarSubGenericaDetalleCT5Layout);
                                                    LlenarSubGenericaDetalleCT5Layout.setHorizontalGroup(
                                                        LlenarSubGenericaDetalleCT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane5)
                                                    );
                                                    LlenarSubGenericaDetalleCT5Layout.setVerticalGroup(
                                                        LlenarSubGenericaDetalleCT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarSubGenericaDetalleCT5Layout.createSequentialGroup()
                                                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                                                    );

                                                    LlenarEspecifica.setAlwaysOnTop(true);
                                                    LlenarEspecifica.setMinimumSize(new java.awt.Dimension(450, 430));
                                                    LlenarEspecifica.setResizable(false);

                                                    jPanel17.setBackground(new java.awt.Color(230, 230, 230));

                                                    jLabel53.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                    jLabel53.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel53.setText("Especifica");

                                                    btnbuscar5.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                                                    btnbuscar5.setToolTipText("");
                                                    btnbuscar5.setContentAreaFilled(false);
                                                    btnbuscar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar5.setIconTextGap(30);
                                                    btnbuscar5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar5.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar5ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar4.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar4CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar4ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                                                    jPanel17.setLayout(jPanel17Layout);
                                                    jPanel17Layout.setHorizontalGroup(
                                                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel53)
                                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel17Layout.setVerticalGroup(
                                                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(btnbuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                                    .addComponent(jLabel53)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    jScrollPane6.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Grupo4.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo4.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo4.setRowHeight(25);
                                                    tb_Grupo4.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Grupo4.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo4MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo4.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo4KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane6.setViewportView(tb_Grupo4);

                                                    javax.swing.GroupLayout LlenarEspecificaLayout = new javax.swing.GroupLayout(LlenarEspecifica.getContentPane());
                                                    LlenarEspecifica.getContentPane().setLayout(LlenarEspecificaLayout);
                                                    LlenarEspecificaLayout.setHorizontalGroup(
                                                        LlenarEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane6)
                                                    );
                                                    LlenarEspecificaLayout.setVerticalGroup(
                                                        LlenarEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarEspecificaLayout.createSequentialGroup()
                                                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                                                    );

                                                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                                                    setMinimumSize(new java.awt.Dimension(749, 473));

                                                    jPanel1.setBackground(new java.awt.Color(23, 160, 134));

                                                    btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Documento-32.png"))); // NOI18N
                                                    btnNuevo.setText("Nuevo");
                                                    btnNuevo.setContentAreaFilled(false);
                                                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    btnNuevo.setIconTextGap(30);
                                                    btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnNuevoActionPerformed(evt);
                                                        }
                                                    });

                                                    btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    btneditar.setForeground(new java.awt.Color(240, 240, 240));
                                                    btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Editar-32.png"))); // NOI18N
                                                    btneditar.setText("Editar");
                                                    btneditar.setContentAreaFilled(false);
                                                    btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    btneditar.setIconTextGap(30);
                                                    btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btneditar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btneditarActionPerformed(evt);
                                                        }
                                                    });

                                                    btnguardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Guardar-32.png"))); // NOI18N
                                                    btnguardar.setText("Guardar");
                                                    btnguardar.setContentAreaFilled(false);
                                                    btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnguardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    btnguardar.setIconTextGap(30);
                                                    btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnguardar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnguardarActionPerformed(evt);
                                                        }
                                                    });

                                                    btneliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                                                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Basura-32.png"))); // NOI18N
                                                    btneliminar.setText("Eliminar");
                                                    btneliminar.setContentAreaFilled(false);
                                                    btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    btneliminar.setIconTextGap(30);
                                                    btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btneliminar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btneliminarActionPerformed(evt);
                                                        }
                                                    });

                                                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblusu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                                    lblusu.setText("Silvana");
                                                    lblusu.setFocusable(false);
                                                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                                    jLabel60.setBackground(new java.awt.Color(255, 255, 255));
                                                    jLabel60.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                    jLabel60.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel60.setText("<html>Cuentas<span style=\"font-size:'14px'\"><br>Contables</br></span></html>");

                                                    jPanel24.setBackground(new java.awt.Color(255, 255, 255));

                                                    buscartodo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                    buscartodo.setForeground(new java.awt.Color(51, 51, 51));
                                                    buscartodo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                    buscartodo.setBorder(null);
                                                    buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            buscartodoCaretUpdate(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                                                    jPanel24.setLayout(jPanel24Layout);
                                                    jPanel24Layout.setHorizontalGroup(
                                                        jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel24Layout.createSequentialGroup()
                                                            .addGap(2, 2, 2)
                                                            .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel24Layout.setVerticalGroup(
                                                        jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel24Layout.createSequentialGroup()
                                                            .addGap(0, 0, 0)
                                                            .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                                    btnBuscarPaciente.setContentAreaFilled(false);
                                                    btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnBuscarPacienteActionPerformed(evt);
                                                        }
                                                    });

                                                    lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                                    lbldetalle.setText("Cuenta, Descripción");

                                                    lblBusqueda.setForeground(new java.awt.Color(23, 160, 134));
                                                    lblBusqueda.setText("0");

                                                    lblMant.setForeground(new java.awt.Color(23, 160, 134));
                                                    lblMant.setText("jLabel1");

                                                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                                    jPanel1.setLayout(jPanel1Layout);
                                                    jPanel1Layout.setHorizontalGroup(
                                                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addContainerGap()
                                                                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addContainerGap()
                                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btneditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                                                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addGap(25, 25, 25)
                                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lbldetalle)))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addGap(33, 33, 33)
                                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblMant)
                                                                        .addComponent(lblBusqueda))))
                                                            .addContainerGap(28, Short.MAX_VALUE))
                                                    );
                                                    jPanel1Layout.setVerticalGroup(
                                                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(34, 34, 34)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lbldetalle)
                                                            .addGap(21, 21, 21)
                                                            .addComponent(btnNuevo)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(btnguardar)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(btneditar)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(btneliminar)
                                                            .addGap(29, 29, 29)
                                                            .addComponent(lblBusqueda)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lblMant)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    PaginasCuentas.setBackground(new java.awt.Color(255, 255, 255));
                                                    PaginasCuentas.setForeground(new java.awt.Color(255, 255, 255));
                                                    PaginasCuentas.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                                                    PaginasCuentas.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
                                                    PaginasCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            PaginasCuentasMouseClicked(evt);
                                                        }
                                                    });

                                                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                                                    bc.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    bc.setForeground(new java.awt.Color(51, 51, 51));
                                                    bc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Vista general 2-50.png"))); // NOI18N
                                                    bc.setText("Tipo de Transacción");
                                                    bc.setContentAreaFilled(false);
                                                    bc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc.setFocusPainted(false);
                                                    bc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc.setIconTextGap(30);
                                                    bc.setVerifyInputWhenFocusTarget(false);
                                                    bc.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bcActionPerformed(evt);
                                                        }
                                                    });

                                                    bc1.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    bc1.setForeground(new java.awt.Color(51, 51, 51));
                                                    bc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tipo de archivo de libro genérico-50.png"))); // NOI18N
                                                    bc1.setText("Génerica");
                                                    bc1.setContentAreaFilled(false);
                                                    bc1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc1.setFocusPainted(false);
                                                    bc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc1.setIconTextGap(30);
                                                    bc1.setVerifyInputWhenFocusTarget(false);
                                                    bc1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc1ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc2.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    bc2.setForeground(new java.awt.Color(51, 51, 51));
                                                    bc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Información general Página 4-50.png"))); // NOI18N
                                                    bc2.setText("Sub Génerica Detalle");
                                                    bc2.setContentAreaFilled(false);
                                                    bc2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc2.setFocusPainted(false);
                                                    bc2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc2.setIconTextGap(30);
                                                    bc2.setVerifyInputWhenFocusTarget(false);
                                                    bc2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc2ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc3.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    bc3.setForeground(new java.awt.Color(51, 51, 51));
                                                    bc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Documentos-50.png"))); // NOI18N
                                                    bc3.setText("Sub Genérica");
                                                    bc3.setContentAreaFilled(false);
                                                    bc3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc3.setFocusPainted(false);
                                                    bc3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc3.setIconTextGap(30);
                                                    bc3.setVerifyInputWhenFocusTarget(false);
                                                    bc3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc3ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc4.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    bc4.setForeground(new java.awt.Color(51, 51, 51));
                                                    bc4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Vista general 2-50 (1).png"))); // NOI18N
                                                    bc4.setText("Especifica");
                                                    bc4.setContentAreaFilled(false);
                                                    bc4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc4.setFocusPainted(false);
                                                    bc4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc4.setIconTextGap(30);
                                                    bc4.setVerifyInputWhenFocusTarget(false);
                                                    bc4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc4ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc5.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                    bc5.setForeground(new java.awt.Color(51, 51, 51));
                                                    bc5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Alinear a la derecha-50.png"))); // NOI18N
                                                    bc5.setText("Especifica de Detalle");
                                                    bc5.setContentAreaFilled(false);
                                                    bc5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc5.setFocusPainted(false);
                                                    bc5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc5.setIconTextGap(30);
                                                    bc5.setVerifyInputWhenFocusTarget(false);
                                                    bc5.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc5ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                                    jPanel3.setLayout(jPanel3Layout);
                                                    jPanel3Layout.setHorizontalGroup(
                                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addGap(5, 5, 5)
                                                                            .addComponent(bc, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(bc4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(122, 122, 122)
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(bc2)
                                                                        .addComponent(bc1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(bc5)))
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                    .addContainerGap()
                                                                    .addComponent(bc3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(0, 289, Short.MAX_VALUE))
                                                    );
                                                    jPanel3Layout.setVerticalGroup(
                                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                            .addGap(44, 44, 44)
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bc)
                                                                .addComponent(bc1))
                                                            .addGap(52, 52, 52)
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bc3)
                                                                .addComponent(bc2))
                                                            .addGap(52, 52, 52)
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bc4)
                                                                .addComponent(bc5))
                                                            .addGap(118, 118, 118))
                                                    );

                                                    PaginasCuentas.addTab("B", jPanel3);

                                                    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel10.setText("Tipo");

                                                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel11.setText("Descripcion");

                                                    txtcodT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtcodT.setEnabled(false);
                                                    txtcodT.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodTActionPerformed(evt);
                                                        }
                                                    });

                                                    txttipoT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txttipoT.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txttipoTActionPerformed(evt);
                                                        }
                                                    });

                                                    txtdesT.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtdesT.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                                            txtdesTKeyReleased(evt);
                                                        }
                                                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                                            txtdesTKeyTyped(evt);
                                                        }
                                                    });
                                                    jScrollPane1.setViewportView(txtdesT);

                                                    jScrollPane7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Cta1.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Cta1.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Cta1.setRowHeight(35);
                                                    tb_Cta1.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Cta1.getTableHeader().setReorderingAllowed(false);
                                                    tb_Cta1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Cta1MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Cta1.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Cta1KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane7.setViewportView(tb_Cta1);

                                                    jPanel36.setBackground(new java.awt.Color(41, 127, 184));
                                                    jPanel36.setPreferredSize(new java.awt.Dimension(0, 2));

                                                    javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
                                                    jPanel36.setLayout(jPanel36Layout);
                                                    jPanel36Layout.setHorizontalGroup(
                                                        jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                    );
                                                    jPanel36Layout.setVerticalGroup(
                                                        jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 2, Short.MAX_VALUE)
                                                    );

                                                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                                    jPanel4.setLayout(jPanel4Layout);
                                                    jPanel4Layout.setHorizontalGroup(
                                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel10)
                                                                .addComponent(jLabel11))
                                                            .addGap(33, 33, 33)
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                    .addComponent(txttipoT, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(33, 33, 33)
                                                                    .addComponent(txtcodT, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(337, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                                                    );
                                                    jPanel4Layout.setVerticalGroup(
                                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel10)
                                                                .addComponent(txttipoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtcodT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel11)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(9, 9, 9)
                                                            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                                                    );

                                                    PaginasCuentas.addTab("C1", jPanel4);

                                                    jPanel11.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel15.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel15.setText("Transaccion");

                                                    jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel16.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel16.setText("Descripcion");

                                                    txtcodG.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtcodG.setEnabled(false);
                                                    txtcodG.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodGActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel17.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel17.setText("Generica");

                                                    txttrans1.setEditable(false);
                                                    txttrans1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txttrans1.setForeground(new java.awt.Color(51, 51, 51));
                                                    txttrans1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txttrans1ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel18.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel18.setText(".");

                                                    txtgen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtgen.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtgen.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtgenActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT1.setBackground(new java.awt.Color(255, 255, 255));
                                                    lblCT1.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT1.setText("jLabel2");

                                                    cuentagenerica.setBackground(new java.awt.Color(255, 255, 255));
                                                    cuentagenerica.setForeground(new java.awt.Color(255, 255, 255));
                                                    cuentagenerica.setText("000");

                                                    panelCPT.setBackground(new java.awt.Color(255, 255, 255));
                                                    panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                    txttrans.setEditable(false);
                                                    txttrans.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                    txttrans.setForeground(new java.awt.Color(51, 51, 51));
                                                    txttrans.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                    txttrans.setBorder(null);
                                                    txttrans.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txttransCaretUpdate(evt);
                                                        }
                                                    });

                                                    B1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                    B1.setContentAreaFilled(false);
                                                    B1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    B1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            B1ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
                                                    panelCPT.setLayout(panelCPTLayout);
                                                    panelCPTLayout.setHorizontalGroup(
                                                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPTLayout.createSequentialGroup()
                                                            .addGap(5, 5, 5)
                                                            .addComponent(txttrans, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(3, 3, 3))
                                                    );
                                                    panelCPTLayout.setVerticalGroup(
                                                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPTLayout.createSequentialGroup()
                                                            .addGap(0, 0, 0)
                                                            .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txttrans, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    txtdesG.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtdesG.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                                            txtdesGKeyReleased(evt);
                                                        }
                                                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                                            txtdesGKeyTyped(evt);
                                                        }
                                                    });
                                                    jScrollPane13.setViewportView(txtdesG);

                                                    jScrollPane8.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Cta2.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Cta2.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Cta2.setRowHeight(25);
                                                    tb_Cta2.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Cta2.getTableHeader().setReorderingAllowed(false);
                                                    tb_Cta2.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Cta2MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Cta2.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Cta2KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane8.setViewportView(tb_Cta2);

                                                    jPanel37.setBackground(new java.awt.Color(41, 127, 184));
                                                    jPanel37.setPreferredSize(new java.awt.Dimension(0, 2));

                                                    javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
                                                    jPanel37.setLayout(jPanel37Layout);
                                                    jPanel37Layout.setHorizontalGroup(
                                                        jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                    );
                                                    jPanel37Layout.setVerticalGroup(
                                                        jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 2, Short.MAX_VALUE)
                                                    );

                                                    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                                    jPanel11.setLayout(jPanel11Layout);
                                                    jPanel11Layout.setHorizontalGroup(
                                                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addGap(10, 10, 10)
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel15)
                                                                        .addComponent(jLabel17)))
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addContainerGap()
                                                                    .addComponent(jLabel16)))
                                                            .addGap(36, 36, 36)
                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                                                            .addComponent(txttrans1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jLabel18)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(txtgen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(panelCPT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(txtcodG, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(cuentagenerica, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(210, 210, 210))
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                        .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane8)
                                                    );
                                                    jPanel11Layout.setVerticalGroup(
                                                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(cuentagenerica)
                                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(txtcodG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(lblCT1)))
                                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel15)
                                                                        .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel17)
                                                                        .addComponent(txttrans1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel18)
                                                                        .addComponent(txtgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel16))
                                                                    .addGap(35, 35, 35)))
                                                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addContainerGap())
                                                    );

                                                    PaginasCuentas.addTab("C2", jPanel11);

                                                    jPanel12.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel42.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel42.setText("Generica");

                                                    jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel43.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel43.setText("Descripcion");

                                                    txtcodsubgen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodsubgen.setEnabled(false);
                                                    txtcodsubgen.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodsubgenActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel44.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel44.setText("Sub Generica");

                                                    txtgen3.setEditable(false);
                                                    txtgen3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtgen3.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtgen3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtgen3ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel45.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel45.setText(".");

                                                    txtsub.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtsub.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtsub.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsubActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT2.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT2.setText("jLabel2");

                                                    sugenericacadena.setForeground(new java.awt.Color(255, 255, 255));
                                                    sugenericacadena.setText("00000");

                                                    panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                                                    panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                    txtgen2.setEditable(false);
                                                    txtgen2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                    txtgen2.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtgen2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                    txtgen2.setBorder(null);
                                                    txtgen2.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtgen2CaretUpdate(evt);
                                                        }
                                                    });

                                                    b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                    b2.setContentAreaFilled(false);
                                                    b2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    b2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            b2ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                                                    panelCPT1.setLayout(panelCPT1Layout);
                                                    panelCPT1Layout.setHorizontalGroup(
                                                        panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT1Layout.createSequentialGroup()
                                                            .addGap(5, 5, 5)
                                                            .addComponent(txtgen2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(3, 3, 3))
                                                    );
                                                    panelCPT1Layout.setVerticalGroup(
                                                        panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT1Layout.createSequentialGroup()
                                                            .addGap(0, 0, 0)
                                                            .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtgen2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    txtdesSubG.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtdesSubG.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                                            txtdesSubGKeyReleased(evt);
                                                        }
                                                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                                            txtdesSubGKeyTyped(evt);
                                                        }
                                                    });
                                                    jScrollPane14.setViewportView(txtdesSubG);

                                                    jScrollPane9.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Cta3.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Cta3.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Cta3.setRowHeight(25);
                                                    tb_Cta3.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Cta3.getTableHeader().setReorderingAllowed(false);
                                                    tb_Cta3.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Cta3MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Cta3.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Cta3KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane9.setViewportView(tb_Cta3);

                                                    jPanel38.setBackground(new java.awt.Color(41, 127, 184));
                                                    jPanel38.setPreferredSize(new java.awt.Dimension(0, 2));

                                                    javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
                                                    jPanel38.setLayout(jPanel38Layout);
                                                    jPanel38Layout.setHorizontalGroup(
                                                        jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                    );
                                                    jPanel38Layout.setVerticalGroup(
                                                        jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 2, Short.MAX_VALUE)
                                                    );

                                                    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                                    jPanel12.setLayout(jPanel12Layout);
                                                    jPanel12Layout.setHorizontalGroup(
                                                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addComponent(jLabel42)
                                                                    .addGap(57, 57, 57)
                                                                    .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(txtcodsubgen, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel44)
                                                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(30, 30, 30)
                                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                                            .addComponent(txtgen3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(4, 4, 4)
                                                                            .addComponent(jLabel45)
                                                                            .addGap(4, 4, 4)
                                                                            .addComponent(txtsub, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(sugenericacadena)
                                                                            .addGap(58, 58, 58)
                                                                            .addComponent(lblCT2))
                                                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane9)
                                                        .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                                                    );
                                                    jPanel12Layout.setVerticalGroup(
                                                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel42)
                                                                .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtcodsubgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(txtgen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtsub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblCT2))
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel44)
                                                                        .addComponent(jLabel45)))
                                                                .addComponent(sugenericacadena))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel43)
                                                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                                                    );

                                                    PaginasCuentas.addTab("C2", jPanel12);

                                                    jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel25.setText("Sub Generica");

                                                    jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel26.setText("Descripcion");

                                                    txtcodGD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodGD.setEnabled(false);
                                                    txtcodGD.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodGDActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel27.setText("Sub Generica Detalle");

                                                    txtsuggend.setEditable(false);
                                                    txtsuggend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtsuggend.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtsuggend.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsuggendActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel28.setText(".");

                                                    txtgend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtgend.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtgend.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtgendActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT3.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT3.setText("jLabel2");

                                                    Subgenericadetalle.setForeground(new java.awt.Color(255, 255, 255));
                                                    Subgenericadetalle.setText("0000000");

                                                    panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                                                    panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                    txtsubgen.setEditable(false);
                                                    txtsubgen.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                    txtsubgen.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtsubgen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                    txtsubgen.setBorder(null);
                                                    txtsubgen.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtsubgenCaretUpdate(evt);
                                                        }
                                                    });

                                                    B3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                    B3.setContentAreaFilled(false);
                                                    B3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    B3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            B3ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                                                    panelCPT2.setLayout(panelCPT2Layout);
                                                    panelCPT2Layout.setHorizontalGroup(
                                                        panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT2Layout.createSequentialGroup()
                                                            .addGap(5, 5, 5)
                                                            .addComponent(txtsubgen, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(3, 3, 3))
                                                    );
                                                    panelCPT2Layout.setVerticalGroup(
                                                        panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT2Layout.createSequentialGroup()
                                                            .addGap(0, 0, 0)
                                                            .addGroup(panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtsubgen, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    txtdesGd.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtdesGd.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                                            txtdesGdKeyReleased(evt);
                                                        }
                                                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                                            txtdesGdKeyTyped(evt);
                                                        }
                                                    });
                                                    jScrollPane15.setViewportView(txtdesGd);

                                                    jScrollPane10.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Cta4.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Cta4.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Cta4.setRowHeight(25);
                                                    tb_Cta4.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Cta4.getTableHeader().setReorderingAllowed(false);
                                                    tb_Cta4.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Cta4MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Cta4.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Cta4KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane10.setViewportView(tb_Cta4);

                                                    jPanel39.setBackground(new java.awt.Color(41, 127, 184));
                                                    jPanel39.setPreferredSize(new java.awt.Dimension(0, 2));

                                                    javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
                                                    jPanel39.setLayout(jPanel39Layout);
                                                    jPanel39Layout.setHorizontalGroup(
                                                        jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                    );
                                                    jPanel39Layout.setVerticalGroup(
                                                        jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 2, Short.MAX_VALUE)
                                                    );

                                                    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                                                    jPanel13.setLayout(jPanel13Layout);
                                                    jPanel13Layout.setHorizontalGroup(
                                                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel25)
                                                                .addComponent(jLabel26)
                                                                .addComponent(jLabel27))
                                                            .addGap(31, 31, 31)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                                            .addComponent(txtsuggend, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jLabel28)
                                                                            .addGap(5, 5, 5)
                                                                            .addComponent(txtgend, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(txtcodGD, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(lblCT3)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(Subgenericadetalle))
                                                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane10)
                                                        .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                                                    );
                                                    jPanel13Layout.setVerticalGroup(
                                                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel25)
                                                                .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtcodGD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblCT3)
                                                                    .addComponent(Subgenericadetalle)))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(jLabel27)
                                                                    .addComponent(txtsuggend, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel28))
                                                                .addComponent(txtgend))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel26)
                                                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                                                    );

                                                    PaginasCuentas.addTab("C2", jPanel13);

                                                    jPanel14.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel32.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel32.setText("Sub Generica Detalle");

                                                    jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel36.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel36.setText("Descripcion");

                                                    txtcodE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodE.setEnabled(false);
                                                    txtcodE.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodEActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel37.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel37.setText("Especifica");

                                                    txtsgd.setEditable(false);
                                                    txtsgd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtsgd.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtsgd.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsgdActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel38.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel38.setText(".");

                                                    txtespe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtespe.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtespe.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtespeActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT4.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT4.setText("jLabel2");

                                                    Especifica.setForeground(new java.awt.Color(255, 255, 255));
                                                    Especifica.setText("0000000");

                                                    panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
                                                    panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                    txtsubgd.setEditable(false);
                                                    txtsubgd.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                    txtsubgd.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtsubgd.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                    txtsubgd.setBorder(null);
                                                    txtsubgd.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtsubgdCaretUpdate(evt);
                                                        }
                                                    });

                                                    b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                    b4.setContentAreaFilled(false);
                                                    b4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    b4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            b4ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
                                                    panelCPT3.setLayout(panelCPT3Layout);
                                                    panelCPT3Layout.setHorizontalGroup(
                                                        panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT3Layout.createSequentialGroup()
                                                            .addGap(5, 5, 5)
                                                            .addComponent(txtsubgd, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(3, 3, 3))
                                                    );
                                                    panelCPT3Layout.setVerticalGroup(
                                                        panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT3Layout.createSequentialGroup()
                                                            .addGap(0, 0, 0)
                                                            .addGroup(panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtsubgd, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    txtdesE.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtdesE.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                                            txtdesEKeyReleased(evt);
                                                        }
                                                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                                            txtdesEKeyTyped(evt);
                                                        }
                                                    });
                                                    jScrollPane16.setViewportView(txtdesE);

                                                    jScrollPane11.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Cta5.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Cta5.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Cta5.setRowHeight(35);
                                                    tb_Cta5.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Cta5.getTableHeader().setReorderingAllowed(false);
                                                    tb_Cta5.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Cta5MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Cta5.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Cta5KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane11.setViewportView(tb_Cta5);

                                                    jPanel40.setBackground(new java.awt.Color(41, 127, 184));
                                                    jPanel40.setPreferredSize(new java.awt.Dimension(0, 2));

                                                    javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
                                                    jPanel40.setLayout(jPanel40Layout);
                                                    jPanel40Layout.setHorizontalGroup(
                                                        jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                    );
                                                    jPanel40Layout.setVerticalGroup(
                                                        jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 2, Short.MAX_VALUE)
                                                    );

                                                    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                                                    jPanel14.setLayout(jPanel14Layout);
                                                    jPanel14Layout.setHorizontalGroup(
                                                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel37)
                                                                .addComponent(jLabel36)
                                                                .addComponent(jLabel32))
                                                            .addGap(36, 36, 36)
                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                    .addComponent(txtsgd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(jLabel38)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(txtespe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(26, 26, 26)
                                                                    .addComponent(lblCT4)
                                                                    .addGap(29, 29, 29)
                                                                    .addComponent(Especifica, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                    .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtcodE, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane11)
                                                        .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                                                    );
                                                    jPanel14Layout.setVerticalGroup(
                                                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel32)
                                                                .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtcodE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(jLabel37)
                                                                    .addComponent(txtsgd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel38))
                                                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtespe)
                                                                    .addComponent(lblCT4)
                                                                    .addComponent(Especifica)))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel36)
                                                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                                    );

                                                    PaginasCuentas.addTab("C2", jPanel14);

                                                    jPanel16.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel47.setText("Especifica");

                                                    jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel48.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel48.setText("Descripcion");

                                                    txtcodEd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodEd.setEnabled(false);
                                                    txtcodEd.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodEdActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    jLabel49.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel49.setText("Especifica de Detalle");

                                                    txted1.setEditable(false);
                                                    txted1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txted1.setForeground(new java.awt.Color(51, 51, 51));
                                                    txted1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txted1ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel50.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel50.setText(".");

                                                    txtespedet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                    txtespedet.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtespedet.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtespedetActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT5.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT5.setText("jLabel2");

                                                    especificadetalle.setForeground(new java.awt.Color(255, 255, 255));
                                                    especificadetalle.setText("jLabel2");

                                                    panelCPT4.setBackground(new java.awt.Color(255, 255, 255));
                                                    panelCPT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                    txtespe2.setEditable(false);
                                                    txtespe2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                    txtespe2.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtespe2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                    txtespe2.setBorder(null);
                                                    txtespe2.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtespe2CaretUpdate(evt);
                                                        }
                                                    });

                                                    b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                                    b6.setContentAreaFilled(false);
                                                    b6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    b6.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            b6ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
                                                    panelCPT4.setLayout(panelCPT4Layout);
                                                    panelCPT4Layout.setHorizontalGroup(
                                                        panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT4Layout.createSequentialGroup()
                                                            .addGap(5, 5, 5)
                                                            .addComponent(txtespe2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(3, 3, 3))
                                                    );
                                                    panelCPT4Layout.setVerticalGroup(
                                                        panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCPT4Layout.createSequentialGroup()
                                                            .addGap(0, 0, 0)
                                                            .addGroup(panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtespe2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    txtdesEd.setForeground(new java.awt.Color(51, 51, 51));
                                                    txtdesEd.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                                            txtdesEdKeyReleased(evt);
                                                        }
                                                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                                            txtdesEdKeyTyped(evt);
                                                        }
                                                    });
                                                    jScrollPane17.setViewportView(txtdesEd);

                                                    jScrollPane12.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                    tb_Cta6.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Cta6.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Cta6.setRowHeight(35);
                                                    tb_Cta6.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                    tb_Cta6.getTableHeader().setReorderingAllowed(false);
                                                    tb_Cta6.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Cta6MouseClicked(evt);
                                                        }
                                                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                                            tb_Cta6MousePressed(evt);
                                                        }
                                                    });
                                                    tb_Cta6.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Cta6KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane12.setViewportView(tb_Cta6);

                                                    jPanel41.setBackground(new java.awt.Color(41, 127, 184));
                                                    jPanel41.setPreferredSize(new java.awt.Dimension(0, 2));

                                                    javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
                                                    jPanel41.setLayout(jPanel41Layout);
                                                    jPanel41Layout.setHorizontalGroup(
                                                        jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                    );
                                                    jPanel41Layout.setVerticalGroup(
                                                        jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 2, Short.MAX_VALUE)
                                                    );

                                                    javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                                                    jPanel16.setLayout(jPanel16Layout);
                                                    jPanel16Layout.setHorizontalGroup(
                                                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel47)
                                                                .addComponent(jLabel48)
                                                                .addComponent(jLabel49))
                                                            .addGap(36, 36, 36)
                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel16Layout.createSequentialGroup()
                                                                    .addComponent(txted1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(jLabel50)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(txtespedet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(txtcodEd, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(especificadetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(lblCT5))
                                                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                                                    );
                                                    jPanel16Layout.setVerticalGroup(
                                                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel47)
                                                                .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(jLabel49)
                                                                    .addComponent(txted1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel50))
                                                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtespedet)
                                                                    .addComponent(txtcodEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(especificadetalle)
                                                                    .addComponent(lblCT5)))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel48)
                                                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                                                    );

                                                    PaginasCuentas.addTab("C2", jPanel16);

                                                    jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                    jLabel20.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel20.setText("No se hallaron coincidencias");

                                                    jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 100)); // NOI18N
                                                    jLabel22.setForeground(new java.awt.Color(50, 151, 219));
                                                    jLabel22.setText(":(");

                                                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                                    jPanel6.setLayout(jPanel6Layout);
                                                    jPanel6Layout.setHorizontalGroup(
                                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 964, Short.MAX_VALUE)
                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addGap(197, 197, 197)
                                                                .addComponent(jLabel22)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel20)
                                                                .addContainerGap(282, Short.MAX_VALUE)))
                                                    );
                                                    jPanel6Layout.setVerticalGroup(
                                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 384, Short.MAX_VALUE)
                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addGap(216, 216, 216)
                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel22)
                                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                                        .addGap(55, 55, 55)
                                                                        .addComponent(jLabel20)))
                                                                .addContainerGap(34, Short.MAX_VALUE)))
                                                    );

                                                    PaginasCuentas.addTab("B", jPanel6);

                                                    jPanel5.setBackground(new java.awt.Color(230, 230, 230));
                                                    jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

                                                    lblDescripcion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
                                                    lblDescripcion.setForeground(new java.awt.Color(51, 51, 51));
                                                    lblDescripcion.setText("Agregar una Nueva Cuenta");

                                                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                                    jPanel5.setLayout(jPanel5Layout);
                                                    jPanel5Layout.setHorizontalGroup(
                                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel5Layout.setVerticalGroup(
                                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addGap(11, 11, 11)
                                                            .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addContainerGap(67, Short.MAX_VALUE))
                                                    );

                                                    cargareliminar.setBackground(new java.awt.Color(255, 153, 51));

                                                    Mensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                    Mensaje.setForeground(new java.awt.Color(255, 255, 255));
                                                    Mensaje.setText("Desea Actualizar el Registro ?");

                                                    eli.setForeground(new java.awt.Color(240, 240, 240));
                                                    eli.setText("Si");
                                                    eli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                    eli.setContentAreaFilled(false);
                                                    eli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    eli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    eli.setIconTextGap(30);
                                                    eli.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            eliActionPerformed(evt);
                                                        }
                                                    });

                                                    noeli.setForeground(new java.awt.Color(240, 240, 240));
                                                    noeli.setText("No");
                                                    noeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                    noeli.setContentAreaFilled(false);
                                                    noeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    noeli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    noeli.setIconTextGap(30);
                                                    noeli.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            noeliActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout cargareliminarLayout = new javax.swing.GroupLayout(cargareliminar);
                                                    cargareliminar.setLayout(cargareliminarLayout);
                                                    cargareliminarLayout.setHorizontalGroup(
                                                        cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(cargareliminarLayout.createSequentialGroup()
                                                            .addGap(19, 19, 19)
                                                            .addComponent(Mensaje)
                                                            .addGap(46, 46, 46)
                                                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    cargareliminarLayout.setVerticalGroup(
                                                        cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(cargareliminarLayout.createSequentialGroup()
                                                            .addGap(17, 17, 17)
                                                            .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(Mensaje)
                                                                .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                                    getContentPane().setLayout(layout);
                                                    layout.setHorizontalGroup(
                                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
                                                                .addComponent(PaginasCuentas)
                                                                .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    );
                                                    layout.setVerticalGroup(
                                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(PaginasCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    );

                                                    pack();
                                                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        lblDescripcion.setText("Agregar una Nueva Cuenta");
        lblBusqueda.setText("0");
        buscartodo.setText("");
        PaginasCuentas.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
       //CUENTA 1
        txttipoT.setEditable(true);
        txtdesT.setEditable(true); 
        txtdesT.setRequestFocusEnabled(true);
        //CUENTA2
        txtgen.setEditable(true);
        txtdesG.setEditable(true); 
        txtgen.setRequestFocusEnabled(true);
        //CUENTA 3
        txtsub.setEditable(true);
        txtdesSubG.setEditable(true);
        txtsub.setRequestFocusEnabled(true);
       //CUENTA 4
        txtgend.setEditable(true);
        txtdesGd.setEditable(true);
        //CUENTA 5
        txtespe.setEditable(true);
        txtdesE.setEditable(true);
        //Cuenta 6
        txtespedet.setEditable(true);
        txtdesEd.setEditable(true);        
        
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        tg=2;
        
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed

        //CUENTA 1  
        if(tg==1){ 
            if(lblMant.getText().equals("1")){ 
                GuardarCta1(); 
            }

            //CUENTA 2
            if(lblMant.getText().equals("2")){
                 GuardarCta2(); 
            }

            //CUENTA 3
            if(lblMant.getText().equals("3")){
                 GuardarCta3(); 
            }

            //CUENTA 4    
            if(lblMant.getText().equals("4")){ 
                GuardarCta4();
            }

            //CUENTA 5
            if(lblMant.getText().equals("5")){
                GuardarCta5();
            }

            //CUENTA 6
            if(lblMant.getText().equals("6")){
                GuardarCta6(); 
            }
          
        }else if(tg==2){
            
//           if(tg==11 || tg==22 ||tg==33 ||tg==44||tg==55||tg==66){
           cargareliminar.setVisible(true);
           cargareliminar.setBackground(new Color(255,153,51)); 
           Mensaje.setText("Desea Actualizar el Registro ?");
           eli.setText("Si");
           eli.setVisible(true);
           noeli.setVisible(true);
           if(lblMant.getText().equals("1")){
                tge=11;  
            }else if(lblMant.getText().equals("2")){
                tge=22;
            }else if(lblMant.getText().equals("3")){
                tge=33;
            }else if(lblMant.getText().equals("4")){
                tge=44;
            }else if(lblMant.getText().equals("5")){
                tge=55;
            }else if(lblMant.getText().equals("6")){
                tge=66;
            }
        }
        
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
              cargareliminar.setVisible(true);
              cargareliminar.setBackground(new Color(255,91,70)); 
              Mensaje.setText("Desea Eliminar este registro?");
              eli.setText("Si");
              eli.setVisible(true);
              noeli.setText("No");
              noeli.setVisible(true);
              tge=6;
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        BuscarCta1_CTA2();
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        BuscarCta1_CTA2();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
      //CUENTA2
        int fila=tb_Grupo.getSelectedRow();
        if(evt.getClickCount()==2){
            LlenarTransCT2.dispose();
            txttrans1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txttrans.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            lblCT1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
        }
        txttrans.setEditable(false);
        txttrans1.setEditable(false);
        txtgen.requestFocus();
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            LlenarTransCT2.dispose();
            txttrans1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txttrans.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            lblCT1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
        }
        txtgen.requestFocus();
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void PaginasCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaginasCuentasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PaginasCuentasMouseClicked

    private void txtgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgenActionPerformed

    private void txttrans1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttrans1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttrans1ActionPerformed

    private void txtcodGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodGActionPerformed

    private void txttipoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipoTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipoTActionPerformed

    private void txtcodTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodTActionPerformed

    private void txtcodsubgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodsubgenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodsubgenActionPerformed

    private void txtgen3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgen3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgen3ActionPerformed

    private void txtsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubActionPerformed

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
          BuscarCta2_CTA3();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
       //CUENTA3
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==2){
            LlenarGenericaCT3.dispose();
            txtgen2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            txtgen3.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            lblCT2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
        }
        txtgen2.setEditable(false);
        txtgen3.setEditable(false);
        txtsub.requestFocus();
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed
       // CUENTA 3
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();

            LlenarGenericaCT3.dispose();
            txtgen2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            txtgen3.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            lblCT2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
        }
        txtgen2.setEditable(false);
        txtgen3.setEditable(false);
        txtsub.requestFocus();
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void txtcodGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodGDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodGDActionPerformed

    private void txtsuggendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsuggendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuggendActionPerformed

    private void txtgendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgendActionPerformed

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
       BuscarCta3_CTA4();
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void tb_Grupo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo2MouseClicked
          //CUENTA3
        int fila=tb_Grupo2.getSelectedRow();
        if(evt.getClickCount()==2){
            LlenarSubGenericaCT4.dispose();
            txtsubgen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 1)));
            txtsuggend.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            lblCT3.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
        }
        txtsubgen.setEditable(false);
        txtsuggend.setEditable(false);
        txtgend.requestFocus();
    }//GEN-LAST:event_tb_Grupo2MouseClicked

    private void tb_Grupo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo2KeyPressed
     // CUENTA 3
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo2.getSelectedRow();

            LlenarSubGenericaCT4.dispose();
            txtsubgen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 1)));
            txtsuggend.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            lblCT3.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
        }
        txtsubgen.setEditable(false);
        txtsuggend.setEditable(false);
        txtgend.requestFocus();
    }//GEN-LAST:event_tb_Grupo2KeyPressed

    private void txtcodEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodEActionPerformed

    private void txtsgdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsgdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsgdActionPerformed

    private void txtespeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtespeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtespeActionPerformed

    private void btnbuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar4ActionPerformed

    private void txtBuscar3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar3CaretUpdate
        BuscarCta4_CTA5();
    }//GEN-LAST:event_txtBuscar3CaretUpdate

    private void txtBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar3ActionPerformed

    private void tb_Grupo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo3MouseClicked
           //CUENTA3
        int fila=tb_Grupo3.getSelectedRow();
        if(evt.getClickCount()==2){
  
            LlenarSubGenericaDetalleCT5.dispose();
            txtsubgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            txtsgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            lblCT4.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 2)));
        }
        txtdesE.requestFocus();
    }//GEN-LAST:event_tb_Grupo3MouseClicked

    private void tb_Grupo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo3KeyPressed
      // CUENTA 4
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo3.getSelectedRow();

            LlenarSubGenericaDetalleCT5.dispose();
            txtsubgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
            txtsgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            lblCT4.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 2)));
        }
        txtdesE.requestFocus();
    }//GEN-LAST:event_tb_Grupo3KeyPressed

    private void txtcodEdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodEdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodEdActionPerformed

    private void txted1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txted1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txted1ActionPerformed

    private void txtespedetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtespedetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtespedetActionPerformed

    private void btnbuscar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar5ActionPerformed

    private void txtBuscar4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar4CaretUpdate
         BuscarCta5_CTA6();
    }//GEN-LAST:event_txtBuscar4CaretUpdate

    private void txtBuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar4ActionPerformed

    private void tb_Grupo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseClicked
       //CUENTA5
        int fila=tb_Grupo4.getSelectedRow();
        if(evt.getClickCount()==2){
         LlenarEspecifica.dispose();
            txtespe2.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 1)));
            txted1.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 0)));
            lblCT5.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 2)));
        }
        
        txtespedet.requestFocus();
    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
            // CUENTA 5
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo4.getSelectedRow();

            LlenarEspecifica.dispose();
            txtespe2.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 1)));
            txted1.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 0)));
            lblCT5.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 2)));
        }
        
        txtespedet.requestFocus();
    }//GEN-LAST:event_tb_Grupo4KeyPressed

    private void tb_Cta1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Cta1MouseClicked
         //CUENTA1 EDITAR
        int fila=tb_Cta1.getSelectedRow();
        if(evt.getClickCount()==1){
            txtcodT.setText(String.valueOf(tb_Cta1.getValueAt(fila, 2)));
      
            txttipoT.setText(String.valueOf(tb_Cta1.getValueAt(fila, 0)));
            txtdesT.setText(String.valueOf(tb_Cta1.getValueAt(fila, 1)));
        }
         
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
     
         PaginasCuentas.setSelectedIndex(1);  
         tg=11;
         txttipoT.setEditable(false);
         txtdesT.setEditable(false);
         lblDescripcion.setText("Tipo de Transacción");
    }//GEN-LAST:event_tb_Cta1MouseClicked

    private void tb_Cta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Cta1KeyPressed
        // CUENTA 1 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Cta1.getSelectedRow();

            txtcodT.setText(String.valueOf(tb_Cta1.getValueAt(fila, 2)));
         
            txttipoT.setText(String.valueOf(tb_Cta1.getValueAt(fila, 0)));
            txtdesT.setText(String.valueOf(tb_Cta1.getValueAt(fila, 1)));
        }
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
   
        
         PaginasCuentas.setSelectedIndex(1);  
         tg=11;
         txttipoT.setEditable(false);
         txtdesT.setEditable(false);
         lblDescripcion.setText("Tipo de Transacción");
       
    }//GEN-LAST:event_tb_Cta1KeyPressed

    private void tb_Cta2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Cta2MouseClicked
            //CUENTA2 EDITAR
        int fila=tb_Cta2.getSelectedRow();
        if(evt.getClickCount()==1){
            txtcodG.setText(String.valueOf(tb_Cta2.getValueAt(fila, 2)));
            cuentagenerica.setText(String.valueOf(tb_Cta2.getValueAt(fila, 0)));
            txtdesG.setText(String.valueOf(tb_Cta2.getValueAt(fila, 1)));
            lblCT1.setText(String.valueOf(tb_Cta2.getValueAt(fila, 3)));
        }
          try {
            String cuenta2=cuentagenerica.getText();
            String palabra=String.valueOf(cuenta2.charAt(2));
            
            String cuenta1=cuentagenerica.getText();
            String palabra2=String.valueOf(cuenta2.charAt(0));
 
            txtgen.setText(palabra);
            txttrans.setText(palabra2);
            txttrans1.setText(palabra2);
            
        } catch (Exception e) {
        }
          
        btneditar.setEnabled(true);
        btnguardar.setEnabled(false);
        btneliminar.setEnabled(true);
        
         PaginasCuentas.setSelectedIndex(2);  
         tg=22;
         txtgen.setEditable(false);
         txtdesG.setEditable(false);
         B1.setVisible(false);
         lblDescripcion.setText("Génerica");
    }//GEN-LAST:event_tb_Cta2MouseClicked

    private void tb_Cta2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Cta2KeyPressed
        // CUENTA 2 EDITAR
        
        
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Cta2.getSelectedRow();

            txtcodG.setText(String.valueOf(tb_Cta2.getValueAt(fila, 2)));
            cuentagenerica.setText(String.valueOf(tb_Cta2.getValueAt(fila, 0)));
            txtdesG.setText(String.valueOf(tb_Cta2.getValueAt(fila, 1)));
            lblCT1.setText(String.valueOf(tb_Cta2.getValueAt(fila, 3)));
        }
        try {
            String cuenta2=cuentagenerica.getText();
            String palabra=String.valueOf(cuenta2.charAt(2));
            
            String cuenta1=cuentagenerica.getText();
            String palabra2=String.valueOf(cuenta2.charAt(0));
 
            txtgen.setText(palabra);
            txttrans.setText(palabra2);
            txttrans1.setText(palabra2);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
     
        
         PaginasCuentas.setSelectedIndex(2);  
         tg=22;
         txtgen.setEditable(false);
         txtdesG.setEditable(false);
         B1.setVisible(false);
          lblDescripcion.setText("Génerica");
    }//GEN-LAST:event_tb_Cta2KeyPressed

    private void tb_Cta3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Cta3MouseClicked
               //CUENTA3 EDITAR
        int fila=tb_Cta3.getSelectedRow();
        if(evt.getClickCount()==1){
            txtcodsubgen.setText(String.valueOf(tb_Cta3.getValueAt(fila, 2)));
            sugenericacadena.setText(String.valueOf(tb_Cta3.getValueAt(fila, 0)));
            txtdesSubG.setText(String.valueOf(tb_Cta3.getValueAt(fila, 1)));
            lblCT2.setText(String.valueOf(tb_Cta3.getValueAt(fila, 3)));
        }
        try {
            String cuenta3=sugenericacadena.getText();
            String palabra=String.valueOf(cuenta3.charAt(4));

            String palabra3=String.valueOf(cuenta3.charAt(0)+""+cuenta3.charAt(1)+""+cuenta3.charAt(2));
 
            txtsub.setText(palabra);
            txtgen2.setText(palabra3);
            txtgen3.setText(palabra3);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
        
         PaginasCuentas.setSelectedIndex(3);  
         tg=33;
         txtsub.setEditable(false);
         txtdesSubG.setEditable(false);
         b2.setVisible(false);
          lblDescripcion.setText("Sub Génerica");
    }//GEN-LAST:event_tb_Cta3MouseClicked

    private void tb_Cta3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Cta3KeyPressed
          // CUENTA 3 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Cta3.getSelectedRow();

            txtcodsubgen.setText(String.valueOf(tb_Cta3.getValueAt(fila, 2)));
            sugenericacadena.setText(String.valueOf(tb_Cta3.getValueAt(fila, 0)));
            txtdesSubG.setText(String.valueOf(tb_Cta3.getValueAt(fila, 1)));
            lblCT2.setText(String.valueOf(tb_Cta3.getValueAt(fila, 3)));
        }
        try {
            String cuenta3=sugenericacadena.getText();
            String palabra=String.valueOf(cuenta3.charAt(4));
            
            String palabra2=String.valueOf(cuenta3.charAt(0)+""+cuenta3.charAt(1)+""+cuenta3.charAt(2));
 
            txtsub.setText(palabra);
            txtgen2.setText(palabra2);
            txtgen3.setText(palabra2);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);

        
         PaginasCuentas.setSelectedIndex(3);  
         tg=33;
         txtsub.setEditable(false);
         txtdesSubG.setEditable(false);
         b2.setVisible(false);
          lblDescripcion.setText("Sub Génerica");
    }//GEN-LAST:event_tb_Cta3KeyPressed

    private void tb_Cta4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Cta4MouseClicked
              //CUENTA4 EDITAR
        int fila=tb_Cta4.getSelectedRow();
        if(evt.getClickCount()==1){
            txtcodGD.setText(String.valueOf(tb_Cta4.getValueAt(fila, 2)));
            Subgenericadetalle.setText(String.valueOf(tb_Cta4.getValueAt(fila, 0)));
            txtdesGd.setText(String.valueOf(tb_Cta4.getValueAt(fila, 1)));
            lblCT3.setText(String.valueOf(tb_Cta4.getValueAt(fila, 3)));
        }
        try {
            String cuenta4=Subgenericadetalle.getText();
            String palabra=String.valueOf(cuenta4.charAt(6));
            String palabra4=String.valueOf(cuenta4.charAt(0)+""+cuenta4.charAt(1)+""+cuenta4.charAt(2)+""+cuenta4.charAt(3)+""+cuenta4.charAt(4));
 
            txtgend.setText(palabra);
            txtsubgen.setText(palabra4);
            txtsuggend.setText(palabra4);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
        
         PaginasCuentas.setSelectedIndex(4);  
         tg=44;
         txtgend.setEditable(false);
         txtdesGd.setEditable(false);
         B3.setVisible(false);
          lblDescripcion.setText("Sub Génerica Detalle");
    }//GEN-LAST:event_tb_Cta4MouseClicked

    private void tb_Cta4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Cta4KeyPressed
        // CUENTA 4 EDITAR
        
        
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Cta4.getSelectedRow();

            txtcodGD.setText(String.valueOf(tb_Cta4.getValueAt(fila, 2)));
            Subgenericadetalle.setText(String.valueOf(tb_Cta4.getValueAt(fila, 0)));
            txtdesGd.setText(String.valueOf(tb_Cta4.getValueAt(fila, 1)));
            lblCT3.setText(String.valueOf(tb_Cta4.getValueAt(fila, 3)));
        }
        try {
            String cuenta4=Subgenericadetalle.getText();
            String palabra=String.valueOf(cuenta4.charAt(6));
            String palabra4=String.valueOf(cuenta4.charAt(0)+""+cuenta4.charAt(1)+""+cuenta4.charAt(2)+""+cuenta4.charAt(3)+""+cuenta4.charAt(4));
 
            txtgend.setText(palabra);
            txtsubgen.setText(palabra4);
            txtsuggend.setText(palabra4);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);

        
         PaginasCuentas.setSelectedIndex(4);  
         tg=44;
         txtgend.setEditable(false);
         txtdesGd.setEditable(false);
         B3.setVisible(false);
          lblDescripcion.setText("Sub Génerica Detalle");
    }//GEN-LAST:event_tb_Cta4KeyPressed

    private void tb_Cta5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Cta5MouseClicked
                //CUENTA5 EDITAR
        int fila=tb_Cta5.getSelectedRow();
        if(evt.getClickCount()==1){
            txtcodE.setText(String.valueOf(tb_Cta5.getValueAt(fila, 2)));
            Especifica.setText(String.valueOf(tb_Cta5.getValueAt(fila, 0)));
            txtdesE.setText(String.valueOf(tb_Cta5.getValueAt(fila, 1)));
            lblCT4.setText(String.valueOf(tb_Cta5.getValueAt(fila, 3)));
        }
        try {
            String cuenta5=Especifica.getText();
            String palabr51=String.valueOf(cuenta5.charAt(8));
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1)+""+cuenta5.charAt(2)+""+cuenta5.charAt(3)+""+cuenta5.charAt(4)+""+cuenta5.charAt(5)+""+cuenta5.charAt(6));
 
            txtespe.setText(palabr51);
            txtsgd.setText(palabra5);
            txtsubgd.setText(palabra5);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);

        
         PaginasCuentas.setSelectedIndex(5);  
         tg=55;
         txtespe.setEditable(false);
         txtdesE.setEditable(false);
         b4.setVisible(false);
          lblDescripcion.setText("Especifica");
    }//GEN-LAST:event_tb_Cta5MouseClicked

    private void tb_Cta5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Cta5KeyPressed
           // CUENTA 5 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Cta5.getSelectedRow();

            txtcodE.setText(String.valueOf(tb_Cta5.getValueAt(fila, 2)));
            Especifica.setText(String.valueOf(tb_Cta5.getValueAt(fila, 0)));
            txtdesE.setText(String.valueOf(tb_Cta5.getValueAt(fila, 1)));
            lblCT4.setText(String.valueOf(tb_Cta5.getValueAt(fila, 3)));
        }
        try {
            String cuenta5=Especifica.getText();
            String palabr51=String.valueOf(cuenta5.charAt(8));
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1)+""+cuenta5.charAt(2)+""+cuenta5.charAt(3)+""+cuenta5.charAt(4)+""+cuenta5.charAt(5)+""+cuenta5.charAt(6));
 
            txtespe.setText(palabr51);
            txtsgd.setText(palabra5);
            txtsubgd.setText(palabra5);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
        
         PaginasCuentas.setSelectedIndex(5);  
         tg=55;
         txtespe.setEditable(false);
         txtdesE.setEditable(false);
         b4.setVisible(false);
         lblDescripcion.setText("Especifica");
    }//GEN-LAST:event_tb_Cta5KeyPressed

    private void tb_Cta6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Cta6MouseClicked
                //CUENTA6 EDITAR
        int fila=tb_Cta6.getSelectedRow();
        if(evt.getClickCount()==1){
            txtcodEd.setText(String.valueOf(tb_Cta6.getValueAt(fila, 2)));
            especificadetalle.setText(String.valueOf(tb_Cta6.getValueAt(fila, 0)));
            txtdesEd.setText(String.valueOf(tb_Cta6.getValueAt(fila, 1)));
            lblCT5.setText(String.valueOf(tb_Cta6.getValueAt(fila, 3)));
        }
        try {
            String cuenta6=especificadetalle.getText();
            String palabra61=String.valueOf(cuenta6.charAt(10));
            String palabra6=String.valueOf(cuenta6.charAt(0)+""+cuenta6.charAt(1)+""+cuenta6.charAt(2)+""+cuenta6.charAt(3)+""+cuenta6.charAt(4)+""+cuenta6.charAt(5)+""+cuenta6.charAt(6)+""+cuenta6.charAt(7)+""+cuenta6.charAt(8));
 
            txtespe2.setText(palabra6);
            txted1.setText(palabra6);
            txtespedet.setText(palabra61);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
         tg=66;
         txtespedet.setEditable(false);
         txtdesEd.setEditable(false);
         b6.setVisible(false);
         lblDescripcion.setText("Especificade Detalle");
          PaginasCuentas.setSelectedIndex(6);  
    }//GEN-LAST:event_tb_Cta6MouseClicked

    private void tb_Cta6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Cta6KeyPressed
           // CUENTA 6 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Cta6.getSelectedRow();

            txtcodEd.setText(String.valueOf(tb_Cta6.getValueAt(fila, 2)));
            especificadetalle.setText(String.valueOf(tb_Cta6.getValueAt(fila, 0)));
            txtdesEd.setText(String.valueOf(tb_Cta6.getValueAt(fila, 1)));
            lblCT5.setText(String.valueOf(tb_Cta6.getValueAt(fila, 3)));
        }
        try {
            String cuenta6=especificadetalle.getText();
            String palabra61=String.valueOf(cuenta6.charAt(10));
            String palabra6=String.valueOf(cuenta6.charAt(0)+""+cuenta6.charAt(1)+""+cuenta6.charAt(2)+""+cuenta6.charAt(3)+""+cuenta6.charAt(4)+""+cuenta6.charAt(5)+""+cuenta6.charAt(6)+""+cuenta6.charAt(7)+""+cuenta6.charAt(8));
 
            txtespe2.setText(palabra6);
            txted1.setText(palabra6);
            txtespedet.setText(palabra61);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
         
         tg=66;
         txtespedet.setEditable(false);
         txtdesEd.setEditable(false);
         b6.setVisible(false);
         lblDescripcion.setText("Especificade Detalle");
         PaginasCuentas.setSelectedIndex(6);  
    }//GEN-LAST:event_tb_Cta6KeyPressed

    private void tb_Cta6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Cta6MousePressed
                  //CUENTA6 EDITAR
        int fila=tb_Cta6.getSelectedRow();
        if(evt.getClickCount()==2){

            txtcodEd.setText(String.valueOf(tb_Cta6.getValueAt(fila, 2)));
            especificadetalle.setText(String.valueOf(tb_Cta6.getValueAt(fila, 0)));
            txtdesEd.setText(String.valueOf(tb_Cta6.getValueAt(fila, 1)));
            lblCT5.setText(String.valueOf(tb_Cta6.getValueAt(fila, 3)));
        }
        try {
            String cuenta6=especificadetalle.getText();
            String palabra61=String.valueOf(cuenta6.charAt(10));
            String palabra6=String.valueOf(cuenta6.charAt(0)+""+cuenta6.charAt(1)+""+cuenta6.charAt(2)+""+cuenta6.charAt(3)+""+cuenta6.charAt(4)+""+cuenta6.charAt(5)+""+cuenta6.charAt(6)+""+cuenta6.charAt(7)+""+cuenta6.charAt(8));
 
            txtespe2.setText(palabra6);
            txted1.setText(palabra6);
            txtespedet.setText(palabra61);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnguardar.setEnabled(false);
        tg=66;
         txtespedet.setEditable(false);
         txtdesEd.setEditable(false);
         b6.setVisible(false);
    }//GEN-LAST:event_tb_Cta6MousePressed

    private void txtdesTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesTKeyReleased
        txtdesT.setText(txtdesT.getText().toUpperCase());
    }//GEN-LAST:event_txtdesTKeyReleased

    private void txtdesTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesTKeyTyped

    }//GEN-LAST:event_txtdesTKeyTyped

    private void txttransCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttransCaretUpdate

    }//GEN-LAST:event_txttransCaretUpdate

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
       LlenarTransCT2.setVisible(true);
    }//GEN-LAST:event_B1ActionPerformed

    private void txtdesGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesGKeyReleased
        txtdesG.setText(txtdesG.getText().toUpperCase());

    }//GEN-LAST:event_txtdesGKeyReleased

    private void txtdesGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesGKeyTyped

    }//GEN-LAST:event_txtdesGKeyTyped

    private void txtgen2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtgen2CaretUpdate

    }//GEN-LAST:event_txtgen2CaretUpdate

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        LlenarGenericaCT3.setVisible(true);        
    }//GEN-LAST:event_b2ActionPerformed

    private void txtdesSubGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesSubGKeyReleased
        txtdesSubG.setText(txtdesSubG.getText().toUpperCase());

    }//GEN-LAST:event_txtdesSubGKeyReleased

    private void txtdesSubGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesSubGKeyTyped

    }//GEN-LAST:event_txtdesSubGKeyTyped

    private void txtsubgenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtsubgenCaretUpdate

    }//GEN-LAST:event_txtsubgenCaretUpdate

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
       LlenarSubGenericaCT4.setVisible(true); 
    }//GEN-LAST:event_B3ActionPerformed

    private void txtdesGdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesGdKeyReleased
        txtdesGd.setText(txtdesGd.getText().toUpperCase());

    }//GEN-LAST:event_txtdesGdKeyReleased

    private void txtdesGdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesGdKeyTyped

    }//GEN-LAST:event_txtdesGdKeyTyped

    private void txtsubgdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtsubgdCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubgdCaretUpdate

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        LlenarSubGenericaDetalleCT5.setVisible(true); 
    }//GEN-LAST:event_b4ActionPerformed

    private void txtdesEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesEKeyReleased
        txtdesE.setText(txtdesE.getText().toUpperCase());

    }//GEN-LAST:event_txtdesEKeyReleased

    private void txtdesEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesEKeyTyped

    }//GEN-LAST:event_txtdesEKeyTyped

    private void txtespe2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtespe2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtespe2CaretUpdate

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        LlenarEspecifica.setVisible(true); 
    }//GEN-LAST:event_b6ActionPerformed

    private void txtdesEdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesEdKeyReleased
        txtdesEd.setText(txtdesEd.getText().toUpperCase());

    }//GEN-LAST:event_txtdesEdKeyReleased

    private void txtdesEdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesEdKeyTyped

    }//GEN-LAST:event_txtdesEdKeyTyped

    private void bc5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc5ActionPerformed
        tg=1;
        PaginasCuentas.setSelectedIndex(6);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        lblBusqueda.setText("6");
        lblMant.setText("6");
        txtcodEd.setText(cn6.idCTA6());
        txtespedet.setEditable(true);
        txtdesEd.setEditable(true);
        b6.setVisible(true);

        txtespe2.setText("");
        txted1.setText("");
        txtespedet.setText("");
        txtdesEd.setText("");
        lblDescripcion.setText("Especifica de Detalle");
    }//GEN-LAST:event_bc5ActionPerformed

    private void bc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc4ActionPerformed
        tg=1;
        PaginasCuentas.setSelectedIndex(5);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        lblBusqueda.setText("5");
        lblMant.setText("5");
        txtcodE.setText(cn5.idCTA5());
        //CUENTA 5
        txtespe.setEditable(true);
        txtdesE.setEditable(true);
        b4.setVisible(true);
        txtsubgd.setText("");
        txtsgd.setText("");
        txtespe.setText("");
        txtdesE.setText("");
        lblDescripcion.setText("Especifica");
    }//GEN-LAST:event_bc4ActionPerformed

    private void bc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc3ActionPerformed
        tg=1;
        PaginasCuentas.setSelectedIndex(3);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        lblBusqueda.setText("3");
        lblMant.setText("3");
        txtsub.setEditable(true);
        txtdesSubG.setEditable(true);
        b2.setVisible(true);
        txtcodsubgen.setText(cn3.idCTA3());

        txtgen2.setText("");
        txtgen3.setText("");
        txtsub.setText("");
        txtdesSubG.setText("");
        lblDescripcion.setText("Sub Genérica");
    }//GEN-LAST:event_bc3ActionPerformed

    private void bc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc2ActionPerformed
        tg=1;
        PaginasCuentas.setSelectedIndex(4);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        lblBusqueda.setText("4");
        lblMant.setText("4");
        txtgend.setEditable(true);
        txtdesGd.setEditable(true);
        B3.setVisible(true);
        txtcodGD.setText(cn4.idCTA4());

        txtsubgen.setText("");
        txtsuggend.setText("");
        txtgend.setText("");
        txtdesGd.setText("");
        lblDescripcion.setText("Sub Genérica de Deatalle");
    }//GEN-LAST:event_bc2ActionPerformed

    private void bc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc1ActionPerformed
        tg=1;
        PaginasCuentas.setSelectedIndex(2);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        lblBusqueda.setText("2");
        lblMant.setText("2");
        B1.setVisible(true);
        txtcodG.setText(cn2.idCTA2());

        txttrans.setText("");
        txttrans1.setText("");
        txtgen.setText("");
        txtdesG.setText("");
        lblDescripcion.setText("Genérica");
    }//GEN-LAST:event_bc1ActionPerformed

    private void bcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcActionPerformed
        tg=1;
        PaginasCuentas.setSelectedIndex(1);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        lblBusqueda.setText("1");
        lblMant.setText("1");
        txtcodT.setText(cnn.idCTA1());
        txttipoT.setEditable(true);
        txtdesT.setEditable(true);

        txttipoT.requestFocus();

        txttipoT.setText("");
        txtdesT.setText("");
        lblDescripcion.setText("Tipo de Transacción");
    }//GEN-LAST:event_bcActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        if (buscartodo.getText()!=""){
        if (lblBusqueda.getText().equals("1")){
             BuscarCta1_CTA2EDITAR();
        }else if (lblBusqueda.getText().equals("2")){
             BuscarCta2EDITAR();
        }else if (lblBusqueda.getText().equals("3")){
             BuscarCta3EDITAR();
        }else if (lblBusqueda.getText().equals("4")){
             BuscarCta4EDITAR();
        }else if (lblBusqueda.getText().equals("5")){
             BuscarCta5EDITAR();
        }else if (lblBusqueda.getText().equals("6")){
             BuscarCta6EDITAR();
        }else if (lblBusqueda.getText().equals("0")){
            BuscarCta1_CTA2EDITAR(); 
            BuscarCta2EDITAR();
            BuscarCta3EDITAR();
            BuscarCta4EDITAR();
            BuscarCta5EDITAR();
            BuscarCta6EDITAR();
            
            int cta1=tb_Cta1.getRowCount();
            int cta2=tb_Cta2.getRowCount();
            int cta3=tb_Cta3.getRowCount();
            int cta4=tb_Cta4.getRowCount();
            int cta5=tb_Cta5.getRowCount();
            int cta6=tb_Cta6.getRowCount();
            int[] listaNumeros = {cta1,cta2,cta3,cta4,cta5,cta6};
            int iNumeroMayor,iPosicion;
            iNumeroMayor = listaNumeros[0];
            iPosicion = 1;
            for (int x=1;x<listaNumeros.length;x++){
                if (listaNumeros[x]>iNumeroMayor){
                iNumeroMayor = listaNumeros[x];
                PaginasCuentas.setSelectedIndex(x+1);
                iPosicion=x+1;
                    
                }
                }
            if(iPosicion==1){
               lblDescripcion.setText("Tipo de Transacción"); 
               lblMant.setText("1");
            }else if(iPosicion==2){
               lblDescripcion.setText("Génerica"); 
               lblMant.setText("2");
            }else if(iPosicion==3){
               lblDescripcion.setText("Sub Genérica"); 
               lblMant.setText("3");
            }else if(iPosicion==4){
               lblDescripcion.setText("Sub Génerica Detalle"); 
               lblMant.setText("4");
            }else if(iPosicion==5){
               lblDescripcion.setText("Especifica"); 
               lblMant.setText("5");
            }else if(iPosicion==6){
               lblDescripcion.setText("Especifica de Detalle"); 
               lblMant.setText("6");
            }
            

            
            
        }
        jLabel22.setVisible(false);
        jLabel20.setVisible(false);
        }
//        if (buscartodo.getText().length()==0){
//            lblDescripcion.setText("Busqueda de cuentas, 1 - 6");
//            PaginasCuentas.setSelectedIndex(7);
//            jLabel22.setVisible(false);
//            jLabel20.setVisible(false);
//            
//            
//        }  
            int ct1=tb_Cta1.getRowCount();
            int ct2=tb_Cta2.getRowCount();
            int ct3=tb_Cta3.getRowCount();
            int ct4=tb_Cta4.getRowCount();
            int ct5=tb_Cta5.getRowCount();
            int ct6=tb_Cta6.getRowCount();
        if(ct1==0&&ct1==0&&ct2 == 0&&ct3 == 0&&ct4 == 0&&ct5 == 0&&ct6==0){
          lblDescripcion.setText("Busqueda de cuentas, 1 - 6");
            PaginasCuentas.setSelectedIndex(7);
            jLabel22.setVisible(true);
            jLabel20.setVisible(true);  
        }
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==3 || tge==1 ||tge==9 ||tge==7||tge==9){
            cargareliminar.setVisible(false);

        }
        if (tge==11){
            ModificarCta1();
            btnguardar.setEnabled(false);
//            btneditar.setEnabled(true);
//            btneliminar.setEnabled(true);

        }else if (tge==22){
            ModificarCta2();
            btnguardar.setEnabled(false);
//            btneditar.setEnabled(true);
//            btneliminar.setEnabled(true);

        }else if (tge==33){
            ModificarCta3();
            btnguardar.setEnabled(false);
//            btneditar.setEnabled(true);
//            btneliminar.setEnabled(true);

        }else if (tge==44){
            ModificarCta4();
            btnguardar.setEnabled(false);
//            btneditar.setEnabled(true);
//            btneliminar.setEnabled(true);

        }else if (tge==55){
            ModificarCta5();
            btnguardar.setEnabled(false);
//            btneditar.setEnabled(true);
//            btneliminar.setEnabled(true);

        }else if (tge==66){
            ModificarCta6();
            btnguardar.setEnabled(false);
//            btneditar.setEnabled(true);
//            btneliminar.setEnabled(true);

        }
        if (tge==6){
            if(lblMant.getText().equals("1")){
                EliminarCta1();
            }else if(lblMant.getText().equals("2")){
                EliminarCta2();
            }else if(lblMant.getText().equals("3")){
                EliminarCta3();
            }else if(lblMant.getText().equals("4")){
                EliminarCta4();
            }else if(lblMant.getText().equals("5")){
                EliminarCta5();
            }else if(lblMant.getText().equals("6")){
                EliminarCta6();
            }
   

        }
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed

          if (tge==3 || tge==1 || tge==6|| tge==9|| tge==7){
           cargareliminar.setVisible(false);

        }
        if (tge==2){
           cargareliminar.setVisible(true);
           cargareliminar.setBackground(new Color(255,153,51)); 
           Mensaje.setText("No se han realizado modificaciones");
           eli.setText("OK");
           eli.setVisible(true);
           noeli.setVisible(false);
           tge=9;
        
        }

    }//GEN-LAST:event_noeliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Transaccion().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B1;
    private javax.swing.JButton B3;
    private javax.swing.JLabel Especifica;
    private javax.swing.JDialog LlenarEspecifica;
    private javax.swing.JDialog LlenarGenericaCT3;
    private javax.swing.JDialog LlenarSubGenericaCT4;
    private javax.swing.JDialog LlenarSubGenericaDetalleCT5;
    private javax.swing.JDialog LlenarTransCT2;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JTabbedPane PaginasCuentas;
    private javax.swing.JLabel Subgenericadetalle;
    private javax.swing.JButton b2;
    private javax.swing.JButton b4;
    private javax.swing.JButton b6;
    private javax.swing.JButton bc;
    private javax.swing.JButton bc1;
    private javax.swing.JButton bc2;
    private javax.swing.JButton bc3;
    private javax.swing.JButton bc4;
    private javax.swing.JButton bc5;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JButton btnbuscar4;
    private javax.swing.JButton btnbuscar5;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JLabel cuentagenerica;
    private javax.swing.JButton eli;
    private javax.swing.JLabel especificadetalle;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblBusqueda;
    private javax.swing.JLabel lblCT1;
    private javax.swing.JLabel lblCT2;
    private javax.swing.JLabel lblCT3;
    private javax.swing.JLabel lblCT4;
    private javax.swing.JLabel lblCT5;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JLabel sugenericacadena;
    private javax.swing.JTable tb_Cta1;
    private javax.swing.JTable tb_Cta2;
    private javax.swing.JTable tb_Cta3;
    private javax.swing.JTable tb_Cta4;
    private javax.swing.JTable tb_Cta5;
    private javax.swing.JTable tb_Cta6;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTable tb_Grupo3;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtBuscar3;
    private javax.swing.JTextField txtBuscar4;
    private javax.swing.JTextField txtcodE;
    private javax.swing.JTextField txtcodEd;
    private javax.swing.JTextField txtcodG;
    private javax.swing.JTextField txtcodGD;
    private javax.swing.JTextField txtcodT;
    private javax.swing.JTextField txtcodsubgen;
    private javax.swing.JEditorPane txtdesE;
    private javax.swing.JEditorPane txtdesEd;
    private javax.swing.JEditorPane txtdesG;
    private javax.swing.JEditorPane txtdesGd;
    private javax.swing.JEditorPane txtdesSubG;
    private javax.swing.JEditorPane txtdesT;
    private javax.swing.JTextField txted1;
    private javax.swing.JTextField txtespe;
    public static javax.swing.JTextField txtespe2;
    private javax.swing.JTextField txtespedet;
    private javax.swing.JTextField txtgen;
    public static javax.swing.JTextField txtgen2;
    private javax.swing.JTextField txtgen3;
    private javax.swing.JTextField txtgend;
    private javax.swing.JTextField txtsgd;
    private javax.swing.JTextField txtsub;
    public static javax.swing.JTextField txtsubgd;
    public static javax.swing.JTextField txtsubgen;
    private javax.swing.JTextField txtsuggend;
    private javax.swing.JTextField txttipoT;
    public static javax.swing.JTextField txttrans;
    private javax.swing.JTextField txttrans1;
    // End of variables declaration//GEN-END:variables
}
