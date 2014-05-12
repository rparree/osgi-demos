Organising, grouping together and providing a consistent structure of the OSGI/Fuse/Karaf demos i have used of the past years (still work in progress) 

# The Demos

Please consult the setup below

- **scala-test** a simple scala bundle with an activator. Can be used to check if scala was configured correctly
```bash
JBossFuse:karaf@root> install mvn:com.edc4it/simple-service_2.11/0.1.0-SNAPSHOT
```
- **simple-service** a simple blueprint configured OSGI Service
- **simple-service-test** uses [PAX Exam](https://ops4j1.jira.com/wiki/display/PAXEXAM3/Documentation) to test the `SampleService` 
- **scala-test-feature** a maven project using the [features-maven-plugin](http://karaf.apache.org/manual/latest-2.3.x/developers-guide/features-maven-plugin-generate.html) to generate a `feature.xml` file
# Setup 

You will need to install  [SBT 0.13](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html)

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

You can now either deploy the bundle using a file URL, or you could issue the `publish` command in sbt (this will 
publish to your local repository)


## Configure Karaf

Add the sonatype repository to the list of repositories in `$KARAF_HOME/etc/org.ops4j.pax.url.mvn.cfg`

```ini
org.ops4j.pax.url.mvn.repositories= \
    http://repo1.maven.org/maven2@id=maven.central.repo, \
    http://repo.fusesource.com/nexus/content/repositories/releases@id=fusesource.release.repo, \
    http://repo.fusesource.com/nexus/content/groups/ea@id=fusesource.ea.repo, \
    http://svn.apache.org/repos/asf/servicemix/m2-repo@id=servicemix.repo, \
    http://repository.springsource.com/maven/bundles/release@id=springsource.release.repo, \
    http://repository.springsource.com/maven/bundles/external@id=springsource.external.repo, \
    http://scala-tools.org/repo-releases@id=scala.repo, \
    https://oss.sonatype.org/content/groups/scala-tools@id=sonatype.repo
```

install Scala 2.11

```bash
JBossFuse:karaf@root> osgi:install mvn:org.scala-lang/scala-library/2.11.0
```
