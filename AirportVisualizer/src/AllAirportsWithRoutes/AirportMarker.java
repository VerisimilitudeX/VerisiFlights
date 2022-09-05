package AllAirportsWithRoutes;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import java.util.List;
import processing.core.PGraphics;

/**
 * A class to represent AirportMarkers on a world map.
 *
 * @author Adam Setters and the UC San Diego Intermediate Software Development MOOC team
 */
public class AirportMarker extends CommonMarker {
  public static List<SimpleLinesMarker> routes;

  public AirportMarker(final Feature city) {
    super(((PointFeature) city).getLocation(), city.getProperties());
  }

  @Override
  public void drawMarker(final PGraphics pg, final float x, final float y) {
    pg.fill(11);
    pg.ellipse(x, y, 5, 5);
  }

  @Override
  public void showTitle(final PGraphics pg, final float x, final float y) {
    // show rectangle with title

    // show routes

  }
}
