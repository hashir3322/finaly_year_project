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
	<title>MIS - Login</title>
	<link rel="shortcut icon" type="image/png" href="../Images/favicon.png">
	<link rel="stylesheet" href="../CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="../CSS/shared.css">
	<link rel="stylesheet" href="../CSS/login.css">
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
<!-- 		<button class="login-cta">Login <i class="fas fa-chevron-down"></i></button> -->
			<div class="form-container">
				<img src="../Images/user.png" alt="avatar" class="avatar">
				<h1>Login Here</h1>
				<form action="../Dashboard/dashboard.php" class="login-form" method="post">
					<label for="username">Username</label>
					<input type="text" name="username" class="username-field" placeholder="Username">
					<p class="input-warning">Invalid Input Scroll to get detail about input</p>
					<label for="password">Password</label>
					<input type="password" name="password" class="password-field" placeholder="Password">
					<p class="input-warning">Invalid Input Scroll to get detail about input</p>
					<input type="submit" name="login" class="submit-btn">
					<p class="server-warning">
					<?php
						if($action == 1){
						echo "No match found";
						}else{
							echo "";
						}
					?>	
					</p>
					<a href="./register.php">Not registered? Register Now</a>
				</form>
			</div>
	</section>

	<Section class="login-info-section">
		<div class="info-container">
		<h1 class="info-heading">Input Method</h1>
			
			<h2 class="info-sub-heading">Username:</h2>
			<p class="info-description">
				Username must be greater than 8 and than or equal to 30 characters. It should start with alphabets and end with numeric values (eg. dummy123). There should be no spaces.  
			</p>
			<h2 class="info-sub-heading">Password:</h2>
			<p class="info-description">
				Password must be greater than 8 and less than or equal to 20 characters. It should start with numeric values and end with alphabets values (eg. 123dummy). Special characters and spaces are not allowed. 
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
	<script src="../JS/login.js"></script>

</body>
</html>