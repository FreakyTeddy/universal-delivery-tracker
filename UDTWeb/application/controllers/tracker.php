<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Tracker extends CI_Controller
{
	function __construct()
	{
		parent::__construct();

		$this->load->helper(array('form','url'));
		$this->load->library('form_validation');
		$this->load->library('tank_auth');
		$this->lang->load('tank_auth');
	}


	function index()
	{
		$params=array();
		$this->abstract_view($params,"tracker_form");
	}
	
	function map_tracker()
	{
		$params=array();
		$number = $this->input->post('tracker_number');
		$params['latitud'] = -34.617528;//TODO HARCODE latitud 
		$params['altitud'] = -58.368022;//TODO HARCODE altitud

		$this->abstract_view($params,"map_tracker");
	}
	
	public function abstract_view($params,$view){
    	$params["current_view"] = $view;
        $this->load->view('main',$params);
    }
    
    
    
    
    
    
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */