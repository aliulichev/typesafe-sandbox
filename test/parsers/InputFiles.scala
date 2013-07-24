package parsers

import java.io.File

/**
 * Test factory for the files being tested
 * User: toha
 * Date: 7/21/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
object InputFiles {

    val CsvValid = fromClasspath("Workbook2.csv")
    val PrnValid = fromClasspath("Workbook2.prn")
    val CsvInvalid = fromClasspath("CsvInvalid.csv")
    val PrnInvalid = fromClasspath("PrnInvalid.prn")
    val CsvBadHeader = fromClasspath("CsvBadHeader.csv")
    val CsvBadName = fromClasspath("CsvBadName.csv")
    val CsvBadAddress = fromClasspath("CsvBadAddress.csv")
    val CsvBadPostcode = fromClasspath("CsvBadPostcode.csv")
    val CsvBadPhone = fromClasspath("CsvBadPhone.csv")
    val CsvBadCreditLimit = fromClasspath("CsvBadCreditLimit.csv")
    val CsvBadBirthday = fromClasspath("CsvBadBirthday.csv")

    private def fromClasspath(path: String): File = new File(this.getClass.getResource(path).getFile)
}
