package com.example.rxjavaretrofit.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.data.model.Crypto
import kotlinx.android.synthetic.main.item_crypto.view.*

class CryptoAdapter(private val context: Context)
    : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private val color: ArrayList<String> = arrayListOf("#7E57C2", "#42A5F5", "#26C6DA", "#66BB6A", "#FFEE58", "#FF7043" , "#EC407A" , "#d32f2f")
    private lateinit var listener: ClickListener
    private var cryptoList: List<Crypto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_crypto, parent, false)
        return CryptoHolder(view, listener)
    }

    override fun getItemCount(): Int = cryptoList.size

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoList[position], color)
    }

    fun setCryptoList(cryptoList: List<Crypto>) {
        this.cryptoList = cryptoList
        notifyDataSetChanged()
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    class CryptoHolder(itemView: View, private val listener: ClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(crypto: Crypto, color: ArrayList<String>) {
            itemView.text_name.text = crypto.currency
            itemView.text_price.text = crypto.price
            itemView.setBackgroundColor(Color.parseColor(color[adapterPosition % 8]))
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(adapterPosition)
        }
    }

    interface ClickListener {
        fun onClick(position: Int)
    }
}