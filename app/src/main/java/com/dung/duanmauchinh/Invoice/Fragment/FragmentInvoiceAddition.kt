package com.dung.duanmauchinh.Invoice.Fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dung.duanmauchinh.DAO.InvoiceDAO
import com.dung.duanmauchinh.Model.Invoice
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_invoice_addition.view.*
import java.util.*


class FragmentInvoiceAddition: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.fragment_invoice_addition,container,false)
        var btnChonNgayHoaDon = rootview.btnChonNgayHoaDon
        var edtNgayMua = rootview.edtNgayMua
        var edtMaHoaDon = rootview.edtMaHoaDon
        var btnThemHoaDon = rootview.btnThemHoaDon
        var invoiceDAO = InvoiceDAO(context)

        btnThemHoaDon.setOnClickListener {
            var mahoadon: String = edtMaHoaDon.text.toString()
            var ngaymua: String = edtNgayMua.text.toString()
            if (mahoadon.isEmpty() || mahoadon.length != 5){
                showMessage("Mã hóa đơn phải nhập 5 ký tự",false,context)
            }
            else if(ngaymua.isEmpty()){
                showMessage("Vui lòng chọn ngày hóa đơn được nhập",false,context)
            }
            else if(invoiceDAO.insertInvoice(Invoice(mahoadon, ngaymua)) > 0){
                showMessage("Thêm hóa đơn thành công",true,context)
                edtMaHoaDon.setText("")
                edtNgayMua.setText("yyyy-MM-dd")
            }
            else if(invoiceDAO.insertInvoice(Invoice(mahoadon, ngaymua)) <= 0) {
                showMessage("Thêm hóa đơn thất bại do nhập trùng mã hóa đơn",false,context)
            }
        }

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
                    edtNgayMua.setText("$year-$thang-$ngay")

                }, year, month, day
            )
            datePickerDialog.show()
        }

        return rootview
    }

    fun showMessage(message: String, switch: Boolean,context: Context?){
        var view = layoutInflater.inflate(R.layout.custom_toast,null)
        var txtCustomToast: TextView = view.txtCustomToast
        var imgCustomToast: ImageView = view.imgCustomToast
        var llCustomToast: LinearLayout = view.llCustomToast
        if(switch == true){
            imgCustomToast.setImageResource(R.drawable.ic_check)
            llCustomToast.setBackgroundResource(R.drawable.bg_done_toast)
            txtCustomToast.text = message

            var toast = Toast(context)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = view
            toast.show()
        }
        if(switch == false){
            var view = layoutInflater.inflate(R.layout.custom_toast,null)
            var txtCustomToast: TextView = view.txtCustomToast
            txtCustomToast.text = message

            var toast = Toast(context)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = view
            toast.show()
        }
    }

}