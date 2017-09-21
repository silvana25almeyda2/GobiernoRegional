/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import Servicios.Conexion;
import Vistas.Principal.Principal;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import modelo.Caja.Caja_Cliente;
import modelo.Caja.Caja_NuevaVenta;

/**
 *
 * @author MYS1
 */
public class Caja_Clientes extends javax.swing.JFrame {
Conexion c=new Conexion();
Connection conexion=c.conectar();
byte tg;
byte tgm;
private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * Creates new form Caja_Clientes
     */
    public Caja_Clientes() {
        initComponents();
        Caja_Cliente A = new Caja_Cliente();
        Caja_Cliente N = new Caja_Cliente();
        A.LISTA_CLIENTES("",tb_Clientes);
        N.LISTAR_PERMISOS(Principal.lblUsu.getText());
        cargareliminar.setVisible(false);
        NivelSuperior.setLocationRelativeTo(null);//en el centro
        this.setExtendedState(MAXIMIZED_BOTH);
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Mind Map-100.png")).getImage());
        LIMPIAR();
        HABILITAR(false);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        jPanel7.setVisible(false);
        jPanel6.setVisible(false);
        jPanel8.setVisible(false);
        cbxMoneda.setBackground(Color.WHITE);
        cbxTipoDoc.setBackground(Color.WHITE);
        Paginas.setEnabled(false);
        Paginas.setEnabledAt(0,false);
        Paginas.setEnabledAt(1, false);
        btnCaja.setVisible(false);
    }
    
    private final static Pattern RTRIM = Pattern.compile("\\s+$");
    public static String rtrim(String s) {
    return RTRIM.matcher(s).replaceAll("");
    }
    
