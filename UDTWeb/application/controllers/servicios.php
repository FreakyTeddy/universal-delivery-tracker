<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Servicios extends CI_Controller
{
	function __construct()
	{
		parent::__construct();
		$this->load->helper( 'url');
		$this->load->model('servicio_model');
		$this->load->model('device_model');
		$this->load->library('tank_auth');
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
	
	function add_device_form($user_id){
		$params = array();
		$params["user"] = $user_id;
		$params["addurl"] = base_url()."index.php/servicios/add_device";
		
		$this->abstract_view($params,"newdevice");
	}
	
	function add_device(){
		$imei = $this->input->post('imei');
		$titular = $this->input->post('titular');
		$nombre = $this->input->post('nombre');
		$imagen = $this->input->post('imagen');
		$user =  $this->input->post('client');
		
		$params = array();
		
		if(isset($imei) && isset($titular) && isset($nombre) && isset($imagen) && isset($user)){
		$data = array("imei"=>$imei,"id_cliente"=>$user,"titular"=>$titular,"descripcion"=>"{\"nombre\":\"".$nombre."\",\"img\":\"".$imagen ."\"}" );
			$result = $this->device_model->get_by_imei($imei);
			if(count($result)>0){
				$params["error"] = "Imei Duplicada";
			}else {
				if($this->device_model->save($data)){
					$params["error"] = "Se agrego un nuevo dispositivo";
				
				}else{
					$params["error"] = "Error al agregar el nuevo dispositivo";
				}
			}
		}else{
			$params["error"] = "Datos incorrectos";
		}
		
		$params['user_id'] = $this->tank_auth->get_user_id();
		$params["devices"] = $this->servicio_model->get_devices_by_user($params['user_id']);
		$params['pack'] = $this->servicio_model->get_pack_contratado_by_userid($params['user_id']);
		$params['services'] = $this->servicio_model->get_servicios_full_by_userid($params['user_id']);
		$params['reportlink'] = base_url()."index.php/report/report_gen/".$this->tank_auth->get_user_id();
		$params['recorridolink'] = base_url()."index.php/report/recorrido_gen/".$this->tank_auth->get_user_id();
		$params['addlink'] = base_url()."index.php/servicios/add_device_form/".$this->tank_auth->get_user_id();
		
		$this->abstract_view($params,"dashboard");
	}
	
	
	public function abstract_view($params,$view){
    	$params["current_view"] = $view;
        $this->load->view('main',$params);
    }
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */