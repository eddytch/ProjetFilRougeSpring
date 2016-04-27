angular.module('ModuleMedia').service('MediaService', ['$http', function($http) {

	var self = this;
	var lignes = [];
	var url = "http://localhost:8080/api/medias";
	var urlUnMedia = url +".accession";
	var urlRecherche = url + ".recherche";
	var urlInfo = urlRecherche + ".taille";
	
	
	self.getList = function(params){
		return $http.get(urlRecherche, {params:params}).then(function(response) {
			return response.data;
		});
	}
	
	self.getInfo = function(params){
		return $http.get(urlInfo, {params:params}).then(function(response) {
			return response.data;
		});
	}
	
	self.getMedia = function(id){
		return $http.get(urlUnMedia, {params:{id:id}}).then(function(response) {
			return response.data;
		});		
	}

	// Fonction pour ajouter un media
	self.add = function(media) {	

		var index = lignes.findIndex(function(lignes) {
			return lignes.media.id == media.id;
		});
		
		if (index != -1) {
			lignes.push({
				media : media
			});
		} 
	};

}]);
