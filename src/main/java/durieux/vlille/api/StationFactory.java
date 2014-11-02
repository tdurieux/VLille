package durieux.vlille.api;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class StationFactory {
	public static Station createStationFromXML(Node xmlStation) {
		int stationId = -1;
		String stationName = "";
		double stationLat = 0.0;
		double stationLng = 0.0;
		
		final NamedNodeMap attributes = xmlStation.getAttributes();
		for (int j = 0; j < attributes.getLength(); j++) {
			final Node attribute = attributes.item(j);
			try {
				if (attribute.getNodeName().equals("id")) {
					stationId = Integer.parseInt(attribute.getTextContent());
				} else if (attribute.getNodeName().equals("lat")) {
					stationLat = Double.parseDouble(attribute.getTextContent());
				} else if (attribute.getNodeName().equals("lng")) {
					stationLng = Double.parseDouble(attribute.getTextContent());
				} else if (attribute.getNodeName().equals("name")) {
					stationName = attribute.getTextContent();
				}
			}catch(NumberFormatException e){
				throw new RuntimeException("Unable to create a Station: invalid XML");
			}
		}
		if (stationId == -1 || stationName.equals("")) {
			throw new RuntimeException("Unable to create a Station: invalid XML");
		}
		return new StationImpl(stationId, stationName, stationLat, stationLng);
	}
}
