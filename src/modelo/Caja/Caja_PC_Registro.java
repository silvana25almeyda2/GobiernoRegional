/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Servicios.Conexion;
import Vistas.Caja.Caja_Ajustes;
import Vistas.Caja.Caja_Registro;
import Vistas.Principal.Principal_Configuracion;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author MYS1
 */
public class Caja_PC_Registro {
DefaultTableModel m;
private Connection cn;
private String NOM_USU;  
private int NRO_PC;
private int AR_ID; 
private String PA_MODULO;
private String IMPRESORA;
private int UE_ID;
private String TELEFONO;
private String DIRECCION;
Conexion con = new Conexion();

    public void CajaPC_Listar(){
        String consulta="";
        try {
            consulta="EXEC CAJA_PC_NOMBRE ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Registro.txtPC.setText(r.getString(1)); 

                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
    
    public void PERFIL_SEDE_CONFIGURACION(String cp_id){
        String consulta="";
        try {
            consulta="EXEC SISTEMA_DATOS_CONFIGURACION ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Principal_Configuracion.jLabel7.setText(r.getString(4));
                Principal_Configuracion.jLabel3.setText("SEDE   "+r.getString(5));
                Principal_Configuracion.lblDireccion.setText(r.getString(6));
                Principal_Configuracion.txtTelefono.setText(r.getString(7));
                Principal_Configuracion.lblUE_ID.setText(r.getString(8));
                
                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
    
    public boolean ACTUALIZAR_INFO(){
        boolean resp = false;
        try
        {
            String sql = "Exec SISTEMA_ACTUALIZAR_UE ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getUE_ID());
            cmd.setString(2, getDIRECCION());
            cmd.setString(3, getTELEFONO());

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

    public void PERFIL_USUARIO(String cp_id){
        String consulta="";
        try {
            consulta="EXEC CAJA_PERSONAL_USUARIO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Registro.lblUsu.setText(r.getString(2));
                Caja_Registro.lblResumenUsuario.setText("<html>"+"Cajero "+r.getString(2)+"<html>");
                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
    
    public void LISTAR_CONFIGURACION(String cp_id){
        String consulta="";
        try {
            consulta="EXEC CAJA_LISTAR_CONFIGURACION_TERMINAL ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Ajustes.txtNRO.setText(r.getString(1));
                Caja_Ajustes.txtNRO1.setText(r.getString(2));
                Caja_Ajustes.cbxImpresoras.setSelectedItem(r.getString(3));
                
                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
    
    public boolean ACTUALIZAR_CONFIGURACION(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_ACTUALIZAR_CONFIGURACION_TERMINAL ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getIMPRESORA());
            cmd.setString(2, getNOM_USU());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL ACTUALIZAR EL REGISTRO " + ex.getMessage());
        }
        return resp;
    }
    
        public void VerificarExistencia(String Usuario,String M ,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"PC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[1];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_EXISTENCIA_PC_CONFIGURACION ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Usuario); ///bus1 esto se busca
            cmd.setString(2, M); ///bus1 esto se busca
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR PC EXISTENTE " + e.getMessage());
        }
    }
        
    public void SISTEMA_EQUIPO_TRABAJO(String Usuario,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Módulo","Usuario","Personal","PC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec SISTEMA_GRUPO_TRABAJO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Usuario); ///bus1 esto se busca
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
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR EQUIPO DE TRABAJO " + e.getMessage());
        }
    }
        
        public int VALIDAR_PC(){
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SISTEMA_CONFIGURACION_PC_AREA WHERE NOM_PC=HOST_NAME()";
            PreparedStatement cmd = getCn().prepareStatement(sql);

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
        
        
    public boolean NuevoTerminal(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CONFIGURAR_TERMINAL "
                        + "?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            //cmd.setString(1, getCod_nomen_caja());

            cmd.setString(1, getNOM_USU());
            cmd.setString(2, getPA_MODULO());
            cmd.setInt(3, getNRO_PC());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean NuevoTerminalC_F(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CONFIGURAR_TERMINAL_C_F "
                        + "?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            //cmd.setString(1, getCod_nomen_caja());

            cmd.setString(1, getNOM_USU());
            cmd.setInt(2, getNRO_PC());
            cmd.setString(3, getIMPRESORA());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
    
    public int VerificarNumero(String M,String nombre){
        int resultado=0;
        try
        {
            String sql = "select * from SISTEMA_CONFIGURACION_PC_AREA where PA_MODULO =? AND NRO_PC=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, M);
            cmd.setString(2, nombre);
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
    
    public void reportePRUEBA_TICKET(String H) {
        try {
            Map parametros = new HashMap();
            parametros.put("H",H);
           JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/PRUEBA_TICKET.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 
    
    public void reportePRUEBA_RECIBO(String H) {
        try {
            Map parametros = new HashMap();
            parametros.put("H",H);
           JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/PRUEBA_RECIBO.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 
    
    public void NUMERACION(){
        String consulta="";
        try {
            consulta="EXEC SISTEMA_CONFIGURACION_NUMERO_TERMINAL";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Registro.txtNRO.setText(r.getString(1)); 
                Caja_Registro.txtNRO1.setText(r.getString(2)); 
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR EL PRECIO: " + e.getMessage());
        }
    }
     
     public Caja_PC_Registro(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

    public int getNRO_PC() {
        return NRO_PC;
    }

    public void setNRO_PC(int NRO_PC) {
        this.NRO_PC = NRO_PC;
    }

    public int getAR_ID() {
        return AR_ID;
    }

    public void setAR_ID(int AR_ID) {
        this.AR_ID = AR_ID;
    }

    public String getPA_MODULO() {
        return PA_MODULO;
    }

    public void setPA_MODULO(String PA_MODULO) {
        this.PA_MODULO = PA_MODULO;
    }

    public String getIMPRESORA() {
        return IMPRESORA;
    }

    public void setIMPRESORA(String IMPRESORA) {
        this.IMPRESORA = IMPRESORA;
    }

    public int getUE_ID() {
        return UE_ID;
    }

    public void setUE_ID(int UE_ID) {
        this.UE_ID = UE_ID;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }
    
    
    
}
