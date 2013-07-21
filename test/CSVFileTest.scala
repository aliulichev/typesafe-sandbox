import java.io.File
import org.junit._
import Assert._
import parsers.CSVFile

/**
 * Tests CSV file parser
 * User: toha
 * Date: 7/16/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
class CSVFileTest {

    @Test def testFileParsedSucessFully = {
        val persons =  new CSVFile(new File("/Users/toha/Projects/ebay/AddressBook/test/Workbook2.csv"))
        assertEquals(7, persons.size);
    }
}
