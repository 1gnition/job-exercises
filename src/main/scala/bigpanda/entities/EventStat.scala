package bigpanda.entities

import javax.persistence._

/**
  * Created by orip on 2/26/2016.
  */
@Entity
@Table(name = "event_stats")
class EventStat(e: String, c: Long) {
  @Id
  @GeneratedValue
  var id: Int = _
  @Column
  var eventType: String = e
  @Column
  var cnt: Long = c

  def this() = this(null, 0)

  override def toString = s"EventStat($id, $eventType, $cnt)"
}
