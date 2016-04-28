angular.module('ModuleMedia').service('MediaService', ['$http','UrlService', function($http,UrlService) {

	var self = this;
	var lignes = [];	
	
	self.getList = function(params){
		return $http.get(UrlService.medias, {params:params}).then(function(response) {
			return response.data;
		});
	}
	
	self.getInfo = function(params){
		// TODO Url à complèter
		return $http.get(UrlService.medias, {params:params}).then(function(response) {
			return response.data;
		});
	}
	
	self.getMedia = function(id){
		return $http.get(UrlService.medias+'/'+id).then(function(response) {
			return response.data;
		});		
	}
	
	// Fonction pour ajouter un media
	self.addMedia = function(media) {
		// Ajout du média en BD
		$http.post(UrlService.medias, media).then(function() {
			console.info('Données sauvegardées');
			
			// Ajout du média dans le tableau
			var index = lignes.findIndex(function(lignes) {
				return lignes.media.id == media.id;
			});
			
			if (index != -1) {
				lignes.push({media : media});
			} 
			
		}), function() {
			console.warn('Erreur dans la sauvegarde ...');
		}
	}

}]);
