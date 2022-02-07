<?php
	
	session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>MIS</title>
	<link rel="shortcut icon" type="image/png" href="./Images/favicon.png">
	<link rel="stylesheet" href="./CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="./CSS/shared.css">
	<link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
	<section class="hero-section">
		<nav class="main-nav">
			<div class="logo-container">
				<img src="./Images/Logo.png" alt="" class="logo">
			</div>
			<ul class="nav-list">
				<li class="nav-item"><a href="#" class="nav-link">Home </a></li>
				<li class="nav-item"><a href="./Pages/policy.php" class="nav-link">Policy</a></li>
				<li class="nav-item"><a href="./Pages/about.php" class="nav-link">About</a></li>
				
				<?php 
					if(isset($_SESSION['userInfo']['pharmacy_name'])){
				?>
				<li class="nav-item sub-menu-container">
					<img src="./Images/user.png" alt="" class="avatar">
					<ul class="sub-menu">
						<li class="sub-item"><a href="./Dashboard/dashboard.php" class="sub-link">Dashboard</a></li>
						<li class="sub-item"><a href="./Pages/logout.php" class="sub-link">Logout</a></li>
					</ul>
				</li>
				<?php						
					}else{
				?>
				<li class='nav-item'><a href='./Pages/login.php' class='nav-link'>Login</a></li>
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

		<h1 class="hero-title">Check Medicine Here...</h1>
		<form name="search-form" class="search-form" method="post" action="./Pages/show-medicine-web.php">
			<h2 class="user-tag">Search as a</h2>
			<div class="radio-container">
				<input type="radio" id="user" name="user" value="common" checked="checked"> 
				<label for="user">Common User</label>
				<input type="radio" id="registered" name="user" value="registered">
				<label for="registered">Pharmacy</label>
			</div>
			<div class="input-container">
				<div class="search-container">
					<input type="text" name="search" class="search" required>
					<i class="fas fa-search search-icon"></i>
				</div>
				<div class="submit-container">
					<input type="submit" value="search" name="submit" class="submit">
				</div>
			</div>
			<div class="result-modal">
				<p class="error-message error-active">Must be a valid name</p>
				<ul class="result-list">
				</ul>
			</div>
		</form>
	</section>
	<Section class="goal-section">
		<div class="goal-content-container">
		<h1 class="goal-heading">Our Goal</h1>
			<p class="goal-description">
				Our aim is to provide service to the people that are in need. The main goal of this system is to provide user with the information of availability of his/her required medicine. And the place where it is available. We are passionate about community service as well as gaining new experiences by doing so.
			</p>
		</div>
	</Section>
	<section class="footer">
			<nav class="footer-nav">
				<div class="logo-container">
					<img src="./Images/Logo.png" alt="" class="logo">
				</div>
				<ul class="footer-nav-list">
					<li class="nav-item"><a href="" class="nav-link">Home </a></li>
					<li class="nav-item"><a href="./Pages/policy.php" class="nav-link">Policy</a></li>
					<li class="nav-item"><a href="./Pages/about.php" class="nav-link">About</a></li>
				<?php 
					if(isset($_SESSION['userInfo']['pharmacy_name'])){	
						echo "<li class='nav-item'><a href='./Pages/logout.php' class='nav-link'>Logout</a></li>";
					}else{
						echo "<li class='nav-item'><a href='./Pages/login.php' class='nav-link'>Login</a></li>";
					}
				?>
				</ul>
		</nav>
		<p class="copyright">&copy; 2020 <a href="./Pages/policy.php">Policy</a></p>
	</section>
	<script src="./JS/jquery-3.5.1.min.js"></script>
	<script src="./JS/shared.js"></script>
	<script type="text/javascript" src="./JS/script.js"></script>
</body>
</html>