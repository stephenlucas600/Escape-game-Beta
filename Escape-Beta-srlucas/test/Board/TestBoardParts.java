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

package Board;

import static escape.location.LocationType.*;
import static escape.piece.EscapePiece.MovementPattern.DIAGONAL;
import static escape.piece.EscapePiece.PieceAttributeID.VALUE;
import static escape.piece.EscapePiece.PieceName.*;
import static escape.piece.Player.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import escape.board.*;
import escape.coordinate.*;
import escape.coordinate.Coordinate.CoordinateType;
import escape.exception.BoardExcpetion;
import escape.piece.*;


/**
 * This is a simple test, not really a unit test, to make sure tht the
 * EscapeGameBuilder, in the starting code, is actually working.
 * 
 * @version May 30, 2020
 */
class TestBoardParts
{
	
	private static GameBoard Squareboard;
	private CoordinateTypeSquare testsquard1;
	
	private CoordinateTypeSquare testsquard2;
	
	private PieceTypeDescriptor SnailRules;
	
	@BeforeEach
	void setup() {
		
		testsquard1 = new CoordinateTypeSquare(1,1);
		testsquard2 = new CoordinateTypeSquare(3,4);
		//Squareboard = new MakeSquareBoard();
		Squareboard = new EscapeBoard(4, 4);
		Squareboard.setCoordinateFactory(new CoordinateFactory(CoordinateType.SQUARE));
		
		
	}
	
	
	@Test
	void TestMakeNoFactoryCoorardinateThrow() {
		GameBoard noFactroyBoard = new EscapeBoard(4, 4);
		assertThrows(BoardExcpetion.class, () -> noFactroyBoard.makeCoordinate(1, 1));
		
	}
	
	@Test
	void TestMakeSquareBoardisNear() {
		assertTrue(Squareboard.isNear(Squareboard.makeCoordinate(2, 2), Squareboard.makeCoordinate(2, 1)));
	}
	
	@Test
	void TestMakeSquareBoardMakeCorrdinateThrows() {
		assertEquals(Squareboard.makeCoordinate(5, 1), null); // too big x.
		assertEquals(Squareboard.makeCoordinate(1, 5), null); // too big y.
		assertEquals(Squareboard.makeCoordinate(-1, 1), null); // too small x.
		assertEquals(Squareboard.makeCoordinate(1, -4), null); // too small y.
	}
	
	
	
	@Test
	void TestMakeSquareBoardMakeLocation() {
		EscapeCoordinate c1 = (EscapeCoordinate) Squareboard.makeCoordinate(2, 1);
		EscapeCoordinate c2 = (EscapeCoordinate) Squareboard.makeCoordinate(2, 2);
		EscapeCoordinate c3 = (EscapeCoordinate) Squareboard.makeCoordinate(0,0);
		Squareboard.setLocationType(c1, BLOCK);
		Squareboard.setLocationType(c2, CLEAR);
		Squareboard.setLocationType(c3, EXIT);
		
		assertEquals(BLOCK,Squareboard.getLocationType(c1));
		assertEquals(CLEAR,Squareboard.getLocationType(c2));
		assertEquals(EXIT,Squareboard.getLocationType(c3));
		assertEquals(null,Squareboard.getLocationType(testsquard2));
		

	}
	
	@Test
	void TestMakeSquareBoardMakeLocationThrows() {
		EscapeCoordinate startc = (EscapeCoordinate) Squareboard.makeCoordinate(5, 1);
		EscapeCoordinate endc = (EscapeCoordinate) Squareboard.makeCoordinate(1, 5);
		
		assertThrows(BoardExcpetion.class, () ->  Squareboard.setLocationType(startc, BLOCK)); // too big x.
		assertThrows(BoardExcpetion.class, () -> Squareboard.setLocationType(endc, BLOCK)); // too big y.
	}
	
	@Test
	void TestMakeSquareBoardMakeLocationThrowsOverLayCoordinates() {
		Squareboard.setLocationType(testsquard2, BLOCK);
		assertThrows(BoardExcpetion.class, () -> Squareboard.setLocationType(testsquard2, BLOCK));
	}
	
