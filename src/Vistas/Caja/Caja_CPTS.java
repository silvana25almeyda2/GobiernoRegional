/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Caja.Caja_CPT;
import Servicios.Conexion;
import Vistas.Principal.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import modelo.Caja.Caja_Cta6;
/**
 *
 * @author Ricardo
 */
public class Caja_CPTS extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();
Connection conexion=c.conectar();
ResultSet r;
byte tg;
byte tgm;
    /**
     * Creates new form Caja_CPTs
     */
 Caja_CPT cnn = new Caja_CPT();
    public Caja_CPTS() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
         Cta6.setLocationRelativeTo(null);//en el centro
         Cta6.getContentPane().setBackground(Color.WHITE); 
        this.cbxTipoDocumento.setModel(CargarGrupo()); 

        Caja_Cta6 A = new Caja_Cta6();
        A.LISTA_CTA6("", tb_Grupos2);
        Caja_CPT AL = new Caja_CPT();
        Caja_CPT N = new Caja_CPT();
        AL.LISTA_ITEM("",tb_CPT);
        N.LISTAR_PERMISOS_ITEM(Principal.lblUsu.getText());
        //formatoventanas();
        cargareliminar.setVisible(false);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        b1.setVisible(false);
        txtITEM_COMPLETO.setVisible(false);
        Paginas.setSelectedIndex(1);
         Paginas.setEnabledAt(0,false);
         Paginas.setEnabledAt(1, false);
         btnLista.setVisible(false);
