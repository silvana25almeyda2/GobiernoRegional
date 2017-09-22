/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;
import Servicios.Conexion;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.PrinterInfo;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterLocation;
import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterState;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Caja.Caja_PC_Registro;
import Vistas.Principal.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author MYS1
 */
public class Caja_Registro extends javax.swing.JFrame {
DefaultTableModel m;
ResultSet r;
Conexion c=new Conexion();
Connection conexion=c.conectar();
Caja_PC_Registro nuevaV = new Caja_PC_Registro();
    /**
     * Creates new form Caja_Registro
     */
    public Caja_Registro() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);//fondo blanco
        Caja_PC_Registro pc = new Caja_PC_Registro();
        pc.CajaPC_Listar();
        pc.PERFIL_USUARIO(Principal.lblUsu.getText());
        panelMenu.setVisible(false);
        setLocationRelativeTo(null);
        NivelSuperior2.setLocationRelativeTo(null);
//        jPanel73.setVisible(false);
        panelNRO.setVisible(false);
//        panelMenu1.setVisible(false);
        jButton1.setVisible(false);
        cbxImpresoras.setBackground(Color.WHITE);
        
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Mind Map-100.png")).getImage());
        
   
//      setLayout(new FlowLayout());
//  PrintService[] ps = PrintServiceLookup.lookupPrintServices( null , null);
//        for( int i=0 ; i< ps.length; i++)
//        {
//           cbxImpresoras.addItem(ps[i].getName());
//        }
//  add(cbxImpresoras);
  
       
    }
        public static void printAvailable() {
 
        // busca los servicios de impresion...
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
 
        // -- ver los atributos de las impresoras...
        for (PrintService printService : services) {
            String IM;
            IM=(printService.getName());
            JComboBox jComboBox1 = new JComboBox();

            jComboBox1.addItem(IM);
            cbxImpresoras.addItem(IM);
   
 
            PrintServiceAttributeSet printServiceAttributeSet = printService.getAttributes();
 
            System.out.println("--- atributos");
 
            // todos los atributos de la impresora
            Attribute[] a = printServiceAttributeSet.toArray();
            for (Attribute unAtribute : a) {
                System.out.println("atributo: " + unAtribute.getName());
            }
 
            System.out.println("--- viendo valores especificos de los atributos ");
 
            // valor especifico de un determinado atributo de la impresora
            System.out.println("PrinterLocation: " + printServiceAttributeSet.get(PrinterLocation.class));
            System.out.println("PrinterInfo: " + printServiceAttributeSet.get(PrinterInfo.class));
            System.out.println("PrinterState: " + printServiceAttributeSet.get(PrinterState.class));
            System.out.println("Destination: " + printServiceAttributeSet.get(Destination.class));
            System.out.println("PrinterMakeAndModel: " + printServiceAttributeSet.get(PrinterMakeAndModel.class));
            System.out.println("PrinterIsAcceptingJobs: " + printServiceAttributeSet.get(PrinterIsAcceptingJobs.class));
 
        }
 
    }
        
    public DefaultComboBoxModel CargarGrupo(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("SELECT UPPER(descripcion)as 'descripcion' FROM SISTEMA_MODULO"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "descripcion" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "ERROR AL CARGAR :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public void Guardar(){
                Caja_PC_Registro cno1 = new Caja_PC_Registro();
//                cno1.setAR_ID(Integer.parseInt(lblARID.getText()) );//
                cno1.setNOM_USU(lblUsuario.getText());//
                cno1.setPA_MODULO(jLabel2.getText());//
                cno1.setNRO_PC(Integer.parseInt(txtNRO.getText()));//
                    if(cno1.NuevoTerminal()==true){
                        System.out.println("GUARDADO ");
                        lblMensaje.setText("Disfrute de Conecta-M5");
                        lblDes.setText("La configuración del terminal se guardó de forma correcta.");
                        panelMenu.setVisible(true);
//                        jPanel73.setVisible(false);
                    }else{
                        lblMensaje.setText("Algo salió mal");
                        lblDes.setText("<HTML>"+"Verifique que la configuración sea la correcta,"+"<BR>"+"Puede que el Nº de PC este repetido"+"</HTML>");
                        panelMenu.setVisible(false);
//                        jPanel73.setVisible(true);
    }                  
    }
    
    public void GuardarC_F(){
                Caja_PC_Registro cno1 = new Caja_PC_Registro();
//                cno1.setAR_ID(Integer.parseInt(lblARID.getText()) );//
                cno1.setNOM_USU(lblUsuario.getText());//
                cno1.setNRO_PC(Integer.parseInt(txtNRO.getText()));//
                cno1.setIMPRESORA(cbxImpresoras.getSelectedItem().toString());//
                    if(cno1.NuevoTerminalC_F()==true){
                        System.out.println("GUARDADO ");
                        lblMensaje.setText("Disfrute de Conecta-M5");
                        lblDes.setText("La configuración del terminal se guardó de forma correcta.");
                        panelMenu.setVisible(true);
//                        jPanel73.setVisible(false);
                    }else{
                        lblMensaje.setText("Algo salió mal");
                        lblDes.setText("<HTML>"+"Verifique que la configuración sea la correcta,"+"<BR>"+"Puede que el Nº de PC este repetido"+"</HTML>");
                        panelMenu.setVisible(false);
//                        jPanel73.setVisible(true);
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

        NivelSuperior2 = new javax.swing.JDialog();
        jPanel149 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jPanel150 = new javax.swing.JPanel();
        btnAlertConsulta12 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        tbPaneles = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblMOD = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelCPT5 = new javax.swing.JPanel();
        txtPC = new javax.swing.JTextField();
        lblARID = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelNRO = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelCPT4 = new javax.swing.JPanel();
        txtNRO = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelCPT6 = new javax.swing.JPanel();
        txtModulo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        cbxImpresoras = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        lblResumenUsuario = new javax.swing.JLabel();
        lblResumenPC = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        lblDes = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        lblUsu1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAlertConsulta1 = new javax.swing.JButton();
        btnAlertConsulta9 = new javax.swing.JButton();

        NivelSuperior2.setAlwaysOnTop(true);
        NivelSuperior2.setMinimumSize(new java.awt.Dimension(430, 200));
        NivelSuperior2.setUndecorated(true);
        NivelSuperior2.setResizable(false);

        jPanel149.setBackground(new java.awt.Color(230, 230, 230));

        jLabel66.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Error");

        jPanel150.setBackground(new java.awt.Color(23, 160, 134));

        btnAlertConsulta12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAlertConsulta12.setForeground(new java.awt.Color(240, 240, 240));
        btnAlertConsulta12.setText("Entendido");
        btnAlertConsulta12.setContentAreaFilled(false);
        btnAlertConsulta12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlertConsulta12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlertConsulta12.setIconTextGap(30);
        btnAlertConsulta12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlertConsulta12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel150Layout = new javax.swing.GroupLayout(jPanel150);
        jPanel150.setLayout(jPanel150Layout);
        jPanel150Layout.setHorizontalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel150Layout.createSequentialGroup()
                .addComponent(btnAlertConsulta12, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel150Layout.setVerticalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel150Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAlertConsulta12))
        );

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("<html>No puede haber dos terminales con el mismo nombre. <br>\nComunícate con tu administrador para que cambie el nombre del equipo. <br>\nSistema/Configuración avanzada del sistema/Nombre de equipo\n</html>");

        javax.swing.GroupLayout jPanel149Layout = new javax.swing.GroupLayout(jPanel149);
        jPanel149.setLayout(jPanel149Layout);
        jPanel149Layout.setHorizontalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel149Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel149Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel150, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel149Layout.setVerticalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel149Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel66)
                .addGap(18, 18, 18)
                .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel150, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout NivelSuperior2Layout = new javax.swing.GroupLayout(NivelSuperior2.getContentPane());
        NivelSuperior2.getContentPane().setLayout(NivelSuperior2Layout);
        NivelSuperior2Layout.setHorizontalGroup(
            NivelSuperior2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        NivelSuperior2Layout.setVerticalGroup(
            NivelSuperior2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(807, 535));
        setResizable(false);
        getContentPane().setLayout(null);

        tbPaneles.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tbPaneles.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Le damos la bienvenida");

        lblUsu.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(91, 90, 90));
        lblUsu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsu.setText("Numero de Terminal");

        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("jLabel1");

        lblMOD.setForeground(new java.awt.Color(255, 255, 255));
        lblMOD.setText("jLabel8");

        jButton1.setText("jButton1");
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
                    .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblUsuario)
                                .addGap(18, 18, 18)
                                .addComponent(lblMOD)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addComponent(lblUsu)
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(lblUsuario)
                    .addComponent(lblMOD))
                .addGap(56, 56, 56))
        );

        tbPaneles.addTab("tab1", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(102, 102, 102));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Configuración del Terminal");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Nombre de Terminal");

        panelCPT5.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPC.setEditable(false);
        txtPC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPC.setForeground(new java.awt.Color(51, 51, 51));
        txtPC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPC.setBorder(null);
        txtPC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPCCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout panelCPT5Layout = new javax.swing.GroupLayout(panelCPT5);
        panelCPT5.setLayout(panelCPT5Layout);
        panelCPT5Layout.setHorizontalGroup(
            panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtPC, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
        );
        panelCPT5Layout.setVerticalGroup(
            panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblARID.setForeground(new java.awt.Color(255, 255, 255));
        lblARID.setText("jLabel1");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Módulo");

        panelNRO.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Este Nº de terminal ya se encuentra consignado.");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Numero de Terminal");

        panelCPT4.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtNRO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtNRO.setForeground(new java.awt.Color(51, 51, 51));
        txtNRO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNRO.setBorder(null);
        txtNRO.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNROCaretUpdate(evt);
            }
        });
        txtNRO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNROKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
        panelCPT4.setLayout(panelCPT4Layout);
        panelCPT4Layout.setHorizontalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtNRO, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );
        panelCPT4Layout.setVerticalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtNRO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNROLayout = new javax.swing.GroupLayout(panelNRO);
        panelNRO.setLayout(panelNROLayout);
        panelNROLayout.setHorizontalGroup(
            panelNROLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNROLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(23, 23, 23)
                .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelNROLayout.setVerticalGroup(
            panelNROLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNROLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelNROLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5)
                    .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel2");

        panelCPT6.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtModulo.setEditable(false);
        txtModulo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtModulo.setForeground(new java.awt.Color(51, 51, 51));
        txtModulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtModulo.setBorder(null);
        txtModulo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtModuloCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout panelCPT6Layout = new javax.swing.GroupLayout(panelCPT6);
        panelCPT6.setLayout(panelCPT6Layout);
        panelCPT6Layout.setHorizontalGroup(
            panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtModulo, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
        );
        panelCPT6Layout.setVerticalGroup(
            panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addComponent(jLabel2))
                    .addComponent(panelNRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblARID))
                .addContainerGap(155, Short.MAX_VALUE))
            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel58)
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel2))
                    .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelNRO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblARID)
                .addGap(11, 11, 11))
        );

        tbPaneles.addTab("tab1", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel59.setFont(new java.awt.Font("Segoe UI Semilight", 0, 28)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(102, 102, 102));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Configuración de la Impresora");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Tipo de Comprobante");

        btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(51, 51, 51));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Print-32B.png"))); // NOI18N
        btnImprimir.setText("Prueba de Impresión");
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImprimir.setIconTextGap(30);
        btnImprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        cbxImpresoras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxImpresoras.setForeground(new java.awt.Color(51, 51, 51));
        cbxImpresoras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ticket - Papel Termico", "Recibo 25cm x 12cm" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Verifique que la impresora este encendida y este correctamente configurada.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(23, 23, 23)
                                .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel59)
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(25, 25, 25)
                .addComponent(btnImprimir)
                .addGap(100, 100, 100))
        );

        tbPaneles.addTab("tab1", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(102, 102, 102));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Resumen");

        lblResumenUsuario.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblResumenUsuario.setForeground(new java.awt.Color(51, 51, 51));
        lblResumenUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-User-45.png"))); // NOI18N
        lblResumenUsuario.setText("Nombre de Terminal");

        lblResumenPC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblResumenPC.setForeground(new java.awt.Color(51, 51, 51));
        lblResumenPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Workstation-45.png"))); // NOI18N
        lblResumenPC.setText("Nombre de Terminal");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Print-45.png"))); // NOI18N
        jLabel11.setText("Nombre de Terminal");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblResumenUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addComponent(lblResumenPC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(241, Short.MAX_VALUE))
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel60)
                .addGap(18, 18, 18)
                .addComponent(lblResumenUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblResumenPC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        tbPaneles.addTab("tab1", jPanel5);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(102, 102, 102));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("Disfrute de Conecta-M5");

        lblDes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lblDes.setForeground(new java.awt.Color(51, 51, 51));
        lblDes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDes.setText("La configuración del terminal se guardó de forma correcta.");
        lblDes.setToolTipText("");

        panelMenu.setBackground(new java.awt.Color(102, 102, 102));

        lblUsu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsu1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsu1.setText("Empezar");
        lblUsu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUsu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUsu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsu1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsu1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDes, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblMensaje)
                .addGap(36, 36, 36)
                .addComponent(lblDes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        tbPaneles.addTab("tab1", jPanel2);

        getContentPane().add(tbPaneles);
        tbPaneles.setBounds(30, 240, 740, 345);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/a.gif"))); // NOI18N
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 0, 803, 240);

        btnAlertConsulta1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAlertConsulta1.setForeground(new java.awt.Color(255, 255, 255));
        btnAlertConsulta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Adelante-32.png"))); // NOI18N
        btnAlertConsulta1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnAlertConsulta1.setContentAreaFilled(false);
        btnAlertConsulta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlertConsulta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlertConsulta1.setIconTextGap(30);
        btnAlertConsulta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlertConsulta1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlertConsulta1);
        btnAlertConsulta1.setBounds(768, 376, 35, 25);

        btnAlertConsulta9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAlertConsulta9.setForeground(new java.awt.Color(255, 255, 255));
        btnAlertConsulta9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Back-32.png"))); // NOI18N
        btnAlertConsulta9.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnAlertConsulta9.setContentAreaFilled(false);
        btnAlertConsulta9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlertConsulta9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlertConsulta9.setIconTextGap(30);
        btnAlertConsulta9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlertConsulta9ActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlertConsulta9);
        btnAlertConsulta9.setBounds(0, 376, 35, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNROCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNROCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNROCaretUpdate

    private void txtPCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPCCaretUpdate

    }//GEN-LAST:event_txtPCCaretUpdate

    private void btnAlertConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta1ActionPerformed
