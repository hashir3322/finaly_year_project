<?php 
	
	include "connection.php";
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$id = $data['id'];
	$pharmacyName = $data['pharmacyName'];
	$userName = $data['userName'];
	$userPassword = $data['userPassword'];
	$userStatus = $data['userStatus'];
	$userLicense = $data['userLicense'];


	$sqlUpdate = "UPDATE registered_user_tbl SET pharmacy_name = '{$pharmacyName}',user_name = '{$userName}',user_password = '{$userPassword}',user_status = '{$userStatus}',user_license = '{$userLicense}' WHERE user_id = '{$id}'";

	if(mysqli_query($conn, $sqlUpdate)){
		echo json_encode(array('message' => 'Record Updated', 'status' => true));
	}else{
		echo json_encode(array('message' => 'Could not update record', 'status' => False));
	}

?>