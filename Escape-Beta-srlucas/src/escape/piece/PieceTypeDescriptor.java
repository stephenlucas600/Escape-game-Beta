/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ Copyright ©2016-2020 Gary F. Pollice
 *******************************************************************************/
package escape.piece;

import static escape.piece.EscapePiece.PieceAttributeID.VALUE;
import java.util.*;

/**
 * A JavaBean that represents a complete piece type description. This file is provided as
 * an example that can be used to initialize instances of a GameManager via the
 * EscapeBuilder. You do not have to use this, but are encouraged to do so. However, you
 * do need to be able to load the appropriate named data from the configuration file in
 * order to create a game correctly. MODIFIABLE: YES MOVEABLE: YES REQUIRED: NO
 */
public class PieceTypeDescriptor implements EscapePiece
{
	private PieceName pieceName;
	private MovementPattern movementPattern;
	private PieceAttribute[] attributes;

	public PieceTypeDescriptor()
	{
	}

	/**
	 * Set PieceTypeDescriptor for testing
	 * 
	 * @param pieceName
	 *            name of Piece type
	 * @param movementPattern
	 *            how Piece moves
	 * @param attributes
	 *            other Attributes of Piece
	 */
	public PieceTypeDescriptor(PieceName pieceName, MovementPattern movementPattern,
			PieceAttribute... attributes)
	{
		this.pieceName = pieceName;
		this.movementPattern = movementPattern;
		this.attributes = attributes;
	}

	/**
	 * See if this descriptor has the specified attribute
	 * 
	 * @param id
	 *            the attribute ID
	 * @return the attribute or null if the piece has none, If can't find VALUE return
	 *         VALUE attribute of 1 instead.
	 */
	public PieceAttribute getAttribute(PieceAttributeID id)
	{
		Optional<PieceAttribute> attr = Arrays.stream(attributes)
				.filter(a -> a.getId().equals(id)).findFirst();
		if (id == VALUE) {
			return attr.isPresent() ? attr.get() : new PieceAttribute(VALUE, 1);
		}

		return attr.isPresent() ? attr.get() : null;
	}

	/**
	 * @return the attributes
	 */
	public PieceAttribute[] getAttributes()
	{
		return attributes;
	}

	/**
	 * @return the movementPattern
	 */
	public MovementPattern getMovementPattern()
	{
		return movementPattern;
	}

	/**
	 * @return the pieceName
	 */
	public PieceName getPieceName()
	{
		return pieceName;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(PieceAttribute... attributes)
	{
		this.attributes = attributes;
	}

	/**
	 * @param movementPattern
	 *            the movementPattern to set
	 */
	public void setMovementPattern(MovementPattern movementPattern)
	{
		this.movementPattern = movementPattern;
	}

	/**
	 * @param pieceName
	 *            the pieceName to set
	 */
	public void setPieceName(PieceName pieceName)
	{
		this.pieceName = pieceName;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PieceTypeInitializer [pieceName=" + pieceName + ", movementPattern="
				+ movementPattern + ", attributes=" + Arrays.toString(attributes)
				+ "]";
	}
}
