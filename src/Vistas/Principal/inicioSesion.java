/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.Timer;

import Servicios.Conexion;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import modelo.Caja.Caja_AperturaCierre;
import modelo.Principal.Usuario;


/**
 *
 * @author silvana
 */
public class inicioSesion extends javax.swing.JFrame implements Runnable{
    
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    String usuario;
 
    Conexion cn= new Conexion();
    Connection cnn=cn.conectar();
    
    Conexion cn1= new Conexion();
    Connection cnn1=cn1.conectar();
    
    Conexion cn2= new Conexion();
    Connection cnn2=cn2.conectar();

    private Timer tiempo,tiempo1;
    int cont,cont1,contadmin=1,filtroModulo;
    public final static int TWO_SECOND=5;
    public final static int TWO_SECOND1=5;
    /**
     * Creates new form inicioSesion
     */
    public inicioSesion() {

        initComponents();
        txtUsuario.requestFocus();
        this.getContentPane().setBackground(new Color(69,70,74));
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);//en el centro
//        setResizable(false);//para que no funcione el boton maximizar
        jDialog1.setLocationRelativeTo(null);//en el centro
        panelRecuperar.setVisible(false);
        panelError.setVisible(false);
        lblError.setVisible(false);
        h1 = new Thread(this);
        h1.start();
        
        
//        barra.setBackground(new Color(155,155,155));
//       setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        //ICONO DE FORMULARIO
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Mind Map-100.png")).getImage());
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
//        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/iconos/Imagen/icons8-Mind Map-100.png"));
//        setIconImage(icon);
//        setVisible(true);
 
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
    
