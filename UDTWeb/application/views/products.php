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

<div class="contentBox">
	<div class="innerBox">
		<div class="contentTitle">
			Nuestros productos
		</div>
		<div class="contentText">
			<p>Contamos con diferentes packetes de nustros productos de acuerdo a las necesidades del cliente.</p>
			<p> A Continuacion podran observer en el scroller los distintos tipos.</p>
			<p> Si deseas obtener mas informacion acerca de los paquetes podes contactarte con nosotros.<?php echo mailto('info@universaltracker.com', 'Contactenos'); ?>
		</div>
	</div>	
</div>
<div style="margin-left:252px;margin-top:40px;height: 450px;">  
	<!-- "previous page" action --> 
	<a class="prev browse left" style="margin-top:184px;"></a> 
	 
	<!-- root element for scrollable --> 
	<div class="scrollable" id=scroller>   
	   
	   <!-- root element for the items --> 
	   <div class="items"> 
	   
	      <!-- 1-5 --> 
	      <div> 
	         <img src='<?=base_url()?>images/pack_familiar.png' />
	      </div> 
	      
	      <!-- 5-10 --> 
	      <div> 
	         <img src='<?=base_url()?>images/pack_pyme.jpg' /> 
	      </div> 
	      
	      <!-- 10-15 --> 
	      <div> 
	         <img src='<?=base_url()?>images/pack_corp.png' /> 
	      </div> 
	      
	   </div> 
	   
	</div> 
	 
	<!-- "next page" action --> 
	<a class="next browse right" style="margin-top:181px;"></a> 
</div> 

	