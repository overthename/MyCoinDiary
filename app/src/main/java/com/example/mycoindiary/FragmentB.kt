package com.example.mycoindiary

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.android.synthetic.main.fragment_b.*
import kotlinx.android.synthetic.main.fragment_b.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentB.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentB : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var databaseRef: DatabaseReference
    var selectYear : Int = 0
    var selectMonth : Int = 0
    var selectDay : Int = 0
    var num1 : String? = null
   var num2 : String?=null
    internal var result : Int? = null
    var bundle: Bundle? =null
    var S : String? = null
    var a :String?=null

    lateinit var datas : coinitem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_b, container, false)

        databaseRef = FirebaseDatabase.getInstance().reference
        view.calendarviewb.setOnDateChangeListener { calendarView, i, i2, i3 ->
            selectYear = i
            selectMonth = i2 + 1
            selectDay = i3

        }
        view.chogi.setOnClickListener {

            view.coinstart.setText("")
            view.coinend.setText("")
            view.coinprofitb.setText("")
            view.coinmemo.setText("")
        }

        view.savecoin.setOnClickListener {
            val year = selectYear.toString()
            val month = selectMonth.toString()
            val day = selectDay.toString()
            val start = coinstart.getText().toString()
            val end = coinend.getText().toString()
            val profit = coinprofitb.getText().toString()
            val memo = coinmemo.getText().toString()

            saveComment(year,month,day,start,end,profit,memo)
            Toast.makeText(container!!.context,"저장되었습니다", Toast.LENGTH_SHORT).show()
        }


        return view
    }
    fun saveComment(year:String,month:String,day:String,start:String,end:String,profit:String,memo:String) {
        a =arguments?.getString("name")

        //Toast.makeText(requireContext(), a, Toast.LENGTH_SHORT).show()
        var key : String? = databaseRef.child("comments").child(a!!).push().getKey()
        val comment = coinitem(key!!, year,month,day,start,end,profit,memo)
        val commentValues : HashMap<String, Any> = comment.toMap()
        // commentValues["timestamp"] = ServerValue.TIMESTAMP
        val childUpdates: MutableMap<String, Any> = HashMap()
        childUpdates["/comments/$a/$key"] = commentValues

        databaseRef.updateChildren(childUpdates)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinsub.setOnClickListener {
            num1 = coinstart.text.toString() //getText().toString()
            num2 = coinend.text.toString()
            if (num1!!.trim() == "" || num2!!.trim() == "") {
                Toast.makeText(requireContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT).show()

            } else {
                //3-2 계산
                result =  Integer.parseInt(num2) - Integer.parseInt(num1)
                //3-3 계산된 결과를 TextView 보여준다
                coinprofitb.text = result.toString() //.setText("계산결과 : " + result.toString())

            }}


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentB.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentB().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}

