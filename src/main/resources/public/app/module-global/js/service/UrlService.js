angular.module('ModuleGlobal').service('UrlService', [ function() {
	
	var self = this;
	
	var serveur	= "localhost";
	var port 	= "8080";
	var url 	= "http://" + serveur + ":" + port + "/api/";
	
	self.medias						= url + "medias";	
	self.members					= url + "members";
}]);