    public static boolean valida_Email(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
    public void LIMPIAR(){
        lblID.setText("");
        txtT1.setText("");
        txtT2.setText("");
        txtDOC.setText("");
        txtNom.setText("");
        txtApeP.setText("");
        txtApeM.setText("");
        txtRazonS.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtCodigo.setText("");
        jLabel33.setText("Nuevo Registro");
//        cbxTipoDoc.getSelectedItem().toString());
    }
    
    public void HABILITAR (boolean opcion){
        txtDOC.setEditable(opcion);
        txtNom.setEditable(opcion);
        txtApeP.setEditable(opcion);
        txtApeM.setEditable(opcion);
        txtRazonS.setEditable(opcion);
        txtDireccion.setEditable(opcion);
        txtCorreo.setEditable(opcion);
        cbxMoneda.setEnabled(opcion);
        txtCodigo.setEditable(opcion);
        cbxTipoDoc.setEnabled(opcion);
    }
    public void CARGAR(){
        int fila=tb_Clientes.getSelectedRow();
        lblID.setText(String.valueOf(tb_Clientes.getValueAt(fila, 0)));  
        cbxTipoDoc.setSelectedItem(String.valueOf(tb_Clientes.getValueAt(fila, 1))); 
        txtDOC.setText(String.valueOf(tb_Clientes.getValueAt(fila, 2)));  
        txtNom.setText(String.valueOf(tb_Clientes.getValueAt(fila, 3))); 
        txtApeP.setText(String.valueOf(tb_Clientes.getValueAt(fila, 4)));  
        txtApeM.setText(String.valueOf(tb_Clientes.getValueAt(fila, 5))); 
        txtRazonS.setText(String.valueOf(tb_Clientes.getValueAt(fila, 6)));  
        txtDireccion.setText(String.valueOf(tb_Clientes.getValueAt(fila, 7))); 
        txtCorreo.setText(String.valueOf(tb_Clientes.getValueAt(fila, 8)));  
        cbxMoneda.setSelectedItem(String.valueOf(tb_Clientes.getValueAt(fila, 9))); 
        txtCodigo.setText(String.valueOf(tb_Clientes.getValueAt(fila, 10)));  
        
        if (tb_Clientes.getValueAt(fila, 11).equals("N") ){
            txtT1.setText("X");
            txtT2.setText(""); 
            jPanel7.setVisible(false);
            jPanel6.setVisible(true);
            jPanel8.setVisible(true);
            txtRazonS.setText("");
            txtCodigo.setText("0");
            lblCab.setText("Datos del Cliente");
            lblDet.setVisible(false);
        }
        if (tb_Clientes.getValueAt(fila, 11).equals("J") ){
            txtT2.setText("X");
            txtT1.setText(""); 
            jPanel7.setVisible(true);
            jPanel6.setVisible(true);
            jPanel8.setVisible(true);
            lblDet.setVisible(true);
            lblCab.setText("Datos del Representante");
            lblDet.setText("Datos de la Empresa");

        }
    }
    
    public void NUEVO_REGISTRO(){
        if(valida_Email(txtCorreo.getText())==false){
            System.out.println("correo no valido");
        }
        if((txtDOC.getText().equals(""))){
            cargareliminar.setVisible(true);        
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Debe completar los campos requeridos");
            eli.setVisible(true);
            noeli.setVisible(false);
            tgm=0;                 
        } else {
                Caja_Cliente cno1 = new Caja_Cliente();
                cno1.setDNI(txtDOC.getText());
                cno1.setNOMBRES(txtNom.getText());
                cno1.setAPELLIDO_PATERNO(txtApeP.getText());
                cno1.setAPELLIDO_MATERNO(txtApeM.getText());
                if(txtT1.getText().equals("X"))
                    cno1.setRUC("N");
                else if(txtT2.getText().equals("X"))
                    cno1.setRUC("J");
                cno1.setRAZON_SOCIAL(txtRazonS.getText());
                cno1.setDIRECCION(txtDireccion.getText());
                cno1.setCORREO(txtCorreo.getText());
                cno1.setTIPO_MONEDA(cbxMoneda.getSelectedItem().toString());
                cno1.setCODIGO_FISCAL(Integer.parseInt(txtCodigo.getText()));
                cno1.setTIPO_DOCUMENTO(cbxTipoDoc.getSelectedItem().toString());
                cno1.setUSUARIO(lblusu.getText());
                    if(cno1.NUEVO_CLIENTE()==true){
                        if(lblTipoR.getText().equals("N")){
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(0,153,102)); 
                            Mensaje.setText("Datos Guardados de forma correcta");
                            eli.setText("OK");
                            eli.setVisible(true);
                            noeli.setVisible(false);
                            tgm=1;
                            btnguardar.setEnabled(false);
                            btneditar.setEnabled(false);
                            btneliminar.setEnabled(true);
                            Caja_Cliente A = new Caja_Cliente();
                            A.LISTA_CLIENTES("",tb_Clientes);
                            jLabel33.setText("Listado");
                            Paginas.setSelectedIndex(0);
                        }else if(lblTipoR.getText().equals("C")){
                            Caja_NuevaVenta CNC = new Caja_NuevaVenta();
                            CNC.ULTIMO_CLIENTE_REGISTRADO(lblusu.getText());
                            dispose();
                            
                        }
                        
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                    }
        }                 
    }
    public void MODIFICAR_REGISTRO(){

                Caja_Cliente cno1 = new Caja_Cliente();
                cno1.setID_CLIENTE(Integer.parseInt(lblID.getText()));
                cno1.setDNI(txtDOC.getText());
                cno1.setNOMBRES(txtNom.getText());
                cno1.setAPELLIDO_PATERNO(txtApeP.getText());
                cno1.setAPELLIDO_MATERNO(txtApeM.getText());
                if(txtT1.getText().equals("X"))
                    cno1.setRUC("N");
                else if(txtT2.getText().equals("X"))
                    cno1.setRUC("J");
                cno1.setRAZON_SOCIAL(txtRazonS.getText());
                cno1.setDIRECCION(txtDireccion.getText());
                cno1.setCORREO(txtCorreo.getText());
                cno1.setTIPO_MONEDA(cbxMoneda.getSelectedItem().toString());
                cno1.setCODIGO_FISCAL(Integer.parseInt(txtCodigo.getText()));
                cno1.setTIPO_DOCUMENTO(cbxTipoDoc.getSelectedItem().toString());
                cno1.setUSUARIO(lblusu.getText());
                    if(cno1.MODIFICAR_CLIENTE()==true){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Datos Actualizados de forma correcta");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                        tgm=2;
                        btnguardar.setEnabled(false);
                        btneditar.setEnabled(false);
                        btneliminar.setEnabled(true);
                        Caja_Cliente A = new Caja_Cliente();
                        A.LISTA_CLIENTES("",tb_Clientes);
                        jLabel33.setText("Listado");
                        Paginas.setSelectedIndex(0);
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                    }               
    }
    
