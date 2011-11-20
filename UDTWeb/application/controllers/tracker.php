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
		$this->load->model('track_model');
		$this->load->helper('date');
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
	
	public function track_user_pos(){
		error_log(print_r($_POST,true));
		$imei = $this->input->post('imei');
		$lat  = $this->input->post('lat');
		$lon  = $this->input->post('lon');
		error_log("imei: ".$imei);
		if($imei){    
			
			$result = $this->track_model->get_by_imei($imei);
				if(count($result)>0){
				$data = array("lat"=>$lat,"lon"=>$lon,"timestamp"=>date('Y-m-d H:i:s', now()));
					$this->track_model->update($imei,$data);
					echo "UP";
				}else {
				$data = array("imei"=>$imei,"lat"=>$lat,"lon"=>$lon,"timestamp"=>date('Y-m-d H:i:s', now()));
					$this->track_model->save($data);
					echo "SAVE";
				}
		}
	}
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */