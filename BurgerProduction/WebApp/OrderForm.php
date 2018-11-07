<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="stylesheet.css" />
        <title>Caitlin | Contact Us</title>
        <script type="text/javascript" src="main.js"></script>
    </head>
    
        <div class="menubar">
        <a href="ContactUs.php">Contact Us</a>
        <a href="Pricing.php">Pricing</a>
        <a href="OurPeople.html">Our People</a>
        <a href="index.html">Home</a>
    </div>
    
    <body>
        
        <h1><center>Contact Us</h1>
        <p><center>Feel free to pop in and see us, or complete the form below and we'll be in touch!</p>
        
        <div class="container" id="contactForm">
        <form action="ContactUs.php" method=post>
        
        <p><center>Order Form</p>
        <br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike
                onclick = "orderSummary (this.form)"> <br>
        <input type="checkbox" name="vehicle1" id="n1" onclick="sync()" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        <input type="checkbox" name="vehicle1" value="Bike"> I have a bike<br>
        
        
        <div id = OrderSummary>   
        <h2>Order Summary</h2>
        
        <script>
function sync()
{
  var n1 = document.getElementById('n1');
  var n2 = document.getElementById('n2');
  n2.value = n1.value;
}
</script>
<!--<input type="checkbox" name="n1" id="n1" onclick="sync()">-->
<input type="text" name="n2" id="n2"/>
        
        <textarea rows="" cols=""></textarea>
          <label for="name">Your Name</label>
            <br>
            <input type="text" id="name" name="name">
            <br>
            <label for="emailAddress">Your Email</label>
            <br>
            <input type="text" id="emailAddress" name="emailAddress">
            <br>
<!--             <label for="phoneNumber">Phone Number</label> -->
<!--             <br> -->
<!--             <input type="text" id="phoneNumber" name="phoneNumber"> -->
<!--             <br> -->
<!--             <label for="contactMethod">How should we get in touch?</label> -->
<!--             <br> -->
<!--             <select id="contactMethod" name="contactMethod"> -->
<!--                 <option value="email">Email</option> -->
<!--                 <option value="phone">Phone</option> -->
<!--             </select> -->
            <br>
            <label for="query">Additional Info</label>
            <br>
            <textarea id="query" name="query" placeholder="Write us a message! Just resize the box if you need more space for your message." style="height:200px" style="width:500px"></textarea>
            <br>
            <input type="submit" value="Submit">
            <br>
            <br>
            <br></div>
       
            
            </div>
            
        </form>
    </div>
    </div>
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

        <?php
            $message = file_get_contents("message.txt");
            $array = explode("\n", $message);
        ?>
        
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwOWzhFsW-brtgpLHBRALTUnuJOft8sWU&callback=myMap"></script>
    
    <br>
    <br>
    
    <center><form>
    <input class="button" type="button" value="Home" onclick="window.location.href='index.html'" />
    <input class="button" type="button" value="Our People" onclick="window.location.href='OurPeople.html'" />
    <input class="button" type="button" value="Pricing" onclick="window.location.href='Pricing.php'" />
    <input class="button" type="button" value="Contact Us" onclick="window.location.href='ContactUs.php'" />
    </form>
    
    <br>
    <br>
    <br>
    
    <center><ul class="social-icons">    
        <li><a href="https://www.linkedin.com/in/caitlin-foley-7b0343b1/"><img src='Linkedin.png'/></a></li>
    </ul>
    </body>