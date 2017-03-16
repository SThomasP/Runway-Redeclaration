package controller;



import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

	public WriteXMLFile() {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("airportdata");
		doc.appendChild(rootElement);

		// runway elements
		Element runway = doc.createElement("runway");
		rootElement.appendChild(runway);
		
		Element obstacle = doc.createElement("obstacle");
		rootElement.appendChild(obstacle);
		
		//runway.setAttribute("runway1", 1);
		
		
		//______________DETAILS OF THE RUNWAY_________________
		// rname elements
		Element rname = doc.createElement("rname");
		rname.appendChild(doc.createTextNode("Heathrow runway 1"));
		runway.appendChild(rname);

		// direction elements
		Element direction = doc.createElement("direction");
		direction.appendChild(doc.createTextNode("09L"));
		runway.appendChild(direction);

		// lenght elements
		Element tora = doc.createElement("tora");
		tora.appendChild(doc.createTextNode("4700"));
		runway.appendChild(tora);
		Element toda = doc.createElement("toda");
		toda.appendChild(doc.createTextNode("4701"));
		runway.appendChild(toda);
		Element asda = doc.createElement("asda");
		asda.appendChild(doc.createTextNode("4702"));
		runway.appendChild(asda);
		Element lda = doc.createElement("lda");
		lda.appendChild(doc.createTextNode("4703"));
		runway.appendChild(lda);

		// condition elements
		Element condition = doc.createElement("condition");
		condition.appendChild(doc.createTextNode("not ready for use"));
		runway.appendChild(condition);

		//______________DETAILS OF THE OBSTACLE_________________
		//  obstacle name
		Element oname = doc.createElement("oname");
		oname.appendChild(doc.createTextNode("Obstacle 4"));
		obstacle.appendChild(oname);
		
		// obstacle type
		Element type = doc.createElement("type");
		type.appendChild(doc.createTextNode("part of a plane"));
		obstacle.appendChild(type);
		
		// obstacle height
		Element height = doc.createElement("height");
		height.appendChild(doc.createTextNode("66"));
		obstacle.appendChild(height);
		
		// distance from centre line
		Element distancefromcl = doc.createElement("distancefromcl");
		distancefromcl.appendChild(doc.createTextNode("6"));
		obstacle.appendChild(distancefromcl);
		
		// distance from centre threshold
		Element distancefromthreshold = doc.createElement("distancefromthreshold");
		distancefromthreshold.appendChild(doc.createTextNode("68"));
		obstacle.appendChild(distancefromthreshold);
		
		// level of danger
		Element levelofdanger = doc.createElement("levelofdanger");
		levelofdanger.appendChild(doc.createTextNode("really bad"));
		obstacle.appendChild(levelofdanger);
		
		
		
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("filexmltest4.xml"));
		
		
		

		// Output to console for testing
		//StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("");
		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
