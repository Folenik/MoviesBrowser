package com.folen.moviesbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.MutableLiveData
import com.folen.moviesbrowser.restapi.RetrofitClient
import com.folen.moviesbrowser.restapi.SampleDataClass
import com.folen.moviesbrowser.restapi.SampleRequest
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var myResponse: SampleDataClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() = runBlocking {
        val sampleRequest: SampleRequest = RetrofitClient.getService()
        val call: Call<SampleDataClass> = sampleRequest.getSampleObject()
        call.enqueue(object : Callback<SampleDataClass> {
            override fun onResponse(
                call: Call<SampleDataClass>,
                response: Response<SampleDataClass>
            ) {
                val sampleDataClass: SampleDataClass? = response.body()
                if (sampleDataClass != null) {
                    myResponse = sampleDataClass
                }
            }
            override fun onFailure(call: Call<SampleDataClass?>, t: Throwable) {
                Log.e("Retrofit2 error: ",t.toString())
            }
        })
    }
}