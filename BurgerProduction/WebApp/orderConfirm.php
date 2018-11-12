<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

$name = $_POST['name'];
$email = $_POST['email'];



function createCustomer($name, $email)
{
    $dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=mapleyhayl_jdbc user=mapleyhayl password=pass123") or die("Can't connect to database" . pg_last_error());
    
    $sql = pg_query($dbconn3, "INSERT INTO customers (name,email) VALUES('$name', '$email')");
    
    $query = "SELECT customer_ID FROM customers where name = '$name' AND email = '$email'";
    $result = pg_query($query);
    if (! $result) {
        echo "Problem with query " . $query . "<br/>";
        echo pg_last_error();
        exit();
    }
    $row = pg_fetch_assoc($result);
    $customerID = $row['customer_id'];
    $timestamp = date('Y-m-d G:i:s');
    
    createOrder($customerID, $timestamp);
}

if (isset($_POST['confirm'])) {
    createCustomer($name, $email);
}

function createOrder($customerID, $timestamp)
{
    $dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=mapleyhayl_jdbc user=mapleyhayl password=pass123") or die("Can't connect to database" . pg_last_error());
    
    $sql = pg_query($dbconn3, "INSERT INTO orders (customer_id,completed,timestamp) VALUES('$customerID', false, '$timestamp')");
    
    $query = "SELECT order_id FROM orders WHERE customer_id = '$customerID' AND timestamp='$timestamp'";
    
    $result = pg_query($query);
    
    if (! $result) {
        echo "Problem with query " . $query . "<br/>";
        echo pg_last_error();
        exit();
    }
    $row = pg_fetch_assoc($result);
    $orderID = $row['order_id'];
    
    createBurger($orderID);
}

function createBurger($orderID){
    
    $bunLettuce = $_POST['Bun_Lettuce'];
    $bunStandard = $_POST['Bun_Standard'];
    $vegeLettuce = $_POST['Vege_Lettuce'];
    $vegeTomato = $_POST['Vege_Tomato'];
    $vegeOnion = $_POST['Vege_Onion'];
    $vegePickles = $_POST['Vege_Pickles'];
    $vegeBeetroot = $_POST['Vege_Beetroot'];
    $cheeseCheddar = $_POST['Cheese_Cheddar'];
    $cheeseVegan = $_POST['Cheese_Vegan'];
    $pattyBeef = $_POST['Patty_Beef'];
    $pattyChicken = $_POST['Patty_Chicken'];
    $pattyTofu = $_POST['Patty_Tofu'];
    $sauceTomato = $_POST['Sauce_Tomato'];
    $sauceChilli = $_POST['Sauce_Chilli'];
    $sauceAioli = $_POST['Sauce_Aioli'];
    
    $dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=mapleyhayl_jdbc user=mapleyhayl password=pass123") 
    or die("Can't connect to database" . pg_last_error());
    
    $sql = pg_query($dbconn3, "INSERT INTO burger_ingredients (order_id, bun_lettuce, bun_standard, vege_lettuce, vege_tomato, vege_onion, vege_pickles, vege_beetroot, cheese_cheddar, cheese_vegan, patty_beef, patty_chicken, patty_tofu, sauce_tomato, sauce_chilli, sauce_aioli)
           VALUES('$orderID', '$bunLettuce', '$bunStandard', '$vegeLettuce', '$vegeTomato', '$vegeOnion', '$vegePickles', '$vegeBeetroot', '$cheeseCheddar', '$cheeseVegan', '$pattyBeef', '$pattyChicken', '$pattyTofu', '$sauceTomato', '$sauceChilli', '$sauceAioli')");

    $ingredients = array("bun_lettuce", "bun_standard", "vege_lettuce", "vege_tomato", "vege_onion", "vege_pickles", "vege_beetroot", "cheese_cheddar", "cheese_vegan", "patty_beef", "patty_chicken", "patty_tofu", "sauce_tomato", "sauce_chilli", "sauce_aioli");
    $values = array($bunLettuce, $bunStandard, $vegeLettuce, $vegeTomato, $vegeOnion, $vegePickles, $vegeBeetroot, $cheeseCheddar, $cheeseVegan, $pattyBeef, $pattyChicken, $pattyTofu, $sauceTomato, $sauceChilli, $sauceAioli);
    
    for($i=0; $i<count($ingredients); $i++) {
        
        echo $ingredients[$i] . $values[$i] . "<br>";
        $sqlUpdateStock = pg_query($dbconn3, "update stock_ingredients set quantity = quantity - " . $values[$i] . " where name = '" . $ingredients[$i] . "'");
    }
    
    
    
//     $sqlUpdateStock = pg_query($dbconn3, "update stock_ingredients set quantity = quantity - " . $bunLettuce . " where name = 'Bun_Lettuce'");
    
}
    
    
     


// header('Location: testform2.php');

?>