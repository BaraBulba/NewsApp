package android.example.newsapp.ui

import android.example.newsapp.R
import android.example.newsapp.databinding.ActivityNewsBinding
import android.example.newsapp.db.ArticleDatabase
import android.example.newsapp.repository.NewsRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Лучше инициализировать вью модель перед биндингом, т.к. будет выскакивать ошибка
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(
            newsRepository = newsRepository,
            app = application)
        viewModel = ViewModelProvider(this,viewModelProviderFactory)
            .get(NewsViewModel::class.java)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
            val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController = navController)
    }
}