//         setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Mind Map-100.png")).getImage());
    
    }
    
    public DefaultComboBoxModel CargarGrupo(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC CAJA_GRUPO_DESCRIPCION"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "DESCRIPCION" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "ERROR AL CARGAR :" + ex.getMessage() );
        }        
        return listmodel;
    }
   
    public void formatoventanas(){
    tb_Grupos2.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Grupos2.getColumnModel().getColumn(1).setPreferredWidth(250);
    tb_Grupos2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupos2.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupos2.setRowHeight(45);
    
    }
    
    public void NUEVO_REGISTRO(){
       if((txtITEM.getText().equals(""))){
            cargareliminar.setVisible(true);        
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Debe completar los campos requeridos");
            eli.setVisible(true);
            noeli.setVisible(false);
            tgm=0;                 
        } else {
                Caja_CPT cno1 = new Caja_CPT();
                cno1.setID_GRUPO(Integer.parseInt(lblIDGRUPO.getText()));
                cno1.setID_Cuenta7(Integer.parseInt(lblIDCTA6.getText()));
                cno1.setNRO_ITEM(txtnomenclatura.getText()+txtITEM.getText());
                cno1.setNOMBRE(txtDESCRIPCION.getText());
                cno1.setUSUARIO(lblusu.getText());
                cno1.setID_CPT(0);
                    if(cno1.NUEVO_ITEM()==true){
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
                            Caja_CPT A = new Caja_CPT();
                            A.LISTA_ITEM("",tb_CPT);
                            jLabel33.setText("Listado");
                            Paginas.setSelectedIndex(1);
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
                Caja_CPT cno1 = new Caja_CPT();
                cno1.setID_CPT(Integer.parseInt(lblID_CPT.getText()));
                cno1.setID_GRUPO(Integer.parseInt(lblIDGRUPO.getText()));
                cno1.setID_Cuenta7(Integer.parseInt(lblIDCTA6.getText()));
                cno1.setNRO_ITEM(txtITEM_COMPLETO.getText());
                cno1.setNOMBRE(txtDESCRIPCION.getText());
                cno1.setUSUARIO(lblusu.getText());
                    if(cno1.MODIFICAR_ITEM()==true){
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
                        Caja_CPT A = new Caja_CPT();
                        A.LISTA_ITEM("",tb_CPT);
                        jLabel33.setText("Listado");
                        Paginas.setSelectedIndex(1);
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                    }               
    }
    public void ELIMINAR_CPT(){ 
        try{
                Caja_CPT hCEl = new Caja_CPT();
                hCEl.setID_CPT(Integer.parseInt(lblID_CPT.getText()));
                if(hCEl.ELIMINAR_ITEM()){
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setText("OK");
                    eli.setVisible(true);
                    noeli.setVisible(false);
                    tgm=9;
                    Caja_CPT A = new Caja_CPT();
                    A.LISTA_ITEM("",tb_CPT);
                    jLabel33.setText("Listado");
                    Paginas.setSelectedIndex(1);
                    
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

        Cta6 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        txtBuscar2 = new javax.swing.JTextField();
        btnBuscarPaciente2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_Grupos2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            NivelSuperior = new javax.swing.JDialog();
            jPanel145 = new javax.swing.JPanel();
            jLabel64 = new javax.swing.JLabel();
            jPanel146 = new javax.swing.JPanel();
            btnAlertConsulta10 = new javax.swing.JButton();
            jLabel67 = new javax.swing.JLabel();
            jPanel1 = new javax.swing.JPanel();
            btnNuevo = new javax.swing.JButton();
            btneditar = new javax.swing.JButton();
            btnguardar = new javax.swing.JButton();
            btneliminar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            jLabel57 = new javax.swing.JLabel();
            jPanel23 = new javax.swing.JPanel();
            buscartodo = new javax.swing.JTextField();
            btnBuscarPaciente = new javax.swing.JButton();
            lbldetalle = new javax.swing.JLabel();
            btnLista = new javax.swing.JButton();
            lblNivel = new javax.swing.JLabel();
            lblPermiso = new javax.swing.JLabel();
            Paginas = new javax.swing.JTabbedPane();
            jPanel4 = new javax.swing.JPanel();
            txtnomenclatura = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();
            jLabel12 = new javax.swing.JLabel();
            txtcod = new javax.swing.JTextField();
            lblIDGRUPO = new javax.swing.JLabel();
            lblIDCTA6 = new javax.swing.JLabel();
            unior = new javax.swing.JLabel();
            txtITEM = new javax.swing.JTextField();
            nm = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            txtDESCRIPCION = new javax.swing.JEditorPane();
            txtITEM_COMPLETO = new javax.swing.JTextField();
            panelCPT1 = new javax.swing.JPanel();
            txtct6 = new javax.swing.JTextField();
            b1 = new javax.swing.JButton();
            cbxTipoDocumento = new javax.swing.JComboBox();
            lblID_CPT = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_CPT = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                cargareliminar = new javax.swing.JPanel();
                Mensaje = new javax.swing.JLabel();
                eli = new javax.swing.JButton();
                noeli = new javax.swing.JButton();
                jPanel5 = new javax.swing.JPanel();
                jLabel33 = new javax.swing.JLabel();

                Cta6.setAlwaysOnTop(true);
                Cta6.setMinimumSize(new java.awt.Dimension(700, 422));
                Cta6.setResizable(false);

                jPanel8.setBackground(new java.awt.Color(230, 230, 230));

                jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(102, 102, 102));
                jLabel4.setText("Cuenta Contable");

                jPanel30.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscar2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscar2.setBorder(null);
                txtBuscar2.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscar2CaretUpdate(evt);
                    }
                });
                txtBuscar2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscar2ActionPerformed(evt);
                    }
                });
                txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscar2KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarPaciente2.setContentAreaFilled(false);
                btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPaciente2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(414, Short.MAX_VALUE))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(410, 410, 410))
                );

                jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_Grupos2.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Grupos2.setGridColor(new java.awt.Color(255, 255, 255));
                tb_Grupos2.setRowHeight(25);
                tb_Grupos2.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_Grupos2.getTableHeader().setReorderingAllowed(false);
                tb_Grupos2.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Grupos2MouseClicked(evt);
                    }
                });
                tb_Grupos2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Grupos2KeyPressed(evt);
                    }
                });
                jScrollPane5.setViewportView(tb_Grupos2);

                javax.swing.GroupLayout Cta6Layout = new javax.swing.GroupLayout(Cta6.getContentPane());
                Cta6.getContentPane().setLayout(Cta6Layout);
                Cta6Layout.setHorizontalGroup(
                    Cta6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                );
                Cta6Layout.setVerticalGroup(
                    Cta6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cta6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                );

                NivelSuperior.setAlwaysOnTop(true);
                NivelSuperior.setMinimumSize(new java.awt.Dimension(430, 200));
                NivelSuperior.setUndecorated(true);
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
                jLabel67.setText("<html>Necesita permisos de administrador <BR>Para realizar esta acción.</html>");

                javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
                jPanel145.setLayout(jPanel145Layout);
                jPanel145Layout.setHorizontalGroup(
                    jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(171, Short.MAX_VALUE))
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
                setMinimumSize(new java.awt.Dimension(1054, 550));

                jPanel1.setBackground(new java.awt.Color(39, 174, 96));
                jPanel1.setPreferredSize(new java.awt.Dimension(292, 437));

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

                jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                jLabel57.setText("<html>Ítems<span style=\"font-size:'14px'\"><br>CPT</br></span></html>");

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
                        .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                lbldetalle.setText("CPT, Descripción");

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
                btnLista.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnListaActionPerformed(evt);
                    }
                });

                lblNivel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lblNivel.setForeground(new java.awt.Color(39, 174, 96));
                lblNivel.setText("jLabel2");

                lblPermiso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lblPermiso.setForeground(new java.awt.Color(39, 174, 96));
                lblPermiso.setText("jLabel2");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lbldetalle)))
                                .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNivel)
                                .addGap(18, 18, 18)
                                .addComponent(lblPermiso)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(lbldetalle)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNivel)
                            .addComponent(lblPermiso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                Paginas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                Paginas.setForeground(new java.awt.Color(255, 255, 255));
                Paginas.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                Paginas.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                Paginas.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                txtnomenclatura.setEditable(false);
                txtnomenclatura.setBackground(new java.awt.Color(255, 255, 255));
                txtnomenclatura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtnomenclatura.setForeground(new java.awt.Color(51, 51, 51));
                txtnomenclatura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtnomenclatura.setEnabled(false);

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setText("Grupo");

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel9.setText("Cuenta contable");

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel11.setText("CPT");

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel12.setText("Descripción");

                txtcod.setEditable(false);
                txtcod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtcod.setForeground(new java.awt.Color(255, 255, 255));
                txtcod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                txtcod.setSelectionColor(new java.awt.Color(255, 255, 255));

                lblIDGRUPO.setForeground(new java.awt.Color(255, 255, 255));
                lblIDGRUPO.setText("jLabel3");

                lblIDCTA6.setForeground(new java.awt.Color(255, 255, 255));
                lblIDCTA6.setText("jLabel4");

                unior.setForeground(new java.awt.Color(255, 255, 255));
                unior.setText("jLabel5");

                txtITEM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtITEM.setForeground(new java.awt.Color(51, 51, 51));
                txtITEM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(109, 109, 109)));
                txtITEM.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtITEMKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtITEMKeyReleased(evt);
                    }
                });

                nm.setForeground(new java.awt.Color(255, 255, 255));
                nm.setText("jLabel5");

                txtDESCRIPCION.setForeground(new java.awt.Color(51, 51, 51));
                txtDESCRIPCION.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtDESCRIPCIONKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDESCRIPCIONKeyTyped(evt);
                    }
                });
                jScrollPane1.setViewportView(txtDESCRIPCION);

                txtITEM_COMPLETO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtITEM_COMPLETO.setForeground(new java.awt.Color(51, 51, 51));
                txtITEM_COMPLETO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(109, 109, 109)));
                txtITEM_COMPLETO.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtITEM_COMPLETOKeyPressed(evt);
                    }
                });

                panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtct6.setEditable(false);
                txtct6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtct6.setForeground(new java.awt.Color(51, 51, 51));
                txtct6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtct6.setBorder(null);
                txtct6.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtct6CaretUpdate(evt);
                    }
                });

                b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                b1.setContentAreaFilled(false);
                b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                b1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        b1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                panelCPT1.setLayout(panelCPT1Layout);
                panelCPT1Layout.setHorizontalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtct6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                panelCPT1Layout.setVerticalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtct6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cbxTipoDocumento.setForeground(new java.awt.Color(51, 51, 51));
                cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BOLETA", "FACTURA" }));
                cbxTipoDocumento.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cbxTipoDocumentoMouseClicked(evt);
                    }
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        cbxTipoDocumentoMouseReleased(evt);
                    }
                });
                cbxTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        cbxTipoDocumentoItemStateChanged(evt);
                    }
                });
                cbxTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoDocumentoActionPerformed(evt);
                    }
                });
                cbxTipoDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        cbxTipoDocumentoKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        cbxTipoDocumentoKeyTyped(evt);
                    }
                });

                lblID_CPT.setForeground(new java.awt.Color(255, 255, 255));
                lblID_CPT.setText("jLabel1");

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(0, 50, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12)
                                .addGap(144, 144, 144)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(panelCPT1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.Alignment.LEADING, 0, 327, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtnomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtITEM, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtITEM_COMPLETO, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(396, 396, 396)
                                .addComponent(nm)
                                .addGap(18, 18, 18)
                                .addComponent(lblIDCTA6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(unior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIDGRUPO)
                                .addGap(51, 51, 51)
                                .addComponent(lblID_CPT)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtnomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtITEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtITEM_COMPLETO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblID_CPT))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(129, 129, 129)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblIDCTA6)
                                    .addComponent(nm)
                                    .addComponent(unior)
                                    .addComponent(lblIDGRUPO))))
                        .addContainerGap(127, Short.MAX_VALUE))
                );

                Paginas.addTab("...", jPanel4);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_CPT.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tb_CPT.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_CPT.setGridColor(new java.awt.Color(255, 255, 255));
                tb_CPT.setRowHeight(25);
                tb_CPT.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_CPT.getTableHeader().setReorderingAllowed(false);
                tb_CPT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_CPTMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tb_CPTMousePressed(evt);
                    }
                });
                tb_CPT.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_CPTKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tb_CPT);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                );

                Paginas.addTab("...", jPanel2);

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

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Paginas)))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Paginas, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
      //  txtcod.setText(cnn.idNomen());
      
        tg=1;
         txtITEM.setEditable(false);
         txtct6.setEditable(false);
         txtDESCRIPCION.setEditable(true);
         
         txtITEM.setEditable(true);
         txtnomenclatura.setVisible(true);
         txtITEM.setVisible(true);
         txtITEM_COMPLETO.setVisible(false);
         txtITEM.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
         txtDESCRIPCION.setEditable(true);
    
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         btneliminar.setEnabled(false);

         txtnomenclatura.setText("");
         txtDESCRIPCION.setText("");
         txtITEM.setText("");
         txtct6.setText("");
         b1.setVisible(true);
        
        Paginas.setSelectedIndex(0);
        jLabel33.setText("Edición"); 
        Caja_CPT cno2 = new Caja_CPT();
        cno2.DATOS_GRUPO_CPT(cbxTipoDocumento.getSelectedItem().toString());
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed

        if(lblPermiso.getText().equals("E")){
            tg=2;
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            Paginas.setSelectedIndex(0);
            txtnomenclatura.setEditable(true);
            txtDESCRIPCION.setEditable(true);
            txtITEM_COMPLETO.setEditable(true);
            txtDESCRIPCION.setEditable(true);
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            b1.setVisible(true);
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
            
        }    
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       if(tg==1){
            Caja_CPT cn = new Caja_CPT();
            Caja_CPT cn1 = new Caja_CPT();
            if((cn.VALIDAR_TUPA(txtITEM.getText())>0)||(cn1.VALIDAR_TUPA_DES(txtDESCRIPCION.getText())>0)){
                cargareliminar.setVisible(true);
                 cargareliminar.setBackground(new Color(255,91,70)); 
                 Mensaje.setText("El Ítem ingresado ya existe, Verifique ");
                 eli.setVisible(false);
                 noeli.setVisible(false);
                 txtITEM.setText("");
                 txtITEM.requestFocus();
                
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

    private void tb_CPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTMouseClicked
    int fila=tb_CPT.getSelectedRow();
     if(evt.getClickCount()==1){

       lblID_CPT.setText(String.valueOf(tb_CPT.getValueAt(fila, 0))); 
       cbxTipoDocumento.setSelectedItem(String.valueOf(tb_CPT.getValueAt(fila, 1))); 
       txtct6.setText(String.valueOf(tb_CPT.getValueAt(fila, 2)));
       txtDESCRIPCION.setText(String.valueOf(tb_CPT.getValueAt(fila, 10)));
       lblIDGRUPO.setText(String.valueOf(tb_CPT.getValueAt(fila, 4)));
       lblIDCTA6.setText(String.valueOf(tb_CPT.getValueAt(fila, 5)));  
       txtITEM_COMPLETO.setText(String.valueOf(tb_CPT.getValueAt(fila, 9))); 

   }
     if(evt.getClickCount()==2){
      jLabel33.setText("Edición");
      btnLista.setVisible(true);
      Paginas.setSelectedIndex(0);

      }
//      try {
//            String cuenta5=cpt.getText();
//            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1));
//            txtnomenclatura.setText(palabra5);
//            txtnomenclatura.setEnabled(true);
//            
//            String cj=CPTE.getText();
//            String cja=String.valueOf(cj.charAt(2)+""+cj.charAt(3)+""+cj.charAt(4)+""+cj.charAt(5)+""+cj.charAt(6)+""+cj.charAt(7)+""+cj.charAt(8)+""+cj.charAt(9));
//            txtnom1.setText(cja);
//            txtnom1.setEnabled(true);
//            
//        } catch (Exception e) {
//        }
        tg=2;
         txtnomenclatura.setEditable(true);
         txtDESCRIPCION.setEditable(true);
     
         btnguardar.setEnabled(false);
         btneditar.setEnabled(true);
         btneliminar.setEnabled(true);
         b1.setEnabled(true);
         b1.setVisible(false);
          txtITEM.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
         txtnomenclatura.setVisible(false);
         txtITEM.setVisible(false);
         txtITEM_COMPLETO.setVisible(true);
         txtcod.setEditable(true);
         txtnomenclatura.setEditable(true);
         txtct6.setEditable(true);
         txtDESCRIPCION.setEditable(true);
         txtcod.setEditable(false);
         txtnomenclatura.setEditable(false);
         txtct6.setEditable(false);
         txtITEM_COMPLETO.setEditable(false);
         txtDESCRIPCION.setEditable(false);
         

    }//GEN-LAST:event_tb_CPTMouseClicked

    private void tb_CPTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTMousePressed

    }//GEN-LAST:event_tb_CPTMousePressed

    private void tb_CPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTKeyPressed

          char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_CPT.getSelectedRow();

       txtcod.setText(String.valueOf(tb_CPT.getValueAt(fila, 0)));  
       txtct6.setText(String.valueOf(tb_CPT.getValueAt(fila, 5)));
       txtDESCRIPCION.setText(String.valueOf(tb_CPT.getValueAt(fila, 2)));
       
       lblIDGRUPO.setText(String.valueOf(tb_CPT.getValueAt(fila, 9)));
       lblIDCTA6.setText(String.valueOf(tb_CPT.getValueAt(fila, 11)));  
       unior.setText(String.valueOf(tb_CPT.getValueAt(fila, 10))); 
       txtITEM_COMPLETO.setText(String.valueOf(tb_CPT.getValueAt(fila, 1))); 
