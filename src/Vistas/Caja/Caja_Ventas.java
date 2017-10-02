/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import Servicios.Conexion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import modelo.Caja.Caja_NuevaVenta;
import Vistas.Facturador.*;
import Vistas.Principal.Principal;
import Vistas.Principal.Principal_Caja;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.Formatter;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import modelo.Caja.FormatoTablaReporteDiarioCaja;
import modelo.Facturador.CuentasPorPagarFacturasCabecera;
import modelo.Facturador.CuentasPorPagarFacturasDetalle;
import modelo.Facturador.CuentasPorPagarSfsRpta;

/**
 *
 * @author MYS1
 */
public class Caja_Ventas extends javax.swing.JFrame {
Conexion c=new Conexion();
Connection ConexionS=c.conectar();
Connection conexion=c.conectar();
CuentasPorPagarFacturasCabecera cuentasCab1 = new CuentasPorPagarFacturasCabecera();

static CuentasPorPagarFacturasCabecera F = new CuentasPorPagarFacturasCabecera();

ResultSet r;
DefaultTableModel modelos;
DefaultTableModel m1, modelo1, modelo2,modeloFR,modeloFR2;
Caja_NuevaVenta nuevaV = new Caja_NuevaVenta();
Caja_NuevaVenta nuevaR = new Caja_NuevaVenta();

 String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
    double sumatoriaIGV = 0.00;
    double sumatoriaTotal = 0.00;
    double sumInafectas = 0.00;
    double sumGravadas = 0.00;
    /**
     * Creates new form Caja_Ventas
     */
    public Caja_Ventas() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Mind Map-100.png")).getImage());
        CLIENTES.setLocationRelativeTo(null);//en el centro
        CPT.setLocationRelativeTo(null);//en el centro
        Inafecto.setLocationRelativeTo(null);//en el centro
        Afecto.setLocationRelativeTo(null);//en el centro
        ErrorExistente.setLocationRelativeTo(null);//en el centro
        Preventas.setLocationRelativeTo(null);//en el centro
        CLIENTES.getContentPane().setBackground(Color.WHITE);
        cbxTipoDocumento.setBackground(Color.WHITE);
        cbxFormaPago.setBackground(Color.WHITE);
        tbClientes.getTableHeader().setVisible(false);
        tbClientes.setTableHeader(null);
        tb_CPTBUSCAR.setTableHeader(null);
        jPanel8.setVisible(false);
        paneltablaHC.setVisible(false);
        panelSinHC.setVisible(false);
        panelMensaje.setVisible(false);
        panelEliminacion.setVisible(false);
        panelDetalleHistorial.setVisible(false);
        panelEliminarFR.setVisible(false);
        jPanel13.setVisible(false);
        jPanel15.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        lblIDCliente.setVisible(false);
        Paginas.setEnabled(false);
        Paginas.setEnabledAt(0,false);
        Paginas.setEnabledAt(1, false);
        Paginas.setEnabledAt(2, false);
        panleBoleta.setVisible(false);
        panelProcentaje.setVisible(false);
        lblPorcentaje.setVisible(false);
        
        cuentasCab1.generarSerieCorrelativo("B",Principal.lblUsu.getText());
        
        Paginas.setSelectedIndex(0);
        this.cbxFormaPago.setModel(CargarFP());
        Nuevo(false);
        PanelCantidad.setVisible(false);
        panelAnular.setVisible(false);
        panelEliminar.setVisible(false);
        panelDetalle.setVisible(false);
        addEscapeListenerWindowDialog(CLIENTES);
        addEscapeListenerWindowDialog(CPT);
//        btnLista.doClick();
        cerrar ();
        
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
               SalirFormulario();
            }
        });
        
    }
    
    public void CARGAR(){
        int fila=tbClientes.getSelectedRow();
        lblIDCliente.setText(String.valueOf(tbClientes.getValueAt(fila, 0)));  
        txtCliente.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
        lblCliente.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
        lblDocumento.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
    }
    
    public void CARGAR_PREVENTA(){
        int fila=tbClientes.getSelectedRow();
        lblDNI_PREVENTA.setText(String.valueOf(tbClientes.getValueAt(fila, 2)));  
        lblNOMBRE_PREVENTA.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
    }
    
    private void MostrarPreventa(){
        Caja_NuevaVenta CPPFR= new Caja_NuevaVenta();
        Caja_NuevaVenta CPPFRDNI= new Caja_NuevaVenta();
        CPPFR.CAJA_PREVENTAS_FR(lblDNI_PREVENTA.getText(),tbFR);
        if(tbFR.getRowCount()==0){
            CPPFRDNI.CAJA_PREVENTAS_FR(lblNOMBRE_PREVENTA.getText(),tbFR);
        }
        if(tbFR.getRowCount()>0){
            CuentasPorPagarFacturasCabecera bf = new CuentasPorPagarFacturasCabecera();
            if(cbxTipoDocumento.getSelectedItem().equals("FACTURA")){
            bf.generarSerieCorrelativoFARMACIA("F",lblusu.getText());
            }else if(cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
            bf.generarSerieCorrelativoFARMACIA("B",lblusu.getText());
            }
            jPanel15.setVisible(true);
            System.out.println("SE ENCONTRO UNA PREVENTA");
            jLabel35.setText("Farmacia ["+String.valueOf(tbFR.getRowCount())+"]"); 
            tbFR.getSelectionModel().setSelectionInterval (0,0) ;
            try {
                int fila=tbFR.getSelectedRow();
                Caja_NuevaVenta CPFR = new Caja_NuevaVenta();
                CPFR.CAJA_PREVENTAS_FR_DETALLE(String.valueOf(tbFR.getValueAt(fila, 4)),tFRDet);
                lblID_CAB_PREVENTAS.setText(String.valueOf(tbFR.getValueAt(fila, 4)));
            } catch (Exception e) {
            }
            panelCPT1.setVisible(false);
            lbl6.setVisible(false);
            
        }else if(tbFR.getRowCount()==0){
            System.out.println("No existe Preventa");
            panelCPT1.setVisible(true);
            lbl6.setVisible(true);
            CPT.setVisible(true);
        }
            
    }
    
    public static void addEscapeListenerWindowDialog( final JDialog windowDialog) {
        ActionListener escAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        windowDialog.dispose();
        }
        };
        windowDialog.getRootPane().registerKeyboardAction(escAction,
        KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
        JComponent.WHEN_IN_FOCUSED_WINDOW);
   }
    public void Formato(){
        tb_CPT.getColumnModel().getColumn(0).setMinWidth(0);
        tb_CPT.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_CPT.getColumnModel().getColumn(1).setPreferredWidth(80);
        tb_CPT.getColumnModel().getColumn(2).setPreferredWidth(600);
        tb_CPT.getColumnModel().getColumn(3).setPreferredWidth(80);
        tb_CPT.getColumnModel().getColumn(4).setPreferredWidth(80);
        tb_CPT.getColumnModel().getColumn(5).setPreferredWidth(80);
        tb_CPT.getColumnModel().getColumn(6).setPreferredWidth(80);
    }
    
    public void SalirFormulario(){
        if(tb_CPT.getRowCount()==0){       
                    btnImprimir.setEnabled(true);
                    panelEliminar.setVisible(true);   
                    panelMensaje.setVisible(false);  
                    panelEliminacion.setVisible(false);  
//                    tgnuevoEliminar=1;
                    txtEnterEscapeEnter1.requestFocus();     
        }  
        if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
//                    lblImpresora.setText("original"); 
                    btnTerminiarVenta.setText("Terminar y Generar Boleta");
                    btnImprimir.setEnabled(true);
                    panelAnular.setVisible(true);
                    panelMensaje.setVisible(false);  
                    txtEnterEscapeEnter.requestFocus();
                    calcularValores_BOLETA();
        }else if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("RECIBO")){
//                    lblImpresora.setText("original"); 
                    btnTerminiarVenta.setText("Terminar y Generar Recibo");
                    btnImprimir.setEnabled(true);
                    panelAnular.setVisible(true);
                    panelMensaje.setVisible(false);  
                    txtEnterEscapeEnter.requestFocus();
                    calcularValores_BOLETA();
        }else  if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("FACTURA")){
                    btnTerminiarVenta.setText("Terminar y Generar Factura");
                    btnImprimir.setEnabled(true);
                    panelAnular.setVisible(true);
                    panelMensaje.setVisible(false);  
                    txtEnterEscapeEnter.requestFocus();
                    
        }
        if(lblCliente.getText().equals("Cliente") && tb_CPT.getRowCount()==0){ 
                   dispose();
        }
    }
    
        public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    SalirFormulario();
                }
});
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void CARGAR_CPT(){
        String  ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD,SUBTOTAL,DESCUENTO;
        int i=tb_CPTBUSCAR.getSelectedRow();
        lblGrupo.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(i, 4))); 
        ID_CPT = tb_CPTBUSCAR.getValueAt(i, 0).toString();
        ID_GRUPO = tb_CPTBUSCAR.getValueAt(i, 1).toString();
        CPTs = tb_CPTBUSCAR.getValueAt(i, 2).toString();
        DESCRIPCION = tb_CPTBUSCAR.getValueAt(i, 3).toString();
        //Cargar los datos a la otra tabla 
        CANTIDAD=txtCantidad.getText();
        double c=0.00,p=0.00,t=0.00,DESCT=0.00,DC=0.00;
        c=Double.parseDouble(txtCantidad.getText());  
        p=Double.parseDouble(tb_CPTBUSCAR.getValueAt(i, 3).toString());
        DESCT=Double.parseDouble(txtPorcentaje.getText());  
        t=c*p;
        DC=t*(DESCT/100);
        SUBTOTAL=(String.valueOf(t) );
        DESCUENTO=(String.valueOf(DC) );
        if(tb_CPT.getRowCount()==0){
            modelos = (DefaultTableModel) tb_CPT.getModel();
            String filaelemento[] = {ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD,SUBTOTAL,DESCUENTO};
            modelos.addRow(filaelemento);     
          }
          else{
           if(repiteDetalle()==true){
               System.out.println("El Producto ya ha sido ingresado");
          } 
           else{
                m1=(DefaultTableModel) tb_CPT.getModel();
           String filaelemento[]={ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD,SUBTOTAL,DESCUENTO};
               m1.addRow(filaelemento);   
           }
          }
        PanelCantidad.setVisible(false);
        jScrollPane4.setVisible(true); 
        panelNumeros.setVisible(true); 
        txtBuscarCPT.setText("");
        CPT.dispose();
        Formato();
        btnBuscarCPT.requestFocus();
    }
    
    public void CARGAR_TB_FR(){
        try {        
            modeloFR = (DefaultTableModel) tFRDet.getModel();          
            if(tFRDet.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "No hay registros que cargar");
            }else{
            //pasar datos de una tabla a otra
            for (int i=0;i<modeloFR.getRowCount(); i++){
                String  ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD,SUBTOTAL;
                
                ////////////////////////////////////////////////////////////////
                ID_CPT = tFRDet.getValueAt(i, 7).toString();
                ID_GRUPO = tFRDet.getValueAt(i, 10).toString();
                CPTs = tFRDet.getValueAt(i, 2).toString();
                DESCRIPCION = tFRDet.getValueAt(i, 4).toString();
                //Cargar los datos a la otra tabla 
                CANTIDAD=tFRDet.getValueAt(i, 3).toString();
                double c=0.00,p=0.00,t=0.00;
                c=Double.parseDouble(tFRDet.getValueAt(i, 3).toString());
                p=Double.parseDouble(tFRDet.getValueAt(i, 4).toString());
                t=c*p;
                SUBTOTAL=(String.valueOf(t) );
                BigDecimal ST = new BigDecimal(SUBTOTAL);
                ST = ST.setScale(2, BigDecimal.ROUND_HALF_UP);
                String STS;
                STS=(String.valueOf(ST) );

                //Cargar los datos a la otra tabla 
                modeloFR2 = (DefaultTableModel) tb_CPT.getModel();
                String filaelemento[] = {ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD,STS};
                modeloFR2.addRow(filaelemento);     
            }   
                PanelCantidad.setVisible(false);
                jScrollPane4.setVisible(true); 
                panelNumeros.setVisible(true); 
                Formato();
                Preventas.dispose();
        }    
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR LOS DATOSsss " + e.getMessage()); 
        }
    }
    
    public boolean repiteDetalle(){
         int filaselec=tb_CPTBUSCAR.getSelectedRow();
         boolean c=false;
         for (int i = 0; i < tb_CPT.getRowCount(); i++){    
               if(tb_CPT.getValueAt(i, 0).toString().equalsIgnoreCase(tb_CPTBUSCAR.getValueAt(filaselec, 0).toString())){
                    c=true;
			}}
               return c;
     }

      private void SUMA(){
        double total = 0;
        double subtotal =0.00;
        double IGV =0.00;
        double DESCUENTO =0.00;
        double TD =0.00;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for( int i=0 ; i<tb_CPT.getRowCount() ; i++){
            double numero =0,st=0;
            try{
                //capturamos valor de celda
                numero = (Double.parseDouble(tb_CPT.getValueAt(i, 3).toString() )*Double.parseDouble(tb_CPT.getValueAt(i, 4).toString() ));
                
  
            }
            catch (NumberFormatException nfe){ //si existe un error se coloca 0 a la celda
                numero = 0;
//                tb_CPT.setValueAt(0, i, 4);
                System.out.println("error" + nfe.getMessage());
            }
            //se suma al total
          total += numero;
        }
//        IGV=total*0.18;
//        subtotal=total-IGV;
        subtotal=total/1.18;//SUBTOTAL
        IGV=total-subtotal;//IGV
        double MD=Double.parseDouble(txtPorcentaje.getText());
        DESCUENTO=total*(MD/100);
        TD=total-DESCUENTO;
        ////////////////////////////////////////////////////////////////////////
        
        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal bd3 = new BigDecimal(IGV);
        bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal bd4 = new BigDecimal(subtotal);
        bd4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal bd5 = new BigDecimal(total-IGV);
        bd5 = bd5.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal bd6 = new BigDecimal(DESCUENTO);
        bd6 = bd6.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal bd7 = new BigDecimal(TD);
        bd7 = bd7.setScale(2, BigDecimal.ROUND_HALF_UP);
        ////////////////////////////////////////////////////////////////////////
        //Ventas Gravadas e Inafectas

        

        if(cbxFormaPago.getSelectedItem().equals("EXONERACION")||cbxFormaPago.getSelectedItem().equals("DONACIONES")){
            if (lblGrupo.getText().equals("30 INAFECTO-OPERACIÓN ONEROSA")){
                this.txtSubTotal.setText(String.valueOf(bd2) );
                this.txtIGV.setText("0.00");
                this.txtTotal.setText(String.valueOf(bd7) );
                this.lblDESCUENTO.setText(String.valueOf(bd6));
                lblMontos.setText("SubTotal   S/ "+String.valueOf(bd2)+"       "+"Descuento   S/ "+String.valueOf(bd6)+"       "+"Total a Pagar    S/ "+String.valueOf(bd7));
                lblValorVentaInafectada.setText(String.valueOf(bd2));
                lblValorVentaGravada.setText("0.00");
            }else 
            if (lblGrupo.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                this.txtSubTotal.setText(String.valueOf(bd4) );
                this.txtIGV.setText(String.valueOf(bd3) );
                this.txtTotal.setText(String.valueOf(bd7) );
                this.lblDESCUENTO.setText(String.valueOf(bd6));
                lblMontos.setText("Subtotal    S/ "+String.valueOf(bd4)+"       "+"IGV    S/ "+String.valueOf(bd3)+"       "+"Descuento    S/. "+String.valueOf(bd6)+"       "+"Total a Pagar    S/. "+String.valueOf(bd7));
                lblValorVentaInafectada.setText("0.00");
                lblValorVentaGravada.setText(String.valueOf(bd5));
            }
        }else if(!cbxFormaPago.getSelectedItem().equals("EXONERACION")||!cbxFormaPago.getSelectedItem().equals("DONACIONES")){
            if (lblGrupo.getText().equals("30 INAFECTO-OPERACIÓN ONEROSA")){
                this.txtSubTotal.setText(String.valueOf(bd2) );
                this.txtIGV.setText("0.00");
                this.txtTotal.setText(String.valueOf(bd2) );
                lblMontos.setText("Total a Pagar    S/ "+String.valueOf(bd2));
                lblValorVentaInafectada.setText(String.valueOf(bd2));
                lblValorVentaGravada.setText("0.00");
            }else 
            if (lblGrupo.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                this.txtSubTotal.setText(String.valueOf(bd4) );
                this.txtIGV.setText(String.valueOf(bd3) );
                this.txtTotal.setText(String.valueOf(bd2) );
                lblMontos.setText("Subtotal    S/ "+String.valueOf(bd4)+"       "+"IGV    S/ "+String.valueOf(bd3)+"       "+"Total a Pagar    S/. "+String.valueOf(bd2));
                lblValorVentaInafectada.setText("0.00");
                lblValorVentaGravada.setText(String.valueOf(bd5));
            }
        }
        
        
        
    }
    
  
    public DefaultComboBoxModel CargarFP(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = ConexionS.createStatement();
              r = st.executeQuery ("EXEC CAJA_PAGOS_LISTAR_FORMA_PAGO"); 
//              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "DESCIPCION" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "ERROR CARGAR FP :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public void Nuevo(boolean opcion){
        cbxFormaPago.setVisible(opcion);
        panelCPT3.setVisible(opcion);
        txtSerieC.setVisible(opcion);
        txtSerie1.setVisible(opcion);
        lblNroCorrelativoC.setVisible(opcion);
        panelCPT1.setVisible(opcion);
        lbl2.setVisible(opcion); 
        lbl4.setVisible(opcion); 
        lbl5.setVisible(opcion); 
        lbl6.setVisible(opcion); 
        jScrollPane4.setVisible(opcion); 
        panelNumeros.setVisible(opcion); 
        panelProcentaje.setVisible(opcion);
        lblPorcentaje.setVisible(opcion);
        DefaultTableModel modelo1 = (DefaultTableModel)tb_CPT.getModel(); 
        int b=tb_CPT.getRowCount();
        for(int j=0;j<b;j++){
                    modelo1.removeRow(0);
        }
    }
    
    public void DetalleVenta(boolean opcion){
        cbxTipoDocumento.setEnabled(opcion);
        cbxFormaPago.setEnabled(opcion);
        txtCliente.setEditable(opcion);
        btnBuscarCPT1.setVisible(opcion);
    }
    
    public void Limpiar(){
        txtCliente.setText("");
        lblCliente.setText("Cliente");
        lblDocumento.setText("DNI");
        lblID_CABECERA.setText("A");
        btnTerminiarVenta.setEnabled(true);
        txtPorcentaje.setText("0");
        lblDESCUENTO.setText("0");
 
    }
    
    public void NUEVO_REGISTRO(java.sql.Connection con) {
        try {
            panleBoleta.setVisible(false);
            Caja_NuevaVenta cno2 = new Caja_NuevaVenta();
            cno2.DATOS_FP(cbxFormaPago.getSelectedItem().toString());
            CuentasPorPagarFacturasCabecera cuentasCab2 = new CuentasPorPagarFacturasCabecera();
            if(cbxTipoDocumento.getSelectedItem().equals("FACTURA")){
                cuentasCab2.generarSerieCorrelativo("F",lblusu.getText());
            }else if(cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
                cuentasCab2.generarSerieCorrelativo("B",lblusu.getText());
            }else if(cbxTipoDocumento.getSelectedItem().equals("RECIBO")){
                cuentasCab2.generarSerieCorrelativoRECIBO(lblusu.getText());
            }
//            Caja_NuevaVenta cno3 = new Caja_NuevaVenta();
//            cno3.DATOS_FP(cbxFormaPago.getSelectedItem().toString());
            
            // instanciamos el objeto callable
            CallableStatement cstmt = con.prepareCall("exec CAJA_VENTA_CABECERA_NUEVO ?,?,?,?,?,?,?,?");
            // seteamos los parametros de entrada
            cstmt.setInt(1, Integer.parseInt(lblID_Documento.getText()));
            cstmt.setInt(2, Integer.parseInt(lblIDCliente.getText()));
            cstmt.setString(3, txtSerieC.getText());
            cstmt.setString(4, lblNroCorrelativoC.getText());
            cstmt.setString(5, lblusu.getText());
            if(cbxTipoDocumento.getSelectedItem().equals("BOLETA"))
                  cstmt.setString(6, "B");
            else if(cbxTipoDocumento.getSelectedItem().equals("FACTURA"))
                  cstmt.setString(6, "F");
            else if(cbxTipoDocumento.getSelectedItem().equals("RECIBO"))
                  cstmt.setString(6, "N");
            cstmt.setInt(7, (Integer.parseInt(lblSESION.getText())));
            // registramos el parametro de retorno (si fueran mas, repetimos la linea cambiando el nro de orden del parametro)
            cstmt.registerOutParameter(8, java.sql.Types.INTEGER);
            // ejecutamos
            cstmt.execute();
            // mostramos al usuario el codigo creado
            int ID;
            ID=cstmt.getInt(8);
            this.lblID_CABECERA.setText( String.valueOf(ID) );
            System.out.println("CABECERA GUARDADA");  
            panelMensaje.setVisible(false);
            txtBuscarCliente.setText("");
            lbl5.setVisible(true);
            txtSerieC.setVisible(true);
            txtSerie1.setVisible(true);
            lblNroCorrelativoC.setVisible(true);
            DetalleVenta(false);
            
//            if(tbFR.getRowCount()==0){
//                CPT.setVisible(true);
//                System.out.println("vamo abriendo el cpt");
//            }else if(tbFR.getRowCount()>0){
//                System.out.println("vamo abriendo la preventa");
//            }
                
        }
        catch (Exception e) {
            panelMensaje.setVisible(true);
            panelMensaje.setBackground(new Color(255,51,51));
            Mensaje4.setText("Ocurrió un error verifique");
            btnCorrectoSi.setVisible(false);
            btnCorrectoNo.setVisible(false);
            e.printStackTrace();
        }
    }
    
    public void ACTUALIZAR_CABECERA(){
               
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setID_DOCUMENTO(Integer.parseInt(lblID_CABECERA.getText()));
                cno1.setSERIE(txtSerieC.getText());
                cno1.setCORRELATIVO(lblNroCorrelativoC.getText());
                cno1.setDESCUENTO(Double.parseDouble(lblDESCUENTO.getText()));
                cno1.setSUB_TOTAL(Double.parseDouble(txtSubTotal.getText()));
                cno1.setIGV(Double.parseDouble(txtIGV.getText()));
                cno1.setTOTAL_DOC(Double.parseDouble(txtTotal.getText()));
                cno1.setTIPO_GRUPO(lblGrupo.getText());
                cno1.setGRAVADA(Double.parseDouble(lblValorVentaGravada.getText()));
                cno1.setINAFECTA(Double.parseDouble(lblValorVentaInafectada.getText()));
                    if(cno1.ACTUALIZAR_VENTA()==true){
                        System.out.println("CABECERA ACTUALIZAD"); 
                    } else {
                        System.out.println("OCURRIO UN ERROR AL ACTUALIZAR LA CABECERA");
                    }
    }
    
    public void ACTUALIZAR_PREVENTA(){
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setID_PREVENTA(lblID_CAB_PREVENTAS.getText());
                cno1.ACTUALIZAR_PREVENTA();
                System.out.println("SE CAMBIO EL ESTADO DE LA PREVENTA"); 
                 
                    
    }
    public void ACTUALIZAR_PREVENTA_CAB(){
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setID_PREVENTA(lblID_CAB_PREVENTAS.getText());
                cno1.ACTUALIZAR_PREVENTA_CAB();
                System.out.println("SE CAMBIO EL ESTADO DE LA PREVENTA CAB");  
    }
    
    public void ANULAR_PREVENTA(){
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setDESCRIP_PRE(lblID_DETALLE.getText());
                cno1.ANULAR_PREVENTA();
                Caja_NuevaVenta CPFR = new Caja_NuevaVenta();
                CPFR.CAJA_PREVENTAS_FR_DETALLE(lblID_CAB.getName(),tFRDet);
                panelEliminarFR.setVisible(false);
                System.out.println("SE ANULO LA PREVENTA"); 
                   
    }
    
    
    public void NUEVO_REGISTRO_DETALLE(){
        CuentasPorPagarFacturasCabecera cuentasCab3 = new CuentasPorPagarFacturasCabecera();
            for(int i = 0; i < tb_CPT.getRowCount(); i++){
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setID_DOCUMENTO(Integer.parseInt(lblID_CABECERA.getText()));
                cno1.setID_PRECIO(Integer.parseInt(String.valueOf(tb_CPT.getValueAt(i, 0))));
                cno1.setCANTIDAD(Integer.parseInt(String.valueOf(tb_CPT.getValueAt(i, 4))));
                cno1.setPRECIO(Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3))));
                int cant=0;
                Double Prec=0.0,total=0.0,DESCT=0.0;
                cant=Integer.parseInt(String.valueOf(tb_CPT.getValueAt(i, 4)));
                Prec=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)));
                total=cant*Prec;
                double por=Double.parseDouble(txtPorcentaje.getText());
                DESCT=total*por/100;
                cno1.setTOTAL(total);
                if(lblGrupo.getText().equals("30 INAFECTO-OPERACIÓN ONEROSA")){
                    cno1.setIGVD(0.00);
                    cno1.setDESCUENTOD(Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 6))));
                }else if(lblGrupo.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                   cno1.setIGVD(total*0.18); 
                   cno1.setDESCUENTOD(Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 6))));
                }
                cno1.setUSUARIO(lblusu.getText());
                    if(cno1.NUEVA_VENTA_DETALLE()==true){
                        System.out.println("DETALLE DE VENTA GUARDADO"); 
                    } else {
                        panelMensaje.setVisible(true);
                        panelMensaje.setBackground(new Color(255,51,51)); 
                        Mensaje4.setText("Ocurrió un error verifique");
                        btnCorrectoSi.setVisible(false);
                        btnCorrectoNo.setVisible(false);
                    }
            }
