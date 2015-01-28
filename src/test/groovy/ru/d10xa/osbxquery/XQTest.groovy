package ru.d10xa.osbxquery
import org.junit.Before
import org.junit.Test

class XQTest {
  def xqt

  @Before
   void setup() {
    xqt = new XQueryTransformer(new File("xqueries/Messier.xq"),
                                 src: new File("xml/Messier.xml"),
                                 str: 'hello, world',
                                 n: 42,
                                 b: true,
                                 dt: new Date(),
                                 f: Math.PI,
                                 nd: null as Date)
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
      def data = new XmlSlurper().parseText(str).declareNamespace(oth: "http://other")
      assert data.'oth:m'.size() == 5
      assert data.'oth:m'[3].text() == 'Scorpius'
    }
  }

  @Test
  void testParsing() {
    xqt.withString { str ->
      def data = new XmlParser().parseText(str)
      assert data.'oth:m'.size() == 5
      assert data.'oth:m'[4].text() == 'Serpens'
    }
  }
}