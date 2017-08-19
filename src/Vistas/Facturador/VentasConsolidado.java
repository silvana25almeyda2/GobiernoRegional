/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Facturador;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Servicios.Conexion;

/**
 *
 * @author MYS1
 */
public class VentasConsolidado extends javax.swing.JFrame {
    DefaultTableModel m;
    Conexion cnn = new Conexion();
    static java.sql.Connection conexion=null;
    Conexion c=new Conexion();
    public static boolean Facturado= false;
    public VentasConsolidado() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        txtDni.requestFocus();
        pnlVentas.setVisible(false);
        cbxActoMedico.setBackground(Color.white);
        cbxActoMedico.setBackground(Color.white);
        conexion = c.conectar();
        
        txtDni.setDocument(limitDNI);
        cbxActoMedico.setVisible(false);
        lblMensajeActoMedico.setVisible(false);
        lblIdCabecera.setVisible(false);
        lblCantidadActoMedico.setVisible(false);
        btnAM.setVisible(false);
        btnFacturarPorDocumento.setVisible(false);
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
        });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void buscarVentas(){
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        if (!txtDni.getText().equals("")){
            cabecera1.ventasConsolidadoCabecera(tbCabecera,txtActoMedico.getText());
//            lblApellidos.setText(String.valueOf(tbCabecera.getValueAt(0, 5)));
//            lblHC.setText(String.valueOf(tbCabecera.getValueAt(0, 4)));
            lblDNI.setText(String.valueOf(tbCabecera.getValueAt(0, 3)));
            if(tbCabecera.getRowCount()==0){
            lblIdCabecera.setText("No hay registro por facturar del acto médico " + txtActoMedico.getText());
            lblIdCabecera.setVisible(true);
            limpiparTabla(tbRayos);
            limpiparTabla(tbFarmacia);
            limpiparTabla(tbEcografias);
            limpiparTabla(tbLaboratorio);
            limpiparTabla(tbProcedimientos);
        } else {
            lblIdCabecera.setVisible(false);
        }
            if(tbCabecera.getRowCount()!=0){
                pnlVentas.setVisible(true);
                btnFacturarPorDocumento.setVisible(true);
                int fila = tbCabecera.getSelectedRow();
                tbCabecera.getSelectionModel().setSelectionInterval (0,0) ;
                tbCabecera.requestFocus();
                cabecera1.ventasConsolidadoDetalles(tbProcedimientos,String.valueOf(tbCabecera.getValueAt(0, 14)),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,String.valueOf(tbCabecera.getValueAt(0, 14)),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,String.valueOf(tbCabecera.getValueAt(0, 14)),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,String.valueOf(tbCabecera.getValueAt(0, 14)),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,String.valueOf(tbCabecera.getValueAt(0, 14)),"RX");
                lblIdCabecera.setText(String.valueOf(tbCabecera.getValueAt(0, 14)));
                btnFacturarEcografias.setEnabled(false);
                btnFacturarFarmacia.setEnabled(false);
                btnFacturarLaboratorio.setEnabled(false);
                btnFacturarProcedimientos.setEnabled(false);
                btnFacturarRayos.setEnabled(false);
            } else {
                pnlVentas.setVisible(false);
                btnFacturarPorDocumento.setVisible(false);
            }
        }
        if (txtDni.getText().length()==0){
            pnlVentas.setVisible(false);
                btnFacturarPorDocumento.setVisible(false);
        }     
    }
    
    public static void listarActoMedico(String dni){
        try {
            Statement sta=conexion.createStatement();
            ResultSet rs=sta.executeQuery("EXEC CUENTAS_POR_PAGAR_LISTAR_ACTO_MEDICO '"+dni+"'");
            VentasConsolidado.cbxActoMedico.removeAllItems();
            while(rs.next()){
                VentasConsolidado.cbxActoMedico.addItem(rs.getInt("NUM_ACTOMEDICO"));
            }
        } catch (SQLException e) {
                System.out.println("Error: listarActoMedico:" + e.getMessage());
        }
        }
    
         public void actualizarEstadoFacturacion(JTable tabla){
            CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
            int fila = tabla.getSelectedRow();
            if(cabecera1.actualizarEstadoFacturacion(String.valueOf(tabla.getValueAt(fila,11)),"F")){

//               cabecera1.listarPorFacturar(Facturador.tbFacturacion,txtActoMedico.getText());

               cabecera1.listarPorFacturar(Facturador.tbFacturacion,lblDNI.getText());

               cabecera1.ventasConsolidadoDetalles(tbProcedimientos,lblIdCabecera.getText(),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,lblIdCabecera.getText(),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,lblIdCabecera.getText(),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,lblIdCabecera.getText(),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,lblIdCabecera.getText(),"RX");

//                cabecera1.calcularPrecioVenta(txtActoMedico.getText());
                cabecera1.calculoValorVenta(lblDNI.getText(), "5");
                cabecera1.calculoValorVenta(lblDNI.getText(), "T");
                cabecera1.calcularPrecioVenta(lblDNI.getText());
                Facturador.btnGuardar.doClick();
                Facturador.lblDNI.setText(lblDNI.getText());
            } else {
               JOptionPane.showMessageDialog(null,"No se puedo generar esta factura");
            }
        }
    
         public void limpiparTabla(JTable tabla){
             DefaultTableModel modelo1 = (DefaultTableModel)tabla.getModel(); 
            int b=tabla.getRowCount();
            for(int j=0;j<b;j++){
                        modelo1.removeRow(0);
            }
         }
         
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane9 = new javax.swing.JScrollPane();
        tb_Grupo8 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            tb_Grupo9 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                nomenclaturas = new javax.swing.JDialog();
                jPanel11 = new javax.swing.JPanel();
                jLabel21 = new javax.swing.JLabel();
                jPanel28 = new javax.swing.JPanel();
                txtBuscarCPT = new javax.swing.JTextField();
                btnBuscarPaciente3 = new javax.swing.JButton();
                jScrollPane6 = new javax.swing.JScrollPane();
                tb_CPTBUSCAR = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    formaDePago = new javax.swing.JDialog();
                    jPanel12 = new javax.swing.JPanel();
                    jLabel26 = new javax.swing.JLabel();
                    jPanel29 = new javax.swing.JPanel();
                    txtBuscarFormaDePago = new javax.swing.JTextField();
                    btnBuscarPaciente4 = new javax.swing.JButton();
                    jScrollPane7 = new javax.swing.JScrollPane();
                    tb_CPTBUSCAR1 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jPanel1 = new javax.swing.JPanel();
                        jLabel1 = new javax.swing.JLabel();
                        jPanel9 = new javax.swing.JPanel();
                        txtDni = new javax.swing.JTextField();
                        T3 = new javax.swing.JButton();
                        lbldetalle = new javax.swing.JLabel();
                        cbxActoMedico = new javax.swing.JComboBox();
                        lblMensajeActoMedico = new javax.swing.JLabel();
                        btnNuevo = new javax.swing.JButton();
                        lblCantidadActoMedico = new javax.swing.JLabel();
                        btnAM = new javax.swing.JButton();
                        pnlDatos = new javax.swing.JPanel();
                        lblApellidos = new javax.swing.JLabel();
                        jLabel3 = new javax.swing.JLabel();
                        jLabel5 = new javax.swing.JLabel();
                        lblDNI = new javax.swing.JLabel();
                        jLabel4 = new javax.swing.JLabel();
                        lblHC = new javax.swing.JLabel();
                        txtActoMedico = new javax.swing.JTextField();
                        lblIdCabecera = new javax.swing.JLabel();
                        btnFacturarPorDocumento = new javax.swing.JButton();
                        pnlVentas = new javax.swing.JPanel();
                        spDetalle = new javax.swing.JScrollPane();
                        tbCabecera1 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            spCabecera = new javax.swing.JScrollPane();
                            tbCabecera = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};

                                jScrollPane9.setBorder(null);

                                tb_Grupo8.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_Grupo8.setGridColor(new java.awt.Color(255, 255, 255));
                                tb_Grupo8.setRowHeight(25);
                                tb_Grupo8.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                tb_Grupo8.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_Grupo8MouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tb_Grupo8MousePressed(evt);
                                    }
                                });
                                tb_Grupo8.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_Grupo8KeyPressed(evt);
                                    }
                                });
                                jScrollPane9.setViewportView(tb_Grupo8);

                                tb_Grupo9.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_Grupo9.setGridColor(new java.awt.Color(255, 255, 255));
                                tb_Grupo9.setRowHeight(25);
                                tb_Grupo9.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                tb_Grupo9.getTableHeader().setReorderingAllowed(false);

                                nomenclaturas.setAlwaysOnTop(true);
                                nomenclaturas.setMinimumSize(new java.awt.Dimension(749, 338));
                                nomenclaturas.setResizable(false);

                                jPanel11.setBackground(new java.awt.Color(41, 127, 184));
                                jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                                jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel21.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel21.setText("CPT");

                                jPanel28.setBackground(new java.awt.Color(255, 255, 255));

                                txtBuscarCPT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                txtBuscarCPT.setForeground(new java.awt.Color(98, 98, 98));
                                txtBuscarCPT.setBorder(null);
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
                                        .addContainerGap(458, Short.MAX_VALUE))
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
                                        .addGap(353, 353, 353))
                                );

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

                                javax.swing.GroupLayout nomenclaturasLayout = new javax.swing.GroupLayout(nomenclaturas.getContentPane());
                                nomenclaturas.getContentPane().setLayout(nomenclaturasLayout);
                                nomenclaturasLayout.setHorizontalGroup(
                                    nomenclaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6)
                                );
                                nomenclaturasLayout.setVerticalGroup(
                                    nomenclaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(nomenclaturasLayout.createSequentialGroup()
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                );

                                formaDePago.setAlwaysOnTop(true);
                                formaDePago.setMinimumSize(new java.awt.Dimension(749, 338));
                                formaDePago.setResizable(false);

                                jPanel12.setBackground(new java.awt.Color(41, 127, 184));
                                jPanel12.setMinimumSize(new java.awt.Dimension(310, 441));

                                jLabel26.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel26.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel26.setText("Forma de Pago");

                                jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                                txtBuscarFormaDePago.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                txtBuscarFormaDePago.setForeground(new java.awt.Color(98, 98, 98));
                                txtBuscarFormaDePago.setBorder(null);
                                txtBuscarFormaDePago.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBuscarFormaDePagoCaretUpdate(evt);
                                    }
                                });
                                txtBuscarFormaDePago.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        txtBuscarFormaDePagoActionPerformed(evt);
                                    }
                                });
                                txtBuscarFormaDePago.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtBuscarFormaDePagoKeyPressed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                                jPanel29.setLayout(jPanel29Layout);
                                jPanel29Layout.setHorizontalGroup(
                                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtBuscarFormaDePago, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                );
                                jPanel29Layout.setVerticalGroup(
                                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtBuscarFormaDePago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                );

                                btnBuscarPaciente4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                btnBuscarPaciente4.setContentAreaFilled(false);
                                btnBuscarPaciente4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnBuscarPaciente4.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnBuscarPaciente4ActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                jPanel12.setLayout(jPanel12Layout);
                                jPanel12Layout.setHorizontalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel12Layout.setVerticalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(353, 353, 353))
                                );

                                jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
                                jScrollPane7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_CPTBUSCAR1.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_CPTBUSCAR1.setGridColor(new java.awt.Color(255, 255, 255));
                                tb_CPTBUSCAR1.setRowHeight(25);
                                tb_CPTBUSCAR1.setRowMargin(0);
                                tb_CPTBUSCAR1.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                tb_CPTBUSCAR1.getTableHeader().setReorderingAllowed(false);
                                tb_CPTBUSCAR1.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_CPTBUSCAR1MouseClicked(evt);
                                    }
                                });
                                tb_CPTBUSCAR1.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_CPTBUSCAR1KeyPressed(evt);
                                    }
                                });
                                jScrollPane7.setViewportView(tb_CPTBUSCAR1);

                                javax.swing.GroupLayout formaDePagoLayout = new javax.swing.GroupLayout(formaDePago.getContentPane());
                                formaDePago.getContentPane().setLayout(formaDePagoLayout);
                                formaDePagoLayout.setHorizontalGroup(
                                    formaDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                );
                                formaDePagoLayout.setVerticalGroup(
                                    formaDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formaDePagoLayout.createSequentialGroup()
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                );

                                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                                jPanel1.setBackground(new java.awt.Color(41, 127, 184));

                                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel1.setText("<html>Consolidado<span style=\"font-size:'15px'\"><br> Cuentas Por Pagar</br></span></html>");

                                jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                                txtDni.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                txtDni.setForeground(new java.awt.Color(51, 51, 51));
                                txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                txtDni.setBorder(null);
                                txtDni.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtDniCaretUpdate(evt);
                                    }
                                });
                                txtDni.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        txtDniActionPerformed(evt);
                                    }
                                });
                                txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtDniKeyPressed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                jPanel9.setLayout(jPanel9Layout);
                                jPanel9Layout.setHorizontalGroup(
                                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDni, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                );
                                jPanel9Layout.setVerticalGroup(
                                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                T3.setToolTipText("");
                                T3.setContentAreaFilled(false);
                                T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                T3.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        T3ActionPerformed(evt);
                                    }
                                });

                                lbldetalle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                lbldetalle.setText("Ingrese el DNI del paciente");

                                cbxActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                cbxActoMedico.setForeground(new java.awt.Color(51, 51, 51));
                                cbxActoMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Acto Médico" }));
                                cbxActoMedico.addItemListener(new java.awt.event.ItemListener() {
                                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                        cbxActoMedicoItemStateChanged(evt);
                                    }
                                });

                                lblMensajeActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblMensajeActoMedico.setForeground(new java.awt.Color(255, 255, 255));
                                lblMensajeActoMedico.setText("Seleccione el Acto Médico");

                                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
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

                                lblCantidadActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                lblCantidadActoMedico.setForeground(new java.awt.Color(51, 51, 51));
                                lblCantidadActoMedico.setText("1");

                                btnAM.setText("jButton1");
                                btnAM.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnAMActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                jPanel1.setLayout(jPanel1Layout);
                                jPanel1Layout.setHorizontalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbldetalle)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(lblMensajeActoMedico)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblCantidadActoMedico))
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                        .addComponent(cbxActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(btnAM)))
                                        .addContainerGap(23, Short.MAX_VALUE))
                                );
                                jPanel1Layout.setVerticalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbldetalle)
                                        .addGap(52, 52, 52)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblMensajeActoMedico)
                                            .addComponent(lblCantidadActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNuevo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAM)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                pnlDatos.setBackground(new java.awt.Color(43, 43, 43));

                                lblApellidos.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                                lblApellidos.setForeground(new java.awt.Color(255, 255, 255));
                                lblApellidos.setText("Búsqueda por DNI");

                                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel3.setText("Acto Médico");

                                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel5.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel5.setText("DNI");

                                lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblDNI.setForeground(new java.awt.Color(204, 204, 204));
                                lblDNI.setText("  ");

                                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel4.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel4.setText("Nº H.C.");

                                lblHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblHC.setForeground(new java.awt.Color(204, 204, 204));
                                lblHC.setText("  ");

                                txtActoMedico.setEditable(false);
                                txtActoMedico.setBackground(new java.awt.Color(43, 43, 43));
                                txtActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtActoMedico.setForeground(new java.awt.Color(51, 51, 51));
                                txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtActoMedico.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                txtActoMedico.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtActoMedicoCaretUpdate(evt);
                                    }
                                });
                                txtActoMedico.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        txtActoMedicoKeyPressed(evt);
                                    }
                                });

                                lblIdCabecera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblIdCabecera.setForeground(new java.awt.Color(255, 255, 255));
                                lblIdCabecera.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                                lblIdCabecera.setText("jLabel2");

                                btnFacturarPorDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                btnFacturarPorDocumento.setForeground(new java.awt.Color(255, 255, 255));
                                btnFacturarPorDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casilla de verificación marcada 2-32.png"))); // NOI18N
                                btnFacturarPorDocumento.setText("<html>Agregar Detalles<br>a Factura</html>");
                                btnFacturarPorDocumento.setContentAreaFilled(false);
                                btnFacturarPorDocumento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnFacturarPorDocumento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnFacturarPorDocumento.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnFacturarPorDocumento.setIconTextGap(15);
                                btnFacturarPorDocumento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                                btnFacturarPorDocumento.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnFacturarPorDocumentoActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
                                pnlDatos.setLayout(pnlDatosLayout);
                                pnlDatosLayout.setHorizontalGroup(
                                    pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatosLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel4))
                                                .addGap(105, 105, 105)
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                                    .addComponent(lblHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(77, 77, 77)
                                                .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblApellidos))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                                                .addComponent(btnFacturarPorDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(102, 102, 102))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                                                .addComponent(lblIdCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
                                );
                                pnlDatosLayout.setVerticalGroup(
                                    pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatosLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblApellidos)
                                            .addComponent(lblIdCabecera))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtActoMedico)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel5)
                                                    .addComponent(lblDNI))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lblHC)
                                                    .addComponent(jLabel4)))
                                            .addComponent(btnFacturarPorDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                spDetalle.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tbCabecera1.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbCabecera1.setGridColor(new java.awt.Color(255, 255, 255));
                                tbCabecera1.setRowHeight(25);
                                tbCabecera1.setSelectionBackground(new java.awt.Color(41, 127, 184));
                                tbCabecera1.getTableHeader().setReorderingAllowed(false);
                                tbCabecera1.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbCabecera1MouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbCabecera1MousePressed(evt);
                                    }
                                });
                                tbCabecera1.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbCabecera1KeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tbCabecera1KeyReleased(evt);
                                    }
                                });
                                spDetalle.setViewportView(tbCabecera1);

                                spCabecera.setBorder(null);

                                tbCabecera.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbCabecera.setGridColor(new java.awt.Color(255, 255, 255));
                                tbCabecera.setRowHeight(25);
                                tbCabecera.setSelectionBackground(new java.awt.Color(41, 127, 184));
                                tbCabecera.getTableHeader().setReorderingAllowed(false);
                                tbCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbCabeceraMouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbCabeceraMousePressed(evt);
                                    }
                                });
                                tbCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbCabeceraKeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tbCabeceraKeyReleased(evt);
                                    }
                                });
                                spCabecera.setViewportView(tbCabecera);

                                javax.swing.GroupLayout pnlVentasLayout = new javax.swing.GroupLayout(pnlVentas);
                                pnlVentas.setLayout(pnlVentasLayout);
                                pnlVentasLayout.setHorizontalGroup(
                                    pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                                    .addGroup(pnlVentasLayout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(spDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                                        .addContainerGap())
                                );
                                pnlVentasLayout.setVerticalGroup(
                                    pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlVentasLayout.createSequentialGroup()
                                        .addComponent(spCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
                                );

                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                getContentPane().setLayout(layout);
                                layout.setHorizontalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                );
                                layout.setVerticalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                pack();
                            }// </editor-fold>//GEN-END:initComponents

    private void txtDniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDniCaretUpdate
       
    }//GEN-LAST:event_txtDniCaretUpdate

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed
        
        try {
            CuentasPorPagarVentasConsolidadoCabecera cab = new CuentasPorPagarVentasConsolidadoCabecera();
            if(!txtDni.getText().equals("")){
                listarActoMedico(txtDni.getText());
                lblCantidadActoMedico.setText("("+String.valueOf(cbxActoMedico.getItemCount())+")");
                if(cbxActoMedico.getItemCount()!=0){
                    cbxActoMedico.setVisible(true);
                    lblCantidadActoMedico.setVisible(true);
                    lblMensajeActoMedico.setVisible(true);
                    jLabel3.setForeground(new Color(204,204,204));
                    jLabel4.setForeground(new Color(204,204,204));
                    jLabel5.setForeground(new Color(204,204,204));
                    lblDNI.setForeground(new Color(204,204,204));
                    lblHC.setForeground(new Color(204,204,204));
                    txtActoMedico.setForeground(new Color(204,204,204));
                    txtActoMedico.setVisible(true);
                    cab.datosHC(txtDni.getText());
                    txtDni.setEditable(false);
                } else {
                    cbxActoMedico.setVisible(false);
                    lblCantidadActoMedico.setVisible(false);
                    lblMensajeActoMedico.setVisible(false);
                    lblApellidos.setText("El paciente no tiene registros");
                    jLabel3.setForeground(Color.BLACK);
                    jLabel4.setForeground(Color.BLACK);
                    jLabel5.setForeground(Color.BLACK);
                    lblDNI.setForeground(Color.BLACK);
                    lblHC.setForeground(Color.BLACK);
                    txtActoMedico.setForeground(Color.BLACK);
                    txtActoMedico.setVisible(false);
                    pnlVentas.setVisible(false);
                    btnNuevo.doClick();
                }
            } else {
                cbxActoMedico.setVisible(false);
                    lblCantidadActoMedico.setVisible(false);
                    lblMensajeActoMedico.setVisible(false);
                    lblApellidos.setText("El paciente no tiene registros");
                    jLabel3.setForeground(Color.BLACK);
                    jLabel4.setForeground(Color.BLACK);
                    jLabel5.setForeground(Color.BLACK);
                    lblDNI.setForeground(Color.BLACK);
                    lblHC.setForeground(Color.BLACK);
                    txtActoMedico.setForeground(Color.BLACK);
                    txtActoMedico.setVisible(false);
                    pnlVentas.setVisible(false);
                    cbxActoMedico.removeAllItems();
                    txtDni.setEditable(true);
                    btnNuevo.doClick();
            }
        } catch (Exception e) {
            System.out.println("Error: T3 " + e.getMessage() );
        }
    }//GEN-LAST:event_T3ActionPerformed

    private void tbCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMouseClicked
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        int fila = tbCabecera.getSelectedRow();
        if(evt.getClickCount()==1){
                cabecera1.ventasConsolidadoDetalles(tbProcedimientos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,String.valueOf(tbCabecera.getValueAt(fila, 14)),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,String.valueOf(tbCabecera.getValueAt(fila, 14)),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,String.valueOf(tbCabecera.getValueAt(fila, 14)),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"RX");
                lblIdCabecera.setText(String.valueOf(tbCabecera.getValueAt(fila, 14)));
                btnFacturarEcografias.setEnabled(false);
                btnFacturarFarmacia.setEnabled(false);
                btnFacturarLaboratorio.setEnabled(false);
                btnFacturarProcedimientos.setEnabled(false);
                btnFacturarRayos.setEnabled(false);
        }
        if(evt.getClickCount()==2){
            btnFacturarPorDocumento.doClick();
            Facturador.btnGuardar.doClick();
            }
    }//GEN-LAST:event_tbCabeceraMouseClicked

    private void tbCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMousePressed

    }//GEN-LAST:event_tbCabeceraMousePressed

    private void tbCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabeceraKeyPressed

    }//GEN-LAST:event_tbCabeceraKeyPressed

    private void tb_Grupo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MouseClicked

    private void tb_Grupo8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MousePressed

    private void tb_Grupo8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8KeyPressed

    private void txtActoMedicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtActoMedicoCaretUpdate
        buscarVentas();
    }//GEN-LAST:event_txtActoMedicoCaretUpdate

    private void cbxActoMedicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActoMedicoItemStateChanged
        try {
            txtActoMedico.setText(cbxActoMedico.getSelectedItem().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cbxActoMedicoItemStateChanged

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        if(!txtDni.getText().equals("")){
            if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                    T3.doClick();
            }
            if(cbxActoMedico.isVisible()){
                if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                    cbxActoMedico.requestFocus();
                    cbxActoMedico.showPopup();
                }
            }
        } else {
            cbxActoMedico.setVisible(false);
                lblMensajeActoMedico.setVisible(false);
                lblApellidos.setText("El paciente no tiene registros");
                jLabel3.setForeground(Color.BLACK);
                jLabel4.setForeground(Color.BLACK);
                jLabel5.setForeground(Color.BLACK);
                lblDNI.setForeground(Color.BLACK);
                lblHC.setForeground(Color.BLACK);
                txtActoMedico.setForeground(Color.BLACK);
                txtActoMedico.setVisible(false);
                pnlVentas.setVisible(false);
        }
    }//GEN-LAST:event_txtDniKeyPressed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtActoMedicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtActoMedicoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                buscarVentas();
        }
    }//GEN-LAST:event_txtActoMedicoKeyPressed

    private void tbCabeceraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabeceraKeyReleased
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        int fila = tbCabecera.getSelectedRow();
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
                cabecera1.ventasConsolidadoDetalles(tbProcedimientos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,String.valueOf(tbCabecera.getValueAt(fila, 14)),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,String.valueOf(tbCabecera.getValueAt(fila, 14)),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,String.valueOf(tbCabecera.getValueAt(fila, 14)),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"RX");
        }
    }//GEN-LAST:event_tbCabeceraKeyReleased

    private void txtBuscarCPTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCPTCaretUpdate
        
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

//            CPTNomenclaturas();

        }
    }//GEN-LAST:event_tb_CPTBUSCARMouseClicked

    private void tb_CPTBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTBUSCARKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
//            CPTNomenclaturas();
        }
    }//GEN-LAST:event_tb_CPTBUSCARKeyPressed

    private void txtBuscarFormaDePagoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarFormaDePagoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFormaDePagoCaretUpdate

    private void txtBuscarFormaDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarFormaDePagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFormaDePagoActionPerformed

    private void txtBuscarFormaDePagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarFormaDePagoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFormaDePagoKeyPressed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

    private void tb_CPTBUSCAR1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTBUSCAR1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CPTBUSCAR1MouseClicked

    private void tb_CPTBUSCAR1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTBUSCAR1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_CPTBUSCAR1KeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtDni.setText("");
        cbxActoMedico.removeAllItems();
        txtActoMedico.setText("");
        lblDNI.setText("");
        lblHC.setText("");
        txtDni.setEditable(true);
        lblMensajeActoMedico.setVisible(false);
        cbxActoMedico.setVisible(false);
        txtDni.requestFocus();
        lblCantidadActoMedico.setVisible(false);
        lblIdCabecera.setVisible(false);
        lblIdCabecera.setText("");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnFacturarPorDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarPorDocumentoActionPerformed
        if(Facturado==false){
             Facturador fac=  new Facturador();
            fac.setVisible(true);
            Facturado= true;
        } else {
            Facturador.tbFacturacion.requestFocus();
            Facturador.btnGuardar.doClick();
        }
            CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
            cabecera1.actualizarestadoFacturacionPorDocumento(lblIdCabecera.getText());
            cabecera1.listarPorFacturar(Facturador.tbFacturacion,lblDNI.getText());
                    cabecera1.calculoValorVenta(lblDNI.getText(), "5");
                    cabecera1.calculoValorVenta(lblDNI.getText(), "T");
            cabecera1.ventasConsolidadoCabecera(tbCabecera,txtActoMedico.getText());
            Facturador.btnGuardar.doClick();
            pnlDetalle.setVisible(true);
            btnFacturarPorDocumento.setVisible(false);
            Facturador.lblDNI.setText(lblDNI.getText());
        if(tbCabecera.getRowCount()==0){
            lblIdCabecera.setText("No hay registro por facturar del acto médico " + txtActoMedico.getText());
            lblIdCabecera.setVisible(true);
            limpiparTabla(tbRayos);
            limpiparTabla(tbFarmacia);
            limpiparTabla(tbEcografias);
            limpiparTabla(tbLaboratorio);
            limpiparTabla(tbProcedimientos);
            listarActoMedico(txtDni.getText());
            lblCantidadActoMedico.setText("("+String.valueOf(cbxActoMedico.getItemCount())+")");
            btnAM.doClick();
        } else {
            lblIdCabecera.setVisible(false);
        }
    }//GEN-LAST:event_btnFacturarPorDocumentoActionPerformed

    private void btnAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAMActionPerformed
        if(cbxActoMedico.getItemCount()!=0){
            String actomedico = cbxActoMedico.getSelectedItem().toString();
            txtActoMedico.setText(actomedico);
        } else {
            btnNuevo.doClick();
        }
    }//GEN-LAST:event_btnAMActionPerformed

    private void tbCabecera1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabecera1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCabecera1MouseClicked

    private void tbCabecera1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabecera1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCabecera1MousePressed

    private void tbCabecera1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabecera1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCabecera1KeyPressed

    private void tbCabecera1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabecera1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCabecera1KeyReleased

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
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasConsolidado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton T3;
    private javax.swing.JButton btnAM;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnBuscarPaciente4;
    public static javax.swing.JButton btnFacturarPorDocumento;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JComboBox cbxActoMedico;
    private javax.swing.JDialog formaDePago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblApellidos;
    public static javax.swing.JLabel lblCantidadActoMedico;
    public static javax.swing.JLabel lblDNI;
    public static javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblIdCabecera;
    private javax.swing.JLabel lblMensajeActoMedico;
    private javax.swing.JLabel lbldetalle;
    private javax.swing.JDialog nomenclaturas;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlVentas;
    private javax.swing.JScrollPane spCabecera;
    private javax.swing.JScrollPane spCabecera1;
    private javax.swing.JScrollPane spDetalle;
    private javax.swing.JTable tbCabecera;
    private javax.swing.JTable tbCabecera1;
    private javax.swing.JTable tb_CPTBUSCAR;
    private javax.swing.JTable tb_CPTBUSCAR1;
    private javax.swing.JTable tb_Grupo8;
    private javax.swing.JTable tb_Grupo9;
    public static javax.swing.JTextField txtActoMedico;
    private javax.swing.JTextField txtBuscarCPT;
    private javax.swing.JTextField txtBuscarFormaDePago;
    public static javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
