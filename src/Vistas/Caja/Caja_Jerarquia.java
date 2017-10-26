/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Servicios.Conexion;
import Vistas.Principal.Principal;
import modelo.Caja.Caja_Jerarquias;
/**
 *
 * @author MYS1
 */
public class Caja_Jerarquia extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
byte tge;
Caja_Jerarquias cnn = new Caja_Jerarquias();
    /**
     * Creates new form Caja_Jerarquia
     */
     public Caja_Jerarquia() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new Color(255,255,255)); 
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Mind Map-100.png")).getImage());
         setLocationRelativeTo(null);//en el centro
         cnn.LISTARNIVEL0(tbNivel0);
         tbNivel0.getTableHeader().setVisible(false);
         tbNivel0.setTableHeader(null);
         Caja_Jerarquias N = new Caja_Jerarquias();
         N.LISTAR_PERMISOS_ITEM(Principal.lblUsu.getText());
         NivelSuperior.setLocationRelativeTo(null);//en el centro
        
//         formatoRelacion();
//         LISTAR();
//         formato();
//        panelNivel1.setVisible(false);
//        panelNivel2.setVisible(false);
//        jScrollPane5.setVisible(false);
//        jScrollPane6.setVisible(false);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        cargareliminar.setVisible(false);
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
    }
  
    
     public void Guardar(){

        if((txtFormaPago.getText().equals("")) ){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Ocurrió un error, Debe completar todos los campos");
            eli.setVisible(false);
            noeli.setVisible(false);
        } else {
                Caja_Jerarquias cno1 = new Caja_Jerarquias();
//                cno1.setCod_jerar_forma_pago(txtcodigo.getText());//
                cno1.setDescri_forma_pago(txtFormaPago.getText());//
                if(lblNivel.getText().equals("0")){
                    cno1.setRelacion_forma_pago(0);//    
                }else 
                    if(!lblNivel.getText().equals("0")){
                    cno1.setRelacion_forma_pago(Integer.parseInt(lblCodigo.getText()));//    
                }
                cno1.setNivel_forma_pago(0);//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaJerarquia()==true){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Datos Guardados de forma correcta");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                           tge=1;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(true);
                           txtFormaPago.setEnabled(false);
                           panelAgregar.setVisible(true);
                           if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }
                           
                           
                           
                           
                       } else {
                           cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Ocurrió un error, Verifique");
                           eli.setVisible(false);
                           noeli.setVisible(false);
                       }}}
     
     public void Modificar(){

                        Caja_Jerarquias cno = new Caja_Jerarquias();
                        cno.setCod_jerar_forma_pago(Integer.parseInt(lblCodigoAE.getText()));//
                        cno.setDescri_forma_pago(txtFormaPago.getText());//
                        if(lblNivel.getText().equals("0")){
                            cno.setRelacion_forma_pago(0);//    
                        }else 
                            if(!lblNivel.getText().equals("0")){
                            cno.setRelacion_forma_pago(Integer.parseInt(lblCodigo.getText()));//    
                        }
                        cno.setNivel_forma_pago(Integer.parseInt(lblNivel.getText()));//
                        cno.setNom_usu(lblusu.getText());//
                        if(cno.Caja_Jerarquia_MODIFICAR()==true){
                            cargareliminar.setBackground(new Color(0,153,102)); 
                            Mensaje.setText("Datos Actualizados de forma correcta");
                            eli.setText("OK");
                            eli.setVisible(true);
                            noeli.setVisible(false);
                            btnguardar.setEnabled(false);
                            btneditar.setEnabled(true);
                            txtFormaPago.setEnabled(false);
                            panelAgregar.setVisible(true);
                            tge=9;
                            if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }
                        } else {
                           
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(255,91,70)); 
                            Mensaje.setText("Ocurrió un error, Verifique");
                            eli.setVisible(false);
                            noeli.setVisible(false);
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
        jPanel1 = new javax.swing.JPanel();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        lblNivel1 = new javax.swing.JLabel();
        lblPermiso = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        panelAgregar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFormaPago = new javax.swing.JTextField();
        codpago = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCodigoAE = new javax.swing.JLabel();
        cargareliminar = new javax.swing.JPanel();
        Mensaje = new javax.swing.JLabel();
        eli = new javax.swing.JButton();
        noeli = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbNivel0 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jLabel52 = new javax.swing.JLabel();
            jLabel47 = new javax.swing.JLabel();
            jPanel35 = new javax.swing.JPanel();

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

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(39, 174, 96));

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
            lblusu.setText("Silvana");
            lblusu.setFocusable(false);
            lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

            jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
            jLabel57.setForeground(new java.awt.Color(255, 255, 255));
            jLabel57.setText("<html>Formas de Pago<span style=\"font-size:'14px'\"><br></br></span></html>");

            btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
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

            lblNivel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            lblNivel1.setForeground(new java.awt.Color(39, 174, 96));
            lblNivel1.setText("jLabel2");

            lblPermiso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            lblPermiso.setForeground(new java.awt.Color(39, 174, 96));
            lblPermiso.setText("jLabel2");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblNivel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblPermiso)))
                            .addGap(0, 41, Short.MAX_VALUE))))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(70, 70, 70)
                    .addComponent(btnNuevo)
                    .addGap(18, 18, 18)
                    .addComponent(btnguardar)
                    .addGap(18, 18, 18)
                    .addComponent(btneditar)
                    .addGap(18, 18, 18)
                    .addComponent(btneliminar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNivel1)
                        .addComponent(lblPermiso))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            jPanel5.setBackground(new java.awt.Color(230, 230, 230));
            jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

            jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
            jLabel33.setForeground(new java.awt.Color(51, 51, 51));
            jLabel33.setText("Listado y Registro");

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
                    .addContainerGap(66, Short.MAX_VALUE))
            );

            panelAgregar.setBackground(new java.awt.Color(255, 255, 255));

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setText("Descripcion de la Forma de Pago ");

            txtFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtFormaPago.setEnabled(false);
            txtFormaPago.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtFormaPagoKeyReleased(evt);
                }
            });

            codpago.setBackground(new java.awt.Color(255, 255, 255));
            codpago.setForeground(new java.awt.Color(255, 255, 255));
            codpago.setText("jLabel8");

            lblCodigo.setForeground(new java.awt.Color(255, 255, 255));
            lblCodigo.setText("CODIGO");

            lblNivel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblNivel.setForeground(new java.awt.Color(255, 255, 255));
            lblNivel.setText("jLabel2");

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(51, 51, 51));
            jLabel2.setText("Agregar");

            lblCodigoAE.setForeground(new java.awt.Color(255, 255, 255));
            lblCodigoAE.setText("jLabel4");

            javax.swing.GroupLayout panelAgregarLayout = new javax.swing.GroupLayout(panelAgregar);
            panelAgregar.setLayout(panelAgregarLayout);
            panelAgregarLayout.setHorizontalGroup(
                panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAgregarLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAgregarLayout.createSequentialGroup()
                            .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(lblCodigo))
                            .addGap(41, 41, 41)
                            .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelAgregarLayout.createSequentialGroup()
                                    .addGap(307, 307, 307)
                                    .addComponent(codpago))))
                        .addGroup(panelAgregarLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNivel))
                        .addComponent(lblCodigoAE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelAgregarLayout.setVerticalGroup(
                panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAgregarLayout.createSequentialGroup()
                    .addContainerGap(25, Short.MAX_VALUE)
                    .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNivel)
                        .addComponent(jLabel2))
                    .addGap(15, 15, 15)
                    .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAgregarLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(codpago))
                        .addGroup(panelAgregarLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblCodigo)))
                    .addGap(10, 10, 10)
                    .addComponent(lblCodigoAE)
                    .addContainerGap())
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
                    .addGap(20, 20, 20)
                    .addComponent(Mensaje)
                    .addGap(45, 45, 45)
                    .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            cargareliminarLayout.setVerticalGroup(
                cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cargareliminarLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(Mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
            jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tbNivel0.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            tbNivel0.setForeground(new java.awt.Color(51, 51, 51));
            tbNivel0.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {},
                    {},
                    {},
                    {}
                },
                new String [] {

                }
            ));
            tbNivel0.setGridColor(new java.awt.Color(255, 255, 255));
            tbNivel0.setRowHeight(25);
            tbNivel0.setSelectionBackground(new java.awt.Color(102, 102, 102));
            tbNivel0.getTableHeader().setReorderingAllowed(false);
            tbNivel0.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbNivel0MouseClicked(evt);
                }
            });
            tbNivel0.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbNivel0KeyPressed(evt);
                }
            });
            jScrollPane4.setViewportView(tbNivel0);

            jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            jLabel52.setForeground(new java.awt.Color(51, 51, 51));
            jLabel52.setText("  Métodos de Pago Registrados");

            jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            jLabel47.setForeground(new java.awt.Color(51, 51, 51));
            jLabel47.setText("  Descripción");

            jPanel35.setBackground(new java.awt.Color(23, 160, 134));
            jPanel35.setPreferredSize(new java.awt.Dimension(0, 2));

            javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
            jPanel35.setLayout(jPanel35Layout);
            jPanel35Layout.setHorizontalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 450, Short.MAX_VALUE)
            );
            jPanel35Layout.setVerticalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 2, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel52))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel52)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
                        .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        panelAgregar.setVisible(true);
        int fila=tbNivel0.getSelectedRow();
