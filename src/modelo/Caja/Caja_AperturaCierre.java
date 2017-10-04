/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;
import Servicios.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
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
import java.sql.Connection;
import Vistas.Caja.Caja_Apertura;
import Vistas.Caja.Caja_Cierre;
import Vistas.Caja.Caja_Cierre_sa;
import Vistas.Caja.Caja_Reportes;
import Vistas.Caja.Caja_Ventas;
import Vistas.Principal.Principal;
import Vistas.Principal.Principal_Caja;
import net.sf.jasperreports.view.JasperViewer;
/**
/**
 *
 * @author Ricardo
 */
public class Caja_AperturaCierre {
private Connection cn;
private String Cajero ;
private String Caja ;
private String BASE ;

/////////PARA EL CIERRE
private int Id_Apertura;
private String CAJERO_CIERRE;
private int TERMINAL;
private String MONTO_CIERRE_C;
private String MONTO_CIERRE_O;
private String MONTO_CIERRE_A;


        
Conexion con = new Conexion();
static DefaultTableModel m;
public boolean NUEVO()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_APERTURA_CAJA ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            
            cmd.setString(1, getCajero());
            cmd.setString(2, getCaja());
            cmd.setString(3, getBASE());
            cmd.setInt(4, getId_Apertura());

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
public void reporteAperura(int id) {
        try {
            Map parametros = new HashMap();
            parametros.put("id",id);
           JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/Apertura.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 

    public void reporteCierre(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
           JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierre.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
                
            }
    } 

    public void reporteCierreANULADAS(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierreAnuladas.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
                
            }
    } 
    public void reporteCierreV(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierreVacio.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 
    
    ////////////////////////////////////////////////////////////////////////////
    //REPORTE CIERRE DETALLE
     public void reporteCierreCT6_C(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteA4_CCT6.jasper"), parametros, con.conectar());   
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Cierre de Caja");
            ventanavisor.setVisible(true);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 
    
    ////////////////////////////////////////////////////////////////////////////
    //REPORTE CIERRE ESPECIFICO 4A
    
    public void reporteCierreCT6(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierreA4.jasper"), parametros, con.conectar());   
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Cierre de Caja");
            ventanavisor.setVisible(true);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 

    public void reporteCierreANULADASCT6(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierreA4Anulados.jasper"), parametros, con.conectar());   
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Cierre de Caja");
            ventanavisor.setVisible(true);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 
    
    public void reporteCierreVACIOCT6(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierreA4Vacio.jasper"), parametros, con.conectar());   
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Cierre de Caja");
            ventanavisor.setVisible(true);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //REPORTE CIERRE GENERAL
    
    public void reporteCierreGENERAL(String USUARIO,int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("USUARIO",USUARIO);
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierreGeneral.jasper"), parametros, con.conectar());   
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Cierre de Caja");
            ventanavisor.setVisible(true);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 
    
    public void reporteCierreGENERALVACIO(String USUARIO,int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("USUARIO",USUARIO);
            parametros.put("SESION",SESION);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/Caja/ReporteCierreGeneralVacio.jasper"), parametros, con.conectar());   
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Cierre de Caja");
            ventanavisor.setVisible(true);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
            }
    } 
    


public boolean CIERRE(){
        boolean resp = false;
        try
        {
            String sql = "Exec CAJA_CIERRE_CAJA ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_Apertura());
            cmd.setString(2, getCAJERO_CIERRE());
            cmd.setInt(3, getTERMINAL());
            cmd.setString(4, getMONTO_CIERRE_C());
//            cmd.setString(5, getMONTO_CIERRE_O());
//            cmd.setString(6, getMONTO_CIERRE_A());

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

    public void DDATOS_GENERALES(String usu){
        String consulta="";
        try {
            consulta="exec SISTEMA_DATOS_ACCESO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Principal.lblPersonal.setText(r.getString(1)); 
                Principal.lblNivel.setText(r.getString(2)); 
                Principal.lblCod_Modulo.setText(r.getString(3)); 
                Principal.lblUbicacion.setText(r.getString(4)); 
                }
            //
        } catch (Exception e) {
            System.out.println("DATOS: " + e.getMessage());
        }
    }

//public String Apertura(){//muestra el codigo
//        String id = "";
//        try {
//            String consulta = "exec CAJA_Apertura";
//            ResultSet r;
//            r=con.Listar(consulta);
//        if(r.next()){
//               id = r.getString(1);
//        }
//        }catch(Exception ex){
//            System.out.println("Error " + ex.getMessage());
//        }
//        return id;
//    }

public void CajaPC_NRO(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_PC_SERIE_APERTURA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Apertura.lblCorrelativo.setText(r.getString(2)); 
                Caja_Apertura.lblTerminal.setText(r.getString(1)); 


                }
            //
        } catch (Exception e) {
            System.out.println("Error serie de apertura " + e.getMessage());
        }
    }
public void CajaPC_NRO_CIERRE(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_PC_SERIE_APERTURA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Cierre.lblCorrelativo.setText(r.getString(2)); 
                Caja_Cierre.lblTerminal.setText(r.getString(1)); 

                }
            //
        } catch (Exception e) {
            System.out.println("Error serie cierre: " + e.getMessage());
        }
    }

public void CajaPC_NRO_CIERRE_SA(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_PC_SERIE_APERTURA_REMOTO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Cierre_sa.lblCorrelativo.setText(r.getString(2)); 
                Caja_Cierre_sa.lblTerminal.setText(r.getString(1)); 

                }
            //
        } catch (Exception e) {
            System.out.println("Error serie cierre: " + e.getMessage());
        }
    }

public void CajaID_SESION(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_ID_SESION_APERTURA_CAJA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Principal_Caja.lblIDSESION.setText(r.getString(1)); 
                Principal_Caja.ibiIDAPERTURA.setText(r.getString(2)); 

                }
        } catch (Exception e) {
            System.out.println("ERROR ID_SESION: " + e.getMessage());
        }
    }