//            cuentasCab3.generarSerieCorrelativo("B",lblusu.getText());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CLIENTES = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bus = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtBuscarCliente = new javax.swing.JTextField();
        bus3 = new javax.swing.JLabel();
        btnBuscarPaciente2 = new javax.swing.JButton();
        lblDNI_PREVENTA = new javax.swing.JLabel();
        lblNOMBRE_PREVENTA = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        ABONOS = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            lblIdPreventa = new javax.swing.JLabel();
            jScrollPane9 = new javax.swing.JScrollPane();
            tbpreventas = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jScrollPane27 = new javax.swing.JScrollPane();
                tbpreventasFR = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    panelBuscarHC = new javax.swing.JPanel();
                    jLabel9 = new javax.swing.JLabel();
                    paneltablaHC = new javax.swing.JPanel();
                    jScrollPane2 = new javax.swing.JScrollPane();
                    tbClientes = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jPanel35 = new javax.swing.JPanel();
                        jLabel40 = new javax.swing.JLabel();
                        jLabel47 = new javax.swing.JLabel();
                        jLabel50 = new javax.swing.JLabel();
                        panelSinHC = new javax.swing.JPanel();
                        jLabel16 = new javax.swing.JLabel();
                        jLabel17 = new javax.swing.JLabel();
                        jPanel9 = new javax.swing.JPanel();
                        jLabel18 = new javax.swing.JLabel();
                        CPT = new javax.swing.JDialog();
                        jPanel11 = new javax.swing.JPanel();
                        jLabel21 = new javax.swing.JLabel();
                        jPanel28 = new javax.swing.JPanel();
                        txtBuscarCPT = new javax.swing.JTextField();
                        btnBuscarPaciente3 = new javax.swing.JButton();
                        lblTIPO = new javax.swing.JLabel();
                        panelBuscar = new javax.swing.JPanel();
                        jLabel22 = new javax.swing.JLabel();
                        panelCargarCPT = new javax.swing.JPanel();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        tb_CPTBUSCAR = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jLabel3 = new javax.swing.JLabel();
                            jLabel8 = new javax.swing.JLabel();
                            jLabel10 = new javax.swing.JLabel();
                            jPanel33 = new javax.swing.JPanel();
                            PanelCantidad = new javax.swing.JPanel();
                            txtCantidad = new javax.swing.JTextField();
                            jLabel4 = new javax.swing.JLabel();
                            btnImprimir2 = new javax.swing.JButton();
                            panelSinCPT = new javax.swing.JPanel();
                            jLabel23 = new javax.swing.JLabel();
                            jLabel24 = new javax.swing.JLabel();
                            jPanel16 = new javax.swing.JPanel();
                            jLabel25 = new javax.swing.JLabel();
                            ErrorExistente = new javax.swing.JDialog();
                            jPanel134 = new javax.swing.JPanel();
                            lblAd1 = new javax.swing.JLabel();
                            jLabel119 = new javax.swing.JLabel();
                            jPanel135 = new javax.swing.JPanel();
                            btnAlertConsulta7 = new javax.swing.JButton();
                            Preventas = new javax.swing.JDialog();
                            jPanel14 = new javax.swing.JPanel();
                            jLabel20 = new javax.swing.JLabel();
                            jLabel35 = new javax.swing.JLabel();
                            lblID_DETALLE = new javax.swing.JLabel();
                            lblID_CAB = new javax.swing.JLabel();
                            jScrollPane28 = new javax.swing.JScrollPane();
                            tbFR = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                jScrollPane29 = new javax.swing.JScrollPane();
                                tFRDet = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    panelEliminarFR = new javax.swing.JPanel();
                                    Mensaje9 = new javax.swing.JLabel();
                                    eli9 = new javax.swing.JButton();
                                    noeli7 = new javax.swing.JButton();
                                    panelOpcionesFR = new javax.swing.JPanel();
                                    btnCargarHOS1 = new javax.swing.JButton();
                                    btnEliminarHOS1 = new javax.swing.JButton();
                                    Afecto = new javax.swing.JDialog();
                                    jPanel145 = new javax.swing.JPanel();
                                    jLabel64 = new javax.swing.JLabel();
                                    jPanel146 = new javax.swing.JPanel();
                                    btnAlertConsulta10 = new javax.swing.JButton();
                                    jLabel67 = new javax.swing.JLabel();
                                    Inafecto = new javax.swing.JDialog();
                                    jPanel147 = new javax.swing.JPanel();
                                    jLabel65 = new javax.swing.JLabel();
                                    jPanel148 = new javax.swing.JPanel();
                                    btnAlertConsulta11 = new javax.swing.JButton();
                                    jLabel68 = new javax.swing.JLabel();
                                    jPanel1 = new javax.swing.JPanel();
                                    jLabel57 = new javax.swing.JLabel();
                                    jPanel23 = new javax.swing.JPanel();
                                    txtBusquedas = new javax.swing.JTextField();
                                    btnBuscarPaciente = new javax.swing.JButton();
                                    lbldetalle = new javax.swing.JLabel();
                                    btnNuevo = new javax.swing.JButton();
                                    btnImprimir = new javax.swing.JButton();
                                    btneliminar = new javax.swing.JButton();
                                    btnLista = new javax.swing.JButton();
                                    lblusu1 = new javax.swing.JLabel();
                                    lblusu = new javax.swing.JLabel();
                                    lblID_SESION = new javax.swing.JLabel();
                                    lblID_DOCUEMENTO = new javax.swing.JLabel();
                                    Tipo_Documento = new javax.swing.JLabel();
                                    lblTIPO_DOC = new javax.swing.JLabel();
                                    jPanel13 = new javax.swing.JPanel();
                                    txtDsctoGlobal = new javax.swing.JLabel();
                                    txtOtrosCargos = new javax.swing.JLabel();
                                    txtTotalDscto = new javax.swing.JLabel();
                                    txtVentaExonerada = new javax.swing.JLabel();
                                    txtMtoISC = new javax.swing.JLabel();
                                    txtOtrosTributos = new javax.swing.JLabel();
                                    lblIGV_B = new javax.swing.JLabel();
                                    precioV_B = new javax.swing.JLabel();
                                    venta_B = new javax.swing.JLabel();
                                    LBL_CPF_ID = new javax.swing.JLabel();
                                    Paginas = new javax.swing.JTabbedPane();
                                    jPanel3 = new javax.swing.JPanel();
                                    jPanel12 = new javax.swing.JPanel();
                                    lblCliente2 = new javax.swing.JLabel();
                                    jScrollPane12 = new javax.swing.JScrollPane();
                                    tb_ReporteDiario1 = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};
                                        panelDetalleHistorial = new javax.swing.JPanel();
                                        jScrollPane5 = new javax.swing.JScrollPane();
                                        tb_Clientes1 = new javax.swing.JTable(){
                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                return false; //Disallow the editing of any cell
                                            }};
                                            jPanel4 = new javax.swing.JPanel();
                                            lbl4 = new javax.swing.JLabel();
                                            lbl1 = new javax.swing.JLabel();
                                            cbxFormaPago = new javax.swing.JComboBox();
                                            lbl5 = new javax.swing.JLabel();
                                            lbl6 = new javax.swing.JLabel();
                                            panelCPT1 = new javax.swing.JPanel();
                                            txtCPT = new javax.swing.JTextField();
                                            btnBuscarCPT = new javax.swing.JButton();
                                            jScrollPane4 = new javax.swing.JScrollPane();
                                            tb_CPT = new javax.swing.JTable(){
                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                    return false; //Disallow the editing of any cell
                                                }};
                                                panelEliminacion = new javax.swing.JPanel();
                                                btnEliminarDetalle = new javax.swing.JButton();
                                                noeli5 = new javax.swing.JButton();
                                                eli5 = new javax.swing.JButton();
                                                panelCPT3 = new javax.swing.JPanel();
                                                txtCliente = new javax.swing.JTextField();
                                                btnBuscarCPT1 = new javax.swing.JButton();
                                                lblSerie = new javax.swing.JLabel();
                                                lblCorrelativo = new javax.swing.JLabel();
                                                lblID_Documento = new javax.swing.JLabel();
                                                lbl2 = new javax.swing.JLabel();
                                                cbxTipoDocumento = new javax.swing.JComboBox();
                                                panelMensaje = new javax.swing.JPanel();
                                                Mensaje4 = new javax.swing.JLabel();
                                                btnCorrectoSi = new javax.swing.JButton();
                                                btnCorrectoNo = new javax.swing.JButton();
                                                jPanel2 = new javax.swing.JPanel();
                                                jPanel10 = new javax.swing.JPanel();
                                                lblCliente = new javax.swing.JLabel();
                                                lblDocumento1 = new javax.swing.JLabel();
                                                lblDocumento = new javax.swing.JLabel();
                                                lblId = new javax.swing.JLabel();
                                                lblSESION = new javax.swing.JLabel();
                                                lblID_CABECERA = new javax.swing.JLabel();
                                                panelAnular = new javax.swing.JPanel();
                                                btnTerminiarVenta = new javax.swing.JButton();
                                                txtEnterEscapeEnter = new javax.swing.JTextField();
                                                jPanel17 = new javax.swing.JPanel();
                                                btnAnularVenta = new javax.swing.JButton();
                                                panelEliminar = new javax.swing.JPanel();
                                                Mensaje5 = new javax.swing.JLabel();
                                                btnEliminarSi = new javax.swing.JButton();
                                                btnEliminarNo = new javax.swing.JButton();
                                                txtEnterEscapeEnter1 = new javax.swing.JTextField();
                                                lblGrupo = new javax.swing.JLabel();
                                                panelNumeros = new javax.swing.JPanel();
                                                lblMontos = new javax.swing.JLabel();
                                                txtSubTotal = new javax.swing.JLabel();
                                                txtIGV = new javax.swing.JLabel();
                                                txtTotal = new javax.swing.JLabel();
                                                jButton1 = new javax.swing.JButton();
                                                jButton2 = new javax.swing.JButton();
                                                jPanel30 = new javax.swing.JPanel();
                                                lblDESCUENTO = new javax.swing.JLabel();
                                                lbltipo_DOC_boleta = new javax.swing.JLabel();
                                                lblApeNom = new javax.swing.JLabel();
                                                lblFechaEmision = new javax.swing.JLabel();
                                                lblValorVentaGravada = new javax.swing.JLabel();
                                                lblValorVentaInafectada = new javax.swing.JLabel();
                                                lblIDCliente = new javax.swing.JTextField();
                                                lblNroCorrelativoC = new javax.swing.JLabel();
                                                txtSerieC = new javax.swing.JLabel();
                                                txtSerie1 = new javax.swing.JLabel();
                                                lblID_CAB_PREVENTAS = new javax.swing.JLabel();
                                                jPanel15 = new javax.swing.JPanel();
                                                Mensaje6 = new javax.swing.JLabel();
                                                panleBoleta = new javax.swing.JPanel();
                                                Mensaje7 = new javax.swing.JLabel();
                                                ImpS = new javax.swing.JButton();
                                                ImpN = new javax.swing.JButton();
                                                lblPorcentaje = new javax.swing.JLabel();
                                                panelProcentaje = new javax.swing.JPanel();
                                                txtPorcentaje = new javax.swing.JTextField();
                                                jLabel1 = new javax.swing.JLabel();
                                                jPanel5 = new javax.swing.JPanel();
                                                jPanel6 = new javax.swing.JPanel();
                                                lblCliente1 = new javax.swing.JLabel();
                                                btnImprimir1 = new javax.swing.JButton();
                                                lblTR = new javax.swing.JLabel();
                                                jScrollPane11 = new javax.swing.JScrollPane();
                                                tb_ReporteDiario = new javax.swing.JTable(){
                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                        return false; //Disallow the editing of any cell
                                                    }};
                                                    panelDetalle = new javax.swing.JPanel();
                                                    jScrollPane3 = new javax.swing.JScrollPane();
                                                    tb_Clientes = new javax.swing.JTable(){
                                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                                            return false; //Disallow the editing of any cell
                                                        }};

                                                        CLIENTES.setAlwaysOnTop(true);
                                                        CLIENTES.setMinimumSize(new java.awt.Dimension(749, 350));
                                                        CLIENTES.setResizable(false);

                                                        jPanel7.setBackground(new java.awt.Color(230, 230, 230));
                                                        jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                                                        jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel19.setText("Clientes");

                                                        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel13.setText("DNI, RUC, Apellidos o Razón Social");

                                                        bus.setForeground(new java.awt.Color(230, 230, 230));
                                                        bus.setText("jLabel37");

                                                        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                                                        txtBuscarCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        txtBuscarCliente.setForeground(new java.awt.Color(98, 98, 98));
                                                        txtBuscarCliente.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtBuscarCliente.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                txtBuscarClienteCaretUpdate(evt);
                                                            }
                                                        });
                                                        txtBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                txtBuscarClienteMouseClicked(evt);
                                                            }
                                                        });
                                                        txtBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                txtBuscarClienteActionPerformed(evt);
                                                            }
                                                        });
                                                        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                txtBuscarClienteKeyPressed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                                                        jPanel27.setLayout(jPanel27Layout);
                                                        jPanel27Layout.setHorizontalGroup(
                                                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel27Layout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())
                                                        );
                                                        jPanel27Layout.setVerticalGroup(
                                                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        bus3.setForeground(new java.awt.Color(230, 230, 230));
                                                        bus3.setText("jLabel37");

                                                        btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                                                        btnBuscarPaciente2.setContentAreaFilled(false);
                                                        btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnBuscarPaciente2ActionPerformed(evt);
                                                            }
                                                        });

                                                        lblDNI_PREVENTA.setForeground(new java.awt.Color(230, 230, 230));
                                                        lblDNI_PREVENTA.setText("jLabel1");

                                                        lblNOMBRE_PREVENTA.setForeground(new java.awt.Color(230, 230, 230));
                                                        lblNOMBRE_PREVENTA.setText("jLabel1");

                                                        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                                                        jPanel7.setLayout(jPanel7Layout);
                                                        jPanel7Layout.setHorizontalGroup(
                                                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                .addComponent(jLabel13)
                                                                                .addGap(39, 39, 39)
                                                                                .addComponent(bus)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblNOMBRE_PREVENTA)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(bus3))
                                                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(2, 2, 2)
                                                                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                                        .addComponent(jLabel19)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(lblDNI_PREVENTA)
                                                                        .addGap(148, 148, 148))))
                                                        );
                                                        jPanel7Layout.setVerticalGroup(
                                                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(jLabel19)
                                                                            .addComponent(lblDNI_PREVENTA))
                                                                        .addGap(10, 10, 10)
                                                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(bus)
                                                                            .addComponent(bus3)
                                                                            .addComponent(lblNOMBRE_PREVENTA))))
                                                                .addGap(331, 331, 331))
                                                        );

                                                        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

                                                        ABONOS.setModel(new javax.swing.table.DefaultTableModel(
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
                                                        ABONOS.setGridColor(new java.awt.Color(255, 255, 255));
                                                        ABONOS.setRowHeight(25);
                                                        ABONOS.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                        ABONOS.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                ABONOSMouseClicked(evt);
                                                            }
                                                        });
                                                        ABONOS.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                ABONOSKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane10.setViewportView(ABONOS);

                                                        lblIdPreventa.setText("jLabel57");

                                                        tbpreventas.setModel(new javax.swing.table.DefaultTableModel(
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
                                                        tbpreventas.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tbpreventas.setRowHeight(25);
                                                        tbpreventas.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                        tbpreventas.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tbpreventasMouseClicked(evt);
                                                            }
                                                        });
                                                        tbpreventas.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tbpreventasKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane9.setViewportView(tbpreventas);

                                                        tbpreventasFR.setModel(new javax.swing.table.DefaultTableModel(
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
                                                        tbpreventasFR.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tbpreventasFR.setRowHeight(25);
                                                        tbpreventasFR.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                        tbpreventasFR.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tbpreventasFRMouseClicked(evt);
                                                            }
                                                        });
                                                        tbpreventasFR.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tbpreventasFRKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane27.setViewportView(tbpreventasFR);

                                                        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                                        jPanel8.setLayout(jPanel8Layout);
                                                        jPanel8Layout.setHorizontalGroup(
                                                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(10, 10, 10)
                                                                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addComponent(lblIdPreventa))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        jPanel8Layout.setVerticalGroup(
                                                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblIdPreventa)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        panelBuscarHC.setBackground(new java.awt.Color(255, 255, 255));

                                                        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 34)); // NOI18N
                                                        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Búsqueda-64.png"))); // NOI18N
                                                        jLabel9.setText("Busqueda de Clientes ");

                                                        javax.swing.GroupLayout panelBuscarHCLayout = new javax.swing.GroupLayout(panelBuscarHC);
                                                        panelBuscarHC.setLayout(panelBuscarHCLayout);
                                                        panelBuscarHCLayout.setHorizontalGroup(
                                                            panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelBuscarHCLayout.createSequentialGroup()
                                                                .addGap(134, 134, 134)
                                                                .addComponent(jLabel9)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        panelBuscarHCLayout.setVerticalGroup(
                                                            panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelBuscarHCLayout.createSequentialGroup()
                                                                .addGap(62, 62, 62)
                                                                .addComponent(jLabel9)
                                                                .addContainerGap(50, Short.MAX_VALUE))
                                                        );

                                                        paneltablaHC.setBackground(new java.awt.Color(255, 255, 255));

                                                        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                        tbClientes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        tbClientes.setForeground(new java.awt.Color(51, 51, 51));
                                                        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
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
                                                        tbClientes.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tbClientes.setRowHeight(25);
                                                        tbClientes.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                        tbClientes.getTableHeader().setReorderingAllowed(false);
                                                        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tbClientesMouseClicked(evt);
                                                            }
                                                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                                                tbClientesMouseEntered(evt);
                                                            }
                                                        });
                                                        tbClientes.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tbClientesKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane2.setViewportView(tbClientes);

                                                        jPanel35.setBackground(new java.awt.Color(102, 102, 102));
                                                        jPanel35.setPreferredSize(new java.awt.Dimension(0, 2));

                                                        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
                                                        jPanel35.setLayout(jPanel35Layout);
                                                        jPanel35Layout.setHorizontalGroup(
                                                            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                        );
                                                        jPanel35Layout.setVerticalGroup(
                                                            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                        );

                                                        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel40.setText("Documento");

                                                        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel47.setText(" Tipo Documento");

                                                        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel50.setText("Cliente");

                                                        javax.swing.GroupLayout paneltablaHCLayout = new javax.swing.GroupLayout(paneltablaHC);
                                                        paneltablaHC.setLayout(paneltablaHCLayout);
                                                        paneltablaHCLayout.setHorizontalGroup(
                                                            paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                                                            .addGroup(paneltablaHCLayout.createSequentialGroup()
                                                                .addComponent(jLabel47)
                                                                .addGap(36, 36, 36)
                                                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(66, 66, 66)
                                                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(301, Short.MAX_VALUE))
                                                            .addComponent(jScrollPane2)
                                                        );
                                                        paneltablaHCLayout.setVerticalGroup(
                                                            paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(paneltablaHCLayout.createSequentialGroup()
                                                                .addGroup(paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        panelSinHC.setBackground(new java.awt.Color(255, 255, 255));

                                                        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel16.setText("No se hallaron coincidencias");

                                                        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 100)); // NOI18N
                                                        jLabel17.setForeground(new java.awt.Color(0, 153, 102));
                                                        jLabel17.setText(":(");

                                                        jPanel9.setBackground(new java.awt.Color(0, 153, 102));

                                                        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
                                                        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                                        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Invitado masculino-64.png"))); // NOI18N
                                                        jLabel18.setText("Nuevo Registro");
                                                        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                                                        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                jLabel18MouseClicked(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                                        jPanel9.setLayout(jPanel9Layout);
                                                        jPanel9Layout.setHorizontalGroup(
                                                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                                        );
                                                        jPanel9Layout.setVerticalGroup(
                                                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        javax.swing.GroupLayout panelSinHCLayout = new javax.swing.GroupLayout(panelSinHC);
                                                        panelSinHC.setLayout(panelSinHCLayout);
                                                        panelSinHCLayout.setHorizontalGroup(
                                                            panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel17)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel16)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );
                                                        panelSinHCLayout.setVerticalGroup(
                                                            panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                .addGroup(panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                        .addGap(32, 32, 32)
                                                                        .addComponent(jLabel17))
                                                                    .addGroup(panelSinHCLayout.createSequentialGroup()
                                                                        .addGap(87, 87, 87)
                                                                        .addComponent(jLabel16)))
                                                                .addContainerGap(202, Short.MAX_VALUE))
                                                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );

                                                        javax.swing.GroupLayout CLIENTESLayout = new javax.swing.GroupLayout(CLIENTES.getContentPane());
                                                        CLIENTES.getContentPane().setLayout(CLIENTESLayout);
                                                        CLIENTESLayout.setHorizontalGroup(
                                                            CLIENTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelBuscarHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(paneltablaHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelSinHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        CLIENTESLayout.setVerticalGroup(
                                                            CLIENTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(CLIENTESLayout.createSequentialGroup()
                                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelBuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(paneltablaHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelSinHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        );

                                                        CPT.setAlwaysOnTop(true);
                                                        CPT.setMinimumSize(new java.awt.Dimension(850, 338));
                                                        CPT.setResizable(false);

                                                        jPanel11.setBackground(new java.awt.Color(230, 230, 230));
                                                        jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                                                        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel21.setText("Ítems");

                                                        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

                                                        txtBuscarCPT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        txtBuscarCPT.setForeground(new java.awt.Color(98, 98, 98));
                                                        txtBuscarCPT.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtBuscarCPT.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                txtBuscarCPTCaretUpdate(evt);
                                                            }
                                                        });
                                                        txtBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                txtBuscarCPTActionPerformed(evt);
                                                            }
                                                        });
                                                        txtBuscarCPT.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                txtBuscarCPTKeyPressed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                                                        jPanel28.setLayout(jPanel28Layout);
                                                        jPanel28Layout.setHorizontalGroup(
                                                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel28Layout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(txtBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        );
                                                        jPanel28Layout.setVerticalGroup(
                                                            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(txtBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        btnBuscarPaciente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                                                        btnBuscarPaciente3.setContentAreaFilled(false);
                                                        btnBuscarPaciente3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnBuscarPaciente3.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnBuscarPaciente3ActionPerformed(evt);
                                                            }
                                                        });

                                                        lblTIPO.setForeground(new java.awt.Color(230, 230, 230));
                                                        lblTIPO.setText("jLabel1");

                                                        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                                        jPanel11.setLayout(jPanel11Layout);
                                                        jPanel11Layout.setHorizontalGroup(
                                                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel21)
                                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(42, 42, 42)
                                                                        .addComponent(lblTIPO)))
                                                                .addContainerGap(520, Short.MAX_VALUE))
                                                        );
                                                        jPanel11Layout.setVerticalGroup(
                                                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(lblTIPO)
                                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                                        .addComponent(jLabel21)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                            .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                                .addGap(350, 350, 350))
                                                        );

                                                        panelBuscar.setBackground(new java.awt.Color(255, 255, 255));

                                                        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Búsqueda-64.png"))); // NOI18N
                                                        jLabel22.setText("Busqueda de TUPA ");

                                                        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
                                                        panelBuscar.setLayout(panelBuscarLayout);
                                                        panelBuscarLayout.setHorizontalGroup(
                                                            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelBuscarLayout.createSequentialGroup()
                                                                .addGap(160, 160, 160)
                                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        panelBuscarLayout.setVerticalGroup(
                                                            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelBuscarLayout.createSequentialGroup()
                                                                .addGap(59, 59, 59)
                                                                .addComponent(jLabel22)
                                                                .addContainerGap(86, Short.MAX_VALUE))
                                                        );

                                                        panelCargarCPT.setBackground(new java.awt.Color(255, 255, 255));

                                                        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane6.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                        tb_CPTBUSCAR.setModel(new javax.swing.table.DefaultTableModel(
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
                                                        tb_CPTBUSCAR.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tb_CPTBUSCAR.setRowHeight(25);
                                                        tb_CPTBUSCAR.setRowMargin(0);
                                                        tb_CPTBUSCAR.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                        tb_CPTBUSCAR.getTableHeader().setReorderingAllowed(false);
                                                        tb_CPTBUSCAR.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tb_CPTBUSCARMouseClicked(evt);
                                                            }
                                                        });
                                                        tb_CPTBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tb_CPTBUSCARKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane6.setViewportView(tb_CPTBUSCAR);

                                                        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel3.setText("  Ítem");

                                                        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel8.setText("Descripción");

                                                        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel10.setText("Precio");

                                                        jPanel33.setBackground(new java.awt.Color(102, 102, 102));
                                                        jPanel33.setPreferredSize(new java.awt.Dimension(0, 2));

                                                        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                                                        jPanel33.setLayout(jPanel33Layout);
                                                        jPanel33Layout.setHorizontalGroup(
                                                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                        );
                                                        jPanel33Layout.setVerticalGroup(
                                                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGap(0, 2, Short.MAX_VALUE)
                                                        );

                                                        PanelCantidad.setBackground(new java.awt.Color(230, 230, 230));

                                                        txtCantidad.setBackground(new java.awt.Color(230, 230, 230));
                                                        txtCantidad.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
                                                        txtCantidad.setForeground(new java.awt.Color(51, 51, 51));
                                                        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtCantidad.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                txtCantidadCaretUpdate(evt);
                                                            }
                                                        });
                                                        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                txtCantidadKeyPressed(evt);
                                                            }
                                                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                                                txtCantidadKeyTyped(evt);
                                                            }
                                                        });

                                                        jLabel4.setBackground(new java.awt.Color(230, 230, 230));
                                                        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel4.setText("Cantidad");

                                                        btnImprimir2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnImprimir2.setForeground(new java.awt.Color(51, 51, 51));
                                                        btnImprimir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Sell Stock-40.png"))); // NOI18N
                                                        btnImprimir2.setText("Agregar");
                                                        btnImprimir2.setContentAreaFilled(false);
                                                        btnImprimir2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnImprimir2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btnImprimir2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnImprimir2.setIconTextGap(30);
                                                        btnImprimir2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                        btnImprimir2.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnImprimir2ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout PanelCantidadLayout = new javax.swing.GroupLayout(PanelCantidad);
                                                        PanelCantidad.setLayout(PanelCantidadLayout);
                                                        PanelCantidadLayout.setHorizontalGroup(
                                                            PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                                .addGap(140, 140, 140)
                                                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(622, Short.MAX_VALUE))
                                                            .addGroup(PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                                    .addContainerGap()
                                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addContainerGap(776, Short.MAX_VALUE)))
                                                            .addGroup(PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                                    .addGap(325, 325, 325)
                                                                    .addComponent(btnImprimir2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addContainerGap(326, Short.MAX_VALUE)))
                                                        );
                                                        PanelCantidadLayout.setVerticalGroup(
                                                            PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                                .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                                                .addContainerGap())
                                                            .addGroup(PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                                                    .addContainerGap()))
                                                            .addGroup(PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                                    .addComponent(btnImprimir2)
                                                                    .addGap(0, 15, Short.MAX_VALUE)))
                                                        );

                                                        javax.swing.GroupLayout panelCargarCPTLayout = new javax.swing.GroupLayout(panelCargarCPT);
                                                        panelCargarCPT.setLayout(panelCargarCPTLayout);
                                                        panelCargarCPTLayout.setHorizontalGroup(
                                                            panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                                                            .addComponent(PanelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                                                .addGroup(panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                                                                    .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(36, 36, 36)
                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap())
                                                        );
                                                        panelCargarCPTLayout.setVerticalGroup(
                                                            panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCargarCPTLayout.createSequentialGroup()
                                                                .addGroup(panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(PanelCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        panelSinCPT.setBackground(new java.awt.Color(255, 255, 255));

                                                        jLabel23.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel23.setText("No se hallaron coincidencias");

                                                        jLabel24.setFont(new java.awt.Font("Segoe UI Light", 0, 100)); // NOI18N
                                                        jLabel24.setForeground(new java.awt.Color(0, 153, 102));
                                                        jLabel24.setText(":(");

                                                        jPanel16.setBackground(new java.awt.Color(0, 153, 102));

                                                        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
                                                        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                                        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Editar propiedad-64 (1).png"))); // NOI18N
                                                        jLabel25.setText("Nuevo CPT");
                                                        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        jLabel25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

                                                        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                                                        jPanel16.setLayout(jPanel16Layout);
                                                        jPanel16Layout.setHorizontalGroup(
                                                            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                                        );
                                                        jPanel16Layout.setVerticalGroup(
                                                            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        javax.swing.GroupLayout panelSinCPTLayout = new javax.swing.GroupLayout(panelSinCPT);
                                                        panelSinCPT.setLayout(panelSinCPTLayout);
                                                        panelSinCPTLayout.setHorizontalGroup(
                                                            panelSinCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelSinCPTLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel24)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel23)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                                                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );
                                                        panelSinCPTLayout.setVerticalGroup(
                                                            panelSinCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelSinCPTLayout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(jLabel24)
                                                                .addContainerGap(39, Short.MAX_VALUE))
                                                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSinCPTLayout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel23)
                                                                .addGap(73, 73, 73))
                                                        );

                                                        javax.swing.GroupLayout CPTLayout = new javax.swing.GroupLayout(CPT.getContentPane());
                                                        CPT.getContentPane().setLayout(CPTLayout);
                                                        CPTLayout.setHorizontalGroup(
                                                            CPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelSinCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelCargarCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        CPTLayout.setVerticalGroup(
                                                            CPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(CPTLayout.createSequentialGroup()
                                                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelCargarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelSinCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        );

                                                        ErrorExistente.setAlwaysOnTop(true);
                                                        ErrorExistente.setMinimumSize(new java.awt.Dimension(660, 240));
                                                        ErrorExistente.setResizable(false);

                                                        jPanel134.setBackground(new java.awt.Color(230, 230, 230));
                                                        jPanel134.setMinimumSize(new java.awt.Dimension(310, 441));

                                                        lblAd1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                        lblAd1.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblAd1.setText("Error");

                                                        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel119.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel119.setText("<html>No se puedo imprimir el comprobante,<br>\nEsto puede deberse a que se agotó el papel o la impresora no está disponible.<br>\nPar reimprimir el comprobante vaya a ventas de hoy \n</html> ");

                                                        jPanel135.setBackground(new java.awt.Color(102, 102, 102));

                                                        btnAlertConsulta7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnAlertConsulta7.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnAlertConsulta7.setText("OK");
                                                        btnAlertConsulta7.setContentAreaFilled(false);
                                                        btnAlertConsulta7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnAlertConsulta7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        btnAlertConsulta7.setIconTextGap(30);
                                                        btnAlertConsulta7.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnAlertConsulta7ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
                                                        jPanel135.setLayout(jPanel135Layout);
                                                        jPanel135Layout.setHorizontalGroup(
                                                            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel135Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())
                                                        );
                                                        jPanel135Layout.setVerticalGroup(
                                                            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel135Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        javax.swing.GroupLayout jPanel134Layout = new javax.swing.GroupLayout(jPanel134);
                                                        jPanel134.setLayout(jPanel134Layout);
                                                        jPanel134Layout.setHorizontalGroup(
                                                            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel134Layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblAd1))
                                                                .addContainerGap(101, Short.MAX_VALUE))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel134Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jPanel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())
                                                        );
                                                        jPanel134Layout.setVerticalGroup(
                                                            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel134Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lblAd1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jPanel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        javax.swing.GroupLayout ErrorExistenteLayout = new javax.swing.GroupLayout(ErrorExistente.getContentPane());
                                                        ErrorExistente.getContentPane().setLayout(ErrorExistenteLayout);
                                                        ErrorExistenteLayout.setHorizontalGroup(
                                                            ErrorExistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        ErrorExistenteLayout.setVerticalGroup(
                                                            ErrorExistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
                                                        );

                                                        Preventas.setAlwaysOnTop(true);
                                                        Preventas.setMinimumSize(new java.awt.Dimension(816, 429));
                                                        Preventas.setResizable(false);

                                                        jPanel14.setBackground(new java.awt.Color(230, 230, 230));
                                                        jPanel14.setMinimumSize(new java.awt.Dimension(310, 441));

                                                        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                                                        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel20.setText("Preventas");

                                                        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
                                                        jLabel35.setText("cantidad");
                                                        jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                jLabel35MouseClicked(evt);
                                                            }
                                                        });

                                                        lblID_DETALLE.setForeground(new java.awt.Color(230, 230, 230));
                                                        lblID_DETALLE.setText("jLabel1");

                                                        lblID_CAB.setForeground(new java.awt.Color(230, 230, 230));
                                                        lblID_CAB.setText("jLabel1");

                                                        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                                                        jPanel14.setLayout(jPanel14Layout);
                                                        jPanel14Layout.setHorizontalGroup(
                                                            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel35)
                                                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                                                                            .addComponent(lblID_DETALLE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(lblID_CAB))
                                                                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        jPanel14Layout.setVerticalGroup(
                                                            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel20)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel35)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(lblID_DETALLE)
                                                                    .addComponent(lblID_CAB))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        jScrollPane28.setBackground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane28.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                        tbFR.setModel(new javax.swing.table.DefaultTableModel(
                                                            new Object [][] {
                                                                {},
                                                                {},
                                                                {},
                                                                {}
                                                            },
                                                            new String [] {

                                                            }
                                                        ));
                                                        tbFR.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tbFR.setRowHeight(25);
                                                        tbFR.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                        tbFR.getTableHeader().setReorderingAllowed(false);
                                                        tbFR.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tbFRMouseClicked(evt);
                                                            }
                                                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                                                tbFRMouseEntered(evt);
                                                            }
                                                        });
                                                        tbFR.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tbFRKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane28.setViewportView(tbFR);

                                                        jScrollPane29.setBackground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane29.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                        tFRDet.setModel(new javax.swing.table.DefaultTableModel(
                                                            new Object [][] {
                                                                {},
                                                                {},
                                                                {},
                                                                {}
                                                            },
                                                            new String [] {

                                                            }
                                                        ));
                                                        tFRDet.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
                                                        tFRDet.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tFRDet.setRowHeight(25);
                                                        tFRDet.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                        tFRDet.getTableHeader().setReorderingAllowed(false);
                                                        tFRDet.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tFRDetMouseClicked(evt);
                                                            }
                                                        });
                                                        tFRDet.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tFRDetKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane29.setViewportView(tFRDet);

                                                        panelEliminarFR.setBackground(new java.awt.Color(255, 91, 70));

                                                        Mensaje9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        Mensaje9.setForeground(new java.awt.Color(255, 255, 255));
                                                        Mensaje9.setText("Eliminar el Registro ?");

                                                        eli9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        eli9.setForeground(new java.awt.Color(240, 240, 240));
                                                        eli9.setText("Si");
                                                        eli9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        eli9.setContentAreaFilled(false);
                                                        eli9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        eli9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        eli9.setIconTextGap(30);
                                                        eli9.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                eli9ActionPerformed(evt);
                                                            }
                                                        });

                                                        noeli7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        noeli7.setForeground(new java.awt.Color(240, 240, 240));
                                                        noeli7.setText("No");
                                                        noeli7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        noeli7.setContentAreaFilled(false);
                                                        noeli7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        noeli7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        noeli7.setIconTextGap(30);
                                                        noeli7.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                noeli7ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panelEliminarFRLayout = new javax.swing.GroupLayout(panelEliminarFR);
                                                        panelEliminarFR.setLayout(panelEliminarFRLayout);
                                                        panelEliminarFRLayout.setHorizontalGroup(
                                                            panelEliminarFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelEliminarFRLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(Mensaje9, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(eli9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(noeli7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        panelEliminarFRLayout.setVerticalGroup(
                                                            panelEliminarFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelEliminarFRLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(panelEliminarFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(eli9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(noeli7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(18, Short.MAX_VALUE))
                                                            .addComponent(Mensaje9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );

                                                        panelOpcionesFR.setBackground(new java.awt.Color(230, 230, 230));

                                                        btnCargarHOS1.setBackground(new java.awt.Color(51, 51, 51));
                                                        btnCargarHOS1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnCargarHOS1.setForeground(new java.awt.Color(51, 51, 51));
                                                        btnCargarHOS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Sell Stock-40.png"))); // NOI18N
                                                        btnCargarHOS1.setText("Cargar para venta");
                                                        btnCargarHOS1.setContentAreaFilled(false);
                                                        btnCargarHOS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnCargarHOS1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnCargarHOS1.setIconTextGap(30);
                                                        btnCargarHOS1.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnCargarHOS1ActionPerformed(evt);
                                                            }
                                                        });

                                                        btnEliminarHOS1.setBackground(new java.awt.Color(51, 51, 51));
                                                        btnEliminarHOS1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnEliminarHOS1.setForeground(new java.awt.Color(51, 51, 51));
                                                        btnEliminarHOS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Trash-40.png"))); // NOI18N
                                                        btnEliminarHOS1.setText("Eliminar Registro");
                                                        btnEliminarHOS1.setContentAreaFilled(false);
                                                        btnEliminarHOS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnEliminarHOS1.setEnabled(false);
                                                        btnEliminarHOS1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnEliminarHOS1.setIconTextGap(30);
                                                        btnEliminarHOS1.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnEliminarHOS1ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panelOpcionesFRLayout = new javax.swing.GroupLayout(panelOpcionesFR);
                                                        panelOpcionesFR.setLayout(panelOpcionesFRLayout);
                                                        panelOpcionesFRLayout.setHorizontalGroup(
                                                            panelOpcionesFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelOpcionesFRLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(btnCargarHOS1)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(btnEliminarHOS1)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        panelOpcionesFRLayout.setVerticalGroup(
                                                            panelOpcionesFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnCargarHOS1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(btnEliminarHOS1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );

                                                        javax.swing.GroupLayout PreventasLayout = new javax.swing.GroupLayout(Preventas.getContentPane());
                                                        Preventas.getContentPane().setLayout(PreventasLayout);
                                                        PreventasLayout.setHorizontalGroup(
                                                            PreventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jScrollPane28)
                                                            .addComponent(jScrollPane29)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PreventasLayout.createSequentialGroup()
                                                                .addComponent(panelOpcionesFR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelEliminarFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );
                                                        PreventasLayout.setVerticalGroup(
                                                            PreventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(PreventasLayout.createSequentialGroup()
                                                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)
                                                                .addGroup(PreventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(panelOpcionesFR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(panelEliminarFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        );

                                                        Afecto.setAlwaysOnTop(true);
                                                        Afecto.setMinimumSize(new java.awt.Dimension(430, 210));
                                                        Afecto.setResizable(false);

                                                        jPanel145.setBackground(new java.awt.Color(230, 230, 230));

                                                        jLabel64.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel64.setText("Error");

                                                        jPanel146.setBackground(new java.awt.Color(23, 160, 134));

                                                        btnAlertConsulta10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        btnAlertConsulta10.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnAlertConsulta10.setText("Entendido");
                                                        btnAlertConsulta10.setContentAreaFilled(false);
                                                        btnAlertConsulta10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnAlertConsulta10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        btnAlertConsulta10.setIconTextGap(30);
                                                        btnAlertConsulta10.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnAlertConsulta10ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel146Layout = new javax.swing.GroupLayout(jPanel146);
                                                        jPanel146.setLayout(jPanel146Layout);
                                                        jPanel146Layout.setHorizontalGroup(
                                                            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnAlertConsulta10, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                                        );
                                                        jPanel146Layout.setVerticalGroup(
                                                            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel146Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(btnAlertConsulta10))
                                                        );

                                                        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel67.setText("<html>Estas vendiendo Ítems de la categoría [gravado].<br>\nNo se pueden adicionar ítem de la categoría [inafecto], a esta venta.</html>\n");

                                                        javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
                                                        jPanel145.setLayout(jPanel145Layout);
                                                        jPanel145Layout.setHorizontalGroup(
                                                            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel145Layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel64)
                                                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel145Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(20, 20, 20))
                                                        );
                                                        jPanel145Layout.setVerticalGroup(
                                                            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel145Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel64)
                                                                .addGap(16, 16, 16)
                                                                .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(18, Short.MAX_VALUE))
                                                        );

                                                        javax.swing.GroupLayout AfectoLayout = new javax.swing.GroupLayout(Afecto.getContentPane());
                                                        Afecto.getContentPane().setLayout(AfectoLayout);
                                                        AfectoLayout.setHorizontalGroup(
                                                            AfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        AfectoLayout.setVerticalGroup(
                                                            AfectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );

                                                        Inafecto.setAlwaysOnTop(true);
                                                        Inafecto.setMinimumSize(new java.awt.Dimension(563, 210));
                                                        Inafecto.setResizable(false);

                                                        jPanel147.setBackground(new java.awt.Color(230, 230, 230));

                                                        jLabel65.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel65.setText("Error");

                                                        jPanel148.setBackground(new java.awt.Color(23, 160, 134));

                                                        btnAlertConsulta11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        btnAlertConsulta11.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnAlertConsulta11.setText("Entendido");
                                                        btnAlertConsulta11.setContentAreaFilled(false);
                                                        btnAlertConsulta11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnAlertConsulta11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        btnAlertConsulta11.setIconTextGap(30);
                                                        btnAlertConsulta11.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnAlertConsulta11ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel148Layout = new javax.swing.GroupLayout(jPanel148);
                                                        jPanel148.setLayout(jPanel148Layout);
                                                        jPanel148Layout.setHorizontalGroup(
                                                            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnAlertConsulta11, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                                        );
                                                        jPanel148Layout.setVerticalGroup(
                                                            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel148Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(btnAlertConsulta11))
                                                        );

                                                        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel68.setText("<html>Estas vendiendo Ítems de la categoría  [inafecto].<br> No se pueden adicionar ítem de la categoría [gravado], a esta venta.</html> ");

                                                        javax.swing.GroupLayout jPanel147Layout = new javax.swing.GroupLayout(jPanel147);
                                                        jPanel147.setLayout(jPanel147Layout);
                                                        jPanel147Layout.setHorizontalGroup(
                                                            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel147Layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addGroup(jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel65)
                                                                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel147Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jPanel148, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(20, 20, 20))
                                                        );
                                                        jPanel147Layout.setVerticalGroup(
                                                            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel147Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel65)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                                                .addGap(28, 28, 28)
                                                                .addComponent(jPanel148, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(18, Short.MAX_VALUE))
                                                        );

                                                        javax.swing.GroupLayout InafectoLayout = new javax.swing.GroupLayout(Inafecto.getContentPane());
                                                        Inafecto.getContentPane().setLayout(InafectoLayout);
                                                        InafectoLayout.setHorizontalGroup(
                                                            InafectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel147, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        InafectoLayout.setVerticalGroup(
                                                            InafectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel147, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );

                                                        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                                                        jPanel1.setBackground(new java.awt.Color(39, 174, 96));
                                                        jPanel1.setPreferredSize(new java.awt.Dimension(284, 684));

                                                        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
                                                        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                                        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                                                        jLabel57.setText("<html>Ventas<span style=\"font-size:'14px'\"><br>Caja Central</br></span></html>");

                                                        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

                                                        txtBusquedas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                        txtBusquedas.setForeground(new java.awt.Color(51, 51, 51));
                                                        txtBusquedas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                        txtBusquedas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtBusquedas.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                txtBusquedasCaretUpdate(evt);
                                                            }
                                                        });
                                                        txtBusquedas.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                                                txtBusquedasKeyTyped(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                                                        jPanel23.setLayout(jPanel23Layout);
                                                        jPanel23Layout.setHorizontalGroup(
                                                            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel23Layout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(txtBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        jPanel23Layout.setVerticalGroup(
                                                            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel23Layout.createSequentialGroup()
                                                                .addGap(0, 0, 0)
                                                                .addComponent(txtBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-27.png"))); // NOI18N
                                                        btnBuscarPaciente.setContentAreaFilled(false);
                                                        btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnBuscarPacienteActionPerformed(evt);
                                                            }
                                                        });

                                                        lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                                        lbldetalle.setText("   ");

                                                        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
                                                        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Documento-32.png"))); // NOI18N
                                                        btnNuevo.setText("Nuevo");
                                                        btnNuevo.setContentAreaFilled(false);
                                                        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnNuevo.setIconTextGap(30);
                                                        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnNuevoActionPerformed(evt);
                                                            }
                                                        });

                                                        btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnImprimir.setForeground(new java.awt.Color(255, 255, 255));
                                                        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Imprimir-32.png"))); // NOI18N
                                                        btnImprimir.setText("Imprimir");
                                                        btnImprimir.setContentAreaFilled(false);
                                                        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnImprimir.setIconTextGap(30);
                                                        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnImprimirActionPerformed(evt);
                                                            }
                                                        });

                                                        btneliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btneliminar.setForeground(new java.awt.Color(255, 255, 255));
                                                        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Basura-32.png"))); // NOI18N
                                                        btneliminar.setText("Anular");
                                                        btneliminar.setContentAreaFilled(false);
                                                        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btneliminar.setIconTextGap(30);
                                                        btneliminar.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btneliminarActionPerformed(evt);
                                                            }
                                                        });

                                                        btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnLista.setForeground(new java.awt.Color(255, 255, 255));
                                                        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Orden de compra-32.png"))); // NOI18N
                                                        btnLista.setText("Ventas");
                                                        btnLista.setContentAreaFilled(false);
                                                        btnLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnLista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnLista.setIconTextGap(30);
                                                        btnLista.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                        btnLista.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnListaActionPerformed(evt);
                                                            }
                                                        });

                                                        lblusu1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                        lblusu1.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblusu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Usuario-40.png"))); // NOI18N
                                                        lblusu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
                                                        lblusu1.setFocusable(false);
                                                        lblusu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                                        lblusu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblusu.setText("RicardoCR");
                                                        lblusu.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                        lblusu.setFocusable(false);
                                                        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        lblusu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

                                                        lblID_SESION.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                                                        lblID_SESION.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblID_SESION.setText("jLabel51");
                                                        lblID_SESION.setVerticalAlignment(javax.swing.SwingConstants.TOP);

                                                        lblID_DOCUEMENTO.setForeground(new java.awt.Color(39, 174, 96));
                                                        lblID_DOCUEMENTO.setText("jLabel1");

                                                        Tipo_Documento.setForeground(new java.awt.Color(39, 174, 96));
                                                        Tipo_Documento.setText("jLabel1");

                                                        lblTIPO_DOC.setForeground(new java.awt.Color(39, 174, 96));
                                                        lblTIPO_DOC.setText("0");

                                                        txtDsctoGlobal.setText("jLa");

                                                        txtOtrosCargos.setText("jLa");

                                                        txtTotalDscto.setText("jLa");

                                                        txtVentaExonerada.setText("jLa");

                                                        txtMtoISC.setText("jLa");

                                                        txtOtrosTributos.setText("jLa");

                                                        lblIGV_B.setText("jLa");

                                                        precioV_B.setText("jLa");

                                                        venta_B.setText("jLa");

                                                        LBL_CPF_ID.setText("jLabel1");

                                                        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                                                        jPanel13.setLayout(jPanel13Layout);
                                                        jPanel13Layout.setHorizontalGroup(
                                                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                                                        .addComponent(txtDsctoGlobal)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(txtOtrosCargos)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(txtTotalDscto))
                                                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                                                        .addComponent(txtVentaExonerada)
                                                                        .addGap(70, 70, 70)
                                                                        .addComponent(txtMtoISC)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(txtOtrosTributos))
                                                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                                                        .addComponent(lblIGV_B)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(precioV_B)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(venta_B)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(LBL_CPF_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                .addContainerGap(34, Short.MAX_VALUE))
                                                        );
                                                        jPanel13Layout.setVerticalGroup(
                                                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtDsctoGlobal)
                                                                    .addComponent(txtOtrosCargos)
                                                                    .addComponent(txtTotalDscto))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtVentaExonerada)
                                                                    .addComponent(txtMtoISC)
                                                                    .addComponent(txtOtrosTributos))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(lblIGV_B)
                                                                    .addComponent(precioV_B)
                                                                    .addComponent(venta_B))
                                                                .addGap(24, 24, 24))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(LBL_CPF_ID)
                                                                .addContainerGap())
                                                        );

                                                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                                        jPanel1.setLayout(jPanel1Layout);
                                                        jPanel1Layout.setHorizontalGroup(
                                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGap(24, 24, 24)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                            .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(lbldetalle)
                                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(lblusu1)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(lblID_SESION, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(lblID_DOCUEMENTO)
                                                                            .addComponent(Tipo_Documento)
                                                                            .addComponent(lblTIPO_DOC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(19, Short.MAX_VALUE))
                                                        );
                                                        jPanel1Layout.setVerticalGroup(
                                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(47, 47, 47)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lbldetalle)
                                                                .addGap(21, 21, 21)
                                                                .addComponent(btnNuevo)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnImprimir)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btneliminar)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnLista)
                                                                .addGap(8, 8, 8)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(lblID_DOCUEMENTO)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(Tipo_Documento)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(lblTIPO_DOC))
                                                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(lblusu1)
                                                                        .addComponent(lblID_SESION))
                                                                    .addComponent(lblusu))
                                                                .addContainerGap())
                                                        );

                                                        Paginas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        Paginas.setForeground(new java.awt.Color(255, 255, 255));
                                                        Paginas.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                                                        Paginas.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                                                        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                                                        jPanel12.setBackground(new java.awt.Color(230, 230, 230));
                                                        jPanel12.setPreferredSize(new java.awt.Dimension(405, 113));

                                                        lblCliente2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
                                                        lblCliente2.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblCliente2.setText("Resultados de la Búsqueda");

                                                        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                                        jPanel12.setLayout(jPanel12Layout);
                                                        jPanel12Layout.setHorizontalGroup(
                                                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lblCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        jPanel12Layout.setVerticalGroup(
                                                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(lblCliente2)
                                                                .addContainerGap(58, Short.MAX_VALUE))
                                                        );

                                                        jScrollPane12.setBackground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane12.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        jScrollPane12.setForeground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane12.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                                                        tb_ReporteDiario1.setForeground(new java.awt.Color(51, 51, 51));
                                                        tb_ReporteDiario1.setModel(new javax.swing.table.DefaultTableModel(
                                                            new Object [][] {
                                                                {},
                                                                {},
                                                                {},
                                                                {}
                                                            },
                                                            new String [] {

                                                            }
                                                        ));
                                                        tb_ReporteDiario1.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tb_ReporteDiario1.setRowHeight(25);
                                                        tb_ReporteDiario1.setSelectionBackground(new java.awt.Color(0, 153, 102));
                                                        tb_ReporteDiario1.getTableHeader().setReorderingAllowed(false);
                                                        tb_ReporteDiario1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tb_ReporteDiario1MouseClicked(evt);
                                                            }
                                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                tb_ReporteDiario1MousePressed(evt);
                                                            }
                                                        });
                                                        tb_ReporteDiario1.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tb_ReporteDiario1KeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane12.setViewportView(tb_ReporteDiario1);

                                                        jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                        tb_Clientes1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        tb_Clientes1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        tb_Clientes1.setModel(new javax.swing.table.DefaultTableModel(
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
                                                        tb_Clientes1.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tb_Clientes1.setRowHeight(25);
                                                        tb_Clientes1.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                        jScrollPane5.setViewportView(tb_Clientes1);

                                                        javax.swing.GroupLayout panelDetalleHistorialLayout = new javax.swing.GroupLayout(panelDetalleHistorial);
                                                        panelDetalleHistorial.setLayout(panelDetalleHistorialLayout);
                                                        panelDetalleHistorialLayout.setHorizontalGroup(
                                                            panelDetalleHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                            .addGroup(panelDetalleHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE))
                                                        );
                                                        panelDetalleHistorialLayout.setVerticalGroup(
                                                            panelDetalleHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGap(0, 215, Short.MAX_VALUE)
                                                            .addGroup(panelDetalleHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                                                        );

                                                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                                        jPanel3.setLayout(jPanel3Layout);
                                                        jPanel3Layout.setHorizontalGroup(
                                                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                                            .addComponent(panelDetalleHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        jPanel3Layout.setVerticalGroup(
                                                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelDetalleHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        Paginas.addTab("tab1", jPanel3);

                                                        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                                        lbl4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        lbl4.setForeground(new java.awt.Color(51, 51, 51));
                                                        lbl4.setText("Cliente");

                                                        lbl1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        lbl1.setForeground(new java.awt.Color(51, 51, 51));
                                                        lbl1.setText("Tipo de Documento      ");

                                                        cbxFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        cbxFormaPago.setForeground(new java.awt.Color(51, 51, 51));
                                                        cbxFormaPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                                                        cbxFormaPago.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseReleased(java.awt.event.MouseEvent evt) {
                                                                cbxFormaPagoMouseReleased(evt);
                                                            }
                                                        });
                                                        cbxFormaPago.addItemListener(new java.awt.event.ItemListener() {
                                                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                                cbxFormaPagoItemStateChanged(evt);
                                                            }
                                                        });
                                                        cbxFormaPago.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                cbxFormaPagoActionPerformed(evt);
                                                            }
                                                        });
                                                        cbxFormaPago.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                cbxFormaPagoKeyPressed(evt);
                                                            }
                                                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                                                cbxFormaPagoKeyTyped(evt);
                                                            }
                                                        });

                                                        lbl5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        lbl5.setForeground(new java.awt.Color(51, 51, 51));
                                                        lbl5.setText("Número de Documento");

                                                        lbl6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        lbl6.setForeground(new java.awt.Color(51, 51, 51));
                                                        lbl6.setText("Item");

                                                        panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                                                        panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                        txtCPT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                        txtCPT.setForeground(new java.awt.Color(51, 51, 51));
                                                        txtCPT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                        txtCPT.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtCPT.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                txtCPTCaretUpdate(evt);
                                                            }
                                                        });

                                                        btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                                                        btnBuscarCPT.setContentAreaFilled(false);
                                                        btnBuscarCPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnBuscarCPTActionPerformed(evt);
                                                            }
                                                        });
                                                        btnBuscarCPT.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                btnBuscarCPTKeyPressed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                                                        panelCPT1.setLayout(panelCPT1Layout);
                                                        panelCPT1Layout.setHorizontalGroup(
                                                            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelCPT1Layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(txtCPT)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(3, 3, 3))
                                                        );
                                                        panelCPT1Layout.setVerticalGroup(
                                                            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelCPT1Layout.createSequentialGroup()
                                                                .addGap(0, 0, 0)
                                                                .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(txtCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                    .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        jScrollPane4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

                                                        tb_CPT.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        tb_CPT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        tb_CPT.setForeground(new java.awt.Color(51, 51, 51));
                                                        tb_CPT.setModel(new javax.swing.table.DefaultTableModel(
                                                            new Object [][] {

                                                            },
                                                            new String [] {
                                                                "ID_PRECIO", "ITEM", "DESCRIPCION", "PRECIO", "CANTIDAD", "SUBTOTAL", "DESCUENTO"
                                                            }
                                                        ));
                                                        tb_CPT.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tb_CPT.setRowHeight(38);
                                                        tb_CPT.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                        tb_CPT.getTableHeader().setReorderingAllowed(false);
                                                        tb_CPT.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tb_CPTMouseClicked(evt);
                                                            }
                                                        });
                                                        tb_CPT.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tb_CPTKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane4.setViewportView(tb_CPT);

                                                        panelEliminacion.setBackground(new java.awt.Color(255, 51, 51));

                                                        btnEliminarDetalle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnEliminarDetalle.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnEliminarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Trash-32B.png"))); // NOI18N
                                                        btnEliminarDetalle.setText("Eliminar Este Registro ?");
                                                        btnEliminarDetalle.setContentAreaFilled(false);
                                                        btnEliminarDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnEliminarDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnEliminarDetalle.setIconTextGap(30);
                                                        btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnEliminarDetalleActionPerformed(evt);
                                                            }
                                                        });

                                                        noeli5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        noeli5.setForeground(new java.awt.Color(240, 240, 240));
                                                        noeli5.setText("No");
                                                        noeli5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        noeli5.setContentAreaFilled(false);
                                                        noeli5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        noeli5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        noeli5.setIconTextGap(30);
                                                        noeli5.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                noeli5ActionPerformed(evt);
                                                            }
                                                        });

                                                        eli5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        eli5.setForeground(new java.awt.Color(240, 240, 240));
                                                        eli5.setText("Si");
                                                        eli5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        eli5.setContentAreaFilled(false);
                                                        eli5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        eli5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        eli5.setIconTextGap(30);
                                                        eli5.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                eli5ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panelEliminacionLayout = new javax.swing.GroupLayout(panelEliminacion);
                                                        panelEliminacion.setLayout(panelEliminacionLayout);
                                                        panelEliminacionLayout.setHorizontalGroup(
                                                            panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelEliminacionLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(btnEliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(eli5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(noeli5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        panelEliminacionLayout.setVerticalGroup(
                                                            panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelEliminacionLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(panelEliminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(btnEliminarDetalle)
                                                                    .addComponent(eli5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(noeli5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(6, 6, 6))
                                                        );

                                                        panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
                                                        panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                        txtCliente.setEditable(false);
                                                        txtCliente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                        txtCliente.setForeground(new java.awt.Color(51, 51, 51));
                                                        txtCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                        txtCliente.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtCliente.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                                                        txtCliente.setSelectionColor(new java.awt.Color(255, 255, 255));
                                                        txtCliente.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                txtClienteCaretUpdate(evt);
                                                            }
                                                        });

                                                        btnBuscarCPT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                                                        btnBuscarCPT1.setContentAreaFilled(false);
                                                        btnBuscarCPT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnBuscarCPT1.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnBuscarCPT1ActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
                                                        panelCPT3.setLayout(panelCPT3Layout);
                                                        panelCPT3Layout.setHorizontalGroup(
                                                            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelCPT3Layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(txtCliente)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(3, 3, 3))
                                                        );
                                                        panelCPT3Layout.setVerticalGroup(
                                                            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelCPT3Layout.createSequentialGroup()
                                                                .addGap(0, 0, 0)
                                                                .addGroup(panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                                                    .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        lblSerie.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblSerie.setText("jLabel1");

                                                        lblCorrelativo.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblCorrelativo.setText("jLabel3");

                                                        lblID_Documento.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblID_Documento.setText("jLabel1");

                                                        lbl2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        lbl2.setForeground(new java.awt.Color(51, 51, 51));
                                                        lbl2.setText("Forma de Pago             ");

                                                        cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        cbxTipoDocumento.setForeground(new java.awt.Color(51, 51, 51));
                                                        cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BOLETA", "FACTURA", "RECIBO" }));
                                                        cbxTipoDocumento.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                cbxTipoDocumentoMouseClicked(evt);
                                                            }
                                                            public void mouseReleased(java.awt.event.MouseEvent evt) {
                                                                cbxTipoDocumentoMouseReleased(evt);
                                                            }
                                                        });
                                                        cbxTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
                                                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                                cbxTipoDocumentoItemStateChanged(evt);
                                                            }
                                                        });
                                                        cbxTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                cbxTipoDocumentoActionPerformed(evt);
                                                            }
                                                        });
                                                        cbxTipoDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                cbxTipoDocumentoKeyPressed(evt);
                                                            }
                                                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                                                cbxTipoDocumentoKeyTyped(evt);
                                                            }
                                                        });

                                                        panelMensaje.setBackground(new java.awt.Color(255, 51, 51));

                                                        Mensaje4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        Mensaje4.setForeground(new java.awt.Color(255, 255, 255));
                                                        Mensaje4.setText("Cancelar la venta ?");

                                                        btnCorrectoSi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        btnCorrectoSi.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnCorrectoSi.setText("Si");
                                                        btnCorrectoSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        btnCorrectoSi.setContentAreaFilled(false);
                                                        btnCorrectoSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnCorrectoSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        btnCorrectoSi.setIconTextGap(30);
                                                        btnCorrectoSi.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnCorrectoSiActionPerformed(evt);
                                                            }
                                                        });
                                                        btnCorrectoSi.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                btnCorrectoSiKeyPressed(evt);
                                                            }
                                                        });

                                                        btnCorrectoNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        btnCorrectoNo.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnCorrectoNo.setText("No");
                                                        btnCorrectoNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        btnCorrectoNo.setContentAreaFilled(false);
                                                        btnCorrectoNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnCorrectoNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        btnCorrectoNo.setIconTextGap(30);
                                                        btnCorrectoNo.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnCorrectoNoActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panelMensajeLayout = new javax.swing.GroupLayout(panelMensaje);
                                                        panelMensaje.setLayout(panelMensajeLayout);
                                                        panelMensajeLayout.setHorizontalGroup(
                                                            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelMensajeLayout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(Mensaje4)
                                                                .addGap(43, 43, 43)
                                                                .addComponent(btnCorrectoSi, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnCorrectoNo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        panelMensajeLayout.setVerticalGroup(
                                                            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMensajeLayout.createSequentialGroup()
                                                                .addContainerGap(14, Short.MAX_VALUE)
                                                                .addGroup(panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(btnCorrectoSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btnCorrectoNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap())
                                                            .addComponent(Mensaje4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );

                                                        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
                                                        jPanel2.setPreferredSize(new java.awt.Dimension(405, 113));

                                                        jPanel10.setBackground(new java.awt.Color(230, 230, 230));

                                                        lblCliente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 22)); // NOI18N
                                                        lblCliente.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblCliente.setText("Cliente");

                                                        lblDocumento1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                        lblDocumento1.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblDocumento1.setText("DOCUMENTO");

                                                        lblDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                        lblDocumento.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblDocumento.setText("DNI");

                                                        lblId.setForeground(new java.awt.Color(230, 230, 230));
                                                        lblId.setText("jLabel1");

                                                        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                                                        jPanel10.setLayout(jPanel10Layout);
                                                        jPanel10Layout.setHorizontalGroup(
                                                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                                                        .addComponent(lblDocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(lblDocumento)
                                                                        .addGap(516, 516, 516))
                                                                    .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                                                                .addComponent(lblId)
                                                                .addGap(80, 80, 80))
                                                        );
                                                        jPanel10Layout.setVerticalGroup(
                                                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(lblId)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                                                        .addGap(7, 7, 7)
                                                                        .addComponent(lblCliente)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(lblDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(lblDocumento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                                .addContainerGap())
                                                        );

                                                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                                        jPanel2.setLayout(jPanel2Layout);
                                                        jPanel2Layout.setHorizontalGroup(
                                                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addContainerGap())
                                                        );
                                                        jPanel2Layout.setVerticalGroup(
                                                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, Short.MAX_VALUE)
                                                                .addGap(43, 43, 43))
                                                        );

                                                        lblSESION.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblSESION.setText("jLabel1");

                                                        lblID_CABECERA.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblID_CABECERA.setText("A");

                                                        panelAnular.setBackground(new java.awt.Color(241, 197, 14));
                                                        panelAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        panelAnular.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                panelAnularMouseClicked(evt);
                                                            }
                                                        });
                                                        panelAnular.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                panelAnularKeyPressed(evt);
                                                            }
                                                        });

                                                        btnTerminiarVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnTerminiarVenta.setForeground(new java.awt.Color(51, 51, 51));
                                                        btnTerminiarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Print-32BANNER.png"))); // NOI18N
                                                        btnTerminiarVenta.setText("ENTER ESCAPE ENTER");
                                                        btnTerminiarVenta.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        btnTerminiarVenta.setContentAreaFilled(false);
                                                        btnTerminiarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnTerminiarVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btnTerminiarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnTerminiarVenta.setIconTextGap(30);
                                                        btnTerminiarVenta.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnTerminiarVentaActionPerformed(evt);
                                                            }
                                                        });

                                                        txtEnterEscapeEnter.setEditable(false);
                                                        txtEnterEscapeEnter.setBackground(new java.awt.Color(241, 197, 14));
                                                        txtEnterEscapeEnter.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
                                                        txtEnterEscapeEnter.setForeground(new java.awt.Color(241, 197, 14));
                                                        txtEnterEscapeEnter.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtEnterEscapeEnter.setCaretColor(new java.awt.Color(102, 102, 102));
                                                        txtEnterEscapeEnter.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                txtEnterEscapeEnterActionPerformed(evt);
                                                            }
                                                        });
                                                        txtEnterEscapeEnter.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                txtEnterEscapeEnterKeyPressed(evt);
                                                            }
                                                        });

                                                        jPanel17.setBackground(new java.awt.Color(255, 51, 51));
                                                        jPanel17.setPreferredSize(new java.awt.Dimension(125, 25));

                                                        btnAnularVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnAnularVenta.setForeground(new java.awt.Color(255, 255, 255));
                                                        btnAnularVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Trash-32B.png"))); // NOI18N
                                                        btnAnularVenta.setText("Cancelar Venta");
                                                        btnAnularVenta.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        btnAnularVenta.setContentAreaFilled(false);
                                                        btnAnularVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnAnularVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btnAnularVenta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnAnularVenta.setIconTextGap(30);
                                                        btnAnularVenta.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnAnularVentaActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                                                        jPanel17.setLayout(jPanel17Layout);
                                                        jPanel17Layout.setHorizontalGroup(
                                                            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(btnAnularVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        jPanel17Layout.setVerticalGroup(
                                                            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnAnularVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                                        );

                                                        javax.swing.GroupLayout panelAnularLayout = new javax.swing.GroupLayout(panelAnular);
                                                        panelAnular.setLayout(panelAnularLayout);
                                                        panelAnularLayout.setHorizontalGroup(
                                                            panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelAnularLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(btnTerminiarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(120, 120, 120)
                                                                .addComponent(txtEnterEscapeEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                                                        );
                                                        panelAnularLayout.setVerticalGroup(
                                                            panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                                            .addComponent(btnTerminiarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(txtEnterEscapeEnter)
                                                        );

                                                        panelEliminar.setBackground(new java.awt.Color(255, 51, 51));

                                                        Mensaje5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        Mensaje5.setForeground(new java.awt.Color(255, 255, 255));
                                                        Mensaje5.setText("Cancelar la venta ?");

                                                        btnEliminarSi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        btnEliminarSi.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnEliminarSi.setText("Si");
                                                        btnEliminarSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        btnEliminarSi.setContentAreaFilled(false);
                                                        btnEliminarSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnEliminarSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        btnEliminarSi.setIconTextGap(30);
                                                        btnEliminarSi.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnEliminarSiActionPerformed(evt);
                                                            }
                                                        });

                                                        btnEliminarNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        btnEliminarNo.setForeground(new java.awt.Color(240, 240, 240));
                                                        btnEliminarNo.setText("No");
                                                        btnEliminarNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        btnEliminarNo.setContentAreaFilled(false);
                                                        btnEliminarNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnEliminarNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        btnEliminarNo.setIconTextGap(30);
                                                        btnEliminarNo.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnEliminarNoActionPerformed(evt);
                                                            }
                                                        });

                                                        txtEnterEscapeEnter1.setEditable(false);
                                                        txtEnterEscapeEnter1.setBackground(new java.awt.Color(255, 51, 51));
                                                        txtEnterEscapeEnter1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
                                                        txtEnterEscapeEnter1.setForeground(new java.awt.Color(255, 91, 70));
                                                        txtEnterEscapeEnter1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtEnterEscapeEnter1.setCaretColor(new java.awt.Color(102, 102, 102));
                                                        txtEnterEscapeEnter1.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                txtEnterEscapeEnter1KeyPressed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panelEliminarLayout = new javax.swing.GroupLayout(panelEliminar);
                                                        panelEliminar.setLayout(panelEliminarLayout);
                                                        panelEliminarLayout.setHorizontalGroup(
                                                            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelEliminarLayout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(Mensaje5)
                                                                .addGap(43, 43, 43)
                                                                .addComponent(btnEliminarSi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnEliminarNo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtEnterEscapeEnter1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(98, 98, 98))
                                                        );
                                                        panelEliminarLayout.setVerticalGroup(
                                                            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(Mensaje5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(txtEnterEscapeEnter1, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarLayout.createSequentialGroup()
                                                                        .addGap(0, 3, Short.MAX_VALUE)
                                                                        .addGroup(panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(btnEliminarSi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnEliminarNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap())
                                                        );

                                                        lblGrupo.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblGrupo.setText("jLabel1");

                                                        panelNumeros.setBackground(new java.awt.Color(214, 217, 223));

                                                        lblMontos.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                                                        lblMontos.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblMontos.setText("0");

                                                        javax.swing.GroupLayout panelNumerosLayout = new javax.swing.GroupLayout(panelNumeros);
                                                        panelNumeros.setLayout(panelNumerosLayout);
                                                        panelNumerosLayout.setHorizontalGroup(
                                                            panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelNumerosLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lblMontos)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        panelNumerosLayout.setVerticalGroup(
                                                            panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNumerosLayout.createSequentialGroup()
                                                                .addContainerGap(13, Short.MAX_VALUE)
                                                                .addComponent(lblMontos)
                                                                .addContainerGap())
                                                        );

                                                        txtSubTotal.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
                                                        txtSubTotal.setForeground(new java.awt.Color(255, 255, 255));
                                                        txtSubTotal.setText("0");

                                                        txtIGV.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
                                                        txtIGV.setForeground(new java.awt.Color(255, 255, 255));
                                                        txtIGV.setText("0");

                                                        txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
                                                        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
                                                        txtTotal.setText("0");

                                                        jButton1.setText("jButton1");
                                                        jButton1.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                jButton1ActionPerformed(evt);
                                                            }
                                                        });

                                                        jButton2.setText("jButton2");
                                                        jButton2.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                jButton2ActionPerformed(evt);
                                                            }
                                                        });

                                                        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
                                                        jPanel30.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                        lblDESCUENTO.setText("jLabel2");

                                                        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                                                        jPanel30.setLayout(jPanel30Layout);
                                                        jPanel30Layout.setHorizontalGroup(
                                                            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel30Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(lblDESCUENTO)
                                                                .addContainerGap(37, Short.MAX_VALUE))
                                                        );
                                                        jPanel30Layout.setVerticalGroup(
                                                            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                                                .addContainerGap(25, Short.MAX_VALUE)
                                                                .addComponent(lblDESCUENTO)
                                                                .addContainerGap())
                                                        );

                                                        lbltipo_DOC_boleta.setForeground(new java.awt.Color(255, 255, 255));
                                                        lbltipo_DOC_boleta.setText(" ");

                                                        lblApeNom.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblApeNom.setText(" ");

                                                        lblFechaEmision.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblFechaEmision.setText(" ");

                                                        lblValorVentaGravada.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblValorVentaGravada.setText("jLabel1");

                                                        lblValorVentaInafectada.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblValorVentaInafectada.setText("jLabel5");

                                                        lblIDCliente.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                lblIDClienteCaretUpdate(evt);
                                                            }
                                                        });

                                                        lblNroCorrelativoC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        lblNroCorrelativoC.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblNroCorrelativoC.setText("000000003");

                                                        txtSerieC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        txtSerieC.setForeground(new java.awt.Color(51, 51, 51));
                                                        txtSerieC.setText("Nº 000");

                                                        txtSerie1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        txtSerie1.setForeground(new java.awt.Color(51, 51, 51));
                                                        txtSerie1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                                        txtSerie1.setText("-");

                                                        lblID_CAB_PREVENTAS.setForeground(new java.awt.Color(255, 255, 255));
                                                        lblID_CAB_PREVENTAS.setText("jLabel1");

                                                        jPanel15.setBackground(new java.awt.Color(25, 188, 157));

                                                        Mensaje6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        Mensaje6.setForeground(new java.awt.Color(255, 255, 255));
                                                        Mensaje6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Alert-32.png"))); // NOI18N
                                                        Mensaje6.setText("Preventas pendiente de pago, cargar?");
                                                        Mensaje6.setIconTextGap(30);
                                                        Mensaje6.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                Mensaje6MouseClicked(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                                                        jPanel15.setLayout(jPanel15Layout);
                                                        jPanel15Layout.setHorizontalGroup(
                                                            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(Mensaje6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );
                                                        jPanel15Layout.setVerticalGroup(
                                                            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(Mensaje6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                                        );

                                                        panleBoleta.setBackground(new java.awt.Color(164, 192, 79));

                                                        Mensaje7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        Mensaje7.setForeground(new java.awt.Color(255, 255, 255));
                                                        Mensaje7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Tree Planting-32.png"))); // NOI18N
                                                        Mensaje7.setText("Boleta Electrónica Generada");
                                                        Mensaje7.setIconTextGap(30);
                                                        Mensaje7.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                Mensaje7MouseClicked(evt);
                                                            }
                                                        });

                                                        ImpS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        ImpS.setForeground(new java.awt.Color(240, 240, 240));
                                                        ImpS.setText("Si");
                                                        ImpS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        ImpS.setContentAreaFilled(false);
                                                        ImpS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        ImpS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        ImpS.setIconTextGap(30);
                                                        ImpS.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                ImpSActionPerformed(evt);
                                                            }
                                                        });

                                                        ImpN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        ImpN.setForeground(new java.awt.Color(240, 240, 240));
                                                        ImpN.setText("No");
                                                        ImpN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                                                        ImpN.setContentAreaFilled(false);
                                                        ImpN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        ImpN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                        ImpN.setIconTextGap(30);
                                                        ImpN.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                ImpNActionPerformed(evt);
                                                            }
                                                        });

                                                        javax.swing.GroupLayout panleBoletaLayout = new javax.swing.GroupLayout(panleBoleta);
                                                        panleBoleta.setLayout(panleBoletaLayout);
                                                        panleBoletaLayout.setHorizontalGroup(
                                                            panleBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panleBoletaLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(Mensaje7, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(ImpS, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ImpN, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(297, Short.MAX_VALUE))
                                                        );
                                                        panleBoletaLayout.setVerticalGroup(
                                                            panleBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panleBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(Mensaje7, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                .addComponent(ImpN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(ImpS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        lblPorcentaje.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        lblPorcentaje.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblPorcentaje.setText("Porcentaje de Descuento");

                                                        panelProcentaje.setBackground(new java.awt.Color(255, 255, 255));
                                                        panelProcentaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                                        txtPorcentaje.setEditable(false);
                                                        txtPorcentaje.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                                        txtPorcentaje.setForeground(new java.awt.Color(51, 51, 51));
                                                        txtPorcentaje.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                                        txtPorcentaje.setText("0");
                                                        txtPorcentaje.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        txtPorcentaje.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                                                        txtPorcentaje.setSelectionColor(new java.awt.Color(255, 255, 255));
                                                        txtPorcentaje.addCaretListener(new javax.swing.event.CaretListener() {
                                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                                txtPorcentajeCaretUpdate(evt);
                                                            }
                                                        });

                                                        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
                                                        jLabel1.setText("%");

                                                        javax.swing.GroupLayout panelProcentajeLayout = new javax.swing.GroupLayout(panelProcentaje);
                                                        panelProcentaje.setLayout(panelProcentajeLayout);
                                                        panelProcentajeLayout.setHorizontalGroup(
                                                            panelProcentajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelProcentajeLayout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(txtPorcentaje)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel1)
                                                                .addGap(8, 8, 8))
                                                        );
                                                        panelProcentajeLayout.setVerticalGroup(
                                                            panelProcentajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelProcentajeLayout.createSequentialGroup()
                                                                .addGap(0, 0, 0)
                                                                .addGroup(panelProcentajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel1))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        );

                                                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                                        jPanel4.setLayout(jPanel4Layout);
                                                        jPanel4Layout.setHorizontalGroup(
                                                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jScrollPane4)
                                                            .addComponent(panelMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                                            .addComponent(panelAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelEliminacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panelNumeros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(lbl6)
                                                                    .addComponent(lbl4)
                                                                    .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(lblPorcentaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(47, 47, 47)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(panelCPT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(cbxFormaPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(cbxTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                                        .addComponent(txtSerieC, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(txtSerie1)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(lblNroCorrelativoC, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addComponent(panelProcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(23, 23, 23)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                                        .addComponent(lblID_Documento)
                                                                        .addGap(36, 36, 36)
                                                                        .addComponent(lblSESION, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(lbltipo_DOC_boleta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(lblApeNom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(lblFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(19, 19, 19))
                                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                .addComponent(lblIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(lblID_CAB_PREVENTAS)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblID_CABECERA)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(lblGrupo))
                                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addComponent(jButton1)
                                                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(lblSerie)
                                                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                                .addGap(8, 8, 8)
                                                                                                .addComponent(lblValorVentaInafectada)))
                                                                                        .addGap(18, 18, 18)
                                                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(lblValorVentaGravada)
                                                                                            .addComponent(lblCorrelativo))))
                                                                                .addGap(21, 21, 21)
                                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(jButton2))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtSubTotal)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtIGV)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtTotal)))
                                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(panleBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        jPanel4Layout.setVerticalGroup(
                                                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panleBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lbl1)
                                                                    .addComponent(lblID_Documento)
                                                                    .addComponent(lblSESION)
                                                                    .addComponent(lbltipo_DOC_boleta)
                                                                    .addComponent(lblApeNom)
                                                                    .addComponent(lblFechaEmision))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(lbl2)
                                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(cbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblID_CABECERA)
                                                                        .addComponent(lblGrupo)
                                                                        .addComponent(lblIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblID_CAB_PREVENTAS)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(panelProcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblPorcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jButton1)
                                                                    .addComponent(jButton2)
                                                                    .addComponent(lbl4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(txtSubTotal)
                                                                            .addComponent(txtIGV)
                                                                            .addComponent(txtTotal))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(txtSerieC)
                                                                                    .addComponent(txtSerie1)
                                                                                    .addComponent(lblNroCorrelativoC))
                                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(lbl5)
                                                                                    .addComponent(lblSerie)
                                                                                    .addComponent(lblCorrelativo)))
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(lbl6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(panelCPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(lblValorVentaGravada)
                                                                            .addComponent(lblValorVentaInafectada))
                                                                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelEliminacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        Paginas.addTab("tab2", jPanel4);

                                                        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                                                        jPanel6.setBackground(new java.awt.Color(230, 230, 230));
                                                        jPanel6.setPreferredSize(new java.awt.Dimension(405, 113));

                                                        lblCliente1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
                                                        lblCliente1.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblCliente1.setText("Ventas de esta sesión");

                                                        btnImprimir1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                                        btnImprimir1.setForeground(new java.awt.Color(51, 51, 51));
                                                        btnImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Print-32.png"))); // NOI18N
                                                        btnImprimir1.setText("Imprimir");
                                                        btnImprimir1.setContentAreaFilled(false);
                                                        btnImprimir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                        btnImprimir1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                        btnImprimir1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                        btnImprimir1.setIconTextGap(30);
                                                        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnImprimir1ActionPerformed(evt);
                                                            }
                                                        });

                                                        lblTR.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                                        lblTR.setForeground(new java.awt.Color(51, 51, 51));
                                                        lblTR.setText("Total Recaudado");

                                                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                                        jPanel6.setLayout(jPanel6Layout);
                                                        jPanel6Layout.setHorizontalGroup(
                                                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                                        .addComponent(lblTR, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                                        .addComponent(lblCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                                                                        .addComponent(btnImprimir1)))
                                                                .addContainerGap())
                                                        );
                                                        jPanel6Layout.setVerticalGroup(
                                                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(lblCliente1)
                                                                    .addComponent(btnImprimir1))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblTR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(13, Short.MAX_VALUE))
                                                        );

                                                        jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane11.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        jScrollPane11.setForeground(new java.awt.Color(255, 255, 255));
                                                        jScrollPane11.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                                                        tb_ReporteDiario.setForeground(new java.awt.Color(255, 255, 255));
                                                        tb_ReporteDiario.setModel(new javax.swing.table.DefaultTableModel(
                                                            new Object [][] {
                                                                {},
                                                                {},
                                                                {},
                                                                {}
                                                            },
                                                            new String [] {

                                                            }
                                                        ));
                                                        tb_ReporteDiario.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tb_ReporteDiario.setRowHeight(25);
                                                        tb_ReporteDiario.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                        tb_ReporteDiario.getTableHeader().setReorderingAllowed(false);
                                                        tb_ReporteDiario.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tb_ReporteDiarioMouseClicked(evt);
                                                            }
                                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                tb_ReporteDiarioMousePressed(evt);
                                                            }
                                                        });
                                                        tb_ReporteDiario.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tb_ReporteDiarioKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane11.setViewportView(tb_ReporteDiario);

                                                        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                                        tb_Clientes.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                                        tb_Clientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                                        tb_Clientes.setModel(new javax.swing.table.DefaultTableModel(
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
                                                        tb_Clientes.setGridColor(new java.awt.Color(255, 255, 255));
                                                        tb_Clientes.setRowHeight(25);
                                                        tb_Clientes.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                                        tb_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
                                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                tb_ClientesMouseClicked(evt);
                                                            }
                                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                                tb_ClientesMousePressed(evt);
                                                            }
                                                        });
                                                        tb_Clientes.addKeyListener(new java.awt.event.KeyAdapter() {
                                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                tb_ClientesKeyPressed(evt);
                                                            }
                                                        });
                                                        jScrollPane3.setViewportView(tb_Clientes);

                                                        javax.swing.GroupLayout panelDetalleLayout = new javax.swing.GroupLayout(panelDetalle);
                                                        panelDetalle.setLayout(panelDetalleLayout);
                                                        panelDetalleLayout.setHorizontalGroup(
                                                            panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jScrollPane3)
                                                        );
                                                        panelDetalleLayout.setVerticalGroup(
                                                            panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalleLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                                        jPanel5.setLayout(jPanel5Layout);
                                                        jPanel5Layout.setHorizontalGroup(
                                                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                                            .addComponent(panelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        );
                                                        jPanel5Layout.setVerticalGroup(
                                                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(panelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        );

                                                        Paginas.addTab("tab3", jPanel5);

                                                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                                        getContentPane().setLayout(layout);
                                                        layout.setHorizontalGroup(
                                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(Paginas))
                                                        );
                                                        layout.setVerticalGroup(
                                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                                                            .addComponent(Paginas, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        );

                                                        pack();
                                                    }// </editor-fold>//GEN-END:initComponents

    private void txtBusquedasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedasCaretUpdate
        if(txtBusquedas.getText().length()>0){
            jScrollPane12.setVisible(true);
            tb_ReporteDiario1.setVisible(true);
            Paginas.setSelectedIndex(0); 
            panelDetalleHistorial.setVisible(false);
            Caja_NuevaVenta CNVRCCB = new  Caja_NuevaVenta();
            CNVRCCB.ReporteDiariocajaCabeceraBUSQUEDA(txtBusquedas.getText(),tb_ReporteDiario1);
            if(tb_ReporteDiario1.getRowCount()==0){
                lblCliente2.setText("Sin Resultados"); 
                jScrollPane12.setVisible(false);
                panelDetalleHistorial.setVisible(false);
            }else if(tb_ReporteDiario1.getRowCount()>0){
                lblTIPO_DOC.setText("C");
                lblCliente2.setText(txtBusquedas.getText().toUpperCase()); 
            }
        } 
        if(txtBusquedas.getText().length()==0){
            jScrollPane12.setVisible(false);
            panelDetalleHistorial.setVisible(false);
            lblCliente2.setText("<html>"+"Búsqueda"+"<span style=\"font-size:'14px'\"><br>"+"Puede Buscar por DNI, RUC, Apellidos, Razón Social o Fecha"+"<html>");
        }
        
    }//GEN-LAST:event_txtBusquedasCaretUpdate

    private void txtBusquedasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedasKeyTyped

    }//GEN-LAST:event_txtBusquedasKeyTyped

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        if(lblID_CABECERA.getText().equals("A")){
            panelAnular.setVisible(false);
            ////////////////////////////////////////////////////////////////////////
            Paginas.setSelectedIndex(1);
            Caja_NuevaVenta CSC = new Caja_NuevaVenta();
//            CSC.Caja_Correlativo(lblusu.getText());
            Caja_NuevaVenta cno2 = new Caja_NuevaVenta();
            cno2.DATOS_FP(cbxFormaPago.getSelectedItem().toString());
            cbxTipoDocumento.setVisible(true);    

            cbxTipoDocumento.requestFocus(true);
            lblTIPO_DOC.setText("N");
            btnTerminiarVenta.setEnabled(true);
            txtPorcentaje.setText("0");
            lblDESCUENTO.setText("0");
        }
        if(!lblID_CABECERA.getText().equals("A")&&tb_CPT.getRowCount()==0){
            btnImprimir.setEnabled(true);
            panelEliminar.setVisible(true);   
            panelMensaje.setVisible(false);  
            txtEnterEscapeEnter1.requestFocus(); 
            Paginas.setSelectedIndex(1);
        }
        if(!lblID_CABECERA.getText().equals("A")&&tb_CPT.getRowCount()>0){
            btnImprimir.setEnabled(true);
            panelAnular.setVisible(true);
            panelMensaje.setVisible(false);  
            txtEnterEscapeEnter.requestFocus(); 
            Paginas.setSelectedIndex(1);
            SalirFormulario();
        }
        
