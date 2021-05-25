var stcm = {
	endWith : function(a, b) {
		var c = a.length - b.length;
		var result = ((c >= 0) && (a.lastIndexOf(b) == c))
		return result;
	},
	startWith : function(a, b) {
		var c = a.length - b.length;
		var result = ((c >= 0) && (a.firstIndexOf(b) == c))
	}
}