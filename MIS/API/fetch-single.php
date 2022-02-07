<?php 
	
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,
		Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);
	$med_id = $data['m_id'];

	include "connection.php";


	$sql = "SELECT *FROM medicine_tbl WHERE med_id = {$med_id}";
	$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));

	if(mysqli_num_rows($result) > 0){
		$output = mysqli_fetch_all($result, MYSQLI_ASSOC);
		echo json_encode($output);
	}else{
		echo json_encode(array('message'  => 'No Record Found.', 'status' => False));
	}
?>