<link rel="stylesheet" type="text/css" href='<?=base_url()?>css/scrollable-horizontal.css' /> 
<link rel="stylesheet" type="text/css" href="http://static.flowplayer.org/tools/css/scrollable-buttons.css" />

	<!-- javascript coding --> 
	<script> 
		// What is $(document).ready ? See: http://flowplayer.org/tools/documentation/basics.html#document_ready
		$(document).ready(function() {
		 
		// initialize scrollable together with the autoscroll plugin
		var root = $("#scroller").scrollable({circular: true}).autoscroll({ autoplay: false });
		 
		// provide scrollable API for the action buttons
		window.api = root.data("scrollable");
		 
			
		});
	</script>



<div style="margin-left:252px;margin-top:40px;height: 450px;">  
	<!-- "previous page" action --> 
	<a class="prev browse left" style="margin-top:184px;"></a> 
	 
	<!-- root element for scrollable --> 
	<div class="scrollable" id=scroller>   
	   
	   <!-- root element for the items --> 
	   <div class="items"> 
	   
	      <!-- 1-5 --> 
	      <div> 
	         <img src='<?=base_url()?>images/img1.jpg' />
	      </div> 
	      
	      <!-- 5-10 --> 
	      <div> 
	         <img src='<?=base_url()?>images/img2.jpg' /> 
	      </div> 
	      
	      <!-- 10-15 --> 
	      <div> 
	         <img src='<?=base_url()?>images/img3.png' /> 
	      </div> 
	      
	   </div> 
	   
	</div> 
	 
	<!-- "next page" action --> 
	<a class="next browse right" style="margin-top:181px;"></a> 
</div> 

	