<html>
<head><title> FreeMarker Spring MVC Hello World</title>

    <style>
        .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 25px;
            text-align: center;
            font-size: 16px;
            cursor: pointer;
        }

        .button:hover {
            background-color: green;
        }
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

    <!--<fieldset>
        <legend>Add Car</legend>
        <form name="car" action="add" method="post">
            Make : <input type="text" name="make" />	<br/>
            Model: <input type="text" name="model" />	<br/>
            <input type="submit" value="   Save   " />
        </form>
    </fieldset>
    <fieldset>
        <legend>Add User</legend>
        <form name="user" action="register" method="post">
            Name : <input type="text" name="name" /> <br/>
            Email :  <input type="text" name="email" /> <br/>
            Password :  <input type="password" name="password" /> <br/>
            Birthday : <input type="date" name="birthday" value="2000-01-01"/> <br/>
            <input type="submit" value="   Register   " />
        </form>
    </fieldset>-->

    <br/>
    <table class="datatable">
        <tr>
            <th>Auditorium</th>
            <th>Name</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>

        </tr>
    <#list model["events"] as event>
        <tr>
            <td>${event.auditorium.name}</td>
            <td>${event.name}</td>
            <td>${event.rate}</td>
            <td>${event.basePrice}</td>
            <td>${event.dateTime}</td>

        </tr>
    </#list>
    </table>

    <br/>
    <form name="user" action="pageAddEvent" method="get">
        <input type="submit" value="   Add Movie   " />
    </form>

</div>
</body>
</html>