package com.demo.citiespath.calculation.edges;

import org.jgrapht.graph.DefaultWeightedEdge;

import com.demo.citiesmanagement.domain.CityPath;

/**
 * The Class CitiesDefaultWeightedEdge.
 *  		Enable public methods to get the source,target and weigth for and weighted edge
 */
public class CitiesDefaultWeightedEdge extends DefaultWeightedEdge {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4130456707100203451L;
	
	/** The city path. */
	private CityPath cityPath;
	
	
	/**
	 * Gets the citypath class.
	 *
	 * @return the city path
	 */
	public CityPath getCityPath() {
		return cityPath;
	}

	/**
	 * Sets the city path.
	 *
	 * @param cityPath the new city path
	 */
	public void setCityPath(CityPath cityPath) {
		this.cityPath = cityPath;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jgrapht.graph.DefaultWeightedEdge#getSource()
	 */
	public String getSource() {

		return (String) super.getSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jgrapht.graph.DefaultWeightedEdge#getTarget()
	 */
	public String getTarget() {
		return (String) super.getTarget();
	}

	/**
	 * Gets the weigth.
	 *
	 * @return the weigth
	 */
	public int getWeigth() {
		return (int) super.getWeight();
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jgrapht.graph.DefaultWeightedEdge#toString()
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(" + getSource()).append(":" + getTarget()).append(":" + getWeigth()).append(")");
		return builder.toString();
	}
}
