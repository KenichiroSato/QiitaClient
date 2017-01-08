package capken.com.qiitaclient

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import capken.com.qiitaclient.model.Article
import capken.com.qiitaclient.view.ArticleView

/**
 * Created by ken on 2017/01/07.
 */

class ArticleListAdapter(private  val context: Context) : BaseAdapter() {

    var articles: List<Article> = emptyList()

    override fun getCount(): Int = articles.size

    override fun getItem(p0: Int): Any = articles[p0]

    override fun getItemId(p0: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? ArticleView) ?: ArticleView(context).apply { setArticle(articles[position]) })


}