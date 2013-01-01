function mapjson() {
	$.ajax({
	  url: 'json',
	  success: function(data) {
	  	alert(JSON.stringify(data));
	  }
	});
}

function jsonarray() {
	$.ajax({
	  url: 'jsonarray',
	  success: function(data) {
	  	alert(JSON.stringify(data));
	  }
	});
}

function complexform() {
	$.ajax({
	  url: 'complex',
	  type: "POST",
	  data: {username: 'guest', password: '123456', sex: 'M'},
	  success: function(data) {
	  	alert(JSON.stringify(data));
	  }
	});
}