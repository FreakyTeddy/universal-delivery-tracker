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
		$this->load->model('ticket_model');
		$this->load->model('track_model');
		$this->load->model('track_history_model');
		$this->load->helper('date');
	}


	function index()
	{
		$params=array();
		$this->abstract_view($params,"tracker_form");
	}
	
	function map_tracker()
	{
	
	error_log(print_r($this->track_model,true));
	
		$params=array();
		$ticket_id = $this->input->post('tracker_number');
		$tracks =null;
		if($ticket_id){
			$trackinfo = $this->ticket_model->get_by_id($ticket_id);
			if($trackinfo){
			$params['descripcion'] = $trackinfo['descripcion'];
			$tracks = $this->track_model->get_tracks_by_ticket($ticket_id);
		
			if(is_array($tracks)){
				$params['json_tracks'] = json_encode($tracks);
				$this->abstract_view($params,"map_tracker");	
			}else{
				$params['error'] = "Ticket Inv\u00e1lido";
				$this->abstract_view($params,"tracker_form");
			}}else {
				$params['error'] = "Ticket no existe o es Privado";
				$this->abstract_view($params,"tracker_form");
			}
		}else{
			$this->abstract_view($params,"tracker_form");
		}
		
	}
	
	public function map_family_tracker(){
		$user_id = null;
		$user_id = $this->tank_auth->get_user_id();
		if ( $user_id ){
			$trackinfo = null;
			$trackinfo = $this->ticket_model->get_family_ticket($user_id);
		}
		$this->abstract_view($trackinfo,"map_family_tracker");
	}
	
	public function ajax_map_family(){
		$id_ticket = null;
		$id_ticket=$this->input->get('id_ticket');
		if ( $id_ticket ){
			$data = null;
			$data = $this->track_model->get_family_tracks($id_ticket);
		}
		echo json_encode($data);
	}
	
	public function abstract_view($params,$view){
    	$params["current_view"] = $view;
        $this->load->view('main',$params);
    }
	
	
	/**Guarda el reporte de un imei*/
	public function track_user_pos(){
		error_log(print_r($_POST,true));
		$imei = $this->input->post('imei');
		$lat  = $this->input->post('lat');
		$lon  = $this->input->post('lon');
		error_log("imei: ".$imei);
		if($imei){    
				$data = array("imei"=>$imei,"lat"=>$lat,"lon"=>$lon,"timestamp"=>date('Y-m-d H:i:s', now()));
				$this->track_history_model->save($data);
				
				$result = $this->track_model->get_by_imei($imei);
				if(count($result)>0){
					unset($data['imei']);
					$this->track_model->update($imei,$data);
					echo "UP";
				}else {
					$this->track_model->save($data);
					echo "SAVE";
				}
		}
	}
	
	
	public function get_ticket_info(){
		$user_id = $this->input->post('user_id');
		$ticket_id = $this->input->post('ticket_id');
		/* Deberia chequear si es un ticket publico, ej casa de empanadas 
		o si se trata de un ticket que rastrea familiares o cosas de valor*/
		$ticket = $this->ticket_model->get_by_id($ticket_id);
		echo json_encode($ticket);
	}
	
	public function get_ticket_coords(){
		$user_id = $this->input->post('user_id');
		$ticket_id = $this->input->post('ticket_id');
		$tracks = $this->track_model->get_tracks_by_ticket($ticket_id);
		echo json_encode($tracks);
	}
	
	
	public function get_family_info(){
		$user_id = $this->input->post('user_id');
		/* Deberia chequear si es un ticket publico, ej casa de empanadas 
		o si se trata de un ticket que rastrea familiares o cosas de valor*/
		$info = $this->ticket_model->get_family_ticket($user_id);
		error_log( json_encode($info));
		echo json_encode($info);
	}
	
	public function get_family_coords(){
		$ticket_id = $this->input->post('ticket_id');
		$tracks = $this->track_model->get_family_tracks($ticket_id);
		$json = "[";
		
		foreach($tracks as $track){
			$json .= "{\"lat\": \"".$track['lat']."\", \"lon\":\"".$track['lon']."\" , \"descripcion\": ".$track['descripcion']."},";
		}
		$array = str_split($json,strlen($json)-1);
		$json = $array[0];
		$json .= "]";
		error_log($json);
		echo $json;
	}
	public function get_tickets_by_client_device(){
		$user_id = $this->input->post('user_id');
		$imei = $this->input->post('imei');
		$tickets = $this->ticket_model->get_tickets_by_client_device($user_id,$imei);
		error_log( json_encode($tickets));
		echo json_encode($tickets);
	}
	
	public function set_ticket_entregado(){
		$user_id = $this->input->post('user_id');
		$imei = $this->input->post('imei');
		$ticket_id = $this->input->post('ticket_id');
		error_log("marcar ticket: ".$ticket_id);
		echo $this->ticket_model->set_ticket_entregado($user_id,$imei,$ticket_id);
	}
	
	
	
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */