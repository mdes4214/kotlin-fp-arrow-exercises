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
    when (state) {
        is State.Cancelled -> processCancelled(state)
        is State.Error -> processError(state)
        is State.Success -> processSuccess(state)
    }