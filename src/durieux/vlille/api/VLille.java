package durieux.vlille.api;

import java.io.IOException;
import java.util.Map;

public interface VLille {
	/**
	 * 
	 * @return
	 */
	Map<Integer, Station> getStations();

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	Map<Integer, Station> getStationsWithDetails() throws IOException;
}
