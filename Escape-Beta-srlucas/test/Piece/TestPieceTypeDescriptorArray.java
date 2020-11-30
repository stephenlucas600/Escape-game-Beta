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

package Piece;

import static escape.piece.EscapePiece.MovementPattern.*;
import static escape.piece.EscapePiece.PieceAttributeID.*;
import static escape.piece.EscapePiece.PieceName.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import escape.piece.*;

/**
 * Description
 * @version Nov 26, 2020
 */
class TestPieceTypeDescriptorArray
{
	
	private PieceTypeDescriptor snailRules;
	private PieceTypeDescriptor dogRules;
	private PieceTypeDescriptor birdRules;
	private PieceTypeDescriptorArray ptda;
	@BeforeEach
	void setup() {
		
		snailRules = new PieceTypeDescriptor(SNAIL, DIAGONAL, new PieceAttribute(VALUE, 2));
		dogRules = new PieceTypeDescriptor(DOG, LINEAR, new PieceAttribute(VALUE, 6), new PieceAttribute(JUMP,6));
		birdRules = new PieceTypeDescriptor(BIRD, OMNI, new PieceAttribute(VALUE, 6), new PieceAttribute(FLY,3));	
	}

	@Test
	void testGetSnail()
	{
		ptda = new PieceTypeDescriptorArray(new PieceTypeDescriptor[]{snailRules,dogRules,birdRules}) ;
		assertEquals(snailRules, ptda.getDescriptor(SNAIL));
	}
	
	@Test
	void testGetDog()
	{
		ptda = new PieceTypeDescriptorArray(new PieceTypeDescriptor[]{snailRules,dogRules,birdRules}) ;
		assertEquals(dogRules, ptda.getDescriptor(DOG));
	}
	
	@Test
	void testGetNullDog()
	{
		ptda = new PieceTypeDescriptorArray(new PieceTypeDescriptor[]{snailRules,birdRules}) ;
		assertNull(ptda.getDescriptor(DOG));
	}
	
	

}
