package id.nz.template.mvvm.core.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

/**
 * Created by Anwar on 10/7/2019.
 */

object RetrofitUtils{

    /**
     * This method use to create instance of service used of retrofit call
     * @param baseUrl is base url of the endpoint
     * @param service is an interface of the endpoint
     * @return sercive interface instance
     */
    @JvmStatic
    fun <T> createService(baseUrl : String, service : Class<T>) : T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service)
    }

    /**
     * This method use to create instance of service used of retrofit call
     * @param baseUrl is base url of the endpoint
     * @param service is an interface of the endpoint
     * @param client is OkHttpClient
     * @return sercive interface instance
     */
    @JvmStatic
    fun <T> createServiceWithClient(baseUrl : String, service : Class<T>, client : OkHttpClient) : T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(service)
    }


    /**
     * This method to create OkHttpClient
     * @param interceptor used to add interceptor for OkHttpClient
     * @param logger used to enabled logging interceptor OkHttpClient, Level.Body
     * @return OkHttpClient instance
     */
    @JvmStatic
    fun okHttpClientWithInterceptor(
        interceptor: Interceptor,
        level : HttpLoggingInterceptor.Level? = null) : OkHttpClient{

        val bulider = OkHttpClient.Builder()
            .readTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .pingInterval(30,TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if(interceptor is HttpLoggingInterceptor){
            throw IllegalArgumentException("don't send logging interceptor, use level on second" +
                    " parameter if you want to enable logger")
        }

        level?.also { logLevel ->
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = logLevel

            bulider.addInterceptor(loggingInterceptor)
        }

        return bulider.build()
    }

    /**
     * This method to create OkHttpClient with add interceptor HEADER or Query for an api key
     * @param param used to parameter key name
     * @param apiKey used as value apiKye
     * @param method used as how the key is send, HEADER or QUERY
     * @param level used to enabled logging interceptor OkHttpClient, Level.Body
     * @return OkHttpClient instance
     */
    @JvmStatic
    fun okHttpClientWithKey(
        param: String,
        apiKey : String,
        method: KeyParameter,
        level : HttpLoggingInterceptor.Level? = null) : OkHttpClient{

        val builder = OkHttpClient.Builder()
            .readTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .pingInterval(30,TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor(param,apiKey,method))

        level?.also { logLevel ->
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = logLevel

            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    internal class RequestInterceptor(
        val params : String,
        val key : String,
        val parameters : KeyParameter) : Interceptor{

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            if(parameters == KeyParameter.QUERY) {
                val url = request.url()
                    .newBuilder()
                    .addQueryParameter(params, key)
                    .build()

                return chain.proceed(request.newBuilder().url(url).build())
            }else{
                return chain.proceed(request.newBuilder().addHeader(params,key).build())
            }
        }
    }

    enum class KeyParameter{
        HEADER,
        QUERY
    }
}