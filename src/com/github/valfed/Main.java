package com.github.valfed;

import coursier.core.*;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DependencyResolver dependencyResolver = new DependencyResolver();

        List<Dep> jDeps = new ArrayList<>();
        jDeps.add(new Dep("com.google.http-client", "google-http-client", "1.30.1"));
        jDeps.add(new Dep("com.google.guava", "guava", "27.0.1-jre"));

        Seq<Dep> sDeps = CollectionConverters.IterableHasAsScala(jDeps).asScala().toSeq();

        Resolution resolution = dependencyResolver.resolve(sDeps);

        Iterator<Dependency> dependencyIterator = resolution.dependencies().iterator();
        System.out.println("All dependencies:");
        while (dependencyIterator.hasNext()) {
            System.out.println(dependencyIterator.next().moduleVersion());
        }

        System.out.println();

        Iterator<Artifact> artifactIterator = resolution.artifacts().iterator();
        System.out.println("Urls to jars:");
        while (artifactIterator.hasNext()) {
            System.out.println(artifactIterator.next().url());
        }
    }
    /*
    The result:

    All dependencies:
    (com.google.guava:guava,27.0.1-jre)
    (io.grpc:grpc-context,1.19.0)
    (commons-codec:commons-codec,1.11)
    (io.opencensus:opencensus-api,0.21.0)
    (com.google.http-client:google-http-client,1.30.1)
    (com.google.guava:failureaccess,1.0.1)
    (com.google.j2objc:j2objc-annotations,1.3)
    (com.google.errorprone:error_prone_annotations,2.2.0)
    (org.apache.httpcomponents:httpcore,4.4.11)
    (com.google.code.findbugs:jsr305,3.0.2)
    (io.opencensus:opencensus-contrib-http-util,0.21.0)
    (com.google.guava:listenablefuture,9999.0-empty-to-avoid-conflict-with-guava)
    (org.codehaus.mojo:animal-sniffer-annotations,1.17)
    (org.apache.httpcomponents:httpclient,4.5.8)
    (commons-logging:commons-logging,1.2)
    (org.checkerframework:checker-qual,2.5.2)

    Urls to jars:
    https://repo1.maven.org/maven2/com/google/guava/guava/27.0.1-jre/guava-27.0.1-jre.jar
    https://repo1.maven.org/maven2/io/grpc/grpc-context/1.19.0/grpc-context-1.19.0.jar
    https://repo1.maven.org/maven2/commons-codec/commons-codec/1.11/commons-codec-1.11.jar
    https://repo1.maven.org/maven2/io/opencensus/opencensus-api/0.21.0/opencensus-api-0.21.0.jar
    https://repo1.maven.org/maven2/com/google/http-client/google-http-client/1.30.1/google-http-client-1.30.1.jar
    https://repo1.maven.org/maven2/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar
    https://repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/1.3/j2objc-annotations-1.3.jar
    https://repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.2.0/error_prone_annotations-2.2.0.jar
    https://repo1.maven.org/maven2/org/apache/httpcomponents/httpcore/4.4.11/httpcore-4.4.11.jar
    https://repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar
    https://repo1.maven.org/maven2/io/opencensus/opencensus-contrib-http-util/0.21.0/opencensus-contrib-http-util-0.21.0.jar
    https://repo1.maven.org/maven2/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar
    https://repo1.maven.org/maven2/org/codehaus/mojo/animal-sniffer-annotations/1.17/animal-sniffer-annotations-1.17.jar
    https://repo1.maven.org/maven2/org/apache/httpcomponents/httpclient/4.5.8/httpclient-4.5.8.jar
    https://repo1.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar
    https://repo1.maven.org/maven2/org/checkerframework/checker-qual/2.5.2/checker-qual-2.5.2.jar

     */
}
