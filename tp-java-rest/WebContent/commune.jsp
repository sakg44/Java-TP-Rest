<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="angular.min.js"></script>

<%@ include file="menu.jsp"%>
</head>
<body data-ng-app="monApp">

	<div class="row container">
		<div class="col-lg-6 col-md-6  col-xs-6 col-sm-6">

			<h4>Creation</h4>
			<form class="form-inline" action="rest/commune/create" method="post">
				<div class="form-group">
					<label for="textarea">Nom de la commune :</label> <input
						type="text" name="commune" />
				</div>
				<button type="submit" class="btn btn-success">Créer commune</button>
			</form>
		</div>
		<div class="col-lg-6 col-md-6  col-xs-6 col-sm-6">
			<h4>Modification</h4>
			<form action="rest/commune/modifie" method="post">
				<div class="form-group">
					<label for="textarea">Nouveau nom :</label> <input type="text"
						name="newCommune" /> <label for="textarea">Id :</label> <input
						type="text" name="id" />

					<button type="submit" class="btn btn-warning">Update
						commune</button>
				</div>
			</form>
		</div>
	</div>
	<div class="row container">
		<div class="col-lg-6 col-md-6  col-xs-6 col-sm-6">
			<div data-ng-controller="myCommunesCTRL"
				class="col-lg-10 col-md-10  col-xs-10 col-sm-10">
				<p>Liste des Communes</p>

				<table class="table table-bordered">
					<tr>
						<th>Id</th>
						<th>Nom commune</th>
						<th>Id_maire</th>
						<th>Nom_maire</th>
						<th>Prenom_maire</th>
					</tr>
					<tr data-ng-repeat="x in communes">
						<th>{{x.id }}</th>
						<th>{{x.nom }}</th>
						<th>{{x.maire.id }}</th>
						<th>{{x.maire.nom }}</th>
						<th>{{x.maire.prenom }}</th>
						

						<th>
							<button data-ng-click="removeCommune(x.id)">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
					</tr>
				</table>



			</div>
		</div>
		<div class="col-lg-6 col-md-6  col-xs-6 col-sm-6">
			<h4>Mise en relation Commune et Maire</h4>

			<div data-ng-controller='relationctrl'>
				<label for="textarea">Id commune :</label><input type="number"
					data-ng-model='idcommune'> <label for="textarea">Id
					maire :</label><input type="number" data-ng-model='idmaire'>
				<button data-ng-click="metEnRelation()" type="submit"
					class="btn btn-info">Validez</button>
			</div>


		</div>

	</div>


	<script>
		var app = angular.module('monApp', []);

		function getALLcommune($scope, $http) {
			var url = "http://localhost:8080/tp-java-rest/rest/commune/retrieveAll";

			$http.get(url).then(successCallback, errorCallback);

			function successCallback(response) {
				console.log("success: " + response.data);
				$scope.communes = [];
				response.data.forEach(function(element) {
					$scope.communes.push(element);
				});
			}

			function errorCallback(response) {
				console.log('err', err);
			}

			$scope.removeCommune = function(id) {

				var url = "http://localhost:8080/tp-java-rest/rest/commune/delete/"+ id;

				$http.get(url).then(successCallback1, errorCallback1);
				function successCallback1(response) {
					console.log("success: " + response.data);
							 location.reload(); 
				}
				function errorCallback1(response) {
					console.log('err', err);
				}

			};

		}
		
		function gereRelation($scope, $http) {
		
		$scope.metEnRelation = function() {
				var url = "http://localhost:8080/tp-java-rest/rest/commune/"+$scope.idcommune+"/maire/"+$scope.idmaire;
				

				$http.get(url).then(successCallback3, errorCallback3);
				
				function successCallback3(response) {
					console.log("success: " + response.data);					
					 location.reload(); 
				}
				
				function errorCallback3(response) {
					console.log(url);
					location.reload(); 
				}

			};
			}
		app.controller('relationctrl', gereRelation);

		app.controller('myCommunesCTRL', getALLcommune);
	</script>




</body>
</html>