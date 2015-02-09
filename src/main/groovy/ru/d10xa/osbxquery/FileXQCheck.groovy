package ru.d10xa.osbxquery

public class FileXQCheck extends ContentXQCheck implements XQCheck {

    public FileXQCheck(String xquery, String inputXml, String expectedXml) {
        super(
                new File(xquery).text,
                new File(inputXml).text,
                new File(expectedXml).text
        )
    }

}
