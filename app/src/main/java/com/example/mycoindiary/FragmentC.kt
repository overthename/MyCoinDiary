package com.example.mycoindiary

import android.content.ContentValues
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_c.*
import kotlinx.android.synthetic.main.fragment_c.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentC.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentC : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var sums=0;
    var sumend1=0;
    var sumend2=0;
    var sumend3=0;
    var sumend4=0;
    var sumend5=0;
    var sumend6=0;
    var sumend7=0;
    var sumend8=0;
    var sumend9=0;
    var sumend10=0;
    var sumend11=0;
    var sumend12=0;
    var sumstart1=0;
    var sumstart2=0;
    var sumstart3=0;
    var sumstart4=0;
    var sumstart5=0;
    var sumstart6=0;
    var sumstart7=0;
    var sumstart8=0;
    var sumstart9=0;
    var sumstart10=0;
    var sumstart11=0;
    var sumstart12=0;
    var sumprofit1=0;
    var sumprofit2=0;
    var sumprofit3=0;
    var sumprofit4=0;
    var sumprofit5=0;
    var sumprofit6=0;
    var sumprofit7=0;
    var sumprofit8=0;
    var sumprofit9=0;
    var sumprofit10=0;
    var sumprofit11=0;
    var sumprofit12=0;
    var values : ArrayList<Entry> = ArrayList()
    var values1 : ArrayList<Entry> = ArrayList()
    var values2 : ArrayList<Entry> = ArrayList()
    private lateinit var databaseRef: DatabaseReference
    private lateinit var databaseRef1: DatabaseReference
    private lateinit var databaseRef2: DatabaseReference
    var a :String?=null
    var t:Int =0
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
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_c, container, false)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy")
        val formatted = current.format(formatter)
        val cyear = Integer.parseInt(formatted)

        view.charttextstart.text="< 입금금액별 "+formatted+"년 차트 >"
        view.charttextend.text="< 출금금액별 "+ formatted+"년 차트 >"
        view.charttextprofit.text="< 손익금액별 "+formatted+"년 차트 >"

        databaseRef = FirebaseDatabase.getInstance().reference
        var dataset: LineDataSet
        var data: LineData

        val postListener1 = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val collectionIterator = dataSnapshot!!.children.iterator()
                if (collectionIterator.hasNext()) {
                    val comments = collectionIterator.next()
                    a = arguments?.getString("name3")
                    val itemsIterator = comments.children.iterator()
                    while (itemsIterator.hasNext()) {
                        val currentItem = itemsIterator.next()
                        if(currentItem.key.toString()==a){
                            val itemsIterator1 = currentItem.children.iterator()
                            while (itemsIterator1.hasNext()) {
                                val currentItem1 = itemsIterator1.next()

                                val map = currentItem1.value as HashMap<String, Any>
                                val listyear = map["listyear"].toString()
                                val listmonth = map["listmonth"].toString()
                                val liststart = map["liststart"].toString()
                                val listend = map["listend"].toString()
                                val listprofit = map["listprofit"].toString()

                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(1))) {
                                    sumend1 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(2))) {
                                    sumend2 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(3))) {
                                    sumend3 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(4))) {
                                    sumend4 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(5))) {
                                    sumend5 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(6))) {
                                    sumend6 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(9))) {
                                    sumend9 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(7))) {
                                    sumend7 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(8))) {
                                    sumend8 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(10))) {
                                    sumend10 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(11))) {
                                    sumend11 += Integer.parseInt(listend)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(12))) {
                                    sumend12 += Integer.parseInt(listend)
                                }
                            }
                        }
                    }}
                values.add(Entry(1.toFloat(),sumend1.toFloat()))
                values.add(Entry(2.toFloat(),sumend2.toFloat()))
                values.add(Entry(3.toFloat(), sumend3.toFloat()))
                values.add(Entry(4.toFloat(), sumend4.toFloat()))
                values.add(Entry(5.toFloat(), sumend5.toFloat()))
                values.add(Entry(6.toFloat(), sumend6.toFloat()))
                values.add(Entry(7.toFloat(), sumend7.toFloat()))
                values.add(Entry(8.toFloat(), sumend8.toFloat()))
                values.add(Entry(9.toFloat(), sumend9.toFloat()))
                values.add(Entry(10.toFloat(), sumend10.toFloat()))
                values.add(Entry(11.toFloat(), sumend11.toFloat()))
                values.add(Entry(12.toFloat(), sumend12.toFloat()))

                dataset = LineDataSet(values, "월별출금금액")
                dataset.color = Color.BLUE
                dataset.setCircleColor(Color.MAGENTA)
                data= LineData(dataset)
                view.linechart.data = data
                view.linechart.invalidate()


            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        databaseRef.addValueEventListener(postListener1)

        // }
        //  view.charshow1.setOnClickListener {
        databaseRef1 = FirebaseDatabase.getInstance().reference
        var dataset1: LineDataSet
        var data1: LineData

        val postListener2 = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val collectionIterator = dataSnapshot!!.children.iterator()
                if (collectionIterator.hasNext()) {
                    val comments = collectionIterator.next()
                    a = arguments?.getString("name3")
                    val itemsIterator = comments.children.iterator()
                    while (itemsIterator.hasNext()) {
                        val currentItem = itemsIterator.next()
                        if(currentItem.key.toString()==a){
                            val itemsIterator1 = currentItem.children.iterator()
                            while (itemsIterator1.hasNext()) {
                                val currentItem1 = itemsIterator1.next()

                                val map = currentItem1.value as HashMap<String, Any>
                                val listyear = map["listyear"].toString()
                                val listmonth = map["listmonth"].toString()
                                val liststart = map["liststart"].toString()
                                val listend = map["listend"].toString()
                                val listprofit = map["listprofit"].toString()

                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(1))) {
                                    sumstart1 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(2))) {
                                    sumstart2 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(3))) {
                                    sumstart3 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(4))) {
                                    sumstart4 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(5))) {
                                    sumstart5 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(6))) {
                                    sumstart6 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(9))) {
                                    sumstart9 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(7))) {
                                    sumstart7 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(8))) {
                                    sumstart8 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(10))) {
                                    sumstart10 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(11))) {
                                    sumstart11 += Integer.parseInt(liststart)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(12))) {
                                    sumstart12 += Integer.parseInt(liststart)
                                }
                            }
                        }
                    }}
                values1.add(Entry(1.toFloat(),sumstart1.toFloat()))
                values1.add(Entry(2.toFloat(),sumstart2.toFloat()))
                values1.add(Entry(3.toFloat(), sumstart3.toFloat()))
                values1.add(Entry(4.toFloat(), sumstart4.toFloat()))
                values1.add(Entry(5.toFloat(), sumstart5.toFloat()))
                values1.add(Entry(6.toFloat(), sumstart6.toFloat()))
                values1.add(Entry(7.toFloat(), sumstart7.toFloat()))
                values1.add(Entry(8.toFloat(), sumstart8.toFloat()))
                values1.add(Entry(9.toFloat(), sumstart9.toFloat()))
                values1.add(Entry(10.toFloat(), sumstart10.toFloat()))
                values1.add(Entry(11.toFloat(), sumstart11.toFloat()))
                values1.add(Entry(12.toFloat(), sumstart12.toFloat()))



                dataset1 = LineDataSet(values1, "월별입금금액")
                dataset1.color = Color.BLUE
                dataset1.setCircleColor(Color.MAGENTA)
                data1= LineData(dataset1)
                view.linechart1.data = data1
                view.linechart1.invalidate()


            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        databaseRef1.addValueEventListener(postListener2)



        databaseRef2 = FirebaseDatabase.getInstance().reference
        var dataset2: LineDataSet
        var data2: LineData

        val postListener3 = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val collectionIterator = dataSnapshot!!.children.iterator()
                if (collectionIterator.hasNext()) {
                    val comments = collectionIterator.next()
                    a = arguments?.getString("name3")
                    val itemsIterator = comments.children.iterator()
                    while (itemsIterator.hasNext()) {
                        val currentItem = itemsIterator.next()
                        if(currentItem.key.toString()==a){
                            val itemsIterator1 = currentItem.children.iterator()
                            while (itemsIterator1.hasNext()) {
                                val currentItem1 = itemsIterator1.next()

                                val map = currentItem1.value as HashMap<String, Any>
                                val listyear = map["listyear"].toString()
                                val listmonth = map["listmonth"].toString()
                                val liststart = map["liststart"].toString()
                                val listend = map["listend"].toString()
                                val listprofit = map["listprofit"].toString()

                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(1))) {
                                    sumprofit1 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(2))) {
                                    sumprofit2 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(3))) {
                                    sumprofit3 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(4))) {
                                    sumprofit4 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(5))) {
                                    sumprofit5 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(6))) {
                                    sumprofit6 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(9))) {
                                    sumprofit9 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(7))) {
                                    sumprofit7 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(8))) {
                                    sumprofit8 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(10))) {
                                    sumprofit10 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(11))) {
                                    sumprofit11 += Integer.parseInt(listprofit)
                                }
                                if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(12))) {
                                    sumprofit12 += Integer.parseInt(listprofit)
                                }
                            }
                        }
                    }}
                values2.add(Entry(1.toFloat(),sumprofit1.toFloat()))
                values2.add(Entry(2.toFloat(),sumprofit2.toFloat()))
                values2.add(Entry(3.toFloat(), sumprofit3.toFloat()))
                values2.add(Entry(4.toFloat(), sumprofit4.toFloat()))
                values2.add(Entry(5.toFloat(), sumprofit5.toFloat()))
                values2.add(Entry(6.toFloat(), sumprofit6.toFloat()))
                values2.add(Entry(7.toFloat(), sumprofit7.toFloat()))
                values2.add(Entry(8.toFloat(), sumprofit8.toFloat()))
                values2.add(Entry(9.toFloat(), sumprofit9.toFloat()))
                values2.add(Entry(10.toFloat(), sumprofit10.toFloat()))
                values2.add(Entry(11.toFloat(), sumprofit11.toFloat()))
                values2.add(Entry(12.toFloat(), sumprofit12.toFloat()))



                dataset2 = LineDataSet(values2, "월별손익금액")
                dataset2.color = Color.BLUE
                dataset2.setCircleColor(Color.MAGENTA)
                data2= LineData(dataset2)
                view.linechart3.data = data2
                view.linechart3.invalidate()


            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        databaseRef2.addValueEventListener(postListener3)



        return view
    }
        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentC().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}