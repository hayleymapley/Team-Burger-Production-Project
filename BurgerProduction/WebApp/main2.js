$total = "1";


 // Banana $4

$('#checkbox1').click(function(){
    if (this.checked) {
        $total += 4;
        $('#total').html( $total );
    } 
  
}) 

// Apple $2

$('#checkbox2').click(function(){
    if (this.checked) {
      $total += 2;
      $('#total').html( $total );
    }    


}) 
    
// Biscuit $11

$('#checkbox3').click(function(){
    if (this.checked) {
        $total += 11;
        $('#total1').html( $total );
    } 
  
}) 

// Jam $1

$('#checkbox4').click(function(){
    if (this.checked) {
      $total += 1;
      $('#total').html( $total );
    }    

}) 

document.getElementById("total1").innerHTML = 5 + 6;

function myFunction() {
	  // Get the checkbox
	  var checkBox = document.getElementById("bun");
	  // Get the output text
	  var text = document.getElementById("bun1");
	  
	  var total = document.getElementById("total1")

	  // If the checkbox is checked, display the output text
	  if (checkBox.checked == true){
	    text.style.display = "block";
	    total.innerHTML = 1;
	  } else {
	    text.style.display = "none";
	  }
	}