package parsers

import java.io.File
import org.junit._
import Assert._

/**
 * Tests CSV file parser
 * User: toha
 * Date: 7/16/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
class CSVFileTest {


    @Test def testFileParsedSucessFully = {

        val url = this.getClass().getResource("Workbook2.csv");
        val csv = new File(url.getFile());
        val persons = new CsvFile(csv)
        assertEquals(7, persons.size);
    }
}
