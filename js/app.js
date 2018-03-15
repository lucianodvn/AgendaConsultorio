var permissao = false;

var app = angular.module('consultorio', [ 'ngRoute', 'ngResource', 'ngAnimate']);

app.config(function($routeProvider) {
	
	        // -------------Médico--------------

			$routeProvider.when("/medicolist", {
				controller : "medicoController",
				templateUrl : "medico/list.html"
			})// listar
			
			.when("/medicoedit/:id", {
				controller : "medicoController",
				templateUrl : "medico/cadastro.html"
			})// editar
			
			.when("/medico/cadastro", {
				controller : "medicoController",
				templateUrl : "medico/cadastro.html"
			})// novo
			
			// -------------Consultas--------------
			$routeProvider.when("/consultalist", {
				controller : "consultaController",
				templateUrl : "consulta/list.html"
			})// listar
			
			.when("/consultaedit/:id", {
				controller : "consultaController",
				templateUrl : "consulta/cadastro.html"
			})// editar
			
			.when("/consulta/cadastro", {
				controller : "consultaController",
				templateUrl : "consulta/cadastro.html"
			})// novo
			
			
			// --------------Consultorio---------------------
				$routeProvider.when("/consultoriolist", {
				controller : "consultorioController",
				templateUrl : "consultorio/list.html"
			})// listar
			
			.when("/consultorioedit/:id", {
				controller : "consultorioController",
				templateUrl : "consultorio/cadastro.html"
			})// editar
			
			.when("/consultorio/cadastro", {
				controller : "consultorioController",
				templateUrl : "consultorio/cadastro.html"
			})// novo
			

			.otherwise({
				redirectTo : "/"
			});
			
			
});

app.controller('medicoController', function($scope, $http, $location, $routeParams) {
	
	// editar medico
	if($routeParams.id != null && $routeParams.id != undefined && $routeParams.id != ''){
		$http.get("medico/buscarMedico/" + $routeParams.id).success(function(response){
			$scope.medico = response;
			
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});
		
	}else { // novo medico
		$scope.medico = {};
	}
	
	
	$scope.editarMedico = function(id) {
		$location.path('medicoedit/' + id);
	};
	
// salvar medico ou editar
	
	$scope.medico = {};
	$scope.salvarMedico = function(){
	
		$http.post("medico/salvar", $scope.medico).success(function(response) {
			$scope.medico = {};
						
			sucesso("Salvo com sucesso!");
		}).error(function(response) {
			erro("Erro:" + response);
		});
	};
	
	$scope.proximo = function () {
		if(new Number($scope.numeroPagina) < new Number($scope.totalPagina)){
		$scope.listarMedicos(new Number($scope.numeroPagina + 1));
			}
		};
	
	$scope.anterior = function () {
		if(new Number($scope.numeroPagina) > 1){
		$scope.listarMedicos(new Number($scope.numeroPagina - 1));
		}
	};
	
	
	// listar todos os medicos
	$scope.listarMedicos = function(numeroPagina) {
		$scope.numeroPagina = numeroPagina;// listar por pagina
		$http.get("medico/listar/" + numeroPagina).success(function(response) {

			$scope.data = response;
			
		$http.get("medico/totalPagina").success(function(response){
			
				$scope.totalPagina = response;
		}).error(function(response) {
			erro("Error: " + response);
		});
			
		}).error(function(response) {
			erro("Erro:" + response);
		});
		
	};
	
	$scope.removerMedico = function(codMedico){
		$http.delete("medico/deletar/" + codMedico).success(function(response){
			$scope.listarMedicos($scope.numeroPagina);
			sucesso("Removido com sucesso!");
		}).error(function(data, status, headers, config) {
			erro("Erro:" + status);
		});
	};
});
	
	
// configurações consultaController
app.controller('consultaController', function($scope, $http, $location, $routeParams) {
	
	// editar consulta
	if($routeParams.id != null && $routeParams.id != undefined && $routeParams.id != ''){
		$http.get("consulta/buscarConsulta/" + $routeParams.id).success(function(response){
			$scope.consulta = response;
	
			$http.get("medico/listartodos").success(function(response) {
				$scope.medicoList = response;
				setTimeout(function () {
					$("#selectMedico").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.consulta.medico.id));
				}, 1000);
		
				
				$http.get("consultorio/listartodos").success(function(response) {
					$scope.consultorioList = response;
					setTimeout(function () {
						$("#selectConsultorio").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.consulta.consultorio.id));
					}, 1000);
					
			}).error(function(data, status, headers, config) {
				erro("Error: " + status);
			});
				
			}).error(function(data, status, headers, config) {
				erro("Error: " + status);
			});
		
		
	}).error(function(data, status, headers, config) {
		erro("Error: " + status);
	});
		
	}else { // novo 
		$scope.consulta = {};
	}

	
	$scope.editarConsulta = function(id) {
		$location.path('consultaedit/' + id);
	};
	
