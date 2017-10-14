/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Facturador;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Servicios.Conexion;
import modelo.Facturador.CuentasPorPagarComunicacionDeBaja;
import modelo.Facturador.CuentasPorPagarFacturasCabecera;
import modelo.Facturador.CuentasPorPagarNotaDeCreditoDebito;
import modelo.Facturador.CuentasPorPagarSfsRpta;
import modelo.Principal.Usuario;

/**
 *
 * @author PC02
 */
public class ComunicacionBaja extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();

    String barra = File.separator;
    String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
     CuentasPorPagarNotaDeCreditoDebito serie = new CuentasPorPagarNotaDeCreditoDebito();
     static CuentasPorPagarComunicacionDeBaja DT = new CuentasPorPagarComunicacionDeBaja();
    public ComunicacionBaja() {
        initComponents();
        c.conectar();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
        
        cbxDocumento.setBackground(Color.WHITE);
        panelSFS.setVisible(false);
        cargareliminar.setVisible(false);
        
        //SFS
//        agregarFacturas();
       
        tbFacturasRpta.setVisible(false);
        jScrollPane1.setVisible(false);
//        tbFacturacion.getTableHeader().setVisible(false);
//        tbFacturacion.setTableHeader(null);
     
        lblFechaEmision.setText(fechaActual());
       
        cbxBuscarDocumento.setBackground(Color.WHITE);
        
        BUSCAR_FACTURA_BOLETA.setLocationRelativeTo(null);
        BUSCAR_FACTURA_BOLETA.getContentPane().setBackground(Color.white);
        
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar("","1");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
        
        CuentasPorPagarComunicacionDeBaja serie=new CuentasPorPagarComunicacionDeBaja();
                    lblNroBaja.setText(serie.generarNroBaja());
        
        //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                dispose();

            }
        });
        
        LBL_ID_DOCUMENTO.setVisible(false);
    }

    public void agregarFacturas(){
         tbFacturasRpta.setModel(new DefaultTableModel());
         
//        TableColumnModel modCol = tbFacturasRpta.getColumnModel();
//        int cl=modCol.getColumnCount();
//        for(int i=0;i<cl;i++){
//            modCol.removeColumn(modCol.getColumn(0));
//            }
        DefaultTableModel m;
        
        File ruta = new File("C:\\sunat_archivos\\sfs\\RPTA");
        //        System.out.println(ruta.getAbsolutePath());
        String[] nombres_archivos = ruta.list();
        m = (DefaultTableModel) tbFacturasRpta.getModel();
        m.addColumn("Tipo",nombres_archivos);
        CuentasPorPagarSfsRpta rpta = new CuentasPorPagarSfsRpta();
        rpta.mantenimientoCuentasPorPagarSfsRptaNotas("E");
        rpta.mantenimientoCuentasPorPagarSfsRptaNotas("R");
        for (int i = 0; i < tbFacturasRpta.getRowCount(); i++){
            rpta.setNombre(String.valueOf(tbFacturasRpta.getValueAt(i, 0)));
            rpta.mantenimientoCuentasPorPagarSfsRptaNotas("I");
        }
    }
    public void limpiar(){
       
        CuentasPorPagarComunicacionDeBaja serie=new CuentasPorPagarComunicacionDeBaja();
                    lblNroBaja.setText(serie.generarNroBaja());
                    
        cbxDocumento.setSelectedIndex(0);
        //CREDITO
        lblIdFactura.setText("");
        txtDescripcion.setText("");
        lblFechaDocumento.setText("");
        txtSerie.setText("");
        lblNroCorrelativo.setText("");
      
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar("","1");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
    }
    
     public void CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(String buscar,String tipo){
        try {
            String consulta="";
             String titulos[]={"ID","Fecha","DOCUMENTO","Serie","Número","Tipo Doc"
                     , "N° Documento","Apellidos y Nombres","Correo Electrónico","Desc. GLobal",
                "Sum Otros Cargos","Total Descuentos","Valor VFravada","Valor VInafectas","Valor Exoneradas",
             "Sumatoria IGV","Sumatoria ISC","Sum Otros Atributos","Importe Total"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[20];
            Usuario obj=new Usuario();
//            consulta="exec sp_CUENTAS_POR_PAGAR_LISTAR_SFS_RPTA_72h_LISTAR ?,?";
            consulta="exec SP_CUENTAS_POR_PAGAR_FACTURA_BOLETA_72h_listar ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, tipo);
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
            fila[12]=r.getString(13);
            fila[13]=r.getString(14);
            fila[14]=r.getString(15);
            fila[15]=r.getString(16);
            fila[16]=r.getString(17);
            fila[17]=r.getString(18);
            fila[18]=r.getString(19);
                m.addRow(fila);
                c++;
            }
            tb_Factura_Boleta.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Factura_Boleta.setRowSorter(elQueOrdena);
            this.tb_Factura_Boleta.setModel(m);
    } catch (Exception e) {
    }
    }
    
    public void CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato(){
    tb_Factura_Boleta.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Factura_Boleta.getColumnModel().getColumn(2).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(3).setPreferredWidth(100);
    tb_Factura_Boleta.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(5).setPreferredWidth(110);
    tb_Factura_Boleta.getColumnModel().getColumn(6).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(7).setPreferredWidth(160);
    tb_Factura_Boleta.getColumnModel().getColumn(8).setPreferredWidth(160);
    //Ocultar    
    tb_Factura_Boleta.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(9).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(9).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(10).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(11).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(11).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(12).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(12).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(13).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(14).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(14).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(15).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(15).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(16).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(16).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(17).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(17).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(18).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(18).setMaxWidth(0);
    
    }
    
    public String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.format(now);
    }
    
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel22 = new javax.swing.JPanel();
        Serie = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        BUSCAR_FACTURA_BOLETA = new javax.swing.JDialog();
        jpanel = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();
        btnGuardar2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Factura_Boleta = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            cbxBuscarDocumento = new javax.swing.JComboBox();
            jLabel68 = new javax.swing.JLabel();
            jLabel69 = new javax.swing.JLabel();
            lblIdFactura = new javax.swing.JLabel();
            panelCPT50 = new javax.swing.JPanel();
            txtBuscarDocumento = new javax.swing.JTextField();
            panelSFS = new javax.swing.JPanel();
            LBL_ID_DOCUMENTO = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            tbFacturasRpta = new javax.swing.JTable();
            jPanel2 = new javax.swing.JPanel();
            jPanel32 = new javax.swing.JPanel();
            jLabel26 = new javax.swing.JLabel();
            panelCPT13 = new javax.swing.JPanel();
            txtDescripcion = new javax.swing.JTextField();
            jPanel9 = new javax.swing.JPanel();
            jLabel8 = new javax.swing.JLabel();
            txtSerie = new javax.swing.JLabel();
            txtSerie1 = new javax.swing.JLabel();
            lblNroCorrelativo = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            jLabel6 = new javax.swing.JLabel();
            lblNroBaja = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            jLabel5 = new javax.swing.JLabel();
            lblFechaDocumento = new javax.swing.JLabel();
            jPanel10 = new javax.swing.JPanel();
            cbxDocumento = new javax.swing.JComboBox();
            jLabel7 = new javax.swing.JLabel();
            jPanel7 = new javax.swing.JPanel();
            lblFechaEmision = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            cargareliminar = new javax.swing.JPanel();
            Mensaje = new javax.swing.JLabel();

            jPanel22.setBackground(new java.awt.Color(41, 127, 184));

            javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
            jPanel22.setLayout(jPanel22Layout);
            jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 19, Short.MAX_VALUE)
            );
            jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jMenuItem1.setText("jMenuItem1");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            Serie.add(jMenuItem1);

            jMenuItem2.setText("jMenuItem2");
            Serie.add(jMenuItem2);

            BUSCAR_FACTURA_BOLETA.setMinimumSize(new java.awt.Dimension(784, 561));

            jpanel.setBackground(new java.awt.Color(41, 127, 184));

            titulo5.setBackground(new java.awt.Color(0, 102, 102));
            titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
            titulo5.setForeground(new java.awt.Color(255, 255, 255));
            titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titulo5.setText("Factura - Boleta");
            titulo5.setToolTipText("");
            titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            lblEstado.setText("jLabel70");

            jButton1.setBackground(new java.awt.Color(204, 0, 51));
            jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jButton1.setForeground(new java.awt.Color(255, 255, 255));
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Cancel-22.png"))); // NOI18N
            jButton1.setText("Salir");
            jButton1.setContentAreaFilled(false);
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
            jpanel.setLayout(jpanelLayout);
            jpanelLayout.setHorizontalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(titulo5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
                            .addComponent(lblEstado)
                            .addGap(94, 94, 94))
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addGap(0, 667, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addContainerGap())))
            );
            jpanelLayout.setVerticalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo5)
                        .addComponent(lblEstado))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(27, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout BUSCAR_FACTURA_BOLETALayout = new javax.swing.GroupLayout(BUSCAR_FACTURA_BOLETA.getContentPane());
            BUSCAR_FACTURA_BOLETA.getContentPane().setLayout(BUSCAR_FACTURA_BOLETALayout);
            BUSCAR_FACTURA_BOLETALayout.setHorizontalGroup(
                BUSCAR_FACTURA_BOLETALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            BUSCAR_FACTURA_BOLETALayout.setVerticalGroup(
                BUSCAR_FACTURA_BOLETALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BUSCAR_FACTURA_BOLETALayout.createSequentialGroup()
                    .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(450, Short.MAX_VALUE))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(41, 127, 184));
            jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("<html><span style=\"font-size:'30px'\">Comunicación de Baja - </span>Cuentas por Cobrar</html>");

            btnGuardar1.setBackground(new java.awt.Color(102, 0, 102));
            btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Documento-32.png"))); // NOI18N
            btnGuardar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            btnGuardar1.setBorderPainted(false);
            btnGuardar1.setContentAreaFilled(false);
            btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar1.setDefaultCapable(false);
            btnGuardar1.setFocusPainted(false);
            btnGuardar1.setFocusable(false);
            btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardar1ActionPerformed(evt);
                }
            });

            btnGuardar2.setBackground(new java.awt.Color(102, 0, 102));
            btnGuardar2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnGuardar2.setForeground(new java.awt.Color(255, 255, 255));
            btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Basura-32.png"))); // NOI18N
            btnGuardar2.setText("Dar de Baja el Documento");
            btnGuardar2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            btnGuardar2.setBorderPainted(false);
            btnGuardar2.setContentAreaFilled(false);
            btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar2.setDefaultCapable(false);
            btnGuardar2.setFocusPainted(false);
            btnGuardar2.setFocusable(false);
            btnGuardar2.setIconTextGap(30);
            btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardar2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnGuardar2)))
                    .addContainerGap(727, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tb_Factura_Boleta.setModel(new javax.swing.table.DefaultTableModel(
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
            tb_Factura_Boleta.setRowHeight(25);
            tb_Factura_Boleta.setSelectionBackground(new java.awt.Color(102, 102, 102));
            tb_Factura_Boleta.getTableHeader().setReorderingAllowed(false);
            tb_Factura_Boleta.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_Factura_BoletaMouseClicked(evt);
                }
            });
            tb_Factura_Boleta.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_Factura_BoletaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tb_Factura_BoletaKeyReleased(evt);
                }
            });
            jScrollPane2.setViewportView(tb_Factura_Boleta);

            cbxBuscarDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            cbxBuscarDocumento.setForeground(new java.awt.Color(102, 102, 102));
            cbxBuscarDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "01 FACTURA", "03 BOLETA" }));
            cbxBuscarDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxBuscarDocumento.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    cbxBuscarDocumentoItemStateChanged(evt);
                }
            });
            cbxBuscarDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxBuscarDocumentoActionPerformed(evt);
                }
            });

            jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel68.setText("Documento");

            jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel69.setText("Nro de Documento ");

            panelCPT50.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtBuscarDocumento.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtBuscarDocumento.setForeground(new java.awt.Color(51, 51, 51));
            txtBuscarDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtBuscarDocumento.setBorder(null);
            txtBuscarDocumento.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscarDocumentoCaretUpdate(evt);
                }
            });
            txtBuscarDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscarDocumentoActionPerformed(evt);
                }
            });
            txtBuscarDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtBuscarDocumentoKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPT50Layout = new javax.swing.GroupLayout(panelCPT50);
            panelCPT50.setLayout(panelCPT50Layout);
            panelCPT50Layout.setHorizontalGroup(
                panelCPT50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtBuscarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            );
            panelCPT50Layout.setVerticalGroup(
                panelCPT50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtBuscarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout panelSFSLayout = new javax.swing.GroupLayout(panelSFS);
            panelSFS.setLayout(panelSFSLayout);
            panelSFSLayout.setHorizontalGroup(
                panelSFSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE)
            );
            panelSFSLayout.setVerticalGroup(
                panelSFSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 40, Short.MAX_VALUE)
            );

            LBL_ID_DOCUMENTO.setText("jLabel2");

            tbFacturasRpta.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {},
                    {},
                    {},
                    {}
                },
                new String [] {

                }
            ));
            jScrollPane1.setViewportView(tbFacturasRpta);

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            jPanel32.setBackground(new java.awt.Color(255, 255, 255));
            jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));

            jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel26.setForeground(new java.awt.Color(51, 51, 51));
            jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel26.setText("Descripción de motivo o sustento");

            panelCPT13.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtDescripcion.setForeground(new java.awt.Color(51, 51, 51));
            txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtDescripcion.setBorder(null);
            txtDescripcion.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtDescripcionCaretUpdate(evt);
                }
            });
            txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtDescripcionActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT13Layout = new javax.swing.GroupLayout(panelCPT13);
            panelCPT13.setLayout(panelCPT13Layout);
            panelCPT13Layout.setHorizontalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT13Layout.setVerticalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
            jPanel32.setLayout(jPanel32Layout);
            jPanel32Layout.setHorizontalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel32Layout.setVerticalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel26)
                    .addGap(3, 3, 3)
                    .addComponent(panelCPT13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));
            jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
            jPanel9.setDoubleBuffered(false);
            jPanel9.setFocusable(false);

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(51, 51, 51));
            jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel8.setText("Nro Documento Baja");

            txtSerie.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtSerie.setForeground(new java.awt.Color(51, 51, 51));
            txtSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            txtSerie1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
            txtSerie1.setForeground(new java.awt.Color(51, 51, 51));
            txtSerie1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            txtSerie1.setText("-");

            lblNroCorrelativo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblNroCorrelativo.setForeground(new java.awt.Color(51, 51, 51));
            lblNroCorrelativo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(txtSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSerie1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSerie1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSerie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(4, 4, 4))
            );

            jPanel8.setBackground(new java.awt.Color(255, 255, 255));
            jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
            jPanel8.setDoubleBuffered(false);
            jPanel8.setFocusable(false);

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(51, 51, 51));
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("Nro Baja");

            lblNroBaja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblNroBaja.setForeground(new java.awt.Color(102, 102, 102));
            lblNroBaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(lblNroBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblNroBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );

            jPanel6.setBackground(new java.awt.Color(255, 255, 255));
            jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
            jPanel6.setDoubleBuffered(false);
            jPanel6.setFocusable(false);

            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(51, 51, 51));
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setText("Fecha Generado el Documento");

            lblFechaDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblFechaDocumento.setForeground(new java.awt.Color(102, 102, 102));
            lblFechaDocumento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addComponent(lblFechaDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblFechaDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );

            jPanel10.setBackground(new java.awt.Color(255, 255, 255));
            jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));

            cbxDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            cbxDocumento.setForeground(new java.awt.Color(102, 102, 102));
            cbxDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 FACTURA", "03 BOLETA" }));
            cbxDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxDocumento.setEnabled(false);
            cbxDocumento.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    cbxDocumentoItemStateChanged(evt);
                }
            });
            cbxDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxDocumentoActionPerformed(evt);
                }
            });

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(51, 51, 51));
            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setText("Tipo de Documento");

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                        .addComponent(cbxDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel7.setBackground(new java.awt.Color(255, 255, 255));
            jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));

            lblFechaEmision.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblFechaEmision.setForeground(new java.awt.Color(102, 102, 102));
            lblFechaEmision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFechaEmision.setText("2017-05-30");

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(51, 51, 51));
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText("Fecha de Comunicacion de Baja");

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblFechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblFechaEmision)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            lblUsu.setForeground(new java.awt.Color(240, 240, 240));
            lblUsu.setText("KARINA");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(12, 12, 12)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            cargareliminar.setBackground(new java.awt.Color(255, 153, 51));

            Mensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            Mensaje.setForeground(new java.awt.Color(255, 255, 255));
            Mensaje.setText("Desea Actualizar el Registro ?");

            javax.swing.GroupLayout cargareliminarLayout = new javax.swing.GroupLayout(cargareliminar);
            cargareliminar.setLayout(cargareliminarLayout);
            cargareliminarLayout.setHorizontalGroup(
                cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cargareliminarLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(Mensaje)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            cargareliminarLayout.setVerticalGroup(
                cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cargareliminarLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(Mensaje)
                    .addContainerGap(16, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel69)
                    .addGap(27, 27, 27)
                    .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(80, 80, 80)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panelCPT50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(LBL_ID_DOCUMENTO)
                    .addGap(60, 60, 60)
                    .addComponent(panelSFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(jScrollPane2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelSFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LBL_ID_DOCUMENTO)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69)
                                    .addComponent(jLabel68))
                                .addComponent(panelCPT50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(11, 11, 11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void cbxDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocumentoActionPerformed

    private void cbxDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDocumentoItemStateChanged
//        if(cbxDocumento.getSelectedIndex()==0){
//            cbxSerie.setSelectedItem("F001");
//            lblNroCorrelativo.setText("00000001");
//        } else
//        if(cbxDocumento.getSelectedIndex()==1){
//            cbxSerie.setSelectedItem("B001");
//            lblNroCorrelativo.setText("00000002");
//        }    
    }//GEN-LAST:event_cbxDocumentoItemStateChanged

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        limpiar();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtDescripcionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcionCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionCaretUpdate

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void cbxBuscarDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoItemStateChanged

    private void cbxBuscarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoActionPerformed

    private void txtBuscarDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoCaretUpdate
        if(txtBuscarDocumento.getText().equalsIgnoreCase("")){
//        agregarFacturas();
        }
        if(cbxBuscarDocumento.getSelectedIndex()==0){
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(txtBuscarDocumento.getText(), "2");
        }else if(cbxBuscarDocumento.getSelectedIndex()==1){
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(txtBuscarDocumento.getText(), "3");
        }if(cbxBuscarDocumento.getSelectedIndex()==2){
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(txtBuscarDocumento.getText(), "4");
        }
        
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
        
    }//GEN-LAST:event_txtBuscarDocumentoCaretUpdate

    private void txtBuscarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumentoActionPerformed

    private void txtBuscarDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumentoKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BUSCAR_FACTURA_BOLETA.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_Factura_BoletaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_BoletaKeyPressed

    }//GEN-LAST:event_tb_Factura_BoletaKeyPressed

    private void tb_Factura_BoletaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_BoletaKeyReleased
      if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            
                int filaselec=tb_Factura_Boleta.getSelectedRow();
                lblIdFactura.setText(tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
                    lblFechaDocumento.setText(tb_Factura_Boleta.getValueAt(filaselec, 1).toString());
                    cbxDocumento.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 2).toString());     
                    txtSerie.setText(tb_Factura_Boleta.getValueAt(filaselec, 3).toString());
                    lblNroCorrelativo.setText(tb_Factura_Boleta.getValueAt(filaselec, 4).toString());
                            
                    int CPF_ID = Integer.parseInt(String.valueOf(tb_Factura_Boleta.getValueAt(filaselec, 0)));
                    MOSTRAR_ID_DOCUMENTO(CPF_ID);
        }
    }//GEN-LAST:event_tb_Factura_BoletaKeyReleased

    private void tb_Factura_BoletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_BoletaMouseClicked
            int filaselec=tb_Factura_Boleta.getSelectedRow();
            lblIdFactura.setText(tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
                    lblFechaDocumento.setText(tb_Factura_Boleta.getValueAt(filaselec, 1).toString());
                    cbxDocumento.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 2).toString());     
                    txtSerie.setText(tb_Factura_Boleta.getValueAt(filaselec, 3).toString());
                    lblNroCorrelativo.setText(tb_Factura_Boleta.getValueAt(filaselec, 4).toString());
                   
                    int CPF_ID = Integer.parseInt(String.valueOf(tb_Factura_Boleta.getValueAt(filaselec, 0)));
                    MOSTRAR_ID_DOCUMENTO(CPF_ID);
    }//GEN-LAST:event_tb_Factura_BoletaMouseClicked

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        int estado=0; 
        String correlativo="";
       Usuario cabecera = new Usuario();
         if(txtDescripcion.getText().equals("")){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Describa el Motivo o Sustento de la Comunicación de Baja");
         }
         else if(!lblIdFactura.getText().equals("")){
                CuentasPorPagarComunicacionDeBaja comunicacion = new CuentasPorPagarComunicacionDeBaja();
                    comunicacion.setIdFactura(Integer.parseInt(lblIdFactura.getText()));
                    comunicacion.setDescripcion(txtDescripcion.getText());
                   CuentasPorPagarComunicacionDeBaja serie=new CuentasPorPagarComunicacionDeBaja();
                    lblNroBaja.setText(serie.generarNroBaja());
                    comunicacion.setNumero(lblNroBaja.getText());
                    
                    comunicacion.setCod_usu(cabecera.codigoValidacion(lblUsu.getText()));
            if(comunicacion.mantenimientoCuentasPorPagarComunicacionBaja()){
                CuentasPorPagarFacturasCabecera ruc1=new CuentasPorPagarFacturasCabecera();
                String archivo = ruc1.factura_ruc() + "-" + 
               "RA" + "-" +
               lblFechaEmision.getText().toString().substring(0, 4)+ 
               lblFechaEmision.getText().toString().substring(5, 7)+
               lblFechaEmision.getText().toString().substring(8, 10)+"-" + 
                lblNroBaja.getText() + ".CBA";
//        File crea_ubicacion = new File(ubicacion);
                File crea_archivo = new File(archivo);
                if(lblIdFactura.getText().equals("")){
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,91,70)); 
                    Mensaje.setText("Ocurrió un error, No se encontró el identificador");
                } else {
                    try {
                        if(crea_archivo.exists()){
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(255,91,70)); 
                            Mensaje.setText("Ocurrió un error, El registro ya existe");
                        } else {
                        Formatter crea = new Formatter(ubicacion+archivo);
                        crea.format(lblFechaDocumento.getText()+"|"+
                            lblFechaEmision.getText()+"|"+
                            String.valueOf(cbxDocumento.getSelectedItem().toString().charAt(0)) + 
                        String.valueOf(cbxDocumento.getSelectedItem().toString().charAt(1))+"|"+
                        txtSerie.getText() + "-" + 
                        lblNroCorrelativo.getText() +"|"+
                        txtDescripcion.getText()); 
                        crea.close();
                        estado=1;
                        }   
                    } catch (Exception e) {
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(255,91,70)); 
                            Mensaje.setText("Ocurrió un error, Verifique");
            }
        }
               
            }
                if(estado==1){
                    MODIFICAR_DATOS_CAJA();
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("La Comunicación de Baja fue generada de forma correcta");
                    CuentasPorPagarComunicacionDeBaja est=new CuentasPorPagarComunicacionDeBaja();
                    est.CuentasPorPagarFacturaEstado(Integer.parseInt(lblIdFactura.getText()),"3");
                    limpiar();
                }
        }
        else{
                cargareliminar.setVisible(true);
                cargareliminar.setBackground(new Color(255,91,70)); 
                Mensaje.setText("Ocurrió un error, Verifique");

        }
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    public void MODIFICAR_DATOS_CAJA(){
        
        try {
         
        CuentasPorPagarComunicacionDeBaja MP = new CuentasPorPagarComunicacionDeBaja();
        
        MP.setID_DOCUMENTO(Integer.parseInt(LBL_ID_DOCUMENTO.getText()));
        MP.setNumero(lblNroBaja.getText());
        
        MP.COMUNICACION_DE_BAJA_MODIFICAR_CAJA();
        
        } catch (Exception e) {
            System.out.println("error modificar DATOS CAJA" + e.getMessage());
        }
    }
    
     public void mostrarFacturacionDetalle( JTable table){
        
        try {
            int filaselec=tb_Factura_Boleta.getSelectedRow();

        
          String consulta="";
            table.setModel(new DefaultTableModel());
            String titulos[]={"Codigo CPT","Descripción","Valor U", "Cantidad",
                "Precio Venta","IGV","Descuento Total","Total","Codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];

            Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_FACTURA_DETALLE_listar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
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
            table.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            table.setRowSorter(elQueOrdena);
        
            
            
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
     
     public void formatoFacturacionDetalle( JTable table){
         table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(700);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.getColumnModel().getColumn(7).setPreferredWidth(60);
         table.getColumnModel().getColumn(8).setMinWidth(0);
            table.getColumnModel().getColumn(8).setMaxWidth(0);
     }
     
     public void MOSTRAR_ID_DOCUMENTO(int cod){
        String consulta="";
        try {
            consulta="EXEC CUENTAS_POR_PAGAR_COMUNICACION_DE_BAJA_ID_DOCUMENTO ?";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
            cmd.setInt(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                LBL_ID_DOCUMENTO.setText(r.getString(1));
               
            }
            
        } catch (Exception e) {
            System.out.println("Error carga cod cabecera: " + e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ComunicacionBaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BUSCAR_FACTURA_BOLETA;
    private javax.swing.JLabel LBL_ID_DOCUMENTO;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JPopupMenu Serie;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxBuscarDocumento;
    private javax.swing.JComboBox cbxDocumento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaDocumento;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblIdFactura;
    private javax.swing.JLabel lblNroBaja;
    public static javax.swing.JLabel lblNroCorrelativo;
    private javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCPT13;
    private javax.swing.JPanel panelCPT50;
    private javax.swing.JPanel panelSFS;
    private javax.swing.JTable tbFacturasRpta;
    private javax.swing.JTable tb_Factura_Boleta;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtBuscarDocumento;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JLabel txtSerie;
    public static javax.swing.JLabel txtSerie1;
    // End of variables declaration//GEN-END:variables
}
