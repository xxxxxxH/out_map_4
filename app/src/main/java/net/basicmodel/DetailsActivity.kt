package net.basicmodel

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.pacific.adapter.AdapterUtils
import com.pacific.adapter.AdapterViewHolder
import com.pacific.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.layout_f_i.*
import net.adapter.Item
import net.entity.DataEntity
import net.http.RequestService
import net.http.RetrofitUtils
import net.utils.DataHandleUtils
import net.utils.MapOperationUtils
import net.utils.StreetViewUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*

/**
 * Copyright (C) 2021,2021/11/30, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class DetailsActivity : AppCompatActivity() {
    var entity: DataEntity? = null
    var retrofit: Retrofit? = null
    private var mStreetViewPanorama: StreetViewPanorama? = null
    val adapter = RecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_details)
        val intent = intent
        entity = intent.getSerializableExtra("data") as DataEntity
        init()
        initMap(savedInstanceState)
    }

    private fun init() {
        retrofit = RetrofitUtils.get().retrofit()
        retrofit!!.create(RequestService::class.java).getSmallData(entity!!.key)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val result = response.body()!!.string()
                    val data = DataHandleUtils.get().strToObject2(result, entity!!)
                    for (item in data) {
                        val i = Item(this@DetailsActivity, this@DetailsActivity, item)
                        adapter.add(i)
                    }
                    recycler.layoutManager = LinearLayoutManager(this@DetailsActivity)
                    recycler.adapter = adapter
                    adapter.onClickListener = View.OnClickListener {
                        val holder: AdapterViewHolder = AdapterUtils.getHolder(it)
                        val dataEntity: DataEntity = data[holder.bindingAdapterPosition]
                        if (entity!!.fife) {
                            StreetViewUtils.get()
                                .setPosition(mStreetViewPanorama!!, "F:" + dataEntity.pannoId)
                        } else {
                            StreetViewUtils.get()
                                .setPosition(mStreetViewPanorama!!, dataEntity.pannoId)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("xxxxxxH", "onFailure = $t")
                }
            })
    }

    private fun initMap(savedInstanceState: Bundle?) {
        try {
            val streetF =
                supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportStreetViewPanoramaFragment?
            streetF?.getStreetViewPanoramaAsync { p0 ->
                mStreetViewPanorama = p0
                MapOperationUtils.get()
                    .initMap(savedInstanceState, mStreetViewPanorama!!, entity!!.panoid)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}