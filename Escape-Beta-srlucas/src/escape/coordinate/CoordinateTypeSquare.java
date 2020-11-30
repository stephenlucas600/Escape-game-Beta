/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.coordinate;

import static escape.coordinate.Coordinate.CoordinateType.SQUARE;

/**
 * Create a Coordinate for Square boards
 * 
 * @version Nov 10, 2020
 */
public class CoordinateTypeSquare implements EscapeCoordinate
{

	private int x, y;

	public CoordinateTypeSquare(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * @see escape.coordinate.Coordinate#DistanceTo(escape.coordinate.Coordinate)
	 * @throw c was not a CoordinateTypeSquare
	 */
	@Override
	public int DistanceTo(Coordinate c)
	{
		// if C is a CoordinateTypeSquare, convert generic Coordinate to
		// CoordinateTypeSquare
		// try{
		CoordinateTypeSquare sc = (CoordinateTypeSquare) c;
		// get dinstacne with Hypothinus
		return (int) Math.hypot(getDirectHorizontalDistance(sc),
				getDirectVerticalDistance(sc));
		// }
		// catch(Exception e) {
		// throw new CoordinateExcpetion("DistanceTo coordinates are not Square", e);
		// }

	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinateTypeSquare other = (CoordinateTypeSquare) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/*
	 * @see escape.coordinate.EscapeCoordinate#getCoordinateType()
	 */
	@Override
	public CoordinateType getCoordinateType()
	{
		return SQUARE;
	}

	/**
	 * @see escape.coordinate.EscapeCoordinate#getDicection(escape.coordinate.EscapeCoordinate)
	 * @return [0] right; [1] Left; [2] Up; [3] Down; [4] right Up; [5] right Down; [6]
	 *         Left Up; [7] Left Down; [-1] not make a direct line
	 */
	@Override
	public int getDicection(EscapeCoordinate other)
	{
		int difX = other.getX() - x;
		int difY = other.getY() - y;

		if (difX > 0 && difY == 0)
			return 0; // right
		else if (difX < 0 && difY == 0)
			return 1; // Left
		else if (difX == 0 && difY > 0)
			return 2; // Up
		else if (difX == 0 && difY < 0)
			return 3; // Down
		else if (difX > 0 && difY > 0 && (Math.abs(difX) - Math.abs(difY) == 0))
			return 4; // right up
		else if (difX > 0 && difY < 0 && (Math.abs(difX) - Math.abs(difY) == 0))
			return 5; // right down
		else if (difX < 0 && difY > 0 && (Math.abs(difX) - Math.abs(difY) == 0))
			return 6; // Left up
		else if (difX < 0 && difY < 0 && (Math.abs(difX) - Math.abs(difY) == 0))
			return 7; // Left down

		return -1;
	}

	/*
	 * @see escape.coordinate.EscapeCoordinate#getHorizontalDistance(escape.coordinate.
	 * EscapeCoordinate)
	 */
	@Override
	public int getDirectHorizontalDistance(EscapeCoordinate other)
	{
		return Math.abs(x - other.getX());
	}

	/*
	 * @see escape.coordinate.EscapeCoordinate#getVerticalDistance(escape.coordinate.
	 * EscapeCoordinate)
	 */
	@Override
	public int getDirectVerticalDistance(EscapeCoordinate c)
	{
		return Math.abs(y - c.getY());

	}

	/*
	 * @see escape.required.XYCoordinate#getX()
	 */
	@Override
	public int getX()
	{
		return x;
	}

	/*
	 * @see escape.required.XYCoordinate#getY()
	 */
	@Override
	public int getY()
	{
		return y;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * @see escape.coordinate.EscapeCoordinate#isNear(escape.coordinate.EscapeCoordinate)
	 * @return true if findC is one space diangnal, horazontal or vertical from this
	 *         Coordinate.
	 */
	@Override
	public boolean isNear(EscapeCoordinate findC)
	{
		int xdif = getDirectHorizontalDistance(findC);
		int ydif = getDirectVerticalDistance(findC);

		if (xdif == 0 && ydif == 1) {// vertical
			return true;
		} else if (xdif == 1 && ydif == 0) {// horazontal
			return true;
		} else if (xdif == 1 && ydif == 1) {// diangnal
			return true;
		}
		return false;
	}

	/**
	 * @see escape.coordinate.EscapeCoordinate#isNearCluster()
	 * @return CoordinateTypeSquare[8]
	 */
	@Override
	public EscapeCoordinate[] isNearCluster()
	{
		CoordinateTypeSquare[] isNearCluster = new CoordinateTypeSquare[8];
		isNearCluster[0] = new CoordinateTypeSquare(x + 1, y); // right
		isNearCluster[1] = new CoordinateTypeSquare(x - 1, y); // left
		isNearCluster[2] = new CoordinateTypeSquare(x, y + 1); // up
		isNearCluster[3] = new CoordinateTypeSquare(x, y - 1); // down
		isNearCluster[4] = new CoordinateTypeSquare(x + 1, y + 1); // right up
		isNearCluster[5] = new CoordinateTypeSquare(x + 1, y - 1); // right down
		isNearCluster[6] = new CoordinateTypeSquare(x - 1, y + 1); // left up
		isNearCluster[7] = new CoordinateTypeSquare(x - 1, y - 1); // left down
		return isNearCluster;
	}

	/*
	 * @see escape.coordinate.EscapeCoordinate#SameCoordinate(escape.coordinate.EscapeCoordinate,
	 *      escape.location.LocationType)
	 */
	@Override
	public boolean SameCoordinate(EscapeCoordinate other)
	{
		if (other.getX() == x && other.getY() == y) {
			return true;
		}
		return false;
	}

}
