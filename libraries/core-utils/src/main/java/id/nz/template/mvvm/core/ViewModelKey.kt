package id.nz.template.mvvm.core

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Anwar on 11/22/2019.
 */

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.PROPERTY_SETTER,AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val key : KClass<out ViewModel>)