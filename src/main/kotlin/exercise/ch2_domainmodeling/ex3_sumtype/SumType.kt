package exercise.ch2_domainmodeling.ex3_sumtype

sealed class State<out A> {
    data class Error(val e: Exception) : State<Nothing>()
    data class Success<A>(val a: A) : State<A>()
    object Cancelled : State<Nothing>()
}

fun <A> processState(
    state: State<A>,
    processCancelled: (State.Cancelled) -> Unit,
    processError: (State.Error) -> Unit,
    processSuccess: (State.Success<A>) -> Unit,
): Unit =
    TODO("Call the input function depends on the `state`. " +
            "i.e., If the `state` is `Cancelled`, then call `processCancelled()` function, and so on.")