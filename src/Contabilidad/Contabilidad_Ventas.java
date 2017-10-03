/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contabilidad;

import Servicios.Conexion;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Contabilidad.Contabilidad_LE;
import modelo.Facturador.CuentasPorPagarFacturasCabecera;

/**
 *
 * @author MYS1
 */
public class Contabilidad_Ventas extends javax.swing.JFrame {
ResultSet r;
Conexion c=new Conexion();
Connection conexion=c.conectar();

DefaultTableModel m, m1;
    /**
     * Creates new form Contabilidad_Ventas
     */
    public Contabilidad_Ventas() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cargareliminar.setVisible(false);
        
        this.cbxAnios.setModel(Anio());
        cbxAnios.setBackground(Color.white);
        cbxMeses.setBackground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        lblPermiso = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBuscarP = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxAnios = new javax.swing.JComboBox();
        cbxMeses = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        cargareliminar = new javax.swing.JPanel();
        Mensaje = new javax.swing.JLabel();
        eli = new javax.swing.JButton();
        noeli = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TB_VENTAS_LE = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addContainerGap(565, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(39, 174, 96));

        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("<html>Ventas<span style=\"font-size:'14px'\"><br>Libro Electronico</br></span></html>");

        lblusu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblusu.setForeground(new java.awt.Color(255, 255, 255));
        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Usuario-40.png"))); // NOI18N
        lblusu.setText("Silvana");
        lblusu.setFocusable(false);
        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblNivel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNivel.setForeground(new java.awt.Color(39, 174, 96));
        lblNivel.setText("jLabel2");

        lblPermiso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPermiso.setForeground(new java.awt.Color(39, 174, 96));
        lblPermiso.setText("jLabel2");

        jPanel2.setBackground(new java.awt.Color(39, 174, 96));