public void Caja_Cantidad_Ventas(int ID,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[1];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_VENTAS_SESION ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, ID);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: CANTIDAD DE VENTAS: " + e.getMessage());
        }
    }

public void Caja_Verificar_Apertura(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base","Estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_APERTURA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                fila[8]=r.getString(9);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaAperturas(tabla);
        } catch (Exception e) {
            System.out.println("Error: admisionEmergenciaTriajeListarReporte: " + e.getMessage());
        }
    }

    public void CIERRE_DETALLE_APERTURA(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_VERIFICAR_APERTURA_CIERRE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Cierre.lblID_APERTURA.setText(r.getString(1)); 
                Caja_Cierre.lblSerie.setText(r.getString(3)); 
                Caja_Cierre.lblFechaA.setText(r.getString(4)); 
                Caja_Cierre.lblHoraA.setText(r.getString(5));
                Caja_Cierre.lblTerminalA.setText(r.getString(6)); 
                Caja_Cierre.lblBaseA.setText(r.getString(7));
                Caja_Cierre.lblIDSESION_A.setText(r.getString(9)); 

                }
            //
        } catch (Exception e) {
            System.out.println("error detalle cierre " + e.getMessage());
        }
    }
    
    public void CIERRE_DETALLE_APERTURA_SA(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_VERIFICAR_APERTURA_CIERRE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Cierre_sa.lblID_APERTURA.setText(r.getString(1)); 
                Caja_Cierre_sa.lblSerie.setText(r.getString(3)); 
                Caja_Cierre_sa.lblFechaA.setText(r.getString(4)); 
                Caja_Cierre_sa.lblHoraA.setText(r.getString(5));
                Caja_Cierre_sa.lblTerminalA.setText(r.getString(6)); 
                Caja_Cierre_sa.lblBaseA.setText(r.getString(7));
                Caja_Cierre_sa.lblIDSESION_A.setText(r.getString(9)); 

                }
            //
        } catch (Exception e) {
            System.out.println("error detalle cierre sa " + e.getMessage());
        }
    }
    
    public void CIERRE_DETALLE_APERTURA_PRINCIPAL(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_VERIFICAR_APERTURA_CIERRE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Principal.lblSerie.setText(r.getString(3)); 
                Principal.lblFecha.setText(r.getString(4)); 
                Principal.lblHora.setText(r.getString(5));
                Principal.lblTerminal.setText(r.getString(6)); 
                Principal.lblBase.setText(r.getString(7));
                }
            //
        } catch (Exception e) {
            System.out.println("error cierre desde el princiipal " + e.getMessage());
        }
    }
    
    public void CIERRE_DETALLE_APERTURA_SSESION_ACTIVA(String usu){
        String consulta="";
        try {
            consulta="exec CAJA_VERIFICAR_APERTURA_CIERRE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Reportes.lblSerie.setText(r.getString(3)); 
                Caja_Reportes.lblFecha.setText(r.getString(4)); 
                Caja_Reportes.lblHora.setText(r.getString(5));
                Caja_Reportes.lblTerminal.setText(r.getString(6)); 
                Caja_Reportes.lblBase.setText(r.getString(7));
                }
            //
        } catch (Exception e) {
            System.out.println("error verificar apertura " + e.getMessage());
        }
    }
   

