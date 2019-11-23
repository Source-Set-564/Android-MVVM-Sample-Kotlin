package id.nz.template.mvvm.core

/**
 * Created by Anwar on 11/22/2019.
 */
interface BaseUseCase<T> {
    fun setContract(contract : T)
    fun onStop()
}