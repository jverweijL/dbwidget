package com.liferay.demo.db.config.definition;

import com.liferay.demo.db.config.dbConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

public class dbPortletInstanceConfigurationBeanDeclaration
        implements ConfigurationBeanDeclaration {
    @Override
    public Class<?> getConfigurationBeanClass() {
        return dbConfiguration.class;
    }
}
