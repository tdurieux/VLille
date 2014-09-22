package durieux.vlille.main;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import durieux.vlille.api.Station;
import durieux.vlille.api.VLille;
import durieux.vlille.api.VLilleImpl;

public class Main {
	public static void main(final String[] args) throws IOException {
		final VLille vlille = new VLilleImpl();
		final Map<Integer, Station> stations = vlille.getStationsWithDetails();

		System.out.println("{\n  \"stations\": [");
		final Collection<Station> values = stations.values();
		final Iterator<Station> it = values.iterator();
		while (it.hasNext()) {
			final Station station = it.next();
			System.out.println("    {");
			System.out.println("      \"id\": " + station.getId() + ",");
			System.out
					.println("      \"name\": \"" + station.getName() + "\",");
			System.out.println("      \"address\": \"" + station.getAddress()
					+ "\",");
			System.out.println("      \"bikes\": " + station.getBikes() + ",");
			System.out.println("      \"attachs\": " + station.getAttachs()
					+ ",");
			System.out.println("      \"latitude\": " + station.getLat() + ",");
			System.out.println("      \"longitude\": " + station.getLng() + ",");
			System.out.println("      \"paiement\": \"" + station.getPaiement() + "\"");
			System.out.println("    }" + (it.hasNext() ? "," : ""));
		}
		System.out.println("  ]\n}");
	}
}
