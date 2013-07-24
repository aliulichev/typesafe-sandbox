package parsers

import org.junit._
import Assert._
import org.scalatest.junit.{ShouldMatchersForJUnit, JUnitSuite}
import java.io.File

/**
 * Tests CSV file parser
 * User: toha
 * Date: 7/16/13
 * Time: 10:48 PM
 */
class CSVFileTest extends JUnitSuite with ShouldMatchersForJUnit {


    @Test def testFileParsedSucessFully() {
        val persons = new CsvFile(InputFiles.CsvValid)
        assertEquals(Persons.all.size, persons.size);
        persons.toList.zipWithIndex foreach {
            case (person, number) => TestUtils.assertPersonSame(Persons.all(number), person)
        }
    }

    @Test def testFileParseFailed() {
        evaluating {
            new CsvFile(InputFiles.CsvInvalid).foreach {
                each => fail("Should fail before")
            }
        } should produce[ParseException]
    }

    @Test def testBadAddress() = assertFailsWith(
        InputFiles.CsvBadAddress, "requirement failed: Address cannot be empty at line 2")

    @Test def testBadName() = assertFailsWith(InputFiles.CsvBadName,
        "requirement failed: Name and Lastname should be separated by comma like this \"Smith, John\" at line 2")

    @Test def testBadBirthday = assertFailsWith(InputFiles.CsvBadBirthday,
        "Cannot parse Birthday at line 2")

    @Test def testBadCreditLimit = assertFailsWith(InputFiles.CsvBadCreditLimit,
        "requirement failed: Credit limit should be a numeric value at line 2")

    @Test def testBadPhone = assertFailsWith(InputFiles.CsvBadPhone, "requirement failed: Phone cannot be empty at line 2")

    @Test def testBadPostCode = assertFailsWith(InputFiles.CsvBadPostcode, "requirement failed: Postcode is invalid at line 2");

    private def assertFailsWith(file: File, message: String) {
        val exception = intercept[ParseException] {
            new CsvFile(file).foreach {
                each => fail("Should fail before")
            }
        }
        assertEquals(message, exception.message)
    }
}
