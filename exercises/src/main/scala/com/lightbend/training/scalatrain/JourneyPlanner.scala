package com.lightbend.training.scalatrain
//import scala.collection.Seq
class JourneyPlanner(trains: Set[Train]) {

  val stations: Set[Station] = trains.flatMap(_.stations)
  def trainsAt(station: Station):Set[Train] = trains.filter(train => train.stations.contains(station))

}
