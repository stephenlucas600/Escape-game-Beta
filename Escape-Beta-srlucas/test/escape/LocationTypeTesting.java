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

package escape;

import static escape.location.LocationType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import escape.location.LocationType;

/**
 * Check if LocationType Enum is working.
 * @version Nov 19, 2020
 */
class LocationTypeTesting
{

	@Test
	void TestLocationType()
	{
		assertEquals(LocationType.BLOCK, BLOCK);
		assertEquals(LocationType.CLEAR, CLEAR);
		assertEquals(LocationType.EXIT, EXIT);	
	}

}
