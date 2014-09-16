package durieux.vlille;

public interface Station {
	int getId();

	void setId(int id);

	String getName();

	void setName(String name);

	String getCity();

	void setCity(String city);

	String getAddress();

	void setAddress(String address);

	int getStatus();

	void setStatus(int status);

	int getBikes();

	void setBikes(int bikes);

	int getAttachs();

	void setAttachs(int attachs);

	String getPaiement();

	void setPaiement(String paiement);

	double getLat();

	void setLat(double lat);

	double getLng();

	void setLng(double lng);
}
