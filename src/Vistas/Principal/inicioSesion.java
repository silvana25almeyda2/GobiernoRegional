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
        jDialog1.setVisible(false);
        panelRecuperar.setVisible(false);
        panelError.setVisible(false);
        lblCambio.setVisible(false);
        panelCambio.setVisible(false);
        lblError.setVisible(false);
        estable.setVisible(false);
        jLabel2.setForeground(new Color(255,255,255)); 
        jLabel17.setForeground(new Color(255,255,255)); 
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
                estable.setVisible(false);
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
    
    public void CAMBIO_CONTRASENA_v(){
        if(txtContra1.getText().equalsIgnoreCase(txtContra2.getText())==false){
            estable.setVisible(false);
            panelError.setVisible(true);
            lblError.setText("Las Contraseñas no coinciden");
            lblError.setVisible(true);
            txtContra2.requestFocus();
            
        } else {
                Usuario cno1 = new Usuario();
                cno1.setUsu_Usuario(txtUsuario.getText());
                cno1.setUsu_Contrasena(txtContra2.getText());
                    if(cno1.CAMBIO_CONTRASENA()==true){
                        jLabel9.setText("Cuenta");
                        jLabel2.setForeground(new Color(255,255,255)); 
                        jLabel17.setForeground(new Color(255,255,255)); 
                        jPanel7.setVisible(true);
                        jDialog1.setVisible(false);
                        txtContra.requestFocus();    
                        panelError.setVisible(false);
                        lblError.setVisible(false);
                        lblCambio.setVisible(true);
                        panelCambio.setVisible(true);
//                        jLabel3.setVisible(true);
//                        jPanel5.setVisible(true);
                        estable.setVisible(false);
                        System.out.println("Datos Guardados de forma correcta");
                            
                    } else {
                        panelError.setVisible(true);
                        lblError.setText("Ocurrio un error, Verifique");
                        lblError.setVisible(true);
                    }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JPanel();
        panelCPT11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtContra1 = new javax.swing.JPasswordField();
        panelCPT12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtContra2 = new javax.swing.JPasswordField();
        jPanel21 = new javax.swing.JPanel();
        btnRecuperar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelError = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        panelCambio = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblCambio = new javax.swing.JLabel();
        estable = new javax.swing.JPanel();

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
                    .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("jLabel16");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Se recomienda que cambie su contraseña.");

        jDialog1.setBackground(new java.awt.Color(255, 255, 255));
        jDialog1.setForeground(new java.awt.Color(0, 153, 153));

        panelCPT11.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelCPT11.setPreferredSize(new java.awt.Dimension(50, 33));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Nueva Contraseña");

        txtContra1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtContra1.setForeground(new java.awt.Color(51, 51, 51));
        txtContra1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtContra1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContra1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT11Layout = new javax.swing.GroupLayout(panelCPT11);
        panelCPT11.setLayout(panelCPT11Layout);
        panelCPT11Layout.setHorizontalGroup(
            panelCPT11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCPT11Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(txtContra1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCPT11Layout.setVerticalGroup(
            panelCPT11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtContra1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        );

        panelCPT12.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelCPT12.setPreferredSize(new java.awt.Dimension(308, 33));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Repita");

        txtContra2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtContra2.setForeground(new java.awt.Color(51, 51, 51));
        txtContra2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtContra2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContra2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT12Layout = new javax.swing.GroupLayout(panelCPT12);
        panelCPT12.setLayout(panelCPT12Layout);
        panelCPT12Layout.setHorizontalGroup(
            panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT12Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtContra2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCPT12Layout.setVerticalGroup(
            panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
            .addComponent(txtContra2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(102, 102, 102));
        jPanel21.setPreferredSize(new java.awt.Dimension(125, 25));

        btnRecuperar1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRecuperar1.setForeground(new java.awt.Color(255, 255, 255));
        btnRecuperar1.setText("Cambiar");
        btnRecuperar1.setContentAreaFilled(false);
        btnRecuperar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecuperar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecuperar1.setIconTextGap(30);
        btnRecuperar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRecuperar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRecuperar1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1);
        jDialog1.setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelCPT12, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                        .addComponent(panelCPT11, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelCPT11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCPT12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelRecuperar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDialog1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(51, 51, 51))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRecuperar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDialog1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(64, 64, 64)
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

        panelCambio.setBackground(new java.awt.Color(39, 174, 96));
        panelCambio.setPreferredSize(new java.awt.Dimension(25, 25));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("!");

        javax.swing.GroupLayout panelCambioLayout = new javax.swing.GroupLayout(panelCambio);
        panelCambio.setLayout(panelCambioLayout);
        panelCambioLayout.setHorizontalGroup(
            panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        panelCambioLayout.setVerticalGroup(
            panelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblCambio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCambio.setForeground(new java.awt.Color(255, 255, 255));
        lblCambio.setText("Se cambió la contraseña");
        lblCambio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCambioMouseClicked(evt);
            }
        });

        estable.setBackground(new java.awt.Color(69, 70, 74));
        estable.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout estableLayout = new javax.swing.GroupLayout(estable);
        estable.setLayout(estableLayout);
        estableLayout.setHorizontalGroup(
            estableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        estableLayout.setVerticalGroup(
            estableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

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
                        .addComponent(lblError))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCambio))
                    .addComponent(estable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(estable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
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
        panelError.setVisible(false);
        lblError.setVisible(false);
        lblCambio.setVisible(false);
        panelCambio.setVisible(false);
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
            jLabel3.setVisible(false);
            jPanel5.setVisible(false);
            estable.setVisible(true);
            jLabel9.setText("Recuperación");
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
            estable.setVisible(false);
            panelError.setVisible(true);
            lblError.setText("Escriba su Respuesta");
            lblError.setVisible(true);
//            JOptionPane.showMessageDialog(rootPane, "Escriba su Respuesta");
        }else if(u.Respuesta(txtUsuario.getText(), txtPregunta.getText()).equalsIgnoreCase(txtRespuesta.getText())){
            jLabel9.setText("Cambio de contraseña");
            estable.setVisible(true);
            panelError.setVisible(false);
            lblError.setVisible(false);
            jPanel7.setVisible(false);
            panelRecuperar.setVisible(false);
            String a ="";
            a=us.Contrasena(txtUsuario.getText(), txtPregunta.getText());
            jLabel2.setForeground(new Color(255,255,255)); 
            jLabel17.setForeground(new Color(255,255,255)); 
            jLabel2.setText("Su contraseña de Recuperación es    "+a);
            jDialog1.setVisible(true);
            txtPregunta.setText("");
            txtRespuesta.setText("");
            txtContra1.requestFocus();
        }
        else{
            estable.setVisible(false);
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

    private void btnRecuperar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperar1ActionPerformed
        CAMBIO_CONTRASENA_v();
        
    }//GEN-LAST:event_btnRecuperar1ActionPerformed

    private void txtContra1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContra1KeyPressed
        // TODO add your handling code here:

        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnIniciarSesion.doClick();
        }
    }//GEN-LAST:event_txtContra1KeyPressed

    private void txtContra2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContra2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContra2KeyPressed

    private void lblCambioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCambioMouseClicked
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
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JButton btnRecuperar1;
    private javax.swing.JPanel estable;
    private javax.swing.JPanel jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT10;
    private javax.swing.JPanel panelCPT11;
    private javax.swing.JPanel panelCPT12;
    private javax.swing.JPanel panelCPT9;
    private javax.swing.JPanel panelCambio;
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
