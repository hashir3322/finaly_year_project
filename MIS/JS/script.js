$(document).ready(function(){

	var searchForm = $(".search-form");

	var searchBox = $(".search-container .search");
	var searchIcon = $(".search-container .search-icon");

	var resultList = $(".result-modal .result-list");
	var errorMessage = $(".result-modal .error-message")

	var regex = /[`~!@#\$\%\^\&\*\(\)\_\=\[\]\{\}\\;\'\"\:\,\.\/\<\>\?]/ig;

	var med = {
		medName: ""
	}


	searchBox.val("");


	searchBox.focus(function(){
		searchIcon.addClass("search-icon-animated");
	})

	searchBox.focusout(function(){
		if(searchBox.val() === ""){
			searchIcon.removeClass("search-icon-animated");
		}
	})

	searchForm.submit(function(event){
		var notValid = validatorFunction(searchBox.val())
		if(notValid){
			event.preventDefault();
		}

	});

	searchBox.keyup(function(){
		var value = $(this).val();
		var notValid = validatorFunction(value);
		if(value===""){
			errorMessage.hide();
			removeFromDom();
		}else if(notValid){
			errorMessage.text("Must be a valid name");
			errorMessage.show();
			removeFromDom();
		}
		else{
			errorMessage.hide();
			med.medName = value;
			getData(med);
		}
	})

	function validatorFunction(value){
		return regex.test(value);
	}

	function getData(med){
		$.ajax({
			type: "POST",
			url: "API/fetch-live.php",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(med),
			success: function(data){
				if(data.status===false){
					errorMessage.text("No record starting with these letters");
					errorMessage.show();
					removeFromDom();
				}else{
					removeFromDom();
					for(var i=0;i<data.length;i++){
						addToDom(data[i].med_name);
					}
				}
			},
			error: function(error){
				console.log(error);
			}
		})
	}
	function addToDom(item){
		var li = '<li class="list-item">'+item+'</li>';
		resultList.append(li);
	}
	function removeFromDom(){
		resultList.empty();
	}
	resultList.click(function(e){
		var temp = e.target;
		if(temp.className=="list-item"){
			$(".search-container .search").val(temp.innerText);
		}
	})

});
