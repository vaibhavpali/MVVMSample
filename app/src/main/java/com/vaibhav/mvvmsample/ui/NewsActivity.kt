package com.vaibhav.mvvmsample.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaibhav.mvvmsample.NewsApplication
import com.vaibhav.mvvmsample.data.model.Article
import com.vaibhav.mvvmsample.databinding.ActivityNewsBinding
import com.vaibhav.mvvmsample.di.component.DaggerActivityComponent
import com.vaibhav.mvvmsample.di.module.ActivityModule
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {
    @Inject
    lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var adapter: NewsAdapter

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
        setObserver()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@NewsActivity, it.message, Toast.LENGTH_SHORT).show()
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        }

    }

    private fun renderList(data: List<Article>) {
        adapter.addData(data)
        adapter.notifyDataSetChanged()
    }

    private fun setView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(
                ActivityModule(this)
            ).build().inject(this)
    }
}