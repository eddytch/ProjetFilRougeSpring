var moduleAdherent = angular.module("ModuleAdherent") ;
moduleAdherent.service('ServiceAdherent',['$http','UrlService',function($http,UrlService){
    var self = this ;

    var promise = undefined ;

    this.adherents = [] ;

    this.nbPages = Infinity;

    this.getAdherents = function(object){

        pages = $http.get(UrlService.members,object).then(
                function(response){
                    self.nbPages = response.data['pages']
                }
        );
        promise = $http.get(UrlService.adherent_recherche,object).then(
            function(response){
                self.adherents.splice(0,self.adherents.length)
                for(var index in response.data){
                    var itemFromServeur = response.data[index];
                    var itemForIHM = {
                                    id : itemFromServeur.id,
                                    nom : itemFromServeur.lastname,
                                    prenom : itemFromServeur.firstname,
                                    email : itemFromServeur.email,
                                    date_naissance : itemFromServeur.birthday
//                                    cotisation_correcte : itemFromServeur.cotisation_correcte,
//                                    nombre_media : itemFromServeur.nombre_media
                    };
                    self.adherents.push(itemForIHM);
                }
            }
        );

        return promise ;

    }

    this.addAdherent = function(object){
        promise = $http.post(UrlService.members,object ).then(
            function(response){
                console.log(response) ;
            }

        );
        return promise ;
    }

}]);