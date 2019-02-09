package com.dung.duanmauchinh.Invoice.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dung.duanmauchinh.Adapter.BillAdapter
import com.dung.duanmauchinh.Adapter.InvoiceAdapter
import com.dung.duanmauchinh.DAO.InvoiceDAO
import com.dung.duanmauchinh.DAO.InvoiceDetailDAO
import com.dung.duanmauchinh.Model.BillDetail
import com.dung.duanmauchinh.Model.Invoice
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.dialog_invoice_detail.view.*
import kotlinx.android.synthetic.main.fragment_invoice_list.view.*
import java.util.*

class FragmentInvoiceList : Fragment() {

    var list: ArrayList<Invoice>? = null
    var adapter: InvoiceAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.fragment_invoice_list,container,false)

        // -- Hóa đơn
        var lvHoaDon = rootview.lvHoaDon

        var invoiceDAO = InvoiceDAO(context)
        list = ArrayList()
        list = invoiceDAO.getAllInvoice()
        adapter = InvoiceAdapter(context,this,list!!)

        lvHoaDon.adapter = adapter

        // -- Hóa đơn chi tiết
        lvHoaDon.setOnItemClickListener { parent, view, position, id ->
            var invoiceDetailDAO = InvoiceDetailDAO(context)
            var invoiDetailList: ArrayList<BillDetail> = ArrayList()

            var invoice = list!!.get(position)

            invoiDetailList = invoiceDetailDAO.getallInvoiceDetail(invoice.mahoadon!!)
            var invoiceDetailAdapter = BillAdapter(context,invoiDetailList)

            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle("Chi tiết hóa đơn")
            alertDialog.setIcon(R.drawable.img_banchay)
            var view: View = layoutInflater.inflate(R.layout.dialog_invoice_detail,null)

            var rvInvoiceDetail = view.rvInvoiceDetail
            rvInvoiceDetail.layoutManager = LinearLayoutManager(context)
            rvInvoiceDetail.adapter = invoiceDetailAdapter

            alertDialog.setView(view)
            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            var dialog = alertDialog.create()
            dialog.show()
        }

        return rootview
    }

    fun editInvoice(invoiceID: String,position: Int){
        var invoiceDAO = InvoiceDAO(context)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

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
                var date = "$year-$thang-$ngay"

                var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                alertDialog.setTitle("Chỉnh sửa hóa đơn")
                alertDialog.setIcon(R.drawable.img_hoadon)
                alertDialog.setMessage("Bạn có muốn chuyển ngày xuất hóa đơn thành ngày $date?")

                alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                    if(invoiceDAO.editInvoice(invoiceID,Invoice(null,date)) > 0){
                        showMessage("Chỉnh sửa hóa đơn mã $invoiceID thành công",true,context)
                        list!!.set(position, Invoice(invoiceID,date))
                        adapter!!.notifyDataSetChanged()
                    }
                    else if(invoiceDAO.editInvoice(invoiceID,Invoice(null,date)) <= 0){
                        showMessage("Chỉnh sửa hóa đơn thất bại",false,context)
                    }
                })

                alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                var dialog = alertDialog.create()
                dialog.show()

                }, year, month, day
            )
            datePickerDialog.show()
    }

    fun removeInvoice(invoiceID: String, position: Int){
        var invoiceDAO = InvoiceDAO(context)
        var invoiceDetailDAO = InvoiceDetailDAO(context)

        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialog.setTitle("Xóa thông tin hóa đơn")
        alertDialog.setIcon(R.drawable.img_hoadon)
        alertDialog.setMessage("Bạn có chắc muốn xóa hóa đơn $invoiceID? \n\n" +
                "Xóa hóa đơn sẽ dẫn đến việc xóa hết hóa đơn chi tiết liên quan")

        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            if(invoiceDAO.removeInvoice(invoiceID) > 0){
                invoiceDetailDAO.removeInvoiceDetail(invoiceID)
                list!!.removeAt(position)
                showMessage("Xóa hóa đơn mã $invoiceID thành công",true,context)
                adapter?.notifyDataSetChanged()
            }
            else if(invoiceDAO.removeInvoice(invoiceID) <= 0){
                showMessage("Xóa hóa đơn thất bại",false,context)
            }
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        var dialog = alertDialog.create()
        dialog.show()

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
