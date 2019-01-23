package com.dung.duanmauchinh.SQLite

open class Constants {

    // Tên bảng
    var user_table = "nguoidung"
    var category_table = "theloai"
    var book_table = "sach"
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

    // Tạo bảng
    var create_table_user = "create table $user_table($user_username nvarchar, $user_password nvarchar, $user_phone nvarchar, $user_fullname nvarchar)"
    var create_table_category = "create table $category_table($category_idcategory nvarchar not null primary key, $category_categoryname nvarchar, $category_position integer, $category_describe nvarchar)"
    var create_table_book = "create table $book_table(" +
            "$book_idbook nvarchar not null primary key, $book_idcategory nvarchar not null, $book_bookname nvarchar," +
            "$book_author nvarchar, $book_price integer, $book_amount integer, $book_foreign_key)"
}