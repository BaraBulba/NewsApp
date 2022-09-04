package android.example.newsapp.ui

import android.example.newsapp.R
import android.example.newsapp.databinding.ActivityNewsBinding
import android.example.newsapp.db.ArticleDatabase
import android.example.newsapp.repository.NewsRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository = newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory)
            .get(NewsViewModel::class.java)

        binding.bottomNavigationView
            .setupWithNavController(findNavController(R.id.newsNavHostFragment))
    }
}