package ru.d10xa.osbxquery
import org.junit.Assert
import org.junit.Before
import org.junit.Test

public class XQueryTransformCheckTest {

    String xqueryFile = "xqueries/strip_namespace.xq"
    String inputXml = "xml/strip_namespace_01_input.xml"
    String expectedXml = "xml/strip_namespace_01_expected.xml"

    XQueryTransformCheck xqc

    @Before
    void 'init'(){
        xqc = new XQueryTransformCheck(new File(xqueryFile), new File(inputXml), new File(expectedXml))
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
