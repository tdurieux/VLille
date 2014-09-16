package durieux.vlille;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VLilleImpl implements VLille {
	private static String URLVLILLE = "http://www.vlille.fr/stations/les-stations-vlille.aspx";
	private static String URLVLILLEXML = "http://www.vlille.fr/stations/xml-stations.aspx";

	private final Map<Integer, Station> stations;
	private boolean hasDetails = false;

	public VLilleImpl() {
		this.stations = new HashMap<Integer, Station>();
	}

	@Override
	public Map<Integer, Station> getStations() {
		if (this.stations.size() > 0) {
			return this.stations;
		}
		final XMLFromUrl xmlParser = new XMLFromUrl(URLVLILLEXML,
				Charset.forName("UTF-8"));
		;
		final Document doc = xmlParser.parse();
		final NodeList nodeList = doc.getElementsByTagName("marker");
		for (int i = 0; i < nodeList.getLength(); i++) {
			int stationId = -1;
			String nom = "";
			double lat = 0.0;
			double lng = 0.0;
			final Node item = nodeList.item(i);
			final NamedNodeMap attributes = item.getAttributes();
			for (int j = 0; j < attributes.getLength(); j++) {
				final Node attribute = attributes.item(j);
				if (attribute.getNodeName().equals("id")) {
					stationId = Integer.parseInt(attribute.getTextContent());
				} else if (attribute.getNodeName().equals("lat")) {
					lat = Double.parseDouble(attribute.getTextContent());
				} else if (attribute.getNodeName().equals("lng")) {
					lng = Double.parseDouble(attribute.getTextContent());
				} else if (attribute.getNodeName().equals("name")) {
					nom = attribute.getTextContent();
				}
			}
			if (stationId != -1) {
				this.stations.put(stationId, new StationImpl(stationId, nom,
						lat, lng));
			}
		}
		return this.stations;
	}

	public Map<Integer, Station> getStationsWithDetails(final boolean refresh)
			throws IOException {
		this.hasDetails = false;
		return this.getStationsWithDetails();
	}

	@Override
	public Map<Integer, Station> getStationsWithDetails() throws IOException {
		if (this.hasDetails) {
			return this.stations;
		}
		this.getStations();
		final org.jsoup.nodes.Document doc = Jsoup.connect(URLVLILLE).get();
		final Elements lines = doc
				.select("#ctl00_Contenu_ListeStations1_ListViewStations_itemPlaceholderContainer tr");
		for (final Element line : lines) {
			final Elements elements = line.select("td span");
			int stationId = -1, nombreVeloDispo = 0, nombreBorneDispo = 0;
			String stationAdresse = "", nom = "", ville = "";
			for (final Element value : elements) {
				final String id = value.id();
				if (id.contains("LibelleLabel")) {
					stationId = Integer.parseInt(value.text());
				} else if (id.contains("NomLabel")) {
					nom = value.text();
				} else if (id.contains("Label1")) {
					stationAdresse = value.text();
				} else if (id.contains("Label2")) {
					ville = value.text();
				} else if (id.contains("NbreVelosDispoLabel")) {
					nombreVeloDispo = Integer.parseInt(value.text());
				} else if (id.contains("NbreBornesDispoLabel")) {
					nombreBorneDispo = Integer.parseInt(value.text());
				}
			}
			if (stationId != -1) {
				Station station;
				if (this.stations.get(stationId) != null) {
					station = this.stations.get(stationId);
					station.setCity(ville);
					station.setAddress(stationAdresse);
					station.setStatus(1);
					station.setBikes(nombreBorneDispo + nombreVeloDispo);
					station.setAttachs(nombreVeloDispo);
				} else {
					station = new StationImpl(stationId, nom, ville,
							stationAdresse, 1, nombreBorneDispo
									+ nombreVeloDispo, nombreVeloDispo, "");
				}
				this.stations.put(stationId, station);
			}
		}
		this.hasDetails = true;
		return this.stations;
	}

}
