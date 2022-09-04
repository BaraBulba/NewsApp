package android.example.newsapp.ui.fragments

import android.example.newsapp.R
import android.example.newsapp.ui.NewsActivity
import android.example.newsapp.ui.NewsViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }

}