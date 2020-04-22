package com.lightbend.training.scalatrain

case class Time( hours: Int = 0, minutes:Int = 0) {

  require(hours >= 0 && hours < 24, "Invalid check")
  require(minutes >= 0 && minutes < 60, "Invalid check")

  //TODO  Verify that hours is within 0 and 23
  //TODO  Verify that minutes is within 0 and 59

  val asMinutes: Int = hours*60 + minutes //  private [this] to make

  def minus( that: Time): Int = asMinutes - that.asMinutes

  def -(that: Time): Int = minus(that)  // no need explicit this.minus()test
}

object Time {
  def fromMinutes(minutes: Int): Time = Time(minutes/60,minutes%60)
}