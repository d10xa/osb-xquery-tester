package ru.d10xa.osbxquery

public class ContentXQCheck extends BaseXQCheck implements XQCheck {

    private Content xquery
    private Content input
    private Content expected

    public ContentXQCheck(String xquery, String input, String expected) {
        this.xquery = new Content(xquery)
        this.input = new Content(input)
        this.expected = new Content(expected)
    }

    @Override
    Content getXQuery() {
        return xquery
    }

    @Override
    Content getInputXml() {
        return input
    }

    @Override
    Content getExpectedXml() {
        return expected
    }
}
