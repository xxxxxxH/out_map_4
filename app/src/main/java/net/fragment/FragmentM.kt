package net.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.layout_f_m.*
import net.basicmodel.R
import net.utils.MapOperationUtils
import net.utils.MapUtils

@SuppressLint("MissingPermission")
class FragmentM : BaseFragment() {

    var mMap: GoogleMap? = null
    override fun getLayout(): Int {
        return R.layout.layout_f_m
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapview.onCreate(savedInstanceState)
        mapview.onResume()
        MapsInitializer.initialize(activity)
        initView()
        initViewClick()
    }


    private fun initView() {
        val errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity)
        if (errorCode == ConnectionResult.SUCCESS) {
            mapview.getMapAsync {
                mMap = it
                it.moveCamera(CameraUpdateFactory.newLatLng(LatLng(40.73, -73.99)))
                activity?.let { it1 -> MapOperationUtils.get().getMapAsync(it1, mMap!!) }
            }
        } else {
            GooglePlayServicesUtil.getErrorDialog(errorCode, activity, 0).show()
        }
    }

    private fun initViewClick(){
        search.setOnClickListener {
            MapOperationUtils.get().mapTypeClick("search",requireActivity(),mMap!!)
        }
        mapNormal.setOnClickListener {
            MapOperationUtils.get().mapTypeClick("n",requireActivity(),mMap!!)
        }
        mapHybrid.setOnClickListener {
            MapOperationUtils.get().mapTypeClick("h",requireActivity(),mMap!!)
        }
        mapSat.setOnClickListener {
            MapOperationUtils.get().mapTypeClick("s",requireActivity(),mMap!!)
        }
        mapTer.setOnClickListener {
            MapOperationUtils.get().mapTypeClick("t",requireActivity(),mMap!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                MapOperationUtils.get().mapActivityResult(requireActivity(),data!!,mMap!!)
            }
        }
    }
}