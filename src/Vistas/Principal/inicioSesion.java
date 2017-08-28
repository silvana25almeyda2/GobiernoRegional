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
        this.getContentPane().setBackground(Color.lightGray);
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        panelRecuperar.setVisible(false);
        h1 = new Thread(this);
        h1.start();
//        barra.setBackground(new Color(155,155,155));
//       setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        //ICONO DE FORMULARIO
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
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
             JOptionPane.showMessageDialog(this, "Debe llenar todos los Campos");
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
           JOptionPane.showMessageDialog(this, "Usuario y/o Contraseña Incorrectos");
           txtContra.setText("");
       }else if(cap.equalsIgnoreCase(codAdmin("ADMINISTRACION"))){//Para editar tipo de usuario
                
        //p.pack();
         cont=-1;
        tiempo=new Timer(TWO_SECOND, new TimerListener());
        activar();
      }else if(cap.equalsIgnoreCase(codAdmin("CAJA"))){//Para editar tipo de usuario
                
        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=1;
      }else if(cap.equalsIgnoreCase(codAdmin("FACTURADOR"))){//Para editar tipo de usuario

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
      }else if(cap.equalsIgnoreCase(codAdmin("COSTOS"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=4;
      }else if(cap.equalsIgnoreCase(codAdmin("HOSPITALIZACION"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=5;
      }else if(cap.equalsIgnoreCase(codAdmin("LABORATORIO"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=6;
      }else if(cap.equalsIgnoreCase(codAdmin("RAYOS X"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=7;
      }else if(cap.equalsIgnoreCase(codAdmin("ECOGRAFIA"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=8;
      }else if(cap.equalsIgnoreCase(codAdmin("CONSULTORIOS EXTERNOS"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=9;
      }else if(cap.equalsIgnoreCase(codAdmin("INVESTIGACION BACTEREOLOGICA"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=10;
      }else if(cap.equalsIgnoreCase(codAdmin("ALMACEN"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=11;
      }else if(cap.equalsIgnoreCase(codAdmin("PERSONAL"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=12;
      }else if(cap.equalsIgnoreCase(codAdmin("CONFIGURACION"))){//Para editar tipo de usuario

        cont1=-1;
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=13;
      }else{
          JOptionPane.showMessageDialog(this, "NO PERTENECE A NINGÚN MÓDULO DEL SISTEMA");
      }
         }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,e.getMessage()+ "Error al Iniciar Sesión");
    }
}
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

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelRecuperar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnRecuperar = new javax.swing.JButton();
        txtRespuesta = new javax.swing.JTextField();
        txtPregunta = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        panelCPT = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        panelCPT1 = new javax.swing.JPanel();
        txtContra = new javax.swing.JPasswordField();
        jPanel17 = new javax.swing.JPanel();
        btnIniciarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Huandoy");
        setMinimumSize(new java.awt.Dimension(372, 435));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Iniciar sesión");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Usuario");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Contraseña");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("He olvidado mi contraseña");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        panelRecuperar.setBackground(new java.awt.Color(39, 174, 97));
        panelRecuperar.setForeground(new java.awt.Color(0, 153, 153));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pregunta");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Respuesta");

        btnRecuperar.setBackground(new java.awt.Color(255, 255, 255));
        btnRecuperar.setForeground(new java.awt.Color(102, 102, 102));
        btnRecuperar.setText("Recuperar Contraseña");
        btnRecuperar.setToolTipText("");
        btnRecuperar.setActionCommand("Recuperar \nContraseña");
        btnRecuperar.setBorder(null);
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRecuperarLayout = new javax.swing.GroupLayout(panelRecuperar);
        panelRecuperar.setLayout(panelRecuperarLayout);
        panelRecuperarLayout.setHorizontalGroup(
            panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecuperarLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRecuperarLayout.createSequentialGroup()
                        .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPregunta, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(txtRespuesta))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRecuperarLayout.setVerticalGroup(
            panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecuperarLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(btnRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(230, 230, 230));
        lblFecha.setText("00/00/00");

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(230, 230, 230));
        jLabel2.setText("Fecha");

        jLabel5.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(230, 230, 230));
        jLabel5.setText("Hora");

        lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(230, 230, 230));
        lblHora.setText("00:00:00");

        panelCPT.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

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

        javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
        panelCPT.setLayout(panelCPTLayout);
        panelCPTLayout.setHorizontalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtUsuario)
                .addContainerGap())
        );
        panelCPTLayout.setVerticalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
        panelCPT1.setLayout(panelCPT1Layout);
        panelCPT1Layout.setHorizontalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCPT1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtContra)
                .addContainerGap())
        );
        panelCPT1Layout.setVerticalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT1Layout.createSequentialGroup()
                .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(39, 174, 97));
        jPanel17.setPreferredSize(new java.awt.Dimension(125, 25));

        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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
            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRecuperar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHora)))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        } else if(u.ver_usuario(txtUsuario.getText(),"1")==0){
        } else if(u.ver_usuario(txtUsuario.getText(),"2")==0){


        }else if(u.ver_usuario(txtUsuario.getText(),"2")==0){
            JOptionPane.showMessageDialog(this, "El usuario no existe en el Sistema");     
            panelRecuperar.setVisible(false);
        }else{
            panelRecuperar.setVisible(true);
            txtPregunta.setEditable(false);
            txtPregunta.setText(u1.Pregunta(txtUsuario.getText()));
        }
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
        // TODO add your handling code here:
        Usuario u=new Usuario();
        Usuario us=new Usuario();
        if(txtUsuario.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Ingrese su Nombre de Usuario");
        } else if(txtRespuesta.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Escriba su Respuesta");
        }else if(u.Respuesta(txtUsuario.getText(), txtPregunta.getText()).equalsIgnoreCase(txtRespuesta.getText())){
            JOptionPane.showMessageDialog(rootPane, "Su Contraseña es : "+us.Contrasena(txtUsuario.getText(), txtPregunta.getText()));
            panelRecuperar.setVisible(false);
            txtPregunta.setText("");
            txtRespuesta.setText("");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "La Respuesta es Incorrecta");
            txtRespuesta.setText("");
        }
    }//GEN-LAST:event_btnRecuperarActionPerformed

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
               
                if(filtroModulo==1){
                  Principal.btnCaja.setEnabled(true);
                  Principal.btnFacturador.setEnabled(true);
                }else if(filtroModulo==2){
                  Principal.btnCaja.setEnabled(true);
                  Principal.btnFacturador.setEnabled(true);
                }else if(filtroModulo==3){
                 Principal.btnPersonal.setEnabled(true);
                }else if(filtroModulo==4){
                 Principal.btnUsuarios.setEnabled(true);
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
            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
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
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelRecuperar;
    public static javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtPregunta;
    private javax.swing.JTextField txtRespuesta;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
