package parsers

import java.io.File
import org.apache.commons.lang3.time.DateUtils

/**
 * Implementation of {UploadedFile) to support PNR files
 * User: aliulichev
 * Date: 7/16/13
 * Time: 1:54 AM
 */
class PrnFile(file: File) extends UploadedFile(file) {
    var regions = List.empty[(Int, Int)]

    override def tokenize(line: String): Array[String] = {
        regions.map {
            // loop through the regions
            case (startIndex, endIndex) => line.substring(startIndex, endIndex).trim // and extract the fields
        }.toArray
    }

    // Must be overriden if header validation or pre processing required
    override def processHeader(header: String) = {
        regions = List(
            positionOf(Header.Name) -> positionOf(Header.Address),
            positionOf(Header.Address) -> positionOf(Header.Postcode),
            positionOf(Header.Postcode) -> positionOf(Header.Phone),
            positionOf(Header.Phone) -> positionOf(Header.CreditLimit),
            positionOf(Header.CreditLimit) -> positionOf(Header.Birthday),
            positionOf(Header.Birthday) -> header.length
        )

        def positionOf(position: Header.Value): Int = {
            header.indexOf(position.toString) match {
                case -1 => throw ParseException("Cannot read your file. Please make sure you upload valid PNR file")
                case _ => header.indexOf(position.toString)
            }
        }
        validateHeader(tokenize(header))
    }


}
