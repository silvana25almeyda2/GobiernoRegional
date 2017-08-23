/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import Servicios.Conexion;
import Vistas.Caja.Caja_Ventas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author MYS1
 */
public class Caja_NuevaVenta {
private Connection cn;    
Conexion con = new Conexion();
static DefaultTableModel m;   
private int ID_DOCUMENTO;
private int ID_FORMA_PAGO  ;
private int ID_CLIENTE  ;
private int ID_ANULACION  ;
private String SERIE;
private String CORRELATIVO;
private double DESCUENTO  ;
private double SUB_TOTAL  ;
private double IGV  ;
private double TOTAL_DOC  ;
private String ESTADO_PAGO;
private double DEVOLUCION  ;
private String USUARIO  ;
private String USUARIO_DEV  ;
private String TIPO_VENTA  ;
private int ID_APERTURA  ;
private String ESTADO  ;
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
private int ID_PRECIO ;
private int CANTIDAD ;
private double PRECIO ;
private double TOTAL ;
private double DESCUENTOD ;           
                
    
    public void VENTA_LISTA_CLIENTES(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","TD","D","C"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_VENTA_LISTA_CLIENTES ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
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
            Formato(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR : " + e.getMessage());
        }
    }
    
    public void VENTA_LISTA_CPT(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","TD_GRUPO","CTP","DES"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_VENTA_CPT_LISTA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
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
            Formato(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR CPT: " + e.getMessage());
        }
    }
    
    public void Formato(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(600);
        tabla.setRowHeight(38);
    }
    
        public void ULTIMO_CLIENTE_REGISTRADO(String usu){
        String consulta="";
        try {
            consulta="CAJA_VENTA_ULTIMO_CLIENTE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Ventas.lblCliente.setText(r.getString(4)); 
                Caja_Ventas.lblDocumento.setText(r.getString(3)); 
                Caja_Ventas.lblIDCliente.setText(r.getString(1)); 
                Caja_Ventas.btnBuscarCPT1.setEnabled(false);

                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR AL CLIENTE: " + e.getMessage());
        }
    }
        
    public void Caja_Correlativo(String usu){
        try {
            String consulta = "exec CAJA_VENTA_SERIE_CORRELATIVO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
        if(r.next()){
               Caja_Ventas.lblSerie.setText(r.getString(1));
               Caja_Ventas.lblCorrelativo.setText(r.getString(2));
               Caja_Ventas.lblSerie_Correlativo.setText(r.getString(1)+"-"+r.getString(2));
        }
        }catch(Exception ex){
            System.out.println("Error al generar serie y numero " + ex.getMessage());
        }
    }
    
    public void DATOS_FP(String usu){
        try {
            String consulta = "exec CAJA_VENTA_FP_DESC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
        if(r.next()){
               Caja_Ventas.lblID_Documento.setText(r.getString(1));
        }
        }catch(Exception ex){
            System.out.println("ERROR AL CARGAR DATOS " + ex.getMessage());
        }
    }
    
    public boolean ELIMINAR_CABECERA(){
        boolean resp = false;
        try
        {
            String sql = "EXEC CAJA_ELIMINAR_CABECERA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DOCUMENTO());
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
    
    public boolean NUEVA_VENTA_DETALLE(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_VENTA_DETALLE_NUEVO "
                        + "?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DOCUMENTO());
            cmd.setInt(2, getID_PRECIO());
            cmd.setInt(3, getCANTIDAD());
            cmd.setDouble(4, getPRECIO());
            cmd.setDouble(5, getTOTAL());
            cmd.setDouble(6, getDESCUENTOD());
            cmd.setString(7, getUSUARIO());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL REGISTRAR VENTA  " + ex.getMessage());
        }
        return resp;
    }
    
        public boolean ACTUALIZAR_VENTA(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_VENTA_CABECERA_ACTUALIZAR "
                        + "?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DOCUMENTO());
            cmd.setDouble(2, getDESCUENTO());
            cmd.setDouble(3, getSUB_TOTAL());
            cmd.setDouble(4, getIGV());
            cmd.setDouble(5, getTOTAL_DOC());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL REGISTRAR VENTA  " + ex.getMessage());
        }
        return resp;
    }
    
