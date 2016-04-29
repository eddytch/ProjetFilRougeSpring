// Récupération du module global pour y ajouter le service du Panier

//Récupération du module global pour y ajouter le service du Panier
angular.module('ModuleLogin').service('LoginService', ['$http', '$q','UrlService', function($http, $q,UrlService) {
	
	var ctrl = this;
	
	var connected = false;
		
	// Fonction pour voir si l'user est connecté
	this.isConnected = function(){
		return true;
	}
	
	// Fonction qui permet de se connecter
	this.connect = function(login, mdp){
		
		// Fonction qui permet de dire que l'user est connecté
		return $http.post(UrlService.login, {'login' : login, 'mdp' : mdp}).then(function(response) {
			connected = true;
			return true;
		}, function(response) {
			return false;
		});
	};
	
	// Fonction déconnexion
	this.logout = function() {
		connected = false;
	};
    
}]);