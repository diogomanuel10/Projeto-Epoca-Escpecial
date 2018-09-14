$(document).ready(function () {
    $('#videoModal').modal('show');
    $("#registo").button().on("click", function () {
    $('#registoModal').modal('show');
    });
   
			$(".navbar a").on('click',function(event){
				console.debug('entrei');
				if(this.hash !== ""){
					event.preventDefault();
					var hash=this.hash
					$('html,body').animate({scrollTop:$(hash).offset().top},900,function(){
						window.location.hash=hash;
					})
				}	
			}); 
});
