package exercise.ch2_domainmodeling.ex2_typesafety

// TODO:
//  Without domain modeling, sometimes we may be confused by the type.
//  e.g. `val weight: Int = 260`, is this 260g? or 260kg?
//  It will be more clearly and safer if we model the type by domain.
//  i.e. `val weight: WeightGram = WeightGram(260)`, yeah now I know it's 260g.
//  Please try to model data types in `MyFile` as above description.
// hint:
//  The `Title` is for an example.
// *** TODO section START ***

@JvmInline value class Title(val value: String)

data class MyFile(
    val title: Title,
    val content: String,
    val extension: String,
    val name: String
)

fun createMyFile() = MyFile(
    title = Title("Functional Programming"),
    content = "This is a learning note for FP.",
    extension = ".txt",
    name = "FP_note"
)

// *** TODO section END ***

