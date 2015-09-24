var app = angular.module('hemoterapia.controllers', ["hemoterapia.services"]);

app.controller('DefaultPersonController', ['$scope', 'PersonFactory', function ($scope, PersonFactory) {
	 PersonFactory.query({}, function (personFactory) {
       $scope.name = personFactory.name;
       $scope.surname = personFactory.surname;
   })
}]);


app.controller('SavePersonController', ['$scope', 'PersonFactory', function($scope, PersonFactory){
	$scope.savePerson = function(person){
		PersonFactory.save(person);
	}
}]);

//app.controller('SavePersonController', ['$scope', 'saveService', function($scope, saveService){
//	$scope.person= {};
//	
//	this.savePerson= function(person){
//		 saveService.savePerson(person).success(function() { 
//             alert('saved successfully!!!'); 
//         }).error(function(){
//             alert('something went wrong!!!');
//         });
//	}
//}]);