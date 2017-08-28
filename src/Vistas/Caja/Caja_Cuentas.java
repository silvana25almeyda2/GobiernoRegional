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
import modelo.Caja.Caja_Jerarquias;
/**
 *
 * @author MYS1
 */
public class Caja_Cuentas extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
byte tge;
Caja_Jerarquias cnn = new Caja_Jerarquias();
    /**
     * Creates new form Caja_Jerarquia
     */
     public Caja_Cuentas() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new Color(255,255,255)); 
         setLocationRelativeTo(null);//en el centro
         cnn.LISTARNIVEL0(tbNivel0);
         tbNivel0.getTableHeader().setVisible(false);
         tbNivel0.setTableHeader(null);
         tbNivel1.getTableHeader().setVisible(false);
         tbNivel1.setTableHeader(null);
         tbNivel2.getTableHeader().setVisible(false);
         tbNivel2.setTableHeader(null);
         
        
//         formatoRelacion();
//         LISTAR();
//         formato();
        panelAgregar.setVisible(false);
        panelNuevoN1.setBackground(new Color(240,240,240)); 
        panelNuevoN2.setBackground(new Color(240,240,240)); 
        panelNuevoN3.setBackground(new Color(240,240,240)); 
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
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
                Caja_Jerarquias cno1 = new Caja_Jerarquias();
