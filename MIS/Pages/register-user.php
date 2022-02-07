<?php
	

	include "../API/connection.php";
			
	extract($_REQUEST);

	if(isset($_POST['register'])){
		$pharmacy_name = $_POST['name'];
		$pharmacy_username = $_POST['username'];
		$pharmacy_password = $_POST['password'];
		$pharmacy_license = $_POST['license'];
		$status = "pending";

		$sqlFind = "SELECT *FROM registered_user_tbl WHERE user_name = '{$pharmacy_username}'";
		$resultFind = mysqli_query($conn,$sqlFind) or die (mysqli_error($conn));
		if(mysqli_num_rows($resultFind)>0){
			header('location: ./register.php?action=1');
			exit;
		}else{
			$sqlInsert = "INSERT INTO registered_user_tbl(pharmacy_name,user_name,user_password,user_status,user_license) VALUES ('{$pharmacy_name}','{$pharmacy_username}','{$pharmacy_password}','{$status}','{$pharmacy_license}')";
				if(mysqli_query($conn, $sqlInsert)){
					header('location: ./register.php?action=2');
				}else{
					header('location: ./register.php?action=3');
				}
		}
	}

?>