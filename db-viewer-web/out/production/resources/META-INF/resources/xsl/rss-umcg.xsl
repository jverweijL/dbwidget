<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">


<xsl:template match="/">
    <xsl:apply-templates />
</xsl:template>

    <xsl:template match="item">
        <!--<xsl:variable name="pubdate" select="xs:dateTime(./pubDate)" as="xs:dateTime"/>-->
        <div>
            <hr/>
            <div style="color: #ddd; font-size: small;"><xsl:value-of select="./pubDate"/></div>
            <!--<div><xsl:value-of select="format-date($pubdate, '[D01]/[M01]/[Y0001]')"/></div>-->
            <div style="font-weight: bolder;"><a target="_blank" href="{./link}"><xsl:value-of select="./title"/></a></div>
            <div><xsl:value-of select="./description"/></div>
        </div>
    </xsl:template>

    <!-- override default template todo nothing -->
    <xsl:template match="text()"/>

</xsl:stylesheet>