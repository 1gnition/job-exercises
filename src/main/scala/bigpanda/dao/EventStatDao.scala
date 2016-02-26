package bigpanda.dao

import java.util
import javax.persistence.{NoResultException, EntityTransaction, EntityManager, Query}
import bigpanda.entities.EventStat

import scala.collection.JavaConversions._
import scala.util.{Success, Failure, Try}

/**
  * Created by orip on 2/26/2016.
  */
object EventStatDao {
  private val em: EntityManager = EntityManagers.get

  def getAll: List[EventStat] = {
    val tx: EntityTransaction = em.getTransaction
    tx.begin()
    val query: Query = em.createQuery("from EventStat")
    val list: util.List[_] = query.getResultList
    tx.commit()
    list.toList.asInstanceOf[List[EventStat]]
  }

  def inc(eventType: String): Unit = {
    val tx: EntityTransaction = em.getTransaction
    tx.begin()
    val query: Query = em.createQuery(s"from EventStat where eventType = '$eventType'")
    val triedStat: Try[EventStat] = Try(query.getSingleResult.asInstanceOf[EventStat])
    triedStat match {
      case Failure(ex: NoResultException) =>
        tx.commit()
        tx.begin()
        val eventStat: EventStat = new EventStat(eventType, 1)
        em.persist(eventStat)
      case Success(eventStat) =>
        eventStat.cnt += 1
        em.merge(eventStat)
      case Failure(ex) => throw ex
    }
    tx.commit()
  }
}