//                cno1.setCod_jerar_forma_pago(txtcodigo.getText());//
                cno1.setNom_forma_pago(txtFormaPago.getText());//
                cno1.setDescri_forma_pago(txtFormaPago.getText());//
                if(lblNivel.getText().equals("0")){
                    cno1.setRelacion_forma_pago("0");//    
                }else 
                    if(!lblNivel.getText().equals("0")){
                    cno1.setRelacion_forma_pago(lblCodigo.getText());//    
                }
                cno1.setNivel_forma_pago(lblNivel.getText());//
                cno1.setTipo_estado_pago("P(Pendiente)");//
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
                           panelAgregar.setVisible(false);
                           if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }else if(lblNivel.getText().equals("1")){
                             cnn.LISTARNIVEL1(lblCodigo.getText(),tbNivel1);
                                  panelNuevoN2.setBackground(new Color(240,240,240)); 
                                  panelNuevoN3.setBackground(new Color(240,240,240)); 
                           }else if(lblNivel.getText().equals("2")){
                             cnn.LISTARNIVEL2(lblCodigo.getText(),tbNivel2);
                                  panelNuevoN3.setBackground(new Color(240,240,240)); 
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
                        cno.setCod_jerar_forma_pago(lblCodigoAE.getText());//
                        cno.setNom_forma_pago(txtFormaPago.getText());//
                        cno.setDescri_forma_pago(txtFormaPago.getText());//
                        if(lblNivel.getText().equals("0")){
                            cno.setRelacion_forma_pago("0");//    
                        }else 
                            if(!lblNivel.getText().equals("0")){
                            cno.setRelacion_forma_pago(lblCodigo.getText());//    
                        }
                        cno.setNivel_forma_pago(lblNivel.getText());//
                        cno.setTipo_estado_pago("P(Pendiente)");//
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
                            panelAgregar.setVisible(false);
                            tge=9;
                            if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }else if(lblNivel.getText().equals("1")){
                             cnn.LISTARNIVEL1(lblCodigo.getText(),tbNivel1);
                        
                           }else if(lblNivel.getText().equals("2")){
                             cnn.LISTARNIVEL2(lblCodigo.getText(),tbNivel2);
        
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

        jPanel1 = new javax.swing.JPanel();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        cargareliminar = new javax.swing.JPanel();
        Mensaje = new javax.swing.JLabel();
        eli = new javax.swing.JButton();
        noeli = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        panelNivel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbNivel0 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            panelAgregar = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            txtFormaPago = new javax.swing.JTextField();
            codpago = new javax.swing.JLabel();
            lblCodigo = new javax.swing.JLabel();
            lblNivel = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            lblCodigoAE = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbNivel1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                panelNivel2 = new javax.swing.JPanel();
                jLabel7 = new javax.swing.JLabel();
                panelNuevoN1 = new javax.swing.JPanel();
                btnNuevo = new javax.swing.JButton();
                panelNuevoN3 = new javax.swing.JPanel();
                btnNuevo2 = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                jScrollPane6 = new javax.swing.JScrollPane();
                tbNivel2 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    panelNuevoN2 = new javax.swing.JPanel();
                    btnNuevo1 = new javax.swing.JButton();
                    jPanel2 = new javax.swing.JPanel();
                    jScrollPane7 = new javax.swing.JScrollPane();
                    tbNivel3 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        panelNuevoN4 = new javax.swing.JPanel();
                        btnNuevo3 = new javax.swing.JButton();
                        jPanel7 = new javax.swing.JPanel();
                        panelNivel3 = new javax.swing.JPanel();
                        jLabel8 = new javax.swing.JLabel();
                        jScrollPane8 = new javax.swing.JScrollPane();
                        tbNivel4 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            panelNuevoN5 = new javax.swing.JPanel();
                            btnNuevo4 = new javax.swing.JButton();
                            jPanel8 = new javax.swing.JPanel();
                            panelNivel4 = new javax.swing.JPanel();
                            jLabel9 = new javax.swing.JLabel();
                            jScrollPane9 = new javax.swing.JScrollPane();
                            tbNivel5 = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                panelNuevoN6 = new javax.swing.JPanel();
                                btnNuevo5 = new javax.swing.JButton();
                                jPanel9 = new javax.swing.JPanel();
                                panelNivel5 = new javax.swing.JPanel();
                                jLabel10 = new javax.swing.JLabel();
                                jScrollPane10 = new javax.swing.JScrollPane();
                                tbNivel6 = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    panelNuevoN7 = new javax.swing.JPanel();
                                    btnNuevo6 = new javax.swing.JButton();
                                    jPanel10 = new javax.swing.JPanel();
                                    panelNivel6 = new javax.swing.JPanel();
                                    jLabel11 = new javax.swing.JLabel();

                                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                                    jPanel1.setBackground(new java.awt.Color(41, 127, 184));

                                    btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btneditar.setForeground(new java.awt.Color(240, 240, 240));
                                    btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Editar-32.png"))); // NOI18N
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
                                    btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Guardar-32.png"))); // NOI18N
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
                                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Basura-32.png"))); // NOI18N
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

                                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                    lblusu.setText("Silvana");
                                    lblusu.setFocusable(false);
                                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                    jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                                    jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel57.setText("<html>Jerarquías<span style=\"font-size:'14px'\"><br>Formas de Pago</br></span></html>");

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
                                                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 41, Short.MAX_VALUE))))
                                    );
                                    jPanel1Layout.setVerticalGroup(
                                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(70, 70, 70)
                                            .addComponent(btnguardar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btneditar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btneliminar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0))
                                    );

                                    jPanel5.setBackground(new java.awt.Color(43, 43, 43));
                                    jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

                                    jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel33.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel33.setText("Árbol de Jerarquias");

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
                                            .addGap(19, 19, 19)
                                            .addComponent(Mensaje)
                                            .addGap(46, 46, 46)
                                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(505, Short.MAX_VALUE))
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

                                    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                    panelNivel1.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel6.setText("CUENTA 2");

                                    javax.swing.GroupLayout panelNivel1Layout = new javax.swing.GroupLayout(panelNivel1);
                                    panelNivel1.setLayout(panelNivel1Layout);
                                    panelNivel1Layout.setHorizontalGroup(
                                        panelNivel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    );
                                    panelNivel1Layout.setVerticalGroup(
                                        panelNivel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNivel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel6)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                    lblNivel.setForeground(new java.awt.Color(51, 51, 51));
                                    lblNivel.setText("jLabel2");

                                    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                                    jLabel2.setText("Agregar al Nivel");

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
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

                                    jPanel6.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel1.setText("CUENTA 1");

                                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                    jPanel6.setLayout(jPanel6Layout);
                                    jPanel6Layout.setHorizontalGroup(
                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    );
                                    jPanel6Layout.setVerticalGroup(
                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel1)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );

                                    jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
                                    jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                    tbNivel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    tbNivel1.setForeground(new java.awt.Color(51, 51, 51));
                                    tbNivel1.setModel(new javax.swing.table.DefaultTableModel(
                                        new Object [][] {
                                            {},
                                            {},
                                            {},
                                            {}
                                        },
                                        new String [] {

                                        }
                                    ));
                                    tbNivel1.setGridColor(new java.awt.Color(255, 255, 255));
                                    tbNivel1.setRowHeight(25);
                                    tbNivel1.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                    tbNivel1.getTableHeader().setReorderingAllowed(false);
                                    tbNivel1.addMouseListener(new java.awt.event.MouseAdapter() {
                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                            tbNivel1MouseClicked(evt);
                                        }
                                    });
                                    tbNivel1.addKeyListener(new java.awt.event.KeyAdapter() {
                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                            tbNivel1KeyPressed(evt);
                                        }
                                    });
                                    jScrollPane5.setViewportView(tbNivel1);

                                    panelNivel2.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel7.setText("CUENTA 7");

                                    javax.swing.GroupLayout panelNivel2Layout = new javax.swing.GroupLayout(panelNivel2);
                                    panelNivel2.setLayout(panelNivel2Layout);
                                    panelNivel2Layout.setHorizontalGroup(
                                        panelNivel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    );
                                    panelNivel2Layout.setVerticalGroup(
                                        panelNivel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNivel2Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel7)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );

                                    panelNuevoN1.setBackground(new java.awt.Color(43, 43, 43));

                                    btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Documento-32.png"))); // NOI18N
                                    btnNuevo.setText("Agregar");
                                    btnNuevo.setContentAreaFilled(false);
                                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                    btnNuevo.setIconTextGap(30);
                                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnNuevoActionPerformed(evt);
                                        }
                                    });

                                    javax.swing.GroupLayout panelNuevoN1Layout = new javax.swing.GroupLayout(panelNuevoN1);
                                    panelNuevoN1.setLayout(panelNuevoN1Layout);
                                    panelNuevoN1Layout.setHorizontalGroup(
                                        panelNuevoN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoN1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                                    );
                                    panelNuevoN1Layout.setVerticalGroup(
                                        panelNuevoN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    );

                                    panelNuevoN3.setBackground(new java.awt.Color(43, 43, 43));

                                    btnNuevo2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnNuevo2.setForeground(new java.awt.Color(240, 240, 240));
                                    btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                    btnNuevo2.setText("Agregar");
                                    btnNuevo2.setContentAreaFilled(false);
                                    btnNuevo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnNuevo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                    btnNuevo2.setIconTextGap(30);
                                    btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnNuevo2ActionPerformed(evt);
                                        }
                                    });

                                    jPanel3.setPreferredSize(new java.awt.Dimension(1, 0));

                                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                    jPanel3.setLayout(jPanel3Layout);
                                    jPanel3Layout.setHorizontalGroup(
                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 1, Short.MAX_VALUE)
                                    );
                                    jPanel3Layout.setVerticalGroup(
                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                    );

                                    javax.swing.GroupLayout panelNuevoN3Layout = new javax.swing.GroupLayout(panelNuevoN3);
                                    panelNuevoN3.setLayout(panelNuevoN3Layout);
                                    panelNuevoN3Layout.setHorizontalGroup(
                                        panelNuevoN3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNuevoN3Layout.createSequentialGroup()
                                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addContainerGap())
                                    );
                                    panelNuevoN3Layout.setVerticalGroup(
                                        panelNuevoN3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    );

                                    jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
                                    jScrollPane6.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                    tbNivel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    tbNivel2.setForeground(new java.awt.Color(51, 51, 51));
                                    tbNivel2.setModel(new javax.swing.table.DefaultTableModel(
                                        new Object [][] {
                                            {},
                                            {},
                                            {},
                                            {}
                                        },
                                        new String [] {

                                        }
                                    ));
                                    tbNivel2.setGridColor(new java.awt.Color(255, 255, 255));
                                    tbNivel2.setRowHeight(25);
                                    tbNivel2.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                    tbNivel2.getTableHeader().setReorderingAllowed(false);
                                    tbNivel2.addMouseListener(new java.awt.event.MouseAdapter() {
                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                            tbNivel2MouseClicked(evt);
                                        }
                                    });
                                    tbNivel2.addKeyListener(new java.awt.event.KeyAdapter() {
                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                            tbNivel2KeyPressed(evt);
                                        }
                                    });
                                    jScrollPane6.setViewportView(tbNivel2);

                                    panelNuevoN2.setBackground(new java.awt.Color(43, 43, 43));

                                    btnNuevo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnNuevo1.setForeground(new java.awt.Color(240, 240, 240));
                                    btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                    btnNuevo1.setText("Agregar");
                                    btnNuevo1.setContentAreaFilled(false);
                                    btnNuevo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnNuevo1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                    btnNuevo1.setIconTextGap(30);
                                    btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnNuevo1ActionPerformed(evt);
                                        }
                                    });

                                    jPanel2.setPreferredSize(new java.awt.Dimension(1, 0));

                                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                    jPanel2.setLayout(jPanel2Layout);
                                    jPanel2Layout.setHorizontalGroup(
                                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 1, Short.MAX_VALUE)
                                    );
                                    jPanel2Layout.setVerticalGroup(
                                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                    );

                                    javax.swing.GroupLayout panelNuevoN2Layout = new javax.swing.GroupLayout(panelNuevoN2);
                                    panelNuevoN2.setLayout(panelNuevoN2Layout);
                                    panelNuevoN2Layout.setHorizontalGroup(
                                        panelNuevoN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoN2Layout.createSequentialGroup()
                                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addContainerGap())
                                    );
                                    panelNuevoN2Layout.setVerticalGroup(
                                        panelNuevoN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    );

                                    jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
                                    jScrollPane7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                    tbNivel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    tbNivel3.setForeground(new java.awt.Color(51, 51, 51));
                                    tbNivel3.setModel(new javax.swing.table.DefaultTableModel(
                                        new Object [][] {
                                            {},
                                            {},
                                            {},
                                            {}
                                        },
                                        new String [] {

                                        }
                                    ));
                                    tbNivel3.setGridColor(new java.awt.Color(255, 255, 255));
                                    tbNivel3.setRowHeight(25);
                                    tbNivel3.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                    tbNivel3.getTableHeader().setReorderingAllowed(false);
                                    tbNivel3.addMouseListener(new java.awt.event.MouseAdapter() {
                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                            tbNivel3MouseClicked(evt);
                                        }
                                    });
                                    tbNivel3.addKeyListener(new java.awt.event.KeyAdapter() {
                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                            tbNivel3KeyPressed(evt);
                                        }
                                    });
                                    jScrollPane7.setViewportView(tbNivel3);

                                    panelNuevoN4.setBackground(new java.awt.Color(43, 43, 43));

                                    btnNuevo3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnNuevo3.setForeground(new java.awt.Color(240, 240, 240));
                                    btnNuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                    btnNuevo3.setText("Agregar");
                                    btnNuevo3.setContentAreaFilled(false);
                                    btnNuevo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnNuevo3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                    btnNuevo3.setIconTextGap(30);
                                    btnNuevo3.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnNuevo3ActionPerformed(evt);
                                        }
                                    });

                                    jPanel7.setPreferredSize(new java.awt.Dimension(1, 0));

                                    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                                    jPanel7.setLayout(jPanel7Layout);
                                    jPanel7Layout.setHorizontalGroup(
                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 1, Short.MAX_VALUE)
                                    );
                                    jPanel7Layout.setVerticalGroup(
                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                    );

                                    javax.swing.GroupLayout panelNuevoN4Layout = new javax.swing.GroupLayout(panelNuevoN4);
                                    panelNuevoN4.setLayout(panelNuevoN4Layout);
                                    panelNuevoN4Layout.setHorizontalGroup(
                                        panelNuevoN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoN4Layout.createSequentialGroup()
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addContainerGap())
                                    );
                                    panelNuevoN4Layout.setVerticalGroup(
                                        panelNuevoN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    );

                                    panelNivel3.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel8.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel8.setText("CUENTA 3");

                                    javax.swing.GroupLayout panelNivel3Layout = new javax.swing.GroupLayout(panelNivel3);
                                    panelNivel3.setLayout(panelNivel3Layout);
                                    panelNivel3Layout.setHorizontalGroup(
                                        panelNivel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    );
                                    panelNivel3Layout.setVerticalGroup(
                                        panelNivel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNivel3Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel8)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );

                                    jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
                                    jScrollPane8.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                    tbNivel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    tbNivel4.setForeground(new java.awt.Color(51, 51, 51));
                                    tbNivel4.setModel(new javax.swing.table.DefaultTableModel(
                                        new Object [][] {
                                            {},
                                            {},
                                            {},
                                            {}
                                        },
                                        new String [] {

                                        }
                                    ));
                                    tbNivel4.setGridColor(new java.awt.Color(255, 255, 255));
                                    tbNivel4.setRowHeight(25);
                                    tbNivel4.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                    tbNivel4.getTableHeader().setReorderingAllowed(false);
                                    tbNivel4.addMouseListener(new java.awt.event.MouseAdapter() {
                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                            tbNivel4MouseClicked(evt);
                                        }
                                    });
                                    tbNivel4.addKeyListener(new java.awt.event.KeyAdapter() {
                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                            tbNivel4KeyPressed(evt);
                                        }
                                    });
                                    jScrollPane8.setViewportView(tbNivel4);

                                    panelNuevoN5.setBackground(new java.awt.Color(43, 43, 43));

                                    btnNuevo4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnNuevo4.setForeground(new java.awt.Color(240, 240, 240));
                                    btnNuevo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                    btnNuevo4.setText("Agregar");
                                    btnNuevo4.setContentAreaFilled(false);
                                    btnNuevo4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnNuevo4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                    btnNuevo4.setIconTextGap(30);
                                    btnNuevo4.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnNuevo4ActionPerformed(evt);
                                        }
                                    });

                                    jPanel8.setPreferredSize(new java.awt.Dimension(1, 0));

                                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                    jPanel8.setLayout(jPanel8Layout);
                                    jPanel8Layout.setHorizontalGroup(
                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 1, Short.MAX_VALUE)
                                    );
                                    jPanel8Layout.setVerticalGroup(
                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                    );

                                    javax.swing.GroupLayout panelNuevoN5Layout = new javax.swing.GroupLayout(panelNuevoN5);
                                    panelNuevoN5.setLayout(panelNuevoN5Layout);
                                    panelNuevoN5Layout.setHorizontalGroup(
                                        panelNuevoN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoN5Layout.createSequentialGroup()
                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addContainerGap())
                                    );
                                    panelNuevoN5Layout.setVerticalGroup(
                                        panelNuevoN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    );

                                    panelNivel4.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel9.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel9.setText("CUENTA 4");

                                    javax.swing.GroupLayout panelNivel4Layout = new javax.swing.GroupLayout(panelNivel4);
                                    panelNivel4.setLayout(panelNivel4Layout);
                                    panelNivel4Layout.setHorizontalGroup(
                                        panelNivel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    );
                                    panelNivel4Layout.setVerticalGroup(
                                        panelNivel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNivel4Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel9)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );

                                    jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));
                                    jScrollPane9.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                    tbNivel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    tbNivel5.setForeground(new java.awt.Color(51, 51, 51));
                                    tbNivel5.setModel(new javax.swing.table.DefaultTableModel(
                                        new Object [][] {
                                            {},
                                            {},
                                            {},
                                            {}
                                        },
                                        new String [] {

                                        }
                                    ));
                                    tbNivel5.setGridColor(new java.awt.Color(255, 255, 255));
                                    tbNivel5.setRowHeight(25);
                                    tbNivel5.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                    tbNivel5.getTableHeader().setReorderingAllowed(false);
                                    tbNivel5.addMouseListener(new java.awt.event.MouseAdapter() {
                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                            tbNivel5MouseClicked(evt);
                                        }
                                    });
                                    tbNivel5.addKeyListener(new java.awt.event.KeyAdapter() {
                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                            tbNivel5KeyPressed(evt);
                                        }
                                    });
                                    jScrollPane9.setViewportView(tbNivel5);

                                    panelNuevoN6.setBackground(new java.awt.Color(43, 43, 43));

                                    btnNuevo5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnNuevo5.setForeground(new java.awt.Color(240, 240, 240));
                                    btnNuevo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                    btnNuevo5.setText("Agregar");
                                    btnNuevo5.setContentAreaFilled(false);
                                    btnNuevo5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnNuevo5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                    btnNuevo5.setIconTextGap(30);
                                    btnNuevo5.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnNuevo5ActionPerformed(evt);
                                        }
                                    });

                                    jPanel9.setPreferredSize(new java.awt.Dimension(1, 0));

                                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                    jPanel9.setLayout(jPanel9Layout);
                                    jPanel9Layout.setHorizontalGroup(
                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 1, Short.MAX_VALUE)
                                    );
                                    jPanel9Layout.setVerticalGroup(
                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                    );

                                    javax.swing.GroupLayout panelNuevoN6Layout = new javax.swing.GroupLayout(panelNuevoN6);
                                    panelNuevoN6.setLayout(panelNuevoN6Layout);
                                    panelNuevoN6Layout.setHorizontalGroup(
                                        panelNuevoN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoN6Layout.createSequentialGroup()
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addContainerGap())
                                    );
                                    panelNuevoN6Layout.setVerticalGroup(
                                        panelNuevoN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    );

                                    panelNivel5.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel10.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel10.setText("CUENTA 5");

                                    javax.swing.GroupLayout panelNivel5Layout = new javax.swing.GroupLayout(panelNivel5);
                                    panelNivel5.setLayout(panelNivel5Layout);
                                    panelNivel5Layout.setHorizontalGroup(
                                        panelNivel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    );
                                    panelNivel5Layout.setVerticalGroup(
                                        panelNivel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNivel5Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel10)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );

                                    jScrollPane10.setBackground(new java.awt.Color(255, 255, 255));
                                    jScrollPane10.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                    tbNivel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                    tbNivel6.setForeground(new java.awt.Color(51, 51, 51));
                                    tbNivel6.setModel(new javax.swing.table.DefaultTableModel(
                                        new Object [][] {
                                            {},
                                            {},
                                            {},
                                            {}
                                        },
                                        new String [] {

                                        }
                                    ));
                                    tbNivel6.setGridColor(new java.awt.Color(255, 255, 255));
                                    tbNivel6.setRowHeight(25);
                                    tbNivel6.setSelectionBackground(new java.awt.Color(102, 102, 102));
                                    tbNivel6.getTableHeader().setReorderingAllowed(false);
                                    tbNivel6.addMouseListener(new java.awt.event.MouseAdapter() {
                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                            tbNivel6MouseClicked(evt);
                                        }
                                    });
                                    tbNivel6.addKeyListener(new java.awt.event.KeyAdapter() {
                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                            tbNivel6KeyPressed(evt);
                                        }
                                    });
                                    jScrollPane10.setViewportView(tbNivel6);

                                    panelNuevoN7.setBackground(new java.awt.Color(43, 43, 43));

                                    btnNuevo6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                    btnNuevo6.setForeground(new java.awt.Color(240, 240, 240));
                                    btnNuevo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                    btnNuevo6.setText("Agregar");
                                    btnNuevo6.setContentAreaFilled(false);
                                    btnNuevo6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                    btnNuevo6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                    btnNuevo6.setIconTextGap(30);
                                    btnNuevo6.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            btnNuevo6ActionPerformed(evt);
                                        }
                                    });

                                    jPanel10.setPreferredSize(new java.awt.Dimension(1, 0));

                                    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                                    jPanel10.setLayout(jPanel10Layout);
                                    jPanel10Layout.setHorizontalGroup(
                                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 1, Short.MAX_VALUE)
                                    );
                                    jPanel10Layout.setVerticalGroup(
                                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                    );

                                    javax.swing.GroupLayout panelNuevoN7Layout = new javax.swing.GroupLayout(panelNuevoN7);
                                    panelNuevoN7.setLayout(panelNuevoN7Layout);
                                    panelNuevoN7Layout.setHorizontalGroup(
                                        panelNuevoN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoN7Layout.createSequentialGroup()
                                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addContainerGap())
                                    );
                                    panelNuevoN7Layout.setVerticalGroup(
                                        panelNuevoN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    );

                                    panelNivel6.setBackground(new java.awt.Color(50, 151, 219));

                                    jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                    jLabel11.setForeground(new java.awt.Color(255, 255, 255));
                                    jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                    jLabel11.setText("CUENTA 6");

                                    javax.swing.GroupLayout panelNivel6Layout = new javax.swing.GroupLayout(panelNivel6);
                                    panelNivel6.setLayout(panelNivel6Layout);
                                    panelNivel6Layout.setHorizontalGroup(
                                        panelNivel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    );
                                    panelNivel6Layout.setVerticalGroup(
                                        panelNivel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNivel6Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel11)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );

                                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                    jPanel4.setLayout(jPanel4Layout);
                                    jPanel4Layout.setHorizontalGroup(
                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(0, 0, 0)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(panelAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(panelNuevoN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, 0)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(panelNuevoN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(panelNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, 0)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(panelNuevoN4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(panelNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, 0)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(panelNuevoN5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(panelNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, 0)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(panelNuevoN6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(panelNivel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, 0)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(panelNuevoN7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(panelNivel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, 0)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(panelNivel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(panelNuevoN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    );
                                    jPanel4Layout.setVerticalGroup(
                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(0, 0, 0)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(panelNivel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, 0)
                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(panelNivel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(panelNivel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, 0)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(panelNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(panelNivel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, 0)
                                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(panelNivel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, 0)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                            .addGap(0, 0, 0)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(panelNuevoN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelNuevoN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelNuevoN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelNuevoN4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelNuevoN6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelNuevoN5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelNuevoN7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(panelAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap())
                                    );

                                    jScrollPane1.setViewportView(jPanel4);

                                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                    getContentPane().setLayout(layout);
                                    layout.setHorizontalGroup(
                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)))
                                                    .addContainerGap())))
                                    );
                                    layout.setVerticalGroup(
                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    );

                                    pack();
                                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        panelAgregar.setVisible(true);
        int fila=tbNivel0.getSelectedRow();
        lblCodigo.setText(String.valueOf(tbNivel0.getValueAt(fila, 1)));
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
         txtFormaPago.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         tg=2;
         panelAgregar.setVisible(true);
         if(lblNivel.getText().equals("0")){
                int fila=tbNivel0.getSelectedRow();
                txtFormaPago.setText(String.valueOf(tbNivel0.getValueAt(fila, 0)));
                
            }else  if(lblNivel.getText().equals("1")){
                int fila=tbNivel1.getSelectedRow();
                txtFormaPago.setText(String.valueOf(tbNivel1.getValueAt(fila, 0)));
                
            }else  if(lblNivel.getText().equals("2")){
                int fila=tbNivel2.getSelectedRow();
                txtFormaPago.setText(String.valueOf(tbNivel2.getValueAt(fila, 0)));
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

            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,91,70)); 
            if(lblNivel.getText().equals("0")){
                int fila=tbNivel0.getSelectedRow();
                Mensaje.setText("Desea Eliminar "+String.valueOf(tbNivel0.getValueAt(fila, 0))+" del Nivel "+lblNivel.getText()+"?");
                
            }else  if(lblNivel.getText().equals("1")){
                int fila=tbNivel1.getSelectedRow();
                Mensaje.setText("Desea Eliminar "+String.valueOf(tbNivel1.getValueAt(fila, 0))+" del Nivel "+lblNivel.getText()+"?");
                
            }else  if(lblNivel.getText().equals("2")){
                int fila=tbNivel2.getSelectedRow();
                Mensaje.setText("Desea Eliminar "+String.valueOf(tbNivel2.getValueAt(fila, 0))+" del Nivel "+lblNivel.getText()+"?");
            }
           
            eli.setText("Si");
            eli.setVisible(true);
            noeli.setText("No");
            noeli.setVisible(true);
            tge=6;
    }//GEN-LAST:event_btneliminarActionPerformed

    private void tbNivel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel0MouseClicked
        Caja_Jerarquias cno1 = new Caja_Jerarquias();
        int fila=tbNivel0.getSelectedRow();
        if(evt.getClickCount()==1){
            cno1.LISTARNIVEL1(String.valueOf(tbNivel0.getValueAt(fila, 1)),tbNivel1);
            lblCodigoAE.setText(String.valueOf(tbNivel0.getValueAt(fila, 1)));
            panelNuevoN1.setBackground(new Color(43,43,43)); 
            btnNuevo.setEnabled(true);
            panelNivel1.setVisible(true);
            jScrollPane5.setVisible(true);
            DefaultTableModel modelo1 = (DefaultTableModel)tbNivel2.getModel(); 
            int b=tbNivel2.getRowCount();
            for(int j=0;j<b;j++){
                        modelo1.removeRow(0);
            }
            if(tbNivel1.getRowCount()==0){
              panelNuevoN2.setBackground(new Color(43,43,43)); 
              btnNuevo1.setEnabled(true);
            }else if(tbNivel1.getRowCount()>0){
              panelNuevoN2.setBackground(new Color(240,240,240)); 
              btnNuevo1.setEnabled(false);
            }
            
             
            
            panelAgregar.setVisible(false);
            
            panelNuevoN1.setBackground(new Color(43,43,43)); 
            btnNuevo.setEnabled(true);
            panelNuevoN3.setBackground(new Color(240,240,240)); 
            btnNuevo2.setEnabled(false);
            btneliminar.setEnabled(true);
            btneditar.setEnabled(true);
            lblNivel.setText("0");
        }
    }//GEN-LAST:event_tbNivel0MouseClicked

    private void tbNivel0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel0KeyPressed

    }//GEN-LAST:event_tbNivel0KeyPressed

    private void tbNivel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel1MouseClicked
        Caja_Jerarquias cno1 = new Caja_Jerarquias();
        
        if(evt.getClickCount()==1){
            int fila=tbNivel1.getSelectedRow();
            cno1.LISTARNIVEL2(String.valueOf(tbNivel1.getValueAt(fila, 1)),tbNivel2);
            lblCodigoAE.setText(String.valueOf(tbNivel1.getValueAt(fila, 1)));
            
             if(tbNivel2.getRowCount()==0){
              panelNuevoN3.setBackground(new Color(43,43,43));  
              btnNuevo2.setEnabled(true);

            }else if(tbNivel2.getRowCount()>0){
              panelNuevoN3.setBackground(new Color(240,240,240));  
              btnNuevo2.setEnabled(false);
       
            }
             
                    panelNuevoN2.setBackground(new Color(43,43,43)); 
                    btnNuevo1.setEnabled(true);
                    panelNivel2.setVisible(true);
                    jScrollPane6.setVisible(true);
                    panelAgregar.setVisible(false);
                    panelNuevoN1.setBackground(new Color(240,240,240)); 
                    btnNuevo.setEnabled(false);
                    btneliminar.setEnabled(true);
                    btneditar.setEnabled(true);
                    lblNivel.setText("1");
                    
                     if (tbNivel1.getRowCount()>0){
                        int fila1=tbNivel1.getSelectedRow();
                        lblCodigo.setText(String.valueOf(tbNivel1.getValueAt(fila1, 2))); 
                    }else if (tbNivel1.getRowCount()==0){
                        int fila0=tbNivel0.getSelectedRow();
                        lblCodigo.setText(String.valueOf(tbNivel0.getValueAt(fila0, 1))); 
//            lblCodigo.setText(String.valueOf(tbNivel1.getValueAt(fila0, 2)));
        }
        }
    }//GEN-LAST:event_tbNivel1MouseClicked

    private void tbNivel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel1KeyPressed

    private void tbNivel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel2MouseClicked
               panelNuevoN3.setBackground(new Color(43,43,43)); 
               btnNuevo2.setEnabled(true);
               panelAgregar.setVisible(false);
               panelNuevoN2.setBackground(new Color(43,43,43)); 
               btnNuevo1.setEnabled(true);
               panelNuevoN1.setBackground(new Color(240,240,240)); 
               btnNuevo.setEnabled(false);
               btneliminar.setEnabled(true);
               btneditar.setEnabled(true);
               int fila=tbNivel2.getSelectedRow();
               lblCodigoAE.setText(String.valueOf(tbNivel2.getValueAt(fila, 1)));
               lblNivel.setText("2");
               if (tbNivel2.getRowCount()>0){
                    int fila2=tbNivel2.getSelectedRow();
                    lblCodigo.setText(String.valueOf(tbNivel2.getValueAt(fila2, 2))); 
                }else if (tbNivel2.getRowCount()==0){
                    int fila0=tbNivel1.getSelectedRow();
                    lblCodigo.setText(String.valueOf(tbNivel1.getValueAt(fila0, 1))); 
                }
    }//GEN-LAST:event_tbNivel2MouseClicked

    private void tbNivel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel2KeyPressed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        panelAgregar.setVisible(true);
        
     
        if (tbNivel1.getRowCount()>0){
            int fila=tbNivel1.getSelectedRow();
            lblCodigo.setText(String.valueOf(tbNivel1.getValueAt(fila, 2))); 
        }else if (tbNivel1.getRowCount()==0){
            int fila0=tbNivel0.getSelectedRow();
            lblCodigo.setText(String.valueOf(tbNivel0.getValueAt(fila0, 1))); 
//            lblCodigo.setText(String.valueOf(tbNivel1.getValueAt(fila0, 2)));
        }
        
        lblNivel.setText("1");
        tg=1;
        txtFormaPago.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        txtFormaPago.setText("");
        txtFormaPago.requestFocus();
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        panelAgregar.setVisible(true);

          if (tbNivel2.getRowCount()>0){
            int fila=tbNivel2.getSelectedRow();
            lblCodigo.setText(String.valueOf(tbNivel2.getValueAt(fila, 2))); 
        }else if (tbNivel2.getRowCount()==0){
            int fila0=tbNivel1.getSelectedRow();
            lblCodigo.setText(String.valueOf(tbNivel1.getValueAt(fila0, 1))); 
        }

        lblNivel.setText("2");
        tg=1;
        txtFormaPago.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        txtFormaPago.setText("");
        txtFormaPago.requestFocus();
    }//GEN-LAST:event_btnNuevo2ActionPerformed

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
                if (tbNivel1.getRowCount()==0){
                Caja_Jerarquias hCEl = new Caja_Jerarquias();
                hCEl.setCod_jerar_forma_pago(lblCodigoAE.getText());
                if(hCEl.eliminarjerarquia()){
                     cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=7;
                                 if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }else if(lblNivel.getText().equals("1")){
                             cnn.LISTARNIVEL1(lblCodigo.getText(),tbNivel1);
                           }else if(lblNivel.getText().equals("2")){
                             cnn.LISTARNIVEL2(lblCodigo.getText(),tbNivel2);
                           }
                }   
            }else if (tbNivel1.getRowCount()>0){
                cargareliminar.setBackground(new Color(255,91,70)); 
                Mensaje.setText("Este Nivel Tiene Registros en Otros Niveles, Borre Los Otros Antes");
                eli.setText("OK");
                eli.setVisible(true);
                noeli.setVisible(false);
                tge=7;
                
            }
                
            }else if(lblNivel.getText().equals("1")){
                if (tbNivel2.getRowCount()==0){
                Caja_Jerarquias hCEl = new Caja_Jerarquias();
                hCEl.setCod_jerar_forma_pago(lblCodigoAE.getText());
                if(hCEl.eliminarjerarquia()){
                     cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=7;
                                 if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }else if(lblNivel.getText().equals("1")){
                             cnn.LISTARNIVEL1(lblCodigo.getText(),tbNivel1);
                           }else if(lblNivel.getText().equals("2")){
                             cnn.LISTARNIVEL2(lblCodigo.getText(),tbNivel2);
                           }
                }   
            }else if (tbNivel2.getRowCount()>0){
                cargareliminar.setBackground(new Color(255,91,70)); 
                Mensaje.setText("Este Nivel Tiene Registros en Otros Niveles, Borre Los Otros Antes");
                eli.setText("OK");
                eli.setVisible(true);
                noeli.setVisible(false);
                tge=7;
                
            }
                
            }if(lblNivel.getText().equals("2")){
                Caja_Jerarquias hCEl = new Caja_Jerarquias();
                hCEl.setCod_jerar_forma_pago(lblCodigoAE.getText());
                if(hCEl.eliminarjerarquia()){
                     cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tge=7;
                                 if(lblNivel.getText().equals("0")){
                             cnn.LISTARNIVEL0(tbNivel0);  
                           }else if(lblNivel.getText().equals("1")){
                             cnn.LISTARNIVEL1(lblCodigo.getText(),tbNivel1);
                           }else if(lblNivel.getText().equals("2")){
                             cnn.LISTARNIVEL2(lblCodigo.getText(),tbNivel2);
                           }
                }  
            }
            
            
                
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
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

    private void tbNivel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel3MouseClicked

    private void tbNivel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel3KeyPressed

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void tbNivel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel4MouseClicked

    private void tbNivel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel4KeyPressed

    private void btnNuevo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo4ActionPerformed

    private void tbNivel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel5MouseClicked

    private void tbNivel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel5KeyPressed

    private void btnNuevo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo5ActionPerformed

    private void tbNivel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNivel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel6MouseClicked

    private void tbNivel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNivel6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNivel6KeyPressed

    private void btnNuevo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo6ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Cuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Cuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Cuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Cuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Cuentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnNuevo3;
    private javax.swing.JButton btnNuevo4;
    private javax.swing.JButton btnNuevo5;
    private javax.swing.JButton btnNuevo6;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JLabel codpago;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoAE;
    private javax.swing.JLabel lblNivel;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelAgregar;
    private javax.swing.JPanel panelNivel1;
    private javax.swing.JPanel panelNivel2;
    private javax.swing.JPanel panelNivel3;
    private javax.swing.JPanel panelNivel4;
    private javax.swing.JPanel panelNivel5;
    private javax.swing.JPanel panelNivel6;
    private javax.swing.JPanel panelNuevoN1;
    private javax.swing.JPanel panelNuevoN2;
    private javax.swing.JPanel panelNuevoN3;
    private javax.swing.JPanel panelNuevoN4;
    private javax.swing.JPanel panelNuevoN5;
    private javax.swing.JPanel panelNuevoN6;
    private javax.swing.JPanel panelNuevoN7;
    private javax.swing.JTable tbNivel0;
    private javax.swing.JTable tbNivel1;
    private javax.swing.JTable tbNivel2;
    private javax.swing.JTable tbNivel3;
    private javax.swing.JTable tbNivel4;
    private javax.swing.JTable tbNivel5;
    private javax.swing.JTable tbNivel6;
    private javax.swing.JTextField txtFormaPago;
    // End of variables declaration//GEN-END:variables
}
