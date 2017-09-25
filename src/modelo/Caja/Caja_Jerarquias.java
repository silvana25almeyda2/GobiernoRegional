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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Servicios.Conexion;
import Vistas.Caja.Caja_Jerarquia;
/**
 *
 * @author MYS1
 */
public class Caja_Jerarquias {
private Connection cn;
DefaultTableModel m;
//CUENTA 2
private int cod_jerar_forma_pago ;
private String descri_forma_pago;
private int relacion_forma_pago ;
private int nivel_forma_pago ;
private String nom_usu;
Conexion con = new Conexion(); 

public boolean NuevaJerarquia()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_FORMA_PAGO_NUEVO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCod_jerar_forma_pago());
            cmd.setString(1, getDescri_forma_pago());
            cmd.setInt(2, getRelacion_forma_pago());
            cmd.setInt(3, getNivel_forma_pago());
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
            System.out.println("ERROR AL INSERTAR : " + ex.getMessage());
        }
        return resp;
    }
    
    
//public String idCJ(){//muestra el codigo
//        String id = "";
//        try {
//            String consulta = "exec Caja_jerarquia_ID";
//            ResultSet r;
//            r=con.Listar(consulta);
//        if(r.next()){
//               id = r.getString(1);
//        }
//        }catch(Exception ex){
//            System.out.println("Error " + ex.getMessage());
//        }
//        return id;
//    }

public boolean Caja_Jerarquia_MODIFICAR(){
        boolean resp = false;
        try
        {
            String sql = "Exec CAJA_FORMA_PAGO_MODIFICAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCod_jerar_forma_pago());
            cmd.setString(2, getDescri_forma_pago());
            cmd.setInt(3, getRelacion_forma_pago());
            cmd.setInt(4, getNivel_forma_pago());
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
          System.out.println("ERROR AL MODIFICAR " + ex.getMessage());
        }
        return resp;
    }

public String BuscarC(String codigo)
    {
        String cod="";
        try
        {
            String sql = "Exec Caja_Jerarquia_Codigo ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, codigo);
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
            System.out.println("ErrorDDDD: " + ex.getMessage());
        }
        return cod;
    }

public boolean eliminarjerarquia(){
        boolean resp = false;
        try
        {
            String sql = "EXEC CAJA_FORMA_PAGO_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCod_jerar_forma_pago());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("ERROR AL ELIMINAR: " + ex.getMessage());
        }
        return resp;
    }

public String codTipo(String tipo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_jerar_forma_pago \n" +
                        "FROM CAJA_JERARQUIA_FORMA_PAGO\n" +
                        "WHERE nom_forma_pago = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, tipo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codDistrito: " + ex.getMessage());
        }
        return cod;
    }
        public void LISTARNIVEL0(JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Descripcion","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec Caja_FORMA_PAGO_RELACION0 ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, Servicio);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            FORMATO0(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar NIVEL 0 " + e.getMessage());
        }
    }
        
        public void LISTARNIVEL1(String NIVEL0 ,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Descripcion","ID","R"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec Caja_FORMA_PAGO_RELACION1 ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, NIVEL0);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            FORMATO(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar NIVEL 1" + e.getMessage());
        }
    }
        public void LISTARNIVEL2(String NIVEL0 ,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Descripcion","ID","R"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec Caja_FORMA_PAGO_RELACION2 ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, NIVEL0);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            FORMATO(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar NIVEL2" + e.getMessage());
        }
    }
        
      
      public void FORMATO(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla.getColumnModel().getColumn(2).setMinWidth(0);
            tabla.getColumnModel().getColumn(2).setMaxWidth(0);
            tabla.setRowHeight(40);
        
    }
      public void FORMATO0(JTable tabla){

            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setMinWidth(0);
            tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.setRowHeight(40);
        
    }


      public void LISTAR_PERMISOS_ITEM(String usu){
        String consulta="";
        try {
            consulta="EXEC CAJA_VERIFICAR_NIVEL_USUARIO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Jerarquia.lblNivel1.setText(r.getString(1)); 
                if(r.getString(2).equals("X")){
                    Caja_Jerarquia.lblPermiso.setText("L"); 
                }else   if(r.getString(3).equals("X")){
                    Caja_Jerarquia.lblPermiso.setText("E"); 
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error AL CARGAR EL PERMISOS: " + e.getMessage());
        }
    }


 public Caja_Jerarquias(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    public void setCod_jerar_forma_pago(int cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }

    public String getDescri_forma_pago() {
        return descri_forma_pago;
    }

    public void setDescri_forma_pago(String descri_forma_pago) {
        this.descri_forma_pago = descri_forma_pago;
    }

    public int getRelacion_forma_pago() {
        return relacion_forma_pago;
    }

    public void setRelacion_forma_pago(int relacion_forma_pago) {
        this.relacion_forma_pago = relacion_forma_pago;
    }

    public int getNivel_forma_pago() {
        return nivel_forma_pago;
    }

    public void setNivel_forma_pago(int nivel_forma_pago) {
        this.nivel_forma_pago = nivel_forma_pago;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
 
}
