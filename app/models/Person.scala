package models

import java.util.Date
import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: toha
 * Date: 7/16/13
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
case class Person(name:String, address:String, postcode:String, phone:String, creditLimit:BigDecimal, birthday:Date)
object Person{implicit  val format = Json.format[Person]}
