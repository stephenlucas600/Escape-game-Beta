/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import escape.coordinate.CoordinateTypeSquare;
import escape.piece.*;
import static escape.piece.EscapePiece.MovementPattern.*;
import static escape.piece.EscapePiece.PieceAttributeID.*;
import static escape.piece.EscapePiece.PieceName.*;
import static escape.piece.Player.*;

/**
 * Test the PieceMovementMissMatch method
 * 
 * @version Nov 28, 2020
 */
class TestPieceMovementMissMatch
{

	private CoordinateTypeSquare testsquard;

	private PieceTypeDescriptor snailRules;
	private PieceTypeDescriptor dogRules;
	private PieceTypeDescriptor birdRules;
	private PieceTypeDescriptor frogRules;
	private EscapePieceImpl snail;
	private EscapePieceImpl dog;
	private EscapePieceImpl bird;
	private EscapePieceImpl frog;

	@BeforeEach
	void setup()
	{

		testsquard = new CoordinateTypeSquare(1, 1);

		snailRules = new PieceTypeDescriptor(SNAIL, DIAGONAL,
				new PieceAttribute(VALUE, 2));
		dogRules = new PieceTypeDescriptor(DOG, LINEAR, new PieceAttribute(VALUE, 6),
				new PieceAttribute(JUMP, 6));
		birdRules = new PieceTypeDescriptor(BIRD, OMNI, new PieceAttribute(FLY, 3));
		frogRules = new PieceTypeDescriptor(FROG, DIAGONAL,
				new PieceAttribute(UNBLOCK));

		snail = new EscapePieceImpl(PLAYER2, SNAIL, snailRules);
		dog = new EscapePieceImpl(PLAYER1, DOG, dogRules);
		bird = new EscapePieceImpl(PLAYER1, BIRD, birdRules);
		frog = new EscapePieceImpl(PLAYER1, FROG, frogRules);

	}

	@Test
	void testMovmentInSQUARE()
	{
		assertNotNull(new PieceMovementMissMatch(testsquard, snail));
		assertNotNull(new PieceMovementMissMatch(testsquard, dog));
		assertNotNull(new PieceMovementMissMatch(testsquard, bird));
		assertNotNull(new PieceMovementMissMatch(testsquard, frog));
	}

}
