package com.liferay.demo.db.config;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;


@ExtendedObjectClassDefinition(
        category = "Dynamic Content",
        scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
        id = "com.liferay.demo.db.config.dbConfiguration",
        localization = "content/Language",
        name = "xmlwidget-configuration-name"
)
public interface dbConfiguration {

    @Meta.AD(
            deflt = "select 1=1",
            description = "sql-description",
            name = "sql-name",
            required = false
    )
    public String sql();

    @Meta.AD(
            deflt = "not implemented",
            description = "jndi-description",
            name = "jndi-name",
            required = false
    )
    public String jndi();

    @Meta.AD(
            deflt = "jdbc:mysql://{host}[:{port}]/[{database}]",
            description = "jdbc-connection-description",
            name = "jdbc-connection-name",
            required = false
    )
    public String jdbcConnection();

    @Meta.AD(
            deflt = "com.mysql.jdbc.Driver",
            description = "jdbc-driver-description",
            name = "jdbc-driver-name",
            required = false
    )
    public String jdbcDriver();

    @Meta.AD(
            deflt = "",
            description = "jdbc-username-description",
            name = "jdbc-username-name",
            required = false
    )
    public String jdbcUsername();

    @Meta.AD(
            deflt = "",
            description = "jdbc-password-description",
            name = "jdbc-password-name",
            required = false
    )
    public String jdbcPassword();

}
