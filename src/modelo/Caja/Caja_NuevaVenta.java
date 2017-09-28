/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;

import Servicios.Conexion;
import Vistas.Caja.Caja_Reportes;
import Vistas.Caja.Caja_Ventas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

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
private String TIPO_GRUPO;
private String ID_PREVENTA;
private int ID_DETALLE_PREVENTA;
private String DESCRIP_PRE;
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
private int ID_PRECIO ;
private int CANTIDAD ;
private double PRECIO ;
private double TOTAL ;
private double DESCUENTOD ;
private double GRAVADA;
private double INAFECTA;
                
    

    
    public void IMPRIMIR_FACTURA_CAJA(String usu){
        try {
            String consulta = "exec CUENTAS_POR_PAGAR_ID_FACTURA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
        if(r.next()){
               Caja_Ventas.LBL_CPF_ID.setText(r.getString(1));
        }
        }catch(Exception ex){
            System.out.println("ERROR AL CARGAR el codigo de factura " + ex.getMessage());
        }
    }


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
    
    public void VENTA_LISTA_CPT(String descripcion,String FP,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","TD_GRUPO","CTP","DES","GRUPO"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_VENTA_CPT_LISTA ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            cmd.setString(2, FP);
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
            FormatoCPT(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR CPT: " + e.getMessage());
        }
    }
    public void FormatoCPT(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(800);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.setRowHeight(38);
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
            consulta="EXEC CAJA_VENTA_ULTIMO_CLIENTE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Ventas.lblCliente.setText(r.getString(4)); 
                Caja_Ventas.lblDocumento.setText(r.getString(3)); 
                Caja_Ventas.txtCliente.setText(r.getString(3)); 
                Caja_Ventas.lblIDCliente.setText(r.getString(1)); 
                Caja_Ventas.btnBuscarCPT1.setEnabled(false);
                Caja_Ventas.jButton1.doClick();

                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR AL CLIENTE: " + e.getMessage());
        }
    }
        
