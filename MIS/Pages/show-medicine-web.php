<?php
	session_start();
	
?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>MIS</title>
	<link rel="shortcut icon" type="image/png" href="../Images/favicon.png">
	<link rel="stylesheet" href="../CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="../CSS/shared.css">
	<link rel="stylesheet" href="../CSS/show-medicine.css">
</head>
<body>
	<section class="hero-section">
		<nav class="main-nav">
			<div class="logo-container">
				<img src="../Images/Logo.png" alt="" class="logo">
			</div>
			<ul class="nav-list">
				<li class="nav-item"><a href="../index.php" class="nav-link">Home </a></li>
				<li class="nav-item"><a href="./policy" class="nav-link">Policy </a></li>
				<li class="nav-item"><a href="./About" class="nav-link">About</a></li>
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
	<?php
	
	include "../API/connection.php";

	extract($_REQUEST);
	if(isset($_POST['submit'])){
		$m_name = $_POST['search'];
		$user = $_POST['user'];
		if($user == "common"){
			$sql = "SELECT med_name,med_net_price,med_quantity FROM medicine_tbl WHERE med_name = '{$m_name}'";
			$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));
			if(mysqli_num_rows($result) > 0){
					$output = mysqli_fetch_assoc($result);
					echo '<div class="result-container">
							<div class="info-container">
								<h3 class="info-heading">Name</h3>
								<h3 class="medicine-info med-name">'.$output['med_name'].'</h3>
								<h3 class="info-heading">Price</h3>
								<h3 class="medicine-info">'.$output['med_net_price'].'</h3>
								<h3 class="info-heading">Available</h3>
								<h3 class="medicine-info">Yes</h3>
								<h3 class="info-heading">Pharmacy Name</h3>
								<h3 class="medicine-info">Ali Pharmacy</h3>
								<h3 class="info-heading">Address</h3>
								<h3 class="medicine-info">Dummy Address xyz</h3>
							</div>
						</div>';
			
			}else{
				echo '<div class="not-found-container">
						<h2 class="not-found">Sorry can\'t find your required medicine</h2>
					</div>';
			}
		}else if($user == "registered"){

			$sql = "SELECT med_name,med_net_price,med_quantity FROM medicine_tbl WHERE med_name = '{$m_name}'";
			$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));
			if(mysqli_num_rows($result) > 0){
				$output = mysqli_fetch_assoc($result);

				if($output['med_quantity'] >= 100){
					echo '<div class="result-container">
							<div class="info-container">
								<h3 class="info-heading">Name</h3>
								<h3 class="medicine-info med-name">'.$output['med_name'].'</h3>
								<h3 class="info-heading">Price</h3>
								<h3 class="medicine-info">'.$output['med_net_price'].'</h3>
								<h3 class="info-heading">Available</h3>
								<h3 class="medicine-info">Yes</h3>
								<h3 class="info-heading">Pharmacy Name</h3>
								<h3 class="medicine-info">Ali Pharmacy</h3>
								<h3 class="info-heading">Address</h3>
								<h3 class="medicine-info">Dummy Address xyz</h3>

							</div>
							<form class="order-form">
								<label for="med-name">Medicine Name</label>
								<input type="text" name="medName" class="med-name-input order-input" readonly value="">
								<label for="quantity">Quantity</label>
								<input type="number" name="medQuantity" min="100" class="quantity order-input" required>
								<label for="username">Username</label>
								<input type="text" name="username" class="username username-field order-input" required>
								<label for="password">Password</label>
								<input type="password" name="password" class="password password-field order-input" required>
								<input type="submit" class="submit-btn" value="Order">
								<p class="input-warning">Invalid Input Scroll to get detail about input</p>
							</form>
								<div class="result-modal">
									<h2 class="message">Your result</h2>
									<a href="../index.php">Return to Home page</a>
								</div>
								<div class="modal-overlay"></div>
						</div>';
				}
				else if($output['med_quantity'] > 0){
					echo '<div class="result-container">
							<div class="info-container">
								<h3 class="info-heading">Name</h3>
								<h3 class="medicine-info">'.$output['med_name'].'</h3>
								<h3 class="info-heading">Price</h3>
								<h3 class="medicine-info">'.$output['med_net_price'].'</h3>
								<h3 class="info-heading">Available</h3>
								<h3 class="medicine-info">Yes</h3>
								<h3 class="info-heading">Pharmacy Name</h3>
								<h3 class="medicine-info">Ali Pharmacy</h3>
								<h3 class="info-heading">Address</h3>
								<h3 class="medicine-info">Dummy Address xyz</h3>
								<p class="less-quantity-info">Not enough quantity available in stock for online order</p>
							</div>
						</div>';
				}else if($output['med_quantity'] <= 0){
					echo '<div class="result-container">
							<div class="info-container">
								<h3 class="info-heading">Name</h3>
								<h3 class="medicine-info">'.$output['med_name'].'</h3>
								<h3 class="info-heading">Price</h3>
								<h3 class="medicine-info">'.$output['med_net_price'].'</h3>
								<h3 class="info-heading">Available</h3>
								<h3 class="medicine-info">Out of Stock</h3>
								<h3 class="info-heading">Pharmacy Name</h3>
								<h3 class="medicine-info">Ali Pharmacy</h3>
								<h3 class="info-heading">Address</h3>
								<h3 class="medicine-info">Dummy Address xyz</h3>
								<h3 class="medicine-info">Out of Stock</h3>
							</div>
						</div>';
				}
			}else{
				echo '<div class="not-found-container">
						<h2 class="not-found">Sorry can\'t find your required medicine</h2>
					</div>';
			}
		}
	}
?>
	</section>

	<section class="order-info-section">
		<div class="info-container">
			<h1 class="info-main-heading">Input Method</h1>
			
			<h3 class="info-sub-heading">Username</h3>
			<p class="info-description">
				Username must be greater than 8 and than or equal to 30 characters. It should start with alphabets and end with numeric values (eg. dummy123). There should be no spaces.  
			</p>
			<h3 class="info-sub-heading">Password</h3>
			<p class="info-description">
				Password must be greater than 8 and less than or equal to 20 characters. It should start with numeric values and end with alphabets values (eg. 123dummy). Special characters and spaces are not allowed. 
			</p>
			<h3 class="info-sub-heading">Quantity</h3>
			<p class="info-description">
				If you are a registered user, you can order. But ordered quantity must be greater than or equal to 100
			</p>
		</div>
	</section>

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
	<script src="../JS/show-medicine.js"></script>
</body>
</html>


