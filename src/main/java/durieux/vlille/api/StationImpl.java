package durieux.vlille.api;

public class StationImpl implements Station {
	private int id;
	private String name;
	private String city;
	private String address;
	private int status;
	private int bikes;
	private int attachs;
	private String paiement;
	private double lat;
	private double lng;

	public StationImpl(final int id, final String nom, final double lat,
			final double lng) {
		super();
		this.id = id;
		this.name = nom;
		this.lat = lat;
		this.lng = lng;
	}

	public StationImpl(final int id, final String nom, final String ville,
			final String address, final int status, final int bikes,
			final int attachs, final String paiement) {
		super();
		this.id = id;
		this.name = nom;
		this.city = ville;
		this.address = address;
		this.status = status;
		this.bikes = bikes;
		this.attachs = attachs;
		this.paiement = paiement;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getCity() {
		return this.city;
	}

	@Override
	public void setCity(final String city) {
		this.city = city;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public void setAddress(final String address) {
		this.address = address;
	}

	@Override
	public int getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(final int status) {
		this.status = status;
	}

	@Override
	public int getBikes() {
		return this.bikes;
	}

	@Override
	public void setBikes(final int bikes) {
		this.bikes = bikes;
	}

	@Override
	public int getAttachs() {
		return this.attachs;
	}

	@Override
	public void setAttachs(final int attachs) {
		this.attachs = attachs;
	}

	@Override
	public String getPaiement() {
		return this.paiement;
	}

	@Override
	public void setPaiement(final String paiement) {
		this.paiement = paiement;
	}

	@Override
	public double getLat() {
		return this.lat;
	}

	@Override
	public void setLat(final double lat) {
		this.lat = lat;
	}

	@Override
	public double getLng() {
		return this.lng;
	}

	@Override
	public void setLng(final double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "StationImpl [id=" + this.id + ", nom=" + this.name + ", ville="
				+ this.city + ", address=" + this.address + ", status="
				+ this.status + ", bikes=" + this.bikes + ", attachs="
				+ this.attachs + ", paiement=" + this.paiement + ", lat="
				+ this.lat + ", lng=" + this.lng + "]";
	}

}