//    public void Caja_Correlativo(String usu){
//        try {
//            String consulta = "exec CAJA_VENTA_SERIE_CORRELATIVO ?";
//            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, usu);
//            ResultSet r= cmd.executeQuery();
//        if(r.next()){
//               Caja_Ventas.lblSerie.setText(r.getString(1));
//               Caja_Ventas.lblCorrelativo.setText(r.getString(2));
//               Caja_Ventas.lblSerie_Correlativo.setText(r.getString(1)+"-"+r.getString(2));
//        }
//        }catch(Exception ex){
//            System.out.println("Error al generar serie y numero " + ex.getMessage());
//        }
//    }
    
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
    
    public void CAJA_CIERRE_TOTAL(String usu,int descrip){
        String consulta="";
        try {
            consulta="EXEC CAJA_CIERRE_TOTAL_VENTAS ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            cmd.setInt(2, descrip);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Ventas.lblTR.setText("Total Recaudado S/  "+r.getString(1)); 
                }
            //
        } catch (Exception e) {
            System.out.println("DATOS del cierre: " + e.getMessage());
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
            String sql = "exec CAJA_VENTA_CABECERA_ACTUALIZAR ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DOCUMENTO());
            cmd.setString(2, getSERIE());
            cmd.setString(3, getCORRELATIVO());
            cmd.setDouble(4, getDESCUENTO());
            cmd.setDouble(5, getSUB_TOTAL());
            cmd.setDouble(6, getIGV());
            cmd.setDouble(7, getTOTAL_DOC());
            cmd.setString(8, getTIPO_GRUPO());
            cmd.setDouble(9, getGRAVADA());
            cmd.setDouble(10, getINAFECTA());
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
    
    public boolean ACTUALIZAR_PREVENTA(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_PREVENTA_ACTUALIZAR_ESTADO ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PREVENTA());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL ACTUALIZAR ESTADO DE LA PREVENTA  " + ex.getMessage());
        }
        return resp;
    }
    public boolean ACTUALIZAR_PREVENTA_CAB(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_PREVENTA_ACTUALIZAR_ESTADO_CAB ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PREVENTA());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL ACTUALIZAR ESTADO DE LA PREVENTA CAB " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean ANULAR_PREVENTA(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_PREVENTA_ANULAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getDESCRIP_PRE());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL ANULAR PREVENTA  " + ex.getMessage());
        }
        return resp;
    }
        
    public void ReporteDiariocajaCabeceraBUSQUEDA(String Usuario,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Documento","Serie - Nº Documento","Forma de Pago","Cliente","Total","Fecha","Hora","ID","TIPO"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CONSULTAR_HISTORIAL ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Usuario);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteCabeceraBUSQUEDA(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE BUSQUEDA" + e.getMessage());
        }
    }
    
    public void LISTAR_LOCALIDAD_SEDE(String usu){
        String consulta="";
        try {
            consulta="EXEC CAJA_VERIFICAR_UNIDAD_EJECUTORA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Reportes.lblTipo_Sede.setText(r.getString(1));
                if(Caja_Reportes.lblTipo_Sede.getText().equals("P")){
                    Caja_Reportes.txtUbicacion.setEditable(true);
                    Caja_Reportes.lblA.setText("A");
                }else if(!Caja_Reportes.lblTipo_Sede.getText().equals("P")){
                    Caja_Reportes.txtUbicacion.setEditable(false);
                    Caja_Reportes.lblA.setText("D");
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR TIPO SEDE: " + e.getMessage());
        }
    }
    
    public void ReporteSESION_ACTIVA(String Ubicacion,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Sesión Activa","Cajero","Usuario","PC","Serie","Base","Fecha","Hora","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_VENTAS_SESIONES_ACTIVAS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Ubicacion);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteAPERTURA_CIERRE(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE BUSQUEDA" + e.getMessage());
        }
    }
    
    public void ReporteSESION_ACTIVA_TODOS(JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Sesión Activa","Cajero","Usuario","PC","Serie","Base","Fecha","Hora","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_VENTAS_SESIONES_ACTIVAS_TODOS";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, Ubicacion);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteAPERTURA_CIERRE(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE BUSQUEDA" + e.getMessage());
        }
    }
    
        public void ReporteSESION_CERRADA(int MES,int ANIO,String Lugar,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Sesión","Cajero","Usuario","PC","Serie","Total Ventas","Fecha","Hora","ID","IDA"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CIERRES ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, MES);
            cmd.setInt(2, ANIO);
            cmd.setString(3, Lugar);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteAPERTURA_CIERRE1(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE BUSQUEDA" + e.getMessage());
        }
    }
        
    public void ReporteSESION_CERRADA_TODOS(int MES,int ANIO,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Sesión","Cajero","Usuario","PC","Serie","Total Ventas","Fecha","Hora","ID","IDA"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_CIERRES ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, MES);
            cmd.setInt(2, ANIO);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteAPERTURA_CIERRE1(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE BUSQUEDA" + e.getMessage());
        }
    }
        
    public void ReporteMENSUAL_CCTA7(int MES,int ANIO,String Lugar, JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Cuenta","Item","Cantidad","Precio","Total","TOTAL_TOTAL"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_REPORTE_MENSUAL_CTA7 ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, MES);
            cmd.setInt(2, ANIO);
            cmd.setString(3, Lugar);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteMENSUAL_CCT7(tabla);
        } catch (Exception e) {
            System.out.println("ERROR LISTAR REPORTE MENSUAL CCT7" + e.getMessage());
        }
    }
    
    public void ReporteMENSUAL_CCTA7_TODOS(int MES,int ANIO, JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Cuenta","Item","Cantidad","Precio","Total","","","Total_general"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_REPORTE_MENSUAL_CTA7_TODOS ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, MES);
            cmd.setInt(2, ANIO);
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
            formatoTablaReporteMENSUAL_CCT7TODOS(tabla);
        } catch (Exception e) {
            System.out.println("ERROR LISTAR REPORTE MENSUAL CCT7 TODOS" + e.getMessage());
        }
    }
    
    public void LISTAR_SEDES(String BUSQUEDA, JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={" "," "};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_BUSCAR_SEDES ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, BUSQUEDA);
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
            formatoTablaSEDES(tabla);
        } catch (Exception e) {
            System.out.println("ERROR LISTAR SEDES" + e.getMessage());
        }
    }
    
    
    
    public void ReporteMENSUAL_CCTA6_TODOS(int MES,int ANIO, JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Cuenta","Total","USU","DESC","DIR","RUC","TELEF","Sede","mes","tot"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_REPORTE_MENSUAL_CTA6_TODOS ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, MES);
            cmd.setInt(2, ANIO);
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
                
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaReporteMENSUAL_CCT6_TODOS(tabla);
        } catch (Exception e) {
            System.out.println("ERROR LISTAR REPORTE MENSUAL CCT6 TODOS" + e.getMessage());
        }
    }
    
    public void ReporteMENSUAL_CCTA6(int MES,int ANIO,String Lugar, JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Cuenta","Total","TOTAL_TOTAL"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_REPORTE_MENSUAL_CTA6 ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, MES);
            cmd.setInt(2, ANIO);
            cmd.setString(3, Lugar);
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
            formatoTablaReporteMENSUAL_CCT6(tabla);
        } catch (Exception e) {
            System.out.println("ERROR LISTAR REPORTE MENSUAL CCT6" + e.getMessage());
        }
    }
    
    public void ReporteDiariocajaCabecera(String Usuario,Integer SESION,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Documento","Serie - Nº Documento","Forma de Pago","Cliente","Total","Fecha","Hora","ID","TIPO","ANULADO"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
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
                fila[8]=r.getString(9);
                fila[9]=r.getString(10);
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
    
    public void ReporteDiariocajaDETALLE(Integer ID ,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Item","Descripción","Precio","Cantidad","Total"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_VENTA_REPORTE_DETALLE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, ID);
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
            formatoTablaReporteDETALLE(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar REPORTE DETALLE" + e.getMessage());
        }
    }
    
    public void formatoTablaReporteDETALLE(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(600);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabla.setRowHeight(40);
        
    }
    
    public void formatoTablaReporteMENSUAL_CCT7(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(800);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.setRowHeight(40);
        
    }
    
        public void formatoTablaReporteMENSUAL_CCT7TODOS(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(800);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(6).setMinWidth(0);
            tabla.getColumnModel().getColumn(6).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.setRowHeight(40);
        
    }
    public void formatoTablaReporteMENSUAL_CCT6_TODOS(JTable tabla){

        tabla.getColumnModel().getColumn(0).setPreferredWidth(800);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.setRowHeight(40);
        
    }
    public void formatoTablaReporteMENSUAL_CCT6(JTable tabla){

        tabla.getColumnModel().getColumn(0).setPreferredWidth(800);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.setRowHeight(40);
        
    }
    public void formatoTablaSEDES(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.setRowHeight(40); 
    }
    
    public void formatoTablaReporteAPERTURA_CIERRE(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(8).setMinWidth(0);
            tabla.getColumnModel().getColumn(8).setMaxWidth(0);

        tabla.setRowHeight(40);
        
    }
    public void formatoTablaReporteAPERTURA_CIERRE1(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(8).setMinWidth(0);
            tabla.getColumnModel().getColumn(8).setMaxWidth(0);
            tabla.getColumnModel().getColumn(9).setMinWidth(0);
            tabla.getColumnModel().getColumn(9).setMaxWidth(0);

        tabla.setRowHeight(40);
        
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
            tabla.getColumnModel().getColumn(8).setMinWidth(0);
            tabla.getColumnModel().getColumn(8).setMaxWidth(0);
            tabla.getColumnModel().getColumn(9).setMinWidth(0);
            tabla.getColumnModel().getColumn(9).setMaxWidth(0);
            tabla.setRowHeight(40);  
    }
    public void formatoTablaReporteCabeceraBUSQUEDA(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(360);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(8).setMinWidth(0);
            tabla.getColumnModel().getColumn(8).setMaxWidth(0);
            tabla.setRowHeight(48);
    }
    
    public void ReporteMENSUAL_DETALLE_LOCAL(Integer MES,Integer ANIO, String LUGAR) {
        try {
            Map parametros = new HashMap();
            parametros.put("MES", MES);
            parametros.put("ANIO", ANIO);
            parametros.put("LUGAR", LUGAR);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteA4_CT6_MENSUAL.jasper"), parametros, con.conectar()); 
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Reporte Mensual");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporteDiario:"+e.getMessage());
        }
    }
    
    public void ReporteMENSUAL_ESPECIFICA_LOCAL(Integer MES,Integer SESION, String LUGAR) {
        try {
            Map parametros = new HashMap();
            parametros.put("MES", MES);
            parametros.put("SESION", SESION);
            parametros.put("LUGAR", LUGAR);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteA4_CT7_MENSUAL.jasper"), parametros, con.conectar()); 
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Reporte Mensual");
           ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporteDiario:"+e.getMessage());
        }
    }
    
    public void ReporteMENSUAL_DETALLE_TODOS(Integer MES,Integer SESION, String LUGAR) {
        try {
            Map parametros = new HashMap();
            parametros.put("MES", MES);
            parametros.put("SESION", SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteA4_CT6_MENSUAL_TODOS.jasper"), parametros, con.conectar()); 
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Reporte Mensual");
           ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporteDiario:"+e.getMessage());
        }
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
    
    public void ReporteDiario(String USUARIO,Integer SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("USUARIO", USUARIO);
            parametros.put("SESION", SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteDiario.jasper"), parametros, con.conectar()); 
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Reporte Diario");
           ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error_reporteDiario:"+e.getMessage());
        }
    }
    
    public void CAJA_PREVENTAS_FR(String Texto,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Forma Pago","DNI","Paciente","Fecha","id"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_PREVENTAS_FARMACIA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Texto);
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
            formatoPreventaFR(tabla);
        } catch (Exception e) {
            System.out.println("Error: PREVENTA FR: " + e.getMessage());
        }
    }
    public void formatoPreventaFR(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.setRowHeight(40);
    }
    
    public void CAJA_PREVENTAS_FR_DETALLE(String Texto,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID_CABECERA","ID_CABECERA","Descripción","Cantidad","Precio","Estado","ID_CABECERA",
            "ID_PRECIO","ID_CTP","NOMBRE","ITEM"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[11];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_PREVENTAS_FARMACIA_DETALLE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Texto);
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
            formatoPreventaFR_DETALLE(tabla);
        } catch (Exception e) {
            System.out.println("Error: PREVENTA FR DETALLE: " + e.getMessage());
        }
    }
