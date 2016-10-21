<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:scala="http://davidpriest.ca/saxon/myfunction"
                exclude-result-prefixes="xs"
                version="2.0">

    <xsl:template match="/">
        <xsl:text>•••••</xsl:text>
        <xsl:value-of select="scala:MyFunction('so','long','farewell')"/>
        <xsl:text>•••••</xsl:text>
    </xsl:template>

</xsl:stylesheet>