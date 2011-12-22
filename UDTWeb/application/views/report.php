<link rel="stylesheet" type="text/css" href="<?=base_url()?>css/ui-lightness/jquery-ui-1.8.16.custom.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<?=base_url()?>css/report.css"  />		
<script type="text/javascript" src="<?=base_url()?>js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<?=base_url()?>js/jquery-ui-1.8.16.custom.min.js"></script>


<meta charset="utf-8">
	<script>
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


	
	$(document).ready(function() {
		  $('#generate').click(function() {

			  $.ajax({
				  type: 'POST',
				  url: '<?php echo $report_url;?>',
				  data: 'imei='+$("#device").val()+'&from='+$("#from").val()+'&to='+$("#to").val(),
				  dataType:'json',
				  success: function(response){
					   var resp = response;
					   $("#report_table").empty();
					   $('#report_table').append('<tr><th>Timestamp</th><th>Lat</th><th>Lon</th><th>Map</th></tr>');
					    
					  
					   if(resp!=null){
					   for(var i=0;i< resp.length;i++){
					        var obj = resp[i];
					        $('#report_table').append('<tr id="fila'+i+'"><td>'+obj['timestamp']+'</td><td>'+obj['lat']+'</td><td>'+obj['lon']+'</td><td><a target="blank" href="http://maps.google.com/maps/api/staticmap?zoom=16&size=1024x1024&maptype=roadmap&markers=color:blue|'+(obj['lat']/1000000)+','+(obj['lon']/1000000)+'&sensor=false">Ver</a></td></tr>');
					  }
					   }
				  }
				});
			    
		  });
		});
	

</script>  

	
	</script>



<div class="demo">
<div id="title">Generar reporte</div>
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

<div align="center" class="submit"><button id="generate">Generar</button></div>
</div>
<div id="report" style="padding-top: 20px;">
<table id="report_table" style="color:white;" border="1">
  <tr>
    <th>Timestamp</th>
    <th>Lat</th>
    <th>Lon</th>
    <th>Map</th>    
  </tr>
  
</table>
</div>

