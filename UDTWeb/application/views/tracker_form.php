<?php echo form_open('tracker/map_tracker'); ?>

<div id="title">Ingrese su número de pedido: </div>
<div align="center" class="submit"><input type="text" name="tracker_number" value="" size="35" /></div>
<div align="center" class="submit"><input type="submit" value="Rastrear" /></div>
</form>

 <script type="text/javascript">
		<?php if(isset($error)){ ?>
			alert('<?php echo $error; ?> ');		
		<?php }?>
 </script> 