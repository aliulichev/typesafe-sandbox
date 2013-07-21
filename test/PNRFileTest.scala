import org.junit.Test
import java.io.File
import org.junit.Assert._
import parsers.PNRFile

/**
 * Tests pnr file parser
 * User: toha
 * Date: 7/20/13
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
class PNRFileTest {

    @Test def testFileParsedSucessFully = {
        val persons =  new PNRFile(new File("/Users/toha/Projects/ebay/AddressBook/test/Workbook2.prn"))
        assertEquals(7, persons.size);
    }

}
