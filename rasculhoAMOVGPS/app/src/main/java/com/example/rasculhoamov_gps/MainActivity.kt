package com.example.rasculhoamov_gps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


val TAG = "Location"

class MainActivity : AppCompatActivity(), LocationListener, OnMapReadyCallback {
    lateinit var fLoc: FusedLocationProviderClient

    //    lateinit var lm: LocationManager
    val ISEC = LatLng(40.1925, -8.4115)
    val DEIS = LatLng(40.1925, -8.4128)
    var locActive = false
    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        lm = getSystemService(LOCATION_SERVICE) as LocationManager
        fLoc = FusedLocationProviderClient(this)
        startLocationUpdates(true)
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates(true)
    }

    override fun onPause() {
        super.onPause()
        if (locActive) {
//        lm.removeUpdates()
            fLoc.removeLocationUpdates(locationCallback)
            locActive = false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        startLocationUpdates(false)
    }

    private fun startLocationUpdates(askPerm: Boolean) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (askPerm)
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    10
                )
            else
                finish()
            return
        }
//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 100f, this)
        val locReq = LocationRequest().apply {
            interval = 4000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fLoc.requestLocationUpdates(locReq, locationCallback, null)
        locActive = true

    }

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude

        Log.i(TAG, "onLocationChanged: $latitude $longitude")


    }


    var locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            Log.i(TAG, "onLocationResult: ")

            p0?.locations?.forEach {
                Log.i(TAG, "locationCallback: ${it.latitude} ${it.longitude}")
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap?) {
        map ?: return
        map.isMyLocationEnabled = true
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        map.uiSettings.isCompassEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true
        val cp = CameraPosition.Builder().target(ISEC).zoom(17f)
            .bearing(0f).tilt(0f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cp))
        map.addCircle(
            CircleOptions().center(ISEC).radius(150.0)
                .fillColor(Color.argb(128, 128, 128, 128))
                .strokeColor(Color.rgb(128, 0, 0)).strokeWidth(4f)
        )
        val mo = MarkerOptions().position(ISEC).title("ISEC-IPC")
            .snippet("Instituto Superior de Engenharia de Coimbra")
        val isec = map.addMarker(mo)
        isec.showInfoWindow()
        map.addMarker(MarkerOptions().position(DEIS).title("DEIS-ISEC"))
    }


    //problemas de compatiblidade

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {}

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

}

