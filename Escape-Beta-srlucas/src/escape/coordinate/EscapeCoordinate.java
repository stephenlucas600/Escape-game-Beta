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

package escape.coordinate;

/**
 * This interface identifies an object has X and Y coordinate.
 * It also outlines tools for finding distance with with anouther XYCoordinate object.
 * 
 * @version Nov 10, 2020
 */
public interface EscapeCoordinate extends Coordinate
{
	
	/*
	  * @see java.lang.Object#equals(java.lang.Object)
	  */
	 @Override
	 boolean equals(Object obj);
	
	CoordinateType getCoordinateType();
	
	/**
	  * @param other EscapeCoordinate
	  * @return return which movable direction the two Coordinates line up on. else return -1 if not possible   
	  */
	 public int getDicection (EscapeCoordinate other);
	
  
 
	/**
	 * Find the Direct distance between this object's X coordinate and the other object's X coordinate
	 * @param other anouther object with a XYCoordinate.
	 * @return retuen the diffence between the two Xs.
	 */
	int getDirectHorizontalDistance(EscapeCoordinate other);
 
	/**
	 * Find the Direct distance between this object's Y coordinate and the other object's Y coordinate
	 * @param other anouther object with a XYCoordinate.
	 * @return retuen the diffence between the two Ys.
	 */
	int getDirectVerticalDistance(EscapeCoordinate other);
	
	
	 /**
	 * @return return the object's x cordinate (int)
	 */
	int getX();
	 
	 /**
	 * @return return the object's y cordinate (int)
	 */
	int getY();
	 
	 /*
	  * @see java.lang.Object#hashCode()
	  */
	 @Override
	 public int hashCode();
	 
	 /**
	  * find if this coordinaites is near anouther.
	  * @param findC
	  * @return return true if this coordinate is one space away from findC, else false.
	  */
	 boolean isNear(EscapeCoordinate findC);
	 
	 /**
	  * @return Get all coordinates near this one
	  */
	 public EscapeCoordinate[] isNearCluster();
	 
	

	 /**
	  * is this Coordinate the same as anouther Coordinate?
	  * @param other anouther object with a XYCoordinate.
	  * @return return true if same, else false.
	  */
	 boolean SameCoordinate(EscapeCoordinate other);



}
