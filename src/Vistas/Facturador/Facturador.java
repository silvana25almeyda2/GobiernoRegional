/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Facturador;

//import campos.LimitadorDeDocumento;
//import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import Vistas.Caja.Caja_Ventas;
import static Vistas.Caja.Caja_Ventas.lblID_CABECERA;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Facturador.CuentasPorPagarFacturasCabecera;
import modelo.Facturador.CuentasPorPagarFacturasDetalle;
import modelo.Facturador.CuentasPorPagarSfsRpta;
import modelo.Personal.CLS_Personal;
//import modelos.Caja.Caja_NuevaVenta;
//import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
//import modelos.cuentaPorPagar.CuentasPorPagarFacturasCabecera;
//import modelos.cuentaPorPagar.CuentasPorPagarFacturasDetalle;
//import modelos.cuentaPorPagar.CuentasPorPagarSfsRpta;
//import modelos.cuentaPorPagar.CuentasPorPagarVentasConsolidadoCabecera;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
//import Vistas.Caja.Caja_Empresa_jerarquia;
//import static vista.Principal.fechaActual;
//import static vista.admisionEmergencia.FrmFormatoEmergencia.pnlEObservación;
//import static vista.cuentaPorPagar.VentasConsolidado.Facturado;
//import static vista.cuentaPorPagar.VentasConsolidado.cbxActoMedico;
//import static vista.cuentaPorPagar.VentasConsolidado.lblCantidadActoMedico;
//import static vista.cuentaPorPagar.VentasConsolidado.lblDNI;
/**
 *
 * @author PC02
 */
public class Facturador extends javax.swing.JFrame {

    String barra = File.separator;
    String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
    CuentasPorPagarFacturasCabecera cuentasCab1 = new CuentasPorPagarFacturasCabecera();
    double sumatoriaIGV = 0.00;
    double sumatoriaTotal = 0.00;
    double sumInafectas = 0.00;
    double sumGravadas = 0.00;
    DefaultTableModel m;
    static CuentasPorPagarFacturasCabecera F = new CuentasPorPagarFacturasCabecera();
//    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
    public Facturador() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
        
        inicializar_TB_FACTURADOR();
        cbxTipoOperacion.setBackground(Color.WHITE);
        cbxCodUnidad.setBackground(Color.WHITE);
        cbxDocumento.setBackground(Color.WHITE);
        cbxTipoDocumento.setBackground(Color.WHITE);
        cbxGravado.setBackground(Color.WHITE);
        cbxTipoMoneda.setBackground(Color.WHITE);
        cbxAfecIGV.setBackground(Color.WHITE);
        cbxAfecISC.setBackground(Color.WHITE);
//        cuentasCab1.generarSerieCorrelativo("F");
     
        lblFechaEmision.setText(fechaActual());
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
                
            }
        });
        cerrar();
    }

    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
        });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void limpiar(){
        cbxAfecIGV.setSelectedIndex(0);
        cbxAfecISC.setSelectedIndex(0);
        cbxCodUnidad.setSelectedIndex(0);
        cbxDocumento.setSelectedIndex(0);
        cbxGravado.setSelectedIndex(0);
        cbxTipoDocumento.setSelectedIndex(0);
        cbxTipoMoneda.setSelectedIndex(0);
        cbxTipoOperacion.setSelectedIndex(0);
        txtApeNom.setText("");
        txtDsctoGlobal.setText("");
        txtImporteTotalVenta.setText("");
        txtMtoIGV.setText("");
        txtMtoISC.setText("");
        txtOtrosCargos.setText("");
        txtOtrosTributos.setText("");
        txtTipoDocumento.setText("");
        txtTotalDscto.setText("");
        txtValorVentaGravada.setText("");
        txtValorVentaInafectada.setText("");
        txtVentaExonerada.setText("");
       
    }
    
    public void enviarDatosEmpresa(){
        int fila=tb_Empresa.getSelectedRow();
        Empresa.dispose();
        txtApeNom.setText(String.valueOf(tb_Empresa.getValueAt(fila, 3)));
        txtTipoDocumento.setText(String.valueOf(tb_Empresa.getValueAt(fila, 4)));
        txtCorreo.setText(String.valueOf(tb_Empresa.getValueAt(fila, 10)));
        lblCodDomicilioFiscal.setText(String.valueOf(tb_Empresa.getValueAt(fila, 12)));
        lblEmpresa.setText(String.valueOf(tb_Empresa.getValueAt(fila, 0)));
        cbxTipoDocumento.setSelectedItem(String.valueOf(tb_Empresa.getValueAt(fila, 13)));
    }
    
    public String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.format(now);
    }
    
