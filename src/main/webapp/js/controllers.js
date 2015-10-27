var app = angular.module('hemoterapia.controllers', [ "hemoterapia.services" ])

app.controller('DefaultPersonController', [ '$scope', 'PersonFactory',
		function($scope, PersonFactory) {
			PersonFactory.query({}, function(personFactory) {
				$scope.name = personFactory.name;
				$scope.surname = personFactory.surname;
				$scope.companions = personFactory.companions;
				$scope.certificate = personFactory.certificate;
			})
		} ]);

app.controller('SavePersonController', [ '$scope', 'PersonFactory',
		function($scope, PersonFactory) {
			$scope.person = {
				companions : 0,
			}
			$scope.savePerson = function(person) {
				PersonFactory.save(person);
			}
		} ]);


// Solo permitimos numeros en aquellos tags html que tengan la palabra
// numericOnly
app.directive('numericOnly', function() {
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
			});
		}
	};
});

app.directive("mySearchResults", function(){
	console.log("Dir - mySearchResults");
	return {
		restrict: "E",
		scope: {
			tablesdata:'=',
			addmodify:'&'
		},
		templateUrl: "partials/search-results-directive.html",
		controller: function ($scope, $attrs){
			$scope.modifyline= function(){
				console.log("Dir - en el controlador invocando a modificar");
				$scope.addmodify();
			}
		}
	};
})

app.directive("myTableResults", function(){
	console.log("Dir - myTableResults");
	return {
		restrict: "E",
		scope:{
			botonmodificar:'&',
			data:'='
		}
	}
})

app.controller('SearchPersonController', [ '$scope', 'PersonFactory',
                                           'searchService', function($scope, PersonFactory, searchService) {
		
	$scope.getPersons = function(person) {
		console.log(person.name);
		console.log(person.surname);
		var result = PersonFactory.search({
			name : person.name,
			surname : person.surname
		});
		searchService.setResults(result);
	};
	$scope.resetForm = function() {
		console.log("Ctrl - Borrando formulaio");
        $scope.person.name = '';  
        $scope.person.surname = '';
    };
    
    $scope.getResultsForTable=function(){
    	console.log ("Ctrl - Obteniendo resultados para la tabla");
    	return searchService.getResults();
    };
    
    $scope.prueba = function(){
    	console.log("Ctrl - ingresando a prueba");
    };   
    
    $scope.setModifyButton= function(id){
    	console.log("Ctrl - Seteando resultados para la tabla");
		$scope.tablesdata = searchService.results;
		
//		$scope.editingData = [];
//		for (var i = 0, length = $scope.tablesdata.length; i < length; i++) {
//			$scope.editingData[$scope.tablesdata[i].id] = false;
//		}
//		$scope.modify = function(tableData) {
//			$scope.editingData[tableData.id] = true;
//		};
//		$scope.update = function(tableData) {
//			$scope.editingData[tableData.id] = false;
//		};
    }
} ]);




//app.controller('modifyPersonCtrl', ['$scope', '$rootScope', 'searchService',
//	function($scope, $rootScope, searchService) {
//		$rootScope.$on('llenartabla', function(result) {
//			console.log("ADENTRO");
//			$scope.tablesData = searchService.results;
//		
//			$scope.editingData = [];
//			for (var i = 0, length = $scope.tablesData.length; i < length; i++) {
//				$scope.editingData[$scope.tablesData[i].id] = false;
//			}
//			$scope.modify = function(tableData) {
//				$scope.editingData[tableData.id] = true;
//			};
//			$scope.update = function(tableData) {
//				$scope.editingData[tableData.id] = false;
//			}
//		});
//} ]);

// app.controller('SavePersonController', ['$scope', 'saveService',
// function($scope, saveService){
// $scope.person= {};
//	
// this.savePerson= function(person){
// saveService.savePerson(person).success(function() {
// alert('saved successfully!!!');
// }).error(function(){
// alert('something went wrong!!!');
// });
// }
// }]);
