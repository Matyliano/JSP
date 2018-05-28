<html>

<body>
<h3>Training Portal</h3>

<!-- read the favorite programming language cookie -->
<%
	// the default ... if there are no cookies
	String favLang = "Java";

	// get the cookies from the browser request
	Cookie[] theCookies = request.getCookies();
	
	// find our favorite language cookie
	if (theCookies != null) {
		
		for (Cookie tempCookie : theCookies) {
			
			if ("myApp.favoriteLanguage".equals(tempCookie.getName())) {
				favLang = tempCookie.getValue();
				break;
			}
		}
	}
%>

<!-- now show a personalized page ... use the "favLang" variable -->

<!-- show new books for this lang -->
<h4>New Books for <%= favLang %></h4>
<ul>
	<li>Lorem ipsum dolor sit amet, consectetur adipiscing elit</li>
	<li> Integer volutpat tortor eget fringilla dignissim</li>
</ul>

<h4>Latest News Reports for <%= favLang %></h4>
<ul>
	<li>Donec at convallis orci</li>
	<li>Phasellus ac sagittis erat.</li>
</ul>

<h4>Hot Jobs for <%= favLang %></h4>
<ul>
	<li>Maecenas elementum feugiat feugiat</li>
	<li>Donec vitae quam tincidunt, bibendum ipsum vitae, porttitor erat.</li>
</ul>

<hr>
<a href="cookies-personalize-form.html">Personalize this page</a>
</body>

</html>











