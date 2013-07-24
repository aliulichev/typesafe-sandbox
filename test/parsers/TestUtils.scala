package parsers

import models.Person
import junit.framework.Assert

/**
 * Util class for tests
 * User: toha
 * Date: 7/24/13
 * Time: 12:15 AM
 */
trait TestUtils {
      def assertPersonSame(expected:Person, actual:Person) {
          Assert.assertEquals(expected.name, actual.name)
          Assert.assertEquals(expected.address, actual.address)
          Assert.assertEquals(expected.postcode, actual.postcode)
          Assert.assertEquals(expected.phone, actual.phone)
          Assert.assertTrue(expected.creditLimit.equals(actual.creditLimit)
              || expected.creditLimit.equals(actual.creditLimit / 100) )
          Assert.assertEquals(expected.birthday, actual.birthday)
      }

}

object TestUtils extends TestUtils
