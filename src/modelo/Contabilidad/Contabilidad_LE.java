/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Contabilidad;

import Servicios.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MYS1
 */
public class Contabilidad_LE {
    private Conexion con = new Conexion();
    private Connection cn;
    
    public Contabilidad_LE()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String factura_ruc(){
        String ruc = "";
        try {
            String consulta = "SELECT TOP 1 RUC FROM SISTEMA_UNIDAD_EJECUTORA";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               ruc = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: idFactura " + ex.getMessage());
        }
        return ruc;
    }

    public boolean LE_CAMBIAR_ESTADO_DOCUMENTO_CAJA(int id_documento){
         boolean resp = false;
        try{
            String sql = "EXEC LIBRO_ELECTRONICO_CAMBIAR_ESTADO ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, id_documento);

            if(!cmd.execute())
            {
                resp = true;
                System.out.println("estado cambiado");
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error ESTADO CAJA LE: " + ex.getMessage());
        }
        return resp;
    }
    
    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
    
}
