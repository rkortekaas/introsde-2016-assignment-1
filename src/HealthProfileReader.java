// import java.util.HashMap;
// import java.util.Map;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pojos.HealthProfile;
import pojos.Person;

public class HealthProfileReader {

	Document doc;
    XPath xpath;

    // Load xml file in memory

	public void loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse("people.xml");

        //creating xpath object
        getXPathObj();
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }

    // retrieve list of persons

    public NodeList getPersonList() throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;
    }

    // retrieve healthprofile of person by personId

    public NodeList getHealthProfile(String personId) throws XPathExpressionException {
        String expression = "/people/person[@id='" + personId + "']/healthprofile/*";
        XPathExpression expr = xpath.compile(expression);
        NodeList hplist = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return hplist;   
    }

    // retrieve list of person's with certain weight

    public NodeList getPersonsByWeight(String weight, String condition) throws XPathExpressionException {
        String expression = "//healthprofile[weight " + condition + "'" + weight + "']";
    	XPathExpression expr = xpath.compile(expression);
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;	
    }

    public NodeList getPersonName(String personId) throws XPathExpressionException {
        XPathExpression expr = xpath.compile("/people/person[@id='" + personId + "']/firstname | /people/person[@id='" + personId + "']/lastname");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;
    }

    // // retrieve person's weight by personId

    // public Node getWeight(String personId) throws XPathExpressionException {

    //     String expression = "/people/person[@id='" + personId + "']/healthprofile/weight";
    //     XPathExpression expr = xpath.compile(expression);
    //     Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
    //     return node;
    // }

    // // retrieve person's height by personId

    // public Node getHeight(String personId) throws XPathExpressionException {

    //     String expression = "/people/person[@id='" + personId + "']/healthprofile/height";
    //     XPathExpression expr = xpath.compile(expression);
    //     Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
    //     return node;
    // }

	/**
	 *
     * The health profile reader gets information from people.xml about personal and health data
     * from persons included in the xml document.
	 * 
	 * @param args
	 */

	public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {
		
		HealthProfileReader peopleobject = new HealthProfileReader();

		peopleobject.loadXML();

        // Print list of people with detail
        System.out.println();
        System.out.println("List of people in xml: ");
        NodeList nodes = peopleobject.getPersonList();
        for (int i = 0; i < nodes.getLength(); i++) {

                        Node node3 = nodes.item(i);

                        if (node3.getNodeType() == Node.ELEMENT_NODE) {

                            System.out.println("=====================");

                            Element e = (Element) node3;
                            
                            NodeList nodeList = e.getElementsByTagName("firstname");
                            System.out.println("Firstname: " + nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            nodeList = e.getElementsByTagName("lastname");
                            System.out.println("Lastname: " + nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            nodeList = e.getElementsByTagName("birthdate");
                            System.out.println("Birthdate: " + nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            System.out.println("--HealthProfile--");

                            nodeList = e.getElementsByTagName("lastupdate");
                            System.out.println("Last update: " + nodeList.item(0).getChildNodes().item(0).getNodeValue());
                            
                            nodeList = e.getElementsByTagName("weight");
                            System.out.println("Weight: "+ nodeList.item(0).getChildNodes().item(0).getNodeValue());
                            
                            nodeList = e.getElementsByTagName("height");
                            System.out.println("Height: "+ nodeList.item(0).getChildNodes().item(0).getNodeValue());
                            
                            nodeList = e.getElementsByTagName("bmi");
                            System.out.println("BMI: "+ nodeList.item(0).getChildNodes().item(0).getNodeValue());

                        }
                    }
        
        // Get healthprofile information for person with id=5

        System.out.println();
        System.out.println();
        System.out.println("Health Profile for person with id=5" );
        System.out.println("=====================");
        
        NodeList personfullname = peopleobject.getPersonName("5");
        for (int i = 0; i < personfullname.getLength(); i++) {
            Node name = personfullname.item(i);
            System.out.println(name.getNodeName() + ": " + name.getTextContent());
            }

        NodeList hp = peopleobject.getHealthProfile("5");
        for (int i = 0; i < hp.getLength(); i++) {
            Node hpitem = hp.item(i);
            System.out.println(hpitem.getNodeName() + ": " + hpitem.getTextContent());
            }
        
        // get list of persons with weight > 90kg

        System.out.println();
        System.out.println();
		NodeList healthprofiles = peopleobject.getPersonsByWeight("90", ">");
		System.out.println("Persons having weight > 90kg");
              for (int i = 0; i < healthprofiles.getLength(); i++) {
                Node person = healthprofiles.item(i).getParentNode();

                Element e = (Element) person;

                System.out.println("=====================");

                NodeList name = e.getElementsByTagName("firstname");
                System.out.println("Firstname: " + name.item(0).getChildNodes().item(0).getNodeValue());

                name = e.getElementsByTagName("lastname");
                System.out.println("Lastname: " + name.item(0).getChildNodes().item(0).getNodeValue());

                name = e.getElementsByTagName("weight");
                System.out.println("Weight: " + name.item(0).getChildNodes().item(0).getNodeValue());
	    }
        System.out.println();
        System.out.println();
	}

}