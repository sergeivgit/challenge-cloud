package com.demo.citiespath.calculation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import com.demo.citiesmanagement.domain.CityPath;
import com.demo.citiespath.calculation.edges.CitiesDefaultWeightedEdge;

/**
 * The Class PathCalculator.
 * 		Class to encapsulate the use of JGraph library to create graphs and
 * 		run the graphs algorithms to find the shortess path in vertex and
 * 		in weight between 2 vertex of the graph.
 * 		The vertex are going to be the cities
 * 		The edges are going to be the two cities link
 */
public class PathCalculator {

	/** The no weigth graph. */
	public DirectedWeightedMultigraph<String, CitiesDefaultWeightedEdge> noWeigthGraph;

	/** The weight graph. */
	public DirectedWeightedMultigraph<String, CitiesDefaultWeightedEdge> weightGraph;

	/**
	 * Creates the no weight graph.
	 *
	 * @param routes
	 *            list of cities to build the no weight graph
	 */
	public void setNoWeightGraph(Collection<CityPath> routes) {
		noWeigthGraph = new DirectedWeightedMultigraph<>(CitiesDefaultWeightedEdge.class);
		for (Iterator<CityPath> iterator = routes.iterator(); iterator.hasNext();) {
			CityPath cityPath = (CityPath) iterator.next();
			noWeigthGraph.addVertex(cityPath.getCitySource());
			noWeigthGraph.addVertex(cityPath.getCityDestination());
			CitiesDefaultWeightedEdge edge = noWeigthGraph.addEdge(cityPath.getCitySource(), cityPath.getCityDestination());
			edge.setCityPath(cityPath);
		}
	}

	/**
	 * Creates the weight graph.
	 *
	 * @param routes
	 *            list of cities to build the weight graph
	 */
	public void setWeightGraph(Collection<CityPath> routes) {
		weightGraph = new DirectedWeightedMultigraph<>(CitiesDefaultWeightedEdge.class);
		for (Iterator<CityPath> iterator = routes.iterator(); iterator.hasNext();) {
			CityPath cityPath = (CityPath) iterator.next();
			weightGraph.addVertex(cityPath.getCitySource());
			weightGraph.addVertex(cityPath.getCityDestination());
			CitiesDefaultWeightedEdge edge = (CitiesDefaultWeightedEdge) weightGraph.addEdge(cityPath.getCitySource(),
					cityPath.getCityDestination());
			weightGraph.setEdgeWeight(edge, cityPath.getDuration().intValue());
			edge.setCityPath(cityPath);
		}
	}

	/**
	 * Gets the weight shortest path async.
	 *
	 * @param origin
	 *            the origin
	 * @param destination
	 *            the destination
	 * @param algorithm
	 *            the algorithm
	 * @return the weight shortest path async
	 */
	public Future<Collection<CityPath>> getWeightShortestPathAsync(String origin, String destination,
			String algorithm) {
		CompletableFuture<Collection<CityPath>> completableFuture = new CompletableFuture<Collection<CityPath>>();
		ArrayList<CityPath> path = new ArrayList<CityPath>();

		Executors.newCachedThreadPool().submit(() -> {
			DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(weightGraph);
			List<CitiesDefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath(origin, destination)
					.getEdgeList();

			for (CitiesDefaultWeightedEdge edge : shortestPath) {
				path.add(edge.getCityPath());
			}
			completableFuture.complete(path);
			return null;
		});
		return completableFuture;
	}

	/**
	 * Gets the weight shortest path Sync.
	 *
	 * @param origin
	 *            the origin
	 * @param destination
	 *            the destination
	 * @param algorithm
	 *            the algorithm
	 * @return the weight shortest path
	 */
	public Collection<CityPath> getWeightShortestPath(String origin, String destination, String algorithm) {

		ArrayList<CityPath> path = new ArrayList<CityPath>();

		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(weightGraph);
		List<CitiesDefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath(origin, destination).getEdgeList();

		for (CitiesDefaultWeightedEdge edge : shortestPath) {
			path.add(edge.getCityPath());
		}

		return path;
	}

	/**
	 * Gets the shortest path async.
	 *
	 * @param origin
	 *            the origin
	 * @param destination
	 *            the destination
	 * @param algorithm
	 *            the algorithm
	 * @return the shortest path async
	 */
	public Future<Collection<CityPath>> getShortestPathAsync(String origin, String destination, String algorithm) {
		CompletableFuture<Collection<CityPath>> completableFuture = new CompletableFuture<Collection<CityPath>>();
		ArrayList<CityPath> path = new ArrayList<CityPath>();
		Executors.newCachedThreadPool().submit(() -> {
			DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(noWeigthGraph);
			List<CitiesDefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath(origin, destination)
					.getEdgeList();

			for (CitiesDefaultWeightedEdge edge : shortestPath) {
				path.add(edge.getCityPath());
			}
			completableFuture.complete(path);

			return null;
		});
		return completableFuture;
	}

	/**
	 * Gets the shortest path sync.
	 *
	 * @param origin
	 *            the origin
	 * @param destination
	 *            the destination
	 * @param algorithm
	 *            the algorithm
	 * @return the shortest path
	 */
	public Collection<CityPath> getShortestPath(String origin, String destination, String algorithm) {

		ArrayList<CityPath> path = new ArrayList<CityPath>();

		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(noWeigthGraph);
		List<CitiesDefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath(origin, destination).getEdgeList();

		for (CitiesDefaultWeightedEdge edge : shortestPath) {
			path.add(edge.getCityPath());
		}

		return path;
	}
}
