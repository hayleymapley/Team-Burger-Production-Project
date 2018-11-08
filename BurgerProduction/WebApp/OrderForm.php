<!DOCTYPE html>
<html>
    <head>
    
        <meta charset="utf-8" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
        
        <div style='float:left' class="container" id="contactForm">
        <form action="ContactUs.php" method=post>
        
       
        <p><left>Order Form</p>
        <br>
        <p>Bun</p>
        <input type="checkbox" name="n1" id="n1" onclick="sync()" value="Bun"> Bun<br>
        <input type="checkbox" name="n3" id="n3" onclick="sync2()" value="Lettuce"> Lettuce<br>
        <p>Vegetable Fillings</p>
        <input type="checkbox" name="n5" id="n5" onclick="sync3()" value="Tomato"> Tomato<br>
        <input type="checkbox" name="n7" id="n7" onclick="sync4()" value="Lettuce"> Lettuce<br>
        <input type="checkbox" name="n9" id="n9" onclick="sync5()" value="Onion"> Onion<br>
        <input type="checkbox" name="n11" id="n11" onclick="sync6()" value="Pickles"> Pickles<br>
        <input type="checkbox" name="n13" id="n13" onclick="sync7()" value="Beetroot"> Beetroot<br>
       	<p>Cheese</p>
       
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
        
        </div>
        <div style='float:center' class="textarea" id="summaryForm">
        <h2>Order Summary</h2>
        
        <script>
function sync()
{
  var n1 = document.getElementById('n1');
  var n2 = document.getElementById('n2');
  n2.value = n1.value;
  }
  
  function sync2()
{
  var n3 = document.getElementById('n3');
  var n4 = document.getElementById('n4');
  n4.value = n3.value;
 
 }
 
   function sync3()
{
 
  var n5 = document.getElementById('n5');
  var n6 = document.getElementById('n6');
  n6.value = n5.value;
 
 }
</script>


<form>
<input type="list" name="n2" id="n2" readonly/><br>
<input type="list" name="n4" id="n4" readonly/><br>

</form>
</div>
        
    <!--    <textarea rows="" cols=""></textarea>
   <!--       <label for="name">Your Name</label>
   <!--         <br>
   <!--         <input type="text" id="name" name="name">
   <!--         <br>
  <!--          <label for="emailAddress">Your Email</label>
  <!--          <br>
 <!--           <input type="text" id="emailAddress" name="emailAddress">
  <!--         <br>
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
  <!--          <br>
  <!--          <label for="query">Additional Info</label>
   <!--         <br>
    <!--        <textarea id="query" name="query" placeholder="Write us a message! Just resize the box if you need more space for your message." style="height:200px" style="width:500px"></textarea>
            <br>
    <!--        <input type="submit" value="Submit">
     <!--       <br>
     <!--       <br>
      <!--      <br></div>
       
            
           <!-- </div>
            
      <!--  </form>
   <!-- </div>
  <!--  </div>
   <!-- </div>
    
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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>