//        Caja_PC_Registro cn = new Caja_PC_Registro();
//            if((cn.VALIDAR_PC()>0)){
//                NivelSuperior2.setVisible(true);
//            }
        if(tbPaneles.getSelectedIndex()==0){
            btnAlertConsulta1.setEnabled(true);
            if(lblMOD.getText().equals("CC")||lblMOD.getText().equals("CPP")){
            txtModulo.setText("CAJA / FACTURADOR"); 
            }else if(lblMOD.getText().equals("PP")){
                txtModulo.setText("PERSONAL"); 
            }
            jButton1.doClick();

            tbPaneles.setSelectedIndex(1); 
        }else if(tbPaneles.getSelectedIndex()==1){
            btnAlertConsulta1.setEnabled(true);
            Caja_PC_Registro cn = new Caja_PC_Registro();
            if(txtModulo.getText().equals("CAJA / FACTURADOR")){
                if(cn.VerificarNumero(jLabel2.getText(),txtNRO.getText())>0){
                     jLabel1.setForeground(new Color(255,255,255));
                }else{
                    jLabel1.setForeground(new Color(255,51,51));
                    tbPaneles.setSelectedIndex(2);
                }   
                }else if(!txtModulo.getText().equals("CAJA / FACTURADOR")){
                    tbPaneles.setSelectedIndex(2);
                } 
        }else if(tbPaneles.getSelectedIndex()==2){
            btnAlertConsulta1.setEnabled(true);
            lblResumenPC.setText("<HTML>"+"Terminal "+txtPC.getText()+"<br>"+"Nº "+txtNRO.getText());
            tbPaneles.setSelectedIndex(3);
        }else if(tbPaneles.getSelectedIndex()==3){
            btnAlertConsulta1.setEnabled(true);
            if(txtModulo.getText().equals("CAJA / FACTURADOR")){
            GuardarC_F();
            tbPaneles.setSelectedIndex(4);
            }else if(txtModulo.getText().equals("PERSONAL")){
                Guardar();
                tbPaneles.setSelectedIndex(4);
            }    
        }else if(tbPaneles.getSelectedIndex()==4){
            btnAlertConsulta1.setEnabled(false);
        }
    }//GEN-LAST:event_btnAlertConsulta1ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if(cbxImpresoras.getSelectedItem().equals("Ticket - Papel Termico")){
            nuevaV.reportePRUEBA_TICKET("");
        }else if(!cbxImpresoras.getSelectedItem().equals("Ticket - Papel Termico")){
            
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void lblUsu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu1MouseClicked
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(Principal.lblUsu.getText(),Principal.lblCod_Modulo.getText(),Principal.tbPC);
        dispose();
    }//GEN-LAST:event_lblUsu1MouseClicked

    private void txtNROKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNROKeyTyped
        if (txtNRO.getText().length()==1){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();            
        }    
    }//GEN-LAST:event_txtNROKeyTyped

    private void txtModuloCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtModuloCaretUpdate
       
    }//GEN-LAST:event_txtModuloCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(txtModulo.getText().equals("CAJA / FACTURADOR")){
            jLabel2.setText("CC"); 
            panelNRO.setVisible(true);
