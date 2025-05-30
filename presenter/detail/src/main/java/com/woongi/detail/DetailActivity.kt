package com.woongi.detail

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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


        recyclerView = findViewById(R.id.rv_detail)
        recyclerViewAdapter = DetailRecyclerViewAdapter(
            onClick = { path -> viewModel.navigateHome(item = path)},
            onRemove = { path ->
                viewModel.delete(path)
                viewModel.showSnackBar("삭제 되었습니다.")
            }
        )

        recyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
            createSwipeCallback(
                onSwipe = { position -> recyclerViewAdapter.remove(position = position) }
            ).attachToRecyclerView(this)
        }
    }
}