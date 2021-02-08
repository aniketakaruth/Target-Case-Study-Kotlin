package com.target.targetcasestudy.ui

import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.target.targetcasestudy.OnBackPressed
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ViewModel.DealItemsViewModel
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.ProductsData


class DealItemFragment : Fragment() {


    private lateinit var viewModel: DealItemsViewModel

    @BindView(R.id.price_to_show)
    lateinit var priceToShow: TextView
    @BindView(R.id.regular_price)
    lateinit var regularPrice: TextView
    @BindView(R.id.product_title)
    lateinit var productTitle: TextView
    @BindView(R.id.product_description)
    lateinit var productDesc: TextView
    @BindView(R.id.product_image)
    lateinit var productImage: ImageView
    @BindView(R.id.loading_layout)
    lateinit var loadingView :LinearLayout
    @BindView(R.id.setView)
    lateinit var setView :ScrollView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_item, container, false)
        ButterKnife.bind(this,view)
        return view;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DealItemsViewModel::class.java)
        loadingView.visibility = View.VISIBLE
        setView.visibility = View.GONE
        viewModel.getDealsItemObserver().observe(viewLifecycleOwner, Observer<ProductsData> {
            if (it != null) {
                loadingView.visibility = View.GONE
                setView.visibility = View.VISIBLE
                setProductData(it)
            } else {
                loadingView.visibility = View.GONE
                setView.visibility = View.VISIBLE
                Toast.makeText(context,"Some Error Occurred",Toast.LENGTH_SHORT).show()
            }
        })
        arguments?.getInt("DEAL_ID")?.let { viewModel.fetchDealDetails(it) }

    }


    fun setProductData(data: ProductsData) {
        if (!data.imageUrl.isEmpty()) {
            Glide.with(productImage.context)
                .load(Uri.parse(data.imageUrl))
                .into(productImage)
        }
        productTitle.text = data.title
        productDesc.text = data.description

        if (data.salePriceData != null) {
            priceToShow.text = data.salePriceData.priceString
            regularPrice.visibility = View.VISIBLE
            regularPrice.text = "Reg :"+data.regularPriceData.priceString
        } else {
            priceToShow.text = data.regularPriceData.priceString
            regularPrice.visibility = View.GONE
        }


    }

    @OnClick(R.id.back)
    fun onBackButtonPressed(){
        activity!!.supportFragmentManager.popBackStack()
    }


}



