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

import model.Obstacle;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class ReadObstacleXMLFile {


	File file = new File("objects.xml");
	public ArrayList<Obstacle> read() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("obstacle");
			ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Obstacle newObs;
					String name;
					int height;
					int width;
					int length;
					name = eElement.getElementsByTagName("name").item(0).getTextContent();
					height = Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent());
					width = Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent());
					length = Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent());
					newObs = new Obstacle(name,height,width,length);
					listOfObstacles.add(newObs);

				}
			}
			return listOfObstacles;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void write(ArrayList<Obstacle> obstacleList)
	{
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("obstacleList");
			doc.appendChild(rootElement);
			int counter = 1;
			for(Obstacle o : obstacleList)
			{
				// obstacle elements
				Element obstacle = doc.createElement("obstacle");
				rootElement.appendChild(obstacle);

				// set attribute to obstacle element
				Attr attr = doc.createAttribute("id");
				attr.setValue(String.valueOf(counter));
				obstacle.setAttributeNode(attr);
				
				// name elements
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(o.getName()));
				obstacle.appendChild(name);
				
				Element height = doc.createElement("height");
				height.appendChild(doc.createTextNode(Integer.toString(o.getObstacleHeight())));
				obstacle.appendChild(height);
				
				Element width = doc.createElement("width");
				width.appendChild(doc.createTextNode(Integer.toString(o.getObstacleLength())));
				obstacle.appendChild(width);
				
				Element length = doc.createElement("length");
				length.appendChild(doc.createTextNode(Integer.toString(o.getObstacleLength())));
				obstacle.appendChild(length);

				counter +=1;
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

}

