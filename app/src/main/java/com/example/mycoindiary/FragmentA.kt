package com.example.mycoindiary

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.*
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.dialog1.view.*
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentA : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter:CointemAdapter
    private lateinit var databaseRef: DatabaseReference
    lateinit var listener : TabLayout.OnTabSelectedListener
    var a :String?=null
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
        val view= inflater.inflate(R.layout.fragment_a, container, false)

        a =arguments?.getString("name1")
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.setReverseLayout(true)
        layoutManager.setStackFromEnd(true)
        view.recyclerView.layoutManager = layoutManager

        adapter = CointemAdapter(container!!.context,a!!)
        view.recyclerView.adapter = adapter
        view.recyclerView.addItemDecoration(DividerItemDecoration( container!!.context, LinearLayoutManager.VERTICAL))

         databaseRef = FirebaseDatabase.getInstance().reference
        databaseRef.orderByKey().limitToFirst(10).addValueEventListener(object :
            ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            loadCommentList(snapshot)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("test", "loadItem:onCancelled : ${error.toException()}")
        }
    })


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    fun loadCommentList(dataSnapshot: DataSnapshot) {


        val collectionIterator = dataSnapshot!!.children.iterator()

        if (collectionIterator.hasNext()) {
            adapter.items.clear()
            val comments = collectionIterator.next()
            a =arguments?.getString("name1")

                val itemsIterator = comments.children.iterator()
                while (itemsIterator.hasNext()) {
                    val currentItem = itemsIterator.next()
                    if(currentItem.key.toString()==a){
                        val itemsIterator1 = currentItem.children.iterator()
                        while (itemsIterator1.hasNext()) {
                            val currentItem1 = itemsIterator1.next()

                    val map = currentItem1.value as HashMap<String, Any>
                    val objectId = map["objectId"].toString()
                    val listyear = map["listyear"].toString()
                    val listmonth= map["listmonth"].toString()
                    val listday = map["listday"].toString()
                    val liststart = map["liststart"].toString()
                    val listend = map["listend"].toString()
                    val listprofit = map["listprofit"].toString()
                    val listmemo = map["listmemo"].toString()
                    adapter.items.add(coinitem(objectId, listyear, listmonth, listday, liststart, listend,listprofit,listmemo))
                }}}
                adapter.notifyDataSetChanged()
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentA.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentA().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}