<form name="adddevice" action="<?php echo $addurl?>" method="post">
<div id="title">Agregar nuevo Dispositivo</div>
<div class="row">
	<label class="col1">IMEI:</label> 
	<span class="col2"><input class="input" type="text" name="imei" /></span>
</div>	
<div class="row">
	<label class="col1">Nombre Titular:</label>
	<span class="col2"><input class="input" type="text" name="titular" /></span>
</div>
<div class="row">
	<label class="col1">Nombre Portador:</label>
	<span class="col2"><input type="text" name="nombre" class="input" /></span>
</div>
<div class="row">
	<label class="col1">URL Imagen:</label>
	<span class="col2"><input type="text" name="imagen" class="input" /></span>
</div>
<input type="hidden" name="client" value="<?php echo $user;?>" />
<div align="center" class="submit"><input type="submit" value="Agregar" /></div>
</form>
