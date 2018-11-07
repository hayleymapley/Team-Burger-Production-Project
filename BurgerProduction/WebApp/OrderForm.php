<!DOCTYPE html>
<html>
    <head>
        <meta charset=“utf-8” />
        <link rel=“stylesheet” href=“stylesheet.css” />
        <title>Caitlin | Contact Us</title>
        <script type=“text/javascript” src=“main.js”></script>
    </head>
    
        <div class=“menubar”>
        <a href=“ContactUs.php”>Contact Us</a>
        <a href=“Pricing.php”>Pricing</a>
        <a href=“OurPeople.html”>Our People</a>
        <a href=“index.html”>Home</a>
    </div>
    
    <body>
        
        <h1><center>Contact Us</h1>
        <p><center>Feel free to pop in and see us, or complete the form below and we’ll be in touch!</p>
        
        <div class=“container” id=“contactForm”>
        <form action=“ContactUs.php” method=post>
        
        <p><center>Location</p>
        <div id=“map” style=“width:100%;height:500px”></div>
        </br>
        <p><center>Contact Form</p>
        <br>
            <label for=“name”>Your Name</label>
            <br>
            <input type=“text” id=“name” name=“name”>
            <br>
            <label for=“emailAddress”>Your Email</label>
            <br>
            <input type=“text” id=“emailAddress” name=“emailAddress”>
            <br>
            <label for=“phoneNumber”>Phone Number</label>
            <br>
            <input type=“text” id=“phoneNumber” name=“phoneNumber”>
            <br>
            <label for=“contactMethod”>How should we get in touch?</label>
            <br>
            <select id=“contactMethod” name=“contactMethod”>
                <option value=“email”>Email</option>
                <option value=“phone”>Phone</option>
            </select>
            <br>
            <label for=“query”>Your Query</label>
            <br>
            <textarea id=“query” name=“query” placeholder=“Write us a message! Just resize the box if you need more space for your message.” style=“height:200px” style=“width:500px”></textarea>
            <br>
            <input type=“submit” value=“Submit”>
            <br>
            <br>
            <br>
            
            </div>
            
        </form>
    </div>
    </div>
    </div>
    
        <?php
            if (isset($_POST[‘name’])) {
                $nameValue = $_POST[‘name’] . “\n”;
                $emailAddress = $_POST[‘emailAddress’] . “\n”;
                $phoneNumber = $_POST[‘phoneNumber’] . “\n”;
                $contactMethod = $_POST[‘contactMethod’] . “\n”;
                $query = $_POST[‘query’] . “\n”;
                file_put_contents(“message.txt”, $nameValue . $emailAddress . $phoneNumber . $contactMethod . $query, FILE_APPEND);
                print “<p><center><em>Thanks for submitting your form, we’ll be in touch soon!</em></p>“;
            }
        ?>

        <?php
            $message = file_get_contents(“message.txt”);
            $array = explode(“\n”, $message);
        ?>
        
    <script src=“https://maps.googleapis.com/maps/api/js?key=AIzaSyCwOWzhFsW-brtgpLHBRALTUnuJOft8sWU&callback=myMap“></script>
    
    <br>
    <br>
    
    <center><form>
    <input class=“button” type=“button” value=“Home” onclick=“window.location.href=‘index.html’” />
    <input class=“button” type=“button” value=“Our People” onclick=“window.location.href=‘OurPeople.html’” />
    <input class=“button” type=“button” value=“Pricing” onclick=“window.location.href=‘Pricing.php’” />
    <input class=“button” type=“button” value=“Contact Us” onclick=“window.location.href=‘ContactUs.php’” />
    </form>
    
    <br>
    <br>
    <br>
    
    <center><ul class=“social-icons”>    
        <li><a href=“https://www.linkedin.com/in/caitlin-foley-7b0343b1/“><img src=‘Linkedin.png’/></a></li>
    </ul>
    </body>

</html>