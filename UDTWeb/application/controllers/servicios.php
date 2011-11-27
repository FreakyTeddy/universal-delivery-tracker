<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Servicios extends CI_Controller
{
	function __construct()
	{
		parent::__construct();
	
		$this->load->model('servicio_model');
		
	}

	public function index(){
		
	}

	function test(){
		echo $this->servicio_model->list_all();
	}
	
	function get_servicios_contratados(){
		$user_id =  $this->input->post('user_id');
		$servicios = $this->servicio_model->get_servicios_by_userid($user_id);
		echo json_encode($servicios);	
	}
	
	
	
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */