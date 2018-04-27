<html xmlns="http://www.w3.org/1999/html">
<head><title> FreeMarker Spring MVC Hello World</title>

    <style>
        body, input {
            font-family: Calibri, Arial;
            margin: 0px;
            padding: 0px;
        }
        #header h2 {
            color: white;
            background-color: #3275A8;
            height: 50px;
            padding: 5px 0 0 5px;
            font-size: 20px;
        }

        .datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:400px;max-width:100%;font-family:Calibri}
        .datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
        .datatable tr {border:1px solid #888}
        .datatable tr.odd {background-color:#eee}
        .datatable td {padding:2px;border:1px solid #888}
        #content { padding 5px; margin: 5px; text-align: center}
        fieldset { width: 300px; padding: 5px; margin-bottom: 0px; }
        legend { font-weight: bold; }
    </style>

<body>
<div id="header">
    <H2>
        FreeMarker Spring MVC Hello World
    </H2>
</div>

<div id="content">
    <fieldset>
        <legend>Add Movie</legend>
        <form name="event" action="addEvent" method="post">
            Name : <input type="text" name="name" /> <br/>
            <!--Rate :
            <select name="rate" id="hall">
                <option selected = selected>"HIGH"</option>
                <option>"MID"</option>
                <option>"LOW"</option>
            </select> <br/>-->
            Base price :  <input type="number" step="0.01" name="basePrice" /> <br/>
            <#--Date and time : <input type="datetime-local" name="dateTime" value="2000-01-01"/> <br/>-->
            <#--Auditorium :-->
            <#--<select name="auditorium">-->
                <#--<#list model["auditoriums"] as auditorium>-->
                    <#--<option>${auditorium}</option>-->
                <#--</#list>-->
            <#--</select> <br/>-->
            <!-- Input field for Movie's name -->
            <!-- Select Tag, for giving the actors as a Dropdown -->
            <!-- From Controller we'll set a list of Actors in Model and send it for this JSP -->
            <select name="auditorium">
                <#list model["auditoriums"] as auditorium>
                    <option>${auditorium.name}</option>
                </#list>
            </select>
            <input type="submit" value="   Add movie   " />
        </form>
    </fieldset>
</div>
</body>
</html>