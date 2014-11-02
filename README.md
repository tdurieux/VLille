VLille [![Build Status](https://travis-ci.org/tdurieux/VLille.svg)](https://travis-ci.org/tdurieux/VLille)
======

Java VLille API


## Dependencies

- JSOUP (http://jsoup.org)

# Interfaces

- Station
- VLille

# Implementations

- StationImpl
- VLilleImpl

# Usage

```Java


import durieux.vlille.Station;
import durieux.vlille.VLille;
import durieux.vlille.VLilleImpl;

final VLille vlille = new VLilleImpl();
final Map<Integer, Station> stations = vlille.getStationsWithDetails();
// or
final Map<Integer, Station> stations = vlille.getStations();
```
# Execute

```bash
java -jar vlille.jar
```

# Output

```JSON
{
  "stations": [
    {
      "id": 1,
      "name": "Lille Metropole",
      "address": "LMCU RUE DU BALLON",
      "bikes": 36,
      "attachs": 15,
      "latidude": 50.6419,
      "longitude": 3.07599
    },
    {
      "id": 2,
      "name": "Universite Catholique",
      "address": "RUE DU PORT BD VAUBAN",
      "bikes": 31,
      "attachs": 27,
      "latidude": 50.6322,
      "longitude": 3.04613
    },
    ...
}
```
