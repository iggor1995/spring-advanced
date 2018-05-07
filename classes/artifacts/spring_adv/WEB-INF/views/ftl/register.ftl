<#import "tags/nav.ftl" as u>
<html>
<title> Registration</title>

<body>
<@u.page/>

<div id="content">
<#if  model["error"]?has_content>
    <h2>${model["error"]}</h2>
</#if>
    <div class="row align-items-center justify-content-center" >
        <form name="user" action="register" method="post" style="background-color: deepskyblue">
            <legend>Add User</legend>
            <div class="form-group" align="left">
                <label for="name">Name</label>
                <input id="name" type="text" name="name" />
            </div>
            <div class="form-group" align="left">
                <label for="email">Email</label>
                <input id="email" type="text" name="email" />
            </div>
            <div class="form-group" align="left">
                <label for="password">Password</label>
                <input id="password" type="password" name="password" />
            </div>
            <div class="form-group" align="left">
                <label for="date">Birth date</label>
                <input id="date" type="date" name="birthday" value="2000-01-01"/> <br/>
            <input type="submit" value="   Register   " />
        </form>
    </div>
</div>
</body>
</html>