/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import Servicios.Conexion;
import static Vistas.Caja.Caja_CPTS.btnLista;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import modelo.Caja.Caja_Farmacos;

/**
 *
 * @author Administrador
 */
public class Caja_Farmacia extends javax.swing.JFrame {
ResultSet r;
Conexion c=new Conexion();
Connection ConexionS=c.conectar();
    /**
     * Creates new form Caja_Farmacia
     */
    public Caja_Farmacia() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        Caja_Farmacos CF = new Caja_Farmacos();
        CF.LISTA_FARMACOS("",tb_FARMACIA);
        cbxFormaPago.setBackground(Color.WHITE);
        this.cbxFormaPago.setModel(CargarFP());
        panelIGV.setVisible(false);
        btnguardar.setEnabled(false);
        cargareliminar.setVisible(false);
    }
    
    public DefaultComboBoxModel CargarFP(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = ConexionS.createStatement();
              r = st.executeQuery ("EXEC CAJA_TIPO_IGV"); 
//              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "DESCRIPCION" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "ERROR CARGAR TIPO IGV :" + ex.getMessage() );
        }        
        return listmodel;
    }
    public void CARGAR(){
        int fila = tb_FARMACIA.getSelectedRow();
        lblID_CPT.setText(String.valueOf(tb_FARMACIA.getValueAt(fila, 4)));
        jLabel4.setText(String.valueOf(tb_FARMACIA.getValueAt(fila, 1)));
        cbxFormaPago.setSelectedItem(String.valueOf(tb_FARMACIA.getValueAt(fila, 2)));  
    }
    
    public void MODIFICAR_REGISTRO(){
        Caja_Farmacos cno2 = new Caja_Farmacos();
        cno2.DATOS_FARMACOS(cbxFormaPago.getSelectedItem().toString());        
        Caja_Farmacos cno1 = new Caja_Farmacos();
        cno1.setID_CPT(Integer.parseInt(lblID_CPT.getText()));
        cno1.setID_GRUPO(Integer.parseInt(lblID_GRUPO.getText()));
                    if(cno1.CAMBIO_GRUPO()==true){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Datos Actualizados de forma correcta");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                        btnguardar.setEnabled(false);
                        btneditar.setEnabled(false);
                        Caja_Farmacos A = new Caja_Farmacos();
                        A.LISTA_FARMACOS("",tb_FARMACIA);
                        jLabel33.setText("Listado");
                        panelIGV.setVisible(false);
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
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
        jLabel1 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        buscartodo = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        lbldetalle = new javax.swing.JLabel();
        lblEditar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_FARMACIA = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            panelIGV = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            cbxFormaPago = new javax.swing.JComboBox();
            jLabel4 = new javax.swing.JLabel();
            lblID_GRUPO = new javax.swing.JLabel();
            lblID_CPT = new javax.swing.JLabel();
            cargareliminar = new javax.swing.JPanel();
            Mensaje = new javax.swing.JLabel();
            eli = new javax.swing.JButton();
            noeli = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(39, 174, 96));
            jPanel1.setPreferredSize(new java.awt.Dimension(284, 678));
            jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jPanel1MouseEntered(evt);
                }
            });

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("<html>Farmacia<span style=\"font-size:'14px'\"><br>Sismed</br></span></html>");

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
            lbldetalle.setText("Descripción");

            lblEditar.setForeground(new java.awt.Color(23, 160, 134));
            lblEditar.setText("0");

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("<HTML>Catálogo de farmacia, <BR>Provisto por SISMED</HTML>");

            jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel15.setForeground(new java.awt.Color(255, 255, 255));
            jLabel15.setText("Copyright © 2016 - MINSA");

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
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbldetalle)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addGap(25, 25, 25)
                    .addComponent(btneditar)
                    .addGap(18, 18, 18)
                    .addComponent(btnguardar)
                    .addGap(57, 57, 57)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel15)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
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

            jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tb_FARMACIA.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            tb_FARMACIA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            tb_FARMACIA.setModel(new javax.swing.table.DefaultTableModel(
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
            tb_FARMACIA.setGridColor(new java.awt.Color(255, 255, 255));
            tb_FARMACIA.setRowHeight(25);
            tb_FARMACIA.setSelectionBackground(new java.awt.Color(102, 102, 102));
            tb_FARMACIA.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_FARMACIAMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tb_FARMACIAMousePressed(evt);
                }
            });
            tb_FARMACIA.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_FARMACIAKeyPressed(evt);
                }
            });
            jScrollPane3.setViewportView(tb_FARMACIA);

            panelIGV.setBackground(new java.awt.Color(255, 255, 255));

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setText("Tipo");

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

            jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
            jLabel4.setText("jLabel4");

            lblID_GRUPO.setForeground(new java.awt.Color(255, 255, 255));
            lblID_GRUPO.setText("jLabel5");

            lblID_CPT.setForeground(new java.awt.Color(255, 255, 255));
            lblID_CPT.setText("jLabel5");

            javax.swing.GroupLayout panelIGVLayout = new javax.swing.GroupLayout(panelIGV);
            panelIGV.setLayout(panelIGVLayout);
            panelIGVLayout.setHorizontalGroup(
                panelIGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIGVLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelIGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelIGVLayout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(panelIGVLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(29, 29, 29)
                            .addComponent(cbxFormaPago, 0, 226, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(lblID_GRUPO)
                            .addGap(18, 18, 18)
                            .addComponent(lblID_CPT)
                            .addGap(144, 144, 144))))
            );
            panelIGVLayout.setVerticalGroup(
                panelIGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIGVLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(jLabel4)
                    .addGap(18, 18, 18)
                    .addGroup(panelIGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblID_GRUPO)
                        .addComponent(lblID_CPT))
                    .addContainerGap(33, Short.MAX_VALUE))
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
                    .addGap(19, 19, 19)
                    .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(Mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                        .addComponent(panelIGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panelIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Caja_Farmacos CF = new Caja_Farmacos();
        CF.LISTA_FARMACOS(buscartodo.getText(),tb_FARMACIA);
        jLabel33.setText("Resultados de la Busqueda");
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        //        panelH.setVisible(false);
        //        panelV.setVisible(false);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void tb_FARMACIAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_FARMACIAMouseClicked
        if(evt.getClickCount()==2){
            jLabel33.setText("Edición");
            panelIGV.setVisible(true);
            btneditar.setEnabled(false);
            btnguardar.setEnabled(true);
        }
        if(evt.getClickCount()==1){
            CARGAR();
            jLabel33.setText("Listado");
            panelIGV.setVisible(false);
            cargareliminar.setVisible(false);
            btneditar.setEnabled(true);
            btnguardar.setEnabled(false);
        }
       
    }//GEN-LAST:event_tb_FARMACIAMouseClicked

    private void tb_FARMACIAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_FARMACIAMousePressed

    }//GEN-LAST:event_tb_FARMACIAMousePressed

    private void tb_FARMACIAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_FARMACIAKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
           jLabel33.setText("Edición");
            panelIGV.setVisible(true);
            CARGAR();
            btneditar.setEnabled(false);
            btnguardar.setEnabled(true);
            cargareliminar.setVisible(false);
        }
    }//GEN-LAST:event_tb_FARMACIAKeyPressed

    private void cbxFormaPagoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxFormaPagoMouseReleased

    }//GEN-LAST:event_cbxFormaPagoMouseReleased

    private void cbxFormaPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFormaPagoItemStateChanged
      
    }//GEN-LAST:event_cbxFormaPagoItemStateChanged

    private void cbxFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFormaPagoActionPerformed
     
    }//GEN-LAST:event_cbxFormaPagoActionPerformed

    private void cbxFormaPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxFormaPagoKeyPressed
        
    }//GEN-LAST:event_cbxFormaPagoKeyPressed

    private void cbxFormaPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxFormaPagoKeyTyped

    }//GEN-LAST:event_cbxFormaPagoKeyTyped

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        jLabel33.setText("Edición");
        panelIGV.setVisible(true);
        btnguardar.setEnabled(true);
        Caja_Farmacos cno2 = new Caja_Farmacos();
        cno2.DATOS_FARMACOS(cbxFormaPago.getSelectedItem().toString());
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        
        MODIFICAR_REGISTRO();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Farmacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Farmacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Farmacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Farmacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Farmacia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxFormaPago;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel23;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblID_CPT;
    public static javax.swing.JLabel lblID_GRUPO;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelIGV;
    private javax.swing.JTable tb_FARMACIA;
    // End of variables declaration//GEN-END:variables
}
