package bigpanda.dao

import java.util
import javax.persistence.{EntityManager, EntityTransaction, NoResultException, Query}

import bigpanda.entities.WordStat

import scala.collection.JavaConversions._
import scala.util.{Failure, Success, Try}

/**
  * Created by orip on 2/26/2016.
  */
object WordStatDao {
  private val em: EntityManager = EntityManagers.get

  def getAll: List[WordStat] = {
    val tx: EntityTransaction = em.getTransaction
    tx.begin()
    val query: Query = em.createQuery("from WordStat")
    val list: util.List[_] = query.getResultList
    tx.commit()
    list.toList.asInstanceOf[List[WordStat]]
  }

  def inc(word: String): Unit = {
    val tx: EntityTransaction = em.getTransaction
    tx.begin()
    val query: Query = em.createQuery(s"from WordStat where word = '$word'")
    val triedStat: Try[WordStat] = Try(query.getSingleResult.asInstanceOf[WordStat])
    triedStat match {
      case Failure(ex: NoResultException) =>
        tx.commit()
        tx.begin()
        val wordStat: WordStat = new WordStat()
        wordStat.word = word
        wordStat.cnt = 1
        em.persist(wordStat)
      case Success(wordStat) =>
        wordStat.cnt += 1
        em.merge(wordStat)
      case Failure(ex) => throw ex
    }
    tx.commit()
  }
}
