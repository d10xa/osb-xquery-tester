package ru.d10xa.osbxquery
import org.apache.xmlbeans.*

class XQueryOptionsUtils {

    public static Map createOptions(map) {
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
                case Content:
                    def txt = v as String
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
}
