/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import Vistas.Principal.Principal;
import java.awt.Color;
import java.awt.Toolkit;
import modelo.Caja.Caja_PC_Registro;

/**
 *
 * @author Administrador
 */
public class Caja_Ajustes extends javax.swing.JFrame {
Caja_PC_Registro nuevaV = new Caja_PC_Registro();
    /**
     * Creates new form Caja_Ajustes
     */
    public Caja_Ajustes() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);//fondo blanco
        this.setExtendedState(MAXIMIZED_BOTH);
        cargareliminar.setVisible(false);
        cbxImpresoras.setEnabled(false);
        btnImprimir.setEnabled(false);
        btnguardar.setEnabled(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        Caja_PC_Registro pc = new Caja_PC_Registro();
        pc.LISTAR_CONFIGURACION(Principal.lblUsu.getText());
    }
    
    public void ACTUALIZAR(){

                
        Caja_PC_Registro cno1 = new Caja_PC_Registro();
        cno1.setIMPRESORA(cbxImpresoras.getSelectedItem().toString());
        cno1.setNOM_USU(lblusu.getText());
        if(cno1.ACTUALIZAR_CONFIGURACION()==true){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(0,153,102)); 
            Mensaje.setText("Se actualizo un cambio en la configuración");
            eli.setText("OK");
            eli.setVisible(true);
            noeli.setVisible(false);
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            cbxImpresoras.setEnabled(false);
            btnImprimir.setEnabled(false);
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

        jPanel1 = new javax.swing.JPanel();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblNivel1 = new javax.swing.JLabel();
        lblPermiso = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        cargareliminar = new javax.swing.JPanel();
        Mensaje = new javax.swing.JLabel();
        eli = new javax.swing.JButton();
        noeli = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        panelCPT4 = new javax.swing.JPanel();
        txtNRO = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        panelCPT7 = new javax.swing.JPanel();
        txtNRO1 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxImpresoras = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

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

        lblusu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblusu.setForeground(new java.awt.Color(255, 255, 255));
        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Usuario-40.png"))); // NOI18N
        lblusu.setText("Silvana");
        lblusu.setFocusable(false);
        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Ajustes");

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
                                .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLabel33.setText("Propiedades");

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
            .addComponent(Mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Serie Caja Central");

        panelCPT4.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtNRO.setEditable(false);
        txtNRO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtNRO.setForeground(new java.awt.Color(51, 51, 51));
        txtNRO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNRO.setBorder(null);
        txtNRO.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNROCaretUpdate(evt);
            }
        });
        txtNRO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNROMouseClicked(evt);
            }
        });
        txtNRO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNROKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
        panelCPT4.setLayout(panelCPT4Layout);
        panelCPT4Layout.setHorizontalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtNRO, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );
        panelCPT4Layout.setVerticalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtNRO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Serie Farmacia");

        panelCPT7.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtNRO1.setEditable(false);
        txtNRO1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtNRO1.setForeground(new java.awt.Color(51, 51, 51));
        txtNRO1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNRO1.setBorder(null);
        txtNRO1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNRO1CaretUpdate(evt);
            }
        });
        txtNRO1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNRO1MouseClicked(evt);
            }
        });
        txtNRO1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNRO1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelCPT7Layout = new javax.swing.GroupLayout(panelCPT7);
        panelCPT7.setLayout(panelCPT7Layout);
        panelCPT7Layout.setHorizontalGroup(
            panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtNRO1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );
        panelCPT7Layout.setVerticalGroup(
            panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtNRO1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(102, 102, 102));
        jLabel59.setText("Configuración de la Impresora");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Tipo de Comprobante");

        cbxImpresoras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxImpresoras.setForeground(new java.awt.Color(51, 51, 51));
        cbxImpresoras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ticket - Papel Termico", "Recibo 25cm x 12cm" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Verifique que la impresora este encendida y este correctamente configurada.");

        btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(51, 51, 51));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Print-32BANNER.png"))); // NOI18N
        btnImprimir.setText("Prueba de Impresión");
        btnImprimir.setBorder(null);
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImprimir.setIconTextGap(30);
        btnImprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(102, 102, 102));
        jLabel58.setText("Configuración del Terminal");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 91, 70));
        jLabel1.setText("Esta opción no está disponible por el momento.");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 91, 70));
        jLabel2.setText("Esta opción no está disponible por el momento.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(59, 59, 59)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelCPT7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(38, 38, 38)
                                        .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)
                                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel58)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(panelCPT7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel59)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(25, 25, 25)
                .addComponent(btnImprimir)
                .addGap(0, 113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        cbxImpresoras.setEnabled(true);
        btnImprimir.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        ACTUALIZAR();     
    }//GEN-LAST:event_btnguardarActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void txtNROCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNROCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNROCaretUpdate

    private void txtNROKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNROKeyTyped
        if (txtNRO.getText().length()==1){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNROKeyTyped

    private void txtNRO1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNRO1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNRO1CaretUpdate

    private void txtNRO1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNRO1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNRO1KeyTyped

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if(cbxImpresoras.getSelectedItem().equals("Ticket - Papel Termico")){
            nuevaV.reportePRUEBA_TICKET("");
        }else if(!cbxImpresoras.getSelectedItem().equals("Ticket - Papel Termico")){

        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtNROMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNROMouseClicked
        jLabel1.setVisible(true);
    }//GEN-LAST:event_txtNROMouseClicked

    private void txtNRO1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNRO1MouseClicked
        jLabel2.setVisible(true);
    }//GEN-LAST:event_txtNRO1MouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
    }//GEN-LAST:event_formMouseEntered

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
            java.util.logging.Logger.getLogger(Caja_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Ajustes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    public static javax.swing.JButton btnImprimir;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JPanel cargareliminar;
    public static javax.swing.JComboBox cbxImpresoras;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel5;
    public static javax.swing.JLabel lblNivel1;
    public static javax.swing.JLabel lblPermiso;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT7;
    public static javax.swing.JTextField txtNRO;
    public static javax.swing.JTextField txtNRO1;
    // End of variables declaration//GEN-END:variables
}
