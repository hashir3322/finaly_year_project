<?php 
	
	include "connection.php";

	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$m_name = $data['medName'];

	$sql = "SELECT med_id,med_name,med_price,med_net_price,	med_manufacturer,med_quantity FROM medicine_tbl WHERE med_name LIKE '{$m_name}%'";
	$result = mysqli_query($conn,$sql) or die (mysqli_error($conn));

	if(mysqli_num_rows($result) > 0){
		$output = mysqli_fetch_all($result, MYSQLI_ASSOC);
		echo json_encode($output);
	}else{
		echo json_encode(array('message'  => 'No Record Found.', 'status' => False));
	}

?>