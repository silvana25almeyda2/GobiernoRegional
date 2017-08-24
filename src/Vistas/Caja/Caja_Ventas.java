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

/**
 *
 * @author MYS1
 */
public class Caja_Ventas extends javax.swing.JFrame {
Conexion c=new Conexion();
Connection ConexionS=c.conectar();
ResultSet r;
DefaultTableModel modelos;
DefaultTableModel m1;
Caja_NuevaVenta nuevaV = new Caja_NuevaVenta();
    /**
     * Creates new form Caja_Ventas
     */
    public Caja_Ventas() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        CLIENTES.setLocationRelativeTo(null);//en el centro
        CPT.setLocationRelativeTo(null);//en el centro
        ErrorExistente.setLocationRelativeTo(null);//en el centro
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
        this.cbxFormaPago.setModel(CargarFP());
        Nuevo(false);
        PanelCantidad.setVisible(false);
        panelAnular.setVisible(false);
        panelEliminar.setVisible(false);
        
        addEscapeListenerWindowDialog(CLIENTES);
        addEscapeListenerWindowDialog(CPT);
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
    
    public void SalirFormulario(){
        if(tb_CPT.getRowCount()==0){       
                    btnImprimir.setEnabled(true);
                    panelEliminar.setVisible(true);   
                    panelMensaje.setVisible(false);  
//                    tgnuevoEliminar=1;
                    txtEnterEscapeEnter1.requestFocus();     
        }  
        if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
//                    lblImpresora.setText("original"); 
                    btnImprimir.setEnabled(true);
                    panelAnular.setVisible(true);
                    panelMensaje.setVisible(false);  
                    txtEnterEscapeEnter.requestFocus();
        }else  if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("FACTURA")){
                    JOptionPane.showMessageDialog(this, "VAMO FACTURANDO");
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
        String  ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD;
        int i=tb_CPTBUSCAR.getSelectedRow();
        lblGrupo.setText(String.valueOf(tb_CPTBUSCAR.getValueAt(i, 4))); 
        ID_CPT = tb_CPTBUSCAR.getValueAt(i, 0).toString();
        ID_GRUPO = tb_CPTBUSCAR.getValueAt(i, 1).toString();
        CPTs = tb_CPTBUSCAR.getValueAt(i, 2).toString();
        DESCRIPCION = tb_CPTBUSCAR.getValueAt(i, 3).toString();
        //Cargar los datos a la otra tabla 
        CANTIDAD=txtCantidad.getText();
        
        
        if(tb_CPT.getRowCount()==0){
            modelos = (DefaultTableModel) tb_CPT.getModel();
            String filaelemento[] = {ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD};
            modelos.addRow(filaelemento);
               
          }
          else{
           if(repiteDetalle()==true){
               System.out.println("El Producto ya ha sido ingresado");
          } 
           else{
                m1=(DefaultTableModel) tb_CPT.getModel();
           String filaelemento[]={ID_CPT, ID_GRUPO,CPTs,DESCRIPCION,CANTIDAD};
               m1.addRow(filaelemento);   
           }
          }
        
        PanelCantidad.setVisible(false);
        jPanel36.setVisible(true); 
        jScrollPane4.setVisible(true); 
        panelNumeros.setVisible(true); 
        CPT.dispose();
        btnBuscarCPT.requestFocus();
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
        double IGV=0;
        double subtotal=0;
        double descuento =0;

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
        IGV=total*0.18;
        subtotal=total-IGV;
        descuento=0;
        ////////////////////////////////////////////////////////////////////////
        
        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal bd3 = new BigDecimal(IGV);
        bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal bd4 = new BigDecimal(subtotal);
        bd4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
        ////////////////////////////////////////////////////////////////////////
        //muestra en el componente
        
        

        if (lblGrupo.getText().equals("TP")){
            this.txtSubTotal.setText(String.valueOf(bd2) );
            this.txtIGV.setText("0.00");
            this.txtTotal.setText(String.valueOf(bd2) );
            lblMontos.setText("Total a Pagar    S/."+String.valueOf(bd2));
        }else if (!lblGrupo.getText().equals("TP")){
            this.txtSubTotal.setText(String.valueOf(bd4) );
            this.txtIGV.setText(String.valueOf(bd3) );
            this.txtTotal.setText(String.valueOf(bd2) );
            lblMontos.setText("Subtotal    S/."+String.valueOf(bd4)+"       "+"IGV    S/."+String.valueOf(bd3)+"       "+"Total a Pagar    S/."+String.valueOf(bd2));

        }
    }
    
    public DefaultComboBoxModel CargarFP(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = ConexionS.createStatement();
              r = st.executeQuery ("CAJA_PAGOS_LISTAR_FORMA_PAGO"); 
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
        lblSerie_Correlativo.setVisible(opcion);
        panelCPT1.setVisible(opcion);
        lTipoDoc1.setVisible(opcion); 
        jLabel2.setVisible(opcion); 
        lDoc.setVisible(opcion); 
        lDoc1.setVisible(opcion); 
        lDoc2.setVisible(opcion); 
        panelCPT2.setVisible(opcion); 
        jPanel36.setVisible(opcion); 
        jScrollPane4.setVisible(opcion); 
        panelNumeros.setVisible(opcion); 
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
        
    }
    
    public void NUEVO_REGISTRO(java.sql.Connection con) {
        try {
            // instanciamos el objeto callable
            CallableStatement cstmt = con.prepareCall("exec CAJA_VENTA_CABECERA_NUEVO ?,?,?,?,?,?,?,?");
            // seteamos los parametros de entrada
            cstmt.setInt(1, Integer.parseInt(lblID_Documento.getText()));
            cstmt.setInt(2, Integer.parseInt(lblIDCliente.getText()));
            cstmt.setString(3, lblSerie.getText());
            cstmt.setString(4, lblCorrelativo.getText());
            cstmt.setString(5, lblusu.getText());
            if(cbxTipoDocumento.getSelectedItem().equals("BOLETA"))
                  cstmt.setString(6, "B");
            else if(!cbxTipoDocumento.getSelectedItem().equals("BOLETA"))
                  cstmt.setString(6, "F");
            cstmt.setInt(7, (Integer.parseInt(lblSESION.getText())));
            // registramos el parametro de retorno (si fueran mas, repetimos la linea cambiando el nro de orden del parametro)
            cstmt.registerOutParameter(8, java.sql.Types.INTEGER);
            // ejecutamos
            cstmt.execute();
            // mostramos al usuario el codigo creado
            int ID;
            ID=cstmt.getInt(8);
            this.lblID_CABECERA.setText( String.valueOf(ID) );
            System.out.println("CABECERA GURADADA");  
            txtBuscarCliente.setText("");
            lDoc.setVisible(true);
            lblSerie_Correlativo.setVisible(true);
            lDoc1.setVisible(true);
            panelCPT1.setVisible(true);
            DetalleVenta(false);
            CPT.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    
    public void ACTUALIZAR_CABECERA(){
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setID_DOCUMENTO(Integer.parseInt(lblID_CABECERA.getText()));
                cno1.setDESCUENTO(0.00);
                cno1.setSUB_TOTAL(Double.parseDouble(txtSubTotal.getText()));
                cno1.setIGV(Double.parseDouble(txtIGV.getText()));
                cno1.setTOTAL_DOC(Double.parseDouble(txtTotal.getText()));
                    if(cno1.ACTUALIZAR_VENTA()==true){
                        System.out.println("Cabecera Actualizada"); 
                    } else {
                        System.out.println("Ocurrio un error");
                    }
    }
    
    public void NUEVO_REGISTRO_DETALLE(){
            for(int i = 0; i < tb_CPT.getRowCount(); i++){
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setID_DOCUMENTO(Integer.parseInt(lblID_CABECERA.getText()));
                cno1.setID_PRECIO(Integer.parseInt(String.valueOf(tb_CPT.getValueAt(i, 0))));
                cno1.setCANTIDAD(Integer.parseInt(String.valueOf(tb_CPT.getValueAt(i, 4))));
                cno1.setPRECIO(Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3))));
                int cant=0;
                Double Prec=0.0,total=0.0;
                cant=Integer.parseInt(String.valueOf(tb_CPT.getValueAt(i, 4)));
                Prec=Double.parseDouble(String.valueOf(tb_CPT.getValueAt(i, 3)));
                total=cant*Prec;
                cno1.setTOTAL(total);
                cno1.setDESCUENTOD(0.0);
                cno1.setUSUARIO(lblusu.getText());
                    if(cno1.NUEVA_VENTA_DETALLE()==true){
                        System.out.println("DETALLE DE VENTA GURADADO"); 
                    } else {
                        panelMensaje.setVisible(true);
                        panelMensaje.setBackground(new Color(255,51,51)); 
                        Mensaje4.setText("Ocurrió un error verifique");
                        btnCorrectoSi.setVisible(false);
                        btnCorrectoNo.setVisible(false);
                    }
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

        CLIENTES = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bus = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtBuscarCliente = new javax.swing.JTextField();
        bus3 = new javax.swing.JLabel();
        btnBuscarPaciente2 = new javax.swing.JButton();
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
                            jLabel11 = new javax.swing.JLabel();
                            PanelCantidad = new javax.swing.JPanel();
                            txtCantidad = new javax.swing.JTextField();
                            jLabel4 = new javax.swing.JLabel();
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
                            Paginas = new javax.swing.JTabbedPane();
                            jPanel3 = new javax.swing.JPanel();
                            jPanel4 = new javax.swing.JPanel();
                            jLabel2 = new javax.swing.JLabel();
                            lTipoDoc = new javax.swing.JLabel();
                            cbxFormaPago = new javax.swing.JComboBox();
                            lDoc = new javax.swing.JLabel();
                            lblSerie_Correlativo = new javax.swing.JLabel();
                            lDoc1 = new javax.swing.JLabel();
                            panelCPT1 = new javax.swing.JPanel();
                            txtCPT = new javax.swing.JTextField();
                            btnBuscarCPT = new javax.swing.JButton();
                            jPanel36 = new javax.swing.JPanel();
                            jScrollPane4 = new javax.swing.JScrollPane();
                            tb_CPT = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                panelEliminacion = new javax.swing.JPanel();
                                btnEliminarDetalle = new javax.swing.JButton();
                                noeli5 = new javax.swing.JButton();
                                eli5 = new javax.swing.JButton();
                                lDoc2 = new javax.swing.JLabel();
                                panelCPT2 = new javax.swing.JPanel();
                                txtgrupo1 = new javax.swing.JTextField();
                                panelCPT3 = new javax.swing.JPanel();
                                txtCliente = new javax.swing.JTextField();
                                btnBuscarCPT1 = new javax.swing.JButton();
                                lblIDCliente = new javax.swing.JLabel();
                                lblSerie = new javax.swing.JLabel();
                                lblCorrelativo = new javax.swing.JLabel();
                                lblID_Documento = new javax.swing.JLabel();
                                lTipoDoc1 = new javax.swing.JLabel();
                                cbxTipoDocumento = new javax.swing.JComboBox();
                                panelMensaje = new javax.swing.JPanel();
                                Mensaje4 = new javax.swing.JLabel();
                                btnCorrectoSi = new javax.swing.JButton();
                                btnCorrectoNo = new javax.swing.JButton();
                                jPanel2 = new javax.swing.JPanel();
                                lblCliente = new javax.swing.JLabel();
                                lblDocumento = new javax.swing.JLabel();
                                lblDocumento1 = new javax.swing.JLabel();
                                lblSESION = new javax.swing.JLabel();
                                lblID_CABECERA = new javax.swing.JLabel();
                                panelAnular = new javax.swing.JPanel();
                                btnbuscar9 = new javax.swing.JButton();
                                jLabel38 = new javax.swing.JLabel();
                                btnTerminiarVenta = new javax.swing.JButton();
                                jPanel17 = new javax.swing.JPanel();
                                btnAnularVenta = new javax.swing.JButton();
                                txtEnterEscapeEnter = new javax.swing.JTextField();
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
                                jPanel5 = new javax.swing.JPanel();
                                jPanel6 = new javax.swing.JPanel();
                                lblCliente1 = new javax.swing.JLabel();
                                lblDocumento2 = new javax.swing.JLabel();
                                lblDocumento3 = new javax.swing.JLabel();
                                jScrollPane11 = new javax.swing.JScrollPane();
                                tb_ReporteDiario = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};

                                    CLIENTES.setAlwaysOnTop(true);
                                    CLIENTES.setMinimumSize(new java.awt.Dimension(749, 350));
                                    CLIENTES.setResizable(false);

                                    jPanel7.setBackground(new java.awt.Color(41, 127, 184));
                                    jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                                    jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel19.setText("Clientes");

                                    jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                    jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel13.setText("DNI, RUC, Apellidos, Razón Social");

                                    bus.setForeground(new java.awt.Color(41, 127, 184));
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

                                    bus3.setForeground(new java.awt.Color(41, 127, 184));
                                    bus3.setText("jLabel37");

                                    btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                    btnBuscarPaciente2.setContentAreaFilled(false);
                                    btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnBuscarPaciente2ActionPerformed(evt);
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
                                                    .addComponent(jLabel13)
                                                    .addGap(39, 39, 39)
                                                    .addComponent(bus)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(bus3))
                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(2, 2, 2)
                                                    .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );
                                    jPanel7Layout.setVerticalGroup(
                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jLabel19)
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
                                                        .addComponent(bus3))))
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
                                    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Búsqueda-64.png"))); // NOI18N
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

                                    jPanel35.setBackground(new java.awt.Color(41, 127, 184));
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
                                    jLabel17.setForeground(new java.awt.Color(50, 151, 219));
                                    jLabel17.setText(":(");

                                    jPanel9.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    jLabel18.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Invitado masculino-64.png"))); // NOI18N
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
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 23, Short.MAX_VALUE))
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

                                    jPanel11.setBackground(new java.awt.Color(41, 127, 184));
                                    jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                                    jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                    jLabel21.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel21.setText("CPT");

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

                                    btnBuscarPaciente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                    btnBuscarPaciente3.setContentAreaFilled(false);
                                    btnBuscarPaciente3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnBuscarPaciente3.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnBuscarPaciente3ActionPerformed(evt);
                                        }
                                    });

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
                                                    .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );
                                    jPanel11Layout.setVerticalGroup(
                                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel21)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(350, 350, 350))
                                    );

                                    panelBuscar.setBackground(new java.awt.Color(255, 255, 255));

                                    jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                    jLabel22.setForeground(new java.awt.Color(102, 102, 102));
                                    jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Búsqueda-64.png"))); // NOI18N
                                    jLabel22.setText("Busqueda de CPT ");

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
                                    jLabel3.setText("  CPT");

                                    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                                    jLabel8.setText("Descripción");

                                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                                    jLabel10.setText("Precio");

                                    jPanel33.setBackground(new java.awt.Color(41, 127, 184));
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

                                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                                    jLabel11.setText("Cantidad");

                                    PanelCantidad.setBackground(new java.awt.Color(214, 217, 223));

                                    txtCantidad.setBackground(new java.awt.Color(214, 217, 223));
                                    txtCantidad.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
                                    txtCantidad.setForeground(new java.awt.Color(51, 51, 51));
                                    txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                    txtCantidad.setBorder(null);
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

                                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                                    jLabel4.setText("Cantidad");

                                    javax.swing.GroupLayout PanelCantidadLayout = new javax.swing.GroupLayout(PanelCantidad);
                                    PanelCantidad.setLayout(PanelCantidadLayout);
                                    PanelCantidadLayout.setHorizontalGroup(
                                        PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelCantidadLayout.createSequentialGroup()
                                            .addGap(140, 140, 140)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(776, Short.MAX_VALUE)))
                                    );
                                    PanelCantidadLayout.setVerticalGroup(
                                        PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addGroup(PanelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelCantidadLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    );

                                    javax.swing.GroupLayout panelCargarCPTLayout = new javax.swing.GroupLayout(panelCargarCPT);
                                    panelCargarCPT.setLayout(panelCargarCPTLayout);
                                    panelCargarCPTLayout.setHorizontalGroup(
                                        panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6)
                                        .addComponent(PanelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                            .addGroup(panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                                                .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                                    .addGroup(panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(64, 64, 64)
                                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelCargarCPTLayout.createSequentialGroup()
                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addContainerGap())
                                    );
                                    panelCargarCPTLayout.setVerticalGroup(
                                        panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCargarCPTLayout.createSequentialGroup()
                                            .addGroup(panelCargarCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    jLabel24.setForeground(new java.awt.Color(50, 151, 219));
                                    jLabel24.setText(":(");

                                    jPanel16.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    jLabel25.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Editar propiedad-64 (1).png"))); // NOI18N
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

                                    jPanel134.setBackground(new java.awt.Color(241, 197, 14));
                                    jPanel134.setMinimumSize(new java.awt.Dimension(310, 441));

                                    lblAd1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                    lblAd1.setForeground(new java.awt.Color(51, 51, 51));
                                    lblAd1.setText("Error");

                                    jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    jLabel119.setForeground(new java.awt.Color(51, 51, 51));
                                    jLabel119.setText("<html>No se puedo imprimir el comprobante,<br>\nEsto puede deberse a que se agotó el papel o la impresora no está disponible.<br>\nPar reimprimir el comprobante vaya a ventas de hoy \n</html> ");

                                    jPanel135.setBackground(new java.awt.Color(43, 43, 43));

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

                                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                                    jPanel1.setBackground(new java.awt.Color(41, 127, 184));
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

                                    btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-27.png"))); // NOI18N
                                    btnBuscarPaciente.setContentAreaFilled(false);
                                    btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnBuscarPacienteActionPerformed(evt);
                                        }
                                    });

                                    lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                    lbldetalle.setText("DNI, ID Venta");

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

                                    btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnImprimir.setForeground(new java.awt.Color(240, 240, 240));
                                    btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imprimir-32.png"))); // NOI18N
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
                                    btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Basura-32.png"))); // NOI18N
                                    btneliminar.setText("Anular");
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

                                    btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnLista.setForeground(new java.awt.Color(240, 240, 240));
                                    btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Orden de compra-32.png"))); // NOI18N
                                    btnLista.setText("Ventas de Hoy");
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
                                    lblusu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                    lblusu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
                                    lblusu1.setFocusable(false);
                                    lblusu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
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

                                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                    jPanel1.setLayout(jPanel1Layout);
                                    jPanel1Layout.setHorizontalGroup(
                                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(24, 24, 24)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lbldetalle)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(lblusu1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblID_SESION, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addContainerGap(19, Short.MAX_VALUE))
                                    );
                                    jPanel1Layout.setVerticalGroup(
                                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(19, 19, 19)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(39, 39, 39)
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

                                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                    jPanel3.setLayout(jPanel3Layout);
                                    jPanel3Layout.setHorizontalGroup(
                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 891, Short.MAX_VALUE)
                                    );
                                    jPanel3Layout.setVerticalGroup(
                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 680, Short.MAX_VALUE)
                                    );

                                    Paginas.addTab("tab1", jPanel3);

                                    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                    jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                                    jLabel2.setText("Cliente");

                                    lTipoDoc.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                    lTipoDoc.setForeground(new java.awt.Color(51, 51, 51));
                                    lTipoDoc.setText("Tipo de Documento");

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

                                    lDoc.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                    lDoc.setForeground(new java.awt.Color(51, 51, 51));
                                    lDoc.setText("Numero de Documento");

                                    lblSerie_Correlativo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                    lblSerie_Correlativo.setForeground(new java.awt.Color(51, 51, 51));
                                    lblSerie_Correlativo.setText("Serie_Correlativo");

                                    lDoc1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                    lDoc1.setForeground(new java.awt.Color(51, 51, 51));
                                    lDoc1.setText("Item");

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

                                    btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
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
                                            .addComponent(txtCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
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
                                            "ID_PRECIO", "ITEM", "DESCRIPCION", "PRECIO", "CANTIDAD"
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
                                    btnEliminarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Basura-32.png"))); // NOI18N
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

                                    lDoc2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                    lDoc2.setForeground(new java.awt.Color(51, 51, 51));
                                    lDoc2.setText("Cantidad");

                                    panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                                    panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                    txtgrupo1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                    txtgrupo1.setForeground(new java.awt.Color(51, 51, 51));
                                    txtgrupo1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                    txtgrupo1.setBorder(null);
                                    txtgrupo1.addCaretListener(new javax.swing.event.CaretListener() {
                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                            txtgrupo1CaretUpdate(evt);
                                        }
                                    });

                                    javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                                    panelCPT2.setLayout(panelCPT2Layout);
                                    panelCPT2Layout.setHorizontalGroup(
                                        panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCPT2Layout.createSequentialGroup()
                                            .addGap(5, 5, 5)
                                            .addComponent(txtgrupo1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                            .addGap(4, 4, 4))
                                    );
                                    panelCPT2Layout.setVerticalGroup(
                                        panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCPT2Layout.createSequentialGroup()
                                            .addGap(0, 0, 0)
                                            .addComponent(txtgrupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

                                    btnBuscarCPT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
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
                                            .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
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

                                    lblIDCliente.setForeground(new java.awt.Color(255, 255, 255));
                                    lblIDCliente.setText("jLabel1");

                                    lblSerie.setForeground(new java.awt.Color(255, 255, 255));
                                    lblSerie.setText("jLabel1");

                                    lblCorrelativo.setForeground(new java.awt.Color(255, 255, 255));
                                    lblCorrelativo.setText("jLabel3");

                                    lblID_Documento.setForeground(new java.awt.Color(255, 255, 255));
                                    lblID_Documento.setText("jLabel1");

                                    lTipoDoc1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                    lTipoDoc1.setForeground(new java.awt.Color(51, 51, 51));
                                    lTipoDoc1.setText("Forma de Pago");

                                    cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                    cbxTipoDocumento.setForeground(new java.awt.Color(51, 51, 51));
                                    cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BOLETA", "FACTURA" }));
                                    cbxTipoDocumento.addMouseListener(new java.awt.event.MouseAdapter() {
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

                                    Mensaje4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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

                                    jPanel2.setBackground(new java.awt.Color(127, 140, 141));
                                    jPanel2.setPreferredSize(new java.awt.Dimension(405, 113));

                                    lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    lblCliente.setForeground(new java.awt.Color(255, 255, 255));
                                    lblCliente.setText("Cliente");

                                    lblDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                    lblDocumento.setForeground(new java.awt.Color(255, 255, 255));
                                    lblDocumento.setText("DNI");

                                    lblDocumento1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                    lblDocumento1.setForeground(new java.awt.Color(255, 255, 255));
                                    lblDocumento1.setText("DOCUMENTO");

                                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                    jPanel2.setLayout(jPanel2Layout);
                                    jPanel2Layout.setHorizontalGroup(
                                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(lblDocumento1)
                                                    .addGap(11, 11, 11)
                                                    .addComponent(lblDocumento)))
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );
                                    jPanel2Layout.setVerticalGroup(
                                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(23, 23, 23)
                                            .addComponent(lblCliente)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblDocumento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(43, 43, 43))
                                    );

                                    lblSESION.setForeground(new java.awt.Color(255, 255, 255));
                                    lblSESION.setText("jLabel1");

                                    lblID_CABECERA.setForeground(new java.awt.Color(255, 0, 51));
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

                                    btnbuscar9.setForeground(new java.awt.Color(240, 240, 240));
                                    btnbuscar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Print-32.png"))); // NOI18N
                                    btnbuscar9.setContentAreaFilled(false);
                                    btnbuscar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnbuscar9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                    btnbuscar9.setIconTextGap(30);
                                    btnbuscar9.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnbuscar9ActionPerformed(evt);
                                        }
                                    });

                                    jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    jLabel38.setForeground(new java.awt.Color(102, 102, 102));
                                    jLabel38.setText("La venta fue guardada de forma exitosa");

                                    btnTerminiarVenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                    btnTerminiarVenta.setForeground(new java.awt.Color(102, 102, 102));
                                    btnTerminiarVenta.setText("Terminar y empezar una nueva venta");
                                    btnTerminiarVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
                                    btnTerminiarVenta.setContentAreaFilled(false);
                                    btnTerminiarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnTerminiarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                    btnTerminiarVenta.setIconTextGap(30);
                                    btnTerminiarVenta.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnTerminiarVentaActionPerformed(evt);
                                        }
                                    });

                                    jPanel17.setBackground(new java.awt.Color(255, 51, 51));
                                    jPanel17.setPreferredSize(new java.awt.Dimension(125, 25));

                                    btnAnularVenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                    btnAnularVenta.setForeground(new java.awt.Color(240, 240, 240));
                                    btnAnularVenta.setText("Anular");
                                    btnAnularVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
                                    btnAnularVenta.setContentAreaFilled(false);
                                    btnAnularVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnAnularVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(btnAnularVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    );
                                    jPanel17Layout.setVerticalGroup(
                                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAnularVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    );

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

                                    javax.swing.GroupLayout panelAnularLayout = new javax.swing.GroupLayout(panelAnular);
                                    panelAnular.setLayout(panelAnularLayout);
                                    panelAnularLayout.setHorizontalGroup(
                                        panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelAnularLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(btnbuscar9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel38)
                                            .addGap(73, 73, 73)
                                            .addComponent(btnTerminiarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(26, 26, 26)
                                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtEnterEscapeEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(30, Short.MAX_VALUE))
                                    );
                                    panelAnularLayout.setVerticalGroup(
                                        panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnbuscar9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelAnularLayout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addGroup(panelAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                                .addComponent(btnTerminiarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtEnterEscapeEnter))
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    );

                                    panelEliminar.setBackground(new java.awt.Color(255, 51, 51));

                                    Mensaje5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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

                                    lblGrupo.setText("jLabel1");

                                    panelNumeros.setBackground(new java.awt.Color(214, 217, 223));

                                    lblMontos.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
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
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblMontos)
                                            .addContainerGap())
                                    );

                                    txtSubTotal.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
                                    txtSubTotal.setForeground(new java.awt.Color(51, 51, 51));
                                    txtSubTotal.setText("0");

                                    txtIGV.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
                                    txtIGV.setForeground(new java.awt.Color(51, 51, 51));
                                    txtIGV.setText("0");

                                    txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
                                    txtTotal.setForeground(new java.awt.Color(51, 51, 51));
                                    txtTotal.setText("0");

                                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                    jPanel4.setLayout(jPanel4Layout);
                                    jPanel4Layout.setHorizontalGroup(
                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4)
                                        .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lDoc)
                                                .addComponent(lDoc1)
                                                .addComponent(jLabel2)
                                                .addComponent(lTipoDoc)
                                                .addComponent(lTipoDoc1))
                                            .addGap(50, 50, 50)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(panelCPT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblSerie_Correlativo)
                                                .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbxFormaPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbxTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(lDoc2)
                                                    .addGap(33, 33, 33)
                                                    .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtSubTotal)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtIGV)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtTotal))
                                                .addComponent(lblIDCliente)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(lblSerie)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblCorrelativo))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(lblID_Documento)
                                                    .addGap(36, 36, 36)
                                                    .addComponent(lblSESION, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(lblID_CABECERA)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblGrupo)))
                                            .addContainerGap(180, Short.MAX_VALUE))
                                        .addComponent(panelMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                        .addComponent(panelAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelEliminacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelNumeros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lTipoDoc)
                                                .addComponent(lblID_Documento)
                                                .addComponent(lblSESION))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lTipoDoc1)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(cbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblID_CABECERA)
                                                    .addComponent(lblGrupo)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblIDCliente)
                                                .addComponent(jLabel2))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lDoc)
                                                        .addComponent(lblSerie_Correlativo)
                                                        .addComponent(lblSerie)
                                                        .addComponent(lblCorrelativo))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(lDoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lDoc2, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(panelCPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(panelCPT2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtSubTotal)
                                                    .addComponent(txtIGV)
                                                    .addComponent(txtTotal)))
                                            .addGap(18, 18, 18)
                                            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                            .addGap(0, 0, 0)
                                            .addComponent(panelEliminacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(panelNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    );

                                    Paginas.addTab("tab2", jPanel4);

                                    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                                    jPanel6.setBackground(new java.awt.Color(127, 140, 141));
                                    jPanel6.setPreferredSize(new java.awt.Dimension(405, 113));

                                    lblCliente1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    lblCliente1.setForeground(new java.awt.Color(255, 255, 255));
                                    lblCliente1.setText("Cliente");

                                    lblDocumento2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                    lblDocumento2.setForeground(new java.awt.Color(255, 255, 255));
                                    lblDocumento2.setText("DNI");

                                    lblDocumento3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                    lblDocumento3.setForeground(new java.awt.Color(255, 255, 255));
                                    lblDocumento3.setText("Documento");

                                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                    jPanel6.setLayout(jPanel6Layout);
                                    jPanel6Layout.setHorizontalGroup(
                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addComponent(lblDocumento3)
                                                    .addGap(11, 11, 11)
                                                    .addComponent(lblDocumento2)))
                                            .addContainerGap(440, Short.MAX_VALUE))
                                    );
                                    jPanel6Layout.setVerticalGroup(
                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGap(23, 23, 23)
                                            .addComponent(lblCliente1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblDocumento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblDocumento3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(43, 43, 43))
                                    );

                                    jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));
                                    jScrollPane11.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                    jScrollPane11.setForeground(new java.awt.Color(255, 255, 255));
                                    jScrollPane11.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                                    tb_ReporteDiario.setForeground(new java.awt.Color(51, 51, 51));
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

                                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                    jPanel5.setLayout(jPanel5Layout);
                                    jPanel5Layout.setHorizontalGroup(
                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                                                .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE))
                                            .addGap(0, 0, 0))
                                    );
                                    jPanel5Layout.setVerticalGroup(
                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                                            .addGap(0, 0, 0))
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
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                                        .addComponent(Paginas, javax.swing.GroupLayout.Alignment.TRAILING)
                                    );

                                    pack();
                                }// </editor-fold>//GEN-END:initComponents

    private void txtBusquedasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedasCaretUpdate

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
            CSC.Caja_Correlativo(lblusu.getText());
            Caja_NuevaVenta cno2 = new Caja_NuevaVenta();
            cno2.DATOS_FP(cbxFormaPago.getSelectedItem().toString());
        }
        if(!lblID_CABECERA.getText().equals("A")&&tb_CPT.getRowCount()==0){
            btnImprimir.setEnabled(true);
            panelEliminar.setVisible(true);   
            panelMensaje.setVisible(false);  
            txtEnterEscapeEnter1.requestFocus(); 
        }
        if(!lblID_CABECERA.getText().equals("A")&&tb_CPT.getRowCount()>0){
            btnImprimir.setEnabled(true);
            panelAnular.setVisible(true);
            panelMensaje.setVisible(false);  
            txtEnterEscapeEnter.requestFocus();  
        }
        
