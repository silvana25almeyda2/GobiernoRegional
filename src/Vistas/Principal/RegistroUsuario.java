/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Principal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;

import Servicios.Conexion;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Principal.Usuario;


/**
 *
 * @author Profe
 */
public class RegistroUsuario extends javax.swing.JFrame {
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    
    DefaultTableModel m;
    Conexion c=new Conexion();
    byte est;
    byte mens;
    /**
     * Creates new form Usuario
     */
    public RegistroUsuario() {
        initComponents();
        c.conectar();
       
        this.getContentPane().setBackground(Color.white); 
        this.pnlUsuario.setBackground(Color.white);   
//      setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        this.setExtendedState(MAXIMIZED_BOTH);
        cbxModulo.setBackground(Color.WHITE);
        cbxNivel.setBackground(Color.WHITE);
        cbxPregunta.setBackground(Color.WHITE);
        cargareliminar.setVisible(false);
        PERSONAL.setLocationRelativeTo(null);
        Paginas.setEnabled(false);
        Paginas.setEnabledAt(0,false);
        Paginas.setEnabledAt(1, false);
        txtUsuario.requestFocus();
        txtPersonal.setText("");
        
        cargarUsuario("","1",Principal.lblUbicacion.getText());
       
        formatoUsuario();
       

        /*
        cbxPersonal.removeAllItems();
        try {
            Statement sta=conexion.createStatement();
            ResultSet rs=sta.executeQuery("SELECT nombres_per+' '+ape_pat_per+' '+ape_mat_per as nombreCompleto FROM personal_personal ");
            this.cbxPersonal.addItem("Seleccionar...");
            while(rs.next()){
                 this.cbxPersonal.addItem(rs.getString("nombreCompleto"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccion incorrecta en el Personal");
        }
    */
        cbxModulo.removeAllItems();
        try {
            Statement sta=c.conectar().createStatement();
            ResultSet rs=sta.executeQuery("select descripcion from SISTEMA_MODULO ORDER BY descripcion");
            this.cbxModulo.addItem("Seleccionar...");
            while(rs.next()){
                 this.cbxModulo.addItem(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccion incorrecta");
        }
//        btnNuevo.setMnemonic(KeyEvent.VK_N);
        //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();

            }
        });
    }
    public void cargarUsuario(String buscar,String tipo, String ubicacion){
    try {
            String consulta="";
            String titulos[]={"Nº","Codigo","Apellido Paterno","Apellido Materno","Nombres",
                 "cod_modulo","Nombre de Usuario","Modulo","Descripcion","Contrasena","Pregunta de Recuperación","Respuesta","Nivel","Lectura y Escritura","Control Total"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];

            Usuario obj=new Usuario();
           
               consulta="exec SP_USUARIO_listar ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, tipo);
            cmd.setString(3, ubicacion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            
            while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            fila[6]=r.getString(6);
            fila[7]=r.getString(7);
            fila[8]=r.getString(8);
            fila[9]=r.getString(9);
            fila[10]=r.getString(10);
            fila[11]=r.getString(11);
            fila[12]=r.getString(12);
            fila[13]=r.getString(13);
            fila[14]=r.getString(14);
                m.addRow(fila);
                c++;
            }
            tb_Usuario.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Usuario.setRowSorter(elQueOrdena);
            this.tb_Usuario.setModel(m);
    } catch (Exception e) {
    }
}
    public void formatoUsuario(){
    tb_Usuario.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Usuario.getColumnModel().getColumn(2).setPreferredWidth(150);
    tb_Usuario.getColumnModel().getColumn(3).setPreferredWidth(150);
    tb_Usuario.getColumnModel().getColumn(4).setPreferredWidth(150);
    tb_Usuario.getColumnModel().getColumn(6).setPreferredWidth(150);
    tb_Usuario.getColumnModel().getColumn(7).setPreferredWidth(150);
    tb_Usuario.getColumnModel().getColumn(8).setPreferredWidth(150);
    
    //Ocultar
     tb_Usuario.getColumnModel().getColumn(1).setMinWidth(0);
     tb_Usuario.getColumnModel().getColumn(1).setMaxWidth(0);
     tb_Usuario.getColumnModel().getColumn(5).setMinWidth(0);
     tb_Usuario.getColumnModel().getColumn(5).setMaxWidth(0);
     tb_Usuario.getColumnModel().getColumn(9).setMinWidth(0);
     tb_Usuario.getColumnModel().getColumn(9).setMaxWidth(0);
     tb_Usuario.getColumnModel().getColumn(10).setMinWidth(0);
     tb_Usuario.getColumnModel().getColumn(10).setMaxWidth(0);
     tb_Usuario.getColumnModel().getColumn(11).setMinWidth(0);
     tb_Usuario.getColumnModel().getColumn(11).setMaxWidth(0);
     
     tb_Usuario.getColumnModel().getColumn(12).setPreferredWidth(150);
     tb_Usuario.getColumnModel().getColumn(13).setPreferredWidth(150);
}
    
    public void Personal_listar(String buscar,String tipo){
      try {
            String consulta="";
            String titulos[]={"Nº","Codigo","DNI","Apellido Paterno","Apellido Materno","Nombres",
                 "Sexo","Unidad Ejecutora","Distrito","Dirección"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];

            Usuario obj=new Usuario();
           
               consulta="exec SP_PERSONAL_LISTAR ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            
            while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            fila[6]=r.getString(6);
            fila[7]=r.getString(7);
            fila[8]=r.getString(8);
            fila[9]=r.getString(9);
                m.addRow(fila);
                c++;
            }
            tbPersonal.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPersonal.setRowSorter(elQueOrdena);
            this.tbPersonal.setModel(m);
    } catch (Exception e) {
    }  
    }
    
    public void formatoPersonal(){
    tbPersonal.getColumnModel().getColumn(0).setPreferredWidth(50);
    tbPersonal.getColumnModel().getColumn(1).setPreferredWidth(100);
    tbPersonal.getColumnModel().getColumn(2).setPreferredWidth(150);
    tbPersonal.getColumnModel().getColumn(3).setPreferredWidth(150);
    tbPersonal.getColumnModel().getColumn(4).setPreferredWidth(150);;
    tbPersonal.getColumnModel().getColumn(5).setPreferredWidth(150);
    tbPersonal.getColumnModel().getColumn(6).setPreferredWidth(65);
    tbPersonal.getColumnModel().getColumn(7).setPreferredWidth(150);
    tbPersonal.getColumnModel().getColumn(8).setPreferredWidth(150);
    tbPersonal.getColumnModel().getColumn(9).setPreferredWidth(150);
    
    //Ocultar
    tbPersonal.getColumnModel().getColumn(1).setMinWidth(0);
    tbPersonal.getColumnModel().getColumn(1).setMaxWidth(0);
   
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PERSONAL = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPersonal = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                if(colIndex==0){
                    return true;
                }else{
                    return false; //Disallow the editing of any cell
                }}};
                jPanel3 = new javax.swing.JPanel();
                btnNuevo = new javax.swing.JButton();
                btnmodificar = new javax.swing.JButton();
                btnguardar = new javax.swing.JButton();
                btneliminar = new javax.swing.JButton();
                lblusu = new javax.swing.JLabel();
                jLabel61 = new javax.swing.JLabel();
                jPanel23 = new javax.swing.JPanel();
                buscartodo = new javax.swing.JTextField();
                btnBuscarPaciente = new javax.swing.JButton();
                lbldetalle = new javax.swing.JLabel();
                btnLista = new javax.swing.JButton();
                jPanel6 = new javax.swing.JPanel();
                lblFrase = new javax.swing.JLabel();
                lblUsuario = new javax.swing.JLabel();
                lblCodigo = new javax.swing.JLabel();
                lblCodPer = new javax.swing.JLabel();
                lblCodModulo = new javax.swing.JLabel();
                Paginas = new javax.swing.JTabbedPane();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tb_Usuario = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        if(colIndex==0){
                            return true;
                        }else{
                            return false; //Disallow the editing of any cell
                        }}};
                        pnlUsuario = new javax.swing.JPanel();
                        jLabel11 = new javax.swing.JLabel();
                        txtContra = new javax.swing.JPasswordField();
                        jLabel12 = new javax.swing.JLabel();
                        txtConfirmar = new javax.swing.JPasswordField();
                        jLabel13 = new javax.swing.JLabel();
                        txtUsuario = new javax.swing.JTextField();
                        jLabel2 = new javax.swing.JLabel();
                        cbxModulo = new javax.swing.JComboBox();
                        jLabel7 = new javax.swing.JLabel();
                        cbxPregunta = new javax.swing.JComboBox();
                        jLabel9 = new javax.swing.JLabel();
                        txtRespuesta = new javax.swing.JTextField();
                        jLabel1 = new javax.swing.JLabel();
                        btnBuscarPersonal = new javax.swing.JButton();
                        txtPersonal = new javax.swing.JTextField();
                        jLabel14 = new javax.swing.JLabel();
                        cbxNivel = new javax.swing.JComboBox();
                        jLabel15 = new javax.swing.JLabel();
                        txtCT = new javax.swing.JTextField();
                        jLabel16 = new javax.swing.JLabel();
                        txtL = new javax.swing.JTextField();
                        jLabel17 = new javax.swing.JLabel();
                        cargareliminar = new javax.swing.JPanel();
                        Mensaje = new javax.swing.JLabel();
                        eli = new javax.swing.JButton();
                        noeli = new javax.swing.JButton();

                        PERSONAL.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        PERSONAL.setMinimumSize(new java.awt.Dimension(747, 383));
                        PERSONAL.setResizable(false);

                        jPanel1.setBackground(new java.awt.Color(230, 230, 230));

                        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
                        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        jLabel3.setText("Personal");

                        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel4.setText(" DNI; Apellidos y Nombres");

                        txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                        txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtBuscarCaretUpdate(evt);
                            }
                        });
                        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarActionPerformed(evt);
                            }
                        });
                        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarKeyTyped(evt);
                            }
                        });

                        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                        btnBuscar.setBorder(null);
                        btnBuscar.setContentAreaFilled(false);
                        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addContainerGap())
                        );

                        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tbPersonal.setModel(new javax.swing.table.DefaultTableModel(
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
                        tbPersonal.setRowHeight(30);
                        tbPersonal.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tbPersonal.getTableHeader().setReorderingAllowed(false);
                        tbPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tbPersonalMouseClicked(evt);
                            }
                        });
                        tbPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tbPersonalKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                tbPersonalKeyReleased(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                tbPersonalKeyTyped(evt);
                            }
                        });
                        jScrollPane2.setViewportView(tbPersonal);

                        javax.swing.GroupLayout PERSONALLayout = new javax.swing.GroupLayout(PERSONAL.getContentPane());
                        PERSONAL.getContentPane().setLayout(PERSONALLayout);
                        PERSONALLayout.setHorizontalGroup(
                            PERSONALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                        );
                        PERSONALLayout.setVerticalGroup(
                            PERSONALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PERSONALLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowOpened(java.awt.event.WindowEvent evt) {
                                formWindowOpened(evt);
                            }
                        });

                        jPanel3.setBackground(new java.awt.Color(209, 52, 56));
                        jPanel3.setPreferredSize(new java.awt.Dimension(292, 437));

                        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Documento-32.png"))); // NOI18N
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

                        btnmodificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnmodificar.setForeground(new java.awt.Color(240, 240, 240));
                        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Editar-32.png"))); // NOI18N
                        btnmodificar.setText("Editar");
                        btnmodificar.setContentAreaFilled(false);
                        btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnmodificar.setEnabled(false);
                        btnmodificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btnmodificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnmodificar.setIconTextGap(30);
                        btnmodificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnmodificarActionPerformed(evt);
                            }
                        });

                        btnguardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Guardar-32.png"))); // NOI18N
                        btnguardar.setText("Guardar");
                        btnguardar.setContentAreaFilled(false);
                        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnguardar.setEnabled(false);
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
                        btneliminar.setEnabled(false);
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
                        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                        lblusu.setText("SILVANA");
                        lblusu.setFocusable(false);
                        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                        jLabel61.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel61.setText("<html>Usuarios<span style=\"font-size:'14px'\"><br>Registro</br></span></html>");

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
                        buscartodo.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                buscartodoKeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                        jPanel23.setLayout(jPanel23Layout);
                        jPanel23Layout.setHorizontalGroup(
                            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscartodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        );
                        jPanel23Layout.setVerticalGroup(
                            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-27.png"))); // NOI18N
                        btnBuscarPaciente.setContentAreaFilled(false);
                        btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPacienteActionPerformed(evt);
                            }
                        });

                        lbldetalle.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                        lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                        lbldetalle.setText("Usuario, Apellidos y Nombres y Módulo");

                        btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnLista.setForeground(new java.awt.Color(240, 240, 240));
                        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Orden de compra-32.png"))); // NOI18N
                        btnLista.setText("Listado");
                        btnLista.setContentAreaFilled(false);
                        btnLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnLista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnLista.setIconTextGap(30);
                        btnLista.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        btnLista.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnListaActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                        jPanel3.setLayout(jPanel3Layout);
                        jPanel3Layout.setHorizontalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(15, 15, 15)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lbldetalle)))
                                        .addComponent(btnmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel3Layout.setVerticalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(lbldetalle)
                                .addGap(21, 21, 21)
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnguardar)
                                .addGap(18, 18, 18)
                                .addComponent(btnmodificar)
                                .addGap(18, 18, 18)
                                .addComponent(btneliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnLista)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        jPanel6.setBackground(new java.awt.Color(230, 230, 230));
                        jPanel6.setForeground(new java.awt.Color(127, 140, 141));
                        jPanel6.setPreferredSize(new java.awt.Dimension(929, 115));

                        lblFrase.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
                        lblFrase.setForeground(new java.awt.Color(51, 51, 51));
                        lblFrase.setText("Listado");

                        lblUsuario.setForeground(new java.awt.Color(230, 230, 230));
                        lblUsuario.setText("Silvana");

                        lblCodigo.setForeground(new java.awt.Color(230, 230, 230));
                        lblCodigo.setText("1");

                        lblCodPer.setForeground(new java.awt.Color(230, 230, 230));

                        lblCodModulo.setForeground(new java.awt.Color(230, 230, 230));

                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel6Layout.setHorizontalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblFrase, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(lblUsuario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCodigo)
                                        .addGap(158, 158, 158)
                                        .addComponent(lblCodPer)
                                        .addGap(37, 37, 37)
                                        .addComponent(lblCodModulo)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblFrase, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblUsuario)
                                    .addComponent(lblCodigo)
                                    .addComponent(lblCodPer)
                                    .addComponent(lblCodModulo))
                                .addContainerGap(35, Short.MAX_VALUE))
                        );

                        Paginas.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                        Paginas.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tb_Usuario.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        tb_Usuario.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Usuario.setRowHeight(35);
                        tb_Usuario.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tb_Usuario.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_UsuarioMouseClicked(evt);
                            }
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                tb_UsuarioMouseEntered(evt);
                            }
                        });
                        tb_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_UsuarioKeyPressed(evt);
                            }
                        });
                        jScrollPane1.setViewportView(tb_Usuario);

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                        );

                        Paginas.addTab("", jPanel2);

                        pnlUsuario.setBackground(new java.awt.Color(255, 255, 255));

                        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel11.setText("Contraseña");

                        txtContra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtContra.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtContraActionPerformed(evt);
                            }
                        });

                        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel12.setText("Confirmar");

                        txtConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtConfirmar.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtConfirmarCaretUpdate(evt);
                            }
                        });

                        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel13.setText("Nombre de Usuario");

                        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtUsuarioActionPerformed(evt);
                            }
                        });
                        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtUsuarioKeyReleased(evt);
                            }
                        });

                        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel2.setText("Módulo");

                        cbxModulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        cbxModulo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Auxiliar Tecnico", "Trabajador", " " }));
                        cbxModulo.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbxModuloActionPerformed(evt);
                            }
                        });

                        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel7.setText("Pregunta");

                        cbxPregunta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        cbxPregunta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "¿En que País nació?", "¿Cual es su comida preferida?", "¿Nombre de su mascota?", "¿Cual es su deporte preferido?" }));
                        cbxPregunta.setMinimumSize(new java.awt.Dimension(216, 30));
                        cbxPregunta.setPreferredSize(new java.awt.Dimension(216, 30));
                        cbxPregunta.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbxPreguntaActionPerformed(evt);
                            }
                        });

                        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel9.setText("Respuesta");

                        txtRespuesta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtRespuesta.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtRespuestaKeyReleased(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtRespuestaKeyTyped(evt);
                            }
                        });

                        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel1.setText("Personal");

                        btnBuscarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                        btnBuscarPersonal.setBorder(null);
                        btnBuscarPersonal.setContentAreaFilled(false);
                        btnBuscarPersonal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPersonalActionPerformed(evt);
                            }
                        });

                        txtPersonal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtPersonal.setEnabled(false);
                        txtPersonal.setPreferredSize(new java.awt.Dimension(7, 20));

                        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel14.setText("Nivel");

                        cbxNivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        cbxNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ADMINISTRADOR", "AUXILIAR TÉCNICO", "TRABAJADOR", "CAJERO" }));

                        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel15.setText("Permisos");

                        txtCT.setEditable(false);
                        txtCT.setBackground(new java.awt.Color(255, 255, 255));
                        txtCT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                        txtCT.setForeground(new java.awt.Color(102, 102, 102));
                        txtCT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtCT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                        txtCT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                        txtCT.setPreferredSize(new java.awt.Dimension(18, 18));
                        txtCT.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                        txtCT.setSelectionColor(new java.awt.Color(255, 255, 255));
                        txtCT.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtCTCaretUpdate(evt);
                            }
                        });
                        txtCT.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                txtCTMouseClicked(evt);
                            }
                        });
                        txtCT.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtCTActionPerformed(evt);
                            }
                        });

                        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel16.setText("Control Total");

                        txtL.setEditable(false);
                        txtL.setBackground(new java.awt.Color(255, 255, 255));
                        txtL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                        txtL.setForeground(new java.awt.Color(102, 102, 102));
                        txtL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                        txtL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                        txtL.setPreferredSize(new java.awt.Dimension(18, 18));
                        txtL.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                        txtL.setSelectionColor(new java.awt.Color(255, 255, 255));
                        txtL.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtLCaretUpdate(evt);
                            }
                        });
                        txtL.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                txtLMouseClicked(evt);
                            }
                        });
                        txtL.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtLActionPerformed(evt);
                            }
                        });

                        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel17.setText("Lectura y Escritura");

                        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
                        pnlUsuario.setLayout(pnlUsuarioLayout);
                        pnlUsuarioLayout.setHorizontalGroup(
                            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGap(27, 27, 27)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlUsuarioLayout.createSequentialGroup()
                                        .addComponent(txtCT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtL, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17))
                                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtConfirmar, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxNivel, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxModulo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtContra, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(pnlUsuarioLayout.createSequentialGroup()
                                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtRespuesta, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxPregunta, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtPersonal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(238, Short.MAX_VALUE))
                        );
                        pnlUsuarioLayout.setVerticalGroup(
                            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnBuscarPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(txtUsuario))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(cbxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17))
                                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(txtCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16)))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cbxPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        Paginas.addTab("", pnlUsuario);

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

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                                    .addComponent(Paginas)
                                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(Paginas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void txtContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void cbxModuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxModuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxModuloActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void cbxPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPreguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPreguntaActionPerformed

    private void btnBuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonalActionPerformed
       PERSONAL.setVisible(true);
       txtBuscar.requestFocus();
        Personal_listar("", "1");
        formatoPersonal();
      
    }//GEN-LAST:event_btnBuscarPersonalActionPerformed

    private void txtRespuestaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRespuestaKeyTyped
        // TODO add your handling code here:
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtRespuestaKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //  txtcod.setText(cnn.idNomen());
        Paginas.setSelectedIndex(1);
        est=1;
        lblFrase.setText("Registro");
        limpiar();
        btnguardar.setEnabled(true);
        btneliminar.setEnabled(false);
        enableDatos(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        //btnguardar.setEnabled(true);
        
        enableDatos(true);
        btnguardar.setEnabled(true);
        btnmodificar.setEnabled(false);
        Paginas.setSelectedIndex(1);
        est=2;
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(est==1){
            Guardar();
        }else if(est==2){
               cargareliminar.setVisible(true);
               cargareliminar.setBackground(new Color(255,153,51)); 
               Mensaje.setText("Desea Actualizar el Registro ?");
               eli.setText("Si");
               eli.setVisible(true);
               noeli.setText("No");
               noeli.setVisible(true); 
               mens=2;
            
            
        }

    }//GEN-LAST:event_btnguardarActionPerformed

     public void Guardar(){
//         ImageIcon i=new ImageIcon(this.getClass().getResource("Imagenes/Iconos/Guardar-32.png")); 
        Usuario u1=new Usuario();
            if(lblCodPer.getText().equalsIgnoreCase("")||
               txtUsuario.getText().equalsIgnoreCase("")
                  ||cbxModulo.getSelectedIndex()==0||txtContra.getText().equalsIgnoreCase("") 
                    ||txtConfirmar.getText().equalsIgnoreCase("")
                  ||cbxPregunta.getSelectedIndex()==0||txtRespuesta.getText().equalsIgnoreCase("")){
                cargareliminar.setVisible(true);        
                cargareliminar.setBackground(new Color(255,91,70)); 
                eli.setText("OK");
                Mensaje.setText("Debe completar los campos requeridos");
                eli.setVisible(true);
                noeli.setVisible(false);
                mens=0;   
          }
          else if(txtContra.getText().equalsIgnoreCase(txtConfirmar.getText())==false){
                cargareliminar.setVisible(true);        
                cargareliminar.setBackground(new Color(255,91,70)); 
                eli.setText("OK");
                Mensaje.setText("Las Contraseñas no coinciden");
                eli.setVisible(true);
                noeli.setVisible(false);
                mens=0;  
                txtContra.setText("");
                txtConfirmar.setText("");
                txtContra.requestFocus();
              
          }
          else if(u1.ver_usuario(txtUsuario.getText(),"2")>0){
                cargareliminar.setVisible(true);        
                cargareliminar.setBackground(new Color(255,91,70)); 
                eli.setText("OK");
                Mensaje.setText("El Nombre de usuario ya existe");
                eli.setVisible(true);
                noeli.setVisible(false);
                mens=0;  
                txtUsuario.setText("");
                txtUsuario.requestFocus();
          }else{

                  Usuario u = new Usuario();
                  Usuario usu=new Usuario();
                  
                  String tipo=cbxModulo.getSelectedItem().toString();
                  u.setCod_modulo(usu.codTipo(tipo,"1"));
                  u.setCod_per(lblCodPer.getText());
                                  
                  u.setUsu_Usuario(txtUsuario.getText());
                  u.setUsu_Contrasena(txtContra.getText());
                  String preg=cbxPregunta.getSelectedItem().toString();
                  u.setUsu_Pregunta(preg);
                  u.setUsu_Respuesta(txtRespuesta.getText());
                  u.setNIVEL(cbxNivel.getSelectedItem().toString());
                  if(txtL.getText().equals("X")){
                    u.setL("X");
                    u.setE(" ");
                  }else if(txtCT.getText().equals("X")){
                    u.setE("X");
                    u.setL(" "); 
                  }
                  if(u.guardarUsuario()){
                      limpiar();
                      enableDatos(true);
                      est=1;
                      Paginas.setSelectedIndex(0);
                      
                       cargareliminar.setVisible(true);
                       cargareliminar.setBackground(new Color(0,153,102)); 
                       Mensaje.setText("Datos Guardados de forma correcta");
                       eli.setText("OK");
                       eli.setVisible(true);
                       noeli.setVisible(false);
                       mens=1;
                            
                  }
                  else{
                      JOptionPane.showMessageDialog(this, "El Usuario ya se encuentra registrado\nIntente nuevamente");
                      
                  }
          }
                        
    }
    
    public void Modificar(){
        Usuario u1=new Usuario();
        Usuario u2=new Usuario();
            if(lblCodPer.getText().equalsIgnoreCase("")||
               txtUsuario.getText().equalsIgnoreCase("")
                  ||cbxModulo.getSelectedIndex()==0||txtContra.getText().equalsIgnoreCase("") 
                    ||txtConfirmar.getText().equalsIgnoreCase("")
                  ||cbxPregunta.getSelectedIndex()==0||txtRespuesta.getText().equalsIgnoreCase("")){
                cargareliminar.setVisible(true);        
                cargareliminar.setBackground(new Color(255,91,70)); 
                eli.setText("OK");
                Mensaje.setText("Debe completar los campos requeridos");
                eli.setVisible(true);
                noeli.setVisible(false);
                mens=0;   
          }
          else if(txtContra.getText().equalsIgnoreCase(txtConfirmar.getText())==false){
                cargareliminar.setVisible(true);        
                cargareliminar.setBackground(new Color(255,91,70)); 
                eli.setText("OK");
                Mensaje.setText("Las Contraseñas no coinciden");
                eli.setVisible(true);
                noeli.setVisible(false);
                mens=0;  
                txtContra.setText("");
                txtConfirmar.setText("");
                txtContra.requestFocus();
              
          }
          else if(u2.codigoValidacion(txtUsuario.getText()).equalsIgnoreCase(lblCodigo.getText())==false && u1.ver_usuario(txtUsuario.getText(),"2")>0 ){
                  
                cargareliminar.setVisible(true);        
                cargareliminar.setBackground(new Color(255,91,70)); 
                eli.setText("OK");
                Mensaje.setText("El Nombre de usuario ya existe");
                eli.setVisible(true);
                noeli.setVisible(false);
                mens=0;  
                txtUsuario.setText("");
                txtUsuario.requestFocus();
          }else{

                  Usuario u = new Usuario();
                  Usuario usu=new Usuario();
                  u.setCodigo(Integer.parseInt(lblCodigo.getText()));
                  String tipo=cbxModulo.getSelectedItem().toString();
                  u.setCod_modulo(usu.codTipo(tipo,"1"));
                  u.setCod_per(lblCodPer.getText());
                                  
                  u.setUsu_Usuario(txtUsuario.getText());
                  u.setUsu_Contrasena(txtContra.getText());
                  String preg=cbxPregunta.getSelectedItem().toString();
                  u.setUsu_Pregunta(preg);
                  u.setUsu_Respuesta(txtRespuesta.getText());
                  u.setNIVEL(cbxNivel.getSelectedItem().toString());
                  if(txtL.getText().equals("X")){
                    u.setL("X");
                    u.setE(" ");
                  }else if(txtCT.getText().equals("X")){
                    u.setE("X");
                    u.setL(" "); 
                  }
                  if(u.modificarUsuario()){
                      limpiar();
                      enableDatos(true);
                      est=1;
                      Paginas.setSelectedIndex(0);
                      
                       cargareliminar.setVisible(true);
                       cargareliminar.setBackground(new Color(0,153,102)); 
                       Mensaje.setText("Datos Modificados de forma correcta");
                       eli.setText("OK");
                       eli.setVisible(true);
                       noeli.setVisible(false);
                       mens=1;
                            
                  }
                  else{
                      JOptionPane.showMessageDialog(this, "El Usuario ya se encuentra registrado\nIntente nuevamente");
                      
                  }
          }
                        
    }
    public void Eliminar(){
         try{

            int filaselec=tb_Usuario.getSelectedRow();
            if( filaselec>=0){
                    Usuario obj=new Usuario();
                    
//                    obj.setCodigo(Integer.parseInt(tb_Usuario.getValueAt(filaselec, 1).toString()));
                   obj.setCodigo(Integer.parseInt(lblCodigo.getText()));
                    
                    if(obj.eliminarUsuario()){
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Registro Eliminado");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                        mens=0;
                        Paginas.setSelectedIndex(0);
                
            }
        }}catch(Exception e){
             System.out.println( "Selecione el Detalle a eliminar");
        }
    }
    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
      
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,91,70)); 
                    Mensaje.setText("Desea Eliminar este registro?");
                    eli.setText("Si");
                    eli.setVisible(true);
                    noeli.setText("No");
                    noeli.setVisible(true);
                    mens=3;  
                 
    }//GEN-LAST:event_btneliminarActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        cargarUsuario(buscartodo.getText(), "2","");
        formatoUsuario();
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        String ubic = Principal.lblUbicacion.getText();
        Paginas.setSelectedIndex(0);
        limpiar();
        enableDatos(true);
        cargarUsuario("","1",ubic);
        
        formatoUsuario();
    }//GEN-LAST:event_btnListaActionPerformed

    private void buscartodoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscartodoKeyPressed
        
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            
            tb_Usuario.getSelectionModel().setSelectionInterval(0, 0);
            tb_Usuario.requestFocus();
        }  
        
    }//GEN-LAST:event_buscartodoKeyPressed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (mens==2){
            Modificar();
        } else if (mens==3){
            Eliminar();
            limpiar();
        }else if (mens==0 ||mens==1){
            cargareliminar.setVisible(false);
        }
    }//GEN-LAST:event_eliActionPerformed

    
    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        Personal_listar(txtBuscar.getText(), "2");
        formatoPersonal();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            tbPersonal.getSelectionModel().setSelectionInterval(0, 0);
            tbPersonal.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Personal_listar(txtBuscar.getText(),"2");
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tbPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPersonalMouseClicked
        if(evt.getClickCount()==2){
            try{
                
                int filaselec=tbPersonal.getSelectedRow();
                if( tbPersonal.getRowCount()>0){
                    lblCodPer.setText(tbPersonal.getValueAt(filaselec, 1).toString());
                    txtPersonal.setText(tbPersonal.getValueAt(filaselec, 3).toString()+' '+
                            tbPersonal.getValueAt(filaselec, 4).toString()+' '+
                            tbPersonal.getValueAt(filaselec, 5).toString());
                    PERSONAL.setVisible(false);
                }
                
                txtUsuario.requestFocus();
                
            }
            catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_tbPersonalMouseClicked

    private void tbPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                
                int filaselec=tbPersonal.getSelectedRow();
                if( tbPersonal.getRowCount()>0){
                    lblCodPer.setText(tbPersonal.getValueAt(filaselec, 1).toString());
                    txtPersonal.setText(tbPersonal.getValueAt(filaselec, 3).toString()+' '+
                            tbPersonal.getValueAt(filaselec, 4).toString()+' '+
                            tbPersonal.getValueAt(filaselec, 5).toString());
                    PERSONAL.setVisible(false);
                }
                
                txtUsuario.requestFocus();
                
            }
            catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_tbPersonalKeyPressed

    private void tbPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPersonalKeyReleased

    private void tbPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPersonalKeyTyped

    private void tb_UsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_UsuarioKeyPressed
       char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                     try{
                      Paginas.setSelectedIndex(1);
                     }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ingreso "+e.getMessage());
        }
                }
    }//GEN-LAST:event_tb_UsuarioKeyPressed

    private void tb_UsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_UsuarioMouseClicked
      if(evt.getClickCount()==1){
            cargar();
            btnmodificar.setEnabled(true);
            btneliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tb_UsuarioMouseClicked

    private void tb_UsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_UsuarioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_UsuarioMouseEntered

    private void txtCTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCTCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTCaretUpdate

    private void txtCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCTMouseClicked
//        if (lblID.getText().equals("")){
            if(txtCT.getText().equals("") && evt.getClickCount()==1){
                txtCT.setText("X");
                txtL.setText("");
            }else
            if(txtCT.getText().equals("X") && evt.getClickCount()==1){
                txtCT.setText("");
                txtL.setText("");
            }
//        }

    }//GEN-LAST:event_txtCTMouseClicked

    private void txtCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTActionPerformed

    private void txtLCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLCaretUpdate

    private void txtLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLMouseClicked
        if(txtL.getText().equals("") && evt.getClickCount()==1){
                txtL.setText("X");
                txtCT.setText("");
            }else
            if(txtL.getText().equals("X") && evt.getClickCount()==1){
                txtCT.setText("");
                txtL.setText("");
            }
    }//GEN-LAST:event_txtLMouseClicked

    private void txtLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLActionPerformed

    private void txtConfirmarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConfirmarCaretUpdate
        
    }//GEN-LAST:event_txtConfirmarCaretUpdate

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        txtUsuario.setText(txtUsuario.getText().toUpperCase());
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtRespuestaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRespuestaKeyReleased
        txtRespuesta.setText(txtRespuesta.getText().toUpperCase());
    }//GEN-LAST:event_txtRespuestaKeyReleased
    public void enableDatos(boolean e){
        btnBuscarPersonal.setEnabled(e);
        txtUsuario.setEnabled(e);
        cbxModulo.setEnabled(e);
        cbxNivel.setEnabled(e);
        txtCT.setEnabled(e);
        txtL.setEnabled(e);
        txtContra.setEnabled(e);
        txtConfirmar.setEnabled(e);
        cbxPregunta.setEnabled(e);
        txtRespuesta.setEnabled(e);
}
    
    public void cargar(){
         if( tb_Usuario.getRowCount()>0){
                            lblFrase.setText("Edición");
                           
                            int filaselec=tb_Usuario.getSelectedRow();
                            
                            lblCodigo.setText(tb_Usuario.getValueAt(filaselec, 1).toString());
                            lblCodPer.setText(tb_Usuario.getValueAt(filaselec, 5).toString());
                            txtPersonal.setText(tb_Usuario.getValueAt(filaselec, 2).toString()+' '+
                                    tb_Usuario.getValueAt(filaselec, 3).toString()+' '+
                                    tb_Usuario.getValueAt(filaselec, 4).toString()+' ');
                            txtUsuario.setText(tb_Usuario.getValueAt(filaselec, 6).toString());
                            cbxModulo.setSelectedItem(tb_Usuario.getValueAt(filaselec, 8).toString());
                            txtContra.setText(tb_Usuario.getValueAt(filaselec, 9).toString());
                            txtConfirmar.setText(tb_Usuario.getValueAt(filaselec, 9).toString());
                            
                            cbxPregunta.setSelectedItem(tb_Usuario.getValueAt(filaselec, 10).toString());
                            txtRespuesta.setText(tb_Usuario.getValueAt(filaselec, 11).toString());
                            cbxNivel.setSelectedItem(String.valueOf(tb_Usuario.getValueAt(filaselec, 12))); 
                            if (tb_Usuario.getValueAt(filaselec, 13).equals("X") && tb_Usuario.getValueAt(filaselec, 14).equals(" ")){
                                txtL.setText("X");
                                txtCT.setText(""); 
                            }
                            if (tb_Usuario.getValueAt(filaselec, 13).equals(" ") && tb_Usuario.getValueAt(filaselec, 14).equals("X")){
                                txtL.setText(" ");
                                txtCT.setText("X"); 
                            }
                            enableDatos(false);
                            btnmodificar.setEnabled(true);
                        
                        }
    }
    public void limpiar()
    {
    lblFrase.setText("Listado");
    txtUsuario.setText("");
    txtContra.setText("");
    txtConfirmar.setText("");
    txtRespuesta.setText("");
    txtPersonal.setText("");
    txtBuscar.setText("");
    
    btnguardar.setEnabled(false);
    btnmodificar.setEnabled(false);
    btneliminar.setEnabled(false);
    cbxModulo.setSelectedIndex(0);
    cbxPregunta.setSelectedIndex(0);
    cargarUsuario("", "1", Principal.lblUbicacion.getText());
    
    formatoUsuario();
    est=1;
    
    cbxModulo.removeAllItems();
        try {
            Statement sta=c.conectar().createStatement();
            ResultSet rs=sta.executeQuery("select descripcion from SISTEMA_MODULO ORDER BY descripcion");
            this.cbxModulo.addItem("Seleccionar...");
            while(rs.next()){
                 this.cbxModulo.addItem(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccion incorrecta");
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
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new RegistroUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog PERSONAL;
    private javax.swing.JTabbedPane Paginas;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarPaciente;
    public static javax.swing.JButton btnBuscarPersonal;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnmodificar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    public static javax.swing.JComboBox cbxModulo;
    private javax.swing.JComboBox cbxNivel;
    public static javax.swing.JComboBox cbxPregunta;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCodModulo;
    private javax.swing.JLabel lblCodPer;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblFrase;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel pnlUsuario;
    public static javax.swing.JTable tbPersonal;
    private javax.swing.JTable tb_Usuario;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCT;
    public static javax.swing.JPasswordField txtConfirmar;
    public static javax.swing.JPasswordField txtContra;
    public static javax.swing.JTextField txtL;
    public static javax.swing.JTextField txtPersonal;
    public static javax.swing.JTextField txtRespuesta;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables


}
