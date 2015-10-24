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

app.controller('SearchPersonController', [ '$scope', 'PersonFactory',
		function($scope, PersonFactory) {
//			$scope.gentes= [ {
//				'name' : 'Marcos Esteban',
//				'surname' : 'Urbaneja Sanchez',
//				'certificate' : '1',
//				'lodgings' : '1',
//				'companions' : '2',
//				'id' : '7'
//			}];

			$scope.obtenerPersonas = function(person){ 
				console.log(person.name);
				console.log(person.surname);
				$scope.gentes= PersonFactory.search({name: person.name, surname: person.surname});
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

app.controller('modifyPersonCtrl', [ '$scope', function($scope) {
	$scope.tablesData = [ {
		'name' : 'Marcos Esteban',
		'surname' : 'Urbaneja Sanchez',
		'certificate' : '1',
		'lodgings' : '1',
		'companions' : '2',
		'id' : '7'
	}, {
		'name' : 'Daniel',
		'surname' : 'Formia',
		'certificate' : '2',
		'lodgings' : '1',
		'companions' : '1',
		'id' : '8'
	}, {
		'name' : 'Ricardo',
		'surname' : 'Jaime',
		'certificate' : '2',
		'lodgings' : '1',
		'companions' : '2',
		'id' : '9'
	} ];

	$scope.editingData = [];
	for (var i = 0, length = $scope.tablesData.length; i < length; i++) {
		$scope.editingData[$scope.tablesData[i].id] = false;
	}
	$scope.modify = function(tableData) {
		$scope.editingData[tableData.id] = true;
	};
	$scope.update = function(tableData) {
		$scope.editingData[tableData.id] = false;
	};

} ]);

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
