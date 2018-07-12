/*----------------------------------------------------------------------------*/
/*$(".checkbox-dropdown").click(function () {
    $(this).toggleClass("is-active");
});

$(".checkbox-dropdown ul").click(function(e) {
    e.stopPropagation();
});*/
/*---------------------------------------------------------------------------------*/
	$(document).ready(function(e) {
		$(".checkbox-dropdown").on("click", function(){
			$(this).toggleClass("is-active");
		});
		$(".checkbox-dropdown ul").on("click", function(e) {
			e.stopPropagation();
		});
	 });