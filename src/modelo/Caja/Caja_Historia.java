/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;
import Servicios.Conexion;
import Vistas.Caja.Caja_Clientes;
import Vistas.Caja.Caja_Historia_Clinica;
import Vistas.Principal.Principal_Caja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Administrador
 */
public class Caja_Historia {
DefaultTableModel m;
private Connection cn;
private int ID_CLIENTE;
private String DNI ;
private String NOMBRES ;
private String APELLIDO_PATERNO ;
private String APELLIDO_MATERNO ;
private String FECHA ;
private String HORA ;
private String PC ;
private String USUARIO ;
private String ESTADO;
private String COD_HC;
private String SEXO;
private String FECHA_NAC;
private String DEP_NAC;
private String PROV_NAC;
private String DIS_NAC;
private String OCUPACION;
private String ESTADO_CIVIL;
private String GRUPO_SANGUINEO;
private String RELIGION;
private String TELEFONO;
private String CELULAR;
private String GRADO_INST;
private String NACIONALIDAD;
private String DESP_R;
private String PROV_R;
private String DIST_R;
private String RIESGO;
Conexion con = new Conexion();


    public void codHistoriaClinica(String usu){
        String consulta="";
        try {
            consulta="CAJA_HISTORIA_GENERAR_NUMERO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Historia_Clinica.txtCodigo.setText(r.getString(1)); 
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL GENERAR EL N HC: " + e.getMessage());
        }
    }

