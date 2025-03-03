package com.woongi.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.woongi.detail.recyclerview.DetailRecyclerViewAdapter
import com.woongi.navigator.NavigateItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_detail)
        init()

        viewModel.load()

        lifecycleScope.launch {
            viewModel.lines.collectLatest {
                recyclerViewAdapter.set(it)
            }
        }

        lifecycleScope.launch {
            viewModel.snackBar.collectLatest { message ->
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        faButton = findViewById(R.id.fab_add)
        faButton.setOnClickListener { viewModel.navigateHome() }


        recyclerView = findViewById(R.id.rv_detail)
        recyclerViewAdapter = DetailRecyclerViewAdapter { path->
           viewModel.navigateHome(item = path)
        }

        recyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}