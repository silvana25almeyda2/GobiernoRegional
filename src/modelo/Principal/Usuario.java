/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Servicios.Conexion;
import Vistas.Principal.Principal;

/**
 *
 * @author silvana
 */
public class Usuario {
    private Connection cn;
   private int codigo;
private String cod_modulo;
private String cod_per;
private String Usu_Usuario;
private String Usu_Contrasena;
private String Usu_Pregunta;
private String Usu_Respuesta;
private String NIVEL;
private String L ;
private String E ;

public boolean guardarUsuario()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_Insertar ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_modulo());
            cmd.setString(2, getCod_per());
            cmd.setString(3, getUsu_Usuario());
            cmd.setString(4, getUsu_Contrasena());
            cmd.setString(5, getUsu_Pregunta());
            cmd.setString(6, getUsu_Respuesta());
            //
            cmd.setString(7, getNIVEL());
            cmd.setString(8, getL());
            cmd.setString(9, getE());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
         
         
    public boolean modificarUsuario()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_Modificar ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCodigo());
            cmd.setString(2, getCod_modulo());
            cmd.setString(3, getCod_per());
            cmd.setString(4, getUsu_Usuario());
            cmd.setString(5, getUsu_Contrasena());
            cmd.setString(6, getUsu_Pregunta());
            cmd.setString(7, getUsu_Respuesta());
            //
            cmd.setString(8, getNIVEL());
            cmd.setString(9, getL());
            cmd.setString(10, getE());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    public boolean eliminarUsuario()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCodigo());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
   
    
    
    public String codTipo(String tipo,String t)
    {
        String cod="";
        try
        {
            String sql = "exec SP_USUARIO_VALIDAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, tipo);
            cmd.setString(2, t);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    public int ver_usuario(String usu,String tipo)
    {
        int resultado=0;
        try
        {
            String sql = "exec SP_USUARIO_VALIDAR ?,? ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            cmd.setString(2, tipo);
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
    
    public String codigoValidacion(String usu)
    {
        String cod="";
        try
        {
            String sql = "SELECT Usu_Codigo FROM SISTEMA_USUARIO where usu_usuario=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    
    public String Pregunta(String usu)
    {
        String cod="";
        try
        {
            String sql = "SELECT UPPER(usu_pregunta) FROM SISTEMA_USUARIO where usu_usuario=? AND USU_ESTADO='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    public String Respuesta(String usu,String pregunta)
    {
        String con="";
        try
        {
            String sql = "SELECT usu_respuesta FROM SISTEMA_USUARIO where usu_usuario=? and usu_pregunta=? AND USU_ESTADO='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            cmd.setString(2, pregunta);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               con = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return con;
    }
    
    public boolean CAMBIO_CONTRASENA(){
        boolean resp = false;
        try{
            String sql = "exec SISTEMA_CAMBIAR_CONTRASEÑA ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getUsu_Usuario());
            cmd.setString(2, getUsu_Contrasena());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL CAMBIAR LA CONTRASEÑA  " + ex.getMessage());
        }
        return resp;
    }
    
    public String Contrasena(String usu,String pregunta)
    {
        String con="";
        try
        {
            String sql = "SELECT dbo.fnLeeClave(Usu_Contrasena) FROM SISTEMA_USUARIO where usu_usuario=? and usu_pregunta=? AND USU_ESTADO='A'" ;
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            cmd.setString(2, pregunta);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               con = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return con;
    }
    
    //validar lblUsuario y Boton
    public String codAdmin()
        {
        String cod="";
        try
        {
            String sql = "SELECT tipoUsu_Codigo FROM tipo_Usuario where tipoUsu_tipo='Administrador'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codigoAdminVali(String usu)
    {
        String cod="";
        try
        {
            String sql = "SELECT tipoUsu_Codigo FROM Usuario where usu_usuario=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    public int VALIDAR_NOMBRE_USU(String NOM_USU)
    {
        int resultado=0;
        try
        {
            String sql = "select usu_usuario from sistema_usuario where usu_usuario =? and USU_estado='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, NOM_USU);
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
            System.out.println("Error VALIDAR_NOM_USU: " + ex.getMessage());
        }
        return resultado;
    }
    
    
//    public void CAJA_CONTRASEÑA(String usu){
//        try {
//            String consulta = "select dbo.fnLeeClave(usu_contrasena) from SISTEMA_USUARIO where Usu_Usuario= ?";
//            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, usu);
//            ResultSet r= cmd.executeQuery();
//        if(r.next()){
//               Principal.lblCont.setText(r.getString(1));
//        }
//        }catch(Exception ex){
//            System.out.println("ERROR AL CARGAR USUARIO " + ex.getMessage());
//        }
//    }
//    
    public int VALIDAR_CONTRASEÑA(String usu,String pass){
        int resultado=0;
        try
        {
            String sql = "select * from SISTEMA_USUARIO where Usu_Usuario=? and dbo.fnLeeClave(usu_contrasena)=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            cmd.setString(2, pass);
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
     
    
    public Usuario()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the cod_modulo
     */
    public String getCod_modulo() {
        return cod_modulo;
    }

    /**
     * @param cod_modulo the cod_modulo to set
     */
    public void setCod_modulo(String cod_modulo) {
        this.cod_modulo = cod_modulo;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    /**
     * @return the Usu_Usuario
     */
    public String getUsu_Usuario() {
        return Usu_Usuario;
    }

    /**
     * @param Usu_Usuario the Usu_Usuario to set
     */
    public void setUsu_Usuario(String Usu_Usuario) {
        this.Usu_Usuario = Usu_Usuario;
    }

    /**
     * @return the Usu_Contrasena
     */
    public String getUsu_Contrasena() {
        return Usu_Contrasena;
    }

    /**
     * @param Usu_Contrasena the Usu_Contrasena to set
     */
    public void setUsu_Contrasena(String Usu_Contrasena) {
        this.Usu_Contrasena = Usu_Contrasena;
    }

    /**
     * @return the Usu_Pregunta
     */
    public String getUsu_Pregunta() {
        return Usu_Pregunta;
    }

    /**
     * @param Usu_Pregunta the Usu_Pregunta to set
     */
    public void setUsu_Pregunta(String Usu_Pregunta) {
        this.Usu_Pregunta = Usu_Pregunta;
    }

    /**
     * @return the Usu_Respuesta
     */
    public String getUsu_Respuesta() {
        return Usu_Respuesta;
    }

    /**
     * @param Usu_Respuesta the Usu_Respuesta to set
     */
    public void setUsu_Respuesta(String Usu_Respuesta) {
        this.Usu_Respuesta = Usu_Respuesta;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNIVEL() {
        return NIVEL;
    }

    public void setNIVEL(String NIVEL) {
        this.NIVEL = NIVEL;
    }

    public String getL() {
        return L;
    }

    public void setL(String L) {
        this.L = L;
    }

    public String getE() {
        return E;
    }

    public void setE(String E) {
        this.E = E;
    }
    
    

}
