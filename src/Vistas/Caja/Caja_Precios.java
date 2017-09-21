/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import Servicios.Conexion;
import Vistas.Principal.Principal;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import modelo.Caja.Caja_Tarifario;

/**
 *
 * @author Administrador
 */
public class Caja_Precios extends javax.swing.JFrame {
byte tg;
byte tgm;
ResultSet r;
Conexion c=new Conexion();
Connection ConexionS=c.conectar();
    /**
     * Creates new form Caja_Precios
     */
    public Caja_Precios() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(MAXIMIZED_BOTH);
        cargareliminar.setVisible(false);
        panelDetalle.setVisible(false);
        NivelSuperior.setLocationRelativeTo(null);//en el centro
        Items.setLocationRelativeTo(null);//en el centro
        jPanel2.setVisible(false);
        cbxFormaPago.setBackground(Color.WHITE);
        this.cbxFormaPago.setModel(CargarFP());
        Caja_Tarifario N = new Caja_Tarifario();
        N.LISTA_PRECIOS("",tb_Tarifas);
        Caja_Tarifario Np = new Caja_Tarifario();
        Np.LISTAR_PERMISOS(Principal.lblUsu.getText());
        HABILITAR(false);
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
    public void HABILITAR(boolean opcion){
        cbxFormaPago.setEnabled(opcion);
        btnBusquedaITEM.setEnabled(opcion);
        txtPrecio.setEditable(opcion);
    }
    public void LIMPIAR(){

        txtPrecio.setText("");
        txtITEM.setText("");
        lblID_ITEM.setText("");
    }
    
    public void CARGAR(){
        HABILITAR(false);
        int fila=tb_Tarifas.getSelectedRow();
        lblID_PRECIO.setText(String.valueOf(tb_Tarifas.getValueAt(fila, 0)));  
        cbxFormaPago.setSelectedItem(String.valueOf(tb_Tarifas.getValueAt(fila, 1))); 
        txtITEM.setText(String.valueOf(tb_Tarifas.getValueAt(fila, 3))); 
        txtPrecio.setText(String.valueOf(tb_Tarifas.getValueAt(fila, 4)));  
        lblID_ITEM.setText(String.valueOf(tb_Tarifas.getValueAt(fila, 6))); 
        jLabel17.setText(String.valueOf(tb_Tarifas.getValueAt(fila, 7))); 
        if(jLabel17.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                txtPrecio.setRequestFocusEnabled(true);
                lblVALORVENTA.setVisible(true);
                jPanel2.setVisible(true);
            }else if(!jLabel17.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                txtPrecio.setRequestFocusEnabled(true);
                lblVALORVENTA.setVisible(false);
                jPanel2.setVisible(true);
            }

        
    }
    
