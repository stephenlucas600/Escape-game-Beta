/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package Board;

import static escape.location.LocationType.*;
import static escape.piece.EscapePiece.MovementPattern.*;
import static escape.piece.EscapePiece.PieceAttributeID.*;
import static escape.piece.EscapePiece.PieceName.*;
import static escape.piece.Player.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import escape.board.*;
import escape.coordinate.*;
import escape.coordinate.Coordinate.CoordinateType;
import escape.piece.*;

/**
 * Description
 * 
 * @version Nov 29, 2020
 */
class TestBoardPartsBata
{

	private CoordinateTypeSquare testsquard1;
	private CoordinateTypeSquare testsquard2;

	private static GameBoard Squareboard;

	private PieceTypeDescriptor snailRules;
	private PieceTypeDescriptor dogRules;
	private PieceTypeDescriptor dogRules2;
	private PieceTypeDescriptor birdRules;
	private PieceTypeDescriptor frogRules;
	private EscapePieceImpl snail;
	private EscapePieceImpl dog;
	private EscapePieceImpl dog2;
	private EscapePieceImpl bird;
	private EscapePieceImpl frog;

	@BeforeEach
	void setup()
	{

		testsquard1 = new CoordinateTypeSquare(1, 1);
		testsquard2 = new CoordinateTypeSquare(3, 4);
		// Squareboard = new MakeSquareBoard();
		Squareboard = new EscapeBoard(6, 6);
		Squareboard
				.setCoordinateFactory(new CoordinateFactory(CoordinateType.SQUARE));

		snailRules = new PieceTypeDescriptor(SNAIL, DIAGONAL,
				new PieceAttribute(VALUE, 2));
		dogRules = new PieceTypeDescriptor(DOG, LINEAR, new PieceAttribute(VALUE, 6),
				new PieceAttribute(JUMP, 6));
		dogRules2 = new PieceTypeDescriptor(DOG, LINEAR,
				new PieceAttribute(VALUE, 6), new PieceAttribute(DISTANCE, 6));
		birdRules = new PieceTypeDescriptor(BIRD, OMNI, new PieceAttribute(FLY, 3));
		frogRules = new PieceTypeDescriptor(FROG, DIAGONAL,
				new PieceAttribute(UNBLOCK));

		snail = new EscapePieceImpl(PLAYER2, SNAIL, snailRules);
		dog = new EscapePieceImpl(PLAYER1, DOG, dogRules);
		dog2 = new EscapePieceImpl(PLAYER1, DOG, dogRules2);
		bird = new EscapePieceImpl(PLAYER1, BIRD, birdRules);
		frog = new EscapePieceImpl(PLAYER1, FROG, frogRules);
	}

	@Test
	void testPassThorughNormalClear()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 5), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 3), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 5), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 3), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 3), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 4), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 5), CLEAR);

		for (int i = 0; i < 8; i++) {
			assertTrue(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4),
					i, dog2));
		}

	}

	@Test
	void testPassThorughNormalBlock()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 5), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 5), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 5), BLOCK);

		for (int i = 0; i < 8; i++) {
			assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4),
					i, dog2));
		}

	}

	@Test
	void testPassThorughUnBlockBlock()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 5), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 5), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 5), BLOCK);

		for (int i = 0; i < 8; i++) {
			assertTrue(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4),
					i, frog));
		}

	}

	@Test
	void testPassThorughUnBlockBlockClear()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), CLEAR);

		assertTrue(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				frog));
	}

	@Test
	void testPassThorughNormalBlockClear()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), CLEAR);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog2));

	}

	@Test
	void testPassThorughNormalBlockBlock()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), BLOCK);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog2));

	}

	@Test
	void testPassThorughNormalPieceBlock()
	{
		Squareboard.putPieceAt(Squareboard.makeCoordinate(4, 4), frog);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), BLOCK);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog2));

	}

	@Test
	void testPassThorughNormalExit()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), EXIT);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog2));

	}

	@Test
	void testPassThorughNormalPiecePiece()
	{
		Squareboard.putPieceAt(Squareboard.makeCoordinate(4, 4), frog);
		Squareboard.putPieceAt(Squareboard.makeCoordinate(5, 4), frog);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog2));

	}

	@Test
	void testPassThorughUnblockPieceClear()
	{
		Squareboard.putPieceAt(Squareboard.makeCoordinate(4, 4), frog);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), CLEAR);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				frog));

	}

	@Test
	void testPassThorugJumpClearClear()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), CLEAR);

		assertTrue(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog));

	}

	@Test
	void testPassThorugJumpPieceClear()
	{
		Squareboard.putPieceAt(Squareboard.makeCoordinate(4, 4), frog);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), CLEAR);

		assertTrue(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog));

	}

	@Test
	void testPassThorugJumpPiecePiece()
	{
		Squareboard.putPieceAt(Squareboard.makeCoordinate(4, 4), frog);
		Squareboard.putPieceAt(Squareboard.makeCoordinate(5, 4), frog);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog));

	}

	@Test
	void testPassThorughJumpPieceBlock()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), CLEAR);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), BLOCK);
		Squareboard.putPieceAt(Squareboard.makeCoordinate(4, 4), frog);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog));

	}

	@Test
	void testPassThorughJumpBlockBlock()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), BLOCK);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog));
	}

	@Test
	void testPassThorughJumpPiecePiece()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.putPieceAt(Squareboard.makeCoordinate(5, 4), frog);

		assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4), 0,
				dog));

	}

	@Test
	void testPassThorughJumpBlock()
	{
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 5), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(4, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 5), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(5, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 3), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 4), BLOCK);
		Squareboard.setLocationType(Squareboard.makeCoordinate(3, 5), BLOCK);

		for (int i = 0; i < 8; i++) {
			assertFalse(Squareboard.CanMoveThrough(Squareboard.makeCoordinate(4, 4),
					i, dog));
		}

	}

}
