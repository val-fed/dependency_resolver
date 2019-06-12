import coursier.core.Dependency;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.DependencyResolver;

public class Main {

    public static void main(String[] args) {
        DependencyResolver dependencyResolver = new DependencyResolver();

        Set<Dependency> deps = dependencyResolver.resolve(
                "com.google.guava",
                "guava",
                "27.0.1-jre")
                .dependencies();
        Iterator<Dependency> iterator = deps.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().moduleVersion());
        }
    }
}
