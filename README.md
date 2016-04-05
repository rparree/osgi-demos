Organising, grouping together and providing a consistent structure of the OSGI/Fuse/Karaf demos i have used of the past years (still work in progress)

These demos require JBoss Fuse 6.2

# Setup

### nexus

The demos publish to a nexus repository:

```
docker run -d -p 8081:8081 --name nexus --volumes-from nexus-data sonatype/nexus
```

With one time:

```
docker run -d --name nexus-data sonatype/nexus echo "data-only container for Nexus"
docker stop nexus-data
```

### Fuse

See the docker image here https://github.com/rparree/jboss-fuse-docker

```
docker run -Pd -p 8101:8101 -p 61616:61616 \
  --name fuse --link nexus \
    rparree/jboss-fuse-full-admin
```

# The Demos

Please consult the setup below

- **scala-test** a simple scala bundle with an activator. Can be used to check if scala was configured correctly
```bash
JBossFuse:karaf@root> install mvn:com.edc4it/scala-test/0.1.0-SNAPSHOT
```
- **mvn-bnd** a simple maven project to explore the Maven BND plugin
- **simple-service** a simple blueprint service
- **declarative-services** a simple OSGI declarative service (using bnd annotations)
- **simple-service-consumer** camel route using the simple-service
- **simple-service-test** uses [PAX Exam](https://ops4j1.jira.com/wiki/display/PAXEXAM3/Documentation) to test the `SampleService`
- **scala-test-feature** a maven project using the [features-maven-plugin](http://karaf.apache.org/manual/latest-2.3.x/developers-guide/features-maven-plugin-generate.html) to generate a `feature.xml` file
you can create the `feature.xml` by running

```bash
$ mvn generate-resources
$ vi target/classes/feature.xml
```

Then install and use

```shell
JBossFuse:karaf@root> features:addurl file:///../target/classes/feature.xml
JBossFuse:karaf@root> features:install scala-test_2.1
```

- **classloading-sample**:  **bundleA** and **bundleB** both use **common** and the **simple-service**. The demos shows how each bundle has its
own classloader. Publish all and then copy the `feature.xml` to the hot deploy directory.
- **camel-blueprint** Illustrates the use of camel on OSGI as well as a unit test. The configuration uses a PropertyPlaceHolder. Two routes use these properties.
The inlined route uses a property `uriSystemA` (which is overridden during test to a mock). The `demo.blueprint.routes.MyRoute` uses another property (which also is overridden during test).
The value for this property is injected into the Java DSL routebuiler using `@EndpointInject`. Note that the properties have defaulf values, there is no need to add `etc/camel-demo.cfg` to fuse.
To consume the inlined service once deployed to fuse use the following curl command:
```bash
#creates file in /tmp/camel/out
curl -H "Content-Type: text/plain" --data "johnny"  http://localhost:9090/service
```

- **fabric-demo** has been moved to the [camel-demos](https://github.com/rparree/camel-demos/) project ([fabric-demo](https://github.com/rparree/camel-demos/tree/master/fabric-demo))

# Setup

### karaf features

Make sure to install the `camel-scala` feature. 

### SBT 
You will need to install  [SBT 0.13](http://www.scala-sbt.org/download.html)

```bash
$ me@mylinux:camel-demos> sbt
Loading ...
[info] ...
...
> project scala-test
[info] Set current project to scala-test ...
> osgi-bundle
...
[info] Compiling 1 Scala source to ...
[success] Total time: 5 s, completed
```

You can now either deploy the bundle using a file URL, or you could issue the `publishM2` command in sbt (this will
publish to your local repository). To publish to a local nexus server use `publish`