//    public boolean mantenimientoCuentasPorPagarFacturaCabecera(){
//        boolean resultado = false;
//        try {
//            CuentasPorPagarFacturasCabecera cuentasCab = new CuentasPorPagarFacturasCabecera();
//            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
//            if(lblMant.getText().equals("U") || lblMant.getText().equals("E"))
//                cuentasCab.setId(Integer.parseInt(lblId.getText()));
//            cuentasCab.setSerie(txtSerie.getText());
//            cuentasCab.setCorrelativo(lblNroCorrelativo.getText());
//            cuentasCab.setTipoOperacion(cbxTipoOperacion.getSelectedItem().toString());
//            cuentasCab.setFechaEmision(lblFechaEmision.getText());
//            cuentasCab.setTipoMoneda(cbxTipoMoneda.getSelectedItem().toString());
//            cuentasCab.setDocumento(cbxDocumento.getSelectedItem().toString());
//            cuentasCab.setCod_usu(adEmerCab.codUsuario(lblusu.getText()));
//            if(cuentasCab.mantenimientoCuentasPorPagarFacturasCabecera(lblMant.getText())){
//                resultado = true;
//            } else {
//                resultado = false;
//            }
//        } catch (Exception e) {
//            System.out.println("Error: mantenimientoCuentasPorPagarFacturaCabecera" + e.getMessage());
//        }
//        return resultado;
//    }
    
    public boolean crearCabecera(){
        CuentasPorPagarFacturasCabecera ruc=new CuentasPorPagarFacturasCabecera();
        boolean retorna = false;
        String archivo = ruc.factura_ruc() + "-" + 
                cbxDocumento.getSelectedItem().toString().charAt(0) + 
                cbxDocumento.getSelectedItem().toString().charAt(1) + "-" +
                txtSerieF.getText() + "-" + 
                lblNroCorrelativo.getText() + ".CAB";
        File crea_archivo = new File(archivo);
        if(txtTipoDocumento.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Ingrese tipo de documento");
        } else {
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                    retorna = false;
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                    if(cbxDocumento.getSelectedIndex()==0){
                        crea.format(String.valueOf(cbxTipoOperacion.getSelectedItem().toString().charAt(0)) + 
                        String.valueOf(cbxTipoOperacion.getSelectedItem().toString().charAt(1))+
                        "|"+lblFechaEmision.getText()+"|"+
                        lblCodDomicilioFiscal.getText()+"|"+
                        (cbxTipoDocumento.getSelectedItem().toString()).charAt(0)+"|"+
                        txtTipoDocumento.getText()+"|"+
                        txtApeNom.getText() + "|" + 
                        cbxTipoMoneda.getSelectedItem().toString() + "|" + 
                        txtDsctoGlobal.getText() + "|" + 
                        txtOtrosCargos.getText() + "|" + 
                        txtTotalDscto.getText() + "|" +
                        txtValorVentaGravada.getText() + "|" + 
                        txtValorVentaInafectada.getText() + "|" + 
                        txtVentaExonerada.getText() + "|" + 
                        txtMtoIGV.getText() + "|" +
                        txtMtoISC.getText()+ "|" + 
                        txtOtrosTributos.getText() + "|" + 
                        txtImporteTotalVenta.getText());
                    } else {
                        crea.format(String.valueOf(cbxTipoOperacion.getSelectedItem().toString().charAt(0)) + 
                        String.valueOf(cbxTipoOperacion.getSelectedItem().toString().charAt(1))+
                        "|"+lblFechaEmision.getText()+"|"+
                        lblCodDomicilioFiscal.getText()+"|"+
                        ""+"|"+
                        ""+"|"+
                        txtApeNom.getText() + "|" + 
                        cbxTipoMoneda.getSelectedItem().toString() + "|" + 
                        txtDsctoGlobal.getText() + "|" + 
                        txtOtrosCargos.getText() + "|" + 
                        txtTotalDscto.getText() + "|" +
                        txtValorVentaGravada.getText() + "|" + 
                        txtValorVentaInafectada.getText() + "|" + 
                        txtVentaExonerada.getText() + "|" + 
                        txtMtoIGV.getText() + "|" +
                        txtMtoISC.getText()+ "|" + 
                        txtOtrosTributos.getText() + "|" + 
                        txtImporteTotalVenta.getText());
                    }
                    crea.close();
                    retorna = true;
                }   
            } catch (Exception e) {
                    retorna = false;
            }
        }
        return retorna;
    }   
    
    public void crearDetalle(){
        String archivo = "20410275768" + "-" + 
                cbxDocumento.getSelectedItem().toString().charAt(0) + 
                cbxDocumento.getSelectedItem().toString().charAt(1) + "-" +
                txtSerieF.getText() + "-" + 
                lblNroCorrelativo.getText() + ".DET";
        File crea_archivo = new File(archivo);
        if(txtTipoDocumento.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No hay ID");
        } else {
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                    if(cbxCodUnidad.getSelectedIndex()==0 || cbxCodUnidad.getSelectedIndex()==4 ||
                               cbxCodUnidad.getSelectedIndex()==5 || cbxCodUnidad.getSelectedIndex()==6 ||
                               cbxCodUnidad.getSelectedIndex()==7){
                        crea.format(String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
                        String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) +
                        String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(2)) + "|" +
                        String.valueOf(tbFacturacion.getValueAt(0, 3)) + "|" + String.valueOf(tbFacturacion.getValueAt(0, 0))+  "|" + 
                         ""+ "|" + 
                        String.valueOf(tbFacturacion.getValueAt(0, 1))+ "|" + 
                         String.valueOf(tbFacturacion.getValueAt(0, 2)) + "|" + 
                         "0.00" + "|" + //DESCUENTO
                        "0.00" + "|" +  //IGV
                         String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
                        String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
                        "0.00"+ "|" + //ISC
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
                        String.valueOf("") +
                        String.valueOf("") + "|" +
                        "0.00" + "|" + //PRECIO DE VENTA
                        "0.00" //VALOR DE VENTA
                        );
                    } else {
                        crea.format(String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
                        String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) + "|" +
                         String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) +
                        String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(2)) + "|" +
                        String.valueOf(tbFacturacion.getValueAt(0, 3)) + "|" + String.valueOf(tbFacturacion.getValueAt(0, 0))+  "|" + 
                         ""+ "|" + 
                        String.valueOf(tbFacturacion.getValueAt(0, 1))+ "|" + 
                         String.valueOf(tbFacturacion.getValueAt(0, 2)) + "|"  + 
                         "0.00" + "|" + //DESCUENTO
                        "0.00" + "|" +  //IGV
                         String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
                        String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
                        "0.00"+ "|" + //ISC
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
                        String.valueOf("") +
                        String.valueOf("") + "|" +
                        "0.00" + "|" + //PRECIO DE VENTA
                        "0.00" //VALOR DE VENTA
                        );
                    }
                    crea.close();
                    JOptionPane.showMessageDialog(this, "Factura Electrónica Generada");
                }   
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "No se pudo");
            }
        }
    }   
    
    public boolean crearDetalles(File crea_archivo, String archivo){
        boolean retorna = false;
        try {
            Formatter crea = new Formatter(ubicacion+archivo);
            if(crea_archivo.exists()){
                JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                retorna = false;
            } else {
                String bloc1 = "";
                for (int c = 0; c < tbFacturacion.getRowCount(); c++){    
                    bloc1 = bloc1 + String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
                    String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) +
                    String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(2)) + "|" +
                    String.valueOf(tbFacturacion.getValueAt(c, 3)) + "|" + String.valueOf(tbFacturacion.getValueAt(c, 0))+  "|" + 
                     ""+ "|" + 
                    String.valueOf(tbFacturacion.getValueAt(c, 1))+ "|" + 
                     String.valueOf(tbFacturacion.getValueAt(c, 2)) + "|" + 
                     "0.00" + "|" + //DESCUENTO
                        String.valueOf(tbFacturacion.getValueAt(c, 5)) + "|" +  //IGV
                         String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
                        String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
                        "0.00"+ "|" + //ISC
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
//                        String.valueOf("") +
//                        String.valueOf("") + 
                            "|" +
                        String.valueOf(tbFacturacion.getValueAt(c, 4)) + "|" + //PRECIO DE VENTA
                        String.valueOf(tbFacturacion.getValueAt(c, 7)) //VALOR DE VENTA
                            + "\r\n";
                }
                String bloc2 = "";
                for (int c = 0; c < tbFacturacion.getRowCount(); c++){    
                    bloc2 = bloc2 + String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
                    String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) + "|" +
                    String.valueOf(tbFacturacion.getValueAt(c, 3)) + "|" + String.valueOf(tbFacturacion.getValueAt(c, 0))+  "|" + 
                     ""+ "|" + 
                    String.valueOf(tbFacturacion.getValueAt(c, 1))+ "|" + 
                     String.valueOf(tbFacturacion.getValueAt(c, 2)) + "|"  + 
                     "0.00" + "|" + //DESCUENTO
                       String.valueOf(tbFacturacion.getValueAt(c, 5)) + "|" +  //IGV
                         String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
                        String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
                        "0.00"+ "|" + //ISC
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
//                        String.valueOf("") +
//                        String.valueOf("") +
                            "|" +
                        String.valueOf(tbFacturacion.getValueAt(c, 4)) + "|" + //PRECIO DE VENTA
                        String.valueOf(tbFacturacion.getValueAt(c, 7)) //VALOR DE VENTA
                            + "\r\n";
                }
                if(cbxCodUnidad.getSelectedIndex()==0 || cbxCodUnidad.getSelectedIndex()==4 ||
                           cbxCodUnidad.getSelectedIndex()==5 || cbxCodUnidad.getSelectedIndex()==6 ||
                           cbxCodUnidad.getSelectedIndex()==7){
                    crea.format(bloc1);
                } else {
                    crea.format(bloc2);
                }
                crea.close();
                retorna = true;
            }   
        } catch (Exception e) {
            System.out.println("Error crear detalle: " + e.getMessage());
                retorna = false;
        }
        return retorna;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Empresa = new javax.swing.JDialog();
        jPanel48 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        txtBuscarEmpresa = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        btnBuscarPaciente5 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tb_Empresa = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
            jPanel21 = new javax.swing.JPanel();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            lblMant = new javax.swing.JLabel();
            lblId = new javax.swing.JLabel();
            lblEmpresa = new javax.swing.JLabel();
            btnGuardar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            lblDNI3 = new javax.swing.JLabel();
            lblDNI = new javax.swing.JLabel();
            TXT_ID_CLIENTE_F = new javax.swing.JTextField();
            lbl_id_cabecera_factura = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            jPanel6 = new javax.swing.JPanel();
            jLabel2 = new javax.swing.JLabel();
            cbxTipoOperacion = new javax.swing.JComboBox();
            jPanel7 = new javax.swing.JPanel();
            lblFechaEmision = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            lblCodDomicilioFiscal = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            jLabel6 = new javax.swing.JLabel();
            cbxTipoMoneda = new javax.swing.JComboBox();
            jPanel10 = new javax.swing.JPanel();
            cbxDocumento = new javax.swing.JComboBox();
            jLabel7 = new javax.swing.JLabel();
            jPanel12 = new javax.swing.JPanel();
            jLabel9 = new javax.swing.JLabel();
            lblNroCorrelativo = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            txtSerieF = new javax.swing.JLabel();
            txtSerie1 = new javax.swing.JLabel();
            jPanel13 = new javax.swing.JPanel();
            jLabel10 = new javax.swing.JLabel();
            cbxTipoDocumento = new javax.swing.JComboBox();
            jPanel14 = new javax.swing.JPanel();
            jLabel11 = new javax.swing.JLabel();
            panelCPT = new javax.swing.JPanel();
            txtTipoDocumento = new javax.swing.JTextField();
            jPanel15 = new javax.swing.JPanel();
            jLabel12 = new javax.swing.JLabel();
            panelCPT1 = new javax.swing.JPanel();
            txtApeNom = new javax.swing.JTextField();
            jPanel16 = new javax.swing.JPanel();
            jLabel13 = new javax.swing.JLabel();
            panelCPT2 = new javax.swing.JPanel();
            txtCorreo = new javax.swing.JTextField();
            jPanel18 = new javax.swing.JPanel();
            cbxGravado = new javax.swing.JComboBox();
            jPanel19 = new javax.swing.JPanel();
            cbxCodUnidad = new javax.swing.JComboBox();
            jLabel15 = new javax.swing.JLabel();
            jPanel30 = new javax.swing.JPanel();
            jLabel24 = new javax.swing.JLabel();
            cbxAfecIGV = new javax.swing.JComboBox();
            jPanel32 = new javax.swing.JPanel();
            jLabel26 = new javax.swing.JLabel();
            cbxAfecISC = new javax.swing.JComboBox();
            tablaS = new javax.swing.JScrollPane();
            tbFacturacion = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel36 = new javax.swing.JPanel();
                jLabel29 = new javax.swing.JLabel();
                panelCPT15 = new javax.swing.JPanel();
                txtDsctoGlobal = new javax.swing.JTextField();
                jPanel37 = new javax.swing.JPanel();
                jLabel30 = new javax.swing.JLabel();
                panelCPT16 = new javax.swing.JPanel();
                txtOtrosCargos = new javax.swing.JTextField();
                jPanel38 = new javax.swing.JPanel();
                jLabel31 = new javax.swing.JLabel();
                panelCPT17 = new javax.swing.JPanel();
                txtMtoIGV = new javax.swing.JTextField();
                jPanel39 = new javax.swing.JPanel();
                jLabel32 = new javax.swing.JLabel();
                panelCPT18 = new javax.swing.JPanel();
                txtValorVentaGravada = new javax.swing.JTextField();
                jPanel40 = new javax.swing.JPanel();
                jLabel33 = new javax.swing.JLabel();
                panelCPT19 = new javax.swing.JPanel();
                txtValorVentaInafectada = new javax.swing.JTextField();
                jPanel41 = new javax.swing.JPanel();
                jLabel34 = new javax.swing.JLabel();
                panelCPT20 = new javax.swing.JPanel();
                txtTotalDscto = new javax.swing.JTextField();
                jPanel42 = new javax.swing.JPanel();
                jLabel35 = new javax.swing.JLabel();
                panelCPT21 = new javax.swing.JPanel();
                txtOtrosTributos = new javax.swing.JTextField();
                jPanel43 = new javax.swing.JPanel();
                jLabel36 = new javax.swing.JLabel();
                panelCPT22 = new javax.swing.JPanel();
                txtMtoISC = new javax.swing.JTextField();
                jPanel44 = new javax.swing.JPanel();
                jLabel37 = new javax.swing.JLabel();
                panelCPT23 = new javax.swing.JPanel();
                txtVentaExonerada = new javax.swing.JTextField();
                jPanel45 = new javax.swing.JPanel();
                jLabel38 = new javax.swing.JLabel();
                panelCPT24 = new javax.swing.JPanel();
                txtImporteTotalVenta = new javax.swing.JTextField();
                jPanel46 = new javax.swing.JPanel();
                btnGenerarDoc = new javax.swing.JButton();

                Empresa.setAlwaysOnTop(true);
                Empresa.setMinimumSize(new java.awt.Dimension(754, 452));
                Empresa.setResizable(false);

                jPanel48.setBackground(new java.awt.Color(41, 127, 184));
                jPanel48.setMinimumSize(new java.awt.Dimension(310, 441));

                jLabel62.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel62.setForeground(new java.awt.Color(255, 255, 255));
                jLabel62.setText("<html>Empresa<span style=\"font-size:'14px'\"> Forma de pago</span></html>");

                jPanel49.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscarEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscarEmpresa.setBorder(null);
                txtBuscarEmpresa.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarEmpresaCaretUpdate(evt);
                    }
                });
                txtBuscarEmpresa.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarEmpresaActionPerformed(evt);
                    }
                });
                txtBuscarEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarEmpresaKeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
                jPanel49.setLayout(jPanel49Layout);
                jPanel49Layout.setHorizontalGroup(
                    jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                );
                jPanel49Layout.setVerticalGroup(
                    jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel56.setForeground(new java.awt.Color(255, 255, 255));
                jLabel56.setText("Representante o Razón social");

                btnBuscarPaciente5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                btnBuscarPaciente5.setContentAreaFilled(false);
                btnBuscarPaciente5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente5.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPaciente5ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
                jPanel48.setLayout(jPanel48Layout);
                jPanel48Layout.setHorizontalGroup(
                    jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(470, Short.MAX_VALUE))
                );
                jPanel48Layout.setVerticalGroup(
                    jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56)
                        .addGap(326, 326, 326))
                );

                jScrollPane16.setBackground(new java.awt.Color(255, 255, 255));
                jScrollPane16.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_Empresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tb_Empresa.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Empresa.setGridColor(new java.awt.Color(255, 255, 255));
                tb_Empresa.setRowHeight(25);
                tb_Empresa.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_Empresa.getTableHeader().setReorderingAllowed(false);
                tb_Empresa.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_EmpresaMouseClicked(evt);
                    }
                });
                tb_Empresa.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_EmpresaKeyPressed(evt);
                    }
                });
                jScrollPane16.setViewportView(tb_Empresa);

                javax.swing.GroupLayout EmpresaLayout = new javax.swing.GroupLayout(Empresa.getContentPane());
                Empresa.getContentPane().setLayout(EmpresaLayout);
                EmpresaLayout.setHorizontalGroup(
                    EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmpresaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );
                EmpresaLayout.setVerticalGroup(
                    EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmpresaLayout.createSequentialGroup()
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jPanel21.setBackground(new java.awt.Color(41, 127, 184));

                javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                jPanel21.setLayout(jPanel21Layout);
                jPanel21Layout.setHorizontalGroup(
                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 19, Short.MAX_VALUE)
                );
                jPanel21Layout.setVerticalGroup(
                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(41, 127, 184));
                jLabel1.setText("<html><span style=\"font-size:'30px'\">Cuenta por Pagar - </span>Factura Electrónica</html>");

                lblMant.setForeground(new java.awt.Color(255, 255, 255));
                lblMant.setText("I");

                lblId.setForeground(new java.awt.Color(255, 255, 255));
                lblId.setText("jLabel3");

                lblEmpresa.setForeground(new java.awt.Color(255, 255, 255));
                lblEmpresa.setText("Empresa");

                btnGuardar.setBackground(new java.awt.Color(102, 0, 102));
                btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
                btnGuardar.setText("Guardar");
                btnGuardar.setBorderPainted(false);
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                btnGuardar.setDefaultCapable(false);
                btnGuardar.setFocusPainted(false);
                btnGuardar.setFocusable(false);
                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                lblusu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                lblusu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lblusu.setText("Karina");

                lblDNI3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                lblDNI3.setForeground(new java.awt.Color(41, 127, 184));
                lblDNI3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lblDNI3.setText("RUC del Cliente:");

                lblDNI.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                lblDNI.setForeground(new java.awt.Color(41, 127, 184));
                lblDNI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lblDNI.setText("DNI");

                TXT_ID_CLIENTE_F.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        TXT_ID_CLIENTE_FCaretUpdate(evt);
                    }
                });

                lbl_id_cabecera_factura.setText(" ");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 547, Short.MAX_VALUE)
                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(lblMant)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEmpresa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar)
                                .addGap(68, 68, 68)
                                .addComponent(lbl_id_cabecera_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TXT_ID_CLIENTE_F, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(lblDNI3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMant)
                                .addComponent(lblId)
                                .addComponent(lblEmpresa)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_id_cabecera_factura))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblusu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDNI3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDNI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TXT_ID_CLIENTE_F, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                );

                jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

                jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                jPanel6.setDoubleBuffered(false);
                jPanel6.setFocusable(false);

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(102, 102, 102));
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("Tipo de Operación:");

                cbxTipoOperacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoOperacion.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoOperacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 VENTA INTERNA", "02 EXPORTACIÓN", "03 NO DOMICILIADOS", "04 VENTA INTERNA- ANTICIPOS", "05 VENTA ITINERANTE" }));
                cbxTipoOperacion.setBorder(null);
                cbxTipoOperacion.setLightWeightPopupEnabled(false);
                cbxTipoOperacion.setOpaque(false);
                cbxTipoOperacion.setRequestFocusEnabled(false);
                cbxTipoOperacion.setVerifyInputWhenFocusTarget(false);
                cbxTipoOperacion.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoOperacionActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbxTipoOperacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoOperacion)
                        .addContainerGap())
                );

                jPanel7.setBackground(new java.awt.Color(255, 255, 255));
                jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                lblFechaEmision.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                lblFechaEmision.setForeground(new java.awt.Color(102, 102, 102));
                lblFechaEmision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblFechaEmision.setText("2017-05-30");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 51, 51));
                jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel4.setText("Fecha de Emisión");

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );

                jPanel8.setBackground(new java.awt.Color(255, 255, 255));
                jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                lblCodDomicilioFiscal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                lblCodDomicilioFiscal.setForeground(new java.awt.Color(102, 102, 102));
                lblCodDomicilioFiscal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(102, 102, 102));
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Cod. Domicilio Fiscal");

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(lblCodDomicilioFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCodDomicilioFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 51, 51));
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel6.setText("Tipo Moneda");

                cbxTipoMoneda.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoMoneda.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PEN" }));
                cbxTipoMoneda.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxTipoMoneda.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoMonedaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipoMoneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoMoneda)
                        .addContainerGap())
                );

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                cbxDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxDocumento.setForeground(new java.awt.Color(102, 102, 102));
                cbxDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 FACTURA", "03 BOLETA" }));
                cbxDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxDocumento.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        cbxDocumentoItemStateChanged(evt);
                    }
                });
                cbxDocumento.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxDocumentoActionPerformed(evt);
                    }
                });

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(255, 51, 51));
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel7.setText("Documento");

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxDocumento, 0, 171, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDocumento)
                        .addContainerGap())
                );

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(255, 51, 51));
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel9.setText("Nº Correlativo");

                lblNroCorrelativo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                lblNroCorrelativo.setForeground(new java.awt.Color(51, 51, 51));
                lblNroCorrelativo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblNroCorrelativo.setText("000000003");

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(255, 51, 51));
                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel8.setText("Serie");

                txtSerieF.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                txtSerieF.setForeground(new java.awt.Color(51, 51, 51));
                txtSerieF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                txtSerieF.setText("F001");

                txtSerie1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                txtSerie1.setForeground(new java.awt.Color(51, 51, 51));
                txtSerie1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                txtSerie1.setText("-");

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSerieF, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSerie1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSerieF)
                                    .addComponent(txtSerie1)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNroCorrelativo)))
                        .addContainerGap())
                );

                jPanel13.setBackground(new java.awt.Color(255, 255, 255));
                jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel10.setForeground(new java.awt.Color(255, 51, 51));
                jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel10.setText("Tipo Documento");

                cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoDocumento.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "4 CARNET DE EXTRANJERIA", "6 RUC", "7 PASAPORTE", "A CED.DIPLOMATICA DE IDENTIDAD" }));
                cbxTipoDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addContainerGap())
                );

                jPanel14.setBackground(new java.awt.Color(255, 255, 255));
                jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel11.setForeground(new java.awt.Color(255, 51, 51));
                jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel11.setText("Nro de Documento");

                panelCPT.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtTipoDocumento.setEditable(false);
                txtTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtTipoDocumento.setForeground(new java.awt.Color(51, 51, 51));
                txtTipoDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTipoDocumento.setBorder(null);
                txtTipoDocumento.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTipoDocumentoCaretUpdate(evt);
                    }
                });
                txtTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtTipoDocumentoActionPerformed(evt);
                    }
                });
                txtTipoDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtTipoDocumentoKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
                panelCPT.setLayout(panelCPTLayout);
                panelCPTLayout.setHorizontalGroup(
                    panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoDocumento, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPTLayout.setVerticalGroup(
                    panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPTLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel15.setBackground(new java.awt.Color(255, 255, 255));
                jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(255, 51, 51));
                jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel12.setText("Apellidos y Nombres / Razón Social");

                panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtApeNom.setEditable(false);
                txtApeNom.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtApeNom.setForeground(new java.awt.Color(51, 51, 51));
                txtApeNom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtApeNom.setBorder(null);
                txtApeNom.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtApeNomCaretUpdate(evt);
                    }
                });
                txtApeNom.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtApeNomActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                panelCPT1.setLayout(panelCPT1Layout);
                panelCPT1Layout.setHorizontalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApeNom, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT1Layout.setVerticalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtApeNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel16.setBackground(new java.awt.Color(255, 255, 255));
                jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(255, 51, 51));
                jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel13.setText("Correo Electrónico");

                panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCorreo.setEditable(false);
                txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtCorreo.setForeground(new java.awt.Color(51, 51, 51));
                txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCorreo.setBorder(null);
                txtCorreo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCorreoCaretUpdate(evt);
                    }
                });
                txtCorreo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCorreoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                panelCPT2.setLayout(panelCPT2Layout);
                panelCPT2Layout.setHorizontalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT2Layout.setVerticalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel16Layout.setVerticalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel18.setBackground(new java.awt.Color(255, 255, 255));
                jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                cbxGravado.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                cbxGravado.setForeground(new java.awt.Color(102, 102, 102));
                cbxGravado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GRAVADO", "EXONERADO", "INAFECTO", "EXPORTACION", "GRATUITAS" }));
                cbxGravado.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxGravado.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxGravadoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                jPanel18.setLayout(jPanel18Layout);
                jPanel18Layout.setHorizontalGroup(
                    jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxGravado, 0, 0, Short.MAX_VALUE)
                        .addContainerGap())
                );
                jPanel18Layout.setVerticalGroup(
                    jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(cbxGravado)
                        .addContainerGap())
                );

                jPanel19.setBackground(new java.awt.Color(255, 255, 255));
                jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                cbxCodUnidad.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                cbxCodUnidad.setForeground(new java.awt.Color(102, 102, 102));
                cbxCodUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NIU UNIDAD", "ZZ SERVICIO", "BX CAJA", "CT CAJA DE CARTON", "LTR LITRO", "MTR METRO", "CMT CENTIMETRO", "KGM KILOGRAMO" }));
                cbxCodUnidad.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxCodUnidad.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxCodUnidadActionPerformed(evt);
                    }
                });

                jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(255, 51, 51));
                jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel15.setText("Cod. Unidad");

                javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                jPanel19.setLayout(jPanel19Layout);
                jPanel19Layout.setHorizontalGroup(
                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxCodUnidad, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addContainerGap(13, Short.MAX_VALUE))
                );
                jPanel19Layout.setVerticalGroup(
                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCodUnidad)
                        .addContainerGap())
                );

                jPanel30.setBackground(new java.awt.Color(255, 255, 255));
                jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel24.setForeground(new java.awt.Color(255, 51, 51));
                jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel24.setText("Afec. IGV");

                cbxAfecIGV.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                cbxAfecIGV.setForeground(new java.awt.Color(102, 102, 102));
                cbxAfecIGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10 GRAVADO-OPERACIÓN ONEROSA", "11 GRAVADO-RETIRO POR PREMIO", "12 GRAVADO-RETIRO POR DONACIÓN", "13 GRAVADO-RETIRO", "14 GRAVADO-RETIRO POR PUBLICIDAD", "15 GRAVADO-BONIFICACIONES", "16 GRAVADO-RETIRO POR ENTREGA A TRABAJADORES", "20 EXONERADO-OPERACIÓN ONEROSA", "30 INAFECTO-OPERACIÓN ONEROSA", "31 INAFECTO-RETIRO POR BONIFICACIÓN", "32 INAFECTO-RETIRO", "33 INAFECTO-RETIRO POR MUESTRAS MÉDICAS", "34 INAFECTO-RETIRO POR CONVENIO COLECTIVO", "35 INAFECTO-RETIRO POR PREMIO", "36 INAFECTO-RETIRO POR PUBLICIDAD", "40 EXPORTACIÓN" }));
                cbxAfecIGV.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxAfecIGV.setLightWeightPopupEnabled(false);
                cbxAfecIGV.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxAfecIGVActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                .addComponent(cbxAfecIGV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAfecIGV)
                        .addContainerGap())
                );

                jPanel32.setBackground(new java.awt.Color(255, 255, 255));
                jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(102, 102, 102));
                jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel26.setText("Afec. ISC");

                cbxAfecISC.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                cbxAfecISC.setForeground(new java.awt.Color(102, 102, 102));
                cbxAfecISC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 SISTEMA AL VALOR", "02 APLICACIÓN DEL MONTO FIJO", "03 SISTEMA DE PRECIOS DE VENTA AL PÚBLICO" }));
                cbxAfecISC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxAfecISC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxAfecISCActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
                jPanel32.setLayout(jPanel32Layout);
                jPanel32Layout.setHorizontalGroup(
                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxAfecISC, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel32Layout.setVerticalGroup(
                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAfecISC, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addContainerGap())
                );

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                tablaS.setBackground(new java.awt.Color(255, 255, 255));
                tablaS.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tablaS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                tbFacturacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                tbFacturacion.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Título 1", "Título 2", "Título 3", "Título 4"
                    }
                ));
                tbFacturacion.setGridColor(new java.awt.Color(255, 255, 255));
                tbFacturacion.setRowHeight(25);
                tbFacturacion.setSelectionBackground(new java.awt.Color(153, 153, 153));
                tbFacturacion.getTableHeader().setReorderingAllowed(false);
                tbFacturacion.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbFacturacionMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbFacturacionMousePressed(evt);
                    }
                });
                tbFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbFacturacionKeyPressed(evt);
                    }
                });
                tablaS.setViewportView(tbFacturacion);

                jPanel36.setBackground(new java.awt.Color(255, 255, 255));
                jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(102, 102, 102));
                jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel29.setText("Descuento Global");

                panelCPT15.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtDsctoGlobal.setEditable(false);
                txtDsctoGlobal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtDsctoGlobal.setForeground(new java.awt.Color(51, 51, 51));
                txtDsctoGlobal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDsctoGlobal.setText("0.00");
                txtDsctoGlobal.setBorder(null);
                txtDsctoGlobal.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDsctoGlobalCaretUpdate(evt);
                    }
                });
                txtDsctoGlobal.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDsctoGlobalActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT15Layout = new javax.swing.GroupLayout(panelCPT15);
                panelCPT15.setLayout(panelCPT15Layout);
                panelCPT15Layout.setHorizontalGroup(
                    panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDsctoGlobal, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT15Layout.setVerticalGroup(
                    panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT15Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDsctoGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
                jPanel36.setLayout(jPanel36Layout);
                jPanel36Layout.setHorizontalGroup(
                    jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(panelCPT15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel36Layout.setVerticalGroup(
                    jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel37.setBackground(new java.awt.Color(255, 255, 255));
                jPanel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(102, 102, 102));
                jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel30.setText("Sum. otros Cargos");

                panelCPT16.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtOtrosCargos.setEditable(false);
                txtOtrosCargos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtOtrosCargos.setForeground(new java.awt.Color(51, 51, 51));
                txtOtrosCargos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtOtrosCargos.setText("0.00");
                txtOtrosCargos.setBorder(null);
                txtOtrosCargos.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtOtrosCargosCaretUpdate(evt);
                    }
                });
                txtOtrosCargos.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtOtrosCargosActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT16Layout = new javax.swing.GroupLayout(panelCPT16);
                panelCPT16.setLayout(panelCPT16Layout);
                panelCPT16Layout.setHorizontalGroup(
                    panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOtrosCargos, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT16Layout.setVerticalGroup(
                    panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT16Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtOtrosCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
                jPanel37.setLayout(jPanel37Layout);
                jPanel37Layout.setHorizontalGroup(
                    jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(panelCPT16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel37Layout.setVerticalGroup(
                    jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel38.setBackground(new java.awt.Color(255, 255, 255));
                jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel31.setForeground(new java.awt.Color(102, 102, 102));
                jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel31.setText("Sumatoria IGV");

                panelCPT17.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMtoIGV.setEditable(false);
                txtMtoIGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtMtoIGV.setForeground(new java.awt.Color(51, 51, 51));
                txtMtoIGV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMtoIGV.setText("0.00");
                txtMtoIGV.setBorder(null);
                txtMtoIGV.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMtoIGVCaretUpdate(evt);
                    }
                });
                txtMtoIGV.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtMtoIGVActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT17Layout = new javax.swing.GroupLayout(panelCPT17);
                panelCPT17.setLayout(panelCPT17Layout);
                panelCPT17Layout.setHorizontalGroup(
                    panelCPT17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMtoIGV, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT17Layout.setVerticalGroup(
                    panelCPT17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT17Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtMtoIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
                jPanel38.setLayout(jPanel38Layout);
                jPanel38Layout.setHorizontalGroup(
                    jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(panelCPT17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel38Layout.setVerticalGroup(
                    jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel39.setBackground(new java.awt.Color(255, 255, 255));
                jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel32.setForeground(new java.awt.Color(255, 51, 51));
                jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel32.setText("Total Valor de Ventas Gravadas");

                panelCPT18.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVentaGravada.setEditable(false);
                txtValorVentaGravada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtValorVentaGravada.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVentaGravada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVentaGravada.setText("0.00");
                txtValorVentaGravada.setBorder(null);
                txtValorVentaGravada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaGravadaCaretUpdate(evt);
                    }
                });
                txtValorVentaGravada.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaGravadaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT18Layout = new javax.swing.GroupLayout(panelCPT18);
                panelCPT18.setLayout(panelCPT18Layout);
                panelCPT18Layout.setHorizontalGroup(
                    panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVentaGravada, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT18Layout.setVerticalGroup(
                    panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT18Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVentaGravada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
                jPanel39.setLayout(jPanel39Layout);
                jPanel39Layout.setHorizontalGroup(
                    jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel39Layout.setVerticalGroup(
                    jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel40.setBackground(new java.awt.Color(255, 255, 255));
                jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel33.setForeground(new java.awt.Color(255, 51, 51));
                jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel33.setText("Total Valor de Ventas Inafectadas");

                panelCPT19.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVentaInafectada.setEditable(false);
                txtValorVentaInafectada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtValorVentaInafectada.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVentaInafectada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVentaInafectada.setText("0.00");
                txtValorVentaInafectada.setBorder(null);
                txtValorVentaInafectada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaInafectadaCaretUpdate(evt);
                    }
                });
                txtValorVentaInafectada.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaInafectadaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT19Layout = new javax.swing.GroupLayout(panelCPT19);
                panelCPT19.setLayout(panelCPT19Layout);
                panelCPT19Layout.setHorizontalGroup(
                    panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVentaInafectada, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT19Layout.setVerticalGroup(
                    panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT19Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVentaInafectada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
                jPanel40.setLayout(jPanel40Layout);
                jPanel40Layout.setHorizontalGroup(
                    jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel40Layout.setVerticalGroup(
                    jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel41.setBackground(new java.awt.Color(255, 255, 255));
                jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel34.setForeground(new java.awt.Color(102, 102, 102));
                jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel34.setText("Total Descuentos");

                panelCPT20.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtTotalDscto.setEditable(false);
                txtTotalDscto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtTotalDscto.setForeground(new java.awt.Color(51, 51, 51));
                txtTotalDscto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTotalDscto.setText("0.00");
                txtTotalDscto.setBorder(null);
                txtTotalDscto.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTotalDsctoCaretUpdate(evt);
                    }
                });
                txtTotalDscto.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtTotalDsctoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT20Layout = new javax.swing.GroupLayout(panelCPT20);
                panelCPT20.setLayout(panelCPT20Layout);
                panelCPT20Layout.setHorizontalGroup(
                    panelCPT20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalDscto, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT20Layout.setVerticalGroup(
                    panelCPT20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT20Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtTotalDscto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
                jPanel41.setLayout(jPanel41Layout);
                jPanel41Layout.setHorizontalGroup(
                    jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(panelCPT20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel41Layout.setVerticalGroup(
                    jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel42.setBackground(new java.awt.Color(255, 255, 255));
                jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel35.setForeground(new java.awt.Color(102, 102, 102));
                jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel35.setText("Sum. otros Tributos");

                panelCPT21.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtOtrosTributos.setEditable(false);
                txtOtrosTributos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtOtrosTributos.setForeground(new java.awt.Color(51, 51, 51));
                txtOtrosTributos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtOtrosTributos.setText("0.00");
                txtOtrosTributos.setBorder(null);
                txtOtrosTributos.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtOtrosTributosCaretUpdate(evt);
                    }
                });
                txtOtrosTributos.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtOtrosTributosActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT21Layout = new javax.swing.GroupLayout(panelCPT21);
                panelCPT21.setLayout(panelCPT21Layout);
                panelCPT21Layout.setHorizontalGroup(
                    panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOtrosTributos, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT21Layout.setVerticalGroup(
                    panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT21Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtOtrosTributos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
                jPanel42.setLayout(jPanel42Layout);
                jPanel42Layout.setHorizontalGroup(
                    jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(panelCPT21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel42Layout.setVerticalGroup(
                    jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel43.setBackground(new java.awt.Color(255, 255, 255));
                jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel36.setForeground(new java.awt.Color(102, 102, 102));
                jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel36.setText("Sumatoria ISC");

                panelCPT22.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMtoISC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtMtoISC.setForeground(new java.awt.Color(51, 51, 51));
                txtMtoISC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMtoISC.setText("0.00");
                txtMtoISC.setBorder(null);
                txtMtoISC.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMtoISCCaretUpdate(evt);
                    }
                });
                txtMtoISC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtMtoISCActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT22Layout = new javax.swing.GroupLayout(panelCPT22);
                panelCPT22.setLayout(panelCPT22Layout);
                panelCPT22Layout.setHorizontalGroup(
                    panelCPT22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMtoISC, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT22Layout.setVerticalGroup(
                    panelCPT22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT22Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtMtoISC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
                jPanel43.setLayout(jPanel43Layout);
                jPanel43Layout.setHorizontalGroup(
                    jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(panelCPT22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel43Layout.setVerticalGroup(
                    jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel44.setBackground(new java.awt.Color(255, 255, 255));
                jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel37.setForeground(new java.awt.Color(255, 51, 51));
                jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel37.setText("Total de Ventas Exoneradas");

                panelCPT23.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtVentaExonerada.setEditable(false);
                txtVentaExonerada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtVentaExonerada.setForeground(new java.awt.Color(51, 51, 51));
                txtVentaExonerada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtVentaExonerada.setText("0.00");
                txtVentaExonerada.setBorder(null);
                txtVentaExonerada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtVentaExoneradaCaretUpdate(evt);
                    }
                });
                txtVentaExonerada.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtVentaExoneradaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT23Layout = new javax.swing.GroupLayout(panelCPT23);
                panelCPT23.setLayout(panelCPT23Layout);
                panelCPT23Layout.setHorizontalGroup(
                    panelCPT23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVentaExonerada, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT23Layout.setVerticalGroup(
                    panelCPT23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT23Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtVentaExonerada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
                jPanel44.setLayout(jPanel44Layout);
                jPanel44Layout.setHorizontalGroup(
                    jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel44Layout.setVerticalGroup(
                    jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel45.setBackground(new java.awt.Color(255, 255, 255));
                jPanel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel38.setForeground(new java.awt.Color(255, 51, 51));
                jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel38.setText("Importe Total de Venta");

                panelCPT24.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtImporteTotalVenta.setEditable(false);
                txtImporteTotalVenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtImporteTotalVenta.setForeground(new java.awt.Color(51, 51, 51));
                txtImporteTotalVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtImporteTotalVenta.setText("0.00");
                txtImporteTotalVenta.setBorder(null);
                txtImporteTotalVenta.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtImporteTotalVentaCaretUpdate(evt);
                    }
                });
                txtImporteTotalVenta.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtImporteTotalVentaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT24Layout = new javax.swing.GroupLayout(panelCPT24);
                panelCPT24.setLayout(panelCPT24Layout);
                panelCPT24Layout.setHorizontalGroup(
                    panelCPT24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtImporteTotalVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT24Layout.setVerticalGroup(
                    panelCPT24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT24Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtImporteTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
                jPanel45.setLayout(jPanel45Layout);
                jPanel45Layout.setHorizontalGroup(
                    jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel45Layout.setVerticalGroup(
                    jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel46.setBackground(new java.awt.Color(41, 127, 184));
                jPanel46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                btnGenerarDoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                btnGenerarDoc.setForeground(new java.awt.Color(255, 255, 255));
                btnGenerarDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Documento-50.png"))); // NOI18N
                btnGenerarDoc.setMnemonic('G');
                btnGenerarDoc.setText("<html>Generar Documento <br>Electrónico</html>");
                btnGenerarDoc.setBorderPainted(false);
                btnGenerarDoc.setContentAreaFilled(false);
                btnGenerarDoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGenerarDoc.setIconTextGap(20);
                btnGenerarDoc.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGenerarDocActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
                jPanel46.setLayout(jPanel46Layout);
                jPanel46Layout.setHorizontalGroup(
                    jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarDoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                );
                jPanel46Layout.setVerticalGroup(
                    jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))
                                    .addComponent(tablaS))))
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tablaS, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(28, Short.MAX_VALUE))))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoOperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoOperacionActionPerformed

    private void cbxTipoMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoMonedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoMonedaActionPerformed

    private void cbxDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocumentoActionPerformed

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void txtTipoDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTipoDocumentoCaretUpdate

    }//GEN-LAST:event_txtTipoDocumentoCaretUpdate

    private void txtTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocumentoActionPerformed

    private void txtApeNomCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtApeNomCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeNomCaretUpdate

    private void txtApeNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApeNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeNomActionPerformed

    private void txtCorreoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCorreoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoCaretUpdate

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void cbxGravadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGravadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGravadoActionPerformed

    private void cbxCodUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCodUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCodUnidadActionPerformed

    private void cbxAfecIGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAfecIGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAfecIGVActionPerformed

    private void cbxAfecISCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAfecISCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAfecISCActionPerformed

    private void tbFacturacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionMouseClicked

    }//GEN-LAST:event_tbFacturacionMouseClicked

    private void tbFacturacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionMousePressed

    }//GEN-LAST:event_tbFacturacionMousePressed

    private void tbFacturacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFacturacionKeyPressed
//        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
//            int fila = tbFacturacion.getSelectedRow();
//        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
//            if(cabecera1.actualizarEstadoFacturacion(String.valueOf(tbFacturacion.getValueAt(fila,8)),"P")){
//                cabecera1.listarPorFacturar(tbFacturacion,lblDNI.getText());
//                VentasConsolidado.btnNuevo.doClick();
//                VentasConsolidado.txtDni.setText(lblDNI.getText());
//                VentasConsolidado.T3.doClick();
//            } else {
//                System.out.println("error");
//            }
//        }
    }//GEN-LAST:event_tbFacturacionKeyPressed

    private void txtDsctoGlobalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDsctoGlobalCaretUpdate
        btnGuardar.doClick();
    }//GEN-LAST:event_txtDsctoGlobalCaretUpdate

    private void txtDsctoGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsctoGlobalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsctoGlobalActionPerformed

    private void txtOtrosCargosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOtrosCargosCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosCargosCaretUpdate

    private void txtOtrosCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtrosCargosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosCargosActionPerformed

    private void txtMtoIGVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMtoIGVCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoIGVCaretUpdate

    private void txtMtoIGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMtoIGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoIGVActionPerformed

    private void txtValorVentaGravadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaGravadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaGravadaCaretUpdate

    private void txtValorVentaGravadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaGravadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaGravadaActionPerformed

    private void txtValorVentaInafectadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaInafectadaCaretUpdate
        
    }//GEN-LAST:event_txtValorVentaInafectadaCaretUpdate

    private void txtValorVentaInafectadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaInafectadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaInafectadaActionPerformed

    private void txtTotalDsctoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTotalDsctoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDsctoCaretUpdate

    private void txtTotalDsctoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalDsctoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDsctoActionPerformed

    private void txtOtrosTributosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOtrosTributosCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosTributosCaretUpdate

    private void txtOtrosTributosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtrosTributosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosTributosActionPerformed

    private void txtMtoISCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMtoISCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoISCCaretUpdate

    private void txtMtoISCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMtoISCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoISCActionPerformed

    private void txtVentaExoneradaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVentaExoneradaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentaExoneradaCaretUpdate

    private void txtVentaExoneradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVentaExoneradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentaExoneradaActionPerformed

    private void txtImporteTotalVentaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaCaretUpdate

    private void txtImporteTotalVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaActionPerformed

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void cbxDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDocumentoItemStateChanged
//        CuentasPorPagarFacturasCabecera cuentasCab = new CuentasPorPagarFacturasCabecera();
//        if(cbxDocumento.getSelectedIndex()==0){
//            cuentasCab.generarSerieCorrelativo("F");
//        } else{
//            cuentasCab.generarSerieCorrelativo("B");
//        }
    }//GEN-LAST:event_cbxDocumentoItemStateChanged

    private void txtTipoDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocumentoKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(cbxTipoDocumento.getSelectedIndex()==1 || cbxTipoDocumento.getSelectedIndex()==3){//DNI
            if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
                evt.consume();
                getToolkit().beep();            
            }
        }
    }//GEN-LAST:event_txtTipoDocumentoKeyTyped

    private void btnGenerarDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDocActionPerformed
        boolean rpta = false;
        CuentasPorPagarFacturasCabecera cabecera = new CuentasPorPagarFacturasCabecera();
        if(!txtTipoDocumento.getText().equals("")){
                    Caja_Ventas.jButton2.doClick();
                    Caja_Ventas.btnNuevo.doClick();
                    
                    CuentasPorPagarFacturasCabecera facturaCabecera = new CuentasPorPagarFacturasCabecera();
                    facturaCabecera.setCodigoEmpresa(TXT_ID_CLIENTE_F.getText());
                    facturaCabecera.setSerie(txtSerieF.getText());
                    facturaCabecera.setCorrelativo(lblNroCorrelativo.getText());
                    facturaCabecera.setTipoOperacion(cbxTipoOperacion.getSelectedItem().toString());
                    facturaCabecera.setFechaEmision(lblFechaEmision.getText());
                    facturaCabecera.setTipoMoneda(cbxTipoMoneda.getSelectedItem().toString());
                    facturaCabecera.setDocumento(cbxDocumento.getSelectedItem().toString());
                    
                    facturaCabecera.setDsctoGlobal(Double.parseDouble(txtDsctoGlobal.getText()));
                    facturaCabecera.setOtrosCargos(Double.parseDouble(txtOtrosCargos.getText()));
                    facturaCabecera.setTotalDscto(Double.parseDouble(txtTotalDscto.getText()));
                    facturaCabecera.setValorVGravada(Double.parseDouble(txtValorVentaGravada.getText()));
                    facturaCabecera.setValorVInafectada(Double.parseDouble(txtValorVentaInafectada.getText()));
                    facturaCabecera.setVentaExonerada(Double.parseDouble(txtVentaExonerada.getText()));
                    facturaCabecera.setMontoIgv(Double.parseDouble(txtMtoIGV.getText()));
                    facturaCabecera.setMontoIsc(Double.parseDouble(txtMtoISC.getText()));
                    facturaCabecera.setOtrosTributos(Double.parseDouble(txtOtrosTributos.getText()));
                    facturaCabecera.setImportaTotalVta(Double.parseDouble(txtImporteTotalVenta.getText()));
                    facturaCabecera.setCod_usu(lblusu.getText());
                    facturaCabecera.setID_DOCUMENTO_FACTURA(Integer.parseInt(lbl_id_cabecera_factura.getText()));
            if(facturaCabecera.mantenimientoCuentasPorPagarFacturasCabecera()){
                if(crearCabecera()){
                    CuentasPorPagarFacturasDetalle facturaDetalle1 = new CuentasPorPagarFacturasDetalle();
                    CuentasPorPagarFacturasCabecera ruc1=new CuentasPorPagarFacturasCabecera();
                    lblId.setText(facturaDetalle1.facturaCabeceraId());
                    String archivo = ruc1.factura_ruc() + "-" + 
                    cbxDocumento.getSelectedItem().toString().charAt(0) + 
                    cbxDocumento.getSelectedItem().toString().charAt(1) + "-" +
                    txtSerieF.getText() + "-" + 
                    lblNroCorrelativo.getText() + ".DET";
                    File crea_archivo = new File(archivo);
                    for (int i = 0; i < tbFacturacion.getRowCount(); i++){      
                        CuentasPorPagarFacturasDetalle facturaDetalle = new CuentasPorPagarFacturasDetalle();
                        facturaDetalle.setCpfId(Integer.parseInt(lblId.getText()));
                        facturaDetalle.setCpdGrav(cbxGravado.getSelectedItem().toString());
                        facturaDetalle.setCpdCodUnidad(cbxCodUnidad.getSelectedItem().toString());
                        facturaDetalle.setCpdCantidad(Integer.parseInt(tbFacturacion.getValueAt(i,3).toString()));
                        facturaDetalle.setNomenclatura(facturaDetalle.codNomen(tbFacturacion.getValueAt(i,0).toString()));
                        facturaDetalle.setCpdCodProdSunat("");
                        facturaDetalle.setCpdValorU(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,2).toString())));
                        facturaDetalle.setCpdDescPorcen(BigDecimal.valueOf(Double.parseDouble("0.00")));
                        facturaDetalle.setCpdDscto(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,6).toString())));
                        facturaDetalle.setCpdIgv(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,5).toString())));
                        facturaDetalle.setCpdAfecIgv(cbxAfecIGV.getSelectedItem().toString()); 
                        facturaDetalle.setCpdIsc(BigDecimal.valueOf(Double.parseDouble("0.00")));
