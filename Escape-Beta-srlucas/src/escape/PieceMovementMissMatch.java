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

import static escape.coordinate.Coordinate.CoordinateType.*;
import static escape.piece.EscapePiece.MovementPattern.*;
import escape.coordinate.EscapeCoordinate;
import escape.coordinate.Coordinate.CoordinateType;
import escape.exception.EscapeGameInitializationException;
import escape.piece.EscapePieceImpl;
import escape.piece.EscapePiece.MovementPattern;


/**
 * Description
 * @version Nov 28, 2020
 */
public class PieceMovementMissMatch
{
 
	/**
     * check if the movment pattern is alllowed for given board shape
     * @param c EscapeCoordinate
     * @param p EscapePieceImpl
     * @return 
     * @throws throw if a violation is detectied
     */
	public PieceMovementMissMatch(EscapeCoordinate c, EscapePieceImpl p)
	{
		CoordinateType bShape = c.getCoordinateType();
    	MovementPattern pMovementPattern = p.getMovementPattern();
    	
    	if(pMovementPattern == ORTHOGONAL && (bShape == HEX || bShape == TRIANGLE)) {
    		throw new EscapeGameInitializationException(
					"ORTHOGONAL can't be used in " + bShape);
    	}
    	else if(pMovementPattern == DIAGONAL && (bShape == ORTHOSQUARE ||  bShape == HEX || bShape == TRIANGLE)) {
    		throw new EscapeGameInitializationException(
					"DIAGONAL can't be used in " + bShape);
    	}
	}
}