public void Caja_Verificar_Apertura_C(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base","Estado","ID SESION"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_APERTURA_CIERRE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                fila[8]=r.getString(9);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaAperturasS(tabla);
        } catch (Exception e) {
            System.out.println("Error: CARGAR SESION DEL USUARIO: " + e.getMessage());
        }
    }

public void Caja_Verificar_SESIONES(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_SESION_APERTURADA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR SESIONES: " + e.getMessage());
        }
    }

    public void Caja_Verificar_ULTIMO_CIERRE(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[1];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_ULTIMA_SESION ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR ULTIMO CIERRE: " + e.getMessage());
        }
    }

    public void Caja_Verificar_SESIONES_OTRA_PC(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_CERRAR_SESION_DISTINTA_PC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaAperturasP(tabla);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR SESIONES: " + e.getMessage());
        }
    }

    public void formatoTablaAperturasS(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);//dni
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);//paciente
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);//fecha de ingreso
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);//hora de ingreso 
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);//traido por
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);

        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(38);
    }
    public void formatoTablaAperturasP(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80);//hora de ingreso 
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//traido por


        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(38);
    }
    public void formatoTablaAperturas(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80);//hora de ingreso 
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//traido por
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);

        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(38);
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
                Caja_Cierre.lblVC.setText(r.getString(1)); 
                }
            //
        } catch (Exception e) {
            System.out.println("DATOS del cierre: " + e.getMessage());
        }
    }
    
    public void CAJA_CIERRE_TOTAL_SA(String usu,int descrip){
        String consulta="";
        try {
            consulta="EXEC CAJA_CIERRE_TOTAL_VENTAS ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            cmd.setInt(2, descrip);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Cierre_sa.lblVC.setText(r.getString(1)); 
                }
            //
        } catch (Exception e) {
            System.out.println("DATOS del cierre: " + e.getMessage());
        }
    }
    
 public Caja_AperturaCierre(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCajero() {
        return Cajero;
    }

    public void setCajero(String Cajero) {
        this.Cajero = Cajero;
    }

    public String getCaja() {
        return Caja;
    }

    public void setCaja(String Caja) {
        this.Caja = Caja;
    }

    public String getBASE() {
        return BASE;
    }

    public void setBASE(String BASE) {
        this.BASE = BASE;
    }

    public int getId_Apertura() {
        return Id_Apertura;
    }

    public void setId_Apertura(int Id_Apertura) {
        this.Id_Apertura = Id_Apertura;
    }

    public String getCAJERO_CIERRE() {
        return CAJERO_CIERRE;
    }

    public void setCAJERO_CIERRE(String CAJERO_CIERRE) {
        this.CAJERO_CIERRE = CAJERO_CIERRE;
    }

    public int getTERMINAL() {
        return TERMINAL;
    }

    public void setTERMINAL(int TERMINAL) {
        this.TERMINAL = TERMINAL;
    }

    public String getMONTO_CIERRE_C() {
        return MONTO_CIERRE_C;
    }

    public void setMONTO_CIERRE_C(String MONTO_CIERRE_C) {
        this.MONTO_CIERRE_C = MONTO_CIERRE_C;
    }

    public String getMONTO_CIERRE_O() {
        return MONTO_CIERRE_O;
    }

    public void setMONTO_CIERRE_O(String MONTO_CIERRE_O) {
        this.MONTO_CIERRE_O = MONTO_CIERRE_O;
    }

    public String getMONTO_CIERRE_A() {
        return MONTO_CIERRE_A;
    }

    public void setMONTO_CIERRE_A(String MONTO_CIERRE_A) {
        this.MONTO_CIERRE_A = MONTO_CIERRE_A;
    }

    

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
 
 
    
}
