<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "tags/nav.ftl"as u>
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
<body>
<@u.page/>
<div class="row align-items-center justify-content-center" >
    <legend>Add Movie</legend>
    <div class="span6" style="background-color: #0c5460; opacity: 0.7; border: 1px solid black" align="center">
        <form name="event" action="/spring_adv/manager/addEvent" method="post">
            <div class="form-group" align="left">
                <label for="name"><p style="color: azure">Name</p></label>
                <input id="name" type="text" name="name" />
            </div>
            <div class="form-group" align="left">
                <label for="price"><p style="color: azure">Base price</p></label>
                <input id="price" type="number" name="basePrice" />
            </div>
            <div class="form-group" align="left">
                <label for="date"><p style="color: azure">Date and time</p></label>
                <input id="date" type="datetime-local" name="dateTime" value="2017-06-01T08:30"/>
            </div>
            <div class="form-group" align="left">
                <label for="rate"><p style="color: azure">Rate</p></label>
                    <select id="rate" name="rate">
                        <option selected="selected">HIGH</option>
                        <option>MID</option>
                        <option>LOW</option>
                    </select>
            </div>
            <div class="form-group" align="left">
                <label>
                    <select name="auditorium">
                        <#list model["auditoriums"] as auditorium>
                            <option>${auditorium.name}</option>
                        </#list>
                    </select>
                </label>
            </div>
            <@security.authorize  access="hasRole('ROLE_BOOKING_MANAGER')">
                <button type="submit" class="btn btn-primary" style="background-color: #0c5460">Add movie</button>
            </@security.authorize>
        </form>
    </div>
</div>
</body>
</html>