/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import Servicios.Conexion;
import Vistas.Caja.Caja_Clientes;
import Vistas.Caja.Caja_Historia_Clinica;
import Vistas.Principal.Principal_Caja;
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
public class Caja_Cliente {
DefaultTableModel m;
private Connection cn;
private int ID_CLIENTE;
private String DNI;
private String NOMBRES;
private String APELLIDO_PATERNO;
private String APELLIDO_MATERNO;
private String RUC;
private String RAZON_SOCIAL;
private String DIRECCION;
private String CORREO;
private String TIPO_MONEDA;
private int CODIGO_FISCAL;
private String TIPO_DOCUMENTO;
private String USUARIO;
private String ESTADO; 
Conexion con = new Conexion(); 


    public boolean NUEVO_CLIENTE(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CLIENTES_NUEVO "
                        + "?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getDNI());
            cmd.setString(2, getNOMBRES());
            cmd.setString(3, getAPELLIDO_PATERNO());
            cmd.setString(4, getAPELLIDO_MATERNO());
            cmd.setString(5, getRUC());
            cmd.setString(6, getRAZON_SOCIAL());
            cmd.setString(7, getDIRECCION());
            cmd.setString(8, getCORREO());
            cmd.setString(9, getTIPO_MONEDA());
            cmd.setInt(10, getCODIGO_FISCAL());
            cmd.setString(11, getTIPO_DOCUMENTO());
            cmd.setString(12, getUSUARIO());
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
    
    public boolean MODIFICAR_CLIENTE(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CLIENTES_MODIFICAR "
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_CLIENTE());
            cmd.setString(2, getDNI());
            cmd.setString(3, getNOMBRES());
            cmd.setString(4, getAPELLIDO_PATERNO());
            cmd.setString(5, getAPELLIDO_MATERNO());
            cmd.setString(6, getRUC());
            cmd.setString(7, getRAZON_SOCIAL());
            cmd.setString(8, getDIRECCION());
            cmd.setString(9, getCORREO());
            cmd.setString(10, getTIPO_MONEDA());
            cmd.setInt(11, getCODIGO_FISCAL());
            cmd.setString(12, getTIPO_DOCUMENTO());
            cmd.setString(13, getUSUARIO());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL MODIFICAR  " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean ELIMINAR_CLIENTE(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CLIENTES_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_CLIENTE());
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
    
    public int VALIDAR_DOCUMENTO(String nombre){
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM CAJA_CLIENTES where DNI=?  AND ESTADO='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
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
            consulta="CAJA_VERIFICAR_NIVEL_USUARIO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Clientes.lblNivel.setText(r.getString(1)); 
                if(r.getString(2).equals("X")){
                    Caja_Clientes.lblPermiso.setText("L"); 
                }else   if(r.getString(3).equals("X")){
                    Caja_Clientes.lblPermiso.setText("E"); 
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR EL PERMISOS: " + e.getMessage());
        }
    }
    
    public void LISTAR_LOCALIDAD_SEDE(String usu){
        String consulta="";
        try {
            consulta="CAJA_VERIFICAR_UNIDAD_EJECUTORA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Principal_Caja.lblTipo_Sede.setText(r.getString(1));
                Principal_Caja.txtUbicacion.setText(r.getString(2));
                if(Principal_Caja.lblTipo_Sede.getText().equals("P")){
                    Principal_Caja.btnClientes.setText("Clientes");
                }else if(!Principal_Caja.lblTipo_Sede.getText().equals("P")){
                    Principal_Caja.btnClientes.setText("Historia Clínica");
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR TIPO SEDE: " + e.getMessage());
        }
    }
    
    public void LISTA_CLIENTES(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Documento","Nº de Documento","Nombres","Apellido Paterno","Apellido Materno","Razon Social","Dirección","Correo","Tipo de Moneda","Código Fiscal","RUC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CLIENTES_LISTAR ?";
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
                fila[11]=r.getString(12);
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
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0);
        tabla.setRowHeight(40);
    }

    public Caja_Cliente(){
            Conexion con = new Conexion();
            cn = con.conectar();
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDO_PATERNO() {
        return APELLIDO_PATERNO;
    }

    public void setAPELLIDO_PATERNO(String APELLIDO_PATERNO) {
        this.APELLIDO_PATERNO = APELLIDO_PATERNO;
    }

    public String getAPELLIDO_MATERNO() {
        return APELLIDO_MATERNO;
    }

    public void setAPELLIDO_MATERNO(String APELLIDO_MATERNO) {
        this.APELLIDO_MATERNO = APELLIDO_MATERNO;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRAZON_SOCIAL() {
        return RAZON_SOCIAL;
    }

    public void setRAZON_SOCIAL(String RAZON_SOCIAL) {
        this.RAZON_SOCIAL = RAZON_SOCIAL;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getTIPO_MONEDA() {
        return TIPO_MONEDA;
    }

    public void setTIPO_MONEDA(String TIPO_MONEDA) {
        this.TIPO_MONEDA = TIPO_MONEDA;
    }

    public int getCODIGO_FISCAL() {
        return CODIGO_FISCAL;
    }

    public void setCODIGO_FISCAL(int CODIGO_FISCAL) {
        this.CODIGO_FISCAL = CODIGO_FISCAL;
    }

    public String getTIPO_DOCUMENTO() {
        return TIPO_DOCUMENTO;
    }

    public void setTIPO_DOCUMENTO(String TIPO_DOCUMENTO) {
        this.TIPO_DOCUMENTO = TIPO_DOCUMENTO;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    

}
