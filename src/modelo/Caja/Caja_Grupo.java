/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Caja;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Servicios.Conexion;
/**
 *
 * @author Ricardo
 */
public class Caja_Grupo {
private Connection cn;
private String cod_grupo_nomen_aten;  
private String codigo_grupo;
private String nombre_grupo_nomen;  
private String nom_usu;  
private String AFECTO;
Conexion con = new Conexion();  

public boolean nuevoGrupoNomenclatura(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_GRUPOS_NUEVO "
                        + "?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCodigo_grupo());
            cmd.setString(2, getNombre_grupo_nomen());
            cmd.setString(3, getAFECTO());
            cmd.setString(4, getNom_usu());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }


public boolean modificarGrupoNomenclatura(){
        boolean resp = false;
        try
        {
            String sql = "EXEC CAJA_GRUPOS_ACTUALIZAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_grupo_nomen_aten());
            cmd.setString(2, getCodigo_grupo());
            cmd.setString(3, getNombre_grupo_nomen());
            cmd.setString(4, getAFECTO());
            cmd.setString(5, getNom_usu());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }

 public boolean eliminarCajaNomenclatura(){
        boolean resp = false;
        try
        {
            String sql = "EXEC CAJA_GRUPOS_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_grupo_nomen_aten());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminar: " + ex.getMessage());
        }
        return resp;
    }
 
    public String Filtrar(){
        String cod = "";
        try {
            String consulta = "exec Caja_Cta1_LISTAR";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               cod = r.getString(1);
        }
        }catch(Exception ex){
        }
        return cod;
    }
    

    
    public Caja_Grupo(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCod_grupo_nomen_aten() {
        return cod_grupo_nomen_aten;
    }

    public void setCod_grupo_nomen_aten(String cod_grupo_nomen_aten) {
        this.cod_grupo_nomen_aten = cod_grupo_nomen_aten;
    }

    public String getCodigo_grupo() {
        return codigo_grupo;
    }

    public void setCodigo_grupo(String codigo_grupo) {
        this.codigo_grupo = codigo_grupo;
    }

    public String getNombre_grupo_nomen() {
        return nombre_grupo_nomen;
    }

    public void setNombre_grupo_nomen(String nombre_grupo_nomen) {
        this.nombre_grupo_nomen = nombre_grupo_nomen;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getAFECTO() {
        return AFECTO;
    }

    public void setAFECTO(String AFECTO) {
        this.AFECTO = AFECTO;
    }

    

}