////////////////////////////////////////////////////////////////////////
        
        
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if(lblTIPO_DOC.getText().equals("N")){
//           if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
//                    btnImprimir.setEnabled(true);
//                    panelAnular.setVisible(true);
//                    panelMensaje.setVisible(false);  
//                    txtEnterEscapeEnter.requestFocus();
//            }else  if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("FACTURA")){
//                        Facturador frm_F = new Facturador();
//                        frm_F.setVisible(true);
//                        Facturador.TXT_ID_CLIENTE_F.setText(lblIDCliente.getText());
//                        CARGAR_TB_FACTURADOR();
//            } 
            SalirFormulario();
        }else if(lblTIPO_DOC.getText().equals("C")){
            if(Tipo_Documento.getText().equals("B")){
                nuevaV.reporteVenta(Integer.parseInt(lblID_DOCUEMENTO.getText()));
            }else if(Tipo_Documento.getText().equals("N")){
                nuevaV.RECIBO(Integer.parseInt(lblID_DOCUEMENTO.getText()));
            }else if(Tipo_Documento.getText().equals("F")){
                Caja_NuevaVenta ER=new Caja_NuevaVenta();
                ER.IMPRIMIR_FACTURA_CAJA(lblID_DOCUEMENTO.getText());

                System.out.println("CODIGILLO "+LBL_CPF_ID.getText());
                CuentasPorPagarSfsRpta rpta2 = new CuentasPorPagarSfsRpta();
                rpta2.reporteFactura(LBL_CPF_ID.getText());
                System.out.println("VAMO IMPRIMIENDO LA FACTURA");
            }
        }  
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        ComunicacionBaja cb = new ComunicacionBaja();
        cb.setVisible(true);
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        Caja_NuevaVenta CNVRCC = new  Caja_NuevaVenta();
        Caja_NuevaVenta CNVRCCTR = new  Caja_NuevaVenta();
        panelDetalle.setVisible(false);
        CNVRCC.ReporteDiariocajaCabecera(lblusu.getText(),Integer.parseInt(lblSESION.getText()),tb_ReporteDiario);
        CNVRCCTR.CAJA_CIERRE_TOTAL(lblusu.getText(),Integer.parseInt(lblSESION.getText()));
        tb_ReporteDiario.setDefaultRenderer(Object.class,new FormatoTablaReporteDiarioCaja());
        lblTIPO_DOC.setText("C");
        if(tb_ReporteDiario.getRowCount()==0){
            btnImprimir1.setEnabled(false);
        }else if(tb_ReporteDiario.getRowCount()>0){
            btnImprimir1.setEnabled(true);
        }
        Paginas.setSelectedIndex(2);
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnCorrectoSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrectoSiActionPerformed

    }//GEN-LAST:event_btnCorrectoSiActionPerformed

    private void btnCorrectoSiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCorrectoSiKeyPressed

    }//GEN-LAST:event_btnCorrectoSiKeyPressed

    private void btnCorrectoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrectoNoActionPerformed
        panelMensaje.setVisible(false);
    }//GEN-LAST:event_btnCorrectoNoActionPerformed

    private void cbxFormaPagoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxFormaPagoMouseReleased

    }//GEN-LAST:event_cbxFormaPagoMouseReleased

    private void cbxFormaPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFormaPagoItemStateChanged
        Caja_NuevaVenta cno2 = new Caja_NuevaVenta();
        cno2.DATOS_FP(cbxFormaPago.getSelectedItem().toString());
        panelMensaje.setVisible(false);

        if(cbxFormaPago.getSelectedItem().equals("EXONERACION")){
            panelProcentaje.setVisible(true);
            lblPorcentaje.setVisible(true);
            txtPorcentaje.setText("");
            txtPorcentaje.setEditable(true);
            txtPorcentaje.requestFocus();
        }else if(cbxFormaPago.getSelectedItem().equals("DONACIONES")){
            panelProcentaje.setVisible(true);
            lblPorcentaje.setVisible(true);
            txtPorcentaje.setEditable(false);
            txtPorcentaje.setText("100");
        }else if(!cbxFormaPago.getSelectedItem().equals("DONACIONES")||cbxFormaPago.getSelectedItem().equals("EXONERACION")){
                    panelProcentaje.setVisible(false);
                    lblPorcentaje.setVisible(false);
                    txtPorcentaje.setText("0");
        }
    }//GEN-LAST:event_cbxFormaPagoItemStateChanged

    private void cbxFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFormaPagoActionPerformed
        panelCPT3.setVisible(true);
        lbl4.setVisible(true);
    }//GEN-LAST:event_cbxFormaPagoActionPerformed

    private void cbxFormaPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxFormaPagoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            panelCPT3.setVisible(true);
            lbl4.setVisible(true);
            btnBuscarCPT1.doClick();
        }  
    }//GEN-LAST:event_cbxFormaPagoKeyPressed

    private void cbxFormaPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxFormaPagoKeyTyped

    }//GEN-LAST:event_cbxFormaPagoKeyTyped

    private void txtCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPTCaretUpdate

    }//GEN-LAST:event_txtCPTCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
        
        txtBuscarCPT.requestFocus();
        CPT.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    private void tb_CPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTMouseClicked
        panelEliminacion.setVisible(true);
    }//GEN-LAST:event_tb_CPTMouseClicked

    private void tb_CPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CPTKeyPressed

    private void btnEliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalleActionPerformed

    }//GEN-LAST:event_btnEliminarDetalleActionPerformed

    private void noeli5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli5ActionPerformed
        panelEliminacion.setVisible(false);
    }//GEN-LAST:event_noeli5ActionPerformed

    private void eli5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli5ActionPerformed
        int filaselec=tb_CPT.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel)tb_CPT.getModel(); 
        modelo.removeRow(filaselec);
        System.out.println("Detalle Eliminado");
        panelEliminacion.setVisible(false);
    }//GEN-LAST:event_eli5ActionPerformed

    private void txtBuscarClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarClienteCaretUpdate
        panelBuscarHC.setVisible(false);
        paneltablaHC.setVisible(true);
        panelSinHC.setVisible(false);
        Caja_NuevaVenta CBC = new Caja_NuevaVenta();
        CBC.VENTA_LISTA_CLIENTES(txtBuscarCliente.getText(),tbClientes);
        if (tbClientes.getRowCount() == 0){
            panelBuscarHC.setVisible(false);
            paneltablaHC.setVisible(false);
            panelSinHC.setVisible(true);
        }
        if (txtBuscarCliente.getText().length()==0){
            panelBuscarHC.setVisible(true);
            paneltablaHC.setVisible(false);
            panelSinHC.setVisible(false);
        } 
    }//GEN-LAST:event_txtBuscarClienteCaretUpdate

    private void txtBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarClienteMouseClicked

    }//GEN-LAST:event_txtBuscarClienteMouseClicked

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    private void txtBuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tbClientes.getSelectionModel().setSelectionInterval (0,0) ;
          tbClientes.requestFocus();
       
          } 
    }//GEN-LAST:event_txtBuscarClienteKeyPressed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void ABONOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ABONOSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ABONOSMouseClicked

    private void ABONOSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ABONOSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ABONOSKeyPressed

    private void tbpreventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpreventasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasMouseClicked

    private void tbpreventasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpreventasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasKeyPressed

    private void tbpreventasFRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpreventasFRMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasFRMouseClicked

    private void tbpreventasFRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpreventasFRKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpreventasFRKeyPressed

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked

        if(evt.getClickCount()==2){
            CARGAR_PREVENTA(); 
            CARGAR();
            CLIENTES.dispose();
            NUEVO_REGISTRO(ConexionS);   
            MostrarPreventa();
            
        }
    }//GEN-LAST:event_tbClientesMouseClicked

    private void tbClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbClientesMouseEntered

    private void tbClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbClientesKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            CARGAR_PREVENTA();
            CARGAR();
            CLIENTES.dispose();
            NUEVO_REGISTRO(ConexionS);
            MostrarPreventa();
            
        }
    }//GEN-LAST:event_tbClientesKeyPressed

    private void txtClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtClienteCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteCaretUpdate

    private void btnBuscarCPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT1ActionPerformed
        CLIENTES.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPT1ActionPerformed

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        if(Principal_Caja.lblTipo_Sede.getText().equals("P")){
            Caja_Clientes CT = new Caja_Clientes();
            CT.setVisible(true);
            String u=Principal.lblUsu.getText();
            CT.lblusu.setText(u);
            CT.btnCaja.doClick();
            CLIENTES.dispose();
        }else if(!Principal_Caja.lblTipo_Sede.getText().equals("P")){
            Caja_Historia_Clinica CH = new Caja_Historia_Clinica();
            CH.setVisible(true);
            String u=Principal.lblUsu.getText();
            CH.lblusu.setText(u);
            CH.btnCaja.doClick();
            CLIENTES.dispose();
        }
        

    }//GEN-LAST:event_jLabel18MouseClicked

    private void cbxTipoDocumentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoMouseReleased

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
       
        panelMensaje.setVisible(false);
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
        lbl2.setVisible(true);
        cbxFormaPago.setVisible(true);
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void cbxTipoDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyPressed

        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            lbl2.setVisible(true); 
            cbxFormaPago.setVisible(true);    
            cbxFormaPago.showPopup();
            cbxFormaPago.requestFocus(true);
            panelMensaje.setVisible(false);
        }       
    }//GEN-LAST:event_cbxTipoDocumentoKeyPressed

    private void cbxTipoDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoKeyTyped

    private void txtBuscarCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCPTCaretUpdate
        panelCargarCPT.setVisible(true);
        panelBuscar.setVisible(false);
        panelSinCPT.setVisible(false);
        Caja_NuevaVenta CNVCPT = new Caja_NuevaVenta();
        CNVCPT.VENTA_LISTA_CPT(txtBuscarCPT.getText(),cbxFormaPago.getSelectedItem().toString(),tb_CPTBUSCAR);

        if (tb_CPTBUSCAR.getRowCount() == 0){
            panelSinCPT.setVisible(true);
            panelCargarCPT.setVisible(false);
            panelBuscar.setVisible(false);
        }
        if (txtBuscarCPT.getText().length()==0){
            panelBuscar.setVisible(true);
            panelCargarCPT.setVisible(false);
            panelSinCPT.setVisible(false);
        }
        //
    }//GEN-LAST:event_txtBuscarCPTCaretUpdate

    private void txtBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCPTActionPerformed

    private void txtBuscarCPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCPTKeyPressed
        char teclaPresionada = evt.getKeyChar();

        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_CPTBUSCAR.getSelectionModel().setSelectionInterval (0,0) ;
            tb_CPTBUSCAR.requestFocus();

        }
    }//GEN-LAST:event_txtBuscarCPTKeyPressed

    private void btnBuscarPaciente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente3ActionPerformed

    private void tb_CPTBUSCARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARMouseClicked
        if(evt.getClickCount()==1){
            int fila=tb_CPTBUSCAR.getSelectedRow();
            lblTIPO.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(fila, 4)));  
        }
        if(evt.getClickCount()==2){
            PanelCantidad.setVisible(true);
            txtCantidad.setText("1");
            txtCantidad.requestFocus();
        }
    }//GEN-LAST:event_tb_CPTBUSCARMouseClicked

    private void tb_CPTBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARKeyPressed
