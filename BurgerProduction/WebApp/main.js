$total = "";


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
        $('#total').html( $total );
    } 
  
}) 

// Jam $1

$('#checkbox4').click(function(){
    if (this.checked) {
      $total += 1;
      $('#total').html( $total );
    }    

}) 