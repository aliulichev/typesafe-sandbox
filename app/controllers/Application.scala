package controllers

import play.api.mvc._
import java.io.File
import java.util.Calendar
import parsers.{PrnFile, CsvFile}
import play.api.libs.json.Json._

object Application extends Controller {

    def index = Action {
        //parse.multipartFormData val uploadedFile = new File("/tmp/picture")
        Ok(views.html.index())
    }

    def upload = Action(parse.multipartFormData) {
        request =>
            request.body.file("workbook").map {
                workbook =>
                    val filename = workbook.filename
                    val file = new File("/tmp/uploads/" + Calendar.getInstance().getTimeInMillis + filename)
                    workbook.ref.moveTo(file)
                    match {
                        case csv if filename.endsWith(".csv") => Ok(toJson(new CsvFile(file).toList))
                        case prn if filename.endsWith(".prn") => Ok(toJson(new PrnFile(file).toList))
                        case _ => BadRequest("We accept only csv and pnr files")
                    }
            }.getOrElse {
                BadRequest("Looks like you miss the file")
            }
    }

}