//                        facturaDetalle.setCpdAfecIsc(cbxAfecISC.getSelectedItem().toString()); 
                        facturaDetalle.setCpdAfecIsc(""); 
                        facturaDetalle.setCpdPrecioVenta(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,4).toString())));
                        facturaDetalle.setCpdValorVenta(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,7).toString())));
                        facturaDetalle.setCpdDsctoGlobal(BigDecimal.valueOf(Double.parseDouble(txtDsctoGlobal.getText())));
                        facturaDetalle.setCpdSumOtrosCargos(BigDecimal.valueOf(Double.parseDouble(txtOtrosCargos.getText())));
                        facturaDetalle.setCpdSumIgv(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,5).toString())));
                        facturaDetalle.setCpdTVvInafec(BigDecimal.valueOf(Double.parseDouble(txtValorVentaInafectada.getText())));
                        facturaDetalle.setCpdTVvGrav(BigDecimal.valueOf(Double.parseDouble(txtValorVentaGravada.getText())));
                        facturaDetalle.setCpdTDsctos(BigDecimal.valueOf(Double.parseDouble(txtTotalDscto.getText())));
                        facturaDetalle.setCpdOtrosTribut(BigDecimal.valueOf(Double.parseDouble(txtOtrosTributos.getText())));
                        facturaDetalle.setCpdSumIsc(BigDecimal.valueOf(Double.parseDouble(txtMtoISC.getText())));
                        facturaDetalle.setCpdTVExonen(BigDecimal.valueOf(Double.parseDouble(txtVentaExonerada.getText())));
                        facturaDetalle.setCpdImpTotVtas(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,7).toString())));
                        facturaDetalle.setCodUsu(lblusu.getText());
