<?php echo form_open('tracker/map_tracker'); ?>

<h5>Ingrese su número de pedido: </h5>
<input type="text" name="tracker_number" value="" size="50" />
<div><input type="submit" value="Submit" /></div>
</form>

 <script type="text/javascript">
		<?php if(isset($error)){ ?>
			alert('<?php echo $error; ?> ');		
		<?php }?>
 </script> 