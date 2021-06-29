package com.example.mycoindiary

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_d.*
import kotlinx.android.synthetic.main.fragment_d.view.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentD.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentD : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var itemList: ArrayList<NewsItem> ?= arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_d, container, false)

        view.searchbutton.setOnClickListener {
            val what = view.searchitem.getText().toString()
            var intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY,what)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.recyclerView4) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        val myurl ="https://find.mk.co.kr/new/search.php?pageNum=1&cat=&cat1=&media_eco=&pageSize=20&sub=news&dispFlag=OFF&page=news&s_kwd=%C4%DA%C0%CE&s_page=total&go_page=&ord=1&ord1=1&ord2=0&s_keyword=%C4%DA%C0%CE&y1=1991&m1=01&d1=01&y2=2021&m2=05&d2=30&area=ttbd"

        var documentTitle: String = ""
        var itemList: ArrayList<NewsItem> = arrayListOf()
        Single.fromCallable {
            try {
                val doc = Jsoup.connect(myurl).get()
                val elems: Elements = doc.select("div.sub_list")
                run elemsLoop@{
                    elems.forEachIndexed { index, elem ->
                        val title = elem.select("span.art_tit a").text()
                        val about = elem.select("a").text()
                        val url = elem.select("span.art_tit a").attr("href")
                        var item = NewsItem(title, about,url)
                        itemList.add(item)
                        if (index == 9) {
                            return@elemsLoop
                        }
                    }
                }
                documentTitle = doc.title()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return@fromCallable documentTitle
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text ->
                recyclerView.adapter = NewsAdapter(itemList,requireContext())
            }, {
                it.printStackTrace()
            })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentD.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentD().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}