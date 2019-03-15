package com.liferay.demo.db.portlet;

import com.liferay.demo.db.api.DbViewingService;
import com.liferay.demo.db.config.dbConfiguration;
import com.liferay.demo.db.constants.dbPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.*;

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

		renderRequest.setAttribute("result", _dbViewingService.getResult(renderRequest));

		super.doView(renderRequest, renderResponse);
	}


	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(dbConfiguration.class, properties);
	}

	private volatile dbConfiguration _configuration;

	@Reference(cardinality= ReferenceCardinality.MANDATORY)
	protected DbViewingService _dbViewingService;

	private static final Log _log = LogFactoryUtil.getLog(dbPortlet.class);

}