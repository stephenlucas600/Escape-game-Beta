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
import escape.exception.EscapeGameInitializationException;

/**
 * Test the EscapeGameBuilder throw errors.
 * 
 * @version Nov 19, 2020
 */
class TestEscapeGameBuilder
{

	@Test
	void TestEscapeGameBuilderThrowNoName() throws Exception
	{
		EscapeGameBuilder egb = new EscapeGameBuilder("config/egc/FailTest1.egc");
		assertThrows(EscapeGameInitializationException.class,
				() -> egb.makeGameManager());

	}

	// @Test
	// void TestEscapeGameBuilderThrowNoPlayer() throws Exception
	// {
	// EscapeGameBuilder egb = new EscapeGameBuilder("config/egc/FailTest2.egc");
	// assertThrows(EscapeGameInitializationException.class, ()-> egb.makeGameManager());
	//
	// }

}
