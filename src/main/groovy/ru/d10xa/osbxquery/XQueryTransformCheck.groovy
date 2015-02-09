package ru.d10xa.osbxquery

@Deprecated
public class XQueryTransformCheck extends BaseXQCheck implements XQCheck {

    private String xquerySource;
    private String inputXmlSource;
    private String expectedXmlSource;

    public XQueryTransformCheck(File xquery, File inputXml, File expectedXml) {
        this.xquerySource = xquery.text
        this.inputXmlSource = inputXml.text
        this.expectedXmlSource = expectedXml.text
    }

    @Override
    Content getXQuery() {
        return new Content(xquerySource)
    }

    @Override
    Content getInputXml() {
        return new Content(inputXmlSource)
    }

    @Override
    Content getExpectedXml() {
        return new Content(expectedXmlSource)
    }
}
