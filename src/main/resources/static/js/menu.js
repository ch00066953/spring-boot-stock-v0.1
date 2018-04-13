$(function () {
	menuActive();
});

//Menu active
function menuActive() {
	var pathName=window.document.location.pathname; 
	//alert(pathName)
	$("a[href='"+pathName+"']").parents("li").addClass('active');
}


