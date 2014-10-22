name := """neo4j-test"""

version := "1.0-SNAPSHOT"

scalaVersion  := "2.11.1"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "neo4j-releases" at "http://m2.neo4j.org/releases/"

resolvers += "opencast-public" at "http://repository.opencastproject.org/nexus/content/repositories/public/"

libraryDependencies += "org.neo4j" % "neo4j" % "2.1.5"

libraryDependencies += "org.neo4j" % "neo4j-jdbc" % "2.1.4"

libraryDependencies += "org.restlet.jse" % "org.restlet" % "2.1.7"

EclipseKeys.withSource := true

EclipseKeys.withBundledScalaContainers := true

fork := true