//            panelMenu1.setVisible(true);
//            lblUsu1.setText("Ir al Modulo de Caja");
//            lblUsu2.setText("Ir al Facturador");
//            panelMenu.setBackground(new Color(23,160,134)); 
//            panelMenu1.setBackground(new Color(41,127,184));
            Caja_PC_Registro U = new Caja_PC_Registro();
            U.NUMERACION();
            txtNRO.requestFocus();
        }else if(txtModulo.getText().equals("CAJA / FACTURADOR")){
            jLabel2.setText("CC"); 
//            panelMenu.setBackground(new Color(23,160,134)); 
//            panelMenu1.setBackground(new Color(41,127,184));
//            panelNRO.setVisible(true);
//            panelMenu1.setVisible(true);
//            lblUsu2.setText("Ir al Facturador");
//            lblUsu1.setText("Ir al Modulo de Caja");
            Caja_PC_Registro U = new Caja_PC_Registro();
            U.NUMERACION();
            txtNRO.requestFocus();
        }else if(txtModulo.getText().equals("PERSONAL")){
            jLabel2.setText("PP"); 
//            panelMenu.setBackground(new Color(122,77,135));
            panelNRO.setVisible(false);
//            panelMenu1.setVisible(true);
//            panelMenu1.setBackground(new Color(209,52,56));
            txtNRO.setText("0");
//            lblUsu1.setText("Ir al Modulo de Personal");
//            lblUsu2.setText("Ir al Modulo de Usuarios");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAlertConsulta9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta9ActionPerformed
        if(tbPaneles.getSelectedIndex()==0){
            btnAlertConsulta9.setEnabled(false);
        }else if(tbPaneles.getSelectedIndex()==1){
            btnAlertConsulta9.setEnabled(true);
            tbPaneles.setSelectedIndex(0);
        }else if(tbPaneles.getSelectedIndex()==2){
            btnAlertConsulta9.setEnabled(true);
            tbPaneles.setSelectedIndex(1);
        }else if(tbPaneles.getSelectedIndex()==3){
            btnAlertConsulta9.setEnabled(true);
            tbPaneles.setSelectedIndex(2);
        }else if(tbPaneles.getSelectedIndex()==4){
            btnAlertConsulta9.setEnabled(true);
            tbPaneles.setSelectedIndex(1);
        }
    }//GEN-LAST:event_btnAlertConsulta9ActionPerformed

    private void btnAlertConsulta12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta12ActionPerformed
        NivelSuperior.dispose();
    }//GEN-LAST:event_btnAlertConsulta12ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog NivelSuperior;
    private javax.swing.JDialog NivelSuperior1;
    private javax.swing.JDialog NivelSuperior2;
    private javax.swing.JButton btnAlertConsulta1;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnAlertConsulta11;
    private javax.swing.JButton btnAlertConsulta12;
    private javax.swing.JButton btnAlertConsulta9;
    public static javax.swing.JButton btnImprimir;
    public static javax.swing.JComboBox cbxImpresoras;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
    private javax.swing.JPanel jPanel150;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblARID;
    private javax.swing.JLabel lblDes;
    public static javax.swing.JLabel lblMOD;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblResumenPC;
    public static javax.swing.JLabel lblResumenUsuario;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblUsu1;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCPT6;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelNRO;
    private javax.swing.JTabbedPane tbPaneles;
    public static javax.swing.JTextField txtModulo;
    public static javax.swing.JTextField txtNRO;
    public static javax.swing.JTextField txtPC;
    // End of variables declaration//GEN-END:variables
}