    public void NUEVO_REGISTRO(){
       if((txtPrecio.getText().equals(""))){
            cargareliminar.setVisible(true);        
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Debe completar los campos requeridos");
            eli.setVisible(true);
            noeli.setVisible(false);
            tgm=0;                 
        } else {
                Caja_Tarifario cno1 = new Caja_Tarifario();
                Caja_Tarifario cno2 = new Caja_Tarifario();
                String prov = cbxFormaPago.getSelectedItem().toString();
                String FP = cno2.DATOS_FOR_PAGO(prov);
                cno1.setID_FORMA_PAGO(Integer.parseInt(FP));
                cno1.setID_CPT(Integer.parseInt(lblID_ITEM.getText()));
                cno1.setPRECIO(Double.parseDouble(txtPrecio.getText()));
                cno1.setUsuario(lblusu.getText());
                    if(cno1.NUEVO_PRECIO()==true){
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(0,153,102)); 
                            Mensaje.setText("Datos Guardados de forma correcta");
                            eli.setText("OK");
                            eli.setVisible(true);
                            noeli.setVisible(false);
                            tgm=1;
                            btnguardar.setEnabled(false);
                            btneditar.setEnabled(false);
                            btneliminar.setEnabled(false);
                            panelDetalle.setVisible(false);
                            Caja_Tarifario A = new Caja_Tarifario();
                            A.LISTA_PRECIOS("",tb_Tarifas);
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
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

        NivelSuperior = new javax.swing.JDialog();
        jPanel145 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jPanel146 = new javax.swing.JPanel();
        btnAlertConsulta10 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        Items = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        txtBuscar2 = new javax.swing.JTextField();
        btnBuscarPaciente2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_ITEMS = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btneditar = new javax.swing.JButton();
            btnguardar = new javax.swing.JButton();
            btneliminar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            jPanel23 = new javax.swing.JPanel();
            buscartodo = new javax.swing.JTextField();
            btnBuscarPaciente = new javax.swing.JButton();
            lbldetalle = new javax.swing.JLabel();
            btnImprimir = new javax.swing.JButton();
            lblNivel = new javax.swing.JLabel();
            lblPermiso = new javax.swing.JLabel();
            lblEditar = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            jLabel33 = new javax.swing.JLabel();
            cargareliminar = new javax.swing.JPanel();
            Mensaje = new javax.swing.JLabel();
            eli = new javax.swing.JButton();
            noeli = new javax.swing.JButton();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Tarifas = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                panelDetalle = new javax.swing.JPanel();
                cbxFormaPago = new javax.swing.JComboBox();
                jLabel12 = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                panelCPT1 = new javax.swing.JPanel();
                txtITEM = new javax.swing.JTextField();
                btnBusquedaITEM = new javax.swing.JButton();
                lblID_ITEM = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                panelTUPA_COMPLETO1 = new javax.swing.JPanel();
                txtPrecio = new javax.swing.JTextField();
                lblVALORVENTA = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                lblID_PRECIO = new javax.swing.JLabel();

                NivelSuperior.setAlwaysOnTop(true);
                NivelSuperior.setMinimumSize(new java.awt.Dimension(430, 200));
                NivelSuperior.setUndecorated(true);
                NivelSuperior.setResizable(false);

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
                jLabel67.setText("<html>Necesita permisos de administrador <BR>Para realizar esta acción.</html>");

                javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
                jPanel145.setLayout(jPanel145Layout);
                jPanel145Layout.setHorizontalGroup(
                    jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(171, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel145Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                );
                jPanel145Layout.setVerticalGroup(
                    jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel64)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout NivelSuperiorLayout = new javax.swing.GroupLayout(NivelSuperior.getContentPane());
                NivelSuperior.getContentPane().setLayout(NivelSuperiorLayout);
                NivelSuperiorLayout.setHorizontalGroup(
                    NivelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                NivelSuperiorLayout.setVerticalGroup(
                    NivelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                Items.setAlwaysOnTop(true);
                Items.setMinimumSize(new java.awt.Dimension(700, 422));
                Items.setResizable(false);

                jPanel8.setBackground(new java.awt.Color(230, 230, 230));

                jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(102, 102, 102));
                jLabel4.setText("Ítems");

                jPanel30.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscar2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscar2.setBorder(null);
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
                txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscar2KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                btnBuscarPaciente2.setContentAreaFilled(false);
                btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPaciente2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(414, Short.MAX_VALUE))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(410, 410, 410))
                );

                jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_ITEMS.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_ITEMS.setGridColor(new java.awt.Color(255, 255, 255));
                tb_ITEMS.setRowHeight(25);
                tb_ITEMS.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_ITEMS.getTableHeader().setReorderingAllowed(false);
                tb_ITEMS.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_ITEMSMouseClicked(evt);
                    }
                });
                tb_ITEMS.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_ITEMSKeyPressed(evt);
                    }
                });
                jScrollPane5.setViewportView(tb_ITEMS);

                javax.swing.GroupLayout ItemsLayout = new javax.swing.GroupLayout(Items.getContentPane());
                Items.getContentPane().setLayout(ItemsLayout);
                ItemsLayout.setHorizontalGroup(
                    ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                );
                ItemsLayout.setVerticalGroup(
                    ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ItemsLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setBackground(new java.awt.Color(255, 255, 255));

                jPanel1.setBackground(new java.awt.Color(39, 174, 96));
                jPanel1.setPreferredSize(new java.awt.Dimension(284, 678));
                jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        jPanel1MouseEntered(evt);
                    }
                });

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Tarifario<span style=\"font-size:'14px'\"><br>Items</br></span></html>");

                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Documento-32.png"))); // NOI18N
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
                btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Editar-32.png"))); // NOI18N
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
                btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Guardar-32.png"))); // NOI18N
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
                btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Basura-32.png"))); // NOI18N
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

                lblusu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Usuario-40.png"))); // NOI18N
                lblusu.setText("USUARIO");
                lblusu.setFocusable(false);
                lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                jPanel23.setBackground(new java.awt.Color(255, 255, 255));

                buscartodo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                buscartodo.setForeground(new java.awt.Color(51, 51, 51));
                buscartodo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                buscartodo.setBorder(null);
                buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        buscartodoCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                jPanel23.setLayout(jPanel23Layout);
                jPanel23Layout.setHorizontalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel23Layout.setVerticalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                lbldetalle.setText("Item, Grupo, Forma de Pago");

                btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnImprimir.setForeground(new java.awt.Color(240, 240, 240));
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

                lblNivel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lblNivel.setForeground(new java.awt.Color(39, 174, 96));
                lblNivel.setText("jLabel2");

                lblPermiso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lblPermiso.setForeground(new java.awt.Color(39, 174, 96));
                lblPermiso.setText("jLabel2");

                lblEditar.setForeground(new java.awt.Color(23, 160, 134));
                lblEditar.setText("0");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbldetalle)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblNivel)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblPermiso)))
                                .addGap(0, 9, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEditar)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbldetalle))
                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnguardar)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar)
                        .addGap(18, 18, 18)
                        .addComponent(btneliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNivel)
                            .addComponent(lblPermiso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblusu)
                        .addContainerGap())
                );

                jPanel5.setBackground(new java.awt.Color(230, 230, 230));
                jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

                jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
                jLabel33.setForeground(new java.awt.Color(51, 51, 51));
                jLabel33.setText("Listado");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_Tarifas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tb_Tarifas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tb_Tarifas.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Tarifas.setGridColor(new java.awt.Color(255, 255, 255));
                tb_Tarifas.setRowHeight(25);
                tb_Tarifas.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_Tarifas.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_TarifasMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tb_TarifasMousePressed(evt);
                    }
                });
                tb_Tarifas.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_TarifasKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tb_Tarifas);

                panelDetalle.setBackground(new java.awt.Color(255, 255, 255));

                cbxFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cbxFormaPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "4 CARNET DE EXTRANJERIA", "6 RUC", "7 PASAPORTE", "A CED.DIPLOMATICA DE IDENTIDAD" }));
                cbxFormaPago.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        cbxFormaPagoItemStateChanged(evt);
                    }
                });

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                jLabel12.setText("Forma de Pago");

                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel17.setText(" ");

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel13.setText("Item");

                panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtITEM.setEditable(false);
                txtITEM.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtITEM.setForeground(new java.awt.Color(51, 51, 51));
                txtITEM.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtITEM.setBorder(null);
                txtITEM.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtITEMCaretUpdate(evt);
                    }
                });

                btnBusquedaITEM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                btnBusquedaITEM.setContentAreaFilled(false);
                btnBusquedaITEM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBusquedaITEM.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBusquedaITEMActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                panelCPT1.setLayout(panelCPT1Layout);
                panelCPT1Layout.setHorizontalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtITEM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusquedaITEM, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                panelCPT1Layout.setVerticalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtITEM, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnBusquedaITEM, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblID_ITEM.setText("jLabel2");

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                panelTUPA_COMPLETO1.setBackground(new java.awt.Color(255, 255, 255));
                panelTUPA_COMPLETO1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPrecio.setEditable(false);
                txtPrecio.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtPrecio.setForeground(new java.awt.Color(51, 51, 51));
                txtPrecio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtPrecio.setBorder(null);
                txtPrecio.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPrecioCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout panelTUPA_COMPLETO1Layout = new javax.swing.GroupLayout(panelTUPA_COMPLETO1);
                panelTUPA_COMPLETO1.setLayout(panelTUPA_COMPLETO1Layout);
                panelTUPA_COMPLETO1Layout.setHorizontalGroup(
                    panelTUPA_COMPLETO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTUPA_COMPLETO1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                );
                panelTUPA_COMPLETO1Layout.setVerticalGroup(
                    panelTUPA_COMPLETO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTUPA_COMPLETO1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblVALORVENTA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblVALORVENTA.setText("Valor de Venta S/");

                jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel21.setText("Precio de Venta");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTUPA_COMPLETO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblVALORVENTA)
                        .addContainerGap(167, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelTUPA_COMPLETO1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVALORVENTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0))
                );

                lblID_PRECIO.setText("jLabel2");

                javax.swing.GroupLayout panelDetalleLayout = new javax.swing.GroupLayout(panelDetalle);
                panelDetalle.setLayout(panelDetalleLayout);
                panelDetalleLayout.setHorizontalGroup(
                    panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetalleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalleLayout.createSequentialGroup()
                                .addComponent(lblID_PRECIO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblID_ITEM)))
                        .addGap(15, 15, 15)
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(50, Short.MAX_VALUE))
                );
                panelDetalleLayout.setVerticalGroup(
                    panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetalleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblID_ITEM)
                            .addComponent(lblID_PRECIO))
                        .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(panelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //        if(lblPermiso.getText().equals("E")){

            tg = 1;
            btnNuevo.setEnabled(true);
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
            panelDetalle.setVisible(true);
            LIMPIAR();
            HABILITAR(true);

            //        }else if(!lblPermiso.getText().equals("E")){
            //            NivelSuperior.setUndecorated(true);
            //            NivelSuperior.setVisible(true);
            //        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if(lblPermiso.getText().equals("E")){
            tg=2;
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            HABILITAR(true);

        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);

        }
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            Caja_Tarifario cn = new Caja_Tarifario();
            Caja_Tarifario cn1 = new Caja_Tarifario();
            if((cn.VALIDAR_PRECIO(cbxFormaPago.getSelectedItem().toString(),lblID_ITEM.getText())>0)){
                cargareliminar.setVisible(true);
                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("El Ítem ingresado ya tiene precio para esta forma de pago, Verifique ");
                eli.setVisible(false);
                noeli.setVisible(false);
                txtPrecio.setText("");
                txtPrecio.requestFocus();

            }else
            NUEVO_REGISTRO();

        }
        if(tg==2){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,153,51));
            Mensaje.setText("Desea Actualizar el Registro ?");
            eli.setText("Si");
            eli.setVisible(true);
            noeli.setVisible(true);
            tgm=3;
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if(lblPermiso.getText().equals("E")){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,91,70));
            Mensaje.setText("Desea Eliminar este registro?");
            eli.setText("Si");
            eli.setVisible(true);
            noeli.setText("No");
            noeli.setVisible(true);
            tgm=8;
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Caja_Tarifario N = new Caja_Tarifario();
        N.LISTA_PRECIOS(buscartodo.getText(),tb_Tarifas);
        jLabel33.setText("Resultados de la Busqueda");
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
     
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        //        panelH.setVisible(false);
        //        panelV.setVisible(false);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tgm==3){
            if(lblPermiso.getText().equals("E")){
//                MODIFICAR_REGISTRO();
            }else if(!lblPermiso.getText().equals("E")){
                NivelSuperior.setUndecorated(true);
                NivelSuperior.setVisible(true);

            }
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
        } else
        if (tgm==8){
            if(lblPermiso.getText().equals("E")){
//                ELIMINAR_CPT();
            }else if(!lblPermiso.getText().equals("E")){
                NivelSuperior.setUndecorated(true);
                NivelSuperior.setVisible(true);
            }
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
        }
        else if (tgm==2 ||tgm==1||tgm==9){
            cargareliminar.setVisible(false);
        }
        if (tgm==0){
            cargareliminar.setVisible(false);
        }
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void tb_TarifasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_TarifasMouseClicked

        if(evt.getClickCount()==1){
            CARGAR();
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);
            panelDetalle.setVisible(false);
            cargareliminar.setVisible(false);
        }
        if(evt.getClickCount()==2){
//            CARGAR();
            panelDetalle.setVisible(true);
        }
    }//GEN-LAST:event_tb_TarifasMouseClicked

    private void tb_TarifasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_TarifasMousePressed

    }//GEN-LAST:event_tb_TarifasMousePressed

    private void tb_TarifasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_TarifasKeyPressed

    }//GEN-LAST:event_tb_TarifasKeyPressed

    private void txtITEMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtITEMCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtITEMCaretUpdate

    private void btnBusquedaITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaITEMActionPerformed
        Caja_Tarifario A = new Caja_Tarifario();
        A.LISTA_ITEMS("", tb_ITEMS);
        Items.setVisible(true);
    }//GEN-LAST:event_btnBusquedaITEMActionPerformed

    private void cbxFormaPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFormaPagoItemStateChanged
        
    }//GEN-LAST:event_cbxFormaPagoItemStateChanged

    private void btnAlertConsulta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta10ActionPerformed
        NivelSuperior.dispose();
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        Caja_Tarifario A = new Caja_Tarifario();
        A.LISTA_ITEMS(txtBuscar2.getText(), tb_ITEMS);
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_ITEMS.getSelectionModel().setSelectionInterval (0,0) ;
            tb_ITEMS.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void tb_ITEMSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ITEMSMouseClicked
        int fila=tb_ITEMS.getSelectedRow();
        if(evt.getClickCount()==2){
            Items.dispose();
            txtITEM.setText(String.valueOf(tb_ITEMS.getValueAt(fila, 0)));
            jLabel17.setText(String.valueOf(tb_ITEMS.getValueAt(fila, 1)));
            lblID_ITEM.setText(String.valueOf(tb_ITEMS.getValueAt(fila, 2)));
            if(jLabel17.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                txtPrecio.setRequestFocusEnabled(true);
                lblVALORVENTA.setVisible(true);
                jPanel2.setVisible(true);
            }else if(!jLabel17.getText().equals("10 GRAVADO-OPERACIÓN ONEROSA")){
                txtPrecio.setRequestFocusEnabled(true);
                lblVALORVENTA.setVisible(false);
                jPanel2.setVisible(true);
            }
            
        }
    }//GEN-LAST:event_tb_ITEMSMouseClicked

    private void tb_ITEMSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ITEMSKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_ITEMS.getSelectedRow();
            Items.dispose();
            txtITEM.setText(String.valueOf(tb_ITEMS.getValueAt(fila, 0)));
            jLabel17.setText(String.valueOf(tb_ITEMS.getValueAt(fila, 1)));
            lblID_ITEM.setText(String.valueOf(tb_ITEMS.getValueAt(fila, 2)));
            txtPrecio.setRequestFocusEnabled(true);
        }
    }//GEN-LAST:event_tb_ITEMSKeyPressed

    private void txtPrecioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPrecioCaretUpdate
        try {
            double subtotal=0.00;
            double total=0.00;
            total=Double.parseDouble(txtPrecio.getText());
            subtotal=total/1.18;//SUBTOTAL
            BigDecimal bd4 = new BigDecimal(subtotal);
            bd4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
            this.lblVALORVENTA.setText("Valor de Venta S/ "+String.valueOf(bd4) );
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_txtPrecioCaretUpdate

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
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Precios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Items;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog NivelSuperior;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnBusquedaITEM;
    public static javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxFormaPago;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel30;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblID_ITEM;
    private javax.swing.JLabel lblID_PRECIO;
    public static javax.swing.JLabel lblNivel;
    public static javax.swing.JLabel lblPermiso;
    private javax.swing.JLabel lblVALORVENTA;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelDetalle;
    private javax.swing.JPanel panelTUPA_COMPLETO1;
    private javax.swing.JTable tb_ITEMS;
    private javax.swing.JTable tb_Tarifas;
    private javax.swing.JTextField txtBuscar2;
    public static javax.swing.JTextField txtITEM;
    public static javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
