$(document).ready(function(){

	var menuBtn = $(".main-nav .menu-btn");
	var navList = $(".main-nav .nav-list");

	var subMenuContainer = $(".sub-menu-container");
	var subMenu = $(".sub-menu-container .sub-menu");


	menuBtn.click(function(e){
		navList.toggleClass("show");
	})

	subMenuContainer.click(function(e){

		subMenu.fadeToggle(250);
	})	
	
})