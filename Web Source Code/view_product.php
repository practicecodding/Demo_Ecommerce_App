<?php

header('Content-Type: application/json; charset=utf-8');

$con = mysqli_connect( "localhost","wpkkcuac_root","Mmy_databaseH","wpkkcuac_my_database" );

$sql = " SELECT * FROM product_table ORDER BY id DESC" ;

$result = mysqli_query($con,$sql);

$data = array();

foreach($result as $item){
	
	$products['id'] = (int) $item['id'];
	$products['name'] = $item['name'];
	$products['image_url'] = $item['image_url'];
	$products['status'] = $item['status'];
	$products['price'] =(double)$item['price'];
	$products['discount'] = (double)$item['discount'];
	$products['stock'] = (int)$item['stock'];
	array_push($data,$products);
	
}

echo json_encode($data);

?>