//                        facturaDetalle.setFormaPago(tbFacturacion.getValueAt(i,7).toString());
                        if(facturaDetalle.mantenimientoCuentasPorPagarFacturasDetalle()){
                            if(crearDetalles(crea_archivo, archivo)){
                                rpta = true;                     
                                

                            } else
                                rpta = false;
                                
                        }
                }
                    if(rpta==true){
                    
                    JOptionPane.showMessageDialog(this, "Factura Electrónica Generada");
                    
                    int guardar = JOptionPane.showConfirmDialog(this, "¿Imprimir Factura Electrónica?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(guardar == 0){ // SELECCION SI
                        dispose();
                        CuentasPorPagarFacturasCabecera cab3 = new CuentasPorPagarFacturasCabecera();
                        CuentasPorPagarSfsRpta rpta2 = new CuentasPorPagarSfsRpta();
                        rpta2.reporteFactura(cab3.idFactura());
                    }else{
                        dispose();
                    }
                  
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al crear la factura");
                    }
            }//fin if crearCabecera    
            }
        } else{
         
        }
    }//GEN-LAST:event_btnGenerarDocActionPerformed

    private void txtBuscarEmpresaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaCaretUpdate
//        Caja_NuevaVenta CNVE = new Caja_NuevaVenta();
//        //        CNV.listarMedicos(txtBuscarEmpresa.getText(),lblServicio.getText(),tb_Empresa);
//        CNVE.listarEmpresa(txtBuscarEmpresa.getText(),tb_Empresa);

    }//GEN-LAST:event_txtBuscarEmpresaCaretUpdate

    private void txtBuscarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpresaActionPerformed

    private void txtBuscarEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpresaKeyPressed

    private void btnBuscarPaciente5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente5ActionPerformed

    }//GEN-LAST:event_btnBuscarPaciente5ActionPerformed

    private void tb_EmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EmpresaMouseClicked
        
        if(evt.getClickCount()==2){
            enviarDatosEmpresa();
        }

    }//GEN-LAST:event_tb_EmpresaMouseClicked

    private void tb_EmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_EmpresaKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
           enviarDatosEmpresa();
        }

    }//GEN-LAST:event_tb_EmpresaKeyPressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        sumatoriaIGV = 0.00;
        sumatoriaTotal = 0.00;
        sumInafectas=0.00;
        sumGravadas=0.00;
        double igv,importeTotalVenta,inaf,grav;
        for (int i = 0; i < tbFacturacion.getRowCount(); i++){      
            sumatoriaIGV = sumatoriaIGV + Double.parseDouble(tbFacturacion.getValueAt(i,5).toString());     
            sumatoriaTotal = sumatoriaTotal + Double.parseDouble(tbFacturacion.getValueAt(i,7).toString());  
            if( tbFacturacion.getValueAt(i,5).toString().equalsIgnoreCase("0.00")){
                sumInafectas= sumInafectas+(Double.parseDouble(tbFacturacion.getValueAt(i,2).toString())
                        *Double.parseDouble(tbFacturacion.getValueAt(i,3).toString())); 
            }else{
                sumGravadas=sumGravadas+(Double.parseDouble(tbFacturacion.getValueAt(i,2).toString())
                        *Double.parseDouble(tbFacturacion.getValueAt(i,3).toString())); 
            }
                
        }
        igv = sumatoriaIGV;
        BigDecimal bd2 = new BigDecimal(igv);
        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
        //
        importeTotalVenta = sumatoriaTotal;
        BigDecimal bdImporte  = new BigDecimal(importeTotalVenta);
        bdImporte = bdImporte.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        inaf = sumInafectas;
        BigDecimal bdI = new BigDecimal(inaf);
        bdI = bdI.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        grav = sumGravadas;
        BigDecimal bdG = new BigDecimal(grav);
        bdG = bdG.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        txtMtoIGV.setText(String.valueOf(bd2));
        txtImporteTotalVenta.setText(String.valueOf(bdImporte));
        txtValorVentaInafectada.setText(String.valueOf(bdI));
        txtValorVentaGravada.setText(String.valueOf(bdG));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void TXT_ID_CLIENTE_FCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TXT_ID_CLIENTE_FCaretUpdate
        MOSTRAR_DATOS_CLIENTE(TXT_ID_CLIENTE_F.getText());
    }//GEN-LAST:event_TXT_ID_CLIENTE_FCaretUpdate

    public void MOSTRAR_DATOS_CLIENTE(String cod){
        String consulta="";
        try {
            consulta="EXEC CUENTAS_POR_PAGAR_EMPRESA_DATOS ?";
            PreparedStatement cmd = F.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                txtTipoDocumento.setText(r.getString(1));
                txtApeNom.setText(r.getString(2));
                txtCorreo.setText(r.getString(3));
                cbxTipoDocumento.setSelectedItem(String.valueOf(r.getString(4)));
                lblCodDomicilioFiscal.setText(r.getString(5));
                lblDNI.setText(r.getString(1));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga datos cliente: " + e.getMessage());
        }
    }
    
    public void inicializar_TB_FACTURADOR(){       
        try {
            
            String titulosb[]={"ITEM","Descripción","Valor U.","Cantidad","Precio",
                "IGV", "Dscto","Total"};
            m=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(m);
            String filasb[]=new String[8];
            tbFacturacion.setModel(m);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(m);
            tbFacturacion.setRowSorter(elQueOrdenasb);
            tbFacturacion.setModel(m);
            
            formato_TB_FACTURADOR();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla FACTURADOR: " + e);
        }      
    }
    
    public void formato_TB_FACTURADOR(){        
            tbFacturacion.getColumnModel().getColumn(0).setPreferredWidth(65);
            tbFacturacion.getColumnModel().getColumn(1).setPreferredWidth(800); 
            tbFacturacion.getColumnModel().getColumn(2).setPreferredWidth(65);
            tbFacturacion.getColumnModel().getColumn(3).setPreferredWidth(65);
            tbFacturacion.getColumnModel().getColumn(4).setPreferredWidth(65);                
            tbFacturacion.getColumnModel().getColumn(5).setPreferredWidth(65); 
            tbFacturacion.getColumnModel().getColumn(6).setPreferredWidth(65);
            tbFacturacion.getColumnModel().getColumn(7).setPreferredWidth(65);
            
            //Ocultar
//            TB_UO_ACTIVIDADES.getColumnModel().getColumn(2).setMinWidth(0);
//            TB_UO_ACTIVIDADES.getColumnModel().getColumn(2).setMaxWidth(0);    
//            TB_UO_ACTIVIDADES.getColumnModel().getColumn(3).setMinWidth(0);
//            TB_UO_ACTIVIDADES.getColumnModel().getColumn(3).setMaxWidth(0);      
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
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Empresa;
    public static javax.swing.JTextField TXT_ID_CLIENTE_F;
    private javax.swing.JButton btnBuscarPaciente5;
    private javax.swing.JButton btnGenerarDoc;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JComboBox cbxAfecIGV;
    private javax.swing.JComboBox cbxAfecISC;
    private javax.swing.JComboBox cbxCodUnidad;
    private javax.swing.JComboBox cbxDocumento;
    private javax.swing.JComboBox cbxGravado;
    private javax.swing.JComboBox cbxTipoDocumento;
    private javax.swing.JComboBox cbxTipoMoneda;
    private javax.swing.JComboBox cbxTipoOperacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JLabel lblCodDomicilioFiscal;
    public static javax.swing.JLabel lblDNI;
    public static javax.swing.JLabel lblDNI3;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblNroCorrelativo;
    public static javax.swing.JLabel lbl_id_cabecera_factura;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT15;
    private javax.swing.JPanel panelCPT16;
    private javax.swing.JPanel panelCPT17;
    private javax.swing.JPanel panelCPT18;
    private javax.swing.JPanel panelCPT19;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT20;
    private javax.swing.JPanel panelCPT21;
    private javax.swing.JPanel panelCPT22;
    private javax.swing.JPanel panelCPT23;
    private javax.swing.JPanel panelCPT24;
    private javax.swing.JScrollPane tablaS;
    public static javax.swing.JTable tbFacturacion;
    private javax.swing.JTable tb_Empresa;
    public static javax.swing.JTextField txtApeNom;
    private javax.swing.JTextField txtBuscarEmpresa;
    public static javax.swing.JTextField txtCorreo;
    public static javax.swing.JTextField txtDsctoGlobal;
    public static javax.swing.JTextField txtImporteTotalVenta;
    public static javax.swing.JTextField txtMtoIGV;
    public static javax.swing.JTextField txtMtoISC;
    public static javax.swing.JTextField txtOtrosCargos;
    public static javax.swing.JTextField txtOtrosTributos;
    public static javax.swing.JLabel txtSerie1;
    public static javax.swing.JLabel txtSerieF;
    public static javax.swing.JTextField txtTipoDocumento;
    public static javax.swing.JTextField txtTotalDscto;
    public static javax.swing.JTextField txtValorVentaGravada;
    public static javax.swing.JTextField txtValorVentaInafectada;
    public static javax.swing.JTextField txtVentaExonerada;
    // End of variables declaration//GEN-END:variables
}
