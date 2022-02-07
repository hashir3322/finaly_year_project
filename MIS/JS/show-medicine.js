$(document).ready(function(){


	var medName = $(".info-container .med-name");
	
	var orderBtn = $(".result-container .order-btn");
	var orderForm = $(".result-container .order-form");

	var medNameInput = $(".order-form .med-name-input");
	var submitBtn = $(".order-form .submit-btn");

	medNameInput.attr("value",medName.text());

	var resultModal = $(".result-container .result-modal");
	var modalOverlay = $(".result-container .modal-overlay");

	var usernameField = $(".order-form .username-field");
	var passwordField = $(".order-form .password-field");

	var inputWarning = $(".order-form .input-warning");


	var resultMsg = $(".result-modal .message");

	var usernameRegex = /^[a-zA-Z]{1,}\d*$/ig;
	var spaceRegex = /[\s]/ig;
	var passwordRegex = /^[\d]{1,}[A-Za-z]*$/ig;

	orderForm.submit(function(event){
		event.preventDefault();
		if(validateAll() == false){
			inputWarning.addClass("input-warning-active");
		}else{
			var medObj = {
				m_name: $(".order-form .med-name-input").val(),
				m_quantity: $(".order-form .quantity").val(),
				u_name: $(".order-form .username").val(),
				u_password: $(".order-form .password").val()
			}
			placeOrder(medObj);
		}
	})

	function validateAll(){
		var username = usernameField.val();
		var password = passwordField.val();
		var isUsernameValid = validateUsername(username);
		var isPasswordValid = validatePassword(password);
		if(isUsernameValid == true && isPasswordValid == true){
			return true;
		}else{
			return false;
		}
	}

	function validateUsername(username){
		if(usernameRegex.test(username) == true && spaceRegex.test(username) == false && username.length>=8 && username.length <= 30){
			return true;
		}else{
			return false;
		}
	}

	function validatePassword(password){
		if(passwordRegex.test(password) == true && spaceRegex.test(password) == false && password.length>=8 && password.length <= 20){
			return true;
		}else{
			return false;
		}
	}

	function placeOrder(medObj){
		$.ajax({
			type: "POST",
			url: "../API/order.php",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(medObj),
			success: function(data){
				updateDom(data);
			},
			error: function(error){
				console.log(error);
			}
		});
	}

	function updateDom(data){
		resultModal.fadeIn();
		modalOverlay.fadeIn();
		resultMsg.text(data.message);
	}



});



