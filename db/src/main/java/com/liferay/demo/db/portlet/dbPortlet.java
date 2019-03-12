package com.liferay.demo.db.portlet;

import com.liferay.demo.db.config.dbConfiguration;
import com.liferay.demo.db.constants.dbPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jverweij
 */
@Component(
	configurationPid = "com.liferay.demo.db.config.dbConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + dbPortletKeys.dbWidget,
		"javax.portlet.display-name=" + dbPortletKeys.dbWidget,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class dbPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

			String jdbcConnection = renderRequest.getPreferences().getValue("jdbcConnection", "");
			String jdbcDriver = renderRequest.getPreferences().getValue("jdbcDriver", "");
			String jdbcUsername = renderRequest.getPreferences().getValue("jdbcUsername", "");
			String jdbcPassword = renderRequest.getPreferences().getValue("jdbcPassword", "");
			String sql = renderRequest.getPreferences().getValue("sql", "");




			Connection conn = null;
			Statement stmt = null;
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
				renderRequest.setAttribute("result", convertResultSetToList(rs));
				/*while(rs.next()){
					//Retrieve by column name
					int id  = rs.getInt("id");
					int age = rs.getInt("age");
					String first = rs.getString("first");
					String last = rs.getString("last");

					//Display values
					System.out.print("ID: " + id);
					System.out.print(", Age: " + age);
					System.out.print(", First: " + first);
					System.out.println(", Last: " + last);
				}*/
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
			System.out.println("Goodbye!");


		super.doView(renderRequest, renderResponse);
	}

	public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
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

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(dbConfiguration.class, properties);
	}

	private volatile dbConfiguration _configuration;

	private static final Log _log = LogFactoryUtil.getLog(dbPortlet.class);

}