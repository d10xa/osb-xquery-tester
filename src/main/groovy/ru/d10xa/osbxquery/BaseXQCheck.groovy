package ru.d10xa.osbxquery

import groovy.transform.Memoized
import org.custommonkey.xmlunit.DetailedDiff
import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.XMLUnit

public abstract class BaseXQCheck implements XQCheck {

    public abstract Content getXQuery()

    public abstract Content getInputXml()

    public abstract Content getExpectedXml()

    static {
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Memoized
    public String getGeneratedXml() {
        XQueryTransformer.buildSingleVariableTransformer(
                getXQuery(),
                getInputXml()).transform()
    }

    @Memoized
    @Deprecated
    public String getGeneratedXmlSource() {
        return getGeneratedXml()
    }

    @Memoized
    public boolean similar() {
        return diff.similar();
    }

    @Memoized
    public boolean identical() {
        return diff.identical();
    }

    @Memoized
    protected Diff getDiff() {
        return new Diff(expectedXml?.content, getGeneratedXml().toString())
    }

    @Memoized
    public List getDifferences(){
        DetailedDiff detDiff = new DetailedDiff(diff);
        List<String> differences = detDiff.getAllDifferences();
        List<String> stringList = new ArrayList<String>(differences.size());
        for (String difference : differences) {
            stringList.add(String.valueOf(difference));
        }
        return stringList;
    }

    String toString(){
        """
------input------
${inputXml?.content}
------generated------
${generatedXml}
------expected------
${expectedXml?.content}
"""
    }

}

