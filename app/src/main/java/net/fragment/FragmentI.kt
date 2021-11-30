package net.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.pacific.adapter.AdapterUtils
import com.pacific.adapter.AdapterViewHolder
import com.pacific.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.layout_f_i.*
import net.adapter.Item
import net.basicmodel.DetailsActivity
import net.basicmodel.R
import net.entity.DataEntity
import net.http.RequestService
import net.http.RetrofitUtils
import net.utils.DataHandleUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class FragmentI:BaseFragment() {
    val adapter = RecyclerAdapter()
    override fun getLayout(): Int {
        return R.layout.layout_f_i
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        val retrofit = RetrofitUtils.get().retrofit()
        retrofit.create(RequestService::class.java).getData()
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    try {
                        val result = response.body()!!.string()
                        val data : ArrayList<DataEntity> = DataHandleUtils.get().strToObject(result)
                        for (item in data){
                            val i = Item(requireActivity(),requireContext(),item)
                            adapter.add(i)
                        }
                        recycler.layoutManager = LinearLayoutManager(requireActivity())
                        recycler.adapter = adapter
                        adapter.onClickListener = View.OnClickListener {
                            val holder: AdapterViewHolder = AdapterUtils.getHolder(it)
                            val entity :DataEntity = data[holder.bindingAdapterPosition]
                            val i = Intent(activity, DetailsActivity::class.java)
                            i.putExtra("data", entity)
                            requireActivity().startActivity(i)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("xxxxxxH", "onFailure = $t")
                }
            })
    }
}