//        char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){
//            PanelCantidad.setVisible(true);
//            txtCantidad.setText("1");
//            txtCantidad.requestFocus();
//        }   
    }//GEN-LAST:event_tb_CPTBUSCARKeyPressed

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            if(tb_CPT.getRowCount()==0){
            CARGAR_CPT();
            SUMA();
        }else
//        lblValorVentaGravada
        if(tb_CPT.getRowCount()>0){
            if(!lblValorVentaInafectada.getText().equals("0.00")&&lblTIPO.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                Inafecto.setVisible(true);
            }else if(!lblValorVentaInafectada.getText().equals("0.00")&&lblTIPO.getText().equals("30 INAFECTO-OPERACIÓN ONEROSA")){
                CARGAR_CPT();
                SUMA();
            }else
                
            if(!lblValorVentaGravada.getText().equals("0.00")&&lblTIPO.getText().equals("30 INAFECTO-OPERACIÓN ONEROSA")){
                Afecto.setVisible(true);
            }else if(!lblValorVentaGravada.getText().equals("0.00")&&lblTIPO.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                CARGAR_CPT();
                SUMA();
            }
        }   
        }
        
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void btnBuscarCPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarCPTKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            CPT.setVisible(true);
        }
    }//GEN-LAST:event_btnBuscarCPTKeyPressed

    private void tb_ReporteDiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioMouseClicked

        int fila=tb_ReporteDiario.getSelectedRow();
        if(evt.getClickCount()==1){
            panelDetalle.setVisible(false);
            lblID_DOCUEMENTO.setText(String.valueOf(tb_ReporteDiario.getValueAt(fila, 7)));
            Tipo_Documento.setText(String.valueOf(tb_ReporteDiario.getValueAt(fila, 8)));
            btnImprimir.setEnabled(true);
//            panelIMprimir.setVisible(false);
            btneliminar.setEnabled(true);
        }
        if(evt.getClickCount()==2){
            Caja_NuevaVenta CNVRCD = new  Caja_NuevaVenta();
            CNVRCD.ReporteDiariocajaDETALLE(Integer.parseInt(lblID_DOCUEMENTO.getText()),tb_Clientes);
            panelDetalle.setVisible(true);
        }
    }//GEN-LAST:event_tb_ReporteDiarioMouseClicked

    private void tb_ReporteDiarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiarioMousePressed

    private void tb_ReporteDiarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiarioKeyPressed

    private void btnEliminarSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSiActionPerformed
        try{
            Caja_NuevaVenta ELIM = new Caja_NuevaVenta();
            ELIM.setID_DOCUMENTO(Integer.parseInt(lblID_CABECERA.getText()));
            if(ELIM.ELIMINAR_CABECERA()){
                Nuevo(false);
                DetalleVenta(true);
                Limpiar();
                jPanel15.setVisible(false);
                panelAnular.setVisible(false);
                System.out.println("ELIMINADO CABECERA");
                panelEliminar.setVisible(false);
                cbxTipoDocumento.setEnabled(true);
            }
        }catch(Exception e){
            System.out.println("Error Eliminar" + e.toString());
        }

    }//GEN-LAST:event_btnEliminarSiActionPerformed

    private void btnEliminarNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNoActionPerformed
        panelEliminar.setVisible(false);
    }//GEN-LAST:event_btnEliminarNoActionPerformed

    private void txtEnterEscapeEnter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterEscapeEnter1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            btnEliminarSi.doClick();
        }
    }//GEN-LAST:event_txtEnterEscapeEnter1KeyPressed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();            
        }    
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnAlertConsulta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta7ActionPerformed
        ErrorExistente.dispose();
    }//GEN-LAST:event_btnAlertConsulta7ActionPerformed

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        nuevaR.ReporteDiario(lblusu.getText(),Integer.parseInt(lblSESION.getText()));
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void panelAnularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelAnularKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnularKeyPressed

    private void panelAnularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAnularMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnularMouseClicked

    private void txtEnterEscapeEnterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterEscapeEnterKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            btnTerminiarVenta.doClick();
        }
    }//GEN-LAST:event_txtEnterEscapeEnterKeyPressed

    private void txtEnterEscapeEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnterEscapeEnterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnterEscapeEnterActionPerformed

    private void btnTerminiarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminiarVentaActionPerformed
        btnTerminiarVenta.setEnabled(false);
        if(cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
            NUEVO_REGISTRO_DETALLE();
            ACTUALIZAR_CABECERA();
            GENERAR_BOLETA_ELECTRONICA(conexion);
            if(!lblID_CAB_PREVENTAS.getText().equals("jLabel1")){
                ACTUALIZAR_PREVENTA();
                ACTUALIZAR_PREVENTA_CAB();
            }
            
            nuevaV.reporteVenta(Integer.parseInt(lblID_CABECERA.getText()));
            panelAnular.setVisible(false);
            panelEliminacion.setVisible(false);
            Nuevo(false);
            DetalleVenta(true);
            Limpiar();
            cbxTipoDocumento.setEnabled(true);
            cbxTipoDocumento.requestFocus();
            
        }if(cbxTipoDocumento.getSelectedItem().equals("RECIBO")){
            NUEVO_REGISTRO_DETALLE();
            ACTUALIZAR_CABECERA();
//            GENERAR_BOLETA_ELECTRONICA();

            nuevaV.RECIBO(Integer.parseInt(lblID_CABECERA.getText()));
            panelAnular.setVisible(false);
            panelEliminacion.setVisible(false);
            Nuevo(false);
            DetalleVenta(true);
            Limpiar();
            cbxTipoDocumento.setEnabled(true);
            cbxTipoDocumento.requestFocus();
            
        }else if(cbxTipoDocumento.getSelectedItem().equals("FACTURA")){
            Facturador frm_F = new Facturador();
            frm_F.setVisible(true);
            Facturador.TXT_ID_CLIENTE_F.setText(lblIDCliente.getText());
            Facturador.lbl_id_cabecera_factura.setText(lblID_CABECERA.getText());
            Facturador.txtSerieF.setText(txtSerieC.getText());
            Facturador.lblNroCorrelativo.setText(lblNroCorrelativoC.getText());
            CARGAR_TB_FACTURADOR();
        }
    }//GEN-LAST:event_btnTerminiarVentaActionPerformed

    private void tb_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMouseClicked

    }//GEN-LAST:event_tb_ClientesMouseClicked

    private void tb_ClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMousePressed

    }//GEN-LAST:event_tb_ClientesMousePressed

    private void tb_ClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ClientesKeyPressed

    }//GEN-LAST:event_tb_ClientesKeyPressed

    private void tb_ReporteDiario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiario1MouseClicked
        int fila=tb_ReporteDiario1.getSelectedRow();
        if(evt.getClickCount()==1){
            panelDetalleHistorial.setVisible(false);
            lblID_DOCUEMENTO.setText(String.valueOf(tb_ReporteDiario1.getValueAt(fila, 7)));
            Tipo_Documento.setText(String.valueOf(tb_ReporteDiario1.getValueAt(fila, 8)));
            btnImprimir.setEnabled(true);
//            panelIMprimir.setVisible(false);
            btneliminar.setEnabled(true);
            
        }
        if(evt.getClickCount()==2){
            Caja_NuevaVenta CNVRCDB = new  Caja_NuevaVenta();
            CNVRCDB.ReporteDiariocajaDETALLE(Integer.parseInt(lblID_DOCUEMENTO.getText()),tb_Clientes1);
            panelDetalleHistorial.setVisible(true);
        }
    }//GEN-LAST:event_tb_ReporteDiario1MouseClicked

    private void tb_ReporteDiario1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiario1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiario1MousePressed

    private void tb_ReporteDiario1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ReporteDiario1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiario1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//            CARGAR_PREVENTA(); 
