package bigpanda.dao

import java.util
import javax.persistence.{EntityManager, Query}

import bigpanda.entities.EventStat

import scala.collection.JavaConversions._

/**
  * Created by orip on 2/26/2016.
  */
object EventStatDao {
  private val em: EntityManager = EntityManagers.get

  def getAll: List[EventStat] = {
    val query: Query = em.createQuery("from EventStat")
    val list: util.List[_] = query.getResultList
    list.toList.asInstanceOf[List[EventStat]]
  }

  def inc(eventType: String): Unit = {
    try {
      em.getTransaction.begin()
      val query: Query = em.createQuery(s"from EventStat where eventType = '$eventType'")
      val list: util.List[_] = query.getResultList
      if (list.isEmpty) {
        val eventStat: EventStat = new EventStat(eventType, 1)
        em.persist(eventStat)
      } else {
        val eventStat: EventStat = list.head.asInstanceOf[EventStat]
        eventStat.cnt += 1
        em.merge(eventStat)
      }
    } catch {
      case ex: Throwable =>
        if (em.getTransaction.isActive) {
          em.getTransaction.rollback()
        }
        ex.printStackTrace()
    } finally {
      if (em.getTransaction.isActive) {
        em.getTransaction.commit()
      }
    }
  }
}
