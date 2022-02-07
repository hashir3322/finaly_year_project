<?php 
	
	include "connection.php";

	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$admin_user = $data['adminUsername'];
	$admin_pass = $data['adminPassword'];

	$sql = "SELECT *FROM admin_tbl WHERE admin_username = '{$admin_user}' AND admin_password = '{$admin_pass}'";
	$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));

	if(mysqli_num_rows($result) > 0){
		$output = mysqli_fetch_all($result, MYSQLI_ASSOC);
		echo json_encode(array('message'  => 'Record Found.', 'status' => True));
	}else{
		echo json_encode(array('message'  => 'No Record Found.', 'status' => False));
	}

?>