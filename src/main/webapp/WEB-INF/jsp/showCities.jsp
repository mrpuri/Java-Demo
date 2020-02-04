<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <title>Cities</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <h2>List of cities</h2>

        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Population</th>
            </tr>
        
<c:forEach begin="0" end="${fn:length(cities) - 1}" var="index">
   <tr>
      <td><c:out value="${cities[index].id}"/></td>
      <td><c:out value="${cities[index].name}"/></td>
      <td><c:out value="${cities[index].population}"/></td>
   </tr>
</c:forEach>
</table>
    </body>
</html>