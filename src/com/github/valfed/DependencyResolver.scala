package com.github.valfed

import coursier._
import coursier.cache.Cache

import scala.concurrent.ExecutionContext.Implicits.global


final class DependencyResolver() {

  private def startResolution(deps: Seq[Dep]): Resolution = {
    var dependenciesToResolve: List[Dependency] = List.empty
    deps.foreach(
      dep => {
        dependenciesToResolve = List.from(dependenciesToResolve) :+
          // Converting com.github.valfed.Dep to coursier.Dependency
          Dependency(
            Module(
              Organization.apply(dep.org),
              ModuleName.apply(dep.module)),
            dep.version
        )
      })
    Resolution(dependenciesToResolve)
  }

  private val repositories = Seq(
    MavenRepository("https://repo1.maven.org/maven2")
  )

  private val fetch = ResolutionProcess.fetch(repositories, Cache.default.fetch)

  def resolve(dependencies: Seq[Dep]): Resolution = {
    startResolution(dependencies).process.run(fetch).unsafeRun()
  }
}
