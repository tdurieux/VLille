package test.durieux.vlille.api;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import durieux.vlille.api.Station;
import durieux.vlille.api.StationFactory;

public class StationFactoryTest {

	private Document parseXML(String xml) throws ParserConfigurationException,
			SAXException, IOException {
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		final DocumentBuilder db = dbf.newDocumentBuilder();

		BufferedReader br = new BufferedReader(new StringReader(xml));
		final InputSource is = new InputSource(br);

		final Document doc = db.parse(is);
		doc.getDocumentElement().normalize();
		return doc;
	}

	@Test
	public void createStationFromXMLTest() throws Exception {
		String xml = "<marker id=\"1\" lat=\"50.6419\" lng=\"3.07599\" name=\"Lille Metropole\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		Station station = StationFactory.createStationFromXML(nodeList.item(0));
		assertEquals(station.getId(), 1);
		assertEquals(station.getLat(), 50.6419, 4);
		assertEquals(station.getLng(), 3.07599, 4);
		assertEquals(station.getName(), "Lille Metropole");
	}
	
	@Test(expected=RuntimeException.class)
	public void createStationFromXMLInvalidTest() throws Exception {
		String xml = "<marker id=\"Blabla\" lat=\"50.6419\" lng=\"3.07599\" name=\"Lille Metropole\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		StationFactory.createStationFromXML(nodeList.item(0));
		fail("The parser must fail");
	}
	
	@Test
	public void createStationFromBigIdTest() throws Exception {
		String xml = "<marker id=\"999\" lat=\"50.6419\" lng=\"3.07599\" name=\"Lille Metropole\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		Station station = StationFactory.createStationFromXML(nodeList.item(0));
		assertEquals(station.getId(), 999);
		assertEquals(station.getLat(), 50.6419, 4);
		assertEquals(station.getLng(), 3.07599, 4);
		assertEquals(station.getName(), "Lille Metropole");
	}
	
	@Test(expected=RuntimeException.class)
	public void createStationFromEmptyNameTest() throws Exception {
		String xml = "<marker id=\"1\" lat=\"50.6419\" lng=\"3.07599\" name=\"\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		Station station = StationFactory.createStationFromXML(nodeList.item(0));
		fail("The parser must fail");
	}
	
	@Test(expected=RuntimeException.class)
	public void createStationFromXMLEmptyIdTest() throws Exception {
		String xml = "<marker id=\"\" lat=\"50.6419\" lng=\"3.07599\" name=\"Lille Metropole\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		StationFactory.createStationFromXML(nodeList.item(0));
		fail("The parser must fail");
	}
	
	@Test(expected=RuntimeException.class)
	public void createStationFromWihtoutNameTest() throws Exception {
		String xml = "<marker id=\"1\" lat=\"50.6419\" lng=\"3.07599\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		Station station = StationFactory.createStationFromXML(nodeList.item(0));
		fail("The parser must fail");
	}
	
	@Test(expected=RuntimeException.class)
	public void createStationFromXMLWihtoutIdTest() throws Exception {
		String xml = "<marker lat=\"50.6419\" lng=\"3.07599\" name=\"Lille Metropole\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		StationFactory.createStationFromXML(nodeList.item(0));
		fail("The parser must fail");
	}
	

	@Test(expected=RuntimeException.class)
	public void createStationFromXMLInvalidLatTest() throws Exception {
		String xml = "<marker id=\"1\" lat=\"wer\" lng=\"3.07599\" name=\"Lille Metropole\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		StationFactory.createStationFromXML(nodeList.item(0));
		fail("The parser must fail");
	}
	

	@Test(expected=RuntimeException.class)
	public void createStationFromXMLInvalidLngTest() throws Exception {
		String xml = "<marker id=\"1\" lat=\"50.6419\" lng=\"qwe\" name=\"Lille Metropole\" />";
		Document doc = parseXML(xml);
		final NodeList nodeList = doc.getElementsByTagName("marker");
		StationFactory.createStationFromXML(nodeList.item(0));
		fail("The parser must fail");
	}
}
