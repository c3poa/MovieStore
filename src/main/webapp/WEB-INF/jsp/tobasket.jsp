<%@include file="top.jsp" %>
    <div class="leftbox">
          <h3>${movie.title}</h3>
          <img src="resources/images/${movie.photo}" width="93" height="95" alt="photo 1" class="left" />
          <p><b>Price:</b> <b>$${movie.price}</b> &amp; eligible for FREE Super Saver Shipping on orders over <b>$${movie.supersaver}</b>.</p>
          <p><b>Availability:</b> Usually ships within 24 hours</p>
          <div class="clear"></div>
          <form method="post" action="addtobasket">
              <input type="hidden" name="id" value="${movie.id}" />
              <input type="number" min="1" name="quantity" value="1" />
              <input type="submit" value="Add to basket" />
          </form>
        </div>
        <!-- end .rightbox -->
        <div class="clear"></div>
        <%@include file="bottom.jsp" %>