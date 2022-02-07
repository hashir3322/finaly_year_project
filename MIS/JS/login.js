$(document).ready(function(){

	var loginForm = $(".form-container .login-form");
	var usernameField = $(".login-form .username-field");
	var passwordField = $(".login-form .password-field");
	var inputWarning = $(".login-form .input-warning");

	var usernameRegex = /^[a-zA-Z]{1,}\d*$/ig;
	var spaceRegex = /[\s]/ig;
	var passwordRegex = /^[\d]{1,}[A-Za-z]*$/ig;

	loginForm.submit(function(event){
	// console.log(usernameRegex.test("hashir332"));
		// console.log(validateAll());
		if(validateAll() == false){
			inputWarning.each(function(index){
				$(this).addClass("input-warning-active");
				event.preventDefault();
			})
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
});