//            CARGAR();
            NUEVO_REGISTRO(ConexionS);   
            MostrarPreventa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            NUEVO_REGISTRO_DETALLE();
            ACTUALIZAR_CABECERA();
            if(!lblID_CAB_PREVENTAS.getText().equals("jLabel1")){
                ACTUALIZAR_PREVENTA();
                ACTUALIZAR_PREVENTA_CAB();
            }
//            nuevaV.reporteVenta(Integer.parseInt(lblID_CABECERA.getText()));
            panelAnular.setVisible(false);
            panelEliminacion.setVisible(false);
            Nuevo(false);
            DetalleVenta(true);
            Limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lblIDClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblIDClienteCaretUpdate
        MOSTRAR_DATOS_CLIENTE_BOLETA(lblIDCliente.getText());
    }//GEN-LAST:event_lblIDClienteCaretUpdate

    private void btnImprimir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir2ActionPerformed
        if(tb_CPT.getRowCount()==0){
            CARGAR_CPT();
            SUMA();
        }else
//        lblValorVentaGravada
        if(tb_CPT.getRowCount()>0){
            if(!lblValorVentaInafectada.getText().equals("0.00")&&lblTIPO.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                Inafecto.setVisible(true);
            }else if(!lblValorVentaInafectada.getText().equals("0.00")&&lblTIPO.getText().equals("30 INAFECTO-OPERACIÓN ONEROSA")){
                CARGAR_CPT();
                SUMA();
            }else
                
            if(!lblValorVentaGravada.getText().equals("0.00")&&lblTIPO.getText().equals("30 INAFECTO-OPERACIÓN ONEROSA")){
                Afecto.setVisible(true);
            }else if(!lblValorVentaGravada.getText().equals("0.00")&&lblTIPO.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                CARGAR_CPT();
                SUMA();
            }
        }   
    }//GEN-LAST:event_btnImprimir2ActionPerformed

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
      
    }//GEN-LAST:event_jLabel35MouseClicked

    private void tbFRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFRMouseClicked
        int fila=tbFR.getSelectedRow();
        if(evt.getClickCount()==1){
            Caja_NuevaVenta CPFR = new Caja_NuevaVenta();
//            Caja_Documento_Detalle FPFR = new Caja_Documento_Detalle();
            lblID_CAB_PREVENTAS.setText(String.valueOf(tbFR.getValueAt(fila, 4)));
            System.out.println("CODIGO PARA ACTUALIZAR    "+String.valueOf(tbFR.getValueAt(fila, 4)));
//            lblCabecera.setText(String.valueOf(tbFR.getValueAt(fila, 4)));
            ////////////////////////////////////////////////CAMBIO FORMA DE PAGO
//            txtFormaPago.setText(String.valueOf(tbFR.getValueAt(fila, 0)));
//            FPFR.CONSULTAR_COD_FP_FR(txtFormaPago.getText());
            ////////////////////////////////////////////////////////////////////
            CPFR.CAJA_PREVENTAS_FR_DETALLE(String.valueOf(tbFR.getValueAt(fila, 4)),tFRDet);
        }
    }//GEN-LAST:event_tbFRMouseClicked

    private void tbFRMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFRMouseEntered
        try {
            int fila=tbFR.getSelectedRow();
            Caja_NuevaVenta CPFR = new Caja_NuevaVenta();
            CPFR.CAJA_PREVENTAS_FR_DETALLE(String.valueOf(tbFR.getValueAt(fila, 4)),tFRDet);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbFRMouseEntered

    private void tbFRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFRKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFRKeyPressed

    private void tFRDetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFRDetMouseClicked
        int fila=tFRDet.getSelectedRow();
        if(evt.getClickCount()==1){
            lblID_DETALLE.setText(String.valueOf(tFRDet.getValueAt(fila, 2)));
            System.out.println("VAMO ELIMINANDO       "+String.valueOf(tFRDet.getValueAt(fila, 2)));
            lblID_CAB.setText(String.valueOf(tFRDet.getValueAt(fila, 0)));
            btnEliminarHOS1.setEnabled(true);
        }
    }//GEN-LAST:event_tFRDetMouseClicked

    private void tFRDetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFRDetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tFRDetKeyPressed

    private void eli9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli9ActionPerformed
        ANULAR_PREVENTA();
    }//GEN-LAST:event_eli9ActionPerformed

    private void noeli7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeli7ActionPerformed
        panelEliminarFR.setVisible(false);
    }//GEN-LAST:event_noeli7ActionPerformed

    private void btnCargarHOS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarHOS1ActionPerformed
        if(!lblID_CAB_PREVENTAS.getText().equals("lblID_CAB_PREVENTAS")){
            lblGrupo.setText("10 GRAVADO-OPERACIÓN ONEROSA");
            CARGAR_TB_FR();
            SUMA();
            jPanel15.setVisible(false);
        }
    }//GEN-LAST:event_btnCargarHOS1ActionPerformed

    private void btnEliminarHOS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHOS1ActionPerformed
        panelEliminarFR.setVisible(true);
    }//GEN-LAST:event_btnEliminarHOS1ActionPerformed

    private void Mensaje6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mensaje6MouseClicked
        Preventas.setVisible(true);
    }//GEN-LAST:event_Mensaje6MouseClicked

    private void btnAnularVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularVentaActionPerformed
        panelEliminar.setVisible(true);
    }//GEN-LAST:event_btnAnularVentaActionPerformed

    private void cbxTipoDocumentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoMouseClicked
        panelMensaje.setVisible(false);
    }//GEN-LAST:event_cbxTipoDocumentoMouseClicked

    private void Mensaje7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mensaje7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Mensaje7MouseClicked

    private void ImpSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImpSActionPerformed
        CuentasPorPagarFacturasCabecera cab3 = new CuentasPorPagarFacturasCabecera();
        CuentasPorPagarSfsRpta rpta2 = new CuentasPorPagarSfsRpta();
        rpta2.reporteFactura(cab3.idFactura(Principal.lblUsu.getText()));
        panleBoleta.setVisible(false);
    }//GEN-LAST:event_ImpSActionPerformed

    private void ImpNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImpNActionPerformed
        panleBoleta.setVisible(false);
    }//GEN-LAST:event_ImpNActionPerformed

    private void btnAlertConsulta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta10ActionPerformed
        Afecto.dispose();
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

    private void btnAlertConsulta11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta11ActionPerformed
        Inafecto.dispose();
    }//GEN-LAST:event_btnAlertConsulta11ActionPerformed

    private void txtPorcentajeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPorcentajeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeCaretUpdate

    public void GENERAR_BOLETA_ELECTRONICA(java.sql.Connection con){
        boolean rpta = false;
        CuentasPorPagarFacturasCabecera cabecera = new CuentasPorPagarFacturasCabecera();
        if(!txtCliente.getText().equals("")){
//                    Caja_Ventas.jButton2.doClick();
//                    Caja_Ventas.btnNuevo.doClick();
            try {
            // instanciamos el objeto callable
            CallableStatement cstmt = con.prepareCall("exec CUENTAS_POR_PAGAR_FACTURAS_CABECERA_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            // seteamos los parametros de entrada
            cstmt.setString(1,lblIDCliente.getText());
            cstmt.setString(2,txtSerieC.getText());
            cstmt.setString(3,lblNroCorrelativoC.getText());
            cstmt.setString(4,"01");
            cstmt.setString(5,lblFechaEmision.getText());
            cstmt.setString(6,"PEN");
            cstmt.setString(7,"03 BOLETA");
//                    
            cstmt.setDouble(8,Double.parseDouble(txtDsctoGlobal.getText()));
            cstmt.setDouble(9,Double.parseDouble(txtOtrosCargos.getText()));
            cstmt.setDouble(10,Double.parseDouble(lblDESCUENTO.getText()));
            cstmt.setDouble(11,Double.parseDouble(lblValorVentaGravada.getText()));
            cstmt.setDouble(12,Double.parseDouble(lblValorVentaInafectada.getText()));
            cstmt.setDouble(13,Double.parseDouble(txtVentaExonerada.getText()));
            cstmt.setDouble(14,Double.parseDouble(txtIGV.getText()));
            cstmt.setDouble(15,Double.parseDouble(txtMtoISC.getText()));
            cstmt.setDouble(16,Double.parseDouble(txtOtrosTributos.getText()));
            cstmt.setDouble(17,Double.parseDouble(txtTotal.getText()));
            cstmt.setString(18,lblusu.getText());
            cstmt.setInt(19,Integer.parseInt(lblID_CABECERA.getText()));
            // registramos el parametro de retorno (si fueran mas, repetimos la linea cambiando el nro de orden del parametro)
            cstmt.registerOutParameter(20, java.sql.Types.INTEGER);
            // ejecutamos
            cstmt.execute();
            // mostramos al usuario el codigo creado
            int ID;
            ID=cstmt.getInt(20);
            this.lblId.setText( String.valueOf(ID) );
           

        
//                    CuentasPorPagarFacturasCabecera facturaCabecera = new CuentasPorPagarFacturasCabecera();
//                    facturaCabecera.setCodigoEmpresa(lblIDCliente.getText());
//                    facturaCabecera.setSerie(txtSerieC.getText());
//                    facturaCabecera.setCorrelativo(lblNroCorrelativoC.getText());
//                    facturaCabecera.setTipoOperacion("01");
//                    facturaCabecera.setFechaEmision(lblFechaEmision.getText());
//                    facturaCabecera.setTipoMoneda("PEN");
//                    facturaCabecera.setDocumento("03 BOLETA");
////                    
//                    facturaCabecera.setDsctoGlobal(Double.parseDouble(txtDsctoGlobal.getText()));
//                    facturaCabecera.setOtrosCargos(Double.parseDouble(txtOtrosCargos.getText()));
//                    facturaCabecera.setTotalDscto(Double.parseDouble(txtTotalDscto.getText()));
//                    facturaCabecera.setValorVGravada(Double.parseDouble(lblValorVentaGravada.getText()));
//                    facturaCabecera.setValorVInafectada(Double.parseDouble(lblValorVentaInafectada.getText()));
//                    facturaCabecera.setVentaExonerada(Double.parseDouble(txtVentaExonerada.getText()));
//                    facturaCabecera.setMontoIgv(Double.parseDouble(txtIGV.getText()));
//                    facturaCabecera.setMontoIsc(Double.parseDouble(txtMtoISC.getText()));
//                    facturaCabecera.setOtrosTributos(Double.parseDouble(txtOtrosTributos.getText()));
//                    facturaCabecera.setImportaTotalVta(Double.parseDouble(txtTotal.getText()));
//                    facturaCabecera.setCod_usu(lblusu.getText());
//                    facturaCabecera.setID_DOCUMENTO_FACTURA(Integer.parseInt(lblID_CABECERA.getText()));
//            if(facturaCabecera.mantenimientoCuentasPorPagarFacturasCabecera()){
                if(crearCabecera()){
                    CuentasPorPagarFacturasCabecera ruc1=new CuentasPorPagarFacturasCabecera();
//                    CuentasPorPagarFacturasDetalle facturaDetalle1 = new CuentasPorPagarFacturasDetalle();
//                    facturaDetalle1.facturaCabeceraId(lblusu.getText());
                    String archivo = ruc1.factura_ruc() + "-" + 
                    "03" + "-" +
                    txtSerieC.getText() + "-" + 
                    lblNroCorrelativoC.getText() + ".DET";
                    File crea_archivo = new File(archivo);
                    for (int i = 0; i < tb_CPT.getRowCount(); i++){
                        
                        double valorU=0.00,igv=0.00,precioV=0.00,venta=0.00,prxcan,dscto=0.00;                      
                        if(lblGrupo.getText().equalsIgnoreCase("30 INAFECTO-OPERACIÓN ONEROSA")){
                            if(cbxFormaPago.getSelectedItem().equals("EXONERACION")){
                                valorU=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)));
                                igv=0.00;
                                precioV=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)));
                                dscto=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 6)));
                                venta=(precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4)))) - dscto;
                            }else{
                                valorU=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)));
                                igv=0.00;
                                precioV=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)));
                                venta=precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4)));
                                dscto=0.00;
                            }       
                        }else{
                            if(lblGrupo.getText().equalsIgnoreCase("10 GRAVADO-OPERACIÓN ONEROSA")){
                                if(cbxFormaPago.getSelectedItem().equals("EXONERACION")){
                                    valorU=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)))*100/118;
                                    prxcan=valorU*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4)));
                                    igv=prxcan*0.18;
                                    precioV=valorU+(igv/Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4))));
                                    dscto=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 6)));
                                    venta=(precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4)))) - dscto;
                                }else{
                                    valorU=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)))*100/118;
                                    prxcan=valorU*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4)));
                                    igv=prxcan*0.18;
                                    precioV=valorU+(igv/Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4))));
                                    venta=precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 4)));
                                    }                              
                                }                    
                        }
