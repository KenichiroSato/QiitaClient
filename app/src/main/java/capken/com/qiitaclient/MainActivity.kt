package capken.com.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import capken.com.qiitaclient.client.ArticleClient
import capken.com.qiitaclient.model.Article
import capken.com.qiitaclient.model.User
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    private val articleClient: ArticleClient by lazy {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        retrofit.create(ArticleClient::class.java)
    }

    private val listAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter(applicationContext).apply {
            articles = emptyList()//listOf(dummyArticle("title1", "name1"), dummyArticle("title2", "name2"))
        }
    }

    private val progresBar: ProgressBar by lazy {
        findViewById(R.id.progress_bar) as ProgressBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupList()
        setupSearch()
    }

    private fun setupList() {

        val listView: ListView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this, article).let { startActivity(it) }
        }
    }

    private fun setupSearch() {
        val editText = findViewById(R.id.query_edit_text) as EditText
        val button = findViewById(R.id.search_button) as Button

        button.setOnClickListener {
            progresBar.visibility = View.VISIBLE
            articleClient.search(editText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progresBar.visibility = View.GONE
                    }
                    .subscribe({
                        editText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("error: $it")
                    })

        }
    }
/*
    private  fun setUpArticleClient() {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val articleClient = retrofit.create(ArticleClient::class.java)

    }
*/
    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "",
                    title = title,
                    url = "https://www.ipentec.com/document/document.aspx?page=android-webview-page-load-in-webview",
                    user = User(id = "", name = userName, profileImageUrl = ""))


}
