package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class MonthlyReport (
                           monthlyReportId:Long,
                           date: Date,
                           noOfCoordinators:Int,
                           noOfCarers:Int,
                           noOfAdherences:Int,
                           noOfChronicSupportGroups:Int,
                           noOfPSRSupportGroups:Int
                           )

object MonthlyReports{
  implicit lazy val monthlyReportFmt = Json.format[MonthlyReport]
}
