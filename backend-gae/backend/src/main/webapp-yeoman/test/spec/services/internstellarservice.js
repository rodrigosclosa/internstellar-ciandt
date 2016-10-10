'use strict';

describe('Service: InternstellarService', function () {

  // load the service's module
  beforeEach(module('internstellarDashApp'));

  // instantiate service
  var InternstellarService;
  beforeEach(inject(function (_InternstellarService_) {
    InternstellarService = _InternstellarService_;
  }));

  it('should do something', function () {
    expect(!!InternstellarService).toBe(true);
  });

});
