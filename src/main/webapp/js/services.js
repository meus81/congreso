var services = angular.module('hemoterapia.services', ['ngResource']);

services.factory('PersonFactory', function ($resource) {
    return $resource('/congreso/rest/person', {}, {
        query: {
            method: 'GET',
            params: {},
            url: '/congreso/rest/default_person',
            isArray: false
        },
    	save: {
	        method: 'POST'
	    }
    })
});


//services.service('saveService',['$http', function($http){
//	  // expose a saveRecipe function from your service
//	  // that takes a recipe object
//	  this.savePerson = function(person){
//	      // return a Promise object so that the caller can handle success/failure
//	      return $http({ method: 'POST', url: '/CongresoHemoterapia/rest/save_person', data: person});
//	  }
//}]);
