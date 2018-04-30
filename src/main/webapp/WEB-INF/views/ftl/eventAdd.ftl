<html xmlns="http://www.w3.org/1999/html">
<head><title> Add Movie</title>
<#import "/spring.ftl" as spring/>
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
        Add Movie
    </H2>
</div>
<#if Session.registeredUser??>
<H2>
    Hello! ${Session.registeredUser.name}
</H2>
    <form name="logout" action="logout" method="get">
        <input type="submit" value="   Logout  " /><br/>
    </form>
<#else>
<div id="content">
    <fieldset>
        <legend>Add Movie</legend>
        <form name="event" action="addEvent" method="post">
            Name : <input type="text" name="name" /> <br/>
            Base price :  <input type="number" name="basePrice" /> <br/>
            Date and time : <input type="datetime-local" name="dateTime" value="2017-06-01T08:30"/> <br/>
            <label>
                <select name="rate">
                    <option selected="selected">HIGH</option>
                    <option>MID</option>
                    <option>LOW</option>
                </select>
            </label>
            <label>
                <select name="auditorium">
                    <#list model["auditoriums"] as auditorium>
                        <option>${auditorium.name}</option>
                    </#list>
                </select>
            </label>
            <input type="submit" value="   Add movie   " />
        </form>
    </fieldset>
</div>
</body>
</html>