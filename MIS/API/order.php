<?php 
	
	include "connection.php";
	header('Content-Type: application/json');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: POST');
	header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type: application/json,Access-Control-Allow-Methods, Authorization, X-Requested-With');

	$data = json_decode(file_get_contents("php://input"), true);

	$med_name = $data['m_name'];
	$med_quantity = $data['m_quantity'];
	$u_name = $data['u_name'];
	$u_password = $data['u_password'];
	

	$orderStatus = "pending";
	$found = false;

	$sqlFind = "SELECT user_name,user_password,pharmacy_name,user_status FROM registered_user_tbl";
	$resultFind = mysqli_query($conn,$sqlFind) or die (mysqli_error($conn));

	if(mysqli_num_rows($resultFind) > 0){
		while($row = mysqli_fetch_assoc($resultFind)){
			$pharmacyName = $row['pharmacy_name'];
			if($row['user_name'] == $u_name && $row['user_password'] == $u_password && $row['user_status'] == "active"){
				$found = true;
				$sqlQuantity = "SELECT med_name,med_quantity,med_net_price FROM medicine_tbl WHERE med_name = '{$med_name}'";
				$resultQuantity = mysqli_query($conn, $sqlQuantity) or die (mysqli_error($conn));
				if(mysqli_num_rows($resultQuantity) > 0){
					$output = mysqli_fetch_assoc($resultQuantity);
					$qtyDed = $output['med_quantity'] - $med_quantity;
					if($qtyDed >= 0){
						$sqlUpdate = "UPDATE medicine_tbl SET med_quantity = '{$qtyDed}' WHERE med_name = '{$med_name}'";
						if(mysqli_query($conn, $sqlUpdate)){
							placeOrder($med_quantity,$output['med_name'],$output['med_net_price'],$u_name,$pharmacyName,$orderStatus,$conn);
						}else{
							json_encode(array('message' => 'Could not update record', 'status' => false));
						}
					}else if($qtyDed < 0){
						echo json_encode(array('message' => 'Your given quantity exceeded the quantity in stock', 'status' => false));
					}
				}else{
					echo json_encode(array('message' => 'No quantity found', 'status' => false));
				}
			}else{
				continue;
			}
		}
		if($found == false){
			echo json_encode(array('message' => 'You are not a registered pharmacy or your status has not been approved(yet)', 'status' => false));
		}
	}else{
		echo json_encode(array('message' => 'No record Found', 'status' => False));
	}

	function placeOrder($given_med_qty,$med_name,$med_price,$u_name,$pharmacyName,$orderStatus,$conn){
		
		$total = $given_med_qty * $med_price;
		$sqlInsert = "INSERT INTO order_tbl(ordered_med_name,ordered_med_price,ordered_med_qty,ordered_med_net,ordering_pharmacy,pharmacy_name,order_status) VALUES ('{$med_name}','{$med_price}','{$given_med_qty}','{$total}','{$u_name}','{$pharmacyName}','{$orderStatus}')";


		if(mysqli_query($conn, $sqlInsert)){
			echo json_encode(array('message' => 'Your ordere was placed successfully', 'status' => True));
		}else{
			echo json_encode(array('message' => 'Could not place order successfully', 'status' => False));
		}
	}

?>