package com.asadbek.bottomsheetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asadbek.bottomsheetcompose.ui.theme.BottomSheetComposeTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetComposeTheme {
                // animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy) == animatsiyasi
                val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed, animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy))
                val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
                val scope = rememberCoroutineScope()
                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center){
                        Text(text = "Bottom sheet", fontSize = 60.sp) // bottom sheet matni va yozuv o`lchami
                    }
                }, sheetBackgroundColor = Color.Green) { // bottom sheet fon rangi
                   Box(
                       modifier = Modifier
                           .fillMaxSize(),
                   ){
                       Button(onClick = { // button on click listeneri
                           scope.launch { // sheetState ning expandi scope.launch ichida ishlaydi
                               if (sheetState.isCollapsed){
                                   sheetState.expand() // button bosilganda bottom sheet yuqoriga ko`tariladi ya`ni ishlaydi.
                               }else{
                                   sheetState.collapse() // button bosilganda bottom sheet pastga ya`ni o`z o`rniga qaytadi.
                               }

                           }

                       }) {
                           Text(text = "Bottom sheet fraction: ${sheetState.progress.fraction}")
                           // fraction - progress darajasini ko`rsatib beradi
                       }
                   }
                }
            }
        }
    }
}

