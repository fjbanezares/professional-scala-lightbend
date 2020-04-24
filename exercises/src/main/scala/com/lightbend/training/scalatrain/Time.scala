package com.lightbend.training.scalatrain

import play.api.libs.json.{JsNumber, JsObject, JsValue}

import scala.util.{Failure, Success, Try}

case class Time( hours: Int = 0, minutes:Int = 0) extends Ordered[Time]{

  require(hours >= 0 && hours < 24, "Invalid check")
  require(minutes >= 0 && minutes < 60, "Invalid check")

  //TODO  Verify that hours is within 0 and 23
  //TODO  Verify that minutes is within 0 and 59

  val asMinutes: Int = hours*60 + minutes //  private [this] to make

  def minus( that: Time): Int = asMinutes - that.asMinutes

  def -(that: Time): Int = minus(that)  // no need explicit this.minus()test

  override lazy val toString: String = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int = asMinutes - that.asMinutes
//  override def compare(that: Time): Int = {
//    val hoursCompare = this.hours - that.hours
//    if (hoursCompare == 0) this.minutes - that.minutes else hoursCompare
//
//  }


  def toJsonSeq: JsValue = JsObject(Seq(("hours",JsNumber(hours)),("minutes",JsNumber(minutes))))
  def toJson: JsValue = JsObject(Map("hours" -> JsNumber(hours),"minutes" -> JsNumber(minutes)))

}

object Time {

  def fromMinutes(minutes: Int): Time = Time(minutes/60,minutes%60)
  def fromJson(param: JsValue): Option[Time] = {
    val hours: Try[Int] = Try((param \ "hours").as[Int])
    lazy val minutes: Int = Try((param \ "minutes").as[Int]).getOrElse(0)
    hours match {
      case Success(value) => Some(Time(value, minutes))
      case Failure(_) => None
    }
  }
}