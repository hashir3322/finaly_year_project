<?php 
	
	include "connection.php";
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$orderId = $data['orderId'];

	$sql = "SELECT *FROM order_tbl WHERE order_id = '{$orderId}'";
	$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));

	if(mysqli_num_rows($result) > 0){
		$deleteSql = "DELETE FROM order_tbl WHERE order_id = '{$orderId}'";
		if(mysqli_query($conn,$deleteSql)){
			echo json_encode(array('message'  => 'Order Deleted Successfully.', 'status' => True));
		}else{
			echo json_encode(array('message'  => 'Could not delete order', 'status' => False));
		}
	}else{
		echo json_encode(array('message'  => 'No record found', 'status' => False));
	}

?>