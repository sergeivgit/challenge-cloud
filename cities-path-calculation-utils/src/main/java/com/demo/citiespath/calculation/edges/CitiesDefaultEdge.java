package com.demo.citiespath.calculation.edges;

import org.jgrapht.graph.DefaultEdge;

import com.demo.citiesmanagement.domain.CityPath;

/**
 * The Class CitiesDefaultEdge.
 * 		Enable public methods to get the source and target for and edge
 */
public class CitiesDefaultEdge extends DefaultEdge {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6557563601762942588L;

	/** The city path. */
	private CityPath cityPath;
	
	/**
	 * Gets the city path.
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
	 * @see org.jgrapht.graph.DefaultEdge#getSource()
	 */
	public String getSource() {
		return (String) super.getSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jgrapht.graph.DefaultEdge#getTarget()
	 */
	public String getTarget() {
		return (String) super.getTarget();
	}

}