	@Test
	void TestMakeSquareBoardMakePieceThrowsOverLayCoordinates() {
		PieceTypeDescriptor SnailRules = new PieceTypeDescriptor(SNAIL, DIAGONAL, new PieceAttribute(VALUE, 2));
		EscapePieceImpl snail = new EscapePieceImpl(PLAYER2, SNAIL,SnailRules);
		
		Squareboard.putPieceAt(testsquard1,snail);	
		assertThrows(BoardExcpetion.class, () -> Squareboard.putPieceAt(testsquard1,snail));
	}
	
	@Test
	void TestMakeSquareBoardMakeSnailPiece() {
		PieceTypeDescriptor SnailRules = new PieceTypeDescriptor(SNAIL, DIAGONAL, new PieceAttribute(VALUE, 2));
		EscapePieceImpl snail = new EscapePieceImpl(PLAYER2, SNAIL,SnailRules);
		
	}
	
	@Test
	void TestMakeSquareBoardMove0_1_to_3_3_clear() {
		EscapeCoordinate startc = (EscapeCoordinate) Squareboard.makeCoordinate(0,1);
		EscapeCoordinate endc = (EscapeCoordinate) Squareboard.makeCoordinate(3,3);
		EscapePieceImpl bird = new EscapePieceImpl(PLAYER1, BIRD);
		
		Squareboard.setLocationType(endc, CLEAR);
		Squareboard.putPieceAt(startc,bird);	
		Squareboard.move(startc, endc);
		assertEquals(Squareboard.getPieceAt(endc), bird);
	}
	
	
	@Test
	void TestMakeSquareBoardMove0_1_to_3_3_exit() {
		CoordinateTypeSquare startc = new CoordinateTypeSquare(0,1);
		CoordinateTypeSquare endc = new CoordinateTypeSquare(3,3);
		EscapePieceImpl bird = new EscapePieceImpl(PLAYER1, BIRD);
		
		Squareboard.setLocationType(endc, EXIT);
		Squareboard.putPieceAt(startc,bird);	
		Squareboard.move(startc, endc);
		assertEquals(Squareboard.getPieceAt(endc), null);
	}
	
	@Test
	void TestMakeSquareBoardMove0_1_to_3_3_noPiece() {
		CoordinateTypeSquare startc = new CoordinateTypeSquare(0,1);
		assertFalse(Squareboard.move(startc, new CoordinateTypeSquare(3,3)));
		
	}

	@Test
	void TestMakeSquareBoardMove0_1_to_3_3_null() {
		EscapeCoordinate startc = (EscapeCoordinate) Squareboard.makeCoordinate(0,1);
		//EscapeCoordinate endc = (EscapeCoordinate) Squareboard.makeCoordinate(3,3);
		EscapePieceImpl bird = new EscapePieceImpl(PLAYER1, BIRD);
		
		//Squareboard.setLocationType(endc, CLEAR);
		Squareboard.putPieceAt(startc,bird);	
		assertFalse(Squareboard.move(startc, new CoordinateTypeSquare(3,3)));
		
	}
	
	@Test
	void TestMakeSquareBoardMove0_1_to_3_3_ocuppied() {
		CoordinateTypeSquare startc = new CoordinateTypeSquare(0,1);
		CoordinateTypeSquare endc = new CoordinateTypeSquare(3,3);
		EscapePieceImpl bird = new EscapePieceImpl(PLAYER1, BIRD);
		EscapePieceImpl snail = new EscapePieceImpl(PLAYER1, SNAIL);
		
		Squareboard.setLocationType(endc, CLEAR);
		Squareboard.putPieceAt(startc,bird);
		Squareboard.putPieceAt(endc,snail);
		assertFalse(Squareboard.move(startc, endc));
		
	}
	
	@Test
	void TestMakeSquareBoardThrow2() {
		assertThrows(BoardExcpetion.class, () -> new EscapeBoard(4,-3));
	}
	
	@Test
	void TestMakeSquareBoardThrowX() {
		assertThrows(BoardExcpetion.class, () -> new EscapeBoard(0,3));
	}


}
