package com.lightbend.training.scalatrain
//import scala.collection.Seq
class JourneyPlanner(trains: Set[Train]) {

  val stations: Set[Station] = trains.flatMap(_.stations)
  // what trains stop at this station
  def trainsAt(station: Station):Set[Train] = trains.filter(train => train.stations.contains(station))

  // What time and what trains you have stopped at the station
  def stopsAtOld(station: Station):Set[(Time,Train)] = for {
    train:Train <- trainsAt(station)
    (time,stationLoop) <- train.schedule if stationLoop == station
  }yield time -> train //equivalent to (time,train)

  def stopsAt(station: Station):Set[(Time,Train)] = for {
    train:Train <- trainsAt(station)
    time: Time <- train.timeAt(station)
  }yield  time -> train //equivalent to (time,train)

  // true if exists a Train in trains where stations contain from and to with at most one other Station in between
    def isShortTripPM(from: Station, to: Station): Boolean = {

      def trainComplies(train: Train): Boolean = {
        val initialSeq = train.stations.dropWhile(station => station != from)
        initialSeq match{
          case seq @ Seq(_*) if !seq.contains(to)=> false
          case Seq(`from`,`to`,_*) => true
          case Seq(`from`,_,`to`,_*) => true
          case _ => false


        }
      }
      trains.dropWhile(train => !trainComplies(train)).nonEmpty

    }

  def isShortTripF(from: Station, to: Station): Boolean = trains.exists(train =>
    train.stations.dropWhile(station=>station!=from).drop(1).take(2).contains(to))

  def isShortTrip(from: Station, to: Station): Boolean = trains.exists(_.stations match {
  case _+:`from`+:`to` +: _ => true
  case _+:`from`+:_+:`to` +: _ => true
  case `from`+:`to` +: _ => true
  case `from`+:_+:`to` +: _ => true
  case _ => false
})
  //  def isShortTrip(from: Station, to: Station): Boolean = {
//
//    val predicate: Train => Boolean = train => train.stations match {
//      case Seq(_,`from`,`to`,_)  => true
//      case Seq(`from`,`to`,_) => true
//      case Seq(_,`from`,_,`to`,_) => true
//      case Seq(`from`,_,`to`,_) => true
//      case _ => false
//    }
//    val trainsUntilMAtch = trains.dropWhile(train => !predicate(train))
//    trainsUntilMAtch.nonEmpty
//  }

}

  // trainsAt gibe us the set of Trains stopping at that station
  // In train, schedule gives us schedule: Seq[(Time, Station)
