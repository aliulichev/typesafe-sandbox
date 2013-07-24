package parsers

import org.apache.commons.lang.time.DateUtils
import models.Person

/**
 * Person factory
 * User: toha
 * Date: 7/23/13
 * Time: 12:08 AM
 */
object Persons {
    val John = new
            Person("Johnson, John",
                "Voorstraat 32", "3122gg",   "020 3849381",
                BigDecimal.valueOf(10000), DateUtils.parseDateStrictly("01/01/1987", Array("dd/MM/yyyy")))
    val Paul = new
            Person("Anderson, Paul",
                "Dorpsplein 3A","4532 AA","030 3458986",
                BigDecimal.valueOf(109093), DateUtils.parseDateStrictly("03/12/1965", Array("dd/MM/yyyy")))
    val Steve = new
            Person("Wicket, Steve",
                "Mendelssohnstraat 54d","3423 ba","0313-398475",
                BigDecimal.valueOf(934), DateUtils.parseDateStrictly("03/06/1964", Array("dd/MM/yyyy")))
    val Pat = new
            Person("Benetar, Pat",
                "Driehoog 3zwart","2340 CC","06-28938945",
                BigDecimal.valueOf(54), DateUtils.parseDateStrictly("04/09/1964", Array("dd/MM/yyyy")))
    val Mal = new
            Person("Gibson, Mal",
                "Vredenburg 21", "3209 DD", "06-48958986",
                BigDecimal.valueOf(54.5), DateUtils.parseDateStrictly("09/11/1978", Array("dd/MM/yyyy")))
    val User = new
            Person("Friendly, User",
                "Sint Jansstraat 32", "4220 EE", "0885-291029",
                BigDecimal.valueOf(63.6), DateUtils.parseDateStrictly("10/08/1980", Array("dd/MM/yyyy")))

    val JohnSmith = new
            Person("Smith, John",
                "Borkestrasse 32", "87823","+44 728 889838",
                BigDecimal.valueOf(9898.3), DateUtils.parseDateStrictly("20/09/1999", Array("dd/MM/yyyy")))

    val all = Array(John, Paul, Steve, Pat, Mal, User, JohnSmith)
}
