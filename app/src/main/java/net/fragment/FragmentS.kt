package net.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import net.basicmodel.R
import net.event.MessageEvent
import net.utils.StreetViewUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@SuppressLint("MissingPermission")
class FragmentS : BaseFragment() {
    var sydeny = LatLng(-33.87365, 151.20689)
    var mStreetViewPanorama: StreetViewPanorama? = null
    var marker: Marker? = null
    var markerPosition: LatLng? = null
    override fun getLayout(): Int {
        return R.layout.layout_f_s
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
        initView()
    }

    private fun initView() {
        val locationManager = StreetViewUtils.get().getLocationManager(requireActivity())
        val location = StreetViewUtils.get().getLocation(locationManager)
        markerPosition = if (location != null) {
            LatLng(location.latitude, location.longitude)
        } else {
            sydeny
        }
        val panoramaFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.streetviewpanorama) as SupportStreetViewPanoramaFragment?
        panoramaFragment?.getStreetViewPanoramaAsync { streetViewPanorama ->
            mStreetViewPanorama = streetViewPanorama
            StreetViewUtils.get()
                .streetViewPanoramaAsync(mStreetViewPanorama!!, marker!!, markerPosition!!)
        }
        val mapFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.mapstreet) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            StreetViewUtils.get().getMapAsync(googleMap, mStreetViewPanorama!!, markerPosition!!)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        val msg = event.getMessage()
        when (msg[0]) {
            "marker" -> {
                marker = msg[1] as Marker?
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}