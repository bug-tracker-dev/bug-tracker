function mapjson() {
	$.ajax({
	  url: 'json',
	  success: function(data) {
	  }
	});
}

function jsonarray() {
	$.ajax({
	  url: 'jsonarray',
	  success: function(data) {
	  }
	});
}

function complexform() {
	$.ajax({
	  url: 'complex',
	  type: "POST",
	  data: {username: 'guest', password: '123456', sex: 'M'},
	  success: function(data) {
	  }
	});
}