        btnBuscarP.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnBuscarP.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Play-32D.png"))); // NOI18N
        btnBuscarP.setText("Iniciar Busqueda");
        btnBuscarP.setContentAreaFilled(false);
        btnBuscarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Año");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mes");

        cbxAnios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxAnios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        cbxAnios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAniosItemStateChanged(evt);
            }
        });

        cbxMeses.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxMeses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMesesItemStateChanged(evt);
            }
        });
        cbxMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMesesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxAnios, 0, 157, Short.MAX_VALUE)
                            .addComponent(cbxMeses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbxAnios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Orden de compra-32.png"))); // NOI18N
        jButton1.setText("Generar Archivo txt");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNivel)
                        .addGap(18, 18, 18)
                        .addComponent(lblPermiso))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNivel)
                    .addComponent(lblPermiso))
                .addGap(18, 18, 18)
                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(571, Short.MAX_VALUE))
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        TB_VENTAS_LE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TB_VENTAS_LE.setRowHeight(35);
        TB_VENTAS_LE.setSelectionBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(TB_VENTAS_LE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if(Mensaje.getText().equalsIgnoreCase("TXT Generado")){
            cargareliminar.setVisible(false);
        }
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        try{
                 BUSCAR_VENTAS_ESTADO_NULL();
                 cargareliminar.setVisible(false);
        }catch(Exception e) {
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(Color.red);
            Mensaje.setText("Seleccione un año y mes valido");
            eli.setVisible(false);
            noeli.setVisible(false);
            Clear_TB_VENTAS_LE();
        }
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        generar_libro_electronico();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxAniosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAniosItemStateChanged
        try{
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.cbxAnios.getSelectedIndex()>0){
                    this.cbxMeses.removeAllItems();
                    Statement sta=conexion.createStatement();
                    String dpto=cbxAnios.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC CAJA_MOSTRAR_MES '"+dpto+"'");

                    while(rs.next()){
                        this.cbxMeses.addItem(rs.getString("MES"));
                        //  this.cbxProvincia.setModel(null);
                    }

                }else{
                    this.cbxMeses.removeAllItems();

                    this.cbxMeses.addItem("Seleccione");
                    
                }
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }

    }//GEN-LAST:event_cbxAniosItemStateChanged

    private void cbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMesesItemStateChanged
        
    }//GEN-LAST:event_cbxMesesItemStateChanged

    private void cbxMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMesesActionPerformed

    public void BUSCAR_VENTAS_ESTADO_NULL(){
                          
            int mes=0;
            if(cbxMeses.getSelectedItem().equals("ENERO")){
                mes=1;
            }else if(cbxMeses.getSelectedItem().equals("FEBRERO")){
                mes=2;
            }else if(cbxMeses.getSelectedItem().equals("MARZO")){
                mes=3;
            }else if(cbxMeses.getSelectedItem().equals("ABRIL")){
                mes=4;
            }else if(cbxMeses.getSelectedItem().equals("MAYO")){
                mes=5;
            }else if(cbxMeses.getSelectedItem().equals("JUNIO")){
                mes=6;
            }else if(cbxMeses.getSelectedItem().equals("JULIO")){
                mes=7;
            }else if(cbxMeses.getSelectedItem().equals("AGOSTO")){
                mes=8;
            }else if(cbxMeses.getSelectedItem().equals("SETIEMBRE")){
                mes=9;
            }else if(cbxMeses.getSelectedItem().equals("OCTUBRE")){
                mes=10;
            }else if(cbxMeses.getSelectedItem().equals("NOVIEMBRE")){
                mes=11;
            }else if(cbxMeses.getSelectedItem().equals("DICIEMBRE")){
                mes=12;
            }
            int A=0;
            A=Integer.parseInt(cbxAnios.getSelectedItem().toString());
        
        
        String consulta="";
        try {
            TB_VENTAS_LE.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Documento","DOCE","TRECE","CATORCE","QUINCE","6"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[16];

            CuentasPorPagarFacturasCabecera obj=new CuentasPorPagarFacturasCabecera();
            consulta="exec LIBRO_ELECTRONICO_VENTAS_LISTAR ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1,mes);
            cmd.setInt(2, A);
            
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
                
                m.addRow(fila);
                c++;
            }
            TB_VENTAS_LE.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            TB_VENTAS_LE.setRowSorter(elQueOrdena);
            this.TB_VENTAS_LE.setModel(m);
            
//            formatoExamen();
        
        }catch (Exception e) {
            System.out.println("Error buscar examen: " + e.getMessage());
        }
    }
    
    public void BUSCAR_VENTAS_ESTADO_C(){
                          
            int mes=0;
            if(cbxMeses.getSelectedItem().equals("ENERO")){
                mes=1;
            }else if(cbxMeses.getSelectedItem().equals("FEBRERO")){
                mes=2;
            }else if(cbxMeses.getSelectedItem().equals("MARZO")){
                mes=3;
            }else if(cbxMeses.getSelectedItem().equals("ABRIL")){
                mes=4;
            }else if(cbxMeses.getSelectedItem().equals("MAYO")){
                mes=5;
            }else if(cbxMeses.getSelectedItem().equals("JUNIO")){
                mes=6;
            }else if(cbxMeses.getSelectedItem().equals("JULIO")){
                mes=7;
            }else if(cbxMeses.getSelectedItem().equals("AGOSTO")){
                mes=8;
            }else if(cbxMeses.getSelectedItem().equals("SETIEMBRE")){
                mes=9;
            }else if(cbxMeses.getSelectedItem().equals("OCTUBRE")){
                mes=10;
            }else if(cbxMeses.getSelectedItem().equals("NOVIEMBRE")){
                mes=11;
            }else if(cbxMeses.getSelectedItem().equals("DICIEMBRE")){
                mes=12;
            }
            int A=0;
            A=Integer.parseInt(cbxAnios.getSelectedItem().toString());
        
        
        String consulta="";
        try {
            TB_VENTAS_LE.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Documento","DOCE","TRECE","CATORCE","QUINCE","6"};
            m1=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m1);
            String fila[]=new String[16];

            CuentasPorPagarFacturasCabecera obj=new CuentasPorPagarFacturasCabecera();
            consulta="exec LIBRO_ELECTRONICO_VENTAS_LISTAR_ESTADO_C ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1,mes);
            cmd.setInt(2, A);
            
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
                
                m1.addRow(fila);
                c++;
            }
            TB_VENTAS_LE.setModel(m1);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m1);
            TB_VENTAS_LE.setRowSorter(elQueOrdena);
            this.TB_VENTAS_LE.setModel(m1);
            
