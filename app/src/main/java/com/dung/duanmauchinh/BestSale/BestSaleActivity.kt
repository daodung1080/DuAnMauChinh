package com.dung.duanmauchinh.BestSale

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.dung.duanmauchinh.Adapter.BestSaleAdapter
import com.dung.duanmauchinh.BaseActivity
import com.dung.duanmauchinh.DAO.InvoiceDetailDAO
import com.dung.duanmauchinh.Model.BestSale
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.activity_best_sale.*

class BestSaleActivity : BaseActivity() {

    var spinnerList: List<String>
    init {
        spinnerList = listOf("01","02","03","04","05","06","07","08","09","10","11","12")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_sale)

        // -- Danh sách Spinner
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_item_for_all,spinnerList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spBanChay.adapter = arrayAdapter

        spBanChay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                this@BestSaleActivity.getAllBestSale(position)
            }
        }

    }

    fun getAllBestSale(position: Int){
        var list: ArrayList<BestSale> = ArrayList()
        list.clear()
        var invoiceDetailDAO = InvoiceDetailDAO(this@BestSaleActivity)
        list = invoiceDetailDAO.getBestSaleInvoice(spinnerList.get(position))
        var bestSaleAdapter = BestSaleAdapter(this@BestSaleActivity,list)
        rvBanChay.layoutManager = LinearLayoutManager(this@BestSaleActivity)
        rvBanChay.adapter = bestSaleAdapter
    }
}
