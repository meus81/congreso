var hemoApp = angular.module('hemoterapia.controllers', [ "hemoterapia.services" ]);

hemoApp.controller('DefaultPersonController', ['$scope', 'PersonFactory',
	function($scope, PersonFactory) {
		PersonFactory.query({}, function(personFactory) {
			$scope.name = personFactory.name;
			$scope.surname = personFactory.surname;
			$scope.companions = personFactory.companions;
			$scope.certificate = personFactory.certificate;
		})
	} 
]);

hemoApp.controller('SavePersonController', [ '$scope', 'PersonFactory',
	function($scope, PersonFactory) {
		$scope.person = {
			certificate: {idCertificate: 1},
			lodgings: {lodgings_type: 'hemoterapia.domain.WithoutLodgings'},
			companions : 0,
			email: 'me@example.com',
			address:''			
		};
		$scope.email = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
		$scope.savePerson = function(person) {
			PersonFactory.save(person, function(response){
				console.log(response);
				$scope.person = {amount: response.amount};
			});
		};
		$scope.resetForm = function() {			
	        $scope.person= {
        		name: '',
        		surname: '',
        		email: 'me@example.com',
        		address: '',
        		certificate: {idCertificate: 1},
    			lodgings: {lodgings_type: 'hemoterapia.domain.WithoutLodgings'},
    			companions : 0
	        }
	    };
	    	    
	} 
]);

hemoApp.controller('StatisticsController', ['$scope', 'StatisticsFactory',
                                        function($scope, StatisticsFactory) {
	StatisticsFactory.query({}, function(staticsResults){
		$scope.professionalsQty= staticsResults.professionalQTy;
		$scope.professionalWithLodgingsQty= staticsResults.professionalWithLodgingsQty
		$scope.professionalWithoutLodgingsQty= staticsResults.professionalWithoutLodgingsQty
		
		$scope.technicianQty= staticsResults.technicianQTy;
		$scope.technicianWithLodgingsQty= staticsResults.technicianWithLodgingsQty
		$scope.technicianWithoutLodgingsQty= staticsResults.technicianWithoutLodgingsQty
		
		$scope.guestsQty= staticsResults.guestQTy;
		$scope.companionsQty= staticsResults.companionsQty;
		$scope.personsWithLodgingsQty= staticsResults.personsWithLodgingsQty;
		$scope.totalPersons= staticsResults.totalPersons;
		$scope.totalAmount= staticsResults.totalAmount;
	})
}]);

hemoApp.controller('SearchPersonController', [ '$scope', 'PersonFactory', 
                                               function($scope, PersonFactory) {
		
		$scope.$watch('personToSearch', function(personToSearch){
			debugger;
			if (!angular.equals($scope.personToSearch, undefined)){
				if(!angular.equals($scope.personToSearch, {})){
					$scope.personSearchResults = [];
					$scope.isSearchingForAPerson = true;
					PersonFactory.search({
						name : personToSearch.name,
						surname : personToSearch.surname
					}).$promise.then(function (data){
						$scope.isSearchingForAPerson = false;
						for (var i = 0, length = data.length; typeof data[i] != 'undefined'; i++) {    		
							$scope.personSearchResults[i] = data[i];
						}
						debugger;
					});
					$scope.personToSearch={};
				} else {
					$scope.isSearchingForAPerson = false;
					$scope.personSearchResults = [];
				}
			} else {
				$scope.isSearchingForAPerson = false;
				$scope.personSearchResults = [];
			}
		});
	}
]);

hemoApp.controller('OperationInSearchResults', [ '$scope', 'PersonFactory',
                                                 'searchService', function($scope, PersonFactory, searchService) {
	
		$scope.editingData = {};
//		for (var i = 0, length = $scope.searchResults.length; i < length; i++) {
//		      $scope.editingData[$scope.searchResults[i].idPerson] = false;
//		}
		
		$scope.modifyPerson= function(tabledata){
			debugger;
			$scope.editingData[tabledata.idPerson] = true;
		};
		
		$scope.updatePerson = function(tabledata) {
			debugger;
			console.log("Updating person...");
			$scope.editingData[tabledata.idPerson] = false;
			
			PersonFactory.updatePerson({id:tabledata.idPerson, name: tabledata.name,
										surname:tabledata.surname, email: tabledata.email,
										address:tabledata.address, companions: tabledata.companions, 
										certificate:tabledata.certificate,
										lodgings:tabledata.lodgings}, function(response){
				console.log(response);
			});
			
			var indice = 0;
			var keys = Object.keys($scope.searchResults);
			for(var i = keys.length - 1; i >= 0; i--){
				if(tabledata.idPerson == $scope.searchResults[i].idPerson) {
					indice = i;
				}
			};
			tabledata = $scope.searchResults[indice];
		};
		
		$scope.deletePerson = function(tabledata){
			debugger;
			console.log("eliminando de editingData");
			console.log("Los resultados de la tabla: " + $scope.searchResults);
			
			$scope.editingData[tabledata.idPerson] = false;			
			var indice = 0;
			var keys = Object.keys($scope.searchResults);
			for(var i = keys.length - 1; i >= 0; i--){
				if(tabledata.idPerson == $scope.searchResults[i].idPerson) {
					indice = i;
				}
			};
			$scope.searchResults.splice(indice, 1);
			console.log("Despues del borrado: " + $scope.searchResults);
			
  	  	    PersonFactory.deletePerson({id:tabledata.idPerson});
		};
	}
]);

hemoApp.directive("hemoSearchBox", function(){	
	return {
		restrict: 'E',
		transclude: true,
		scope: {
			searchPerson: '=',
			isSearching: '='
		},
		controller: function($scope) {
			$scope.localPerson = {};
			
			$scope.clearSearch = function() {
				$scope.searchPerson = {};
				$scope.localPerson = {};
			};
			
			$scope.doSearch = function() {
				debugger;
				$scope.searchPerson = $scope.localPerson;
			};
		},
		templateUrl:"partials/search-box.html"
	}
});

hemoApp.directive("hemoSearchResults", function(){
	return {
		restrict: "E",
		transclude: true,
		scope: {
			isSearching:'=',
			searchResults: '=',
			searchPerson: '='
		},
		templateUrl:"partials/search-results-directive.html"
	};
});
//		> is not in the documentation.
//		< is for one-way binding.
//		@ binding is for passing strings. These strings support {{}} expressions for interpolated values.
//		= binding is for two-way model binding. The model in parent scope is linked to the model in the directive's isolated scope.
//		& binding is for passing a method into your directive's scope so that it can be called within your directive.


// Solo permitimos numeros en aquellos tags html que tengan la palabra
// numericOnly
hemoApp.directive('numericOnly', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, modelCtrl) {
			modelCtrl.$parsers.push(function(inputValue) {
				var transformedInput = inputValue ? inputValue.replace(
						/[^\d.-]/g, '') : null;
				if (transformedInput != inputValue) {
					modelCtrl.$setViewValue(transformedInput);
					modelCtrl.$render();
				}
				return transformedInput;
			})
		}
	}
});
