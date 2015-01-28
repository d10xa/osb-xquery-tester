package ru.d10xa.osbxquery

import org.apache.xmlbeans.XmlBoolean
import org.apache.xmlbeans.XmlDateTime
import org.apache.xmlbeans.XmlDouble
import org.apache.xmlbeans.XmlInteger
import org.apache.xmlbeans.XmlObject
import org.apache.xmlbeans.XmlOptions
import org.apache.xmlbeans.XmlString

import static ru.d10xa.osbxquery.XQueryExternalVariable.extractLastExternalVariable

class XQueryTransformer {

    private final String xquerySource
    private Map params

    XQueryTransformer(String xquerySource){
        this.xquerySource = xquerySource
    }

    XQueryTransformer(File xqueryFile) {
        this.xquerySource = xqueryFile.text
    }
    
    XQueryTransformer(Map params, File xqueryFile) {
        this.xquerySource = xqueryFile.text
        this.params = params
    }

    XQueryTransformer(Map params, String xquerySource) {
        this.xquerySource = xquerySource
        this.params = params
    }

    static XQueryTransformer buildSingleVariableTransformer(File xquery,File inputXml){
        XQueryExternalVariable variable = extractLastExternalVariable(xquery.text)
        Map<String, Object> xmlOptions = new HashMap<String, Object>();
        xmlOptions.put(variable.name, inputXml);
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

    private createOptions(map) {
        def optionsMap = [:]
        map.each { k, v ->
            switch (v?.class) {
                case Date:
                    XmlDateTime dt = XmlDateTime.Factory.newInstance();
                    dt.setDateValue(v)
                    optionsMap.put(k, dt)
                    break
                case Boolean:
                    XmlBoolean b = XmlBoolean.Factory.newInstance();
                    b.set(v)
                    optionsMap.put(k, b)
                    break
                case [BigInteger, Integer, Long, Short, Byte, byte, short, int, long]:
                    XmlInteger i = XmlInteger.Factory.newInstance();
                    i.setBigIntegerValue(v as BigInteger)
                    optionsMap.put(k, i)
                    break
                case [BigDecimal, Float, Double, float, double]:
                    XmlDouble xd = XmlDouble.Factory.newInstance();
                    xd.setDoubleValue(v as double)
                    optionsMap.put(k, xd)
                    break
                case String:
                    XmlString string = XmlString.Factory.newInstance();
                    string.setStringValue(v);
                    optionsMap.put(k, string)
                    break
                case File:
                    def txt = v.text
                    optionsMap.put(k, XmlObject.Factory.parse(txt).selectPath("/*")[0])
                    break

                case null:
                    XmlObject n = XmlObject.Factory.newInstance()
                    n.setNil()
                    optionsMap.put(k, n)
                    break

                default:
                    throw new Exception("Unhandled type: ${v.class}")
                    break
            }
        }
        optionsMap
    }

    def withString(c){
        def s = transform()
        c.call s
    }
}
