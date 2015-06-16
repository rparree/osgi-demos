exit# Fabric Demo

## Setup the fabric

1. Create a new fabric

```shell
fabric:create --zookeeper-password masterkey --global-resolver localip --wait-for-provisioning
```

2. create three child containers

```shell
container-create-child --jmx-user admin --jmx-password admin --profile jboss-fuse-minimal root node 3
```

## Create the profiles

1. The client (plus deploy to node1)

```shell
profile-edit --bundles mvn:com.edc4it/fabric-demo-client/0.1.0-SNAPSHOT fabric-demo-client 
container-add-profile node1 fabric-demo-client 
```

1. The base for the http services

```shell
profile-create fabric-demo-jetty.base
profile-edit --bundles mvn:com.edc4it/fabric-jetty/0.1.0-SNAPSHOT fabric-demo-jetty.base
```

2. http service X

```shell
profile-create --parents fabric-demo-jetty.base fabric-demo-jetty.x
profile-edit -p fabric.demo.jetty/port=9092 fabric-demo-jetty.x 
profile-edit -p fabric.demo.jetty/suffix=home.com fabric-demo-jetty.x

container-add-profile node2 fabric-demo-jetty.x
```
2. http service Y

```shell
profile-create --parents fabric-demo-jetty.base fabric-demo-jetty.y
profile-edit -p fabric.demo.jetty/port=9093 fabric-demo-jetty.y
profile-edit -p fabric.demo.jetty/suffix=work.com fabric-demo-jetty.y

container-add-profile node3 fabric-demo-jetty.y
```

## Test

Tail the child containers' log files 

```shell
tail -F instances/node*/data/log/karaf.log | grep test
```

Craete some files and watch the log

```shell
echo -n test > /tmp/camel-box/test.txt
```

