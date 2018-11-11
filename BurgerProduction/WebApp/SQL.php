
 <?php
$dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=yauloui_jdbc user=yauloui password=pass123")
//$dbconn3 = pg_connect("host=localhost dbname=yauloui")
or die("Can't connect to database".pg_last_error());


//show all available ingredients (quanity>20)

$query = "SELECT (name,quantity) FROM stock_ingredients where quantity > 20 ";
$result = pg_query($query);
if (!$result) {
    echo "Problem with query " . $query . "<br/>";
    echo pg_last_error();
    exit();
}
$i = 0;
$arr = pg_fetch_all($result);
foreach ($arr as &$value) {
    
    //print results as a list/table
    $row = $value;
    foreach($row as &$value){
        echo "row= name, quantity"."<br/>";
        print_r("row=". $value. "<br/>");
    }
}


//Add new customer row and print just ID
// $sql = pg_query($dbconn3,"INSERT INTO customers (name,email) VALUES('john', 'john@example.com')");
$query = "SELECT customer_ID FROM customers where email = 'bob@example.com'";
$result2 = pg_query($query);
if (!$result2) {
    echo "Problem with query " . $query . "<br/>";
    echo pg_last_error();
    exit();
}

$myrow = pg_fetch_assoc($result2);
$customerID = $myrow['customer_id'];
//$value2 = $myrow[numofratings];

echo "customerID =" . "$customerID <br/>";

//add customerID to order table and create new order id row
$timestamp = date('Y-m-d G:i:s');


echo 'timestamp=' ."$timestamp <br/>";

//$sql2 = pg_query($dbconn3,"INSERT INTO orders (customer_id,completed,timestamp) VALUES('$customerID', false, '$timestamp')");
$query = "SELECT order_ID FROM orders where customer_id = '1'";
$result3 = pg_query($query);
if (!$result3) {
    echo "Problem with query " . $query . "<br/>";
    echo pg_last_error();
    exit();
}

$myrow = pg_fetch_assoc($result3);
$orderID2 = $myrow['order_id'];
//$value2 = $myrow[numofratings];

echo "orderID =" . "$orderID2 <br/>";

//return orderID for a certain customer
$sql2 = pg_query($dbconn3,"INSERT INTO orders (customer_id,completed,timestamp) VALUES('2', false, '$timestamp')");
$query = "SELECT order_ID FROM orders where customer_id = '2'";
$result4 = pg_query($query);
if (!$result4) {
    echo "Problem with query " . "$query <br/>";
    echo pg_last_error();
    exit();
}

$myrow = pg_fetch_assoc($result4);
$orderID1 = $myrow['order_id'];
//$value2 = $myrow[numofratings];
echo "all orders for customerID 2";
echo "orderID =" . "$orderID1";

// add selected quantities of ingredients to a new burger

 //$sql = pg_query($dbconn3,"INSERT INTO burger_ingredients (order_id,bun_lettuce, vege_lettuce, vege_tomato
// , vege_onion , vege_pickles , vege_beetroot , cheese_cheddar,
 // patty_beef , sauce_tomato , sauce_aioli) VALUES('8',2,2,2,2,2,2,2,2,2,2)");
$query = "SELECT * FROM burger_ingredients where order_id = '8'";
$result5 = pg_query($query);
if (!$result5) {
    echo "Problem with query " . $query . "<br/>";
    echo pg_last_error();
    exit();
}

$i = 0;
$arr2 = pg_fetch_all($result5);
foreach ($arr2 as &$value) {
    
    //print results as a list/table
    $row = $value;
    foreach($row as &$value){
        print_r("row=". $value. "<br/>");
    }
}


//remove the ingredients used in confirmed order from ingredients table


//get all orders for a specific customer id
$query = "SELECT order_ID FROM orders where customer_id = '2'";
$result3 = pg_query($query);
if (!$result3) {
    echo "Problem with query " . $query . "<br/>";
    echo pg_last_error();
    exit();
}

$myrow = pg_fetch_assoc($result3);

// need to find a way to return all rows
$orderID2 = $myrow['order_id'];
//$value2 = $myrow[numofratings];

echo "orderID =" . "$orderID2";




//get the burger ingredients for specific order id





//update customerID in order table
// $sql2 = pg_query($dbconn3,"UPDATE orders 
//         SET (customer_id) = 
//         ('3') WHERE order_id = '9' ");
// $query = "SELECT order_ID FROM orders where customer_id = '1'";
// $result6 = pg_query($query);
// if (!$result6) {
//     echo "Problem with query " . $query . "<br/>";
//     echo pg_last_error();
//     exit();
// }

// $myrow = pg_fetch_assoc($result6);
// $orderID2 = $myrow['order_id'];

// //print result
// echo "orderID =" . "$orderID2 <br/>";



 
 
// add selected quantities of ingredients to a new burger 
 
//remove the ingredients used in confirmed order from ingredients table


//get all orders for a specific customer id

 
//get the burger ingredients for specific order id


//put ingredient quantitiys back if ingredient unselected or order canceled




//get variables from form inputs to pass to sql query

//create a customer entry 
// $sql = pg_query($dbconn3,"INSERT INTO customers (name,email) VALUES('john', 'john@example.com')");
// //return all from customer
// //print result
// $result = pg_query($dbconn3, "SELECT * FROM customers WHERE email = ");
// if (!$result) {
//     echo "An error occurred.\n";
//     exit;
// }

// $arr = pg_fetch_all($result);

// print_r($arr);


// }
    
//  //query customer id created    
//  $custID = pg_query($dbconn3,"SELECT customer_id FROM customer WHERE email = $email");
//  //print custID
//  $row = pg_fetch_row($custID);
//  echo $row[0] . "\n";
 
//  //create orderID after receiving customerID
//  $sql = "INSERT INTO orders (customer_ID, completed) VALUES ('$custID', false)";
//  //return orderID for the customer
//  $result = pg_query($dbconn3,"SELECT order_id FROM orders WHERE customer_ID = '$custID'");
//  //print result
//  while ($row = pg_fetch_row($result)) {
//      echo "Row: $row[0]";}
     
     
// //display/select all available ingredients
//      $result = pg_query($dbconn3,"SELECT * from stock_ingredients WHERE quantity > 5");
//      //print result
//      while ($row = pg_fetch_row($result)) {
//          echo "Row: $row[0]";
// }







 ?>

