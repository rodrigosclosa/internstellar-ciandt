angular.module('app.services')

  .factory('EquipesMock', function () {

    // Somente dados para teste
    var equipes = [{
      id: "2446466346334",
      nome: "Team Fenix",
      cor: "Verde",
      base: "Campinas",
      imagem: "http://internstellar-ciandt.appspot.com/images/azul/fenix.png"
    }, {
      id: "4983967634324",
      nome: "Team Aguia",
      cor: "Azul",
      base: "Campinas",
      imagem: "http://internstellar-ciandt.appspot.com/images/azul/aguia.png"
    }, {
      id: "4235454246246",
      nome: "Team Centauro",
      cor: "Verde",
      base: "Campinas",
      imagem: "http://internstellar-ciandt.appspot.com/images/verde/centauro.png"
    }];

    return {
      all: function () {
        return equipes;
      },
      get: function (equipeId) {
        for (var i = 0; i < equipes.length; i++) {
          if (equipes[i].id === parseInt(equipeId)) {
            return equipes[i];
          }
        }
        return null;
      }
    };
  });
