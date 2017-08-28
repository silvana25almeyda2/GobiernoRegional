/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Facturador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;
import Servicios.Conexion;

public class CuentasPorPagarFacturasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cpdId;
    private int cpfId;
    private String nomenclatura;

    private String cpdGrav;

    private String cpdCodUnidad;

    private Integer cpdCantidad;

    private String cpdCodProdSunat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private BigDecimal cpdValorU;

    private BigDecimal cpdDescPorcen;

    private BigDecimal cpdDscto;

    private BigDecimal cpdIgv;
   
    private String cpdAfecIgv;
 
    private BigDecimal cpdIsc;

    private String cpdAfecIsc;

    private BigDecimal cpdPrecioVenta;

    private BigDecimal cpdValorVenta;

    private BigDecimal cpdDsctoGlobal;

    private BigDecimal cpdSumOtrosCargos;

    private BigDecimal cpdSumIgv;

    private BigDecimal cpdTVvInafec;

    private BigDecimal cpdTVvGrav;

    private BigDecimal cpdTDsctos;

    private BigDecimal cpdOtrosTribut;

    private BigDecimal cpdSumIsc;

    private BigDecimal cpdTVExonen;

    private BigDecimal cpdImpTotVtas;

    private String fechaActu;

    private String horaActu;

    private String nomPc;

    private Character estado;

    private String codUsu;
    private String formaPago;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    public boolean mantenimientoCuentasPorPagarFacturasDetalle()
        {
        boolean resp = false;
        try{
            String sql = " exec CUENTAS_POR_PAGAR_FACTURAS_DETALLE_insertar ?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCpfId());
            cmd.setString(2, getCpdGrav());
            cmd.setString(3, getCpdCodUnidad());
            cmd.setInt(4, getCpdCantidad());
            cmd.setString(5, getNomenclatura());
            cmd.setString(6, getCpdCodProdSunat());
            cmd.setBigDecimal(7, getCpdValorU());
            cmd.setBigDecimal(8, getCpdDescPorcen());
            cmd.setBigDecimal(9, getCpdDscto());
            cmd.setBigDecimal(10, getCpdIgv());
            cmd.setString(11, getCpdAfecIgv());
            cmd.setBigDecimal(12, getCpdIsc());
            cmd.setString(13, getCpdAfecIsc());
            cmd.setBigDecimal(14, getCpdPrecioVenta());
            cmd.setBigDecimal(15, getCpdValorVenta());
            cmd.setBigDecimal(16, getCpdDsctoGlobal());
            cmd.setBigDecimal(17, getCpdSumOtrosCargos());
            cmd.setBigDecimal(18, getCpdSumIgv());
            cmd.setBigDecimal(19, getCpdTVvInafec());
            cmd.setBigDecimal(20, getCpdTVvGrav());
            cmd.setBigDecimal(21, getCpdTDsctos());
            cmd.setBigDecimal(22, getCpdOtrosTribut());
            cmd.setBigDecimal(23, getCpdSumIsc());
            cmd.setBigDecimal(24, getCpdTVExonen());
            cmd.setBigDecimal(25, getCpdImpTotVtas());
            cmd.setString(26, getCodUsu());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoCuentasPorPagarFacturasDetalle: " + ex.getMessage());
        }
        return resp;
    }
    
    public String facturaCabeceraId(){
        String cod="";
        try
        {
            String sql = "exec CUENTAS_POR_PAGAR_FACTURAS_CABECERA_ID";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error facturaCabeceraId: " + ex.getMessage());
        }
        return cod;
    }
    
     public String codNomen(String nomenclatura)
    {
        String cod="";
        try
        {
            String sql = "SELECT ID_CPT FROM CAJA_CPT WHERE NRO_ITEM = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nomenclatura);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error codNomen: " + ex.getMessage());
        }
        return cod;
    }
    
    public CuentasPorPagarFacturasDetalle() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public CuentasPorPagarFacturasDetalle(int cpdId) {
        this.cpdId = cpdId;
    }

    public int getCpdId() {
        return cpdId;
    }

    public void setCpdId(int cpdId) {
        this.cpdId = cpdId;
    }

    public String getCpdGrav() {
        return cpdGrav;
    }

    public void setCpdGrav(String cpdGrav) {
        this.cpdGrav = cpdGrav;
    }

    public String getCpdCodUnidad() {
        return cpdCodUnidad;
    }

    public void setCpdCodUnidad(String cpdCodUnidad) {
        this.cpdCodUnidad = cpdCodUnidad;
    }

    public Integer getCpdCantidad() {
        return cpdCantidad;
    }

    public void setCpdCantidad(Integer cpdCantidad) {
        this.cpdCantidad = cpdCantidad;
    }

    public String getCpdCodProdSunat() {
        return cpdCodProdSunat;
    }

    public void setCpdCodProdSunat(String cpdCodProdSunat) {
        this.cpdCodProdSunat = cpdCodProdSunat;
    }

    public BigDecimal getCpdValorU() {
        return cpdValorU;
    }

    public void setCpdValorU(BigDecimal cpdValorU) {
        this.cpdValorU = cpdValorU;
    }

    public BigDecimal getCpdDescPorcen() {
        return cpdDescPorcen;
    }

    public void setCpdDescPorcen(BigDecimal cpdDescPorcen) {
        this.cpdDescPorcen = cpdDescPorcen;
    }

    public BigDecimal getCpdDscto() {
        return cpdDscto;
    }

    public void setCpdDscto(BigDecimal cpdDscto) {
        this.cpdDscto = cpdDscto;
    }

    public BigDecimal getCpdIgv() {
        return cpdIgv;
    }

    public void setCpdIgv(BigDecimal cpdIgv) {
        this.cpdIgv = cpdIgv;
    }

    public String getCpdAfecIgv() {
        return cpdAfecIgv;
    }

    public void setCpdAfecIgv(String cpdAfecIgv) {
        this.cpdAfecIgv = cpdAfecIgv;
    }

    public BigDecimal getCpdIsc() {
        return cpdIsc;
    }

    public void setCpdIsc(BigDecimal cpdIsc) {
        this.cpdIsc = cpdIsc;
    }

    public String getCpdAfecIsc() {
        return cpdAfecIsc;
    }

    public void setCpdAfecIsc(String cpdAfecIsc) {
        this.cpdAfecIsc = cpdAfecIsc;
    }

    public BigDecimal getCpdPrecioVenta() {
        return cpdPrecioVenta;
    }

    public void setCpdPrecioVenta(BigDecimal cpdPrecioVenta) {
        this.cpdPrecioVenta = cpdPrecioVenta;
    }

    public BigDecimal getCpdValorVenta() {
        return cpdValorVenta;
    }

    public void setCpdValorVenta(BigDecimal cpdValorVenta) {
        this.cpdValorVenta = cpdValorVenta;
    }

    public BigDecimal getCpdDsctoGlobal() {
        return cpdDsctoGlobal;
    }

    public void setCpdDsctoGlobal(BigDecimal cpdDsctoGlobal) {
        this.cpdDsctoGlobal = cpdDsctoGlobal;
    }

    public BigDecimal getCpdSumOtrosCargos() {
        return cpdSumOtrosCargos;
    }

    public void setCpdSumOtrosCargos(BigDecimal cpdSumOtrosCargos) {
        this.cpdSumOtrosCargos = cpdSumOtrosCargos;
    }

    public BigDecimal getCpdSumIgv() {
        return cpdSumIgv;
    }

    public void setCpdSumIgv(BigDecimal cpdSumIgv) {
        this.cpdSumIgv = cpdSumIgv;
    }

    public BigDecimal getCpdTVvInafec() {
        return cpdTVvInafec;
    }

    public void setCpdTVvInafec(BigDecimal cpdTVvInafec) {
        this.cpdTVvInafec = cpdTVvInafec;
    }

    public BigDecimal getCpdTVvGrav() {
        return cpdTVvGrav;
    }

    public void setCpdTVvGrav(BigDecimal cpdTVvGrav) {
        this.cpdTVvGrav = cpdTVvGrav;
    }

    public BigDecimal getCpdTDsctos() {
        return cpdTDsctos;
    }

    public void setCpdTDsctos(BigDecimal cpdTDsctos) {
        this.cpdTDsctos = cpdTDsctos;
    }

    public BigDecimal getCpdOtrosTribut() {
        return cpdOtrosTribut;
    }

    public void setCpdOtrosTribut(BigDecimal cpdOtrosTribut) {
        this.cpdOtrosTribut = cpdOtrosTribut;
    }

    public BigDecimal getCpdSumIsc() {
        return cpdSumIsc;
    }

    public void setCpdSumIsc(BigDecimal cpdSumIsc) {
        this.cpdSumIsc = cpdSumIsc;
    }

    public BigDecimal getCpdTVExonen() {
        return cpdTVExonen;
    }

    public void setCpdTVExonen(BigDecimal cpdTVExonen) {
        this.cpdTVExonen = cpdTVExonen;
    }

    public BigDecimal getCpdImpTotVtas() {
        return cpdImpTotVtas;
    }

    public void setCpdImpTotVtas(BigDecimal cpdImpTotVtas) {
        this.cpdImpTotVtas = cpdImpTotVtas;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    @Override
    public String toString() {
        return "modelos.cuentaCorriente.CuentasPorPagarFacturasDetalle[ cpdId=" + cpdId + " ]";
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
     * @return the cpfId
     */
    public int getCpfId() {
        return cpfId;
    }

    /**
     * @param cpfId the cpfId to set
     */
    public void setCpfId(int cpfId) {
        this.cpfId = cpfId;
    }

    /**
     * @return the nomenclatura
     */
    public String getNomenclatura() {
        return nomenclatura;
    }

    /**
     * @param nomenclatura the nomenclatura to set
     */
    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
}
