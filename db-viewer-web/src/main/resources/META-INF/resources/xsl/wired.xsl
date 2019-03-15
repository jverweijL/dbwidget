<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:dc="http://purl.org/dc/elements/1.1/"
                xmlns:media="http://search.yahoo.com/mrss/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:template match="/">
    <div class="container">
        <div class="row">
                <xsl:apply-templates />
        </div>
    </div>
</xsl:template>

    <xsl:template match="item">
        <xsl:variable name="itemnumber" select="count(preceding-sibling::item) + 1"/>
        <xsl:choose>
            <xsl:when test="$itemnumber mod 6 = 0">
                <!--1-->
                <div class="col carditem">
                    <xsl:call-template name="card"/>
                </div>
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 1">
                <!--2/3-->
                <div class="col-8 carditem">
                    <xsl:call-template name="card"/>
                </div>
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 2">
                <div class="col-4 carditem">
                    <xsl:call-template name="card"/>
                </div>
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 3 or $itemnumber mod 6 = 4">
                <!--1/2/1/2-->
                <div class="col-6 carditem">
                    <xsl:call-template name="card"/>
                </div>
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 5">
                <!--1/2-->
                <div class="col-6 carditem">
                    <xsl:call-template name="card"/>
                </div>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="card">
        <div class="card">
            <div class="aspect-ratio aspect-ratio-3-to-2 card-item-first">
                <div class="custom-control custom-checkbox">
                    <img alt="thumbnail" class="aspect-ratio-item-center-middle aspect-ratio-item">
                        <xsl:attribute name="src" select="./media:thumbnail/@url"/>
                    </img>
                </div>
            </div>
            <div class="card-body">
                <div class="card-row">
                    <div class="autofit-col autofit-col-expand">
                        <div class="card-title text-truncate" title="thumbnail_coffee.jpg">
                            <strong><xsl:value-of select="./title"/></strong>
                        </div>
                        <div class="card-subtitle text-truncate" title="Author Action">
                            <xsl:value-of select="./dc:creator"/>
                        </div>
                        <!--<div class="card-detail">
                            <xsl:value-of select="./description"/>
                        </div>-->
                    </div>
                </div>
            </div>
        </div>
    </xsl:template>

    <!-- override default template todo nothing -->
    <xsl:template match="text()"/>

</xsl:stylesheet>