package co.proexe.utils

interface IView<S : IState> {
    fun render(state: S)
}