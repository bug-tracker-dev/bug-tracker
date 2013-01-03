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
	  dataType: 'json',
	  data: {username: 'guest', password: '123456', gender: 'M'},
	  success: function(data) {
	  	alert(JSON.stringify(data));
	  }
	});
}

function xml() {
	$.ajax({
	  url: 'xml',
	  dataType: 'xml',
	  success: function(data) {
	  	alert(data);
	  }
	});
}