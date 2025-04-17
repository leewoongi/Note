package com.woongi.home

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.woongi.home.extension.rememberGalleryLauncher
import com.woongi.home.extension.rememberPermissionLauncher
import com.woongi.home.model.mapper.uriToImageBitmap
import com.woongi.home.ui.Note
import com.woongi.home.ui.Toolbar
import com.woongi.home.ui.component.LinePropertiesDialog
import com.woongi.home.ui.component.PermissionSettingDialog
import com.woongi.home.ui.component.PlatteDialog
import com.woongi.home.ui.component.SaveDialog
import com.woongi.navigator.NavigateItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MyAppScreen(
    navigateItem: NavigateItem?,
) {
    val context = LocalContext.current
    val viewModel: MainViewModel = hiltViewModel()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var isPropertyPopupVisible by remember { mutableStateOf(false) }
    var isColorPopupVisible by remember { mutableStateOf(false) }

    val uiModel by viewModel.uiModel.collectAsState()
    val saveDialog by viewModel.saveDialog.collectAsState(null)
    var showPermissionDialog by remember { mutableStateOf(false) }

    val galleryLauncher = rememberGalleryLauncher(
        onImageSelected = { uri ->
            viewModel.saveBitmap(uri.uriToImageBitmap(context))
        },
        onCancelled = {
            Toast.makeText(context, "이미지 선택이 취소되었습니다", Toast.LENGTH_SHORT).show()
        }
    )

    val permissionLauncher = rememberPermissionLauncher(
        onGranted = { galleryLauncher.launch("image/*") },
        onDenied = { showPermissionDialog = true }
    )


    LaunchedEffect(Unit) {
        viewModel.snackBar.collectLatest { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = message
                )
            }
        }
    }

    LaunchedEffect(navigateItem) {
        navigateItem?.let {
            viewModel.setNavigateItem(it)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackBarHostState) }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
            ) {
                Toolbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(56.dp),
                    viewModel = viewModel,
                    onClickGallery = {
                        val permission =  when {
                            Build.VERSION.SDK_INT >= 35 -> arrayOf(
                                Manifest.permission.READ_MEDIA_IMAGES,
                                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                            )
                            Build.VERSION.SDK_INT >= 33 -> arrayOf(
                                Manifest.permission.READ_MEDIA_IMAGES
                            )
                            else -> arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                        }
                        permissionLauncher.launch(permission)
                    },
                    onClickPlatte = { isColorPopupVisible = true },
                    onClickDrawing = { isPropertyPopupVisible = true }
                )

                Note(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    viewModel = viewModel
                )
            }

            Box(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .fillMaxSize(),
            )

            if (isPropertyPopupVisible) {
                LinePropertiesDialog(
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { thickness, opacity ->
                        viewModel.updateThickness(thickness)
                        viewModel.updateOpacity(opacity)
                        isPropertyPopupVisible = false
                    },
                    icon = Icons.Default.Close,
                    thickness = uiModel.currentThickness,
                    opacity = uiModel.currentOpacity
                )
            }

            if(isColorPopupVisible) {
                PlatteDialog(
                    modifier = Modifier.fillMaxWidth(),
                    icon = Icons.Default.Close,
                    selectColorProperty = { color, brightness, opacity ->
                        isColorPopupVisible = false
                        viewModel.updateColor(color, brightness, opacity)
                    },
                    onDismissRequest = { isColorPopupVisible = false }
                )
            }

            saveDialog?.let { uiModel ->
                SaveDialog(
                    uiModel = uiModel,
                    onDismiss = { viewModel.closeDialog() },
                    onPositiveClick = { title -> viewModel.savePath(title) }, // 새로 저장
                    onNegativeClick = { title -> viewModel.coverPath(title)  }, // 덮어쓰기
                    showSnackBar = {
                        coroutineScope.launch {
                            snackBarHostState.showSnackbar(
                                message = "제목을 입력하세요."
                            )
                        }
                    }
                )
            }

            if (showPermissionDialog) {
                PermissionSettingDialog(
                    onDismiss = { showPermissionDialog = false }
                )
            }
        }
    }
}
