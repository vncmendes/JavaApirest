package br.edu.ifsul.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO extends BaseDAO {

    public Evento getEventoById(Long id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("select * from eventos where id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Evento c = createEvento(rs);
                rs.close();
                return c;
            }
        }catch(SQLException e){ 
            e.printStackTrace();
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    

    public List<Evento> findByName(String name) throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("select * from eventos where name = ? ");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Evento c = createEvento(rs);
                eventos.add(c);
            }
            rs.close();
        }catch(SQLException e){ 
            e.printStackTrace();
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventos;
    }

    public List<Evento> getEventos() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("select * from eventos");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Evento c = createEvento(rs);
                eventos.add(c);
            }
            rs.close();
        }catch(SQLException e){ 
            e.printStackTrace();
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventos;
    }

    public Evento createEvento(ResultSet rs) throws SQLException {
        Evento c = new Evento();
        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));
        c.setDescription(rs.getString("description"));
        c.setPreco(rs.getInt("preco"));
        c.setUrlFoto(rs.getString("url_foto"));
        return c;
    }

    public boolean save(Evento c) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            if (c.getId() == null) {
                stmt = conn.prepareStatement("insert into eventos (name,description,preco,url_foto) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            } else {
                stmt = conn.prepareStatement("update eventos set name=?,description=?,preco=?,url_foto=? where id=?");
            }
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getDescription());
            stmt.setInt(3, c.getPreco());
            stmt.setString(4, c.getUrlFoto());
            if (c.getId() != null) {
                // Update
                stmt.setLong(5, c.getId());
            }
            int count = stmt.executeUpdate();
            if (count == 0) {
            	return false; //throw new SQLException("Erro ao inserir o carro");
            }
            // Se inseriu, ler o id auto incremento
            if (c.getId() == null) {
                Long id = getGeneratedId(stmt);
                c.setId(id);
            }
            return true;
        }catch(SQLException e){ 
            e.printStackTrace();
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
		return false;
    }

    // id gerado com o campo auto incremento
    public static Long getGeneratedId(Statement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            Long id = rs.getLong(1);
            return id;
        }
        return 0L;
    }

    public boolean delete(Long id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("delete from eventos where id=?");
            stmt.setLong(1, id);
            int count = stmt.executeUpdate();
            boolean ok = count > 0;
            return ok;
        }catch(SQLException e){ 
            e.printStackTrace();
            return false;
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public static void main(String[] args) {
		EventoDAO eventoDAO = new EventoDAO();
		try {
			System.out.println(eventoDAO.getEventos());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
