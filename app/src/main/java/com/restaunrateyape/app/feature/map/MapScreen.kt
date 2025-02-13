package com.restaunrateyape.app.feature.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MarkerState
import com.restaunrateyape.app.R
import com.restaunrateyape.styles.component.topbar.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navHostController: NavHostController, latitude: Double, longitude: Double) {

    Scaffold(topBar = {
        CustomTopAppBar(
            navController = navHostController,
            title = stringResource(R.string.details_information)
        )
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MapSection(latitude = latitude, longitude = longitude)
        }

    }
}

@Composable
fun MapSection(latitude: Double, longitude: Double) {
    val atasehir = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(atasehir, 15f)
    }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.TERRAIN))
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
    ) {
        Marker(
            state = MarkerState(position = atasehir),
            title = "One Marker"
        )
    }
}

