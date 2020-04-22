package com.lightbend.training.scalatrain

import scala.collection.Seq

case class Train( kind:String,number: Int, schedule: Seq[(Time, Station)]) {
  require(schedule.size>=2, "At least 2 elems")
  val stations: Seq[Station] = schedule.map(_._2)
}
