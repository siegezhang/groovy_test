
<%
    // Define variables 注意变量作用域
    fullName = "${name.toUpperCase()}-${className.toUpperCase()}"
%>

package com.example;

public class ${className} {

  private String name = "${fullName}";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}


<%
    // Example list of items
    def items = ["Item1", "Item2", "Item3"]
%>
<ul>
<% items.each { item -> %>
    <li>${item}</li>
<% } %>
</ul>