////////////////////////////////////////////////////////////////////////
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
                    btnImprimir.setEnabled(true);
                    panelAnular.setVisible(true);
                    panelMensaje.setVisible(false);  
                    txtEnterEscapeEnter.requestFocus();
        }else  if(tb_CPT.getRowCount()!=0 && cbxTipoDocumento.getSelectedItem().equals("FACTURA")){
            JOptionPane.showMessageDialog(this, "VAMO FACTURANDO");
        }
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        Caja_NuevaVenta CNVRCC = new  Caja_NuevaVenta();
        CNVRCC.ReporteDiariocajaCabecera(lblusu.getText(),Integer.parseInt(lblSESION.getText()),tb_ReporteDiario);
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
    }//GEN-LAST:event_cbxFormaPagoItemStateChanged

    private void cbxFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFormaPagoActionPerformed
        panelCPT3.setVisible(true);
        jLabel2.setVisible(true);
    }//GEN-LAST:event_cbxFormaPagoActionPerformed

    private void cbxFormaPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxFormaPagoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            panelCPT3.setVisible(true);
            jLabel2.setVisible(true);
            btnBuscarCPT1.doClick();
        }  
    }//GEN-LAST:event_cbxFormaPagoKeyPressed

    private void cbxFormaPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxFormaPagoKeyTyped

    }//GEN-LAST:event_cbxFormaPagoKeyTyped

    private void txtCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPTCaretUpdate

    }//GEN-LAST:event_txtCPTCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
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

    private void txtgrupo1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtgrupo1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgrupo1CaretUpdate

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
            CARGAR();
            CLIENTES.dispose();
            NUEVO_REGISTRO(ConexionS);
        }
    }//GEN-LAST:event_tbClientesMouseClicked

    private void tbClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbClientesMouseEntered

    private void tbClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbClientesKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            CARGAR();
            CLIENTES.dispose();
            NUEVO_REGISTRO(ConexionS);
        }
    }//GEN-LAST:event_tbClientesKeyPressed

    private void txtClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtClienteCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteCaretUpdate

    private void btnBuscarCPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT1ActionPerformed
        CLIENTES.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPT1ActionPerformed

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        Caja_Clientes CT = new Caja_Clientes();
        CT.setVisible(true);
        CT.btnCaja.doClick();
        CLIENTES.dispose();
