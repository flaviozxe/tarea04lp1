package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Tipo_Cliente;
import util.MySqlDBConexion;

public class ClienteModel {
	public List<Cliente> consultaCliente(String nombre){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="SELECT d.*,g.nombre FROM cliente d inner join tipo_cliente g on d.idTipoCliente = g.idTpoCliente where d.nombres = ?";  
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Cliente d = null;
			Tipo_Cliente g = null;
			while(rs.next()){
				d = new Cliente();
				d.setIdCliente(rs.getInt(1));
				d.setNomCliente(rs.getString(2));
				d.setApeCLiente(rs.getString(3));
				d.setDni(rs.getString(4));
				d.setFecNacimiento(rs.getDate(5));
				
				
				g = new Tipo_Cliente();
				g.setIdTpoCliente(rs.getInt(6));
				g.setNombre(rs.getString(7));
				
				d.setTipo(g);
				
				data.add(d);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	public List<Cliente> listarCliente(){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="SELECT d.*,g.nombre FROM cliente d inner join tipo_cliente g on d.idTipoCliente = g.idTpoCliente";  
			pstm = con.prepareStatement(sql);
			
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Cliente d = null;
			Tipo_Cliente g = null;
			while(rs.next()){
				d = new Cliente();
				d.setIdCliente(rs.getInt(1));
				d.setNomCliente(rs.getString(2));
				d.setApeCLiente(rs.getString(3));
				d.setDni(rs.getString(4));
				d.setFecNacimiento(rs.getDate(5));
				
				
				g = new Tipo_Cliente();
				g.setIdTpoCliente(rs.getInt(6));
				g.setNombre(rs.getString(7));
				
				d.setTipo(g);
				
				data.add(d);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
