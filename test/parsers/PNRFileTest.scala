package parsers

import org.junit.Test
import java.io.File
import org.junit.Assert._

/**
 * Tests pnr file parser
 * User: toha
 * Date: 7/20/13
 * Time: 5:00 PMß
 * To change this template use File | Settings | File Templates.
 */
class PNRFileTest {

    @Test def testFileParsedSucessFully = {
        val url = this.getClass().getResource("Workbook2.prn")
        val pnr = new File(url.getFile());
        val persons =  new PrnFile(pnr)
        assertEquals(7, persons.size);
    }

}
