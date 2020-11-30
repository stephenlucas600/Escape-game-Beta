/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.coordinate;

import escape.coordinate.Coordinate.CoordinateType;
import escape.exception.*;

/**
 * Create coordinates.
 * 
 * @version Nov 17, 2020
 */
public class CoordinateFactory<C extends EscapeCoordinate>
{
	CoordinateType coordinateType;

	/**
	 * Description
	 * 
	 * @param coordinateType
	 */
	public CoordinateFactory(CoordinateType coordinateType)
	{
		this.coordinateType = coordinateType;
	}

	/**
	 * make cordinate based on CoordinateType
	 * 
	 * @param x
	 * @param y
	 * @return return coordinate of oppropiate type else return null.
	 * @throws x
	 *             is too small
	 * @throws y
	 *             is too small.
	 */
	public EscapeCoordinate makeCoordinate(int x, int y)
	{
		if (x < 0) {
			throw new CoordinateExcpetion("x too small");
		}
		if (y < 0) {
			throw new CoordinateExcpetion("y too small");
		}
		if (coordinateType == CoordinateType.SQUARE) {
			return new CoordinateTypeSquare(x, y);
		}

		return null;
	}

}
