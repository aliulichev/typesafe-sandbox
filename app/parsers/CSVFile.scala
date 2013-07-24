package parsers

import java.io.File
import org.apache.commons.lang3.time.DateUtils
import java.util.Date

/**
 * Implementation of {UploadedFile) to support CSV files
 * User: aliulichev
 * Date: 7/16/13
 * Time: 1:54 AM
 */
class CsvFile(file: File) extends UploadedFile(file) {

    // regexp handles cvs with quotes
    override def tokenize(line: String): Array[String] = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").
        map(_.replaceAll("\"", "").trim)

    override def processHeader(header: String) = validateHeader(header.split(",").map(_.trim))
}