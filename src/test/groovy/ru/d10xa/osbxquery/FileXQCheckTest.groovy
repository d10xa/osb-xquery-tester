package ru.d10xa.osbxquery
import org.junit.Assert
import org.junit.Before
import org.junit.Test

public class FileXQCheckTest {

    String xqueryFile = "xqueries/strip_namespace.xq"
    String inputXml = "xml/strip_namespace_01_input.xml"
    String expectedXml = "xml/strip_namespace_01_expected.xml"

    BaseXQCheck xqc

    @Before
    void 'init'(){
        xqc = new FileXQCheck(xqueryFile, inputXml, expectedXml)
    }

    @Test void 'check identical method with valid input'() {
        Assert.assertTrue(xqc.identical())
    }

    @Test void 'check similar method with valid input'() {
        Assert.assertTrue(xqc.similar())
    }

    @Test void 'check getDifferences method return empty list'() {
        Assert.assertEquals("expects differences not found",0,xqc.getDifferences().size())
    }
    
}
