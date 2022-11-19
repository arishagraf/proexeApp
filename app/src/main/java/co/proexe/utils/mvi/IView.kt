package co.proexe.utils.mvi

interface IView<S : IState> {
    fun render(state: S)
}