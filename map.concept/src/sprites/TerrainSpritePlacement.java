package sprites;

import map.panels.map.FieldType;

public class TerrainSpritePlacement {

	private final FieldType fieldType;
	private final boolean north;
	private final boolean east;
	private final boolean south;
	private final boolean west;

	public TerrainSpritePlacement(FieldType fieldType, boolean north, boolean east, boolean south, boolean west) {
		super();

		this.fieldType = fieldType;
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public boolean isNorth() {
		return north;
	}

	public boolean isEast() {
		return east;
	}

	public boolean isSouth() {
		return south;
	}

	public boolean isWest() {
		return west;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (east ? 1231 : 1237);
		result = prime * result + ((fieldType == null) ? 0 : fieldType.hashCode());
		result = prime * result + (north ? 1231 : 1237);
		result = prime * result + (south ? 1231 : 1237);
		result = prime * result + (west ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TerrainSpritePlacement other = (TerrainSpritePlacement) obj;
		if (east != other.east)
			return false;
		if (fieldType != other.fieldType)
			return false;
		if (north != other.north)
			return false;
		if (south != other.south)
			return false;
		if (west != other.west)
			return false;
		return true;
	}
}