<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Report extends CI_Controller
{
	function __construct()
	{
		parent::__construct();
		$this->load->library('tank_auth');
		$this->load->helper('url');
		$this->load->model('track_history_model');
		$this->load->model('servicio_model');
	}

	public function index(){
	
	}
	
	public function report_gen($user_id){
		$params=array();
		$params["devices"] = $this->servicio_model->get_devices_by_user($user_id);
		$params["report_url"] = base_url()."index.php/report/generate_report/";
		$this->abstract_view($params,"report");
	}
	
	public function recorrido_gen($user_id){
		$params=array();
		$params["devices"] = $this->servicio_model->get_devices_by_user($user_id);
		$params["report_url"] = base_url()."index.php/report/generate_report/";
		$this->abstract_view($params,"recorrido");
	}
	
	public function generate_report(){
			$from = $this->input->post('from');
			$to = $this->input->post('to');
			$imei = $this->input->post('imei');
			echo json_encode($this->track_history_model->get_tracks_by_date($imei,$from,$to));
	}
	
		
	public function abstract_view($params,$view){
    	$params["current_view"] = $view;
        $this->load->view('main',$params);
    }
	
}

/* End of file welcome.php */
/* Location: ./application/controllers/report.php 
 */