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

import static vista.RegistroTipoUsuario.txtcodigo;
import static vista.RegistroTipoUsuario.txttipo;

/**
 *
 * @author Profe
 */
public class RegistroUsuario extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    DefaultTableModel m;
    Conexion c=new Conexion();
    byte est;
    /**
     * Creates new form Usuario
     */
    public RegistroUsuario() {
        initComponents();
        c.conectar();
        h1 = new Thread(this);
        h1.start();
        this.getContentPane().setBackground(Color.white); 
        this.pnlUsuario.setBackground(Color.white);   
//      setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        this.setExtendedState(MAXIMIZED_BOTH);
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        
        txtUsuario.requestFocus();
        txtPersonal.setText("");
        
        cargarUsuario("","1");
        formatoUsuario();
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
    

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
            ResultSet rs=sta.executeQuery("select modulo from SISTEMA_MODULO ORDER BY modulo");
            this.cbxModulo.addItem("Seleccionar...");
            while(rs.next()){
                 this.cbxModulo.addItem(rs.getString("modulo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccion incorrecta");
        }
        btnNuevo.setMnemonic(KeyEvent.VK_N);
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
    public void cargarUsuario(String buscar,String tipo){
    try {
            String consulta="";
            String titulos[]={"Nº","Codigo","Apellido_Paterno","Apellido_Materno","Nombres",
                 "cod_modulo","Nombre de Usuario","Modulo","Descripcion","Contrasena","Pregunta de Recuperación","Respuesta"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];

            Usuario obj=new Usuario();
           
               consulta="exec SP_USUARIO_listar ?,?";
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
            fila[10]=r.getString(10);
            fila[11]=r.getString(11);
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
//    tb_Usuario.getColumnModel().getColumn(0).setPreferredWidth(50);
//    tb_Usuario.getColumnModel().getColumn(1).setPreferredWidth(100);
//    tb_Usuario.getColumnModel().getColumn(2).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(3).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(4).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(5).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(6).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(7).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(8).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(9).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(10).setPreferredWidth(150);
//    tb_Usuario.getColumnModel().getColumn(11).setPreferredWidth(150);
//        tb_Usuario.getColumnModel().getColumn(10).setMinWidth(0);
//    tb_Usuario.getColumnModel().getColumn(10).setMaxWidth(0);
//            tb_Usuario.getColumnModel().getColumn(11).setMinWidth(0);
//    tb_Usuario.getColumnModel().getColumn(11).setMaxWidth(0);
}
   
     public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        tab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Usuario = new javax.swing.JTable();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(223, 0, 78));
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

        jLabel61.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("<html>Usuario<span style=\"font-size:'14px'\"><br>Registro</br></span></html>");

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
                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                    .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPanel6.setBackground(new java.awt.Color(43, 43, 43));
        jPanel6.setPreferredSize(new java.awt.Dimension(929, 115));

        lblFrase.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        lblFrase.setForeground(new java.awt.Color(255, 255, 255));
        lblFrase.setText("Listado");

        lblUsuario.setForeground(new java.awt.Color(43, 43, 43));
        lblUsuario.setText("Silvana");

        lblCodigo.setText("1");

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

        tab.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

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
        jScrollPane1.setViewportView(tb_Usuario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        tab.addTab("", jPanel2);

        pnlUsuario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Contraseña:");

        txtContra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Confirmar:");

        txtConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Nombre de Usuario:");

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Módulo:");

        cbxModulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxModulo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Auxiliar Tecnico", "Trabajador", " " }));
        cbxModulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxModuloActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Pregunta:");

        cbxPregunta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxPregunta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "¿En que País nació?", "¿Cual es su comida preferida?", "¿Nombre de su mascota?", "¿Cual es su deporte preferido?" }));
        cbxPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPreguntaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Respuesta:");

        txtRespuesta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRespuesta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRespuestaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Personal:");

        btnBuscarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarPersonal.setBorder(null);
        btnBuscarPersonal.setContentAreaFilled(false);
        btnBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPersonalActionPerformed(evt);
            }
        });

        txtPersonal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPersonal.setEnabled(false);
        txtPersonal.setPreferredSize(new java.awt.Dimension(7, 20));

        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
        pnlUsuario.setLayout(pnlUsuarioLayout);
        pnlUsuarioLayout.setHorizontalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(27, 27, 27)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtUsuario)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuarioLayout.createSequentialGroup()
                            .addComponent(txtPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbxModulo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtContra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                    .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtRespuesta, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxPregunta, javax.swing.GroupLayout.Alignment.LEADING, 0, 234, Short.MAX_VALUE)))
                .addContainerGap(307, Short.MAX_VALUE))
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
                .addGap(29, 29, 29)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtUsuario))
                .addGap(29, 29, 29)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(27, 27, 27)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        tab.addTab("", pnlUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addComponent(tab)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tab))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
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
        // TODO add your handling code here:
      
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
        tab.setSelectedIndex(1);
        est=1;
        lblFrase.setText("Registro");
        limpiar();
        enableDatos(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        //btnguardar.setEnabled(true);
        enableDatos(true);
        btnmodificar.setEnabled(false);
        est=2;
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(est==1){
            Guardar();
            
            

        }else if(est==2){
            Modificar();
            tab.setSelectedIndex(0);
            est=1;
        }

    }//GEN-LAST:event_btnguardarActionPerformed

     public void Guardar(){
//         ImageIcon i=new ImageIcon(this.getClass().getResource("Imagenes/Iconos/Guardar-32.png")); 
        Usuario u1=new Usuario();
            if(lblCodPer.getText().equalsIgnoreCase("")||
               lblCodModulo.getText().equalsIgnoreCase("")||txtUsuario.getText().equalsIgnoreCase("")
                  ||cbxModulo.getSelectedIndex()==0||txtContra.getText().equalsIgnoreCase("") 
                    ||txtConfirmar.getText().equalsIgnoreCase("")
                  ||cbxPregunta.getSelectedIndex()==0||txtRespuesta.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Verifique si ha ingresado todos los campos");
          }
          else if(txtContra.getText().equalsIgnoreCase(txtConfirmar.getText())==false){
              JOptionPane.showMessageDialog(rootPane, "Las contraseñas no coinciden");
              
              txtContra.setText("");
              txtConfirmar.setText("");
              txtContra.requestFocus();
              
          }
          else if(u1.ver_usuario(txtUsuario.getText())>0){
              JOptionPane.showMessageDialog(rootPane, "El Nombre de Usuario ingresado ya existe\nIntente nuevamente");
              txtUsuario.setText("");
              txtUsuario.requestFocus();
          }else{
              
              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
              if(guardar == 0 ){
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
                  
                  if(u.guardarUsuario()){
                      JOptionPane.showMessageDialog(this, "Datos Guardados");
                      limpiar();
                      enableDatos(true);
                      est=1;
                      tab.setSelectedIndex(0);
                  }
                  else{
                      JOptionPane.showMessageDialog(this, "El Usuario ya se encuentra registrado\nIntente nuevamente");
                      
                  }}
          }
                        
    }
    
    public void Modificar(){
        
//                RegistroPC REG = new RegistroPC();
//                REG.setModulo(cbxModulo.getSelectedItem().toString());
//                if(lblSEID.getText().equalsIgnoreCase("")==false){
//                REG.setSE_ID(Integer.parseInt(lblSEID.getText()));
//                }
//                if(lblARID.getText().equalsIgnoreCase("")==false){
//                REG.setAR_ID(Integer.parseInt(lblARID.getText()) );//
//                }
//                REG.setNOM_PC(txtPC.getText());
//                REG.setNOM_USU(lblUsuario.getText());//
//                if(txtNRO.getText().equalsIgnoreCase("")==false){
//                REG.setNRO_PC(Integer.parseInt(txtNRO.getText()));//
//                }
//                REG.setPA_ID(Integer.parseInt(lblID.getText()));
//                    if(REG.ModificarTerminal()==true){
//                        System.out.println("MODIFICADO");
//                        lblMensaje.setText("Todo Correcto");
//                         JOptionPane.showMessageDialog(rootPane, "Datos Modificados");
//                         btnguardar.setEnabled(false);
//                    }else{
//                        lblMensaje.setText("Algo salió mal");
//                        
//    }
                        
    }
    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        try{

            int filaselec=tb_Usuario.getSelectedRow();
            if( filaselec>=0){
                ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
                if(eliminar == 0 ){
                    Usuario obj=new Usuario();
//                    obj.setCod_per(Integer.parseInt(tb_Usuario.getValueAt(filaselec, 1).toString()));
                    obj.setCodigo(Integer.parseInt(lblCodigo.getText()));
                if(obj.eliminarUsuario()){
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                        limpiar();
                       btnguardar.setEnabled(true);
                       btnmodificar.setEnabled(false);
                       btneliminar.setEnabled(false);
                      
                }

                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Esquema a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        String consulta="";
        try {
            String titulos[]={"Nº","Area","Módulo","Servicio","Area","Terminal","NRO PC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            Usuario obj=new Usuario();
            consulta="exec CONFIGURACION_PC_AREA_Listar ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            cmd.setString(2, "2");
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
                m.addRow(fila);
                c++;
            }
            tb_Usuario.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Usuario.setRowSorter(elQueOrdena);
            this.tb_Usuario.setModel(m);
            formatoUsuario();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        tab.setSelectedIndex(0);
        limpiar();
        enableDatos(true);
    }//GEN-LAST:event_btnListaActionPerformed
    public void enableDatos(boolean e){
        btnBuscarPersonal.setEnabled(e);
        txtUsuario.setEnabled(e);
        cbxModulo.setEnabled(e);
        txtContra.setEnabled(e);
        txtConfirmar.setEnabled(e);
        cbxPregunta.setEnabled(e);
        txtRespuesta.setEnabled(e);
}
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
           
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
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
    
    btnmodificar.setEnabled(false);
    cbxModulo.setSelectedIndex(0);
    cbxPregunta.setSelectedIndex(0);
   
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
    private javax.swing.JButton btnBuscarPaciente;
    public static javax.swing.JButton btnBuscarPersonal;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnmodificar;
    public static javax.swing.JTextField buscartodo;
    public static javax.swing.JComboBox cbxModulo;
    public static javax.swing.JComboBox cbxPregunta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodModulo;
    private javax.swing.JLabel lblCodPer;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblFrase;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tb_Usuario;
    public static javax.swing.JPasswordField txtConfirmar;
    public static javax.swing.JPasswordField txtContra;
    public static javax.swing.JTextField txtPersonal;
    public static javax.swing.JTextField txtRespuesta;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables


}
