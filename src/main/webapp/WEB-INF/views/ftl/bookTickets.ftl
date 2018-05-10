<#import "tags/nav.ftl" as u>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
<body>
<@u.page/>
<div class="container">
    <br/>
    <table class="table table-dark table-striped">
        <tr>
            <th>Name</th>
            <th>Auditorium</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>
            <th>Seat</th>
            <th>Book</th>
        </tr>
        <tr>
            <#assign event=.data_model["event"]>
            <td>${event.name}</td>
            <td>${event.auditorium.name}</td>
            <td>${event.rate}</td>
            <td>${event.basePrice}</td>
            <td>${event.dateTime}</td>
            <@security.authorize  access="hasRole('ROLE_REGISTERED_USER')">
                <@security.authentication property="principal.name" var="email" scope="page" />
                    <form name="book" action="/spring_adv/user/bookTickets" method="post">
                        <input type="hidden" name="userEmail" value="${.globals.email}">
                        <input type="hidden" name="eventId" value="${event.id}">
                        <td>
                            <select name="seat">
                                <#list .data_model["seats"] as i>
                                    <option>${i}</option>
                                </#list>
                            </select>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary">Book ticket</button>
                        </td>
                    </form>
            </@security.authorize>
            </td>
        </tr>
    </table>
</div>
</body>
</html>