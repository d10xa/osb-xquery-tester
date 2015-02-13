package ru.d10xa.osbxquery
import org.junit.Test

import static org.hamcrest.MatcherAssert.assertThat
import static ru.d10xa.osbxquery.IsSimilarMatcher.similar

public class IsSimilarMatcherTest {

    String xqueryFile = "xqueries/strip_namespace.xq"
    String inputXml = "xml/strip_namespace_01_input.xml"
    String expectedXml = "xml/strip_namespace_01_expected.xml"

    BaseXQCheck xqc

    @Test
    void 'check is similar'(){
        xqc = new FileXQCheck(xqueryFile, inputXml, expectedXml)
        assertThat xqc, similar()
    }

}
