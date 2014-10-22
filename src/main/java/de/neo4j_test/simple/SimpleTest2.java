package de.neo4j_test.simple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;

public class SimpleTest2 {

	public static void main(String[] args) {
		Neo4jConnection connect=null;
		try {
			connect = new Driver(). connect("jdbc:neo4j://localhost:7474", new Properties());
			
			connect.createStatement().executeQuery("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r");
			for (int i = 0; i < 50; i++)
			{
				connect.createStatement().executeQuery("CREATE (n:Kunde {id: '"+ i + "'})");
			}
			for (int i = 0; i < 50; i++)
			{
				connect.createStatement().executeQuery("CREATE (n:Wette {id: "+ i + ", quote: " + (1 + 1/(i+1)) + "})");
			}			
			
			for (int i = 0; i < 200; i++)
			{
				int kundenId = new Random().nextInt(51);
				int wettId = new Random().nextInt(51);
				connect.createStatement().executeQuery("MATCH (kunde:Kunde) WHERE kunde.id = '" + kundenId + "' MATCH (wette:Wette) WHERE wette.id = " + wettId + " CREATE (kunde)-[:KAUFTE]->(wette)");

			}
			connect.createStatement().executeQuery("MATCH (kunde:Kunde) WHERE kunde.id = '3' MATCH (wette:Wette) WHERE wette.id = 3 CREATE (kunde)-[:KAUFTE]->(wette)");
			ResultSet resultSet=connect.createStatement().executeQuery("CREATE (n {name: 'javaQuery'}) RETURN 'hello', n.name");
			System.out.println(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