public void formatoPreventaFR_DETALLE(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
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

    public String getTIPO_GRUPO() {
        return TIPO_GRUPO;
    }

    public void setTIPO_GRUPO(String TIPO_GRUPO) {
        this.TIPO_GRUPO = TIPO_GRUPO;
    }

    public double getGRAVADA() {
        return GRAVADA;
    }

    public void setGRAVADA(double GRAVADA) {
        this.GRAVADA = GRAVADA;
    }

    public double getINAFECTA() {
        return INAFECTA;
    }

    public void setINAFECTA(double INAFECTA) {
        this.INAFECTA = INAFECTA;
    }

    public String getID_PREVENTA() {
        return ID_PREVENTA;
    }

    public void setID_PREVENTA(String ID_PREVENTA) {
        this.ID_PREVENTA = ID_PREVENTA;
    }

    public int getID_DETALLE_PREVENTA() {
        return ID_DETALLE_PREVENTA;
    }

    public void setID_DETALLE_PREVENTA(int ID_DETALLE_PREVENTA) {
        this.ID_DETALLE_PREVENTA = ID_DETALLE_PREVENTA;
    }

    public String getDESCRIP_PRE() {
        return DESCRIP_PRE;
    }

    public void setDESCRIP_PRE(String DESCRIP_PRE) {
        this.DESCRIP_PRE = DESCRIP_PRE;
    }
     
    
}