//            
                        CuentasPorPagarFacturasDetalle facturaDetalle = new CuentasPorPagarFacturasDetalle();
                        facturaDetalle.setCpfId(Integer.parseInt(lblId.getText()));
                        facturaDetalle.setCpdGrav("GRAVADO");
                        facturaDetalle.setCpdCodUnidad("NIU UNIDAD");
                        facturaDetalle.setCpdCantidad(Integer.parseInt(tb_CPT.getValueAt(i,4).toString()));
                        facturaDetalle.setNomenclatura(facturaDetalle.codNomen(tb_CPT.getValueAt(i,1).toString()));
                        facturaDetalle.setCpdCodProdSunat("");
                        facturaDetalle.setCpdValorU(BigDecimal.valueOf(valorU));
                        facturaDetalle.setCpdDescPorcen(BigDecimal.valueOf(Double.parseDouble(txtPorcentaje.getText())));
                        facturaDetalle.setCpdDscto(BigDecimal.valueOf(Double.parseDouble(tb_CPT.getValueAt(i,6).toString())));
                        facturaDetalle.setCpdIgv(BigDecimal.valueOf(igv));
                        facturaDetalle.setCpdAfecIgv(lblGrupo.getText()); 
                        facturaDetalle.setCpdIsc(BigDecimal.valueOf(Double.parseDouble("0.00")));
