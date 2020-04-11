package shanix.xyz.SHBot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/shbot?user=shbot&password=placeholder");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public void setConfigValue (String key, String value) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		boolean isUpdate = false;
		
		String sql = "select * from cfg "
				+ "where cfgkey = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()) {
				// Key already in the database
				isUpdate = true;
			}
			
			if(isUpdate) {
				sql = "update cfg set mdate = now(), cfgvalue = ?"
						+ "where cfgkey = ?";
			} else {
				sql = "insert into cfg (cdate, mdate, cfgvalue, cfgkey) "
						+ "values (now(), now(), ?, ?)";
			}
			
			ps.close();
			ps = con.prepareStatement(sql);
			ps.setString(1, value);
			ps.setString(2, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
	}
	
	public String getConfigValue(String key) {
		String value = "";
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from cfg "
				+ "where cfgkey = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				value = rs.getString("cfgvalue");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return value;
	}

}