//       visible.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
       jLabel33.setText("Edición");
      
   }
   
//      try {
//            String cuenta5=cpt.getText();
//            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1));
//            txtnomenclatura.setText(palabra5);
//            txtnomenclatura.setEnabled(true);
//            
//            String cj=CPTE.getText();
//            String cja=String.valueOf(cj.charAt(2)+""+cj.charAt(3)+""+cj.charAt(4)+""+cj.charAt(5)+""+cj.charAt(6)+""+cj.charAt(7)+""+cj.charAt(8)+""+cj.charAt(9));
//            txtnom1.setText(cja);
//            txtnom1.setEnabled(true);
//            
//        } catch (Exception e) {
//        }
        tg=2;
         txtnomenclatura.setEditable(true);
         txtDESCRIPCION.setEditable(true);
     
         btnguardar.setEnabled(false);
         btneditar.setEnabled(true);
         btneliminar.setEnabled(true);
         b1.setEnabled(true);
         b1.setVisible(false);
          txtITEM.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
         txtnomenclatura.setVisible(false);
         txtITEM.setVisible(false);
         txtITEM_COMPLETO.setVisible(true);
         txtcod.setEditable(true);
         txtnomenclatura.setEditable(true);
         txtct6.setEditable(true);
         txtDESCRIPCION.setEditable(true);
         txtcod.setEditable(false);
         txtnomenclatura.setEditable(false);
         txtct6.setEditable(false);
         txtITEM_COMPLETO.setEditable(false);
         txtDESCRIPCION.setEditable(false);
         btnLista.setVisible(true);

       

    }//GEN-LAST:event_tb_CPTKeyPressed

    private void txtITEMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtITEMKeyPressed
          char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtDESCRIPCION.requestFocus(); 
        }
    }//GEN-LAST:event_txtITEMKeyPressed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tgm==3){
            if(lblPermiso.getText().equals("E")){
                MODIFICAR_REGISTRO();
            }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
            
        }    
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
        } else
        if (tgm==8){
            if(lblPermiso.getText().equals("E")){
                ELIMINAR_CPT();
            }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }
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

    private void txtITEM_COMPLETOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtITEM_COMPLETOKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtITEM_COMPLETOKeyPressed

    private void txtDESCRIPCIONKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDESCRIPCIONKeyReleased
        txtDESCRIPCION.setText(txtDESCRIPCION.getText().toUpperCase());
         
    }//GEN-LAST:event_txtDESCRIPCIONKeyReleased

    private void txtDESCRIPCIONKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDESCRIPCIONKeyTyped
             
    }//GEN-LAST:event_txtDESCRIPCIONKeyTyped

    private void txtITEMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtITEMKeyReleased
        txtITEM.setText(txtITEM.getText().toUpperCase());
    }//GEN-LAST:event_txtITEMKeyReleased

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        jLabel33.setText("Listado"); 
        Paginas.setSelectedIndex(1);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
        ///////////////
         txtITEM.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
         cargareliminar.setVisible(false);
         txtITEM.setEditable(false);
         txtct6.setEditable(false);
         txtDESCRIPCION.setEditable(true);
         
         txtITEM.setEditable(true);
         txtnomenclatura.setVisible(true);
         txtITEM.setVisible(true);
         txtITEM_COMPLETO.setVisible(false);
        
        
         txtDESCRIPCION.setEditable(true);
    
         btneditar.setEnabled(false);

         txtnomenclatura.setText("");
         txtDESCRIPCION.setText("");
         txtITEM.setText("");
         txtct6.setText("");
         btnLista.setVisible(false);
        Caja_CPT A = new Caja_CPT();
        A.LISTA_ITEM(buscartodo.getText(),tb_CPT);
        Paginas.setSelectedIndex(0);
        jLabel33.setText("Resultados de la Busqueda");
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void txtct6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtct6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtct6CaretUpdate

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        jLabel33.setText("Listado");
        buscartodo.setText("");
        Caja_CPT A = new Caja_CPT();
        A.LISTA_ITEM("",tb_CPT);
        Paginas.setSelectedIndex(0);

    }//GEN-LAST:event_btnListaActionPerformed

    private void cbxTipoDocumentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoMouseClicked

    }//GEN-LAST:event_cbxTipoDocumentoMouseClicked

    private void cbxTipoDocumentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoMouseReleased

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        Caja_CPT cno2 = new Caja_CPT();
        cno2.DATOS_GRUPO_CPT(cbxTipoDocumento.getSelectedItem().toString());
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
 
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void cbxTipoDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyPressed

       
    }//GEN-LAST:event_cbxTipoDocumentoKeyPressed

    private void cbxTipoDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoKeyTyped

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        Cta6.setVisible(true);
    }//GEN-LAST:event_b1ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        Caja_Cta6 A = new Caja_Cta6();
        A.LISTA_CTA6(txtBuscar2.getText(), tb_Grupos2);
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_Grupos2.getSelectionModel().setSelectionInterval (0,0) ;
            tb_Grupos2.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void tb_Grupos2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupos2MouseClicked
        int fila=tb_Grupos2.getSelectedRow();
        if(evt.getClickCount()==2){
            Cta6.dispose();
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"\t"+(tb_Grupos2.getValueAt(fila, 1)));
            lblIDCTA6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
