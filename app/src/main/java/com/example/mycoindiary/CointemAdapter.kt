package com.example.mycoindiary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.coinitem.view.*
import kotlinx.android.synthetic.main.dialog1.view.*


class CointemAdapter(val context: Context, val name: String) : RecyclerView.Adapter<CointemAdapter.ViewHolder> () {
    var items = ArrayList<coinitem>()
    private lateinit var databaseRef: DatabaseReference
    // private lateinit var mItemListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CointemAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.coinitem, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
        lateinit var databaseRef: DatabaseReference

        holder.itemView.setOnClickListener {

            var dlg = AlertDialog.Builder(context)
            var dialogView = View.inflate(context, R.layout.dialog1, null)
            dlg.setView(dialogView)
            dialogView.coinstart3.setText(item.liststart)
            dialogView.coinend3.setText(item.listend)
            dialogView.coinprofit3.setText(item.listprofit)
            dialogView.coinmemo3.setText(item.listmemo)
            dialogView.listyeard.setText(item.listyear)
            dialogView.listmonthd.setText(item.listmonth)
            dialogView.listdayd.setText(item.listday)


            dlg.setPositiveButton("확인") { dialog, which -> }
            dlg.setNegativeButton("삭제") { dialog, which ->
                var dlg2 = AlertDialog.Builder(context)
                var dialogView2 = View.inflate(context, R.layout.dialog2, null)
                dlg2.setView(dialogView2)
                dlg2.setPositiveButton("확인") { dialog, which ->
                    // items.removeAt(position)
                    // notifyDataSetChanged()}

                  databaseRef = FirebaseDatabase.getInstance().reference
                    val ci =item.objectId
                    databaseRef.child("comments").child(name).child(ci).removeValue()
                    items.removeAt(position)
                     notifyDataSetChanged()
                }
                dlg2.setNegativeButton("취소") { dialog, which ->

                }
                dlg2.show()

            }
            dlg.show()
        }

    }

    override fun getItemCount() = items.count()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setItem(item: coinitem) {
            itemView.listyear.text = item.listyear
            itemView.listmonth.text = item.listmonth
            itemView.listday.text = item.listday
            itemView.desposit.text = item.liststart
            itemView.withdraw.text = item.listend
            itemView.profit9.text = item.listprofit
            itemView.memo.text = item.listmemo

        }

    }


}