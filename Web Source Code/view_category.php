<?php

header('Content-Type: application/json; charset=utf-8');

$con = mysqli_connect( "localhost","wpkkcuac_root","Mmy_databaseH","wpkkcuac_my_database" );

$sql = " SELECT * FROM category_table ORDER BY priority ASC" ;

$result = mysqli_query($con,$sql);

$data = array();

foreach($result as $item){
	
	$categories['id'] = $item['id'];
	$categories['name'] = $item['name'];
	$categories['priority'] = $item['priority'];
	$categories['color'] = $item['color'];
	$categories['image_url'] = $item['image_url'];
	array_push($data,$categories);
	
}

echo json_encode($data);

?>