//            txtGrupoDes.setRequestFocusEnabled(true);

        }
    }//GEN-LAST:event_tb_Grupos2MouseClicked

    private void tb_Grupos2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos2KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos2.getSelectedRow();
            Cta6.dispose();
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"\t"+(tb_Grupos2.getValueAt(fila, 1)));
            lblIDCTA6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
//            txtGrupoDes.setRequestFocusEnabled(true);
        }
    }//GEN-LAST:event_tb_Grupos2KeyPressed

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
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_CPTS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Cta6;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog NivelSuperior;
    private javax.swing.JTabbedPane Paginas;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxTipoDocumento;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblIDCTA6;
    public static javax.swing.JLabel lblIDGRUPO;
    private javax.swing.JLabel lblID_CPT;
    public static javax.swing.JLabel lblNivel;
    public static javax.swing.JLabel lblPermiso;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel nm;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JTable tb_CPT;
    private javax.swing.JTable tb_Grupos2;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JEditorPane txtDESCRIPCION;
    private javax.swing.JTextField txtITEM;
    private javax.swing.JTextField txtITEM_COMPLETO;
    private javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtct6;
    public static javax.swing.JTextField txtnomenclatura;
    private javax.swing.JLabel unior;
    // End of variables declaration//GEN-END:variables
}
