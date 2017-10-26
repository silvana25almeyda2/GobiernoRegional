/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Caja;

import Servicios.Conexion;
import Vistas.Principal.Principal;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Caja.Caja_Cliente;
import modelo.Caja.Caja_Historia;
import modelo.Caja.Caja_NuevaVenta;

/**
 *
 * @author Administrador
 */
public class Caja_Historia_Clinica extends javax.swing.JFrame {
Conexion c=new Conexion();
Connection conexion=c.conectar();
ResultSet r;
byte tg;
byte tgm;
DefaultTableModel m4;
Caja_Historia hC = new Caja_Historia();
    /**
     * Creates new form Caja_Historia_Clinica
     */
    public Caja_Historia_Clinica() {
        
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Mind Map-100.png")).getImage());
        cargareliminar.setVisible(false);
        
        Caja_Historia A = new Caja_Historia();
        Caja_Historia N = new Caja_Historia();
        
        N.LISTAR_PERMISOS_HC(Principal.lblUsu.getText());
        A.LISTA_CLIENTES("",tb_Clientes);
        
        
        

        cbxGenero.setBackground(Color.WHITE);
        cbxEstadoCivil1.setBackground(Color.WHITE);
        NivelSuperior.setLocationRelativeTo(null);//en el centro
        UBICACIOB.setLocationRelativeTo(null);//en el centro
        
        tbClientes.getTableHeader().setVisible(false);
        tbClientes.setTableHeader(null);

        
        btnCaja.setVisible(false);
        Paginas.setEnabled(false);
        Paginas.setEnabledAt(0,false);
        Paginas.setEnabledAt(1, false);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
    }
    public void LIMPIAR(){
       txtDni.setText("");
       txtCodigo.setText(""); 
       txtApellidoPat.setText(""); 
       txtApellidoMat.setText(""); 
       txtNombre1.setText(""); 
       txtNacionalidad.setText(""); 
       txtGrupoSan.setText(""); 
       txtOcupacion.setText("");
       txtGradoIns.setText(""); 
       txtRiesgo.setText(""); 
       txtReligion.setText(""); 
       txtTelefono.setText(""); 
       txtReligion2.setText("");  
       txtDni.requestFocus();
       txtFecha.setDate(null);
       txtNacimiento.setText(""); 
       txtActual.setText(""); 
       cbxGenero.setSelectedIndex(0);
       cbxEstadoCivil1.setSelectedIndex(0);
    }
    
    public void LIMPIAR_EDITAR(){
       if(txtDni.getText().equals("null")){
           txtDni.setText("");
       }
       if(txtCodigo.getText().equals("null")){
           txtCodigo.setText(""); 
       }
       if(txtApellidoPat.getText().equals("null")){
           txtApellidoPat.setText(""); 
       }
       if(txtApellidoMat.getText().equals("null")){
           txtApellidoMat.setText("");
       }
       if(txtNombre1.getText().equals("null")){
           txtNombre1.setText(""); 
       } 
       if(txtNacionalidad.getText().equals("null")){
           txtNacionalidad.setText(""); 
       }
       if(txtGrupoSan.getText().equals("null")){
           txtGrupoSan.setText(""); 
       }
       if(txtOcupacion.getText().equals("null")){
           txtOcupacion.setText("");
       }
       if(txtGradoIns.getText().equals("null")){
           txtGradoIns.setText(""); 
       }
       if(txtRiesgo.getText().equals("null")){
           txtRiesgo.setText(""); 
       }
       if(txtReligion.getText().equals("null")){
           txtReligion.setText(""); 
       }
       if(txtTelefono.getText().equals("null")){
           txtTelefono.setText(""); 
       }
       if(txtReligion2.getText().equals("null")){
           txtReligion2.setText("");
       }
         
    }

    public void HABILITAR(boolean opcion){
       txtDni.setEditable(opcion);
//       txtCodigo.setEditable(opcion); 
       txtApellidoPat.setEditable(opcion); 
       txtApellidoMat.setEditable(opcion); 
       txtNombre1.setEditable(opcion); 
       txtNacionalidad.setEditable(opcion); 
       txtGrupoSan.setEditable(opcion); 
       txtOcupacion.setEditable(opcion);
       txtGradoIns.setEditable(opcion); 
       txtRiesgo.setEditable(opcion); 
       txtReligion.setEditable(opcion); 
       txtTelefono.setEditable(opcion); 
       txtReligion2.setEditable(opcion); 
       txtFecha.setEnabled(opcion);
       txtNacimiento.setEditable(opcion);
       txtActual.setEditable(opcion);
       cbxGenero.setEnabled(opcion);
       cbxEstadoCivil1.setEnabled(opcion); 
       btnBuscarCPT.setEnabled(opcion);
       btnBuscarCPT1.setEnabled(opcion);
    }
    private final static Pattern RTRIM = Pattern.compile("\\s+$");
    public static String rtrim(String s) {
    return RTRIM.matcher(s).replaceAll("");
    }
    
    public String determinarFecha(JDateChooser calendario){
         
        String fecha = "";
        try {
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH)+1;
        int anio = calendario.getCalendar().get(Calendar.YEAR); 
        
            if(dia < 10 && mes < 10){
            fecha = String.valueOf("0" + dia + "/" + "0" + mes + "/" + anio);
        }else 
            if(dia < 10 || mes < 10){
                if(dia < 10 && mes >=10){
                    fecha = String.valueOf("0" + dia + "/" + mes + "/" + anio);
                } else 
                    if(dia >= 10 && mes < 10){
                        fecha = String.valueOf(dia + "/" + "0" + mes + "/" + anio);
                    } 
            } else 
                fecha = String.valueOf(dia + "/" + mes + "/" + anio); 
         } catch (Exception e) {
                           cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Ingrese una fecha correcta");
                           eli.setVisible(false);
                           noeli.setVisible(false); 
         }
        
