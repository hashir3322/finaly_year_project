<?php
	$action = 0;	
	session_start();

	extract($_REQUEST);

?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>MIS - Register</title>
	<link rel="stylesheet" href="../CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="../CSS/shared.css">
	<link rel="stylesheet" href="../CSS/register.css">
</head>
<body>
	<style>
		.login-info-section{
			padding: 100px 0;
		}
		.info-container{
			width: 80%;
			margin: 0 auto;
		}
		.info-heading{
			text-align: center;
			margin-bottom: 20px;
		}
		.info-sub-heading{
			margin: 10px 0;
		}
		.info-description{
			line-height: 24px;
		}
	</style>
	<section class="hero-section">
		<nav class="main-nav">
			<div class="logo-container">
				<img src="../Images/Logo.png" alt="" class="logo">
			</div>
			<ul class="nav-list">
				<li class="nav-item"><a href="../index.php" class="nav-link">Home </a></li>
				<li class="nav-item"><a href="./policy.php" class="nav-link">Policy</a></li>
				<li class="nav-item"><a href="./about.php" class="nav-link">About</a></li>
				<?php 
					if(isset($_SESSION['userInfo']['pharmacy_name'])){
				?>
				<li class="nav-item sub-menu-container">
					<img src="../Images/user.png" alt="" class="avatar">
					<ul class="sub-menu">
						<li class="sub-item"><a href="../Dashboard/dashboard.php" class="sub-link">Dashboard</a></li>
						<li class="sub-item"><a href="./logout.php" class="sub-link">Logout</a></li>
					</ul>
				</li>
				<?php						
					}else{
				?>
				<li class='nav-item'><a href='./login.php' class='nav-link'>Login</a></li>
				<?php
					}
				?>
			</ul>
			<div class="menu-btn">
				<span class="bar"></span>
				<span class="bar"></span>
				<span class="bar"></span>
			</div>
		</nav>
		<a href="#form-section" class="login-cta">Register <i class="fas fa-chevron-down"></i></a>
		<h2 class="success-label">
			<?php
				if($action == 1){
				echo "Username already taken. Account not created";
				}else if($action == 2){
					echo "You were registered Successfully";
				}else if($action == 3){
					echo "Could not register";
				}
			?>

		</h2>		
	</section>

	<section id="form-section">
			<div class="form-container">
				<h1>Register Here</h1>
				<form action="./register-user.php" class="login-form" method="post">
					<label for="name">Pharmacy Name</label>
					<input type="text" name="name" class="name-field" placeholder="Pharmacy Name">
					<label for="username">Username</label>
					<input type="text" name="username" class="username-field" placeholder="Username">
					<label for="password">Password</label>
					<input type="password" name="password" class="password-field" placeholder="Password">
					<label for="password">License</label>
					<input type="password" name="license" class="license-field" placeholder="license">
					<input type="submit" name="register" class="submit-btn">
					<p class="input-warning">Invalid Input Scroll to get detail about input</p>
					<a href="">Not registered? Register Now</a>
				</form>
			</div>
	</section>
	

	<Section class="login-info-section">
		<div class="info-container">
		<h1 class="info-heading">Input Method</h1>
			<h3 class="info-sub-heading">Pharmacy Name</h3>
			<p class="info-description">
				Pharmacy name should only consist of alphabets. And it should not be empty and should not be greater than 30 characters eg.(dummay pharmacy). 
			</p>
			<h3 class="info-sub-heading">Username</h3>
			<p class="info-description">
				Username must be greater than 8 and than or equal to 30 characters. It should start with alphabets and end with numeric values eg.(dummy123). There should be no spaces.  
			</p>
			<h3 class="info-sub-heading">Password</h3>
			<p class="info-description">
				Password must be greater than 8 and less than or equal to 20 characters. It should start with numeric values and end with alphabets values eg.(123dummy). Special characters and spaces are not allowed. 
			</p>
			<h3 class="info-sub-heading">License</h3>
			<p class="info-description">
				License should be digits only with only one special character allowed i.e - (hyphen).
				For example (12345-43211);
			</p>
		</div>
	</Section>
	<section class="footer">
			<nav class="footer-nav">
				<div class="logo-container">
					<img src="../Images/Logo.png" alt="" class="logo">
				</div>
				<ul class="footer-nav-list">
					<li class="nav-item"><a href="../index.php" class="nav-link">Home </a></li>
					<li class="nav-item"><a href="./policy.php" class="nav-link">Policy</a></li>
					<li class="nav-item"><a href="./about.php" class="nav-link">About</a></li>
				<?php 
				if(isset($_SESSION['userInfo']['pharmacy_name'])){
						
						echo "<li class='nav-item'><a href='./logout.php' class='nav-link'>Logout</a></li>";
					}else{
						echo "<li class='nav-item'><a href='./login.php' class='nav-link'>Login</a></li>";
					}
				?>
				</ul>
		</nav>
		<p class="copyright">&copy; 2020 <a href="./policy.php">Policy</a></p>
	</section>
	<script src="../JS/jquery-3.5.1.min.js"></script>
	<script src="../JS/shared.js"></script>
	<script src="../JS/register.js"></script>
</body>
</html>