/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Personal;

import Servicios.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MYS3
 */
public class CLS_Personal {
    private Connection cn;
    private int cod_per;
    private String DNI_per;
    private String ape_pat_per;
    private String ape_mat_per;
    private String nombres_per;
    private String fec_nac_per;
    private String sexo;
    private String cod_dis;
    private int UE_ID;
    private String direccion_per;
    private String telefono;
    private String celular;
    private String correo_electronico;
    private String estado_civil;
    private String Usu_Codigo;
    private String estado_personal;
    
    public CLS_Personal()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public String PERSONAL_COD_DEPARTAMENTO(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT cod_dep FROM SISTEMA_DEPARTAMENTO WHERE nombre_departamento = ?";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod departamento: " + ex.getMessage());
        }
        return cod;
    }
    
    public String PERSONAL_COD_provincia(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT cod_prov FROM SISTEMA_PROVINCIA WHERE nombre_provincia = ?";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod PROVINCIA: " + ex.getMessage());
        }
        return cod;
    }
    
    public String PERSONAL_COD_DISTRITO(String descrip, String cod_prov){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT cod_dis FROM SISTEMA_DISTRITO WHERE nombre_distrito = ? AND cod_prov = ?";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            cmd.setString(2, cod_prov);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod DISTRITO: " + ex.getMessage());
        }
        return cod;
    }
    
    public String PERSONAL_COD_UNIDAD_EJECUTORA(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT UE_ID FROM SISTEMA_UNIDAD_EJECUTORA WHERE UE_DESC = ?";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod DISTRITO: " + ex.getMessage());
        }
        return cod;
    }
    
    public String PERSONAL_COD_USUARIO(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT Usu_Codigo FROM SISTEMA_USUARIO WHERE Usu_Usuario = ? ";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod USUARIO PERSONAL: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean PERSONAL_INSERTAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_INSERTAR ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getDNI_per());
            cmd.setString(2, getApe_pat_per());
            cmd.setString(3, getApe_mat_per());
            cmd.setString(4, getNombres_per());
            cmd.setString(5, getFec_nac_per());
            cmd.setString(6, getSexo());
            cmd.setString(7, getCod_dis());
            cmd.setInt(8, getUE_ID());
            cmd.setString(9, getDireccion_per());
            cmd.setString(10, getTelefono());
            cmd.setString(11, getCelular());
            cmd.setString(12, getCorreo_electronico());
            cmd.setString(13, getEstado_civil());
            cmd.setString(14, getUsu_Codigo());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error guardar personal: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean PERSONAL_ELIMINAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCod_per());
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error ELIMINAR PERSONAL: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean PERSONAL_MODIFICAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_MODIFICAR ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCod_per());
            cmd.setString(2, getDNI_per());
            cmd.setString(3, getApe_pat_per());
            cmd.setString(4, getApe_mat_per());
            cmd.setString(5, getNombres_per());
            cmd.setString(6, getFec_nac_per());
            cmd.setString(7, getSexo());
            cmd.setString(8, getCod_dis());
            cmd.setInt(9, getUE_ID());
            cmd.setString(10, getDireccion_per());
            cmd.setString(11, getTelefono());
            cmd.setString(12, getCelular());
            cmd.setString(13, getCorreo_electronico());
            cmd.setString(14, getEstado_civil());
            cmd.setString(15, getUsu_Codigo());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error modificar personal: " + ex.getMessage());
        }
        return resp;
    }
    
    public int DNI(String DNI)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT DNI_PER FROM PERSONAL where DNI_per=? AND estado_personal = 'A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, DNI);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getCod_per() {
        return cod_per;
    }

    public void setCod_per(int cod_per) {
        this.cod_per = cod_per;
    }

    public String getDNI_per() {
        return DNI_per;
    }

    public void setDNI_per(String DNI_per) {
        this.DNI_per = DNI_per;
    }

    public String getApe_pat_per() {
        return ape_pat_per;
    }

    public void setApe_pat_per(String ape_pat_per) {
        this.ape_pat_per = ape_pat_per;
    }

    public String getApe_mat_per() {
        return ape_mat_per;
    }

    public void setApe_mat_per(String ape_mat_per) {
        this.ape_mat_per = ape_mat_per;
    }

    public String getNombres_per() {
        return nombres_per;
    }

    public void setNombres_per(String nombres_per) {
        this.nombres_per = nombres_per;
    }

    public String getFec_nac_per() {
        return fec_nac_per;
    }

    public void setFec_nac_per(String fec_nac_per) {
        this.fec_nac_per = fec_nac_per;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCod_dis() {
        return cod_dis;
    }

    public void setCod_dis(String cod_dis) {
        this.cod_dis = cod_dis;
    }

    public int getUE_ID() {
        return UE_ID;
    }

    public void setUE_ID(int UE_ID) {
        this.UE_ID = UE_ID;
    }

    public String getDireccion_per() {
        return direccion_per;
    }

    public void setDireccion_per(String direccion_per) {
        this.direccion_per = direccion_per;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getUsu_Codigo() {
        return Usu_Codigo;
    }

    public void setUsu_Codigo(String Usu_Codigo) {
        this.Usu_Codigo = Usu_Codigo;
    }

    public String getEstado_personal() {
        return estado_personal;
    }

    public void setEstado_personal(String estado_personal) {
        this.estado_personal = estado_personal;
    }
    
    
    
    
    
}
