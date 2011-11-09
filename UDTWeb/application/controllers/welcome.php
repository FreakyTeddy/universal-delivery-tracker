<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Welcome extends CI_Controller
{
	function __construct()
	{
		parent::__construct();

		$this->load->helper('url');
		$this->load->library('tank_auth');
	}


	function index()
	{
		$params=array();
		$this->abstract_view($params,"home");
		
		/*if (!$this->tank_auth->is_logged_in()) {
			redirect('/auth/login/');
		} else {
			$data['user_id']	= $this->tank_auth->get_user_id();
			$data['username']	= $this->tank_auth->get_username();
			$this->load->view('welcome', $data);
		}*/
	}
	
	public function abstract_view($params,$view){
    	$params["current_view"] = $view;
        $this->load->view('main',$params);
    }
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */