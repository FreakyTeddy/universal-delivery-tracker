<?php
if ($use_username) {
	$username = array(
		'name'	=> 'username',
		'id'	=> 'username',
		'value' => set_value('username'),
		'maxlength'	=> $this->config->item('username_max_length', 'tank_auth'),
		'size'	=> 20,
	);
}
$email = array(
	'name'	=> 'email',
	'id'	=> 'email',
	'value'	=> set_value('email'),
	'maxlength'	=> 80,
	'size'	=> 20,
);
$password = array(
	'name'	=> 'password',
	'id'	=> 'password',
	'value' => set_value('password'),
	'maxlength'	=> $this->config->item('password_max_length', 'tank_auth'),
	'size'	=> 20,
);
$confirm_password = array(
	'name'	=> 'confirm_password',
	'id'	=> 'confirm_password',
	'value' => set_value('confirm_password'),
	'maxlength'	=> $this->config->item('password_max_length', 'tank_auth'),
	'size'	=> 20,
);

?>
<?php echo form_open($this->uri->uri_string()); ?>
<div id="title">Registrarse</div> 
	<?php if ($use_username) { ?>
	<div class="row"><?php echo form_label('Usuario:', $username['id'],$attributes = array('class' => 'col1')); ?>
		<span class="col2"><?php echo form_input($username,$attributes = array('class' => 'input')); ?></span>
		<div style="color: red;"><?php echo form_error($username['name']); ?><?php echo isset($errors[$username['name']])?$errors[$username['name']]:''; ?></div>
	</div>
	<?php } ?>
	<div class="row"><?php echo form_label('Email:', $email['id'],$attributes = array('class' => 'col1')); ?>
		<span class="col2"><?php echo form_input($email,$attributes = array('class' => 'input')); ?></span>
		<div style="color: red;"><?php echo form_error($email['name']); ?><?php echo isset($errors[$email['name']])?$errors[$email['name']]:''; ?></div>
	</div>
	<div class="row"><?php echo form_label('Password:', $password['id'],$attributes = array('class' => 'col1')); ?>
		<span class="col2"><?php echo form_password($password,$attributes = array('class' => 'input')); ?></span>
		<div style="color: red;"><?php echo form_error($password['name']); ?></div>
	</div>
	<div class="row"><?php echo form_label('Confirm Password:', $confirm_password['id'],$attributes = array('class' => 'col1')); ?>
		<span class="col2"><?php echo form_password($confirm_password,$attributes = array('class' => 'input')); ?></span>
		<div style="color: red;"><?php echo form_error($confirm_password['name']); ?></div>
	</div>

<div align="center" class="submit"><?php echo form_submit('register', 'Registrarse'); ?></div>
<?php echo form_close(); ?>