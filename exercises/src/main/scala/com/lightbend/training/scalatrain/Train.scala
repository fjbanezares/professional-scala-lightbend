package com.lightbend.training.scalatrain

import scala.collection.Seq

case class Train( info: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.size>=2, "At least 2 elems")
  val stations: Seq[Station] = schedule.map(_._2)

  def timeAt(station: Station): Option[Time] = schedule.find{
    case (_,stationSc) => stationSc == station
  } map {
    case (time,_) => time
  }

  def timeAtV1(station: Station): Option[Time] = schedule.filter{
    case (_,stationFromSchedule) => stationFromSchedule == station
  } match {
    case Seq() => None
    case Seq((x: Time,y: Station)) => Some(x)
  }


}
