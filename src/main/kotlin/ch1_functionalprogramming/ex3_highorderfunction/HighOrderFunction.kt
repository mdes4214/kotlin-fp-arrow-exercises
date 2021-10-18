package ch1_functionalprogramming.ex3_highorderfunction


// TODO:
//  Please make the `createUUID()` function more general. We want to create a UUID with some format whatever we want.
// hint:
//  The `op` should be changed to a function type.
// *** TODO section START ***

fun createUUID(userName: String, userID: String, op: (String, String) -> String): String =
    op(userName, userID)

// *** TODO section END ***