//        lblCodigo.setText(String.valueOf(tbNivel0.getValueAt(fila, 1)));
        lblNivel.setText("0");
        tg=1;
        txtFormaPago.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        txtFormaPago.setText("");
        txtFormaPago.requestFocus();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if(lblPermiso.getText().equals("E")){ 
        txtFormaPago.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         tg=2;
         panelAgregar.setVisible(true);
         if(lblNivel.getText().equals("0")){
                int fila=tbNivel0.getSelectedRow();
                txtFormaPago.setText(String.valueOf(tbNivel0.getValueAt(fila, 0)));
                
            }
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
            
        }   
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       if(tg==1){
            Guardar();

        }else if(tg==2){
           cargareliminar.setVisible(true);
           cargareliminar.setBackground(new Color(255,153,51)); 
           Mensaje.setText("Desea Actualizar el Registro ?");
           eli.setText("Si");
           eli.setVisible(true);
           noeli.setVisible(true);
           tge=2;
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if(lblPermiso.getText().equals("E")){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,91,70)); 
            if(lblNivel.getText().equals("0")){
                int fila=tbNivel0.getSelectedRow();
                Mensaje.setText("Desea Eliminar "+String.valueOf(tbNivel0.getValueAt(fila, 0))+"?");
                
            }
           
            eli.setText("Si");
            eli.setVisible(true);
            noeli.setText("No");
            noeli.setVisible(true);
            tge=6;
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void tbNivel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel0MouseClicked
        Caja_Jerarquias cno1 = new Caja_Jerarquias();
        int fila=tbNivel0.getSelectedRow();
        if(evt.getClickCount()==1){

            lblCodigoAE.setText(String.valueOf(tbNivel0.getValueAt(fila, 1))); 
            btnNuevo.setEnabled(true);


            btnNuevo.setEnabled(true);
            btneliminar.setEnabled(true);
            btneditar.setEnabled(true);
            lblNivel.setText("0");
        }
    }//GEN-LAST:event_tbNivel0MouseClicked

    private void tbNivel0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel0KeyPressed

    }//GEN-LAST:event_tbNivel0KeyPressed

    private void txtFormaPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFormaPagoKeyReleased
        txtFormaPago.setText(txtFormaPago.getText().toUpperCase());
    }//GEN-LAST:event_txtFormaPagoKeyReleased

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==3 || tge==1 ||tge==9 ||tge==7||tge==9){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            Modificar();
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);


        }
        if (tge==6){
        try{
            if(lblNivel.getText().equals("0")){

                Caja_Jerarquias hCEl = new Caja_Jerarquias();
                hCEl.setCod_jerar_forma_pago(Integer.parseInt(lblCodigoAE.getText()));
                if(hCEl.eliminarjerarquia()==true){
                     cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=7;
                                 if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }
                }else{
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,91,70)); 
                    Mensaje.setText("No se pudo eliminar el registro, puede que esté protegido");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                }
            }
            
            
                
        }catch(Exception e){
            System.out.println("Error AL ELIMINAR: " + e.toString());
        }
        }
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed

        if (tge==3 || tge==1 || tge==6){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,153,51));
            Mensaje.setText("No se han realizado modificaciones");
            eli.setText("OK");
            eli.setVisible(true);
            noeli.setVisible(false);
            tge=9;

        }

    }//GEN-LAST:event_noeliActionPerformed

    private void btnAlertConsulta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta10ActionPerformed
        NivelSuperior.dispose();
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Jerarquia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog NivelSuperior;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JLabel codpago;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel35;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoAE;
    private javax.swing.JLabel lblNivel;
    public static javax.swing.JLabel lblNivel1;
    public static javax.swing.JLabel lblPermiso;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelAgregar;
    private javax.swing.JTable tbNivel0;
    private javax.swing.JTextField txtFormaPago;
    // End of variables declaration//GEN-END:variables
}
