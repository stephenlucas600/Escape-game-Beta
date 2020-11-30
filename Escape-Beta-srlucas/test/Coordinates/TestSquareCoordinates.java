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
import static escape.coordinate.Coordinate.CoordinateType.*;

/**
 * Description
 * @version Nov 18, 2020
 */
class TestSquareCoordinates
{

	private CoordinateTypeSquare testsquard1;
	private CoordinateTypeSquare testsquard2;
	private CoordinateTypeSquare testsquare3;
	private CoordinateTypeSquare testsquard4;
	private CoordinateTypeSquare testsquard5;
	
	@BeforeEach
	void setup() {
		
		testsquard1 = new CoordinateTypeSquare(1,1);
		testsquard2 = new CoordinateTypeSquare(4,4);
		
		testsquare3 = new CoordinateTypeSquare(4,1);
		testsquard4 = new CoordinateTypeSquare(3,4);
		testsquard5 = new CoordinateTypeSquare(1,1);
		
	}
	
	@Test
	void TestisSqure()
	{
		assertEquals(SQUARE,testsquard1.getCoordinateType());
		
	}
	
	@Test
	void TestCoordinateTypeSquareDirectHorizontilVerticalDistance()
	{
		assertEquals(testsquard1.getDirectVerticalDistance(testsquard2), 3);
		assertEquals(testsquard2.getDirectVerticalDistance(testsquard1), 3);
		assertEquals(testsquard1.getDirectHorizontalDistance(testsquard2), 3);
		assertEquals(testsquard2.getDirectHorizontalDistance(testsquard1), 3);
	}
	
	@Test
	void TestCoordinateTypeRecDirectHorizontilVerticalDistance()
	{
		assertEquals(testsquare3.getDirectVerticalDistance(testsquard4), 3);
		assertEquals(testsquard4.getDirectVerticalDistance(testsquare3), 3);
		assertEquals(testsquard1.getDirectHorizontalDistance(testsquard4), 2);
		assertEquals(testsquard4.getDirectHorizontalDistance(testsquard1), 2);
	}
	
	@Test
	void TestCoordinateTypeSquareIsSame()
	{
		assertFalse(testsquard1.SameCoordinate(testsquard2));
		assertTrue(testsquard1.SameCoordinate(testsquard1));
		assertTrue(testsquard2.SameCoordinate(testsquard2));
		assertTrue(testsquard1.SameCoordinate(testsquard1));
		assertTrue(testsquard1.SameCoordinate(testsquard5));
		assertTrue(testsquard1.SameCoordinate(new CoordinateTypeSquare(1,1)));
		assertFalse(testsquard1.SameCoordinate(testsquare3));
		assertFalse(testsquard1.SameCoordinate(new CoordinateTypeSquare(2,1)));
		assertFalse(testsquard1.SameCoordinate(new CoordinateTypeSquare(1,2)));
		assertFalse(testsquard1.SameCoordinate(new CoordinateTypeSquare(2,1)));
		
		
	}
	
	@Test
	void TestCoordinateTypeSQHashCode()
	{
		assertEquals(testsquard5.hashCode(),testsquard1.hashCode());
	}
	
	@Test
	void TestCoordinateTypeSQIsNear()
	{
		assertFalse(testsquard1.isNear(testsquard4));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(0,0)));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(1,0)));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(0,1)));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(2,0)));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(0,2)));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(1,2)));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(2,1)));
		assertTrue(testsquard1.isNear(new CoordinateTypeSquare(2,2)));
		
		assertFalse(testsquard1.isNear(new CoordinateTypeSquare(3,3)));
		assertFalse(testsquard1.isNear(new CoordinateTypeSquare(2,3)));
		assertFalse(testsquard1.isNear(new CoordinateTypeSquare(3,1)));	
		assertFalse(testsquard1.isNear(new CoordinateTypeSquare(1,3)));	
	}
	
	
	@Test
	void TestCoordinateTypeSquareEquals() {
		assertTrue(testsquard1.equals(testsquard1));
		assertFalse(testsquard1.equals(testsquard2));
		assertFalse(testsquard1.equals(null));
		assertTrue(testsquard1.equals(testsquard5));
		assertFalse(testsquard1.equals(testsquard4));
		assertFalse(testsquard1.equals( new CoordinateTypeSquare(1,4)));
		assertFalse(testsquard1.equals(null));
		assertTrue(testsquard5.equals(testsquard1));
	}
	
	@Test
	void TestCoordinateTypeSquareDistanceTo() {
		assertEquals(3,testsquard1.DistanceTo(testsquard4));
		
	}
	@Test
	void TestCoordinateTypeSquareisNearCluster() {
		EscapeCoordinate[] isNearCluster = new CoordinateTypeSquare[8];
		isNearCluster[0]= new CoordinateTypeSquare(5,4); // right
		isNearCluster[1]= new CoordinateTypeSquare(3,4); // left
		isNearCluster[2]= new CoordinateTypeSquare(4,5); // up
		isNearCluster[3]= new CoordinateTypeSquare(4,3); // down
		isNearCluster[4]= new CoordinateTypeSquare(5,5); // right up 
		isNearCluster[5]= new CoordinateTypeSquare(5,3); // right down 
		isNearCluster[6]= new CoordinateTypeSquare(3,5); // left up
		isNearCluster[7]= new CoordinateTypeSquare(3,3); // left down
		
		for(int i = 0; i < 8; i++) {
			assertTrue(isNearCluster[i].equals(testsquard2.isNearCluster()[i]));
		}
		
	}
	
	@Test
	void TestCoordinateTypeSquareOnSameLine() {
		EscapeCoordinate[] isNearCluster = new CoordinateTypeSquare[8];
		isNearCluster[0]= new CoordinateTypeSquare(5,4); // right
		isNearCluster[1]= new CoordinateTypeSquare(3,4); // left
		isNearCluster[2]= new CoordinateTypeSquare(4,5); // up
		isNearCluster[3]= new CoordinateTypeSquare(4,3); // down
		isNearCluster[4]= new CoordinateTypeSquare(5,5); // right up 
		isNearCluster[5]= new CoordinateTypeSquare(5,3); // right down 
		isNearCluster[6]= new CoordinateTypeSquare(3,5); // left up
		isNearCluster[7]= new CoordinateTypeSquare(3,3); // left down
		
		EscapeCoordinate notonline = new CoordinateTypeSquare(2,1);
		EscapeCoordinate notonline2 = new CoordinateTypeSquare(5,1);
		EscapeCoordinate notonline3 = new CoordinateTypeSquare(5,9);
		EscapeCoordinate notonline4 = new CoordinateTypeSquare(3,9);
		EscapeCoordinate notonline5 = new CoordinateTypeSquare(9,3);
		EscapeCoordinate notonline6 = new CoordinateTypeSquare(4,4);
		
		for(int i = 0; i < 8; i++) {
			assertEquals(i, testsquard2.getDicection(isNearCluster[i]));
		}
		assertEquals(-1, testsquard2.getDicection(notonline));
		assertEquals(-1, testsquard2.getDicection(notonline2));
		assertEquals(-1, testsquard2.getDicection(notonline3));
		assertEquals(-1, testsquard2.getDicection(notonline4));
		assertEquals(-1, testsquard2.getDicection(notonline5));
		assertEquals(-1, testsquard2.getDicection(notonline6));
		
	}

}
