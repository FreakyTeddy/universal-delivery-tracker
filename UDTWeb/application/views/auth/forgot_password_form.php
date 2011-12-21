<?php
$login = array(
	'name'	=> 'login',
	'id'	=> 'login',
	'value' => set_value('login'),
	'maxlength'	=> 80,
	'size'	=> 20,
);
if ($this->config->item('use_username', 'tank_auth')) {
	$login_label = 'Email o login: ';
} else {
	$login_label = 'Email';
}
?>
<?php echo form_open($this->uri->uri_string()); ?>
<div id="title">Solicitar nuevo password</div>
	<div class="row"><?php echo form_label($login_label, $login['id'],$attributes = array('class' => 'col1')); ?>
		<span class="col2"><?php echo form_input($login,$attributes = array('class' => 'input'));  ?></span>
		<div style="color: red;"><?php echo form_error($login['name']); ?><?php echo isset($errors[$login['name']])?$errors[$login['name']]:''; ?></div>	
	</div>
<div align="center" class="submit"><?php echo form_submit('reset', 'Obtener el nuevo password'); ?></div>
<?php echo form_close(); ?>