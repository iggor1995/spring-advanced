<#macro page>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"
        integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4"
        crossorigin="anonymous"></script>
<style>
    body {
        padding-top: 100px;
    }
    #mainNav {
        opacity: 0.8;
        background: steelblue;
    }
</style>
<body>
<#--NAVBAR-->
<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse" id="mainNav">
    <div class="container align-items-center">
        <li class="nav-item" >
            <form name="showUsers" action="/spring_adv/home" method="get" >
                <button type="submit" class="btn btn-primary" style="background-color: #9fcdff">
                    Home</button>
            </form>
        </li>
        <@security.authorize access="!isAuthenticated()">
            <li class="nav-item" >
                <a class="btn btn-primary" data-toggle="modal" href="#myModal" style="background-color: #9fcdff">Login</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-primary" data-toggle="modal" href="#myRegisterModal" style="background-color: #9fcdff">Register</a>
            </li>
        </@security.authorize>
        <li class="nav-item">
            <form name="showUsers" action="/spring_adv/getUsers" method="get">
                <button type="submit" class="btn btn-primary" style="background-color: #9fcdff">
                    See all users</button><br/>
            </form>
        </li>
        <@security.authorize  access="hasRole('ROLE_BOOKING_MANAGER')">
            <li class="nav-item">
                <form name="showUsers" action="/spring_adv/manager/pageAddEvent" method="get" >
                    <button type="submit" class="btn btn-primary" style="background-color: #9fcdff">
                        Add event</button>
                </form>
             </li>
        </@security.authorize>
        <@security.authorize  access="hasRole('ROLE_REGISTERED_USER')">
            <li class="nav-item">
                <form name="showCart" action="/spring_adv/user/getCart" method="get" >
                    <button type="submit" class="btn btn-primary" style="background-color: #9fcdff">
                       Show cart</button>
                </form>
            </li>
            <li class="nav-item">
                <@security.authentication property="principal.name" var="email" scope="page" />
                <form name="getTickets" action="/spring_adv/user/getTickets" method="post" >
                    <input type="hidden" name="userEmail" value="${.globals.email}">
                    <button type="submit" class="btn btn-primary" style="background-color: #9fcdff">
                        Show booked tickets</button>
                </form>
            </li>
        </@security.authorize>
        <@security.authorize  access="isAuthenticated()">
            <li class="nav-item">
                <form name="logout" action="/spring_adv/logout" method="get">
                    <button type="submit" class="btn btn-primary" style="background-color: palevioletred">
                        Logout</button>
                </form>
            </li>
        </@security.authorize>
    </div>
</nav>
<#--/NAVBAR-->
<#--LOGIN-->
<div class="container" align="center">
    <div class="row">
        <div class="modal hide" id="myModal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <h3>Login to MyWebsite.com</h3>
            </div>
            <div class="modal-body">
                <form method="post" action='login' name="login_form">
                    <p><input type="text" class="span3" name="email" placeholder="Email"></p>
                    <p><input type="password" class="span3" name="password" placeholder="Password"></p>
                    <p><button type="submit" class="btn btn-primary">Sign in</button>                  </p>
                </form>
            </div>
            <div class="modal-footer">
                New To spring_adv.com?
                <a class="btn btn-primary" data-toggle="modal" href="#myModal" style="background-color: #9fcdff">Register</a>
            </div>
        </div>
    </div>
</div>
<#--/LOGIN-->

<#--REGISTER-->
<div class="container" align="center">
    <div class="row">
    <#--<a class="btn btn-primary" data-toggle="modal" href="#myModal" >Login</a>-->
        <div class="modal hide" id="myRegisterModal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <h3>Login to MyWebsite.com</h3>
            </div>
            <div class="modal-body">
                <form method="post" action='register' name="login_form">
                    <p><input type="text" class="span3" name="name" placeholder="Name"></p>
                    <p><input type="text" class="span3" name="email" placeholder="Email"></p>
                    <p><input type="password" class="span3" name="password" placeholder="Password"></p>
                    <p><input type="date" class="span3" name="birthday" value="2000-01-01"></p>
                    <p><button type="submit" class="btn btn-primary">Register</button></p>
                </form>
            </div>
        </div>
    </div>
</div>
<#--/REGISTER-->

</body>
</html>
</#macro>