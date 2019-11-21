package id.nz.template.mvvm.core

import android.util.Log
import androidx.annotation.IntRange
import androidx.annotation.NonNull
import id.nz.template.mvvm.core.extension.jsonify

/**
 * Created by Anwar on 10/7/2019.
 */
object LogHelper {
    private const val VERBOSE = Log.VERBOSE
    private const val DEBUG = Log.DEBUG
    private const val INFO = Log.INFO
    private const val WARN = Log.WARN
    private const val ERROR = Log.ERROR
    private const val WTF = Log.ASSERT

    @JvmStatic var TAG = "LogHelper"

    private fun log(
        @IntRange(from = VERBOSE.toLong(), to = WTF.toLong()) level: Int,
        msg: String?) {

        val elements = Throwable().stackTrace
        var callerClassName = "?"
        var callerMethodName = "?"
        var callerLineNumber = "?"
        if (elements.size >= 3) {
            callerClassName = elements[2].className
            callerClassName = callerClassName
                .substring(callerClassName.lastIndexOf('.') + 1)

            if (callerClassName.indexOf("$") > 0) {
                callerClassName = callerClassName
                    .substring(0, callerClassName.indexOf("$"))
            }
            callerMethodName = elements[2].methodName
            callerMethodName = callerMethodName
                .substring(callerMethodName.lastIndexOf('_') + 1)

            if (callerMethodName == "<init>") {
                callerMethodName = callerClassName
            }

            callerLineNumber = elements[2].lineNumber.toString()
        }

        val stack =
            "[" + callerClassName + "." + callerMethodName + "():" + callerLineNumber + "]" +
                    if (msg.isNullOrEmpty()) "" else " "

        when (level) {
            VERBOSE -> Log.v(TAG, stack + msg)
            DEBUG -> Log.d(TAG, stack + msg)
            INFO -> Log.i(TAG, stack + msg)
            WARN -> Log.w(TAG, stack + msg)
            ERROR -> Log.e(TAG, stack + msg)
            WTF -> Log.wtf(TAG, stack + msg)
            else -> {
            }
        }
    }

    @JvmStatic
    fun debug(`object`: Any) {
        log(DEBUG, `object`.jsonify())
    }

    @JvmStatic
    fun debug(@NonNull msg: String) {
        log(DEBUG, msg)
    }

    @JvmStatic
    fun verbose(`object`: Any) {
        log(DEBUG, `object`.jsonify())
    }

    @JvmStatic
    fun verbose(msg: String) {
        log(VERBOSE, msg)
    }

    @JvmStatic
    fun info(`object`: Any) {
        log(DEBUG, `object`.jsonify())
    }

    @JvmStatic
    fun info(msg: String) {
        log(INFO, msg)
    }

    @JvmStatic
    fun warning(`object`: Any) {
        log(DEBUG, `object`.jsonify())
    }

    @JvmStatic
    fun warning(msg: String) {
        log(WARN, msg)
    }

    @JvmStatic
    fun error(`object`: Any) {
        log(DEBUG, `object`.jsonify())
    }

    @JvmStatic
    fun error(msg: String) {
        log(ERROR, msg)
    }

    @JvmStatic
    fun wtf(`object`: Any) {
        log(DEBUG, `object`.jsonify())
    }

    @JvmStatic
    fun wtf(msg: String) {
        log(WTF, msg)
    }
}