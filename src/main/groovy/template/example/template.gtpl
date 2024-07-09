
<%
    // Define variables
    def fullName = "${name.toUpperCase()}-${className.toUpperCase()}"
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