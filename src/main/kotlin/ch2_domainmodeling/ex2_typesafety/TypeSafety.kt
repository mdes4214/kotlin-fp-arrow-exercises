package ch2_domainmodeling.ex2_typesafety

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
@JvmInline value class Content(val value: String)
@JvmInline value class FileExtension(val value: String)
@JvmInline value class FileName(val value: String)

data class MyFile(
    val title: Title,
    val content: Content,
    val extension: FileExtension,
    val name: FileName
)

fun createMyFile() = MyFile(
    title = Title("Functional Programming"),
    content = Content("This is a learning note for FP."),
    extension = FileExtension(".txt"),
    name = FileName("FP_note")
)

// *** TODO section END ***

