<?php 
	
	include "connection.php";
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$medId = $data['med_id'];

	$sql = "SELECT *FROM medicine_tbl WHERE med_id = '{$medId}'";
	$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));

	if(mysqli_num_rows($result) > 0){
		$deleteSql = "DELETE FROM medicine_tbl WHERE med_id = '{$medId}'";
		if(mysqli_query($conn,$deleteSql)){
			echo json_encode(array('message'  => 'Order Deleted Successfully.', 'status' => True));
		}else{
			echo json_encode(array('message'  => 'Could not delete order', 'status' => False));
		}
	}else{
		echo json_encode(array('message'  => 'No record with such id', 'status' => False));
	}

?>