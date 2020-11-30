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

package escape.piece;

/**
 * impliment Piece for board to use
 * @version Nov 17, 2020
 */
public class EscapePieceImpl implements EscapePiece
{
	private PieceName pieceName;
	private Player player;
	private PieceTypeDescriptor pieceTypeDescriptor;
	private int value;
	
	/**
	 * @param player
	 * @param pieceName
	 */
	public EscapePieceImpl(Player player, PieceName pieceName)
	{
		this.player = player;
		this.pieceName = pieceName;
	}

	/**
	 * EscapePieceImpl for testing
	 * @param player
	 * @param pieceName
	 * @param pieceTypeDescriptor
	 */
	public EscapePieceImpl(Player player, PieceName pieceName, PieceTypeDescriptor pieceTypeDescriptor)
	{
		this.player = player;
		this.pieceName = pieceName;
		this.pieceTypeDescriptor = pieceTypeDescriptor;
	}
	
	/**
	 * @return the pieceName
	 */
	public PieceName getName()
	{
		return pieceName;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * find if thes peice has given Attribute
	 * @param value PieceAttributeID looking for
	 * @return true if piece has given attribute, else false.
	 */
	public boolean hasAttribute(PieceAttributeID value)
	{
		return (getAttribute(value) != null);
	}
	
	/**
	 * @return MovementPattern
	 */
	public MovementPattern getMovementPattern()
    {
        return pieceTypeDescriptor.getMovementPattern();
    }

	/**
	 * @param ptd set PieceTypeDescriptor
	 */
	public void setDescriptor(PieceTypeDescriptor ptd)
	{
		pieceTypeDescriptor = ptd;
		
	}
	
	/**
	 * get Attribute
	 * @param value PieceAttributeID looking for
	 * @return PieceAttribute for value, else null.
	 */
	public PieceAttribute getAttribute(PieceAttributeID value)
	{
		return pieceTypeDescriptor.getAttribute(value);
	}

	/**
	 * @param value to set  
	 */
	public void setValue(int value)
	{
		this.value = value;
	}
	
	/**
	 * @return the value
	 */
	public int getValue()
	{
		return value;
	}

}
