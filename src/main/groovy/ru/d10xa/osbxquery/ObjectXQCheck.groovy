package ru.d10xa.osbxquery

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

public class ObjectXQCheck extends ContentXQCheck implements XQCheck {

    public ObjectXQCheck(Content xquery, Object input, Object expected) {
        super(
                xquery?.content,
                objectToXml(input),
                objectToXml(expected)
        )
    }

    private static String objectToXml(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        def marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.marshal(object, baos);
        return baos as String
    }

}