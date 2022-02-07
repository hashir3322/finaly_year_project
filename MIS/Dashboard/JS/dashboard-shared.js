$(document).ready(function(){

	var subMenuContainer = $(".sub-menu-container");
	var subMenu = $(".sub-menu-container .sub-menu");

	var menuBtn = $(".dashboard-nav .menu-btn");

	var crossContainer = $(".side-bar .cross-container");
	var sidebar = $(".side-bar");

	var sidebarOverlay = $(".sidebar-overlay");

	subMenuContainer.click(function(e){
		subMenu.fadeToggle(250);
	})	

	menuBtn.click(function(e){
		sidebar.addClass("side-bar-active");
		sidebarOverlay.addClass("sidebar-overlay-active");
	})

	crossContainer.click(function(e){
		sidebarCloser();
	})

	sidebarOverlay.click(function(e){
		sidebarCloser();
	})

	function sidebarCloser(){
		sidebarOverlay.removeClass("sidebar-overlay-active");
		sidebar.removeClass("side-bar-active");
	}


});