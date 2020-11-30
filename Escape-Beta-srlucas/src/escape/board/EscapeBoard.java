/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.board;

import static escape.location.LocationType.*;
import static escape.piece.EscapePiece.MovementPattern.*;
import static escape.piece.EscapePiece.PieceAttributeID.*;
import java.util.HashMap;
import escape.coordinate.*;
import escape.exception.BoardExcpetion;
import escape.location.LocationType;
import escape.piece.*;

/**
 * Create Squard board
 * 
 * @version Nov 12, 2020
 */
public class EscapeBoard<C extends EscapeCoordinate> implements GameBoard<C>
{
	// private ArrayList<CoordinateTypeSquare> CoordinateList = new ArrayList();

	private HashMap<EscapeCoordinate, EscapePieceImpl> pieces;
	private HashMap<EscapeCoordinate, LocationType> Locations;
	private CoordinateFactory coordinateFactory;

	private int xMax, yMax;

	/**
	 * Create a Square game Board
	 * 
	 * @param xMax
	 *            max size of game board
	 * @param yMax
	 *            max size of game board
	 * @throws xyMax
	 *             is 1 or smaller
	 */
	public EscapeBoard(int xMax, int yMax)
	{
		makeBoard(xMax, yMax);
		pieces = new HashMap<EscapeCoordinate, EscapePieceImpl>();
		Locations = new HashMap<EscapeCoordinate, LocationType>();
	}

	/**
	 * @see escape.board.GameBoard#makeBoard(int, int)
	 */
	@Override
	public void makeBoard(int xMax, int yMax)
	{
		this.xMax = xMax;
		this.yMax = yMax;

		if (xMax < 1) {
			throw new BoardExcpetion("Board x size is less then 1");
		}

		if (yMax < 1) {
			throw new BoardExcpetion("Board y size is less then 1");
		}

	}

	/**
	 * @throws x
	 *             and/or y can't be bigger Max or smaller then 0.
	 */
	@Override
	public C makeCoordinate(int x, int y)
	{
		// check if x & y are in bounds

		if (coordinateFactory == null) {
			throw new BoardExcpetion("coordinateFactory not initalized");
		}

		else if (x > xMax) {
			return null;
		}

		else if (x < 0) {
			return null;
		}

		else if (y > yMax) {
			return null;
		}

		else if (y < 0) {
			return null;
		}

		C nc = (C) coordinateFactory.makeCoordinate(x, y);

		return nc;

	}

	/*
	 * @see escape.required.GameBoard#setCoordinateFactory(escape.util.CoordinateFactory)
	 */
	@Override
	public void setCoordinateFactory(CoordinateFactory coordinateFactory)
	{
		this.coordinateFactory = coordinateFactory;
	}

	/*
	 * @see escape.required.GameBoard#setLocationType(escape.required.EscapeCoordinate,
	 * escape.required.LocationType)
	 */
	@Override
	public void setLocationType(EscapeCoordinate c, LocationType locationType)
	{
		if (c == null) {
			throw new BoardExcpetion(
					"Coordinites givine for makeLocation is invalid");
		}

		else if (Locations.put(c, locationType) != null) {
			throw new BoardExcpetion(
					"cant put new location in Locations as one already exists");
		}
	}

	/*
	 * @see escape.board.GameBoard#getLocationType(java.lang.Object)
	 */
	@Override
	public LocationType getLocationType(EscapeCoordinate c)
	{
		return Locations.get(c);
	}

	@Override
	public void putPieceAt(EscapeCoordinate c, EscapePieceImpl p)
	{
		if (pieces.put(c, p) != null) {
			throw new BoardExcpetion(
					"cant put new Piece in pieces, location Already Occuppied");
		}
	}

	/*
	 * @see escape.required.GameBoard#getPieceAt(java.lang.Object)
	 */
	@Override
	public EscapePiece getPieceAt(EscapeCoordinate coordinate)
	{
		EscapePiece getp = pieces.get(coordinate);

		// run error if null
		if (getp == null) {
			return null;
		}

		return getp;
	}

	/*
	 * @see escape.required.GameBoard#move(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean move(EscapeCoordinate from, EscapeCoordinate to)
	{

		LocationType loc = Locations.get(to);
		EscapePieceImpl pcfrom = pieces.get(from);
		EscapePieceImpl pcto = pieces.get(to);
		boolean pieceMoveto = false;

		// if the start not null and start & end not the same Coordinate
		if (pcfrom != null) {
			// See if piece is able to reach Destination

			// if fly check if have enough fly distacnce
			if (pcfrom.hasAttribute(FLY)) {
				pieceMoveto = AtPieceMovesEnd(pcfrom, from.DistanceTo(to));
			}

			// else check if thier is a path to piece
			else {
				// pieceMoveto = findPathStart(from, to);
			}

			// if possible to move to place.
			if (pieceMoveto) {
				if (from.equals(to)) {
					return true;
				} else if (loc == CLEAR && pcto == null) {
					pieces.put(to, pcfrom);
					pieces.put(from, null);
					return true;

				} else if (loc == EXIT) {
					pieces.put(from, null);
					return true;
				}
			}
		}

		return false;

	}

	/*
	 * @see escape.required.GameBoard#isNear(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isNear(EscapeCoordinate givenC, EscapeCoordinate findC)
	{
		return givenC.isNear(findC);
	}

	/*
	 * @see escape.board.GameBoard#pathClear(escape.coordinate.EscapeCoordinate,
	 * escape.coordinate.EscapeCoordinate)
	 */
	@Override
	public boolean pathClear(EscapeCoordinate start, EscapeCoordinate end)
	{
		// get moving piece for refrence
		EscapePieceImpl startP = (EscapePieceImpl) getPieceAt(start);

		int direction = start.getDicection(end);

		// ORTHOGONAL: Only UP, Down Left, or Right.
		if (startP.getMovementPattern() == ORTHOGONAL) {
			if (direction >= 1 && direction <= 3) {

				// create a forloop, each iteration, startP moves closer to end. Return
				// false if runs into space it can't move thorugh.
				return checkLine(start, end, direction, startP);

			}
		}

		// DIAGONAL: Move only Diagnal
		else if (startP.getMovementPattern() == DIAGONAL) {
			if (direction >= 4 && direction <= 7) {

				return checkLine(start, end, direction, startP);
			}
		}

		// LINEAR: Move ORTHOGONAL or Diagnal
		else if (startP.getMovementPattern() == LINEAR) {
			if (direction >= 0 && direction <= 7) {

				return checkLine(start, end, direction, startP);
			}
		}

		return false;
	}

