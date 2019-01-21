package com.dung.duanmauchinh.SQLite

open class DAO {

    var DEBUG = true
    // khai báo bảng Người dùng
    var USER_TABLE = "nguoiDung"
    // khai báo tên cột người dùng
    var USER_USER_NAME = "userName"
    var USER_PASSWORD = "Password"
    var USER_PHONE = "Phone"
    var USER_FULL_NAME = "hoTen"

    // Câu lệnh trong bảng người dùng
    // create table nguoiDung (userName nvarchar(50), password nvarchar(50), phone nvarchar(10), hoten nvarchar(50)

    val CREATE_TABLE_USER = "create table $USER_TABLE ($USER_USER_NAME nvarchar(50), " +
            "$USER_PASSWORD nvarchar(50), $USER_PHONE nvarchar(10), $USER_FULL_NAME nvarchar(50) )"
}