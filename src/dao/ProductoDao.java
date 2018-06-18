package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;

public class ProductoDao{
    
    private static final String SQL_INSERT = "INSERT INTO productos (nombre, codigo, tipo, cantidad, precio, disponibilidad) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET nombre = ?, tipo = ?, cantidad = ?, precio = ?, disponibilidad = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE codigo=?";
    private static final String SQL_READ = "SELECT * FROM productos WHERE codigo=?";
    private static final String SQL_READALL = "SELECT * FROM productos";

    public static final Conexion con = Conexion.conectar();

    public boolean create(Producto p) {
        
        PreparedStatement ps;
        
        try{
            
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getTipo());
            ps.setInt(4, p.getCantidad());
            ps.setFloat(5, p.getPrecio());
            ps.setBoolean(6, p.getDisponibilidad());
            
            
            if(ps.executeUpdate() > 0){
                return true;
            }  
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerracConexion();
        }
        
        return false;
        
    }

    public boolean delete(Object key) {
        
        PreparedStatement ps;
        
        try{
            
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            
            if(ps.executeUpdate() > 0){
                return true;
            }            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerracConexion();
        }
        
        return false;
        
    }

    public boolean update(Producto p) {
        PreparedStatement ps;
        
        try{
            
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setInt(3, p.getCantidad());
            ps.setFloat(4, p.getPrecio());
            ps.setBoolean(5, p.getDisponibilidad());
            ps.setString(6, p.getCodigo());
            
            if(ps.executeUpdate() > 0){
                return true;
            }            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerracConexion();
        }
        
        return false;

    }
    
    
    public Producto read(Object key) {
        
        Producto p = null;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while(rs.next()){

                p = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("codigo"), rs.getString("tipo"), rs.getInt("cantidad"), rs.getFloat("precio"), rs.getBoolean("disponibilidad"));

            }
            rs.close();

            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerracConexion();
        }
        
        return p;
    }

    public ArrayList<Producto> readAll() {
    
        ArrayList<Producto> all = new ArrayList<>();
        Statement s;
        ResultSet rs;
        
        try{
            
            s = con.getCnx().prepareStatement(SQL_READALL);
          
            rs = s.executeQuery(SQL_READALL);
            
            while(rs.next()){
                
                all.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("codigo"), rs.getString("tipo"), rs.getInt("cantidad"), rs.getFloat("precio"), rs.getBoolean("disponibilidad")));

            }
            rs.close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerracConexion();
        }
        
        return all;
        
    }
    
}
