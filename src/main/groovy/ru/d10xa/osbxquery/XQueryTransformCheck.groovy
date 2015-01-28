package ru.d10xa.osbxquery
import groovy.transform.Memoized
import org.custommonkey.xmlunit.DetailedDiff
import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.XMLUnit

public class XQueryTransformCheck {

   private File xquery
   private File inputXml
   private File expectedXml

   private String xquerySource;
   private String inputXmlSource;
   private String expectedXmlSource;

   final String generatedXmlSource;

   public XQueryTransformCheck(File xquery, File inputXml, File expectedXml) {
      this.xquery=xquery
      this.inputXml = inputXml
      this.expectedXml = expectedXml

      this.xquerySource = xquery.text
      this.inputXmlSource = inputXml.text
      this.expectedXmlSource = expectedXml.text

      this.generatedXmlSource = XQueryTransformer.buildSingleVariableTransformer(xquery,inputXml).transform()
   }

   {
      XMLUnit.setIgnoreWhitespace(true);
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
   private Diff getDiff(){
      return new Diff(expectedXmlSource,generatedXmlSource)
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
$xquery, $inputXml, $expectedXml
------input------
$inputXmlSource
------generated------ <
$generatedXmlSource>
------expected------
$expectedXmlSource
"""
   }

}
