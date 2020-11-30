/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package Coordinates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import escape.coordinate.*;
import escape.coordinate.Coordinate.CoordinateType;
import escape.exception.*;

/**
 * Description
 * @version Nov 19, 2020
 */
class TestCoordinateFactory
{
	private  CoordinateFactory CF;
	
	@BeforeEach
	void setup() {
		
		CF = new CoordinateFactory(CoordinateType.SQUARE);
	}

	@Test
	void testTooSmallThrow()
	{
		assertThrows(CoordinateExcpetion.class, () -> CF.makeCoordinate(30, -1));
		assertThrows(CoordinateExcpetion.class, () -> CF.makeCoordinate(-7, 10000000));
	}
	
	@Test
	void testMakeSquareCoordinate()
	{
		assertEquals(new CoordinateTypeSquare(1,1), CF.makeCoordinate(1, 1));
		
	}
	
	@Test
	void testMakeHexCoordinate()
	{
		assertEquals(null, new CoordinateFactory(CoordinateType.HEX).makeCoordinate(1, 1));
		
	}

}
