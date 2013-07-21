package parsers

import java.io.File
import org.apache.commons.lang3.time.DateUtils
import java.util.Date

/**
 * Implementation of {UploadedFile) to support CSV files
 * User: toha
 * Date: 7/16/13
 * Time: 1:54 AM
 * To change this template use File | Settings | File Templates.
 */
class CSVFile(file: File) extends UploadedFile(file) {


    override def tokenize(line: String): Array[String] = {
        try {
            val (name, otherFields) = separateName(line) // Separate name from other part
            name.replaceAll("\"", "").trim +: otherFields.split(",").map(_.trim) // get it togather
        } catch {
            case me: MatchError => throw new IllegalArgumentException("Name should be double ßquoted")
        }
    }

    override def parseBirthday(birthday: String): Date = {
        try DateUtils.parseDateStrictly(birthday, "dd/MM/yyyy") catch {
            case pe: ParseException => throw new IllegalArgumentException("Date is not in dd/MM/yyyy format")
        }
    }

    private def separateName(line: String): (String, String) = {
        val namePattern = "^\\s*(\".*\")\\s*,\\s*(.*)".r // Get Double quoted name
        val namePattern(name, otherFields) = line
        (name, otherFields)
    }

    override def processHeader(header: String) = validateHeader(header.split(",").map(_.trim))
}