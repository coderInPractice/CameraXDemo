package com.manish.app.cameraxdemo

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.manish.app.cameraxdemo.screens.CameraScreen
import com.manish.app.cameraxdemo.ui.theme.CameraXDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CameraXDemoTheme {
                RequestCameraPermission()
            }
        }
    }
}

@Composable
fun RequestCameraPermission() {
    val activity = LocalContext.current as MainActivity
    if (ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_DENIED
    ) {
        // if the permission is denied we are calling
        // request permission method to request permissions.
        Log.d("MainActivity", "RequestCameraPermission: DENIED")
        ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
        CameraPermissionRationale(activity)
    } else {
        CameraScreen()
    }
}

@Composable
fun CameraPermissionRationale(activity:Activity) {
    Box(
        modifier = Modifier.fillMaxSize(), // Makes the Box fill the entire screen
        contentAlignment = Alignment.Center // Centers the content inside the Box
    ) {
        Text(text = "Camera access is necessary to run this app") // Replace this with any composable you want to center
    }

    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), 100)

}

