<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Empanadas extends CI_Controller
{
	function __construct()
	{
		parent::__construct();
		$this->load->helper( 'url');
		$this->load->model('servicio_model');
		$this->load->model('device_model');
		$this->load->model('ticket_model');
		$this->load->library('tank_auth');
	}

	public function index(){
		$this->load->view('empanadas_view',array());
	}

	function test(){
		echo $this->servicio_model->list_all();
	}
	
	function crear_ticket(){
		$params = array();
		$params["add_url"] = base_url()."index.php/empanadas/add_empanadas";
		$params["devices"]  = $this->device_model->list_all();
		$this->abstract_view($params,'crear_empanadas');
	}
	
	function listar_tickets(){
		$params = array();
		$params["pedidos"] = $this->ticket_model->get_listado_empanadas();	
		$this->abstract_view($params,'listar_empanadas');
	}
	
	function add_empanadas(){
		$imei = $this->input->post('imei');
		$descripcion = $this->input->post('descripcion');
		$data = array("descripcion"=>$descripcion,"tipo"=>1,"estado"=>1);
		echo $this->ticket_model->save_ticket($data,$imei);
	}
	
	public function abstract_view($params,$view){
    	$params["current_view"] = $view;
        $this->load->view('empanadas_view',$params);
    }
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */