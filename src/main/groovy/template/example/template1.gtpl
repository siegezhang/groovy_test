<%
    // Define a variable in the template 注意变量作用域
    def fullName = "${person.firstName.toUpperCase()} ${person.lastName.toUpperCase()}"
%>
Hello, ${fullName}!