//        String u=PrincipalMDI.lblUsu.getText();
//        CT.lblusu.setText(u);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void cbxTipoDocumentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoMouseReleased

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
        lTipoDoc1.setVisible(true);
        cbxFormaPago.setVisible(true);
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void cbxTipoDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyPressed

        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            lTipoDoc1.setVisible(true);
            cbxTipoDocumento.requestFocus();
        cbxTipoDocumento.showPopup();
//            cbxFormaPago.setVisible(true);    
//            cbxFormaPago.showPopup();
//            cbxFormaPago.requestFocus(true);
            
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
        CNVCPT.VENTA_LISTA_CPT(txtBuscarCPT.getText(),tb_CPTBUSCAR);

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

        if(evt.getClickCount()==2){
            PanelCantidad.setVisible(true);
            txtCantidad.setText("1");
            txtCantidad.requestFocus();

        }
    }//GEN-LAST:event_tb_CPTBUSCARMouseClicked

    private void tb_CPTBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            PanelCantidad.setVisible(true);
            txtCantidad.setText("1");
            txtCantidad.requestFocus();
        }
    }//GEN-LAST:event_tb_CPTBUSCARKeyPressed

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            CARGAR_CPT();
            SUMA();
        }
        
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void btnBuscarCPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarCPTKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            CPT.setVisible(true);
        }
    }//GEN-LAST:event_btnBuscarCPTKeyPressed

    private void tb_ReporteDiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioMouseClicked
