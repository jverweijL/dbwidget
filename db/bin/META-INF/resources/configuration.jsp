
<%@ include file="./init.jsp" %>



<%
    dbConfiguration dbConfiguration =
            (dbConfiguration)
                    renderRequest.getAttribute(dbConfiguration.class.getName());

    String xmlSource = StringPool.BLANK;
    String xslSource = StringPool.BLANK;

    if (Validator.isNotNull(dbConfiguration)) {
        xmlSource =
                portletPreferences.getValue(
                        "xmlSource", dbConfiguration.xmlSource());
        xslSource =
                portletPreferences.getValue(
                        "xslSource", dbConfiguration.xslSource());
    }
%>



<liferay-portlet:actionURL portletConfiguration="<%=true%>"
                           var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%=true%>"
                           var="configurationRenderURL" />

<aui:form action="<%=configurationActionURL%>" method="post" name="fm">
    <aui:input name="<%=Constants.CMD%>" type="hidden"
               value="<%=Constants.UPDATE%>" />

    <aui:input name="redirect" type="hidden"
               value="<%=configurationRenderURL%>" />

    <aui:fieldset>
        <div><aui:input cssClass="lfr-input-text-container" label="xml-url-name" name="xmlSource" type="text" value="<%= xmlSource%>" /></div>
        <div><aui:input cssClass="lfr-input-text-container" label="xsl-url-name" name="xslSource" type="text" value="<%= xslSource%>" /></div>
    </aui:fieldset>



    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>