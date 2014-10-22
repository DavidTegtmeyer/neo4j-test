name := """neo4j-test"""

version := "1.0-SNAPSHOT"

scalaVersion  := "2.11.1"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.neo4j" % "neo4j-kernel" % "2.1.5"

EclipseKeys.withSource := true

EclipseKeys.withBundledScalaContainers := true

fork := true
