package controllers

import play.api.mvc._
import java.io.File
import java.util.Calendar
import parsers.{ParseException, PrnFile, CsvFile}
import play.api.libs.json.Json._
import play.api.Logger

object Application extends Controller {
    private val log = Logger(this.getClass)

    def index = Action {
        Ok(views.html.index())
    }
    def upload = Action(parse.multipartFormData) {
        request =>
            request.body.file("workbook").map {
                workbook =>
                    val file = new File("/tmp/uploads/" + Calendar.getInstance().getTimeInMillis + workbook.filename)
                    workbook.ref.moveTo(file)

                    def parseFile(file: File) = file
                    match {
                        case csv if file.getName.endsWith(".csv") => Ok(toJson(new CsvFile(file).toList))
                        case prn if file.getName.endsWith(".prn") => Ok(toJson(new PrnFile(file).toList))
                        case _ => BadRequest("Sorry. We accept only csv and pnr files")
                    }

                    try parseFile(file) catch {
                        case pe: ParseException => BadRequest(pe.getMessage)
                        case e => log.error(e.getMessage); BadRequest("Something went wrong. Cannot process you file at the moment.")
                    }


            }.getOrElse {
                BadRequest("Looks like you miss the file")
            }
    }

}