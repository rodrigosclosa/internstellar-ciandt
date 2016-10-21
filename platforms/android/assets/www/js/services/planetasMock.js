angular.module('app.services')

  .factory('PlanetasMock', function () {

    // Somente dados para teste
    var planetas = [{
      id: 0,
      name: 'Planeta 1',
      lastText: 'Tem certeza?',
      face: 'img/ben.png'
    }, {
      id: 1,
      name: 'Planeta 2',
      lastText: 'Tudo tranquilo...',
      face: 'img/max.png'
    }, {
      id: 2,
      name: 'Planeta 3',
      lastText: 'Sem legenda',
      face: 'img/adam.jpg'
    }];

    return {
      all: function () {
        return planetas;
      },
      remove: function (planeta) {
        planetas.splice(planetas.indexOf(planeta), 1);
      },
      get: function (planetaId) {
        for (var i = 0; i < planetas.length; i++) {
          if (planetas[i].id === parseInt(planetaId)) {
            return planetas[i];
          }
        }
        return null;
      }
    };
  });
