package parsers

/**
 * Exception to be thrown on parse errors
 * User: toha
 * Date: 7/22/13
 * Time: 9:40 PM
 */
case class ParseException(message: String) extends Exception(message)
