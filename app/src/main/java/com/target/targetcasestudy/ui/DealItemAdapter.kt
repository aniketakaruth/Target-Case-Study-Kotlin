package com.target.targetcasestudy.ui

import android.graphics.Paint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.ProductsData

class DealItemAdapter(private val dealClickListener: OnDealClickedListener) :
    RecyclerView.Adapter<DealItemAdapter.DealItemViewHolder>() {

    var dealsList = ArrayList<ProductsData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return dealsList.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        viewHolder.bind(dealsList[position], dealClickListener)
    }


    class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
        val thumbImageView = itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
        val itemRegPriceView = itemView.findViewById<TextView>(R.id.deal_list_item_price)
        val shipStatusView = itemView.findViewById<TextView>(R.id.ship_status)
        val asileView = itemView.findViewById<LinearLayout>(R.id.asile_view)
        val asileNumber = itemView.findViewById<TextView>(R.id.aisle_number)
        val view = itemView.findViewById<ConstraintLayout>(R.id.view);

        fun bind(data: ProductsData, onDealClickedListener: OnDealClickedListener) {
            tvTitle.text = data.title
            val url = data.imageUrl

            itemRegPriceView.text = data.regularPriceData.priceString
            if (data.aisle == "ship") {
                shipStatusView.visibility = View.VISIBLE
                asileView.visibility = View.GONE
            } else {
                shipStatusView.visibility = View.GONE
                asileView.visibility = View.VISIBLE
                asileNumber.text = data.aisle.toUpperCase()
            }
            Glide.with(thumbImageView.context)
                .load(Uri.parse(url))
                .into(thumbImageView)

            view.setOnClickListener {
                onDealClickedListener.onDealClicked(data.id)
            }


        }
    }

    interface OnDealClickedListener {
        fun onDealClicked(id: Int)
    }
}
