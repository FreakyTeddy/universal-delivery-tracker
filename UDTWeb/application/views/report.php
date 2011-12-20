<link rel="stylesheet" type="text/css" href="<?=base_url()?>css/ui-lightness/jquery-ui-1.8.16.custom.css" media="screen" />	
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


	</script>



<div class="demo">

<label for="from">Desde</label>
<input type="text" id="from" name="from"/>
<label for="to">hasta</label>
<input type="text" id="to" name="to"/>
<button id="generate">Generate!</button>
</div><!-- End demo -->

