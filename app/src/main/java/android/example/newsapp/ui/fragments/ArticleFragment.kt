package android.example.newsapp.ui.fragments


import android.example.newsapp.R
import android.example.newsapp.databinding.FragmentArticleBinding
import android.example.newsapp.ui.NewsActivity
import android.example.newsapp.ui.NewsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article
        binding.webView.apply {
            // Убеждаемся, что загрузка будет в текущем веб вью, а не откроется браузер телефона
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

}