    public void ELIMINAR_CLIENTE(){ 
        try{
                Caja_Cliente hCEl = new Caja_Cliente();
                hCEl.setID_CLIENTE(Integer.parseInt(lblID.getText()));
                if(hCEl.ELIMINAR_CLIENTE()){
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setText("OK");
                    eli.setVisible(true);
                    noeli.setVisible(false);
                    tgm=9;
                    Caja_Cliente A = new Caja_Cliente();
                    A.LISTA_CLIENTES("",tb_Clientes);
                    Paginas.setSelectedIndex(0);
                    
                }
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
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

        NivelSuperior = new javax.swing.JDialog();
        jPanel145 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jPanel146 = new javax.swing.JPanel();
        btnAlertConsulta10 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        buscartodo = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        lbldetalle = new javax.swing.JLabel();
        btnLista = new javax.swing.JButton();
        btnCaja = new javax.swing.JButton();
        lblNivel = new javax.swing.JLabel();
        lblPermiso = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        Paginas = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_Clientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel3 = new javax.swing.JPanel();
            jLabel4 = new javax.swing.JLabel();
            txtT1 = new javax.swing.JTextField();
            txtT2 = new javax.swing.JTextField();
            lblNatural = new javax.swing.JLabel();
            lblJuridica = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            jLabel9 = new javax.swing.JLabel();
            panelCPT4 = new javax.swing.JPanel();
            txtNom = new javax.swing.JTextField();
            jLabel10 = new javax.swing.JLabel();
            panelCPT5 = new javax.swing.JPanel();
            txtApeP = new javax.swing.JTextField();
            panelCPT6 = new javax.swing.JPanel();
            txtApeM = new javax.swing.JTextField();
            panelCPT7 = new javax.swing.JPanel();
            txtDOC = new javax.swing.JTextField();
            jLabel11 = new javax.swing.JLabel();
            jLabel12 = new javax.swing.JLabel();
            lblID = new javax.swing.JLabel();
            cbxTipoDoc = new javax.swing.JComboBox();
            jLabel13 = new javax.swing.JLabel();
            lblCab = new javax.swing.JLabel();
            jPanel7 = new javax.swing.JPanel();
            jLabel14 = new javax.swing.JLabel();
            panelCPT2 = new javax.swing.JPanel();
            txtRazonS = new javax.swing.JTextField();
            jLabel15 = new javax.swing.JLabel();
            panelCPT9 = new javax.swing.JPanel();
            txtCodigo = new javax.swing.JTextField();
            jLabel17 = new javax.swing.JLabel();
            cbxMoneda = new javax.swing.JComboBox();
            lblDet = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            jLabel18 = new javax.swing.JLabel();
            panelCPT3 = new javax.swing.JPanel();
            txtCorreo = new javax.swing.JTextField();
            panelCPT13 = new javax.swing.JPanel();
            txtDireccion = new javax.swing.JTextField();
            jLabel20 = new javax.swing.JLabel();
            lblTipoR = new javax.swing.JLabel();
            cargareliminar = new javax.swing.JPanel();
            Mensaje = new javax.swing.JLabel();
            eli = new javax.swing.JButton();
            noeli = new javax.swing.JButton();

            NivelSuperior.setAlwaysOnTop(true);
            NivelSuperior.setMinimumSize(new java.awt.Dimension(430, 200));
            NivelSuperior.setResizable(false);

            jPanel145.setBackground(new java.awt.Color(230, 230, 230));

            jLabel64.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel64.setForeground(new java.awt.Color(51, 51, 51));
            jLabel64.setText("Error");

            jPanel146.setBackground(new java.awt.Color(23, 160, 134));

            btnAlertConsulta10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            btnAlertConsulta10.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta10.setText("Entendido");
            btnAlertConsulta10.setContentAreaFilled(false);
            btnAlertConsulta10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta10.setIconTextGap(30);
            btnAlertConsulta10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta10ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel146Layout = new javax.swing.GroupLayout(jPanel146);
            jPanel146.setLayout(jPanel146Layout);
            jPanel146Layout.setHorizontalGroup(
                jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta10, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
            );
            jPanel146Layout.setVerticalGroup(
                jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel146Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta10))
            );

            jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel67.setForeground(new java.awt.Color(51, 51, 51));
            jLabel67.setText("<html>Necesita permisos especiales <BR>Para realizar esta acción.</html>");

            javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
            jPanel145.setLayout(jPanel145Layout);
            jPanel145Layout.setHorizontalGroup(
                jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel145Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel64)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(228, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel145Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
            );
            jPanel145Layout.setVerticalGroup(
                jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel145Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel64)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout NivelSuperiorLayout = new javax.swing.GroupLayout(NivelSuperior.getContentPane());
            NivelSuperior.getContentPane().setLayout(NivelSuperiorLayout);
            NivelSuperiorLayout.setHorizontalGroup(
                NivelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            NivelSuperiorLayout.setVerticalGroup(
                NivelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(39, 174, 96));
            jPanel1.setPreferredSize(new java.awt.Dimension(284, 678));

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("<html>Clientes<span style=\"font-size:'14px'\"><br>  </br></span></html>");

            btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Documento-32.png"))); // NOI18N
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
            btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Documento-32.png"))); // NOI18N
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
            btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Basura-32.png"))); // NOI18N
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

            lblusu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblusu.setForeground(new java.awt.Color(255, 255, 255));
            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Usuario-40.png"))); // NOI18N
            lblusu.setText("ALGUIEN");
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
            lbldetalle.setText("Empresa o Representante, RUC, DNI ");

            btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnLista.setForeground(new java.awt.Color(240, 240, 240));
            btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Orden de compra-32.png"))); // NOI18N
            btnLista.setText("Listado");
            btnLista.setContentAreaFilled(false);
            btnLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnLista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnLista.setIconTextGap(30);
            btnLista.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnLista.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnListaMouseClicked(evt);
                }
            });
            btnLista.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnListaActionPerformed(evt);
                }
            });

            btnCaja.setText("CAJA");
            btnCaja.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCajaActionPerformed(evt);
                }
            });

            lblNivel.setForeground(new java.awt.Color(39, 174, 96));
            lblNivel.setText("jLabel2");

            lblPermiso.setForeground(new java.awt.Color(39, 174, 96));
            lblPermiso.setText("jLabel3");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbldetalle)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblNivel)
                                        .addComponent(lblPermiso)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(btnCaja)))
                            .addGap(0, 9, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldetalle))
                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addComponent(btnNuevo)
                    .addGap(18, 18, 18)
                    .addComponent(btnguardar)
                    .addGap(18, 18, 18)
                    .addComponent(btneditar)
                    .addGap(18, 18, 18)
                    .addComponent(btneliminar)
                    .addGap(18, 18, 18)
                    .addComponent(btnLista)
                    .addGap(18, 18, 18)
                    .addComponent(btnCaja)
                    .addGap(30, 30, 30)
                    .addComponent(lblNivel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblPermiso)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

            Paginas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            Paginas.setForeground(new java.awt.Color(255, 255, 255));
            Paginas.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
            Paginas.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

            jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tb_Clientes.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            tb_Clientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            tb_Clientes.setModel(new javax.swing.table.DefaultTableModel(
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
            tb_Clientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tb_Clientes.setGridColor(new java.awt.Color(255, 255, 255));
            tb_Clientes.setRowHeight(25);
            tb_Clientes.setSelectionBackground(new java.awt.Color(102, 102, 102));
            tb_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_ClientesMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tb_ClientesMousePressed(evt);
                }
            });
            tb_Clientes.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_ClientesKeyPressed(evt);
                }
            });
            jScrollPane3.setViewportView(tb_Clientes);

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
            );

            Paginas.addTab("tab1", jPanel2);

            jPanel3.setBackground(new java.awt.Color(255, 255, 255));

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(51, 51, 51));
            jLabel4.setText("Tipo de Cliente ");

            txtT1.setEditable(false);
            txtT1.setBackground(new java.awt.Color(255, 255, 255));
            txtT1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtT1.setForeground(new java.awt.Color(102, 102, 102));
            txtT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtT1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            txtT1.setPreferredSize(new java.awt.Dimension(18, 18));
            txtT1.setSelectedTextColor(new java.awt.Color(102, 102, 102));
            txtT1.setSelectionColor(new java.awt.Color(255, 255, 255));
            txtT1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtT1CaretUpdate(evt);
                }
            });
            txtT1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtT1MouseClicked(evt);
                }
            });
            txtT1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtT1ActionPerformed(evt);
                }
            });

            txtT2.setEditable(false);
            txtT2.setBackground(new java.awt.Color(255, 204, 51));
            txtT2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtT2.setForeground(new java.awt.Color(102, 102, 102));
            txtT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtT2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            txtT2.setPreferredSize(new java.awt.Dimension(18, 18));
            txtT2.setSelectedTextColor(new java.awt.Color(102, 102, 102));
            txtT2.setSelectionColor(new java.awt.Color(255, 204, 51));
            txtT2.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtT2CaretUpdate(evt);
                }
            });
            txtT2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtT2MouseClicked(evt);
                }
            });
            txtT2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtT2ActionPerformed(evt);
                }
            });

            lblNatural.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblNatural.setText("Natural");

            lblJuridica.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblJuridica.setText("Jurídica");

            jPanel6.setBackground(new java.awt.Color(255, 255, 255));

            jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel9.setForeground(new java.awt.Color(51, 51, 51));
            jLabel9.setText("Nombres");

            panelCPT4.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtNom.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtNom.setForeground(new java.awt.Color(51, 51, 51));
            txtNom.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtNom.setBorder(null);
            txtNom.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtNomCaretUpdate(evt);
                }
            });
            txtNom.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNomKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtNomKeyReleased(evt);
                }
            });

            javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
            panelCPT4.setLayout(panelCPT4Layout);
            panelCPT4Layout.setHorizontalGroup(
                panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT4Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtNom)
                    .addGap(4, 4, 4))
            );
            panelCPT4Layout.setVerticalGroup(
                panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT4Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel10.setForeground(new java.awt.Color(51, 51, 51));
            jLabel10.setText("Apellido Materno");

            panelCPT5.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtApeP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtApeP.setForeground(new java.awt.Color(51, 51, 51));
            txtApeP.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtApeP.setBorder(null);
            txtApeP.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtApePCaretUpdate(evt);
                }
            });
            txtApeP.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtApePKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtApePKeyReleased(evt);
                }
            });

            javax.swing.GroupLayout panelCPT5Layout = new javax.swing.GroupLayout(panelCPT5);
            panelCPT5.setLayout(panelCPT5Layout);
            panelCPT5Layout.setHorizontalGroup(
                panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT5Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtApeP)
                    .addGap(4, 4, 4))
            );
            panelCPT5Layout.setVerticalGroup(
                panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT5Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtApeP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            panelCPT6.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtApeM.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtApeM.setForeground(new java.awt.Color(51, 51, 51));
            txtApeM.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtApeM.setBorder(null);
            txtApeM.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtApeMCaretUpdate(evt);
                }
            });
            txtApeM.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtApeMKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtApeMKeyReleased(evt);
                }
            });

            javax.swing.GroupLayout panelCPT6Layout = new javax.swing.GroupLayout(panelCPT6);
            panelCPT6.setLayout(panelCPT6Layout);
            panelCPT6Layout.setHorizontalGroup(
                panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT6Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtApeM)
                    .addGap(4, 4, 4))
            );
            panelCPT6Layout.setVerticalGroup(
                panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT6Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtApeM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            panelCPT7.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtDOC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtDOC.setForeground(new java.awt.Color(51, 51, 51));
            txtDOC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtDOC.setBorder(null);
            txtDOC.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtDOCCaretUpdate(evt);
                }
            });
            txtDOC.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtDOCKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtDOCKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPT7Layout = new javax.swing.GroupLayout(panelCPT7);
            panelCPT7.setLayout(panelCPT7Layout);
            panelCPT7Layout.setHorizontalGroup(
                panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT7Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtDOC, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addGap(4, 4, 4))
            );
            panelCPT7Layout.setVerticalGroup(
                panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT7Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtDOC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel11.setForeground(new java.awt.Color(51, 51, 51));
            jLabel11.setText("Número Documento");

            jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel12.setForeground(new java.awt.Color(51, 51, 51));
            jLabel12.setText("Apellido Paterno");

            lblID.setForeground(new java.awt.Color(255, 255, 255));
            lblID.setText("jLabel3");

            cbxTipoDoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            cbxTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "4 CARNET DE EXTRANJERIA", "6 RUC", "7 PASAPORTE", "A CED.DIPLOMATICA DE IDENTIDAD" }));

            jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel13.setForeground(new java.awt.Color(51, 51, 51));
            jLabel13.setText("Tipo Documento");

            lblCab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblCab.setForeground(new java.awt.Color(51, 51, 51));
            lblCab.setText("jLabel3");

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(lblCab)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12))
                            .addGap(29, 29, 29)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelCPT5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelCPT6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel13))
                            .addGap(9, 9, 9)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(cbxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(panelCPT4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(panelCPT7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblID)
                                    .addGap(99, 99, 99)))))
                    .addContainerGap())
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelCPT7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(lblID))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lblCab)
                    .addGap(8, 8, 8)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)
                        .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            jPanel7.setBackground(new java.awt.Color(255, 255, 255));

            jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel14.setForeground(new java.awt.Color(51, 51, 51));
            jLabel14.setText("Razón social");

            panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtRazonS.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtRazonS.setForeground(new java.awt.Color(51, 51, 51));
            txtRazonS.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtRazonS.setBorder(null);
            txtRazonS.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtRazonSCaretUpdate(evt);
                }
            });
            txtRazonS.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtRazonSKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtRazonSKeyReleased(evt);
                }
            });

            javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
            panelCPT2.setLayout(panelCPT2Layout);
            panelCPT2Layout.setHorizontalGroup(
                panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT2Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtRazonS)
                    .addGap(4, 4, 4))
            );
            panelCPT2Layout.setVerticalGroup(
                panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtRazonS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel15.setForeground(new java.awt.Color(51, 51, 51));
            jLabel15.setText("Código fiscal");

            panelCPT9.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCodigo.setForeground(new java.awt.Color(51, 51, 51));
            txtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtCodigo.setBorder(null);
            txtCodigo.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCodigoCaretUpdate(evt);
                }
            });
            txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtCodigoKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtCodigoKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPT9Layout = new javax.swing.GroupLayout(panelCPT9);
            panelCPT9.setLayout(panelCPT9Layout);
            panelCPT9Layout.setHorizontalGroup(
                panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT9Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addGap(4, 4, 4))
            );
            panelCPT9Layout.setVerticalGroup(
                panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT9Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel17.setForeground(new java.awt.Color(51, 51, 51));
            jLabel17.setText("Moneda");

            cbxMoneda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            cbxMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PEN" }));
            cbxMoneda.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    cbxMonedaKeyPressed(evt);
                }
            });

            lblDet.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblDet.setForeground(new java.awt.Color(51, 51, 51));
            lblDet.setText("jLabel3");

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(lblDet)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15)
                                .addComponent(jLabel17))
                            .addGap(59, 59, 59)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelCPT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelCPT9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addContainerGap())
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblDet)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel14)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel17)
                        .addComponent(cbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel15)
                        .addComponent(panelCPT9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            jPanel8.setBackground(new java.awt.Color(255, 255, 255));

            jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel18.setForeground(new java.awt.Color(51, 51, 51));
            jLabel18.setText("Correo");

            panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCorreo.setForeground(new java.awt.Color(51, 51, 51));
            txtCorreo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtCorreo.setBorder(null);
            txtCorreo.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCorreoCaretUpdate(evt);
                }
            });
            txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtCorreoKeyPressed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
            panelCPT3.setLayout(panelCPT3Layout);
            panelCPT3Layout.setHorizontalGroup(
                panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT3Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtCorreo)
                    .addGap(4, 4, 4))
            );
            panelCPT3Layout.setVerticalGroup(
                panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT3Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            panelCPT13.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtDireccion.setForeground(new java.awt.Color(51, 51, 51));
            txtDireccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtDireccion.setBorder(null);
            txtDireccion.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtDireccionCaretUpdate(evt);
                }
            });
            txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtDireccionKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtDireccionKeyReleased(evt);
                }
            });

            javax.swing.GroupLayout panelCPT13Layout = new javax.swing.GroupLayout(panelCPT13);
            panelCPT13.setLayout(panelCPT13Layout);
            panelCPT13Layout.setHorizontalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtDireccion)
                    .addGap(4, 4, 4))
            );
            panelCPT13Layout.setVerticalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(51, 51, 51));
            jLabel20.setText("Dirección");

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18)
                        .addComponent(jLabel20))
                    .addGap(80, 80, 80)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCPT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(panelCPT13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(151, 151, 151)))
                    .addContainerGap())
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelCPT13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            lblTipoR.setForeground(new java.awt.Color(255, 255, 255));
            lblTipoR.setText("jLabel2");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(40, 40, 40)
                            .addComponent(txtT1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(lblNatural)
                            .addGap(54, 54, 54)
                            .addComponent(txtT2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblJuridica)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTipoR)
                            .addGap(79, 79, 79))
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(32, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNatural)
                        .addComponent(lblJuridica)
                        .addComponent(lblTipoR))
                    .addGap(0, 0, 0)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            Paginas.addTab("tab2", jPanel3);

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
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                        .addComponent(Paginas)
                        .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(Paginas))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
