package ru.d10xa.osbxquery

import org.junit.Before
import org.junit.Test

class EmptyParameterTest {
  def xqt

  @Before
   void setup() {
    xqt = new XQueryTransformer(new File("xqueries/empty_parameter.xq"))
  }

  @Test
  void testTransform() {
    xqt.withString { str ->
      println str
    }
  }

  @Test
  void testSlurping() {
    xqt.withString { str ->
      def data = new XmlSlurper().parseText(str)
      assert data.text() == '2005-07-15'
    }
  }

 
}