//        int fila=tb_ReporteDiario.getSelectedRow();
//        if(evt.getClickCount()==1){
//            lblCodigoImprimir.setText(String.valueOf(tb_ReporteDiario.getValueAt(fila, 12)));
//            lblVisibleImprimir.setText(String.valueOf(tb_ReporteDiario.getValueAt(fila, 13)));
//            lblFpReim.setText(String.valueOf(tb_ReporteDiario.getValueAt(fila, 2)));
//            btnImprimir.setEnabled(true);
//            panelIMprimir.setVisible(false);
//            btneliminar.setEnabled(true);
//        }
    }//GEN-LAST:event_tb_ReporteDiarioMouseClicked

    private void tb_ReporteDiarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiarioMousePressed

    private void tb_ReporteDiarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ReporteDiarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ReporteDiarioKeyPressed

    private void btnbuscar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar9ActionPerformed

    private void btnTerminiarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminiarVentaActionPerformed
        if(cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
            NUEVO_REGISTRO_DETALLE();
            ACTUALIZAR_CABECERA();
            nuevaV.reporteVenta(Integer.parseInt(lblID_CABECERA.getText()));
            panelAnular.setVisible(false);
            Nuevo(false);
            DetalleVenta(true);
            Limpiar();
            cbxTipoDocumento.setEnabled(true);
            cbxTipoDocumento.requestFocus();
        }else if(!cbxTipoDocumento.getSelectedItem().equals("BOLETA")){
            System.out.println("VAMO FACTURANDO");
        } 
    }//GEN-LAST:event_btnTerminiarVentaActionPerformed

    private void btnAnularVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularVentaActionPerformed
       
    }//GEN-LAST:event_btnAnularVentaActionPerformed

    private void txtEnterEscapeEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnterEscapeEnterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnterEscapeEnterActionPerformed

    private void txtEnterEscapeEnterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterEscapeEnterKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            btnTerminiarVenta.doClick();
        }
    }//GEN-LAST:event_txtEnterEscapeEnterKeyPressed

    private void panelAnularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAnularMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnularMouseClicked

    private void panelAnularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelAnularKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnularKeyPressed

    private void btnEliminarSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSiActionPerformed
        try{
            Caja_NuevaVenta ELIM = new Caja_NuevaVenta();
            ELIM.setID_DOCUMENTO(Integer.parseInt(lblID_CABECERA.getText()));
            if(ELIM.ELIMINAR_CABECERA()){
                Nuevo(false);
                DetalleVenta(true);
                Limpiar();
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
    private javax.swing.JDialog CLIENTES;
    private javax.swing.JDialog CPT;
    public static javax.swing.JDialog ErrorExistente;
    private javax.swing.JLabel Mensaje4;
    private javax.swing.JLabel Mensaje5;
    private javax.swing.JTabbedPane Paginas;
    private javax.swing.JPanel PanelCantidad;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnAnularVenta;
    private javax.swing.JButton btnBuscarCPT;
    public static javax.swing.JButton btnBuscarCPT1;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnCorrectoNo;
    private javax.swing.JButton btnCorrectoSi;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnEliminarNo;
    private javax.swing.JButton btnEliminarSi;
    public static javax.swing.JButton btnImprimir;
    public static javax.swing.JButton btnLista;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnTerminiarVenta;
    private javax.swing.JButton btnbuscar9;
    public static javax.swing.JButton btneliminar;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus3;
    private javax.swing.JComboBox cbxFormaPago;
    private javax.swing.JComboBox cbxTipoDocumento;
    private javax.swing.JButton eli5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lDoc;
    private javax.swing.JLabel lDoc1;
    private javax.swing.JLabel lDoc2;
    private javax.swing.JLabel lTipoDoc;
    private javax.swing.JLabel lTipoDoc1;
    private javax.swing.JLabel lblAd1;
    public static javax.swing.JLabel lblCliente;
    public static javax.swing.JLabel lblCliente1;
    public static javax.swing.JLabel lblCorrelativo;
    public static javax.swing.JLabel lblDocumento;
    public static javax.swing.JLabel lblDocumento1;
    public static javax.swing.JLabel lblDocumento2;
    public static javax.swing.JLabel lblDocumento3;
    private javax.swing.JLabel lblGrupo;
    public static javax.swing.JLabel lblIDCliente;
    private javax.swing.JLabel lblID_CABECERA;
    public static javax.swing.JLabel lblID_Documento;
    public static javax.swing.JLabel lblID_SESION;
    public static javax.swing.JLabel lblIdPreventa;
    private javax.swing.JLabel lblMontos;
    public static javax.swing.JLabel lblSESION;
    public static javax.swing.JLabel lblSerie;
    public static javax.swing.JLabel lblSerie_Correlativo;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    public static javax.swing.JLabel lblusu1;
    private javax.swing.JButton noeli5;
    private javax.swing.JPanel panelAnular;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCargarCPT;
    private javax.swing.JPanel panelEliminacion;
    private javax.swing.JPanel panelEliminar;
    private javax.swing.JPanel panelMensaje;
    private javax.swing.JPanel panelNumeros;
    private javax.swing.JPanel panelSinCPT;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel paneltablaHC;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTable tb_CPT;
    private javax.swing.JTable tb_CPTBUSCAR;
    private javax.swing.JTable tb_ReporteDiario;
    private javax.swing.JTable tbpreventas;
    private javax.swing.JTable tbpreventasFR;
    private javax.swing.JTextField txtBuscarCPT;
    private javax.swing.JTextField txtBuscarCliente;
    public static javax.swing.JTextField txtBusquedas;
    public static javax.swing.JTextField txtCPT;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtEnterEscapeEnter;
    private javax.swing.JTextField txtEnterEscapeEnter1;
    private javax.swing.JLabel txtIGV;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JLabel txtTotal;
    public static javax.swing.JTextField txtgrupo1;
    // End of variables declaration//GEN-END:variables
}
