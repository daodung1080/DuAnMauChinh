package com.dung.duanmauchinh.HoaDon.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.fragment_them_hoa_don.view.*
import java.util.*


class FragmentThemHoaDon: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.fragment_them_hoa_don,container,false)
        var btnChonNgayHoaDon = rootview.btnChonNgayHoaDon
        var edtNgayMua = rootview.edtNgayMua

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        btnChonNgayHoaDon.setOnClickListener {
            val datePickerDialog = DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    var thangint = monthOfYear + 1
                    var thang = "$thangint"
                    if(thangint < 10){
                        thang = "0$thangint"
                    }
                    if(thangint == 13){
                        thangint = 12
                    }
                    var ngay = "$dayOfMonth"
                    if(dayOfMonth < 10){
                        ngay = "0$dayOfMonth"
                    }
                    edtNgayMua.setText("$year/$thang/$ngay")

                }, year, month, day
            )
            datePickerDialog.show()
        }

        return rootview
    }

}