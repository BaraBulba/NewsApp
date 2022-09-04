package android.example.newsapp.adapters

import android.example.newsapp.R
import android.example.newsapp.models.Article
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var ivAvatarImage = itemView.findViewById<ImageView>(R.id.ivArticleImage)
        var tvSource = itemView.findViewById<TextView>(R.id.tvSource)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvPublishedAt = itemView.findViewById<TextView>(R.id.tvPublishedAt)

    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_arcticle_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .into(holder.ivAvatarImage)
            holder.tvSource.text = article.source.name
            holder.tvTitle.text = article.title
            holder.tvDescription.text = article.description
            holder.tvPublishedAt.text = article.publishedAt
            setOnClickListener{
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    //
    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}