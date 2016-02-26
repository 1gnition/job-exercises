package bigpanda.dao

import javax.persistence.{EntityManager, EntityManagerFactory, Persistence}

/**
  * Created by orip on 2/26/2016.
  */
object EntityManagers {
  private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("pu")

  def get: EntityManager = {
    emf.createEntityManager()
  }
}
