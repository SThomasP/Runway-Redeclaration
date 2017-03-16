package controller;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFile {

   public  ReadXMLFile() {

    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

	boolean brname = false;
	boolean boname = false;
	boolean bdirection = false;
	boolean btora = false;
	boolean btoda = false;
	boolean basda = false;
	boolean blda = false;
	boolean bcondition = false;
	boolean bheight = false;
	boolean btype = false;
	boolean bdistancefromcl = false;
	boolean bdistancefromthreshold = false;
	boolean blevelofdanger = false;

	public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {

		//System.out.println("Start Element :" + qName);

		if (qName.equalsIgnoreCase("RNAME")) {
			brname = true;
		}
		if (qName.equalsIgnoreCase("DIRECTION")) {
			bdirection = true;
		}
		if (qName.equalsIgnoreCase("TORA")) {
			btora = true;
		}
		if (qName.equalsIgnoreCase("TODA")) {
			btoda = true;
		}
		if (qName.equalsIgnoreCase("ASDA")) {
			basda = true;
		}
		if (qName.equalsIgnoreCase("LDA")) {
			blda = true;
		}
		if (qName.equalsIgnoreCase("CONDITION")) {
			bcondition = true;
		}
		
		
		if (qName.equalsIgnoreCase("ONAME")) {
			boname = true;
		}
		if (qName.equalsIgnoreCase("TYPE")) {
			btype = true;
		}
		if (qName.equalsIgnoreCase("HEIGHT")) {
			bheight = true;
		}
		if (qName.equalsIgnoreCase("DISTANCEFROMCL")) {
			bdistancefromcl = true;
		}
		if (qName.equalsIgnoreCase("DISTANCEFROMTHRESHOLD")) {
			bdistancefromthreshold = true;
		}
		if (qName.equalsIgnoreCase("LEVELOFDANGER")) {
			blevelofdanger = true;
		}

	}

	public void endElement(String uri, String localName,
		String qName) throws SAXException {

		//System.out.println("End Element :" + qName);
		System.out.println(" ");

	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (brname) {
			System.out.println("Runway Name : " + new String(ch, start, length));
			brname = false;
		}

		if (bdirection) {
			System.out.println("Direction: " + new String(ch, start, length));
			bdirection = false;
		}

		if (btora) {
			System.out.println("TORA : " + new String(ch, start, length));
			btora = false;
		}
		if (btoda) {
			System.out.println("TODA : " + new String(ch, start, length));
			btoda = false;
		}
		if (basda) {
			System.out.println("ASDA : " + new String(ch, start, length));
			basda = false;
		}
		if (blda) {
			System.out.println("LDA : " + new String(ch, start, length));
			blda = false;
		}

		if (bcondition) {
			System.out.println("Condition : " + new String(ch, start, length));
			bcondition = false;
		}
		if (boname) {
			System.out.println("Obstacle Name : " + new String(ch, start, length));
			boname = false;
		}
		if (btype) {
			System.out.println("Type : " + new String(ch, start, length));
			btype = false;
		}
		if (bheight) {
			System.out.println("Height : " + new String(ch, start, length));
			bheight = false;
		}
		if (bdistancefromcl) {
			System.out.println("Distance from Centre Line : " + new String(ch, start, length));
			bdistancefromcl = false;
		}
		if (bdistancefromthreshold) {
			System.out.println("Distance from Threshold : " + new String(ch, start, length));
			bdistancefromthreshold = false;
		}
		if (blevelofdanger) {
			System.out.println("Level of danger : " + new String(ch, start, length));
			blevelofdanger = false;
		}

	}

     };
     	
     //CHANGE FILE PATH
       saxParser.parse("c:\\Users\\Polska\\Desktop\\Runway-Redeclaration-master\\src\\controller\\file.xml", handler);

     } catch (Exception e) {
       e.printStackTrace();
     }

   }

}