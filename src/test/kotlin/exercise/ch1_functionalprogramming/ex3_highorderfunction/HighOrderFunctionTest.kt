package exercise.ch1_functionalprogramming.ex3_highorderfunction

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HighOrderFunctionTest {
    @Test
    fun testCreateUUID() {
        val userName = "Jack"
        val userID = "12345"
        Assertions.assertEquals("12345_Jack", createUUID(userName, userID) { userName: String, userID: String -> "${userID}_$userName" }, "The format of UUID is different from expected.")
        Assertions.assertEquals("Jack:12345", createUUID(userName, userID) { userName: String, userID: String -> "$userName:$userID" }, "The format of UUID is different from expected.")
        Assertions.assertEquals("12345/Jack", createUUID(userName, userID) { userName: String, userID: String -> "$userID/$userName" }, "The format of UUID is different from expected.")
    }
}