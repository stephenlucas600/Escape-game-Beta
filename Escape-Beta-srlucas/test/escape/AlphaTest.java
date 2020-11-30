/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape;

import static escape.piece.EscapePiece.PieceName.*;
import static escape.piece.Player.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import escape.coordinate.Coordinate;
import escape.piece.*;
import escape.piece.EscapePiece.PieceName;

/**
 * Description
 * 
 * @version Nov 16, 2020
 */
class AlphaTest
{
	private static EscapeGameManager manager;

	@BeforeEach
	void loadGame() throws Exception
	{
		EscapeGameBuilder egb = new EscapeGameBuilder(
				"config/egc/GameBuilderTest.egc");
		manager = egb.makeGameManager();
		assertNotNull(manager);
	}

	@ParameterizedTest
	@MethodSource("piecesOnBoardProvider")
	void PiecesOnboard(int x, int y, Player player, PieceName name)
	{
		Coordinate c = manager.makeCoordinate(x, y);
		EscapePiece p = manager.getPieceAt(c);
		assertNotNull(p);
		assertEquals(player, p.getPlayer());
		assertEquals(name, p.getName());

	}

	static Stream<Arguments> piecesOnBoardProvider()
	{
		return Stream.of(

				Arguments.of(4, 4, PLAYER1, SNAIL),
				Arguments.of(10, 12, PLAYER2, HORSE),
				Arguments.of(4, 14, PLAYER1, HORSE),
				Arguments.of(10, 17, PLAYER1, SNAIL),
				Arguments.of(9, 2, PLAYER2, FROG), Arguments.of(5, 2, PLAYER2, DOG));
	}

	@Test
	void validMove()
	{
		Coordinate c1 = manager.makeCoordinate(4, 14);
		Coordinate c2 = manager.makeCoordinate(2, 2);
		assertTrue(manager.move(c1, c2));
		assertNull(manager.getPieceAt(c1));
		assertEquals(HORSE, manager.getPieceAt(c2).getName());
		assertEquals(PLAYER1, manager.getPieceAt(c2).getPlayer());

	}

	@Test
	void moveToExit()
	{
		Coordinate c1 = manager.makeCoordinate(4, 14);
		Coordinate c2 = manager.makeCoordinate(20, 10);
		assertTrue(manager.move(c1, c2));
		assertNull(manager.getPieceAt(c1));
		assertNull(manager.getPieceAt(c2));
	}

	@Test
	void moveToSameCoordinate()
	{
		Coordinate c1 = manager.makeCoordinate(10, 17);
		assertTrue(manager.move(c1, c1));
		assertEquals(SNAIL, manager.getPieceAt(c1).getName());
	}

	@Test
	void moveOntoOpponentPiece()
	{
		Coordinate c1 = manager.makeCoordinate(4, 14);
		Coordinate c2 = manager.makeCoordinate(7, 4);
		assertFalse(manager.move(c1, c2));
		assertEquals(HORSE, manager.getPieceAt(c1).getName());
		assertEquals(PLAYER1, manager.getPieceAt(c1).getPlayer());
		assertEquals(FROG, manager.getPieceAt(c2).getName());
		assertEquals(PLAYER2, manager.getPieceAt(c2).getPlayer());
	}

}