//        if(lblPermiso.getText().equals("E")){
            tg = 1;
            lblTipoR.setText("N");
            btnNuevo.setEnabled(true);
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
            jPanel7.setVisible(false);
            jPanel6.setVisible(false);
            jPanel8.setVisible(false);
            LIMPIAR();
            HABILITAR(true);
            Paginas.setSelectedIndex(1);
//        }else if(!lblPermiso.getText().equals("E")){
//            NivelSuperior.setUndecorated(true);
//            NivelSuperior.setVisible(true);
//        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if(lblPermiso.getText().equals("E")){
            tg=2;
            System.out.println("tg  2");
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            HABILITAR(true);
            Paginas.setSelectedIndex(1);
        }else if(!lblPermiso.getText().equals("E")){
            System.out.println("tg");
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        Caja_Cliente cn = new Caja_Cliente();
        String dni;
        dni=txtDOC.getText();
        String sdni;
        sdni=rtrim(dni);
        int c;
        c=sdni.length();
        System.out.println(""+c);
            if(tg==1){
                if(cbxTipoDoc.getSelectedItem().equals("1 DNI")&&c<8){
                cargareliminar.setVisible(true);
                cargareliminar.setBackground(new Color(255,91,70)); 
                Mensaje.setText("El DNI ingresado es Incorrecto, Verifique ");
                eli.setVisible(false);
                noeli.setVisible(false);
                txtDOC.requestFocus(); 
                }else if(cbxTipoDoc.getSelectedItem().equals("6 RUC")&&c<11){
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,91,70)); 
                    Mensaje.setText("El RUC ingresado es Incorrecto, Verifique ");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                    txtDOC.requestFocus(); 
                    } else if(valida_Email(txtCorreo.getText())==false){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("El Correo Electronico ingresado no es valido, Verifique ");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                        txtCorreo.requestFocus();    
                    }else if(cn.VALIDAR_DOCUMENTO(txtDOC.getText())>0){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("El Nª de Documento ingresado ya existe, Verifique ");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                        txtDOC.requestFocus();    
                }else
                 NUEVO_REGISTRO();  
            }
            if(tg==2){
               cargareliminar.setVisible(true);
               cargareliminar.setBackground(new Color(255,153,51)); 
               Mensaje.setText("Desea Actualizar el Registro ?");
               eli.setText("Si");
               eli.setVisible(true);
               noeli.setVisible(true); 
               tgm=3;
            }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if(lblPermiso.getText().equals("E")){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Desea Eliminar este registro?");
            eli.setText("Si");
            eli.setVisible(true);
            noeli.setText("No");
            noeli.setVisible(true);
            tgm=8;  
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }    
    }//GEN-LAST:event_btneliminarActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Caja_Cliente A = new Caja_Cliente();
        A.LISTA_CLIENTES(buscartodo.getText(),tb_Clientes);
        Paginas.setSelectedIndex(0);
        jLabel33.setText("Resultados de la Busqueda");
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        jLabel33.setText("Listado");
        buscartodo.setText("");
        Caja_Cliente A = new Caja_Cliente();
        A.LISTA_CLIENTES("",tb_Clientes);
        Paginas.setSelectedIndex(0);
    }//GEN-LAST:event_btnListaActionPerformed

    private void tb_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMouseClicked
        
        if(evt.getClickCount()==1){
            CARGAR();
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);
        }
        if(evt.getClickCount()==2){
            jLabel33.setText("Edición");
            Paginas.setSelectedIndex(1);
            
        }

        
    }//GEN-LAST:event_tb_ClientesMouseClicked

    private void tb_ClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMousePressed

    }//GEN-LAST:event_tb_ClientesMousePressed

    private void tb_ClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ClientesKeyPressed
        
    }//GEN-LAST:event_tb_ClientesKeyPressed

    private void txtT1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1CaretUpdate

    private void txtT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT1MouseClicked
        if (lblID.getText().equals("")){
            if(txtT1.getText().equals("") && evt.getClickCount()==1){
            txtT1.setText("X");
            txtT2.setText("");
            jPanel7.setVisible(false);
            jPanel6.setVisible(true);
            jPanel8.setVisible(true);
            txtRazonS.setText("");
            txtCodigo.setText("0");
            lblCab.setText("Datos del Cliente");
            lblDet.setVisible(false);

        }else
        if(txtT1.getText().equals("X") && evt.getClickCount()==1){
            txtT1.setText("");
            txtT2.setText("");
            jPanel7.setVisible(false);
            jPanel6.setVisible(false);
            jPanel8.setVisible(false);
        }
        }
        
    }//GEN-LAST:event_txtT1MouseClicked

    private void txtT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1ActionPerformed

    private void txtT2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT2CaretUpdate

    private void txtT2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT2MouseClicked
    
        if (lblID.getText().equals("")){
            if(txtT2.getText().equals("") && evt.getClickCount()==1){
            txtT2.setText("X");
            txtT1.setText("");
            jPanel7.setVisible(true);
            jPanel6.setVisible(true);
            jPanel8.setVisible(true);
            lblDet.setVisible(true);
            lblCab.setText("Datos del Representante");
            lblDet.setText("Datos de la Empresa");
            txtCodigo.setText("");

        }else
        if(txtT2.getText().equals("X") && evt.getClickCount()==1){
            txtT2.setText("");
            txtT1.setText("");
            jPanel7.setVisible(false);
            jPanel6.setVisible(false);
            jPanel8.setVisible(false);
        }
        }
        
    }//GEN-LAST:event_txtT2MouseClicked

    private void txtT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT2ActionPerformed

    private void txtNomCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNomCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomCaretUpdate

    private void txtApePCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtApePCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApePCaretUpdate

    private void txtApeMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtApeMCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeMCaretUpdate

    private void txtDOCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDOCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOCCaretUpdate

    private void txtRazonSCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRazonSCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSCaretUpdate

    private void txtCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodigoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCaretUpdate

    private void txtCorreoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCorreoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoCaretUpdate

    private void txtDireccionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDireccionCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionCaretUpdate

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        Caja_Cliente cn = new Caja_Cliente();
        String dni;
        dni=txtDOC.getText();
        String sdni;
        sdni=rtrim(dni);
        int c;
        c=sdni.length();
        System.out.println(""+c);
        if (tgm==3){
            if(cbxTipoDoc.getSelectedItem().equals("1 DNI")&&c<8){
                cargareliminar.setVisible(true);
                cargareliminar.setBackground(new Color(255,91,70)); 
                Mensaje.setText("El DNI ingresado es Incorrecto, Verifique ");
                eli.setVisible(false);
                noeli.setVisible(false);
                txtDOC.requestFocus(); 
                }else if(cbxTipoDoc.getSelectedItem().equals("6 RUC")&&c<11){
                    cargareliminar.setVisible(true);
                    cargareliminar.setBackground(new Color(255,91,70)); 
                    Mensaje.setText("El RUC ingresado es Incorrecto, Verifique ");
                    eli.setVisible(false);
                    noeli.setVisible(false);
                    txtDOC.requestFocus(); 
                    } else if(valida_Email(txtCorreo.getText())==false){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("El Correo Electronico ingresado no es valido, Verifique ");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                        txtCorreo.requestFocus();    
            }else
                          
            
            
                    MODIFICAR_REGISTRO();
                    btnguardar.setEnabled(true);
                    btneditar.setEnabled(false);
                    btneliminar.setEnabled(false);          
                    
        } else
        if (tgm==8){
            ELIMINAR_CLIENTE();
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
        }
        else if (tgm==2 ||tgm==1||tgm==9){
            cargareliminar.setVisible(false);
        }
        if (tgm==0){
            cargareliminar.setVisible(false);
        }
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void txtNomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyReleased
        txtNom.setText(txtNom.getText().toUpperCase());
    }//GEN-LAST:event_txtNomKeyReleased

    private void txtApePKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePKeyReleased
        txtApeP.setText(txtApeP.getText().toUpperCase());
    }//GEN-LAST:event_txtApePKeyReleased

    private void txtApeMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMKeyReleased
        txtApeM.setText(txtApeM.getText().toUpperCase());
    }//GEN-LAST:event_txtApeMKeyReleased

    private void txtRazonSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSKeyReleased
        txtRazonS.setText(txtRazonS.getText().toUpperCase());
    }//GEN-LAST:event_txtRazonSKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        txtDireccion.setText(txtDireccion.getText().toUpperCase());
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDOCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOCKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtNom.requestFocus();
        }
    }//GEN-LAST:event_txtDOCKeyPressed

    private void txtNomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtApeP.requestFocus();
        }
    }//GEN-LAST:event_txtNomKeyPressed

    private void txtApeMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMKeyPressed
        if(txtT1.getText().equals("X")){
            char teclaPresionada = evt.getKeyChar();
            if(teclaPresionada==KeyEvent.VK_ENTER){
                txtDireccion.requestFocus();
            }
        }else if(txtT2.getText().equals("X")){
            char teclaPresionada = evt.getKeyChar();
            if(teclaPresionada==KeyEvent.VK_ENTER){
                txtRazonS.requestFocus();
            }
        }
    }//GEN-LAST:event_txtApeMKeyPressed

    private void txtApePKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtApeM.requestFocus();
        }
    }//GEN-LAST:event_txtApePKeyPressed

    private void txtCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyPressed

    }//GEN-LAST:event_txtCorreoKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtCorreo.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtRazonSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSKeyPressed
        char teclaPresionada = evt.getKeyChar();
            if(teclaPresionada==KeyEvent.VK_ENTER){
                cbxMoneda.requestFocus(true);
                cbxMoneda.showPopup();        
            }
    }//GEN-LAST:event_txtRazonSKeyPressed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        char teclaPresionada = evt.getKeyChar();
            if(teclaPresionada==KeyEvent.VK_ENTER){
                txtDireccion.requestFocus();
            }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        if (txtCodigo.getText().length()==3){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();            
        } 
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void cbxMonedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxMonedaKeyPressed
        char teclaPresionada = evt.getKeyChar();
            if(teclaPresionada==KeyEvent.VK_ENTER){
                txtCodigo.requestFocus();      
            }
    }//GEN-LAST:event_cbxMonedaKeyPressed

    private void txtDOCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOCKeyTyped
        if(txtT1.getText().equals("X")){
            if (txtDOC.getText().length()==8){
                evt.consume();
//                Toolkit.getDefaultToolkit().beep();
            }
            char tecla;
            tecla = evt.getKeyChar();
            if(!Character.isDigit(tecla)){
                evt.consume();
//                getToolkit().beep();            
            } 
        }else if(txtT2.getText().equals("X")){
            if (txtDOC.getText().length()==11){
                evt.consume();
//                Toolkit.getDefaultToolkit().beep();
            }
            char tecla;
            tecla = evt.getKeyChar();
            if(!Character.isDigit(tecla)){
                evt.consume();
//                getToolkit().beep();            
            } 
            
        }
    }//GEN-LAST:event_txtDOCKeyTyped

    private void btnListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaMouseClicked
        
    }//GEN-LAST:event_btnListaMouseClicked

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed
        tg = 1;
        lblTipoR.setText("C");
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnNuevo.setEnabled(false);
        jPanel7.setVisible(false);
        jPanel6.setVisible(false);
        jPanel8.setVisible(false);
        LIMPIAR();
        HABILITAR(true);
        Paginas.setSelectedIndex(1);
    }//GEN-LAST:event_btnCajaActionPerformed

    private void btnAlertConsulta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta10ActionPerformed
        NivelSuperior.dispose();
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog NivelSuperior;
    private javax.swing.JTabbedPane Paginas;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnBuscarPaciente;
    public static javax.swing.JButton btnCaja;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxMoneda;
    private javax.swing.JComboBox cbxTipoDoc;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCab;
    private javax.swing.JLabel lblDet;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblJuridica;
    private javax.swing.JLabel lblNatural;
    public static javax.swing.JLabel lblNivel;
    public static javax.swing.JLabel lblPermiso;
    private javax.swing.JLabel lblTipoR;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT13;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCPT6;
    private javax.swing.JPanel panelCPT7;
    private javax.swing.JPanel panelCPT9;
    private javax.swing.JTable tb_Clientes;
    public static javax.swing.JTextField txtApeM;
    public static javax.swing.JTextField txtApeP;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtCorreo;
    public static javax.swing.JTextField txtDOC;
    public static javax.swing.JTextField txtDireccion;
    public static javax.swing.JTextField txtNom;
    public static javax.swing.JTextField txtRazonS;
    public static javax.swing.JTextField txtT1;
    public static javax.swing.JTextField txtT2;
    // End of variables declaration//GEN-END:variables
}
