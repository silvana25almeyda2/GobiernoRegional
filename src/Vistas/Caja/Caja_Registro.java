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
    /**
     * Creates new form Caja_Registro
     */
    public Caja_Registro() {
        initComponents();
        Caja_PC_Registro pc = new Caja_PC_Registro();
        pc.CajaPC_Listar();
        pc.PERFIL_USUARIO(Principal.lblUsu.getText());
        this.getContentPane().setBackground(new Color(41,127,184)); 
        panelMenu.setVisible(false);
        setLocationRelativeTo(null);
        Unidad.setLocationRelativeTo(null);
        jPanel73.setVisible(false);
        panelNRO.setVisible(false);
        panelMenu1.setVisible(false);
        jButton1.setVisible(false);
        
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        
   
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
                        lblMensaje.setText("Todo Correcto");
                        lblDes.setText("La configuración del terminal se guardó de forma correcta.");
                        panelMenu.setVisible(true);
                        jPanel73.setVisible(false);
                    }else{
                        lblMensaje.setText("Algo salió mal");
                        lblDes.setText("<HTML>"+"Verifique que la configuración sea la correcta,"+"<BR>"+"Puede que el Nº de PC este repetido"+"</HTML>");
                        panelMenu.setVisible(false);
                        jPanel73.setVisible(true);
    }                  
    }
    
    public void GuardarC_F(){
                Caja_PC_Registro cno1 = new Caja_PC_Registro();
//                cno1.setAR_ID(Integer.parseInt(lblARID.getText()) );//
                cno1.setNOM_USU(lblUsuario.getText());//
                cno1.setNRO_PC(Integer.parseInt(txtNRO.getText()));//
                    if(cno1.NuevoTerminalC_F()==true){
                        System.out.println("GUARDADO ");
                        lblMensaje.setText("Todo Correcto");
                        lblDes.setText("La configuración del terminal se guardó de forma correcta.");
                        panelMenu.setVisible(true);
                        jPanel73.setVisible(false);
                    }else{
                        lblMensaje.setText("Algo salió mal");
                        lblDes.setText("<HTML>"+"Verifique que la configuración sea la correcta,"+"<BR>"+"Puede que el Nº de PC este repetido"+"</HTML>");
                        panelMenu.setVisible(false);
                        jPanel73.setVisible(true);
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

        Unidad = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtBuscar1 = new javax.swing.JTextField();
        btnBuscarPaciente4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Grupos1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jTabbedPane1 = new javax.swing.JTabbedPane();
            jPanel1 = new javax.swing.JPanel();
            jLabel57 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            jPanel66 = new javax.swing.JPanel();
            btnAlertConsulta1 = new javax.swing.JButton();
            lblUsuario = new javax.swing.JLabel();
            lblMOD = new javax.swing.JLabel();
            jButton1 = new javax.swing.JButton();
            jPanel3 = new javax.swing.JPanel();
            jLabel58 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            panelCPT5 = new javax.swing.JPanel();
            txtPC = new javax.swing.JTextField();
            jPanel67 = new javax.swing.JPanel();
            btnAlertConsulta2 = new javax.swing.JButton();
            jPanel68 = new javax.swing.JPanel();
            btnAlertConsulta3 = new javax.swing.JButton();
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
            jPanel69 = new javax.swing.JPanel();
            btnAlertConsulta4 = new javax.swing.JButton();
            jPanel70 = new javax.swing.JPanel();
            btnAlertConsulta5 = new javax.swing.JButton();
            btnImprimir = new javax.swing.JButton();
            cbxImpresoras = new javax.swing.JComboBox();
            jPanel5 = new javax.swing.JPanel();
            jLabel60 = new javax.swing.JLabel();
            jPanel71 = new javax.swing.JPanel();
            btnAlertConsulta6 = new javax.swing.JButton();
            jPanel72 = new javax.swing.JPanel();
            btnAlertConsulta7 = new javax.swing.JButton();
            lblResumenUsuario = new javax.swing.JLabel();
            lblResumenPC = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            lblMensaje = new javax.swing.JLabel();
            lblDes = new javax.swing.JLabel();
            panelMenu = new javax.swing.JPanel();
            lblUsu1 = new javax.swing.JLabel();
            jPanel73 = new javax.swing.JPanel();
            btnAlertConsulta8 = new javax.swing.JButton();
            panelMenu1 = new javax.swing.JPanel();
            lblUsu2 = new javax.swing.JLabel();

            Unidad.setAlwaysOnTop(true);
            Unidad.setMinimumSize(new java.awt.Dimension(591, 419));
            Unidad.setResizable(false);

            jPanel7.setBackground(new java.awt.Color(41, 127, 184));

            jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setText("Área");

            jPanel29.setBackground(new java.awt.Color(255, 255, 255));

            txtBuscar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtBuscar1.setBorder(null);
            txtBuscar1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscar1CaretUpdate(evt);
                }
            });
            txtBuscar1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscar1ActionPerformed(evt);
                }
            });
            txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscar1KeyPressed(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 5, Short.MAX_VALUE))
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            btnBuscarPaciente4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
            btnBuscarPaciente4.setContentAreaFilled(false);
            btnBuscarPaciente4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarPaciente4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarPaciente4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(305, Short.MAX_VALUE))
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarPaciente4))
                    .addGap(412, 412, 412))
            );

            jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tb_Grupos1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {},
                    {},
                    {},
                    {}
                },
                new String [] {

                }
            ));
            tb_Grupos1.setGridColor(new java.awt.Color(255, 255, 255));
            tb_Grupos1.setRowHeight(25);
            tb_Grupos1.setSelectionBackground(new java.awt.Color(102, 102, 102));
            tb_Grupos1.getTableHeader().setReorderingAllowed(false);
            tb_Grupos1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_Grupos1MouseClicked(evt);
                }
            });
            tb_Grupos1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_Grupos1KeyPressed(evt);
                }
            });
            jScrollPane4.setViewportView(tb_Grupos1);

            javax.swing.GroupLayout UnidadLayout = new javax.swing.GroupLayout(Unidad.getContentPane());
            Unidad.getContentPane().setLayout(UnidadLayout);
            UnidadLayout.setHorizontalGroup(
                UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
            );
            UnidadLayout.setVerticalGroup(
                UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(UnidadLayout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setMinimumSize(new java.awt.Dimension(820, 525));
            setResizable(false);
            getContentPane().setLayout(null);

            jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
            jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

            jPanel1.setBackground(new java.awt.Color(230, 230, 230));

            jLabel57.setFont(new java.awt.Font("Segoe UI Semilight", 0, 28)); // NOI18N
            jLabel57.setForeground(new java.awt.Color(102, 102, 102));
            jLabel57.setText("Hola, Bienvenido a la configuración de la estación de trabajo");

            lblUsu.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
            lblUsu.setForeground(new java.awt.Color(91, 90, 90));
            lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-User-64N.png"))); // NOI18N
            lblUsu.setText("Numero de Terminal");

            jPanel66.setBackground(new java.awt.Color(102, 102, 102));

            btnAlertConsulta1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta1.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta1.setText("Siguiente");
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

            javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
            jPanel66.setLayout(jPanel66Layout);
            jPanel66Layout.setHorizontalGroup(
                jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel66Layout.setVerticalGroup(
                jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            lblUsuario.setForeground(new java.awt.Color(230, 230, 230));
            lblUsuario.setText("jLabel1");

            lblMOD.setForeground(new java.awt.Color(230, 230, 230));
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
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(13, 13, 13))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(183, 183, 183))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(lblUsuario)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1)
                                .addComponent(lblMOD))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(lblMOD)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addGap(20, 20, 20)
                    .addComponent(lblUsu)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                    .addComponent(lblUsuario)
                    .addGap(85, 85, 85)
                    .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14))
            );

            jTabbedPane1.addTab("tab1", jPanel1);

            jPanel3.setBackground(new java.awt.Color(230, 230, 230));

            jLabel58.setFont(new java.awt.Font("Segoe UI Semilight", 0, 28)); // NOI18N
            jLabel58.setForeground(new java.awt.Color(102, 102, 102));
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

            jPanel67.setBackground(new java.awt.Color(51, 51, 51));

            btnAlertConsulta2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta2.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta2.setText("Siguiente");
            btnAlertConsulta2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta2.setContentAreaFilled(false);
            btnAlertConsulta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta2.setIconTextGap(30);
            btnAlertConsulta2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
            jPanel67.setLayout(jPanel67Layout);
            jPanel67Layout.setHorizontalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel67Layout.setVerticalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel68.setBackground(new java.awt.Color(153, 153, 153));

            btnAlertConsulta3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta3.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta3.setText("Anterior");
            btnAlertConsulta3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta3.setContentAreaFilled(false);
            btnAlertConsulta3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta3.setIconTextGap(30);
            btnAlertConsulta3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
            jPanel68.setLayout(jPanel68Layout);
            jPanel68Layout.setHorizontalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel68Layout.setVerticalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            lblARID.setForeground(new java.awt.Color(230, 230, 230));
            lblARID.setText("jLabel1");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(51, 51, 51));
            jLabel6.setText("Módulo");

            panelNRO.setBackground(new java.awt.Color(230, 230, 230));

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(230, 230, 230));
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
                    .addGroup(panelNROLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addContainerGap())
            );

            jLabel2.setForeground(new java.awt.Color(230, 230, 230));
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
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel6))
                                            .addGap(23, 23, 23)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblARID)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGap(40, 40, 40)
                                                    .addComponent(jLabel2))))
                                        .addComponent(panelNRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 219, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(panelNRO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(39, 39, 39)
                    .addComponent(lblARID)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14))
            );

            jTabbedPane1.addTab("tab1", jPanel3);

            jPanel4.setBackground(new java.awt.Color(230, 230, 230));

            jLabel59.setFont(new java.awt.Font("Segoe UI Semilight", 0, 28)); // NOI18N
            jLabel59.setForeground(new java.awt.Color(102, 102, 102));
            jLabel59.setText("Configuración de la Impresora");

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(51, 51, 51));
            jLabel7.setText("Nombre de la impresora");

            jPanel69.setBackground(new java.awt.Color(51, 51, 51));

            btnAlertConsulta4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta4.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta4.setText("Siguiente");
            btnAlertConsulta4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta4.setContentAreaFilled(false);
            btnAlertConsulta4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta4.setIconTextGap(30);
            btnAlertConsulta4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
            jPanel69.setLayout(jPanel69Layout);
            jPanel69Layout.setHorizontalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta4, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel69Layout.setVerticalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel70.setBackground(new java.awt.Color(153, 153, 153));

            btnAlertConsulta5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta5.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta5.setText("Anterior");
            btnAlertConsulta5.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta5.setContentAreaFilled(false);
            btnAlertConsulta5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta5.setIconTextGap(30);
            btnAlertConsulta5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
            jPanel70.setLayout(jPanel70Layout);
            jPanel70Layout.setHorizontalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta5, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel70Layout.setVerticalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnImprimir.setForeground(new java.awt.Color(51, 51, 51));
            btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Print-32.png"))); // NOI18N
            btnImprimir.setText("Prueba de Impresión de Ticket");
            btnImprimir.setContentAreaFilled(false);
            btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnImprimir.setEnabled(false);
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
            cbxImpresoras.setEnabled(false);

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(23, 23, 23)
                                    .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addComponent(btnImprimir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                            .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(14, 14, 14))
            );

            jTabbedPane1.addTab("tab1", jPanel4);

            jPanel5.setBackground(new java.awt.Color(230, 230, 230));

            jLabel60.setFont(new java.awt.Font("Segoe UI Semilight", 0, 28)); // NOI18N
            jLabel60.setForeground(new java.awt.Color(102, 102, 102));
            jLabel60.setText("Resumen");

            jPanel71.setBackground(new java.awt.Color(51, 51, 51));

            btnAlertConsulta6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta6.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta6.setText("Terminar");
            btnAlertConsulta6.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta6.setContentAreaFilled(false);
            btnAlertConsulta6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta6.setIconTextGap(30);
            btnAlertConsulta6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
            jPanel71.setLayout(jPanel71Layout);
            jPanel71Layout.setHorizontalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel71Layout.setVerticalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel71Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel72.setBackground(new java.awt.Color(153, 153, 153));

            btnAlertConsulta7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta7.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta7.setText("Anterior");
            btnAlertConsulta7.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta7.setContentAreaFilled(false);
            btnAlertConsulta7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta7.setIconTextGap(30);
            btnAlertConsulta7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta7ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
            jPanel72.setLayout(jPanel72Layout);
            jPanel72Layout.setHorizontalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel72Layout.setVerticalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            lblResumenUsuario.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            lblResumenUsuario.setForeground(new java.awt.Color(51, 51, 51));
            lblResumenUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-User-64N.png"))); // NOI18N
            lblResumenUsuario.setText("Nombre de Terminal");

            lblResumenPC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            lblResumenPC.setForeground(new java.awt.Color(51, 51, 51));
            lblResumenPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Workstation-64N.png"))); // NOI18N
            lblResumenPC.setText("Nombre de Terminal");

            jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel11.setForeground(new java.awt.Color(51, 51, 51));
            jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icons8-Print-64N.png"))); // NOI18N
            jLabel11.setText("Nombre de Terminal");

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblResumenUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                                        .addComponent(lblResumenPC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addContainerGap(519, Short.MAX_VALUE)
                            .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13))
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(lblResumenUsuario)
                            .addGap(18, 18, 18)
                            .addComponent(lblResumenPC)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                            .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel72, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14))
            );

            jTabbedPane1.addTab("tab1", jPanel5);

            jPanel2.setBackground(new java.awt.Color(230, 230, 230));

            lblMensaje.setFont(new java.awt.Font("Segoe UI Semilight", 0, 28)); // NOI18N
            lblMensaje.setForeground(new java.awt.Color(102, 102, 102));
            lblMensaje.setText("Todo Correcto");

            lblDes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
            lblDes.setForeground(new java.awt.Color(51, 51, 51));
            lblDes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblDes.setText("La configuración del terminal se guardó de forma correcta.");
            lblDes.setToolTipText("");

            panelMenu.setBackground(new java.awt.Color(0, 98, 155));

            lblUsu1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
            lblUsu1.setForeground(new java.awt.Color(255, 255, 255));
            lblUsu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblUsu1.setText("Ir al Modulo de ");
            lblUsu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            lblUsu1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblUsu1MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
            panelMenu.setLayout(panelMenuLayout);
            panelMenuLayout.setHorizontalGroup(
                panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblUsu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            panelMenuLayout.setVerticalGroup(
                panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblUsu1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel73.setBackground(new java.awt.Color(153, 153, 153));

            btnAlertConsulta8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta8.setForeground(new java.awt.Color(255, 255, 255));
            btnAlertConsulta8.setText("Anterior");
            btnAlertConsulta8.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta8.setContentAreaFilled(false);
            btnAlertConsulta8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta8.setIconTextGap(30);
            btnAlertConsulta8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta8ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
            jPanel73.setLayout(jPanel73Layout);
            jPanel73Layout.setHorizontalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta8, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel73Layout.setVerticalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            panelMenu1.setBackground(new java.awt.Color(0, 98, 155));

            lblUsu2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
            lblUsu2.setForeground(new java.awt.Color(255, 255, 255));
            lblUsu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblUsu2.setText("Ir al Modulo de ");
            lblUsu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            lblUsu2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblUsu2MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout panelMenu1Layout = new javax.swing.GroupLayout(panelMenu1);
            panelMenu1.setLayout(panelMenu1Layout);
            panelMenu1Layout.setHorizontalGroup(
                panelMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenu1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblUsu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            panelMenu1Layout.setVerticalGroup(
                panelMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenu1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblUsu2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblDes, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13))
                .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addComponent(lblDes)
                    .addGap(111, 111, 111)
                    .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14))
            );

            jTabbedPane1.addTab("tab1", jPanel2);

            getContentPane().add(jTabbedPane1);
            jTabbedPane1.setBounds(0, 0, 817, 516);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtNROCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNROCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNROCaretUpdate

    private void txtPCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPCCaretUpdate

    private void btnAlertConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta1ActionPerformed
        if(lblMOD.getText().equals("CC")||lblMOD.getText().equals("CPP")){
            txtModulo.setText("CAJA / FACTURADOR"); 
        }else if(lblMOD.getText().equals("PP")){
            txtModulo.setText("PERSONAL"); 
        }
        jButton1.doClick();
        
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnAlertConsulta1ActionPerformed

    private void btnAlertConsulta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta2ActionPerformed
       Caja_PC_Registro cn = new Caja_PC_Registro();
        if(cn.VerificarNumero(jLabel2.getText(),txtNRO.getText())>0){
                jLabel1.setForeground(new Color(230,230,230));
               
            }else{
            jLabel1.setForeground(new Color(255,51,51));
            jTabbedPane1.setSelectedIndex(2);
        }
        
    }//GEN-LAST:event_btnAlertConsulta2ActionPerformed

    private void btnAlertConsulta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta3ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnAlertConsulta3ActionPerformed

    private void btnAlertConsulta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta4ActionPerformed

       
        lblResumenPC.setText("<HTML>"+"Terminal "+txtPC.getText()+"<br>"+"Nº "+txtNRO.getText());
        
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btnAlertConsulta4ActionPerformed

    private void btnAlertConsulta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta5ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnAlertConsulta5ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
   
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnAlertConsulta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta6ActionPerformed
        if(txtModulo.getText().equals("CAJA / FACTURADOR")){
            GuardarC_F();
            jTabbedPane1.setSelectedIndex(4);
        }else if(txtModulo.getText().equals("PERSONAL")){
            Guardar();
            jTabbedPane1.setSelectedIndex(4);
        }    
    }//GEN-LAST:event_btnAlertConsulta6ActionPerformed

    private void btnAlertConsulta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta7ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnAlertConsulta7ActionPerformed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
     
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_Grupos1.getSelectionModel().setSelectionInterval (0,0) ;
            tb_Grupos1.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

    private void tb_Grupos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupos1MouseClicked

    }//GEN-LAST:event_tb_Grupos1MouseClicked

    private void tb_Grupos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos1KeyPressed

       
    }//GEN-LAST:event_tb_Grupos1KeyPressed

    private void lblUsu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu1MouseClicked
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(Principal.lblUsu.getText(),Principal.lblCod_Modulo.getText(),Principal.tbPC);
        dispose();
    }//GEN-LAST:event_lblUsu1MouseClicked

    private void btnAlertConsulta8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta8ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnAlertConsulta8ActionPerformed

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

    private void lblUsu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsu2MouseClicked

    private void txtModuloCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtModuloCaretUpdate
       
    }//GEN-LAST:event_txtModuloCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(txtModulo.getText().equals("CAJA / FACTURADOR")){
            jLabel2.setText("CC"); 
            panelNRO.setVisible(true);
            panelMenu1.setVisible(true);
            lblUsu1.setText("Ir al Modulo de Caja");
            lblUsu2.setText("Ir al Facturador");
            panelMenu.setBackground(new Color(23,160,134)); 
            panelMenu1.setBackground(new Color(41,127,184));
            Caja_PC_Registro U = new Caja_PC_Registro();
            U.NUMERACION();
            txtNRO.requestFocus();
        }else if(txtModulo.getText().equals("CAJA / FACTURADOR")){
            jLabel2.setText("CC"); 
            panelMenu.setBackground(new Color(23,160,134)); 
            panelMenu1.setBackground(new Color(41,127,184));
            panelNRO.setVisible(true);
            panelMenu1.setVisible(true);
            lblUsu2.setText("Ir al Facturador");
            lblUsu1.setText("Ir al Modulo de Caja");
            Caja_PC_Registro U = new Caja_PC_Registro();
            U.NUMERACION();
            txtNRO.requestFocus();
        }else if(txtModulo.getText().equals("PERSONAL")){
            jLabel2.setText("PP"); 
            panelMenu.setBackground(new Color(122,77,135));
            panelNRO.setVisible(false);
            panelMenu1.setVisible(true);
            panelMenu1.setBackground(new Color(209,52,56));
            txtNRO.setText("0");
            lblUsu1.setText("Ir al Modulo de Personal");
            lblUsu2.setText("Ir al Modulo de Usuarios");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JDialog Unidad;
    private javax.swing.JButton btnAlertConsulta1;
    private javax.swing.JButton btnAlertConsulta2;
    private javax.swing.JButton btnAlertConsulta3;
    private javax.swing.JButton btnAlertConsulta4;
    private javax.swing.JButton btnAlertConsulta5;
    private javax.swing.JButton btnAlertConsulta6;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnAlertConsulta8;
    private javax.swing.JButton btnBuscarPaciente4;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblARID;
    private javax.swing.JLabel lblDes;
    public static javax.swing.JLabel lblMOD;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblResumenPC;
    public static javax.swing.JLabel lblResumenUsuario;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblUsu1;
    private javax.swing.JLabel lblUsu2;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCPT6;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMenu1;
    private javax.swing.JPanel panelNRO;
    private javax.swing.JTable tb_Grupos1;
    private javax.swing.JTextField txtBuscar1;
    public static javax.swing.JTextField txtModulo;
    public static javax.swing.JTextField txtNRO;
    public static javax.swing.JTextField txtPC;
    // End of variables declaration//GEN-END:variables
}
