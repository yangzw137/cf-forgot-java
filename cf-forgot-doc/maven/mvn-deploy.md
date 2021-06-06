- deploy snapshot
```text
mvn clean \
    package deploy:deploy-file \
    -Dfile=jsf-spring-boot-starter-0.0.1-s-SNAPSHOT.jar \
    -DgroupId=com.jd.jsf \
    -DartifactId=jsf-spring-boot-starter \
    -Dversion=0.0.1-s-SNAPSHOT \
    -Dpackaging=jar \
    -Durl=http://artifactory.jd.com/libs-snapshots-local/ \
    -DrepositoryId=libs-snapshots-local \
    -s /Users/yangzhiwei/servers/maven-repostitory/settings.xml \
    -DpomFile=pom.xml \
    -X \
```


- deploy release
```text
mvn clean \
    package deploy:deploy-file \
    -Dfile=jsf-spring-boot-starter.jar \
    -DgroupId=com.jd.jsf \
    -DartifactId=jsf-spring-boot-starter \
    -Dversion=0.0.1-r-20210606-2 \
    -Dpackaging=jar \
    -Durl=http://artifactory.jd.com/libs-releases-local\
    -DrepositoryId=libs-releases-local\
    -s /Users/yangzhiwei/servers/maven-repostitory/settings.xml \
    -DpomFile=pom.xml \
    -X
```
 

