package scala

import coursier._
import coursier.cache.Cache
import scala.concurrent.ExecutionContext.Implicits.global


final class DependencyResolver() {

  private def startResolution(organization: String, moduleName: String, version: String): Resolution = {
    Resolution(
      Seq(
        Dependency(
          Module(Organization.apply(organization), ModuleName.apply(moduleName)), version
        )
      )
    )
  }

  private val repositories = Seq(
    MavenRepository("https://repo1.maven.org/maven2")
  )

  private val fetch = ResolutionProcess.fetch(repositories, Cache.default.fetch)

  def resolve(organization: String, moduleName: String, version: String): Resolution = {
    startResolution(organization, moduleName, version).process.run(fetch).unsafeRun()
  }
}
