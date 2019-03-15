
<%@ include file="./init.jsp" %>



<%
    dbConfiguration dbConfiguration =
            (dbConfiguration)
                    renderRequest.getAttribute(dbConfiguration.class.getName());

    String sql = StringPool.BLANK;
    String jndi = StringPool.BLANK;
    String jdbcConnection = StringPool.BLANK;
    String jdbcDriver = StringPool.BLANK;
    String jdbcUsername = StringPool.BLANK;
    String jdbcPassword = StringPool.BLANK;

    if (Validator.isNotNull(dbConfiguration)) {
        sql =
                portletPreferences.getValue(
                        "sql", dbConfiguration.sql());
        jndi =
                portletPreferences.getValue(
                        "jndi", dbConfiguration.jndi());
        jdbcConnection =
                portletPreferences.getValue(
                        "jdbcConnection", dbConfiguration.jdbcConnection());
        jdbcDriver =
                portletPreferences.getValue(
                        "jdbcDriver", dbConfiguration.jdbcDriver());
        jdbcUsername =
                portletPreferences.getValue(
                        "jdbcUsername", dbConfiguration.jdbcUsername());
        jdbcPassword =
                portletPreferences.getValue(
                        "jdbcPassword", dbConfiguration.jdbcPassword());
    }
%>


<liferay-portlet:actionURL portletConfiguration="<%=true%>"
                           var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%=true%>"
                           var="configurationRenderURL" />


<div class="panel panel-secondary">
    <div class="panel-header">Heading</div>
    <div class="panel-body">
        <aui:form action="<%=configurationActionURL%>" method="post" name="fm">
            <aui:input name="<%=Constants.CMD%>" type="hidden"
                       value="<%=Constants.UPDATE%>" />

            <aui:input name="redirect" type="hidden"
                       value="<%=configurationRenderURL%>" />

            <aui:fieldset>
                <div><aui:input cssClass="lfr-input-text-container" label="sql-name" name="sql" type="text" value="<%= sql%>" /></div>
                <div><aui:input cssClass="lfr-input-text-container" label="jndi-name" name="jndi" type="text" value="<%= jndi%>" /></div>
                <div><aui:input cssClass="lfr-input-text-container" label="jdbc-connection-name" name="jdbcConnection" type="text" value="<%= jdbcConnection%>" /></div>
                <div><aui:input cssClass="lfr-input-text-container" label="jdbc-driver-name" name="jdbcDriver" type="text" value="<%= jdbcDriver%>" /></div>
                <div><aui:input cssClass="lfr-input-text-container" label="jdbc-username-name" name="jdbcUsername" type="text" value="<%= jdbcUsername%>" /></div>
                <div><aui:input cssClass="lfr-input-text-container" label="jdbc-password-name" name="jdbcPassword" type="password" value="<%= jdbcPassword%>" /></div>
            </aui:fieldset>

            <aui:button-row>
                <aui:button type="submit"></aui:button>
            </aui:button-row>
        </aui:form>
    </div>
</div>




