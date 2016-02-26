package bigpanda.dao

import java.util
import javax.persistence.{EntityManager, Query}

import bigpanda.entities.WordStat

import scala.collection.JavaConversions._

/**
  * Created by orip on 2/26/2016.
  */
object WordStatDao {
  private val em: EntityManager = EntityManagers.get

  def getAll: List[WordStat] = {
    val query: Query = em.createQuery("from WordStat")
    val list: util.List[_] = query.getResultList
    list.toList.asInstanceOf[List[WordStat]]
  }

  def inc(word: String): Unit = {
    try {
      em.getTransaction.begin()
      val query: Query = em.createQuery(s"from WordStat where word = '$word'")
      val list: util.List[_] = query.getResultList
      if (list.isEmpty) {
        val wordStat: WordStat = new WordStat(word, 1)
        em.persist(wordStat)
      } else {
        val wordStat: WordStat = list.head.asInstanceOf[WordStat]
        wordStat.cnt += 1
        em.merge(wordStat)
      }
    } catch {
      case ex: Throwable =>
        em.getTransaction.rollback()
        println(ex.getMessage)
    } finally {
      em.getTransaction.commit()
    }
  }
}
