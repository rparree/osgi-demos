
start the database 

```bash
$ docker run -p 5432:5432 -Pd --name jbtravel rparree/jbtravel:1.0 
```

start fuse linked to the database (make sure nexus is running as explained here [README.md](../README.md))

```bash
$ docker run -Pd -p 8101:8101 -p 8181:8181 --name fuse --link nexus --link jbtravel:postgres  rparree/jboss-fuse-full-admin:6.3.0_d2
```

Install the datasource 

```bash
$ ssh -p 8101 admin@localhost
...

JBossFuse:admin@8246026874db> features:install jdbc hibernate jndi
JBossFuse:admin@8246026874db> install mvn:org.postgresql/postgresql/9.4.1208
Bundle ID: 313
JBossFuse:admin@8246026874db> resolve 313
JBossFuse:admin@8246026874db> jdbc:create -t postgres -u postgres -p postgres  -url ${postgres.addr}:${postgres.port} jbtravel

```

Optionally test the datasource

```
JBossFuse:admin@8246026874db> jdbc:datasources 
                Name         Product    Version                                           URL Status
  jdbc/jbtravel, 649      PostgreSQL      9.4.6            jdbc:postgresql://172.17.0.2:5432/    OK

JBossFuse:admin@8246026874db> jdbc:query jdbc/jbtravel "select count(*) from jbtravel.airport"
count
25
```

Show the jndi names

```
JBossFuse:admin@7a6f59de6793> jndi:names 
JNDI Name            Class Name                                                  
osgi:service/jdbc/jbtravel org.postgresql.ds.PGPoolingDataSource                       
osgi:service/jndi    org.apache.karaf.jndi.internal.JndiServiceImpl 
```

Build the package and install the project's feature  