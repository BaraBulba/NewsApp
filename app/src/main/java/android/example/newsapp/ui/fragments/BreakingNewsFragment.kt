package android.example.newsapp.ui.fragments

import android.example.newsapp.R
import android.example.newsapp.adapters.NewsAdapter
import android.example.newsapp.databinding.FragmentBreakingNewsBinding
import android.example.newsapp.ui.NewsActivity
import android.example.newsapp.ui.NewsViewModel
import android.example.newsapp.util.Resource
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!
    val TAG = "BreakingNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->  
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}