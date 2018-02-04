package com.demo.citiespath.calculation;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/** The complete graph. */
	static DirectedWeightedMultigraph<String, DefaultWeightedEdge> completeGraph;

	/** The complete no weight graph. */
	static DirectedWeightedMultigraph<String, DefaultWeightedEdge> completeNoWeightGraph;

	/**
	 * Creates the graphs.
	 */
	@BeforeClass
	public static void createGraphs() {

		completeGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		completeNoWeightGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

		SimpleGraph simpleGraph = new SimpleGraph<>(DefaultEdge.class);
		completeGraph.addVertex("Zaragoza");
		completeGraph.addVertex("Barcelona");
		completeGraph.addVertex("Madrid");
		completeNoWeightGraph.addVertex("Zaragoza");
		completeNoWeightGraph.addVertex("Barcelona");
		completeNoWeightGraph.addVertex("Madrid");

		DefaultWeightedEdge edge1 = (DefaultWeightedEdge) completeGraph.addEdge("Barcelona", "Zaragoza");
		DefaultWeightedEdge edge2 = (DefaultWeightedEdge) completeGraph.addEdge("Madrid", "Zaragoza");
		DefaultWeightedEdge edge3 = (DefaultWeightedEdge) completeGraph.addEdge("Madrid", "Barcelona");
		DefaultWeightedEdge edge4 = (DefaultWeightedEdge) completeGraph.addEdge("Zaragoza", "Barcelona");
		completeGraph.setEdgeWeight(edge1, 10);
		completeGraph.setEdgeWeight(edge2, 10);
		completeGraph.setEdgeWeight(edge3, 100);
		completeGraph.setEdgeWeight(edge4, 20);

		DefaultWeightedEdge edge11 = (DefaultWeightedEdge) completeNoWeightGraph.addEdge("Barcelona", "Zaragoza");
		DefaultWeightedEdge edge12 = (DefaultWeightedEdge) completeNoWeightGraph.addEdge("Madrid", "Zaragoza");
		DefaultWeightedEdge edge13 = (DefaultWeightedEdge) completeNoWeightGraph.addEdge("Madrid", "Barcelona");
		DefaultWeightedEdge edge14 = (DefaultWeightedEdge) completeNoWeightGraph.addEdge("Zaragoza", "Barcelona");
		completeNoWeightGraph.setEdgeWeight(edge11, 1);
		completeNoWeightGraph.setEdgeWeight(edge12, 1);
		completeNoWeightGraph.setEdgeWeight(edge13, 1);
		completeNoWeightGraph.setEdgeWeight(edge14, 1);

		System.out.println("BEFORE TESTS ");

	}

	/**
	 * Test shortest path jumps calculation dijkstra.
	 */
	@Test
	public void testShortestPathJumpsCalculationDijkstra() {
		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(this.completeNoWeightGraph);
		List<DefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath("Madrid", "Barcelona").getEdgeList();
		// There is a direct edge from Madrid to Barcelona with no jumps
		assertTrue(shortestPath.toArray().length == 1);
	}

	/**
	 * Test shortest path calculation dijkstra.
	 */
	@Test
	public void testShortestPathCalculationDijkstra() {
		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(completeGraph);
		List<DefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath("Madrid", "Barcelona").getEdgeList();
		// The shortest path is from Madrid->Zaragoza->Barcelona 2 edges weight: 30.
		// Madrid -> Barcelona direct connection weight: 100
		assertTrue(shortestPath.toArray().length == 2);
	}

	/**
	 * Test shortest path jumps calculation bellman ford.
	 */
	@Test
	public void testShortestPathJumpsCalculationBellmanFord() {

		BellmanFordShortestPath dijkstraShortestPath = new BellmanFordShortestPath(completeNoWeightGraph);
		List<DefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath("Madrid", "Barcelona").getEdgeList();
		// There is a direct edge from Madrid to Barcelona with no jumps
		assertTrue(shortestPath.toArray().length == 1);
	}

	/**
	 * Test shortest path jumps calculatio bellman ford.
	 */
	@Test
	public void testShortestPathJumpsCalculatioBellmanFord() {
		BellmanFordShortestPath dijkstraShortestPath = new BellmanFordShortestPath(completeGraph);
		List<DefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath("Madrid", "Barcelona").getEdgeList();
		// The shortest path is from Madrid->Zaragoza->Barcelona 2 edges weight: 30.
		// Madrid -> Barcelona direct connection weight: 100
		assertTrue(shortestPath.toArray().length == 2);
	}

}
