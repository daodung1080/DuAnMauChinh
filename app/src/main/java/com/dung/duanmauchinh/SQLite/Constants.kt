package com.dung.duanmauchinh.SQLite

open class Constants {

    // Tên bảng
    var user_table = "nguoidung"
    var category_table = "theloai"
    var book_table = "sach"
    var invoice_table = "hoadon"
    var invoice_detail_table = "hoadonchitiet"
    // Tên các cột
    //user
    var user_username = "username"
    var user_password = "password"
    var user_phone = "phone"
    var user_fullname = "fullname"
    //the loai
    var category_idcategory = "matheloai"
    var category_categoryname = "tentheloai"
    var category_position = "vitri"
    var category_describe = "mota"
    //Sach
    var book_idbook = "masach"
    var book_idcategory = "matheloai"
    var book_bookname = "tensach"
    var book_author = "tacgia"
    var book_price = "giabia"
    var book_amount = "soluong"
    var book_foreign_key = "foreign key($book_idcategory) references $category_table($category_idcategory)"
    //Hóa đơn
    var invoice_id = "mahoadon"
    var invoice_date = "ngaymua"

    //Hóa đơn chi tiết
    var invoice_detail_id = "mahoadonct"
    var invoice_detail_invoiceid = "mahoadon"
    var invoice_detail_idbook = "masach"
    var invoice_detail_buyamount = "soluongmua"
    var invoice_detail_foreign_key1 = "foreign key($invoice_detail_invoiceid) references $invoice_table($invoice_id)"
    var invoice_detail_foreign_key2 = "foreign key($invoice_detail_idbook) references $book_table($book_idbook)"

    // Tạo bảng
    var create_table_user = "create table $user_table($user_username nvarchar not null primary key, $user_password nvarchar not null, $user_phone nvarchar not null, $user_fullname nvarchar not null)"

    var create_table_category = "create table $category_table($category_idcategory nvarchar not null primary key, $category_categoryname nvarchar not null, $category_position integer, $category_describe nvarchar)"

    var create_table_book = "create table $book_table(" +
            "$book_idbook nvarchar not null primary key, $book_idcategory nvarchar not null, $book_bookname nvarchar not null," +
            "$book_author nvarchar not null, $book_price integer, $book_amount integer, $book_foreign_key)"

    var create_table_invoice = "create table $invoice_table(" +
            "$invoice_id nvarchar not null primary key, " +
            "$invoice_date date)"

    var create_table_invoice_detail = "create table $invoice_detail_table(" +
            "$invoice_detail_id integer primary key autoincrement, " +
            "$invoice_detail_invoiceid nvarchar not null," +
            "$invoice_detail_idbook nvarchar not null," +
            "$invoice_detail_buyamount integer, $invoice_detail_foreign_key1, $invoice_detail_foreign_key2)"
}