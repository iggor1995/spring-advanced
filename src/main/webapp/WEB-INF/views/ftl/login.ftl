<#import "tags/nav.ftl" as u>
<html>
<body>
<@u.page/>

<#--LOGIN-->
<#if Session.registeredUser??>You are already Logged in<#else>
<div class="container-fluid" align="center">
    <legend>Log in</legend>
    <form name="login" action="login" method="post">
        <div class="form-group" align="left">
            <label for="email">Name</label>
            <input id="email" type="text" name="email" placeholder="Email">
        </div>
        <div class="form-group" align="left">
            <label for="password">Base price</label>
            <input id="password" type="password" name="password" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Log in</button>
    </form>
    <form name="Register" action="/registerPage" method="get">
        <button type="submit" class="btn btn-primary" style="background-color: #9fcdff">
            Register</button><br/>
    </form>
</div>
</#if>

<#--/LOGIN-->

</body>
</html>