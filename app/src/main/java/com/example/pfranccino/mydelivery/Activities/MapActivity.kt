package com.example.pfranccino.mydelivery.Activities


import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.location.LocationProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import com.example.pfranccino.mydelivery.R
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode
import com.example.pfranccino.mydelivery.Models.User
import kotlinx.android.synthetic.main.activity_new.*

class MapActivity : AppCompatActivity(), PermissionsListener, LocationEngineListener {


    private lateinit var mapView : MapView
    private lateinit var map : MapboxMap
    private lateinit var confirmAddressButton : Button
    private lateinit var permissionManager : PermissionsManager
    private lateinit var originLocation : Location
    private lateinit var originPosition : Point
    private lateinit var destinationPosition: Point


    private var locationEngine : LocationEngine? = null
    private var locationLayerPlugin : LocationLayerPlugin? = null
    private var destinationMarker: Marker? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val user = intent.getSerializableExtra("objeto") as User



        Mapbox.getInstance(this, getString(R.string.access_token))
        mapView = findViewById(R.id.mapView)

        confirmAddressButton = findViewById(R.id.confirmAddressButton)

        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync {mapboxMap ->
            map = mapboxMap

            enableLocation()
        }

        confirmAddressButton.setOnClickListener{




        }
    }

    private fun enableLocation() {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            initializeLocationEngine()
            initializeLocationLayer()

            Log.d("enable location", "True")
        } else {
            permissionManager = PermissionsManager(this)
            permissionManager.requestLocationPermissions(this)
        }
    }

    @SuppressWarnings("MissingPermission")
    private fun initializeLocationEngine() {
        locationEngine = LocationEngineProvider(this).obtainBestLocationEngineAvailable()
        locationEngine?.priority = LocationEnginePriority.HIGH_ACCURACY
        locationEngine?.activate()

        val lastLocation = locationEngine?.lastLocation

        if (lastLocation != null) {
            originLocation = lastLocation
            setCameraPosition(lastLocation)
        } else {
            locationEngine?.addLocationEngineListener(this)
        }
    }

    private fun setCameraPosition(location: Location) {
        map.animateCamera(CameraUpdateFactory.
                newLatLngZoom(LatLng(location.latitude, location.longitude), 13.0))
    }

    private fun initializeLocationLayer() {
        locationLayerPlugin = LocationLayerPlugin(mapView, map, locationEngine)
        locationLayerPlugin?.setLocationLayerEnabled(true)
        locationLayerPlugin?.cameraMode = CameraMode.TRACKING
        locationLayerPlugin?.renderMode = RenderMode.NORMAL
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        // Explain why we need access to GPS


    }

    override fun onPermissionResult(granted: Boolean) {
        // The user has granted permissions, send position to backend

        if (granted) {
            enableLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onLocationChanged(location: Location?) {
        Log.d("changed location", "true")
        location?.let {
            originLocation = location
            setCameraPosition(location)
        }
    }


    @SuppressWarnings("MissingPermission")
    override fun onConnected() {
        locationEngine?.requestLocationUpdates()
    }



    @SuppressWarnings("MissingPermission")
    override fun onStart() {
        super.onStart()

        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            locationEngine?.requestLocationUpdates()



            locationLayerPlugin?.onStart()


        }

        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()

        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()

        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()

        locationEngine?.removeLocationUpdates()
        locationLayerPlugin?.onStop()

        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        locationEngine?.deactivate()

        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        if (outState != null) {
            mapView.onSaveInstanceState(outState)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()

        mapView.onLowMemory()
    }


    /*override fun onMapClick(point: LatLng) {
        destinationMarker = map.addMarker(MarkerOptions().position(point))
        destinationPosition = Point.fromLngLat(point.longitude, point.latitude)
        originPosition = Point.fromLngLat(originLocation.longitude, originLocation.latitude)

        confirmAddressButton.isEnabled = true
        confirmAddressButton.setBackgroundResource(R.color.mapboxBlue)
    }*/


}
