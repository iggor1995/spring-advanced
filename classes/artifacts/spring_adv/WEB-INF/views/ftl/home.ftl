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
<head>
</head>
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
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarExample"
                    aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
                Menu <i class="fa fa-bars"></i>
            </button>
            <div class="container">
                <li class="nav-item">
                    <a class="btn btn-primary" data-toggle="modal" href="#myModal" style="background-color: #9fcdff">Login</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-primary" data-toggle="modal" href="#myRegisterModal" style="background-color: #9fcdff">Register</a>
                </li>
                <form name="showUsers" action="getUsers" method="get" style="background-color: #9fcdff">
                    <button type="submit" class="btn btn-primary">See all users</button><br/>
                </form>
                <div class="collapse navbar-collapse" id="navbarExample">
                    <ul class="navbar-nav ml-auto">
                            <#if Session.registeredUser??>
                                <form name="logout" action="logout" method="get">
                                    <input type="submit" value="   Logout  " /><br/>
                                </form>
                            </#if>
                        <li class="nav-item">
                            <a class="nav-link" href="#about">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#contact">Contact</a>
                        </li>
                    </ul>
                    </div>
                </div>
        </nav>
<#--/NAVBAR-->

    <div style="background-color:black;color:white;padding:20px;">
        <p>If there is no data - there are files attached: /resources/json events.json and users.json</p>
        <p>Upload those files using tools in this page</p>
    </div><br/>
<#--LOGIN-->
<#if Session.registeredUser??><#else>
    <div class="container">
        <div class="row">
            <#--<a class="btn btn-primary" data-toggle="modal" href="#myModal" >Login</a>-->

            <div class="modal hide" id="myModal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h3>Login to MyWebsite.com</h3>
                </div>
                <div class="modal-body">
                    <form method="post" action='login' name="login_form">
                        <p><input type="text" class="span3" name="email" placeholder="Email"></p>
                        <p><input type="password" class="span3" name="password" placeholder="Password"></p>
                        <p><button type="submit" class="btn btn-primary">Sign in</button>
                            <a href="#">Forgot Password?</a>
                        </p>
                    </form>
                </div>
                <div class="modal-footer">
                    New To spring_adv.com?
                    <a class="btn btn-primary" data-toggle="modal" href="#myModal" style="background-color: #9fcdff">Register</a>
                </div>
            </div>
        </div>
    </div>
</#if>
<#--/LOGIN-->

<#--CAROUSEL-->
<div class="container-fluid p-0">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src= "resources/images/tombRaider.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="resources/images/MazeRunner.jpg" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="resources/images/Avengers.jpg" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<#--/CAROUSEL-->

<#--REGISTER-->
    <div class="container">
        <div class="row">
        <#--<a class="btn btn-primary" data-toggle="modal" href="#myModal" >Login</a>-->
            <div class="modal hide" id="myRegisterModal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h3>Login to MyWebsite.com</h3>
                </div>
                <div class="modal-body">
                    <form method="post" action='register' name="login_form">
                        <p><input type="text" class="span3" name="name" placeholder="Email"></p>
                        <p><input type="text" class="span3" name="email" placeholder="Email"></p>
                        <p><input type="password" class="span3" name="password" placeholder="Password"></p>
                        <p><input type="date" class="span3" name="birthday" value="2000-01-01"></p>
                        <p><button type="submit" class="btn btn-primary">Register</button>
                            <a href="#">Forgot Password?</a>
                        </p>
                    </form>
                </div>
                <div class="modal-footer">
                    Do you have Login?
                    <a class="btn btn-primary" data-toggle="modal" href="#myModal" >Login</a>
                </div>
            </div>
        </div>
    </div>
<#--/REGISTER-->

<#--TABLE-->
<#if  model["events"]?has_content>
<div class="container">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Auditorium</th>
            <th>Name</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <#list model["events"] as event>
        <tr>
            <td>${event.auditorium.name}</td>
            <td>${event.name}</td>
            <td>${event.rate}</td>
            <td>${event.basePrice}</td>
            <td>${event.dateTime}</td>
            <td>
                <#if Session.registeredUser??>
                    <form name="booking" action="bookTicketsPage" method="post">
                        <input type="hidden" name="eventId" value=${event.id}><br/>
                        <input type="submit" value="   Book ticket   " /><br/>
                    </form>
                </#if>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</#if>
</div>
<#--/TABLE-->


<div align="center">
<fieldset>
    <h1>Please upload a file for users</h1>
    <form method="post" action="usersUpload" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit"/>
    </form>
        <h1>Please upload a file for events</h1>
        <form method="post" action="eventsUpload" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit"/>
        </form>
    </div>
</fieldset>
<fieldset>
    <form name="showUsers" action="getUsers" method="get">
        <input type="submit" value="   See all Users   " /><br/>
    </form>
</fieldset>
</body>
</html>