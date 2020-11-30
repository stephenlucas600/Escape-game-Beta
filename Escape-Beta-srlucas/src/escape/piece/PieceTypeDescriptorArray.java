/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.piece;

import java.util.*;
import escape.piece.EscapePiece.PieceName;

/**
 * Create a Array for EscapeGamebuild to hold initalized rulses for Pieces.
 * 
 * @version Nov 26, 2020
 */
public class PieceTypeDescriptorArray
{

	private PieceTypeDescriptor[] ptdp;

	public PieceTypeDescriptorArray(PieceTypeDescriptor[] ptdp)
	{
		this.ptdp = ptdp;
	}

	/**
	 * @param pieceName
	 * @return PieceTypeDescriptor for given pieceName
	 */
	public PieceTypeDescriptor getDescriptor(PieceName pieceName)
	{
		Optional<PieceTypeDescriptor> pds = Arrays.stream(ptdp)
				.filter(a -> a.getPieceName().equals(pieceName)).findFirst();
		return pds.isPresent() ? pds.get() : null;
	}

}
