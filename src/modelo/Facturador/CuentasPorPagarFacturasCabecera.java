
package modelo.Facturador;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Servicios.Conexion;
import Vistas.Facturador.Facturador;
import static Vistas.Facturador.Facturador.lblNroCorrelativo;
import static Vistas.Facturador.Facturador.txtSerie;


public class CuentasPorPagarFacturasCabecera implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private String serie;
    private String correlativo;
    private String tipoOperacion;
    private String fechaEmision;
    private String tipoMoneda;
    private String documento;
    private int actoMedico;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String cod_usu;
    private String codigoEmpresa;
    private double dsctoGlobal;
    private double otrosCargos;
    private double totalDscto;
    private double valorVGravada;
    private double valorVInafectada;
    private double ventaExonerada;
    private double montoIgv;
    private double montoIsc;
    private double otrosTributos;
    private double importaTotalVta;
    private String placa;
    private String contratante;
    private String poliza;
    private String cartaGarantia;
    private String dni;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    public void generarSerieCorrelativo(String documento){
        try {
            String consulta = "exec CUENTAS_POR_PAGAR_GENERAR_SERIE_CORRELATIVO '"+documento+"'";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               txtSerie.setText(r.getString(1));
               lblNroCorrelativo.setText(r.getString(2));
        }
        }catch(Exception ex){
            System.out.println("Error: generarSerieCorrelativo - " + ex.getMessage());
        }
    }

    public boolean mantenimientoCuentasPorPagarFacturasCabecera()
        {
        boolean resp = false;
        try{
            String sql = " exec CUENTAS_POR_PAGAR_FACTURAS_CABECERA_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCodigoEmpresa());
            cmd.setString(2, getSerie());
            cmd.setString(3, getCorrelativo());
            cmd.setString(4, getTipoOperacion());
            cmd.setString(5, getFechaEmision());
            cmd.setString(6, getTipoMoneda());
            cmd.setString(7, getDocumento());
            cmd.setDouble(8, getDsctoGlobal());
            cmd.setDouble(9, getOtrosCargos());
            cmd.setDouble(10, getTotalDscto());
            cmd.setDouble(11, getValorVGravada());
            cmd.setDouble(12, getValorVInafectada());
            cmd.setDouble(13, getVentaExonerada());
            cmd.setDouble(14, getMontoIgv());
            cmd.setDouble(15, getMontoIsc());
            cmd.setDouble(16, getOtrosTributos());
            cmd.setDouble(17, getImportaTotalVta());
            cmd.setString(18, getCod_usu());
            
            
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
    
    public String actoMedicoID(String actoMedico){
        String cod="";
        try
        {
            String sql = "SELECT ID_ACTOMEDICO\n" +
                         "FROM CAJA_ACTO_MEDICO\n" +
                         "WHERE NUM_ACTOMEDICO = "+ actoMedico+ "";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("actoMedicoID: " + ex.getMessage());
        }
        return cod;
    }
    
    public void historiaClinicaMotivo(String busqueda){
        String consulta="";
        try {
            consulta="CAJA_EMPRESA_JERARQUIA_PAGO_BUSCAR_RUC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Facturador.txtApeNom.setText(r.getString(1)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios CABECERA LISTAR  " + e.getMessage());
        }
    } 
    
    public void formatoTablaVentas(JTable tabla){
//        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//CODIGO
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna1 = tabla.getColumnModel().getColumn(1);
            columna1.setMaxWidth(0);
            columna1.setMinWidth(0);
            columna1.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna2 = tabla.getColumnModel().getColumn(2);
            columna2.setMaxWidth(0);
            columna2.setMinWidth(0);
            columna2.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna3 = tabla.getColumnModel().getColumn(3);
            columna3.setMaxWidth(0);
            columna3.setMinWidth(0);
            columna3.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(25);
    }
    
    public void cargarVentas(String busqueda,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"","","","",
            "Cantidad","Código","Descripción","Precio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_DOCUMENTO_CABECERA_FACTURADOR_BUSCAR_ACTO_MEDICO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaVentas(tabla);
        } catch (Exception e) {
            System.out.println("Error: cargarVentas: " + e.getMessage());
        }
    }

    public String idFactura(){
        String id = "";
        try {
            String consulta = "EXEC CUENTAS_POR_PAGAR_FACTURAS_CABECERA_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: idFactura " + ex.getMessage());
        }
        return id;
    }
    
    public CuentasPorPagarFacturasCabecera() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "modelos.cuentaCorriente.Facturador[ id=" + id + " ]";
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
     * @return the serieCorrelativo
     */
   
    /**
     * @return the tipoOperacion
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * @param tipoOperacion the tipoOperacion to set
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the tipoMoneda
     */
    public String getTipoMoneda() {
        return tipoMoneda;
    }

    /**
     * @param tipoMoneda the tipoMoneda to set
     */
    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the actoMedico
     */
    public int getActoMedico() {
        return actoMedico;
    }

    /**
     * @param actoMedico the actoMedico to set
     */
    public void setActoMedico(int actoMedico) {
        this.actoMedico = actoMedico;
    }

    /**
     * @return the fecha_actu
     */
    public String getFecha_actu() {
        return fecha_actu;
    }

    /**
     * @param fecha_actu the fecha_actu to set
     */
    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    /**
     * @return the hora_actu
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param hora_actu the hora_actu to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    /**
     * @return the nom_pc
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param nom_pc the nom_pc to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * @return the codigoEmpresa
     */
    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    /**
     * @param codigoEmpresa the codigoEmpresa to set
     */
    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    /**
     * @return the dsctoGlobal
     */
    public double getDsctoGlobal() {
        return dsctoGlobal;
    }

    /**
     * @param dsctoGlobal the dsctoGlobal to set
     */
    public void setDsctoGlobal(double dsctoGlobal) {
        this.dsctoGlobal = dsctoGlobal;
    }

    /**
     * @return the otrosCargos
     */
    public double getOtrosCargos() {
        return otrosCargos;
    }

    /**
     * @param otrosCargos the otrosCargos to set
     */
    public void setOtrosCargos(double otrosCargos) {
        this.otrosCargos = otrosCargos;
    }

    /**
     * @return the totalDscto
     */
    public double getTotalDscto() {
        return totalDscto;
    }

    /**
     * @param totalDscto the totalDscto to set
     */
    public void setTotalDscto(double totalDscto) {
        this.totalDscto = totalDscto;
    }

    /**
     * @return the valorVGravada
     */
    public double getValorVGravada() {
        return valorVGravada;
    }

    /**
     * @param valorVGravada the valorVGravada to set
     */
    public void setValorVGravada(double valorVGravada) {
        this.valorVGravada = valorVGravada;
    }

    /**
     * @return the valorVInafectada
     */
    public double getValorVInafectada() {
        return valorVInafectada;
    }

    /**
     * @param valorVInafectada the valorVInafectada to set
     */
    public void setValorVInafectada(double valorVInafectada) {
        this.valorVInafectada = valorVInafectada;
    }

    /**
     * @return the ventaExonerada
     */
    public double getVentaExonerada() {
        return ventaExonerada;
    }

    /**
     * @param ventaExonerada the ventaExonerada to set
     */
    public void setVentaExonerada(double ventaExonerada) {
        this.ventaExonerada = ventaExonerada;
    }

    /**
     * @return the montoIgv
     */
    public double getMontoIgv() {
        return montoIgv;
    }

    /**
     * @param montoIgv the montoIgv to set
     */
    public void setMontoIgv(double montoIgv) {
        this.montoIgv = montoIgv;
    }

    /**
     * @return the montoIsc
     */
    public double getMontoIsc() {
        return montoIsc;
    }

    /**
     * @param montoIsc the montoIsc to set
     */
    public void setMontoIsc(double montoIsc) {
        this.montoIsc = montoIsc;
    }

    /**
     * @return the otrosTributos
     */
    public double getOtrosTributos() {
        return otrosTributos;
    }

    /**
     * @param otrosTributos the otrosTributos to set
     */
    public void setOtrosTributos(double otrosTributos) {
        this.otrosTributos = otrosTributos;
    }

    /**
     * @return the importaTotalVta
     */
    public double getImportaTotalVta() {
        return importaTotalVta;
    }

    /**
     * @param importaTotalVta the importaTotalVta to set
     */
    public void setImportaTotalVta(double importaTotalVta) {
        this.importaTotalVta = importaTotalVta;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the contratante
     */
    public String getContratante() {
        return contratante;
    }

    /**
     * @param contratante the contratante to set
     */
    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    /**
     * @return the poliza
     */
    public String getPoliza() {
        return poliza;
    }

    /**
     * @param poliza the poliza to set
     */
    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    /**
     * @return the cartaGarantia
     */
    public String getCartaGarantia() {
        return cartaGarantia;
    }

    /**
     * @param cartaGarantia the cartaGarantia to set
     */
    public void setCartaGarantia(String cartaGarantia) {
        this.cartaGarantia = cartaGarantia;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    
}
