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
    fun okHttpClient(interceptor: Interceptor, logger : Boolean) : OkHttpClient{
        val bulider = OkHttpClient.Builder()
            .readTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .pingInterval(30,TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if(interceptor is HttpLoggingInterceptor){
            throw IllegalArgumentException("don't send logging interceptor, use true on second" +
                    " parameter if you want to enable logger")
        }

        if(logger) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            bulider.addInterceptor(loggingInterceptor)
        }

        return bulider.build()
    }

    /**
     * This method to create OkHttpClient with add interceptor HEADER or Query for an api key
     * @param params used to parameter key name
     * @param apiKey used as value apiKye
     * @param logger used to enabled logging interceptor OkHttpClient, Level.Body
     * @return OkHttpClient instance
     */
    @JvmStatic
    fun okHttpClient(params: String, apiKey : String, method: KeyParameter, logger : Boolean) : OkHttpClient{
        val builder = OkHttpClient.Builder()
            .readTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .pingInterval(30,TimeUnit.SECONDS)
            .addInterceptor(RequestQueryInterceptor(params,apiKey,method))

        if(logger) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    internal class RequestQueryInterceptor(
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