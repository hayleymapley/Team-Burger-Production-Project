<!DOCTYPE html>
<html>
    <head>
    
        <meta charset="utf-8" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="stylesheet.css" />
        <title>NullBurger | Order Form</title>
        <script type="text/javascript" src="/Users/Kitty/git/burgermakingproject/BurgerProduction/Scripts/WebApp/OrderForm.js"></script>
     
    </head>
    

    
    <body>
        
        <h2><center>Welcome</h2>
        <p><center>Just fill in the form below and we'll get your burger out to you asap!</p>
        
        <div style='float:left' class="container" id="orderForm">
        <h3><left>Build your burger below!</h3>
        <p>First, choose your bun:</p>
        <input type="checkbox" name="n1" id="n1" onclick="sync()" value="Bun"> Bun<br>
        <input type="checkbox" name="n3" id="n3" onclick="sync2()" value="Lettuce"> Lettuce<br>
        
        <p>Next, select your veges:</p>
        <input type="checkbox" name="n5" id="n5" onclick="sync3()" value="Tomato"> Tomato<br>
        <input type="checkbox" name="n7" id="n7" onclick="sync4()" value="Lettuce"> Lettuce<br>
        <input type="checkbox" name="n9" id="n9" onclick="sync5()" value="Onion"> Onion<br>
        <input type="checkbox" name="n11" id="n11" onclick="sync6()" value="Pickles"> Pickles<br>
        <input type="checkbox" name="n13" id="n13" onclick="sync7()" value="Beetroot"> Beetroot<br>
        
        <p>What type of meat would you like?</p>
        <input type="checkbox" name="n15" id="n15" onclick="sync8()" value="Beef"> Beef Patty<br>
        <input type="checkbox" name="n17" id="n17" onclick="sync9()" value="Chicken"> Chicken Patty<br>
        <input type="checkbox" name="n19" id="n19" onclick="sync10()" value="Vegan"> Vegan Patty<br>
       
       	<p>Now for the cheese:</p>
        <input type="checkbox" name="n21" id="n21" onclick="syn11()" value="Cheddar"> Cheddar<br>
        
        <p>And last - but not least - your sauce:</p>
        <input type="checkbox" name="n23" id="n23" onclick="syn12()" value="Aioli"> Aioli<br>
        
        </div>
        
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
<br>
<div style='float:center' class="textarea" id="summaryForm">
        <h2>Order Summary</h2>
             
<form>
<input type="list" name="n2" id="n2" readonly/><br>
<input type="list" name="n4" id="n4" readonly/><br>

</form>
</div>


        
<<<<<<< HEAD
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
=======
  </body>
>>>>>>> branch 'master' of git@gitlab.ecs.vuw.ac.nz:poperere/burgermakingproject.git
