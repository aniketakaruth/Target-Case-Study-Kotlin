package com.target.targetcasestudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

import com.target.targetcasestudy.R
import com.target.targetcasestudy.ViewModel.DealItemsViewModel
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.ui.payment.PaymentDialogFragment


class DealListFragment : Fragment(), DealItemAdapter.OnDealClickedListener,SwipeRefreshLayout.OnRefreshListener {


    private lateinit var viewModel: DealItemsViewModel

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.loading_layout) lateinit var loaderView :LinearLayout


    @BindView(R.id.swipe_refresh) lateinit var swipeRefresh :SwipeRefreshLayout


    private lateinit var dealsItemAdapter: DealItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        ButterKnife.bind(this,view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dealsItemAdapter = DealItemAdapter(this)
        recyclerView.adapter = dealsItemAdapter
        swipeRefresh.setOnRefreshListener(this)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DealItemsViewModel::class.java)
        fetchDealsList()

    }

    private fun fetchDealsList() {
        viewModel.getDealListObserver().observe(viewLifecycleOwner, Observer<DealItem> {
            if (it != null) {
                loaderView!!.visibility = View.GONE
                dealsItemAdapter.dealsList = it.productsList!!
                dealsItemAdapter.notifyDataSetChanged()

            } else {
                loaderView!!.visibility = View.GONE
                Toast.makeText(context, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.fetchDealsList()

    }

    @SuppressLint("ShowToast")
    override fun onDealClicked(id: Int) {
        val bundle = Bundle()
        bundle.putInt("DEAL_ID", id)
        val dealItemFragment = DealItemFragment()
        dealItemFragment.arguments = bundle
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.container, dealItemFragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }

    override fun onRefresh() {
        loaderView.visibility = View.GONE
        viewModel.getDealListObserver().observe(viewLifecycleOwner, Observer<DealItem> {
            if (it != null) {
                loaderView.visibility = View.GONE
                dealsItemAdapter.dealsList = it.productsList!!
                dealsItemAdapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing=false

            } else {
                loaderView!!.visibility = View.GONE
                swipeRefresh.isRefreshing=false
                Toast.makeText(context, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.fetchDealsList()
    }


    @OnClick(R.id.open_card)
    fun openCardDialog() {
        activity?.supportFragmentManager?.let { PaymentDialogFragment().show(it, "CreditCardValidation") }
    }
}
