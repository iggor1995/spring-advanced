<#import "tags/nav.ftl" as u>
<html>
<head><title> error XXX </title>
<body>
<@u.page/>
<img class="d-block w-100" src= "/spring_adv/resources/images/fatalError.jpg" width="300"
     height="300" alt="Error"><br/>

<h2><p style="color: antiquewhite;"></p>${.data_model["exception"]}</h2>
</body>
</html>