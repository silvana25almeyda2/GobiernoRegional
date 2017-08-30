/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Caja.Caja_NuevaVenta;
import modelo.Caja.Caja_AperturaCierre;
import Servicios.Conexion;
import Vistas.Principal.Principal;
import Vistas.Principal.Principal_Caja;
import java.beans.PropertyVetoException;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class Caja_Apertura extends javax.swing.JFrame {
Calendar calendario;
DefaultTableModel m;
double totales;
byte tg;
int hora,minutos,segundos;
ResultSet r;
Conexion c=new Conexion();
Connection conexion=c.conectar();
Caja_NuevaVenta cnn = new Caja_NuevaVenta();
Caja_AperturaCierre cn1 = new Caja_AperturaCierre();
Caja_AperturaCierre nuevaV = new Caja_AperturaCierre();
    /**
     * Creates new form Caja_Apertura
     */
    public Caja_Apertura() {

        initComponents();
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
       
        this.getContentPane().setBackground(Color.WHITE);//fondo blanco
        setLocationRelativeTo(null);//en el centro
//        cap.setText(cnn.id());
        cn1.CajaPC_NRO(Principal.lblUsu.getText());
        Calendar cal=Calendar.getInstance(); 
        Calendar calendario =new GregorianCalendar();
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblHora.setText(hora);
        lblFecha.setText(fechaActual());
    }
    public static String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        return date.format(now);
    }
    public void Caja_Abrir(){
        Principal_Caja V =new Principal_Caja();
        Principal.Contenedor.add(V);
        Caja_AperturaCierre CAID =new Caja_AperturaCierre();
        CAID.CajaID_SESION(Principal.lblUsu.getText());
        Principal_Caja.ibiIDAPERTURA.setText(lblID.getText());
        try {
            V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.PaginasP.setSelectedIndex(1);
    }
    public void APERTURANDO(){
        Caja_Abrir();
        
        nuevaV.reporteAperura(Integer.parseInt(lblID.getText()));
//        APERTURA.dispose();
        dispose();
    }
    
    public void NUEVO_REGISTRO(java.sql.Connection con) {
        try {
            // instanciamos el objeto callable
            CallableStatement cstmt = con.prepareCall("exec CAJA_APERTURA_CAJA ?,?,?,?");
            // seteamos los parametros de entrada
            cstmt.setString(1, lblusu.getText());
            cstmt.setString(2, lblCorrelativo.getText());
            cstmt.setString(3, txtNRO.getText());
            // registramos el parametro de retorno (si fueran mas, repetimos la linea cambiando el nro de orden del parametro)
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            // ejecutamos
            cstmt.execute();
            // mostramos al usuario el codigo creado
            int ID;
            ID=cstmt.getInt(4);
            this.lblID.setText( String.valueOf(ID) );
            System.out.println("APERTURANDO");  
            jPanel3.setVisible(false);
            this.getContentPane().setBackground(new Color(23,160,134));
            lblTexto.setText("Aperturando Caja, Espere...");
            panelCambio.setVisible(false);
            APERTURANDO();

        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }

    
//    public void Guardar(){
//                
//                Caja_AperturaCierre cno1 = new Caja_AperturaCierre();
//
//                cno1.setCajero(lblusu.getText());//
//                cno1.setCaja(lblCorrelativo.getText());//
//                cno1.setBASE(txtNRO.getText());//
//                cno1.setId_Apertura(0);//
//                    if(cno1.NUEVO()==true){
//                        jPanel3.setVisible(false);
//                        this.getContentPane().setBackground(new Color(23,160,134));
//                        lblTexto.setText("Aperturando Caja, Espere...");
//                        panelCambio.setVisible(false);
////                        APERTURA.setVisible(true);
//                        APERTURANDO();
////                        int id=0;
////                        id=cno1.getId_Apertura();
////                        jLabel5.setText(String.valueOf(id)) ;
//                        
//                           
//                       } else {
//                           JOptionPane.showMessageDialog(this, "Error al guardar");
//                       }}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        panelCambio = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTerminal = new javax.swing.JLabel();
        lblCorrelativo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        panelCPT4 = new javax.swing.JPanel();
        panelCPT5 = new javax.swing.JPanel();
        txtNRO = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTexto = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelCambio.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(51, 51, 51));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Play-32.png"))); // NOI18N
        btnNuevo.setText("Aperturar");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Cajero");

        lblusu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblusu.setForeground(new java.awt.Color(51, 51, 51));
        lblusu.setText("USUARIO");
        lblusu.setFocusable(false);
        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Terminal");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Serie");

        lblTerminal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTerminal.setForeground(new java.awt.Color(51, 51, 51));
        lblTerminal.setText("Terminal");

        lblCorrelativo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCorrelativo.setForeground(new java.awt.Color(51, 51, 51));
        lblCorrelativo.setText("Terminal");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Base");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Fecha");

        lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(51, 51, 51));
        lblFecha.setText("00/00/00");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Hora");

        lblHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(51, 51, 51));
        lblHora.setText("00:00:00");

        panelCPT4.setBackground(new java.awt.Color(255, 255, 255));

        panelCPT5.setBackground(new java.awt.Color(153, 153, 153));
        panelCPT5.setPreferredSize(new java.awt.Dimension(104, 1));

        javax.swing.GroupLayout panelCPT5Layout = new javax.swing.GroupLayout(panelCPT5);
        panelCPT5.setLayout(panelCPT5Layout);
        panelCPT5Layout.setHorizontalGroup(
            panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );
        panelCPT5Layout.setVerticalGroup(
            panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        txtNRO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtNRO.setForeground(new java.awt.Color(51, 51, 51));
        txtNRO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNRO.setBorder(null);
        txtNRO.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNROCaretUpdate(evt);
            }
        });
        txtNRO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNROKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNROKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("S/.");

        javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
        panelCPT4.setLayout(panelCPT4Layout);
        panelCPT4Layout.setHorizontalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCPT5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNRO)))
        );
        panelCPT4Layout.setVerticalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addGroup(panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNRO, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelCambioLayout = new javax.swing.GroupLayout(panelCambio);
        panelCambio.setLayout(panelCambioLayout);
        panelCambioLayout.setHorizontalGroup(
            panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCambioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14))
                .addGap(36, 36, 36)
                .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCambioLayout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(lblHora))
                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblCorrelativo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addComponent(lblTerminal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 47, Short.MAX_VALUE))
        );
        panelCambioLayout.setVerticalGroup(
            panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblusu)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTerminal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblCorrelativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCPT4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(lblHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(23, 160, 134));

        lblTexto.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        lblTexto.setForeground(new java.awt.Color(255, 255, 255));
        lblTexto.setText("Apertura de Caja");

        lblID.setForeground(new java.awt.Color(23, 160, 134));
        lblID.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTexto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblID)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblID)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        NUEVO_REGISTRO(conexion);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtNROCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNROCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNROCaretUpdate

    private void txtNROKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNROKeyTyped

    }//GEN-LAST:event_txtNROKeyTyped

    private void txtNROKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNROKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            btnNuevo.doClick();
          } 
    }//GEN-LAST:event_txtNROKeyPressed

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
            java.util.logging.Logger.getLogger(Caja_Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Apertura().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JLabel lblCorrelativo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblID;
    public static javax.swing.JLabel lblTerminal;
    private javax.swing.JLabel lblTexto;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCambio;
    public static javax.swing.JTextField txtNRO;
    // End of variables declaration//GEN-END:variables
}
