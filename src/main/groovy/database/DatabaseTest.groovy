package database

import groovy.sql.Sql


def hospitalCode = '001'
def database = Sql.newInstance("jdbc:mysql://192.168.3.3:3306/qm_his", "root", "WZ\$l09wjzx9w2LfGF0B)1A",
        "com.mysql.cj.jdbc.Driver")
database.eachRow("select * from sys_white_list where HospitalCode=${hospitalCode} ") {
    println "WhiteName: ${it.WhiteName}"
}