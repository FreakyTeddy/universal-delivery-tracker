<form name="adddevice" action="<?php echo $addurl?>" method="post">
IMEI: <input type="text" name="imei" /></br>
Nombre Titular: <input type="text" name="titular" /></br>
Nombre Portador: <input type="text" name="nombre" /></br>
URL Imagen: <input type="text" name="imagen" /></br>
<input type="hidden" name="client" value="<?php echo $user;?>" />
<input type="submit" value="Agregar" />
</form>