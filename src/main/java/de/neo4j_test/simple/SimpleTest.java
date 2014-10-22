package de.neo4j_test.simple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;

public class SimpleTest {
	private static String DB_PATH = "db/data";
	private static enum RelTypes implements RelationshipType
	{
	    KNOWS
	}
	public static void main(String[] args) {
		Neo4jConnection connect=null;
		try {
			connect = new Driver(). connect(DB_PATH, new Properties());
			ResultSet resultSet=connect.createStatement().executeQuery("CREATE (n {name:\"javaQuery\"}) RETURN \"hello\", n.name");
			System.out.println(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// usingGraphDBService();
	}

	private static void usingGraphDBService() {
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		
		registerShutdownHook( graphDb );

		try ( Transaction tx = graphDb.beginTx() )
		{
			Node firstNode = graphDb.createNode();
			 firstNode.setProperty( "message", "Hello, " );
			Node secondNode = graphDb.createNode();
			secondNode.setProperty( "message", "World!" );

			Relationship relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
			relationship.setProperty( "message", "brave Neo4j " );
			
		    tx.success();
		} catch (Exception e) {
			e.printStackTrace();
		}

		JOptionPane.showConfirmDialog(null, "Mach mich zu :)");
	}

	private static void registerShutdownHook( final GraphDatabaseService graphDb )
	{
	    // Registers a shutdown hook for the Neo4j instance so that it
	    // shuts down nicely when the VM exits (even if you "Ctrl-C" the
	    // running application).
	    Runtime.getRuntime().addShutdownHook( new Thread()
	    {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	        }
	    } );
	}
}
