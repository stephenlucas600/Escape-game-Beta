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

package escape.board;

import escape.coordinate.*;
import escape.location.LocationType;
import escape.piece.*;



/**
 * Description
 * @version Nov 12, 2020
 * @param <C>
 */
public interface GameBoard<C>
{
	
	/**
	 * create board
	 * @param xMax max x size of board
	 * @param yMax max y size of board
	 * @throw Invalide board was made
	 */
	void makeBoard(int xMax, int yMax);
	
//	/**
//	 * Create a Locationtype on board for a Coordinate
//	 * @param x Coordinate
//	 * @param y Coordinate
//	 * @param locationType
//	 * @return return true if task is done with no comlications
//	 * @throws Location can't be made
//	 */
//	boolean makeLocation(int x, int y, LocationType locationType);
//	
	
	/**
	 * Returns a coordinate of the appropriate type. If the coordinate cannot be
	 * created, then null is returned and the status message is set appropriately.
	 * @param x the x component
	 * @param y the y component
	 * @return the coordinate or null if the coordinate cannot be implemented
	 */
	 C makeCoordinate(int x, int y);
	
	
	/**
	 * Return the piece located at the specified coordinate. If executing
	 * this method in the game instance causes an exception, then this method
	 * returns null and sets the status message appropriately. The status message
	 * is not used in the initial release(s) of the game.
	 * @param coordinate the location to probe
	 * @return the piece at the specified location or null if there is none
	 */
	EscapePiece getPieceAt(C coordinate);
	
	
	/**
	 * Make the move in the current game.
	 * @param from starting location
	 * @param to ending location
	 * @return true if the move was legal, false otherwise
	 */
	boolean move(C from, C to);
	
	/**
	 * 
	 * find if a corrdinate is one move space away from anouther corrdinate 
	 * @param givenC first corrdinate given
	 * @param findC Second corrdinate given
	 * @return true if one space away, false if not or is the same corrdinate.
	 */
	boolean isNear(C givenC, C findC);
	
	//arraylist
	
//	/**
//	 * return if this borad allows pieaces to move along the horizontal or vertical axis
//	 */
//	boolean canMoveORTHOGONAL();
//	
//	/**
//	 * return if this borad allows pieaces to move along the diagonal axis
//	 */
//	boolean canMoveDIAGONAL();
//	
//
//	
//	static boolean canMoveLINEAR = true;
//	
//	static boolean canMoveOMNI = true;

	/**
	 * Set up the class that makes Coordinates.
	 * @param coordinateFactory
	 */
	void setCoordinateFactory(CoordinateFactory coordinateFactory);


	/**
	 * change the location type for given Coordinates
	 * @param c Coordinate
	 * @param locationType
	 */
	void setLocationType(C c, LocationType locationType);
	
	/**
	 * get location type for given Coordinate
	 * @param c Coordinate
	 * @return locationType
	 */
	LocationType getLocationType(C c);


	/**
	 * Create a Piece for board in given Coordinate 
	 * @param c Corrdinat
	 * @param p EscapePiece
	 * @throws Piece can't be made
	 */
	void putPieceAt(C c, EscapePieceImpl p);
	
	/**
	 * check if a piece without Fly can pass thorugh a space
	 * @param to Coordinate movening through
	 * @param Direction way the piec is moving
	 * @param p piece being moved
	 * @return return true if piece can pass through ocording to game rules
	 */
	boolean CanMoveThrough(C to, int Direction, EscapePieceImpl p);

	/**
	 * look through line as piece is moving thorugh
	 * @param start start coordinate
	 * @param end End Coordinate
	 * @param direction Direction Piece is moving
	 * @param startP Piece being moved
	 * @return return true if Piece can move through line without being stopped
	 */
	boolean checkLine(C start, C end, int direction, EscapePieceImpl startP);
	
	/**
	 * find if piece has enough DISTANCE Attribute to make the move
	 * @param p EscapePiece
	 * @param movment distacne moved
	 * @return true if piece can make move.
	 */
	 boolean AtPieceMovesEnd(EscapePieceImpl p, int movment);
	 
	/**
	 * check if a given Piece can move to destination.
	 * 
	 * @param start
	 *            coordinate
	 * @param end
	 *            Coordinate
	 * @return return true if Piece at start can end.
	 */
	 boolean pathClear(EscapeCoordinate start, EscapeCoordinate end);
//	/**
//	 * Find a path to given 
//	 * @param Start Starting coordinate
//	 * @param End Ending Coordinate
//	 * @return return if possible for piece to reach End.
//	 */
//	boolean findPathStart(C Start, C End);
//	
//	/**
//	 * the link list for findPathStart
//	 * @param start Starting coordinate
//	 * @param end Ending Coordinate
//	 * @param chain what move program has currenlty made.
//	 * @param distance How many move this chain has made  
//	 * @return Coordinates travaled through to find destination, else Null if no paths can be resably found.
//	 */
//	C[] findPathChain(C start, C end, C[] chain, int distance);
//	
//	/**
//	 * find if piece has enough DISTANCE Attribute to make the move
//	 * @param p EscapePiece
//	 * @param movment distacne moved
//	 * @return true if piece can make move.
//	 */
//	boolean AtPieceMovesEnd(EscapePieceImpl p, int movment);
	
	/**
	 * add points to player for removing pieces
	 * @param startPiece type of Piece
	 * @param player that scored
	 */
	void ScoreP(C startPiece, Player player);
	
	
	
}
