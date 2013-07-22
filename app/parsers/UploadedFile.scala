package parsers

import java.io.File
import scala.io.Source
import java.util.{Calendar, Date}
import models.Person
import java.util

/**
 * Uploaded File base class defines the contract for the uploaded files of different formats
 * User: Anton Liulichev
 * Date: 7/16/13
 * Time: 12:43 AM
 **/

abstract class UploadedFile(file: File) extends Traversable[Person] {

    object Header extends Enumeration {
        val Name = Value("Name")
        val Address = Value("Address")
        val Postcode = Value("Postcode")
        val Phone = Value("Phone")
        val CreditLimit = Value("Credit Limit")
        val Birthday = Value("Birthday")
    }


    private def parsePerson(line: Array[String]): Person = {
        require(line.size.equals(Header.values.size), "Information is incomplete")
        val name = line(Header.Name.id)
        val address = line(Header.Address.id)
        val postcode = line(Header.Postcode.id)
        val phone = line(Header.Phone.id)
        val creditLimit = line(Header.CreditLimit.id)
        val birthday = line(Header.Birthday.id)

        require(name.split(",").size.equals(2), "Name and Lastname should be separated by comma like this \"Smith, John\"")
        require(address.nonEmpty, "Address cannot be empty")
        require(postcode.matches("\\d+\\s*\\w*"), "Postcode is invalid")
        require(phone.nonEmpty, "Phone cannot be empty")
        require(creditLimit.matches("^[\\d.]+$"), "Credit limit should be a numeric value.")
        require(birthday.nonEmpty, "Birthday cannot be empty")
        val birthdate = parseBirthday(birthday);
        require(birthdate.before(Calendar.getInstance().getTime), "Birthday cannot be in the future")

        new Person(name, address, postcode, phone, BigDecimal(creditLimit), birthdate)
    }


    // Extension point. Template method
    def tokenize(line: String): Array[String]

    def parseBirthday(birthday: String): Date

    // Must be overriden if header validation or pre processing required
    def processHeader(header: String)

    override def foreach[U](f: Person => U): Unit = {
        Source.fromFile(file, "ISO-8859-1").getLines().zipWithIndex.filter {
            _._1.nonEmpty
        }.foreach {
            // loop through nonempty lines
            case (line, number) => try {
                number match {
                    case 0 => processHeader(line) // Parse header first
                    case _ => f(parsePerson(tokenize(line)))
                }
            }
            catch {
                case ire: IllegalArgumentException => throw ParseException(ire.getMessage + " at line " + (number + 1))
            }
        }
    }

    def validateHeader(header: Array[String]) {
        require(header.length.equals(Header.values.size),
            "Looks like you miss some columns. " +
                "Please make sure you have following headers:" + Header.values.mkString(","))

        validate(Header.Name, header(Header.Name.id))
        validate(Header.Address, header(Header.Address.id))
        validate(Header.Postcode, header(Header.Postcode.id))
        validate(Header.Phone, header(Header.Phone.id))
        validate(Header.CreditLimit, header(Header.CreditLimit.id))
        validate(Header.Birthday, header(Header.Birthday.id))

        def validate(expected:Header.Value, actual:String) = require(expected.toString().equals(actual),
            "Wrong header found. " + expected.toString() + " expected")

    }
}
