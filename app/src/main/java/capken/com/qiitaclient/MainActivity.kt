package capken.com.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import capken.com.qiitaclient.model.Article
import capken.com.qiitaclient.model.User
import capken.com.qiitaclient.view.ArticleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(dummyArticle("title1", "name1"), dummyArticle("title2", "name2"))

        val listView: ListView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
    }

    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "",
                    title = title,
                    url = "",
                    user = User(id = "", name = userName, profileImageUrl = ""))


}
