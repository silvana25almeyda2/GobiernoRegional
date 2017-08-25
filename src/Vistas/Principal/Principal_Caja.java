/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Principal;

import Vistas.Caja.Caja_CPTS;
import Vistas.Caja.Caja_Cierre;
import Vistas.Caja.Caja_Clientes;
import Vistas.Caja.Caja_Transaccion;
import Vistas.Caja.Caja_Ventas;
import static Vistas.Principal.Principal.PaginasP;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import modelo.Caja.Caja_CPT;

/**
 *
 * @author MYS1
 */
public class Principal_Caja extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
    /**
     * Creates new form Principal_Caja
     */
    public Principal_Caja() {
        initComponents();
        QuitarLaBarraTitulo();
    }
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
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
        jLabel2 = new javax.swing.JLabel();
        PanelSesion = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblIDSESION = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PanelCaja = new javax.swing.JPanel();
        btnVentas = new javax.swing.JButton();
        PanelCPT = new javax.swing.JPanel();
        btnCPT = new javax.swing.JButton();
        PanelCaja2 = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        PanelCaja3 = new javax.swing.JPanel();
        btnCaja3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ibiIDAPERTURA = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CAJA APERTURADA");

        PanelSesion.setBackground(new java.awt.Color(255, 51, 51));
        PanelSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelSesionMouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cierre de Caja");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelSesionLayout = new javax.swing.GroupLayout(PanelSesion);
        PanelSesion.setLayout(PanelSesionLayout);
        PanelSesionLayout.setHorizontalGroup(
            PanelSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSesionLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        PanelSesionLayout.setVerticalGroup(
            PanelSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("ID de Sesión Actual");

        lblIDSESION.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblIDSESION.setForeground(new java.awt.Color(255, 255, 255));
        lblIDSESION.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIDSESION, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lblIDSESION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        PanelCaja.setBackground(new java.awt.Color(255, 255, 255));

        btnVentas.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Monedas-64.png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.setContentAreaFilled(false);
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.setFocusPainted(false);
        btnVentas.setFocusable(false);
        btnVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas.setIconTextGap(30);
        btnVentas.setVerifyInputWhenFocusTarget(false);
        btnVentas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentasMouseEntered(evt);
            }
        });
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCajaLayout = new javax.swing.GroupLayout(PanelCaja);
        PanelCaja.setLayout(PanelCajaLayout);
        PanelCajaLayout.setHorizontalGroup(
            PanelCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );
        PanelCajaLayout.setVerticalGroup(
            PanelCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );

        PanelCPT.setBackground(new java.awt.Color(255, 255, 255));

        btnCPT.setBackground(new java.awt.Color(102, 102, 102));
        btnCPT.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnCPT.setForeground(new java.awt.Color(51, 51, 51));
        btnCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Editar propiedad-64.png"))); // NOI18N
        btnCPT.setText("TUPA");
        btnCPT.setContentAreaFilled(false);
        btnCPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCPT.setFocusPainted(false);
        btnCPT.setFocusable(false);
        btnCPT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCPT.setIconTextGap(30);
        btnCPT.setVerifyInputWhenFocusTarget(false);
        btnCPT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCPTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCPTMouseEntered(evt);
            }
        });
        btnCPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCPTLayout = new javax.swing.GroupLayout(PanelCPT);
        PanelCPT.setLayout(PanelCPTLayout);
        PanelCPTLayout.setHorizontalGroup(
            PanelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );
        PanelCPTLayout.setVerticalGroup(
            PanelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );

        PanelCaja2.setBackground(new java.awt.Color(255, 255, 255));

        btnClientes.setBackground(new java.awt.Color(102, 102, 102));
        btnClientes.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(51, 51, 51));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Grupo de usuarios hombre hombre-64 (1).png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setContentAreaFilled(false);
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.setFocusPainted(false);
        btnClientes.setFocusable(false);
        btnClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClientes.setIconTextGap(30);
        btnClientes.setVerifyInputWhenFocusTarget(false);
        btnClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClientesMouseEntered(evt);
            }
        });
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCaja2Layout = new javax.swing.GroupLayout(PanelCaja2);
        PanelCaja2.setLayout(PanelCaja2Layout);
        PanelCaja2Layout.setHorizontalGroup(
            PanelCaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );
        PanelCaja2Layout.setVerticalGroup(
            PanelCaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );

        PanelCaja3.setBackground(new java.awt.Color(255, 255, 255));

        btnCaja3.setBackground(new java.awt.Color(102, 102, 102));
        btnCaja3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnCaja3.setForeground(new java.awt.Color(51, 51, 51));
        btnCaja3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Accounting-64.png"))); // NOI18N
        btnCaja3.setText("Cuentas Contables");
        btnCaja3.setContentAreaFilled(false);
        btnCaja3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCaja3.setFocusPainted(false);
        btnCaja3.setFocusable(false);
        btnCaja3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCaja3.setIconTextGap(30);
        btnCaja3.setVerifyInputWhenFocusTarget(false);
        btnCaja3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCaja3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCaja3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCaja3MouseEntered(evt);
            }
        });
        btnCaja3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaja3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCaja3Layout = new javax.swing.GroupLayout(PanelCaja3);
        PanelCaja3.setLayout(PanelCaja3Layout);
        PanelCaja3Layout.setHorizontalGroup(
            PanelCaja3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCaja3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelCaja3Layout.setVerticalGroup(
            PanelCaja3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCaja3, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Back-32.png"))); // NOI18N
        jLabel1.setText("Volver");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        ibiIDAPERTURA.setForeground(new java.awt.Color(255, 255, 255));
        ibiIDAPERTURA.setText("jLabel48");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelCaja2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelCaja3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ibiIDAPERTURA)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ibiIDAPERTURA)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelCaja2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelCaja3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PanelSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSesionMouseEntered
     
    }//GEN-LAST:event_PanelSesionMouseEntered

    private void btnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentasMouseClicked

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered
        ImageIcon CambioV=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Monedas-64 (1).png"));
        btnVentas.setIcon(CambioV);
        btnVentas.setForeground(new Color(255,255,255)); 
        PanelCaja.setBackground(new Color(127,140,141)); 
        
        ImageIcon CambioC=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Editar propiedad-64.png"));
        btnCPT.setIcon(CambioC);
        btnCPT.setForeground(new Color(51,51,51)); 
        PanelCPT.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioP=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Grupo de usuarios hombre hombre-64.png"));
        btnClientes.setIcon(CambioP);
        btnClientes.setForeground(new Color(51,51,51)); 
        PanelCaja2.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioCT=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Accounting-64(1).png"));
        btnCaja3.setIcon(CambioCT);
        btnCaja3.setForeground(new Color(51,51,51)); 
        PanelCaja3.setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        Caja_Ventas CT = new Caja_Ventas();
        CT.setVisible(true);
        String u=Principal.lblUsu.getText();
        CT.lblusu.setText(u);
        CT.lblID_SESION.setText(lblIDSESION.getText());
        CT.lblSESION.setText(ibiIDAPERTURA.getText());

    }//GEN-LAST:event_btnVentasActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
            Caja_Cierre frmCIERRE = new Caja_Cierre();
            frmCIERRE.lblusu.setText(Principal.lblUsu.getText());
            frmCIERRE.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnCPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCPTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCPTMouseClicked

    private void btnCPTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCPTMouseEntered
        ImageIcon CambioC=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Editar propiedad-64 (1).png"));
        btnCPT.setIcon(CambioC);
        btnCPT.setForeground(new Color(255,255,255)); 
        PanelCPT.setBackground(new Color(127,140,141)); 
        
        ImageIcon CambioV=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Monedas-64.png"));
        btnVentas.setIcon(CambioV);
        btnVentas.setForeground(new Color(51,51,51)); 
        PanelCaja.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioP=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Grupo de usuarios hombre hombre-64.png"));
        btnClientes.setIcon(CambioP);
        btnClientes.setForeground(new Color(51,51,51)); 
        PanelCaja2.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioCT=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Accounting-64(1).png"));
        btnCaja3.setIcon(CambioCT);
        btnCaja3.setForeground(new Color(51,51,51)); 
        PanelCaja3.setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnCPTMouseEntered

    private void btnCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPTActionPerformed
        Caja_CPTS TP = new Caja_CPTS();
        TP.setVisible(true);
        String u=Principal.lblUsu.getText();
        TP.lblusu.setText(u);
    }//GEN-LAST:event_btnCPTActionPerformed

    private void btnClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesMouseClicked

    private void btnClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseEntered

        ImageIcon CambioP=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Grupo de usuarios hombre hombre-64 (1).png"));
        btnClientes.setIcon(CambioP);
        btnClientes.setForeground(new Color(255,255,255)); 
        PanelCaja2.setBackground(new Color(127,140,141)); 
        
        ImageIcon CambioV=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Monedas-64.png"));
        btnVentas.setIcon(CambioV);
        btnVentas.setForeground(new Color(51,51,51)); 
        PanelCaja.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioC=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Editar propiedad-64.png"));
        btnCPT.setIcon(CambioC);
        btnCPT.setForeground(new Color(51,51,51)); 
        PanelCPT.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioCT=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Accounting-64(1).png"));
        btnCaja3.setIcon(CambioCT);
        btnCaja3.setForeground(new Color(51,51,51)); 
        PanelCaja3.setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnClientesMouseEntered

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        Caja_Clientes CCLI = new Caja_Clientes();
        CCLI.setVisible(true);
        String u=Principal.lblUsu.getText();
        CCLI.lblusu.setText(u);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnCaja3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCaja3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCaja3MouseClicked

    private void btnCaja3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCaja3MouseEntered
        ImageIcon CambioP=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Grupo de usuarios hombre hombre-64.png"));
        btnClientes.setIcon(CambioP);
        btnClientes.setForeground(new Color(51,51,51)); 
        PanelCaja2.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioV=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Monedas-64.png"));
        btnVentas.setIcon(CambioV);
        btnVentas.setForeground(new Color(51,51,51)); 
        PanelCaja.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioC=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Editar propiedad-64.png"));
        btnCPT.setIcon(CambioC);
        btnCPT.setForeground(new Color(51,51,51)); 
        PanelCPT.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioCT=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Accounting-64(1).png"));
        btnCaja3.setIcon(CambioCT);
        btnCaja3.setForeground(new Color(255,255,255)); 
        PanelCaja3.setBackground(new Color(127,140,141));
    }//GEN-LAST:event_btnCaja3MouseEntered

    private void btnCaja3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaja3ActionPerformed
        Caja_Transaccion CCC = new Caja_Transaccion();
        CCC.setVisible(true);
        String u=Principal.lblUsu.getText();
        CCC.lblusu.setText(u);
    }//GEN-LAST:event_btnCaja3ActionPerformed

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        ImageIcon CambioV=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Monedas-64.png"));
        btnVentas.setIcon(CambioV);
        btnVentas.setForeground(new Color(51,51,51)); 
        PanelCaja.setBackground(new Color(255,255,255)); 
        
        ImageIcon CambioC=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Editar propiedad-64.png"));
        btnCPT.setIcon(CambioC);
        btnCPT.setForeground(new Color(51,51,51)); 
        PanelCPT.setBackground(new Color(255,255,255)); 
        
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       this.dispose();
       Principal.PaginasP.setSelectedIndex(0);
       
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCPT;
    private javax.swing.JPanel PanelCaja;
    private javax.swing.JPanel PanelCaja2;
    private javax.swing.JPanel PanelCaja3;
    private javax.swing.JPanel PanelSesion;
    public static javax.swing.JButton btnCPT;
    public static javax.swing.JButton btnCaja3;
    public static javax.swing.JButton btnClientes;
    public static javax.swing.JButton btnVentas;
    public static javax.swing.JLabel ibiIDAPERTURA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JLabel lblIDSESION;
    // End of variables declaration//GEN-END:variables
}
