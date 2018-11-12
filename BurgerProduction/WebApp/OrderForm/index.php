<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="stylesheet.css" />
    <title>Caitlin | Contact Us</title>
    <script type="text/javascript" src="../Scripts/OrderForm.js"></script>
</head>

<body onload="renderOrderForm()">

    <?php require '../Components/Header/index.php' ?> 
    
    <h1>Contact Us</h1>
    <p>Feel free to pop in and see us, or complete the form below and we'll be in touch!</p>

    <div style='float:left' class="container" id="orderForm"></div>

    <div style='float:center' class="textarea" id="summaryForm">
        <h2>Order Summary</h2>

        <ul id="list"></ul> 
    </div>

    <?php
        if (isset($_POST['name'])) {
            $nameValue = $_POST['name'] . "\n";
            $emailAddress = $_POST['emailAddress'] . "\n";
            $phoneNumber = $_POST['phoneNumber'] . "\n";
            $contactMethod = $_POST['contactMethod'] . "\n";
            $query = $_POST['query'] . "\n";
            file_put_contents("message.txt", $nameValue . $emailAddress . $phoneNumber . $contactMethod . $query, FILE_APPEND);
            print"<p><center><em>Thanks for submitting your form, we'll be in touch soon!</em></p>";
        }
    ?>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwOWzhFsW-brtgpLHBRALTUnuJOft8sWU&callback=myMap"></script>

    <br>
    <br>
    
    

    <?php require '../Components/Footer/index.php' ?> 


</body>