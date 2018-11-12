<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

$name = $_POST['name'];
$email = $_POST['email'];

function createCustomer($name, $email) {
    $dbconn3 = pg_connect("host=db.ecs.vuw.ac.nz dbname=mapleyhayl_jdbc user=mapleyhayl password=pass123")
    or die("Can't connect to database".pg_last_error());
    
    $sql = pg_query($dbconn3,"INSERT INTO customers (name,email) VALUES('$name', '$email')");
}

if (isset($_POST['confirm'])) {
    createCustomer($name, $email);
}

header('Location: index.html');

?>