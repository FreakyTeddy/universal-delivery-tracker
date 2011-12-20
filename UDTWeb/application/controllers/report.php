<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Report extends CI_Controller
{
	function __construct()
	{
		parent::__construct();
		$this->load->library('tank_auth');
		$this->load->helper('url');
		$this->load->model('track_history_model');
	}

	public function index(){
	
	}
	
	public function report_gen($user_id){
		$params=array();
		$this->abstract_view($params,"report");
	}
	
	public function generate_report($from,$to){
			$from = "2011-12-20";
			$to = "2011-12-21";
			$imei = "354713040937492";
			error_log(print_r($this->track_history_model->get_tracks_by_date($imei,$from,$to),true));
	}
	
	public function abstract_view($params,$view){
    	$params["current_view"] = $view;
        $this->load->view('main',$params);
    }
	
}

/* End of file welcome.php */
/* Location: ./application/controllers/report.php 
 */