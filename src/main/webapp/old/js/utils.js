var utils = {
	getKey : function(name) {
		return decodeURIComponent((new RegExp('[?|&]' + name + '='
				+ '([^&;]+?)(&|#|;|$)').exec(location.href) || [ , "" ])[1]
				.replace(/\+/g, '%20'))
				|| null
	},
	switchToByTopHref : function(url) {
		top.location.href = "./" + url + ".html"
	},
	loadNav : function() {
		$.ajax({
			url :"nav.html",
			type : "GET",
			dataType : "html",
			scriSptCharset: 'utf-8',
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success : function(res) {
				var Obj = $("#nav").html(res);// 包装数据
			}
		})
	}
}