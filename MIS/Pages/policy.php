<?php
	session_start();

?>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>MIS - Policy</title>
	<link rel="shortcut icon" type="image/png" href="../Images/favicon.png">
	<link rel="stylesheet" href="../CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="../CSS/shared.css">
	<link rel="stylesheet" href="../CSS/about.css">
</head>
<style>

	.policy-section .policy-content-container{
		width: 80%;
		margin: 0 auto;
	}

	.policy-content-container .policy-heading{
		text-align: center;
	}
	.policy-content-container .policy-description{
		margin-top: 20px;
		text-align: center;
	}

	.policy-content-container .user-heading{
		margin-top: 10px;
	}
	.policy-content-container .user-description{
		margin-top: 10px;
		line-height: 24px;
	}
	.policy-section{
		padding: 100px 0;
	}
</style>
<body>
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

		<h1 class="hero-title">Our Policy</h1>
	</section>

	<Section class="policy-section">
		<div class="policy-content-container">
		<h1 class="policy-heading">Our Policy</h1>
			<p class="policy-description">
				There are two main roles in our website. You can either search as common user or you can search as
				a registered pharmacy.
			</p>
			<h2 class="user-heading">Common User:</h2>
			<p class="user-description">
				If you search as a common user, you will be displayed only the name of pharmacy where the medicine is 
				available as well as other information about the medicine and address of pharmacy.
			</p>
			<h2 class="user-heading">Registered User:</h2>
			<p class="user-description">
				If you search as a registered pharmacy you will be shown with information about the medicine as well 
				as an option to place order. To place order you need to provide your username, password and quantity.  If you are not a registered pharmacy and you want to register yourself, you have to physically go to the pharmacy to register youself. The pharmacy admin will verify your identity and register you. 
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
</body>
</html>