//                        facturaDetalle.setCpdAfecIsc(cbxAfecISC.getSelectedItem().toString()); 
                        facturaDetalle.setCpdAfecIsc(""); 
                        facturaDetalle.setCpdPrecioVenta(BigDecimal.valueOf(precioV));
                        facturaDetalle.setCpdValorVenta(BigDecimal.valueOf(venta));
                        facturaDetalle.setCpdDsctoGlobal(BigDecimal.valueOf(Double.parseDouble(txtDsctoGlobal.getText())));
                        facturaDetalle.setCpdSumOtrosCargos(BigDecimal.valueOf(Double.parseDouble(txtOtrosCargos.getText())));
                        facturaDetalle.setCpdSumIgv(BigDecimal.valueOf(igv));
                        facturaDetalle.setCpdTVvInafec(BigDecimal.valueOf(Double.parseDouble(lblValorVentaInafectada.getText())));
                        facturaDetalle.setCpdTVvGrav(BigDecimal.valueOf(Double.parseDouble(lblValorVentaGravada.getText())));
                        facturaDetalle.setCpdTDsctos(BigDecimal.valueOf(Double.parseDouble(tb_CPT.getValueAt(i,6).toString())));
                        facturaDetalle.setCpdOtrosTribut(BigDecimal.valueOf(Double.parseDouble(txtOtrosTributos.getText())));
                        facturaDetalle.setCpdSumIsc(BigDecimal.valueOf(Double.parseDouble(txtMtoISC.getText())));
                        facturaDetalle.setCpdTVExonen(BigDecimal.valueOf(Double.parseDouble(txtVentaExonerada.getText())));
                        facturaDetalle.setCpdImpTotVtas(BigDecimal.valueOf(venta));
                        facturaDetalle.setCodUsu(lblusu.getText());
