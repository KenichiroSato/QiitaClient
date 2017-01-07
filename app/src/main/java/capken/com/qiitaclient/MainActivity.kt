package capken.com.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import capken.com.qiitaclient.model.Article
import capken.com.qiitaclient.model.User
import capken.com.qiitaclient.view.ArticleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val articleView = ArticleView(applicationContext)

        articleView.setArticle(Article(id = "123",
                title = "Koblin 入門",
                url = "http://www.example",
                user = User(id = "456", name = "Taro", profileImageUrl = "")))

        setContentView(articleView)
    }



}
