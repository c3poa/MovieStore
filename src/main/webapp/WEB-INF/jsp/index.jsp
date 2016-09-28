<%@include file="top.jsp" %>
<c:forEach items="${movies}" var="movie" varStatus="counter">
    <div class="${counter.count%2==0?"rightbox":"leftbox"}">
          <h3>${movie.title}</h3>
          <img src="resources/images/${movie.photo}" width="93" height="95" alt="photo 1" class="left" />
          <p><b>Price:</b> <b>$${movie.price}</b> &amp; eligible for FREE Super Saver Shipping on orders over <b>$${movie.supersaver}</b>.</p>
          <p><b>Availability:</b> Usually ships within 24 hours</p>
          <p class="readmore"><a href="tobasket/${movie.id}">BUY <b>NOW</b></a></p>
          <div class="clear"></div>
        </div>
</c:forEach>
        <!-- end .rightbox -->
        <div class="clear"></div>
        <%@include file="bottom.jsp" %>