//////                        facturaDetalle.setFormaPago(tbFacturacion.getValueAt(i,7).toString());
                        if(facturaDetalle.mantenimientoCuentasPorPagarFacturasDetalle()){
                            if(crearDetalles(crea_archivo, archivo)){
                                rpta = true;                     
                                

                            } else
                                rpta = false;
                                
                        }
                    }
                    if(rpta==true){
                    
                        panleBoleta.setVisible(true);
                        panleBoleta.setBackground(new Color(164,192,79));
                        Mensaje7.setText("Boleta Electrónica Generada");
                        ImpS.setVisible(false);
                        ImpN.setVisible(false);
                  
                    } else {
                        panleBoleta.setVisible(true);
                        panleBoleta.setBackground(new Color(255,51,51));
                        Mensaje7.setText("Ocurrió un error al Generar la Boleta Electrónica, Verifique");
                        ImpS.setVisible(false);
                        ImpN.setVisible(false);
                        
                    }
            }//fin if crearCabecera    
//            }
                }
        catch (Exception e) {
          e.printStackTrace();
        }
        } else{
         JOptionPane.showMessageDialog(this, "Error al crear la BOLETA");
        }
    }
    public boolean crearCabecera(){
        CuentasPorPagarFacturasCabecera ruc=new CuentasPorPagarFacturasCabecera();
        boolean retorna = false;
        String archivo = ruc.factura_ruc() + "-" + 
                "03" + "-" +
                txtSerieC.getText() + "-" + 
                lblNroCorrelativoC.getText() + ".CAB";
        File crea_archivo = new File(archivo);
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                    retorna = false;
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                        crea.format("01" + "|" +lblFechaEmision.getText()+"|"+
                        ""+"|"+
                        lbltipo_DOC_boleta.getText()+"|"+
                        txtCliente.getText()+"|"+
                        lblApeNom.getText() + "|" + 
                        "PEN" + "|" + 
                        "0.00" + "|" + 
                        "0.00" + "|" + 
                        lblDESCUENTO.getText() + "|" +
                        lblValorVentaGravada.getText() + "|" + 
                        lblValorVentaInafectada.getText() + "|" + 
                        "0.00" + "|" + 
                        txtIGV.getText() + "|" +
                        "0.00"+ "|" + 
                        "0.00" + "|" + 
                        txtTotal.getText());
                    crea.close();
                    retorna = true;
                }   
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                    retorna = false;
            }
        return retorna;
    }   
    
    
    //CREA EL DETALLE PARA EL FACTURADOR (ARCHIVO .DET) 
    //DE ACUERDO A LA CANTIDAD DE DETALLES QUE TENGA LA BOLETA DE VENTA
    public boolean crearDetalles(File crea_archivo, String archivo){
        boolean retorna = false;
        try {
            Formatter crea = new Formatter(ubicacion+archivo);
            if(crea_archivo.exists()){
                JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                retorna = false;
            } else {
                String bloc1 = "";
                double valorU=0,igv=0.00,precioV=0.00,venta=0.00,prxcan;
                for (int c = 0; c < tb_CPT.getRowCount(); c++){    
                    if(lblGrupo.getText().equalsIgnoreCase("30 INAFECTO-OPERACIÓN ONEROSA")){
                        valorU=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 3)));
                        igv=0.00;
                        precioV=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 3)));
                        venta=precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4)));
                    }else{
                        valorU=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 3)))*100/118;
                        prxcan=valorU*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4)));
                        igv=prxcan*0.18;
                        precioV=valorU+(igv/Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4))));
                        venta=precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4)));
                    }
                    
                    
                    BigDecimal bdvalorU = new BigDecimal(valorU);
                    bdvalorU = bdvalorU.setScale(2, BigDecimal.ROUND_HALF_UP);
                    
                    BigDecimal bdigv = new BigDecimal(igv);
                    bdigv = bdigv.setScale(2, BigDecimal.ROUND_HALF_UP);
                    
                    BigDecimal bdprecioV = new BigDecimal(precioV);
                    bdprecioV = bdprecioV.setScale(2, BigDecimal.ROUND_HALF_UP);
                    
                    BigDecimal bdventa = new BigDecimal(venta);
                    bdventa = bdventa.setScale(2, BigDecimal.ROUND_HALF_UP);
                    
                    
                    bloc1 = bloc1 + "NIU" + "|" +
                        String.valueOf(tb_CPT.getValueAt(c, 4)) + "|" + String.valueOf(tb_CPT.getValueAt(c, 1))+  "|" + 
                         ""+ "|" + 
                        String.valueOf(tb_CPT.getValueAt(c, 2))+ "|" + 
                         bdvalorU + "|" + 
                         String.valueOf(tb_CPT.getValueAt(c, 6)) + "|" + //DESCUENTO
                            //IGV
                        bdigv + "|" +  //IGV
                       String.valueOf(lblGrupo.getText().charAt(0)) +
                        String.valueOf(lblGrupo.getText().charAt(1)) + "|" +  //AFECTACION IGV
                        "0.00"+ "|" + //ISC
                        "" + "|" + //AFECTACION ISC
                            
                        String.valueOf(bdprecioV) + "|" + //PRECIO DE VENTA
                        String.valueOf(bdventa)  + "\r\n" /*VALOR DE VENTA*/;
                }
                crea.format(bloc1);
                crea.close();
                retorna = true;
            }   
        } catch (Exception e) {
            System.out.println("Error crear detalle: " + e.getMessage());
                retorna = false;
        }
        return retorna;
    }
    
    public void CARGAR_TB_FACTURADOR(){
        try {
            
            modelo1 = (DefaultTableModel) tb_CPT.getModel();
                        
            if(tb_CPT.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "No hay registros que cargar");
            }else{
            
            //pasar datos de una tabla a otra
            for (int i=0;i<modelo1.getRowCount(); i++){
                String item, descripcion, valor_u, cantidad,
                precio, igv="", dscto="", total="";
                double Precio_cant=0.00, tot=0.00, ig =0.00,vu=0.00;

                item = tb_CPT.getValueAt(i, 1).toString();
                descripcion = tb_CPT.getValueAt(i, 2).toString();     
                
                vu = Double.parseDouble(tb_CPT.getValueAt(i, 3).toString());
                
                cantidad = tb_CPT.getValueAt(i, 4).toString();
                precio = tb_CPT.getValueAt(i, 3).toString();
                
                if(lblGrupo.getText().equalsIgnoreCase("30 INAFECTO-OPERACIÓN ONEROSA")){
                    if(cbxFormaPago.getSelectedItem().equals("EXONERACION")){
                        igv = "0.00";
                        dscto=tb_CPT.getValueAt(i, 6).toString();
                        total=(String.valueOf((Double.parseDouble(precio)*Double.parseDouble(cantidad)) - Double.parseDouble(dscto)));
                        Facturador.cbxAfecIGV.setSelectedItem("30 INAFECTO-OPERACIÓN ONEROSA");
                    }else{
                        igv = "0.00";
                        total=String.valueOf(Double.parseDouble(precio)*Double.parseDouble(cantidad));
                        Facturador.cbxAfecIGV.setSelectedItem("30 INAFECTO-OPERACIÓN ONEROSA");
                        dscto="0.00";
                    }
                    
                }else if(lblGrupo.getText().equalsIgnoreCase("10 GRAVADO-OPERACIÓN ONEROSA")){
                    if(cbxFormaPago.getSelectedItem().equals("EXONERACION")){
                        vu = Double.parseDouble( tb_CPT.getValueAt(i, 3).toString())*100/118;
                        igv=String.valueOf(vu*Double.parseDouble(cantidad)*0.18);
                        
                        precio=String.valueOf(vu+(Double.parseDouble(igv)/Double.parseDouble(cantidad)));
                        dscto=tb_CPT.getValueAt(i, 6).toString();
                        total=(String.valueOf((Double.parseDouble(precio)*Double.parseDouble(cantidad)) - Double.parseDouble(dscto)));
                        Facturador.cbxAfecIGV.setSelectedItem("10 GRAVADO-OPERACIÓN ONEROSA");
                    }else{
                        vu = Double.parseDouble( tb_CPT.getValueAt(i, 3).toString())*100/118;
                        igv=String.valueOf(vu*Double.parseDouble(cantidad)*0.18);

                        precio=String.valueOf(vu+(Double.parseDouble(igv)/Double.parseDouble(cantidad)));
                        dscto="0.00";
                        total=String.valueOf(Double.parseDouble(precio)*Double.parseDouble(cantidad));
                        Facturador.cbxAfecIGV.setSelectedItem("10 GRAVADO-OPERACIÓN ONEROSA");
                    }
                    
                }
                
                BigDecimal ST = new BigDecimal(total);
                ST = ST.setScale(2, BigDecimal.ROUND_HALF_UP);
                String STS;
                STS=(String.valueOf(ST) );
                
                BigDecimal precior = new BigDecimal(precio);
                precior = precior.setScale(2, BigDecimal.ROUND_HALF_UP);
                      
                precio = String.valueOf(precior);
                
                BigDecimal igvr = new BigDecimal(igv);
                igvr = igvr.setScale(2, BigDecimal.ROUND_HALF_UP);
                      
                igv = String.valueOf(igvr);
                           
                
                BigDecimal bdvalor_u = new BigDecimal(vu);
                bdvalor_u = bdvalor_u.setScale(2, BigDecimal.ROUND_HALF_UP);
                      
                valor_u = String.valueOf(bdvalor_u);
                
                BigDecimal DSCTOR = new BigDecimal(dscto);
                DSCTOR = DSCTOR.setScale(2, BigDecimal.ROUND_HALF_UP);
                      
                dscto = String.valueOf(DSCTOR);
//                dscto = "0.00";

//                Precio_cant = Double.parseDouble(tb_CPT.getValueAt(i, 5).toString());
//
//                total = String.valueOf(Precio_cant);

                //Cargar los datos a la otra tabla 
                modelo2 = (DefaultTableModel) Facturador.tbFacturacion.getModel();

                String filaelemento[] = {item, descripcion, valor_u, cantidad,
                precio, igv, dscto, STS};

                modelo2.addRow(filaelemento);


            }   

                Facturador.btnGuardar.doClick();
            
        }    
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR LOS DATOS" + e.getMessage());
            
        }
    }
    
    public void MOSTRAR_DATOS_CLIENTE_BOLETA(String cod){
        String consulta="";
        try {
            consulta="EXEC CUENTAS_POR_PAGAR_DATOS_CLIENTE_BOLETA ?";
            PreparedStatement cmd = F.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lbltipo_DOC_boleta.setText(r.getString(1));
                lblApeNom.setText(r.getString(2));
                lblFechaEmision.setText(r.getString(3));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga datos cliente boleta: " + e.getMessage());
        }
    }
    
    public void calcularValores_BOLETA(){
            int fila = tb_CPT.getSelectedRow();
//            boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(fila, 15)), "");
//            lblDNI.setText(String.valueOf(tbBoletasCabecera.getValueAt(fila, 4)));
//            lblApeNom.setText(String.valueOf(tbBoletasCabecera.getValueAt(fila, 6)));
//            double sumatoriaTotal = 0.00;
//            double igv,importeTotalVenta;
//            for (int i = 0; i < tb_CPT.getRowCount(); i++){          
//                sumatoriaTotal = sumatoriaTotal + Double.parseDouble(tb_CPT.getValueAt(i,5).toString());         
//            }
//            igv = 0.00;
//            BigDecimal bd2 = new BigDecimal(igv);
//            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
            //
//            importeTotalVenta = sumatoriaTotal;
//            BigDecimal bdImporte  = new BigDecimal(importeTotalVenta);
//            bdImporte = bdImporte.setScale(2, BigDecimal.ROUND_HALF_UP);
//            txtMtoIGV.setText(String.valueOf(bd2));
//            txtImporteTotalVenta.setText(String.valueOf(bdImporte));
//            lblValorVentaInafectada.setText(String.valueOf(bdImporte));
//            
            
//            double igv=0.00,precioV=0.00,venta=0.00,prxcan;
//            for (int c = 0; c < tb_CPT.getRowCount(); c++){ 
//            if(lblGrupo.getText().equalsIgnoreCase("TP")){
//                        igv=0.00;
//                        precioV=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 3)));
//                        venta=precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4)));
//                    }else{
//                        prxcan=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 3)))*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4)));
//                        igv=prxcan*0.18;
//                        precioV=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 3)))+(igv/Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4))));
//                       
//                        venta=precioV*Double.parseDouble(String.valueOf(tb_CPT.getValueAt(c, 4)));
//                    }
//            
//            lblIGV_B.setText(String.valueOf(igv));
//            precioV_B.setText(String.valueOf(precioV));
//            venta_B.setText(String.valueOf(venta));
//            
//            }
            
            
            //VALORES POR DEFECTO ENVIADO CON 0.00 PORQUE NO AFECTAN 
            txtDsctoGlobal.setText("0.00");
            txtMtoISC.setText("0.00");
            txtOtrosCargos.setText("0.00");
            txtOtrosTributos.setText("0.00");
            txtTotalDscto.setText("0.00");
//            lblValorVentaGravada.setText("0.00");
            txtVentaExonerada.setText("0.00");
    
    }
    

    
    
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
            java.util.logging.Logger.getLogger(Caja_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ABONOS;
    private javax.swing.JDialog Afecto;
    private javax.swing.JDialog CLIENTES;
    private javax.swing.JDialog CPT;
    public static javax.swing.JDialog ErrorExistente;
    public static javax.swing.JButton ImpN;
    public static javax.swing.JButton ImpS;
    private javax.swing.JDialog Inafecto;
    public static javax.swing.JLabel LBL_CPF_ID;
    private javax.swing.JLabel Mensaje4;
    private javax.swing.JLabel Mensaje5;
    private javax.swing.JLabel Mensaje6;
    public static javax.swing.JLabel Mensaje7;
    private javax.swing.JLabel Mensaje9;
    private javax.swing.JTabbedPane Paginas;
    private javax.swing.JPanel PanelCantidad;
    private javax.swing.JDialog Preventas;
    private javax.swing.JLabel Tipo_Documento;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnAlertConsulta11;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnAnularVenta;
    private javax.swing.JButton btnBuscarCPT;
    public static javax.swing.JButton btnBuscarCPT1;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnCargarHOS1;
    private javax.swing.JButton btnCorrectoNo;
    private javax.swing.JButton btnCorrectoSi;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnEliminarHOS1;
    private javax.swing.JButton btnEliminarNo;
    private javax.swing.JButton btnEliminarSi;
    public static javax.swing.JButton btnImprimir;
    public static javax.swing.JButton btnImprimir1;
    public static javax.swing.JButton btnImprimir2;
    public static javax.swing.JButton btnLista;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnTerminiarVenta;
    public static javax.swing.JButton btneliminar;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus3;
    private javax.swing.JComboBox cbxFormaPago;
    private javax.swing.JComboBox cbxTipoDocumento;
    private javax.swing.JButton eli5;
    private javax.swing.JButton eli9;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lblAd1;
    private javax.swing.JLabel lblApeNom;
    public static javax.swing.JLabel lblCliente;
    public static javax.swing.JLabel lblCliente1;
    public static javax.swing.JLabel lblCliente2;
    public static javax.swing.JLabel lblCorrelativo;
    private javax.swing.JLabel lblDESCUENTO;
    private javax.swing.JLabel lblDNI_PREVENTA;
    public static javax.swing.JLabel lblDocumento;
    public static javax.swing.JLabel lblDocumento1;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblGrupo;
    public static javax.swing.JTextField lblIDCliente;
    private javax.swing.JLabel lblID_CAB;
    public static javax.swing.JLabel lblID_CABECERA;
    private javax.swing.JLabel lblID_CAB_PREVENTAS;
    private javax.swing.JLabel lblID_DETALLE;
    private javax.swing.JLabel lblID_DOCUEMENTO;
    public static javax.swing.JLabel lblID_Documento;
    public static javax.swing.JLabel lblID_SESION;
    private javax.swing.JLabel lblIGV_B;
    private javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblIdPreventa;
    private javax.swing.JLabel lblMontos;
    private javax.swing.JLabel lblNOMBRE_PREVENTA;
    public static javax.swing.JLabel lblNroCorrelativoC;
    private javax.swing.JLabel lblPorcentaje;
    public static javax.swing.JLabel lblSESION;
    public static javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblTIPO;
    private javax.swing.JLabel lblTIPO_DOC;
    public static javax.swing.JLabel lblTR;
    private javax.swing.JLabel lblValorVentaGravada;
    private javax.swing.JLabel lblValorVentaInafectada;
    private javax.swing.JLabel lbldetalle;
    private javax.swing.JLabel lbltipo_DOC_boleta;
    public static javax.swing.JLabel lblusu;
    public static javax.swing.JLabel lblusu1;
    private javax.swing.JButton noeli5;
    private javax.swing.JButton noeli7;
    private javax.swing.JPanel panelAnular;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCargarCPT;
    private javax.swing.JPanel panelDetalle;
    private javax.swing.JPanel panelDetalleHistorial;
    private javax.swing.JPanel panelEliminacion;
    private javax.swing.JPanel panelEliminar;
    private javax.swing.JPanel panelEliminarFR;
    private javax.swing.JPanel panelMensaje;
    private javax.swing.JPanel panelNumeros;
    private javax.swing.JPanel panelOpcionesFR;
    private javax.swing.JPanel panelProcentaje;
    private javax.swing.JPanel panelSinCPT;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel paneltablaHC;
    public static javax.swing.JPanel panleBoleta;
    private javax.swing.JLabel precioV_B;
    private javax.swing.JTable tFRDet;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTable tbFR;
    private javax.swing.JTable tb_CPT;
    private javax.swing.JTable tb_CPTBUSCAR;
    private javax.swing.JTable tb_Clientes;
    private javax.swing.JTable tb_Clientes1;
    private javax.swing.JTable tb_ReporteDiario;
    private javax.swing.JTable tb_ReporteDiario1;
    private javax.swing.JTable tbpreventas;
    private javax.swing.JTable tbpreventasFR;
    private javax.swing.JTextField txtBuscarCPT;
    private javax.swing.JTextField txtBuscarCliente;
    public static javax.swing.JTextField txtBusquedas;
    public static javax.swing.JTextField txtCPT;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCliente;
    private javax.swing.JLabel txtDsctoGlobal;
    private javax.swing.JTextField txtEnterEscapeEnter;
    private javax.swing.JTextField txtEnterEscapeEnter1;
    private javax.swing.JLabel txtIGV;
    private javax.swing.JLabel txtMtoISC;
    private javax.swing.JLabel txtOtrosCargos;
    private javax.swing.JLabel txtOtrosTributos;
    public static javax.swing.JTextField txtPorcentaje;
    public static javax.swing.JLabel txtSerie1;
    public static javax.swing.JLabel txtSerieC;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotalDscto;
    private javax.swing.JLabel txtVentaExonerada;
    private javax.swing.JLabel venta_B;
    // End of variables declaration//GEN-END:variables
}