    void acceder(String usu,String pass){
     try {
         if(txtUsuario.getText().equalsIgnoreCase("")||txtContra.getText().equalsIgnoreCase("")){
            panelError.setVisible(true);
            lblError.setText("Debe llenar todos los Campos");
            lblError.setVisible(true);
////             JOptionPane.showMessageDialog(this, "Debe llenar todos los Campos");
         }else{
            String cap="";
            String sql="select * from SISTEMA_USUARIO where Usu_Usuario='"+usu+"' and dbo.fnLeeClave(usu_contrasena)='"+pass+"'";
            PreparedStatement st=cnn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
            cap=rs.getString("cod_modulo");
            }
            st.close();
            Usuario u=new Usuario();
            if(cap.equalsIgnoreCase("")){
                panelError.setVisible(true);
                lblError.setText("Usuario y/o Contraseña Incorrectos");
                lblError.setVisible(true);
//           JOptionPane.showMessageDialog(this, "Usuario y/o Contraseña Incorrectos");
           txtContra.setText("");
       }else if(cap.equalsIgnoreCase(codAdmin("CONFIGURACION"))){//Para editar tipo de usuario
                
        //p.pack();
         cont=-1;
        tiempo=new Timer(TWO_SECOND, new TimerListener());
        activar();
      }else if(cap.equalsIgnoreCase(codAdmin("CAJA"))){//Para editar tipo de usuario
                
        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=1;
      }else if(cap.equalsIgnoreCase(codAdmin("CAJA / FACTURADOR"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=2;
      }else if(cap.equalsIgnoreCase(codAdmin("PERSONAL"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=3;
      }else if(cap.equalsIgnoreCase(codAdmin("USUARIOS"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=4;
      }else if(cap.equalsIgnoreCase(codAdmin("ADMINISTRACION"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=5;
      }else if(cap.equalsIgnoreCase(codAdmin("CONTABILIDAD"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=6;
      }else{
          JOptionPane.showMessageDialog(this, "NO PERTENECE A NINGÚN MÓDULO DEL SISTEMA");
      }
         }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,e.getMessage()+ "Error al Iniciar Sesión");
    }
}

    
    //CODIGO DE ADMINISTRADOR
    ////////////////////////////////////////////
    ///////////////////////////////////////////
    
    public String codAdmin(String codTipo)
    {
       Usuario u=new  Usuario();
        String cod="";
        try
        {
            String sql = "SELECT cod_modulo FROM SISTEMA_MODULO where descripcion=?";
            PreparedStatement cmd = u.getCn().prepareStatement(sql);
            cmd.setString(1, codTipo);
            ResultSet rs = cmd.executeQuery();
           // Statement cmd = cnn1.createStatement();
          //  ResultSet rs = cmd.executeQuery(sql);
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
        }
        catch(Exception ex)        {
            System.out.println("Error en Admin: " + ex.getMessage());
        }
        return cod;
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelCPT3 = new javax.swing.JPanel();
        txtContra2 = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        panelCPT2 = new javax.swing.JPanel();
        txtContra1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnIniciarSesion1 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        btnIniciarSesion2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelRecuperar = new javax.swing.JPanel();
        panelCPT9 = new javax.swing.JPanel();
        txtPregunta = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelCPT10 = new javax.swing.JPanel();
        txtRespuesta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        btnRecuperar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        btnIniciarSesion = new javax.swing.JButton();
        panelCPT = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelCPT1 = new javax.swing.JPanel();
        txtContra = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelError = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();

        jDialog1.setAlwaysOnTop(true);
        jDialog1.setMinimumSize(new java.awt.Dimension(394, 320));
        jDialog1.setUndecorated(true);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Se recomienda que cambie su contraseña.");

        panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtContra2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtContra2.setForeground(new java.awt.Color(51, 51, 51));
        txtContra2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtContra2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContra2KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Confirme");

        javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
        panelCPT3.setLayout(panelCPT3Layout);
        panelCPT3Layout.setHorizontalGroup(
            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtContra2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCPT3Layout.setVerticalGroup(
            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtContra2)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtContra1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtContra1.setForeground(new java.awt.Color(51, 51, 51));
        txtContra1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtContra1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContra1KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nueva Contraseña");

        javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
        panelCPT2.setLayout(panelCPT2Layout);
        panelCPT2Layout.setHorizontalGroup(
            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtContra1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCPT2Layout.setVerticalGroup(
            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtContra1)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel19.setBackground(new java.awt.Color(102, 102, 102));
        jPanel19.setPreferredSize(new java.awt.Dimension(125, 25));

        btnIniciarSesion1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnIniciarSesion1.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion1.setText("Cambiar");
        btnIniciarSesion1.setContentAreaFilled(false);
        btnIniciarSesion1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniciarSesion1.setIconTextGap(30);
        btnIniciarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(102, 102, 102));
        jPanel20.setPreferredSize(new java.awt.Dimension(125, 25));

        btnIniciarSesion2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnIniciarSesion2.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion2.setText("Regresar");
        btnIniciarSesion2.setContentAreaFilled(false);
        btnIniciarSesion2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniciarSesion2.setIconTextGap(30);
        btnIniciarSesion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesion2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(69, 70, 74));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Recuperación");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/a.gif"))); // NOI18N
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conecta");
        setMinimumSize(new java.awt.Dimension(372, 435));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(69, 70, 74));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        panelRecuperar.setBackground(new java.awt.Color(255, 255, 255));
        panelRecuperar.setForeground(new java.awt.Color(0, 153, 153));

        panelCPT9.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelCPT9.setPreferredSize(new java.awt.Dimension(50, 33));

        txtPregunta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPregunta.setForeground(new java.awt.Color(51, 51, 51));
        txtPregunta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPregunta.setBorder(null);
        txtPregunta.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPreguntaCaretUpdate(evt);
            }
        });
        txtPregunta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPreguntaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPreguntaKeyTyped(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(111, 111, 111));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("?");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelCPT9Layout = new javax.swing.GroupLayout(panelCPT9);
        panelCPT9.setLayout(panelCPT9Layout);
        panelCPT9Layout.setHorizontalGroup(
            panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPregunta)
                .addContainerGap())
        );
        panelCPT9Layout.setVerticalGroup(
            panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtPregunta)
        );

        panelCPT10.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelCPT10.setPreferredSize(new java.awt.Dimension(308, 33));

        txtRespuesta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRespuesta.setForeground(new java.awt.Color(51, 51, 51));
        txtRespuesta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRespuesta.setBorder(null);
        txtRespuesta.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRespuestaCaretUpdate(evt);
            }
        });
        txtRespuesta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRespuestaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRespuestaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRespuestaKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Respuesta");

        javax.swing.GroupLayout panelCPT10Layout = new javax.swing.GroupLayout(panelCPT10);
        panelCPT10.setLayout(panelCPT10Layout);
        panelCPT10Layout.setHorizontalGroup(
            panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );
        panelCPT10Layout.setVerticalGroup(
            panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
            .addComponent(txtRespuesta)
        );

        jPanel18.setBackground(new java.awt.Color(102, 102, 102));
        jPanel18.setPreferredSize(new java.awt.Dimension(125, 25));

        btnRecuperar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRecuperar.setForeground(new java.awt.Color(255, 255, 255));
        btnRecuperar.setText("Recuperar");
        btnRecuperar.setContentAreaFilled(false);
        btnRecuperar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecuperar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecuperar.setIconTextGap(30);
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRecuperar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRecuperar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRecuperarLayout = new javax.swing.GroupLayout(panelRecuperar);
        panelRecuperar.setLayout(panelRecuperarLayout);
        panelRecuperarLayout.setHorizontalGroup(
            panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecuperarLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelCPT10, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                        .addComponent(panelCPT9, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRecuperarLayout.setVerticalGroup(
            panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecuperarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelCPT9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCPT10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/a.gif"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(102, 102, 102));
        jPanel17.setPreferredSize(new java.awt.Dimension(125, 25));

        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.setContentAreaFilled(false);
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniciarSesion.setIconTextGap(30);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        panelCPT.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelCPT.setPreferredSize(new java.awt.Dimension(42, 33));

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsuario.setBorder(null);
        txtUsuario.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtUsuarioCaretUpdate(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(111, 111, 111));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-User-20.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
        panelCPT.setLayout(panelCPTLayout);
        panelCPTLayout.setHorizontalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCPTLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario)
                .addContainerGap())
        );
        panelCPTLayout.setVerticalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtContra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtContra.setForeground(new java.awt.Color(51, 51, 51));
        txtContra.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraKeyPressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(111, 111, 111));
        jPanel4.setPreferredSize(new java.awt.Dimension(32, 31));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Password-20.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
        panelCPT1.setLayout(panelCPT1Layout);
        panelCPT1Layout.setHorizontalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCPT1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCPT1Layout.setVerticalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtContra, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(panelCPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelRecuperar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRecuperar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cuenta");

        jPanel5.setBackground(new java.awt.Color(0, 120, 215));
        jPanel5.setPreferredSize(new java.awt.Dimension(25, 25));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("?");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("He olvidado mi contraseña");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        panelError.setBackground(new java.awt.Color(209, 52, 56));
        panelError.setPreferredSize(new java.awt.Dimension(25, 25));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("!");

        javax.swing.GroupLayout panelErrorLayout = new javax.swing.GroupLayout(panelError);
        panelError.setLayout(panelErrorLayout);
        panelErrorLayout.setHorizontalGroup(
            panelErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        panelErrorLayout.setVerticalGroup(
            panelErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblError.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 255, 255));
        lblError.setText("Usuario y/o Contraseña Incorrectos");
        lblError.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblError.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblErrorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblError)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Usuario u=new Usuario();
        Usuario u1=new Usuario();
        if(txtUsuario.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Ingrese su Nombre de Usuario");     
            panelRecuperar.setVisible(false);
//        } else if(u.ver_usuario(txtUsuario.getText(),"1")==0){
//        } else if(u.ver_usuario(txtUsuario.getText(),"2")==0){


        }else if(u.ver_usuario(txtUsuario.getText(),"2")==0){
            JOptionPane.showMessageDialog(this, "El usuario no existe en el Sistema");     
            panelRecuperar.setVisible(false);
        }else{
            jPanel7.setVisible(false);
            panelRecuperar.setVisible(true);
            txtPregunta.setEditable(false);
            txtPregunta.setText(u1.Pregunta(txtUsuario.getText()));
            txtRespuesta.requestFocus();
        }
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtUsuarioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUsuarioCaretUpdate

    }//GEN-LAST:event_txtUsuarioCaretUpdate

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
     
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraKeyPressed
        // TODO add your handling code here:

        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnIniciarSesion.doClick();
        }
    }//GEN-LAST:event_txtContraKeyPressed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
          
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        txtUsuario.setText(txtUsuario.getText().toUpperCase());
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        // TODO add your handling code here:
        usuario=txtUsuario.getText();
        String contra=String.valueOf(txtContra.getPassword());
        acceder(usuario, contra);
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void lblErrorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblErrorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblErrorMouseClicked

    private void txtPreguntaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPreguntaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPreguntaCaretUpdate

    private void txtPreguntaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreguntaKeyPressed
       
    }//GEN-LAST:event_txtPreguntaKeyPressed

    private void txtPreguntaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreguntaKeyTyped
        if (txtPregunta.getText().length()==3){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtPreguntaKeyTyped

    private void txtRespuestaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRespuestaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRespuestaCaretUpdate

    private void txtRespuestaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRespuestaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRespuestaKeyPressed

    private void txtRespuestaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRespuestaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRespuestaKeyTyped

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
         // TODO add your handling code here:
        Usuario u=new Usuario();
        Usuario us=new Usuario();
        if(txtUsuario.getText().equalsIgnoreCase("")){
            panelError.setVisible(true);
            lblError.setText("Ingrese su Nombre de Usuario");
            lblError.setVisible(true);
//            JOptionPane.showMessageDialog(this, "Ingrese su Nombre de Usuario");
        } else if(txtRespuesta.getText().equalsIgnoreCase("")){
            panelError.setVisible(true);
            lblError.setText("Escriba su Respuesta");
            lblError.setVisible(true);
//            JOptionPane.showMessageDialog(rootPane, "Escriba su Respuesta");
        }else if(u.Respuesta(txtUsuario.getText(), txtPregunta.getText()).equalsIgnoreCase(txtRespuesta.getText())){
            jPanel7.setVisible(true);
            panelRecuperar.setVisible(false);
            String a ="";
            a=us.Contrasena(txtUsuario.getText(), txtPregunta.getText());
            jLabel2.setText("Su Contraseña es ,   "+a);
            jDialog1.setVisible(true);
            txtPregunta.setText("");
            txtRespuesta.setText("");
        }
        else{
            panelError.setVisible(true);
            lblError.setText("La Respuesta es Incorrecta");
            lblError.setVisible(true);
//            JOptionPane.showMessageDialog(rootPane, "La Respuesta es Incorrecta");
            txtRespuesta.setText("");
        }
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void txtRespuestaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRespuestaKeyReleased
        txtRespuesta.setText(txtRespuesta.getText().toUpperCase());
    }//GEN-LAST:event_txtRespuestaKeyReleased

    private void txtContra1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContra1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContra1KeyPressed

    private void txtContra2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContra2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContra2KeyPressed

    private void btnIniciarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarSesion1ActionPerformed

    private void btnIniciarSesion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesion2ActionPerformed
        jDialog1.dispose();
    }//GEN-LAST:event_btnIniciarSesion2ActionPerformed
  class TimerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           cont++;
           if(cont==101){
               tiempo.stop();
               esconder();
               
                Principal pmdi = new Principal();
                Principal.lblUsu.setText(usuario);
                pmdi.setVisible(true);   
           }
        }
    }
   class TimerListener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           cont1++;
           if(cont1==101){
               tiempo1.stop();
               esconder();
                Principal pmdi = new Principal();
                Principal.lblUsu.setText(usuario);
                Caja_AperturaCierre CAP = new Caja_AperturaCierre();
                CAP.DDATOS_GENERALES(usuario);
                pmdi.setVisible(true);

                Principal.btnCaja.setEnabled(false);
//                Principal.btnCaja.setContentAreaFilled(true);
//                Principal.btnCaja.setBackground(new Color(153,153,153));
                Principal.btnFacturador.setEnabled(false);
                Principal.btnPersonal.setEnabled(false);
                Principal.btnUsuarios.setEnabled(false);
                Principal.btnCaja2.setEnabled(false);
               
                if(filtroModulo==1){
                  Principal.btnCaja.setEnabled(true);
                  Principal.btnFacturador.setEnabled(true);
                }else if(filtroModulo==2){
                  Principal.btnCaja.setEnabled(true);
                  Principal.btnFacturador.setEnabled(true);
                }else if(filtroModulo==3){
                 Principal.btnPersonal.setEnabled(true);
                 Principal.btnUsuarios.setEnabled(true);
                }else if(filtroModulo==4){
                 Principal.btnPersonal.setEnabled(true);
                 Principal.btnUsuarios.setEnabled(true);
                }else if(filtroModulo==5){
                 Principal.btnCaja.setEnabled(true);
                 Principal.btnFacturador.setEnabled(true);
                 Principal.btnPersonal.setEnabled(true);
                 Principal.btnUsuarios.setEnabled(true);
                 Principal.btnCaja2.setEnabled(true);
                }else if(filtroModulo==6){
                 Principal.btnCaja2.setEnabled(true);
                }
           }
        }
    }
    public void esconder(){
        this.setVisible(false);
    }
    public void activar(){
        tiempo.start();
        
    }
    public void activar1(){
        tiempo1.start();
        
    }


    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
//            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
           /*
    String a="sql";
            int intIndex = a.indexOf("lenguaje sql");
          if(intIndex == - 1){
             System.out.println("palabra encontrada");
          }else{
             System.out.println("palabra no encontrada"
             + intIndex);
          }
    */
    
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
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnIniciarSesion1;
    private javax.swing.JButton btnIniciarSesion2;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT10;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCPT9;
    private javax.swing.JPanel panelError;
    private javax.swing.JPanel panelRecuperar;
    public static javax.swing.JPasswordField txtContra;
    public static javax.swing.JPasswordField txtContra1;
    public static javax.swing.JPasswordField txtContra2;
    public static javax.swing.JTextField txtPregunta;
    public static javax.swing.JTextField txtRespuesta;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
