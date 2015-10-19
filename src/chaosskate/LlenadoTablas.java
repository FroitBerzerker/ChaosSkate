/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaosskate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class LlenadoTablas {
    Connection conn;
  
public LlenadoTablas(){
    Mysql con = new Mysql();
    conn=con.geConnection();
}

    public void CargarCatalogo(DefaultTableModel Modelo){
        try{
            String sql = "select * from catalogo;";
            try (CallableStatement cmd = conn.prepareCall(sql)) {
                ResultSet rs = cmd.executeQuery();
                while (rs.next()) {
                    Object[] datos = new Object[8];
                    datos[0] = rs.getString("id_producto");
                    datos[1] = rs.getString("nombre");
                    datos[2] = rs.getString("descripcion");
                    datos[3] = rs.getString("marca");
                    datos[4] = rs.getString("precio_compra");
                    datos[5] = rs.getString("precio_venta");
                    datos[6] = rs.getString("proveedor");
                    datos[7] = rs.getString("nombre_inversionistas");
                    Modelo.addRow(datos);
                }
            }
            conn.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
