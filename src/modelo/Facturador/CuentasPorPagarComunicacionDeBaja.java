/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Facturador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Servicios.Conexion;
import Vistas.Facturador.NotasCreditoDebito;
import static Vistas.Facturador.NotasCreditoDebito.lblNroCorrelativo;

/**
 *
 * @author Profe
 */
public class CuentasPorPagarComunicacionDeBaja {
    private Conexion con = new Conexion();
    private Connection cn;
    private int idFactura;
    private String descripcion;
    private String numero;
    private String cod_usu;
    private int ID_DOCUMENTO;
    
       public CuentasPorPagarComunicacionDeBaja()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String generarNroBaja(){
        String cor="";
         try {
            String consulta = "exec SP_CUENTAS_POR_PAGAR_COMUNICACION_BAJA_GENERAR_numero";
            ResultSet r;
            r=getCon().Listar(consulta);
        if(r.next()){
               cor=r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: generarSerieCorrelativo - " + ex.getMessage());
        }
         return cor;
    }
    
  
    
    public boolean mantenimientoCuentasPorPagarComunicacionBaja()
        {
        boolean resp = false;
        try{
            String sql = "EXEC sp_CUENTAS_POR_PAGAR_COMUNICACION_BAJA ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdFactura());
            cmd.setString(2, getDescripcion());
            cmd.setString(3, getNumero());
            cmd.setString(4, getCod_usu());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoCuentasPorPagarFacturasCabecera: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean CuentasPorPagarFacturaEstado(int CPF_ID,String tipo){
         boolean resp = false;
        try{
            String sql = "EXEC sp_CUENTAS_POR_PAGAR_FACTURA_CABECERA_update ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, CPF_ID);
            cmd.setString(2, tipo);
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

    public boolean CAMBIAR_ESTADO_DOCUMENTO_CAJA(int id_documento){
         boolean resp = false;
        try{
            String sql = "EXEC CUENTAS_POR_PAGAR_COMUNICACION_DE_BAJA_ESTADO_CAJA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, id_documento);

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error ESTADO CAJA: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean COMUNICACION_DE_BAJA_MODIFICAR_CAJA()
    {
        boolean resp = false;
        try
        {
            String sql = "exec CUENTAS_POR_PAGAR_COMUNICACION_DE_BAJA_ESTADO_CAJA ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DOCUMENTO());
            cmd.setString(2, getNumero());
            
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error modificar CAJA: " + ex.getMessage());
        }
        return resp;
    }
    
    
    /**
     * @return the con
     */
    public Conexion getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Conexion con) {
        this.con = con;
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
     * @return the idFactura
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * @param idFactura the idFactura to set
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the cod_usu
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param cod_usu the cod_usu to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public int getID_DOCUMENTO() {
        return ID_DOCUMENTO;
    }

    public void setID_DOCUMENTO(int ID_DOCUMENTO) {
        this.ID_DOCUMENTO = ID_DOCUMENTO;
    }

    
    
}
