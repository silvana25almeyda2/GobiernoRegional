/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Principal;

import Servicios.Conexion_siga;
import java.sql.Connection;

/**
 *
 * @author MYS1
 */
public class SIGA {
     private Connection cn;
     
     public SIGA()
    {
        Conexion_siga con = new Conexion_siga();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
     
     
    
}
