<?php 
	
	include "connection.php";
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$pharmacyName = $data['pharmacyName'];
	$userName = $data['userName'];
	$userPassword = $data['userPassword'];
	$userStatus = $data['userStatus'];
	$userLicense = $data['userLicense'];


	$sql = "SELECT *FROM registered_user_tbl";

	$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));

	if(mysqli_num_rows($result) > 0){
		while($row = mysqli_fetch_assoc($result)){
			if($row['user_name'] == $userName){
				echo json_encode(array('message' => 'Username is already taken', 'status' => False));
			}
		}
		$sqlInsert = "INSERT INTO registered_user_tbl(pharmacy_name,user_name,user_password,user_status,user_license) VALUES ('{$pharmacyName}','{$userName}','{$userPassword}','{$userStatus}','{$userLicense}')";

		if(mysqli_query($conn, $sqlInsert)){
			echo json_encode(array('message' => 'You were Registered Successfully', 'status' => True));
		}else{
			echo json_encode(array('message' => 'Could not register you pharmacy', 'status' => False));
		}
	}else{
		$sqlinsert = "INSERT INTO registered_user_tbl(pharmacy_name,user_name,user_password,user_status,user_license) VALUES ('{$pharmacyName}','{$userName}','{$userPassword}','{$userStatus}','{$userLicense}')";

		if(mysqli_query($conn, $sqlInsert)){
			echo json_encode(array('message' => 'You were Registered Successfully', 'status' => True));
		}else{
			echo json_encode(array('message' => 'Could not register you pharmacy', 'status' => False));
		}
	}

?>