package com.liferay.demo.db.portlet.action;


import com.liferay.demo.db.config.dbConfiguration;
import com.liferay.demo.db.constants.dbPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.*;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component(
        configurationPid = "com.liferay.demo.db.portlet.action.dbConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = {"javax.portlet.name=" + dbPortletKeys.dbWidget},
        service = ConfigurationAction.class
)
public class dbConfigurationAction extends DefaultConfigurationAction {
    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
            throws Exception {

        //TODO looping over request params would perhaps work better?

        String xmlSource = ParamUtil.getString(actionRequest, "xmlSource");
        String xslSource = ParamUtil.getString(actionRequest, "xslSource");
        String sql = ParamUtil.getString(actionRequest, "sql");
        String jndi = ParamUtil.getString(actionRequest, "jndi");
        String jdbcConnection = ParamUtil.getString(actionRequest, "jdbcConnection");
        String jdbcDriver = ParamUtil.getString(actionRequest, "jdbcDriver");
        String jdbcUsername = ParamUtil.getString(actionRequest, "jdbcUsername");
        String jdbcPassword = ParamUtil.getString(actionRequest, "jdbcPassword");

        setPreference(actionRequest, "xmlSource", xmlSource);
        setPreference(actionRequest, "xslSource", xslSource);
        setPreference(actionRequest, "sql", sql);
        setPreference(actionRequest, "jndi", jndi);
        setPreference(actionRequest, "jdbcConnection", jdbcConnection);
        setPreference(actionRequest, "jdbcDriver", jdbcDriver);
        setPreference(actionRequest, "jdbcUsername", jdbcUsername);
        setPreference(actionRequest, "jdbcPassword", jdbcPassword);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
            PortletConfig portletConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
                dbConfiguration.class.getName(),
                _configuration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
                dbConfiguration.class, properties);

    }

    private volatile dbConfiguration _configuration;
}
