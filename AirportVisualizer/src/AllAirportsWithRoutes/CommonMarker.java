package AllAirportsWithRoutes;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

/**
 * Implements a common marker for cities and earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 *
 */
public abstract class CommonMarker extends SimplePointMarker {

	// Records whether this marker has been clicked (most recently)
	protected boolean clicked = false;

	public CommonMarker(final Location location) {
		super(location);
	}

	public CommonMarker(final Location location,
			final java.util.HashMap<java.lang.String, java.lang.Object> properties) {
		super(location, properties);
	}

	// Getter method for clicked field
	public boolean getClicked() {
		return clicked;
	}

	// Setter method for clicked field
	public void setClicked(final boolean state) {
		clicked = state;
	}

	// Common piece of drawing method for markers;
	// YOU WILL IMPLEMENT.
	// Note that you should implement this by making calls
	// drawMarker and showTitle, which are abstract methods
	// implemented in subclasses
	public void draw(final PGraphics pg, final float x, final float y) {
		// For starter code just drawMaker(...)
		if (!hidden) {
			drawMarker(pg, x, y);
			if (selected) {
				showTitle(pg, x, y);
			}
		}
	}

	public abstract void drawMarker(PGraphics pg, float x, float y);

	public abstract void showTitle(PGraphics pg, float x, float y);
}