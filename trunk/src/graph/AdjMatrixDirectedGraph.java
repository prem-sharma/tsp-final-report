/*
 *   Graph Representation using adjacency matrix
 *   
 * 	 This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *   Last Update: 2012/12/09
 */

package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Vector;

public class AdjMatrixDirectedGraph extends Graph {
	
	public AdjMatrixDirectedGraph(int numVertex) {

		super(numVertex);
		
		edgeCost = new Vector<Vector<Double>>(numVertex);

		for (int i = 0; i < numVertex; i++) {
			Vector<Double> costVector = new Vector<Double>(numVertex);
			for (int j = 0; j < numVertex; j++)
				costVector.add(new Double(Graph.MAXEDGECOST));
			edgeCost.add(costVector);
		}

	}

	public int getNumEdges() {
		int numEdges = 0;
		double tmpEdgeCost;
		Iterator itx = edgeCost.iterator();
		while (itx.hasNext()) {
			Vector<Double> row = (Vector<Double>) itx.next();
			Iterator ity = row.iterator();
			while (ity.hasNext()) {
				tmpEdgeCost = (Double) ity.next();
				if (tmpEdgeCost > 0 && tmpEdgeCost < Graph.MAXEDGECOST)
					numEdges++;
			}
		}
		return numEdges;
	}

	public double getEdgeCost(int i, int j) {
		return edgeCost.get(i).get(j);
	}

	public void setEdgeCost(int i, int j, double cost) {
		edgeCost.get(i).set(j, cost);
	}

	// Edge Cost Adjacency Matrix
	public void printCostMatrix() throws IOException {
		BufferedWriter w = new BufferedWriter(new PrintWriter(System.out));
		printCostMatrix(w);
	}

	public void printCostMatrix(BufferedWriter w) throws IOException {

		double tmpCost;
		w.write("      ");
		for (int i = 0; i < getNumVertex(); i++)
			w.write(String.format("%5s ", getVertexName(i)));
		w.write("\n");
		for (int i = 0; i < getNumVertex(); i++) {
			w.write(String.format("%5s ", getVertexName(i)));
			for (int j = 0; j < getNumVertex(); j++) {
				tmpCost=getEdgeCost(i,j);
				if (tmpCost<Graph.MAXEDGECOST)
					w.write(String.format("%5.0f ", tmpCost));
				else w.write(String.format("  INF "));
			}
			w.write("\n");
		}
		w.flush();

	}

	// Adjacency Matrix for the edges cost
	private Vector<Vector<Double>> edgeCost;

}
