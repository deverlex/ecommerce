package vn.needy.ecommerce.common.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class PropertyProvider implements Serializable {

	private static final long serialVersionUID = 15345353L;
	
	public static final String PROPURL = "jdbc:sqlite:properties.db"; 
	public static final String DRIVER = "org.sqlite.JDBC";
	
	public Number getNumberByTag(String tag) {
	    try { 
	    	Connection conn = DriverManager.getConnection(PROPURL); 
	        Statement st = conn.createStatement(); 
	        String query = "SELECT value FROM Numbers WHERE tag = '" + tag + "'"; 
	        ResultSet rs = null; 
	        try { 
	           rs = st.executeQuery(query); 
	           if (rs.next()) return rs.getFloat(1);
	        } finally { 
	           if (rs != null) rs.close(); 
	        } 
	    } catch (Exception e) { 
	      e.printStackTrace(); 
	    } 
	    
		return 0;
	}

	public String getTextByTag(String tag) {
		try { 
	    	Connection conn = DriverManager.getConnection(PROPURL); 
	        Statement st = conn.createStatement(); 
	        String query = "SELECT value FROM Text WHERE tag = '" + tag + "'"; 
	        ResultSet rs = null; 
	        try { 
	           rs = st.executeQuery(query); 
	           if (rs.next()) return rs.getString(1);
	        } finally { 
	           if (rs != null) rs.close(); 
	        } 
	    } catch (Exception e) { 
	      e.printStackTrace(); 
	    } 
		return "";
	}
}
