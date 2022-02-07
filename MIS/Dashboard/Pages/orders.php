<?php

	session_start();
	
	if(isset($_SESSION['userInfo']['pharmacy_name']) == false){
		header('location: ../../Pages/login.php');
	}

?>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>My Orders</title>
	<link rel="shortcut icon" type="image/png" href="../../Images/favicon.png">
	<link rel="stylesheet" href="../../CSS/normalize.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="../CSS/dashboard-shared.css">

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
					<img src="../../Images/user.png" alt="" class="avatar">
					<ul class="sub-menu">
						<li class="sub-item"><a href="../../index.php" class="logout-link">Home</a></li>
						<li class="sub-item"><a href="../../Pages/logout.php" class="logout-link">Logout</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</header>


	<section class="content-section">
		<h1>My Orders</h1>
		<div class="content-table-container">
			<?php

				include "../../API/connection.php";

				$pharmacyUsername = $_SESSION['userInfo']['user_name'];

				$sql = "SELECT *FROM order_tbl WHERE ordering_pharmacy = '{$pharmacyUsername}'";
				$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));
				if(mysqli_num_rows($result) > 0){

			?>
			<table class="content-table">
				<thead>
				  <tr>
				    <th>Id</th>
				    <th>Med Name</th>
				    <th>Price</th>
				    <th>Quantity</th>
				    <th>Net Price</th>
				    <th>Username</th>
				    <th>Pharmacy Name</th>
				    <th>Status</th>
				  </tr>
				</thead>
				<tbody>
				<?php
					while($row = mysqli_fetch_assoc($result)){
				?>				
				  <tr>
				    <td><?php echo $row['order_id']?></td>
				    <td><?php echo $row['ordered_med_name']?></td>
				    <td><?php echo $row['ordered_med_price']?></td>
				    <td><?php echo $row['ordered_med_qty']?></td>
				    <td><?php echo $row['ordered_med_net']?></td>
				    <td><?php echo $row['ordering_pharmacy']?></td>
				    <td><?php echo $row['pharmacy_name']?></td>
				    <td><?php echo $row['order_status']?></td>
				  </tr>
				  <?php
				  	}
				  ?>
				</tbody>
			</table>
			<?php 
		}else{
			echo "<h2>You do not have any order placed<h2>";
		}
		?>
		</div>
		
	</section>

	<div class="sidebar-overlay"></div>
	

	<div class="side-bar">
		<div class="logo-container"><img src="../../Images/logo.png" alt="" class="logo"></div>
		<div class="cross-container"><i class="fa fa-times"></i></div>
		<ul class="side-bar-list">
			<li class="side-bar-list-item"><a href="../Pages/Account-Status.php" class="side-bar-item-link">Account Status</a></li>
			<li class="side-bar-list-item"><a href="../Pages/orders.php" class="side-bar-item-link">My Orders</a></li>
		</ul>
	</div>
	<script src="../../JS/jquery-3.5.1.min.js"></script>
	<script src="../JS/dashboard-shared.js"></script>
</body>
</html>