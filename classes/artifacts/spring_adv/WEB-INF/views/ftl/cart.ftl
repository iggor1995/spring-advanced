<#import "tags/nav.ftl" as u>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
<body>
<@u.page/>

<#if model["userCash"]??>
    <h2><p style="color: white">Account ${model["userCash"]}</p></h2>
</#if>
<div class="container">
    <br/>
    <table class="table table-dark table-striped" >
        <tr>
            <th>Name</th>
            <th>Auditorium</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>
            <th>Seat</th>
        </tr>
        <#if Session.cart??>
            <#list Session.cart as ticket>
                <tr>
                    <td>${ticket.event.name}</td>
                    <td>${ticket.event.auditorium.name}</td>
                    <td>${ticket.event.rate}</td>
                    <td>${ticket.event.basePrice}</td>
                    <td>${ticket.event.dateTime}</td>
                    <td>${ticket.seats}</td>
                </tr>
                <form name="book" action="/spring_adv/user/deleteFromCart" method="post">
                    <button type="submit" class="btn btn-primary">Remove</button>
                </form>
            </#list>
        <#else>
        <h2>There is no tickets in the cart</h2>
        </#if>
    </table>
    <h2>Total price - ${model["totalPrice"]}</h2>
<@security.authentication property="principal.name"
    var="email" scope="page" />
    <form name="book" action="/spring_adv/user/bookCart" method="post">
        <input type="hidden" name="userEmail" value="${.globals.email}">
        <button type="submit" class="btn btn-primary">Book Cart</button>
    </form>
</div>
</body>
</html>