// salvar consulta ou editar
	
	$scope.consulta = {};
	$scope.salvarConsulta = function(){
		
				
		$http.post("consulta/salvar", $scope.consulta).success(function(response) {
			$scope.consulta = {};
						
			sucesso("Salvo com sucesso!");
		}).error(function(response) {
			erro("Erro:" + response);
		});
	};
	
	$scope.proximo = function () {
		if(new Number($scope.numeroPagina) < new Number($scope.totalPagina)){
		$scope.listarConsulta(new Number($scope.numeroPagina + 1));
			}
		};
	
	$scope.anterior = function () {
		if(new Number($scope.numeroPagina) > 1){
		$scope.listarConsulta(new Number($scope.numeroPagina - 1));
		}
	};
	
	
	// listar todos as consultas
	$scope.listarConsultas = function(numeroPagina) {
		$scope.numeroPagina = numeroPagina;// listar por pagina
		$http.get("consulta/listar/" + numeroPagina).success(function(response) {

			$scope.data = response;
			
		$http.get("consulta/totalPagina").success(function(response){
			
				$scope.totalPagina = response;
		}).error(function(response) {
			erro("Error: " + response);
		});
			
		}).error(function(response) {
			erro("Erro:" + response);
		});
		
	};
	
	$scope.removerConsulta = function(codConsulta){
		$http.delete("consulta/deletar/" + codConsulta).success(function(response){
			$scope.listarConsulta($scope.numeroPagina);
			sucesso("Removido com sucesso!");
		}).error(function(data, status, headers, config) {
			erro("Erro:" + status);
		});
	};
	
	
	
	$scope.listarMedicos = function() {
		$http.get("medico/listartodos").success(function(response) {
			$scope.medicoList = response;
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});
	};
	
	$scope.listarConsultorios = function() {
		$http.get("consultorio/listartodos").success(function(response) {
			$scope.consultorioList = response;
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});
	};
	
	
});

app.controller('consultorioController', function($scope, $http, $location, $routeParams) {
	
	// editar consultorio
	if($routeParams.id != null && $routeParams.id != undefined && $routeParams.id != ''){
		$http.get("consultorio/buscarConsultorio/" + $routeParams.id).success(function(response){
			$scope.consultorio = response;
			
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});
		
	}else { // novo consultorio
		$scope.consultorio = {};
	}
	
	
	$scope.editarConsultorio = function(id) {
		$location.path('consultorioedit/' + id);
	};
	
// salvar consultorio ou editar
	
	$scope.consultorio = {};
	$scope.salvarConsultorio = function(){
	
		$http.post("consultorio/salvar", $scope.consultorio).success(function(response) {
			$scope.consultorio = {};
						
			sucesso("Salvo com sucesso!");
		}).error(function(response) {
			erro("Erro:" + response);
		});
	};
	
	$scope.proximo = function () {
		if(new Number($scope.numeroPagina) < new Number($scope.totalPagina)){
		$scope.listarConsultorio(new Number($scope.numeroPagina + 1));
			}
		};
	
	$scope.anterior = function () {
		if(new Number($scope.numeroPagina) > 1){
		$scope.listarConsultorio(new Number($scope.numeroPagina - 1));
		}
	};
	
	
	// listar todos os medicos
	$scope.listarConsultorio = function(numeroPagina) {
		$scope.numeroPagina = numeroPagina;// listar por pagina
		$http.get("consultorio/listar/" + numeroPagina).success(function(response) {

			$scope.data = response;
			
		$http.get("consultorio/totalPagina").success(function(response){
			
				$scope.totalPagina = response;
		}).error(function(response) {
			erro("Error: " + response);
		});
			
		}).error(function(response) {
			erro("Erro:" + response);
		});
		
	};
	
	$scope.removerConsultorio = function(codConsultorio){
		$http.delete("consultorio/deletar/" + codConsultorio).success(function(response){
			$scope.listarConsultorio($scope.numeroPagina);
			sucesso("Removido com sucesso!");
		}).error(function(data, status, headers, config) {
			erro("Erro:" + status);
		});
	};
});
	
	


function sucesso(msg){
    	$.notify({
        	message: msg

        },{
            type: 'success',
            timer: 1000
        });
}

function erro(msg){
	$.notify({
    	message: msg

    },{
        type: 'danger',
        timer: 1000
    });
}
function buscarKeyJson(obj, key, value)
{
    for (var i = 0; i < obj.length; i++) {
        if (obj[i][key] == value) {
            return i + 2;
        }
    }
    return null;
}


// identificar navegador
function identific_nav(){
    var nav = navigator.userAgent.toLowerCase();
    if(nav.indexOf("msie") != -1){
       return browser = "msie";
    }else if(nav.indexOf("opera") != -1){
    	return browser = "opera";
    }else if(nav.indexOf("mozilla") != -1){
        if(nav.indexOf("firefox") != -1){
        	return  browser = "firefox";
        }else if(nav.indexOf("firefox") != -1){
        	return browser = "mozilla";
        }else if(nav.indexOf("chrome") != -1){
        	return browser = "chrome";
        }
    }else{
    	alert("Navegador desconhecido!");
    }
}

function visualizarImg() {
	 var preview = document.querySelectorAll('img').item(1);
	  var file    = document.querySelector('input[type=file]').files[0];
	  var reader  = new FileReader();

	  reader.onloadend = function () {
	    preview.src = reader.result;// carrega em base64 a img
	  };

	  if (file) {
	    reader.readAsDataURL(file);		    
	  } else {
	    preview.src = "";
	  }
	  
}

