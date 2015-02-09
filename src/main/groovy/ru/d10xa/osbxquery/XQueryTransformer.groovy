package ru.d10xa.osbxquery

import org.apache.xmlbeans.XmlObject
import org.apache.xmlbeans.XmlOptions

import static ru.d10xa.osbxquery.XQueryExternalVariable.extractLastExternalVariable
import static ru.d10xa.osbxquery.XQueryOptionsUtils.createOptions

class XQueryTransformer {

    private final String xquerySource

    private Map params

    XQueryTransformer(String xqueryFile){
        this.xquerySource = new File(xqueryFile).text
    }

    XQueryTransformer(File xquery) {
        this.xquerySource = xquery.text
    }
    
    XQueryTransformer(Map params, File xquery) {
        this.xquerySource = xquery.text
        this.params = params
    }

    XQueryTransformer(Map params, String xqueryFile) {
        this.xquerySource = new File(xqueryFile).text
        this.params = params
    }

    XQueryTransformer(Map params, Content xquery) {
        this.xquerySource = xquery.content
        this.params = params
    }

    static XQueryTransformer buildSingleVariableTransformer(File xquery, File inputXml){
        XQueryExternalVariable variable = extractLastExternalVariable(xquery.text)
        Map<String, Object> xmlOptions = new HashMap<String, Object>();
        xmlOptions.put(variable.name, inputXml);
        return new XQueryTransformer(xmlOptions,xquery);
    }

    static XQueryTransformer buildSingleVariableTransformer(Content xquery, Content input){
        XQueryExternalVariable variable = extractLastExternalVariable(xquery as String)
        Map<String, Object> xmlOptions = new HashMap<String, Object>();
        xmlOptions.put(variable.name, input);
        return new XQueryTransformer(xmlOptions,xquery);
    }

    String transform() {
        XmlObject xmlObject = XmlObject.Factory.newInstance()
        XmlOptions options = new XmlOptions()
        if(params!=null) {
            options.setXqueryVariables(createOptions(params))
        }
        XmlObject[] results = xmlObject.execQuery(xquerySource, options)
        return results[0].xmlText(new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2))
    }

    def withString(c){
        def s = transform()
        c.call s
    }
}
