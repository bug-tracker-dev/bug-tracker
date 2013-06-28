<html>
<head>
<script src="<%=request.getContextPath()%>/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/home.js"></script>
</head>
<body>
<h1>Sample Home Page</h1>
<hr>
<input type="button" value="map json" onclick="mapjson()"/> <br>
<input type="button" value="json array" onclick="jsonarray()"/> <br>
<input type="button" value="complex form" onclick="complexform()"/> <br>

<input type="button" value="xml" onclick="xml()" /> <br>

<input type="button" value="ws" onclick="ws()" /> <br>

<a href="legacy">legacy request</a>
<hr>

<div id="result" style="width: 100%; height: 200px;"></div>
</body>
</html>