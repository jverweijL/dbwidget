package com.liferay.demo.db.service;

import com.liferay.demo.db.api.DbViewingService;
import org.osgi.service.component.annotations.Component;

import javax.portlet.RenderRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author jverweij
 */
@Component(
	immediate = true,
	property = {
		// TODO enter required service properties
	},
	service = DbViewingService.class
)
public class DbViewingServiceImpl implements DbViewingService {

	@Override
	public List<HashMap<String, Object>> getResult(RenderRequest request) {
		String jdbcConnection = request.getPreferences().getValue("jdbcConnection", "");
		String jdbcDriver = request.getPreferences().getValue("jdbcDriver", "");
		String jdbcUsername = request.getPreferences().getValue("jdbcUsername", "");
		String jdbcPassword = request.getPreferences().getValue("jdbcPassword", "");
		String sql = request.getPreferences().getValue("sql", "");

		Connection conn = null;
		Statement stmt = null;
		List<HashMap<String, Object>> result = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(jdbcConnection,jdbcUsername,jdbcPassword);

			System.out.println("Valid JDBC connection status: " + conn.isValid(2000));
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			result =  convertResultSetToList(rs);

			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

		return result;
	}

	private List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

		while (rs.next()) {
			HashMap<String,Object> row = new HashMap<String, Object>(columns);
			for(int i=1; i<=columns; ++i) {
				row.put(md.getColumnName(i),rs.getObject(i));
			}
			list.add(row);
		}

		return list;
	}

	// TODO enter required service methods

}