package net.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.model.MarkerOptions

class MapOperationUtils {

    companion object {
        private var i: MapOperationUtils? = null
            get() {
                field ?: run {
                    field = MapOperationUtils()
                }
                return field
            }

        @Synchronized
        fun get(): MapOperationUtils {
            return i!!
        }
    }

    @SuppressLint("MissingPermission")
    fun getMapAsync(context: Context, mMap: GoogleMap) {
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.isMyLocationEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.setOnMyLocationButtonClickListener(GoogleMap.OnMyLocationButtonClickListener {
            val locationManager: LocationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location =
                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) as Location
            if (location.latitude != 0.0 && location.longitude != 0.0) {
                mMap.animateCamera(
                    MapUtils.get().getPosition(
                        location.latitude,
                        location.longitude

                    ), 1000, null
                )
            } else {
                mMap.animateCamera(
                    MapUtils.get().getPosition(
                        33.9204, -117.9465
                    ), 1000, null
                )
            }
            false
        })
        mMap.setOnMyLocationClickListener {
            Toast.makeText(context, "Current location:\n$it", Toast.LENGTH_LONG).show()
        }
    }

    fun mapActivityResult(context: Context, data: Intent, mMap: GoogleMap) {
        val place = PlacePicker.getPlace(data, context)
        val toastMsg = String.format("Place: %s", place.name)
        Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
        mMap.addMarker(MarkerOptions().position(place.latLng).title("Marker in Sydney"))
        mMap.animateCamera(
            MapUtils.get().getPosition(place.latLng.latitude, place.latLng.longitude),
            1000,
            null
        )
    }

    fun mapTypeClick(s: String, activity: Activity, mMap: GoogleMap) {
        when (s) {
            "search" -> {
                activity.startActivityForResult(
                    PlacePicker.IntentBuilder().build(activity),
                    1
                )
            }
            "n" -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            "h" -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            "s" -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            "t" -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
        }
    }

    fun initMap(savedInstanceState: Bundle?,mStreetViewPanorama: StreetViewPanorama,panoid:String){
        if (savedInstanceState == null) {
            mStreetViewPanorama.setPosition(panoid)
        }
    }
}