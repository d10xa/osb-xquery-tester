package ru.d10xa.osbxquery

import groovy.transform.ToString

import java.util.regex.Matcher
import java.util.regex.Pattern


@ToString
class XQueryExternalVariable {
    String name, type

    public static XQueryExternalVariable extractLastExternalVariable (String xquerySource){
        String regexp = "declare[ ]+variable.*";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(xquerySource);
        String[] tokenlist = null;
        while (m.find()){
            tokenlist = m.group().split("\\s+");
        }
        return new XQueryExternalVariable(
                name:removeDollarToken(tokenlist[2]),
                type:tokenlist[4]
        )
    }

    private static String removeDollarToken(String string) {
        return string.replace(String.valueOf('$'), "");
    }
}
