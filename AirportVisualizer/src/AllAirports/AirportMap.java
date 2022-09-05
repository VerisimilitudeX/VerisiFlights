package AllAirports;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import parsing.ParseFeed;
import processing.core.PApplet;

/**
 * An applet that shows airports (and routes) on a world map.
 *
 * @author Adam Setters and the UC San Diego Intermediate Software Development MOOC team
 */
public class AirportMap extends PApplet {

  UnfoldingMap map;
  private List<Marker> airportList;
  List<Marker> routeList;

  public void setup() {
    // setting up PAppler
    size(800, 600, OPENGL);

    // setting up map and default events
    map = new UnfoldingMap(this, 50, 50, 750, 550);
    MapUtils.createDefaultEventDispatcher(this, map);

    // get features from airport data
    final List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");

    // list for markers, hashmap for quicker access when matching with routes
    airportList = new ArrayList<Marker>();
    final HashMap<Integer, Location> airports = new HashMap<Integer, Location>();

    // create markers from features
    for (final PointFeature feature : features) {
      final AirportMarker m = new AirportMarker(feature);

      m.setRadius(5);
      airportList.add(m);

      // put airport in hashmap with OpenFlights unique id for key
      airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
    }

    // parse route data
    final List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
    routeList = new ArrayList<Marker>();
    for (final ShapeFeature route : routes) {

      // get source and destination airportIds
      final int source = Integer.parseInt((String) route.getProperty("source"));
      final int dest = Integer.parseInt((String) route.getProperty("destination"));

      // get locations for airports on route
      if (airports.containsKey(source) && airports.containsKey(dest)) {
        route.addLocation(airports.get(source));
        route.addLocation(airports.get(dest));
      }

      new SimpleLinesMarker(route.getLocations(), route.getProperties());

      // System.out.println(sl.getProperties());

      // UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
      // routeList.add(sl);
    }

    // UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
    // map.addMarkers(routeList);

    map.addMarkers(airportList);
  }

  public void draw() {
    background(0);
    map.draw();
  }
}