	@Override
	public boolean checkLine(EscapeCoordinate start, EscapeCoordinate end,
			int direction, EscapePieceImpl startP)
	{
		// get what direction the coordinites are incementing by.
		int incromentX = (end.getX() - start.getX())
				/ Math.abs(end.getX() - start.getX());
		int incromentY = (end.getY() - start.getY())
				/ Math.abs(end.getY() - start.getY());

		// create a forloop, each iteration, startP moves closer to end. Return false if
		// runs into space it can't move thorugh.
		for (int x = start.getX(), y = start.getY(); x != end.getX()
				&& y != end.getY(); x += incromentX, y += incromentY) {

			if (!CanMoveThrough((C) end, direction, startP)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * @see escape.board.GameBoard#CanMoveThrough(java.lang.Object, int,
	 * escape.piece.EscapePieceImpl)
	 */
	@Override
	public boolean CanMoveThrough(C to, int direction, EscapePieceImpl p)
	{
		// get TO scquare for quick refrence
		EscapeCoordinate toC = makeCoordinate(to.getX(), to.getY());
		LocationType toLocation = getLocationType(toC);
		EscapePiece toPiece = getPieceAt(toC);
		EscapeCoordinate[] around = to.isNearCluster();
		EscapeCoordinate NextCoordinateFoward = around[direction];

		// Pieces can't move over exits without fly
		if (toLocation == EXIT) {
			return false;
		}

		// can't move through block withouth UNBLOCK
		else if (toLocation == BLOCK && !p.hasAttribute(UNBLOCK)) {
			return false;
		}
		// can't move through Piece withouth Jump
		else if (toPiece != null && !p.hasAttribute(JUMP)) {
			return false;
		}
		// when jumping over a Piece, can't land on BLOCK, EXIT or anouther piece
		else if (toPiece != null && p.hasAttribute(JUMP)) {
			if (getLocationType(NextCoordinateFoward) == BLOCK || toLocation == EXIT
					|| getPieceAt(NextCoordinateFoward) != null) {
				return false;
			}
		}

		return true;

	}

	/*
	 * @see escape.board.GameBoard#findShortestPathStart(java.lang.Object,
	 * java.lang.Object)
	 */
	// @Override
	// public boolean findPathStart(EscapeCoordinate start, EscapeCoordinate end)
	// {
	// // TODO Auto-generated method stub
	// // return false;
	// //
	// //hold coordinate that chain passes through.
	// EscapeCoordinate[] Chain = null;
	//
	// CoordinateTypeSquare[] chain = {(CoordinateTypeSquare) start};
	//
	// //hold paths start can go down
	//
	//
	// // for(CoordinateTypeSquare cl: isNearCluster) {
	// // finishedChain = findShortestPathChain(start, end, chain, 1 );
	// //
	// // if(finishedChain != null) {
	// //
	// // }
	// // }
	//
	//
	// }

	// public EscapeCoordinate IsRetredingEscapeCoordinateArray(EscapeCoordinate
	// coordinate)
	// {
	// return coordinate;
	// // TODO Auto-generated method stub
	// // Optional<EscapeCoordinate> attr =
	// // Arrays.stream(attributes)
	// // .filter(a -> a.getId().equals(id))
	// // .findFirst();
	// // return attr.isPresent() ? attr.get() : null;
	// }

	// /*
	// * @see escape.board.GameBoard#findShortestPathChain(java.lang.Object,
	// java.lang.Object, java.lang.Object, int)
	// */
	// @Override
	// public EscapeCoordinate[] findPathChain(EscapeCoordinate start, EscapeCoordinate
	// end, EscapeCoordinate[] chain, EscapePieceImpl p)
	// {
	// CoordinateTypeSquare[] isNearCluster = (CoordinateTypeSquare[])
	// start.isNearCluster();
	// EscapeCoordinate[] chainIn = new EscapeCoordinate[chain.length+1];
	// chainIn = chain;
	//
	// if(pieces.get(start).getMovementPattern() == ORTHOGONAL) {
	// chainIn[chainIn.length] = isNearCluster[0]; // get left
	// findPathChain(start, end, chainIn,p);
	// }
	//
	//
	// return null;
	// }

	/*
	 * @see escape.board.GameBoard#AtPieceMovesEnd(escape.piece.EscapePieceImpl, int)
	 */
	@Override
	public boolean AtPieceMovesEnd(EscapePieceImpl p, int movment)
	{
		if (p.hasAttribute(FLY)) {
			return p.getAttribute(FLY).getValue() < movment;
		} else if (p.hasAttribute(DISTANCE)) {
			return p.getAttribute(DISTANCE).getValue() < movment;
		}
		return false;
	}

	/*
	 * @see escape.board.GameBoard#ScoreP(java.lang.Object, escape.piece.Player)
	 */
	@Override
	public void ScoreP(C startPiece, Player player)
	{
		// TODO Auto-generated method stub

	}

}
