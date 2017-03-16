package controller;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import model.Airport;
import model.Obstacle;
import model.Runway;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class ReadAirportXMLFile {


	File file = new File("airport.xml");
	public Airport read() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("runway");
			ArrayList<Runway> listOfRunways = new ArrayList<Runway>();
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Runway newRun;
					char location;
					int orientation;
					int tora;
					int toda;
					int asda;
					int lda;
					int displacedThreshold;
					location = eElement.getElementsByTagName("location").item(0).getTextContent().charAt(0);
					orientation = Integer.parseInt(eElement.getElementsByTagName("orientation").item(0).getTextContent());
					tora = Integer.parseInt(eElement.getElementsByTagName("tora").item(0).getTextContent());
					toda = Integer.parseInt(eElement.getElementsByTagName("toda").item(0).getTextContent());
					asda = Integer.parseInt(eElement.getElementsByTagName("asda").item(0).getTextContent());
					lda = Integer.parseInt(eElement.getElementsByTagName("lda").item(0).getTextContent());
					displacedThreshold = Integer.parseInt(eElement.getElementsByTagName("displacedThreshold").item(0).getTextContent());
					

					newRun = new Runway(orientation,location,tora,toda,asda,lda,displacedThreshold,50);
					listOfRunways.add(newRun);

				}
			}
			return new Airport(listOfRunways);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void write(Airport a, boolean b, ArrayList<Obstacle> obstacleOnRunway)
	{
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Airport");
			doc.appendChild(rootElement);
			for(Runway r : a.getListOfRunways())
			{
				// runway elements
				Element runway = doc.createElement("runway");
				rootElement.appendChild(runway);

				// set attribute to runway element
				Attr attr = doc.createAttribute("id");
				attr.setValue(r.toString());
				runway.setAttributeNode(attr);
				
				// name elements
				Element location = doc.createElement("location");
				location.appendChild(doc.createTextNode(String.valueOf(r.getLocation())));
				runway.appendChild(location);
				
				Element orientation = doc.createElement("orientation");
				orientation.appendChild(doc.createTextNode(Integer.toString(r.getOrientation())));
				runway.appendChild(orientation);
				
				Element tora = doc.createElement("tora");
				tora.appendChild(doc.createTextNode(Integer.toString(r.getTora())));
				runway.appendChild(tora);
				
				Element toda = doc.createElement("toda");
				toda.appendChild(doc.createTextNode(Integer.toString(r.getToda())));
				runway.appendChild(toda);
				
				Element asda = doc.createElement("asda");
				asda.appendChild(doc.createTextNode(Integer.toString(r.getAsda())));
				runway.appendChild(asda);
				
				Element lda = doc.createElement("lda");
				lda.appendChild(doc.createTextNode(Integer.toString(r.getLda())));
				runway.appendChild(lda);
				
				Element displacedThreshold = doc.createElement("displacedThreshold");
				displacedThreshold.appendChild(doc.createTextNode(Integer.toString(r.getDisplacedThreshold())));
				runway.appendChild(displacedThreshold);
				
				if (b = true){
					
					Element obstacle = doc.createElement("obstacle");
					runway.appendChild(obstacle);

					// set attribute to runway element
					Attr obattr = doc.createAttribute("type");
					obattr.setValue("Obstacle");
					obstacle.setAttributeNode(obattr);
					
				
					Obstacle o = obstacleOnRunway.get(0);
					Element one = doc.createElement("height");
					one.appendChild(doc.createTextNode(Integer.toString(o.getObstacleHeight())));
					obstacle.appendChild(one);
					
				}

			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addObatacle(ArrayList<Obstacle> obstacleOnRunway) {
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			Element root = doc.getDocumentElement();
			NodeList list = root.getChildNodes();
			Element first = (Element) root.getFirstChild();
			Element one = doc.createElement("first part");
			Obstacle o = obstacleOnRunway.get(0);
			one.appendChild(doc.createTextNode(Integer.toString(o.getObstacleHeight())));
			first.appendChild(one);
			
			 TransformerFactory transformerFactory = TransformerFactory
	                    .newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File("new airport.xml"));
	            transformer.transform(source, result);

	            System.out.println("Done");

			
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

}

