package com.dung.duanmauchinh.Invoice.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dung.duanmauchinh.DAO.BookDAO
import com.dung.duanmauchinh.DAO.InvoiceDAO
import com.dung.duanmauchinh.DAO.InvoiceDetailDAO
import com.dung.duanmauchinh.Model.Book
import com.dung.duanmauchinh.Model.InvoiceDetail
import com.dung.duanmauchinh.R
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_invoice_detail.view.*

class FragmentInvoiceDetail: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.fragment_invoice_detail,container,false)

        var spMaHoaDon = rootview.spMaHoaDon
        var spMaSach = rootview.spMaSach
        var edtSoLuongMuaCT = rootview.edtSoLuongMuaCT
        var btnThemHDCT = rootview.btnThemHDCT

        var invoiceDAO = InvoiceDAO(context)
        var bookDAO = BookDAO(context)
        var invoiceDetailDAO = InvoiceDetailDAO(context)

        var listHoaDon: ArrayList<String> = ArrayList()
        listHoaDon = invoiceDAO.getIDInvoice()
        var listSach: ArrayList<String> = ArrayList()
        listSach = bookDAO.getIDBook()

        var HoaDonAdapter: ArrayAdapter<String> = ArrayAdapter(context,R.layout.spinner_item_for_all,listHoaDon)
        HoaDonAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        var SachAdapter: ArrayAdapter<String> = ArrayAdapter(context,R.layout.spinner_item_for_all,listSach)
        SachAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)

        spMaHoaDon.adapter = HoaDonAdapter
        spMaSach.adapter = SachAdapter

        btnThemHDCT.setOnClickListener {
            try {
                var mahoadon = listHoaDon.get(spMaHoaDon.selectedItemPosition)
                var masach = listSach.get(spMaSach.selectedItemPosition)
                var soluongconlai = bookDAO.getBookAmount(masach)
                var soluongmua = (edtSoLuongMuaCT.text.toString()).toInt()
                if (soluongmua < 0){
                    showMessage("Vui lòng nhập số lượng mua > 0",false,context)
                }
                else if(soluongconlai == 0){
                    showMessage("Số lượng sách trong kho đã hết vui lòng nhập thêm",false,context)
                }
                else if(soluongmua > soluongconlai){
                    showMessage("Vui lòng nhập số lượng mua ít hơn số lượng sách còn lại",false,context)
                }
                else if(mahoadon.length == 0){
                    showMessage("Vui lòng chọn mã hóa đơn",false,context)
                }
                else if(masach.length == 0){
                    showMessage("Vui lòng chọn mã sách",false,context)
                }
                else if(invoiceDetailDAO.insertInvoiceDetail(InvoiceDetail(null,mahoadon,masach, soluongmua)) > 0){
                    showMessage("Thêm chi tiết hóa đơn thành công",true,context)
                    edtSoLuongMuaCT.setText("")
                    var tongsoluongsach = soluongconlai - soluongmua
                    bookDAO.editBookAmount(masach, Book(null,null,null,null,null,tongsoluongsach))

                    var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertDialog.setTitle("Tổng số lượng sách")
                    alertDialog.setIcon(R.drawable.img_sach)
                    if(tongsoluongsach > 0) {
                        alertDialog.setMessage("Số lượng sách mã $masach hiện tại còn $tongsoluongsach quyển")
                    }
                    else if(tongsoluongsach == 0){
                        alertDialog.setMessage("Số lượng sách mã $masach trong kho đã hết")
                    }
                    alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                    var dialog = alertDialog.create()
                    dialog.show()
                }
                else if(invoiceDetailDAO.insertInvoiceDetail(InvoiceDetail(null,mahoadon,masach, soluongmua)) <= 0){
                    showMessage("Thêm chi tiết hóa đơn thất bại",true,context)
                }
            }
            catch (e: NumberFormatException){
                showMessage("Vui lòng nhập số lượng mua là số",false,context)
            }
            catch (e2: ArrayIndexOutOfBoundsException){
                showMessage("Vui lòng nhập thể loại trước",false,context)
            }
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