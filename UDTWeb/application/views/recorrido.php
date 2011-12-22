 <link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<?=base_url()?>css/ui-lightness/jquery-ui-1.8.16.custom.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<?=base_url()?>css/report.css"  />			
<script type="text/javascript" src="<?=base_url()?>js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<?=base_url()?>js/jquery-ui-1.8.16.custom.min.js"></script>


<meta charset="utf-8">
	<script type="text/javascript">
	$(function() {
		var dates = $( "#from, #to" ).datepicker({
			maxDate: "+1D" ,
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			numberOfMonths: 2,
			onSelect: function( selectedDate ) {
				var option = this.id == "from" ? "minDate" : "maxDate",
					instance = $( this ).data( "datepicker" ),
					date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date );

			}
		});
	});

	 var resp = null;

	function showRecorrido() {
		
			  if(resp!=null){
					var mapOptions = {
							zoom: 13,
							mapTypeId: google.maps.MapTypeId.ROADMAP
						};
					var map = new google.maps.Map(document.getElementById("map_canvas"),
					           mapOptions);
				map.setCenter(new google.maps.LatLng(resp[0]['lat']/1000000,resp[0]['lon']/1000000),5);

				var coordinates = [];
				  for(var i=0;i< resp.length -1;i++){
				        var obj = resp[i];
				        coordinates.push( new google.maps.LatLng(resp[i]['lat']/1000000,resp[i]['lon']/1000000));
				  }
				  var path = new google.maps.Polyline({
                      path: coordinates,
                      strokeColor: "#FF0000",
                      strokeOpacity: 1.0,
                      strokeWeight: 2
                    });

                    path.setMap(map);

	
		}
	} 
	
	$(document).ready(function() {
		  $('#generate').click(function() {
			  $.ajax({
				  type: 'POST',
				  url: '<?php echo $report_url;?>',
				  data: 'imei='+$("#device").val()+'&from='+$("#from").val()+'&to='+$("#to").val(),
				  dataType:'json',
				  success: function(response){
					  resp = response;
					  $("#map_canvas").empty();
					  showRecorrido();
					   }
				  });
			    
		  });
		});
	

</script>  

	


<div class="demo">
<div id="title">Visualizar Recorrido</div>
<div class="row">
	<label class="col1">Dispositivo: </label>

	<span class="col2">	
		<select id="device" class="input">
		<?php 
		foreach ( $devices as $device){
			echo "<option class='input' value=\"".$device["imei"]."\" >".$device["imei"]."</option>";
		}
		?>
		</select>
	</span>
</div>	
<div class="row">	
	<label class="col1" for="from">Desde:</label>
	<span class="col2"><input class="input" type="text" id="from" name="from"/></span>
</div>
<div class="row">	
	<label class="col1" for="to">Hasta:</label>
	<span class="col2"><input class="input" type="text" id="to" name="to"/></span>
</div>	

<div align="center" class="submit"><button id="generate">Recorrido</button></div>
</div>

<div id="map_canvas" style="height: 500px;width:500px;"></div>