//            formatoExamen();
        
        }catch (Exception e) {
            System.out.println("Error buscar estado C: " + e.getMessage());
        }
    }
    
    public void generar_libro_electronico(){
        try {
            if(TB_VENTAS_LE.getRowCount()>0){
                
                crearArchivo();
                Clear_TB_VENTAS_LE();
                cbxAnios.setSelectedIndex(0);
                cbxMeses.setSelectedIndex(0);
            }else{
                System.out.println("error al crear archivo");
            }
        } catch (Exception e) {
            System.out.println("error generar libro 1: " + e.getMessage());
        }
    }
    
    public boolean crearArchivo(){
        Contabilidad_LE ruc=new Contabilidad_LE();
        boolean retorna = false;
        String anio= (cbxAnios.getSelectedItem().toString());
        String mes= (cbxMeses.getSelectedItem().toString());
        String carp = "C:\\Libro_Electronico\\" + anio + "\\";
        String carp_mes = "C:\\Libro_Electronico\\" + anio + "\\" + mes + "\\" ;
        File crea_carpeta_anio = new File(carp);
        File crea_carpeta_mes = new File(carp_mes);
        if(crea_carpeta_anio.exists()){
            System.out.println("carpeta ya creada");
        }else{
            crear_carpeta();
        }
        if(crea_carpeta_mes.exists()){
            System.out.println("carpeta mes ya existe");
        }else{
            crear_carpeta_mes();
        }
        
        
        String ubicacion = "C:\\Libro_Electronico\\" + anio + "\\" + mes + "\\";
        
        String archivo = "LE"+ruc.factura_ruc() + "20171000" + 
                "140100" + 
                "00" + "1" +
                "1" + "1" + 
                "1" + ".TXT";
        File crea_archivo = new File(archivo);
        if(TB_VENTAS_LE.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"No hay registros");
        } else {
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                    retorna = false;
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                    if(TB_VENTAS_LE.getRowCount()>0){
                        String bloc1 = "";
                        for (int c = 0; c < TB_VENTAS_LE.getRowCount(); c++){   
                            bloc1 = bloc1 + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 0)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 1)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 2)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 3)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 4)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 5)) + "|" + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 6)) + "|" + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 7)) + "|" + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 8)) + "|" + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 9)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 10)) + "|" + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 11)) + "|" + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 12)) + "|" + 
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 13)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 14)) + "|" +
                            String.valueOf(TB_VENTAS_LE.getValueAt(c, 15)) + "\r\n";
                            
                            System.out.println("datos guardados txt");
                            Contabilidad_LE ER=new Contabilidad_LE();
                            ER.LE_CAMBIAR_ESTADO_DOCUMENTO_CAJA(Integer.parseInt(String.valueOf(TB_VENTAS_LE.getValueAt(c, 0))));
                            
                        }
                        crea.format(bloc1);
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(164,192,79));
                        Mensaje.setText("TXT Generado");
                        noeli.setVisible(false);
                        eli.setText("OK");
                    } 
                    crea.close();
                    retorna = true;
                }   
            } catch (Exception e) {
                    retorna = false;
                    System.out.println("erro cargar datos " + e.getMessage());
            }
        }
        return retorna;
    } 
    
    public void crear_carpeta(){
        String A = cbxAnios.getSelectedItem().toString();
        String carpeta = "C:\\Libro_Electronico\\" + A ;
        
        File crea_carpeta = new File(carpeta);
        if(crea_carpeta.exists()){
            System.out.println("la carpeta ya existe");
        }else{
            crea_carpeta.mkdirs();
            try {
                if(crea_carpeta.createNewFile()){
                    System.out.println("carpeta creada");
                }
            } catch (Exception e) {
                System.out.println("error crear carpetas" + e.getMessage());
            }
        }
    }
    
    public void crear_carpeta_mes(){
        String M = cbxMeses.getSelectedItem().toString();
        String A = cbxAnios.getSelectedItem().toString();
        String carpeta = "C:\\Libro_Electronico\\" + A + "\\" + M ;
        
        File crea_carpeta_mes = new File(carpeta);
        if(crea_carpeta_mes.exists()){
            System.out.println("la carpeta mes ya existe");
        }else{
            crea_carpeta_mes.mkdirs();
            try {
                if(crea_carpeta_mes.createNewFile()){
                    System.out.println("carpeta mes creada");
                }
            } catch (Exception e) {
            }
        }
    }
    
    public DefaultComboBoxModel Anio(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("CAJA_MOSTRAR_AÑO"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "ANIO" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    private void Clear_TB_VENTAS_LE(){
        DefaultTableModel modelo1 = (DefaultTableModel)TB_VENTAS_LE.getModel(); 
        int b=TB_VENTAS_LE.getRowCount();
        for(int j=0;j<b;j++){
                    modelo1.removeRow(0);
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
            java.util.logging.Logger.getLogger(Contabilidad_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contabilidad_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contabilidad_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contabilidad_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contabilidad_Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JTable TB_VENTAS_LE;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxAnios;
    private javax.swing.JComboBox cbxMeses;
    private javax.swing.JButton eli;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblNivel;
    public static javax.swing.JLabel lblPermiso;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    // End of variables declaration//GEN-END:variables
}
