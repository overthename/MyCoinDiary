package com.example.mycoindiary

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_e.*
import kotlinx.android.synthetic.main.fragment_e.view.*
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentE.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentE : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var a :String?=null
    private lateinit var databaseRef: DatabaseReference
    lateinit var btnPlay: Button
    lateinit var btnPause: Button
    lateinit var btnStop: Button

    lateinit var tvTime: TextView
    lateinit var pbMP3: SeekBar

    lateinit var mp3List: ArrayList<String>
    lateinit var selectedMP3: String
    var mp3Path = Environment.getExternalStorageDirectory().path + "/"
    var PAUSED = false
    var num1:String?=null
    var num2:String?=null
    lateinit var mPlayer: MediaPlayer
    var ccmonth:String?=null
    var cccmonth:Int?= null
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
        val view=inflater.inflate(R.layout.fragment_e, container, false)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy")
        val formatted = current.format(formatter)
        val cyear = Integer.parseInt(formatted)
        val formatter1 = DateTimeFormatter.ofPattern("MM")
        val formatted1 = current.format(formatter1)

        val cmonth= Integer.parseInt(formatted1)
        if((cmonth/10)<1){
             ccmonth=formatted1.substring(1, 2)
            cccmonth=Integer.parseInt(ccmonth)
        }else{
            cccmonth=Integer.parseInt(formatted1)
        }
        view.resultyear.text=formatted
        view.resultmonth.text=cccmonth.toString()

        databaseRef = FirebaseDatabase.getInstance().reference
        val postListener1 = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                view.sumcoinstartd.text="0"
                view.sumcoinendd.text="0"
                view.sumcoinprofitd.text="0"
                var sumstartd=0;
                var sumendd=0;
                var sumprofitd=0;


                val collectionIterator = dataSnapshot!!.children.iterator()
                if (collectionIterator.hasNext()) {
                    val comments = collectionIterator.next()
                    a = arguments?.getString("name2")
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
                            if (((Integer.parseInt(listyear)).equals(cyear)) && ((Integer.parseInt(listmonth)).equals(cccmonth))) {
                                sumstartd += Integer.parseInt(liststart)
                                sumendd += Integer.parseInt(listend)
                                sumprofitd += Integer.parseInt(listprofit)

                            }
                        }}}
                    view.sumcoinstartd.text = sumstartd.toString()
                    view.sumcoinendd.text = sumendd.toString()
                    view.sumcoinprofitd.text = sumprofitd.toString()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        databaseRef.addValueEventListener(postListener1)


        view.searchbuttond.setOnClickListener {


            num1=view.searchyeard.text.toString()
            num2=view.searchmonthd.text.toString()
             if (num1!!.trim() == "" || num2!!.trim() == "") {
                        Toast.makeText(requireContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT).show()
                    }
             else{
                 view.resultyear.text=view.searchyeard.text.toString()
                 view.resultmonth.text=view.searchmonthd.text.toString()
                 view.sumcoinstartd.text="0"
                 view.sumcoinendd.text="0"
                 view.sumcoinprofitd.text="0"
            var number1=Integer.parseInt(view.searchyeard.text.toString());
            var number2 = Integer.parseInt(view.searchmonthd.text.toString());

            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var sumstart=0;
                    var sumend=0;
                    var sumprofit=0;
                    val collectionIterator = dataSnapshot!!.children.iterator()
                    if (collectionIterator.hasNext()) {
                        val comments = collectionIterator.next()
                        a = arguments?.getString("name2")
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
                            if(((Integer.parseInt(listyear)).equals(number1))&&((Integer.parseInt(listmonth)).equals(number2))){
                                sumstart +=Integer.parseInt(liststart)
                                sumend +=Integer.parseInt(listend)
                                sumprofit +=Integer.parseInt(listprofit)
                            }
                        }
                    }}
                    view.sumcoinstartd.text=sumstart.toString()
                    view.sumcoinendd.text=sumend.toString()
                    view.sumcoinprofitd.text=sumprofit.toString()}
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                }
            }
            databaseRef.addValueEventListener(postListener) }}

       mp3player(view)
        return view
    }

    fun mp3player ( view: View){

        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)


        mp3List = ArrayList()

        var listFiles = File(mp3Path).listFiles()
        var fileName: String
        var extName: String
        for (file in listFiles!!) {
            fileName = file.name
            extName = fileName.substring(fileName.length - 3)
            if (extName == "mp3")
                mp3List.add(fileName)
        }
        selectedMP3 = mp3List[0]

        btnPlay = view.findViewById<Button>(R.id.btnPlay)
        btnStop = view.findViewById<Button>(R.id.btnStop)
        tvTime = view.findViewById<TextView>(R.id.tvTime)
        pbMP3 = view.findViewById<SeekBar>(R.id.pbMP3)
        btnPause = view.findViewById<Button>(R.id.btnPause)

        btnPlay.setOnClickListener {
            mPlayer = MediaPlayer()
            mPlayer.setDataSource(mp3Path + selectedMP3)
            mPlayer.prepare()
            mPlayer.start()
            btnPlay.isClickable = false
            btnPause.isClickable = true
            btnStop.isClickable = true
            // tvMP3.text = "실행중인 음악 :  $selectedMP3"
            pbMP3.visibility = View.VISIBLE

            object:Thread(){
                var timeFormat = SimpleDateFormat("mm:ss")
                override fun run() {
                    if(mPlayer == null)
                        return
                    pbMP3.max = mPlayer.duration
                    while(mPlayer.isPlaying){
                        requireActivity().runOnUiThread{
                            pbMP3.progress = mPlayer.currentPosition
                            tvTime.text ="진행시간 : "+timeFormat.format(mPlayer.currentPosition)
                        }
                        SystemClock.sleep(200)
                    }
                }
            }.start()
        }
        btnPause.isClickable = false
        btnPause.setOnClickListener {
            if (PAUSED == false) {
                mPlayer.pause()
                btnPause.text = "이어듣기"
                PAUSED = true
                // pbMP3.visibility = View.INVISIBLE
            } else {
                mPlayer.start()
                PAUSED = false
                btnPause.text = "일시정지"
                // pbMP3.visibility = View.VISIBLE
                object:Thread(){
                    var timeFormat = SimpleDateFormat("mm:ss")
                    override fun run() {
                        if(mPlayer == null)
                            return
                        pbMP3.max = mPlayer.duration
                        while(mPlayer.isPlaying){
                            requireActivity().runOnUiThread{
                                pbMP3.progress = mPlayer.currentPosition
                                tvTime.text ="진행시간 : "+timeFormat.format(mPlayer.currentPosition)
                            }
                            SystemClock.sleep(200)
                        }
                    }
                }.start()
            }
        }
        btnStop.isClickable = false
        btnStop.setOnClickListener {
            mPlayer.stop()
            mPlayer.reset()
            btnPlay.isClickable = true
            btnPause.isClickable = false
            btnPause.text = "일시정지"
            btnStop.isClickable = false
            //  tvMP3.text = "실행중인 음악 :  "
            tvTime.text ="진행시간 : "
            pbMP3.visibility = View.INVISIBLE
        }


}

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentE.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentE().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}