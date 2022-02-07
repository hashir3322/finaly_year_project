<?php
	
	session_start();

?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>MIS - About</title>
	<link rel="shortcut icon" type="image/png" href="../Images/favicon.png">
	<link rel="stylesheet" href="../CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="../CSS/shared.css">
	<link rel="stylesheet" href="../CSS/about.css">
</head>
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

		<h1 class="hero-title">About</h1>
	</section>

	<Section class="about-section">
		<div class="about-content-container">
		<h1 class="about-heading">About Us</h1>
			<p class="about-description">
				Our aim is to provide service to the people that are in need. The main goal of this system is to provide user with the information of availability of his/her required medicine. And the place where it is available. We are passionate about community service as well as gaining new experiences by doing so.
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