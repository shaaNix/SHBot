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
	
	public String getConfigValue(String key) {
		String value = "";
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from cfg "
				+ "where `key` = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				value = rs.getString("value");
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
