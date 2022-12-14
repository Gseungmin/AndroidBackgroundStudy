package com.example.coinstudy.view.adapter

import android.content.ClipData.Item
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coinstudy.R
import com.example.coinstudy.dataModel.CurrentPriceResult
import com.example.umc.db.InterestCoinEntity

class CoinListRVAdapter(val context: Context, val dataSet : List<InterestCoinEntity>)
    : RecyclerView.Adapter<CoinListRVAdapter.ViewHolder>() {

    /**
     * 클릭 로직을 Fragment에서 처리하기 위해서는 Interface를 만들어야 함
     * */
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val coinName : TextView = view.findViewById(R.id.coinName)
        val likeImage : ImageView = view.findViewById(R.id.likeBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_coin_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.coinName.text = dataSet[position].coin_name

        val selected = dataSet[position].selected
        if (selected) {
            holder.likeImage.setImageResource(R.drawable.like_red)
        } else {
            holder.likeImage.setImageResource(R.drawable.like_grey)
        }

        /**
         * 클릭 이벤트
         * */
        holder.itemView.findViewById<ImageView>(R.id.likeBtn).setOnClickListener{
            v -> itemClick?.onClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}