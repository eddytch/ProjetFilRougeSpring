angular.module('ModuleMedia').service('MediaService', ['$http','UrlService', function($http,UrlService) {

	var self = this;
	var lignes = [];	
	
	self.getList = function(params){
		return $http.get(UrlService.media_recherche, {params:params}).then(function(response) {
			return response.data;
		});
	}
	
	self.getInfo = function(params){
		return $http.get(UrlService.media_recherche_taille, {params:params}).then(function(response) {
			return response.data;
		});
	}
	
	self.getMedia = function(id){
		return $http.get(UrlService.media_accession, {params:{id:id}}).then(function(response) {
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