    public boolean NUEVA_HISTORIA(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_HISTORIA_CLINICA_NUEVO "
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getDNI());
            cmd.setString(2, getNOMBRES());
            cmd.setString(3, getAPELLIDO_PATERNO());
            cmd.setString(4, getAPELLIDO_MATERNO());
            cmd.setString(5, getUSUARIO());
            cmd.setString(6, getCOD_HC());
            cmd.setString(7, getSEXO());
            cmd.setString(8, getFECHA_NAC());
            cmd.setString(9, getDEP_NAC());
            cmd.setString(10, getPROV_NAC());
            cmd.setString(11, getDIS_NAC());
            cmd.setString(12, getOCUPACION());
            cmd.setString(13, getESTADO_CIVIL());
            cmd.setString(14, getGRUPO_SANGUINEO());
            cmd.setString(15, getRELIGION());
            cmd.setString(16, getTELEFONO());
            cmd.setString(17, getCELULAR());
            cmd.setString(18, getGRADO_INST());
            cmd.setString(19, getNACIONALIDAD());
            cmd.setString(20, getDESP_R());
            cmd.setString(21, getPROV_R());
            cmd.setString(22, getDIST_R());
            cmd.setString(23, getRIESGO());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL REGISTRAR  " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean MODIFICAR_HISTORIA(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_HISTORIA_CLINICA_MODIFICAR "
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_CLIENTE());
            cmd.setString(2, getDNI());
            cmd.setString(3, getNOMBRES());
            cmd.setString(4, getAPELLIDO_PATERNO());
            cmd.setString(5, getAPELLIDO_MATERNO());
            cmd.setString(6, getUSUARIO());
            cmd.setString(7, getCOD_HC());
            cmd.setString(8, getSEXO());
            cmd.setString(9, getFECHA_NAC());
            cmd.setString(10, getDEP_NAC());
            cmd.setString(11, getPROV_NAC());
            cmd.setString(12, getDIS_NAC());
            cmd.setString(13, getOCUPACION());
            cmd.setString(14, getESTADO_CIVIL());
            cmd.setString(15, getGRUPO_SANGUINEO());
            cmd.setString(16, getRELIGION());
            cmd.setString(17, getTELEFONO());
            cmd.setString(18, getCELULAR());
            cmd.setString(19, getGRADO_INST());
            cmd.setString(20, getNACIONALIDAD());
            cmd.setString(21, getDESP_R());
            cmd.setString(22, getPROV_R());
            cmd.setString(23, getDIST_R());
            cmd.setString(24, getRIESGO());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL REGISTRAR  " + ex.getMessage());
        }
        return resp;
    }
    
    public int VALIDAR_DOCUMENTO(String nombre){
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM CAJA_CLIENTES where DNI=?  AND ESTADO='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            //getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error verificacion repetidos: " + ex.getMessage());
        }
        return resultado;
    }
    
    public void LISTAR_PERMISOS(String usu){
        String consulta="";
        try {
            consulta="CAJA_VERIFICAR_NIVEL_USUARIO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Historia_Clinica.lblNivel.setText(r.getString(1)); 
                if(r.getString(2).equals("X")){
                    Caja_Historia_Clinica.lblPermiso.setText("L"); 
                }else   if(r.getString(3).equals("X")){
                    Caja_Historia_Clinica.lblPermiso.setText("E"); 
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR EL PERMISOS: " + e.getMessage());
        }
    }
    
    public void LISTA_CLIENTES(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","DNI","Nº HC","Apellido Paterno","Apellido Materno","Nombres","Sexo",
            "Fecha de Nacimiento","Lugar de Nacimiento","Nacionalidad","Lugar de Residencia","Ocupación",
            "Estado Civil","Grupo Sanguineo","Religión","Telefono","Celular","Grado de Instrucción","Riesgo",
            "DN","PN","DN","DR","PR","DR"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[25];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_HISTORIA_CLINICA_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); 
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                fila[9]=r.getString(10); 
                fila[10]=r.getString(11);
                fila[11]=r.getString(12);
                fila[12]=r.getString(13); 
                fila[13]=r.getString(14);
                fila[14]=r.getString(15);
                fila[15]=r.getString(16); 
                fila[16]=r.getString(17);
                fila[17]=r.getString(18);
                fila[18]=r.getString(19); 
                fila[19]=r.getString(20);
                fila[20]=r.getString(21);
                fila[21]=r.getString(22); 
                fila[22]=r.getString(23);
                fila[23]=r.getString(24);
                fila[24]=r.getString(25);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            Formato(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR H.C. : " + e.getMessage());
        }
    }
    
    public void Formato(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);//DNI
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);//HC
        tabla.getColumnModel().getColumn(3).setPreferredWidth(200);//AP
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);//AM
        tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//NOM
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);//SEX
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);//FN
        tabla.getColumnModel().getColumn(8).setPreferredWidth(150);//LN
        tabla.getColumnModel().getColumn(9).setPreferredWidth(90);//NAC
        tabla.getColumnModel().getColumn(10).setPreferredWidth(150);//LR
        tabla.getColumnModel().getColumn(11).setPreferredWidth(120);//OC
        tabla.getColumnModel().getColumn(12).setPreferredWidth(100);//EC
        tabla.getColumnModel().getColumn(13).setPreferredWidth(80);//GS
        tabla.getColumnModel().getColumn(14).setPreferredWidth(100);//REL
        tabla.getColumnModel().getColumn(15).setPreferredWidth(80);//TEL
        tabla.getColumnModel().getColumn(16).setPreferredWidth(80);//CEL
        tabla.getColumnModel().getColumn(17).setPreferredWidth(100);//INST
        tabla.getColumnModel().getColumn(18).setPreferredWidth(150);//RIES
        tabla.getColumnModel().getColumn(19).setMinWidth(0);
        tabla.getColumnModel().getColumn(19).setMaxWidth(0);
        tabla.getColumnModel().getColumn(20).setMinWidth(0);
        tabla.getColumnModel().getColumn(20).setMaxWidth(0);
        tabla.getColumnModel().getColumn(21).setMinWidth(0);
        tabla.getColumnModel().getColumn(21).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(22).setMinWidth(0);
        tabla.getColumnModel().getColumn(22).setMaxWidth(0);
        tabla.getColumnModel().getColumn(23).setMinWidth(0);
        tabla.getColumnModel().getColumn(23).setMaxWidth(0);
        tabla.getColumnModel().getColumn(24).setMinWidth(0);
        tabla.getColumnModel().getColumn(24).setMaxWidth(0);
        tabla.setRowHeight(40);
    }


    public Caja_Historia(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }
    

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDO_PATERNO() {
        return APELLIDO_PATERNO;
    }

    public void setAPELLIDO_PATERNO(String APELLIDO_PATERNO) {
        this.APELLIDO_PATERNO = APELLIDO_PATERNO;
    }

    public String getAPELLIDO_MATERNO() {
        return APELLIDO_MATERNO;
    }

    public void setAPELLIDO_MATERNO(String APELLIDO_MATERNO) {
        this.APELLIDO_MATERNO = APELLIDO_MATERNO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }

    public String getPC() {
        return PC;
    }

    public void setPC(String PC) {
        this.PC = PC;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getCOD_HC() {
        return COD_HC;
    }

    public void setCOD_HC(String COD_HC) {
        this.COD_HC = COD_HC;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getFECHA_NAC() {
        return FECHA_NAC;
    }

    public void setFECHA_NAC(String FECHA_NAC) {
        this.FECHA_NAC = FECHA_NAC;
    }

    public String getDEP_NAC() {
        return DEP_NAC;
    }

    public void setDEP_NAC(String DEP_NAC) {
        this.DEP_NAC = DEP_NAC;
    }

    public String getPROV_NAC() {
        return PROV_NAC;
    }

    public void setPROV_NAC(String PROV_NAC) {
        this.PROV_NAC = PROV_NAC;
    }

    public String getDIS_NAC() {
        return DIS_NAC;
    }

    public void setDIS_NAC(String DIS_NAC) {
        this.DIS_NAC = DIS_NAC;
    }

    public String getOCUPACION() {
        return OCUPACION;
    }

    public void setOCUPACION(String OCUPACION) {
        this.OCUPACION = OCUPACION;
    }

    public String getESTADO_CIVIL() {
        return ESTADO_CIVIL;
    }

    public void setESTADO_CIVIL(String ESTADO_CIVIL) {
        this.ESTADO_CIVIL = ESTADO_CIVIL;
    }

    public String getGRUPO_SANGUINEO() {
        return GRUPO_SANGUINEO;
    }

    public void setGRUPO_SANGUINEO(String GRUPO_SANGUINEO) {
        this.GRUPO_SANGUINEO = GRUPO_SANGUINEO;
    }

    public String getRELIGION() {
        return RELIGION;
    }

    public void setRELIGION(String RELIGION) {
        this.RELIGION = RELIGION;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getCELULAR() {
        return CELULAR;
    }

    public void setCELULAR(String CELULAR) {
        this.CELULAR = CELULAR;
    }

    public String getGRADO_INST() {
        return GRADO_INST;
    }

    public void setGRADO_INST(String GRADO_INST) {
        this.GRADO_INST = GRADO_INST;
    }

    public String getNACIONALIDAD() {
        return NACIONALIDAD;
    }

    public void setNACIONALIDAD(String NACIONALIDAD) {
        this.NACIONALIDAD = NACIONALIDAD;
    }

    public String getDESP_R() {
        return DESP_R;
    }

    public void setDESP_R(String DESP_R) {
        this.DESP_R = DESP_R;
    }

    public String getPROV_R() {
        return PROV_R;
    }

    public void setPROV_R(String PROV_R) {
        this.PROV_R = PROV_R;
    }

    public String getDIST_R() {
        return DIST_R;
    }

    public void setDIST_R(String DIST_R) {
        this.DIST_R = DIST_R;
    }

    public String getRIESGO() {
        return RIESGO;
    }

    public void setRIESGO(String RIESGO) {
        this.RIESGO = RIESGO;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
}