        return fecha;
    }
    
    
    
    public void mostrarNumHC(){
        char c1 = txtCodigo.getText().charAt(0);
        char c2 = txtCodigo.getText().charAt(1);
        char c3 = txtCodigo.getText().charAt(2);
        char c4 = txtCodigo.getText().charAt(3);
        char c5 = txtCodigo.getText().charAt(4);
        char c6 = txtCodigo.getText().charAt(5);
        char c7 = txtCodigo.getText().charAt(6);
        txtCodigo.setText(String.valueOf(c1)+String.valueOf(c2)+String.valueOf(c3)+String.valueOf(c4)+String.valueOf(c5)
                + "-" + String.valueOf(c6)+String.valueOf(c7));
    }
     
    
    public void CARGAR(){
        try {

        int fila=tb_Clientes.getSelectedRow();
        lblID.setText(String.valueOf(tb_Clientes.getValueAt(fila, 0)));   
        txtDni.setText(String.valueOf(tb_Clientes.getValueAt(fila, 1)));  

        txtCodigo.setText(String.valueOf(tb_Clientes.getValueAt(fila, 2))); 

        txtApellidoPat.setText(String.valueOf(tb_Clientes.getValueAt(fila, 3)));  
        txtApellidoMat.setText(String.valueOf(tb_Clientes.getValueAt(fila, 4))); 
        txtNombre1.setText(String.valueOf(tb_Clientes.getValueAt(fila, 5)));  
        
        String fechaSeleccionada1 = (String) tb_Clientes.getModel().getValueAt(fila, 7);
        try {
        DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = dfo.parse(fechaSeleccionada1);
        txtFecha.setDate(fecha);
        } catch (Exception e) {
        }
        txtNacionalidad.setText(String.valueOf(tb_Clientes.getValueAt(fila, 9))); 
        

        txtNacimiento.setText(String.valueOf(tb_Clientes.getValueAt(fila, 8))); 
        txtActual.setText(String.valueOf(tb_Clientes.getValueAt(fila, 10))); 
        if(txtNacimiento.getText().equals("null")||txtNacimiento.getText().equals("null null null")){
            txtNacimiento.setText("No Especificado");
        }
        if(txtActual.getText().equals("null")||txtActual.getText().equals("null null null")){
            txtActual.setText("No Especificado");
        }
        lblDEP.setText(String.valueOf(tb_Clientes.getValueAt(fila, 19))); 
        lblPROV.setText(String.valueOf(tb_Clientes.getValueAt(fila, 20))); 
        lblDIST.setText(String.valueOf(tb_Clientes.getValueAt(fila, 21))); 
        lblDEP1.setText(String.valueOf(tb_Clientes.getValueAt(fila, 22))); 
        lblPROV1.setText(String.valueOf(tb_Clientes.getValueAt(fila, 23))); 
        lblDIST1.setText(String.valueOf(tb_Clientes.getValueAt(fila, 24))); 
        
        cbxGenero.setSelectedItem(String.valueOf(tb_Clientes.getValueAt(fila, 6)));
        cbxEstadoCivil1.setSelectedItem(String.valueOf(tb_Clientes.getValueAt(fila, 12)));  
        txtGrupoSan.setText(String.valueOf(tb_Clientes.getValueAt(fila, 13))); 
        txtOcupacion.setText(String.valueOf(tb_Clientes.getValueAt(fila, 11)));
        txtGradoIns.setText(String.valueOf(tb_Clientes.getValueAt(fila, 17)));  
        txtRiesgo.setText(String.valueOf(tb_Clientes.getValueAt(fila, 18))); 
        
        txtReligion.setText(String.valueOf(tb_Clientes.getValueAt(fila, 14)));
        txtTelefono.setText(String.valueOf(tb_Clientes.getValueAt(fila, 15)));  
        txtReligion2.setText(String.valueOf(tb_Clientes.getValueAt(fila, 16))); 
        } catch (Exception e) {
        }
    }
    
    public void NUEVO_REGISTRO(){
        if(txtDni.getText().equals("")||txtApellidoPat.getText().equals("")||txtApellidoMat.getText().equals("")
                ||txtNombre1.getText().equals("")){
            cargareliminar.setVisible(true);        
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Debe completar los campos requeridos");
            eli.setText("Ok");
            eli.setVisible(true);
            noeli.setVisible(false);
            tgm=0;                 
        } else {
                String codigo = String.valueOf(txtCodigo.getText().charAt(0)) + 
                String.valueOf(txtCodigo.getText().charAt(1)) + String.valueOf(txtCodigo.getText().charAt(2)) + 
                String.valueOf(txtCodigo.getText().charAt(3)) + String.valueOf(txtCodigo.getText().charAt(4)) + 
                String.valueOf(txtCodigo.getText().charAt(6) + String.valueOf(txtCodigo.getText().charAt(7))) ;
                Caja_Historia cno1 = new Caja_Historia();
                cno1.setDNI(txtDni.getText());
                cno1.setNOMBRES(txtNombre1.getText());
                cno1.setAPELLIDO_PATERNO(txtApellidoPat.getText());
                cno1.setAPELLIDO_MATERNO(txtApellidoMat.getText());
                cno1.setUSUARIO(lblusu.getText());
                cno1.setCOD_HC(codigo);
                cno1.setSEXO(cbxGenero.getSelectedItem().toString());
                cno1.setFECHA_NAC(determinarFecha(txtFecha));
                cno1.setDEP_NAC(lblDEP.getText());
                cno1.setPROV_NAC(lblPROV.getText());
                cno1.setDIS_NAC(lblDIST.getText());
                cno1.setOCUPACION(txtOcupacion.getText());
                cno1.setESTADO_CIVIL(cbxEstadoCivil1.getSelectedItem().toString());
                cno1.setGRUPO_SANGUINEO(txtGrupoSan.getText());
                cno1.setRELIGION(txtReligion.getText());
                cno1.setTELEFONO(txtTelefono.getText());
                cno1.setCELULAR(txtReligion2.getText());
                cno1.setGRADO_INST(txtGradoIns.getText());
                cno1.setNACIONALIDAD(txtNacionalidad.getText());
                cno1.setDESP_R(lblDEP1.getText());
                cno1.setPROV_R(lblPROV1.getText());
                cno1.setDIST_R(lblDIST1.getText());
                cno1.setRIESGO(txtRiesgo.getText());
                
                    if(cno1.NUEVA_HISTORIA()==true){
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
                            Caja_Historia A = new Caja_Historia();
                            A.LISTA_CLIENTES("",tb_Clientes);
                            jLabel33.setText("Listado");
                            Paginas.setSelectedIndex(0);
                        }else if(lblTipoR.getText().equals("C")){
                            System.out.println("VAMO VENDIENDO");
                            Caja_NuevaVenta CNC = new Caja_NuevaVenta();
                            CNC.ULTIMO_CLIENTE_REGISTRADO(lblusu.getText());
                            Caja_Ventas.jButton1.doClick();
                            dispose();
                            
                        }
                        
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                    }
        } }                
    
        public void MODIFICAR_REGISTRO(){

                Caja_Historia cno1 = new Caja_Historia();
                String codigo = String.valueOf(txtCodigo.getText().charAt(0)) + 
                String.valueOf(txtCodigo.getText().charAt(1)) + String.valueOf(txtCodigo.getText().charAt(2)) + 
                String.valueOf(txtCodigo.getText().charAt(3)) + String.valueOf(txtCodigo.getText().charAt(4)) + 
                String.valueOf(txtCodigo.getText().charAt(6) + String.valueOf(txtCodigo.getText().charAt(7))) ;
                cno1.setID_CLIENTE(Integer.parseInt(lblID.getText()));
                cno1.setDNI(txtDni.getText());
                cno1.setNOMBRES(txtNombre1.getText());
                cno1.setAPELLIDO_PATERNO(txtApellidoPat.getText());
                cno1.setAPELLIDO_MATERNO(txtApellidoMat.getText());
                cno1.setUSUARIO(lblusu.getText());
                cno1.setCOD_HC(codigo);
                cno1.setSEXO(cbxGenero.getSelectedItem().toString());
                cno1.setFECHA_NAC(determinarFecha(txtFecha));
                cno1.setDEP_NAC(lblDEP.getText());
                cno1.setPROV_NAC(lblPROV.getText());
                cno1.setDIS_NAC(lblDIST.getText());
                cno1.setOCUPACION(txtOcupacion.getText());
                cno1.setESTADO_CIVIL(cbxEstadoCivil1.getSelectedItem().toString());
                cno1.setGRUPO_SANGUINEO(txtGrupoSan.getText());
                cno1.setRELIGION(txtReligion.getText());
                cno1.setTELEFONO(txtTelefono.getText());
                cno1.setCELULAR(txtReligion2.getText());
                cno1.setGRADO_INST(txtGradoIns.getText());
                cno1.setNACIONALIDAD(txtNacionalidad.getText());
                cno1.setDESP_R(lblDEP1.getText());
                cno1.setPROV_R(lblPROV1.getText());
                cno1.setDIST_R(lblDIST1.getText());
                cno1.setRIESGO(txtRiesgo.getText());
                    if(cno1.MODIFICAR_HISTORIA()==true){
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
                        Caja_Historia A = new Caja_Historia();
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
                    Caja_Historia A = new Caja_Historia();
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
        UBICACIOB = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        lblUbicacion = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bus = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtBuscarCliente = new javax.swing.JTextField();
        bus3 = new javax.swing.JLabel();
        btnBuscarPaciente2 = new javax.swing.JButton();
        lblDNI_PREVENTA = new javax.swing.JLabel();
        lblNOMBRE_PREVENTA = new javax.swing.JLabel();
        paneltablaHC = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel35 = new javax.swing.JPanel();
            jLabel47 = new javax.swing.JLabel();
            panelSinHC = new javax.swing.JPanel();
            jLabel16 = new javax.swing.JLabel();
            jLabel21 = new javax.swing.JLabel();
            panelBuscarHC = new javax.swing.JPanel();
            jLabel15 = new javax.swing.JLabel();
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
            lblTipoR = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            jLabel33 = new javax.swing.JLabel();
            Paginas = new javax.swing.JTabbedPane();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Clientes = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel2 = new javax.swing.JPanel();
                jLabel9 = new javax.swing.JLabel();
                txtDni = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                txtCodigo = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                txtApellidoPat = new javax.swing.JTextField();
                txtApellidoMat = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                txtNombre1 = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                txtFecha = new com.toedter.calendar.JDateChooser();
                jLabel25 = new javax.swing.JLabel();
                txtNacionalidad = new javax.swing.JTextField();
                jLabel18 = new javax.swing.JLabel();
                jLabel22 = new javax.swing.JLabel();
                jLabel12 = new javax.swing.JLabel();
                cbxGenero = new javax.swing.JComboBox<String>();
                jLabel27 = new javax.swing.JLabel();
                cbxEstadoCivil1 = new javax.swing.JComboBox<String>();
                jSeparator1 = new javax.swing.JSeparator();
                jSeparator2 = new javax.swing.JSeparator();
                jLabel13 = new javax.swing.JLabel();
                txtOcupacion = new javax.swing.JTextField();
                jLabel14 = new javax.swing.JLabel();
                txtGradoIns = new javax.swing.JTextField();
                jLabel29 = new javax.swing.JLabel();
                txtReligion = new javax.swing.JTextField();
                jLabel30 = new javax.swing.JLabel();
                txtTelefono = new javax.swing.JTextField();
                jLabel34 = new javax.swing.JLabel();
                txtReligion2 = new javax.swing.JTextField();
                txtRiesgo = new javax.swing.JTextField();
                jLabel28 = new javax.swing.JLabel();
                jLabel31 = new javax.swing.JLabel();
                txtGrupoSan = new javax.swing.JTextField();
                lblID = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                panelCPT1 = new javax.swing.JPanel();
                txtNacimiento = new javax.swing.JTextField();
                btnBuscarCPT = new javax.swing.JButton();
                panelCPT2 = new javax.swing.JPanel();
                txtActual = new javax.swing.JTextField();
                btnBuscarCPT1 = new javax.swing.JButton();
                lblDEP = new javax.swing.JLabel();
                lblPROV = new javax.swing.JLabel();
                lblDIST = new javax.swing.JLabel();
                lblDEP1 = new javax.swing.JLabel();
                lblPROV1 = new javax.swing.JLabel();
                lblDIST1 = new javax.swing.JLabel();
                jLabel19 = new javax.swing.JLabel();
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

                UBICACIOB.setAlwaysOnTop(true);
                UBICACIOB.setMinimumSize(new java.awt.Dimension(749, 350));
                UBICACIOB.setResizable(false);

                jPanel7.setBackground(new java.awt.Color(230, 230, 230));
                jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                lblUbicacion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                lblUbicacion.setForeground(new java.awt.Color(102, 102, 102));
                lblUbicacion.setText("Clientes");

                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel17.setForeground(new java.awt.Color(102, 102, 102));
                jLabel17.setText("Distrito Provincia Departamento");

                bus.setForeground(new java.awt.Color(230, 230, 230));
                bus.setText("jLabel37");

                jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscarCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                txtBuscarCliente.setForeground(new java.awt.Color(98, 98, 98));
                txtBuscarCliente.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                txtBuscarCliente.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarClienteCaretUpdate(evt);
                    }
                });
                txtBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtBuscarClienteMouseClicked(evt);
                    }
                });
                txtBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarClienteActionPerformed(evt);
                    }
                });
                txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarClienteKeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                jPanel27.setLayout(jPanel27Layout);
                jPanel27Layout.setHorizontalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel27Layout.setVerticalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                bus3.setForeground(new java.awt.Color(230, 230, 230));
                bus3.setText("jLabel37");

                btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                btnBuscarPaciente2.setContentAreaFilled(false);
                btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPaciente2ActionPerformed(evt);
                    }
                });

                lblDNI_PREVENTA.setForeground(new java.awt.Color(230, 230, 230));
                lblDNI_PREVENTA.setText("jLabel1");

                lblNOMBRE_PREVENTA.setForeground(new java.awt.Color(230, 230, 230));
                lblNOMBRE_PREVENTA.setText("jLabel1");

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(39, 39, 39)
                                        .addComponent(bus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNOMBRE_PREVENTA)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bus3))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblUbicacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDNI_PREVENTA)
                                .addGap(148, 148, 148))))
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblUbicacion)
                                    .addComponent(lblDNI_PREVENTA))
                                .addGap(10, 10, 10)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bus)
                                    .addComponent(bus3)
                                    .addComponent(lblNOMBRE_PREVENTA))))
                        .addGap(331, 331, 331))
                );

                paneltablaHC.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
                jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tbClientes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                tbClientes.setForeground(new java.awt.Color(51, 51, 51));
                tbClientes.setModel(new javax.swing.table.DefaultTableModel(
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
                tbClientes.setGridColor(new java.awt.Color(255, 255, 255));
                tbClientes.setRowHeight(38);
                tbClientes.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tbClientes.getTableHeader().setReorderingAllowed(false);
                tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbClientesMouseClicked(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        tbClientesMouseEntered(evt);
                    }
                });
                tbClientes.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbClientesKeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(tbClientes);

                jPanel35.setBackground(new java.awt.Color(102, 102, 102));
                jPanel35.setPreferredSize(new java.awt.Dimension(0, 2));

                javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
                jPanel35.setLayout(jPanel35Layout);
                jPanel35Layout.setHorizontalGroup(
                    jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );
                jPanel35Layout.setVerticalGroup(
                    jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 2, Short.MAX_VALUE)
                );

                jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                jLabel47.setText("Departamento - Provincia - Distrito");

                javax.swing.GroupLayout paneltablaHCLayout = new javax.swing.GroupLayout(paneltablaHC);
                paneltablaHC.setLayout(paneltablaHCLayout);
                paneltablaHCLayout.setHorizontalGroup(
                    paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(paneltablaHCLayout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                );
                paneltablaHCLayout.setVerticalGroup(
                    paneltablaHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneltablaHCLayout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                panelSinHC.setBackground(new java.awt.Color(255, 255, 255));

                jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel16.setForeground(new java.awt.Color(102, 102, 102));
                jLabel16.setText("No se hallaron coincidencias");

                jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 100)); // NOI18N
                jLabel21.setForeground(new java.awt.Color(39, 174, 96));
                jLabel21.setText(":(");

                javax.swing.GroupLayout panelSinHCLayout = new javax.swing.GroupLayout(panelSinHC);
                panelSinHC.setLayout(panelSinHCLayout);
                panelSinHCLayout.setHorizontalGroup(
                    panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSinHCLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addContainerGap(255, Short.MAX_VALUE))
                );
                panelSinHCLayout.setVerticalGroup(
                    panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSinHCLayout.createSequentialGroup()
                        .addGroup(panelSinHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel21))
                            .addGroup(panelSinHCLayout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel16)))
                        .addContainerGap(202, Short.MAX_VALUE))
                );

                panelBuscarHC.setBackground(new java.awt.Color(255, 255, 255));

                jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 34)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(102, 102, 102));
                jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/icons8-Mapa-64.png"))); // NOI18N
                jLabel15.setText("Ubicación");
                jLabel15.setIconTextGap(60);

                javax.swing.GroupLayout panelBuscarHCLayout = new javax.swing.GroupLayout(panelBuscarHC);
                panelBuscarHC.setLayout(panelBuscarHCLayout);
                panelBuscarHCLayout.setHorizontalGroup(
                    panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarHCLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelBuscarHCLayout.setVerticalGroup(
                    panelBuscarHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarHCLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel15)
                        .addContainerGap(85, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout UBICACIOBLayout = new javax.swing.GroupLayout(UBICACIOB.getContentPane());
                UBICACIOB.getContentPane().setLayout(UBICACIOBLayout);
                UBICACIOBLayout.setHorizontalGroup(
                    UBICACIOBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneltablaHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSinHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscarHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                UBICACIOBLayout.setVerticalGroup(
                    UBICACIOBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UBICACIOBLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelBuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(paneltablaHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelSinHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(39, 174, 96));
                jPanel1.setPreferredSize(new java.awt.Dimension(284, 678));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Historia Clínica<span style=\"font-size:'14px'\"><br>Admisión</br></span></html>");

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

                btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Buscar-32.png"))); // NOI18N
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

                lblTipoR.setForeground(new java.awt.Color(39, 174, 96));
                lblTipoR.setText("jLabel6");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbldetalle)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(btnCaja))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(lblTipoR)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNivel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(lblPermiso)))
                                .addGap(0, 9, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNivel)
                            .addComponent(lblTipoR))
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
                jLabel33.setText("Registro - Datos del Paciente");

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

                Paginas.addTab("tab1", jScrollPane3);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                jLabel9.setText("DNI");

                txtDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtDni.setForeground(new java.awt.Color(102, 102, 102));
                txtDni.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDniActionPerformed(evt);
                    }
                });
                txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtDniKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDniKeyTyped(evt);
                    }
                });

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                jLabel2.setText("N° de Historia Clínica");

                txtCodigo.setEditable(false);
                txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtCodigo.setForeground(new java.awt.Color(102, 102, 102));

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("Apellido Paterno");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("Apellido Materno");

                txtApellidoPat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtApellidoPat.setForeground(new java.awt.Color(102, 102, 102));
                txtApellidoPat.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtApellidoPatKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtApellidoPatKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtApellidoPatKeyTyped(evt);
                    }
                });

                txtApellidoMat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtApellidoMat.setForeground(new java.awt.Color(102, 102, 102));
                txtApellidoMat.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtApellidoMatActionPerformed(evt);
                    }
                });
                txtApellidoMat.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtApellidoMatKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtApellidoMatKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtApellidoMatKeyTyped(evt);
                    }
                });

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                jLabel5.setText("Primer Nombre");

                txtNombre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtNombre1.setForeground(new java.awt.Color(102, 102, 102));
                txtNombre1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtNombre1ActionPerformed(evt);
                    }
                });
                txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtNombre1KeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtNombre1KeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtNombre1KeyTyped(evt);
                    }
                });

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                jLabel8.setText("Fecha de Nacimiento");

                txtFecha.setBackground(new java.awt.Color(255, 255, 255));
                txtFecha.setDateFormatString("dd/MM/yyyy");
                txtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

                jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                jLabel25.setText("Nacionalidad");

                txtNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtNacionalidad.setForeground(new java.awt.Color(102, 102, 102));
                txtNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtNacionalidadKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtNacionalidadKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtNacionalidadKeyTyped(evt);
                    }
                });

                jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel18.setForeground(new java.awt.Color(51, 51, 51));
                jLabel18.setText("Lugar de Nacimiento");

                jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(51, 51, 51));
                jLabel22.setText("Residencia Actual");

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                jLabel12.setText("Género");

                cbxGenero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cbxGenero.setForeground(new java.awt.Color(102, 102, 102));
                cbxGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Femenino", "Masculino" }));
                cbxGenero.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        cbxGeneroKeyPressed(evt);
                    }
                });

                jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                jLabel27.setText("Estado Civil");

                cbxEstadoCivil1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cbxEstadoCivil1.setForeground(new java.awt.Color(102, 102, 102));
                cbxEstadoCivil1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Soltero(a)", "Casado(a)", "Viudo(a)", "Divorciado(a),", "Conviviente" }));
                cbxEstadoCivil1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        cbxEstadoCivil1KeyPressed(evt);
                    }
                });

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                jLabel13.setText("Ocupación");

                txtOcupacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtOcupacion.setForeground(new java.awt.Color(102, 102, 102));
                txtOcupacion.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtOcupacionKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtOcupacionKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtOcupacionKeyTyped(evt);
                    }
                });

                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                jLabel14.setText("Grado Instrucción");

                txtGradoIns.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtGradoIns.setForeground(new java.awt.Color(102, 102, 102));
                txtGradoIns.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtGradoInsKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtGradoInsKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtGradoInsKeyTyped(evt);
                    }
                });

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(51, 51, 51));
                jLabel29.setText("Religión");

                txtReligion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtReligion.setForeground(new java.awt.Color(102, 102, 102));
                txtReligion.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtReligionKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtReligionKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtReligionKeyTyped(evt);
                    }
                });

                jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(51, 51, 51));
                jLabel30.setText("Teléfono");

                txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtTelefono.setForeground(new java.awt.Color(102, 102, 102));
                txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtTelefonoKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtTelefonoKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtTelefonoKeyTyped(evt);
                    }
                });

                jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel34.setForeground(new java.awt.Color(51, 51, 51));
                jLabel34.setText("Celular");

                txtReligion2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtReligion2.setForeground(new java.awt.Color(102, 102, 102));
                txtReligion2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtReligion2KeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtReligion2KeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtReligion2KeyTyped(evt);
                    }
                });

                txtRiesgo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtRiesgo.setForeground(new java.awt.Color(102, 102, 102));
                txtRiesgo.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtRiesgoKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtRiesgoKeyReleased(evt);
                    }
                });

                jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                jLabel28.setText("Riesgo");

                jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel31.setForeground(new java.awt.Color(51, 51, 51));
                jLabel31.setText("Grupo Sanguineo");

                txtGrupoSan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtGrupoSan.setForeground(new java.awt.Color(102, 102, 102));
                txtGrupoSan.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtGrupoSanKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtGrupoSanKeyReleased(evt);
                    }
                });

                lblID.setForeground(new java.awt.Color(255, 255, 255));
                lblID.setText("jLabel6");

                jLabel6.setForeground(new java.awt.Color(255, 51, 51));
                jLabel6.setText("*");

                jLabel7.setForeground(new java.awt.Color(255, 51, 51));
                jLabel7.setText("*");

                jLabel10.setForeground(new java.awt.Color(255, 51, 51));
                jLabel10.setText("*");

                jLabel11.setForeground(new java.awt.Color(255, 51, 51));
                jLabel11.setText("*");

                panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtNacimiento.setForeground(new java.awt.Color(51, 51, 51));
                txtNacimiento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtNacimiento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                txtNacimiento.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNacimientoCaretUpdate(evt);
                    }
                });

                btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                btnBuscarCPT.setContentAreaFilled(false);
                btnBuscarCPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarCPTActionPerformed(evt);
                    }
                });
                btnBuscarCPT.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        btnBuscarCPTKeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                panelCPT1.setLayout(panelCPT1Layout);
                panelCPT1Layout.setHorizontalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtNacimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                panelCPT1Layout.setVerticalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtActual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtActual.setForeground(new java.awt.Color(51, 51, 51));
                txtActual.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtActual.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                txtActual.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtActualCaretUpdate(evt);
                    }
                });

                btnBuscarCPT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imagen/Búsqueda-25.png"))); // NOI18N
                btnBuscarCPT1.setContentAreaFilled(false);
                btnBuscarCPT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarCPT1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarCPT1ActionPerformed(evt);
                    }
                });
                btnBuscarCPT1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        btnBuscarCPT1KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                panelCPT2.setLayout(panelCPT2Layout);
                panelCPT2Layout.setHorizontalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtActual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                panelCPT2Layout.setVerticalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtActual, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblDEP.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
                lblDEP.setForeground(new java.awt.Color(255, 255, 255));
                lblDEP.setText("jLabel16");

                lblPROV.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
                lblPROV.setForeground(new java.awt.Color(255, 255, 255));
                lblPROV.setText("jLabel17");

                lblDIST.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
                lblDIST.setForeground(new java.awt.Color(255, 255, 255));
                lblDIST.setText("jLabel19");

                lblDEP1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
                lblDEP1.setForeground(new java.awt.Color(255, 255, 255));
                lblDEP1.setText("jLabel16");

                lblPROV1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
                lblPROV1.setForeground(new java.awt.Color(255, 255, 255));
                lblPROV1.setText("jLabel17");

                lblDIST1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
                lblDIST1.setForeground(new java.awt.Color(255, 255, 255));
                lblDIST1.setText("jLabel19");

                jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                jLabel19.setText("jLabel19");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDni, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(txtCodigo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblID)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(66, 66, 66)
                                        .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel8))
                                                    .addGap(40, 40, 40)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel25)
                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(panelCPT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtNacionalidad))))
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel22))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblDEP1)
                                                    .addComponent(lblDEP)
                                                    .addComponent(lblPROV)
                                                    .addComponent(lblPROV1)
                                                    .addComponent(lblDIST)
                                                    .addComponent(lblDIST1)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel10)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel31)))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbxEstadoCivil1, javax.swing.GroupLayout.Alignment.LEADING, 0, 178, Short.MAX_VALUE)
                                    .addComponent(cbxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGrupoSan))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel28))
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGradoIns, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                    .addComponent(txtOcupacion)
                                    .addComponent(txtRiesgo))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtReligion, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtReligion2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(45, 45, 45))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(9, 9, 9)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDEP)
                                .addGap(5, 5, 5)
                                .addComponent(lblDEP1)
                                .addGap(5, 5, 5)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblPROV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPROV1))
                            .addComponent(panelCPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDIST)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDIST1))
                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txtOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(txtReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cbxEstadoCivil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txtGradoIns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtReligion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRiesgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel31)
                            .addComponent(txtGrupoSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addContainerGap())
                );

                Paginas.addTab("tab2", jPanel2);

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
                        .addGap(29, 29, 29)
                        .addComponent(Mensaje)
                        .addGap(36, 36, 36)
                        .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                cargareliminarLayout.setVerticalGroup(
                    cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cargareliminarLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Paginas)))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
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

            tg = 1;
            lblTipoR.setText("N");
            btnNuevo.setEnabled(true);
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
            LIMPIAR();
            HABILITAR(true);
            hC.codHistoriaClinica(Principal.lblUsu.getText());
            mostrarNumHC();
            jLabel33.setText("Registro - Datos del Paciente");
            Paginas.setSelectedIndex(1);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if(lblPermiso.getText().equals("E")){
            tg=2;
            System.out.println("tg 2");
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            HABILITAR(true);
            jLabel33.setText("Registro - Datos del Paciente");
            Paginas.setSelectedIndex(1);
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        Caja_Cliente cn = new Caja_Cliente();
        String dni;
        dni=txtDni.getText();
        String sdni;
        sdni=rtrim(dni);
        int c;
        c=sdni.length();
        System.out.println(""+c);
        if(tg==1){
            if(c<=8){
                cargareliminar.setVisible(true);
                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("El DNI ingresado es Incorrecto, Verifique ");
                eli.setVisible(false);
                noeli.setVisible(false);
                txtDni.requestFocus();
               NUEVO_REGISTRO();
        }}
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

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Caja_Cliente A = new Caja_Cliente();
        A.LISTA_CLIENTES(buscartodo.getText(),tb_Clientes);
        Paginas.setSelectedIndex(0);
        jLabel33.setText("Resultados de la Busqueda");
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaMouseClicked

    }//GEN-LAST:event_btnListaMouseClicked

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        jLabel33.setText("Listado");
        buscartodo.setText("");
        Caja_Historia A = new Caja_Historia();
        A.LISTA_CLIENTES("",tb_Clientes);
        Paginas.setSelectedIndex(0);
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed
        tg = 1;
        lblTipoR.setText("C");
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnNuevo.setEnabled(false);
        LIMPIAR();
        HABILITAR(true);
        hC.codHistoriaClinica(Principal.lblUsu.getText());
        mostrarNumHC();
        Paginas.setSelectedIndex(1);
    }//GEN-LAST:event_btnCajaActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        Caja_Cliente cn = new Caja_Cliente();
        String dni;
        dni=txtDni.getText();
        String sdni;
        sdni=rtrim(dni);
        int c;
        c=sdni.length();
        System.out.println(""+c);
        if (tgm==3){
            if(c<8){
                cargareliminar.setVisible(true);
                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("El DNI ingresado es Incorrecto, Verifique ");
                eli.setVisible(false);
                noeli.setVisible(false);
                txtDni.requestFocus();
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

    private void tb_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMouseClicked

        if(evt.getClickCount()==1){
            CARGAR();
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);
        }
        if(evt.getClickCount()==2){
            jLabel33.setText("Edición");
            if(txtCodigo.getText().equals("null")){
                hC.codHistoriaClinica(Principal.lblUsu.getText());
                mostrarNumHC();
            }else if(!txtCodigo.getText().equals("null")){
                mostrarNumHC();
                System.out.println("VENGA HOMBRE UD TIENE HISTORIA...");
            }
            LIMPIAR_EDITAR();
            HABILITAR(false);
            Paginas.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tb_ClientesMouseClicked

    private void tb_ClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMousePressed

    }//GEN-LAST:event_tb_ClientesMousePressed

    private void tb_ClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ClientesKeyPressed

    }//GEN-LAST:event_tb_ClientesKeyPressed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed

    }//GEN-LAST:event_txtDniActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtApellidoPat.requestFocus();
        }
    }//GEN-LAST:event_txtDniKeyPressed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped

        try {        
            char tecla;
            tecla = evt.getKeyChar();
            if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
                evt.consume();
                getToolkit().beep();            
            }
            
            if (txtDni.getText().length()>7)
            {
                evt.consume();
            }

        } catch (Exception e) {
            System.out.println("error DNI: " + e.getMessage());
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtApellidoPatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPatKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtApellidoMat.requestFocus();
        }
    }//GEN-LAST:event_txtApellidoPatKeyPressed

    private void txtApellidoPatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPatKeyReleased
        txtApellidoPat.setText(txtApellidoPat.getText().toUpperCase());
    }//GEN-LAST:event_txtApellidoPatKeyReleased

    private void txtApellidoPatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPatKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtApellidoPatKeyTyped

    private void txtApellidoMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMatActionPerformed

    private void txtApellidoMatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMatKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtNombre1.requestFocus();
        }
    }//GEN-LAST:event_txtApellidoMatKeyPressed

    private void txtApellidoMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMatKeyReleased
        txtApellidoMat.setText(txtApellidoMat.getText().toUpperCase());
    }//GEN-LAST:event_txtApellidoMatKeyReleased

    private void txtApellidoMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMatKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtApellidoMatKeyTyped

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtNombre1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyPressed
       
    }//GEN-LAST:event_txtNombre1KeyPressed

    private void txtNombre1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyReleased
        txtNombre1.setText(txtNombre1.getText().toUpperCase());
    }//GEN-LAST:event_txtNombre1KeyReleased

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNombre1KeyTyped

    private void txtNacionalidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            btnBuscarCPT.requestFocus();
        }
    }//GEN-LAST:event_txtNacionalidadKeyPressed

    private void txtNacionalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyReleased
        txtNacionalidad.setText(txtNacionalidad.getText().toUpperCase());
    }//GEN-LAST:event_txtNacionalidadKeyReleased

    private void txtNacionalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNacionalidadKeyTyped

    private void cbxGeneroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxGeneroKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtReligion.requestFocus();
        }
    }//GEN-LAST:event_cbxGeneroKeyPressed

    private void cbxEstadoCivil1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxEstadoCivil1KeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtReligion.requestFocus();
        }
    }//GEN-LAST:event_cbxEstadoCivil1KeyPressed

    private void txtOcupacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtOcupacionKeyPressed

    private void txtOcupacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionKeyReleased
        txtOcupacion.setText(txtOcupacion.getText().toUpperCase());
    }//GEN-LAST:event_txtOcupacionKeyReleased

    private void txtOcupacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtOcupacionKeyTyped

    private void txtGradoInsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradoInsKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtRiesgo.requestFocus();
        }
    }//GEN-LAST:event_txtGradoInsKeyPressed

    private void txtGradoInsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradoInsKeyReleased
        txtGradoIns.setText(txtGradoIns.getText().toUpperCase());
    }//GEN-LAST:event_txtGradoInsKeyReleased

    private void txtGradoInsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradoInsKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtGradoInsKeyTyped

    private void txtReligionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligionKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtGrupoSan.requestFocus();
        }
    }//GEN-LAST:event_txtReligionKeyPressed

    private void txtReligionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligionKeyReleased

    }//GEN-LAST:event_txtReligionKeyReleased

    private void txtReligionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligionKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtReligionKeyTyped

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtRiesgoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRiesgoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtOcupacion.requestFocus();
        }
    }//GEN-LAST:event_txtRiesgoKeyPressed

    private void txtRiesgoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRiesgoKeyReleased
        txtRiesgo.setText(txtRiesgo.getText().toUpperCase());
    }//GEN-LAST:event_txtRiesgoKeyReleased

    private void txtReligion2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligion2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReligion2KeyPressed

    private void txtReligion2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligion2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReligion2KeyReleased

    private void txtReligion2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReligion2KeyTyped
        try {        
            char tecla;
            tecla = evt.getKeyChar();
            if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
                evt.consume();
                getToolkit().beep();            
            }
            
            if (txtDni.getText().length()>8)
            {
                evt.consume();
            }

        } catch (Exception e) {
            System.out.println("error DNI: " + e.getMessage());
        }
    }//GEN-LAST:event_txtReligion2KeyTyped

    private void btnAlertConsulta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta10ActionPerformed
        NivelSuperior.dispose();
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

    private void txtGrupoSanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoSanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoSanKeyPressed

    private void txtGrupoSanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoSanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoSanKeyReleased

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

    private void txtNacimientoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNacimientoCaretUpdate

    }//GEN-LAST:event_txtNacimientoCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
        Caja_Historia BU = new Caja_Historia();
        BU.LISTA_UBICACION("",tbClientes);
        txtBuscarCliente.setText("");
        txtBuscarCliente.requestFocus();
        lblUbicacion.setText("Lugar de Nacimiento");
        jLabel19.setText("N");
        UBICACIOB.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    private void btnBuscarCPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarCPTKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            UBICACIOB.setVisible(true);
            txtBuscarCliente.requestFocus();
        }
    }//GEN-LAST:event_btnBuscarCPTKeyPressed

    private void txtActualCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtActualCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActualCaretUpdate

    private void btnBuscarCPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT1ActionPerformed
        Caja_Historia BU = new Caja_Historia();
        BU.LISTA_UBICACION("",tbClientes);
        txtBuscarCliente.setText("");
        txtBuscarCliente.requestFocus();
        lblUbicacion.setText("Residencia Actual");
        jLabel19.setText("R");
        UBICACIOB.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPT1ActionPerformed

    private void btnBuscarCPT1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarCPT1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarCPT1KeyPressed

    private void txtBuscarClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarClienteCaretUpdate
        panelBuscarHC.setVisible(false);
        paneltablaHC.setVisible(true);
        panelSinHC.setVisible(false);
        Caja_Historia BU = new Caja_Historia();
        BU.LISTA_UBICACION(txtBuscarCliente.getText(),tbClientes);
        if (tbClientes.getRowCount() == 0){
            panelBuscarHC.setVisible(false);
            paneltablaHC.setVisible(false);
            panelSinHC.setVisible(true);
        }
        if (txtBuscarCliente.getText().length()==0){
            panelBuscarHC.setVisible(true);
            paneltablaHC.setVisible(false);
            panelSinHC.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarClienteCaretUpdate

    private void txtBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarClienteMouseClicked

    }//GEN-LAST:event_txtBuscarClienteMouseClicked

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    private void txtBuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tbClientes.getSelectionModel().setSelectionInterval (0,0) ;
            tbClientes.requestFocus();

        }
    }//GEN-LAST:event_txtBuscarClienteKeyPressed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked

        if(evt.getClickCount()==2){
            int fila=tbClientes.getSelectedRow();
            if(jLabel19.getText().equals("N")){
                txtNacimiento.setText(String.valueOf(tbClientes.getValueAt(fila, 0)));  
                lblDEP.setText(String.valueOf(tbClientes.getValueAt(fila, 1))); 
                lblPROV.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
                lblDIST.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
                //////
                txtActual.setText(String.valueOf(tbClientes.getValueAt(fila, 0)));  
                lblDEP1.setText(String.valueOf(tbClientes.getValueAt(fila, 1))); 
                lblPROV1.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
                lblDIST1.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
                
            } else if(jLabel19.getText().equals("R")){
                txtActual.setText(String.valueOf(tbClientes.getValueAt(fila, 0)));  
                lblDEP1.setText(String.valueOf(tbClientes.getValueAt(fila, 1))); 
                lblPROV1.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
                lblDIST1.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
       
            }
            UBICACIOB.setVisible(false);
        }
    }//GEN-LAST:event_tbClientesMouseClicked

    private void tbClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbClientesMouseEntered

    private void tbClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbClientesKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila=tbClientes.getSelectedRow();
            if(jLabel19.getText().equals("N")){
                txtNacimiento.setText(String.valueOf(tbClientes.getValueAt(fila, 0)));  
                lblDEP.setText(String.valueOf(tbClientes.getValueAt(fila, 1))); 
                lblPROV.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
                lblDIST.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
                //////
                txtActual.setText(String.valueOf(tbClientes.getValueAt(fila, 0)));  
                lblDEP1.setText(String.valueOf(tbClientes.getValueAt(fila, 1))); 
                lblPROV1.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
                lblDIST1.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
                
            } else if(jLabel19.getText().equals("R")){
                txtActual.setText(String.valueOf(tbClientes.getValueAt(fila, 0)));  
                lblDEP1.setText(String.valueOf(tbClientes.getValueAt(fila, 1))); 
                lblPROV1.setText(String.valueOf(tbClientes.getValueAt(fila, 2))); 
                lblDIST1.setText(String.valueOf(tbClientes.getValueAt(fila, 3))); 
       
            }
            UBICACIOB.setVisible(false);

        }
    }//GEN-LAST:event_tbClientesKeyPressed

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
            java.util.logging.Logger.getLogger(Caja_Historia_Clinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Historia_Clinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Historia_Clinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Historia_Clinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Historia_Clinica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog NivelSuperior;
    private javax.swing.JTabbedPane Paginas;
    private javax.swing.JDialog UBICACIOB;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarCPT1;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    public static javax.swing.JButton btnCaja;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel bus;
    private javax.swing.JLabel bus3;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    public static javax.swing.JComboBox<String> cbxEstadoCivil1;
    public static javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel35;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDEP;
    private javax.swing.JLabel lblDEP1;
    private javax.swing.JLabel lblDIST;
    private javax.swing.JLabel lblDIST1;
    private javax.swing.JLabel lblDNI_PREVENTA;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNOMBRE_PREVENTA;
    public static javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblPROV;
    private javax.swing.JLabel lblPROV1;
    public static javax.swing.JLabel lblPermiso;
    private javax.swing.JLabel lblTipoR;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelBuscarHC;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelSinHC;
    private javax.swing.JPanel paneltablaHC;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTable tb_Clientes;
    public static javax.swing.JTextField txtActual;
    public static javax.swing.JTextField txtApellidoMat;
    public static javax.swing.JTextField txtApellidoPat;
    private javax.swing.JTextField txtBuscarCliente;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDni;
    private com.toedter.calendar.JDateChooser txtFecha;
    public static javax.swing.JTextField txtGradoIns;
    public static javax.swing.JTextField txtGrupoSan;
    public static javax.swing.JTextField txtNacimiento;
    public static javax.swing.JTextField txtNacionalidad;
    public static javax.swing.JTextField txtNombre1;
    public static javax.swing.JTextField txtOcupacion;
    public static javax.swing.JTextField txtReligion;
    public static javax.swing.JTextField txtReligion2;
    public static javax.swing.JTextField txtRiesgo;
    public static javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
