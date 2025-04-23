package com.woongi.detail

import android.Manifest
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Build.VERSION_CODES.VANILLA_ICE_CREAM
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.woongi.detail.extension.registerPermissionLauncher
import com.woongi.detail.extension.requestGallery
import com.woongi.detail.recyclerview.DetailRecyclerViewAdapter
import com.woongi.detail.recyclerview.event.createSwipeCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var toolbar: Toolbar
    private lateinit var faButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: DetailRecyclerViewAdapter
    private lateinit var subTitleTextView: TextView
    private lateinit var settingButton: ImageView
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var galleryLauncher: ActivityResultLauncher<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_detail)
        init()

        lifecycleScope.launch {
            viewModel.lines.collectLatest {
                subTitleTextView.text = this@DetailActivity.getString(R.string.note_count, it.size)
                recyclerViewAdapter.set(it)
            }
        }

        lifecycleScope.launch {
            viewModel.snackBar.collectLatest { message ->
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        permissionLauncher = registerPermissionLauncher(
            onGranted = { openGallery() }
        )

        galleryLauncher = requestGallery(
            onImageSelected = { uri ->

            },
            onCancelled = {
                Toast.makeText(this, "이미지 선택이 취소되었습니다", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        faButton = findViewById(R.id.fab_add)
        faButton.setOnClickListener { viewModel.navigateHome() }
        subTitleTextView = findViewById(R.id.tv_sub_title)
        settingButton = findViewById(R.id.btn_more)

        recyclerView = findViewById(R.id.rv_detail)
        recyclerViewAdapter = DetailRecyclerViewAdapter(
            onClick = { path -> viewModel.navigateHome(item = path) },
            onRemove = { path ->
                viewModel.delete(path)
                viewModel.showSnackBar("삭제 되었습니다.")
            }
        )

        recyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
            createSwipeCallback(
                onSwipe = { position ->
                    recyclerViewAdapter.remove(position = position)
                }
            ).attachToRecyclerView(this)
        }

        settingButton.setOnClickListener {
            val popupMenu = PopupMenu(this@DetailActivity, settingButton)
            menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.item_image -> {
                        val permission =  when {
                            Build.VERSION.SDK_INT >= VANILLA_ICE_CREAM -> arrayOf(
                                Manifest.permission.READ_MEDIA_IMAGES,
                                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                            )
                            Build.VERSION.SDK_INT >= TIRAMISU -> arrayOf(
                                Manifest.permission.READ_MEDIA_IMAGES
                            )
                            else -> arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                        }
                        permissionLauncher.launch(permission)
                    }
                }
                false
            }
            popupMenu.show()
        }
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }
}