package database

import groovy.sql.Sql

//{
//    def hospitalCode = '001'
//    def database = Sql.newInstance("jdbc:mysql://192.168.3.3:3306/qm_his", "root", "WZ\$l09wjzx9w2LfGF0B)1A",
//            "com.mysql.cj.jdbc.Driver")
//    database.eachRow("select * from sys_white_list where HospitalCode=${hospitalCode} ") {
//        println "WhiteName: ${it.WhiteName}"
//    }
//}
{
    def database = Sql.newInstance("jdbc:mysql://172.16.39.50:3306/mysql?timeout=10000&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true", "tszoscn", "tszoscn123",
            "com.mysql.cj.jdbc.Driver")
    database.eachRow("select * from user") {
        println "user: ${it.user}"
    }
}

//{
//    def database = Sql.newInstance("jdbc:mysql://10.71.200.30:3306/tsportcenter?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true", "tszoscn", "tszoscn123",
//            "com.mysql.cj.jdbc.Driver")
//    database.eachRow("select * from t_sys_ini_config") {
//        println "systemToken: ${it.system_token}"
//    }
//}