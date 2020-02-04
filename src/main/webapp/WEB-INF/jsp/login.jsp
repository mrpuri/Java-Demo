<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
      <body>
        <h2>List of cities</h2>
        <table>
            <tr>
              <th>UserName</th>
               <th>Password</th>
               <th>UserType</th>
             </tr> 
             <tr> ${user} </tr>
  
</table>

<form action="index.jsp" method="post">  
    <h1>Hello! I'm duke! What's you name?</h1>
    Username:<input type="text" name="user"><br><br>
    Password:<input type="password" name="password"/><br/><br/> 
    <input type="submit" value="login"/> 
    <input type="reset">
    
  </form>

    </body>
</html>
