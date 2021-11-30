package net.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import net.event.MessageEvent
import org.greenrobot.eventbus.EventBus

/**
 * Copyright (C) 2021,2021/11/30, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class StreetViewUtils {
    companion object {
        private var i: StreetViewUtils? = null
            get() {
                field ?: run {
                    field = StreetViewUtils()
                }
                return field
            }

        @Synchronized
        fun get(): StreetViewUtils {
            return i!!
        }
    }

    fun getLocationManager(context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @SuppressLint("MissingPermission")
    fun getLocation(locationManager: LocationManager): Location? {
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    fun streetViewPanoramaAsync(
        mStreetViewPanorama: StreetViewPanorama,
        marker: Marker,
        markerPosition: LatLng
    ) {
        mStreetViewPanorama.setOnStreetViewPanoramaChangeListener { streetViewPanoramaLocation ->
            if (streetViewPanoramaLocation != null) {
                marker.position = streetViewPanoramaLocation.position
            }
        }
        mStreetViewPanorama.setPosition(markerPosition)
    }

    fun getMapAsync(
        googleMap: GoogleMap,
        mStreetViewPanorama: StreetViewPanorama,
        markerPosition: LatLng
    ) {
        googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {}
            override fun onMarkerDrag(marker: Marker) {}
            override fun onMarkerDragEnd(marker: Marker) {
                mStreetViewPanorama.setPosition(marker.position, 150)
            }
        })
        googleMap.moveCamera(
            MapUtils.get().getPosition(markerPosition.latitude, markerPosition.longitude)
        )
        val marker = googleMap.addMarker(
            MapUtils.get().getMarkerOptions(
                markerPosition.latitude,
                markerPosition.longitude
            )
        )
        EventBus.getDefault().post(MessageEvent("marker", marker))
        googleMap.setOnMapClickListener { latLng ->
            googleMap.clear()
            val marker =
                googleMap.addMarker(
                    MapUtils.get().getMarkerOptions(latLng.latitude, latLng.longitude)
                )
            mStreetViewPanorama.setPosition(latLng)
            EventBus.getDefault().post(MessageEvent("marker", marker))
        }
    }

    fun setPosition(mStreetViewPanorama: StreetViewPanorama,pannoId:String){
        mStreetViewPanorama.setPosition(pannoId)
    }
}