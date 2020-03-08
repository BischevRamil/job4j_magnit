<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<html>
<body>
<entries>
    <xsl:for-each select="user/entries">
                       <entry>
                           
                               <p><xsl:value-of select="entry"/></p>
                          
                       </entry>
    </xsl:for-each>
</entries>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
