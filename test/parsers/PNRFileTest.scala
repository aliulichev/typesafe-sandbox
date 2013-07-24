package parsers

import org.junit.Test
import org.junit.Assert._
import org.scalatest.junit.{ShouldMatchersForJUnit, JUnitSuite}

/**
 * Tests prn file parser
 * User: aliulichev
 * Date: 7/20/13
 * Time: 5:00 PMß
 * To change this template use File | Settings | File Templates.
 */
class PNRFileTest extends JUnitSuite with ShouldMatchersForJUnit {

    @Test
    def testFileParsedSucessFully() {
        val persons = new PrnFile(InputFiles.PrnValid)
        assertEquals(Persons.all.size, persons.size);
        persons.toList.zipWithIndex foreach {
            case (person, number) => TestUtils.assertPersonSame(Persons.all(number), person)
        }
    }

    @Test
    def testFileParseFailed(){
        evaluating {
            new PrnFile(InputFiles.PrnInvalid).foreach(each=> fail("Expected te be failed"))
        } should produce[ParseException]
    }

}
