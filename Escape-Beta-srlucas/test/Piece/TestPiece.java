package Piece;
import static escape.piece.EscapePiece.MovementPattern.*;
import static escape.piece.EscapePiece.PieceAttributeID.*;
import static escape.piece.EscapePiece.PieceName.*;
import static escape.piece.Player.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import escape.piece.*;

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

/**
 * Description
 * @version Nov 25, 2020
 */
class TestPiece
{
	
	private PieceTypeDescriptor snailRules;
	private PieceTypeDescriptor dogRules;
	private PieceTypeDescriptor birdRules;
	private PieceTypeDescriptor frogRules;
	private EscapePieceImpl snail;
	private EscapePieceImpl dog;
	private EscapePieceImpl bird;
	private EscapePieceImpl frog;
	
	@BeforeEach
	void setup() {
		
		snailRules = new PieceTypeDescriptor(SNAIL, DIAGONAL, new PieceAttribute(VALUE, 2));
		dogRules = new PieceTypeDescriptor(DOG, LINEAR, new PieceAttribute(VALUE, 6), new PieceAttribute(JUMP,6));
		birdRules = new PieceTypeDescriptor(BIRD, OMNI, new PieceAttribute(FLY,3));
		frogRules = new PieceTypeDescriptor(FROG, DIAGONAL, new PieceAttribute(UNBLOCK));
		
		snail = new EscapePieceImpl(PLAYER2, SNAIL,snailRules);
		dog = new EscapePieceImpl(PLAYER1, DOG,dogRules);
		bird = new EscapePieceImpl(PLAYER1, BIRD, birdRules);
		frog = new EscapePieceImpl(PLAYER1, FROG);
		
	}
	
	@Test
	void TestMakeFrog() {
		
		frog.setDescriptor(frogRules);
		assertEquals(frog.getName(),FROG);
		assertTrue(frog.hasAttribute(UNBLOCK));
		
	}

	@Test
	void TestMakeSnailPiece() {
		
		assertEquals(snail.getName(),SNAIL);
		assertEquals(snail.getPlayer(),PLAYER2);
		assertEquals(snail.getMovementPattern(),DIAGONAL);
		assertEquals(snail.getAttribute(VALUE).getValue(),2);
		
	}
	
	@Test
	void TestMakeDogPiece() {
		
		assertEquals(dog.getName(),DOG);
		assertEquals(dog.getPlayer(),PLAYER1);
		assertEquals(dog.getAttribute(VALUE).getValue(),6);
		assertTrue(dog.hasAttribute(VALUE));
		assertTrue(dog.hasAttribute(JUMP));
		assertFalse(dog.hasAttribute(UNBLOCK));
		
	}
	
	@Test
	void testSetValue()
	{
		snail.setValue(snail.getAttribute(VALUE).getValue());
		dog.setValue(dog.getAttribute(VALUE).getValue());
		assertEquals(6,dog.getValue());
		assertEquals(2,snail.getValue());
	}
	
	@Test
	void testSetValueNoValue()
	{
		bird.setValue(bird.getAttribute(VALUE).getValue());
		assertEquals(1,bird.getValue());
	}

}
