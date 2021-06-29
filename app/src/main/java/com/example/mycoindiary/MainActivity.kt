package com.example.mycoindiary

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private lateinit var transaction: FragmentTransaction
    private val fragmentManager1 = supportFragmentManager
    private lateinit var transaction1: FragmentTransaction
    private val fragmentManager3 = supportFragmentManager
    private lateinit var transaction3: FragmentTransaction
    private val fragmentManager2 = supportFragmentManager
    private lateinit var transaction2: FragmentTransaction
    private val fragmentManager4 = supportFragmentManager
    private lateinit var transaction4: FragmentTransaction
    var email :String? =null
    var n :Int?=null
    var name :String?=null
var offset=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



         email = intent.getStringExtra("email")
       n= email!!.indexOf("@")
         name=email.toString().substring(0, n!!)
        title=name+"님의 CoinDiary"
        Toast.makeText(applicationContext,email+"님 환영합니다",Toast.LENGTH_LONG).show()

        val fragmentA = FragmentA()
        transaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("name1", name)
        fragmentA.arguments=bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, FragmentA())
        transaction.replace(R.id.container, FragmentA().apply { arguments = bundle })
        transaction.commit()


        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tab1 -> {
                    val fragmentA = FragmentA()
                    transaction3 = fragmentManager3.beginTransaction()
                    val bundle = Bundle()

                    bundle.putString("name1", name)
                    fragmentA.arguments=bundle
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(R.id.container, FragmentA())
                    transaction.replace(R.id.container, FragmentA().apply { arguments = bundle })
                    transaction.commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab2 -> {
                    //  Toast.makeText(applicationContext,"두번쨰 프레그먼트", Toast.LENGTH_SHORT).show()
                    val fragmentB = FragmentB()
                    transaction1 = fragmentManager1.beginTransaction()
                    val bundle = Bundle()

                    bundle.putString("name", name)
                    fragmentB.arguments=bundle
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(R.id.container, FragmentB())
                    transaction.replace(R.id.container, FragmentB().apply { arguments = bundle })
                    transaction.commit()

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab3 -> {
                    // Toast.makeText(applicationContext,"세번쨰 프레그먼트", Toast.LENGTH_SHORT).show()
                    val fragmentE = FragmentE()
                    transaction2 = fragmentManager2.beginTransaction()
                    val bundle = Bundle()

                    bundle.putString("name2", name)
                    fragmentE.arguments=bundle
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(R.id.container, FragmentE())
                    transaction.replace(R.id.container, FragmentE().apply { arguments = bundle })
                    transaction.commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab4 -> {
                    // Toast.makeText(applicationContext,"4번쨰 프레그먼트", Toast.LENGTH_SHORT).show()
                    val fragmentC = FragmentC()
                    transaction4 = fragmentManager4.beginTransaction()
                    val bundle = Bundle()

                    bundle.putString("name3", name)
                    fragmentC.arguments=bundle
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(R.id.container, FragmentC())
                    transaction.replace(R.id.container, FragmentC().apply { arguments = bundle })
                    transaction.commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab5 -> {
                    //Toast.makeText(applicationContext,"5번쨰 프레그먼트", Toast.LENGTH_SHORT).show()
                    with(supportFragmentManager.beginTransaction()) {
                        val fragment5 = FragmentD()
                        replace(R.id.container, fragment5)
                        commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }

            }
            return@setOnNavigationItemSelectedListener false
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mInflater = menuInflater
        mInflater.inflate(R.menu.menu1, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemlogout-> {
                title="님의 CoinDiary"
                Toast.makeText(applicationContext,"정상적으로 로그아웃되었습니다.",Toast.LENGTH_SHORT).show()
                // 로그아웃
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                        Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                    }
                    else {
                        Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                    }
                }

                return true}
            R.id.itemquit -> {
             finish()
                return true}
            R.id.itemnext -> {
                var intent = Intent(this@MainActivity, LoginActivity::class.java)
                // intent.putExtra("email",user.kakaoAccount?.email.toString())
                startActivity(intent)
                finish()
                return true}

        }
        return false
    }


}