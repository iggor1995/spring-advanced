<#import "tags/nav.ftl" as u>

<html xmlns="http://www.w3.org/1999/html">
<head><title> Add Movie</title>

<body>
<@u.page/>
<div class="container-fluid" align="center">
        <legend>Add Movie</legend>
        <form name="event" action="addEvent" method="post">
            <div class="form-group" align="left">
                <label for="name">Name</label>
                <input id="name" type="text" name="name" />
            </div>
            <div class="form-group" align="left">
                <label for="price">Base price</label>
                <input id="price" type="number" name="basePrice" />
            </div>
            <div class="form-group" align="left">
                <labe for="date">Date and time</labe>
                <input id="date" type="datetime-local" name="dateTime" value="2017-06-01T08:30"/>
            </div>
            <div class="form-group" align="left">
                <label for="rate">Rate</label>
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
            <button type="submit" class="btn btn-primary">Add movie</button>
        </form>
</div>
</body>
</html>