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

import escape.board.*;
import escape.coordinate.*;
import escape.coordinate.Coordinate.CoordinateType;
import escape.piece.EscapePiece;

/**
 * Handles control and ininalization over the EscapeBoard class
 * @version Nov 16, 2020
 */
public class EscapeGameManagerImpl<C extends EscapeCoordinate> implements EscapeGameManager<C>
{

	private EscapeBoard board;
	private CoordinateType type;
	private CoordinateFactory coordinateFactory;

	/**
	 * Create new manager
	 * @param coordinateType set what type of board being used.
	 */
	public EscapeGameManagerImpl(CoordinateType coordinateType)
	{
		this.coordinateFactory = new CoordinateFactory(coordinateType);
	}

	/*
	 * @see escape.EscapeGameManager#move(escape.required.Coordinate, escape.required.Coordinate)
	 */
	@Override
	public boolean move(C from, C to)
	{
		return board.move(from, to);
	}

	/*
	 * @see escape.EscapeGameManager#getPieceAt(escape.required.Coordinate)
	 */
	@Override
	public EscapePiece getPieceAt(C coordinate)
	{
		return board.getPieceAt(coordinate);
	}

	/*
	 * @see escape.EscapeGameManager#makeCoordinate(int, int)
	 */
	@Override
	public C makeCoordinate(int x, int y)
	{
		return (C) board.makeCoordinate(x, y);
		
	}

	
	/**
	 * Astablish a link between a given board and this manager, also sharing the same coordinateFactory.
	 * @param board the board to set
	 */
	public void setBoard(EscapeBoard board) {
		this.board = board;
		board.setCoordinateFactory(coordinateFactory);
	}
	

	/**
	 * @return factory
	 */
	public CoordinateFactory getCoordinateFactory() {
		return coordinateFactory;
	}
}