    public void ReporteDiariocajaCabecera(String Usuario,Integer SESION,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Documento","Serie - Nº Documento","Forma de Pago","Cliente","Total","Fecha","Hora","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CONSULTAR_REPORTE_DIA_PC ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Usuario);
            cmd.setInt(2, SESION);
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
            formatoTablaReporteCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE CABECERA" + e.getMessage());
        }
    }
    
          public void formatoTablaReporteCabecera(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(360);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        
//        tabla.getColumnModel().getColumn(2).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(3).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(4).setPreferredWidth(50); 
//        tabla.getColumnModel().getColumn(5).setPreferredWidth(150); 
//        
  
        tabla.setRowHeight(40);
        
    }
          
    public void reporteVenta(int id_documento) {
        try {
            Map parametros = new HashMap();
            parametros.put("doc",id_documento);
           JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/Ticket.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                Caja_Ventas.ErrorExistente.setVisible(true);
                
            }
    }       
    
     public Caja_NuevaVenta(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getID_FORMA_PAGO() {
        return ID_FORMA_PAGO;
    }

    public void setID_FORMA_PAGO(int ID_FORMA_PAGO) {
        this.ID_FORMA_PAGO = ID_FORMA_PAGO;
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public int getID_ANULACION() {
        return ID_ANULACION;
    }

    public void setID_ANULACION(int ID_ANULACION) {
        this.ID_ANULACION = ID_ANULACION;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public String getCORRELATIVO() {
        return CORRELATIVO;
    }

    public void setCORRELATIVO(String CORRELATIVO) {
        this.CORRELATIVO = CORRELATIVO;
    }

    public double getDESCUENTO() {
        return DESCUENTO;
    }

    public void setDESCUENTO(double DESCUENTO) {
        this.DESCUENTO = DESCUENTO;
    }

    public double getSUB_TOTAL() {
        return SUB_TOTAL;
    }

    public void setSUB_TOTAL(double SUB_TOTAL) {
        this.SUB_TOTAL = SUB_TOTAL;
    }

    public double getIGV() {
        return IGV;
    }

    public void setIGV(double IGV) {
        this.IGV = IGV;
    }

    public double getTOTAL_DOC() {
        return TOTAL_DOC;
    }

    public void setTOTAL_DOC(double TOTAL_DOC) {
        this.TOTAL_DOC = TOTAL_DOC;
    }

    public String getESTADO_PAGO() {
        return ESTADO_PAGO;
    }

    public void setESTADO_PAGO(String ESTADO_PAGO) {
        this.ESTADO_PAGO = ESTADO_PAGO;
    }

    public double getDEVOLUCION() {
        return DEVOLUCION;
    }

    public void setDEVOLUCION(double DEVOLUCION) {
        this.DEVOLUCION = DEVOLUCION;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getUSUARIO_DEV() {
        return USUARIO_DEV;
    }

    public void setUSUARIO_DEV(String USUARIO_DEV) {
        this.USUARIO_DEV = USUARIO_DEV;
    }

    public String getTIPO_VENTA() {
        return TIPO_VENTA;
    }

    public void setTIPO_VENTA(String TIPO_VENTA) {
        this.TIPO_VENTA = TIPO_VENTA;
    }

    public int getID_APERTURA() {
        return ID_APERTURA;
    }

    public void setID_APERTURA(int ID_APERTURA) {
        this.ID_APERTURA = ID_APERTURA;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public int getID_DOCUMENTO() {
        return ID_DOCUMENTO;
    }

    public void setID_DOCUMENTO(int ID_DOCUMENTO) {
        this.ID_DOCUMENTO = ID_DOCUMENTO;
    }

    public int getID_PRECIO() {
        return ID_PRECIO;
    }

    public void setID_PRECIO(int ID_PRECIO) {
        this.ID_PRECIO = ID_PRECIO;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public double getDESCUENTOD() {
        return DESCUENTOD;
    }

    public void setDESCUENTOD(double DESCUENTOD) {
        this.DESCUENTOD = DESCUENTOD;
    }
     
     
    
}
