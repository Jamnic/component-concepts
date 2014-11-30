package map.panels.map;

import map.concept.Coords;

public class Field {

	private Coords coords;

	private FieldType type;

	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

}
