package bigpanda.entities

import javax.persistence._

/**
  * Created by orip on 2/26/2016.
  */
@Entity
@Table(name = "word_stats")
class WordStat(w: String, c: Long) {
  @Id
  @GeneratedValue
  var id: Int = _
  @Column
  var word: String = w
  @Column
  var cnt: Long = c

  def this() = this(null, 0)

  override def toString = s"WordStat($id, $word, $cnt)"
}
