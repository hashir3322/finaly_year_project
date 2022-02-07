
<?php
	
	session_start();


	include "../API/connection.php";
		

	extract($_REQUEST);
	if(isset($_POST['login'])){
		$pharmacy_username = $_POST['username'];
		$pharmacy_password = $_POST['password'];

		$sql = "SELECT *FROM registered_user_tbl WHERE user_name = '{$pharmacy_username}' AND user_password = '{$pharmacy_password}'";
		$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));
		if(mysqli_num_rows($result) > 0){
			$output = mysqli_fetch_assoc($result);
		// = array("pharmacy_name" => "","user_name" => "");
			$_SESSION['userInfo'] = array("pharmacy_name" => $output['pharmacy_name'],"user_name" => $output['user_name']);
		}else{
				header('location: ../Pages/login.php?action=1');
		}
	}

	if(isset($_SESSION['userInfo']['pharmacy_name']) == false){
		header('location: ../Pages/login.php?action=1');
	}


?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Dashboard</title>
	<link rel="shortcut icon" type="image/png" href="../Images/favicon.png">
	<link rel="stylesheet" href="../CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="./CSS/dashboard-shared.css">

</head>
<body>



	<header class="dashboard-header">
		<nav class="dashboard-nav">
		<div class="menu-btn">
			<span class="bar"></span>
			<span class="bar"></span>
			<span class="bar"></span>
		</div>
			<ul class="nav-list">
				<li class="list-item"><span class="label">Welcome </span><span class="username-info">
			<?php
				if(isset($_SESSION['userInfo']['pharmacy_name'])){				
					echo $_SESSION['userInfo']['pharmacy_name'];
					}else{
						echo "";
					}
			?>
				</span></li>
				<li class="list-item sub-menu-container">
					<img src="../Images/user.png" alt="" class="avatar">
					<ul class="sub-menu">
						<li class="sub-item"><a href="../index.php" class="logout-link">Home</a></li>
						<li class="sub-item"><a href="../Pages/logout.php" class="logout-link">Logout</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</header>


	<section class="content-section">
		<h1>Welcome to Dashboard</h1>
	</section>

	<div class="sidebar-overlay"></div>
	

	<div class="side-bar">
		<div class="logo-container"><img src="../Images/logo.png" alt="" class="logo"></div>
		<div class="cross-container"><i class="fa fa-times"></i></div>
		<ul class="side-bar-list">
			<li class="side-bar-list-item"><a href="./Pages/Account-Status.php" class="side-bar-item-link">Account Status</a></li>
			<li class="side-bar-list-item"><a href="./Pages/orders.php" class="side-bar-item-link">My Orders</a></li>
		</ul>
	</div>
	<script src="../JS/jquery-3.5.1.min.js"></script>
	<script src="./JS/dashboard-shared.js"></script>
</body>
</html>