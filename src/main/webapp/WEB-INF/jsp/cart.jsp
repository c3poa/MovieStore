<%@include file="top.jsp" %>
<a href="confirm">CONFIRM ORDER</a><br />
<c:forEach items="${movies}" var="movie">
    <div class="leftbox">
          <h3>${movie.title}</h3>
          <img src="resources/images/${movie.photo}" width="93" height="95" alt="photo 1" class="left" />
          <p><b>Price:</b> <b>$${movie.price}</b> &amp; eligible for FREE Super Saver Shipping on orders over <b>$${movie.supersaver}</b>.</p>
          <p><b>Availability:</b> Usually ships within 24 hours</p>
          <p><b>Quantity:</b> ${movie.quantity}</p>
          <div class="clear"></div>
          <form method="post" action="remove">
              <input type="hidden" name="id" value="${movie.id}" />
              <input type="submit" value="Remove from basket" />
          </form>
        </div>
        <!-- end .rightbox -->
        <div class="clear"></div>
</c:forEach>
<%@include file="bottom.jsp" %>