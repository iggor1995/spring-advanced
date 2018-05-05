<html>
<head><title> Registeration</title>

<body>
<div id="header">
    <H2>
        FreeMarker Spring MVC Cinema
    </H2>
</div>

<div id="content">

    <fieldset>
        <legend>Add User</legend>
        <form name="user" action="register" method="post">
            Name : <input type="text" name="name" /> <br/>
            Email :  <input type="text" name="email" /> <br/>
            Password :  <input type="password" name="password" /> <br/>
            Birthday : <input type="date" name="birthday" value="2000-01-01"/> <br/>
            <input type="submit" value="   Register   " />
        </form>
    </fieldset>
</div>
</body>
</html>