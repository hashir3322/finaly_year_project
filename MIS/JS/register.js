$(document).ready(function(){

	var loginForm = $(".form-container .login-form");
	var nameField = $(".login-form .name-field");
	var usernameField = $(".login-form .username-field");
	var passwordField = $(".login-form .password-field");
	var licenseField = $(".login-form .license-field");
	var inputWarning = $(".login-form .input-warning");

	var nameRegex = /[^A-Za-z\s]/ig;
	var usernameRegex = /^[a-zA-Z]{1,}\d*$/ig;
	var spaceRegex = /[\s]/ig;
	var passwordRegex = /^[\d]{1,}[A-Za-z]*$/ig;
	var licenseRegex = /[^0-9\-]+/ig;


	loginForm.submit(function(event){
		if(validateAll() == false){
			inputWarning.addClass("input-warning-active");
			event.preventDefault();
		}
	})

	function validateAll(){
		var name = nameField.val();
		var username = usernameField.val();
		var password = passwordField.val();
		var license = licenseField.val();
		var isNameValid = validateName(name);
		var isLicenseValid = validateLicense(license);
		var isUsernameValid = validateUsername(username);
		var isPasswordValid = validatePassword(password);
		if(isNameValid == true && isUsernameValid == true && isPasswordValid == true && isLicenseValid == true){
			return true;
		}else{
			return false;
		}
	}

	function validateName(name){
		if(nameRegex.test(name) == false && name.length >= 1 && name.length <= 30){
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

	function validateLicense(license){
		if(licenseRegex.test(license) == false && spaceRegex.test(license) == false && license.length>=8 && license.length <= 20){
			return true;
		}else{
			return false;
		}	
	}
});