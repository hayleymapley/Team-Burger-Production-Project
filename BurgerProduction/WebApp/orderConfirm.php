<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

$name = $_POST['name'];
$email = $_POST['email'];

function createCustomer($name, $email) {
    $dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=mapleyhayl_jdbc user=mapleyhayl password=pass123")
    or die("Can't connect to database".pg_last_error());
    
    $sql = pg_query($dbconn3,"INSERT INTO customers (name,email) VALUES('$name', '$email')");
    
    $query = "SELECT customer_ID FROM customers where name = '$name' AND email = '$email'";
    $result = pg_query($query);
    if (!$result) {
        echo "Problem with query " . $query . "<br/>";
        echo pg_last_error();
        exit();
    }
    $row = pg_fetch_assoc($result);
    $customerID = $row['customer_id'];
    $timestamp = date('Y-m-d G:i:s');
    
    echo "create customer method here";
    
    createOrder($customerID, $timestamp);
    
}

if (isset($_POST['confirm'])) {
    createCustomer($name, $email);
}




function createOrder($customerID, $timestamp) {
    
    $dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=mapleyhayl_jdbc user=mapleyhayl password=pass123")
    or die("Can't connect to database".pg_last_error());
    
    $sql = pg_query($dbconn3,"INSERT INTO orders (customer_id,completed,timestamp) VALUES('$customerID', false, '$timestamp')");
    
    $query = "SELECT order_id FROM orders WHERE customer_id = '$customerID' AND timestamp='$timestamp'";
    $result = pg_query($query);
    if (!$result) {
        echo "Problem with query " . $query . "<br/>";
        echo pg_last_error();
        exit();
    }
    $row = pg_fetch_assoc($result);
    $orderID = $row['order_id'];
}

// header('Location: testform2.php');

?>